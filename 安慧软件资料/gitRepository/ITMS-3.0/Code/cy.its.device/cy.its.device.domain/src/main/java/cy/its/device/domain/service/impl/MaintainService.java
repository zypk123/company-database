package cy.its.device.domain.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.device.domain.criteria.DailyMaintainCriteria;
import cy.its.device.domain.criteria.FaultCriteria;
import cy.its.device.domain.criteria.MaintainRegisterCriteria;
import cy.its.device.domain.model.DailyMaintain;
import cy.its.device.domain.model.Fault;
import cy.its.device.domain.model.MaintainRegister;
import cy.its.device.domain.repository.maintain.IDailyMaintainRepository;
import cy.its.device.domain.repository.maintain.IFaultRepository;
import cy.its.device.domain.repository.maintain.IMaintainRegisterRepository;
import cy.its.device.domain.service.IMaintainService;

/**
 * 设备维护服务
 * 
 * @author STJ
 *
 */
@Service
public class MaintainService implements IMaintainService {

	@Autowired
	IFaultRepository faultRepository;
	
	@Autowired
	IDailyMaintainRepository dailyMaintainRepository;
	
	@Autowired
	IMaintainRegisterRepository maintainRegisterRepository;

	public void createDeviceFault(Fault fault) {
		faultRepository.save(fault);
	}

	public List<Fault> findFaults(FaultCriteria faultCriteria) {
		if(faultCriteria.getNeedTotal()){
			faultCriteria.setTotal(faultRepository.countFaults(faultCriteria));
		}
		return faultRepository.findFaults(faultCriteria);
	}

	public Boolean validateFault(String faultId) throws Exception {
		Fault deviceFault = faultRepository.aggregateOfId(faultId);
		return deviceFault.isValidity();
	}

	public void createMaintainRegister(List<String> faultIds,
			MaintainRegister maintainRegister) throws Exception {
		String maintenanceId = maintainRegisterRepository
				.save(maintainRegister);
		for (String faultId : faultIds) {
			Fault fault = faultRepository.aggregateOfId(faultId);
			fault.attachMaintenance(maintenanceId);
			faultRepository.update(fault);
		}
	}

	public void assignMaintainToCompany(String maintainId,
			String maintenanceCompany, String contactPerson, String phoneNbr,
			String finishTime, String assignBy) throws Exception {
		MaintainRegister maintain = maintainRegisterRepository
				.aggregateOfId(maintainId);
		maintain.assignToCompany(maintenanceCompany, contactPerson, phoneNbr,
				finishTime, assignBy);
		maintainRegisterRepository.update(maintain);
	}

	public List<Fault> faultsOfMaintain(String maintenanceId) {
		FaultCriteria criteria = new FaultCriteria();
		criteria.maintenanceId = maintenanceId;
		return faultRepository.findFaults(criteria);
	}

	public void attachFaultToMaintain(String maintenanceId, String faultId)
			throws Exception {
		Fault fault = faultRepository.aggregateOfId(faultId);
		fault.attachMaintenance(maintenanceId);
		faultRepository.update(fault);
	}

	public void disAttachFaultOfMaintain(String maintenanceId, String faultId)
			throws Exception {
		Fault fault = faultRepository.aggregateOfId(faultId);
		fault.disAttachMaintenance(maintenanceId);
		faultRepository.update(fault);
	}

	public List<DailyMaintain> findDailyMaintains(DailyMaintainCriteria criteria) {
		return dailyMaintainRepository.findDailyMaintains(criteria);
	}

	public void createDailyMaintain(DailyMaintain dailyMaintain) {
		dailyMaintainRepository.save(dailyMaintain);
	}

	public DailyMaintain dailyMaintainOfId(String dailyMaintenanceId) throws Exception {
		return dailyMaintainRepository.aggregateOfId(dailyMaintenanceId);
	}
	
	public void updateDailyMaintain(DailyMaintain dailyMaintain) throws Exception {
		dailyMaintainRepository.update(dailyMaintain);
	}
	
	public void deleteDailyMaintain(String dailyMaintenanceId) throws Exception {
		dailyMaintainRepository.delete(dailyMaintenanceId);
	}

	public List<MaintainRegister> findMaintainRegisters(
			MaintainRegisterCriteria criteria) {
		if(criteria.getNeedTotal()){
			criteria.setTotal(maintainRegisterRepository.countMaintainRegisters(criteria));
		}
		return maintainRegisterRepository.findMaintainRegisters(criteria);
	}

	public void registerMaintainResult(String maintenanceId,
			String maintendanceResult, String solution, Date solutionTime,
			String remark, String processState) throws Exception {
		
		MaintainRegister maintain = maintainRegisterRepository
				.aggregateOfId(maintenanceId);
		maintain.regMaintainResult(maintendanceResult, solution, solutionTime,
				remark);
		
		FaultCriteria criteria = new FaultCriteria();
		criteria.maintenanceId = maintenanceId;
		List<Fault> faults = faultRepository.findFaults(criteria);
		for (Fault fault : faults) {
			fault.setProcessState(processState);
			faultRepository.update(fault);
		}

		maintainRegisterRepository.update(maintain);
	}


}
