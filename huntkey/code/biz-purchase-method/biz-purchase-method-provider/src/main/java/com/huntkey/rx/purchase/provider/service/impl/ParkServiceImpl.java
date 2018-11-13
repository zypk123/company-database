package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.constant.ParkProperty;
import com.huntkey.rx.edm.entity.ParkEntity;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.ParkService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * @author yaoss
 */
@Service
public class ParkServiceImpl implements ParkService {

    Logger logger = LoggerFactory.getLogger(ParkServiceImpl.class);

    @Autowired
    private OrmService ormService;

    @Transactional(readOnly = true,rollbackFor = Exception.class)
    @Override
    public Result getParkList() {
        Result result = new Result();
        OrmParam ormParam = new OrmParam();

        ormParam.addColumn(NodeConstant.ID).addColumn(ParkProperty.RPAK_CODE).addColumn(ParkProperty.RPAK_NAME);
        JSONArray jsonArray = new JSONArray();
        try {
            List<Map<String,Object>> mapList = ormService.selectMapList(ParkEntity.class,ormParam);
            if(null!=mapList&&mapList.size()>0){
                for(Map<String,Object> map:mapList){
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(NodeConstant.ID, NullUtils.valueOf(map.get(NodeConstant.ID)));
                    jsonObject.put(ParkProperty.RPAK_CODE, NullUtils.valueOf(map.get(ParkProperty.RPAK_CODE)));
                    jsonObject.put(ParkProperty.RPAK_NAME, NullUtils.valueOf(map.get(ParkProperty.RPAK_NAME)));
                    jsonArray.add(jsonObject);
                }
                JsonUtils.underLine2Camel(jsonArray);
            }
        } catch (Exception e) {
            logger.error("查询所有园区类信息服务出现错误:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        result.setErrMsg("获取园区列表成功！");
        result.setData(jsonArray);
        return result;
    }
}
