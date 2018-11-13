package com.huntkey.rx.commons.utils.string;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @ClassName: StringUtil
 * @Description: 瀛楃涓插父鐢ㄥ伐鍏风被
 * @author: zhangyu
 * @date: 2017骞?4鏈?11鏃ヤ笂鍗?8:37:02
 */
public class StringUtil {

    public final static String EMPTY_STR = "";

    public static boolean isNullOrEmpty(String str) {
        return str == null || EMPTY_STR.equals(str);
    }

    public static String padRight(String str, int totalWidth, char paddingChar) {
        if (StringUtil.isNullOrEmpty(str)) {
            str = EMPTY_STR;
        }

        int strLen = str.length();
        if (strLen >= totalWidth) {
            return str;
        }

        StringBuilder sb = new StringBuilder(totalWidth);
        sb.append(str);
        while (sb.length() < totalWidth) {
            sb.append(paddingChar);
        }

        return sb.toString();
    }

    public static String padLeft(String str, int totalWidth, char paddingChar) {
        if (StringUtil.isNullOrEmpty(str)) {
            str = EMPTY_STR;
        }

        int strLen = str.length();
        if (strLen >= totalWidth) {
            return str;
        }

        StringBuilder sb = new StringBuilder(totalWidth);
        int len = totalWidth - strLen;
        while (sb.length() < len) {
            sb.append(paddingChar);
        }
        sb.append(str);

        return sb.toString();
    }

    public static String trimEnd(String target, char c) {
        if (StringUtil.isNullOrEmpty(target) || !target.endsWith(String.valueOf(c))) {
            return target;
        }

        for (int i = target.length() - 1; i >= 0; i--) {
            if (target.charAt(i) == c) {
                if (i == 0) {
                    return EMPTY_STR;
                }
                continue;
            } else {
                return target.substring(0, i + 1);
            }
        }
        return target;
    }

    public static String trimStart(String target, char c) {
        if (StringUtil.isNullOrEmpty(target) || !target.startsWith(String.valueOf(c))) {
            return target;
        }

        int len = target.length() - 1;
        for (int i = 0; i <= len; i++) {
            if (target.charAt(i) == c) {
                if (i == len) {
                    return EMPTY_STR;
                }
                continue;
            } else {
                return target.substring(i);
            }
        }
        return target;
    }

    public static String generateUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static int getDefaultByteCount(String text) {
        if (isNullOrEmpty(text)) {
            return 0;
        }
        return text.getBytes().length;
    }

    public static String getErrorDetail(Exception e) {
        java.io.Writer w = null;
        java.io.PrintWriter pw = null;
        try {
            w = new java.io.StringWriter();
            pw = new java.io.PrintWriter(w);
            e.printStackTrace(pw);
            return w.toString();
        } catch (Exception e2) {
        } finally {
            if (w != null) {
                try {
                    w.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

            if (pw != null) {
                pw.close();
            }
        }

        return EMPTY_STR;
    }

    public static Boolean isEqual(String a, String b) {
        if (a == null && b == null) {
            return true;
        }

        if (a != null) {
            return a.equals(b);
        }

        return b.equals(a);
    }

    public static boolean isNullOrEmpty(Object obj) {
        return obj == null || EMPTY_STR.equals(String.valueOf(obj).trim());
    }

    /**
     * 瀵硅鏀垮尯鍒掍唬鐮佽繘琛岃浆鎹?
     *
     * @param distritCodes
     * @return
     */
    public static String convertDistritCodes(String distritCodes) {
        if (!StringUtil.isNullOrEmpty(distritCodes)) {
            StringBuffer strb = new StringBuffer();
            String[] distritCodesArray = distritCodes.split(",");
            for (String distritCode : distritCodesArray) {
                // 鐪?
                if ("0000".equals(distritCode.substring(2, 6))) {
                    strb.append(distritCode.substring(0, 2));
                }
                // 鍦板競
                else if ("00".equals(distritCode.substring(4, 6))) {
                    if (strb.length() > 0) {
                        strb.append(",");
                    }
                    strb.append(distritCode.substring(0, 4));
                } else {
                    if (strb.length() > 0) {
                        strb.append(",");
                    }
                    strb.append(distritCode);
                }
            }
            return strb.toString();
        }
        return null;
    }

    /**
     * 数据库字段转实体类字段
     */
    public static String toEntityField(String sqlField) {
        if (sqlField == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        boolean flag = false;
        for (int i = 0; i < sqlField.length(); i++) {
            char cur = sqlField.charAt(i);
            if (cur == '_') {
                flag = true;
            } else {
                if (flag) {
                    sb.append(Character.toUpperCase(cur));
                    flag = false;
                } else {
                    sb.append(Character.toLowerCase(cur));
                }
            }
        }
        return sb.toString();
    }

    /**
     * 实体类字段转数据库字段
     *
     * @param entityField
     * @return
     */
    public static String toSqlField(String entityField) {
        if (entityField == null) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < entityField.length(); i++) {
            char cur = entityField.charAt(i);
            if (Character.isUpperCase(cur)) {
                sb.append("_");
                sb.append(cur);
            } else {
                sb.append(cur);
            }
        }
        return sb.toString().toLowerCase();
    }

    public static String[] spiltStringToArray(String str, String seprator) {
        List<String> list = new ArrayList<String>();
        if (isNullOrEmpty(str))
            return null;
        int index = str.indexOf(seprator);
        if (index >= 0) {
            while (index >= 0) {
                String tem = str.substring(0, index);
                list.add(tem);
                str = str.substring(index + 1);
                index = str.indexOf(seprator);
            }
            if (!isNullOrEmpty(str)) {
                list.add(str);
            }
        } else {
            list.add(str);
        }
        String[] arr = new String[list.size()];
        for (int i = 0; i < list.size(); i++) {
            arr[i] = list.get(i);
        }
        return arr;
    }

    /**
     * 下划线转驼峰法
     *
     * @param line       源字符串
     * @param smallCamel 大小驼峰,是否为小驼峰
     * @return 转换后的字符串
     */
    public static String underline2Camel(String line, boolean smallCamel) {
        if (line == null || "".equals(line)) {
            return "";
        }
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("([A-Za-z\\d]+)(_)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(smallCamel && matcher.start() == 0 ? Character.toLowerCase(word.charAt(0)) : Character.toUpperCase(word.charAt(0)));
            int index = word.lastIndexOf('_');
            if (index > 0) {
                sb.append(word.substring(1, index).toLowerCase());
            } else {
                sb.append(word.substring(1).toLowerCase());
            }
        }
        return sb.toString();
    }

    /**
     * 驼峰法转下划线
     *
     * @param line 源字符串
     * @return 转换后的字符串
     */
    public static String camel2Underline(String line) {
        if (line == null || "".equals(line)) {
            return "";
        }
        line = String.valueOf(line.charAt(0)).toUpperCase().concat(line.substring(1));
        StringBuffer sb = new StringBuffer();
        Pattern pattern = Pattern.compile("[A-Z]([a-z\\d]+)?");
        Matcher matcher = pattern.matcher(line);
        while (matcher.find()) {
            String word = matcher.group();
            sb.append(word.toUpperCase());
            sb.append(matcher.end() == line.length() ? "" : "_");
        }
        return sb.toString().toLowerCase();
    }

}
