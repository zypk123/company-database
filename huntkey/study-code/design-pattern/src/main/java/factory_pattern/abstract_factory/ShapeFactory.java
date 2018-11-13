package factory_pattern.abstract_factory;

/**
 * 扩展了 AbstractFactory 的工厂类
 *
 * @author zhangyu
 * @create 2017-08-01 14:57
 **/
public class ShapeFactory extends AbstractFactory {

    @Override
    public Shape getShape(String shapeType) {
        if (shapeType == null) {
            return null;
        }
        if (shapeType.equalsIgnoreCase("CIRCLE")) {
            return new Circle();
        } else if (shapeType.equalsIgnoreCase("RECTANGLE")) {
            return new Rectangle();
        }
        return null;

    }

    @Override
    public Color getColor(String color) {
        return null;
    }
}
