package com.huntkey.rx.purchase.common.model.supplier;

import com.alibaba.fastjson.annotation.JSONField;

import java.io.Serializable;

/**
 * 供应商维护单-体系认证DTO
 *
 * @author zhangyu
 * @create 2018-01-03 9:55
 **/
public class SumoSystemSetDTO implements Serializable {

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
    @JSONField(name = "sumo_sname")
    private String sumoSname;

    /**
     * 体系名称(旧)
     */
    @JSONField(name = "sumo_sname_old")
    private String sumoSnameOld;

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

    public String getSumoSname() {
        return sumoSname;
    }

    public void setSumoSname(String sumoSname) {
        this.sumoSname = sumoSname;
    }

    public String getSumoSnameOld() {
        return sumoSnameOld;
    }

    public void setSumoSnameOld(String sumoSnameOld) {
        this.sumoSnameOld = sumoSnameOld;
    }

    @Override
    public String toString() {
        return "SumoSystemSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", sumoSname='" + sumoSname + '\'' +
                ", sumoSnameOld='" + sumoSnameOld + '\'' +
                '}';
    }
}
