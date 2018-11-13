package com.huntkey.rx.sceo.orm.config;


import com.huntkey.rx.sceo.orm.annotation.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * Created by zhanglb on 2017/6/26.
 */
@Aspect
@Order(-10)//保证该AOP在@Transactional之前执行
@Component

public class DynamicDataSourceAspect {

    private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceAspect.class);

    @Before("@annotation(targetDataSource)")
    public void changeDataSource(JoinPoint point, TargetDataSource targetDataSource) throws Throwable {

        //获取当前的指定的数据源;
        String dsId = targetDataSource.value();
        logger.info("targetDataSource={}", dsId);
        //如果不在我们注入的所有的数据源范围之内，那么输出警告信息，系统自动使用默认的数据源。
        if (!DynamicDataSourceContextHolder.containsDataSource(dsId)) {
            logger.info("数据源[{}]不存在，使用默认数据源[{}]", targetDataSource.value(), point.getSignature());
        } else {
            logger.info("UseDataSource : {} > {}", targetDataSource.value() + point.getSignature());
            //找到的话，那么设置到动态数据源上下文中。
            DynamicDataSourceContextHolder.setDataSourceType(targetDataSource.value());
        }

    }

    @After("@annotation(targetDataSource)")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        logger.info("RevertDataSource : {} > {}", targetDataSource.value(), point.getSignature());
        //方法执行完毕之后，销毁当前数据源信息，进行垃圾回收。
        DynamicDataSourceContextHolder.clearDataSourceType();

    }
}
