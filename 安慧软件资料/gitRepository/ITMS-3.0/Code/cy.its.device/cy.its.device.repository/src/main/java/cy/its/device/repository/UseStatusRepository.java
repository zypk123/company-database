package cy.its.device.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.model.DeviceSysUseStatus;
import cy.its.device.domain.repository.IUseStatusRepository;
import cy.its.device.mybatis.client.DeviceSysUseStatusMapper;

@Service
public class UseStatusRepository implements IUseStatusRepository {

	@Autowired
	DeviceSysUseStatusMapper deviceSysUseStatusMapper;

	@Override
	public DeviceSysUseStatus aggregateOfId(String id) throws Exception {
		return deviceSysUseStatusMapper.selectByPrimaryKey(id);
	}

	@Override
	public String save(DeviceSysUseStatus obj) {
		obj.setSysUseStatusId(StringUtil.generateUUID());
		deviceSysUseStatusMapper.insertSelective(obj);
		return obj.getSysUseStatusId();
	}

	@Override
	public int delete(String id) {
		return deviceSysUseStatusMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(DeviceSysUseStatus obj) {
		return deviceSysUseStatusMapper.updateByPrimaryKeySelective(obj);
	}
}
