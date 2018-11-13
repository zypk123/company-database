package com.huntkey.rx.hr.common.util;

/**
 * @author yaoss
 * @Date 2017/12/11 21:28
 * @@Description
 */
public class NullUtils {
    public static String valueOf(Object o){
        if(null==o){
            return "";
        }
        return o.toString();
    }
}
