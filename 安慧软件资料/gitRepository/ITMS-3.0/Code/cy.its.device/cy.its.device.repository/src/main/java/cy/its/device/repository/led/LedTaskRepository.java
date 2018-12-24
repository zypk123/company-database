package cy.its.device.repository.led;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.LedTaskCriteria;
import cy.its.device.domain.model.led.LedProg;
import cy.its.device.domain.model.led.LedProgCycle;
import cy.its.device.domain.model.led.LedProgEmergency;
import cy.its.device.domain.model.led.LedProgGrab;
import cy.its.device.domain.model.led.LedProgTimed;
import cy.its.device.domain.model.led.LedTask;
import cy.its.device.domain.model.led.LedTaskAuto;
import cy.its.device.domain.model.led.LedTaskManual;
import cy.its.device.domain.model.led.LedTimedParam;
import cy.its.device.domain.repository.led.ILedTaskRepository;
import cy.its.device.mybatis.client.led.LedProgAndTemplateMapper;
import cy.its.device.mybatis.client.led.TDeviceLedProgMapper;
import cy.its.device.mybatis.client.led.TDeviceLedTaskAutoMapper;
import cy.its.device.mybatis.client.led.TDeviceLedTaskManualMapper;
import cy.its.device.mybatis.model.DeviceLedTask;
import cy.its.device.mybatis.model.TDeviceLedProg;
import cy.its.device.mybatis.model.TDeviceLedTaskAuto;
import cy.its.device.mybatis.model.TDeviceLedTaskManual;

@Service
public class LedTaskRepository implements ILedTaskRepository {

	@Autowired
	LedProgAndTemplateMapper ledProgAndTemplateMapper;
	
	@Autowired
	TDeviceLedProgMapper tDeviceLedProgMapper;
	
	@Autowired
	TDeviceLedTaskAutoMapper tDeviceLedTaskAutoMapper;
	
	@Autowired
	TDeviceLedTaskManualMapper tDeviceLedTaskManualMapper;
		
	@Override
	public LedTask aggregateOfId(String id) throws Exception {
		LedTaskCriteria c = new LedTaskCriteria();
		c.taskIdArr = new String[] { id };
		List<LedTask> lstTask = findLedTask(c);
		return lstTask != null && lstTask.size() > 0 ? lstTask.get(0) : null;
	}

	@Override
	public String save(LedTask obj) {
		if(obj instanceof LedTaskAuto) {			
			LedTaskAuto taskAuto = (LedTaskAuto)obj;
			tDeviceLedProgMapper.insertSelective(convert(taskAuto.getLedProgGrad()));
//			saveLedProgContent(taskAuto.getLedProgGrad());
			tDeviceLedTaskAutoMapper.insertSelective(convert(taskAuto));
		} else if(obj instanceof LedTaskManual) {
			LedTaskManual taskManual = (LedTaskManual)obj;
			tDeviceLedProgMapper.insertSelective(convert(taskManual.getLedProg()));
//			saveLedProgContent(taskManual.getLedProg());
			tDeviceLedTaskManualMapper.insertSelective(convert(taskManual));
		}
		
		return obj.getTaskId();
	}

	@Override
	public int delete(String id) {
		TDeviceLedTaskAuto taskAuto = tDeviceLedTaskAutoMapper.selectByPrimaryKey(id);		
		if(taskAuto != null) {
			return tDeviceLedProgMapper.deleteByPrimaryKey(taskAuto.getProgramId());
		}		
		TDeviceLedTaskManual taskManual = tDeviceLedTaskManualMapper.selectByPrimaryKey(id);		
		if(taskManual != null) {
			return tDeviceLedProgMapper.deleteByPrimaryKey(taskManual.getProgramId());
		}
		return 0;
	}

