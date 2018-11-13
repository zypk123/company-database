package com.huntkey.rx.sceo.formula.common.function;

/**
 * @author chenfei on 2017/7/19.
 */
public enum OptionType {

    /**
     * Option
     */
    Option,

    /**
     * NonOption
     */
    NonOption;

    public static OptionType getTypeByName(String typeName) {

        for (OptionType ot : OptionType.values()) {
            if (ot.toString().equalsIgnoreCase(typeName)) {
                return ot;
            }
        }

        throw new RuntimeException("The typeName: " + typeName + " can not be supported.");
    }

}
