package com.huntkey.rx.modeler.provider.util;

import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.EdmColumn;
import com.huntkey.rx.modeler.common.model.EdmTable;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.provider.service.impl.EdmLinkServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhoucj on 2017/10/30.
 */
public class TableStructureGetter {

    private static Logger log = LoggerFactory.getLogger(EdmLinkServiceImpl.class);
    private DataSource dataSource;

    public TableStructureGetter(DataSource dataSource){
        this.dataSource = dataSource;
    }

    public List<EdmTable> getAllEdmTables(){
        Assert.notNull(dataSource, "dataSource must not be null");

        List<EdmTable> edmTableList = new ArrayList<>();
        Connection con = null;
        log.info("查询数据库旧的表");
        try {
            log.info("获取连接开始 start.....");
            log.info("............"+dataSource.getConnection());
            con = dataSource.getConnection();
            log.info("获取连接结束 end..... ");
            DatabaseMetaData meta = con.getMetaData();
            ResultSet rs = meta.getTables(null, null, "%", null);
            log.info("start 循环查询表");
            while (rs.next()) {
                log.info("查询表");
                String tableName = rs.getString("TABLE_NAME");
                if (StringUtil.isNullOrEmpty(tableName)) {
                    log.info(tableName+"不存在，继续下一张表");
                    continue; }
                EdmTable edmTable = getEdmTable(tableName); //TABLE_NAME
                if (edmTable == null) { continue; }
                edmTableList.add(edmTable);
                log.info(rs.getString("REMARKS"));
            }
            log.info("end 循环查询表");
            rs.close();
        } catch (SQLException e) {
            log.error("查询旧表出错",e);
            e.printStackTrace();
        } finally {
            try {
                log.info("断开连接");
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        log.info("返回list"+edmTableList.size());
        return edmTableList;
    }

    public EdmTable getEdmTable(String tableName) {
        Assert.notNull(dataSource, "dataSource must not be null");
        if (StringUtil.isNullOrEmpty(tableName)) { return null; }
        EdmTable edmTable = new EdmTable();
        Connection con = null;
        try {
            con = dataSource.getConnection();
            DatabaseMetaData meta = con.getMetaData();

            ResultSet columns = meta.getColumns(null, null, tableName, null);
            ResultSet tables = meta.getTables(null, "%", tableName, new String[] { "TABLE" });

            if (tables == null) { return null; }

            log.info("新旧数据对比！！！");
            while (columns.next()) {
                EdmColumn edmColumn = new EdmColumn();
                String columnName = columns.getString("COLUMN_NAME");
                if ("cretime".equals(columnName) || "creuser".equals(columnName)
                        || "modtime".equals(columnName) || "moduser".equals(columnName)
                        || "acctid".equals(columnName) || "pid".equals(columnName)
                        || "id".equals(columnName) || "classname".equals(columnName)
                        || "is_del".equals(columnName) || columnName.contains("_delete") || tableName.contains(Constant.LINK_NAME)) {
                    continue;
                }

                String columnSizeStr = "";
                Integer columnSize = columns.getInt("COLUMN_SIZE");
                Integer decimalDigits = columns.getInt("DECIMAL_DIGITS");
                columnSizeStr = decimalDigits == 0 ? columnSize.toString() : columnSize.toString() + "," + decimalDigits.toString();

                String typeName = columns.getString("TYPE_NAME");
                if ("DATETIME".equals(typeName)) { columnSizeStr = Constant.DATE_DB_SIZE; }
                if ("TEXT".equals(typeName) || "MEDIUMTEXT".equals(typeName) || "LONGTEXT".equals(typeName)) {
                    typeName = "TEXT";
                    columnSizeStr = Constant.TEXT_DB_SIZE;
                }

                String columnDef = columns.getString("COLUMN_DEF");
                if ("DATETIME".equals(typeName)) { columnDef = "NULL"; }
                if ("TEXT".equals(typeName)) { columnDef = "TEXT"; }
                if (columnDef == null) { columnDef = "NULL"; }
                if (columnDef.isEmpty()) { columnDef = ""; }


                String isNullable = columns.getString("IS_NULLABLE");
                isNullable = "NO".equals(isNullable) ? "NOT NULL" : "";

                String remarks = columns.getString("REMARKS");

                edmColumn.setTableName(tableName);
                edmColumn.setColumnName(columnName);
                edmColumn.setTypeName(typeName);
                edmColumn.setColumnSize(columnSizeStr);
                edmColumn.setColumnDefault(columnDef);
                edmColumn.setIsNullable(isNullable);
                edmColumn.setRemarks(remarks);

                edmTable.getColumnList().add(edmColumn);
            }

            while (tables.next()) {
                edmTable.setRemarks(tables.getString("REMARKS"));
            }

            columns.close();
            tables.close();
        } catch (Exception e) {
            log.error("与旧表做对比",e);
            e.printStackTrace();
        } finally {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        edmTable.setTableName(tableName);
        return edmTable;
    }
}
