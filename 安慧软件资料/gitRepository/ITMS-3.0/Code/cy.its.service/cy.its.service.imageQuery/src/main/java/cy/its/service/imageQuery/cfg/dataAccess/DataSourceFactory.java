package cy.its.service.imageQuery.cfg.dataAccess;

import java.sql.SQLException;

import javax.sql.DataSource;


public class DataSourceFactory implements IDataSourceFactory {

	static DataSource dataSource;
	
	public static void setDataSource(DataSource ds){
		dataSource = ds;
	}
	
	@Override
	public javax.sql.DataSource createDataSource() throws SQLException {
		return dataSource;
	}

}
