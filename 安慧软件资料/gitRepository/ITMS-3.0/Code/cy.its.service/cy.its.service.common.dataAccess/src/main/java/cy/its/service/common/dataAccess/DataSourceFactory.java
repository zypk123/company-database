package cy.its.service.common.dataAccess;


import java.sql.SQLException;




class DataSourceFactory implements IDataSourceFactory {

	@Override
	public javax.sql.DataSource createDataSource() throws SQLException {
		// PoolDataSource pds = PoolDataSourceFactory.getPoolDataSource();
		//
		// pds.setConnectionFactoryClassName("oracle.jdbc.pool.OracleDataSource");
		// pds.setURL(ITSConfig.findValue("jdbc_url"));
		// pds.setUser(ITSConfig.findValue("jdbc_user"));
		// pds.setPassword(ITSConfig.findValue("jdbc_pwd"));
		// pds.setInitialPoolSize(Integer.valueOf(ITSConfig.findValue("pool_iniSize")));
		// pds.setMinPoolSize(Integer.valueOf(ITSConfig.findValue("pool_minSize")));
		// pds.setTimeoutCheckInterval(43200);
		// return pds;

//		return new MyPoolDataSource(ITSConfig.findValue("jdbc_url"), ITSConfig.findValue("jdbc_user"),
//				ITSConfig.findValue("jdbc_pwd"));
		
		return null;
	}
}





