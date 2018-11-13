package com.huntkey.rx.sceo.orm.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.orm.common.constant.Constant;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValue;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.boot.bind.RelaxedDataBinder;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.env.Environment;
import org.springframework.core.type.AnnotationMetadata;
import org.springframework.stereotype.Component;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.sql.DataSource;
import java.io.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

/**
 * Created by zhanglb on 2017/6/26.
 */
@Configuration(value = "DynamicDataSourceRegister")
@MapperScan("com.huntkey.rx.sceo.orm.dao")
@ComponentScan(basePackages = "com.huntkey.rx.sceo.orm.service")
public class DynamicDataSourceRegister implements ImportBeanDefinitionRegistrar, EnvironmentAware {

//    @Value("${redis.cluster.nodes}")
    private String nodeList = "10.3.98.153:7000,10.3.98.153:7001,10.3.98.154:7002,10.3.98.154:7003,10.3.98.155:7004,10.3.98.155:7005";

//    @Value("${redis.cache.commandTimeout}")
    private int commandTimeout = 5000;

//    @Autowired
//    private DataSource dataSource;

    @Autowired
    private ApplicationContext applicationContext;

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
//        addDynamicDataSource(env);
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
//        if (dataSourcePropertyValues == null) {
//            Map<String, Object> rpr = new RelaxedPropertyResolver(env, "druid.jdbc").getSubProperties(".");
//            Map<String, Object> values = new HashMap<String, Object>(rpr);
//            // 排除已经设置的属性
//            values.remove("type");
//            values.remove("driver-class-name");
//            values.remove("url");
//            values.remove("username");
//            values.remove("password");
//            dataSourcePropertyValues = new MutablePropertyValues(values);
//        }
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
        if(!StringUtil.isNullOrEmpty(dsPrefixs)){
            for (String dsPrefix : dsPrefixs.split(",")) {// 多个数据源
                Map<String, Object> dsMap = propertyResolver.getSubProperties(dsPrefix + ".");
                DataSource ds = buildDataSource(dsMap);
                customDataSources.put(dsPrefix, ds);
                dataBinder(ds, env);
            }
        }
    }

    public JedisCluster getJedisCluster(){
        String[] serverArray = nodeList.split(",");
        Set<HostAndPort> nodes = new HashSet<>();
        for (String ipPort: serverArray) {
            String[] ipPortPair = ipPort.split(":");
            nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));
        }
        JedisCluster jedisCluster = new JedisCluster(nodes, commandTimeout);
        return jedisCluster;
    }

    public void addDynamicDataSource(Environment env){
        Connection connection;
        List<Map<String, Object>> list = new ArrayList<>();
        try {
            logger.info("===========执行datasource===========");
            connection = defaultDataSource.getConnection();
            String sql = "SELECT ente_sceo_url, ente_dbuser, ente_dbpassword, ente_dbaddress FROM enterprise where is_del = '0'";
            ResultSet resultSet = connection.createStatement().executeQuery(sql);
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columns = metaData.getColumnCount();

            while(resultSet.next())
            {
                for(int i=1; i<=columns; i++)
                {
                    String dbName = resultSet.getString("ente_sceo_url");
                    String url = resultSet.getString("ente_dbaddress");
                    String username = resultSet.getString("ente_dbuser");
                    String password = resultSet.getString("ente_dbpassword");

                    if (!StringUtil.isNullOrEmpty(dbName)
                            && !StringUtil.isNullOrEmpty(username)
                            && !StringUtil.isNullOrEmpty(password)
                            && !StringUtil.isNullOrEmpty(url)) {
                        url = "jdbc:mysql://"+url+"/"+dbName+"?useSSL=false";
                        logger.info("----第{}个数据库：{}", i, dbName);
                        Map<String, Object> map = new HashMap<>();
                        map.put("dataSource", dbName);
                        map.put("driveClassName", "com.mysql.jdbc.Driver");
                        map.put("jdbcUrl", url);
//                        map.put("jdbcUsername", "root");
//                        map.put("jdbcPassword", "root");
                        map.put("jdbcUsername", username);
                        map.put("jdbcPassword", password);
                        map.put("maxActive", 20);
                        map.put("initialSize", 1);
                        map.put("maxWait", 60000);
                        map.put("minIdle", 3);
                        map.put("timeBetweenEvictionRunsMillis", 600000);
                        map.put("minEvictableIdleTimeMillis", 30000);
                        map.put("validationQuery", "SELECT 'x'");
                        map.put("testWhileIdle", true);
                        map.put("testOnBrowwon", false);
                        map.put("testOnReturn", false);
                        map.put("filters", "stat,wall");
                        list.add(map);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

//        String redisKey = "testdbkey";
//        Map<String, Object> map = new HashMap<>();
//        map.put("dataSource", "A000001");
//        map.put("driveClassName", "com.mysql.jdbc.Driver");
//        map.put("jdbcUrl", "jdbc:mysql://localhost:3306/test?useSSL=true");
//        map.put("jdbcUsername", "root");
//        map.put("jdbcPassword", "root");
//        map.put("maxActive", 20);
//        map.put("initialSize", 1);
//        map.put("maxWait", 60000);
//        map.put("minIdle", 3);
//        map.put("timeBetweenEvictionRunsMillis", 600000);
//        map.put("minEvictableIdleTimeMillis", 30000);
//        map.put("validationQuery", "SELECT 'x'");
//        map.put("testWhileIdle", true);
//        map.put("testOnBrowwon", false);
//        map.put("testOnReturn", false);
//        map.put("filters", "stat,wall");
//        list.add(map);
//        JedisCluster jedisCluster = getJedisCluster();
//        jedisCluster.set(redisKey.getBytes(), serialize(list));
//
//        byte[] bytes = jedisCluster.get(redisKey.getBytes());
//        List<Map<String, Object>>  redisList = (List<Map<String, Object>>) deserialize(bytes);
//        JedisCluster jedisCluster = getJedisCluster();
//        jedisCluster.setex("test123", 1000, "test123");
        for(Map<String, Object> m: list){
            DruidDataSource datasource = new DruidDataSource();
            datasource.setDriverClassName(m.get("driveClassName").toString());
            datasource.setUrl(m.get("jdbcUrl").toString());
            datasource.setUsername(m.get("jdbcUsername").toString());
            datasource.setPassword(m.get("jdbcPassword").toString());
            datasource.setMaxActive(Integer.parseInt(m.get("maxActive").toString()));
            datasource.setInitialSize(Integer.parseInt(m.get("initialSize").toString()));
            datasource.setMaxWait(Integer.parseInt(m.get("maxWait").toString()));
            datasource.setMinIdle(Integer.parseInt(m.get("minIdle").toString()));
            datasource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(m.get("timeBetweenEvictionRunsMillis").toString()));
            datasource.setMinEvictableIdleTimeMillis(Integer.parseInt(m.get("minEvictableIdleTimeMillis").toString()));
            datasource.setValidationQuery(m.get("validationQuery").toString());
            datasource.setTestWhileIdle(Boolean.valueOf(m.get("testWhileIdle").toString()));
            datasource.setTestOnBorrow(Boolean.valueOf(m.get("testOnBrowwon").toString()));
            datasource.setTestOnReturn(Boolean.valueOf(m.get("testOnReturn").toString()));
            customDataSources.put(m.get("dataSource").toString(), datasource);
            logger.info("------dataSource={}", m.get("dataSource").toString());
            dataBinder(datasource, env);
        }

    }

    public byte[] serialize(Object value) {
        if (value == null) {
            throw new NullPointerException("Can't serialize null");
        }
        byte[] rv=null;
        ByteArrayOutputStream bos = null;
        ObjectOutputStream os = null;
        try {
            bos = new ByteArrayOutputStream();
            os = new ObjectOutputStream(bos);
            os.writeObject(value);
            os.close();
            bos.close();
            rv = bos.toByteArray();
        } catch (IOException e) {
            throw new IllegalArgumentException("Non-serializable object", e);
        } finally {
            try {
                if(os!=null)os.close();
                if(bos!=null)bos.close();
            }catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return rv;
    }

    public Object deserialize(byte[] in) {
        Object rv=null;
        ByteArrayInputStream bis = null;
        ObjectInputStream is = null;
        try {
            if(in != null) {
                bis=new ByteArrayInputStream(in);
                is=new ObjectInputStream(bis);
                rv=is.readObject();
                is.close();
                bis.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                if(is!=null)is.close();
                if(bis!=null)bis.close();
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        return rv;
    }

}
