package com.huntkey.rx.sceo.orm.common.model;

public class DataSourceEntity {

    //数据库用户名
    private String username;

    //数据库密码
    private String password;

    //数据库url
    private String url;

    //数据库的端口
    private String port;

    //库名
    private String dbName;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public String getDbName() {
        return dbName;
    }

    public void setDbName(String dbName) {
        this.dbName = dbName;
    }
}
