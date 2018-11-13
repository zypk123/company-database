package com.huntkey.rx.sceo.common.service.client.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.client.feign.FormService;
import com.huntkey.rx.sceo.common.service.common.model.Form;
import org.springframework.stereotype.Component;

/**
 * @author zhangyu
 * @create 2017-10-17 15:49
 **/
@Component
public class FormServiceHystrix implements FormService {

    @Override
    public Result getFormTables(String db) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("调用服务降级处理逻辑！");
        return result;
    }

    @Override
    public Result getFormTableColumn(String db, String table) {
        return null;
    }

    @Override
    public Result listByType(Form.FormType type) {
        return null;
    }

    @Override
    public Result get(String id) {
        return null;
    }

    @Override
    public Result save(Form form) {
        return null;
    }

    @Override
    public Result formDataSubmit(String formId, String data) {
        return null;
    }
}
