package cy.its.device.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.MaintainRegisterCriteria;
import cy.its.device.domain.model.MaintainRegister;
import cy.its.device.domain.repository.maintain.IMaintainRegisterRepository;
import cy.its.device.mybatis.client.MaintainRegisterMapper;

@Service
public class MaintainRegisterRepository implements IMaintainRegisterRepository {
	
	@Autowired
	MaintainRegisterMapper maintainRegisterMapper;

	public MaintainRegister aggregateOfId(String id) throws Exception {
		return maintainRegisterMapper.selectByPrimaryKey(id);
	}

	public String save(MaintainRegister obj) {
		obj.setMaintenanceId(StringUtil.generateUUID());
		maintainRegisterMapper.insertSelective(obj);
		return obj.getMaintenanceId();
	}

	public int delete(String id) {
		return maintainRegisterMapper.deleteByPrimaryKey(id);
	}

	public int update(MaintainRegister obj) {
		return maintainRegisterMapper.updateByPrimaryKeySelective(obj);
	}

	public List<MaintainRegister> findMaintainRegisters(
			MaintainRegisterCriteria maintainRegisterCriteria) {
		return maintainRegisterMapper.selectMaintainRegisters(ParamUtil
				.parseParams(maintainRegisterCriteria));
	}

	public int countMaintainRegisters(MaintainRegisterCriteria criteria) {
		return maintainRegisterMapper.countMaintainRegisters(ParamUtil
				.parseParams(criteria));
	}

}