	@Override
	public int update(LedTask obj) {
		tDeviceLedProgMapper.updateByPrimaryKey(convert(obj.ledProgOfTask()));
//		deleteLedProgContent(obj.ledProgOfTask());
//		saveLedProgContent(obj.ledProgOfTask());
		
		if(obj instanceof LedTaskAuto) {			
			LedTaskAuto taskAuto = (LedTaskAuto)obj;		
			tDeviceLedTaskAutoMapper.updateByPrimaryKey(convert(taskAuto));			
		} else if(obj instanceof LedTaskManual) {
			LedTaskManual taskManual = (LedTaskManual)obj;
			tDeviceLedTaskManualMapper.updateByPrimaryKey(convert(taskManual));
		}
				
		return 0;
	}
	
//	private void deleteLedProgContent(LedProg ledProg) {
//		Map<String, Object> params = new HashMap<String, Object>(1);
//		params.put("programId", ledProg.getProgramId());
//		tDeviceLedMediaMapper.deleteByParams(params);
//		tDeviceLedTextMapper.deleteByParams(params);
//	}
	

	@Override
	public List<LedTask> findLedTask(LedTaskCriteria criteria) {
		List<DeviceLedTask> lst = ledProgAndTemplateMapper.selectTasks(ParamUtil.parseParams(criteria));
		return lst.stream().map(t -> convert(t)).collect(Collectors.toList());
	}

	@Override
	public int countLedTask(LedTaskCriteria criteria) {
		return ledProgAndTemplateMapper.countTasks(ParamUtil.parseParams(criteria));
	}
	
//	/**
//	 * 保存节目内容
//	 * @param ledProg
//	 */
//	private void saveLedProgContent(LedProg ledProg) {
//		if (ledProg.getLstLedMedia() != null && ledProg.getLstLedMedia().size() > 0) {
//			TDeviceLedMedia media;
//			for (LedProgContentMedia m : ledProg.getLstLedMedia()) {
//				media = new TDeviceLedMedia(StringUtil.generateUUID(), ledProg.getProgramId(), null,
//						m.getMediaNo(), m.getMediaXml());
//				tDeviceLedMediaMapper.insertSelective(media);
//			}
//		}
//
//		if (ledProg.getLstLedText() != null && ledProg.getLstLedText().size() > 0) {
//			TDeviceLedText text;
//			for (LedProgContentText t : ledProg.getLstLedText()) {
//				text = new TDeviceLedText(StringUtil.generateUUID(), ledProg.getProgramId(), null, t.getTextNo(),
//						t.getIsTempletText(), t.getTextXml());
//				tDeviceLedTextMapper.insertSelective(text);
//			}
//		}
//	}
	
	/**
	 * 任务转换处理
	 * 
	 * @param devLedTask
	 * @return
	 */
	private LedTask convert(DeviceLedTask devLedTask) {
		if ("1".equals(devLedTask.getPublishType())) {
			LedTaskAuto autoTask = new LedTaskAuto();
			formatBaseLedTask(autoTask, devLedTask);

			autoTask.setDeviceId(devLedTask.getDeviceId());
			autoTask.setRegionalId(devLedTask.getRegionalId());
			autoTask.setTaskFilter(devLedTask.getTaskFilter());
			autoTask.setEnableFlag(devLedTask.getEnableFlag());
			if(devLedTask.getTimeMode() != null) {
				autoTask.setLedTimedParam(new LedTimedParam(devLedTask.getTimeMode(), 
						devLedTask.getStartDate(),
						devLedTask.getEndDate(),
						devLedTask.getStartTime(),
						devLedTask.getEndTime(),
						devLedTask.getWeek()));
			}
			autoTask.setLedProgGrad((LedProgGrab) convert(devLedTask.gettDeviceLedProg()));

			return autoTask;
		} else {
			LedTaskManual manualTask = new LedTaskManual();
			formatBaseLedTask(manualTask, devLedTask);
			manualTask.setIssueStatus(devLedTask.getIssueStatus());
			manualTask.setLedProg(
					convert(devLedTask.gettDeviceLedProg()));

			return manualTask;
		}
	}

