package com.huntkey.rx.sceo.orm.config;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by zhanglb on 2017/6/26.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        /*
        * DynamicDataSourceContextHolder代码中使用setDataSourceType
        * 设置当前的数据源，在路由类中使用getDataSourceType进行获取，
        *  交给AbstractRoutingDataSource进行注入使用。
        */
        return DynamicDataSourceContextHolder.getDataSourceType();
    }
}
