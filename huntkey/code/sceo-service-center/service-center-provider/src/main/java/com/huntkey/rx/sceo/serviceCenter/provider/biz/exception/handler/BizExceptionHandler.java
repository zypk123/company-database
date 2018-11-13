package com.huntkey.rx.sceo.serviceCenter.provider.biz.exception.handler;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.exception.ServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Created by zhaomj on 2017/8/20.
 */
@RestControllerAdvice
public class BizExceptionHandler {
    @ExceptionHandler(ServiceException.class)
    public Result handleServiceException(ServiceException e) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("service exception：" + e.getMessage());
        return result;
    }
}
