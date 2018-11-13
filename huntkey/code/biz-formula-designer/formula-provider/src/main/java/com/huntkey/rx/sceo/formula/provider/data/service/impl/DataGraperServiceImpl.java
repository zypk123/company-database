package com.huntkey.rx.sceo.formula.provider.data.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.model.TfdClassRelated;
import com.huntkey.rx.sceo.formula.common.model.TfdFormula;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.provider.data.entity.DataSharingCenterParam;
import com.huntkey.rx.sceo.formula.provider.data.entity.FormulaStyle;
import com.huntkey.rx.sceo.formula.provider.data.service.DataGraperService;
import com.huntkey.rx.sceo.formula.provider.data.service.DataSharingCenterService;
import com.huntkey.rx.sceo.formula.provider.engine.dao.TfdFormulaMapper;
import com.huntkey.rx.sceo.formula.provider.engine.feign.EdmdService;
import com.huntkey.rx.sceo.formula.provider.engine.service.FormulaEngineService;
import com.huntkey.rx.sceo.formula.provider.related.classes.service.RelatedClassSettingService;
import com.huntkey.rx.sceo.formula.provider.variant.service.VariantMgrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * @author lulx on 2017/6/14 0014.
 */
@Service
public class DataGraperServiceImpl implements DataGraperService {

    private Logger logger = LoggerFactory.getLogger(DataGraperServiceImpl.class);

    @Autowired
    private RelatedClassSettingService relatedClassSettingService;

    @Autowired
    private TfdFormulaMapper tfdFormulaMapper;

    @Autowired
    private DataSharingCenterService dataSharingCenterService;

    @Autowired
    private VariantMgrService variantMgrService;

    @Autowired
    private FormulaEngineService formulaEngineService;

    @Autowired
    private EdmdService edmdService;

    /**
     * 解析属性集数据
     *
     * @param obj
     * @param formulaStyles
     * @param mainId
     * @param edmName
     * @return
     */
    private JSONArray analyticalData(Object obj, List<FormulaStyle> formulaStyles, String mainId, String edmName) {

        JSONObject mainDataJson = (JSONObject) JSON.toJSON(obj);
        JSONArray dataset = (JSONArray) JSON.toJSON(mainDataJson.get("dataset"));
        JSONArray array = new JSONArray();
        List<String> propIds = new ArrayList<String>();
        for (FormulaStyle formulaStyle : formulaStyles) {

            String[] strs = formulaStyle.getVal().split("_");
            if (strs.length <= 1) {
                continue;
            }

            String propStr = strs[1];
            if (propIds.contains(propStr)) {
                continue;
            }
            propIds.add(propStr);
            Result result = edmdService.getProperty(propStr);
            JSONObject object = (JSONObject) JSON.toJSON(result.getData());

            if (!StringUtils.isEmpty(object.get("edmpParentId"))) {

                //递归查询属性
                List<JSONObject> newList = new ArrayList<JSONObject>();
                recursiveGetData(newList, object);

                if (CollectionUtils.isEmpty(newList)) {
                    continue;
                }

                int len = newList.size() - 1;
                StringBuilder sb = new StringBuilder();
                sb.append(edmName);
                List<String> idsArray = new ArrayList<String>();
                idsArray.add(mainId);
                List<String> newIdsArray = new ArrayList<String>();
                for (int i = len; i >= 0; i--) {
                    JSONObject jsonObject = newList.get(i);
                    sb.append(".");
                    sb.append(jsonObject.getString("edmpCode"));
                    for (String objectId : idsArray) {
                        DataSharingCenterParam param = new DataSharingCenterParam();
                        param.setEdmName(sb.toString());
                        param.buildCondition2Add("pid", "=", objectId);
                        JSONObject pidObject = (JSONObject) JSON.toJSON(dataSharingCenterService.search(param));
                        JSONArray jsonArray = (JSONArray) JSONArray.toJSON(pidObject.get("dataset"));
                        for (Object o : jsonArray) {
                            JSONObject newJson = (JSONObject) o;
                            if (i == 0) {
                                array.add(newJson);
                            } else {
                                newIdsArray.add(newJson.getString("id"));
                            }
                        }
                    }
                    if (!CollectionUtils.isEmpty(newIdsArray)) {
                        idsArray = newIdsArray;
                        newIdsArray = new ArrayList<String>();
                    }
                }
            } else {
                for (Object o : dataset) {
                    array.add(o);
                }
            }
        }
        return array;
    }

