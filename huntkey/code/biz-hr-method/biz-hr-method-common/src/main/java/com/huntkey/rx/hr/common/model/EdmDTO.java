package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.constant.EdmProperty;

/**
 * Created by xuyf on 2017/12/13 0013.
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
}
