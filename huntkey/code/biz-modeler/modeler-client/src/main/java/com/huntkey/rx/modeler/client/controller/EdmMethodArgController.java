package com.huntkey.rx.modeler.client.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmMethodArgService;
import com.huntkey.rx.modeler.common.model.EdmMethodArg;

/**
 * 
 * @ClassName: EdmMethodArgController
 * @Description: 方法参数接口Controller
 * @author: zhangyu
 * @date: 2017年4月12日上午11:35:15
 *
 */
@RestController
@RequestMapping("/v1/methodArgs")
public class EdmMethodArgController {

	private static Logger log = LoggerFactory.getLogger(EdmMethodArgController.class);

	@Autowired
	private EdmMethodArgService edmMethodArgService;

	/**
	 * 方法参数名校验
	 * @param edmaName
	 * @param edmaEdmmId
	 * @return
	 */
	@RequestMapping(value = "/edmaName", method = RequestMethod.GET)
	public Result checkData(@RequestParam String edmaName,
							@RequestParam String edmaEdmmId) {
		Result result = edmMethodArgService.checkData(edmaName, edmaEdmmId);
		return result;
	}

	/**
	 * 新增输入参数
	 * @param edmMethodArg
	 * @return
	 */
	@RequestMapping(value = "/inputArg", method = RequestMethod.POST)
	public Result addInputArg(@Validated @RequestBody EdmMethodArg edmMethodArg) {
		Result result = edmMethodArgService.insertInputArg(edmMethodArg);
		return result;
	}

	/**
	 * 新增返回值
	 * @param edmMethodArg
	 * @return
	 */
	@RequestMapping(value = "/returnArg", method = RequestMethod.POST)
	public Result addReturnArg(@Validated @RequestBody EdmMethodArg edmMethodArg) {
		Result result = edmMethodArgService.insertReturnArg(edmMethodArg);
		return result;
	}

	/**
	 * 根据id删除返回值
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/retArgs/{id}", method = RequestMethod.DELETE)
	public Result deleteInputArg(@PathVariable(value = "id") String id) {
		Result result = edmMethodArgService.deleteRetArg(id);
		return result;
	}

	/**
	 * 修改参数
	 * @param edmMethodArg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Result updateInputArg(@RequestBody EdmMethodArg edmMethodArg) {
		Result result =  edmMethodArgService.update(edmMethodArg);
		return result;
	}

	/**
	 * 通过方法ID查询输入参数列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/inputArgs/{id}", method = RequestMethod.GET)
	public Result selectInputArgById(@PathVariable("id") String id) {
		Result result = edmMethodArgService.getInputArgById(id);
		return result;
	}

	/**
	 * 通过方法ID查询返回值列表（返回值只有一个）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/retArgs/{id}", method = RequestMethod.GET)
	public Result selectReturnArgById(@PathVariable("id") String id) {
		Result result = edmMethodArgService.getReturnArgById(id);
		return result;
	}

	/**
	 * 参数的移动
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/move", method = RequestMethod.PUT)
	public Result move(@RequestBody String[] ids) {
		Result result = edmMethodArgService.move(ids);
		return result;
	}

}
