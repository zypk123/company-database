/**
 * Project Name:service-center-provider
 * File Name:MeasureUnitServiceImpl.java
 * Package Name:com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service.impl
 * Date:2017年11月14日下午1:38:26
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
*/

package com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.biz.index.ppi.util.JsonUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.common.model.FullInputArgument;
import com.huntkey.rx.sceo.serviceCenter.common.model.InputArgument;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service.MeasureUnitService;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;
import com.huntkey.rx.sceo.serviceCenter.provider.service.Persistance;

/**
 * ClassName:MeasureUnitServiceImpl
 * Function: 计量单位类方法实现
 * Date:     2017年11月14日 下午1:38:26
 * @author   caozhenx
 */
@Service("measureUnitService")
public class MeasureUnitServiceImpl implements MeasureUnitService {

    private String MEASURE_UNIT = "measureunit";

    private String MEAS_DEFINE_SET = "measureunit.meas_define_set";

    @Autowired
    private Persistance persistance;

    @Override
    public Result getBaseUnitObjects() throws DBException {
        Result result = new Result();

        SearchParam param = new SearchParam(MEASURE_UNIT);
        param.addCond_equals("meas_enable", "1");
        InputArgument argument = new FullInputArgument(param);

        JSONObject json = persistance.find(argument);
        JSONArray array = json.getJSONArray(NodeConstant.DATASET);

        result.setData(array);
        return result;
    }

    @Override
    public Result getUnitObjects(String pid) throws DBException {
        Result result = new Result();

        SearchParam param = new SearchParam(MEAS_DEFINE_SET);
        param.addCond_equals(NodeConstant.PID, pid);
        InputArgument argument = new FullInputArgument(param);

        JSONObject json = persistance.find(argument);
        JSONArray array = json.getJSONArray(NodeConstant.DATASET);

        result.setData(array);
        return result;
    }

    @Override
    public Result getBaseUnit() throws DBException {

        Result result = new Result();
        
        Result unitObj = getBaseUnitObjects();
        if (Result.RECODE_SUCCESS.equals(unitObj.getRetCode())) {
            JSONArray retArray = new JSONArray();
            JSONArray array = JsonUtil.getJsonArray(unitObj.getData());
            if (array != null && !array.isEmpty()) {
                for (Object o : array) {
                    JSONObject json = JsonUtil.getJsonObj(o);
                    String id = json.getString(NodeConstant.ID);
                    JSONArray unitArray = getDbaseUnit(id);
                    json.put("meas_define_set", unitArray);
                    retArray.add(json);
                }
                
                result.setData(retArray);
            }
        }else{
            return unitObj;
        }

        return result;
    }
    
    /**
     * getDbaseUnit:根据父id查询其下  基准单位
     * @author caozhenx
     * @param pid
     * @return
     * @throws DBException
     */
    private JSONArray getDbaseUnit(String pid) throws DBException {
        SearchParam param = new SearchParam(MEAS_DEFINE_SET);
        param.addCond_equals(NodeConstant.PID, pid);
        param.addCond_equals("meas_dbase", "1");
        InputArgument argument = new FullInputArgument(param);

        JSONObject json = persistance.find(argument);
        JSONArray array = json.getJSONArray(NodeConstant.DATASET);
        return array;
    }

    /**
     * getUnitObj:根据父id查询其关联的 所有单位信息
     * @author caozhenx
     * @param pid
     * @return
     * @throws DBException
     */
    private JSONArray getUnitObj(String pid) throws DBException {
        SearchParam param = new SearchParam(MEAS_DEFINE_SET);
        param.addCond_equals(NodeConstant.PID, pid);
        param.addCond_equals("is_del", "0");
        InputArgument argument = new FullInputArgument(param);

        JSONObject json = persistance.find(argument);
        JSONArray array = json.getJSONArray(NodeConstant.DATASET);
        return array;
    }

}
