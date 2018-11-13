package factory_pattern.simple_factory;

/**
 * 圆实现类
 *
 * @author zhangyu
 * @create 2017-08-01 10:35
 **/
public class Circle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Circle::draw() method.");
    }
}
