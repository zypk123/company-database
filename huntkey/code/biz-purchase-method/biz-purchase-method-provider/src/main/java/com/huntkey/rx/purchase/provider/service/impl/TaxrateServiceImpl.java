package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.InformationProperty;
import com.huntkey.rx.edm.constant.TaxrateProperty;
import com.huntkey.rx.edm.entity.TaxrateEntity;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.TaxrateService;
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
 * 税率类Service接口实现类
 *
 * @author zhangyu
 * @create 2018-01-10 15:14
 **/
@Service
public class TaxrateServiceImpl implements TaxrateService {

    Logger logger = LoggerFactory.getLogger(TaxrateServiceImpl.class);

    @Autowired
    private OrmService ormService;

    @Override
    public TaxrateEntity load(String id) {
        if (StringUtil.isNullOrEmpty(id)){
            return null;
        }
        TaxrateEntity taxrateEntity = null;
        try {
            taxrateEntity = ormService.load(TaxrateEntity.class, id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return taxrateEntity;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Result getTaxrateList() {

        Result result = new Result();
        OrmParam ormParam = new OrmParam();

        ormParam.addColumn(NodeConstant.ID).addColumn(TaxrateProperty.TAXR_NAME);
        JSONArray jsonArray = new JSONArray();
        try {
            List<Map<String, Object>> mapList = ormService.selectMapList(TaxrateEntity.class, ormParam);
            if (null != mapList && mapList.size() > 0) {
                for (Map<String, Object> map : mapList) {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(NodeConstant.ID, NullUtils.valueOf(map.get(NodeConstant.ID)));
                    jsonObject.put(TaxrateProperty.TAXR_NAME, NullUtils.valueOf(map.get(TaxrateProperty.TAXR_NAME)));
                    jsonArray.add(jsonObject);
                }
                JsonUtils.underLine2Camel(jsonArray);
            }
        } catch (Exception e) {
            logger.error("查询所有税率类信息服务出现错误:", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        result.setErrMsg("获取税率列表成功！");
        result.setData(jsonArray);
        return result;
    }
}
