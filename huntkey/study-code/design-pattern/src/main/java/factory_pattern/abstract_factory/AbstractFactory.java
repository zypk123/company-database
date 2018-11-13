package factory_pattern.abstract_factory;

/**
 * 抽象工厂类
 *
 * @author zhangyu
 * @create 2017-08-01 14:55
 **/
public abstract class AbstractFactory {

    abstract Color getColor(String color);

    abstract Shape getShape(String shape);

}
