package cy.its.device.domain.repository.led;

import java.util.Map;

import cy.its.device.domain.model.led.LedProg;
import cy.its.device.domain.model.led.ReponseResult;

public interface ILedProgControlRepository {

	ReponseResult issueProgram(String serverIp, String deviceSysNbr, LedProg ledProg) throws Exception;

	ReponseResult modifyProgramInRemote(String serverIp, String deviceSysNbr, LedProg ledProg) throws Exception;

	ReponseResult removeProgramInRemote(String ledSvrIp, String deviceSysNbr, int programNo);

	String[] getPlayingProgNoArr(String ledSvrIp, String deviceSysNbr) throws Exception;

	void swapProgram(String serverIp, String deviceSysNbr, String progNo1, String progNo2) throws Exception;

	void changeProgNoSeq(String ledSvrIp, String deviceSysNbr, Map<Integer, Integer> oldNewProgNoMap) throws Exception;
	
}
