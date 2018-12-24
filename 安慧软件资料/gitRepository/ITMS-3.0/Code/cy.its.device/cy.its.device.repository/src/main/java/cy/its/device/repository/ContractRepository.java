package cy.its.device.repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.ContractCriteria;
import cy.its.device.domain.model.Contract;
import cy.its.device.domain.repository.contract.IContractRepository;
import cy.its.device.mybatis.client.ContractMapper;

@Service
public class ContractRepository implements IContractRepository {
	
	@Autowired
	ContractMapper contractMapper;
	
	public Contract aggregateOfId(String id) throws Exception {
		return contractMapper.selectByPrimaryKey(id);
	}

	public String save(Contract obj) {
		obj.setContractId(StringUtil.generateUUID());
		contractMapper.insertSelective(obj);
		return obj.getContractId();
	}

	public int delete(String id) {
		return contractMapper.deleteByPrimaryKey(id);
	}

	public int update(Contract obj) {
		return contractMapper.updateByPrimaryKeySelective(obj);
	}

	public List<Contract> findDeviceContracts(ContractCriteria criteria) {
		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(criteria.getOrderName())){
			PageHelper.orderBy(criteria.getOrderName() + " " + criteria.getOrderType());
		}
		Page<Contract> page = (Page<Contract>) contractMapper.selectContracts(ParamUtil.parseParams(criteria));
		criteria.setTotal(page.getTotal());
		return page.getResult();
	}
	
	public int countDeviceContracts(ContractCriteria criteria) {
		return contractMapper.countContracts(ParamUtil.parseParams(criteria));
	}
}
