package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmModelerService;
import com.huntkey.rx.modeler.common.model.EdmModeler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @ClassName: EdmMethodController
 * @Description: 方法api接口Controller
 * @author: zhangyu
 * @date: 2017年4月12日上午11:35:15
 *
 */
@RestController
@RequestMapping("/v1/modelers")
public class EdmModelerController {

	private static Logger log = LoggerFactory.getLogger(EdmModelerController.class);

	@Autowired
	private EdmModelerService edmModelerService; // 注入Service

	/**
	 * 根据模型id获取类树
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}/classes", method = RequestMethod.GET)
	public Result query(@PathVariable("id") String id) {
		Result result = edmModelerService.selectEdmClassesByMid(id);
		return result;
	}

	/**
	 * 根据模型版本和模型更新说明查询模型
	 * @param edmdVer
	 * @param edmdUpdateDesc
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result queryList(@RequestParam(required = false) String edmdVer,
							@RequestParam(required = false) String edmdUpdateDesc,
							@RequestParam(required = false, defaultValue = "1") int pageNum,
							@RequestParam(required = false, defaultValue = "10") int pageSize){
		Result result =  edmModelerService.queryList(edmdVer, edmdUpdateDesc, pageNum, pageSize);
		return result;
	}

	/**
	 * 获取所有版本名称
	 * @return
	 */
	@RequestMapping(value = "/versions",method = RequestMethod.GET)
	public Result getAllVers(){
		Result result  = edmModelerService.getAllVers();
		return result;
	}

	/**
	 * 根据模型id查询模型
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Result queryObject(@PathVariable("id") String id){
		Result result =  edmModelerService.queryObject(id);
		return result;
	}

	/**
	 * 查询当前可用的最大版本号
	 * @return
	 */
	@RequestMapping(value = "/version", method = RequestMethod.GET)
	public Result queryModelerVer(){
		Result result =  edmModelerService.queryModelerVer();
		return result;
	}

	/**
	 * 在进行新增模型之前的判断，如有处于编辑状态的模型时，addStatus返回0，否则返回1
	 * @return
	 */
	@RequestMapping(value = "/status", method = RequestMethod.GET)
	public Result status(){
		Result result  = edmModelerService.status();
		return result;
	}

	/**
	 * 模型版本校验
	 * @param edmdVer
	 * @return
	 */
	@RequestMapping(value = "/edmdVer", method = RequestMethod.GET)
	public Result checkData(@RequestParam String edmdVer){
		Result result = edmModelerService.checkData(edmdVer);
		return result;
	}

	/**
	 * 新增模型
	 * @param edmModeler
	 * @return
	 */
	@RequestMapping(method = RequestMethod.POST)
	public Result add(@Validated @RequestBody EdmModeler edmModeler){
		Result result = edmModelerService.add(edmModeler);
		return result;
	}

	/**
	 * 更新模型
	 * @param edmModeler
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Result update(@RequestBody EdmModeler edmModeler){
		Result result = edmModelerService.update(edmModeler);
		return result;
	}

	/**
	 * 根据id删除模型
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public Result delete(@PathVariable("id") String id){
		Result result =  edmModelerService.delete(id);
		return result;
	}

	/**
	 * 导出模型
	 * @param id
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/export",method = RequestMethod.GET)
	public Result exportExcel(@RequestParam("id") String id,
							  @RequestParam HttpServletResponse response){
		Result result = edmModelerService.exportExcel(id,response);
		return result;
	}

	/**
	 * 根据版本名查询版本id
	 * @return
	 */
	@RequestMapping(value = "/queryClasses",method = RequestMethod.GET)
	public Result queryIdByVer(){
		Result result = edmModelerService.queryIdByVer();
		return result;
	}

}
