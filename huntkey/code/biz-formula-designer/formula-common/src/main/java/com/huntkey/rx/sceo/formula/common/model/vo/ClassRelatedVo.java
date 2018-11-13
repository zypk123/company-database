package com.huntkey.rx.sceo.formula.common.model.vo;

import com.huntkey.rx.sceo.formula.common.model.TfdClassRelated;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;

import java.util.List;

/**
 * 相关类VO
 *
 * @author zhangyu
 * @create 2017-07-08 13:27
 **/
public class ClassRelatedVo {

    /**
     * 相关类
     */
    private TfdClassRelated classRelated;

    /**
     * 相关类条件列表
     */
    private List<TplCondition> tplConditions;

    public TfdClassRelated getClassRelated() {
        return classRelated;
    }

    public void setClassRelated(TfdClassRelated classRelated) {
        this.classRelated = classRelated;
    }

    public List<TplCondition> getTplConditions() {
        return tplConditions;
    }

    public void setTplConditions(List<TplCondition> tplConditions) {
        this.tplConditions = tplConditions;
    }
}
