package com.huntkey.rx.sceo.formula.common.util;

/**
 * @author lulx on 2017/6/15 0015.
 */
public interface Constant {
    public static final String MODUSER = "admin";
    public static final String ADDUSER = "admin";
    /**
     * 暂存
     */
    public static final byte ISENABLE_TEMP = -1;
    /**
     * 可用
     */
    public static final byte ISENABLE_YSE = 1;
    /**
     * 不可用
     */
    public static final byte ISENABLE_NO = 0;
    /**
     * 有依赖jar
     */
    public static final byte HASJAR_YSE = 1;
    /**
     * 无依赖jar
     */
    public static final byte HASJAR_NO = 0;
}
