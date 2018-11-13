package com.huntkey.rx.sceo.formula.engine;


import java.util.Map;

/**
 * Data provider
 * 用于在公式计算时提供变量值
 * @author chenfei on 2017/5/15
 */
public interface DataProvider {

    /**
     * 获取指定变量的值
     *
     * @param varName      变量名
     * @param defaultValue 缺省值
     * @return 变量值
     */
    public Object getValue(String varName, String defaultValue);

    /**
     * 获取数据上下文
     *
     * @return
     */
    public Map<String, Object> getDataContext();

}
