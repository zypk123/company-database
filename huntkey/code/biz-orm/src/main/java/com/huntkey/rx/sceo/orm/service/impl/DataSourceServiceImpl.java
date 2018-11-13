package com.huntkey.rx.sceo.orm.service.impl;

import com.alibaba.druid.pool.DruidDataSource;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.orm.common.model.DataSourceEntity;
import com.huntkey.rx.sceo.orm.config.DynamicDataSourceContextHolder;
import com.huntkey.rx.sceo.orm.service.DataSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

/**
 * Created by zhanglb on 2017/12/12.
 */

@Service
public class DataSourceServiceImpl implements DataSourceService {

    private static Logger logger = LoggerFactory.getLogger(DataSourceServiceImpl.class);

    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void addDynamicDataSource(DataSourceEntity dataSourceEntity) {
        //读取配置文件
        String filename = "datasource";
        ResourceBundle resource = ResourceBundle.getBundle(filename);
        logger.info("filename={}", filename);
        Map<String, Object> dsMap = new HashMap<>();
        //动态数据源的key
        String dataSourceKey = resource.getString("dataSource");
        String username = "";
        String password = "";
        String url = "";
        String port = "";
        String dbName = "";
        StringBuffer jdbcUrl = new StringBuffer("");
        if(!StringUtil.isNullOrEmpty(dataSourceEntity)){
            username = dataSourceEntity.getUsername();
            password = dataSourceEntity.getPassword();
            url = dataSourceEntity.getUrl();
            port = dataSourceEntity.getPort();
            dbName = dataSourceEntity.getDbName();
            dataSourceKey = dbName;
        }
        jdbcUrl.append("jdbc:mysql://"+url+":"+port+"/"+dbName+"?useSSL=true");
        dsMap.put("driveClassName", resource.getString("driveClassName"));
//            dsMap.put("jdbcUrl", resource.getString("jdbcUrl"));
//            dsMap.put("jdbcUsername", resource.getString("jdbcUsername"));
//            dsMap.put("jdbcPassword", resource.getString("jdbcPassword"));
        dsMap.put("jdbcUrl", jdbcUrl.toString());
        dsMap.put("jdbcUsername", username);
        dsMap.put("jdbcPassword", password);//这个密码传过来的有可能是密文，是密文需要解密处理
        dsMap.put("maxActive", resource.getString("maxActive"));
        dsMap.put("initialSize", resource.getString("initialSize"));
        dsMap.put("maxWait", resource.getString("maxWait"));
        dsMap.put("minIdle", resource.getString("minIdle"));
        dsMap.put("timeBetweenEvictionRunsMillis", resource.getString("timeBetweenEvictionRunsMillis"));
        dsMap.put("minEvictableIdleTimeMillis", resource.getString("minEvictableIdleTimeMillis"));
        dsMap.put("validationQuery", resource.getString("validationQuery"));
        dsMap.put("testWhileIdle", resource.getString("testWhileIdle"));
        dsMap.put("testOnBrowwon", resource.getString("testOnBrowwon"));
        dsMap.put("testOnReturn", resource.getString("testOnReturn"));
        dsMap.put("filters", resource.getString("filters"));
        DataSource ds = buildDataSource(dsMap);

        //dataSource放入map中
        customDataSources.put(dataSourceKey, ds);
        registerDataSourceBean(dataSourceKey, customDataSources);
    }

    //创建dataSource
    private DataSource buildDataSource(Map<String, Object> dsMap) {
        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(dsMap.get("driveClassName").toString());
        datasource.setUrl(dsMap.get("jdbcUrl").toString());
        datasource.setUsername(dsMap.get("jdbcUsername").toString());
        datasource.setPassword(dsMap.get("jdbcPassword").toString());
        datasource.setMaxActive(Integer.parseInt(dsMap.get("maxActive").toString()));
        datasource.setInitialSize(Integer.parseInt(dsMap.get("initialSize").toString()));
        datasource.setMaxWait(Integer.parseInt(dsMap.get("maxWait").toString()));
        datasource.setMinIdle(Integer.parseInt(dsMap.get("minIdle").toString()));
        datasource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(dsMap.get("timeBetweenEvictionRunsMillis").toString()));
        datasource.setMinEvictableIdleTimeMillis(Integer.parseInt(dsMap.get("minEvictableIdleTimeMillis").toString()));
        datasource.setValidationQuery(dsMap.get("validationQuery").toString());
        datasource.setTestWhileIdle(Boolean.valueOf(dsMap.get("testWhileIdle").toString()));
        datasource.setTestOnBorrow(Boolean.valueOf(dsMap.get("testOnBrowwon").toString()));
        datasource.setTestOnReturn(Boolean.valueOf(dsMap.get("testOnReturn").toString()));

        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    private void registerDataSourceBean(String dataSourceKey, Map<String, DataSource> customDataSources){

        //判断如果没有再增加新的数据源
        if (!DynamicDataSourceContextHolder.dataSourceIds.contains(dataSourceKey)) {
            ConfigurableApplicationContext context = (ConfigurableApplicationContext) applicationContext;
            //取spring的所有bean
            DefaultListableBeanFactory beanFactory = (DefaultListableBeanFactory) context.getBeanFactory();
            DynamicDataSourceContextHolder.dataSourceIds.add(dataSourceKey);
            //取dataSource bean定义
            BeanDefinition beanDefinition = beanFactory.getBeanDefinition("dataSource");
            MutablePropertyValues mutablePropertyValues = beanFactory.getBeanDefinition("dataSource").getPropertyValues();
            PropertyValue propertyValue = mutablePropertyValues.getPropertyValue("targetDataSources");
            if(!StringUtil.isNullOrEmpty(propertyValue)){
                Map<String, DataSource> map = (Map<String, DataSource>) propertyValue.getValue();
                map.putAll(customDataSources);
                mutablePropertyValues.addPropertyValue("targetDataSources", map);
                beanFactory.registerBeanDefinition("dataSource", beanDefinition);
                logger.info("----->动态加载数据源结束！");
            }
            logger.info("-----执行完成----");
        } else {
            //数据源已存在不做操作
            logger.info("-----数据源已存在----");
        }

    }

}
