package cy.its.service.common.dataAccess.customDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Timer;

import cy.its.service.common.dataAccess.IConnDataSource;

public class MyConnDataPool implements IConnDataSource{ 
	private int overTimeSpan = 60000;
	private Timer timer;
	MyConnManager connMgr;

	public MyConnDataPool(String url, String user, String passWord) {
		connMgr = new MyConnManager(url, user, passWord, overTimeSpan);
		timer = new Timer(true);
		timer.schedule(connMgr, 60000, 60000);
	}
 
	@Override
	public Connection getConnection() throws SQLException {
		return connMgr.getConnection();
	}
}