package cy.its.service.common.dataAccess;

import java.sql.SQLException;

import javax.sql.DataSource;

interface IDataSourceFactory {
	DataSource createDataSource() throws SQLException;
}
