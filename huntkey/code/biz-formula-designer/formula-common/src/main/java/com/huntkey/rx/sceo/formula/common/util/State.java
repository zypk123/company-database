package com.huntkey.rx.sceo.formula.common.util;

/**
 * @author lulx on 2017/6/15 0015.
 */
public enum State{

    /**
     * 禁用状态
     */
    VARIANT_STATE_PROHIBIT("prohibit","禁用状态"),

    /**
     * 可用状态
     */
    VARIANT_STATE_INUSING("inusing","可用状态");

    private String state;

    private String stateDesc;

    State(String state, String statedesc) {
        this.state = state;
        this.stateDesc = statedesc;
    }

    public String getState() {
        return state;
    }




}
