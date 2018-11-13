package com.huntkey.rx.commons.utils.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;

/**
 * 基于FastJson封装的json工具类
 *
 * @author zhangyu
 * @create 2018-01-13 11:44
 **/
public class FastJsonUtils {

    private static final Logger logger = LoggerFactory.getLogger(FastJsonUtils.class);

    /**
     * 把json string 转化成类对象
     *
     * @param str
     * @param t
     * @return
     */
    public static <T> T parseObject(String str, Class<T> t) {
        try {
            if (str != null && !"".equals(str.trim())) {
                T res = JSON.parseObject(str.trim(), t);
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据转换出错", "exception:" + e.getMessage());
        }
        return null;
    }

    /**
     * 把json string 转化成类对象
     *
     * @param str
     * @param t
     * @return
     */
    public static <T> List<T> parseArray(String str, Class<T> t) {
        try {
            if (str != null && !"".equals(str.trim())) {
                List<T> res = JSON.parseArray(str.trim(), t);
                return res;
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据转换出错", "exception:" + e.getMessage());
        }
        return null;
    }

    /**
     * 功能描述：把JSON数据转换成较为复杂的java对象列表
     *
     * @param jsonData
     * @return
     * @throws Exception
     */
    public static List<Map<String, Object>> getBeanMapList(String jsonData) throws Exception {
        return JSON.parseObject(jsonData, new TypeReference<List<Map<String, Object>>>() {
        });
    }

    /**
     * 把类对象转化成json string
     *
     * @param t
     * @return
     */
    public static <T> String toJson(T t) {
        try {
            return JSON.toJSONString(t);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("数据转换出错", "exception:" + e.getMessage());
        }
        return "";
    }
}
