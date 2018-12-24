package ah.its.wrokflow.action.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
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
import ah.its.wrokflow.action.SysApproveCompleteFormActionI;
import ah.its.wrokflow.action.WorkflowEngineActionI;
import ah.its.wrokflow.domain.SysApproveCompleteServiceI;
import ah.its.wrokflow.domain.SysApproveServiceI;
import ah.its.wrokflow.dto.SystemApproveDto;
import ah.its.wrokflow.repository.dao.SysApprove;
import ah.its.wrokflow.repository.dao.SysApproveFile;
/**
 * @Title: SysApproveCompleteFormActionImpl.java
 * @Package ah.its.wrokflow.action.impl
 * @Description: 系统申请建设后审批单(验收)
 * @author chengf chengf@cychina.cn
 * @date 2016年6月22日 上午11:10:15
 * @version V1.0
 */
@RestController
@RequestMapping("/workFlow/sACservice")
public class SysApproveCompleteFormActionImpl implements SysApproveCompleteFormActionI {
	@Autowired
	private SysApproveCompleteServiceI sysApproveCompleteServiceImpl;
	@Autowired
	private SysApproveServiceI sysApproveServiceImpl;
	@Autowired
	private WorkflowEngineActionI workflowEngineActionImpl;
	@Autowired
	private ApproveChkActionImpl   approveChkActionImplmpl;
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/saveSysApproveComplete")
	public Map saveSysApproveComplete(@RequestBody SysApproveFile form) {
		String  Id = form.getId();
		String status = form.getStatus();
		//第一次创建
		if(StringUtil.isEmpty(status)&&StringUtil.isNotEmpty(Id)){
			
			form.setId(Id);
			form.setApproveTime(new Date());
			form.setDisabled("0");
			form.setStatus("0");
			sysApproveCompleteServiceImpl.updateSystemApproveComplete(form);
		
		}else{
		//重新创建提交
			//重新提交之前删除之前不通过的申请信息，不能在页面展示
			SysApproveFile sysApproveFile = new SysApproveFile();
			sysApproveFile.setId(Id);
			sysApproveFile.setDisabled("1");
			sysApproveCompleteServiceImpl.updateSystemApproveComplete(sysApproveFile);
			//创建一条新的系统申请记录
			form.setId(CodeGenUtil.getUDDICode());
			form.setApproveTime(new Date());
			form.setDisabled("0");
			form.setStatus("0");
			sysApproveCompleteServiceImpl.saveSystemApproveComplete(form);
		
		}
		Map  map  = new HashMap();
		map.put("status", "1");
		return map;
	
	}
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/submitSysApproveComplete")
	public Map submitSysApproveComplete(@RequestParam(value ="processId") String processId,@RequestParam(value ="approveId") String approveId) {
		Map  map = new HashMap();
		String returnprcossId = workflowEngineActionImpl.startProcessDefinitionByKey(processId,approveId);
		SysApproveFile SAF = new SysApproveFile();
		SAF.setId(approveId);
		SAF.setStatus("1");
		SAF.setProcessId(returnprcossId);
		this.updateSysApproveComplete(SAF);
		map.put("status","1");
		return map;
	}
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/saveSysApproveCompleteSubmit")
	public Map saveSysApproveCompleteSubmit(@RequestBody SysApproveFile form) {
		Map map  = new HashMap<>();
		this.saveSysApproveComplete(form);
		this.submitSysApproveComplete(form.getProcessId(), form.getId());
		map.put("status", "1");
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/updateSysApproveComplete")
	public Map updateSysApproveComplete(@RequestBody SysApproveFile form) {
		Map map  = new HashMap();
		sysApproveCompleteServiceImpl.updateSystemApproveComplete(form);
		map.put("status", "1");
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/deleteSystemApprove")
	public Map deleteSysApproveComplete(@RequestParam(value ="approveId") String  approveId) {
		Map map  = new HashMap();
		SysApproveFile SAF = new SysApproveFile();
		SAF.setId(approveId);
		SAF.setDisabled("1");
		SAF.setStatus("1");
		this.updateSysApproveComplete(SAF);
		map.put("status","1");
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/querySysApproveCompleteData")
	public Map querySysApproveComplete(@RequestBody SystemApproveDto dto) {

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
		List<SysApproveFile>  list  = sysApproveCompleteServiceImpl.selectAllData(map);
		PageInfo page = new PageInfo(list);
		JSONArray  array  =  new JSONArray();
		for (SysApprove SAF : list) {
			JSONObject obj = JSONObject.parseObject(JSONObject.toJSONString(SAF));
			obj.put("approveTime", DateUtil.DatetoString(SAF.getApproveTime(),"yyyy-MM-dd HH:mm:ss"));
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
	@RequestMapping(value = "/querySysApproveCompleteById")
	public Map querySysApproveCompleteById(@RequestParam(value ="approveId") String approveId) {
		SysApproveFile SAF = sysApproveCompleteServiceImpl.querySystemApproveCompleteById(approveId);
		//获取系统申请详细信息
//		String systemApproveId = SAF.getSystemApproveId();
//		SysApprove sysApprove = sysApproveServiceImpl.querySystemApproveById(systemApproveId);
		JSONObject  obj = JSONObject.parseObject(JSONObject.toJSONString(SAF));
		obj.put("approveTime", DateUtil.DatetoString(SAF.getApproveTime(),"yyyy-MM-dd HH:mm:ss"));
//		obj.put("sysApprove", sysApprove);
		Map  map = new HashMap();
		map.put("status", "1");
		map.put("data", obj);
		return map;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/querySysApproveCompleteDataById")
	public Map querySysApproveCompleteDataById(@RequestParam(value ="approveId") String approveId) {

		//根据approveId查找申请单详情
		Map map  = new HashMap();
		SysApproveFile SAF = sysApproveCompleteServiceImpl.querySystemApproveCompleteById(approveId);
		//获取系统申请详细信息
		String systemApproveId = SAF.getSystemApproveId();
		SysApprove sysApprove = sysApproveServiceImpl.querySystemApproveById(systemApproveId);
		
		SAF.setUnitName(sysApprove.getUnitName());
		SAF.setSystemName(sysApprove.getSystemName());
		SAF.setUserDepartments(sysApprove.getUserDepartments());
		SAF.setSystemManageer(sysApprove.getSystemManageer());
		SAF.setPhone(sysApprove.getPhone());
		SAF.setSysytemFunction(sysApprove.getSysytemFunction());
		SAF.setApproveImg(sysApprove.getApproveImg());
		SAF.setDepartmentsImg(sysApprove.getDepartmentsImg());
		SAF.setSolutionFile(sysApprove.getSolutionFile());
		SAF.setPlansFile(sysApprove.getPlansFile());
		SAF.setExpertOpinionImg(sysApprove.getExpertOpinionImg());
		
		JSONObject  obj = JSONObject.parseObject(JSONObject.toJSONString(SAF));
		obj.put("approveTime", DateUtil.DatetoString(SAF.getApproveTime(),"yyyy-MM-dd HH:mm:ss"));
		//查找当前申请记录的审批详情
		Map dataChk = approveChkActionImplmpl.queryChkRecord(SAF.getId());
		map.put("status", "1");
		map.put("dataChk",dataChk.get("data"));
		map.put("data", obj);
		return map;
	
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	@RequestMapping(value = "/querySysApproveCompleteHistory")
	public Map querySysApproveCompleteHistory(@RequestBody SystemApproveDto dto) {


		Map map  = new HashMap();
		map.put("systemName", dto.getSystemName());
		map.put("startTime", DateUtil.StringtoDate(dto.getStartTime(), "yyyy-MM-dd HH:mm:ss"));
		map.put("endTime", DateUtil.StringtoDate(dto.getEndTime(), "yyyy-MM-dd HH:mm:ss"));
		map.put("approveStatus", dto.getStatus());
		map.put("groupId", dto.getGroupId());
		SimpleDateFormat   sd  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		//分页，每页显示大概5条记录
		PageHelper.startPage(Integer.valueOf(dto.getStartPage()), Integer.valueOf(dto.getPageSize()));
		List<SysApprove>  list  = sysApproveCompleteServiceImpl.querySystemApproveCompleteHistory(map);
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

}
