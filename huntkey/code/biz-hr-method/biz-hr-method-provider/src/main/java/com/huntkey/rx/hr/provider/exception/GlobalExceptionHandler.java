package com.huntkey.rx.hr.provider.exception;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.util.ValidatorResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.beanvalidation.MethodValidationPostProcessor;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * Created by clarkzhao on 2017/10/26.
 *
 * @author clarkzhao
 * @date 2017/10/26
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @Bean
    public MethodValidationPostProcessor methodValidationPostProcessor() {
        return new MethodValidationPostProcessor();
    }

    /**
     * 400 - Bad Request 参数类型错误
     */
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        log.error("parameter type error", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        result.setErrMsg("parameter type error :" + e.getMessage());
        return result;
    }

    /**
     * 400 - Bad Request 缺少请求参数
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("required parameter is not present", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        result.setErrMsg("required parameter is not present");
        return result;
    }

    /**
     * 400 - Bad Request 参数解析失败
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("could not read json", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        result.setErrMsg("could not read json");
        return result;
    }

    /**
     * 400 - Bad Request 参数验证失败
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        log.error("parameter validation failure", e);
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = ValidatorResultUtil.getMessage(bindingResult);
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        result.setErrMsg(errorMsg);
        return result;
    }

    /**
     * 400 - Bad Request 参数绑定失败
     */
    @ExceptionHandler(BindException.class)
    public Result handleBindException(BindException e) {
        log.error("parameter binding failure", e);
        BindingResult bindingResult = e.getBindingResult();
        String errorMsg = ValidatorResultUtil.getMessage(bindingResult);
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        result.setErrMsg(errorMsg);
        return result;
    }

    /**
     * 400 - Bad Request 参数验证失败
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public Result handleServiceException(ConstraintViolationException e) {
        log.error("parameter validation failure", e);
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        ConstraintViolation<?> violation = violations.iterator().next();
        String errorMsg = violation.getMessage();
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        result.setErrMsg("parameter:" + errorMsg);
        return result;
    }

    /**
     * 400 - Bad Request 参数验证失败
     */
    @ExceptionHandler(ValidationException.class)
    public Result handleValidationException(ValidationException e) {
        log.error("parameter validation failure", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        result.setErrMsg("validation exception");
        return result;
    }

    /**
     * 405 - Method Not Allowed 不支持当前请求方法
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("request method not supported", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("request method not supported");
        return result;
    }

    /**
     * 415 - Unsupported Media Type 不支持当前媒体类型
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("content type not supported", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("content type not supported");
        return result;
    }

    /**
     * 操作数据库出现异常:名称重复，外键关联等
     */
    @ExceptionHandler(DataIntegrityViolationException.class)
    public Result handleException(DataIntegrityViolationException e) {
        log.error("operating database exception", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("operating database exception");
        return result;
    }

    /**
     * 500 - Internal Server Error 通用异常
     */
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("other exceptions", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("未知服务异常，请联系管理员！");
        return result;
    }

    /**
     * 应用异常
     */
    @ExceptionHandler(ApplicationException.class)
    public Result handleException(ApplicationException e) {
        log.error("应用异常 exceptions", e);
        Result result = new Result();
        result.setRetCode(e.getCode());
        result.setErrMsg(e.getMessage());
        return result;
    }
}