    /**
     * 递归取数据
     *
     * @param propList
     * @param propObject
     */
    private void recursiveGetData(List<JSONObject> propList, JSONObject propObject) {

        if ("assemble".equals(propObject.getString("edmpValueType"))) {
            propList.add(propObject);
        }

        //根据当前属性查询上级属性
        String edmpParentId = propObject.getString("edmpParentId");
        if (StringUtils.isEmpty(edmpParentId)) {
            return;
        }

        //查询属性集列表
        Result result = edmdService.getProperty(edmpParentId);
        JSONObject object = (JSONObject) JSON.toJSON(result.getData());
        recursiveGetData(propList, object);
    }


    @Override
    public void grapData(TvmVariant variant, DataProvider provider) {

        logger.info("Grap data start ... ");
        long startTime = System.currentTimeMillis();

        logger.info("Grap data -> variant id: {}", variant.getVrntId());
        logger.info("Params passed in: {}", provider.getDataContext());

        TfdFormula formula = tfdFormulaMapper.selectByPrimaryKey(variant.getVrntFormulaId());
        logger.info("formula content: {}", formula.getFrmuFormulaContent());

        // There are many mappings in formula style list.
        logger.info("formulaStyle: {}", formula.getFrmuFormulaStyle());
        List<FormulaStyle> formulaStyles = JSON.parseArray(formula.getFrmuFormulaStyle(), FormulaStyle.class);
        logger.info("Parsing the formula styles to a list: {}", formulaStyles);


        Map<String, Object> dataMap = new HashMap<String, Object>();

        if (provider.getDataContext().get("id") != null
                && provider.getDataContext().get("edmName") != null) {

            // get the main class id and use it to load data.
            String mainId = provider.getDataContext().get("id").toString();
            String edmName = provider.getDataContext().get("edmName").toString();

            logger.info("mainId: {}, edmName: {}", mainId, edmName);


            Map<String, String> mainDataTypeMap = parseEdmdType(edmName);
            DataSharingCenterParam mainParam = new DataSharingCenterParam();
            mainParam.setEdmName(edmName);
            mainParam.getSearch().setType("ref");
            mainParam.getSearch().addId(mainId);

            Object mainDataObj = dataSharingCenterService.load(mainParam);
            JSONArray mainDataArray = analyticalData(mainDataObj, formulaStyles, mainId, edmName);

            if (!CollectionUtils.isEmpty(mainDataArray)) {
                Map<String, Object> mainDataMap = doParse(mainDataArray, mainDataTypeMap);
                doTransfer(mainDataMap, dataMap, formulaStyles, null, null);
            }
        } else {
            logger.warn("mainId: {}, edmName: {}", provider.getDataContext().get("id"),
                    provider.getDataContext().get("edmName"));
        }

        List<TfdClassRelated> rClasses = relatedClassSettingService.loadRelatedClasses(variant.getVrntFormulaId());
        Map<TfdClassRelated, List<TplCondition>> conditionCache = new HashMap<TfdClassRelated, List<TplCondition>>();
        List<TfdClassRelated> rClassesSorted = sortClassRelatedToQuery(rClasses, conditionCache);

        for (TfdClassRelated r : rClassesSorted) {
            List<TplCondition> conditions = conditionCache.get(r);
            DataSharingCenterParam param = new DataSharingCenterParam();
            param.setEdmName(r.getClssEdmcNameEn());

            // ORM query do not support OR operator.
            // so, now just simulate AND operator.
            for (TplCondition c : conditions) {
                String op = c.getCndtOperate();
                String propOriginCode = c.getCndtPropOriginCode();
                String valueType = c.getCndtValueType();
                String valueOriginCode = c.getCndtValueOriginCode();

                // if valueOriginCode is class type, should get data from dataContext.
                // if valueOriginCode is variable type, should calculate it.
                // if valueOriginCode is const type, use it directly.
                if ("class".equals(valueType)) {
                    Object len = provider.getDataContext().get(valueOriginCode + "_len");
                    if (Integer.parseInt(len.toString()) != 1) {
                        throw new RuntimeException("The record size of " + valueOriginCode + " is more than one and cartesian product will be happened.");
                    }

                    // get the value from provider.
                    valueOriginCode = ((Object[]) provider.getDataContext().get(valueOriginCode))[0].toString();
                    logger.info("value origin code is 'class': {}", valueOriginCode);
                } else if ("variable".equals(valueType)) {
                    TvmVariant variantInner = variantMgrService.getVariantById(valueOriginCode);
                    TfdFormula varFormula = tfdFormulaMapper.selectByPrimaryKey(variant.getVrntFormulaId());
                    logger.info("var's formula content: {}", varFormula.getFrmuFormulaContent());
                    List<FormulaStyle> varFormulaStyles = JSON.parseArray(varFormula.getFrmuFormulaStyle(), FormulaStyle.class);
                    formulaStyles.addAll(varFormulaStyles);

                    Object value = formulaEngineService.variantCalc(variantInner, provider);
                    valueOriginCode = value.toString();
                    logger.info("value origin code is 'variable': {}", valueOriginCode);
                } else {
                    // nothing to do.
                }
                // build query condition.
                param.buildCondition2Add(propOriginCode, op, valueOriginCode);
            }

            Object relatedClassData = dataSharingCenterService.search(param);
            String id="";
            if(!StringUtil.isNullOrEmpty(provider.getDataContext().get("id"))) {
                id = provider.getDataContext().get("id").toString();
            }
                //解析属性集
                JSONArray mainDataArray = analyticalData(relatedClassData, formulaStyles, id, r.getClssEdmcNameEn());
                String rClassId = r.getClssId();
                String rClassName = r.getClssAliasName();

                Map<String, String> dataTypeMap = parseEdmdType(r.getClssEdmcNameEn());
                Map<String, Object> flatDataMap = doParse(mainDataArray, dataTypeMap);

                logger.info("flatDataMap: {}", flatDataMap);
                doTransfer(flatDataMap, dataMap, formulaStyles, rClassId, rClassName);


        }

        logger.info("dataMap: {}", dataMap);
        provider.getDataContext().putAll(dataMap);

        long endTime = System.currentTimeMillis();
        logger.info("Grap data end, cost time: {}", (endTime - startTime));
    }

