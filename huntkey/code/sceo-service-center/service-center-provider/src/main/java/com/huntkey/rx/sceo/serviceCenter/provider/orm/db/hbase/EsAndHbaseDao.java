package com.huntkey.rx.sceo.serviceCenter.provider.orm.db.hbase;/**
 * Created by yangcong on 2017/6/22.
 */

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author yangcong
 * @create 2017-06-22 18:34
 * @description
 **/
public interface EsAndHbaseDao {

    /**
     * 创建es索引和hbase表
     * @param json
     * @return
     * @throws Exception
     */
    String createEsIndexAndHbaseTable(JSONObject json) throws Exception;

    /**
     * 新增数据到es和hbase
     * @param json
     * @return
     * @throws Exception
     */
    String addDatasToEsAndHbase(JSONObject json) throws  Exception;
    String updateDatasToEsAndHbase(JSONObject json) throws Exception;
    /**
     * 查询
     * @param query     查询条件
     * @param pageNum   页码
     * @param pageSize  每页显示多少数据
     */
    List<JSONObject> queryFromEsAndHbase(String query, int pageNum, int pageSize) throws Exception;

    /**
     * 根据id删除es索引和hbase表
     * @param tableName
     * @param id
     * @return
     * @throws Exception
     */
    String deleteEsAndHbase(String tableName, String id) throws Exception;
}
