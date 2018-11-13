package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmClassHystrix;
import com.huntkey.rx.modeler.common.model.EdmClass;
import com.huntkey.rx.modeler.common.model.vo.EdmClassMethodVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by licj on 2017/4/16.
 */
@FeignClient(value = "modeler-provider", fallback = EdmClassHystrix.class)
public interface EdmClassService {

	@RequestMapping(value = "/classes/{id}", method = RequestMethod.GET)
	public Result getClassById(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/simple/{id}", method = RequestMethod.GET)
	public Result getSimpleClassById(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/edmUnits/properties", method = RequestMethod.GET)
	public Result getDataProperties(@RequestParam(value = "edmcId") String edmcId,@RequestParam(required = false,value = "edmpId") String edmpId);

	@RequestMapping(value = "/classes/relate/{id}", method = RequestMethod.GET)
	public Result getRelateClass(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes", method = RequestMethod.POST)
	Result add(@RequestBody EdmClass edmClass);

	/**
	 * 修改类包含的方法
	 *
	 * @param edmClassMethodVO
	 * @return
	 */
	@RequestMapping(value = "/classes/methods", method = RequestMethod.POST)
	Result updateClassMethods(@RequestBody EdmClassMethodVO edmClassMethodVO);

	@RequestMapping(value = "/classes", method = RequestMethod.PUT)
	Result update(@RequestBody EdmClass edmClass);

	@RequestMapping(value = "/classes/{id}", method = RequestMethod.DELETE)
	Result delete(@PathVariable("id") String id);

	@RequestMapping(value = "/classes/{id}/properties", method = RequestMethod.GET)
	Result getProperties(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/{id}/fatherProperties", method = RequestMethod.GET)
	Result getFatherProperties(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/{id}/fatherConsts", method = RequestMethod.GET)
	Result getFatherConsts(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/{id}/methods", method = RequestMethod.GET)
	Result getMethodByClassId(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/{id}/father/methods", method = RequestMethod.GET)
	Result getFatherMethodByClassId(@PathVariable(value = "id") String id);

	/*
	 * @RequestMapping( value = "/classes/{uid}/{did}",method =
	 * RequestMethod.PUT) Result move(@PathVariable("uid") String
	 * uid,@PathVariable("did") String did);
	 */

	@RequestMapping(value = "/classes/{id}/attachments", method = RequestMethod.GET)
	Result getAttachments(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/{id}/properties/name", method = RequestMethod.GET)
	Result getPropertyNameAndByEdmClassId(@PathVariable(value = "id") String id,
			@RequestParam(required = false, value = "edmpName") String edmpName);

	@RequestMapping(value = "/classes/move", method = RequestMethod.PUT)
	Result move(@RequestBody String[] ids);

	@RequestMapping(value = "/classes/{id}/consts", method = RequestMethod.GET)
	Result getConsts(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/unitClass/{id}", method = RequestMethod.GET)
	Result getUnitClass(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/enumClass/{id}", method = RequestMethod.GET)
	Result getEnumClass(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/statisticClass/{id}", method = RequestMethod.GET)
	Result getStatisticClass(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/copy", method = RequestMethod.POST)
	Result copy(@RequestBody EdmClass edmClass);

	@RequestMapping(value = "/classes/edmcCode", method = RequestMethod.GET)
	Result checkEdmcCode(@RequestParam(value = "edmcCode") String edmcCode,
						 @RequestParam(value = "edmcEdmdId") String edmcEdmdId);

	@RequestMapping(value = "/classes/edmcName", method = RequestMethod.GET)
	Result checkEdmcName(@RequestParam(value = "edmcName") String edmcName,
					 	 @RequestParam(value = "edmcEdmdId") String edmcEdmdId);

	@RequestMapping(value = "/classes/edmcNameEn", method = RequestMethod.GET)
	Result checkEdmcNameEn(@RequestParam(value = "edmcNameEn") String edmcNameEn, @RequestParam(value = "edmcEdmdId") String edmcEdmdId);

	@RequestMapping(value = "/classes/edmcShortName", method = RequestMethod.GET)
	Result checkEdmcShortName(@RequestParam(value = "edmcShortName") String edmcShortName,
					 		  @RequestParam(value = "edmcEdmdId") String edmcEdmdId);


	@RequestMapping(value = "/classes/child/{id}/{sid}", method = RequestMethod.GET)
	Result isChild(@PathVariable(value = "id") String id, @PathVariable(value = "sid") String sid);

	@RequestMapping(value = "/classes/{classId}/{methodId}", method = RequestMethod.DELETE)
	Result updateIsDelByClassIdAndMethodId(@PathVariable(value = "classId") String classId,
			@PathVariable(value = "methodId") String methodId);

	@RequestMapping(value = "/classes/batch/{methodIds}", method = RequestMethod.DELETE)
	Result updateBatchIsDelByClassIdAndMethodId(@PathVariable(value = "methodIds") String methodIds);

	@RequestMapping(value = "/classes/classTree", method = RequestMethod.GET)
	Result queryClassTree(@RequestParam(value = "modelerId") String modelerId,
						  @RequestParam(value="edmcNameEns") String[] edmcNameEns);

	@RequestMapping(value = "/classes/getPublishModelerClass", method = RequestMethod.GET)
	Result getPublishModelerClass();

	@RequestMapping(value = "/classes/getClassAndRelateClass", method = RequestMethod.GET)
	Result getClassAndRelateClass(@RequestParam(value = "classId") String classId,
								  @RequestParam(value = "classIdArray") String[] classIdArray);

	@RequestMapping(value = "/classes/{id}/character/properties", method = RequestMethod.GET)
	Result getClassFormatsByCid(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/{id}/choose/properties", method = RequestMethod.GET)
	Result getChooseRelateProperty(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/{id}/set/formats", method = RequestMethod.GET)
    Result isSet(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/entity", method = RequestMethod.GET)
	Result getEntityByVersionAndEnglishName(@RequestParam(value = "edmdVer")String edmdVer,
											@RequestParam(value = "edmcNameEn") String edmcNameEn);

	/**
	 * 根据类id获取类下的属性集和属性集中属性集
	 * @param edmcId
	 * @return
	 */
	@RequestMapping(value = "/classes/{edmcId}/assemble", method = RequestMethod.GET)
	Result selectAssemblesByEdmcId(@PathVariable(value = "edmcId") String edmcId);

	/**
	 * 获取类下的非属性集属性或者属性集下的非属性集属性
	 * @param edmcId
	 * @param assembleId
	 * @return
	 */
	@RequestMapping(value = "/classes/{edmcId}/propertys/assemble", method = RequestMethod.GET)
	Result selectProperties(@PathVariable(value = "edmcId") String edmcId,
							@RequestParam(value = "assembleId", required = false) String assembleId);

	/**
	 * 根据类id获取索引
	 * @param edmcId
	 * @return
	 */
	@RequestMapping(value = "/classes/{edmcId}/indexes", method = RequestMethod.GET)
	Result selectIndexByEdmcId(@PathVariable(value = "edmcId") String edmcId);

	/**
	 * 根据类id获取父类索引
	 * @param edmcId
	 * @return
	 */
	@RequestMapping(value = "/classes/{edmcId}/parentIndexes", method = RequestMethod.GET)
	Result getParentIndexByEdmcId(@PathVariable(value = "edmcId") String edmcId);

	/**
	 * 根据版本名和类名查询方法名
	 * @param ver
	 * @param className
	 * @return
	 */
	@RequestMapping(value = "/classes/getMethod/{className}/{methodName}",method = RequestMethod.GET)
	Result getMethodsByEdmcNameVer(@PathVariable(value = "className") String className,
								   @PathVariable(value = "methodName") String methodName);

	/**
	 * 根据ids获取edmclass集合
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/classes/getEdmClasses",method = RequestMethod.POST)
	Result getEdmClasses(@RequestBody String[] ids);

	@RequestMapping(value = "/classes/monitorClasses", method = RequestMethod.POST)
    Result getMonitorClasses(@RequestBody String[] ids);

	@RequestMapping(value = "/classes/resourcesClasses", method = RequestMethod.GET)
	Result getResourcesClasses(@RequestParam(value = "version") String version);

	@RequestMapping(value = "/classes/{edmcCode}/classes", method = RequestMethod.GET)
    Result getEdmClassesByEdmcCode(@PathVariable(value = "edmcCode") String edmcCode);

	@RequestMapping(value = "/classes/{classId}/props", method = RequestMethod.GET)
	Result getPropertiesOfEdmClass(@PathVariable(value = "classId") String classId);

	@RequestMapping(value = "/classes/linkMethods", method = RequestMethod.GET)
	Result getLinkMethod(@RequestParam(value = "classId") String classId,
						 @RequestParam(value = "edmmMethodType") String edmmMethodType);

	@RequestMapping(value = "/classes/{id}/allProps", method = RequestMethod.GET)
	Result getAllProperties(@PathVariable(value = "id") String id);

	@RequestMapping(value = "/classes/relateClasses/{id}", method = RequestMethod.GET)
	Result getRelateClasses(@PathVariable(value = "id")String id);

	@RequestMapping(value = "/classes/{ids}/classAndProps", method = RequestMethod.GET)
	Result selectEdmClassesAndProps(@PathVariable(value = "ids") String ids);
}

