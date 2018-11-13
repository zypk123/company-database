package com.huntkey.rx.sceo.formula.client.property.feign.hystrix;

import com.huntkey.rx.sceo.formula.client.property.feign.PropertyLimitSettingService;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.TplPropertyLimit;
import com.huntkey.rx.sceo.formula.common.model.vo.PropLimitCndtVo;
import org.springframework.stereotype.Component;

import com.huntkey.rx.commons.utils.rest.Result;

import java.util.List;

/**
 * 属性限值Hystrix
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@Component
public class PropertyLimitSettingHystrixImpl implements PropertyLimitSettingService {
    @Override
    public Result queryAllConditions(String prprId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }


    @Override
    public Result saveCondition(TplCondition tplCondition) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result updateCondition(TplCondition tplCondition) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result removeCondition(String cndtId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }


    @Override
    public Result savePropLimit(TplPropertyLimit tplPropertyLimit) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result updatePropLimit(TplPropertyLimit tplPropertyLimit) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getPropLimitById(String prprId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result saveOrUpdateLimitAndConditions(PropLimitCndtVo vo) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result queryLimitAndConditions(String prprId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result queryFormulasAndPropertyLimits(List<String> prprIds, List<String> formulaIds) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result deletePropLimit(String prprId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
