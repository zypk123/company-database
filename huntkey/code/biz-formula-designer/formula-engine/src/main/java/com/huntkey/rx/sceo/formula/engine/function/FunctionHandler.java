package com.huntkey.rx.sceo.formula.engine.function;


import com.huntkey.rx.sceo.formula.engine.expression.Expression;

/**
 * 函数处理器接口
 *
 * @author chenfei on 2017/6/15.
 */
public interface FunctionHandler {

    /**
     * 生成自定义函数实现的实例
     * 函数实现的查找次序为：内置静态的函数映射表->本实例的函数映射表->父节点
     *
     * @param funcName 函数名
     * @return
     */
    Expression customize(String funcName);
}
