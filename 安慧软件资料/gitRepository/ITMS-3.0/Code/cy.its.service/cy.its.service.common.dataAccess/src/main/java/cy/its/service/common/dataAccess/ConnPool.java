package cy.its.service.common.dataAccess;

import java.sql.Connection;
import java.sql.SQLException;

import cy.its.service.common.config.ITSConfig;
import cy.its.service.common.dataAccess.customDataSource.MyConnDataPool;

class ConnPool {
	static IConnDataSource dataSource;
	static {
		dataSource = new MyConnDataPool(
				ITSConfig.findValue("jdbc_url"), 
				ITSConfig.findValue("jdbc_user"),
				ITSConfig.findValue("jdbc_pwd"));

	}

	static Connection getConnection() throws SQLException {
		return dataSource.getConnection();
	}
}
