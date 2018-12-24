package cy.its.device.repository.led;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.LedPublishLogCriteria;
import cy.its.device.domain.model.led.LedControlParam;
import cy.its.device.domain.model.led.LedField;
import cy.its.device.domain.model.led.LedProg;
import cy.its.device.domain.model.led.LedPublishLog;
import cy.its.device.domain.model.led.LedTask;
import cy.its.device.domain.model.led.LedTaskManual;
import cy.its.device.domain.model.led.ReponseResult;
import cy.its.device.domain.repository.led.ILedOtherRepository;
import cy.its.device.mybatis.client.led.LedFieldMapper;
import cy.its.device.mybatis.client.led.LedPublishLogMapper;

@Service
public class LedOtherRepository implements ILedOtherRepository {

	@Autowired
	LedFieldMapper ledFieldMapper;
	
	@Autowired
	LedPublishLogMapper ledPublishLogMapper;
	
	@Override
	public List<LedField> allLedFields() {
		return ledFieldMapper.selectAll();
	}

	@Override
	public List<LedPublishLog> findLedPublishLogs(LedPublishLogCriteria criteria) {
		return ledPublishLogMapper.selectLedPublishLogs(ParamUtil.parseParams(criteria));
	}
	
	@Override
	public int countLedPublishLogs(LedPublishLogCriteria criteria) {
		return ledPublishLogMapper.countPublishLogs(ParamUtil.parseParams(criteria));
	}

	@Override
	public void createLedPublishLog(LedPublishLog ledPublishLog) {
		ledPublishLogMapper.insertSelective(ledPublishLog);
	}

//	private  String joinProgContents(LedProg ledProg) {		
//		List<String> lst = null;
//		
//		if(ledProg.getLstLedMedia() != null && ledProg.getLstLedMedia().size() > 0){
//			lst = ledProg.getLstLedMedia().stream().map(m->m.getMediaXml()).
//					collect(Collectors.toList());
//		}
//		
//		if(ledProg.getLstLedText() != null && ledProg.getLstLedText().size() > 0) {
//			if(lst == null) {
//				lst = new ArrayList<String>(ledProg.getLstLedText().size());
//			}
//			
//			lst.addAll(ledProg.getLstLedText().stream().map(m->m.getTextXml()).
//					collect(Collectors.toList()));
//		}
//		
//		if(lst != null){
//			return String.join("", lst);
//		}
//		
//		return null;
//	}

	@Override
	public void savePublishLog(LedTask ledTask, LedControlParam params,
			ReponseResult respRslt, Date beginTime,
			Date endTime) {
		try {
			boolean isManualPublish = ledTask instanceof LedTaskManual;
			LedProg prog = ledTask.ledProgOfTask();
			LedPublishLog log = new LedPublishLog();
			log.setPublishLogId(StringUtil.generateUUID());
			log.setDeviceSysNbr(params.getDeviceSysNbr());
			log.setProgramId(prog.getProgramId());
			log.setProgramNo(prog.getProgramNo());
			log.setBeginTime(beginTime);
			log.setEndTime(endTime);
			log.setResult(respRslt.getSuccess() ? "2" : "1");
			if(!respRslt.getSuccess()){
				log.setFailureCode(respRslt.getResponseCode() != null?
						respRslt.getResponseCode().shortValue():null);
				log.setFailureDesc(respRslt.getResponseString());
			}
			log.setMessageType(prog.getMessageType());
			log.setPublishUser(ledTask.getCreator());
			log.setOrgPrivilegeCode(params.getOrgPrivilegeCode());
			log.setTaskId(ledTask.getTaskId());
			log.setTaskType(ledTask.getTaskType());
			// log.setDataIdentity( );
			log.setPublishMethod(isManualPublish ? "1" : "2");
			log.setProgContent(ledTask.ledProgOfTask().getProgramContent());
			createLedPublishLog(log);
		} catch (Exception e) {
		}
	}

}
