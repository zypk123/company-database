package factory_pattern.factory_method;

/**
 * 圆 工厂类
 *
 * @author zhangyu
 * @create 2017-08-01 12:41
 **/
public class CircleFactory implements BaseFactory {

    @Override
    public Circle getShape() {
        return new Circle();
    }
}
