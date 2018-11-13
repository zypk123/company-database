package com.huntkey.rx.purchase.common.model.goodsfeature;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.purchase.common.model.base.EdmDTO;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;

/**
 * Created by liangh on 2017/12/27 0027.
 * //注意，此DTO是物品特征类
 */
public class GoodsFeatureDTO extends EdmDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="goft_name")
    private String goftName;//物品名称

    @JSONField(name="goft_type")
    private Integer goftType;//控件类型

    @JSONField(name="goft_source")
    private String goftSource;//数据来源
    private String goftSourceName;//数据来源名称

    @JSONField(name="goft_ismult")
    private Integer goftIsmult;//是否多选

    @JSONField(name="goft_seq")
    private Integer goftSeq;//排序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGoftName() {
        return goftName;
    }

    public void setGoftName(String goftName) {
        this.goftName = goftName;
    }

    public Integer getGoftType() {
        return goftType;
    }

    public void setGoftType(Integer goftType) {
        this.goftType = goftType;
    }

    public String getGoftSource() {
        return goftSource;
    }

    public void setGoftSource(String goftSource) {
        this.goftSource = goftSource;
    }

    public String getGoftSourceName() {
        return goftSourceName;
    }

    public void setGoftSourceName(String goftSourceName) {
        this.goftSourceName = goftSourceName;
    }

    public Integer getGoftIsmult() {
        return goftIsmult;
    }

    public void setGoftIsmult(Integer goftIsmult) {
        this.goftIsmult = goftIsmult;
    }

    public Integer getGoftSeq() {
        return goftSeq;
    }

    public void setGoftSeq(Integer goftSeq) {
        this.goftSeq = goftSeq;
    }
}
