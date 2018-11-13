package com.huntkey.rx.sceo.formula.common.base;

import java.io.Serializable;
import java.util.Date;

/**
 * 所有Entity的基类
 *
 * @author zhangyu
 * @create 2017-07-04 9:20
 **/
public abstract class BaseEntity implements Serializable {

    private Date addtime;
    private Date modtime;
    private String adduser;
    private String moduser;
    private Byte isenable;

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser;
    }

    public Byte getIsenable() {
        return isenable;
    }

    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
    }
}
