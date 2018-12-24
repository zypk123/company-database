package cy.its.service.convertFlow.task;

import java.util.ArrayList;
import java.util.List;

import com.alibaba.fastjson.JSONObject;

import cy.its.service.common.rabbitmqClient.MQGateWay;
import cy.its.service.util.RedisPoolUtil;

/**
* @Title: OneHourTask.java 
* @Package cy.its.service.convertFlow.task 
* @Description:一小时分析，相对御5分钟滞后
* @author lil@cychina.cn
* @date 2015年12月22日 上午8:57:25 
* @version V1.0   
 */
public class OneHourTask implements Runnable {

	//分析当前周期
	public long   currKey = 0;
	
	// 当前断面
	public  String   sectionId= "";
	
	@Override
	public void run() {
		JSONObject   jsonobj = new JSONObject();
		//需要访问最近一小时的数据，计算出所有的总数，做波动预警分析
		//获取所有LIST
		List  listall =  new ArrayList();
		
		for(int i=1;i<12;i++){
			long key = currKey - i*5*60*1000;
			String  keyinfo = "m:"+sectionId+":"+key;
			listall.addAll(RedisPoolUtil.lranage(keyinfo, 0, -1));
		}
		int total = 0;
		//前面没数据，只计算当前时间数据
		if(listall.size() > 0){
			//所有的LSIT
			for(int i=0;i<listall.size();i++){
				JSONObject  temp  = JSONObject.parseObject(listall.get(i).toString());
				if(i == 0){
					jsonobj.put("secondId", temp.getString("secondId"));
					jsonobj.put("orgPrivilegeCode", temp.getString("orgPrivilegeCode"));
					jsonobj.put("totalNum",0);
				}
				total += temp.getIntValue("totalNum");
			}
			jsonobj.put("totalNum",total);
			jsonobj.put("lastTotalNum",total);
			
			String lastkey = "m:"+sectionId+":"+(currKey-12*5*60*1000);
			total  = 0;
			List<String>  list  = RedisPoolUtil.lranage(lastkey, 0, -1);
			for(String  str :list){
				JSONObject  temp  = JSONObject.parseObject(str);
				total += temp.getIntValue("totalNum");
			}
			jsonobj.put("lastTotalNum",jsonobj.getIntValue("lastTotalNum")+total);
			
			String key = "m:"+sectionId+":"+currKey;
			list  = RedisPoolUtil.lranage(key, 0, -1);
			total  = 0;
			for(String  str :list){
				JSONObject  temp  = JSONObject.parseObject(str);
				total += temp.getIntValue("totalNum");
			}
			jsonobj.put("totalNum",jsonobj.getIntValue("totalNum")+total);
		}else{
			//计算当前的
			String key = "m:"+sectionId+":"+currKey;
			List<String> list  = RedisPoolUtil.lranage(key, 0, -1);
			total  = 0;
			for(String  str :list){
				JSONObject  temp  = JSONObject.parseObject(str);
				if(total == 0){
					jsonobj.put("secondId", temp.getString("secondId"));
					jsonobj.put("orgPrivilegeCode", temp.getString("orgPrivilegeCode"));
					jsonobj.put("totalNum",0);
				}
				total += temp.getIntValue("totalNum");
			}
			jsonobj.put("totalNum",jsonobj.getIntValue("totalNum")+total);
		}
		if(jsonobj.get("secondId") != null){
			//发布消息
			MQGateWay.publish("its_section_hour_flow", jsonobj.toJSONString());
		}
	}

}
