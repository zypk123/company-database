package cy.its.service.convertFlow.service;

import java.util.Date;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;

import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.util.RedisPoolUtil;
import cy.its.service.util.StringUtils;

/**
* @Title: ConverFlowService.java 
* @Package cy.its.road.convertFlow.service 
* @Description:断面数据接收，只存储当前账期的的数据，或是未来账期的数据
* @author lil@cychina.cn
* @date 2015年10月28日 下午1:49:16 
* @version V1.0   
 */
public class ConverFlowService implements IReceiver{
	
	/**
	 * 日志文件
	 */
	private static  Logger log=Logger.getLogger(ConverFlowService.class);
	/**
	 * 5分钟数据，保存2个小时的数据信息，数据库字段不保存一个小时的数据
	 * 数据接入设置过期时间2个小时10分钟
	 * 数据KEY不能太长，redis采f:regionId:时间----value数值
	 * 延迟的数据计算总量，更新或插入记录，不做推送
	 * 延迟数据采用 redis  hashset 方式保存
	 * 延迟的数据 在5分钟定时计算之前先计算
	 */
	@Override
	public Boolean receive(String arg0, String jsonstring) {
		
		JSONObject   jsonObject  = JSONObject.parseObject(jsonstring);
		Date  currentTime  = new Date();
		//获取当前周期，服务器的时间
		long  key = getPeriodkey(currentTime);
		//获取统计日期的时间
		Date   dataDate  = jsonObject.getDate("countTime");
		//传入数据的周期
		long dataKey = getPeriodkey(dataDate);
		//点位加方向类型作为KEY值
		//找出断面的ID，如果找不到则程序不做处理
		String  sectionId  = CacheManager.getSectionByKey(jsonObject.getString("siteCode")+":"+jsonObject.getString("directionType"));
		if(StringUtils.isEmpty(sectionId)){
			return true;
		}
		//断面ID
		jsonObject.put("sectiondId",sectionId);
		//如果DATA日期等于当前的key日期
		if(key==dataKey){
			addObjectToRedis(key,"m:"+sectionId,jsonObject);
		}else{
			//如果统计时间大于当前时间则把数据放入统计时间中dataKey
			if(dataKey > key){
			    //直接放入新的周期中
			   addObjectToRedis(dataKey,"m:"+sectionId,jsonObject);
			}else{
			   //补录数据，更新数据库信息，补录信息，只更新5数据库5 分钟的信息 一小时的数据不做处理
			   log.info("延迟数据："+sectionId+":"+dataKey+":放入redis缓存！");
			   delayComputeObject(dataKey,key,sectionId,jsonObject);
			}
		}
		return true;
	}

	/** 
	* @Title: delayComputeObject 
	* @Description:补录数据，先判定当前数据周期 缓存是否存在
	* @param @param dataKey
	* @param @param secondId
	* @param @param jsonObject    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void delayComputeObject(long lastKey,long  currKey,String secondId,
			JSONObject jsonObject) {
		//缓存存放的时间为13个周期数据，即如计算8点5分的数据  则需要 8点05- 7点05 ，还有 8点--7点的数据  流量波动暂时不做
//		long  min  = currKey - 5*60*1000;//5 分钟的周期
//		if(lastKey > min && lastKey < currKey){
//			 //更新缓存数据
//			 addObjectToRedis(lastKey,"m:"+secondId,jsonObject);
//		}
		//更新redis信息
		addObjectToRedis(lastKey,"d:"+secondId,jsonObject);
	}
	/** 
	* @Title: addObjectToRedis 
	* @Description: 把记录加入缓存中，以供后续汇总使用
	* @param @param key regionId+时间+
	* @param @param filed
	* @param @param jsonObject    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void addObjectToRedis(long key,String secondId, JSONObject jsonObject) {
		
		RedisPoolUtil.lpush(secondId+":"+key, jsonObject.toJSONString());
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
		int  s = value/10;
		value = value%10;
		if(value>=5){
			currentTime.setMinutes(s*10+5);
		}else{
			currentTime.setMinutes(s*10);
		}
		currentTime.setSeconds(0);
		//现在的周期落后5分钟，因此需要加5分钟
		currentTime.setTime(currentTime.getTime());
		return 1000*(currentTime.getTime()/1000);
	}
}
