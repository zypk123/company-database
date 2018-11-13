package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.constant.CurrencyProperty;
import com.huntkey.rx.edm.constant.InformationProperty;
import com.huntkey.rx.edm.entity.CurrencyEntity;
import com.huntkey.rx.edm.entity.TaxrateEntity;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.CurrencyService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 币别类Service接口实现类
 *
 * @author zhangyu
 * @create 2018-01-10 15:14
 **/
@Service
public class CurrencyServiceImpl implements CurrencyService {

    Logger logger = LoggerFactory.getLogger(CurrencyServiceImpl.class);

    @Autowired
    private OrmService ormService;

    @Transactional(readOnly = true,rollbackFor = Exception.class)
    @Override
    public Result getList() {

        Result result = new Result();
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(NodeConstant.ID).addColumn(CurrencyProperty.CURR_NAME).addColumn(CurrencyProperty.CURR_CODE);
        JSONArray jsonArray = new JSONArray();
        try {
            List<Map<String,Object>> mapList = ormService.selectMapList(CurrencyEntity.class,ormParam);
            if(null!=mapList&&mapList.size()>0){
                for(Map<String,Object> map:mapList){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(NodeConstant.ID, NullUtils.valueOf(map.get(NodeConstant.ID)));
                    jsonObject.put(CurrencyProperty.CURR_NAME, NullUtils.valueOf(map.get(CurrencyProperty.CURR_NAME)));
                    jsonObject.put(CurrencyProperty.CURR_CODE, NullUtils.valueOf(map.get(CurrencyProperty.CURR_CODE)));
                    jsonArray.add(jsonObject);
                }
                JsonUtils.underLine2Camel(jsonArray);
            }
        } catch (Exception e) {
            logger.error("查询所有币别类信息服务出现错误:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        result.setData(jsonArray);
        return result;
    }
}
