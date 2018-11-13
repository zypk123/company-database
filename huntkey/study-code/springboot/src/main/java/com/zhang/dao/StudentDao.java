package com.zhang.dao;

import com.zhang.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 学生dao
 * 继承JpaRepository
 *
 * @author zhangyu
 * @create 2017-07-18 16:27
 **/
public interface StudentDao extends JpaRepository<Student, Integer> {


}
