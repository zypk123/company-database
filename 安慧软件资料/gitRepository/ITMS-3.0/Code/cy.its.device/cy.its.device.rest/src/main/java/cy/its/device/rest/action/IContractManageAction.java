package cy.its.device.rest.action;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import cy.its.device.rest.dto.ContractDto;
import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.TreeDto;

public interface IContractManageAction {
	/**
	 * 查询合同信息
	 * @param form 查询条件
	 * @return 查询结果对象列表
	 */
	public DataGridResult<ContractDto> queryContract(ContractDto form) throws Exception; 
	
	/**
	 * 添加合同信息
	 * @param form 添加的合同信息 
	 * @return 整形 1表示成功，0表示失败
	 */
	public int addContract(ContractDto form) throws Exception ;
	
	/**
	 * 删除合同信息
	 * @param contractId 合同ID
	 * @return 整形 1表示成功，0表示失败
	 */
	public int removeContract(String contractId) throws Exception ;
	
	/**
	 * 批量删除合同信息
	 * @param contractIdStr  多个合同ID字符串
	 * @return 整形 1表示成功，0表示失败
	 */
	public int removeSomeContract(String contractIdStr) throws Exception ;
	
	/**
	 * 编辑合同信息
	 * @param form 合同编辑后信息
	 * @return 整形 1表示成功，0表示失败
	 */
	public int updateContract(ContractDto form) throws Exception ;
	
	/**
	 * 查询所有合同
	 * @return
	 * @throws Exception
	 */
	public List<TreeDto> queryAllContract() throws Exception;
	
	/**
	 * 根据厂商ID查询该厂商的合同数
	 * @param contractorId  厂商ID
	 * @return 合同数
	 */
	public int queryContractNum(String contractorId) throws Exception;
}
