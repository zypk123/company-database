package com.huntkey.rx.sceo.serviceCenter.provider.orm.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhanglb on 2017/6/26.
 */
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

    private static final Logger logger = LoggerFactory.getLogger(DynamicDataSourceRegister.class);

    private ConversionService conversionService = new DefaultConversionService();
    private PropertyValues dataSourcePropertyValues;

//    如配置文件中未指定数据源类型，使用该默认值
//    private static final Object DATASOURCE_TYPE_DEFAULT = "org.apache.tomcat.jdbc.pool.DataSource";

    // 数据源
    private DataSource defaultDataSource;
    private Map<String, DataSource> customDataSources = new HashMap<String, DataSource>();

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        Map<Object, Object> targetDataSources = new HashMap<Object, Object>();
        // 将主数据源添加到更多数据源中
        targetDataSources.put("dataSource", defaultDataSource);
        DynamicDataSourceContextHolder.dataSourceIds.add("dataSource");
        // 添加更多数据源
        targetDataSources.putAll(customDataSources);
        for (String key : customDataSources.keySet()) {
            DynamicDataSourceContextHolder.dataSourceIds.add(key);
        }

        // 创建DynamicDataSource
        GenericBeanDefinition beanDefinition = new GenericBeanDefinition();
        beanDefinition.setBeanClass(DynamicDataSource.class);
        beanDefinition.setSynthetic(true);
        MutablePropertyValues mpv = beanDefinition.getPropertyValues();
        mpv.addPropertyValue("defaultTargetDataSource", defaultDataSource);
        mpv.addPropertyValue("targetDataSources", targetDataSources);
        registry.registerBeanDefinition("dataSource", beanDefinition);

        logger.info("Dynamic DataSource Registry");
    }


    public DataSource buildDataSource(Map<String, Object> dsMap) {
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

    /**
     * 加载多数据源配置
     */
    @Override
    public void setEnvironment(Environment env) {
        initDefaultDataSource(env);
        initCustomDataSources(env);
    }

    /**
     * 初始化主数据源
     *
     * @param env
     */
    private void initDefaultDataSource(Environment env) {
        // 读取主数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "druid.jdbc.");
        Map<String, Object> dsMap = new HashMap<String, Object>();

        dsMap.put("driveClassName", propertyResolver.getProperty("driveClassName"));
        dsMap.put("jdbcUrl", propertyResolver.getProperty("jdbcUrl"));
        dsMap.put("jdbcUsername", propertyResolver.getProperty("jdbcUsername"));
        dsMap.put("jdbcPassword", propertyResolver.getProperty("jdbcPassword"));
        dsMap.put("maxActive", propertyResolver.getProperty("maxActive"));
        dsMap.put("initialSize", propertyResolver.getProperty("initialSize"));
        dsMap.put("maxWait", propertyResolver.getProperty("maxWait"));
        dsMap.put("minIdle", propertyResolver.getProperty("minIdle"));
        dsMap.put("timeBetweenEvictionRunsMillis", propertyResolver.getProperty("timeBetweenEvictionRunsMillis"));
        dsMap.put("timeBetweenEvictionRunsMillis", propertyResolver.getProperty("timeBetweenEvictionRunsMillis"));
        dsMap.put("minEvictableIdleTimeMillis", propertyResolver.getProperty("minEvictableIdleTimeMillis"));
        dsMap.put("validationQuery", propertyResolver.getProperty("validationQuery"));
        dsMap.put("testWhileIdle", propertyResolver.getProperty("testWhileIdle"));
        dsMap.put("testOnBrowwon", propertyResolver.getProperty("testOnBrowwon"));
        dsMap.put("testOnReturn", propertyResolver.getProperty("testOnReturn"));
        dsMap.put("filters", propertyResolver.getProperty("filters"));

        defaultDataSource = buildDataSource(dsMap);

        dataBinder(defaultDataSource, env);
    }

    /**
     * 为DataSource绑定更多数据
     *
     * @param dataSource
     * @param env
     */
    private void dataBinder(DataSource dataSource, Environment env) {
        RelaxedDataBinder dataBinder = new RelaxedDataBinder(dataSource);
        //dataBinder.setValidator(new LocalValidatorFactory().run(this.applicationContext));
        dataBinder.setConversionService(conversionService);
        dataBinder.setIgnoreNestedProperties(false);//false
        dataBinder.setIgnoreInvalidFields(false);//false
        dataBinder.setIgnoreUnknownFields(true);//true
        if (dataSourcePropertyValues == null) {
            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "druid.jdbc").getSubProperties(".");
            Map<String, Object> values = new HashMap<String, Object>(rpr);
            // 排除已经设置的属性
            values.remove("type");
            values.remove("driver-class-name");
            values.remove("url");
            values.remove("username");
            values.remove("password");
            dataSourcePropertyValues = new MutablePropertyValues(values);
        }
        dataBinder.bind(dataSourcePropertyValues);
    }

    /**
     * 初始化更多数据源
     *
     * @param env
     */
    private void initCustomDataSources(Environment env) {
        // 读取配置文件获取更多数据源，也可以通过defaultDataSource读取数据库获取更多数据源
        RelaxedPropertyResolver propertyResolver = new RelaxedPropertyResolver(env, "druid.");
        String dsPrefixs = propertyResolver.getProperty("names");
        for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
            Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
            DataSource ds = buildDataSource(dsMap);
            customDataSources.put(dsPrefix, ds);
            dataBinder(ds, env);
        }
    }
}
