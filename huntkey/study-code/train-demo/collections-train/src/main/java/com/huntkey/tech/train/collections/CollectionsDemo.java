package com.huntkey.tech.train.collections;

import java.util.*;

/**
 * Created by chenfei on 2017/8/31.
 */
public class CollectionsDemo {

    public static void main(String[] args) {

        // Collections 工具类非常的重要，大家需要了解里面比较重要的API
        List<Integer> list = new ArrayList<Integer>();

        for (int i=0; i<10; i++) {
            list.add((int)(Math.random()*10));
        }
        // 正常的字典顺序进行排列
        Collections.sort(list);
        System.out.println("字典顺序：" + list);

        // 通过使用比较器，来实现逆序排序
        Collections.sort(list, new Comparator<Integer>() {
            public int compare(Integer o1, Integer o2) {

                if (o1 > o2) {
                    return -1;
                } else if (o1 < o2) {
                    return 1;
                } else {
                    return 0;
                }
            }
        });

        System.out.println("字典逆序：" + list);

        // 集合的拷贝，需要注意的是，dest的size要大于或者等于src的size
        List<Integer> dest = new ArrayList<Integer>();
        for (int i=0; i<10; i++) {
            dest.add(null);
        }
        Collections.copy(dest, list);
        System.out.println("dest: " + dest);

        // 初始化默认空集合
        List<?> emptyList = Collections.emptyList();
        Map<?, ?> emptyMap = Collections.emptyMap();
        Set<?> emptySet = Collections.emptySet();

        // 取最大最小值
        int max = Collections.max(list);
        int min = Collections.min(list);
    }

}