    private void doTransfer(Map<String, Object> flatDataMap, Map<String, Object> dataMap,
                            List<FormulaStyle> formulaStyles, String rClassId, String rClassName) {

        for (String key : flatDataMap.keySet()) {
            String key4Data = null;
            if (null != rClassId) {
                key4Data = searchVal(formulaStyles, key, rClassId);
            } else {
                key4Data = searchVal(formulaStyles, key);
            }

            if (null != key4Data) {
                key4Data = "prop_" + key4Data;
                dataMap.put(key4Data, flatDataMap.get(key));
                dataMap.put(key4Data + "_len", flatDataMap.get(key + "_len"));
            }

            if (null != rClassName) {
                String key4RClass = rClassName + "." + key;
                dataMap.put(key4RClass, flatDataMap.get(key));
                dataMap.put(key4RClass + "_len", flatDataMap.get(key + "_len"));
            }
        }
    }

    private void parseJSONObjectRecurse(Object obj, Map<String, String> dataTypeMap, Map<String, List> dataHolder, Map<String, Object> instanceHolder) {

        if (obj instanceof JSONArray) {
            JSONArray arr = (JSONArray) obj;
            for (int i = 0; i < arr.size(); i++) {
                JSONObject jsonObj = (JSONObject) arr.get(i);
                parseJSONObjectRecurse(jsonObj, dataTypeMap, dataHolder, instanceHolder);
            }
        } else {
            JSONObject jsonObject = (JSONObject) obj;
            Set<String> keySet = jsonObject.keySet();
            for (String key : keySet) {
                Object value = jsonObject.get(key);
                if (value instanceof JSONObject
                        || value instanceof JSONArray) {
                    parseJSONObjectRecurse((JSONObject) value, dataTypeMap, dataHolder, instanceHolder);
                } else {
                    String dataType = dataTypeMap.get(key);
                    if (null == instanceHolder.get(key)) {
                        instanceHolder.put(key, getDataTypeInstance(dataType));
                    }
                    putData(dataHolder, instanceHolder, key, value.toString());
                }
            }
        }
    }

