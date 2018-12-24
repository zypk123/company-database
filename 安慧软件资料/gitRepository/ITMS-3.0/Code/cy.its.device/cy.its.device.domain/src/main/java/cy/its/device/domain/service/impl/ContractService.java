package cy.its.device.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.criteria.ContractCriteria;
import cy.its.device.domain.criteria.ContractorCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.Contract;
import cy.its.device.domain.model.Contractor;
import cy.its.device.domain.repository.ISysRepository;
import cy.its.device.domain.repository.contract.IContractRepository;
import cy.its.device.domain.repository.contract.IContractorRepository;
import cy.its.device.domain.service.IContractService;

/**
 * 设备厂商和合同服务
 * 
 * @author STJ
 *
 */
@Service
public class ContractService implements IContractService {

	@Autowired
	IContractorRepository contractorRepository;
	
	@Autowired
	IContractRepository contractRepository;
	
	@Autowired
	ISysRepository sysRepository;
	
	
	public void createContractor(Contractor contractor) throws Exception {
		ContractorCriteria c = new ContractorCriteria();
		c.contractorNameForExactFind = contractor.getContractorName();
		if(contractorRepository.countDeviceContractors(c) > 0) {
			throw new Exception("存在相同名称的厂商,无法创建厂商！");
		}
		
		contractorRepository.save(contractor);
	}

	public void deleteContractor(String contractorId) throws Exception {
		SystemCriteria c = new SystemCriteria();
		c.contractorId = contractorId;
		int count = sysRepository.countSystems(c);
		if(count > 0) {
			throw new Exception("该厂商下存在设备和装备,无法删除！");
		}
		
		ContractCriteria cc = new ContractCriteria();
		cc.contractorId = contractorId;
		count = contractRepository.countDeviceContracts(cc);
		if(count > 0) {
			throw new Exception("该厂商下存在合同,无法删除！");
		}
		
		contractorRepository.delete(contractorId);
	}

	public void updateContractor(Contractor contractor) throws Exception {
		ContractorCriteria c = new ContractorCriteria();
		c.contractorNameForExactFind = contractor.getContractorName();
		List<Contractor> lst = contractorRepository.findDeviceContractors(c);
		if(lst != null){
			lst.removeIf(t->t.getContractorId().equals(contractor.getContractorId()));
			
			if(lst.size() > 0){
				throw new Exception("存在相同名称的厂商,无法更新厂商！");
			}
		}
		
		contractorRepository.update(contractor);
	}

	public List<Contractor> findContractors(ContractorCriteria criteria) {
		if(criteria.getNeedTotal()){
			criteria.setTotal(contractorRepository.countDeviceContractors(criteria));
		}
		return contractorRepository.findDeviceContractors(criteria);
	}

	public void createDeviceContract(Contract deviceContract) throws Exception {
		if(deviceContract.nbrIsRepeated(contractRepository)){
			throw new Exception("已存在与当前合同编号相同的合同");
		}
		
		contractRepository.save(deviceContract);
	}

	public void deleteDeviceContract(String contractId) throws Exception {
		SystemCriteria c = new SystemCriteria();
		c.contractId = contractId;
		int count = sysRepository.countSystems(c);
		if(count > 0) {
			throw new Exception("该合同下存在设备和装备,无法删除！");
		}
		
		
		contractRepository.delete(contractId);
	}

	public void updateContract(Contract deviceContract) throws Exception {
		if(deviceContract.nbrIsRepeated(contractRepository)){
			throw new Exception("已存在与当前合同编号相同的合同");
		}
		contractRepository.update(deviceContract);
	}

	public List<Contract> findContracts(ContractCriteria criteria) {
		return contractRepository.findDeviceContracts(criteria);
	}

	/*
	  * <p>Title: getContractorById</p>
	  * <p>Description: </p>
	  * @param contractorId
	  * @return
	  * @see cy.its.device.domain.service.IContractService#getContractorById(java.lang.String)
	  */	
	
	@Override
	public Contractor getContractorById(String contractorId) throws Exception {
		return contractorRepository.aggregateOfId(contractorId);	
	}

}