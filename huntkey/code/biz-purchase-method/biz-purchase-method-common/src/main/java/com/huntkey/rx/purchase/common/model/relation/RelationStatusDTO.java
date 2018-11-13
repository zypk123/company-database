package com.huntkey.rx.purchase.common.model.relation;

/**
 * @author by xuyf on 2018/1/18 0018.
 */
public class RelationStatusDTO implements Comparable<RelationStatusDTO> {

    /**
     * 伙伴类型
     */
    private String relaType;

    /**
     * 状态
     */
    private String relaStatus;

    /**
     * 服务部门
     */
    private String relaServdept;

    /**
     * 服务部门名称
     */
    private String relaServdeptName;

    /**
     * 服务团队类型
     */
    private String relaSttype;

    /**
     * 服务人员
     */
    private String relaStemp;

    /**
     * 服务人员名
     */
    private String relaStempName;

    public String getRelaType() {
        return relaType;
    }

    public void setRelaType(String relaType) {
        this.relaType = relaType;
    }

    public String getRelaStatus() {
        return relaStatus;
    }

    public void setRelaStatus(String relaStatus) {
        this.relaStatus = relaStatus;
    }

    public String getRelaServdept() {
        return relaServdept;
    }

    public void setRelaServdept(String relaServdept) {
        this.relaServdept = relaServdept;
    }

    public String getRelaServdeptName() {
        return relaServdeptName;
    }

    public void setRelaServdeptName(String relaServdeptName) {
        this.relaServdeptName = relaServdeptName;
    }

    public String getRelaSttype() {
        return relaSttype;
    }

    public void setRelaSttype(String relaSttype) {
        this.relaSttype = relaSttype;
    }

    public String getRelaStemp() {
        return relaStemp;
    }

    public void setRelaStemp(String relaStemp) {
        this.relaStemp = relaStemp;
    }

    public String getRelaStempName() {
        return relaStempName;
    }

    public void setRelaStempName(String relaStempName) {
        this.relaStempName = relaStempName;
    }

    @Override
    public int compareTo(RelationStatusDTO o) {
        return this.relaType.compareTo(o.relaType);
    }

    @Override
    public String toString() {
        return "RelationStatusDTO{" +
                "relaType='" + relaType + '\'' +
                ", relaStatus='" + relaStatus + '\'' +
                ", relaServdept='" + relaServdept + '\'' +
                ", relaServdeptName='" + relaServdeptName + '\'' +
                ", relaSttype='" + relaSttype + '\'' +
                ", relaStemp='" + relaStemp + '\'' +
                ", relaStempName='" + relaStempName + '\'' +
                '}';
    }
}
