package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmMethodTypeService;
import com.huntkey.rx.modeler.common.model.EdmMethodType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by gujing on 2017/4/20 0020.
 */
@RestController
@RequestMapping("/v1/methodtypes")
public class EdmMethodTypeController {
    private static Logger log = LoggerFactory.getLogger(EdmMethodController.class);

    @Autowired
    private EdmMethodTypeService edmMethodTypeService; // 注入Service


    /**
     * 获取所有方法分类
     * @param
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result queryAll() {
        Result result = edmMethodTypeService.queryAll();
        return result;
    }

    /**
     * 根据方法分类id获取方法分类及其子分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result query(@PathVariable("id") String id) {
        Result result = edmMethodTypeService.query(id);
        return result;
    }

    /**
     * 方法分类父分类校验(废弃)
     * @param edmtParentId
     * @param id
     * @return
     */
    @RequestMapping(value = "/edmtParentId", method = RequestMethod.GET)
    public Result checkFatherNode(@RequestParam("edmtParentId") String edmtParentId,@RequestParam("id") String id){
        Result result =  edmMethodTypeService.checkFatherNode(edmtParentId,id);
        return result;
    }

    /**
     * 方法分类编码校验
     * @param edmtCode
     * @return
     */
    @RequestMapping(value = "/edmtCode", method = RequestMethod.GET)
    public Result checkCodeUnique(@RequestParam("edmtCode") String edmtCode){
        Result result = edmMethodTypeService.checkCodeUnique(edmtCode);
        return result;
    }


    /**
     * 方法分类名校验
     * @param edmtName
     * @return
     */
    @RequestMapping(value = "/edmtName", method = RequestMethod.GET)
    public Result checkNameUnique(@RequestParam("edmtName") String edmtName){
        Result result = edmMethodTypeService.checkNameUnique(edmtName);
        return result;
    }

    /**
     * 新增方法分类
     * @param edmMethodType
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmMethodType edmMethodType) {
        Result result = edmMethodTypeService.add(edmMethodType);
        return result;
    }

    /**
     * 修改方法分类
     * @param edmMethodType
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody EdmMethodType edmMethodType) {
        Result result = edmMethodTypeService.update(edmMethodType);
        return result;
    }

    /**
     * 根据id删除方法分类;不能删除有子节点的方法分类，不能删除存在方法的方法分类
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable() String id) {
        Result result = edmMethodTypeService.delete(id);
        return result;
    }

    /**
     * 方法分类本级节点包含的所有方法移动到指定节点
     * @param sourceMethodtypeId
     * @param aimMethodtypeId
     * @return
     */
    @RequestMapping(value = "/moveMethod/{sourceMethodtypeId}/{aimMethodtypeId}", method = RequestMethod.PUT)
    public Result moveMethodtypeMethodToMethodtype(@PathVariable("sourceMethodtypeId") String sourceMethodtypeId, @PathVariable("aimMethodtypeId") String aimMethodtypeId) {
        Result result = edmMethodTypeService.moveMethodtypeMethodToMethodtype(sourceMethodtypeId, aimMethodtypeId);
        return result;
    }

}
