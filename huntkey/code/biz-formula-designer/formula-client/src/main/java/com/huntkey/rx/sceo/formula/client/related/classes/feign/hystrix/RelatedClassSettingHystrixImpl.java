package com.huntkey.rx.sceo.formula.client.related.classes.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.related.classes.feign.RelatedClassSettingService;
import com.huntkey.rx.sceo.formula.common.model.TfdClassRelated;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.vo.ClassRelatedVo;
import org.springframework.stereotype.Component;

/**
 * 相关类设置Hystrix
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@Component
public class RelatedClassSettingHystrixImpl implements RelatedClassSettingService {

    @Override
    public Result loadRelatedClasses(String formulaId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result loadRelatedClassesByClssClassIdAndType(String clssClassId,Integer type) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }


    @Override
    public Result saveRrelatedClasses(TfdClassRelated tfdClassRelated) {
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
    public Result queryVariants(String varName) {
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
    public Result removeAllConditions(String[] cndtIdArr) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result queryCondisionsByClssId(String clssId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result updateRelatedClassesWithFormula(String[] classIdArr, String formulaId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result deleteClassRelated(String clssId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result initRelatedClasses(String formulaId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result initRelatedClassesByClssLinkClassId(String clssLinkClassId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result updateClassRelated(TfdClassRelated tfdClassRelated) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getClassRelatedAndConditionsByClssId(String clssId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result saveOrUpdateClassRelatedAndConditions(ClassRelatedVo classRelatedVo) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
