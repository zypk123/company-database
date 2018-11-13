package com.huntkey.rx.modeler.provider.config;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.config.ConfigTools;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.wall.WallConfig;
import com.alibaba.druid.wall.WallFilter;
import com.huntkey.rx.commons.utils.redis.RedisClusterUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Configuration(value = "MybatisConfiguration")
@MapperScan("com.huntkey.rx.modeler.provider.dao")
public class MybatisConfiguration {

	@Value("${druid.jdbc.driveClassName}")
	private String driveClassName;

	@Value("${druid.jdbc.jdbcUrl}")
	private String jdbcUrl;

	@Value("${druid.jdbc.jdbcUsername}")
	private String jdbcUsername;

	@Value("${druid.jdbc.jdbcPassword}")
	private String jdbcPassword;

	@Value("${druid.jdbc.filters}")
	private String filters;

	@Value("${druid.jdbc.maxActive}")
	private int maxActive;

	@Value("${druid.jdbc.initialSize}")
	private int initialSize;

	@Value("${druid.jdbc.maxWait}")
	private int maxWait;

	@Value("${druid.jdbc.minIdle}")
	private int minIdle;

	@Value("${druid.jdbc.timeBetweenEvictionRunsMillis}")
	private long timeBetweenEvictionRunsMillis;

	@Value("${druid.jdbc.minEvictableIdleTimeMillis}")
	private int minEvictableIdleTimeMillis;

	@Value("${druid.jdbc.validationQuery}")
	private String validationQuery;

	@Value("${druid.jdbc.testWhileIdle}")
	private boolean testWhileIdle;

	@Value("${druid.jdbc.testOnBrowwon}")
	private boolean testOnBrowwon;

	@Value("${druid.jdbc.testOnReturn}")
	private boolean testOnReturn;

	@Value("${redis.cluster.nodes}")
	private String nodeList;

	@Value("${spring.redis.cache.commandTimeout}")
	private int commandTimeout;

	@Bean
	public DataSource dataSource() {
		
		System.out.println("------------"+this.jdbcUrl+"---------------------");
		
		DruidDataSource datasource = new DruidDataSource();
		datasource.setDriverClassName(this.driveClassName);
		datasource.setUrl(this.jdbcUrl);
		datasource.setUsername(this.jdbcUsername);
		try {
			datasource.setPassword(ConfigTools.decrypt(this.jdbcPassword));
		} catch (Exception e) {
			e.printStackTrace();
		}
		datasource.setMaxActive(this.maxActive);
		datasource.setInitialSize(this.initialSize);
		datasource.setMaxWait(this.maxWait);
		datasource.setMinIdle(this.minIdle);
		datasource.setTimeBetweenEvictionRunsMillis(this.timeBetweenEvictionRunsMillis);
		datasource.setMinEvictableIdleTimeMillis(this.minEvictableIdleTimeMillis);
		datasource.setValidationQuery(this.validationQuery);
		datasource.setTestWhileIdle(this.testWhileIdle);
		datasource.setTestOnBorrow(this.testOnBrowwon);
		datasource.setTestOnReturn(this.testOnReturn);
		List<Filter> filterList=new ArrayList<>();
		filterList.add(wallFilter());
		datasource.setProxyFilters(filterList);
		try {
			datasource.setFilters("stat,wall");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return datasource;
	}

	@Bean
	public WallFilter wallFilter(){
		WallFilter wallFilter=new WallFilter();
		wallFilter.setConfig(wallConfig());
		return  wallFilter;
	}
	@Bean
	public WallConfig wallConfig(){
		WallConfig config =new WallConfig();
		//允许一次执行多条语句
		config.setMultiStatementAllow(true);
		return config;
	}

	@Bean
	public RedisClusterUtils redisClusterUtils(){
		RedisClusterUtils redisClusterUtils = new RedisClusterUtils(nodeList);
		return  redisClusterUtils;
	}

	@Bean
	public JedisCluster getJedisCluster(){
		String[] serverArray = nodeList.split(",");
		Set<HostAndPort> nodes = new HashSet<>();
		for (String ipPort: serverArray) {
			String[] ipPortPair = ipPort.split(":");
			nodes.add(new HostAndPort(ipPortPair[0].trim(),Integer.valueOf(ipPortPair[1].trim())));
		}
		return new JedisCluster(nodes, commandTimeout);
	}

}
