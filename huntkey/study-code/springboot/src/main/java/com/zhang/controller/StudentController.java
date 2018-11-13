package com.zhang.controller;

import com.zhang.entity.Student;
import com.zhang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyu
 * @create 2017-07-18 16:46
 **/
@RestController
public class StudentController {

    @Autowired
    StudentService studentService;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @Cacheable(value = "user-key") // 该注解表示将查询的结果存在redis中，key为"user-key"
    public List<Student> getAll() {
        List<Student> studentList = new ArrayList<>();
        try {
            System.out.println("若下面没出现“无缓存的时候调用”字样且能打印出数据表示测试成功");
            studentList = studentService.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

}
