package com.huntkey.rx.hr.provider.dao.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.constants.ModelerConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.util.ResultVaildUtils;
import com.huntkey.rx.hr.provider.client.ORMClient;
import com.huntkey.rx.hr.provider.dao.RelationDao;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class RelationDaoImpl implements RelationDao {
    @Autowired
    ORMClient ormClient;

    @Override
    public JSONArray query(SearchParam searchParam) {
        //调用查询接口
        Result searchResult = ormClient.find(searchParam.toJSONString());
        ResultVaildUtils.vaildResult(searchResult);
        JSONObject resultObject = (JSONObject) JSONObject.toJSON(searchResult.getData());
        JSONArray orderArray = resultObject.getJSONArray(NodeConstant.DATASET);
        return orderArray;
    }

    @Override
    public JSONObject findById(String id) {
        //构造查询参数对象
        SearchParam searchParam = new SearchParam(ModelerConstants.EDM_RELATION);
        //添加要查询的列，以ID、部门编码、部门名称为例,默认查所有列
//        searchParam.addColumns(new String[]{NodeConstant.ID, ModelerConstants.MDEP_CODE, ModelerConstants.MDEP_NAME});
        //添加查询条件
        searchParam.addCond_like(NodeConstant.ID, id);
        //调用查询接口
        JSONArray relationArray = query(searchParam);

        if (relationArray == null || relationArray.size() <= 0)
            throw new ApplicationException("对应伙伴不存在");

        return relationArray.getJSONObject(0);
    }
}
