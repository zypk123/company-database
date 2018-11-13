package factory_pattern.abstract_factory;

/**
 * 长方形实现类
 *
 * @author zhangyu
 * @create 2017-08-01 10:28
 **/
public class Rectangle implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Rectangle::draw() method.");
    }
}
