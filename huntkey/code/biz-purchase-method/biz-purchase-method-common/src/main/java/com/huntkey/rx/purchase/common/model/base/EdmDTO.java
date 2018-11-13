package com.huntkey.rx.purchase.common.model.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.EdmProperty;

/**
 * Created by liangh on 2018/1/3 0013.
 */
public class EdmDTO {

    /**
     * 企业模型类:对象编号
     */
    @JSONField(name = EdmProperty.EDMD_CODE)
    private String edmdCode;

    /**
     * 企业模型类:企业对象
     */
    @JSONField(name = EdmProperty.EDMD_ENTE)
    private String edmdEnte;

    /**
     * 企业模型类:所属类
     */
    @JSONField(name = EdmProperty.EDMD_CLASS)
    private String edmdClass;

    /**
     * 企业模型类:来源类
     */
    @JSONField(name = EdmProperty.EDMD_SRC_CLASS)
    private String edmdSrcClass;

    @JSONField(name="cretime")
    private String cretime;//创建时间

    @JSONField(name="creuser")
    private String creuser;//创建人

    @JSONField(name="modtime")
    private String modtime;//更新时间

    @JSONField(name="moduser")
    private String moduser;//更新人


    @JSONField(name = EdmProperty.EDMD_CODE)
    public String getEdmdCode() {
        return edmdCode;
    }
    @JSONField(name = EdmProperty.EDMD_CODE)
    public void setEdmdCode(String edmdCode) {
        this.edmdCode = edmdCode;
    }

    @JSONField(name = EdmProperty.EDMD_ENTE)
    public String getEdmdEnte() {
        return edmdEnte;
    }
    @JSONField(name = EdmProperty.EDMD_ENTE)
    public void setEdmdEnte(String edmdEnte) {
        this.edmdEnte = edmdEnte;
    }

    @JSONField(name = EdmProperty.EDMD_CLASS)
    public String getEdmdClass() {
        return edmdClass;
    }
    @JSONField(name = EdmProperty.EDMD_CLASS)
    public void setEdmdClass(String edmdClass) {
        this.edmdClass = edmdClass;
    }

    @JSONField(name = EdmProperty.EDMD_SRC_CLASS)
    public String getEdmdSrcClass() {
        return edmdSrcClass;
    }
    @JSONField(name = EdmProperty.EDMD_SRC_CLASS)
    public void setEdmdSrcClass(String edmdSrcClass) {
        this.edmdSrcClass = edmdSrcClass;
    }

    public String getCretime() {
        return cretime;
    }

    public void setCretime(String cretime) {
        this.cretime = cretime;
    }

    public String getCreuser() {
        return creuser;
    }

    public void setCreuser(String creuser) {
        this.creuser = creuser;
    }

    public String getModtime() {
        return modtime;
    }

    public void setModtime(String modtime) {
        this.modtime = modtime;
    }

    public String getModuser() {
        return moduser;
    }

    public void setModuser(String moduser) {
        this.moduser = moduser;
    }
}
