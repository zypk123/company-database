package com.huntkey.rx.purchase.common.model.goodsmaintorder;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;

/**
 * Created by liangh on 2017/12/27 0027.
 *
 */
public class GomoDocDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="gomo_doc_type")
    private String gomoDocType;//文档类型

    @JSONField(name="gomo_doc_type_old")
    private String gomoDocTypeOld;//文档类型_旧
    private Integer gomoDocTypeFlag;//文档类型是否修改

    @JSONField(name="gomo_doc_name")
    private String gomoDocName;//文档名称

    @JSONField(name="gomo_doc_name_old")
    private String gomoDocNameOld;//文档名称_旧
    private Integer gomoDocNameFlag;//文档名称是否修改

    @JSONField(name="gomo_doc_url")
    private String gomoDocUrl;//文档地址

    @JSONField(name="gomo_doc_url_old")
    private String gomoDocUrlOld;//文档地址_旧
    private Integer gomoDocUrlFlag;//文档地址是否修改

    private Integer docFlag;//文档资料状态

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGomoDocType() {
        return gomoDocType;
    }

    public void setGomoDocType(String gomoDocType) {
        this.gomoDocType = gomoDocType;
    }

    public String getGomoDocTypeOld() {
        return gomoDocTypeOld;
    }

    public void setGomoDocTypeOld(String gomoDocTypeOld) {
        this.gomoDocTypeOld = gomoDocTypeOld;
    }

    public Integer getGomoDocTypeFlag() {
        return gomoDocTypeFlag;
    }

    public void setGomoDocTypeFlag(Integer gomoDocTypeFlag) {
        this.gomoDocTypeFlag = gomoDocTypeFlag;
    }

    public String getGomoDocName() {
        return gomoDocName;
    }

    public void setGomoDocName(String gomoDocName) {
        this.gomoDocName = gomoDocName;
    }

    public String getGomoDocNameOld() {
        return gomoDocNameOld;
    }

    public void setGomoDocNameOld(String gomoDocNameOld) {
        this.gomoDocNameOld = gomoDocNameOld;
    }

    public Integer getGomoDocNameFlag() {
        return gomoDocNameFlag;
    }

    public void setGomoDocNameFlag(Integer gomoDocNameFlag) {
        this.gomoDocNameFlag = gomoDocNameFlag;
    }

    public String getGomoDocUrl() {
        return gomoDocUrl;
    }

    public void setGomoDocUrl(String gomoDocUrl) {
        this.gomoDocUrl = gomoDocUrl;
    }

    public String getGomoDocUrlOld() {
        return gomoDocUrlOld;
    }

    public void setGomoDocUrlOld(String gomoDocUrlOld) {
        this.gomoDocUrlOld = gomoDocUrlOld;
    }

    public Integer getGomoDocUrlFlag() {
        return gomoDocUrlFlag;
    }

    public void setGomoDocUrlFlag(Integer gomoDocUrlFlag) {
        this.gomoDocUrlFlag = gomoDocUrlFlag;
    }

    public Integer getDocFlag() {
        return docFlag;
    }

    public void setDocFlag(Integer docFlag) {
        this.docFlag = docFlag;
    }
}
