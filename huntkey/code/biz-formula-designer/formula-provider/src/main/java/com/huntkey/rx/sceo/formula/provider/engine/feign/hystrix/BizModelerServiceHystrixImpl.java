package com.huntkey.rx.sceo.formula.provider.engine.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.vo.SimpleParasVo;
import com.huntkey.rx.sceo.formula.provider.engine.feign.BizModelerService;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author lulx on 2017/7/17 0017 上午 9:14
 */
@Component
public class BizModelerServiceHystrixImpl implements BizModelerService {

    @Override
    public Result getPropertyAndClass(String propertyId) {
        return getHystrixResult();
    }

    @Override
    public Result getClassAndRelateClass(String classId, String[] classIdArray) {
        return getHystrixResult();
    }

    @Override
    public Result updatePropertyFormula(SimpleParasVo simpleParasVo) {
        return getHystrixResult();
    }

    @Override
    public Result deleteRelTrigger(String id) {
        return getHystrixResult();
    }

    @Override
    public Result deleteRelCond(String id) {
        return getHystrixResult();
    }

    @Override
    public Result deleteLimitOrFormula(Map<String, Object> map) {
        return getHystrixResult();
    }

    @Override
    public Result getClassById(String id) {
        return getHystrixResult();
    }

    @Override
    public Result getClassPropsById(String id) {
        return getHystrixResult();
    }

    @Override
    public Result getEdmProp(String edmpId) {
        return getHystrixResult();
    }

    @Override
    public Result getChooseProperties(String id) {
        return getHystrixResult();
    }


    private Result getHystrixResult() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
