package com.huntkey.rx.sceo.formula.common.model.vo;

import java.util.Map;

/**
 * @author chengchen on 2017/11/6.
 */
public class FormulaVo {
    private String frmuContent;

    private Map<String,Object> dataContext;

    public String getFrmuContent() {
        return frmuContent;
    }

    public void setFrmuContent(String frmuContent) {
        this.frmuContent = frmuContent;
    }

    public Map<String, Object> getDataContext() {
        return dataContext;
    }

    public void setDataContext(Map<String, Object> dataContext) {
        this.dataContext = dataContext;
    }
}
