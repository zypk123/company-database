package cy.its.device.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.device.domain.criteria.DeviceLedSpecCriteria;
import cy.its.device.domain.model.DeviceLedSpec;

public interface DeviceLedSpecServiceMapper {
	
	    int insert(DeviceLedSpec record);

	    int updateByPrimaryKey(DeviceLedSpec record);
	    
	    List<DeviceLedSpec> findByDeviceLedSpec(DeviceLedSpecCriteria deviceLedCritera);
	    
	    int removeByPrimaryKey(Map<String,Object> ids);
}
