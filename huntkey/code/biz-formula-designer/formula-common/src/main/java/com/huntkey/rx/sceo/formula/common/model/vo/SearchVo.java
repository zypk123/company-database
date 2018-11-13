package com.huntkey.rx.sceo.formula.common.model.vo;

import java.util.List;

/**
 * @author lulx on 2017/8/16 0016 上午 8:53
 */
public class SearchVo {
    List<String> columns;
    List<ConditionsVo> conditions;
    PagenationVo pagenation;

    public List<String> getColumns() {
        return columns;
    }

    public void setColumns(List<String> columns) {
        this.columns = columns;
    }

    public List<ConditionsVo> getConditions() {
        return conditions;
    }

    public void setConditions(List<ConditionsVo> conditions) {
        this.conditions = conditions;
    }

    public PagenationVo getPagenation() {
        return pagenation;
    }

    public void setPagenation(PagenationVo pagenation) {
        this.pagenation = pagenation;
    }
}
