package cy.its.syscfg.domain.repository.sysCode;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.criteria.DistrictCriteria;
import cy.its.syscfg.domain.model.district.District;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.model.sysCode.CodeType;

public interface ISysCodeRepository extends IRepository<CodeType> {
	List<CodeType> findCodeTypes();
	void removeCode(String codeId);
	void saveCode(Code code);
	void upddateCode(Code code);
	List<District> findDistrict(DistrictCriteria crieria);
	int countDistrict(DistrictCriteria crieria);
	void sysCodeChanged();
	
	void deleteCode(Map<String,Object> codeIds);
	Map<String,String> selectAllData();
}
