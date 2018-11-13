package com.huntkey.rx.modeler.common.model.vo;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EdmModelerVO {
    //主键id
    private String id;

    //标准模型id
    private String edmdParentId;

    //模型编码
    private String edmdCode;

    //模型版本
    @NotNull
    private String edmdVer;

    //模型更新说明
    @Length(max = 800)
    private String edmdUpdateDesc;

    //模型状态
    @NotNull
    private Byte edmdStatus;

    //模型发布时间
    private Date edmdReleaseTime;

    //模型失效时间
    private Date edmdExpireTime;

    //是否删除状态
    private Byte isDel;

    //创建时间
    private Date addtime;

    //创建人
    private String adduser;

    //修改时间
    private Date modtime;

    //修改人
    private String moduser;

    //账户标识
    private Byte acctid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEdmdParentId() {
        return edmdParentId;
    }

    public void setEdmdParentId(String edmdParentId) {
        this.edmdParentId = edmdParentId;
    }

    public String getEdmdCode() {
        return edmdCode;
    }

    public void setEdmdCode(String edmdCode) {
        this.edmdCode = edmdCode;
    }

    public String getEdmdVer() {
        return edmdVer;
    }

    public void setEdmdVer(String edmdVer) {
        this.edmdVer = edmdVer;
    }

    public String getEdmdUpdateDesc() {
        return edmdUpdateDesc;
    }

    public void setEdmdUpdateDesc(String edmdUpdateDesc) {
        this.edmdUpdateDesc = edmdUpdateDesc;
    }

    public Byte getEdmdStatus() {
        return edmdStatus;
    }

    public void setEdmdStatus(Byte edmdStatus) {
        this.edmdStatus = edmdStatus;
    }

    public Date getEdmdReleaseTime() {
        if(this.edmdReleaseTime!=null){
            return new Date(this.edmdReleaseTime.getTime());
        }else{
            return null;
        }
    }

    public void setEdmdReleaseTime(Date edmdReleaseTime) {
        if(edmdReleaseTime!=null){
            this.edmdReleaseTime = (Date)edmdReleaseTime.clone();
        }else{
            this.edmdReleaseTime = null;
        }
    }

    public Date getEdmdExpireTime() {
        if(this.edmdExpireTime!=null){
            return new Date(this.edmdExpireTime.getTime());
        }else{
            return null;
        }
    }

    public void setEdmdExpireTime(Date edmdExpireTime) {
        if(edmdExpireTime!=null){
            this.edmdExpireTime = (Date)edmdExpireTime.clone();
        }else{
            this.edmdExpireTime = null;
        }
    }

    public Byte getIsDel() {
        return isDel;
    }

    public void setIsDel(Byte isDel) {
        this.isDel = isDel;
    }

    public Date getAddtime() {
        if(this.addtime!=null){
            return new Date(this.addtime.getTime());
        }else{
            return null;
        }
    }

    public void setAddtime(Date addtime) {
        if(addtime!=null){
            this.addtime = (Date)addtime.clone();
        }else{
            this.addtime = null;
        }
    }

    public String getAdduser() {
        return adduser;
    }

    public void setAdduser(String adduser) {
        this.adduser = adduser;
    }

    public Date getModtime() {
        if(this.modtime!=null){
            return new Date(this.modtime.getTime());
        }else{
            return null;
        }
    }

    public void setModtime(Date modtime) {
        if(modtime!=null){
            this.modtime = (Date)modtime.clone();
        }else{
            this.modtime = null;
        }
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser;
    }

    public Byte getAcctid() {
        return acctid;
    }

    public void setAcctid(Byte acctid) {
        this.acctid = acctid;
    }

    //模型状态名称
    private String edmdStatusName;

    //行业编码
    private String edmiIndustryCode;

    //行业名称
    private String edmiIndustryName;

    //行业模型id
    private String edmiEdmdId;

    //是否可以插入新模型的状态位
    private int addStatus;

    //发布时间str
    private String edmdReleaseTimeStr;

    //失效时间str
    private String edmdExpireTimeStr;

    //加入时间str
    private String addtimeStr;

    //修改时间str
    private String modtimeStr;

    public String getEdmdStatusName() {
        return edmdStatusName;
    }

    public void setEdmdStatusName(String edmdStatusName) {
        this.edmdStatusName = edmdStatusName;
    }

    public String getEdmiIndustryCode() {
        return edmiIndustryCode;
    }

    public void setEdmiIndustryCode(String edmiIndustryCode) {
        this.edmiIndustryCode = edmiIndustryCode;
    }

    public String getEdmiIndustryName() {
        return edmiIndustryName;
    }

    public void setEdmiIndustryName(String edmiIndustryName) {
        this.edmiIndustryName = edmiIndustryName;
    }

    public String getEdmiEdmdId() {
        return edmiEdmdId;
    }

    public void setEdmiEdmdId(String edmiEdmdId) {
        this.edmiEdmdId = edmiEdmdId;
    }

    public int getAddStatus() {
        return addStatus;
    }

    public void setAddStatus(int addStatus) {
        this.addStatus = addStatus;
    }

    public String getEdmdReleaseTimeStr() {
        return getDatetrByDate(edmdReleaseTime);
    }

    public void setEdmdReleaseTimeStr(String edmdReleaseTimeStr) {
        this.edmdReleaseTimeStr = edmdReleaseTimeStr;
    }

    public String getEdmdExpireTimeStr() {
        return getDatetrByDate(edmdExpireTime);
    }

    public void setEdmdExpireTimeStr(String edmdExpireTimeStr) {
        this.edmdExpireTimeStr = edmdExpireTimeStr;
    }

    public String getAddtimeStr() {
        return getDatetrByDate(addtime);
    }

    public void setAddtimeStr(String addtimeStr) {
        this.addtimeStr = addtimeStr;
    }

    public String getModtimeStr() {
        return getDatetrByDate(modtime);
    }

    public void setModtimeStr(String modtimeStr) {
        this.modtimeStr = modtimeStr;
    }

    private String getDatetrByDate(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateStr = "";
        if(date!=null){
            dateStr = formatter.format(date);
        }
        return dateStr;
    }

    @Override
    public String toString() {
        return "${" +
                "id:'" + id + '\'' +
                ", edmdParentId:'" + edmdParentId + '\'' +
                ", edmdCode:'" + edmdCode + '\'' +
                ", edmdVer:'" + edmdVer + '\'' +
                ", edmdUpdateDesc:'" + edmdUpdateDesc + '\'' +
                ", edmdStatus:" + edmdStatus +
                ", edmdReleaseTime:" + edmdReleaseTime +
                ", edmdExpireTime:" + edmdExpireTime +
                ", isDel:" + isDel +
                ", addtime:" + addtime +
                ", adduser:'" + adduser + '\'' +
                ", modtime:" + modtime +
                ", moduser:'" + moduser + '\'' +
                ", acctid:" + acctid +
                ", edmdStatusName:'" + edmdStatusName + '\'' +
                ", edmiIndustryCode:'" + edmiIndustryCode + '\'' +
                ", edmiIndustryName:'" + edmiIndustryName + '\'' +
                ", edmiEdmdId:'" + edmiEdmdId + '\'' +
                ", addStatus:" + addStatus +
                ", edmdReleaseTimeStr:'" + edmdReleaseTimeStr + '\'' +
                ", edmdExpireTimeStr:'" + edmdExpireTimeStr + '\'' +
                ", addtimeStr:'" + addtimeStr + '\'' +
                ", modtimeStr:'" + modtimeStr + '\'' +
                '}';
    }
}