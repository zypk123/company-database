package com.huntkey.rx.purchase.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.Set;

/**
 * 把下划线变成驼峰型
 */
public class JsonUtils {

    public final static void convert(Object json) {
        if (json instanceof JSONArray) {
            JSONArray arr = (JSONArray) json;
            for (Object obj : arr) {
                convert(obj);
            }
        } else if (json instanceof JSONObject) {
            JSONObject jo = (JSONObject) json;
            Set<String> keys = jo.keySet();
            String[] array = keys.toArray(new String[keys.size()]);
            for (String key : array) {
                Object value = jo.get(key);
                String[] key_strs = key.split("_");
                if (key_strs.length > 1) {
                    StringBuilder sb = new StringBuilder();
                    for (int i = 0; i < key_strs.length; i++) {
                        String ks = key_strs[i];
                        if (!"".equals(ks)) {
                            if (i == 0) {
                                sb.append(ks);
                            } else {
                                int c = ks.charAt(0);
                                if (c >= 97 && c <= 122) {
                                    int v = c - 32;
                                    sb.append((char) v);
                                    if (ks.length() > 1) {
                                        sb.append(ks.substring(1));
                                    }
                                } else {
                                    sb.append(ks);
                                }
                            }
                        }
                    }
                    jo.remove(key);
                    jo.put(sb.toString(), value);
                }
                convert(value);
            }
        }
    }

    public final static Object convert(String json) {
        Object obj = JSON.parse(json);
        convert(obj);
        return obj;
    }

    /**
     * 驼峰转下划线
     * @param obj 可能含有驼峰命名的JSONObject
     * @return  转换成下划线方式的JSONObject
     * @author yaoss
     */
    public static void camel2UnderLine(Object obj){

        if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            for (Object o : arr) {
                camel2UnderLine(o);
            }
        } else if (obj instanceof JSONObject) {
            JSONObject jo = (JSONObject) obj;
            Set<String> keys = jo.keySet();
            String[] array = keys.toArray(new String[keys.size()]);
            for (String key : array) {
                Object value = jo.get(key);
                char[] charArr=key.toCharArray();
                String newKey="";
                for(int i=0;i<charArr.length;i++){
                    if(charArr[i] >= 'A' && charArr[i] <= 'Z'){
                        newKey+="_";
                    }
                    newKey+=charArr[i];
                }
                jo.remove(key);
                jo.put(newKey.toLowerCase(), value);
                camel2UnderLine(value);
            }
        }

    }

    /**
     * 下划线转驼峰
     * @param obj 可能含有下划线命名的JSONObject
     * @return  转换成驼峰方式的JSONObject
     * @author yaoss
     */
    public static void underLine2Camel(Object obj){
        convert(obj);
    }


}