    private Map<String, Object> doFlatMap(Map<String, List> dataHolder) {

        Map<String, Object> flatMap = new HashMap<String, Object>();
        for (String key : dataHolder.keySet()) {
            if (dataHolder.get(key).size() == 1) {
                flatMap.put(key, dataHolder.get(key).toArray()[0]);
            } else {
                flatMap.put(key, dataHolder.get(key).toArray());
            }
            flatMap.put(key + "_len", dataHolder.get(key).size());
        }

        return flatMap;
    }

    private Map<String, Object> doParse(Object dataObj, Map<String, String> dataType) {

        Map<String, List> dataHolder = new HashMap<String, List>();
        Map<String, Object> instanceHolder = new HashMap<String, Object>();
        //Object data = ((Map<String, String>) dataObj).get("dataset");
       // logger.info("parse -> data: {}", data);
        parseJSONObjectRecurse(JSONArray.toJSON(dataObj), dataType, dataHolder, instanceHolder);

        return doFlatMap(dataHolder);
    }

    private String searchVal(List<FormulaStyle> formulaStyles, String key, String rClassId) {

        logger.info("enter searchVal method. key: {}, formulaStyles: {}", key, formulaStyles);
        if (null != formulaStyles) {
            for (FormulaStyle fs : formulaStyles) {
                if ("prop".equals(fs.getType()) && fs.getVal().endsWith(key)
                        && fs.getVal().startsWith(rClassId)) {
                    return fs.getVal();
                }
            }
        }
        return null;
    }

    private String searchVal(List<FormulaStyle> formulaStyles, String key) {

        logger.info("enter searchVal method. key: {}, formulaStyles: {}", key, formulaStyles);
        if (null != formulaStyles) {
            for (FormulaStyle fs : formulaStyles) {
                if ("prop".equals(fs.getType()) && fs.getVal().endsWith(key)) {
                    return fs.getVal();
                }
            }
        }
        return null;
    }

    private Map<String, String> parseEdmdType(String edmName) {

        Result result = edmdService.getEdmDataType("V1.0", edmName);
        Object dataType = result.getData();

        if (null == dataType) {
            throw new RuntimeException("The edmName: " + edmName + " has no date type.");
        }

        List<Map<String, String>> dataList = (List<Map<String, String>>) result.getData();

        logger.info("dataList: {}", dataList);
        logger.info("dataList.size: {}", dataList.size());

        Map<String, String> dataTypeMap = new HashMap<String, String>();

        for (Map<String, String> descMap : dataList) {

            for (String key : descMap.keySet()) {
                logger.info(key + ", " + descMap.get(key));
                String[] arr = key.split("\\.");
                logger.info("key: {}, spilt by . and length: {}", key, arr.length);
                String fieldName = arr[arr.length - 1];
                String value = descMap.get(key);

                if (value == null || "null".equals(value.trim())) {
                    continue;
                }
                dataTypeMap.put(fieldName, value);
            }
        }

        return dataTypeMap;
    }

