package com.huntkey.rx.sceo.formula.client.function.definefunc.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.function.definefunc.feign.DefineFunctionService;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author nidongx on 2017/7/26.
 */
@Component
public class DefineFunctionHystrixImpl implements DefineFunctionService {

    public Result commonDegrade() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result queryDefineFunction(String classifyId, String fundName, String fundState, String sortName, String sortOrder, int pageNum, int pageSize) {

        return commonDegrade();
    }

    @Override
    public Result createCustomizeFunction(MultipartFile file, String fundFunName, String fundState, String fundFunDesc,String fundModifyRemarks, String fundFunClassifyId, String fundFunClassifyCode) throws Exception {

        return commonDegrade();
    }

    @Override
    public Result getCompileFunByCompileId(String funcId) {

        return commonDegrade();
    }

    @Override
    public Result getDefineFunByDefineId(String funcId) {

        return commonDegrade();
    }

    @Override
    public Result updateCustomizeFunction(MultipartFile file, String fundId, String fundFunName, String fundState, String fundFunDesc, String fundFunClassifyId, String fundFunClassifyCode) throws Exception {

        return commonDegrade();
    }

    @Override
    public Result deleteCustomizeFunction(String funcId) {

        return commonDegrade();
    }

    @Override
    public Result getFunctionDefinitionByClassifyId(String classifyId) {

        return commonDegrade();
    }

    @Override
    public Result getFunctionDescriberById(String funcId) {

        return commonDegrade();
    }
}
