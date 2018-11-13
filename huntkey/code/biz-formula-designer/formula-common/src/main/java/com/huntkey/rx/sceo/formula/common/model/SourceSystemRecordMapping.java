package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.sceo.formula.common.base.BaseEntity;

import java.util.Date;
/**
 * @author chenfei on 2017/5/15.
 */
public class SourceSystemRecordMapping extends BaseEntity {
    /** 编号 */
    private String recdId;

    /** 源名称 */
    private String sourceName;

    /** 源关联编号 */
    private String sourceMappingId;

    /** 源关联类型：[1:属性公式、2:属性限值、3:关联条件] */
    private String sourceMappingType;

    /** 内部编号 */
    private String insideId;

    /** 内部类型：[1:属性公式、2:属性限值、3:关联条件] */
    private String insideType;

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

    public String getRecdId() {
        return recdId;
    }

    public void setRecdId(String recdId) {
        this.recdId = recdId == null ? null : recdId.trim();
    }

    public String getSourceName() {
        return sourceName;
    }

    public void setSourceName(String sourceName) {
        this.sourceName = sourceName == null ? null : sourceName.trim();
    }

    public String getSourceMappingId() {
        return sourceMappingId;
    }

    public void setSourceMappingId(String sourceMappingId) {
        this.sourceMappingId = sourceMappingId == null ? null : sourceMappingId.trim();
    }

    public String getSourceMappingType() {
        return sourceMappingType;
    }

    public void setSourceMappingType(String sourceMappingType) {
        this.sourceMappingType = sourceMappingType == null ? null : sourceMappingType.trim();
    }

    public String getInsideId() {
        return insideId;
    }

    public void setInsideId(String insideId) {
        this.insideId = insideId == null ? null : insideId.trim();
    }

    public String getInsideType() {
        return insideType;
    }

    public void setInsideType(String insideType) {
        this.insideType = insideType == null ? null : insideType.trim();
    }
    @Override
    public Byte getIsenable() {
        return isenable;
    }
    @Override
    public void setIsenable(Byte isenable) {
        this.isenable = isenable;
    }
    @Override
    public Date getAddtime() {
        return addtime;
    }
    @Override
    public void setAddtime(Date addtime) {
        this.addtime = addtime;
    }
    @Override
    public String getAdduser() {
        return adduser;
    }
    @Override
    public void setAdduser(String adduser) {
        this.adduser = adduser == null ? null : adduser.trim();
    }
    @Override
    public Date getModtime() {
        return modtime;
    }
    @Override
    public void setModtime(Date modtime) {
        this.modtime = modtime;
    }
    @Override
    public String getModuser() {
        return moduser;
    }
    @Override
    public void setModuser(String moduser) {
        this.moduser = moduser == null ? null : moduser.trim();
    }

    public Integer getAcctid() {
        return acctid;
    }

    public void setAcctid(Integer acctid) {
        this.acctid = acctid;
    }
}