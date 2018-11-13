package com.zhang.service.impl;

import com.zhang.dao.StudentDao;
import com.zhang.entity.Student;
import com.zhang.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * service实现类
 *
 * @author zhangyu
 * @create 2017-07-18 16:41
 **/
@Service
@Transactional(readOnly = true)
public class StudentServiceImpl implements StudentService {

    @Autowired
    StudentDao studentDao;

    @Override
    public List<Student> getAll() throws Exception {
        return studentDao.findAll();
    }

}
