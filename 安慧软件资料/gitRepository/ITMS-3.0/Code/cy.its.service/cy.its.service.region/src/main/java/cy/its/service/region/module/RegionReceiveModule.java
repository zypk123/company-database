package cy.its.service.region.module;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import cy.its.service.common.rabbitmqClient.IReceiver;
import cy.its.service.region.domain.Region;
import cy.its.service.region.domain.VehTrackPass;
import cy.its.service.util.RedisPoolUtil;
import cy.its.service.util.StringUtils;
import cy.its.service.util.TimeUtil;


/**
* @Title: RegionReceiveModule.java 
* @Package cy.its.service.Region.module 
* @Description: 区间平均车速利用过车计算
* @author lil@cychina.cn
* @date 2015年11月4日 下午1:49:57 
* @version V1.0   
 */
public class RegionReceiveModule implements IReceiver{
	
	private static  Logger log=Logger.getLogger(RegionReceiveModule.class);
	
	//数据格式化
	DecimalFormat df3 = new DecimalFormat("#.00");
	
	private  DecimalFormat df = new DecimalFormat("#.0000"); 
	
	private SimpleDateFormat  df2  = new  SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	
	private  String  twoHourkey=null;
	
	/* (non-Javadoc)
	 * @see cy.its.service.com.rabbitmqClient.IReceiver#receive(java.lang.String, java.lang.String)
	 * 接收过车数据
	 * 接入过车数据
	 * 查找该节点属于那个区间（多数情况属于两个区间）
	 * 查看该点位是开始点位还是结束点位（一般匹配到起点或终点）
	 * 起始点则把数据存入起点信息
	 * 终点则计算平均车速放入终点
	 * 计算完成则删除起点信息
	 * 如果存在号码未识别的可能一直不过终点，则需要排除掉，最大保存2个小时
	 * 如果只有终点信息则舍弃该条记录不做处理
	 */
	@Override
	public Boolean receive(String arg0, String jsonObject) {
		//过车记录
		VehTrackPass vp = JSONObject.parseObject(jsonObject,VehTrackPass.class);
		
		twoHourkey  = TimeUtil.twoHoursKey(vp.getPassTime());
		if(vp!=null){
			if("未知".equals(vp.getPlateNbr())){
				return true;
			}
		}
		List<Region> Lists = CacheManager.getDataList();
		
		for(Region redgion:Lists){
			if(redgion.getStartSiteCode() != null&&redgion.getDirectionType() != null){
				/**
				 * 起点匹配
				 */
				if(redgion.getStartSiteCode().equals(vp.getSiteCode())&&redgion.getDirectionType().equals(vp.getDirectionType())){
					//起点
					RedisPoolUtil.hset(twoHourkey+"-"+redgion.getRegionalId(), vp.getPlateNbr()+":"+vp.getPlateColor(), JSONObject.toJSONString(vp));
				}
				/**
				 * 终点匹配
				 */
				if(redgion.getEndSiteCode() != null&&redgion.getDirectionType() != null){
					if(redgion.getEndSiteCode().equals(vp.getSiteCode())&&redgion.getDirectionType().equals(vp.getDirectionType())){
						processEndSite(redgion,vp);
					}
				}
			}
		}
		
		return  true;
	}

	/** 
	* @Title: processEndSite 
	* @Description: 区间终点处理
	* @param @param vpl
	* @param @param vp    设定文件 
	* @return void    返回类型 
	* @throws 
	*/
	private void processEndSite(Region vpl,VehTrackPass vp) {
		//如果区间内有相同的号牌加号牌颜色的车，起点只记录最新一条记录
		String  str   =  RedisPoolUtil.hget(twoHourkey+"-"+vpl.getRegionalId(), vp.getPlateNbr()+":"+vp.getPlateColor());
		//起点没记录则，不做处理
		if(StringUtils.isEmpty(str)){
			return;
		}
		VehTrackPass  startVp  = JSONObject.parseObject(str,VehTrackPass.class);
		//找到起点记录后则计算时间
		process(vpl,vp,startVp);
	}

	private void process(Region vpl,VehTrackPass vp, VehTrackPass startVp) {
		Date  startDate = startVp.getPassTime();
		Date  endDate = vp.getPassTime();
		if(endDate.getTime() < startDate.getTime()){
			log.info("起点时间大于终点时间，数据异常，不做处理");
		}
		if(startDate !=null&&endDate !=null){
			long  value  = endDate.getTime()-startDate.getTime();
			//时间换算成小时
			double  time = Double.valueOf(df.format(value*1.00/(1000)));
			double  distance  = Double.valueOf(vpl.getDistance());
			double  avgSpeed= Double.valueOf(df.format(distance*1.00*60*60/time));
			JSONObject   jsonobj = new  JSONObject();
			jsonobj.put("orgCode",vp.getOrgCode());//机构
			jsonobj.put("avgTravelTime",time);//时间秒
			jsonobj.put("updateTime",df2.format(vp.getPassTime()));
			jsonobj.put("avgSpeed",avgSpeed);
			jsonobj.put("roadType",vpl.getRoadType());
			jsonobj.put("plateNbr",vp.getPlateNbr());//车牌号码
			jsonobj.put("plateColor",vp.getPlateColor());//车牌号码
			//把计算结果放入终点的缓存中
			log.info("区间有数据信息："+"pv:"+twoHourkey+"-"+vpl.getRegionalId()+"，号牌号码："+vp.getPlateNbr());
			RedisPoolUtil.lpush("pv:"+twoHourkey+"-"+vpl.getRegionalId(), jsonobj.toJSONString());
	    }
	}

}
