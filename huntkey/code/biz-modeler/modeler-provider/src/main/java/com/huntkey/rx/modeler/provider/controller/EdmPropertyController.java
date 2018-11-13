package com.huntkey.rx.modeler.provider.controller;

import com.alibaba.druid.util.StringUtils;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.EdmConnect;
import com.huntkey.rx.modeler.common.model.EdmConvolute;
import com.huntkey.rx.modeler.common.model.EdmConvoluteExtend;
import com.huntkey.rx.modeler.common.model.EdmProperty;
import com.huntkey.rx.modeler.common.model.vo.*;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.provider.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by licj on 2017/4/13.
 */
@RestController
@RequestMapping("/properties")
public class EdmPropertyController {

	private static Logger log = LoggerFactory.getLogger(EdmPropertyController.class);

	@Autowired
	public EdmPropertyService edmPropertyService;
	@Autowired
	private EdmConnectService edmConnectService;
	@Autowired
	private EdmCondService edmCondService;
	@Autowired
	public EdmLinkService edmLinkService;

	/**
	 * 根据id获取属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result getProperty(@PathVariable String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		EdmPropertyVO edmPropertyVO = edmPropertyService.getProperty(id);
		result.setData(edmPropertyVO);
		return result;
	}

	/**
	 * 根据类id获取属性树
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/tree/{id}/{flag}", method = RequestMethod.GET)
	public Result getPropertyTree(@PathVariable String id,
								  @PathVariable int flag) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		List<EdmPropertyVO> edmPropertyVOList = edmPropertyService.getPropertyTreeByClassId(id,flag);
		result.setData(edmPropertyVOList);
		return result;
	}

	/**
	 * 在进行新增属性之前的检查edmpCode是否符合要求
	 * @param edmpCode
	 * @param edmpEdmcId
	 * @return
	 */
	@RequestMapping(value = "/edmpCode", method = RequestMethod.GET)
	public Result checkEdmpCode(@RequestParam String edmpCode,
								@RequestParam String edmpEdmcId,
								@RequestParam(required = false) String edmpParentId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		// 数据异常校验
		String errorStr = edmPropertyService.checkEdmpCode(edmpCode, edmpEdmcId,edmpParentId);
		if (!StringUtils.isEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
		}
		return result;
	}

	/**
	 * 在进行新增属性之前的检查edmpName是否符合要求
	 * @param edmpName
	 * @param edmpEdmcId
	 * @return
	 */
	@RequestMapping(value = "/edmpName", method = RequestMethod.GET)
	public Result checkEdmpName(@RequestParam(value = "edmpName") String edmpName,
								@RequestParam(value = "edmpEdmcId") String edmpEdmcId,
								@RequestParam(required = false) String edmpParentId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		// 数据异常校验
		String errorStr = edmPropertyService.checkEdmpName(edmpName, edmpEdmcId, edmpParentId);
		if (!StringUtil.isNullOrEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
		}
		return result;
	}

	/**
	 * 根据类id生成类下属性唯一编码（废弃）
	 * @param classId
	 * @return edmpCode
	 */
	@RequestMapping(value = "/getEdmpCode", method = RequestMethod.GET)
	public Result getEdmpCode(@RequestParam String classId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		String code = edmPropertyService.getUniqueEdmpCodeByClassId(classId);
		result.setData(code);
		if (StringUtils.isEmpty(code)) {
			result.setRetCode(Result.RECODE_ERROR);
			result.setErrMsg("获取属性编码失败！");
		}
		return result;
	}

