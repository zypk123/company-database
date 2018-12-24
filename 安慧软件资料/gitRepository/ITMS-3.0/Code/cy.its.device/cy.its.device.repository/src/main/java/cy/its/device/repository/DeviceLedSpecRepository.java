package cy.its.device.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.DeviceLedSpecCriteria;
import cy.its.device.domain.model.DeviceLedSpec;
import cy.its.device.domain.repository.IDeviceLedSpecRepository;
import cy.its.device.mybatis.client.DeviceLedSpecServiceMapper;

@Service
public class DeviceLedSpecRepository implements IDeviceLedSpecRepository{
	@Autowired
	DeviceLedSpecServiceMapper deviceLedSpecServiceMapper;
	@Override
	public DeviceLedSpec aggregateOfId(String id) throws Exception {
		// TODO Auto-generated method stub
		
		return null;
	}

	@Override
	public String save(DeviceLedSpec obj) {
		// TODO Auto-generated method stub
		obj.setSpecId(StringUtil.generateUUID());
		
		deviceLedSpecServiceMapper.insert(obj);
		
		return obj.getSpecId();
	}

	@Override
	public int removeDeviceLedSpecId(Map<String,Object> ids){
		// TODO Auto-generated method stub
		return deviceLedSpecServiceMapper.removeByPrimaryKey(ids);
	}

	@Override
	public int update(DeviceLedSpec obj) {
		// TODO Auto-generated method stub
		return deviceLedSpecServiceMapper.updateByPrimaryKey(obj);
	}

	@Override
	public List<DeviceLedSpec> findByDeviceLedSpec(DeviceLedSpecCriteria deviceLedCritera) {
		// TODO Auto-generated method stub
		return deviceLedSpecServiceMapper.findByDeviceLedSpec(deviceLedCritera);
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

}
