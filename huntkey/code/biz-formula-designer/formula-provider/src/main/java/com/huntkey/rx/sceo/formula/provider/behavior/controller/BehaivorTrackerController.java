package com.huntkey.rx.sceo.formula.provider.behavior.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import com.huntkey.rx.sceo.formula.provider.behavior.service.BehaivorTrackerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lulx on 2017/6/17 0017.
 */
@RestController
@RequestMapping("/behaivorTracker")
public class BehaivorTrackerController {

    private static Logger log = LoggerFactory.getLogger(BehaivorTrackerController.class);

    @Autowired
    private BehaivorTrackerService behaivorTrackerService;

    /**
     *@desc 加载常用函数
     *@pars [userId] 用户id
     *@date 2017/6/22 0022 下午 4:03 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/loadCommonlyFormulas/{userId}", method = RequestMethod.GET)
    public Result loadCommonlyFormulas(@PathVariable(value = "userId")String userId){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String,Object> map = null;
        try {
            if(StringUtil.isNullOrEmpty(userId)){
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("userId不能为空");
                return result;
            }
            map = behaivorTrackerService.loadCommonlyFormulas(userId);
            result.setData(map);
        } catch (Exception e) {
            log.error("加载常用函数出错",e);
            throw new RuntimeException("加载常用函数出错",e);
        }
        return result;
    }
    /**
     *@desc 更新常用函数接口
     *@pars [userId, funcName] 用户ID 函数名
     *@date 2017/6/22 0022 下午 4:35 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/updateCommonlyFormulas/{userId}/{funcName}", method = RequestMethod.POST)
    public Result updateCommonlyFormulas(@PathVariable(value = "userId")String userId, @PathVariable(value = "funcName") String funcName){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String,Object> map = null;
        try {
            FunctionHandlerBuildIn functionHelper = new FunctionHandlerBuildIn();
            Map<String, Integer> funcMap =  functionHelper.getStaticMappingsUserBehavior();
            if(!funcMap.containsKey(funcName)){
                result.setErrMsg("错误的函数名");
                result.setRetCode(Result.RECODE_ERROR);
                return result;
            }
            map = behaivorTrackerService.getUserBehaviorFromRedis(userId);

            if(StringUtil.isNullOrEmpty(map)) {
                //数据库查询
                map = behaivorTrackerService.loadCommonlyFormulasforDb(userId);
            }

            if(StringUtil.isNullOrEmpty(map)) {
                //随机取出
                map = behaivorTrackerService.initUserBehavior();
            }

            // 更新Redis和数据库数据
            behaivorTrackerService.updateTdfUserBehaviorToRedisAndDb(map,userId,funcName);

        } catch (Exception e) {
            log.error("更新常用函数出错",e);
            throw new RuntimeException("更新常用函数出错",e);
        }
        return result;
    }
}