	/**
	 * 新增属性
	 * @param edmProperty
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@Validated @RequestBody EdmProperty edmProperty) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		// 数据校验
		String errorStr = edmPropertyService.checkEdmpCode(edmProperty.getEdmpName(), edmProperty.getEdmpEdmcId(), edmProperty.getEdmpParentId());
		if (!StringUtil.isNullOrEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
			return result;
		}
		errorStr = edmPropertyService.checkEdmpName(edmProperty.getEdmpName(), edmProperty.getEdmpEdmcId(), edmProperty.getEdmpParentId());
		if (!StringUtil.isNullOrEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
			return result;
		}

		edmPropertyService.insert(edmProperty);
		result.setData(edmProperty.getId());
		return result;
	}

	/**
	 * 新增常量
	 * @param edmProperty
	 * @return
	 */
	@RequestMapping(value = "/consts", method = RequestMethod.POST)
	public Result addConsts(@Validated @RequestBody EdmProperty edmProperty) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmProperty.setEdmpValueType("const");//指明属性类别为常量
		// 数据校验
		// 数据校验
		String errorStr = edmPropertyService.checkEdmpCode(edmProperty.getEdmpName(), edmProperty.getEdmpEdmcId(), edmProperty.getEdmpParentId());
		if (!StringUtil.isNullOrEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
			return result;
		}
		errorStr = edmPropertyService.checkEdmpName(edmProperty.getEdmpName(), edmProperty.getEdmpEdmcId(), edmProperty.getEdmpParentId());
		if (!StringUtil.isNullOrEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
			return result;
		}
		edmPropertyService.insert(edmProperty);
		result.setData(edmProperty.getId());
		return result;
	}

	/**
	 * 修改属性、常量
	 * @param edmProperty
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Result update(@Validated @RequestBody EdmProperty edmProperty) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		String errorStr = ""; // 校验提交信息
		EdmProperty oldEdmProperty = edmPropertyService.selectById(edmProperty.getId());
		if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpCode()) && !oldEdmProperty.getEdmpCode().equals(edmProperty.getEdmpCode())) { // 如果属性名称修改，则校验属性名称唯一性
			errorStr = edmPropertyService.checkEdmpCode(edmProperty.getEdmpCode(), edmProperty.getEdmpEdmcId(), edmProperty.getEdmpParentId());
			if (!StringUtils.isEmpty(errorStr)) { // 校验失败
				result.setRetCode(Result.RECODE_VALIDATE_ERROR);
				result.setErrMsg(errorStr);
				return result;
			}
		}
		if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpName()) && !oldEdmProperty.getEdmpName().equals(edmProperty.getEdmpName())) { // 如果属性名称修改，则校验属性名称唯一性
			errorStr = edmPropertyService.checkEdmpName(edmProperty.getEdmpName(), edmProperty.getEdmpEdmcId(), edmProperty.getEdmpParentId());
			if (!StringUtils.isEmpty(errorStr)) { // 校验失败
				result.setRetCode(Result.RECODE_VALIDATE_ERROR);
				result.setErrMsg(errorStr);
				return result;
			}
		}
		edmPropertyService.update(edmProperty);
		return result;
	}

	/**
	 * 根据id删除属性（逻辑删除）(废弃)
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmPropertyService.delete(id);
		return result;
	}

	/**
	 * 根据id集合删除多条属性数据（逻辑删除）
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
	public Result deleteIds(@PathVariable String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmPropertyService.deleteIds(ids);
		return result;
	}

	/**
	 * 属性的上移、下移
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/move", method = RequestMethod.PUT)
	public Result move(@RequestBody String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmPropertyService.move(ids);
		return result;
	}

	/**
	 * 修改属性响应方法
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/methods", method = RequestMethod.PUT)
	public Result updateMethod(@RequestParam(value = "id") String id, @RequestParam(value = "mid") String mid) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmPropertyService.updateMethodById(id, mid);
		return result;
	}

	/**
	 * 修改属性触发条件（废弃）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/triggerCond", method = RequestMethod.PUT)
	public Result updateTriggerCond(@RequestParam(value = "id") String id,
									@RequestParam(value = "triggerCondId") String triggerCondId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmPropertyService.updateTriggerCond(id, triggerCondId);
		return result;
	}

	/**
	 * 根据属性id获取属性集
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/properties", method = RequestMethod.GET)
	public Result getProperties(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		List<EdmPropertyVO> edmPropertyVOList = edmPropertyService.getProperties(id);// 获取属性集
		result.setData(edmPropertyVOList);
		return result;
	}

	/**
	 * 根据属性id获取联动属性列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/connects", method = RequestMethod.GET)
	public Result getConnectsByEdcnEdmpId(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		List<EdmLinkVO> list = edmPropertyService.getConnectsByPropertyId(id);// 获取联动属性列表
		result.setData(list);
		return result;
	}

	/**
	 * 根据属性id 获取卷积属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/convolute", method = RequestMethod.GET)
	public Result getConvolutesByEdcoEdmpId(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		//EdmConvoluteVO edmConvolute = edmPropertyService.getConvolute(id);
		result.setData(edmPropertyService.getConvoluteByPropertyId(id));
		return result;
	}

	/**
	 * 根据属性id获取关联属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/links", method = RequestMethod.GET)
	public Result getLinksByEdmlEdmpId(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		//List<EdmLinkVO> list = edmPropertyService.getTableForLinksAndConnects(id);// 获取关联属性集
		List<EdmLinkVO> list = edmPropertyService.getLinksByPropertyId(id);// 获取关联属性集
		result.setData(list);
		return result;
	}

	/**
	 * 根据属性id获取单位属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/units", method = RequestMethod.GET)
	public Result getUnitsByEdunEdmpId(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		List<EdmUnitVO> list = edmPropertyService.getUnitsByPropertyId(id);// 获取属性集
		result.setData(list);
		return result;
	}

	/**
	 * 根据属性id 获取触发条件
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/conds", method = RequestMethod.GET)
	public Result getCondsByPropertyId(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		List<EdmCondVO> edmCondVOs = edmCondService.getCondsByPropertyId(id);
		result.setData(edmCondVOs);
		return result;
	}

	/**
	 * 查找属性的同类属性（废弃）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/units/sameclassunitlist", method = RequestMethod.GET)
	public Result getSameClassUnitsListByEdunEdmpId(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		try {
			List<EdmProperty> list = edmPropertyService.findPropertyNameByIdInSameClass(id);// 获取属性集
			result.setData(list);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(Result.RECODE_ERROR);
			result.setErrMsg("系统异常");
		}
		return result;
	}

	/**
	 * 根据edcnEdmpId 获取edmConnect（联动表头使用）（废弃）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/connect/properties", method = RequestMethod.GET)
	public Result getEdmConnectById(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmConnectService.getConnectByEdmpId(id));
		return result;
	}

	/**
	 * 根据属性id查询edmp_value_limit
	 * @param id
	 * @return edmp_value_limit
	 */
	@RequestMapping(value = "/getValueLimitById/{id}", method = RequestMethod.GET)
	public Result getValueLimitById(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmPropertyService.getValueLimitById(id));
		return result;
	}

	/**
	 * 根据属性id更新属性某个字段的值
	 * @param edmProperty
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Result updateProperty(@RequestBody EdmProperty edmProperty) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmPropertyService.updateProperty(edmProperty);
		return result;
	}

	/**
	 * 根据类id查询类的前两个属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getTwoProperties/{id}", method = RequestMethod.GET)
	public Result getTwoProperties(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmPropertyService.getTwoProperties(id));
		return result;
	}

	/**
	 * 根据模型id查询枚举类的前两个属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getEnumTwoProperties/{id}", method = RequestMethod.GET)
	public Result getEnumTwoProperties(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmPropertyService.getEnumTwoProperties(id));
		return result;
	}

	/**
	 * 根据属性id查询属性和所属类
	 * @param propertyId
	 * @return
	 */
	@RequestMapping(value = "/getPropertyAndClass", method = RequestMethod.GET)
	public Result getPropertyAndClass(@RequestParam(value="propertyId") String propertyId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		HashMap<String,Object> map = edmPropertyService.getPropertyAndClass(propertyId);
		result.setData(map);
		return result;
	}

	/**
	 * 根据类id与属性编码查询属性值（监管树用）
	 * @param classId
	 * @param edmpCode
	 * @return
	 */
	@RequestMapping(value = "/values", method = RequestMethod.GET)
	public Result getPropertyTypeAndValue(@RequestParam(value="classId") String classId,@RequestParam(value="edmpCode") String edmpCode) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		Map<String,Object> map = edmPropertyService.getPropertyTypeAndValue(classId,edmpCode);
		result.setData(map);
		return result;
	}

	/**
	 * 根据模型版本号和类的英文名称获取类及其父类的所有卷积属性
	 * @param edmdVer
	 * @param edmcNameEn
	 * @return
	 */
	@RequestMapping(value = "/getConProperties", method = RequestMethod.GET)
	public Result getConvolutionPropertiesByVersionAndEnName(@RequestParam(value = "edmdVer") String edmdVer,
															 @RequestParam(value = "edmcNameEn") String edmcNameEn) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		List<EdmProperty> properties = edmPropertyService.getConvolutionPropertiesByVersionAndEnName(edmdVer, edmcNameEn);
		result.setData(properties);
		return result;
	}

	/**
	 * 将指定id的所有属性的is_visible字段更改为指定数值
	 * @param modelerVO
	 * @return
	 */
	@RequestMapping(value = "/changeVisible", method = RequestMethod.PUT)
	public Result changePropertiesVisible(@RequestBody ModelerVO modelerVO) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmPropertyService.changePropertiesVisible(modelerVO);
		return result;
	}

	/**
	 * 根据属性id获取属性的关联关系（联动算法使用）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/links/{id}/connects", method = RequestMethod.GET)
	public Result getConnectsByPid(@PathVariable("id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		List<EdmLinkVO> edmLinks = edmLinkService.getConnectsByPid(id);
		result.setData(edmLinks);
		return result;
	}
	
	/**
	 * 根据属性限值id或者属性公式id置空属性限值(edmp_value_limit)字段或者属性公式(edmp_formula)字段
	 * @param map
	 * @return
	 */
	@RequestMapping(value = "/limitAndFormula", method = RequestMethod.PUT)
	public Result clearLimitAndFormula(@RequestBody Map<String, String> map) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmPropertyService.clearLimitAndFormula(map);
		return result;
	}

	/**
	 * 设置属性的默认值（表单使用）
	 * @param classid
	 * @param objectId
	 * @return
	 */
	@RequestMapping(value = "/default/values", method = RequestMethod.PUT)
	public Result setDefaultValue(@RequestParam String classid,@RequestParam String objectId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmPropertyService.setDefaultValue(classid,objectId);
		return result;
	}

	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	public Result selectGroups(@RequestParam String edmcId, @RequestParam String edmpId, @RequestParam String isSource) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmPropertyService.selectGroups(edmcId, edmpId, isSource));
		return result;
	}

	@RequestMapping(value = "/propTree/{edmcId}", method = RequestMethod.GET)
	public Result getPropTree(@PathVariable(value = "edmcId")String edmcId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmPropertyService.getProptreeByCid(edmcId));
		return result;
	}

	/**
	 * 根据属性的数据类型获取属性的类id
	 * @return
	 */
	@RequestMapping(value = "/edmcIds/{dataType}", method = RequestMethod.GET)
	public Result getEdmcIdsByDataType(@PathVariable(value = "dataType") String dataType) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmPropertyService.getEdmcIdsByDataType(dataType));
		return result;
	}

	/**
	 * 根据类名和属性编码获取枚举类型编码
	 * @param className
	 * @param propCode
	 * @return
	 */
	@RequestMapping(value = "/enumInfoCode", method = RequestMethod.GET)
	public Result getEnumInfoCode(@RequestParam(value = "className") String className,
								  @RequestParam(value = "propCode") String propCode)  {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmPropertyService.getEnumInfoCode(className, propCode));
		return result;
	}

	/**
	 * 根据类名获取枚举属性的数据类型，多个数据类型间用逗号隔开
	 * @param className
	 * @return
	 */
	@RequestMapping(value = "/edmpDataTypes/{className}", method = RequestMethod.GET)
	Result getInfoCodes(@PathVariable(value = "className") String className) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		result.setData(edmPropertyService.getInfoCodes(className));
		return result;
	}
}
