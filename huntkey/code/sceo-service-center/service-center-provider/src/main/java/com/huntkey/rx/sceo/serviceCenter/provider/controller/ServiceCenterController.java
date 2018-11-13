package com.huntkey.rx.sceo.serviceCenter.provider.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.serviceCenter.common.model.FullInputArgument;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.core.MessageUtil;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.DynamicDataSourceContextHolder;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.config.PersistanceConstant;
import com.huntkey.rx.sceo.serviceCenter.provider.service.Persistance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * Created by zhanglb on 2017/6/24.
 */
@RestController
@RequestMapping("/servicecenter")
public class ServiceCenterController {

    private static Logger logger = LoggerFactory.getLogger(ServiceCenterController.class);

    @Autowired
    private Persistance persistance;

    @Autowired
    private MessageUtil messageUtil;

    @Value("${message.send.flag}")
    private boolean isSendFlag;

    /**
     * 合并数据，新增或者修改
     *
     * @param data
     * @return result
     */
    @RequestMapping("/add")
    public Result add(@RequestBody String data) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            FullInputArgument inputArgument = new FullInputArgument(data);
            DynamicDataSourceContextHolder.setDataSourceType(inputArgument.getDataSource());
            Set<String> set = persistance.save(inputArgument);
            result.setData(set);
            if (true == isSendFlag) {
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                for (String str : set) {
                    JSONObject object = new JSONObject();
                    object.put(PersistanceConstant.ID, str);
                    jsonArray.add(object);
                }

                jsonObject.put(PersistanceConstant.IDS, jsonArray);
                jsonObject.put(NodeConstant.EDMNAME, inputArgument.getEdmName());
                jsonObject.put(NodeConstant.SESSIONID, inputArgument.getSessionId()==null?"":inputArgument.getSessionId());
                messageUtil.sendMessageToKafka(jsonObject.toString());
            }
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---add接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 删除数据
     *
     * @param data
     * @return result
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody String data) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            FullInputArgument inputArgument = new FullInputArgument(data);
            DynamicDataSourceContextHolder.setDataSourceType(inputArgument.getDataSource());
            persistance.remove(inputArgument);

            if (true == isSendFlag) {
                JSONArray jsonArray = inputArgument.getParams();
                JSONObject jsonObject = new JSONObject();
                JSONArray array = new JSONArray();
                if (!StringUtil.isNullOrEmpty(jsonArray)) {
                    Iterator<Object> iterator = jsonArray.iterator();
                    while (iterator.hasNext()) {
                        JSONObject object = new JSONObject();
                        Map<String, Object> map = (Map<String, Object>) iterator.next();
                        object.put(PersistanceConstant.ID, map.get(NodeConstant.ID).toString());
                        array.add(object);
                    }
                }
                jsonObject.put(PersistanceConstant.IDS, array);
                jsonObject.put(NodeConstant.EDMNAME, inputArgument.getEdmName());
                jsonObject.put(NodeConstant.SESSIONID, inputArgument.getSessionId()==null?"":inputArgument.getSessionId());
                messageUtil.sendMessageToKafka(jsonObject.toString());
            }
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---delete接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 查询数据
     *
     * @param data
     * @return result
     */
    @RequestMapping("/find")
    public Result find(@RequestBody String data) {
        Long beginTime = System.currentTimeMillis();

        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            FullInputArgument inputArgument = new FullInputArgument(data);
            DynamicDataSourceContextHolder.setDataSourceType(inputArgument.getDataSource());
            JSONObject jsonObject = persistance.find(inputArgument);
            result.setData(jsonObject);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---find接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    /**
     * 删除数据
     *
     * @param data
     * @return result
     */
    @RequestMapping("/update")
    public Result update(@RequestBody String data) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            FullInputArgument inputArgument = new FullInputArgument(data);
            DynamicDataSourceContextHolder.setDataSourceType(inputArgument.getDataSource());
            Object ids = persistance.update(inputArgument);
            result.setData(ids);
            if (true == isSendFlag) {
                Set<String> set = (Set<String>) ids;
                JSONObject jsonObject = new JSONObject();
                JSONArray jsonArray = new JSONArray();
                for (String str : set) {
                    JSONObject object = new JSONObject();
                    object.put(PersistanceConstant.ID, str);
                    jsonArray.add(object);
                }
                jsonObject.put(PersistanceConstant.IDS, jsonArray);
                jsonObject.put(NodeConstant.EDMNAME, inputArgument.getEdmName());
                jsonObject.put(NodeConstant.SESSIONID, inputArgument.getSessionId()==null?"":inputArgument.getSessionId());
                messageUtil.sendMessageToKafka(jsonObject.toString());
            }
        } catch (Exception e) {

            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---upate接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    @RequestMapping("/load")
    public Result load(@RequestBody String data) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            FullInputArgument inputArgument = new FullInputArgument(data);
            DynamicDataSourceContextHolder.setDataSourceType(inputArgument.getDataSource());
            JSONObject jsonObject = persistance.load(inputArgument);
            result.setData(jsonObject);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---load接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    @RequestMapping("/count")
    public Result count(@RequestBody String data) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            FullInputArgument inputArgument = new FullInputArgument(data);
            DynamicDataSourceContextHolder.setDataSourceType(inputArgument.getDataSource());
            long count = persistance.count(inputArgument);
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("count", count);
            result.setData(jsonObject);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        return result;
    }

    @RequestMapping("/richfind")
    public Result richfind(@RequestBody String data) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            FullInputArgument inputArgument = new FullInputArgument(data);
            DynamicDataSourceContextHolder.setDataSourceType(inputArgument.getDataSource());
            JSONObject jsonObject = persistance.richfind(inputArgument);
            result.setData(jsonObject);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---richfind接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    @RequestMapping("/query")
    public Result query(@RequestParam(value = "sql") String sql) {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            result.setData(persistance.query(sql));
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---richfind接口执行时间{}ms", endTime - beginTime);
        return result;
    }
}
