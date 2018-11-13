package com.huntkey.tech.train.collections;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by chenfei1 on 2017/8/30 0030.
 */
public class ArrayListVsLinkedListDemo {

    public static void main(String[] args) {

        ArrayListVsLinkedListDemo demo = new ArrayListVsLinkedListDemo();

        // compare init time cost.
        int[] arrSize = new int[]{10000, 100000, 1000000, 3000000, 5000000, 10000000};

//        for (int size : arrSize) {
//            List<Object> arrayList = new ArrayList<Object>();
//            long cost = demo.statisticsCost("listInit", new Object[]{size, arrayList});
//            System.out.println("init ArrayList with " + size + " objects cost: " + cost + " ms.");
//        }
//
//        for (int size : arrSize) {
//            List<Object> linkedList = new LinkedList<Object>();
//            long cost = demo.statisticsCost("listInit", new Object[]{size, linkedList});
//            System.out.println("init LinkedList with " + size + " objects cost: " + cost + " ms.");
//        }

//        arrSize = new int[]{10000, 30000, 50000, 100000};
        // compare remove randomly time cost.
//        for (int size : arrSize) {
//            List<Object> arrayList = new ArrayList<Object>();
//            demo.statisticsCost("listInit", new Object[]{size, arrayList});
//            long cost = demo.statisticsCost("listRemoveRandom", new Object[]{arrayList});
//            System.out.println("remove ArrayList with " + size + " objects randomly cost: " + cost + " ms.");
//        }
//
//        for (int size : arrSize) {
//            List<Object> linkedList = new LinkedList<Object>();
//            demo.statisticsCost("listInit", new Object[]{size, linkedList});
//            long cost = demo.statisticsCost("listRemoveRandom", new Object[]{linkedList});
//            System.out.println("remove LinkedList with " + size + " objects randomly cost: " + cost + " ms.");
//        }

        // compare remove asc time cost.
//        arrSize = new int[]{10000, 30000, 50000, 100000};
//        for (int size : arrSize) {
//            List<Object> arrayList = new ArrayList<Object>();
//            demo.statisticsCost("listInit", new Object[]{size, arrayList});
//            long cost = demo.statisticsCost("listRemoveAsc", new Object[]{arrayList});
//            System.out.println("remove ArrayList with " + size + " objects asc cost: " + cost + " ms.");
//        }
//
//        for (int size : arrSize) {
//            List<Object> linkedList = new LinkedList<Object>();
//            demo.statisticsCost("listInit", new Object[]{size, linkedList});
//            long cost = demo.statisticsCost("listRemoveAsc", new Object[]{linkedList});
//            System.out.println("remove LinkedList with " + size + " objects asc cost: " + cost + " ms.");
//        }

        // compare remove desc time cost.
//        arrSize = new int[]{10000, 30000, 50000, 100000};
//        for (int size : arrSize) {
//            List<Object> arrayList = new ArrayList<Object>();
//            demo.statisticsCost("listInit", new Object[]{size, arrayList});
//            long cost = demo.statisticsCost("listRemoveDesc", new Object[]{arrayList});
//            System.out.println("remove ArrayList with " + size + " objects asc cost: " + cost + " ms.");
//        }
//
//        for (int size : arrSize) {
//            List<Object> linkedList = new LinkedList<Object>();
//            demo.statisticsCost("listInit", new Object[]{size, linkedList});
//            long cost = demo.statisticsCost("listRemoveDesc", new Object[]{linkedList});
//            System.out.println("remove LinkedList with " + size + " objects asc cost: " + cost + " ms.");
//        }

        // compare get randomly time cost.
        arrSize = new int[]{10000, 30000, 50000, 100000};
        for (int size : arrSize) {
            List<Object> arrayList = new ArrayList<Object>();
            demo.statisticsCost("listInit", new Object[]{size, arrayList});
            long cost = demo.statisticsCost("listGetRandom", new Object[]{arrayList});
            System.out.println("get ArrayList with " + size + " objects randomly cost: " + cost + " ms.");
        }

        arrSize = new int[]{10000, 30000, 50000, 100000};
        for (int size : arrSize) {
            List<Object> linkedList = new LinkedList<Object>();
            demo.statisticsCost("listInit", new Object[]{size, linkedList});
            long cost = demo.statisticsCost("listGetRandom", new Object[]{linkedList});
            System.out.println("get ArrayList with " + size + " objects randomly cost: " + cost + " ms.");
        }
    }

    protected void listGetRandom(List<Object> list) {

        int size = list.size();

        while (size >= 0) {
            int index = (int) (Math.random() * list.size());
            list.get(index);
            size --;
        }
    }

    protected void listRemoveRandom(List<Object> list) {
        while (list.size() > 0) {
            int size = list.size();
            int index = (int) (Math.random() * size);
            list.remove(index);
        }
    }

    protected void listRemoveAsc(List<Object> list) {
        while (list.size() > 0) {
            list.remove(0);
        }
    }

    protected void listRemoveDesc(List<Object> list) {
        while (list.size() > 0) {
            list.remove(list.size() - 1);
        }
    }

    protected void listInit(int size, List<Object> list) {
        for (int i=0; i<size; i++) {
            list.add(new Object());
        }
    }

    public long statisticsCost(String methodName, Object[] params) {
        try {
            Method[] methods = this.getClass().getDeclaredMethods();
            Method method = location(methodName, methods);
            long start = System.currentTimeMillis();
            method.invoke(this, params);
            long end = System.currentTimeMillis();
            return (end - start);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    protected Method location(String methodName, Method[] methods) {
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method;
            }
        }

        throw new RuntimeException("No such method : " + methodName);
    }
}
