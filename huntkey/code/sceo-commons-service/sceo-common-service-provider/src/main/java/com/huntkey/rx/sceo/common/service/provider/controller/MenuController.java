package com.huntkey.rx.sceo.common.service.provider.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.common.model.Menu;
import com.huntkey.rx.sceo.common.service.provider.service.MenuService;

/**
 * @Description:菜单控制器
 * @author: 王宁 
 * @date:   2017年5月5日16:42:44
 */
@RefreshScope
@RestController
@RequestMapping("/menu")
public class MenuController {
	
	@Autowired
	private MenuService menuService;
	
	/**
     * 国际化
     */
    @Autowired
    MessageSource messageSource;
	
	/**
	 * 获取所有菜单
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
    public Result getAllMenu(){
		Result result = new Result();
        try {
            result.setData(menuService.getAllMenu());
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
        }
        return result;
    }
	
	/**
	 * 查询所有的子菜单信息
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/parent/{pid}",method = RequestMethod.GET)
    public Result getSecAllMenu(@PathVariable("pid") String pid){
		Result result = new Result();
        try {
            result.setData(menuService.getSecAllMenu(pid));
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
        }
        return result;
    }
	
	/**
	 * 根据ID查询菜单信息
	 * @param id 菜单ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result getMenuById(@PathVariable("id") String id){
		Result result = new Result();
        try {
            result.setData(menuService.getMenuById(id));
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
        }
        return result;
    }
	
	@RequestMapping(method = RequestMethod.POST)
    public Result save(@Valid @RequestBody Menu menu, BindingResult bindingResult){
		Result result = new Result();
		if (bindingResult.hasErrors()) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.validate.exception", null, LocaleContextHolder.getLocale()));
            result.setData(bindingResult.getAllErrors());
            return result;
        }
		 
        try {
            menuService.save(menu);
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
        }
        return result;
    }
	
	@RequestMapping(method = RequestMethod.PUT)
    public Result update(@Valid @RequestBody Menu menu, BindingResult bindingResult){
		Result result = new Result();
		if (bindingResult.hasErrors()) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.validate.exception", null, LocaleContextHolder.getLocale()));
            result.setData(bindingResult.getAllErrors());
            return result;
        }
		 
        try {
        	menuService.update(menu);
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
        }
        return result;
    }
	
	@RequestMapping(value="/child/{id}",method = RequestMethod.GET)
    public Result hasChild(@PathVariable("id") String id){
		Result result = new Result();
        try {
            result.setData(menuService.hasChild(id));
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
        }
        return result;
    }
	
	@RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id){
		Result result = new Result();
        try {
        	menuService.delete(id);
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
        }
        return result;
    }
}
