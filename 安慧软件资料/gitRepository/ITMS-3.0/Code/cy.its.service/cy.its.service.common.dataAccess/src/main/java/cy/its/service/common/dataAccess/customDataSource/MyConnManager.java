package cy.its.service.common.dataAccess.customDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.TimerTask;

import cy.its.service.common.ConstValue;

class MyConnManager extends TimerTask {
	private String url;
	private String user;
	private String passWord;
	private int overTimeSpan = 60000;
	List<MyConnection> connections = new ArrayList<MyConnection>();
	private boolean isCheckTimeOutConn = false;

	public MyConnManager(String url, String user, String passWord, int overTimeSpan) {
		this.url = url;
		this.user = user;
		this.passWord = passWord;
		this.overTimeSpan = overTimeSpan;
	}

	@Override
	public void run() {
		synchronized (this) {
			if (isCheckTimeOutConn) {
				return;
			}
			isCheckTimeOutConn = ConstValue.BOOL_TRUE;
		}

		this.checkTimeOutConn();

		synchronized (this) {
			isCheckTimeOutConn = ConstValue.BOOL_FALSE;
		}
	}

	public void checkTimeOutConn() {
		synchronized (connections) {
			if (connections.size() > ConstValue.INT_0) {
				MyConnection conn;
				for (int i = this.connections.size() - ConstValue.INT_1; i >= ConstValue.INT_0; i--) {
					conn = this.connections.get(i);
					if (conn.isOldConnection()) {
						this.connections.remove(i);
					}
				}
			}
		}
	}

	public Connection getConnection() throws SQLException {
		while (true) {
			MyConnection conn = null;
			synchronized (connections) {
				if (connections.size() > ConstValue.INT_0) {
					conn = connections.remove(ConstValue.INT_0);
				}
			}

			if (conn == null) {
				break;
			} else if (conn.isClosed() || !conn.isValid(1)) {
				continue;
			} else {
				return conn;
			}
		}

		return new MyConnection(this.url, this.user, this.passWord, this);
	}

	public void saveConn(MyConnection conn) {
		synchronized (connections) {
			connections.add(conn);
		}
	}

	public void removeConn(MyConnection conn) {
		synchronized (connections) {
			connections.remove(conn);
		}
	}

	public int getOverTimeSpan() {
		return overTimeSpan;
	}
}
