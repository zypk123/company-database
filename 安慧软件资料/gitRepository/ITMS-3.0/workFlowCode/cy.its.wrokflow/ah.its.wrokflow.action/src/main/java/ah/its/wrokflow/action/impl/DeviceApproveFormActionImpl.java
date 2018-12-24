package ah.its.wrokflow.action.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.StringUtil;

import ah.its.workFlow.util.CodeGenUtil;
import ah.its.wrokflow.action.DeviceApproveFormActionI;
import ah.its.wrokflow.action.WorkflowEngineActionI;
import ah.its.wrokflow.domain.DeviceApproveServiceI;
import ah.its.wrokflow.dto.DeviceApproveDto;
import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.dao.DeviceApprove;
import ah.its.wrokflow.repository.dao.DeviceApproveResult;

/**
* @Title: DeviceApproveFormActionImpl.java 
* @Package ah.its.wrokflow.action.impl 
* @Description: 设备申报申请单
* @author lil@cychina.cn
* @date 2016年4月17日 上午5:02:58 
* @version V1.0   
 */
@RestController
@RequestMapping("/workFlow/dAservice")
public class DeviceApproveFormActionImpl implements DeviceApproveFormActionI {

	
	
	@Autowired
	private DeviceApproveServiceI deviceApproveServiceImpl;//用户操作领域类
	
	@Autowired
	private WorkflowEngineActionI workflowEngineActionImpl;//流程处理类
	
	@Autowired
	private ApproveChkActionImpl   approveChkActionImplmpl;
	/**
	 * 申请单录入
	 */
	
	@Override
	@RequestMapping(value = "/saveDAFrom")
	public Map saveDeviceApprove(@RequestBody DeviceApprove form) {
		String  deviceApproveId = form.getDeviceApproveId();
		if(StringUtil.isNotEmpty(deviceApproveId)){//修改
			deviceApproveServiceImpl.updateDeviceApprove(form);
		}else{//新增
			form.setDeviceApproveId(CodeGenUtil.getUDDICode());
			form.setApproveTime(new Date());
			form.setDisabled("0");
			form.setStatus("0");
			deviceApproveServiceImpl.saveDeviceApprove(form);
		}
		Map  map  = new HashMap();
		map.put("status", "1");
		return map;
	}
	
	@Override
	@RequestMapping(value = "/saveDAFromSubmit")
	public Map saveDeviceApproveSubmit(@RequestBody DeviceApprove form) {
		Map map = new HashMap();
		saveDeviceApprove(form);
		submitDeviceApprove(form.getProcessId(),form.getDeviceApproveId());
		map.put("status", "1");
		return map;
	}

	@Override
	@RequestMapping(value = "/updateDAFrom")
	public Map updateDeviceApprove(@RequestBody DeviceApprove form) {
		Map map  = new HashMap();
		deviceApproveServiceImpl.updateDeviceApprove(form);
		map.put("status", "1");
		return map;
	}

	@Override
	@RequestMapping(value = "/deleteDeviceApprove")
	public Map deleteDeviceApprove(@RequestParam(value ="approveId") String  approveId) {
		Map map  = new HashMap();
		map.put("approveId", approveId);
		deviceApproveServiceImpl.deleteDeviceApprove(map);
		map.clear();
		map.put("status", "1");
		return map;
	}

	@Override
	@RequestMapping(value = "/queryDeviceApproveData")
	public Map queryDeviceApprove(@RequestBody DeviceApproveDto dto) {
		Map map  = new HashMap();
		map.put("deviceName", dto.getDeviceName());
		map.put("startTime", toDate(dto.getStartTime()));
		map.put("endTime", toDate(dto.getEndTime()));
		map.put("approveStatus", dto.getStatus());
		String groupId= "";
		if(StringUtils.isNotEmpty(dto.getGroupId())){
			groupId= dto.getGroupId().replaceAll("0*$","");
		}
		map.put("groupId",groupId);
		SimpleDateFormat   sd  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//分页，每页显示大概5条记录
		PageHelper.startPage(Integer.valueOf(dto.getStartPage()), Integer.valueOf(dto.getPageSize()));
		List<DeviceApprove>  list  = deviceApproveServiceImpl.selectAllData(map);
		PageInfo page = new PageInfo(list);
		JSONArray  array  =  new JSONArray();
		for(DeviceApprove deviceApprove:list){
			JSONObject  obj = JSONObject.parseObject(JSONObject.toJSONString(deviceApprove));
			obj.put("approveTime",sd.format(deviceApprove.getApproveTime()));
			array.add(obj);
		}
		map.clear();
		map.put("status", "1");
		map.put("data", array);
		map.put("total", page.getTotal());
		return map;
	}
	
