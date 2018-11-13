package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmClassService;
import com.huntkey.rx.modeler.client.feign.EdmIndexService;
import com.huntkey.rx.modeler.common.model.EdmClass;
import com.huntkey.rx.modeler.common.model.vo.EdmClassMethodVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @ClassName: EdmMethodController
 * @Description: 方法api接口Controller
 * @author: zhangyu
 * @date: 2017年4月12日上午11:35:15
 *
 */
@RestController
@RequestMapping("/v1/classes")
//@Api(value = "模型维护页面", description = "提供类的新增、修改、删除和查询服务")
public class EdmClassController {

	private static Logger log = LoggerFactory.getLogger(EdmClassController.class);

	@Autowired
	private EdmClassService edmClassService; // 注入Service

	@Autowired
	private EdmIndexService edmIndexService; // 注入Service


	/**
	 * 获取类的基本信息
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result getClassById(@PathVariable String id) {
		Result result = edmClassService.getClassById(id);
		return result;
	}

	/**
	 * 获取类的简单基本信息，不包括行业
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/simple/{id}", method = RequestMethod.GET)
	public Result getSimpleClassById(@PathVariable String id) {
		Result result =  edmClassService.getSimpleClassById(id);
		return result;
	}

	/**
	 * 根据类id获取类型为整形与字符型的属性(供单位列表中属性选择使用)
	 * @param edmcId
	 * @param edmpId
	 * @return
	 */
	@RequestMapping(value = "/edmUnits/properties", method = RequestMethod.GET)
	public Result getDataProperties(@RequestParam(value = "edmcId") String edmcId,String edmpId){
		Result result =  edmClassService.getDataProperties(edmcId,edmpId);
		return result;
	}

	/**
	 * 根据类id获取所有属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/properties", method = RequestMethod.GET)
	public Result getProperties(@PathVariable String id) {
		Result result = edmClassService.getProperties(id);
		return result;
	}

	/**
	 * 根据类id获取所有的父类属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/fatherProperties", method = RequestMethod.GET)
	public Result getFatherProperties(@PathVariable String id) {
		Result result =  edmClassService.getFatherProperties(id);
		return result;
	}

	/**
	 * 根据类id获取常量属性列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/consts", method = RequestMethod.GET)
	public Result getConsts(@PathVariable(value = "id") String id) {
		Result result =  edmClassService.getConsts(id);
		return result;
	}

	/**
	 * 根据类id获取所有的父类常量
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/fatherConsts", method = RequestMethod.GET)
	public Result getFatherConsts(@PathVariable String id) {
		Result result = edmClassService.getFatherConsts(id);
		return result;
	}

	/**
	 * 根据模型id获取单位类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/unitClass/{id}", method = RequestMethod.GET)
	public Result getUnitClass(@PathVariable(value = "id") String id) {
		Result result = edmClassService.getUnitClass(id);
		return result;
	}

	/**
	 * 根据模型id获取枚举类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/enumClass/{id}", method = RequestMethod.GET)
	public Result getEnumClass(@PathVariable(value = "id") String id) {
		Result result = edmClassService.getEnumClass(id);
		return result;
	}

	/**
	 * 根据模型id获取统计类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/statisticClass/{id}", method = RequestMethod.GET)
	public Result getStatisticClass(@PathVariable(value = "id") String id) {
		Result result = edmClassService.getStatisticClass(id);
		return result;
	}

	/**
	 * 根据类id查找类自身及其相关类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/relate/{id}", method = RequestMethod.GET)
	public Result getRelateClass(@PathVariable String id) {
		Result result = edmClassService.getRelateClass(id);
		return result;
	}

	/**
	 * 根据类id查找类和父类及其相关类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/relateClasses/{id}", method = RequestMethod.GET)
	public Result getRelateClasses(@PathVariable String id) {
		Result result = edmClassService.getRelateClasses(id);
		return result;
	}

	/**
	 * 根据类id获取所有附件
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/attachments", method = RequestMethod.GET)
	public Result getAttachments(@PathVariable String id) {
		Result result  = edmClassService.getAttachments(id);
		return result;
	}

	/**
	 * 类编码校验
	 * @param edmcCode
	 * @param edmcEdmdId
	 * @return
	 */
	@RequestMapping(value = "/edmcCode", method = RequestMethod.GET)
	public Result checkEdmcCode(@RequestParam String edmcCode, @RequestParam String edmcEdmdId) {
		Result result = edmClassService.checkEdmcCode(edmcCode, edmcEdmdId);
		return result;
	}

