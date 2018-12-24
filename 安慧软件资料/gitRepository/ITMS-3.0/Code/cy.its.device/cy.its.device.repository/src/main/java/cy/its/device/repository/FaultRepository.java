package cy.its.device.repository;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.FaultCriteria;
import cy.its.device.domain.model.Fault;
import cy.its.device.domain.repository.maintain.IFaultRepository;
import cy.its.device.mybatis.client.FaultMapper;

@Service
public class FaultRepository implements IFaultRepository {

	@Autowired
	FaultMapper faultMapper;

	public Fault aggregateOfId(String id) throws Exception {
		return faultMapper.selectByPrimaryKey(id);
	}

	public String save(Fault obj) {
		obj.setFaultId(StringUtil.generateUUID());
		faultMapper.insertSelective(obj);
		return obj.getFaultId();
	}

	public int delete(String id) {
		return faultMapper.deleteByPrimaryKey(id);
	}

	public int update(Fault obj) {
		return faultMapper.updateByPrimaryKeySelective(obj);
	}

	public List<Fault> findFaults(FaultCriteria faultCriteria) {
		return faultMapper.selectFaults(ParamUtil.parseParams(faultCriteria));
	}

	public int countFaults(FaultCriteria faultCriteria) {
		return faultMapper.countFaults(ParamUtil.parseParams(faultCriteria));
	}

}
