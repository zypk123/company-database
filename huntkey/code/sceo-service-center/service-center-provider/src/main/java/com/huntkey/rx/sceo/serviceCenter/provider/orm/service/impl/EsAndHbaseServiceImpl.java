package com.huntkey.rx.sceo.serviceCenter.provider.orm.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DataSet;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.db.hbase.EsAndHbaseDao;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.db.hbase.HBaseHandler;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.Criteria;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.model.FullCriteria;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.service.EsAndHbaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yangcong
 * @create 2017-06-22 18:33
 * @description
 **/
@Service
public class EsAndHbaseServiceImpl implements EsAndHbaseService {

    @Autowired
    private EsAndHbaseDao esAndHbaseDao;

    @Autowired
    private HBaseHandler hBaseHandler;

    /**
     * 创建es索引和hbase表
     * @param json
     * @return
     * @throws Exception
     */
    @Override
    public String createEsIndexAndHbaseTable(JSONObject json) throws Exception{
       return esAndHbaseDao.createEsIndexAndHbaseTable(json);
    }

    /**
     * 新增数据到es和hbase
     * @param datas
     * @return
     * @throws Exception
     */
    @Override
    public String addDatasToEsAndHbase(String datas) throws Exception {
        return hBaseHandler.merge(getCriteria(datas));
    }

    /**
     * 查询
     * @param datas     查询条件
     */
    @Override
    public DataSet queryFromEsAndHbase(String datas) throws Exception{
        return hBaseHandler.find(getCriteria(datas)) ;
    }

    /**
     * 根据id删除es索引和hbase表
     * @param datas
     * @return
     * @throws Exception
     */
    @Override
    public String deleteEsAndHbase(String datas) throws Exception {
        hBaseHandler.delete(getCriteria(datas));
        return "";
    }

    /**
     * 返回Criteria对象
     * @param datas
     * @return
     */
    public Criteria getCriteria(String datas){
        Criteria criteria = new FullCriteria(datas);                // 保存数据的对象

        return criteria;
    }
}
