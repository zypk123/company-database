package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmPropertyService;
import com.huntkey.rx.modeler.common.model.EdmProperty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 *
 * @ClassName: EdmPropertyController
 * @Description: 方法api接口Controller
 * @author: zhangyu
 * @date: 2017年4月12日上午11:35:15
 *
 */
@RestController
@RequestMapping("/v1/properties")
public class EdmPropertyController {

	private static Logger log = LoggerFactory.getLogger(EdmPropertyController.class);

	@Autowired
	private EdmPropertyService edmPropertyService; // 注入Service

	/**
	 * 根据id获取属性信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result getProperty(@PathVariable String id) {
		Result result = edmPropertyService.getProperty(id);
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
		Result result = edmPropertyService.getPropertyTree(id,flag);
		return result;
	}

	/**
	 * 验证属性编码edmpCode
	 * @param edmpCode
	 * @param edmpEdmcId
	 * @return
	 */
	@RequestMapping(value = "/edmpCode", method = RequestMethod.GET)
	public Result checkEdmpCode(@RequestParam String edmpCode, @RequestParam String edmpEdmcId,@RequestParam(required = false) String edmpParentId) {
		Result result =  edmPropertyService.checkEdmpCode(edmpCode, edmpEdmcId,edmpParentId);
		return result;
	}

	/**
	 * 验证属性名
	 * @param edmpName
	 * @param edmpEdmcId
	 * @return
	 */
	@RequestMapping(value = "/edmpName", method = RequestMethod.GET)
	public Result checkEdmpName(@RequestParam String edmpName,
								@RequestParam String edmpEdmcId,
								@RequestParam(required = false) String edmpParentId) {
		Result result =  edmPropertyService.checkEdmpName(edmpName, edmpEdmcId, edmpParentId);
		return result;
	}

	/**
	 * 根据类id生成类下属性唯一编码（废弃）
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/getEdmpCode", method = RequestMethod.GET)
	public Result getEdmpCode(@RequestParam String classId) {
		Result result = edmPropertyService.getEdmpCode(classId);
		return result;
	}

	/**
	 * 新增属性
	 * @param edmProperty
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@Validated @RequestBody EdmProperty edmProperty) {
		Result result = edmPropertyService.add(edmProperty);
		return result;
	}

	/**
	 * 新增常量
	 * @param edmProperty
	 * @return
	 */
	@RequestMapping(value = "/consts", method = RequestMethod.POST)
	public Result addConsts(@Validated @RequestBody EdmProperty edmProperty) {
		Result result = edmPropertyService.addConsts(edmProperty);
		return result;
	}

	/**
	 * 修改属性
	 * @param edmProperty
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Result update(@RequestBody EdmProperty edmProperty) {
		Result result = edmPropertyService.update(edmProperty);
		return result;
	}

	/**
	 * 根据id删除属性(逻辑删除)(废弃)
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable String id) {
		Result result = edmPropertyService.delete(id);
		return result;
	}

	/**
	 * 批量逻辑删除属性
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
	public Result deleteIds(@PathVariable String[] ids) {
		Result result = edmPropertyService.deleteIds(ids);
		return result;
	}

	/**
	 * 属性的移动
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/move", method = RequestMethod.PUT)
	public Result moveUpDown(@RequestBody String[] ids) {
		Result result = edmPropertyService.move(ids);
		return result;
	}

	/**
	 * 修改属性响应方法
	 * @param id
	 * @param mid
	 * @return
	 */
	@RequestMapping(value = "/methods", method = RequestMethod.PUT)
	public Result updateMethod(@RequestParam(value = "id") String id, @RequestParam(value = "mid") String mid) {
		Result result = edmPropertyService.updateMethod(id, mid);
		return result;
	}

	/**
	 * 修改属性出发条件（废弃）
	 * @param id
	 * @param triggerCondId
	 * @return
	 */
	@RequestMapping(value = "/{id}/triggerCond/{triggerCondId}", method = RequestMethod.PUT)
	public Result updateTriggerCond(@PathVariable("id") String id,
									@PathVariable("triggerCondId") String triggerCondId) {
		Result result = edmPropertyService.updateTriggerCond(id, triggerCondId);
		return result;
	}

