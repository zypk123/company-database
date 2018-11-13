package com.huntkey.rx.purchase.common.model.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;

/**
 * Created by liangh on 2017/12/27 0027.
 *
 */
public class GoodsDocDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="gods_doc_name")
    private String godsDocName;//文档名称

    @JSONField(name="gods_doc_type")
    private String godsDocType;//文档类型

    @JSONField(name="gods_doc_url")
    private String godsDocUrl;//文档地址

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGodsDocName() {
        return godsDocName;
    }

    public void setGodsDocName(String godsDocName) {
        this.godsDocName = godsDocName;
    }

    public String getGodsDocType() {
        return godsDocType;
    }

    public void setGodsDocType(String godsDocType) {
        this.godsDocType = godsDocType;
    }

    public String getGodsDocUrl() {
        return godsDocUrl;
    }

    public void setGodsDocUrl(String godsDocUrl) {
        this.godsDocUrl = godsDocUrl;
    }
}
