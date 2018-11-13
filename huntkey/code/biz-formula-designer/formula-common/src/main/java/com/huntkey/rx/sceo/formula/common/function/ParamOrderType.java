package com.huntkey.rx.sceo.formula.common.function;

/**
 * @author chenfei on 2017/7/19.
 */
public enum ParamOrderType {

    /**
     * Sequence
     */
    Sequence,

    /**
     * Loop
     */
    Loop;

    public static ParamOrderType getTypeByName(String typeName) {

        for (ParamOrderType pot : ParamOrderType.values()) {
            if (pot.toString().equalsIgnoreCase(typeName)) {
                return pot;
            }
        }

        throw new RuntimeException("The typeName: " + typeName + " can not be supported.");
    }

}
