package cy.its.service.common.dataAccess;

import java.sql.Connection;
import java.sql.SQLException;

public interface IConnFactory {
	Connection create(String url, String user, String pwd) throws SQLException;
}


