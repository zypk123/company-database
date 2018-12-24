package cy.its.device.rest.action;


import java.util.List;

import cy.its.device.rest.dto.CompanyDto;
import cy.its.device.rest.dto.DataGridResult;

public interface ICompanyManageAction {
	/**
	 * 查询厂商信息列表
	 * @param form 查询条件
	 * @return 查询结果对象列表
	 */
	public DataGridResult<CompanyDto> queryCompany(CompanyDto form) throws Exception; 
	
	/**
	 * 查询所有的厂商
	 * @return 查询结果对象列表
	 */
	public List<CompanyDto> queryCompanyInfo() throws Exception; 
	
	/**
	 * 添加厂商信息
	 * @param form 添加的厂商信息 
	 * @return 整形 1表示成功，0表示失败
	 */
	public int addCompany(CompanyDto form) throws Exception;
	
	/**
	 * 删除厂商信息
	 * @param contractorId 承建商ID
	 * @return 整形 1表示成功，0表示失败
	 */
	public int removeCompany(String contractorId) throws Exception;
	
	/**
	 * 批量删除厂商信息
	 * @param contractorIdStr  多个承建商ID字符串
	 * @return 整形 1表示成功，0表示失败
	 */
	public int removeSomeCompany(String contractorIdStr) throws Exception;
	
	/**
	 * 编辑厂商信息
	 * @param form 承建商编辑后信息
	 * @return 整形 1表示成功，0表示失败
	 */
	public int updateCompany(CompanyDto form) throws Exception;
	
	/**
	 *  查询厂商对象
	 * @param contractorId 厂商ID
	 * @return
	 * @throws Exception
	 */
	public CompanyDto queryCompanyDto(String contractorId) throws Exception;
}
