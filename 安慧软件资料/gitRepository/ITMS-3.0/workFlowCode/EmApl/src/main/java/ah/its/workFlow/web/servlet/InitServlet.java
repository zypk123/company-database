package ah.its.workFlow.web.servlet;

import java.io.FileInputStream;
import java.lang.reflect.Constructor;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import ah.its.workFlow.util.DictionaryUtil;
import ah.its.workFlow.util.RedisPoolUtil;

/**
 * @author lilei 工程启动初始化所有参数 目前之动态修改缓存的存放路径
 */
public class InitServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7494197526457091687L;

	public static final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");

	/**
	 * 项目配置文件
	 */
	private String globalPath = "";
	
	/**
	 * 数据字典配置文件信息
	 */
	private static String dictionaryPath;
	
	/**
	 * redis配置文件信息
	 */
	private static String redisPath;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		String prefix = config.getServletContext().getRealPath("/");
		if (FILE_SEPARATOR.equals("\\")) {
			// 获取内容服务器配置文件的路径
			globalPath = prefix + "WEB-INF\\classes\\global.properties";
			dictionaryPath = prefix + "WEB-INF\\classes\\dataDictionary.xml";
			redisPath = prefix + "WEB-INF\\classes\\redis.properties";
		} else if (FILE_SEPARATOR.equals("/")) {
			globalPath = prefix + "WEB-INF/classes/global.properties";
			dictionaryPath = prefix + "WEB-INF/classes/dataDictionary.xml";
			redisPath = prefix + "WEB-INF/classes/redis.properties";
		}
		System.out.println("项目路径为：" + prefix);
        Properties prop = getProperties();
        prop.setProperty("prefix", prefix);
        GlobalProerty.setGlobalProerty(prop);
		//初始化REDIS
		initRedis();
		
		//数据字典初始化，初始化到REDIS缓存
		DictionaryUtil.initAll(config,dictionaryPath);
	}
	
	/** 
	* @Description: 获取配置文件信息 
	* @param @return    设定文件 
	* @return Properties    返回类型 
	* @throws 
	*/
	private Properties getProperties(){
		Properties prop = new 	Properties();
		try {
			FileInputStream fis = new FileInputStream(globalPath);
			prop.load(fis);
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
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
			c0.newInstance(new Object[] {"", redisPath });
		} catch (Exception e) {
			System.out.println("初始化redis出现异常");
			e.printStackTrace();
		}
		System.out.println("初始化redis完成");
	}

}
