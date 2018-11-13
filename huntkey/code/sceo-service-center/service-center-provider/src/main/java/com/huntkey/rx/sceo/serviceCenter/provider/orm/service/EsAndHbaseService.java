package com.huntkey.rx.sceo.serviceCenter.provider.orm.service;/**
 * Created by yangcong on 2017/6/22.
 */

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DataSet;


/**
 * @author yangcong
 * @create 2017-06-22 18:33
 * @description
 **/
public interface EsAndHbaseService {
    /**
     * 创建es索引和hbase表
     * @param json
     * @return
     * @throws Exception
     */
    String createEsIndexAndHbaseTable(JSONObject json) throws Exception;

    /**
     * 新增数据到es和hbase
     * @param datas
     * @return
     * @throws Exception
     */
    String addDatasToEsAndHbase(String datas) throws Exception;

    /**
     * 查询
     * @param datas     查询条件
     */
    DataSet queryFromEsAndHbase(String datas) throws Exception;

    /**
     * 根据id删除es索引和hbase表
     * @param datas
     * @return
     * @throws Exception
     */
    String deleteEsAndHbase(String datas) throws Exception;
}
