package com.huntkey.tech.train.collections;

import com.sun.org.apache.xml.internal.utils.ObjectStack;

import java.util.Stack;

/**
 * Created by chenfei on 2017/8/31.
 */
public class StackDemo {

    public static void main(String[] args) {

        Stack<Object> stack = new Stack<Object>();

        stack.push(new Object());

        Object obj = stack.pop();

    }
}
