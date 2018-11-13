package factory_pattern.abstract_factory;

/**
 * @author zhangyu
 * @create 2017-08-01 14:55
 **/
public class Green implements Color {

    @Override
    public void fill() {
        System.out.println("Inside Green::fill() method.");
    }
}
