
package com.huntkey.rx.sceo.serviceCenter.common.emun;

/**
 * Created by linziy on 2017/8/10.
 */
public enum ConditionsEnum {
    CONDTIONS_SINGLE(0,"单一条件"),
    CONDTIONS_AND(1,"纯and 关系"),
    CONDTIONS_OR(2,"纯or关系"),
    CONDTIONS_MIXTRUE(3,"包含and 与 or 关系"),
    CONDTIONS_NOT(4,"Not 关系的条件");

    private int type;
    private String dec;

    ConditionsEnum(int type,String dec) {
        this.type = type;
        this.dec = dec;
    }

    public int getType() {
        return this.type;
    }
}