	/**
	 * 根据属性id获取属性集
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/properties", method = RequestMethod.GET)
	public Result getProperties(@PathVariable(value = "id") String id) {
		Result result =  edmPropertyService.getProperties(id);// 获取属性集
		return result;
	}

	/**
	 * 根据属性id获取联动属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/connects", method = RequestMethod.GET)
	public Result getConnectsByEdcnEdmpId(@PathVariable(value = "id") String id) {
		Result result = edmPropertyService.getConnectsByEdcnEdmpId(id);// 获取属性集
		return result;
	}

	/**
	 * 根据属性id获取卷积属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/convolute", method = RequestMethod.GET)
	public Result getConvolutesByEdcoEdmpId(@PathVariable(value = "id") String id) {
		Result result = edmPropertyService.getConvolutesByEdcoEdmpId(id);// 获取属性集
		return result;
	}

	/**
	 * 根据属性id获取关联属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/links", method = RequestMethod.GET)
	public Result getLinksByEdmlEdmpId(@PathVariable(value = "id") String id) {
		Result result = edmPropertyService.getLinksByEdmlEdmpId(id);// 获取属性集
		return result;
	}

	/**
	 * 根据属性id获取单位属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/units", method = RequestMethod.GET)
	public Result getUnitsByEdunEdmpId(@PathVariable(value = "id") String id) {
		Result result = edmPropertyService.getUnitsByEdunEdmpId(id);// 获取属性集
		return result;
	}

	/**
	 * 根据属性id 获取触发条件
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/conds", method = RequestMethod.GET)
	public Result getCondsByEdcoEdmpId(@PathVariable(value = "id") String id) {
		Result result = edmPropertyService.getCondsByPropertyId(id);// 获取属性集
		return result;
	}

	/**
	 * 查找属性的同类属性（废弃）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/units/sameclassunitlist", method = RequestMethod.GET)
	public Result getSameClassUnitsListByEdunEdmpId(@PathVariable(value = "id") String id) {
		Result result = edmPropertyService.getSameClassUnitsListByEdunEdmpId(id);// 获取属性集
		return result;
	}

	/**
	 * 根据属性id查找联动属性（废弃）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/connect/properties", method = RequestMethod.GET)
	public Result getEdmConnectById(@PathVariable(value = "id") String id) {
		Result result = edmPropertyService.getEdmConnectById(id);
		return result;
	}

	/**
	 * 根据属性id查询edmp_value_limit
	 * @param id
	 * @return edmp_value_limit
	 */
	@RequestMapping(value = "/getValueLimitById/{id}", method = RequestMethod.GET)
	public Result getValueLimitById(@PathVariable(value = "id") String id) {
		Result result = edmPropertyService.getValueLimitById(id);
		return result;
	}

	/**
	 * 根据属性id更新属性某个字段的值
	 * @param edmProperty
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.PUT)
	public Result updateProperty(@RequestBody EdmProperty edmProperty) {
		Result result = edmPropertyService.updateProperty(edmProperty);
		return result;
	}

	/**
	 * 根据类id查询类的是特征值的属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getTwoProperties/{id}", method = RequestMethod.GET)
	public Result getTwoProperties(@PathVariable(value = "id") String id) {
		Result result = edmPropertyService.getTwoProperties(id);
		return result;
	}

	/**
	 * 根据模型id查询枚举类的是特征值的属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/getEnumTwoProperties/{id}", method = RequestMethod.GET)
	public Result getEnumTwoProperties(@PathVariable(value = "id") String id) {
		Result result = edmPropertyService.getEnumTwoProperties(id);
		return result;
	}

	/**
	 * 根据属性id查询属性和所属类
	 * @param propertyId
	 * @return
	 */
	@RequestMapping(value = "/getPropertyAndClass", method = RequestMethod.GET)
	public Result getPropertyAndClass(@RequestParam(value="propertyId") String propertyId) {
		Result result = edmPropertyService.getPropertyAndClass(propertyId);
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
		Result result = edmPropertyService.getPropertyTypeAndValue(classId,edmpCode);
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
		Result result = edmPropertyService.getConvolutionPropertiesByVersionAndEnName(edmdVer, edmcNameEn);
		return result;
	}

//	/**
//	 * 将指定id的所有属性的is_visible字段更改为指定数值
//	 * @param ids
//	 * @param b （0或1）
//	 */
//	@RequestMapping(value = "/changeVisible", method = RequestMethod.PUT)
//	public Result changePropertiesVisible(@RequestParam (value = "ids") String ids,
//										  @RequestParam(value = "b") byte b) {
//		Result result = edmPropertyService.changePropertiesVisible(ids, b);
//		return result;
//	}

	@RequestMapping(value = "/limitAndFormula", method = RequestMethod.PUT)
	public Result clearLimitAndFormula(@RequestBody Map<String, String> map) {
		Result result = edmPropertyService.clearLimitAndFormula(map);
		return result;
	}

	@RequestMapping(value = "/groups", method = RequestMethod.GET)
	Result selectGroups(@RequestParam(value = "edmcId") String edmcId,
						@RequestParam(value = "edmpId") String edmpId,
						@RequestParam(value = "isSource") String isSource) {
		Result result = edmPropertyService.selectGroups(edmcId, edmpId, isSource);
		return result;
	}

	@RequestMapping(value = "/propTree/{edmcId}", method = RequestMethod.GET)
	public Result getPropTree(@PathVariable(value = "edmcId")String edmcId) {
		Result result = edmPropertyService.getPropTree(edmcId);
		return result;
	}

	/**
	 * 根据类名和字段名获取枚举编码
	 * @param className
	 * @param propCode
	 * @return
	 */
	@RequestMapping(value = "/enumInfoCode", method = RequestMethod.GET)
	public Result getEnumInfoCode(@RequestParam(value = "className") String className,
								  @RequestParam(value = "propCode") String propCode)  {
		Result result = edmPropertyService.getEnumInfoCode(className, propCode);
		return result;
	}
}
