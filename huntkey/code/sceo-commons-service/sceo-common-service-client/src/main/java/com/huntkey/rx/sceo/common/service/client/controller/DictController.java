package com.huntkey.rx.sceo.common.service.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.client.feign.DictService;
import com.huntkey.rx.sceo.common.service.common.model.Dict;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:描述
 * @author: DongHuan
 * @date: 2017年4月20日 上午10:44:49
 */
@RestController
@RequestMapping("${adminPath}/dict")
public class DictController {

    @Autowired
    DictService dictService;

    @RequestMapping(method = RequestMethod.GET)
    public Result selDicts() {
        Result res = new Result();
        try {
            res = dictService.selAllDicts();
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }

    @RequestMapping(value = "/childs/{pid}", method = RequestMethod.GET)
    public Result selDictsByParent(@PathVariable("pid") String pid) {
        Result res = new Result();
        res = dictService.selDictsByParent(pid);
        /*try{
        }catch(Exception e){
			e.printStackTrace();
			res.setErrMsg(e.getMessage());
			res.setRetCode(Result.RECODE_ERROR);
		}*/
        return res;
    }

    @RequestMapping(value = "/childNode/{pid}", method = RequestMethod.GET)
    public Result haveChildNode(@PathVariable("pid") String pid) {
        Result res = new Result();
        try {
            res = dictService.haveChildNode(pid);
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result selDictsById(@PathVariable("id") String id) {
        Result res = new Result();
        try {
            res = dictService.selDictsById(id);
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Dict dict) {
        Result res = new Result();
        try {
            res = dictService.save(dict);
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody Dict dict) {
        Result res = new Result();
        try {
            res = dictService.update(dict);
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id) {
        Result res = new Result();
        try {
            res = dictService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }

    @RequestMapping(value = "/parent/{id}", method = RequestMethod.GET)
    public Result getAllParent(@PathVariable("id") String id) {
        Result res = new Result();
        try {
            res = dictService.getAllParent(id);
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }

    @RequestMapping(value = "/parent_sort/{id}", method = RequestMethod.GET)
    public Result getParentBySort(@PathVariable("id") String id) {
        Result res = new Result();
        try {
            res = dictService.getParentBySort(id);
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }

    @RequestMapping(value = "/code/{code}", method = RequestMethod.GET)
    public Result getDictsByCode(@PathVariable("code") String code) {
        Result res = new Result();
        try {
            res = dictService.getDictsByCode(code);
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }

    @RequestMapping(value = "/codeVal", method = RequestMethod.GET)
    public Result getDictByCodeVal(@RequestParam(value = "code") String code, @RequestParam(value = "value") String value) {
        Result res = new Result();
        try {
            res = dictService.getDictByCodeVal(code, value);
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }

    @RequestMapping(value = "/code", method = RequestMethod.GET)
    public Result getDictsByCodes(@RequestParam(value = "codes[]") String[] codes) {
        Result res = new Result();
        try {
            res = dictService.getDictsByCodes(codes);
        } catch (Exception e) {
            e.printStackTrace();
            res.setErrMsg(e.getMessage());
            res.setRetCode(Result.RECODE_ERROR);
        }
        return res;
    }
}
