package com.huntkey.rx.purchase.common.model.supplier;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 供应商维护单-供应商属性集-附件资料DTO
 *
 * @author zhangyu
 * @create 2018-01-03 16:59
 **/
public class SumoAttachSetDTO implements Serializable {

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
     * 附件类型
     */
    @JSONField(name = "sumo_attatype")
    private String sumoAttatype;

    /**
     * 附件类型(旧)
     */
    @JSONField(name = "sumo_attatype_old")
    private String sumoAttatypeOld;

    /**
     * 附件名称
     */
    @JSONField(name = "sumo_attaname")
    private String sumoAttaname;

    /**
     * 附件名称(旧)
     */
    @JSONField(name = "sumo_attaname_old")
    private String sumoAttanameOld;

    /**
     * 附件地址
     */
    @JSONField(name = "sumo_attaaddr")
    private String sumoAttaaddr;

    /**
     * 附件地址(旧)
     */
    @JSONField(name = "sumo_attaaddr_old")
    private String sumoAttaaddrOld;

    /**
     * 有效期
     */
    @JSONField(name = "sumo_attavalid")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sumoAttavalid;

    /**
     * 有效期(旧)
     */
    @JSONField(name = "sumo_attavalid_old")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date sumoAttavalidOld;

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

    public String getSumoAttatype() {
        return sumoAttatype;
    }

    public void setSumoAttatype(String sumoAttatype) {
        this.sumoAttatype = sumoAttatype;
    }

    public String getSumoAttatypeOld() {
        return sumoAttatypeOld;
    }

    public void setSumoAttatypeOld(String sumoAttatypeOld) {
        this.sumoAttatypeOld = sumoAttatypeOld;
    }

    public String getSumoAttaname() {
        return sumoAttaname;
    }

    public void setSumoAttaname(String sumoAttaname) {
        this.sumoAttaname = sumoAttaname;
    }

    public String getSumoAttanameOld() {
        return sumoAttanameOld;
    }

    public void setSumoAttanameOld(String sumoAttanameOld) {
        this.sumoAttanameOld = sumoAttanameOld;
    }

    public String getSumoAttaaddr() {
        return sumoAttaaddr;
    }

    public void setSumoAttaaddr(String sumoAttaaddr) {
        this.sumoAttaaddr = sumoAttaaddr;
    }

    public String getSumoAttaaddrOld() {
        return sumoAttaaddrOld;
    }

    public void setSumoAttaaddrOld(String sumoAttaaddrOld) {
        this.sumoAttaaddrOld = sumoAttaaddrOld;
    }

    public Date getSumoAttavalid() {
        return sumoAttavalid;
    }

    public void setSumoAttavalid(Date sumoAttavalid) {
        this.sumoAttavalid = sumoAttavalid;
    }

    public Date getSumoAttavalidOld() {
        return sumoAttavalidOld;
    }

    public void setSumoAttavalidOld(Date sumoAttavalidOld) {
        this.sumoAttavalidOld = sumoAttavalidOld;
    }

    @Override
    public String toString() {
        return "SumoAttachSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", sumoAttatype='" + sumoAttatype + '\'' +
                ", sumoAttatypeOld='" + sumoAttatypeOld + '\'' +
                ", sumoAttaname='" + sumoAttaname + '\'' +
                ", sumoAttanameOld='" + sumoAttanameOld + '\'' +
                ", sumoAttaaddr='" + sumoAttaaddr + '\'' +
                ", sumoAttaaddrOld='" + sumoAttaaddrOld + '\'' +
                ", sumoAttavalid=" + sumoAttavalid +
                ", sumoAttavalidOld=" + sumoAttavalidOld +
                '}';
    }
}
