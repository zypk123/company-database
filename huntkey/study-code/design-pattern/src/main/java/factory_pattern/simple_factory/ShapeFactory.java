package factory_pattern.simple_factory;

/**
 * 形状工厂类
 * 生成基于给定信息的实体类的对象
 *
 * @author zhangyu
 * @create 2017-08-01 10:37
 **/
public class ShapeFactory {

    // 通过get方法生成特定对象
    public Shape getShape(String shapeType) {

        if (null == shapeType) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        }
        if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }
        if (shapeType.equalsIgnoreCase("SQUARE")) {
            return new Square();
        }
        return null;
    }
}
