package cy.its.device.domain.model.led;

import java.util.Date;

import cy.its.com.util.DateUtil;
import cy.its.device.domain.repository.ISysRepository;
import cy.its.device.domain.repository.led.ILedProgRepository;
import cy.its.device.domain.repository.led.ILedTaskRepository;

public class LedTaskManual extends LedTask {

	private String issueStatus;

	private LedProg ledProg;

	public String getIssueStatus() {
		return issueStatus;
	}

	public void setIssueStatus(String issueStatus) {
		this.issueStatus = issueStatus;
	}

	public LedProg getLedProg() {
		return ledProg;
	}

	public void setLedProg(LedProg ledProg) {
		this.ledProg = ledProg;
	}

	public void checkPrivate(ISysRepository sysRepository) throws Exception {
		if (this.ledProg == null) {
			throw new Exception("自动任务下未分配节目");
		}
		
		this.ledProg.check(sysRepository);
	}

	public void generate(ILedTaskRepository ledTaskRepository, ILedProgRepository iLedProgRepository) {
		this.setCreateTime(new Date());
		this.setIssueCount((short) 1);
		this.issueStatus = "1";

		if (this.ledProg != null) {
			this.ledProg.generate(iLedProgRepository);
		}

		this.setTaskId(ledTaskRepository.generateTaskId());
	}

	@Override
	public LedProg ledProgOfTask() {
		return ledProg;
	}

	@Override
	public String getTimeString() {
		if (ledProg != null) {
			if (ledProg instanceof LedProgTimed) {
				LedTimedParam p = ((LedProgTimed) ledProg).getLedTimedParam();
				if (p != null) {
					return p.getParamString();
				}
			} else {
				return DateUtil.formatDate(this.getCreateTime());
			}
		}
		return null;
	}
}