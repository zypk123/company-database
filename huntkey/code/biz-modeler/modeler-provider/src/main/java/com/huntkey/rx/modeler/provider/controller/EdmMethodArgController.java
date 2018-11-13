package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.EdmMethodArg;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodArgVO;
import com.huntkey.rx.modeler.provider.service.EdmMethodArgService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 
 * @ClassName: EdmMethodArgController
 * @Description: 方法参数Controller
 * @author: zhangyu
 * @date: 2017年4月25日上午10:39:25
 *
 */
@RestController
@RequestMapping("/methodArgs")
public class EdmMethodArgController {

	private static Logger log = LoggerFactory.getLogger(EdmMethodArgController.class); // 得到log对象

	@Autowired
	EdmMethodArgService edmMethodArgService;// 注入service

	/**
	 * 方法参数名称唯一性校验
	 * @param edmaName
	 * @param edmaEdmmId
	 * @return
	 */
	@RequestMapping(value = "/edmaName", method = RequestMethod.GET)
	public Result checkData(@RequestParam String edmaName,
			@RequestParam String edmaEdmmId) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		// 数据异常校验
		String errorStr = edmMethodArgService.checkUnique(edmaName, edmaEdmmId);
		if (!StringUtil.isNullOrEmpty(errorStr)) {
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
		}
		return result;
	}

	/**
	 * 新增输入参数
	 * @param edmMethodArg
	 * @return
	 */
	@RequestMapping(value = "/inputArg", method = RequestMethod.POST)
	public Result insertInputArg(@Validated @RequestBody EdmMethodArg edmMethodArg) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		// 数据唯一性校验
		String errorStr = edmMethodArgService.checkUnique(edmMethodArg.getEdmaName(), edmMethodArg.getEdmaEdmmId());
		if (!StringUtil.isNullOrEmpty(errorStr)) { // 校验失败
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
			return result;
		}
		edmMethodArgService.insertInputArg(edmMethodArg);
		result.setData(edmMethodArg.getId());
		return result;
	}

	/**
	 * 新增返回值
	 * @param edmMethodArg
	 * @return
	 */
	@RequestMapping(value = "/retArg", method = RequestMethod.POST)
	public Result insertReturnArg(@Validated @RequestBody EdmMethodArg edmMethodArg) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		// 数据唯一性校验
		String errorStr = edmMethodArgService.checkUnique(edmMethodArg.getEdmaName(), edmMethodArg.getEdmaEdmmId());
		if (!StringUtil.isNullOrEmpty(errorStr)) { // 校验失败
			result.setRetCode(Result.RECODE_VALIDATE_ERROR);
			result.setErrMsg(errorStr);
			return result;
		}
		edmMethodArgService.insertReturnArg(edmMethodArg);
		result.setData(edmMethodArg.getId());
		return result;
	}

	/**
	 * 根据id删除返回值
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/retArgs/{id}", method = RequestMethod.DELETE)
	public Result deleteInputArg(@PathVariable String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmMethodArgService.deleteByPrimaryKey(id);
		return result;
	}

	/**
	 * 批量删除输入参数(逻辑删除)
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/inpuArgs/{ids}", method = RequestMethod.DELETE)
	public Result deleteInputArgs(@PathVariable("ids") String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmMethodArgService.deleteInputArgs(ids);
		return result;
	}

	/***
	 * 更新参数
	 * @param edmMethodArg
	 * @return
	 */
	@RequestMapping(method = RequestMethod.PUT)
	public Result updateInputArg(@RequestBody EdmMethodArg edmMethodArg) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		String oldName = edmMethodArgService.geMethodArgNameById(edmMethodArg.getId());// 更新之前的方法参数名
		if(!StringUtil.isNullOrEmpty(edmMethodArg.getEdmaName()) && !edmMethodArg.getEdmaName().equals(oldName)){
			String errorStr = edmMethodArgService.checkUnique(edmMethodArg.getEdmaName(),edmMethodArg.getEdmaEdmmId());
			if (!StringUtil.isNullOrEmpty(errorStr)) { // 校验失败
				result.setRetCode(Result.RECODE_VALIDATE_ERROR);
				result.setErrMsg(errorStr);
				return result;
			}
		}
		edmMethodArgService.updateByPrimaryKey(edmMethodArg);
		return result;
	}

	/**
	 * 通过方法ID查询输入参数列表
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/inputArgs/{id}", method = RequestMethod.GET)
	public Result getInputArgById(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		List<EdmMethodArgVO> edmMethodArgVOList = edmMethodArgService.selectInputArgsByEdmmId(id);
		result.setData(edmMethodArgVOList);
		return result;
	}

	/**
	 * 通过方法ID查询返回值列表（返回值只有一个）
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/retArgs/{id}", method = RequestMethod.GET)
	public Result getReturnArgById(@PathVariable(value = "id") String id) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		EdmMethodArgVO edmMethodArgVO = edmMethodArgService.selectRetArgsByEdmmId(id);
		result.setData(edmMethodArgVO);
		return result;
	}

	/**
	 * 类的移动
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/move", method = RequestMethod.PUT)
	public Result move(@RequestBody String[] ids) {
		Result result = new Result();
		result.setRetCode(Result.RECODE_SUCCESS);
		edmMethodArgService.move(ids);
		return result;
	}

}