	/**
	 * 节目转换
	 * 
	 * @param prog
	 * @param lstLedMedia
	 * @param lstLedText
	 * @return
	 */
	private LedProg convert(TDeviceLedProg prog) {
		LedProg ledProg = null;

		switch (prog.getPerType()) {
		case "0":
			// 0 轮播节目
			ledProg = new LedProgCycle(prog.getPlayTime(), prog.getPlayTimes());
			break;
		case "1":
			// 1 定时播节目
			ledProg = new LedProgTimed(
					new LedTimedParam(prog.getTimeMode(), prog.getStartDate(),
							prog.getEndDate(), prog.getStartTime(),
							prog.getEndTime(), prog.getWeek()));
			break;
		case "2":
			// 2 插播节目
			ledProg = new LedProgGrab(prog.getPlayTime(), prog.getPlayTimes(), prog.getPlayDelay());
			break;
		case "3":
			// 3 紧急节目
			ledProg = new LedProgEmergency();
			break;
		default:
			break;
		}

		format(ledProg, prog);
		return ledProg;
	}

	/**
	 * 节目转换
	 * @param ledProg
	 * @return
	 */
	private TDeviceLedProg convert(LedProg ledProg) {
		TDeviceLedProg tLedProg = new TDeviceLedProg();
		tLedProg.setProgramId(ledProg.getProgramId());
		tLedProg.setDeviceId(ledProg.getDeviceId());
		tLedProg.setProgramNo(ledProg.getProgramNo());
		tLedProg.setPerName(ledProg.getPerName());
		tLedProg.setShowMode(ledProg.getShowMode());
		tLedProg.setProgramPriority(ledProg.getProgramPriority());
		tLedProg.setMessageType(ledProg.getMessageType());
		tLedProg.setProgramContent(ledProg.getProgramContent());
		
		if(ledProg instanceof LedProgCycle){
			// 0 轮播节目
			LedProgCycle p = (LedProgCycle)ledProg;
			tLedProg.setPerType("0");
			tLedProg.setPlayTime(p.getPlayTime());
			tLedProg.setPlayTimes(p.getPlayTimes());
			
		} else if(ledProg instanceof LedProgTimed){
			// 1 定时播节目
			tLedProg.setPerType("1");
			LedProgTimed p = (LedProgTimed)ledProg;			
			LedTimedParam timedParam = p.getLedTimedParam();
			if(timedParam != null) {
				tLedProg.setTimeMode(timedParam.getTimeMode());
				tLedProg.setStartDate(timedParam.getStartDate());
				tLedProg.setEndDate(timedParam.getEndDate());
				tLedProg.setStartTime(timedParam.getStartTime());
				tLedProg.setEndTime(timedParam.getEndTime());
				tLedProg.setWeek(timedParam.getWeek());
			}
		}else if(ledProg instanceof LedProgGrab){
			// 2 插播节目
			tLedProg.setPerType("2");
			LedProgGrab p = (LedProgGrab)ledProg;
			tLedProg.setPlayTime(p.getPlayTime());
			tLedProg.setPlayTimes(p.getPlayTimes());
			tLedProg.setPlayDelay(p.getPlayDelay());
		}else if(ledProg instanceof LedProgEmergency){
			// 3 紧急节目
			tLedProg.setPerType("3");
		}
		
		return tLedProg;
		
	}
	
	/**
	 * 手动任务转换
	 * @param taskManual
	 * @return
	 */
	private TDeviceLedTaskManual convert(LedTaskManual taskManual) {
		TDeviceLedTaskManual tManTask = new TDeviceLedTaskManual();
		tManTask.setTaskId(taskManual.getTaskId());
		tManTask.setProgramId(taskManual.getLedProg().getProgramId());
		tManTask.setTaskName(taskManual.getTaskName());
		tManTask.setCreator(taskManual.getCreator());
		tManTask.setCreateTime(taskManual.getCreateTime());
		tManTask.setApprover(taskManual.getApprover());
		tManTask.setApproveTime(taskManual.getApproveTime());
		tManTask.setIssueStatus(taskManual.getIssueStatus());
		tManTask.setIssueCount(taskManual.getIssueCount());
		tManTask.setTaskType(taskManual.getTaskType());
		return tManTask;
	}
	
