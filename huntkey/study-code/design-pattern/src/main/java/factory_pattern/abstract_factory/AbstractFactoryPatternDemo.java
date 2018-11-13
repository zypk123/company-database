package factory_pattern.abstract_factory;

/**
 * @author zhangyu
 * @create 2017-08-01 15:39
 **/
public class AbstractFactoryPatternDemo {

    public static void main(String[] args) {

        AbstractFactory shapeFactory = FactoryProducer.getFactory("SHAPE"); // 获取shape抽象工厂类
        Circle circle = (Circle) shapeFactory.getShape("CIRCLE"); //  获取circle对象
        circle.draw();

        AbstractFactory colorFactory = FactoryProducer.getFactory("COLOR");// 获取color抽象工厂类
        Color red = colorFactory.getColor("RED");
        red.fill();


    }


}
