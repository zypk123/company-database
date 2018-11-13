package com.huntkey.rx.sceo.formula.provider.data.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.data.service.DataGraperService;
import com.huntkey.rx.sceo.formula.provider.data.service.DataSharingCenterService;
import com.huntkey.rx.sceo.formula.provider.engine.feign.BizModelerService;
import com.huntkey.rx.sceo.formula.provider.engine.feign.DataSharingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lulx on 2017/6/14 0014.
 */
@RestController
@RequestMapping("/dataGraper")
public class DataGraperController {

    @Autowired
    private DataSharingService dataSharingService;

    @Autowired
    private BizModelerService bizModelerService;

    @RequestMapping(value = "/findWordListById", method = RequestMethod.POST)
    public Result findWordListById(@RequestParam(required = false, defaultValue = "", value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {

            JSONObject jsonObject = new JSONObject();
            jsonObject.put("edmName", "wordlist");
            JSONObject search = new JSONObject();
            JSONArray jsonArray = new JSONArray();
            JSONObject cell = new JSONObject();
            cell.put("attr", "info_code");
            cell.put("operator", "=");
            cell.put("value", id);
            jsonArray.add(cell);
            JSONObject cell1 = new JSONObject();
            cell1.put("attr", "word_par");
            cell1.put("operator", "is");
            cell1.put("value", "null");
            jsonArray.add(cell1);
            List<String> propertyCodeList = new ArrayList();
            propertyCodeList.add("id");
            search.put("columns", propertyCodeList);
            search.put("conditions", jsonArray);
            jsonObject.put("search", search);
            String params = JSONObject.toJSONString(jsonObject);
            result = dataSharingService.find(params);
            JSONObject object = (JSONObject) JSONObject.toJSON(result.getData());
            JSONArray array = (JSONArray)JSONArray.toJSON(object.get("dataset"));
            if(array == null || array.size() == 0){
                return result;
            }
            JSONObject o = (JSONObject) array.get(0);
            String str = o.getString("id");
            propertyCodeList.add("word_name");
            jsonArray.clear();
            cell1.put("attr", "word_par");
            cell1.put("operator", "=");
            cell1.put("value", str);
            jsonArray.add(cell1);
            search.put("columns", propertyCodeList);
            search.put("conditions", jsonArray);
            jsonObject.put("search", search);
            params = JSONObject.toJSONString(jsonObject);
            result = dataSharingService.find(params);
        } catch (Exception e) {
            throw new RuntimeException("调用ORM接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/findClassById/{id}", method = RequestMethod.GET)
    public Result findClassById(@PathVariable("id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            result = bizModelerService.getClassById(id);
        } catch (Exception e) {
            throw new RuntimeException("调用ORM接口出错", e);
        }
        return result;
    }

}
