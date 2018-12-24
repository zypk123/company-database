package cy.its.web;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.sql.DataSource;

import cy.its.com.bus.EventBus;
import cy.its.com.util.Config;
import cy.its.device.repository.SysRepository;
import cy.its.platform.common.utils.OrderedProperties;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.platform.common.utils.SftpUtil;
import cy.its.platform.common.utils.SpringBeanFactoryUtil;
import cy.its.service.imageQuery.cfg.dataAccess.DataSourceFactory;

/**
 * @author lilei 工程启动初始化所有参数 目前之动态修改缓存的存放路径
 */
public class InitServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7494197526457091687L;

	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	private String globalPath = "";

	private static String redisPath;

	private static String sftpPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String prefix = config.getServletContext().getRealPath("/");
		if (FILE_SEPARATOR.equals("\\")) {
			// 获取内容服务器配置文件的路径
			globalPath = prefix + "WEB-INF\\classes\\global.properties";
			redisPath = prefix + "WEB-INF\\classes\\redis.properties";
			sftpPath = prefix + "WEB-INF\\classes\\sftp.properties";
		} else if (FILE_SEPARATOR.equals("/")) {
			redisPath = prefix + "WEB-INF/classes/redis.properties";
			globalPath = prefix + "WEB-INF/classes/global.properties";
			sftpPath = prefix + "WEB-INF/classes/sftp.properties";
		}
		System.out.println("项目路径为：" + prefix);

		OrderedProperties prop = getProperties();
		prop.setProperty("prefix", prefix);
		GlobalProerty.setGlobalProerty(prop);
		System.out.println("系统属性初始化完成");
		System.out.println(GlobalProerty.getGlobalProerty().validate_ip);

		// 设置SPRING BEAN
		SpringBeanFactoryUtil.setServletContext(this.getServletContext());

		Config.init(prop);
		EventBus eventBus = EventBus.class.cast(SpringBeanFactoryUtil.getBeanFromFactory("eventBus"));

		if (eventBus != null) {
			eventBus.start();
		}

		DataSourceFactory.setDataSource((DataSource) (SpringBeanFactoryUtil.getBeanFromFactory("dataSource")));

		// 初始化redis
		initRedis();
		// 初始化sftp
		initSftp();

		initDevCertiStatus();

	}

	private void initDevCertiStatus() {
		SysRepository sysRep = SysRepository.class.cast(SpringBeanFactoryUtil.getBeanFromFactory("sysRepository"));
		sysRep.cacheCertiDevice();

	}

	/**
	 * @Title: initRedis @Description:初始化redis信息 @param 设定文件 @return void
	 *         返回类型 @throws
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
	 * @Title: initRedis @Description:初始化redis信息 @param 设定文件 @return void
	 *         返回类型 @throws
	 */
	private void initSftp() {
		Constructor c0;
		try {
			c0 = SftpUtil.class.getDeclaredConstructor(new Class[] { String.class });
			c0.setAccessible(true);
			c0.newInstance(new Object[] { sftpPath });
		} catch (Exception e) {
			System.out.println("初始化sftp出现异常");
			e.printStackTrace();
		}
		System.out.println("初始化sftp完成");
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
