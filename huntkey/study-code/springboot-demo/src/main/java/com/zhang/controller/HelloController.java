package com.zhang.controller;

import com.zhang.exception.MyException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyu
 * @create 2017-12-14 16:04
 **/
@RestController
public class HelloController {

    @Value("${com.zhang.name}")
    private String name;

    @RequestMapping(value = "/hello")
    public String hello() {
        return "hello";
    }

    @RequestMapping(value = "/testProp")
    public String testProp() {
        return "hello:" + name;
    }

    @RequestMapping(value = "/testException")
    public void testException() throws Exception {
        throw new Exception("出现异常~");
    }

    @RequestMapping(value = "/testJsonException")
    public void testJsonException() throws Exception {
        throw new MyException("出现自定义异常~");
    }

}
