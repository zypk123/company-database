package com.huntkey.rx.sceo.common.service.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.client.feign.FormService;
import com.huntkey.rx.sceo.common.service.client.feign.WorkFlowService;
import com.huntkey.rx.sceo.common.service.common.model.Form;
import com.huntkey.rx.sceo.common.service.common.model.Form.FormType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:提供自定义表单设计操作相关接口
 * @author: Wangning
 * @date: 2017年5月3日18:23:55
 */
@RestController
@RequestMapping("${adminPath}/form")
//@Api(value = "自定义表单设计支持服务", description = "提供自定义表单设计的时候需要的一些接口方法")
public class FormController {

    @Autowired
    private FormService formService;

//	@Autowired
//	private WorkFlowService flowService;

    /**
     * 查询数据库表单设计器所需要的表
     *
     * @param db 数据库名称
     * @return
     */
    @RequestMapping(value = "/table/{db}", method = RequestMethod.GET)
//    @ApiOperation("根据数据库名获取表列表")
    public Result getFormTables(@PathVariable("db") String db) {
        Result res = new Result();
        try {
            res = formService.getFormTables(db);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/table/{db}/{table}", method = RequestMethod.GET)
//    @ApiOperation("根据数据库名，表名，获取表字段列表")
    public Result getFormTableColumn(@PathVariable("db") String db, @PathVariable("table") String table) {
        Result res = new Result();
        try {
            res = formService.getFormTableColumn(db, table);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/list/{type}", method = RequestMethod.GET)
//    @ApiOperation("根据类型：form，temp，获取表单列表或者表单模板列表")
    public Result listByType(@PathVariable("type") FormType type) {
        Result res = formService.listByType(type);
        return res;
    }

    @RequestMapping(value = "/get/{id}", method = RequestMethod.GET)
//    @ApiOperation("根据ID获取表单或者模板")
    public Result get(@PathVariable("id") String id) {
        Result res = formService.get(id);
        return res;
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    @ApiOperation("保存表单或者模板，根据ID是否为空自动执行插入或者更新操作")
    public Result save(@RequestBody Form form) {
        //System.out.println("client端接收到的json"+form.getForm());
        Result res = formService.save(form);
        return res;
    }
//	@RequestMapping(value="/formDataSubmit",method = RequestMethod.POST)
//    public Result formDataSubmit(@RequestBody Form form){
//		System.out.println("client端接收到的formId"+ form.getId());
//		System.out.println("client端接收到的flowKey"+ form.getFlowKey());
//		System.out.println("client端接收到的json"+ form.getFormData());
//		Result res = formService.formDataSubmit(form.getId(),form.getFormData());
//		@SuppressWarnings("unchecked")
//		Map<String,Object> map = (Map<String, Object>) res.getData();
//		String busiKey = map.get("mainTable") + ":" + map.get("dataId");
//		Result res2 = flowService.startProcessInstanceByKey(form.getFlowKey(), busiKey, map);
//		return res;
//    }

}
