package factory_pattern.factory_method;

/**
 * 使用工厂类，通过传递类型信息来获取实体类
 *
 * @author zhangyu
 * @create 2017-08-01 10:42
 **/
public class FactoryPatternDemo {

    public static void main(String[] args) {

        Circle circle = new CircleFactory().getShape(); // 得到Circle对象
        circle.draw();

        Rectangle rectangle = new RectangleFactory().getShape(); // 得到rectangle对象
        rectangle.draw();


    }

}
