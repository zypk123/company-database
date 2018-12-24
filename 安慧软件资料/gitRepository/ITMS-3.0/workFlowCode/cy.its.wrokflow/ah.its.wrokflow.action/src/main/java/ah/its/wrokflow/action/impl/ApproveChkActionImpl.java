package ah.its.wrokflow.action.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ah.its.workFlow.util.CodeGenUtil;
import ah.its.wrokflow.action.ApproveChkActionI;
import ah.its.wrokflow.action.DeviceApproveFormActionI;
import ah.its.wrokflow.action.SysApproveFormActionI;
import ah.its.wrokflow.action.WorkflowEngineActionI;
import ah.its.wrokflow.domain.ApproveChkServiceI;
import ah.its.wrokflow.domain.WfNoticeServiceI;
import ah.its.wrokflow.domain.Impl.SysApproveCompleteServiceImpl;
import ah.its.wrokflow.repository.dao.ApproveChk;
import ah.its.wrokflow.repository.dao.DeviceApprove;
import ah.its.wrokflow.repository.dao.SysApprove;
import ah.its.wrokflow.repository.dao.SysApproveFile;

/**
* @Title: ApproveChkActionImpl.java 
* @Package ah.its.wrokflow.action.impl 
* @Description: 审批单处理 
* @author lil@cychina.cn
* @date 2016年4月27日 上午9:17:36 
* @version V1.0   
 */
@RestController
@RequestMapping("/workFlow/aCservice")
public class ApproveChkActionImpl implements ApproveChkActionI {
	   
	@Autowired
	private WfNoticeServiceI  wfNoticeServiceImpl;
	@Autowired
	private ApproveChkServiceI   approveChkServiceImpl;
	
	@Autowired
	private WorkflowEngineActionI workflowEngineActionImpl;//流程处理类
	
	@Autowired
	private DeviceApproveFormActionI deviceApproveFormActionImpl;//流程处理类
	
	@Autowired
	private SysApproveFormActionI sysApproveFormActionI;
	
	@Autowired
	private SysApproveCompleteServiceImpl sysApproveCompleteServiceImpl;
	
	@Override
	@RequestMapping(value = "/queryChkRecord")
	public Map queryChkRecord(String approveId) {
		Map   map = new HashMap();
		JSONArray  array  =  new JSONArray();
		SimpleDateFormat   sd  = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<ApproveChk>  lists= approveChkServiceImpl.queryApproveChkData(approveId);
		for(ApproveChk approveChk:lists){
			JSONObject  obj = JSONObject.parseObject(JSONObject.toJSONString(approveChk));
			obj.put("approvalResult","0".equals(approveChk.getApprovalResult())?"不通过":"通过");
			obj.put("approvalTime",sd.format(approveChk.getApprovalTime()));
			array.add(obj);
		}
		map.put("status", "1");
		map.put("data",array);
		return map;
	}

	@Override
	@RequestMapping(value = "/processChkRecord")
	public Map processChkRecord(@RequestBody ApproveChk form) {
		String  flag  = form.getApprovalId();//是否通过标志
		form.setApprovalTime(new Date());
		form.setApprovalId(CodeGenUtil.getUDDICode());
		//调用工作流， 然后保存记录
		Map map = new HashMap();
		map.put("checkResult", form.getApprovalResult());
		map.put("flag",flag);
		
		workflowEngineActionImpl.completeCheckTask(form.getApprovalUser(), form.getTaskId(), map);
		
		if(workflowEngineActionImpl.isOverByTask(form.getExtension())){
			if("0".equals(form.getIsSystem())){//设备申请
				//更该申请单状态为结束
				DeviceApprove deviceApprove = new DeviceApprove();
				deviceApprove.setDeviceApproveId(form.getApplyId());
				if("0".equals(flag)){
					deviceApprove.setStatus("3");
				}else{
					deviceApprove.setStatus("2");
				}
				deviceApproveFormActionImpl.updateDeviceApprove(deviceApprove);
			}
			if("1".equals(form.getIsSystem())){//系统申请
				//更该申请单状态为结束
				SysApprove sysApprove = new SysApprove();
				sysApprove.setSystemApproveId(form.getApplyId());
				if("0".equals(flag)){
					sysApprove.setStatus("3");
				}else{
					sysApprove.setStatus("2");
					//系统申请通过，自动绑定系统申请systemApproveId到系统验收申请
					SysApproveFile SAF = new SysApproveFile();
					SAF.setId(CodeGenUtil.getUDDICode());
					SAF.setSystemApproveId(sysApprove.getSystemApproveId());//关联外键
					SAF.setApproveTime(new Date());
					SAF.setDisabled("0");
//					SAF.setStatus("0");
					sysApproveCompleteServiceImpl.saveSystemApproveComplete(SAF);
				}
				sysApproveFormActionI.updateSystemApprove(sysApprove);
			}
			if("2".equals(form.getIsSystem())){//系统验收申请
				//更该申请单状态为结束
				SysApproveFile SAF = new SysApproveFile();
				SAF.setId(form.getApplyId());
				if("0".equals(flag)){
					SAF.setStatus("3");
				}else{
					SAF.setStatus("2");
				}
				sysApproveCompleteServiceImpl.updateSystemApproveComplete(SAF);
			}
			//如果完成后，插入信息到通知表中，当前用户就不用通知
			map.put("user",form.getApprovalUser());
			map.put("applyId",form.getApplyId());
			wfNoticeServiceImpl.insertBatch(map);
			
		}
		approveChkServiceImpl.saveApproveChkData(form);
		map.clear();
		map.put("status", "1");
		map.put("info", "审批完成，程序自动关闭该界面！");
		return map;
	}

}
