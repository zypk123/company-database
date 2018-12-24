package cy.its.service.imageQuery.ice;

import cy.its.service.common.StringUtil;

public class ExceptionConverter {
	final static String ALERT_MSG_TIMEOUT = "连接监控服务器超时!";
	final static String ALERT_MSG_CONNFAILED = "因网络故障无法连接监控服务器!";
	final static String ALERT_MSG_CONNREFUSED = "监控服务器拒绝连接!";
	final static String ALERT_MSG_OPTERROR = "监控服务器端处理出错!";
	// final static String ALERT_MSG_DEVCOMMERROR = "%s时监控服务器连接前端设备失败!";
	// final static String ALERT_MSG_DBERROR = "%s时监控服务器端数据库操作失败!";
	// final static String ALERT_MSG_USERERROR = "%s时监控服务器端出错!原因:%s";
	// final static String ALERT_MSG_FAILED = "%s失败!";

	public static String convert(Exception e) {
		if (e instanceof Ice.ConnectTimeoutException || e instanceof Ice.TimeoutException) {
			// 连接超时
			return String.format(ALERT_MSG_TIMEOUT);
		} else if (e instanceof Ice.ConnectFailedException) {
			// 监控服务器连接失败
			return String.format(ALERT_MSG_CONNFAILED);
		} else if (e instanceof Ice.ConnectionRefusedException) {
			// 监控服务器拒绝连接
			return String.format(ALERT_MSG_CONNREFUSED);
		} else if (e instanceof Ice.UnknownException) {
			// 监控服务器端处理出错
			return String.format(ALERT_MSG_OPTERROR);
		} else {
			return StringUtil.EMPTY_STR;
		}
	}
}
