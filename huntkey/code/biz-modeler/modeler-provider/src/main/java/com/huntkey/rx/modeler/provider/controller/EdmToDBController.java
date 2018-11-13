package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.provider.service.EdmToDbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * Created by zhoucj on 2017/11/3.
 */
@RestController
public class EdmToDBController {
    @Autowired
    private EdmToDbService edmToDbService;

    /**
     * 根据类编号 生成物理DB
     * @param classIds
     * @return
     */
    @RequestMapping(value = "/dbCreator", method = RequestMethod.POST)
    public Result dbCreator( @RequestBody String classIds[]){
        Result result = new Result();
        int errCode = Result.RECODE_SUCCESS;
        String msg = edmToDbService.dbCreator(classIds);
        if(!"执行成功".equals(msg)){
            errCode = Result.RECODE_VALIDATE_ERROR;
            result.setErrMsg(msg);
        }else{
            result.setData(msg);
        }
        result.setRetCode(errCode);

        return result;
    }

    @RequestMapping(value = "/dbAllCreator", method = RequestMethod.GET)
    public Result dbAllCreator(@RequestParam(value = "version") String version){
        Result result = new Result();
        int errCode = Result.RECODE_SUCCESS;
        String msg = edmToDbService.dbAllCreator(version);
        if(!"执行成功".equals(msg)){
            errCode = Result.RECODE_VALIDATE_ERROR;
            result.setErrMsg(msg);
        }else{
            result.setData(msg);
        }
        result.setRetCode(errCode);
        return result;
    }

    @RequestMapping(value = "/dbPassive", method = RequestMethod.POST)
    public Result dbPassive(@RequestParam(value = "version") String version, @RequestBody Map<String,String> pathMap){
        Result result = new Result();
        int errCode = Result.RECODE_SUCCESS;
        String msg = edmToDbService.dbPassive(version,pathMap);
        if(!"执行成功".equals(msg)){
            errCode = Result.RECODE_VALIDATE_ERROR;
            result.setErrMsg(msg);
        }else{
            result.setData(msg);
        }
        result.setRetCode(errCode);
        return result;
    }
}
