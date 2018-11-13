package com.huntkey.rx.sceo.formula.common.util;

import com.huntkey.rx.commons.utils.string.StringUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author lulx on 2017/6/28 0028 上午 10:36
 */
public class StringUtils {

    private static Pattern humpPattern = Pattern.compile("[A-Z]");

    /**
     *@desc 驼峰转下划线
     *@pars [str]  驼峰字符串
     *@date 2017/6/28 0028 上午 10:37 lulx
     *@return java.lang.String
     **/
    public static String underscoreName(String str){
        if(StringUtil.isNullOrEmpty(str)){
            return null;
        }
        Matcher matcher = humpPattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "_"+matcher.group(0).toLowerCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    private static Pattern linePattern = Pattern.compile("_(\\w)");

    /**
     *@desc 下划线转驼峰
     *@pars [str] 下划线字符串
     *@date 2017/6/28 0028 上午 10:41 lulx
     *@return java.lang.String
     **/
    public static String humpName(String str){
        if(StringUtil.isNullOrEmpty(str)){
            return null;
        }
        str = str.toLowerCase();
        Matcher matcher = linePattern.matcher(str);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
        }
        matcher.appendTail(sb);
        return sb.toString();
    }

    /**
     * 去除字符串首尾的空格
     * @param str
     * @return
     */
    public static String trim(String str){
        if(StringUtil.isNullOrEmpty(str)){
            return null;
        }
        return str.replaceAll("^[\\s|\\u00A0]*", "").replaceAll("[\\s|\\u00A0]*$", "");
    }
}
