package com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.common.emun.SortType;
import com.huntkey.rx.sceo.serviceCenter.common.model.*;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service.EnumService;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;
import com.huntkey.rx.sceo.serviceCenter.provider.service.Persistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by clarkzhao on 2017/10/23.
 *
 * @author clarkzhao
 * @date 2017/10/23
 */

@Service
public class EnumServiceImpl implements EnumService {

    @Autowired
    private Persistance persistance;

    @Override
    public Result getEnumsObjects(String enumCode) throws DBException {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        Map enums = new HashMap();

        SearchParam param = new SearchParam("wordlist");
        param.addColumns(new String[]{NodeConstant.ID,"info_code"});
        param.addCond_in("info_code", enumCode);
        InputArgument argument = new FullInputArgument(param);

        JSONObject parentObject = persistance.find(argument);
        JSONArray parentArray = parentObject.getJSONArray(NodeConstant.DATASET);

        if (parentArray == null || parentArray.size() == 0) {
            result.setRetCode(10001);
            result.setErrMsg("数据字典不存在！");
            return result;
        }

        for (int i = 0; i < parentArray.size(); i++) {
            JSONObject parentEnum = parentArray.getJSONObject(i);
            String word_par = parentEnum.getString(NodeConstant.ID);

            param.clearConditions().addCond_equals("word_par", word_par)
                    .addSortParam(new SortNode("word_seq", SortType.ASC))
                    .addColumns(new String[]{NodeConstant.ID, "info_code", "word_name", "word_seq"});

            argument = new FullInputArgument(param);
            JSONObject enumsObject = persistance.find(argument);
            JSONArray enumsArray = enumsObject.getJSONArray(NodeConstant.DATASET);

            enums.put(parentEnum.getString("info_code"),enumsArray);
        }
        result.setData(enums);
        return result;
    }

    @Override
    public Result getEnumObjectById(String enumObjId) throws DBException {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        SearchParam param = new SearchParam("wordlist");
        param.addCond_equals(NodeConstant.ID, enumObjId);
        InputArgument argument = new FullInputArgument(param);
        JSONObject parentObject = persistance.find(argument);
        JSONArray parentArray = parentObject.getJSONArray(NodeConstant.DATASET);
        if (parentArray == null || parentArray.size() == 0) {
            result.setRetCode(10001);
            result.setErrMsg("数据字典不存在！");
            return result;
        }
        JSONObject parentEnum = parentArray.getJSONObject(0);
        result.setData(parentEnum);
        return result;
    }
}
