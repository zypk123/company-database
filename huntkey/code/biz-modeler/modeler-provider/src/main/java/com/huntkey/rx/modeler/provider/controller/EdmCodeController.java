package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.common.model.EdmCode;
import com.huntkey.rx.modeler.provider.service.EdmCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by licj on 2017/4/13.
 */
@RestController
@RequestMapping("/codes")
public class EdmCodeController {

    private static Logger log = LoggerFactory.getLogger(EdmCodeController.class);

    @Autowired
    public EdmCodeService edmCodeService;

    @RequestMapping(value = "/{codeType}",method = RequestMethod.GET)
    public Result queryEdmCodeListByCodeType(@PathVariable("codeType") String codeType){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmCode> edmCodeList = edmCodeService.queryEdmCodeListByCodeType(codeType);
        result.setData(edmCodeList);
        return result;
    }

    @RequestMapping(value = "/industries",method = RequestMethod.GET)
    public Result queryIndustries(){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmCode> edmCodeList = edmCodeService.queryIndustries();
        result.setData(edmCodeList);
        return result;
    }

    /**
     * 根据属性类型获取数据类型
     * @param codeValue
     * @return
     */
    @RequestMapping(value = "/dataType/{codeValue}", method = RequestMethod.GET)
    public Result getDataTypeByPropertyValue(@PathVariable String codeValue) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmCode> edmCodeList = edmCodeService.getDataTypeByPropertyValue(codeValue);
        result.setData(edmCodeList);
        return result;
    }

    /**
     * 根据一组codeType值查询字典值的信息
     * @param codeTypes 字典信息的CODE
     * @return 字典列表
     */
    @RequestMapping(value="/code",method = RequestMethod.GET)
    public Result getDictsByCodes(@RequestParam(value="codeTypes") String[] codeTypes){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmCodeService.getDictsByCodes(codeTypes));
        return result;
    }

    /**
     * 根据codeTypes查询tree,适用于少量数据的树形结构展示
     * @param codeType 编码
     */
    @RequestMapping(value="/tree",method = RequestMethod.GET)
    public Result getTreeData(@RequestParam(value = "codeType") String codeType){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmCodeService.getTreeData(codeType));
        return result;
    }

}
