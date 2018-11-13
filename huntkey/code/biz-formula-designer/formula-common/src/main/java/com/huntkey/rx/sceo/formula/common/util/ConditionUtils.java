package com.huntkey.rx.sceo.formula.common.util;

import com.huntkey.rx.sceo.formula.common.model.TplCondition;

/**
 * 条件工具类
 *
 * @author zhangyu
 * @create 2017-07-13 10:47
 **/
public class ConditionUtils {

    /**
     * 通过"."拆分值编码
     *
     * @param condition
     */
    public static void splitValueCode(TplCondition condition) {
        String cndtValueOriginCode = condition.getCndtValueOriginCode();
        if (null != cndtValueOriginCode) {
            String[] arr = cndtValueOriginCode.split("\\.");
            if (arr.length > 1) {
                condition.setCndtValueClassId(arr[0]);
                condition.setCndtValueOriginCode(arr[1]);
            }

        }
    }

    /**
     * 当值类型是"class"时，合并值编码
     *
     * @param condition
     */
    public static void mergeValueCode(TplCondition condition) {
        if ("class".equals(condition.getCndtValueType())) {
            String cndtValueClassId = condition.getCndtValueClassId();
            String cndtValueOriginCode = condition.getCndtValueOriginCode();
            condition.setCndtValueOriginCode(cndtValueClassId + "." + cndtValueOriginCode);
        }
    }
}
