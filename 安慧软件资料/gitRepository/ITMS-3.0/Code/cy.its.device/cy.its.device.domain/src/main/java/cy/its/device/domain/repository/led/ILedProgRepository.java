package cy.its.device.domain.repository.led;

import java.util.List;

import cy.its.device.domain.model.led.LedProgNoChange;

public interface ILedProgRepository {//extends IRepository<LedProg>
	int progCountOfLed(String deviceId);
	String generateProgramNo();
	boolean updateProgNoBatch(List<LedProgNoChange> change);
	boolean updateProgNo(String targetProgId, String oldProgNo, String newProgNo);
}
