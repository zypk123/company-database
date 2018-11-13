package com.huntkey.rx.modeler.provider.util;

import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

/**
 * Created by zhoucj on 2017/10/30.
 */
public class TableCreator {
    private DataSource dataSource;
    private ResourceDatabasePopulator resourceDatabasePopulator;

    public TableCreator(){}

    public TableCreator(DataSource dataSource, ResourceDatabasePopulator resourceDatabasePopulator){
        this.dataSource = dataSource;
        this.resourceDatabasePopulator = resourceDatabasePopulator;
    }

    public TableCreator addDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        return this;
    }

    public TableCreator addSqlScript(Resource... scripts){
        resourceDatabasePopulator = new ResourceDatabasePopulator(scripts);
        return this;
    }

    public void excuteSql() throws SQLException{
        Assert.notNull(dataSource, "dataSource must not be null");
        Assert.notNull(resourceDatabasePopulator, "resourceDatabasePopulator must not be null");
        Connection con = null;
        try {
            con = dataSource.getConnection();
            con.setAutoCommit(false);
            resourceDatabasePopulator.populate(con); // this starts the script execution, in the order as added

        } catch (SQLException e) {
            con.rollback();
            e.printStackTrace();
        } finally {
            try {
                con.commit();
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
