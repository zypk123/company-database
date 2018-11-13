package com.huntkey.rx.sceo.formula.common.model.vo;

import com.huntkey.rx.sceo.formula.common.model.TacConditionRelated;
import com.huntkey.rx.sceo.formula.common.model.TacPropertyRelated;

import java.util.List;

/**
 * 关联属性VO
 *
 * @author zhangyu
 * @create 2017-07-07 15:10
 **/
public class PropertyRelatedVo {

    /**
     * 属性ID
     */
    private String propId;

    /**
     * 关联属性
     */
    private TacPropertyRelated relatedProperty;

    /**
     * 关联属性条件集合
     */
    private List<TacConditionRelated> relatedConditions;

    public TacPropertyRelated getRelatedProperty() {
        return relatedProperty;
    }

    public void setRelatedProperty(TacPropertyRelated relatedProperty) {
        this.relatedProperty = relatedProperty;
    }

    public List<TacConditionRelated> getRelatedConditions() {
        return relatedConditions;
    }

    public void setRelatedConditions(List<TacConditionRelated> relatedConditions) {
        this.relatedConditions = relatedConditions;
    }

    public String getPropId() {
        return propId;
    }

    public void setPropId(String propId) {
        this.propId = propId;
    }

}
