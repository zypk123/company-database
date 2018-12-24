package cy.its.service.region.module;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.alibaba.fastjson.JSONObject;

import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.region.domain.Region;
import cy.its.service.region.domain.RegionSpeed;
import cy.its.service.region.domain.TrafficAlarmValue;
import cy.its.service.util.RedisPoolUtil;
import cy.its.service.util.TimeUtil;



/**
* @Title: RegionScheduleService.java 
* @Package cy.its.service.region.module 
* @Description: 区间5分钟调度程序 
* @author lil@cychina.cn
* @date 2015年12月4日 下午4:14:17 
* @version V1.0   
 */
public class RegionScheduleService  implements Job {
	
	private static  Logger log=Logger.getLogger(RegionScheduleService.class);
    //日期以分为主
	DecimalFormat df3 = new DecimalFormat("#.00");
	
	private  double   a_r  = 1.5;
	
    /**
     * 任务启动程序，5分钟统计一次
     */
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
		//5 分钟刷新一次
		CacheManager.initRegionList();
		CacheManager.initTrafficAlarmList();
		//获取所有生效区间
		List<Region> list  = CacheManager.getDataList();
		//是否考虑一次获取所有记录，然后内存运算
		final Date currentTime =  new Date();
		//时间区间结束
		final Date endTime = getPeriodkey(currentTime);
		
