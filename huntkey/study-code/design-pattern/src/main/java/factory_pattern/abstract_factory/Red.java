package factory_pattern.abstract_factory;

/**
 * @author zhangyu
 * @create 2017-08-01 14:54
 **/
public class Red implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Red::fill() method.");
    }
}