	/**
	 * 包含审批记录
	 */
	@Override
	@RequestMapping(value = "/queryDeviceApproveById")
	public Map queryDeviceApproveById(@RequestParam(value ="approveId") String approveId) {
		DeviceApprove  deviceApprove  = deviceApproveServiceImpl.queryDeviceApproveById(approveId);
		Map  map = new HashMap();
		map.put("status", "1");
		map.put("data", deviceApprove);
		return map;
	}
	
	/**
	 * 包含审批记录
	 */
	@Override
	@RequestMapping(value = "/queryDeviceApproveDataById")
	public Map queryDeviceApproveDataById(@RequestParam(value ="approveId") String approveId) {
		Map map  = new HashMap();
		map.put("approveId", approveId);
		SimpleDateFormat   sd  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//
		DeviceApprove deviceApprove  = deviceApproveServiceImpl.queryDeviceApproveById(approveId);
		map.clear();
		JSONObject  obj = JSONObject.parseObject(JSONObject.toJSONString(deviceApprove));
		obj.put("approveTime",sd.format(deviceApprove.getApproveTime()));
		Map  newMap  = approveChkActionImplmpl.queryChkRecord(approveId);
		map.put("status", "1");
		map.put("dataChk",newMap.get("data"));
		map.put("data", obj);
		return map;
	}



	/** 
	* @Description: 判定流程是否完毕，返回下个节点信息
	* @param @param prcesssInsId
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws 
	*/
	private String getNextChk(String prcesssInsId) {
		return workflowEngineActionImpl.getNextChk(prcesssInsId);
	}

	/** 
	* @Description: 把时间LONG 转化为日期
	* @param @param startTime
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	private Object toDate(String time) {
		if(StringUtils.isEmpty(time)){
			return null;
		}
		long  dt =  Long.valueOf(time);
		Date  date  = new  Date();
		date.setTime(dt);
		return date;
	}
	
	/** 
	* @Description: 把字符串日期时间 转化为date日期时间
	* @param @param time
	* @param @return    设定文件 
	* @return Object    返回类型 
	* @throws 
	*/
	private Object StringtoDate(String time) {
		if(StringUtils.isEmpty(time)){
			return null;
		}
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//注意格式化的表达式
		Date d = null;
		try {
			d = format.parse(time );
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}

	@Override
	@RequestMapping(value = "/submitDeviceApprove")
	public Map submitDeviceApprove(@RequestParam(value ="processId") String processId,@RequestParam(value ="deviceApproveId") String deviceApproveId) {
		Map  map = new HashMap();
		String prcossId=workflowEngineActionImpl.startProcessDefinitionByKey(processId,deviceApproveId);
		DeviceApprove  da   = new DeviceApprove();
		da.setDeviceApproveId(deviceApproveId);
		da.setProcessId(prcossId);
		da.setStatus("1");
		updateDeviceApprove(da);
		map.put("status","1");
		return map;
	}

	@Override
	@RequestMapping(value = "/queryDeviceApproveHistoryData")
	public Map queryDeviceApproveHistory(@RequestBody DeviceApproveDto dto) {

		Map map  = new HashMap();
		map.put("deviceName", dto.getDeviceName());
		map.put("startTime", StringtoDate(dto.getStartTime()));
		map.put("endTime", StringtoDate(dto.getEndTime()));
		map.put("approveStatus", dto.getStatus());
		map.put("groupId", dto.getGroupId());
		SimpleDateFormat   sd  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//分页，每页显示大概5条记录
		PageHelper.startPage(Integer.valueOf(dto.getStartPage()), Integer.valueOf(dto.getPageSize()));
		List<DeviceApprove>  list  = deviceApproveServiceImpl.queryDeviceApproveHistory(map);
		PageInfo page = new PageInfo(list);
		JSONArray  array  =  new JSONArray();
		for(DeviceApprove deviceApprove:list){
			JSONObject  obj = JSONObject.parseObject(JSONObject.toJSONString(deviceApprove));
			obj.put("approveTime",sd.format(deviceApprove.getApproveTime()));
			array.add(obj);
		}
		map.clear();
		map.put("status", "1");
		map.put("data", array);
		map.put("total", page.getTotal());
		return map;
	
	}

	/*@Override
	@RequestMapping(value = "/queryDeviceApproveHistoryDetail")
	public Map queryDeviceApproveHistoryDetail(@RequestParam(value ="approveId") String approveId) {
		//先查询当前申请单详情
		DeviceApprove deviceApprove  = deviceApproveServiceImpl.queryDeviceApproveById(approveId);
		//查询与当前申请单相关的审批意见及审批单位和经办人
		List<ApproveChk>  DeviceApproveResults  = deviceApproveServiceImpl.queryDeviceApproveHistoryDetail(approveId);
		Map  map = new HashMap();
		map.put("status", "1");
		map.put("deviceApprove", deviceApprove);
		map.put("DeviceApproveResults", DeviceApproveResults);
		return map;
	
	}*/

}