package com.huntkey.rx.modeler.client.exception;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.common.util.ValidatorResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.Set;

/**
 * Created by licj on 2017/7/12.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    private static Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 400 - Bad Request 缺少请求参数
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public Result handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        log.error("required parameter is not present", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        result.setErrMsg("required_parameter_is_not_present");
        return result;
    }

    /**
     * 400 - Bad Request 参数解析失败
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public Result handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
        log.error("could not read json", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        result.setErrMsg("could_not_read_json");
        return result;
    }

    /**
     * 400 - Bad Request 参数验证失败
     */
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
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
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
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
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
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
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public Result handleValidationException(ValidationException e) {
        log.error("parameter validation failure", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        result.setErrMsg("validation_exception");
        return result;
    }

    /**
     * 405 - Method Not Allowed 不支持当前请求方法
     */
    //@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        log.error("request method not supported", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("request_method_not_supported");
        return result;
    }

    /**
     * 415 - Unsupported Media Type 不支持当前媒体类型
     */
    //@ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public Result handleHttpMediaTypeNotSupportedException(Exception e) {
        log.error("content type not supported", e);
        Result result = new Result();
        result.setRetCode(Result.RECODE_ERROR);
        result.setErrMsg("content_type_not_supported");
        return result;
    }
}
