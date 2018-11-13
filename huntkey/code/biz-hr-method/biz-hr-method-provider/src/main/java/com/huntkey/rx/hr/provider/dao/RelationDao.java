package com.huntkey.rx.hr.provider.dao;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;

/**
 * 伙伴类数据访问
 */
public interface RelationDao {

    /**
     * 查询伙伴对象集合，不分页
     * @param searchParam
     * @return
     */
    JSONArray query(SearchParam searchParam);

    /**
     * 根据伙伴类ID查询伙伴类对象
     * @param id
     * @return
     */
    JSONObject findById(String id);
}
