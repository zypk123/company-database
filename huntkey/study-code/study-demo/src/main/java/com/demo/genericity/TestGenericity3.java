package com.demo.genericity;

/**
 * 泛型学习3
 * <p>
 * 泛型的上界: 上界使用extends限定,使用上界的话,泛型就可以调用上界的方法
 *
 * @author zhangyu
 * @create 2017-07-19 16:55
 **/
public class TestGenericity3<T extends Human> {

    private T t;

    public TestGenericity3(T t) {
        this.t = t;
    }

    public void proxySay() {
        t.say();// 调用human的方法
    }

    public T get() {
        return t;
    }

    /***************总结*********************
     * 当使用上界时泛型擦除为上界的类型,因此也就解释了为啥可以调用上界的方法.并且会和赋值操作的时候一样自动强转为对应的泛型,之前是Object强转,这里则是Human强转,
     * 两者都是向上转型,为安全的操作
     *
     *
     *
     */


}
