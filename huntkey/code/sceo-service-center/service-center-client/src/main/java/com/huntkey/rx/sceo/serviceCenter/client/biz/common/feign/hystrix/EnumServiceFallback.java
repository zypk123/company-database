package com.huntkey.rx.sceo.serviceCenter.client.biz.common.feign.hystrix;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.serviceCenter.client.biz.common.feign.EnumService;
import org.springframework.stereotype.Component;

/**
 * Created by clarkzhao on 2017/10/24.
 *
 * @author clarkzhao
 * @date 2017/10/24
 */
@Component
public class EnumServiceFallback implements EnumService{
    @Override
    public Result getEnumsObjects(String enumCode) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("服务不可用！");
        return result;
    }
}