	/**
	 * 类名称校验
	 * @param edmcName
	 * @param edmcEdmdId
	 * @return
	 */
	@RequestMapping(value = "/edmcName", method = RequestMethod.GET)
	public Result checkEdmcName(@RequestParam String edmcName,
								@RequestParam String edmcEdmdId) {
		Result result = edmClassService.checkEdmcName(edmcName, edmcEdmdId);
		return result;
	}

	/**
	 * 类英文名称校验
	 * @param edmcNameEn
	 * @param edmcEdmdId
	 * @return
	 */
	@RequestMapping(value = "/edmcNameEn", method = RequestMethod.GET)
	public Result checkEdmcNameEn(@RequestParam String edmcNameEn,
								@RequestParam String edmcEdmdId) {
		Result result = edmClassService.checkEdmcNameEn(edmcNameEn, edmcEdmdId);
		return result;
	}

	/**
	 * 类简称校验
	 * @param edmcShortName
	 * @param edmcEdmdId
	 * @return
	 */
	@RequestMapping(value = "/edmcShortName", method = RequestMethod.GET)
	public Result checkEdmcShortName(@RequestParam String edmcShortName,
									 @RequestParam String edmcEdmdId) {
		Result result =  edmClassService.checkEdmcShortName(edmcShortName, edmcEdmdId);
		return result;
	}

	/**
	 * 索引名称校验
	 * @param indexName
	 * @param edmcId
	 * @return
	 */
	@RequestMapping(value = "/{edmcId}/indexName/validator", method = RequestMethod.GET)
	public Result checkIndexName(@RequestParam(value = "indexName") String indexName,
								 @PathVariable(value = "edmcId") String edmcId) {
		Result result =  edmIndexService.checkIndexName(indexName, edmcId);
		return result;
	}

	/**
	 * 新增类
	 * @param edmClass
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@Validated @RequestBody EdmClass edmClass) {
		Result result = edmClassService.add(edmClass);
		return result;
	}

	/**
	 * 修改类
	 * @param edmClass
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Result update(@RequestBody EdmClass edmClass) {
		Result result = edmClassService.update(edmClass);
		return result;
	}

	/**
	 * 修改类包含的方法
	 * @param edmClassMethodVO
	 * @return
	 */
	@RequestMapping(value = "/methods", method = RequestMethod.POST)
	public Result updateClassMethods(@RequestBody EdmClassMethodVO edmClassMethodVO) {
		Result result = edmClassService.updateClassMethods(edmClassMethodVO);
		return result;
	}

	/**
	 * 根据id删除类（逻辑删除）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable String id) {
		Result result = edmClassService.delete(id);
		return result;
	}

	/**
	 * 根据类的id获得类的方法列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/methods", method = RequestMethod.GET)
	public Result getMethodByClassId(@PathVariable String id) {
		Result result = edmClassService.getMethodByClassId(id);
		return result;
	}

	/**
	 * 根据类的id获得类所有父类的方法列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/father/methods", method = RequestMethod.GET)
	public Result getFatherMethodByClassId(@PathVariable String id) {
		Result result = edmClassService.getFatherMethodByClassId(id);
		return result;
	}

	/**
	 * 类的移动
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/move", method = RequestMethod.PUT)
	public Result move(@RequestBody String[] ids) {
		Result result = edmClassService.move(ids);
		return result;
	}

	/**
	 * 类的复制
	 * @param edmClass
	 * @return
	 */
	@RequestMapping(value = "/copy", method = RequestMethod.POST)
	public Result copy(@RequestBody EdmClass edmClass) {
		Result result = edmClassService.copy(edmClass);
		return result;
	}

	/**
	 * 根据类id 查找属性名
	 * @param id
	 * @param edmpName
	 * @return
	 */
	@RequestMapping(value = "/{id}/properties/name", method = RequestMethod.GET)
	public Result getPropertyNameAndByEdmClassId(@PathVariable(value = "id") String id,
			@RequestParam(required = false, value = "edmpName") String edmpName) {
		Result result = edmClassService.getPropertyNameAndByEdmClassId(id, edmpName);
		return result;
	}

