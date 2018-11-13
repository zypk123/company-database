package com.huntkey.rx.purchase.common.model.custom;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 客户维护单-体系认证DTO
 *
 * @author zhangyu
 * @create 2018-01-03 9:55
 **/
public class CumoSystemSetDTO implements Serializable {

    /**
     * id
     */
    @JSONField(name = "id")
    private String id;

    /**
     * pid
     */
    @JSONField(name = "pid")
    private String pid;

    /**
     * 体系名称
     */
    @JSONField(name = "cumo_sname")
    private String cumoSname;

    /**
     * 体系名称(旧)
     */
    @JSONField(name = "cumo_sname_old")
    private String cumoSnameOld;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCumoSname() {
        return cumoSname;
    }

    public void setCumoSname(String cumoSname) {
        this.cumoSname = cumoSname;
    }

    public String getCumoSnameOld() {
        return cumoSnameOld;
    }

    public void setCumoSnameOld(String cumoSnameOld) {
        this.cumoSnameOld = cumoSnameOld;
    }

    @Override
    public String toString() {
        return "CumoSystemSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", cumoSname='" + cumoSname + '\'' +
                ", cumoSnameOld='" + cumoSnameOld + '\'' +
                '}';
    }
}
