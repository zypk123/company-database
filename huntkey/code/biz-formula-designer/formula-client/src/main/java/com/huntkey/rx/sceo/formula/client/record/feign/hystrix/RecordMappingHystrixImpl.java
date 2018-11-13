package com.huntkey.rx.sceo.formula.client.record.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.record.feign.RecordMappingService;
import com.huntkey.rx.sceo.formula.common.model.SourceSystemRecordMapping;
import org.springframework.stereotype.Component;

/**
 * 源系统操作记录映射Hystrix
 *
 * @author nidongx
 * @create 2017-07-21 16:51
 **/
@Component
public class RecordMappingHystrixImpl implements RecordMappingService {

    @Override
    public Result queryFormularDec(String sourceMappingId, String sourceMappingType) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result addRecordMapping(SourceSystemRecordMapping sourceSystemRecordMapping) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result updateRecordMapping(SourceSystemRecordMapping sourceSystemRecordMapping) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result queryRecord(String sourceMappingId, String sourceMappingType) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }
}
