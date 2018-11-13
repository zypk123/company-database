package com.zhang.lambda.demo;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * 使用Lambda排序集合
 *
 * @author zhangyu
 * @create 2018-01-18 15:06
 **/
public class Demo3 {

    // 在Java中,Comparator 类被用来排序集合

    public static void main(String[] args) {

        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka", "David Ferrer",
                "Roger Federer", "Andy Murray",
                "Tomas Berdych", "Juan Martin Del Potro",
                "Richard Gasquet", "John Isner"};
        List<String> players = Arrays.asList(atp);

        // 1. 使用匿名内部类根据name排序 - Comparator
        Collections.sort(players, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });

        players.forEach((player) -> System.out.println(player));

        // 2. lambda实现排序
        Collections.sort(players, (String s1, String s2) -> (s1.compareTo(s2)));

        players.forEach((player) -> System.out.println(player));

    }
}
