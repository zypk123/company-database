package cy.its.syscfg.domain.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.criteria.DistrictCriteria;
import cy.its.syscfg.domain.model.district.District;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.model.sysCode.CodeType;
import cy.its.syscfg.domain.repository.sysCode.ISysCodeRepository;
import cy.its.syscfg.domain.service.ISysCodeService;

/**
 * 系统代码服务
 *
 */
@Service
public class SysCodeService implements ISysCodeService {

	@Autowired
	ISysCodeRepository sysCodeRepository;

	/**
	 * 查询所有的代码类型信息列表
	 * 
	 * @return 代码类型信息列表
	 */
	public List<CodeType> findCodeTypes() {
		return sysCodeRepository.findCodeTypes();
	}

	/**
	 * 查询所有的系统代码信息列表
	 * 
	 * @return 系统代码信息列表
	 */
	public List<Code> findCodes() {
		List<CodeType> codeTypes = findCodeTypes();
		if (codeTypes != null) {
			List<Code> lstCodes = new ArrayList<Code>();
			codeTypes.stream().forEach(c -> {
				lstCodes.addAll(c.codeList());
			});
			return lstCodes;
		}

		return null;
	}

	/**
	 * 查询指定代码类型下的所有代码定义
	 * 
	 * @param codeType
	 *            代码类型编码
	 * @return 代码定义列表
	 * @throws Exception
	 */
	public List<Code> codeListOfType(String codeType) throws Exception {
		CodeType type = sysCodeRepository.aggregateOfId(codeType);
		return type == null ? null : type.codeList();
	}

	/**
	 * 查询指定的系统代码类型
	 * 
	 * @param codeType
	 *            系统代码类型编码
	 * @return 系统代码类型
	 * @throws Exception
	 */
	public CodeType codeTypeOfId(String codeType) throws Exception {
		return sysCodeRepository.aggregateOfId(codeType);
	}

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
	public Code codeOfId(String codeType, String codeNo) throws Exception {
		CodeType type = sysCodeRepository.aggregateOfId(codeType);
		return type != null ? type.code(codeNo) : null;
	}

	/**
	 * 删除指定的系统代码类型
	 * 
	 * @param codeType
	 *            代码类型编码
	 * @throws Exception
	 */
	public void removeCodeType(String codeType) throws Exception {
		CodeType type = sysCodeRepository.aggregateOfId(codeType);
		for (Code code : type.codeList()) {
			sysCodeRepository.removeCode(code.getIdentityId());
		}
		sysCodeRepository.delete(codeType);
	}

	/**
	 * 删除指定的系统代码
	 * 
	 * @param codeId
	 *            系统代码唯一标识ID
	 */
	public void removeCode(String codeId) {
		sysCodeRepository.removeCode(codeId);
	}
	/**
	 * 批量删除
	 */
	public void deleteCode(Map<String,Object> codeIds){
		sysCodeRepository.deleteCode(codeIds);
	}
	/**
	 * 创建系统代码类型
	 * 
	 * @param codeType
	 *            系统代码类型信息
	 */
	public void createCodeType(CodeType codeType) {
		sysCodeRepository.save(codeType);
	}

	/**
	 * 创建系统代码
	 * 
	 * @param code
	 *            系统代码信息
	 */
	public void createCode(Code code) {
		sysCodeRepository.saveCode(code);
	}

	/**
	 * 更新系统代码
	 * 
	 * @param code
	 *            系统代码信息
	 */
	public void updateCode(Code code) {
		sysCodeRepository.upddateCode(code);
	}

	/**
	 * 查询符合条件的行政区划信息列表 注:可分页
	 * 
	 * @param crieria
	 *            查询条件
	 * @return 行政区划信息列表
	 */
	public List<District> findDistrict(DistrictCriteria crieria) {
		if (crieria.getNeedTotal()) {
			crieria.setTotal(sysCodeRepository.countDistrict(crieria));
		}
		return sysCodeRepository.findDistrict(crieria);
	}

	/**
	 * 系统代码更新通知
	 */
	public void sysCodeChanged() {
		sysCodeRepository.sysCodeChanged();
	}

	@Override
	public Map<String, String> selectAllData() {
		// TODO Auto-generated method stub
		return this.sysCodeRepository.selectAllData();
	}

}
