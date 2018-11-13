package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmMethodService;
import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodAndArgVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @ClassName: EdmMethodController
 * @Description: 方法api接口Controller
 * @author: zhangyu
 * @date: 2017年4月12日上午11:35:15
 */
@RestController
@RequestMapping("/v1/methods")
//@Api(value = "方法维护服务", description = "提供方法的新增、修改、删除和查询服务")
public class EdmMethodController {

    private static Logger log = LoggerFactory.getLogger(EdmMethodController.class);

    @Autowired
    private EdmMethodService edmMethodService; // 注入Service

    /**
     * 通过ID查询方法
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result selectMethodById(@PathVariable("id") String id) {
        Result result = edmMethodService.getMethodById(id);
        return result;
    }

    /**
     * 条件查询，带分页
     * @param edmmName
     * @param edmmClasses 所属类 多个所属类以逗号分隔
     * @param pageNum
     * @param pageSize
     * @return RESTFul接口返回对象格式
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result query(@RequestParam(required = false, value = "edmmType") String edmmType,
                        @RequestParam(required = false, value = "edmmProgramType") String edmmProgramType,
                        @RequestParam(required = false, value = "edmmName") String edmmName,
                        @RequestParam(required = false, value = "edmmClasses") String edmmClasses,
                        @RequestParam(required = false, value = "edmmStatus") String edmmStatus,
                        @RequestParam(defaultValue = "1", value = "pageNum") int pageNum,
                        @RequestParam(defaultValue = "10", value = "pageSize") int pageSize) {
        Result result =  edmMethodService.getMethods(edmmType, edmmProgramType, edmmName, edmmClasses,edmmStatus, pageNum, pageSize);
        return result;
    }

    /**
     * 方法名校验
     * @param edmmName
     * @return
     */
    @RequestMapping(value = "/edmmName", method = RequestMethod.GET)
    public Result checkData(@RequestParam String edmcId,@RequestParam String edmmName) {
        Result result = edmMethodService.checkData(edmcId,edmmName);
        return result;
    }

    /**
     * 新增方法
     * @param edmMethodAndArgVO
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmMethodAndArgVO edmMethodAndArgVO) {
        Result result = edmMethodService.add(edmMethodAndArgVO);
        return result;
    }

    /***
     * 更新方法
     * @param edmMethodAndArgVO
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody EdmMethodAndArgVO edmMethodAndArgVO) {
        Result result = edmMethodService.update(edmMethodAndArgVO);
        return result;
    }

    /***
     * 更新方法基本信息
     * @param edmMethod
     * @return
     */
    @RequestMapping(value = "/baseInfo", method = RequestMethod.PUT)
    public Result updateBaseInfo(@RequestBody EdmMethod edmMethod) {
        Result result = edmMethodService.updateBaseInfo(edmMethod);
        return result;
    }

    /**
     * 根据id删除方法
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) {
        Result result = edmMethodService.delete(id);
        return result;
    }

    /**
     * 批量删除方法
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
    public Result deleteMethods(@PathVariable(value = "id") String[] ids) {
        Result result = edmMethodService.deleteMethods(ids);
        return result;
    }

    /**
     * 方法的移动
     * @param ids
     * @return
     */
    @RequestMapping(value = "/move", method = RequestMethod.PUT)
    public Result moveUpDown(@RequestBody String[] ids) {
        Result result = edmMethodService.move(ids);
        return result;
    }

    /**
     * 通过方法ID查找类
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/classes", method = RequestMethod.GET)
    public Result getClassesByMethodId(@PathVariable("id") String id) {
        Result result = edmMethodService.getClassesByMethodId(id);
        return result;
    }

    /**
     * 查询方法分类结构树。
     * @return
     */
    @RequestMapping(value = "/methodTypeTree", method = RequestMethod.GET)
    public Result queryClassMethodType() {
        Result result = edmMethodService.queryClassMethodTypeTree();
        return result;
    }

    /**
     * 方法与类的关系查询
     * @param type
     * @param name
     * @param version
     * @param className
     * @return
     */
    @RequestMapping(value = "/classes", method = RequestMethod.GET)
    public Result queryMethodsAndClasses(@RequestParam(required = false, value = "type") String type,
                                         @RequestParam(required = false, value = "name") String name,
                                         @RequestParam(required = false, value = "version") String version,
                                         @RequestParam(required = false, value = "className") String className) {
        Result result = edmMethodService.queryMethodsAndClasses(type, name, version, className);
        return result;
    }

    /**
     * 根据类id查询方法id
     * @return
     */
    @RequestMapping(value = "/classes/{id}", method = RequestMethod.GET)
    public Result queryMethodIdsByClassId(@PathVariable("id") String id) {
        Result result = edmMethodService.queryMethodIdsByClassId(id);
        return result;
    }

    /**
     * 上传方法体
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadMethodBody", method = RequestMethod.POST)
    public Result uploadMethodBody(@RequestParam("file") MultipartFile file) throws Exception{
        Result result = edmMethodService.uploadMethodBody(file);
        return result;
    }

    /**
     * 根据方法名获取方法体
     * @param edmmName
     * @return
     */
    @RequestMapping(value = "/getMethodBody", method = RequestMethod.GET)
    public Result getMethodBody(@RequestParam(value = "edmmName") String edmmName) {
        Result result = edmMethodService.getMethodBody(edmmName);
        return result;
    }

    /**
     * 通过方法id禁用方法
     * @param id
     * @return
     */
    @RequestMapping(value = "/updateEdmmStatus",method = RequestMethod.PUT)
    public Result stopMethodById(@RequestParam(value = "id") String id,
                                 @RequestParam(value = "edmmStatus") String edmmStatus){
        Result result = edmMethodService.updateEdmmStatus(id,edmmStatus);
        return result;
    }

    @RequestMapping(value = "/{id}/{classId}",method = RequestMethod.GET)
    public Result getMethodByIdClassId(@PathVariable(value = "id") String id,
                                       @PathVariable(value = "classId") String classId){

        Result result = edmMethodService.getMethodByIdClassId(id,classId);
        return result;
    }


    /**
     * 根据方法id 保存触发条件id
     * @param id
     * @param condId
     * @return
     */
    @RequestMapping(value = "/{id}/{condId}",method = RequestMethod.PUT)
    public Result updateTriggerCond(@PathVariable(value = "id") String id,
                                    @PathVariable(value = "condId") String condId){

        Result result = edmMethodService.updateTriggerCond(id,condId);
        return result;
    }


    /**
     * 根据方法id 删除触发条件id
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}/cond",method = RequestMethod.DELETE)
    public Result deleteTriggerCond(@PathVariable(value = "id") String id){

        Result result = edmMethodService.deleteTriggerCond(id);
        return result;
    }


}
