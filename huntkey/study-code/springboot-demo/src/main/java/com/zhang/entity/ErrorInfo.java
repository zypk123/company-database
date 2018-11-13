package com.zhang.entity;

/**
 * 异常类
 *
 * @author zhangyu
 * @create 2017-12-15 11:05
 **/
public class ErrorInfo<T> {

    public static final Integer OK = 0;
    public static final Integer ERROR = 100;

    /**
     * 消息类型
     */
    private Integer code;

    /**
     * 消息内容
     */
    private String message;

    /**
     * 请求的url
     */
    private String url;

    /**
     * 请求返回的数据
     */
    private T data;

    public static Integer getOK() {
        return OK;
    }

    public static Integer getERROR() {
        return ERROR;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}