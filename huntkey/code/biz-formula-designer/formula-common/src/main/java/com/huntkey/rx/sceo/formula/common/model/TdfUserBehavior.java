package com.huntkey.rx.sceo.formula.common.model;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TdfUserBehavior {
    /** 编号 */
    private String usrbId;

    /** 用户编号 */
    private String usrbUserId;

    /** 是否可用: 1 可用， 0 不可用 */
    private Byte isenable;

    /** 创建时间 */
    private Date addtime;

    /** 创建人 */
    private String adduser;

    /** 更新时间 */
    private Date modtime;

    /** 修改人 */
    private String moduser;

    /** 区域标识 */
    private Integer acctid;

    /** 最近最多使用的公式 */
    private byte[] usrbRecentUse;

    public String getUsrbId() {
        return usrbId;
    }

    public void setUsrbId(String usrbId) {
        this.usrbId = usrbId == null ? null : usrbId.trim();
    }

    public String getUsrbUserId() {
        return usrbUserId;
    }

    public void setUsrbUserId(String usrbUserId) {
        this.usrbUserId = usrbUserId == null ? null : usrbUserId.trim();
    }

    public Byte getIsenable() {
        return isenable;
    }

    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
    }

    public Date getAddtime() {
        return addtime;
    }

    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }

    public Date getModtime() {
        return modtime;
    }

    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser == null ? null : moduser.trim();
    }

    public Integer getAcctid() {
        return acctid;
    }

    public void setAcctid(Integer acctid) {
        this.acctid = acctid;
    }

    public byte[] getUsrbRecentUse() {
        return usrbRecentUse;
    }

    public void setUsrbRecentUse(byte[] usrbRecentUse) {
        this.usrbRecentUse = usrbRecentUse;
    }
}