	/**
	 * 自动任务转换
	 * @param taskAuto
	 * @return
	 */
	private TDeviceLedTaskAuto convert(LedTaskAuto taskAuto) {
		TDeviceLedTaskAuto tAutoTask = new TDeviceLedTaskAuto();
		tAutoTask.setTaskId(taskAuto.getTaskId());
		tAutoTask.setDeviceId(taskAuto.getDeviceId());
		tAutoTask.setRegionalId(taskAuto.getRegionalId());
		tAutoTask.setProgramId(taskAuto.getLedProgGrad().getProgramId());
		tAutoTask.setTaskName(taskAuto.getTaskName());
		tAutoTask.setTaskType(taskAuto.getTaskType());
		tAutoTask.setTaskFilter(taskAuto.getTaskFilter());
		tAutoTask.setEnableFlag(taskAuto.getEnableFlag());
		tAutoTask.setIssueCount(taskAuto.getIssueCount());
		if(taskAuto.getLedTimedParam() != null){
			LedTimedParam p = taskAuto.getLedTimedParam();
			tAutoTask.setTimeMode(p.getTimeMode());
			tAutoTask.setStartDate(p.getStartDate());
			tAutoTask.setEndDate(p.getEndDate());
			tAutoTask.setStartTime(p.getStartTime());
			tAutoTask.setEndTime(p.getEndTime());
			tAutoTask.setWeek(p.getWeek());
		}
		tAutoTask.setCreator(taskAuto.getCreator());
		tAutoTask.setCreateTime(taskAuto.getCreateTime());
		tAutoTask.setApprover(taskAuto.getApprover());
		tAutoTask.setApproveTime(taskAuto.getApproveTime());
		return tAutoTask;
	}
	
	/**
	 * 节目基类赋值
	 * 
	 * @param ledProg
	 * @param lstLedMedia
	 * @param lstLedText
	 * @param prog
	 */
	private void format(LedProg ledProg,
			TDeviceLedProg prog) {
		if (ledProg == null) {
			return;
		}
		ledProg.setProgramId(prog.getProgramId());
		ledProg.setDeviceId(prog.getDeviceId());
		ledProg.setProgramNo(prog.getProgramNo());
		ledProg.setPerName(prog.getPerName());
		ledProg.setShowMode(prog.getShowMode());
		ledProg.setProgramPriority(prog.getProgramPriority());
		ledProg.setMessageType(prog.getMessageType());
		ledProg.setProgramContent(prog.getProgramContent());
//		ledProg.setLstLedMedia(lstLedMedia);
//		
//		ledProg.setLstLedText(lstLedText);
	}

	/**
	 * 任务基类赋值
	 * 
	 * @param ledTask
	 * @param devLedTask
	 */
	private void formatBaseLedTask(LedTask ledTask, DeviceLedTask devLedTask) {
		ledTask.setTaskId(devLedTask.getTaskId());
		ledTask.setTaskType(devLedTask.getTaskType());
		ledTask.setTaskName(devLedTask.getTaskName());
		ledTask.setCreator(devLedTask.getCreator());
		ledTask.setCreateTime(devLedTask.getCreateTime());
		ledTask.setApprover(devLedTask.getApprover());
		ledTask.setApproveTime(devLedTask.getApproveTime());
		ledTask.setIssueCount(devLedTask.getIssueCount());
	}

	static Object sync = new Object();
    int index;
    String lastKey = "";
	@Override	
	public String generateTaskId() {
		synchronized (sync) {
			String key = String.valueOf(System.currentTimeMillis());
			if(key.equals(lastKey)) {
				index ++;
			} else {
				lastKey = key;
				index = 1;
			}			

			return key + StringUtil.padLeft(String.valueOf(index), 3, '0');
		}
	}

}
