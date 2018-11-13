package com.huntkey.rx.sceo.serviceCenter.common.function;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by linziy on 2017/10/9.
 * 常用的正则操作
 */
public class Regex {

    /**
     * 全匹配
     * @param src
     * @param regex
     * @return
     */
    public final static boolean isMatch(String src,String regex){
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(src);
        return matcher.matches();//全部匹配
    }

    /**
     * 判断是否包含正则的内容
     * @param src 字符串
     * @param regex 正则比较
     * @return
     */
    public final static boolean isFind(String src,String regex){
        Pattern pattern = Pattern.compile(regex,Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(src);
        return matcher.find();//查找子串
    }


}
