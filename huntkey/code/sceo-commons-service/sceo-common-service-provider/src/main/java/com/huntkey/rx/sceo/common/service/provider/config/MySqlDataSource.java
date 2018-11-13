package com.huntkey.rx.sceo.common.service.provider.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.huntkey.rx.commons.utils.string.StringUtil;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * Created by zhaomj on 2017/4/28.
 */
@Configuration
@MapperScan(basePackages = MySqlDataSource.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MySqlDataSource {

    public static final String PACKAGE = "com.huntkey.rx.sceo.common.service.provider.dao";

    @Value("${spring.datasource.driver-class-name}")
    private String driveClassName;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.jdbcUsername}")
    private String jdbcUsername;

    @Value("${spring.datasource.jdbcPassword}")
    private String jdbcPassword;

    @Value("${spring.datasource.filters}")
    private String filters;

    @Value("${spring.datasource.maxActive}")
    private int maxActive;

    @Value("${spring.datasource.initialSize}")
    private int initialSize;

    @Value("${spring.datasource.maxWait}")
    private int maxWait;

    @Value("${spring.datasource.minIdle}")
    private int minIdle;

    @Value("${spring.datasource.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${spring.datasource.minEvictableIdleTimeMillis}")
    private int minEvictableIdleTimeMillis;

    @Value("${spring.datasource.validationQuery}")
    private String validationQuery;

    @Value("${spring.datasource.testWhileIdle}")
    private boolean testWhileIdle;

    @Value("${spring.datasource.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${spring.datasource.testOnReturn}")
    private boolean testOnReturn;

    @Bean
    public DataSource dataSource() {

        System.out.println("------------" + this.jdbcUrl + "---------------------");

        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(this.driveClassName);
        datasource.setUrl(this.jdbcUrl);
        datasource.setUsername(this.jdbcUsername);
        datasource.setPassword(this.jdbcPassword);
        datasource.setMaxActive(this.maxActive);
        datasource.setInitialSize(this.initialSize);
        datasource.setMaxWait(this.maxWait);
        datasource.setMinIdle(this.minIdle);
        datasource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
        datasource.setValidationQuery(this.validationQuery);
        datasource.setTestWhileIdle(this.testWhileIdle);
        datasource.setTestOnBorrow(this.testOnBorrow);
        datasource.setTestOnReturn(this.testOnReturn);

        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

    @Bean(name = "masterSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());
        Resource mappers[] = (new PathMatchingResourcePatternResolver()).getResources("classpath:mapper/*.xml");
        sqlSessionFactoryBean.setMapperLocations(mappers);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.huntkey.rx.sceo.common.service.common.model");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean(name = "masterTransactionManager")
    @Primary
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }

    public String getDbName() {
        if (StringUtil.isNullOrEmpty(this.jdbcUrl)) {
            return "";
        }
        int i = this.jdbcUrl.lastIndexOf("/");
        String dbName = this.jdbcUrl.substring(i + 1);
        return dbName;
    }
}
