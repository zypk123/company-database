package cy.its.service.common.rabbitmqClient;

import java.util.List;
import java.util.function.Consumer;

import cy.its.service.common.rabbitmqClient.core.Server;

/**
 * MQ交互入口类
 * 
 * @author STJ
 *
 */
public class MQGateWay {

	static Server server = null;

	/**
	 * 初始化与MQ的连接
	 * 
	 * @param mqAddress
	 *            MQ地址
	 * @param bindRelations
	 *            MQ交换机和队列绑定关系
	 * @throws Exception
	 */
	public static void init(MQAddress mqAddress, List<BindRelation> bindRelations) throws Exception {
		if (server == null) {
			server = new Server(mqAddress, bindRelations);
			server.start();
		}
	}

	/**
	 * 初始化与MQ的连接
	 * 
	 * @param mqAddress
	 * @param bindRelations
	 * @param infoLog
	 * @param errorLog
	 * @throws Exception
	 */
	public static void init(MQAddress mqAddress, List<BindRelation> bindRelations, Consumer<String> infoLog,
			Consumer<String> errorLog) throws Exception {
		Logs.regLog(infoLog, errorLog);
		init(mqAddress, bindRelations);
	}

	/**
	 * 初始化与MQ的连接(同步)
	 * 
	 * @param mqAddress
	 * @param bindRelations
	 * @param infoLog
	 * @param errorLog
	 * @throws Exception
	 */
	public static void synInit(MQAddress mqAddress, List<BindRelation> bindRelations, Consumer<String> infoLog,
			Consumer<String> errorLog) throws Exception {
		Logs.regLog(infoLog, errorLog);
		if (server == null) {
			server = new Server(mqAddress, bindRelations);
			server.synStart();
		}
	}

	/**
	 * 终止与MQ的连接
	 * 
	 * @throws Exception
	 */
	public static void stop() throws Exception {
		if (server != null) {
			server.stop();
		}
	}

	/**
	 * 向MQ发布消息
	 * 
	 * @param routingKey
	 *            发布时的消息关键字
	 * @param message
	 *            发布的消息文本
	 * @return 发布是否成功, true:发布成功; false:发布失败;
	 */
	public static Boolean publish(String routingKey, String message) {
		if (server != null) {
			return server.publish(routingKey, message);
		}
		return false;
	}
}
