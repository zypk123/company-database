package com.huntkey.rx.modeler.provider.controller;

import com.alibaba.druid.util.StringUtils;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmMethodType;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodTypeVO;
import com.huntkey.rx.modeler.provider.service.EdmMethodTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by gujing on 2017/4/19 0019.
 */
@RestController
@RequestMapping("/methodtypes")
public class EdmMethodTypeController {
    private static Logger log = LoggerFactory.getLogger(EdmMethodTypeController.class);

    @Autowired
    public EdmMethodTypeService edmMethodTypeService;


    /**
     * 获取所有方法分类
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result queryAll() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmMethodTypeVO> edmMethodTypeVO = edmMethodTypeService.selectEdmMethodTypeVOWithSonById("");
        result.setData(edmMethodTypeVO);
        return result;
    }


    /**
     * 根据方法分类id获取方法分类及其子分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result query(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmMethodTypeVO> edmMethodTypeVO = edmMethodTypeService.selectEdmMethodTypeVOWithSonById(id);
        result.setData(edmMethodTypeVO);
        return result;
    }

    /**
     * 方法分类父分类校验(废弃)
     * @param edmtParentId
     * @param id
     * @return
     */
    @RequestMapping(value = "/edmtParentId", method = RequestMethod.GET)
    public Result checkFatherNode(@RequestParam("edmtParentId") String edmtParentId,@RequestParam("id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmMethodTypeService.checkFatherNode(edmtParentId,id);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }

    /**
     * 方法分类检验方法分类编码唯一性
     * @param edmtCode
     * @return
     */
    @RequestMapping(value = "/edmtCode", method = RequestMethod.GET)
    public Result checkCodeUnique(@RequestParam("edmtCode") String edmtCode) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmMethodTypeService.checkCodeUnique(edmtCode);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }

    /**
     * 方法分类名称校验
     * @param edmtName
     * @return
     */
    @RequestMapping(value = "/edmtName", method = RequestMethod.GET)
    public Result checkNameUnique(@RequestParam("edmtName") String edmtName) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmMethodTypeService.checkNameUnique(edmtName);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        }
        return result;
    }


    /**
     * 新增方法分类
     * @param edmMethodType
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmMethodType edmMethodType) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr = edmMethodTypeService.checkCodeUnique(edmMethodType.getEdmtCode());
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
            return result;
        }
        errorStr = edmMethodTypeService.checkNameUnique(edmMethodType.getEdmtName());
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
            return result;
        }
        edmMethodTypeService.insert(edmMethodType);
        result.setData(edmMethodType.getId());
        return result;
    }

    /**
     * 修改方法分类
     * @param edmMethodType
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody EdmMethodType edmMethodType) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errorStr;
        EdmMethodType oldEdmMethodType = edmMethodTypeService.selectMethodTypeById(edmMethodType.getId());
        if(!StringUtil.isNullOrEmpty(edmMethodType.getEdmtCode()) && !edmMethodType.getEdmtCode().equals(oldEdmMethodType.getEdmtCode())){
            errorStr = edmMethodTypeService.checkCodeUnique(edmMethodType.getEdmtCode());
            if (!StringUtil.isNullOrEmpty(errorStr)) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }
        }
        if(!StringUtil.isNullOrEmpty(edmMethodType.getEdmtName()) && !edmMethodType.getEdmtName().equals(oldEdmMethodType.getEdmtName())){
            errorStr = edmMethodTypeService.checkNameUnique(edmMethodType.getEdmtName());
            if (!StringUtil.isNullOrEmpty(errorStr)) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }
        }
        edmMethodTypeService.update(edmMethodType);
        return result;
    }

    /**
     * 根据id删除方法分类;不能删除有子节点的方法分类，不能删除存在方法的方法分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        //数据异常校验
        String errorStr = edmMethodTypeService.deleteValidate(id);
        if (!StringUtil.isNullOrEmpty(errorStr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorStr);
        } else {
            edmMethodTypeService.delete(id);
        }
        return result;
    }


    /**
     * 方法分类本级节点包含的所有方法移动到指定节点
     * @param sourceMethodtypeId
     * @param aimMethodtypeId
     * @return
     */
    @RequestMapping(value = "/move/{sourceMethodtypeId}/{aimMethodtypeId}", method = RequestMethod.PUT)
    public Result moveMethodtypeMethodToMethodtype(@PathVariable("sourceMethodtypeId") String sourceMethodtypeId, @PathVariable("aimMethodtypeId") String aimMethodtypeId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmMethodTypeService.moveMethodtypeMethodToMethodtype(sourceMethodtypeId, aimMethodtypeId);
        return result;
    }

}
