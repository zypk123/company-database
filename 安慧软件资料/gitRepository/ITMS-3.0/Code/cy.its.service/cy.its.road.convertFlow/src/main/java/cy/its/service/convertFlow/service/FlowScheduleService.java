package cy.its.service.convertFlow.service;

import java.lang.reflect.Field;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.convertFlow.domain.TrafficSectionFlow;
import cy.its.service.convertFlow.task.TrafficFlowExcute;
import cy.its.service.util.DBUtil;
import cy.its.service.util.ExecutorServiceUtil;
import cy.its.service.util.RedisPoolUtil;
import cy.its.service.util.StringUtils;
import cy.its.service.util.TableColumn;


/**
* @Title: FlowScheduleService.java 
* @Package cy.its.road.convertFlow.service 
* @Description: 5分钟定时任务
* @author lil@cychina.cn
* @date 2015年10月29日 下午4:16:26 
* @version V1.0   
 */
public class FlowScheduleService implements Job {
	//
	private SimpleDateFormat  df2  = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	//数据格式化
	DecimalFormat df3 = new DecimalFormat("#.00"); 
	
	/**
	 * 日志文件
	 */
	private static  Logger log=Logger.getLogger(FlowScheduleService.class);
	/* 
	 * 定时任务需要执行的方法
	 */
	public void execute(JobExecutionContext context) throws JobExecutionException {
		int  count = 0;
		//获取统计账期
		Date  currentTime  = new Date();
		long  key = getPeriodkey(currentTime);
		
		if(key != CacheManager.cuurnKey){
			log.info("当前周期："+key+",上一周期："+CacheManager.cuurnKey);
			CacheManager.initSectionist();
		}
		//通过账期获取数据
		//首先处理延迟记录数据，每一个sectionId + 时间作为一个处理单元
		Set<String>  dsets = RedisPoolUtil.keys("d:*");
		TrafficFlowExcute  excuteTask = new TrafficFlowExcute();
		//dsets != null
		if(dsets != null){
			for (String  keys : dsets) {  
				List<String>  array  = RedisPoolUtil.lranage(keys, 0, -1);
				//删除数据
				RedisPoolUtil.del(keys);
				TrafficSectionFlow  flow  = getTrafficSectionFlow(key,array);
				//判断是更新还是新增  断面 +账期
				try {
					processDelay(flow,excuteTask);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		if(excuteTask.sqlList.size()>0){
			log.info("批量处理延迟记录数据为："+excuteTask.sqlList.size());
			//添加到执行任务中
			ExecutorServiceUtil.addTask(excuteTask);
		}
		//5分钟汇总数据
		Map<String,String>  map  = CacheManager.getSectionMap();
		for(String tmp : map.keySet()){
			String  sectionId  = map.get(tmp);
			String  keyinfo = "m:"+sectionId+":"+key;
			List<String>  array  = RedisPoolUtil.lranage(keyinfo, 0, -1);
			if(array.size()==0){
				continue;
			}
			TrafficSectionFlow  flow  = getTrafficSectionFlow(key,array);
			//如果为空不做处理
			if(flow == null){
				log.info("断面："+sectionId+"当前周期没有数据接入。");
				continue;
			}
			try {
				//插入数据
				DBUtil.execute(getSql(flow));
				//发布信息
				MQGateWay.publish("its_section_flow", JSONObject.toJSONString(flow));
				
				log.info("断面："+sectionId+"信息已推送。");
				
				count++;
				
			} catch (IllegalArgumentException | IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			//计算1小时的数据信息，上周期1小时数据信息,保留数据5分钟数据
			RedisPoolUtil.del(keyinfo);
			//计算1小时流量，因为要频繁的访问redis因此需要排队处理,每个断面一个任务
//			OneHourTask  onetask  =  new   OneHourTask();
//			onetask.currKey  = key;
//			onetask.sectionId  = sectionId;
//			ExecutorServiceUtil.addTask(onetask);
		}
		log.info("当前周期发送"+count+"条记录！");
	}
	
	/**
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException  
	* @Title: processDelay 
	* @Description: 处理延迟周期的数据
	* @param @param flow    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void processDelay(TrafficSectionFlow flow,TrafficFlowExcute excuteTask) throws IllegalArgumentException, IllegalAccessException {
		//判定是否存在
		String  sql  = " select  count(1) CNUM from t_traffic_section_flow  where section_id = '"+flow.getSectionId()+"' and update_time = to_date('"+df2.format(flow.getUpdateTime())+"','yyyy-mm-dd hh24:mi:ss')";
		JSONArray  jsonObj  =  DBUtil.queryJSONData(sql);
		JSONObject  jsonObject  = (JSONObject)jsonObj.get(0);
		int  count = jsonObject.getIntValue("CNUM");
		if(count >0){
			//更新
			excuteTask.sqlList.add(getUpdateSql(flow));
		}else {
			//插入
			excuteTask.sqlList.add(getSql(flow));
		}
	}
	/** 
	* @Title: getUpdateSql 
	* @Description: 获取更新语句
	* @param @param flow
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	private String getUpdateSql(TrafficSectionFlow flow) {
		String  updateSql = " update t_traffic_section_flow  set  ";
		updateSql  += " total_num = total_num + "+flow.getTotalNum()+",";
		updateSql  += " large_num = large_num + "+flow.getLargeNum()+",";
		updateSql  += " small_num = small_num + "+flow.getSmallNum()+",";
		updateSql  += " other_num = other_num + "+flow.getOtherNum()+",";
		updateSql  += " avg_speed =case when total_num+"+flow.getTotalNum()+" = 0 then 0.00 else  trunc(avg_speed*total_num +"+flow.getAvgSpeed()*flow.getTotalNum()+")/(total_num+"+flow.getTotalNum()+"),2) end, ";
		updateSql  += " veh_tail_space =case when total_num+"+flow.getTotalNum()+" = 0 then 0.00 else trunc(veh_tail_space*total_num +"+flow.getVehTailSpace()*flow.getTotalNum()+")/(total_num+"+flow.getTotalNum()+"),2) end ";
		updateSql  += " where  = section_id + '"+flow.getSectionId()+"'  and  update_time = to_date('"+df2.format(flow.getUpdateTime())+"','yyyy-mm-dd hh24:mi:ss')";
		return updateSql;
	}

	/**
	 * @param key  
	* @Title: getTrafficSectionFlow 
	* @Description: 汇总数据
	* @param @param  array
	* @param @return 设定文件 
	* @return JSONObject 返回类型 
	* @throws 
	*/
	private TrafficSectionFlow getTrafficSectionFlow(long timekey, List<String> array) {
		if(array.size() ==0){
			return null;
		}
		TrafficSectionFlow object  = new TrafficSectionFlow();
		JSONObject jsonObject = JSONObject.parseObject(array.get(0));
		String sectionStateId  = UUID.randomUUID().toString().replace("-","");
		String  secondId  = jsonObject.getString("sectiondId");
		if(StringUtils.isEmpty(secondId)){
			return null; //跳出方法
		}
		int totalNum=0;
	    int largeNum=0;
	    int smallNum=0;
	    int otherNum=0;
	    double avgSpeed=0;
	    double vehTailSpace=0;
		for(int i=0;i<array.size();i++){
			JSONObject  obj  = JSONObject.parseObject(array.get(i));
			totalNum += obj.getIntValue("totalNum");
			largeNum += obj.getIntValue("largeNum");
			smallNum += obj.getIntValue("smallNum");
			otherNum += obj.getIntValue("totalNum")-obj.getIntValue("largeNum")-obj.getIntValue("smallNum");
			avgSpeed += obj.getIntValue("totalNum")*obj.getDoubleValue("avrSpeed");
			vehTailSpace+= obj.getIntValue("totalNums")*obj.getDoubleValue("vehTailSpace");
		}
		int  devideNum = totalNum == 0 ? 1:totalNum;
		String avgValue = df3.format(avgSpeed/devideNum);
		String vehValue= df3.format(vehTailSpace/devideNum);
		object.setSectionStateId(sectionStateId);
		object.setSectionId(secondId);
		object.setTotalNum(totalNum);
		object.setLargeNum(largeNum);
		object.setSmallNum(smallNum);
		object.setOtherNum(otherNum);
		object.setAvgSpeed(Double.valueOf(avgValue));
		object.setVehTailSpace(Double.valueOf(vehValue));
		object.setUpdateTime(getUpdateTime(timekey));
		object.setOrgPrivilegeCode(jsonObject.getString("orgPrivilegeCode"));
		return object;
	}

	/** 
	* @Title: getPeriodkey 
	* @Description:根据传入的日期算出当前处于那个周期
	* @param @param format
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	private long getPeriodkey(Date currentTime) {
		int  value  = currentTime.getMinutes();
		int s = value/10;
		value = value%10;
		if(value>=5){
			currentTime.setMinutes(s*10+5);
		}else{
			currentTime.setMinutes(s*10);
		}
		currentTime.setSeconds(0);
		currentTime.setTime(currentTime.getTime()-1000*5*60);
		return 1000*(currentTime.getTime()/1000);
	}
	
	/** 
	* @Title: getUpdateTime 
	* @Description: 数据库更新时间 
	* @param @param dataKey
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	private Date getUpdateTime(long dataKey) {
		Date   date  = new Date();
		date.setTime(dataKey);
		return date;
	}

	/**
	 * @throws IllegalAccessException 
	 * @throws IllegalArgumentException  
	* @Title: getSql 
	* @Description:获取SQL执行语句
	* @param @param alarm
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	public String getSql(TrafficSectionFlow alarm) throws IllegalArgumentException, IllegalAccessException {
		StringBuffer   sb = new StringBuffer();
		StringBuffer   sb2 = new StringBuffer();
		sb.append(" insert  into t_traffic_section_flow( ");
		sb2.append(" values( ");
		Field[] fs = TrafficSectionFlow.class.getDeclaredFields(); 
		for(int i = 0 ; i < fs.length; i++){  
			 Field f = fs[i];  
			 if(f.isAnnotationPresent(TableColumn.class)){
				 TableColumn tableColumn = f.getAnnotation(TableColumn.class);
				 sb.append(tableColumn.name()+",");
				 f.setAccessible(true); //设置些属性是可以访问的 
				 Object val = f.get(alarm);//得到此属性的值
				 String type = f.getType().toString();//得到此属性的类型 
				 if (type.endsWith("String")) {  
					 sb2.append("'"+val+"',");
				 }else if(type.endsWith("Date")){
					 sb2.append(" to_date('"+df2.format(val)+"','yyyy-mm-dd hh24:mi:ss'),");
				 }else{
					 sb2.append(val+",");
				 }
	         }
		}
		if(sb.length() > 1){
			sb.deleteCharAt(sb.length()-1);
		}
		if(sb2.length() > 1){
			sb2.deleteCharAt(sb2.length()-1);
		}
		sb.append(" ) ");
		sb2.append(") ");
		return sb.toString() + sb2.toString();
	}
	
}
