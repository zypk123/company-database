package singleton_pattern;

/**
 * 饿汉式 单例模式
 *
 * @author zhangyu
 * @create 2017-08-04 15:46
 **/
public class Singleton2 {

    /**
     * 构造方法私有化 不允许外界创建对象
     */
    private Singleton2() {

    }

    private static Singleton2 singleton2 = new Singleton2(); // 饿汉式上来就创建对象

    /**
     * 提供静态方法得到对象
     *
     * @return
     */
    public static Singleton2 getInstance() {
        return singleton2;
    }
}
