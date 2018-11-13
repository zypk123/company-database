package com.huntkey.rx.sceo.serviceCenter.provider.biz.monitor.controller;

import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.monitor.service.MonitorTreeService;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;

/**
 * Created by zhaomj on 2017/8/10.
 */
@RestController
@RequestMapping("/servicecenter/business/monitors")
public class MonitorTreeController {

    @Autowired
    MonitorTreeService monitorTreeService;

    @GetMapping
    public Result getMonitorClasses(@RequestParam(value = "treeName",required = false) String treeName,
                                    @RequestParam(value = "beginTime",required = false) String beginTime,
                                    @RequestParam(value = "endTime",required = false) String endTime,
                                    @RequestParam(value = "edmdVer") String edmdVer,
                                    @RequestParam(value = "edmcNameEn") String edmcNameEn) throws DBException{
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(monitorTreeService.getMonitorClasses(treeName,beginTime,endTime,edmdVer,edmcNameEn));
        return  result;
    }
    
    /**
     * 
     * getNodeResources:(描述这个方法的作用)
     * @author lijie
     * @param name
     * @param nodes
     * @param edmId
     * @param edmName
     * @param type 1 - 代表从正式表查  2 - 代表从临时单查询
     * @return
     * @throws DBException
     */
    @GetMapping(value = "/trees/resources")
    Result getNodeResources(@RequestParam(value = "name", required = false) String name,
                            @RequestParam(value = "nodes") List<String> nodes,
                            @RequestParam(value = "edmId") String edmId,
                            @RequestParam(value = "edmName") String edmName,
                            @RequestParam(value = "type") int type) throws DBException{
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(monitorTreeService.getNodeResources(name,nodes,edmId,edmName,type));
        return  result;
    }
    
    @GetMapping(value = "/trees/search")
    public Result searchResourceObj(@RequestParam(value = "resourceClassId") @NotBlank(message = "资源类ID不能为空") String resourceClassId,
                                    @RequestParam(value = "resourceValue") @NotBlank(message = "资源对象值不能为空")  String resourceValue){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(monitorTreeService.searchResourceObj(resourceClassId,resourceValue,1));
        return result;
    }
    
    @ExceptionHandler
    public Result dbExceptionHandler(DBException e){
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("数据库异常:"+e.getMessage());
        return result;
    }
}
