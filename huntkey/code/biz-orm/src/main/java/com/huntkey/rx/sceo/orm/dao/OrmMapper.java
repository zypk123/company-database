package com.huntkey.rx.sceo.orm.dao;

import java.util.List;
import java.util.Map;

/**
 * Created by licj on 2017/11/17.
 */
public interface OrmMapper {

    Map<String, Object> selectByPrimaryKey(Map<String, Object> param);

//    int deleteByPrimaryKey(Map<String, Object> param);

    int deleteByCondition(Map<String, Object> param);

    int insert(Map<String, Object> param);

    int insertBatch(Map<String, Object> param);

    int updateByPrimaryKey(Map<String, Object> param);

    int updateByConditionSelective(Map<String, Object> param);

    List<Map<String, Object>> selectByCondition(Map<String, Object> param);

    long countByCondtion(Map<String, Object> param);

    List<Map<String, Object>> selectBySql(String selectSql);
}
