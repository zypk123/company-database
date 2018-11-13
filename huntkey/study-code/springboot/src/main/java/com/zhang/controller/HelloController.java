package com.zhang.controller;

import com.zhang.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author zhangyu
 * @create 2017-07-18 15:19
 **/
@RestController // 相当于@Controller + @ResponseBody ，接口数据全部以json输出
public class HelloController {

    @Value("${test.name}") // 该注解表示从配置文件读取配置的属性
    private String testName;


    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index() {
        return "hello world! " + testName;
    }

    @RequestMapping(value = "/getUser", method = RequestMethod.GET)
    public User getUser() {
        User user = new User();
        user.setUsername("张三");
        user.setAge(20);
        return user;
    }


}
