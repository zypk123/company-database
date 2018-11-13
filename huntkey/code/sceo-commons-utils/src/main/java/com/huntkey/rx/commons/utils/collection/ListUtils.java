package com.huntkey.rx.commons.utils.collection;

import java.util.ArrayList;
import java.util.List;

/**
 * list操作工具类
 *
 * @author zhangyu
 * @create 2017-11-01 20:41
 **/
public class ListUtils {

    /**
     * 获取交集
     *
     * @param lists
     * @return
     */
    public static List getIntersection(List... lists) {
        List result = new ArrayList();
        if (lists == null || lists.length == 0) {
            return result;
        }

        for (List item : lists) {
            if (item.isEmpty()) {
                continue;
            }
            if (result.isEmpty()) {
                result.addAll(item);
                continue;
            }
            result.retainAll(item);
        }
        return result;
    }

    /**
     * 判断list是否是null或者是空list
     *
     * @param list
     * @return
     */
    public static boolean isNullOrEmpty(List list) {

        boolean flag = (null == list || list.size() == 0) ? true : false;

        return flag;
    }


}
