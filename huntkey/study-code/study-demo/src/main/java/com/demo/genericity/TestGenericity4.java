package com.demo.genericity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangyu
 * @create 2017-07-19 17:14
 **/
public class TestGenericity4 {

    public static void main(String[] args) {

        List<? extends Fruit> list = new ArrayList<>();

//        list.add(new Apple()); // 不能通过编译
//        list.add(new Fruit()); // 不能通过编译

//        list.add(null);// 能通过编译
//        Object apple = list.get(0);
    }

}
