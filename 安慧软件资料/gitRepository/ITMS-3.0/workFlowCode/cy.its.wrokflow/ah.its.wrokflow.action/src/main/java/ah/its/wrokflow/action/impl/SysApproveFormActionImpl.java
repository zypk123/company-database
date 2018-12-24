package ah.its.wrokflow.action.impl;

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
import ah.its.workFlow.util.DateUtil;
import ah.its.wrokflow.action.SysApproveFormActionI;
import ah.its.wrokflow.action.WorkflowEngineActionI;
import ah.its.wrokflow.domain.SysApproveServiceI;
import ah.its.wrokflow.dto.SystemApproveDto;
import ah.its.wrokflow.repository.dao.SysApprove;

/**
 * 系统申报Action接口实现类
 * @Title: SystemApproveFormActionImpl.java
 * @Package ah.its.wrokflow.action.impl
 * @Description: TODO(这里要填写用途)
 * @author chengf chengf@cychina.cn
 * @date 2016年6月13日 下午5:43:09
 * @version V1.0
 */
@RestController
@RequestMapping("/workFlow/sAservice")
public class SysApproveFormActionImpl implements SysApproveFormActionI {
	
	
	@Autowired
	private WorkflowEngineActionI workflowEngineActionImpl;//流程处理类
	@Autowired
	private SysApproveServiceI sysApproveServiceImpl;
	@Autowired
	private ApproveChkActionImpl   approveChkActionImplmpl;

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/saveSAFrom")
	public Map saveSystemApprove(@RequestBody SysApprove form) {
		String  systemApproveId = form.getSystemApproveId();
		if(StringUtil.isNotEmpty(systemApproveId)){
			sysApproveServiceImpl.updateSystemApprove(form);
		}else{
			form.setSystemApproveId(CodeGenUtil.getUDDICode());
			form.setApproveTime(new Date());
			form.setDisabled("0");
			form.setStatus("0");
			sysApproveServiceImpl.saveSystemApprove(form);
		}
		Map  map  = new HashMap();
		map.put("status", "1");
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/submitSystemApprove")
	public Map submitSystemApprove(@RequestParam(value ="processId") String processId,@RequestParam(value ="systemApproveId") String systemApproveId) {
		Map  map = new HashMap();
		String returnprcossId = workflowEngineActionImpl.startProcessDefinitionByKey(processId, systemApproveId);
		SysApprove SA = new SysApprove();
		SA.setSystemApproveId(systemApproveId);
		SA.setStatus("1");
		SA.setProcessId(returnprcossId);
		this.updateSystemApprove(SA);
		map.put("status","1");
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/saveSAFromSubmit")
	public Map saveSystemApproveSubmit(@RequestBody SysApprove form) {
		Map  map = new HashMap();
		this.saveSystemApprove(form);
		this.submitSystemApprove(form.getProcessId(), form.getSystemApproveId());
		map.put("status","1");
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/updateSAFrom")
	public Map updateSystemApprove(@RequestBody SysApprove form) {
		Map map  = new HashMap();
		sysApproveServiceImpl.updateSystemApprove(form);
		map.put("status", "1");
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/deleteSystemApprove")
	public Map deleteSystemApprove(@RequestParam(value ="approveId") String  approveId) {
		Map map  = new HashMap();
		SysApprove SA = new SysApprove();
		SA.setSystemApproveId(approveId);
		SA.setDisabled("1");
		SA.setStatus("1");
		this.updateSystemApprove(SA);
		map.put("status","1");
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/querySystemApproveData")
	public Map querySystemApprove(@RequestBody SystemApproveDto dto) {
		//条件查询
		Map map  = new HashMap();
		map.put("systemName", dto.getSystemName());
		map.put("startTime", DateUtil.StringtoDate(dto.getStartTime(),"yyyy-MM-dd HH:mm:ss"));
		map.put("endTime", DateUtil.StringtoDate(dto.getEndTime(),"yyyy-MM-dd HH:mm:ss"));
		map.put("approveStatus", dto.getStatus());
		String groupId= "";
		if(StringUtils.isNotEmpty(dto.getGroupId())){
			groupId= dto.getGroupId().replaceAll("0*$","");
		}
		map.put("groupId",groupId);
		PageHelper.startPage(Integer.parseInt(dto.getStartPage()), Integer.parseInt(dto.getPageSize()));
		List<SysApprove>  list  = sysApproveServiceImpl.selectAllData(map);
		PageInfo page = new PageInfo(list);
		JSONArray  array  =  new JSONArray();
		for (SysApprove SA : list) {
			JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(SA));
			obj.put("approveTime", DateUtil.DatetoString(SA.getApproveTime(),"yyyy-MM-dd HH:mm:ss"));
			array.add(obj);
		}
		map.clear();
		map.put("status", "1");
		map.put("data", array);
		map.put("total", page.getTotal());
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/querySystemApproveById")
	public Map querySystemApproveById(@RequestParam(value ="approveId") String approveId) {
		SysApprove SA = sysApproveServiceImpl.querySystemApproveById(approveId);
		JSONObject  obj = JSONObject.parseObject(JSONObject.toJSONString(SA));
		obj.put("approveTime", DateUtil.DatetoString(SA.getApproveTime(),"yyyy-MM-dd HH:mm:ss"));
		Map  map = new HashMap();
		map.put("status", "1");
		map.put("data", obj);
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/querySystemApproveDataById")
	public Map querySystemApproveDataById(@RequestParam(value ="approveId") String approveId) {
		//根据approveId查找申请单详情
		Map map  = new HashMap();
		SysApprove SA = sysApproveServiceImpl.querySystemApproveById(approveId);
		JSONObject  obj = JSONObject.parseObject(JSONObject.toJSONString(SA));
		obj.put("approveTime", DateUtil.DatetoString(SA.getApproveTime(),"yyyy-MM-dd HH:mm:ss"));
		//查找当前申请记录的审批详情
		Map dataChk = approveChkActionImplmpl.queryChkRecord(SA.getSystemApproveId());
		map.put("status", "1");
		map.put("dataChk",dataChk.get("data"));
		map.put("data", obj);
		return map;
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/querySystemApproveHistoryData")
	public Map querySystemApproveHistory(@RequestBody SystemApproveDto dto) {

		Map map  = new HashMap();
		map.put("systemName", dto.getSystemName());
		map.put("startTime", DateUtil.StringtoDate(dto.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
		map.put("endTime", DateUtil.StringtoDate(dto.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		map.put("approveStatus", dto.getStatus());
		map.put("groupId", dto.getGroupId());
		SimpleDateFormat   sd  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//分页，每页显示大概5条记录
		PageHelper.startPage(Integer.valueOf(dto.getStartPage()), Integer.valueOf(dto.getPageSize()));
		List<SysApprove>  list  = sysApproveServiceImpl.querySystemApproveHistory(map);
		PageInfo page = new PageInfo(list);
		JSONArray  array  =  new JSONArray();
		for(SysApprove sysApprove:list){
			JSONObject  obj = JSONObject.parseObject(JSONObject.toJSONString(sysApprove));
			obj.put("approveTime",sd.format(sysApprove.getApproveTime()));
			array.add(obj);
		}
		map.clear();
		map.put("status", "1");
		map.put("data", array);
		map.put("total", page.getTotal());
		return map;
	
	}

	/*@Override
	@RequestMapping(value = "/querySystemApproveHistoryDetail")
	public Map querySystemApproveHistoryDetail(@RequestParam(value ="approveId") String approveId) {
		//先查询当前申请单详情
		SysApprove sysApprove  = sysApproveServiceImpl.querySystemApproveById(approveId);
		//查询与当前申请单相关的审批意见及审批单位和经办人
		List<ApproveChk>  sysApproveResults  = sysApproveServiceImpl.querySystemApproveHistoryDetail(approveId);
		for (ApproveChk approveChk : sysApproveResults) {
			Date time = approveChk.getApprovalTime();
			String approvalTimeResult = (String) DateUtil.DatetoString(time, "yyyy-MM-dd HH:mm:ss");
			approveChk.setApprovalTimeResult(approvalTimeResult);
		}
		Map  map = new HashMap();
		map.put("status", "1");
		map.put("SysApprove", sysApprove);
		map.put("SysApproveResults", sysApproveResults);
		return map;
	
	}*/
}
