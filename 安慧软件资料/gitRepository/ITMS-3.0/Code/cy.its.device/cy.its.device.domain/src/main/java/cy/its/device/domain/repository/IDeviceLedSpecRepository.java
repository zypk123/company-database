package cy.its.device.domain.repository;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.device.domain.criteria.DeviceLedSpecCriteria;
import cy.its.device.domain.model.DeviceLedSpec;

public interface IDeviceLedSpecRepository extends IRepository<DeviceLedSpec>{
		public List<DeviceLedSpec> findByDeviceLedSpec(DeviceLedSpecCriteria deviceLedCritera);
		int removeDeviceLedSpecId(Map<String,Object> ids);
}
