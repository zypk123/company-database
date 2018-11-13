package com.hunkey.controller;

import com.alibaba.fastjson.JSON;
import com.hunkey.service.JobPositionService;
import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Created by lulx on 2017/10/18 0018 下午 1:47
 */
@RestController
@RequestMapping(value = "/jobPosition")
public class JobPositionController {

    @Autowired
    private JobPositionService jobPositionService;

    @RequestMapping(value = "/loadJobPosition", method = RequestMethod.POST)
    public Result loadJobPosition(@RequestBody String params,HttpServletRequest request){
        Map map = JSON.parseObject(params, Map.class);
        Result result =  jobPositionService.loadJobPosition(map,request);
        return result;
    }

    @RequestMapping(value = "/reverseCall", method = RequestMethod.POST)
    public Result reverseCall(@RequestBody String params){
        Result result =  jobPositionService.reverseCall(params);
        return result;
    }

    @RequestMapping(value = "/creatSession", method = RequestMethod.POST)
    public Result creatSession(@RequestBody String params){
        Map map = JSON.parseObject(params, Map.class);
        Result result =  jobPositionService.creatSession(map);
        return result;
    }
    @RequestMapping(value = "/execAuto", method = RequestMethod.GET)
    public Result execAuto(@RequestParam String params){
        Result result =  jobPositionService.execAuto(params);
        return result;
    }

    @RequestMapping(value = "/execAsync", method = RequestMethod.GET)
    public Result execAsync(@RequestParam String params){
        Result result =  jobPositionService.execAsync(params);
        return result;
    }

    @RequestMapping(value = "/execSync", method = RequestMethod.GET)
    public Result execSync(@RequestParam String params){
        Result result =  jobPositionService.execSync(params);
        return result;
    }

    @RequestMapping(value = "/execReverseCall", method = RequestMethod.GET)
    public Result execReverseCall(@RequestParam String params){
        Result result =  jobPositionService.execReverseCall(params);
        return result;
    }
}
