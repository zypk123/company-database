package com.huntkey.rx.sceo.formula.common.model.vo;

import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.TplPropertyLimit;

import java.util.List;

/**
 * @author nidongx on 2017/6/30.
 */
public class PropLimitCndtVo {
    private TplPropertyLimit tplPropertyLimit;
    private List<TplCondition> tplConditions;

    public TplPropertyLimit getTplPropertyLimit() {
        return tplPropertyLimit;
    }

    public void setTplPropertyLimit(TplPropertyLimit tplPropertyLimit) {
        this.tplPropertyLimit = tplPropertyLimit;
    }

    public List<TplCondition> getTplConditions() {
        return tplConditions;
    }

    public void setTplConditions(List<TplCondition> tplConditions) {
        this.tplConditions = tplConditions;
    }
}
