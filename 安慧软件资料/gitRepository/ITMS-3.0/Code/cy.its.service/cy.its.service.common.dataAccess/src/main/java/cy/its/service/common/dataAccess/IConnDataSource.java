package cy.its.service.common.dataAccess;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnDataSource {

	Connection getConnection() throws SQLException;

}