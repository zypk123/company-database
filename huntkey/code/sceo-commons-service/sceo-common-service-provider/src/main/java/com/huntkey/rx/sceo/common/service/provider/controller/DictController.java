package com.huntkey.rx.sceo.common.service.provider.controller;

import java.util.List;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.common.model.Dict;
import com.huntkey.rx.sceo.common.service.provider.service.DictService;

/**
 * @Description:字典控制器 
 * @author: DongHuan 
 * @date:   2017年4月25日 下午7:22:34
 */
@RefreshScope
@RestController
@RequestMapping("/dict")
public class DictController {
	
	@Autowired
	private DictService dictService;
	
	/**
     * 国际化
     */
    @Autowired
    MessageSource messageSource;
	
	@RequestMapping(method = RequestMethod.GET)
	public Result selAllDicts(){
		Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            result.setData(dictService.selAllDicts());
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
        }
		return result;
	}
	
	@RequestMapping(value="/childs/{pid}",method = RequestMethod.GET)
	public Result selDictsByParent(@PathVariable("pid") String pid){
		List<Dict> list = dictService.selDictsByParent(pid);
		Result res = new Result();
		res.setErrMsg("查询成功!");
		res.setData(list);
		res.setRetCode(Result.RECODE_SUCCESS);
		return res;
	}
	
	@RequestMapping(value="/{id}",method = RequestMethod.GET)
    public Result selDictById(@PathVariable("id") String id){
		Dict dict = dictService.selDictById(id);
		Result res = new Result();
		res.setErrMsg("查询成功!");
		res.setData(dict);
		res.setRetCode(Result.RECODE_SUCCESS);
        return res;
    }
	
	/**
	 * 查询当前节点下是否存在子节点
	 * @param pid
	 * @return
	 */
	@RequestMapping(value="/childNode/{pid}",method = RequestMethod.GET)
    public Result haveChildNode(@PathVariable("pid") String pid){
    	boolean flag = dictService.haveChildNode(pid);
    	Result res = new Result();
    	res.setErrMsg("查询成功!");
		res.setData(flag);
		res.setRetCode(Result.RECODE_SUCCESS);
        return res;
    }
	
	@RequestMapping(method = RequestMethod.POST)
	public Result addDict(@Valid @RequestBody Dict dict, BindingResult bindingResult) {
		Result result = new Result();
        if (bindingResult.hasErrors()) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.validate.exception", null, LocaleContextHolder.getLocale()));
            result.setData(bindingResult.getAllErrors());
            return result;
        }
		try {
			dictService.addDict(dict);
			result.setRetCode(Result.RECODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(Result.RECODE_ERROR);
			result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
		}
		return result;
	}
	
	@RequestMapping(method = RequestMethod.PUT)
	public Result updateDict(@Valid @RequestBody Dict dict, BindingResult bindingResult) {
		Result result = new Result();
        if (bindingResult.hasErrors()) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(messageSource.getMessage("message.validate.exception", null, LocaleContextHolder.getLocale()));
            result.setData(bindingResult.getAllErrors());
            return result;
        }
    	try {
			dictService.updateDict(dict);
			result.setRetCode(Result.RECODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(Result.RECODE_ERROR);
			result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
		}
		return result;
	}

    @RequestMapping(value="/{id}",method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id){
		Result result = new Result();
		try {
			dictService.delete(id);
			result.setRetCode(Result.RECODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(Result.RECODE_ERROR);
			result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
		}
		return result;
    }
    
    @RequestMapping(value="/parent/{id}",method = RequestMethod.GET)
    public Result getAllParent(@PathVariable("id") String id){
		Result result = new Result();
		try {
			result.setData(dictService.getAllParent(id));
			result.setRetCode(Result.RECODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(Result.RECODE_ERROR);
			result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
		}
		return result;
    }

	@RequestMapping(value="/parent_sort/{id}",method = RequestMethod.GET)
	public Result getParentBySort(@PathVariable("id") String id){
		Result result = new Result();
		try{
			result.setData(dictService.getParentBySort(id));
			result.setRetCode(Result.RECODE_SUCCESS);
		}catch(Exception e){
			e.printStackTrace();
			result.setRetCode(Result.RECODE_ERROR);
			result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
		}
		return result;
	}
    
    /**
     * 根据CODE值查询字典值的信息
     * @param code 字典信息的CODE
     * @return 字典列表
     */
    @RequestMapping(value="/code/{code}",method = RequestMethod.GET)
    public Result getDictsByCode(@PathVariable("code") String code){
		Result result = new Result();
		try {
			result.setData(dictService.getDictsByCode(code));
			result.setRetCode(Result.RECODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(Result.RECODE_ERROR);
			result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
		}
        return result;
    }
    
    @RequestMapping(value="/codeVal",method = RequestMethod.GET)
    public Result getDictByCodeVal(@RequestParam(value = "code") String code,@RequestParam(value = "value") String value){
		Result result = new Result();
		try {
			result.setData(dictService.getDictByCodeVal(code,value));
			result.setRetCode(Result.RECODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(Result.RECODE_ERROR);
			result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
		}
        return result;
    }
    
    
    /**
     * 根据一组CODE值查询字典值的信息
     * @param codes 字典信息的CODE
     * @return 字典列表
     */
    @RequestMapping(value="/code",method = RequestMethod.GET)
    public Result getDictsByCodes(@RequestParam(value="codes[]") String[] codes){
 		Result result = new Result();
		try {
			result.setData(dictService.getDictsByCodes(codes));
			result.setRetCode(Result.RECODE_SUCCESS);
		} catch (Exception e) {
			e.printStackTrace();
			result.setRetCode(Result.RECODE_ERROR);
			result.setErrMsg(messageSource.getMessage("message.system.exception", null, LocaleContextHolder.getLocale()));
		}
        return result;
    }
}
