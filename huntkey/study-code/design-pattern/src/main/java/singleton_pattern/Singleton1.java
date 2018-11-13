package singleton_pattern;

/**
 * 懒汉式 单例模式
 *
 * @author zhangyu
 * @create 2017-08-04 15:35
 **/
public class Singleton1 {

    private static Singleton1 instance;

    /**
     * 构造方法私有化 不允许外界创建对象
     */
    private Singleton1() {

    }

    /**
     * 对外提供static方法返回实例对象
     * 加synchronized给多线程加锁
     *
     * @return
     */
    public static synchronized Singleton1 getInstance() {
        if (instance == null) {
            instance = new Singleton1(); // 等到用的时候才创建对象
        }
        return instance;
    }
}
