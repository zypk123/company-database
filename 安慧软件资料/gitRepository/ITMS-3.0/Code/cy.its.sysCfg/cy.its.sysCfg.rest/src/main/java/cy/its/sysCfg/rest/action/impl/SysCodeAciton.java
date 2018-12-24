package cy.its.sysCfg.rest.action.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import cy.its.sysCfg.rest.action.ISysCodeAction;
import cy.its.sysCfg.rest.dto.CodeComboxDto;
import cy.its.sysCfg.rest.dto.DistrictTreeDto;
import cy.its.sysCfg.rest.dto.OrgTreeDto;
import cy.its.syscfg.domain.criteria.DistrictCriteria;
import cy.its.syscfg.domain.model.district.District;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.service.ISysCodeService;

@RestController
@RequestMapping("/sysCfg/sysCode")
public class SysCodeAciton implements ISysCodeAction{
	
	@Autowired
	ISysCodeService sysCodeService;
	
	/**
	 * 初始化数据
	 * @param codeTypesString 系统代码类型，多个以逗号隔开
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getSysCodes")
	public Map initSyscodeData(@RequestParam(value="codeTypesString",required=true) String codeTypesString) throws Exception{
		String[] codeTypes = codeTypesString.split(",");
		Map<String,List<CodeComboxDto>> returnMap = new HashMap<String,List<CodeComboxDto>>();
		//根据codeType，获得所以后的数据字典
		for(String codeType : codeTypes){
			returnMap.put(codeType, getSysCodesByType(codeType));
		}
		return returnMap;
	}
	
	/**
	 * 初始化行政区划
	 * @param districtCode 行政区划根节点带代码
	 * @return 该节点下的所有行政区划信息
	 * @throws Exception
	 */
	@RequestMapping("/getDistrict")
	public List<DistrictTreeDto> initDistrictData(@RequestParam(value="districtCode",required=true) String districtCode) throws Exception{
		//获取全部的行政区划
		DistrictCriteria crieria = new DistrictCriteria();
		crieria.districtCode = districtCode;
		crieria.setNoPage();
		List<District> districtList = sysCodeService.findDistrict(crieria);
		//按照行政区划编码进行排序
		sortDistrict(districtList);
		//转化成树行结构
		DistrictTreeDto districtTreeDto = convertToDto(districtList.get(0));
		districtTreeDto = convertToDtoList(districtTreeDto, 
				districtList,districtTreeDto.getId().substring(0, 2),4);
		List<DistrictTreeDto> returnList = new ArrayList<DistrictTreeDto>();
		returnList.add(districtTreeDto);
		return returnList;
	}
	
	/**
	 * 将domain返回的list对象转化为树形结构
	 * @param districtTreeDto 树结构对象
	 * @param districtList domain层返回的列表
	 * @param code 父节点编码
	 * @param length 非0字符串长度
	 * @return
	 */
	private DistrictTreeDto convertToDtoList(DistrictTreeDto districtTreeDto, List<District> districtList,
			String code, int length) {
		List<DistrictTreeDto> dtoList = new ArrayList<DistrictTreeDto>();
		for(int index=0 ; index<districtList.size(); index++){
			District district = districtList.get(index);
			String codeWithout0 = subDistrictCode(district.districtCode);
			if(codeWithout0.length() == length && codeWithout0.startsWith(code)){
				districtList.remove(index--);
				DistrictTreeDto dto = convertToDto(district);
				dto = convertToDtoList(dto, 
						districtList,dto.getId().substring(0, length),length + 2);
				dtoList.add(dto);
			}
		}
		if(dtoList.size() != 0){
			districtTreeDto.setChildren(dtoList);
		}
		return districtTreeDto;
	}
	
	/**
	 * 将行政区划代码中后面所有的0去除
	 * @param districtCode
	 * @return
	 */
	private String subDistrictCode(String districtCode) {
		while(districtCode.endsWith("00")){
			districtCode = districtCode.substring(0, districtCode.length() - 2);
		}
		// TODO Auto-generated method stub
		return districtCode;
	}

	
	/**
	 * 给行政区划排序
	 * @param districtList
	 */
	private void sortDistrict(List<District> districtList) {
		Collections.sort(districtList,new Comparator<District>(){
			public int compare(District arg0, District arg1) {
                return arg0.districtCode.compareTo(arg0.districtCode);
            }
		});
	}

	/**
	 * 将领域对象转成dto
	 * @param org
	 * @return
	 */
	private DistrictTreeDto convertToDto(District district){
		DistrictTreeDto dto = new DistrictTreeDto();
		dto.setId(district.getIdentityId());
		dto.setText(district.districtName);
		Map<String,String> attribute = new HashMap<String,String>();
		attribute.put("code", district.districtCode);
		dto.setAttribute(attribute);
		return dto;
	}
	/**
	 * 根据字典类型获取字典项
	 * @return
	 */
	private List<CodeComboxDto> getSysCodesByType(String codeType) throws Exception{
		List<Code> codeList = sysCodeService.codeListOfType(codeType);
		//将code转化为前端使用的DTO，并封装到map中
		Iterator<Code> iter = codeList.iterator();
		List<CodeComboxDto> codeDtoList = new ArrayList<CodeComboxDto>();
		while(iter.hasNext()){
			Code code = iter.next();
			CodeComboxDto codeDto = new CodeComboxDto();
			codeDto.setValue(code.codeNo);
			codeDto.setText(code.codeName);
			codeDtoList.add(codeDto);
		}
		return codeDtoList;
	}

	@Override
	@RequestMapping("/getPlatePrefix")
	public Map<String, String> initPlatePrefixData() throws Exception {
		// TODO Auto-generated method stub
		return this.sysCodeService.selectAllData();
	}
	
	
}
	