	/**
	 * 根据类id判断编辑的类所选择的分类是否是该类的子类
	 * @param id
	 * @param sid
	 * @return
	 */
	@RequestMapping(value = "/child/{id}/{sid}", method = RequestMethod.GET)
	public Result isChild(@PathVariable(value = "id") String id, @PathVariable(value = "sid") String sid) {
		Result result = edmClassService.isChild(id, sid);
		return result;
	}

	/**
	 * 删除类下的指定方法(废弃)
	 * @param classId
	 * @param methodId
	 * @return
	 */
	@RequestMapping(value = "/{classId}/{methodId}", method = RequestMethod.DELETE)
	public Result updateIsDelByClassIdAndMethodId(@PathVariable(value = "classId") String classId,
			@PathVariable(value = "methodId") String methodId) {
		Result result = edmClassService.updateIsDelByClassIdAndMethodId(classId, methodId);
		return result;
	}

	/**
	 * 删除类下的多个方法
	 * @param methodIds
	 * @return
	 */
	@RequestMapping(value = "/batch/{methodIds}", method = RequestMethod.DELETE)
	public Result updateBatchIsDelByClassIdAndMethodIds(@PathVariable(value = "methodIds") String methodIds) {
		Result result = edmClassService.updateBatchIsDelByClassIdAndMethodId(methodIds);
		return result;
	}

	/**
	 * 类树查询接口
	 * @param modelerId  模型id
	 * @param edmcNameEns 类英文名称
	 * @return
	 */
	@RequestMapping(value = "/classTree", method = RequestMethod.GET)
	public Result queryClassTree(@RequestParam(required = false) String modelerId,
								 @RequestParam(value="edmcNameEns") String[] edmcNameEns) {
		Result result = edmClassService.queryClassTree(modelerId, edmcNameEns);
		return  result;
	}

	/**
	 * 查询已发布模型下面的所有类
	 * @return
	 */
	@RequestMapping(value = "/getPublishModelerClass", method = RequestMethod.GET)
	public Result getPublishModelerClass() {
		Result result = edmClassService.getPublishModelerClass();
		return  result;
	}

	/**
	 * 公式设计器类初始化
	 * @param
	 * @return
	 */
	@RequestMapping(value = "/getClassAndRelateClass", method = RequestMethod.GET)
	public Result getClassAndRelateClass(@RequestParam(value="classId") String classId,
										 @RequestParam(value="classIdArray") String[] classIdArray) {
		Result result = edmClassService.getClassAndRelateClass(classId,classIdArray);
		return  result;
	}

	/**
	 * 根据类id查询特征值的属性列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/character/properties", method = RequestMethod.GET)
	public Result getClassFormatsByCid(@PathVariable String id) {
		Result result = edmClassService.getClassFormatsByCid(id);
		return result;
	}

	/**
	 * 根据类id查询可选的关联属性
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/choose/properties", method = RequestMethod.GET)
	public Result getChooseRelateProperty(@PathVariable String id) {
		Result result = edmClassService.getChooseRelateProperty(id);
		return result;
	}

	/**
	 * 根据类id判断该类是否设置了特征值
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "{id}/set/formats", method = RequestMethod.GET)
	public Result isSet(@PathVariable(value = "id") String id) {
		Result result = edmClassService.isSet(id);
		return result;
	}

	/**
	 * 根据模型的版本号和类的英文名称获取所属类子孙类中的实体类
	 * @param edmdVer
	 * @param edmcNameEn
	 * @return
	 */
	@RequestMapping(value = "/entity", method = RequestMethod.GET)
	public Result getEntityByVersionAndEnglishName(@RequestParam(value = "edmdVer")String edmdVer,
												   @RequestParam(value = "edmcNameEn") String edmcNameEn) {
		Result result = edmClassService.getEntityByVersionAndEnglishName(edmdVer, edmcNameEn);
		return result;
	}

	/**
	 * 根据类id获取类下的属性集和属性集中属性集
	 * @param edmcId
	 * @return
	 */
	@RequestMapping(value = "/{edmcId}/assemble", method = RequestMethod.GET)
	Result selectAssemblesByEdmcId(@PathVariable(value = "edmcId") String edmcId) {
		Result result = edmClassService.selectAssemblesByEdmcId(edmcId);
		return result;
	}

