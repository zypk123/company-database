package factory_pattern.simple_factory;

/**
 * 正方形实现类
 *
 * @author zhangyu
 * @create 2017-08-01 10:34
 **/
public class Square implements Shape {

    @Override
    public void draw() {
        System.out.println("Inside Square::draw() method.");
    }
}
