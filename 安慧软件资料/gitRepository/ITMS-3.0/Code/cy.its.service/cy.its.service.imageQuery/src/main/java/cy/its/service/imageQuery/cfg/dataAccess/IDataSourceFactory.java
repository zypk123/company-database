package cy.its.service.imageQuery.cfg.dataAccess;

import java.sql.SQLException;

import javax.sql.DataSource;

interface IDataSourceFactory {
	DataSource createDataSource() throws SQLException;
}
