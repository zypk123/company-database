package com.huntkey.rx.purchase.common.model.goods;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.edm.entity.GodsGodsDocSetaEntity;
import com.huntkey.rx.edm.entity.GodsGodsParkSetaEntity;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liangh on 2017/12/27 0027.
 * //注意，此DTO只是物品特征集，并不是物品特征类
 */
public class GoodsFeaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="gods_feature")
    private String godsFeature;//物品特征

    private Integer godsFeatureType;//物品特征Type，即对应枚举里面的type

    private String godsWordListName;//对应枚举里面的名称

    private String godsSourceCode;//对应枚举的Info_code

    @JSONField(name="gods_feature_val")
    private String godsFeatureVal;//特征值

    private Integer goftIsmult;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGodsFeature() {
        return godsFeature;
    }

    public void setGodsFeature(String godsFeature) {
        this.godsFeature = godsFeature;
    }

    public Integer getGodsFeatureType() {
        return godsFeatureType;
    }

    public void setGodsFeatureType(Integer godsFeatureType) {
        this.godsFeatureType = godsFeatureType;
    }

    public String getGodsWordListName() {
        return godsWordListName;
    }

    public void setGodsWordListName(String godsWordListName) {
        this.godsWordListName = godsWordListName;
    }

    public String getGodsSourceCode() {
        return godsSourceCode;
    }

    public void setGodsSourceCode(String godsSourceCode) {
        this.godsSourceCode = godsSourceCode;
    }

    public String getGodsFeatureVal() {
        return godsFeatureVal;
    }

    public void setGodsFeatureVal(String godsFeatureVal) {
        this.godsFeatureVal = godsFeatureVal;
    }

    public Integer getGoftIsmult() {
        return goftIsmult;
    }

    public void setGoftIsmult(Integer goftIsmult) {
        this.goftIsmult = goftIsmult;
    }
}
