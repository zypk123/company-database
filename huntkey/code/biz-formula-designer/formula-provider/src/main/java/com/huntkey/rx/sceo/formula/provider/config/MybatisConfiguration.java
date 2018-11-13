package com.huntkey.rx.sceo.formula.provider.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.sql.SQLException;

/**
 * @author chenfei on 2017/5/15.
 */
@Configuration(value = "MybatisConfiguration")
@MapperScan(basePackages = {"com.huntkey.rx.sceo.formula.provider.*.*.dao", "com.huntkey.rx.sceo.formula.provider.*.dao"})
public class MybatisConfiguration {

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
}
