package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.SchoolEntity;
import com.huntkey.rx.hr.common.model.DeptTreeDTO;
import com.huntkey.rx.hr.common.model.SchoolDTO;
import com.huntkey.rx.hr.provider.service.SchoolService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by weijian on 2017/12/14.
 */
@Service
public class SchoolServiceImpl implements SchoolService{
    @Autowired
    OrmService ormService;
    @Override
    public Result getSchools(String serchContent) {
        Result result = new Result();
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn("id").addColumn("rsch_city").addColumn("rsch_website").addColumn("rsch_code").addColumn("rsch_name").addColumn("rsch_ranking");
        String whereCondition = OrmParam.and(ormParam.getEqualXML("rsch_enable", "1"),
                OrmParam.or(ormParam.getMatchMiddleXML("rsch_code", serchContent),ormParam.getMatchMiddleXML("rsch_name", serchContent)));
        ormParam.setWhereExp(whereCondition);
        JSONArray jsonArray = new JSONArray();
        try {
            List<Map<String, Object>> list = ormService.selectMapList(SchoolEntity.class,ormParam);
            for(Map<String, Object> map:list){
                JSONObject jsonObject = new JSONObject();
                jsonObject = (JSONObject) JSON.toJSON(map);
                jsonArray.add(jsonObject);
            }
            List<SchoolDTO> schoolList = jsonArray.stream().map(obj -> JSONObject.toJavaObject((JSONObject)JSON.toJSON(obj), SchoolDTO.class)).collect(Collectors.toList());
            result.setData(schoolList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }
}