    private Object getDataTypeInstance(String dataType) {

        String zero = "0";
        if ("int".equals(dataType)) {
            return new Integer(zero);
        } else if ("boolean".equals(dataType)) {
            return new Boolean(true);
        } else if ("num".equals(dataType)) {
            return new Double(zero);
        } else if ("number".equals(dataType)) {
            return new Double(zero);
        } else if ("date".equals(dataType)) {
            return new Date();
        } else if ("logic".equals(dataType)) {
            return new Boolean(true);
        } else if ("decimal".equals(dataType)) {
            return new Double(zero);
        } else if ("float".equals(dataType)) {
            return new Double(zero);
        } else {
            return new String("0");
        }
    }

    private void putData(Map<String, List> dataHolder, Map<String, Object> instanceHolder, String key, String data) {

        if (null == dataHolder.get(key)) {
            dataHolder.put(key, createDataList(instanceHolder.get(key)));
        }

        dataHolder.get(key).add(DataParser.parse(data, instanceHolder.get(key)));
    }

    private <T> List<T> createDataList(T t) {

        String className = t.getClass().getSimpleName();
        Object obj = null;
        if ("Integer".equals(className)) {
            obj = new ArrayList<Integer>();
        } else if ("Float".equals(className)) {
            obj = new ArrayList<Float>();
        } else if ("Double".equals(className)) {
            obj = new ArrayList<Double>();
        } else if ("Boolean".equals(className)) {
            obj = new ArrayList<Boolean>();
        } else {
            obj = new ArrayList<String>();
        }

        return (List<T>) obj;
    }

    private List<TfdClassRelated> sortClassRelatedToQuery(List<TfdClassRelated> rClasses,
                                                          Map<TfdClassRelated, List<TplCondition>> conditionCache) {

        logger.info("sort class related to grapping data from DATA SERVICE CENTER.");
        StringBuffer buff = new StringBuffer();
        for (TfdClassRelated cr : rClasses) {
            buff.append(cr.getClssAliasName() + ", ");
        }
        if (buff.length() > 2) {
            buff.setLength(buff.length() - 2);
        }
        logger.info("ClassRelatedPool: [ {} ].", buff.toString());

        List<TfdClassRelated> rClassesSorted = new ArrayList<TfdClassRelated>();
        List<TfdClassRelated> notRefRelatedClassList = new ArrayList<TfdClassRelated>();
        List<TwoTuples> refRelatedClassList = new ArrayList<TwoTuples>();

        for (TfdClassRelated r : rClasses) {
            List<TplCondition> conditions = relatedClassSettingService.queryCondisionsByClssId(r.getClssId());
            conditionCache.put(r, conditions);

            StringBuffer condBuff = new StringBuffer();
            for (TplCondition cond : conditions) {
                condBuff.append(cond.getCndtProp() + " -> " + cond.getCndtPropOriginCode()
                        + " : " + cond.getCndtValue() + " -> " + cond.getCndtValueOriginCode() + ", ");
            }
            if (condBuff.length() > 2) {
                condBuff.setLength(condBuff.length() - 2);
            }
            logger.info("RelatedClass: {} has conditions: [{}]", r.getClssAliasName(), condBuff.toString());

            for (TplCondition c : conditions) {

                String cndtValueOriginCode = c.getCndtValueOriginCode();
                if ("class".equals(c.getCndtValueType())) {
                    String classId = cndtValueOriginCode.split(".")[0];
                    TfdClassRelated to = null;
                    if ((to = getFromRelatedClassPool(classId, rClasses)) != null) {
                        refRelatedClassList.add(new TwoTuples(r, to));
                        logger.info("RClass: {} --> RClass: {}", r.getClssAliasName(), to.getClssAliasName());
                    }
                } else if ("variable".equals(c.getCndtValueType())) {
                    String formulaId = variantMgrService.getVariantById(cndtValueOriginCode).getVrntFormulaId();
                    TfdFormula formula = tfdFormulaMapper.selectByPrimaryKey(formulaId);
                    List<FormulaStyle> formulaStyles = JSON.parseArray(formula.getFrmuFormulaStyle(), FormulaStyle.class);

                    for (FormulaStyle fs : formulaStyles) {
                        String type = fs.getType();
                        if ("prop".equals(type)) {
                            String val = fs.getVal();
                            String rClassId = val.split("_")[0];
                            TfdClassRelated to = null;
                            if ((to = getFromRelatedClassPool(rClassId, rClasses)) != null) {
                                refRelatedClassList.add(new TwoTuples(r, to));
                                logger.info("RClass: {} --> RClass: {}", r.getClssAliasName(), to.getClssAliasName());
                            }
                        }
                    }
                }
            }

            if (!isInRefRelatedClassList(r, refRelatedClassList)) {
                notRefRelatedClassList.add(r);
            }
        }

        StringBuffer notRefBuff = new StringBuffer();
        for (TfdClassRelated cr : notRefRelatedClassList) {
            notRefBuff.append(cr.getClssAliasName() + ", ");
        }
        if (notRefBuff.length() > 2) {
            notRefBuff.setLength(notRefBuff.length() - 2);
        }
        logger.info("NotRefRelatedClassList: {}", notRefBuff.toString());

        rClassesSorted.addAll(notRefRelatedClassList);

        for (TwoTuples tt : refRelatedClassList) {
            TfdClassRelated from = tt.getFrom();
            TfdClassRelated to = tt.getTo();

            int indexTo = rClassesSorted.indexOf(to);
            if (indexTo == -1) {
                rClassesSorted.add(to);
            }

            int indexFrom = rClassesSorted.indexOf(from);
            if (indexFrom == -1) {
                indexTo = rClassesSorted.indexOf(to);
                rClassesSorted.add(indexTo + 1, from);
            }
        }

        StringBuffer sortedBuffer = new StringBuffer();
        for (TfdClassRelated cr : rClassesSorted) {
            sortedBuffer.append(cr.getClssAliasName() + ", ");
        }
        if (sortedBuffer.length() > 2) {
            sortedBuffer.setLength(sortedBuffer.length() - 2);
        }

        logger.info("SortedRelatedClass: {}", sortedBuffer.toString());
        return rClassesSorted;
    }

