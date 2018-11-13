/**
 * Project Name:biz-hr-method-provider
 * File Name:ParkImpl.java
 * Package Name:com.huntkey.rx.hr.provider.dao.impl
 * Date:2017年11月22日上午9:59:33
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
 */

package com.huntkey.rx.hr.provider.dao.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.ParkConstants;
import com.huntkey.rx.hr.common.model.ParkDTO;
import com.huntkey.rx.hr.common.util.ResultVaildUtils;
import com.huntkey.rx.hr.provider.client.ORMClient;
import com.huntkey.rx.hr.provider.dao.ParkDao;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName:ParkImpl
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年11月22日 上午9:59:33
 * @author   lijie
 * @version
 * @see
 */
@Component
public class ParkImpl implements ParkDao{

    @Autowired
    ORMClient client;

    @Override
    public List<ParkDTO> getAll() {

        SearchParam param = new SearchParam(ParkConstants.EDM_PARK);

        param.addCond_equals("is_del", "0");

        Result result = client.find(param.toJSONString());

        ResultVaildUtils.vaildResult(result);

        JSONObject data = (JSONObject) JSONObject.toJSON(result.getData());

        if(data == null) {
            return null;
        }

        JSONArray dataset = data.getJSONArray("dataset");

        return JSONArray.parseArray(dataset.toJSONString(), ParkDTO.class);
    }

    @Override
    public ParkDTO findById(String id) {
        SearchParam searchParam = new SearchParam(ParkConstants.EDM_PARK);
        searchParam.addCond_equals("id", id);

        Result result = client.find(searchParam.toJSONString());
        ResultVaildUtils.vaildResult(result);

        ParkDTO parkDTO = null;
        JSONObject data = (JSONObject) JSONObject.toJSON(result.getData());
        if (data != null) {
            JSONArray dataset = data.getJSONArray("dataset");
            if (dataset != null && dataset.size() > 0) {
                parkDTO = JSONArray.parseObject(dataset.get(0).toString(), ParkDTO.class);
            }
        }

        return parkDTO;
    }

}

