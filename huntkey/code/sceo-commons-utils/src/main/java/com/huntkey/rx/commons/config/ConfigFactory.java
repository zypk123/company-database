package com.huntkey.rx.commons.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.XMLConfiguration;

/**
 * 
 * @ClassName: ConfigFactory
 * @Description:配置接口对象
 * @author: zhangyu
 * @date: 2017年4月21日下午4:46:39
 *
 */
public class ConfigFactory {

	private static final String CONFIG_FILE_DEFAULT_PATH = "./conf/config.xml";
	private static XMLConfiguration config = null;
	private static File path;

	// 方便在系统启动时候
	public static void init(String configFilePath) {
		// 如果未配置获取默认值
		if (configFilePath == null) {
			configFilePath = CONFIG_FILE_DEFAULT_PATH;
		}
		try {
			File p = new File(configFilePath);
			config = new XMLConfiguration();
			config.setDelimiterParsingDisabled(true);
			config.setAttributeSplittingDisabled(true);
			// 先禁止delimiter,再加载才能生效
			config.load(p);
			config.setFile(p);
			path = p.getParentFile();
		} catch (ConfigurationException e) {
			e.printStackTrace();
			System.out.println("Fatal:Create Config Object Error!!!");
			System.exit(1);
		}
	}

	public static File getPath() {
		return path;
	}

	// 不允许外部实例化
	private ConfigFactory() {
		
	}

	/**
	 * 获取配置的字符串值
	 * 
	 * @param configXPath
	 *            配置项路径
	 * @return
	 */
	public static String getString(String configXPath) {
		return config.getString(configXPath, null);
	}

	/**
	 * 返回属性和内容的字符串列表
	 * 
	 * @param path
	 * @return
	 * @author yanfeng
	 * @date 2013-12-2
	 */
	public static List<String[]> getList(String path, String[] attrs) {
		int i = 0;
		List<String[]> ret = new ArrayList<String[]>();
		String attrVal = null;
		do {
			List<String> attrList = new ArrayList<String>();
			for (String attr : attrs) {// 先加属性值
				attrVal = ConfigFactory.getString(path + "(" + i + ")[@" + attr + "]");
				attrList.add(attrVal);
			}
			// 最后加content
			String content = ConfigFactory.getString(path + "(" + i + ")");
			attrList.add(content);
			if (attrVal != null) {
				String[] vals = attrList.toArray(new String[0]);
				ret.add(vals);
			}
			i++;
		} while (attrVal != null);
		return ret;
	}

	/**
	 * 获取配置的字符串值
	 * 
	 * @param configXPath
	 *            配置项路径
	 * @param defaultValue
	 *            配置项的默认值
	 * @return
	 */
	public static String getString(String configXPath, String defaultValue) {
		return config.getString(configXPath, defaultValue);
	}

	/**
	 * 获取配置的整数值
	 * 
	 * @param configXPath
	 *            配置项路径
	 * @return
	 */
	public static int getInt(String configXPath) {
		return config.getInt(configXPath);
	}

	/**
	 * 获取float型类型参数
	 * 
	 * @param configXPath
	 * @param defaultValue
	 * @return
	 */
	public static float getFloat(String configXPath, float defaultValue) {
		return config.getFloat(configXPath, defaultValue);
	}

	/**
	 * 获取配置的整数值
	 * 
	 * @param configXPath
	 *            配置项路径
	 * @param defaultValue
	 *            配置项的默认值
	 * @return
	 */
	public static int getInt(String configXPath, int defaultValue) {
		return config.getInt(configXPath, defaultValue);
	}

	public static long getLong(String configXPath, long defaultValue) {
		return config.getLong(configXPath, defaultValue);
	}

	public static String getAttr(String configXPath, String attrName) {
		return config.getString(configXPath + "[@" + attrName + "]");
	}

	/**
	 * 获取配置的boolean值
	 * 
	 * @param configXPath
	 *            配置项路径
	 * @return
	 */
	public static boolean getBoolean(String configXPath) {
		return config.getBoolean(configXPath);
	}

	/**
	 * 获取配置的boolean值
	 * 
	 * @param configXPath
	 *            配置项路径
	 * @param defaultValue
	 *            配置项的默认值
	 * @return
	 */
	public static boolean getBoolean(String configXPath, boolean defaultValue) {
		return config.getBoolean(configXPath, defaultValue);
	}

	public static void main(String[] args) {
		System.out.println(XMLConfiguration.getDefaultListDelimiter());
	}

	/**
	 * 获取配置的字符串值
	 * 
	 * @param configXPath
	 *            配置项路径
	 * @return
	 */
	public static void setString(String configXPath, String value) {
		config.setProperty(configXPath, value);
	}

	/**
	 * 获取配置的整数值
	 * 
	 * @param configXPath
	 *            配置项路径
	 * @return
	 */
	public static void setInt(String configXPath, int value) {
		config.setProperty(configXPath, value);
	}

	/**
	 * 获取float型类型参数
	 * 
	 * @param configXPath
	 * @param defaultValue
	 * @return
	 */
	public static void setFloat(String configXPath, float defaultValue) {
		config.setProperty(configXPath, defaultValue);
	}

	/**
	 * 设置float型类型参数
	 * 
	 * @param configXPath
	 * @param defaultValue
	 * @return
	 */
	public static void setLong(String configXPath, long defaultValue) {
		config.setProperty(configXPath, defaultValue);
	}

	/**
	 * 获取配置的boolean值
	 * 
	 * @param configXPath
	 *            配置项路径
	 * @param defaultValue
	 *            配置项的默认值
	 * @return
	 */
	public static void setBoolean(String configXPath, boolean defaultValue) {
		config.setProperty(configXPath, defaultValue);
	}

	/**
	 * 获取配置的List值
	 * 
	 * @param configXPath
	 *            配置项路径
	 * @return
	 */
	public static void setList(String configXPath, List<String> list) {
		config.setProperty(configXPath, list);
	}

	/**
	 * 删除指定配置项
	 * 
	 * @param key
	 */
	public static void remove(String key) {
		config.clearTree(key);
	}

	/**
	 * 
	 */
	public static void save() {
		try {
			config.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// 获取某个父node下面的所有字node的path
	public static Set<String> getChildPath(String parentPath) {
		int parts = parentPath.split("\\.").length;
		Set<String> ret = new HashSet<String>();
		Iterator<String> it = config.getKeys(parentPath);
		for (; it.hasNext();) {
			String path = (String) it.next();
			// System.out.println(path);
			String[] paths = path.split("\\.");
			if (!path.contains("[") && (paths.length > parts)) {// 不包含更深的子节点和属性path
				StringBuilder cPath = new StringBuilder();
				for (int i = 0; i < parts + 1; i++) {
					cPath.append(paths[i] + ".");
				}
				ret.add(cPath.deleteCharAt(cPath.length() - 1).toString());
			}
		}
		return ret;
	}

}