    private boolean isInRefRelatedClassList(TfdClassRelated from, List<TwoTuples> refRelatedClassList) {

        for (TwoTuples tt : refRelatedClassList) {
            if (tt.getFrom().equals(from)) {
                return true;
            }
        }

        return false;
    }

    private TfdClassRelated getFromRelatedClassPool(String classId, List<TfdClassRelated> rClasses) {

        for (TfdClassRelated r : rClasses) {
            if (r.getClssId().equals(classId)) {
                return r;
            }
        }

        return null;
    }

    /**
     * record the relationship of related-class's reference.
     */
    static class TwoTuples {

        private TfdClassRelated from;
        private TfdClassRelated to;

        public TwoTuples(TfdClassRelated from, TfdClassRelated to) {
            this.from = from;
            this.to = to;
        }

        public TfdClassRelated getFrom() {
            return from;
        }

        public void setFrom(TfdClassRelated from) {
            this.from = from;
        }

        public TfdClassRelated getTo() {
            return to;
        }

        public void setTo(TfdClassRelated to) {
            this.to = to;
        }
    }

    static class DataParser {

        public static <T> T parse(String data, T t) {
            String className = t.getClass().getSimpleName();
            Object obj = null;
            if ("Integer".equals(className)) {
                obj = Integer.parseInt(data);
            } else if ("Float".equals(className)) {
                obj = Float.parseFloat(data);
            } else if ("Double".equals(className)) {
                obj = Double.parseDouble(data);
            } else if ("Byte".equals(className)) {
                obj = Byte.parseByte(data);
            } else if ("Short".equals(className)) {
                obj = Short.parseShort(data);
            } else if ("Boolean".equals(className)) {
                obj = Boolean.parseBoolean(data);
            } else {
                obj = data;
            }

            return (T) obj;
        }
    }


}