		//两小时KEY,当前时间可能滞后，尤其在整点时，取数据应该是上个小时的数据，因此需要 - 3分钟的时间
		final String twoHoursKey = TimeUtil.twoHoursKey(new Date(),3);
		//结束
		final Date startTime=TimeUtil.reduceFiveMinute(endTime);
		//遍历所有的区间终点，然后进行处理
		for(Region st:list){
			//设置过期时间
			expoireKey(twoHoursKey,st.getRegionalId());
			List<String> strs = RedisPoolUtil.lranage("pv:"+twoHoursKey+"-"+st.getRegionalId(), 0, -1);
			RegionSpeed   ssp = new RegionSpeed();
			ssp.setRegionalId(st.getRegionalId());
			ssp.setUpdateTime(endTime);
			ssp.setOrgPrivilegeCode(st.getOrgPrivilegeCode());
			//获取新的LIST
			List<RegionSpeed> newList  = getNewList(strs,startTime,endTime,st.getRegionalId());
			try {
				//对list进行排序
				newList = newList.stream().sorted((s1,s2)->{
				    return (int) (s1.getAvgSpeed()-s2.getAvgSpeed());
				}).collect(Collectors.toList());
				//如果没有记录速度为零
				if(newList.size()==0){
					ssp.setAvgSpeed(0);
					ssp.setRoadType("0");
				}else{
					ssp.setRoadType(newList.get(0).getRoadType());
					ssp.setOrgCode(newList.get(0).getOrgCode());
				}
				//计算平均值
				setAvgBylist(ssp,newList);
			    //推送数据
			    processStatus(ssp,st.getEndSiteCode());
			} catch (Exception e) {	
				// TODO Auto-generated catch  w
				log.error("区间："+st.getRegionalId()+"，计算出现问题");
				e.printStackTrace();
			}
		}
	}
	
	private void setAvgBylist(RegionSpeed ssp, List<RegionSpeed> newList) {
		// TODO Auto-generated method stub
	    if(newList.size()<10){
	    	//计算平均值
	    	double avgSpeed  = (double) newList.stream().collect(Collectors.averagingDouble(RegionSpeed::getAvgSpeed));
	    	double avgTravlTime  = (double) newList.stream().collect(Collectors.averagingDouble(RegionSpeed::getAvgTravelTime));
	    	ssp.setAvgSpeed(Double.valueOf(df3.format(avgSpeed)));
	    	ssp.setAvgTravelTime(Double.valueOf(df3.format(avgTravlTime)));
	    }
	    //如果大于样本数则采用五数概括法则计算，平均速度
	    if(newList.size()>=10){
	    	//五数计算法
	    	double  Value41  = ((RegionSpeed)newList.get(newList.size()/4)).getAvgSpeed();
	    	double  Value43  = ((RegionSpeed)newList.get(newList.size()*3/4)).getAvgSpeed();
	    	double  IRQ  = Value43 - Value41;
	    	final double minValue = Value41 - IRQ*a_r;
	    	final double maxValue = Value43 + IRQ*a_r;
	    	//剔除不需要的数据算平均值
	    	List<RegionSpeed> listrs  = newList.stream().filter(sp->{
	    		if(sp.getAvgSpeed()>= minValue && sp.getAvgSpeed()<=maxValue){
	    			return true; 
	    		}
	    		return false;
	    	}).collect(Collectors.toList());
	    	//
	    	double avgSpeed   = listrs.stream().collect(Collectors.averagingDouble(RegionSpeed::getAvgSpeed));
	    	double avgTime  = listrs.stream().collect(Collectors.averagingDouble(RegionSpeed::getAvgTravelTime));
	    	ssp.setAvgSpeed(Double.valueOf(df3.format(avgSpeed)));
	    	ssp.setAvgTravelTime(Double.valueOf(df3.format(avgTime)));
	    }
	}

	private List<RegionSpeed> getNewList(List<String> strs, Date startTime, Date endTime, String regionalId) {
		List<RegionSpeed> newList = new ArrayList();
		for(int i=0;i<strs.size();i++){
			//时间在5分钟内的数据，超过或大于都不做处理
			RegionSpeed jsonobj  = JSONObject.parseObject(strs.get(i),RegionSpeed.class);
			//记录号牌号码 plateNbr
			String plateNbr  = JSONObject.parseObject(strs.get(i)).getString("plateNbr");
			Date passTime=null;
			try {
				passTime =jsonobj.getUpdateTime();
			} catch (Exception e) {
				log.error("区间过车时间转换出现问题！");
				log.error(e);
			}
			//如果时间为空，则直接返回
			if(passTime ==null ){
				continue;
			}
			if(passTime.getTime() > startTime.getTime() && passTime.getTime() <= endTime.getTime()){
				log.info("区间ID:"+regionalId+",过车号码："+plateNbr);
				//加入处理单元
				newList.add(jsonobj);
			}
		}
		return newList;
	}

	/**
	 * @param twoHoursKey
	 * @param regionalId
	 * 设置KEY 过期两个小时
	 */
	private void expoireKey(String twoHoursKey,String regionalId) {
		if(RedisPoolUtil.exists(twoHoursKey+"-"+regionalId)&&RedisPoolUtil.pttl(twoHoursKey+"-"+regionalId)==-1){
			RedisPoolUtil.expire(twoHoursKey+"-"+regionalId,60*60*2);
		}
		if(RedisPoolUtil.exists("pv:"+twoHoursKey+"-"+regionalId)&&RedisPoolUtil.pttl("pv:"+twoHoursKey+"-"+regionalId)==-1){
			RedisPoolUtil.expire("pv:"+twoHoursKey+"-"+regionalId,60*60*2);
		}

	}

	/**
	 * @param publishList 
	 * @param string  
	* @Title: processStatus 
	* @Description:根据速度计算区间状态，同时排除交通管制的点位
	* @param @param ssp 设定文件 
	* @return void 返回类型 
	* @throws 
	*/
	private void processStatus(RegionSpeed ssp, String siteCode) {
		//受管制的点位信息
		ssp.setTrafficState("0");
		//获取状态阈值信息,根据状态的阈值定义区间状态
		List<TrafficAlarmValue> list  =CacheManager.getTrafficAlarmDataList();
		
		for(TrafficAlarmValue tdv:list){
			if(ssp.getAvgSpeed()>tdv.getMin() && ssp.getAvgSpeed() <=tdv.getMax()){
				ssp.setTrafficState(tdv.getAlermGrade());
			}
		}
		MQGateWay.publish("its_region_speed",JSONObject.toJSONString(ssp));
		log.info("推送区间数据："+JSONObject.toJSONString(ssp));
	}

	/**
	 * @throws ParseException  
	* @Title: getPeriodkey 
	* @Description:根据传入的日期算出当前处于那个周期
	* @param @param format
	* @param @return 设定文件 
	* @return String 返回类型 
	* @throws 
	*/
	private  Date getPeriodkey(Date currentTime) {
		int  value  = currentTime.getMinutes();
		int  s = value/10;
		value = value%10;
		if(value>=5){
			currentTime.setMinutes(s*10+5);
		}else{
			currentTime.setMinutes(s*10);
		}
		currentTime.setSeconds(0);
		return currentTime;
	}
}
