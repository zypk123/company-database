package com.huntkey.rx.purchase.common.model.custom;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 客户维护单-客户属性集-附件资料DTO
 *
 * @author zhangyu
 * @create 2018-01-03 16:59
 **/
public class CumoAttachSetDTO implements Serializable {

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
    @JSONField(name = "cumo_attatype")
    private String cumoAttatype;

    /**
     * 附件类型(旧)
     */
    @JSONField(name = "cumo_attatype_old")
    private String cumoAttatypeOld;

    /**
     * 附件名称
     */
    @JSONField(name = "cumo_attaname")
    private String cumoAttaname;

    /**
     * 附件名称(旧)
     */
    @JSONField(name = "cumo_attaname_old")
    private String cumoAttanameOld;

    /**
     * 附件地址
     */
    @JSONField(name = "cumo_attaaddr")
    private String cumoAttaaddr;

    /**
     * 附件地址(旧)
     */
    @JSONField(name = "cumo_attaaddr_old")
    private String cumoAttaaddrOld;

    /**
     * 有效期
     */
    @JSONField(name = "cumo_attavalid")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cumoAttavalid;

    /**
     * 有效期(旧)
     */
    @JSONField(name = "cumo_attavalid_old")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date cumoAttavalidOld;

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

    public String getCumoAttatype() {
        return cumoAttatype;
    }

    public void setCumoAttatype(String cumoAttatype) {
        this.cumoAttatype = cumoAttatype;
    }

    public String getCumoAttatypeOld() {
        return cumoAttatypeOld;
    }

    public void setCumoAttatypeOld(String cumoAttatypeOld) {
        this.cumoAttatypeOld = cumoAttatypeOld;
    }

    public String getCumoAttaname() {
        return cumoAttaname;
    }

    public void setCumoAttaname(String cumoAttaname) {
        this.cumoAttaname = cumoAttaname;
    }

    public String getCumoAttanameOld() {
        return cumoAttanameOld;
    }

    public void setCumoAttanameOld(String cumoAttanameOld) {
        this.cumoAttanameOld = cumoAttanameOld;
    }

    public String getCumoAttaaddr() {
        return cumoAttaaddr;
    }

    public void setCumoAttaaddr(String cumoAttaaddr) {
        this.cumoAttaaddr = cumoAttaaddr;
    }

    public String getCumoAttaaddrOld() {
        return cumoAttaaddrOld;
    }

    public void setCumoAttaaddrOld(String cumoAttaaddrOld) {
        this.cumoAttaaddrOld = cumoAttaaddrOld;
    }

    public Date getCumoAttavalid() {
        return cumoAttavalid;
    }

    public void setCumoAttavalid(Date cumoAttavalid) {
        this.cumoAttavalid = cumoAttavalid;
    }

    public Date getCumoAttavalidOld() {
        return cumoAttavalidOld;
    }

    public void setCumoAttavalidOld(Date cumoAttavalidOld) {
        this.cumoAttavalidOld = cumoAttavalidOld;
    }

    @Override
    public String toString() {
        return "CumoAttachSetDTO{" +
                "id='" + id + '\'' +
                ", pid='" + pid + '\'' +
                ", cumoAttatype='" + cumoAttatype + '\'' +
                ", cumoAttatypeOld='" + cumoAttatypeOld + '\'' +
                ", cumoAttaname='" + cumoAttaname + '\'' +
                ", cumoAttanameOld='" + cumoAttanameOld + '\'' +
                ", cumoAttaaddr='" + cumoAttaaddr + '\'' +
                ", cumoAttaaddrOld='" + cumoAttaaddrOld + '\'' +
                ", cumoAttavalid=" + cumoAttavalid +
                ", cumoAttavalidOld=" + cumoAttavalidOld +
                '}';
    }
}
