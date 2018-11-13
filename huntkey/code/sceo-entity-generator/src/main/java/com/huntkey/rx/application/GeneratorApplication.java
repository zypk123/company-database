package com.huntkey.rx.application;

import com.huntkey.rx.framework.Application;
import com.huntkey.rx.task.*;

/**
 * 程序入口
 * 
 * @version
 * 
 * <pre>
 * Author chenxiaojun
 * </pre>
 * 
 * @since 1.
 */
public class GeneratorApplication {

    public static void main(String[] args) {
        // 程序入口
        Application application = new Application(GeneratorApplication.class.getSimpleName());
        application.parseArgs(args);
        application.setApplicationName(GeneratorApplication.class.getName());
        application.addApplicationTask(InitTask.class) // 获取数据库表以及字段相关信息
            .addApplicationTask(CombineInfoTask.class) // 基本信息封装
            .addApplicationTask(EntityTask.class) // 生成Entity
            .addApplicationTask(ConstantEntityTask.class)//生成类和表的常量类
            .addApplicationTask(ServiceEntityTask.class) // 生成ServiceEntity
            .addApplicationTask(EntityPropertyTask.class) // 生成EntityProperyTask
//            .addApplicationTask(DaoTask.class) // 生成Dao
//            .addApplicationTask(MapperTask.class) // 生成Mapper.xml
//            .addApplicationTask(VoTask.class) // 生成Vo
//            .addApplicationTask(ManagerTask.class) // 生成Manager
//            .addApplicationTask(ManagerImplTask.class) // 生成ManagerImpl
//            .addApplicationTask(CommandTask.class) // 生成Command
//            .addApplicationTask(ServiceTask.class) // 生成Service
//            .addApplicationTask(ServiceImplTask.class) // 生成ServiceImpl
//            .addApplicationTask(ServiceTestTask.class) // 生成Service单元测试类
            .work();
    }
}