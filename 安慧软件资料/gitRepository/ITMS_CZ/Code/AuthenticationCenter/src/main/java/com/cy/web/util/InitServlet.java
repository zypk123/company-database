package com.cy.web.util;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.cy.cache.service.CacheServiceI;
import com.cy.web.GlobalProerty;

import cy.its.common.duplex.rabbitMq.MQEntrance;
import cy.its.common.duplex.rabbitMq.RabbitMqProperties;
import cy.its.platform.common.utils.OrderedProperties;
import cy.its.platform.common.utils.RedisPoolUtil;

/**
 * @author lilei 工程启动初始化所有参数 目前之动态修改缓存的存放路径
 */
public class InitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7494197526457091687L;

	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	private static String dataDictionaryPath;

	private static String redisPath;

	private static String rabbitMqPath;

	private String globalPath = "";

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String prefix = config.getServletContext().getRealPath("/");

		if (FILE_SEPARATOR.equals("\\")) {
			// 获取内容服务器配置文件的路径
			globalPath = prefix + "WEB-INF\\classes\\global.properties";
			dataDictionaryPath = prefix + "WEB-INF\\classes\\dataDictionary.xml";
			redisPath = prefix + "WEB-INF\\classes\\redis.properties";
			rabbitMqPath = prefix + "WEB-INF\\classes\\rabbitmq.properties";
		} else if (FILE_SEPARATOR.equals("/")) {
			globalPath = prefix + "WEB-INF/classes/global.properties";
			dataDictionaryPath = prefix + "WEB-INF/classes/dataDictionary.xml";
			redisPath = prefix + "WEB-INF/classes/redis.properties";
			rabbitMqPath = prefix + "WEB-INF/classes/rabbitmq.properties";
		}
		System.out.println("项目路径为：" + prefix);
		// System.out.println("硬盘缓存路径为："+prefix);
		// chanageJcsIdxPath(); 删除jcs 缓存 采用redis方式
		OrderedProperties prop = getProperties();
		setProperties(prop, prefix);
		System.out.println("系统属性初始化完成");

		// 初始化redis连接池
		initRedis();

		WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(this.getServletContext());
		CacheServiceI cacheServiceImpl = (CacheServiceI) wac.getBean("cacheServiceImpl");
		cacheServiceImpl.initServicesByRoleCode();
		cacheServiceImpl.initKeyList();
		// 初始化redis连接池
		initRabbitMq();
	}

	private void initRabbitMq() {
		Executors.newFixedThreadPool(1).submit(() -> {
			OrderedProperties prop = new OrderedProperties();
			Map<String, String> map = new HashMap<String, String>();
			try {
				FileInputStream fis = new FileInputStream(rabbitMqPath);
				prop.load(fis);
				fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			Class[] paramTypes = { String.class, String.class, String.class, String.class, String.class, Map.class };
			Object[] params = { prop.getProperty("mq.ip"), prop.getProperty("mq.user"), prop.getProperty("mq.pwd"),
					prop.getProperty("mq.dataCenter"), prop.getProperty("mq.exchangeType"), map };
			Set<String> keys = prop.stringPropertyNames();

			for (String key : getNewKey(keys)) {
				String value = prop.getProperty(key);
				map.put(key, value);
			}
			Constructor con;
			Constructor con2;
			try {
				con = RabbitMqProperties.class.getDeclaredConstructor(paramTypes);
				con.setAccessible(true);
				con.newInstance(params);
				// 实例化对象
				con2 = MQEntrance.class.getDeclaredConstructor();
				con2.setAccessible(true);
				con2.newInstance(null);
			} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
					| IllegalArgumentException | InvocationTargetException e) {
				System.out.println("rabbitMq初始化失败！");
				e.printStackTrace();
			}
			sendMqMessage();
		});

	}

	private void sendMqMessage() {
		// 初始化设备状态数据 add chenzhy
		cy.its.common.duplex.mapData.DeviceStatusMapData.sendMqMessage();
		// 初始化服务器状态数据 add chenzhy
		cy.its.common.duplex.mapData.ServerStatusMapData.sendMqMessage();
	}

	private Set<String> getNewKey(Set<String> keys) {
		// 删除KEY
		keys.remove("mq.ip");
		keys.remove("mq.user");
		keys.remove("mq.pwd");
		keys.remove("mq.dataCenter");
		keys.remove("mq.exchangeType");
		return keys;
	}

	/**
	 * @Title: initRedis @Description: redis参数初始化 @param 设定文件 @return void
	 * 返回类型 @throws
	 */
	private void initRedis() {
		Constructor c0;
		try {
			c0 = RedisPoolUtil.class.getDeclaredConstructor(new Class[] { String.class, String.class });
			c0.setAccessible(true);
			c0.newInstance(new Object[] { "", redisPath });
		} catch (Exception e) {
			System.out.println("初始化redis出现异常");
			e.printStackTrace();
		}
		System.out.println("初始化redis完成");
	}

	/**
	 * @param prop
	 * @param prefix
	 *            设置系统全局变量
	 */
	private void setProperties(OrderedProperties prop, String prefix) {
		Class[] paramTypes = { String.class, String.class, String.class, String.class, String.class, String.class,
				String.class };
		String[] params = { prefix, prop.getProperty("isDistributed"), prop.getProperty("model"),
				prop.getProperty("service_ip"), prop.getProperty("validate_ip"), prop.getProperty("servicePName"),
				prop.getProperty("authorityPName") };
		Constructor con;
		try {
			con = GlobalProerty.class.getDeclaredConstructor(paramTypes);
			con.setAccessible(true);
			con.newInstance(params);
		} catch (NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException
				| IllegalArgumentException | InvocationTargetException e) {
			System.out.println("初始化全局变量失败！");
			e.printStackTrace();
		}

	}

	/**
	 * 读取配置文件 为项目分配初始化信息 项目路径信息 系统用户为ADMIN 目前模式信息（开发、生产） 是否分布式
	 */
	private OrderedProperties getProperties() {
		OrderedProperties prop = new OrderedProperties();
		try {
			FileInputStream fis = new FileInputStream(globalPath);
			prop.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}
