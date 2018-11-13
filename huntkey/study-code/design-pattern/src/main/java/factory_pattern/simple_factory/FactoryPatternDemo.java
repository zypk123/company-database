package factory_pattern.simple_factory;

/**
 * 使用工厂类，通过传递类型信息来获取实体类
 *
 * @author zhangyu
 * @create 2017-08-01 10:42
 **/
public class FactoryPatternDemo {

    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory(); // 创建工厂对象

        Shape circle = shapeFactory.getShape("CIRCLE"); // 通过工厂类创建对象，隐藏了对象创建的内部逻辑
        circle.draw();

        Shape rectangle = shapeFactory.getShape("RECTANGLE");// 通过工厂类创建对象，隐藏了对象创建的内部逻辑
        rectangle.draw();

        Shape square = shapeFactory.getShape("SQUARE");// 通过工厂类创建对象，隐藏了对象创建的内部逻辑
        square.draw();

    }

}
