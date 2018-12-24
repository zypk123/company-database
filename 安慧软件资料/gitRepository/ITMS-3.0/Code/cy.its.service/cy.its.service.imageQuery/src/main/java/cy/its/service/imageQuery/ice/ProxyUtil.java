package cy.its.service.imageQuery.ice;

import Ice.Communicator;
import Ice.Properties;
import Ice.Util;

class ProxyUtil {

	final static Communicator commun;

	static {
		// 获取属性
		Properties props = properties();
		// 创建通信器初始化用数据集
		Ice.InitializationData initData = new Ice.InitializationData();
		// 将属性集放到初始化用数据集里
		initData.properties = props;

		// 初始化通信器
		commun = Ice.Util.initialize(initData);
	}

	private static Properties properties() {
		String TIME_OUT = "1000";
		Properties props = Util.createProperties();
		// 设置发送消息的最大尺寸(单位：KB)
		props.setProperty("Ice.MessageSizeMax", "1024000");
		// 设置类垃圾收集器的运行频度(每隔多少秒运行一次)
		props.setProperty("Ice.GC.Interval", "120");
		// 本地监听服务端线程池最小线程数
		props.setProperty("Ice.ThreadPool.Server.Size", "1");
		// 本地监听服务端线程池最大线程数
		props.setProperty("Ice.ThreadPool.Server.SizeMax", "300");
		// 本地客户端线程池最小线程数
		props.setProperty("Ice.ThreadPool.Client.Size", "1");
		// 本地客户端线程池最大线程数
		props.setProperty("Ice.ThreadPool.Client.SizeMax", "500");
		// 超时设置
		props.setProperty("Ice.Override.ConnectTimeout", TIME_OUT);
		return props;
	}
}
