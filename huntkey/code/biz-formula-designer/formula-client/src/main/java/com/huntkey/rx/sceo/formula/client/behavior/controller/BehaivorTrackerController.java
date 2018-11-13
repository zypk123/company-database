package com.huntkey.rx.sceo.formula.client.behavior.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.behavior.feign.BehaivorTrackerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author lulx on 2017/6/14 0014.
 *  用户公式行为跟踪
 */
@RestController
@RequestMapping("/behaivorTracker")
public class BehaivorTrackerController {

    private static Logger log = LoggerFactory.getLogger(BehaivorTrackerController.class);

    @Autowired
    private BehaivorTrackerService behaivorTrackerService;

    /**
     *@desc 加载常用函数接口
     *@pars [userId] 用户ID
     *@date 2017/6/22 0022 下午 3:28 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/loadCommonlyFormulas/{userId}", method = RequestMethod.GET)
    public Result loadCommonlyFormulas(@PathVariable(value = "userId")String userId){
        Result result = null;
        try {
            result = behaivorTrackerService.loadCommonlyFormulas(userId);
        } catch (Exception e) {
            log.error("加载常用函数接口出错",e);
            throw new RuntimeException("加载常用函数接口出错",e);
        }
        return result;
    }

    /**
     *@desc 更新常用函数接口
     *@pars [userId, funcName] 用户ID 函数名
     *@date 2017/6/22 0022 下午 3:33 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/updateCommonlyFormulas/{userId}/{funcName}", method = RequestMethod.POST)
    public Result updateCommonlyFormulas(@PathVariable(value = "userId")String userId, @PathVariable(value = "funcName") String funcName){
        Result result = null;
        try {
            result = behaivorTrackerService.updateCommonlyFormulas(userId,funcName);
        } catch (Exception e) {
            log.error("更新常用函数接口出错",e);
            throw new RuntimeException("更新常用函数接口出错",e);
        }
        return result;
    }
}
