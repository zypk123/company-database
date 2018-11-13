package com.huntkey.tech.train.collections;

import java.util.Vector;

/**
 * Created by chenfei on 2017/8/31.
 */
public class VectorDemo {

    public static void main(String[] args) {
        Vector<Object> v = new Vector<Object>();

        v = new Vector<Object>(5);

        for (int i=0; i<11; i++) {
            if (i == 10) {
                System.out.println("just for debug.");
            }
            v.add(new Object());
        }

        v = new Vector<Object>(5, 2);
        for (int i=0; i<11; i++) {
            if (i == 10) {
                System.out.println("just for debug.");
            }
            v.add(new Object());
        }
    }
}
