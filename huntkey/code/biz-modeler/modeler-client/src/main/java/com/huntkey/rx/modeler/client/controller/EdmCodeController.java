package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 
 * @ClassName: EdmModelerController
 * @Description: 模型api接口Controller
 * @author: liangh
 * @date: 2017年4月16日上午11:35:15
 *
 */
@RestController
@RequestMapping("/v1/codes")
public class EdmCodeController {

	private static Logger log = LoggerFactory.getLogger(EdmCodeController.class);

	@Autowired
	public EdmCodeService edmCodeService;

	/**
	 * 根据编码类型查询
	 * @param codeType
	 * @return
	 */
	@RequestMapping(value = "/{codeType}",method = RequestMethod.GET)
	public Result queryEdmCodeListByCodeType(@PathVariable("codeType") String codeType){
		Result result = edmCodeService.queryEdmCodeListByCodeType(codeType);
		return result;
	}

	/**
	 * 查询行业树
	 * @return
	 */
	@RequestMapping(value = "/industries",method = RequestMethod.GET)
	public Result queryIndustries(){
		Result result = edmCodeService.queryIndustries();
		return result;
	}

	/**
	 * 根据属性类型获取数据类型
	 * @param codeValue
	 * @return
	 */
	@RequestMapping(value = "/dataType/{codeValue}", method = RequestMethod.GET)
	public Result getDataTypeByPropertyValue(@PathVariable("codeValue") String codeValue){
		Result result = edmCodeService.getDataTypeByPropertyValue(codeValue);
		return result;
	}

	/**
	 * 根据一组CODE值查询字典值的信息
	 * @param codeTypes 字典信息的CODE
	 * @return 字典列表
	 */
	@RequestMapping(value="/code",method = RequestMethod.GET)
	public Result getDictsByCodes(@RequestParam(value="codeTypes") String[] codeTypes){
		Result result = edmCodeService.getDictsByCodes(codeTypes);
		return result;
	}

	/**
	 * 根据codeType查询tree,适用于少量数据的树形结构展示
	 * @param codeType 编码
	 */
	@RequestMapping(value="/tree",method = RequestMethod.GET)
	public Result getTreeData(@RequestParam(value = "codeType") String codeType){
		Result result = edmCodeService.getTreeData(codeType);
		return result;
	}
}
