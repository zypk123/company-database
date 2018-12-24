package cy.its.syscfg.repository.sysCode;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.bus.EventBus;
import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.data.SysCodeData;
import cy.its.syscfg.domain.criteria.DistrictCriteria;
import cy.its.syscfg.domain.model.district.District;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.model.sysCode.CodeType;
import cy.its.syscfg.domain.repository.sysCode.ISysCodeRepository;
import cy.its.syscfg.mybatis.client.T_Sys_CodeMapper;
import cy.its.syscfg.mybatis.client.T_Sys_Code_TypeMapper;
import cy.its.syscfg.mybatis.client.T_Sys_DistrictMapper;
import cy.its.syscfg.mybatis.client.T_Sys_Plate_PrefixMapper;
import cy.its.syscfg.mybatis.model.T_Sys_Code;
import cy.its.syscfg.mybatis.model.T_Sys_Code_Type;
import cy.its.syscfg.mybatis.model.T_Sys_District;
import cy.its.syscfg.mybatis.model.T_Sys_Plate_Prefix;
import cy.its.syscfg.util.Convert;

@Service
public class SysCodeRepository implements ISysCodeRepository {

	@Autowired
	SysCodeData sysCodeData;

	@Autowired
	T_Sys_Code_TypeMapper t_Sys_Code_TypeMapper;

	@Autowired
	T_Sys_CodeMapper t_Sys_CodeMapper;

	@Autowired
	T_Sys_DistrictMapper t_Sys_DistrictMapper;
	
	@Autowired
	EventBus eventBus;
	@Autowired
	T_Sys_Plate_PrefixMapper t_Sys_Plate_PrefixMapper;

	@Override
	public CodeType aggregateOfId(String id) {
		return sysCodeData.dataOfId(id);
	}

	@Override
	public String save(CodeType obj) {
		T_Sys_Code_Type codeType = Convert.convert(obj);
		t_Sys_Code_TypeMapper.insertSelective(codeType);
		return codeType.getCodeType();
	}

	@Override
	public int delete(String id) {
		return t_Sys_Code_TypeMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public int update(CodeType obj) {
		return t_Sys_Code_TypeMapper.updateByPrimaryKeySelective(Convert
				.convert(obj));
	}

	@Override
	public List<CodeType> findCodeTypes() {
		return sysCodeData.findCodeTypes();
	}

	@Override
	public void removeCode(String codeId) {
		t_Sys_CodeMapper.deleteByPrimaryKey(codeId);
	}

	@Override
	public void deleteCode(Map<String,Object> codeIds) {
		t_Sys_CodeMapper.removeByPrimaryKey(codeIds);
	}
	@Override
	public void saveCode(Code code) {
		T_Sys_Code sysCode = Convert.convert(code);
		t_Sys_CodeMapper.insertSelective(sysCode);
	}

	@Override
	public void upddateCode(Code code) {
		t_Sys_CodeMapper.updateByPrimaryKeySelective(Convert.convert(code));
	}

	@Override
	public List<District> findDistrict(DistrictCriteria crieria) {
		List<T_Sys_District> lstDistricts = t_Sys_DistrictMapper
				.selectDistricts(parseDistrictParam(crieria));

		return lstDistricts.stream().map((c) -> {
			District district = new District();
			ObjectMapUtils.parseObject(district, c);
			return district;
		}).collect(Collectors.toList());
	}

	@Override
	public int countDistrict(DistrictCriteria crieria) {
		return t_Sys_DistrictMapper.countDistricts(parseDistrictParam(crieria));
	}

	@Override
	public void sysCodeChanged() {
		eventBus.publish(sysCodeData.getTopic(), "");
	}

	protected Map<String, Object> parseDistrictParam(DistrictCriteria crieria) {
		if (!StringUtil.isNullOrEmpty(crieria.districtCode)) {
			crieria.districtCode = StringUtil
					.trimEnd(crieria.districtCode, '0');
			if (crieria.districtCode.length() % 2 != 0) {
				crieria.districtCode = crieria.districtCode + "0";
			}
		}

		Map<String, Object> paramsMap = ParamUtil.parseParams(crieria);
		return paramsMap;
	}

	@Override
	public Map<String,String> selectAllData() {
		Map<String,String> map =  new HashMap<String,String>();
		// TODO Auto-generated method stub
		List<T_Sys_Plate_Prefix> list= this.t_Sys_Plate_PrefixMapper.selectAllData();
		for(T_Sys_Plate_Prefix platePrefix:list){
			map.put(platePrefix.getOrgCode(), platePrefix.getPlatePrefix());
		}
		return map;
	}

}
