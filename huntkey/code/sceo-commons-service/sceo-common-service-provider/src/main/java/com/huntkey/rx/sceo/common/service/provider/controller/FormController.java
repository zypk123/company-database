package com.huntkey.rx.sceo.common.service.provider.controller;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.common.model.Form;
import com.huntkey.rx.sceo.common.service.common.model.TableColumn;
import com.huntkey.rx.sceo.common.service.provider.service.FormService;

/**
 * @Description:表单控制器
 * @author: wangning 
 * @date:   2017年5月3日18:51:22
 */
@RefreshScope
@RestController
@RequestMapping("/form")
public class FormController {

	@Autowired
	private FormService formService;
	/**
	 * 查询数据库表单设计器所需要的表
	 * @param db 数据库名称
	 * @return
	 */
	@RequestMapping(value="/table/{db}",method = RequestMethod.GET)
    public Result getFormTables(@PathVariable("db") String db){
		List<String> list = formService.getFormTables(db);
		Result res = new Result();
		res.setData(list);
		res.setRetCode(Result.RECODE_SUCCESS);
		return res;
    }
	@RequestMapping(value="/table/{db}/{table}",method = RequestMethod.GET)
    public Result getFormTableColumn(@PathVariable("db") String db ,@PathVariable("table") String table){
		List<TableColumn> list = formService.getFormTableColumn(db,table);
		Result res = new Result();
		res.setData(list);
		res.setRetCode(Result.RECODE_SUCCESS);
		return res;
    }
	@RequestMapping(value="/list/{type}",method = RequestMethod.GET)
    public Result listByType(@PathVariable("type") Form.FormType type){
		List<Form> list = formService.listByType(type);
		Result res = new Result();
		res.setData(list);
		res.setRetCode(Result.RECODE_SUCCESS);
		return res;
    }
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result get(@PathVariable("id") String id){
		Form f = formService.get(id);
		Result res = new Result();
		res.setData(f);
		res.setRetCode(Result.RECODE_SUCCESS);
		return res;
    }
	@RequestMapping(value="/save",method = RequestMethod.POST)
    public Result save(@RequestBody Form form){
		//System.out.println("provider端接收到的json"+form.getForm());
		int c = formService.save(form);
		Result res = new Result();
		res.setData(c == 1 ?"保存成功!":"保存失败!");
		res.setRetCode(Result.RECODE_SUCCESS);
		return res;
    }
	@RequestMapping(value="/formDataSubmit",method = RequestMethod.POST)
    public Result formDataSubmit(String formId,String data){
		System.out.println("client端接收到的formId"+ formId);
		System.out.println("client端接收到的json"+ data);
		Result res = formService.formDataSubmit(formId,data);
		return res;
    }
	
}	
