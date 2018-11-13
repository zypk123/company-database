package factory_pattern.abstract_factory;

/**
 * 扩展了 AbstractFactory 的工厂类
 *
 * @author zhangyu
 * @create 2017-08-01 15:23
 **/
public class ColorFactory extends AbstractFactory {

    @Override
    public Color getColor(String colorType) {
        if (colorType == null) {
            return null;
        }
        if (colorType.equalsIgnoreCase("RED")) {
            return new Red();
        } else if (colorType.equalsIgnoreCase("GREEN")) {
            return new Green();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
