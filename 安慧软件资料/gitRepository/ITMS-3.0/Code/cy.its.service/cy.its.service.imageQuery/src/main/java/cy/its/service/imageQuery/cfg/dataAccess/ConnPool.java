package cy.its.service.imageQuery.cfg.dataAccess;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;


class ConnPool {

	static DataSource dataSource;

	static {
		try {
			IDataSourceFactory dataSrcFactory = new DataSourceFactory();
			dataSource = dataSrcFactory.createDataSource();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