	/**
	 * 获取类下的非属性集属性或者属性集下的非属性集属性
	 * @param edmcId
	 * @param assembleId
	 * @return
	 */
	@RequestMapping(value = "/{edmcId}/propertys/assemble", method = RequestMethod.GET)
	Result selectProperties(@PathVariable(value = "edmcId") String edmcId,
							@RequestParam(value = "assembleId", required = false) String assembleId) {
		Result result = edmClassService.selectProperties(edmcId, assembleId);
		return result;
	}

	/**
	 * 根据类id获取索引
	 * @param edmcId
	 * @return
	 */
	@RequestMapping(value = "/{edmcId}/indexes", method = RequestMethod.GET)
	public Result selectIndexByEdmcId(@PathVariable(value = "edmcId") String edmcId) {
		Result result = edmClassService.selectIndexByEdmcId(edmcId);
		return result;
	}

	/**
	 * 根据类id获取父类索引
	 * @param edmcId
	 * @return
	 */
	@RequestMapping(value = "/{edmcId}/parentIndexes", method = RequestMethod.GET)
	public Result getParentIndexByEdmcId(@PathVariable(value = "edmcId") String edmcId) {
		Result result = edmClassService.getParentIndexByEdmcId(edmcId);
		return result;
	}

	/**
	 * 根据版本号，类名，方法名查询方法体
	 * @param ver
	 * @param className
	 * @param methodName
	 * @return
	 */
	@RequestMapping(value = "/getMethod/{className}/{methodName}",method = RequestMethod.GET)
	public Result getMethodsByClassNameVer(@PathVariable(value = "className") String className,
										   @PathVariable(value = "methodName") String methodName){
		Result result = edmClassService.getMethodsByEdmcNameVer(className,methodName);
		return result;
	}

	/**
	 * 根据ids获取edmclass集合
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/getEdmClasses",method = RequestMethod.POST)
	public Result getEdmClasses(@RequestBody String[] ids){
		Result result = edmClassService.getEdmClasses(ids);
		return result;
	}

	/**
	 * 根据资源类id获取监管类的集合
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/monitorClasses", method = RequestMethod.POST)
	public Result getMonitorClasses(@RequestBody String[] ids){
		return edmClassService.getMonitorClasses(ids);
	}

	/**
	 * 获取被监管的资源类集合
	 * @return
	 */
	@RequestMapping(value = "/resourcesClasses", method = RequestMethod.GET)
	public Result getResourcesClasses(@RequestParam(value = "version") String version) {
		return edmClassService.getResourcesClasses(version);
	}

	/**
	 * 根据类编码获取类及其子类
	 * @param edmcCode
	 * @return
	 */
	@RequestMapping(value = "/{edmcCode}/classes", method = RequestMethod.GET)
	public Result getClassesByEdmcCode(@PathVariable(value = "edmcCode") String edmcCode) {
		Result result = edmClassService.getEdmClassesByEdmcCode(edmcCode);
		return result;
	}

	/**
	 * 获取子类及其上一级父类属性中数据类型为岗位类或部门类的属性
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/{classId}/props", method = RequestMethod.GET)
	public Result getPropertiesOfEdmClass(@PathVariable(value = "classId") String classId) {
		Result result = edmClassService.getPropertiesOfEdmClass(classId);
		return result;
	}

	/**
	 * 根据类id获取卷积方法和关联方法
	 * @param classId 类id
	 * @param edmmMethodType 1: 关联方法 2：卷积方法
	 * @return
	 */
	@RequestMapping(value = "/linkMethods", method = RequestMethod.GET)
	public Result getLinkMethod(@RequestParam(value = "classId") String classId,
								@RequestParam(value = "edmmMethodType") String edmmMethodType) {
		return edmClassService.getLinkMethod(classId, edmmMethodType);
	}

	/**
	 * 获取类的所有属性,包括属性集中属性，但不包括属性集本身
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/allProps", method = RequestMethod.GET)
	public Result getAllProperties(@PathVariable(value = "id") String id) {
		return edmClassService.getAllProperties(id);
	}

	@RequestMapping(value = "/{ids}/classAndProps", method = RequestMethod.GET)
	public Result getAllClassesAdProps(@PathVariable(value = "ids") String ids) throws Exception {
		return edmClassService.selectEdmClassesAndProps(ids);
	}
}
