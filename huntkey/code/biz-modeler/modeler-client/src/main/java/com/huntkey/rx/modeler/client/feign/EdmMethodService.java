package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.hystrix.EdmMethodHystrix;
import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodAndArgVO;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@FeignClient(value = "modeler-provider", fallback = EdmMethodHystrix.class)
public interface EdmMethodService {

	/**
	 * 新增方法
	 * @param edmMethodAndArgVO
	 * @return
	 */
	@RequestMapping(value = "/methods", method = RequestMethod.POST)
	Result add(@RequestBody EdmMethodAndArgVO edmMethodAndArgVO);
	
	/**
	 * 在进行新增方法之前的检查数据是否符合要求
	 * @param edmmName
	 * @return
	 */
    @RequestMapping(value="/methods/edmmName", method = RequestMethod.GET)
    Result checkData(@RequestParam(value = "edmcId") String edmcId,@RequestParam(value = "edmmName") String edmmName);
                  
	/**
	 * 查询方法
	 * @param edmmName
	 * @param edmmClasses 所属类 多个所属类以逗号分隔
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/methods", method = RequestMethod.GET)
	public Result getMethods(@RequestParam(required = false, value = "edmmType") String edmmType,
			@RequestParam(required = false, value = "edmmProgramType") String edmmProgramType,
			@RequestParam(required = false, value = "edmmName") String edmmName,
			@RequestParam(required = false, value = "edmmClasses") String edmmClasses,
			@RequestParam(required = false, value = "edmmStatus") String edmmStatus,
			@RequestParam(defaultValue = "1", value = "pageNum") int pageNum,
			@RequestParam(defaultValue = "10", value = "pageSize") int pageSize);

	/**
	 * 通过id查找方法
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/methods/{id}", method = RequestMethod.GET)
	public Result getMethodById(@PathVariable("id") String id);

	/**
	 * 更新方法
	 * @param edmMethodAndArgVO
	 * @return
	 */
	@RequestMapping(value = "/methods", method = RequestMethod.PUT)
	Result update(@RequestBody EdmMethodAndArgVO edmMethodAndArgVO);

	/***
	 * 更新方法基本信息
	 * @param edmMethod
	 * @return
	 */
	@RequestMapping(value = "/methods/baseInfo", method = RequestMethod.PUT)
	Result updateBaseInfo(@RequestBody EdmMethod edmMethod);

	/**
	 * 删除方法
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/methods/{id}", method = RequestMethod.DELETE)
	Result delete(@PathVariable("id") String id);

	/**
	 * 批量删除方法
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/methods/batch/{ids}", method = RequestMethod.DELETE)
	Result deleteMethods(String[] ids);

	/**
	 * 方法的上移、下移
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/methods/move", method = RequestMethod.PUT)
	Result move(@RequestBody String[] ids);

	/**
	 * 通过方法ID查找类
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/methods/{id}/classes", method = RequestMethod.GET)
	Result getClassesByMethodId(@PathVariable("id") String id);

	/**
	 * 查询方法分类结构。
	 *
	 * @return edmMethodTypeVO
	 */
	@RequestMapping(value = "/methods/methodTypeTree", method = RequestMethod.GET)
	Result queryClassMethodTypeTree();

	@RequestMapping(value = "/methods/classes", method = RequestMethod.GET)
    Result queryMethodsAndClasses(@RequestParam(required = false,value = "type") String type,
								  @RequestParam(required = false,value = "name") String name,
								  @RequestParam(required = false, value = "version") String version,
								  @RequestParam(required = false, value = "className") String className);

	/**
	 * 根据类id查方法id
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/methods/classes/{id}", method = RequestMethod.GET)
	Result queryMethodIdsByClassId(@PathVariable("id") String id);

	@RequestMapping(value = "/methods/uploadMethodBody", method = RequestMethod.POST)
	Result uploadMethodBody(@RequestParam(value = "file") MultipartFile file);

	@RequestMapping(value = "/methods/getMethodBody", method = RequestMethod.GET)
	Result getMethodBody(@RequestParam(required = false,value = "edmmName") String edmmName);

	/**
	 * 通过方法id禁/启用方法
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/methods/updateEdmmStatus",method = RequestMethod.PUT)
	Result updateEdmmStatus(@RequestParam(value = "id") String id,
							@RequestParam(value = "edmmStatus") String edmmStatus);

	/**
	 * 通过方法id 类id 获取方法名和类名
	 * @param id
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/methods/{id}/{classId}",method = RequestMethod.GET)
	Result getMethodByIdClassId(@PathVariable(value = "id") String id,
									   @PathVariable(value = "classId") String classId);


	@RequestMapping(value = "/methods/{id}/{condId}",method = RequestMethod.PUT)
	Result updateTriggerCond(@PathVariable(value = "id") String id,
							 @PathVariable(value = "condId") String condId);

	@RequestMapping(value = "/methods/{id}/cond",method = RequestMethod.DELETE)
	Result deleteTriggerCond(@PathVariable(value = "id") String id);

}
