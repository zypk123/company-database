package com.huntkey.rx.purchase.common.model.goodsmaintorder;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;

import java.io.Serializable;

/**
 * Created by liangh on 2017/12/27 0027.
 * //注意，此DTO只是物品维护单特征集
 */
public class GomoFeaDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @JSONField(name = NodeConstant.ID)
    private String id;

    @JSONField(name="gomo_feature")
    private String gomoFeature;//物品特征
    private String gomoWordListName;//物品特征名称

    @JSONField(name="gomo_feature_old")
    private String gomoFeatureOld;//物品特征_旧
    private Integer gomoFeatureFlag;//物品特征是否修改

    private Integer gomoFeatureType;//物品特征Type，即对应枚举里面的type

    @JSONField(name="gomo_feature_val")
    private String gomoFeatureVal;//特征值

    @JSONField(name="gomo_feature_val_old")
    private String gomoFeatureValOld;//特征值_旧
    private Integer gomoFeatureValFlag;//物品特征是否修改

    private String gomoSourceCode;//对应枚举的Info_code

    private Integer goftIsmult;//排序

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getGomoFeature() {
        return gomoFeature;
    }

    public void setGomoFeature(String gomoFeature) {
        this.gomoFeature = gomoFeature;
    }

    public String getGomoWordListName() {
        return gomoWordListName;
    }

    public void setGomoWordListName(String gomoWordListName) {
        this.gomoWordListName = gomoWordListName;
    }

    public String getGomoFeatureOld() {
        return gomoFeatureOld;
    }

    public void setGomoFeatureOld(String gomoFeatureOld) {
        this.gomoFeatureOld = gomoFeatureOld;
    }

    public Integer getGomoFeatureFlag() {
        return gomoFeatureFlag;
    }

    public void setGomoFeatureFlag(Integer gomoFeatureFlag) {
        this.gomoFeatureFlag = gomoFeatureFlag;
    }

    public Integer getGomoFeatureType() {
        return gomoFeatureType;
    }

    public void setGomoFeatureType(Integer gomoFeatureType) {
        this.gomoFeatureType = gomoFeatureType;
    }

    public String getGomoFeatureVal() {
        return gomoFeatureVal;
    }

    public void setGomoFeatureVal(String gomoFeatureVal) {
        this.gomoFeatureVal = gomoFeatureVal;
    }

    public String getGomoFeatureValOld() {
        return gomoFeatureValOld;
    }

    public void setGomoFeatureValOld(String gomoFeatureValOld) {
        this.gomoFeatureValOld = gomoFeatureValOld;
    }

    public Integer getGomoFeatureValFlag() {
        return gomoFeatureValFlag;
    }

    public void setGomoFeatureValFlag(Integer gomoFeatureValFlag) {
        this.gomoFeatureValFlag = gomoFeatureValFlag;
    }

    public String getGomoSourceCode() {
        return gomoSourceCode;
    }

    public void setGomoSourceCode(String gomoSourceCode) {
        this.gomoSourceCode = gomoSourceCode;
    }

    public Integer getGoftIsmult() {
        return goftIsmult;
    }

    public void setGoftIsmult(Integer goftIsmult) {
        this.goftIsmult = goftIsmult;
    }
}
