package com.demo.genericity;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author zhangyu
 * @create 2017-12-19 16:20
 **/
public class TestGenericity5 {

    public static void main(String[] args) {

        List<Timestamp> list = new ArrayList<Timestamp>();
        Date date = new Date();

        new TestGenericity5().upperBound(list, date);

        List<Timestamp> list2 = new ArrayList<Timestamp>();
        Date date2 = new Date();
        Timestamp time2 = new Timestamp(date.getTime());
        new TestGenericity5().upperBound2(list2, time2);
        //upperBound2(list,date);//这句同样无法编译

    }

    public void upperBound(List<? extends Date> list, Date date) {
        Date now = list.get(0);
        System.out.println("now==>" + now);
//        list.add(date); // 这句话无法编译
        list.add(null);// 这句可以编译，因为null没有类型信息
    }


    public <T extends Date> void upperBound2(List<T> list, T date) {
        list.add(date);
    }

}
