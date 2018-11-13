package factory_pattern.abstract_factory;

/**
 * 工厂创造器
 *
 * @author zhangyu
 * @create 2017-08-01 15:38
 **/
public class FactoryProducer {

    /**
     * 获取工厂
     *
     * @param choice
     * @return
     */
    public static AbstractFactory getFactory(String choice) {
        if (choice.equalsIgnoreCase("SHAPE")) {
            return new ShapeFactory();
        } else if (choice.equalsIgnoreCase("COLOR")) {
            return new ColorFactory();
        }
        return null;
    }
}
