package com.zhang.lambda.demo;

import java.util.Arrays;
import java.util.List;

/**
 * @author zhangyu
 * @create 2018-01-18 14:33
 **/
public class Demo1 {

    public static void main(String[] args) {

        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer", "Roger Federer",
                "Andy Murray", "Tomas Berdych",
                "Juan Martin Del Potro"};

        List<String> players = Arrays.asList(atp);

        // 遍历list

        // 1. 传统for循环
        for (String player : players) {
            System.out.println(player);
        }

        // 2. 使用lambda表达式以及函数操作
        players.forEach((player) -> System.out.println(player));

        // 3. 在Java8中使用双冒号操作符
        players.forEach(System.out::println);

    }

}
