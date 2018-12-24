package cy.its.device.domain.service;

import java.util.List;

import cy.its.device.domain.criteria.ContractCriteria;
import cy.its.device.domain.criteria.ContractorCriteria;
import cy.its.device.domain.model.Contract;
import cy.its.device.domain.model.Contractor;



public interface IContractService {

	Contractor getContractorById(String contractorId) throws Exception;
	/**
	 * 创建新设备承建商
	 * 
	 * @param deviceContractor
	 *            设备承建商
	 * @throws Exception 
	 */
	public void createContractor(Contractor  contractor) throws Exception;

	/**
	 * 删除指定设备承建商
	 * 
	 * @param contractorId
	 *            设备承建商唯一标识
	 * @throws Exception 
	 */
	public void deleteContractor(String contractorId) throws Exception;

	/**
	 * 更新设备承建商
	 * 
	 * @param deviceContractor
	 *            设备承建商
	 * @throws Exception 
	 */
	public void updateContractor(Contractor contractor) throws Exception;

	/**
	 * 查询符合条件的设备承建商列表
	 * 
	 * @param criteria
	 *            查询条件
	 * @return 设备承建商
	 */
	public List<Contractor> findContractors(ContractorCriteria criteria);

	/**
	 * 创建新设备合同
	 * 
	 * @param deviceContract
	 *            设备合同
	 * @throws Exception 
	 */
	public void createDeviceContract(Contract deviceContract) throws Exception;

	/**
	 * 删除指定的设备合同
	 * 
	 * @param contractId
	 *            设备合同唯一标识
	 * @throws Exception 
	 */
	public void deleteDeviceContract(String contractId) throws Exception;

	/**
	 * 更新设备合同
	 * 
	 * @param deviceContract
	 *            设备合同
	 * @throws Exception 
	 */
	public void updateContract(Contract deviceContract) throws Exception;

	/**
	 * 查询设备合同列表
	 * 
	 * @param criteria
	 *            查询条件
	 * @return 设备合同列表
	 */
	public List<Contract> findContracts(ContractCriteria criteria);

}