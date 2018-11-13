package com.zhang.service;

import com.zhang.entity.Student;

import java.util.List;

/**
 * 学生Service
 *
 * @author zhangyu
 * @create 2017-07-18 16:32
 **/
public interface StudentService {

    List<Student> getAll() throws Exception;

}
