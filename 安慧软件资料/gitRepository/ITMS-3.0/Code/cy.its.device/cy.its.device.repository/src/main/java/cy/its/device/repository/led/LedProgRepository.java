package cy.its.device.repository.led;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.model.led.LedProgNoChange;
import cy.its.device.domain.repository.led.ILedProgRepository;
import cy.its.device.mybatis.client.led.TDeviceLedProgMapper;

@Service
public class LedProgRepository implements ILedProgRepository {

	@Autowired
	TDeviceLedProgMapper tDeviceLedProgMapper;
	
	@Override
	public int progCountOfLed(String deviceId) {
		return tDeviceLedProgMapper.countProgOfDevice(deviceId);
	}

	@Override
	public String generateProgramNo() {
		return String.valueOf(tDeviceLedProgMapper.selectProgNoSEQ());
	}

	@Override
	public boolean updateProgNo(String targetProgId, String oldProgNo, String newProgNo) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("newProgNo", newProgNo);
		params.put("targetProgId", targetProgId);
		params.put("oldProgNo", oldProgNo);
		return tDeviceLedProgMapper.updateProgNo(params) == 1;
	}

	@Override
	public boolean updateProgNoBatch(List<LedProgNoChange> changes) {		
		return tDeviceLedProgMapper.updateProgNoBatch(changes);
	}
}
