package com.huntkey.rx.sceo.serviceCenter.provider.orm.dao;

import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.TargetDataSource;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglb on 2017/6/24.
 */

public interface FormDataMapper {

    /**
     * 新增方法
     * @param map
     */

    void insert(Map<String, Object> map);

    void insert1(Map<String, Object> map);

    /**
     * 查询
     * @param map
     * @return
     */
    List<Map<String, Object>> select(Map<String, Object> map);

    /**
     * 删除
     * @param map
     */
    void delete(Map<String, Object> map);

    /**
     * 修改
     * @param map
     */
    void update(Map<String, Object> map);

    /**
     * 富查询
     * @param map
     * @return
     */
    List<Map<String, Object>> richfind(Map<String, String> map);

    /**
     * 统计
     * @param map
     * @return
     */
    long count(Map<String,String> map);

    List<Map<String,Object>> query(String sql);
}
