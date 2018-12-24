package cy.its.service.common.dataAccess.customDataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import cy.its.service.common.dataAccess.IConnFactory;

class MyConnFactory implements IConnFactory{
	
	@Override
	public Connection create(String url, String user, String pwd) throws SQLException {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		return DriverManager.getConnection(url, user, pwd);
	}
}