package factory_pattern.factory_method;

/**
 * 矩形 工厂类
 *
 * @author zhangyu
 * @create 2017-08-01 12:44
 **/
public class RectangleFactory implements BaseFactory {

    @Override
    public Rectangle getShape() {
        return new Rectangle();
    }
}
