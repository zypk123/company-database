package cy.its.device.mybatis.client.led;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.model.led.LedProgTemplate;
import cy.its.device.mybatis.model.DeviceLedTask;

public interface LedProgAndTemplateMapper {
	List<LedProgTemplate> selectProgTemplate(Map<String, Object> params);
//	List<TDeviceLedProg> selectProg(Map<String, Object> params);
	
	List<DeviceLedTask> selectTasks(Map<String, Object> params);
	int countTasks(Map<String, Object> params);
	
}
