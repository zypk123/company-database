package cy.its.syscfg.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.criteria.DistrictCriteria;
import cy.its.syscfg.domain.model.district.District;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.model.sysCode.CodeType;

public interface ISysCodeService {

	/**
	 * 查询所有的代码类型信息列表
	 * 
	 * @return 代码类型信息列表
	 */
	public List<CodeType> findCodeTypes();
	
	/**
	 * 查询所有的系统代码信息列表
	 * 
	 * @return 系统代码信息列表
	 */
	public List<Code> findCodes();

	/**
	 * 查询指定代码类型下的所有代码定义
	 * 
	 * @param codeType
	 *            代码类型编码
	 * @return 代码定义列表
	 * @throws Exception 
	 */
	public List<Code> codeListOfType(String codeType) throws Exception;

	/**
	 * 查询指定的系统代码类型
	 * 
	 * @param codeType
	 *            系统代码类型编码
	 * @return 系统代码类型
	 * @throws Exception 
	 */
	public CodeType codeTypeOfId(String codeType) throws Exception;

	/**
	 * 查询指定的系统代码定义
	 * 
	 * @param codeType
	 *            系统代码类型
	 * @param codeNo
	 *            系统代码
	 * @return 系统代码定义
	 * @throws Exception 
	 */
	public Code codeOfId(String codeType, String codeNo) throws Exception;

	/**
	 * 删除指定的系统代码类型
	 * 
	 * @param codeType
	 *            代码类型编码
	 * @throws Exception 
	 */
	public void removeCodeType(String codeType) throws Exception;

	/**
	 * 删除指定的系统代码
	 * 
	 * @param codeId
	 *            系统代码唯一标识ID
	 */
	public void removeCode(String codeId);

	//批量删除
	public void deleteCode(Map<String,Object> codeIds);
	/**
	 * 创建系统代码类型
	 * 
	 * @param codeType
	 *            系统代码类型信息
	 */
	public void createCodeType(CodeType codeType);

	/**
	 * 创建系统代码
	 * 
	 * @param code
	 *            系统代码信息
	 */
	public void createCode(Code code);

	/**
	 * 更新系统代码
	 * 
	 * @param code
	 *            系统代码信息
	 */
	public void updateCode(Code code);

	/**
	 * 查询符合条件的行政区划信息列表
	 * 
	 * @param crieria
	 *            查询条件
	 * @return 行政区划信息列表
	 */
	public List<District> findDistrict(DistrictCriteria crieria);
	
	/**
	 * 系统代码变更通知
	 */
	public void sysCodeChanged();

	/**
	 * 号牌前缀
	 * @return
	 */
	public Map<String,String> selectAllData() ;
}