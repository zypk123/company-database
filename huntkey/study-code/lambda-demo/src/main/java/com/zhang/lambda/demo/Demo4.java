package com.zhang.lambda.demo;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static java.util.stream.Collectors.*;

/**
 * 使用Lambdas和Streams
 *
 * @author zhangyu
 * @create 2018-01-18 16:00
 **/
public class Demo4 {
    public static void main(String[] args) {

        // Stream是对集合的包装,通常和lambda一起使用
        // 使用lambda可以支持许多操作,如 map, filter, limit, sorted, count, min, max, sum, collect 等等

        List<Person> javaProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Elsdon", "Jaycob", "Java programmer", "male", 43, 2000));
                add(new Person("Tamsen", "Brittany", "Java programmer", "female", 23, 1500));
                add(new Person("Floyd", "Donny", "Java programmer", "male", 33, 1800));
                add(new Person("Sindy", "Jonie", "Java programmer", "female", 32, 1600));
                add(new Person("Vere", "Hervey", "Java programmer", "male", 22, 1200));
                add(new Person("Maude", "Jaimie", "Java programmer", "female", 27, 1900));
                add(new Person("Shawn", "Randall", "Java programmer", "male", 30, 2300));
                add(new Person("Jayden", "Corrina", "Java programmer", "female", 35, 1700));
                add(new Person("Palmer", "Dene", "Java programmer", "male", 33, 2000));
                add(new Person("Addison", "Pam", "Java programmer", "female", 34, 1300));
            }
        };

        List<Person> phpProgrammers = new ArrayList<Person>() {
            {
                add(new Person("Jarrod", "Pace", "PHP programmer", "male", 34, 1550));
                add(new Person("Clarette", "Cicely", "PHP programmer", "female", 23, 1200));
                add(new Person("Victor", "Channing", "PHP programmer", "male", 32, 1600));
                add(new Person("Tori", "Sheryl", "PHP programmer", "female", 21, 1000));
                add(new Person("Osborne", "Shad", "PHP programmer", "male", 32, 1100));
                add(new Person("Rosalind", "Layla", "PHP programmer", "female", 25, 1300));
                add(new Person("Fraser", "Hewie", "PHP programmer", "male", 36, 1100));
                add(new Person("Quinn", "Tamara", "PHP programmer", "female", 21, 1000));
                add(new Person("Alvin", "Lance", "PHP programmer", "male", 38, 1600));
                add(new Person("Evonne", "Shari", "PHP programmer", "female", 40, 1800));
            }
        };

        // lambda遍历list
        javaProgrammers.forEach((javaProgrammer) -> System.out.println(javaProgrammer));
        System.out.println("========================================================");
        phpProgrammers.forEach((phpProgrammer) -> System.out.println(phpProgrammer));

        // 增加程序员的工资5%
        System.out.println("下面给程序员的工资增加5%:");
        Consumer<Person> giveRaise = e -> e.setSalary(e.getSalary() / 100 * 5 + e.getSalary());

        javaProgrammers.forEach(giveRaise);
        phpProgrammers.forEach(giveRaise);

        // 使用过滤器filter()
        System.out.println("下面是月薪超过 $1,400 的PHP程序员:");
        phpProgrammers.stream()
                .filter((p) -> (p.getSalary() > 1400))
                .forEach((p) -> System.out.println(p.getFirstName() + "-" + p.getLastName()));


        // 定义过滤器,然后重用它们来执行其他操作
        Predicate<Person> ageFilter = (p) -> (p.getAge() > 25);
        Predicate<Person> salaryFilter = (p) -> (p.getSalary() > 1400);
        Predicate<Person> genderFilter = (p) -> ("female".equals(p.getGender()));

        System.out.println("下面是年龄大于25岁且月薪在$1,400以上的女PHP程序员:");
        phpProgrammers.stream()
                .filter(ageFilter)
                .filter(salaryFilter)
                .filter(genderFilter)
                .forEach((p) -> System.out.println(p));


        // 使用limit方法,限制结果集的个数
        System.out.println("最前面的3个 Java programmers:");
        javaProgrammers.stream()
                .limit(3)
                .forEach((p) -> System.out.println(p));

        System.out.println("最前面的3个女性 Java programmers:");
        javaProgrammers.stream()
                .filter(genderFilter)
                .limit(3)
                .forEach((p) -> System.out.println(p));


        // 在stream中进行排序
        System.out.println("根据 name 排序,并显示前5个 Java programmers:");
        javaProgrammers.stream()
                .sorted((p1, p2) -> (p1.getFirstName().compareTo(p2.getFirstName())))
                .limit(5)
                .collect(toList())
                .forEach((p) -> System.out.println(p));

        System.out.println("根据 salary 排序 Java programmers:");
        javaProgrammers.stream()
                .sorted((p1, p2) -> (-(p1.getSalary() - p2.getSalary())))
                .collect(toList())
                .forEach((p) -> System.out.println(p));

        // 取排序后选择第一个/最后一个 更快的是min和max方法:
        System.out.println("工资最低的 Java programmer:");
        Person minPerson = javaProgrammers.stream()
                .min((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                .get();
        System.out.println(minPerson);

        System.out.println("工资最高的 Java programmer:");
        Person maxPerson = javaProgrammers
                .stream()
                .max((p1, p2) -> (p1.getSalary() - p2.getSalary()))
                .get();
        System.out.println(minPerson);

        // map方法使用
        System.out.println("将 PHP programmers的 name 拼接成字符串:");
        String phpDevelopers = phpProgrammers
                .stream()
                .map(p -> p.getFirstName() + ":" + p.getLastName())
                .collect(joining(" , "));
        System.out.println(phpDevelopers);

        System.out.println("将 Java programmers 的 first name 存放到 Set:");
        Set<String> stringSet = javaProgrammers.stream()
                .map(p -> p.getFirstName())
                .collect(toSet());
        stringSet.forEach((p) -> System.out.println(p));

        // parallel并行stream
        System.out.println("计算付给 Java programmers 的所有money:");
        int totalSalary = javaProgrammers
                .parallelStream()
                .mapToInt(p -> p.getSalary())
                .sum();
        System.out.println(totalSalary);

        // 使用summaryStatistics方法获得stream 中元素的各种汇总数据
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        IntSummaryStatistics stats = numbers
                .stream()
                .mapToInt((x) -> x)
                .summaryStatistics();

        System.out.println("List中最大的数字 : " + stats.getMax());
        System.out.println("List中最小的数字 : " + stats.getMin());
        System.out.println("所有数字的总和   : " + stats.getSum());
        System.out.println("所有数字的平均值 : " + stats.getAverage());

    }
}
