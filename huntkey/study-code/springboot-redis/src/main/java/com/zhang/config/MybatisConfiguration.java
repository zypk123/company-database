package com.zhang.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author zhangyu
 */
@Configuration(value = "MybatisConfiguration")
@MapperScan(basePackages = {"com.zhang.mapper"})
public class MybatisConfiguration {

    @Value("${spring.datasource.driver-class-name}")
    private String driveClassName;

    @Value("${spring.datasource.url}")
    private String jdbcUrl;

    @Value("${spring.datasource.username}")
    private String jdbcUsername;

    @Value("${spring.datasource.password}")
    private String jdbcPassword;

    @Bean
    public DataSource dataSource() {

        System.out.println("------------" + this.jdbcUrl + "---------------------");

        DruidDataSource datasource = new DruidDataSource();
        datasource.setDriverClassName(this.driveClassName);
        datasource.setUrl(this.jdbcUrl);
        datasource.setUsername(this.jdbcUsername);
        datasource.setPassword(this.jdbcPassword);
        try {
            datasource.setFilters("stat,wall");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return datasource;
    }

}
