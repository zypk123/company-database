package com.huntkey.rx.sceo.formula.common.model;

import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.sceo.formula.common.base.BaseEntity;
import com.huntkey.rx.sceo.formula.common.util.Constant;

import java.util.Date;

/**
 * @author chenfei on 2017/5/15.
 */
public class TfmFunctionCompiled extends BaseEntity {
    /** 编号 */
    private String funcId;

    /** 函数编号 */
    private String funcFunId;

    /** 类全名[包含类包路径] */
    private String funcClassFullName;

    /** 编译后的class⽂件内容 */
    private byte[] funcClassBinary;

    /** 类文件的源码 */
    private byte[] funcFunSource;

    /** 是否可用: 1 可用，  0 不可用 */
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

    /** 是否有依赖jar: 1 有，  0 无 */
    private Byte hasjar;

    public String getFuncId() {
        return funcId;
    }

    public void setFuncId(String funcId) {
        this.funcId = funcId == null ? null : funcId.trim();
    }

    public String getFuncFunId() {
        return funcFunId;
    }

    public void setFuncFunId(String funcFunId) {
        this.funcFunId = funcFunId == null ? null : funcFunId.trim();
    }

    public String getFuncClassFullName() {
        return funcClassFullName;
    }

    public void setFuncClassFullName(String funcClassFullName) {
        this.funcClassFullName = funcClassFullName == null ? null : funcClassFullName.trim();
    }
    public byte[] getFuncClassBinary() {
        return funcClassBinary;
    }

    public void setFuncClassBinary(byte[] funcClassBinary) {
        this.funcClassBinary = funcClassBinary;
    }

    public byte[] getFuncFunSource() {
        return funcFunSource;
    }

    public void setFuncFunSource(byte[] funcFunSource) {
        this.funcFunSource = funcFunSource;
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

    public Byte getHasjar() {
        return hasjar;
    }

    public void setHasjar(Byte hasjar) {
        this.hasjar = hasjar;
    }

    public void preSetting() {
        this.setFuncId(UuidCreater.uuid());
        this.setAddtime(new Date());
        this.setIsenable((byte) 1);
        this.setHasjar((byte) 0);
        this.setAdduser(Constant.ADDUSER);
        this.setModtime(new Date());
        this.setModuser(Constant.MODUSER);
    }

}