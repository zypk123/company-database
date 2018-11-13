package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmClassFormatService;
import com.huntkey.rx.modeler.common.model.EdmClassFormat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 *
 * @ClassName: EdmClassFormatController
 * @Description: 对象呈现格式接口Controller
 * @author: qinchun
 * @date: 2017年7月18日18:07:26
 *
 */
@RestController
@RequestMapping("/v1/classFormats")
public class EdmClassFormatController {

	private static Logger log = LoggerFactory.getLogger(EdmClassFormatController.class);

	@Autowired
	private EdmClassFormatService edmClassFormatService; // 注入Service


	/**
	 * 根据类ID查询对象呈现格式
	 * @param edmcId
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public Result queryList(@RequestParam String edmcId){
		Result result =  edmClassFormatService.queryList(edmcId);
		return result;
	}

	/**
	 * 批量新增对象呈现格式
	 * @param edmClassFormatList
	 * @return
	 */
	@RequestMapping(value = "/insertBatch", method = RequestMethod.POST)
	public Result insertBatch(@Validated @RequestBody List<EdmClassFormat> edmClassFormatList){
		Result result = edmClassFormatService.insertBatch(edmClassFormatList);
		return result;
	}

	/**
	 * 批量删除对象呈现格式
	 * @param ids
	 * @return
	 */
	@RequestMapping(value = "/deleteBatch/{ids}", method = RequestMethod.PUT)
	public Result deleteBatch(@PathVariable String[] ids){
		Result result = edmClassFormatService.deleteBatch(ids);
		return result;
	}

	/**
	 * 获取类的特征值及显示格式
	 *
	 * @param classId
	 * @return
	 */
	@RequestMapping(value = "/getCharacterAndFormat", method = RequestMethod.GET)
	public Result getCharacterAndFormat(@RequestParam String classId) {
		Result result = edmClassFormatService.getCharacterAndFormat(classId);
		return result;
	}

	/**
	 * 根据类id获取类的对象列表（呈现、枚举以及其他类）
	 * @param classId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getAppearAndEnumObject", method = RequestMethod.GET)
	public Result getAppearAndEnumObject(@RequestParam String classId,@RequestParam(required = false, defaultValue = "1") int pageNum,@RequestParam(required = false)String wordName,
										 @RequestParam(required = false, defaultValue = "10") int pageSize) {
		Result result = edmClassFormatService.getAppearAndEnumObject(classId,wordName,pageNum,pageSize);
		return result;
	}

	@RequestMapping(value = "/selectWordlists", method = RequestMethod.GET)
	public Result selectWordlists(@RequestParam(value = "wordName",required = false)String wordName,
								  @RequestParam(required = false, defaultValue = "1")int pageNum,@RequestParam(required = false, defaultValue = "15")int pageSize) {
		Result result = edmClassFormatService.selectWordlists(wordName,pageNum,pageSize);
		return result;
	}

}
