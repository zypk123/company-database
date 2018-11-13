package com.huntkey.rx.sceo.orm.common.constant;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by linziy on 2017/12/1.
 */
public class ConstantRegex {

    public final static String AT_LEAST_ZERO_SPACE = "\\s{0,}";
    public final static String AT_LEAST_ONE_SPACE = "\\s+";//一个或者多个空格
    public final static String LEFT_PARENTHESIS = "\\(";
    public final static String RIGHT_PARENTHESIS = "\\)";
    public final static String DOUBLE_COLON = "\""; //双引号 预查询
    public final static String INVERTED_COMMA = "'"; //单引号 预查询
    public final static String ORL_OPERATOR = "\\|";//
    public final static String NUMBERS = "[0-9]+";

    //"xx" 形式的字符串
    public final static String VAR_WITH_DOUBLE_COLON = DOUBLE_COLON + ".{0,}" + DOUBLE_COLON;
    //'xx' 形式的字符串
    public final static String VAR_WITH_INVERTED_COMMA = INVERTED_COMMA + ".{0,}" + INVERTED_COMMA;
    //sql 常量
    public final static String SQL_CONST_VAL = "(" + VAR_WITH_DOUBLE_COLON
            + "|" + VAR_WITH_INVERTED_COMMA
            + "|" + NUMBERS
            + ")";
    //////////////////正则相关函数///////////////////////////////////////////////////

    /**
     * 正则匹配且截取
     *
     * @param regex //正则匹配
     * @param src   //字符串源
     * @return
     */
    public final static String getMatchedOneString(String regex, String src) {
        String result = null;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(src);
        if (m.find()) {
            result = m.group();
        }
        return result;
    }

    /**
     * 获取多个匹配的结果
     *
     * @param regex
     * @param src
     * @return
     */
    public final static List<String> getMatchedMultipleString(String regex, String src) {
        List<String> list = new ArrayList<String>();
        String result = null;
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(src);
        while (m.find()) {
            result = m.group();
            list.add(result);
        }
        return list;
    }

    /**
     * 正则查询字符串中是否包含某串数据
     *
     * @param regex
     * @param src
     * @return
     */
    public static boolean isContains(String src, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(src);
        return matcher.find();//部分匹配
    }

    /**
     * @param src
     * @param regex
     * @return
     */
    public static boolean isMatch(String src, String regex) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(src);
        return matcher.matches();//全部匹配
    }
}
