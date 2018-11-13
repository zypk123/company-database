package com.huntkey.rx.sceo.formula.provider.related.condition.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.collection.ListUtils;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.model.*;
import com.huntkey.rx.sceo.formula.common.model.vo.*;
import com.huntkey.rx.sceo.formula.common.params.OrmOperator;
import com.huntkey.rx.sceo.formula.provider.engine.feign.BizModelerService;
import com.huntkey.rx.sceo.formula.provider.related.classes.service.impl.RelatedClassSettingServiceImpl;
import com.huntkey.rx.sceo.formula.provider.related.condition.dao.TacConditionRelatedMapper;
import com.huntkey.rx.sceo.formula.provider.related.condition.dao.TacPropertyRelatedMapper;
import com.huntkey.rx.sceo.formula.provider.related.condition.service.PropRelatedService;
import com.huntkey.rx.sceo.formula.provider.related.condition.service.RelatedConditionSettingService;
import com.huntkey.rx.sceo.formula.provider.utils.JexlUtil;
import com.huntkey.rx.sceo.formula.provider.utils.ORMUtils;
import com.huntkey.rx.sceo.formula.provider.utils.RelCondValType;
import com.huntkey.rx.sceo.formula.provider.utils.RestUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 关联条件设置Service接口实现类
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@Service
@Transactional(readOnly = true, rollbackFor = Exception.class)
public class RelatedConditionSettingServiceImpl<M extends TacConditionRelated> extends BaseService<M> implements RelatedConditionSettingService {

    private final Logger log = LoggerFactory.getLogger(RelatedConditionSettingServiceImpl.class);

    @Autowired
    private TacConditionRelatedMapper tacConditionRelatedMapper;

    @Autowired
    TacPropertyRelatedMapper tacPropertyRelatedMapper;

    @Autowired
    RelatedConditionSettingServiceImpl relatedConditionSettingServiceImpl;

    @Autowired
    RelatedClassSettingServiceImpl relatedClassSettingServiceImpl;

    @Autowired
    PropRelatedService propRelatedService;

    @Autowired
    private BizModelerService bizModelerService;


    private final String ORM_URL = "http://SERVICECENTER-PROVIDER/servicecenter/find";
    //private final String ORM_URL = "http://10.3.98.155:2008/servicecenter/find";

    private final String ORM_URL_RICH = "http://SERVICECENTER-PROVIDER/servicecenter/richfind";

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int saveConditionRelated(TacConditionRelated tacConditionRelated) {
        relatedConditionSettingServiceImpl.saveSetting(tacConditionRelated);
        return tacConditionRelatedMapper.insertSelective(tacConditionRelated);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updateConditionRelated(TacConditionRelated tacConditionRelated) {
        relatedConditionSettingServiceImpl.updateSetting(tacConditionRelated);
        return tacConditionRelatedMapper.updateByPrimaryKeySelective(tacConditionRelated);
    }

    @Override
    public List queryAllConditionsRelated() {
        TacConditionRelatedExample example = new TacConditionRelatedExample();
        List<TacConditionRelated> tacConditionRelateds = tacConditionRelatedMapper.selectByExample(example);
        return tacConditionRelateds;
    }

    @Override
    public TacConditionRelated queryConditionsRelatedByCndrId(String cndrId) {
        TacConditionRelated tacConditionRelated = tacConditionRelatedMapper.selectByPrimaryKey(cndrId);
        return tacConditionRelated;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int removeConditionRelated(String cndrId) {
        return tacConditionRelatedMapper.updateIsEnaleByPrimaryKey(cndrId);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int savePropRelated(TacPropertyRelated tacPropertyRelated) {
        return propRelatedService.savePropRelated(tacPropertyRelated);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public int updatePropRelated(TacPropertyRelated tacPropertyRelated) {
        return propRelatedService.updatePropRelated(tacPropertyRelated);
    }

    @Override
    public PropertyRelatedVo getPropertyRelatedCondition(String prplId) {
        PropertyRelatedVo propertyRelatedVo = new PropertyRelatedVo();
        propertyRelatedVo.setRelatedProperty(getPropertyRelatedById(prplId));
        propertyRelatedVo.setRelatedConditions(tacConditionRelatedMapper.getConditionRelatedListByPrplId(prplId));
        return propertyRelatedVo;
    }

    @Override
    public TacPropertyRelated getPropertyRelatedById(String prplId) {
        return tacPropertyRelatedMapper.selectByPrimaryKey(prplId);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdateConditionRelated(TacConditionRelated tacConditionRelated) {
        String cndrId = tacConditionRelated.getCndrId();
        // 新增
        if (StringUtil.isNullOrEmpty(cndrId)) {
            return saveConditionRelated(tacConditionRelated);
        } else {
            return updateConditionRelated(tacConditionRelated);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdatePropRelated(TacPropertyRelated tacPropertyRelated) {
        String prplId = tacPropertyRelated.getPrplId();
        // 新增
        if (StringUtil.isNullOrEmpty(prplId)) {
            return savePropRelated(tacPropertyRelated);
        } else {
            return updatePropRelated(tacPropertyRelated);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdatePropRelatedAndConditions(PropertyRelatedVo propertyRelatedVo) {
        int retIntSavePropRelated = -1;
        int retIntSaveCndt = -1;
        retIntSavePropRelated = saveOrUpdatePropRelated(propertyRelatedVo.getRelatedProperty());
        String prplId = propertyRelatedVo.getRelatedProperty().getPrplId();
        for (TacConditionRelated conditionRelated : propertyRelatedVo.getRelatedConditions()) {
            conditionRelated.setCndrPropRelatedId(prplId);
            retIntSaveCndt = saveOrUpdateConditionRelated(conditionRelated);
        }
        if (retIntSavePropRelated == 1 && retIntSaveCndt == 1) {
            return 1;
        } else {
            return -1;
        }
    }

    @Override
    public String getPrplConditionDescByPrplId(String prplId) {
        return tacPropertyRelatedMapper.getPrplConditionDescByPrplId(prplId);
    }

    @Override
    public Map<String, Object> getPrplConditionDescByPrplIdArr(List<String> prplIdArr1, List<String> prplIdArr2) {
        Map<String, Object> retMap = new HashMap<String, Object>();
        if (!StringUtil.isNullOrEmpty(prplIdArr1) && prplIdArr1.size() > 0) {
            Map<String, String> map1 = new HashMap<String, String>();
            Map<String, String> map4 = new HashMap<String, String>();
            List<TacPropertyRelated> tacPropertyRelatedList1 = this.getTacPropertyRelateds(prplIdArr1);
            for (TacPropertyRelated tacPropertyRelated1 : tacPropertyRelatedList1) {
                map1.put(tacPropertyRelated1.getPrplId(), tacPropertyRelated1.getPrplConditionName());
                map4.put(tacPropertyRelated1.getPrplId(), tacPropertyRelated1.getPrplConditionDesc());
            }
            retMap.put("conds", map1);
            retMap.put("condDescs", map4);
        }
        if (!StringUtil.isNullOrEmpty(prplIdArr2) && prplIdArr2.size() > 0) {
            Map<String, String> map2 = new HashMap<String, String>();
            Map<String, String> map3 = new HashMap<String, String>();
            List<TacPropertyRelated> tacPropertyRelatedList2 = this.getTacPropertyRelateds(prplIdArr2);
            for (TacPropertyRelated tacPropertyRelated2 : tacPropertyRelatedList2) {
                map2.put(tacPropertyRelated2.getPrplId(), tacPropertyRelated2.getPrplConditionName());
                map3.put(tacPropertyRelated2.getPrplId(), tacPropertyRelated2.getPrplConditionDesc());
            }
            retMap.put("formulas", map2);
            retMap.put("formulaDescs", map3);
        }
        return retMap;
    }

    /**
     * 通过关联属性ID数组查找关联属性
     *
     * @param prplIds
     * @return
     */
    private List<TacPropertyRelated> getTacPropertyRelateds(List<String> prplIds) {
        TacPropertyRelatedExample example = new TacPropertyRelatedExample();
        TacPropertyRelatedExample.Criteria criteria = example.createCriteria();
        criteria.andPrplIdIn(prplIds);
        return tacPropertyRelatedMapper.selectByExample(example);
    }

    @Override
    public Result relCondDataByPro(String prplId, String dataId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        PropertyRelatedVo propertyRelatedCondition = getPropertyRelatedCondition(prplId);
        if (StringUtil.isNullOrEmpty(propertyRelatedCondition.getRelatedProperty()) || StringUtil.isNullOrEmpty(propertyRelatedCondition.getRelatedConditions())) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("错误的关联对象公式ID");
            return result;
        }
        TacPropertyRelated relatedProperty = propertyRelatedCondition.getRelatedProperty();
        List<TacConditionRelated> relatedConditions = propertyRelatedCondition.getRelatedConditions();
        String conditionFormula = relatedProperty.getPrplConditionFormula();
        List<String> operList = new ArrayList<>();
        List<String> condSeqList = new ArrayList<>();
        getOpertor(conditionFormula,operList);
        getConditionSeqs(conditionFormula,condSeqList);
        log.info("关联对象定位公式 : propertyRelatedCondition : ", propertyRelatedCondition);
        // 查询被关联类 orm数据
        String prplClass1NameEn = relatedProperty.getPrplClass1NameEn();
        String prplProp1Code = relatedProperty.getPrplProp1Code();
        List<ConditionsVo> conditionsPrplClass1 = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass1 = new ConditionsVo();
        if(prplProp1Code.indexOf(".")!=-1){
            //判断被关联类的关联属性是否是属性集属性，如果是，则需要拼接属性集edmName
            String  prplPropClass1NameEn = prplClass1NameEn  + "." + prplProp1Code.substring(0, prplProp1Code.lastIndexOf("."));
            List<ConditionsVo> condPrplPropClass = new ArrayList<>();
            ConditionsVo conditionsProp = new ConditionsVo();
            String propDataId = dataId;
            conditionsProp.setAttr("id");
            conditionsProp.setOperator("=");
            conditionsProp.setValue(propDataId);
            condPrplPropClass.add(conditionsProp);
            EsHbaseCriteriaVo queryPrplPropClass1 = ORMUtils.getEsHbaseCriteria(prplPropClass1NameEn,null,condPrplPropClass,null);
            String restPrplPropClass = RestUtils.doPost(ORM_URL,queryPrplPropClass1);
            List<Map<String,Object>> propDataSet = ORMUtils.getDataSet(restPrplPropClass);
            if(!StringUtil.isNullOrEmpty(propDataSet)&&propDataSet.size()>0){
                //根据属性集id获取对应的对象id
                dataId = (String)propDataSet.get(0).get("pid");
            }
        }
        condPrplClass1.setAttr("id");
        condPrplClass1.setOperator("=");
        condPrplClass1.setValue(dataId);
        conditionsPrplClass1.add(condPrplClass1);

        EsHbaseCriteriaVo queryPrplClass1 = ORMUtils.getEsHbaseCriteria(prplClass1NameEn, null, conditionsPrplClass1, null);
        log.info("ORM query condition vo : queryPrplClass1 : ", queryPrplClass1);
        String restPrplClass1 = RestUtils.doPost(ORM_URL, queryPrplClass1);
        log.info("ORM query result vo : restPrplClass1 : ", restPrplClass1);
        // 关联类 orm 查询
        // 关联类名
        String prplClass2NameEn = relatedProperty.getPrplClass2NameEn();
        //标志位，当关联对象的条件中不包含属性集时为true，采用原有处理逻辑判断，当条件中包含属性集时为false，需要单独计算
        boolean flag = true;
        for (TacConditionRelated relatedCondition : relatedConditions) {
            //当关联类在左边时，取左边的条件 or 当关联类在右边时，取右边的条件
            if ((relatedCondition.getCndrClass1NameEn().equals(prplClass2NameEn) && relatedCondition.getCndrPropOriginCode().indexOf(".") != -1)
                    || (!StringUtil.isNullOrEmpty(relatedCondition.getCndrClass2NameEn())&&!StringUtil.isNullOrEmpty(relatedCondition.getCndrProp2OriginCode())&&relatedCondition.getCndrClass2NameEn().equals(prplClass2NameEn) && relatedCondition.getCndrProp2OriginCode().indexOf(".") != -1)) {
                flag = false;
                break;
            }
        }
        //保存被关联类每个用到的属性集数据
        Map<String,String> restPrplPropClasses = new HashMap<>();
        for(TacConditionRelated relatedCondition : relatedConditions){
            if (!isUseCondtion(relatedCondition.getCndrSeq(), conditionFormula)) {
                continue;
            }
            //判断被关联类条件是否包含属性集
            if((relatedCondition.getCndrClass1NameEn().equals(prplClass1NameEn)&&relatedCondition.getCndrPropOriginCode().indexOf(".")!=-1)){
                if(!restPrplPropClasses.containsKey(relatedCondition.getCndrProp1Code())) {
                    String edmName = prplClass1NameEn + "." + relatedCondition.getCndrProp1Code().substring(0, relatedCondition.getCndrProp1Code().lastIndexOf("."));
                    List<ConditionsVo> conditionsVos = new ArrayList<>();
                    ConditionsVo conditionsVo = new ConditionsVo();
                    conditionsVo.setAttr("pid");
                    conditionsVo.setOperator("=");
                    conditionsVo.setValue(dataId);
                    conditionsVos.add(conditionsVo);
                    EsHbaseCriteriaVo esHbaseCriteriaVo = ORMUtils.getEsHbaseCriteria(edmName, null, conditionsVos, null);
                    String propClass = RestUtils.doPost(ORM_URL, esHbaseCriteriaVo);
                    restPrplPropClasses.put(relatedCondition.getCndrProp1Code(), propClass);
                }
            }
            if(!StringUtil.isNullOrEmpty(relatedCondition.getCndrClass2NameEn())&&!StringUtil.isNullOrEmpty(relatedCondition.getCndrProp2OriginCode())&&relatedCondition.getCndrClass2NameEn().equals(prplClass1NameEn)&&relatedCondition.getCndrProp2OriginCode().indexOf(".")!=-1){
                if(!restPrplPropClasses.containsKey(relatedCondition.getCndrProp2Code())) {
                    String edmName = prplClass1NameEn + "." + relatedCondition.getCndrProp2Code().substring(0, relatedCondition.getCndrProp2Code().lastIndexOf("."));
                    List<ConditionsVo> conditionsVos = new ArrayList<>();
                    ConditionsVo conditionsVo = new ConditionsVo();
                    conditionsVo.setAttr("pid");
                    conditionsVo.setOperator("=");
                    conditionsVo.setValue(dataId);
                    conditionsVos.add(conditionsVo);
                    EsHbaseCriteriaVo esHbaseCriteriaVo = ORMUtils.getEsHbaseCriteria(edmName, null, conditionsVos, null);
                    String propClass = RestUtils.doPost(ORM_URL, esHbaseCriteriaVo);
                    restPrplPropClasses.put(relatedCondition.getCndrProp2Code(), propClass);
                }
            }
        }
        restPrplPropClasses.put(prplClass2NameEn,restPrplClass1);
        Map<Integer,ConditionsVo> ormConditionsByrelatedConditions = getOrmConditionsByPropListRelatedConditions(prplClass2NameEn, relatedConditions, restPrplPropClasses, conditionFormula);
            //保存每个条件的结果
            //List<List<Object>> resultList = new ArrayList<List<Object>>();
            Map<String,List<Object>> resultMap = new HashMap<String, List<Object>>();
            for (Integer i: ormConditionsByrelatedConditions.keySet()) {
                ConditionsVo conditionsVo = ormConditionsByrelatedConditions.get(i);
                //包含属性集运算的条件，需要每个条件单独取值，再计算整个条件的取值
                List<ConditionsVo> conditionsPrpl = new ArrayList<ConditionsVo>();
                List<Map<String, Object>> dataSet = new ArrayList<>();
                //根据属性编码字段判断属性集，属性集格式为：属性集编码.属性编码，普通属性格式为：属性编码
                if (conditionsVo.getAttr().indexOf(".") != -1) {
                    String attr = conditionsVo.getAttr();
                    //根据ORM的规范，属性集查询时edmName格式为：类名.属性集编码
                    String edmName = prplClass2NameEn + "." + attr.substring(0, attr.lastIndexOf("."));
                    String propId = attr.substring(attr.lastIndexOf(".") + 1, attr.length());
                    EsHbaseCriteriaVo queryPrpl = ORMUtils.getEsHbaseCriteria(edmName, null, conditionsPrpl, null);
                    log.info("ORM query condition vo : queryPrpl : ", queryPrpl);
                    String restPrplClass = RestUtils.doPost(ORM_URL, queryPrpl);
                    log.info("ORM query result vo : restPrplClass : ", restPrplClass);
                     dataSet = ORMUtils.getDataSet(restPrplClass);
                    log.info("ORM query result vo : dataSet : ", dataSet);
                    List<Object> dataResult = new ArrayList<Object>();

                    if (!StringUtil.isNullOrEmpty(dataSet) && dataSet.size() >= 0) {
                        for (Map map : dataSet) {
                            String value1 = map.get(propId).toString();
                            String[] values = conditionsVo.getValue().split(",");
                            for(String value2 : values) {
                                double value3;
                                double value4;
                                try {
                                    value3 = Double.parseDouble(value1);
                                    value4 = Double.parseDouble(value2);
                                    if ("=".equals(conditionsVo.getOperator())) {
                                        if (value3 == value4) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    } else if ("!=".equals(conditionsVo.getOperator())) {
                                        if (value3 != value4) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    } else if ("<".equals(conditionsVo.getOperator())) {
                                        if (value3 < value4) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    } else if (">".equals(conditionsVo.getOperator())) {
                                        if (value3 > value4) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    } else if ("<=".equals(conditionsVo.getOperator())) {
                                        if (value3 <= value4) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    } else if (">=".equals(conditionsVo.getOperator())) {
                                        if (value3 >= value4) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    } else if ("like".equals(conditionsVo.getOperator())) {
                                        if (value3 == value4) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    } else if ("not like".equals(conditionsVo.getOperator())) {
                                        if (value3 != value4) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    }
                                } catch (Exception e) {
                                    if ("=".equals(conditionsVo.getOperator())) {
                                        if (value1.equals(value2)) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    } else if ("!=".equals(conditionsVo.getOperator())) {
                                        if (!value1.equals(value2)) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    } else if ("like".equals(conditionsVo.getOperator())) {
                                        if (value1.equals(value2)) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    } else if ("not like".equals(conditionsVo.getOperator())) {
                                        if (!value1.equals(value2)) {
                                            dataResult.add(map.get("pid"));
                                        }
                                    }
                                }
                            }
                        }
                    }

                    resultMap.put(String.valueOf(i),dataResult);
                } else {//普通属性查询
                    List lists = new ArrayList();
                    String valuess = conditionsVo.getValue();
                    if(conditionsVo.getValue().indexOf(",")!=-1) {
                        String[] values = conditionsVo.getValue().split(",");

                        for(String value : values) {
                            conditionsVo.setValue(value);
                            conditionsPrpl.add(conditionsVo);
                            EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria(prplClass2NameEn, null, conditionsPrpl, null);
                            log.info("ORM query condition vo : queryPrplClass : ", queryPrplClass);
                            String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
                            log.info("ORM query result vo : restPrplClass : ", restPrplClass);
                            dataSet = ORMUtils.getDataSet(restPrplClass);
                            log.info("ORM query result vo : dataSet : ", dataSet);
                            conditionsPrpl = new ArrayList<>();
                            List list = getResultListByDataSet(dataSet);
                            lists.addAll(list);
                        }

                    }else{
                        conditionsPrpl.add(conditionsVo);
                        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria(prplClass2NameEn, null, conditionsPrpl, null);
                        log.info("ORM query condition vo : queryPrplClass : ", queryPrplClass);
                        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
                        log.info("ORM query result vo : restPrplClass : ", restPrplClass);
                        dataSet = ORMUtils.getDataSet(restPrplClass);
                        log.info("ORM query result vo : dataSet : ", dataSet);
                        lists = getResultListByDataSet(dataSet);
                    }

                    resultMap.put(String.valueOf(i),lists);
                }

            }
            //List<String> resultList = resultMap.get(condSeqList.get(0));
//            List<Object> resultIds = new ArrayList<Object>();
//            List<Object> lis = new ArrayList<Object>();
//
//            if (!StringUtil.isNullOrEmpty(resultList) && resultList.size() != 0) {
//                if (resultList.size() != 1) {
//                    for (int i = 0; i < resultList.size() - 1; i++) {
//                        if (i == 0) {
//                            resultIds = resultList.get(i);
//                        } else {
//                            resultIds = lis;
//                            lis = new ArrayList<Object>();
//                        }
//                        if (!StringUtil.isNullOrEmpty(resultList.get(i)) && resultList.get(i).size() != 0) {
//                            for (int j = 0; j < resultIds.size(); j++) {
//                                if (!StringUtil.isNullOrEmpty(resultList.get(i + 1)) && resultList.get(i + 1).size() != 0) {
//                                    if (resultList.get(i + 1).contains(resultIds.get(j))) {
//                                        lis.add(resultIds.get(j));
//                                    }
//                                }
//                            }
//                        }
//                    }
//                    lis = getDistinct(lis);
//                    result.setData(lis.toArray());
//                } else {
//                    result.setData(getDistinct(resultList.get(0)).toArray());
//                }
//            } else {
//                result.setData(resultIds.toArray());
//            }
            //List<Object> resultList = resultMap.get(condSeqList.get(0));
            //getResultData(1,condSeqList,operList,resultMap,resultList);
            //resultList = getDistinct(resultList);
            //result.setData(resultList.toArray());
           String idSql = getIdSqlsByFormula(conditionFormula,resultMap);
         SimpleCriteriaVo queryPrplClass = ORMUtils.getSimpleCriteriaVo(prplClass2NameEn, idSql);
        log.info("ORM query condition vo : queryPrplClass : ", queryPrplClass);
        String restPrplClass = RestUtils.doPost(ORM_URL_RICH, queryPrplClass);
        log.info("ORM query result vo : restPrplClass : ", restPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        log.info("ORM query result vo : dataSet : ", dataSet);
        List<String> resultList = new ArrayList<>();
        for(Map<String,Object> data: dataSet){
            resultList.add(String.valueOf(data.get("id")));
        }
        result.setData(resultList);
            return result;
       // }
    }
    private List getDistinct(List list){
        Set set = new HashSet();
        List newList = new ArrayList();
        set.addAll(list);
        newList.addAll(set);
        return  newList;
    }
    private List<Object> getResultListByDataSet(List<Map<String, Object>> dataSet){
        List<Object> list = new ArrayList<Object>();
        Object[] ids = ORMUtils.getValsByPara(dataSet, "id");
        for (int i = 0; i < ids.length; i++) {
            list.add(ids[i]);
        }
        return list;
    }
    /**
     * @return java.util.List<com.huntkey.rx.sceo.formula.common.model.vo.ConditionsVo>
     * @desc 拼装关联类查询条件
     * @pars [prplClassNameEn, relatedConditions, restPrplClassVal] 关联类代码,  条件列  被关联类orm数据
     * @date 2017/8/18 0018 下午 4:59 lulx
     **/
    public List<ConditionsVo> getOrmConditionsByrelatedConditions(String prplClassNameEn, List<TacConditionRelated> relatedConditions, String restPrplClassVal, String conditionFormula) {
        List<ConditionsVo> conditionsPrplClass2 = new ArrayList<ConditionsVo>();
        // TODO 暂时只拼装与关联类相关的条件
        ConditionsVo condPrplClass2;
        OrmOperator ormOperator;
        // 运算符
        String cndrOperate = "";
        // 关联表查询参数
        String cndrPropCode = "";
        // 被关联表查询参数具体值
        String paraVal = "";
        for (TacConditionRelated tacConditionRelated : relatedConditions) {

            //获取设置了的条件
            if (!isUseCondtion(tacConditionRelated.getCndrSeq(), conditionFormula)) {
                continue;
            }
            ormOperator = OrmOperator.getOrmOperator(tacConditionRelated.getCndrOperate());
            if (prplClassNameEn.equals(tacConditionRelated.getCndrClass1NameEn())) {
                //  左
                cndrOperate = ormOperator.getOperator();
                cndrPropCode = tacConditionRelated.getCndrProp1Code();
                if ("class".equals(RelCondValType.getRelCondValType(tacConditionRelated.getCndrValueType()).getValType())) {
                    paraVal = ORMUtils.getValByPara(ORMUtils.getDataSet(restPrplClassVal), tacConditionRelated.getCndrProp2Code()).toString();
                } else {
                    paraVal = getParaVal(tacConditionRelated);
                }
            } else if (prplClassNameEn.equals(tacConditionRelated.getCndrClass2NameEn())) {
                // 右
                cndrOperate = ormOperator.getReversOperator();
                cndrPropCode = tacConditionRelated.getCndrProp2Code();
                paraVal = ORMUtils.getValByPara(ORMUtils.getDataSet(restPrplClassVal), tacConditionRelated.getCndrProp1Code()).toString();
            } else {
                paraVal = "";
            }

            if (StringUtil.isNullOrEmpty(paraVal)) {
                continue;
            }
            condPrplClass2 = new ConditionsVo();
            condPrplClass2.setAttr(cndrPropCode);
            condPrplClass2.setOperator(cndrOperate);
            condPrplClass2.setValue(paraVal);
            conditionsPrplClass2.add(condPrplClass2);
        }
        return conditionsPrplClass2;
    }


    /**
     * @return java.util.List<com.huntkey.rx.sceo.formula.common.model.vo.ConditionsVo>
     * @desc 拼装关联类查询条件
     * @pars [prplClassNameEn, relatedConditions, restPrplClassVal] 关联类代码,  条件列  被关联类orm数据
     * @date 2017/8/18 0018 下午 4:59 lulx
     **/
    public Map<Integer,ConditionsVo> getOrmConditionsByPropListRelatedConditions(String prplClassNameEn, List<TacConditionRelated> relatedConditions, Map<String,String> restPrplClasses, String conditionFormula) {
        Map<Integer,ConditionsVo> conditionsPrplClass = new HashMap<>();
        ConditionsVo condPrplClass2;
        OrmOperator ormOperator;
        // 运算符
        String cndrOperate = "";
        // 关联表查询参数
        String cndrPropCode = "";
        // 被关联表查询参数具体值
        String paraVal = "";
        for (TacConditionRelated tacConditionRelated : relatedConditions) {

            //获取设置了的条件
            if (!isUseCondtion(tacConditionRelated.getCndrSeq(), conditionFormula)) {
                continue;
            }
            ormOperator = OrmOperator.getOrmOperator(tacConditionRelated.getCndrOperate());
            if (prplClassNameEn.equals(tacConditionRelated.getCndrClass1NameEn())) {
                //  左
                cndrOperate = ormOperator.getOperator();
                cndrPropCode = tacConditionRelated.getCndrProp1Code();
                if ("class".equals(RelCondValType.getRelCondValType(tacConditionRelated.getCndrValueType()).getValType())) {
                    if(restPrplClasses.containsKey(tacConditionRelated.getCndrProp2Code())) {
                        String propCode = tacConditionRelated.getCndrProp2Code();
                        Object[] propVals = ORMUtils.getValsByPara(ORMUtils.getDataSet(restPrplClasses.get(tacConditionRelated.getCndrProp2Code())), propCode.substring(propCode.lastIndexOf(".") + 1, propCode.length()));
                        StringBuffer paraValList = new StringBuffer();
                        for(Object propVal : propVals){
                            paraValList=paraValList.append(propVal.toString()).append(",");
                        }
                        paraVal = paraValList.deleteCharAt(paraValList.length()-1).toString();
                    }else{
                        paraVal = ORMUtils.getValByPara(ORMUtils.getDataSet(restPrplClasses.get(prplClassNameEn)), tacConditionRelated.getCndrProp2Code()).toString();
                    }
                } else {
                    paraVal = getParaVal(tacConditionRelated);
                }
            } else if (prplClassNameEn.equals(tacConditionRelated.getCndrClass2NameEn())) {
                // 右
                cndrOperate = ormOperator.getReversOperator();
                cndrPropCode = tacConditionRelated.getCndrProp2Code();
                if(restPrplClasses.containsKey(tacConditionRelated.getCndrProp1Code())) {
                    String propCode = tacConditionRelated.getCndrProp1Code();
                    Object[] propVals = ORMUtils.getValsByPara(ORMUtils.getDataSet(restPrplClasses.get(tacConditionRelated.getCndrProp1Code())), propCode.substring(propCode.lastIndexOf(".") + 1, propCode.length()));
                    StringBuffer paraValList = new StringBuffer();
                    for(Object propVal : propVals){
                        paraValList=paraValList.append(propVal.toString()).append(",");
                    }
                    paraVal = paraValList.deleteCharAt(paraValList.length()-1).toString();
                }else{
                    paraVal = ORMUtils.getValByPara(ORMUtils.getDataSet(restPrplClasses.get(prplClassNameEn)), tacConditionRelated.getCndrProp1Code()).toString();
                }


            } else {
                paraVal = "";
            }

            if (StringUtil.isNullOrEmpty(paraVal)) {
                continue;
            }
            condPrplClass2 = new ConditionsVo();
            condPrplClass2.setAttr(cndrPropCode);
            condPrplClass2.setOperator(cndrOperate);
            condPrplClass2.setValue(paraVal);
            conditionsPrplClass.put(tacConditionRelated.getCndrSeq(),condPrplClass2);
        }
        return conditionsPrplClass;
    }

    /**
     * @return java.util.List<com.huntkey.rx.sceo.formula.common.model.vo.ConditionsVo>
     * @desc 拼装关联类查询条件
     * @pars [prplClassNameEn, relatedConditions, restPrplClassVal] 关联类代码,  条件列  被关联类orm数据
     * @date 2017/8/18 0018 下午 4:59 lulx
     **/
    public Boolean getOrmConditionsByrelatedConditionsForClass(String prplClass1NameEn,List<TacConditionRelated> relatedConditions, String restPrplClassVal, String conditionFormula, String dataId,List<Map<String, Object>> dataSet) {
        List<ConditionsVo> conditionsPrplClass2 = new ArrayList<ConditionsVo>();

        Boolean resultFlag = true;

        ConditionsVo condPrplClass2;
        // 运算符
        String cndrOperate = "";
        // 关联表查询参数
        String cndrPropCode = "";

        List<Boolean> getEveryFlagList = new ArrayList<Boolean>();

        String paraVal;
        for (TacConditionRelated tacConditionRelated : relatedConditions) {
            //获取设置了的条件
            if (!isUseCondtion(tacConditionRelated.getCndrSeq(), conditionFormula)) {
                resultFlag = false;
                getEveryFlagList.add(resultFlag);
                continue;
            } else {
                resultFlag = true;
            }

            cndrOperate = tacConditionRelated.getCndrOperate();
            cndrPropCode = tacConditionRelated.getCndrProp1Code();

            //如果是相关类 需要去验证所选择字段是关联字段
            if (tacConditionRelated.getClassTypeFlag().intValue() == 2) {
                resultFlag = getOrmConditionsByrelatedConditionsForLinkClass(restPrplClassVal, tacConditionRelated);
            } else {
                String cndrClass1NameEn = tacConditionRelated.getCndrClass1NameEn();
                String cndrClassName = cndrClass1NameEn;
                //根据属性编码字段判断属性集，属性集格式为：属性集编码.属性编码，普通属性格式为：属性编码
                if (cndrPropCode.indexOf(".") != -1) {
                    conditionsPrplClass2 = new ArrayList<>();
                    String attr = cndrPropCode;
                    //根据ORM的规范，属性集查询时edmName格式为：类名.属性集编码
                    cndrClass1NameEn = tacConditionRelated.getCndrClass1NameEn() + "." + attr.substring(0, attr.lastIndexOf("."));
                    String propId = attr.substring(attr.lastIndexOf(".") + 1, attr.length());
                    if(prplClass1NameEn.equals(cndrClassName)) {
                        condPrplClass2 = new ConditionsVo();
                        condPrplClass2.setAttr("pid");
                        condPrplClass2.setOperator(cndrOperate);
                        condPrplClass2.setValue(dataId);
                        conditionsPrplClass2.add(condPrplClass2);
                    }
                    EsHbaseCriteriaVo queryPrplClass2 = ORMUtils.getEsHbaseCriteria(cndrClass1NameEn, null, conditionsPrplClass2, null);
                    String restPrplClass2 = RestUtils.doPost(ORM_URL, queryPrplClass2);
                    List<Map<String, Object>> dataSet2 = ORMUtils.getDataSet(restPrplClass2);

                    if (StringUtil.isNullOrEmpty(dataSet2) || dataSet2.size() <= 0) {
                        resultFlag = false;
                    }

                    resultFlag = false;
                    String cndrClassPid = "";
                    List<String> cndrClassPids = new ArrayList<>();
                    for (Map map : dataSet2) {
                        String value1 = map.get(propId).toString();

                        String value2 = tacConditionRelated.getCndrProp2();
                        double value3;
                        double value4;

                        try {
                            value3 = Double.parseDouble(value1);
                            value4 = Double.parseDouble(value2);
                            if ("=".equals(cndrOperate)) {
                                if (value3 == value4) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            } else if ("!=".equals(cndrOperate)) {
                                if (value3 != value4) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            } else if ("<".equals(cndrOperate)) {
                                if (value3 < value4) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            } else if (">".equals(cndrOperate)) {
                                if (value3 > value4) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            } else if ("<=".equals(cndrOperate)) {
                                if (value3 <= value4) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            } else if (">=".equals(cndrOperate)) {
                                if (value3 >= value4) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            } else if ("like".equals(cndrOperate)) {
                                if (value3 == value4) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            } else if ("not like".equals(cndrOperate)) {
                                if (value3 != value4) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            }
                        } catch (Exception e) {
                            if ("=".equals(cndrOperate)) {
                                if (value1.equals(value2)) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            } else if ("!=".equals(cndrOperate)) {
                                if (!value1.equals(value2)) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            } else if ("like".equals(cndrOperate)) {
                                if (value1.equals(value2)) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            } else if ("not like".equals(cndrOperate)) {
                                if (!value1.equals(value2)) {
                                    resultFlag = true;
                                    cndrClassPid = map.get("pid").toString();

                                }
                            }
                        }
                       cndrClassPids.add(cndrClassPid);
                    }
                    if(resultFlag==true&&!prplClass1NameEn.equals(cndrClassName)){
                        resultFlag = false;
                        for(String pid :cndrClassPids){
                            for (Object obj : dataSet.get(0).values()) {
                                if (pid.equals(obj.toString())) {
                                    resultFlag = true;
                                    break;
                                }
                            }
                        }
                    }
                } else {

                    conditionsPrplClass2 = new ArrayList<ConditionsVo>();
                    paraVal = getParaVal(tacConditionRelated);
                    condPrplClass2 = new ConditionsVo();
                    condPrplClass2.setAttr(cndrPropCode);
                    condPrplClass2.setOperator(cndrOperate);
                    condPrplClass2.setValue(paraVal);
                    conditionsPrplClass2.add(condPrplClass2);
                    if(prplClass1NameEn.equals(cndrClass1NameEn)){
                        ConditionsVo condPrplClass3 = new ConditionsVo();
                        condPrplClass3.setAttr("id");
                        condPrplClass3.setOperator("=");
                        condPrplClass3.setValue(dataId);
                        conditionsPrplClass2.add(condPrplClass3);
                    }
                    EsHbaseCriteriaVo queryPrplClass2 = ORMUtils.getEsHbaseCriteria(cndrClass1NameEn, null, conditionsPrplClass2, null);
                    String restPrplClass2 = RestUtils.doPost(ORM_URL, queryPrplClass2);
                    List<Map<String, Object>> dataSet2 = ORMUtils.getDataSet(restPrplClass2);

                    if (StringUtil.isNullOrEmpty(dataSet2) || dataSet2.size() <= 0) {
                        resultFlag = false;
                    }else {
                        if (!prplClass1NameEn.equals(cndrClass1NameEn)) {
                            resultFlag = false;
                            for (Map<String, Object> data : dataSet2) {
                                String cndrClassId = data.get("id").toString();
                                for (Object obj : dataSet.get(0).values()) {
                                    if (cndrClassId.equals(obj.toString())) {
                                        resultFlag = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

            }
            getEveryFlagList.add(resultFlag);

        }

        boolean finalResult = false;
        String expressString = conditionFormula;
        if (null != getEveryFlagList && getEveryFlagList.size() > 0) {
            for (int i = 1; i <= getEveryFlagList.size(); i++) {
                if (expressString.contains("[" + i + "]")) {
                    expressString = StringUtils.replace(expressString, "[" + i + "]", getEveryFlagList.get(i - 1).toString());
                }
            }
            ExpressionParser parser = new SpelExpressionParser();
            finalResult = parser.parseExpression(expressString).getValue(Boolean.class);
        }

        return finalResult;
    }


    public Boolean getOrmConditionsByrelatedConditionsForLinkClass(String restPrplClassVal, TacConditionRelated tacConditionRelated) {

        Boolean resultFlag = true;

        ClassRelatedVo classRelatedVo = relatedClassSettingServiceImpl.getClassRelatedAndConditionsByClssId(tacConditionRelated.getLinkClssId());

        TfdClassRelated classRelated = classRelatedVo.getClassRelated();
        List<TplCondition> tplConditions = classRelatedVo.getTplConditions();

        // 查询被关联类 orm数据
        String prplClass1NameEn = tacConditionRelated.getCndrClass1NameEn();

        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();

        for (TplCondition temp : tplConditions) {

            // 获取已设置的条件
            if (!isUseCondtion(temp.getCndtSeq(), classRelated.getClssConditionFormula())) {
                continue;
            }
            if ("class".equals(temp.getCndtValueType())) {
                String cndrProp2Code = "";
                String cndtValueOriginCode = temp.getCndtValueOriginCode();
                if (null != cndtValueOriginCode) {
                    String[] arr = cndtValueOriginCode.split("\\+");
                    if (arr.length > 1) {
                        cndrProp2Code = arr[1];
                    }
                }

                Object paraVal = (String) ORMUtils.getValByPara(ORMUtils.getDataSet(restPrplClassVal), cndrProp2Code);
                if (StringUtil.isNullOrEmpty(paraVal)) {
                    continue;
                }

                //根据属性编码字段判断属性集，属性集格式为：属性集编码.属性编码，普通属性格式为：属性编码
                if (temp.getCndtPropOriginCode().indexOf(".") != -1) {
                    String attr = temp.getCndtPropOriginCode();
                    //根据ORM的规范，属性集查询时edmName格式为：类名.属性集编码
                    prplClass1NameEn = tacConditionRelated.getCndrClass1NameEn() + "." + attr.substring(0, attr.lastIndexOf("."));

                } else {
                    ConditionsVo condPrplClass = new ConditionsVo();
                    condPrplClass.setAttr(temp.getCndtPropOriginCode());
                    condPrplClass.setOperator(temp.getCndtOperate());
                    condPrplClass.setValue(paraVal.toString());
                    conditionsPrplClass.add(condPrplClass);
                }
            } else {
                //根据属性编码字段判断属性集，属性集格式为：属性集编码.属性编码，普通属性格式为：属性编码
                if (temp.getCndtPropOriginCode().indexOf(".") != -1) {
                    String attr = temp.getCndtPropOriginCode();
                    //根据ORM的规范，属性集查询时edmName格式为：类名.属性集编码
                    prplClass1NameEn = tacConditionRelated.getCndrClass1NameEn() + "." + attr.substring(0, attr.lastIndexOf("."));
                } else {

                    String paraVal = String.valueOf(RelCondValType.getRelCondValType(temp.getCndtValueType()).getRelCondVal().getRelCondVal(temp.getCndtValue()));

                    ConditionsVo condPrplClass = new ConditionsVo();
                    condPrplClass.setAttr(temp.getCndtPropOriginCode());
                    condPrplClass.setOperator(temp.getCndtOperate());
                    condPrplClass.setValue(paraVal);
                    conditionsPrplClass.add(condPrplClass);
                }
            }
        }

        ConditionsVo condPrplClass2 = new ConditionsVo();
        condPrplClass2.setAttr(tacConditionRelated.getCndrProp1Code());
        condPrplClass2.setOperator(tacConditionRelated.getCndrOperate());
        condPrplClass2.setValue(tacConditionRelated.getCndrProp2());
        conditionsPrplClass.add(condPrplClass2);

        EsHbaseCriteriaVo queryPrplClass1 = ORMUtils.getEsHbaseCriteria(prplClass1NameEn, null, conditionsPrplClass, null);

        String restPrplClass1 = RestUtils.doPost(ORM_URL, queryPrplClass1);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass1);

        if (StringUtil.isNullOrEmpty(dataSet) || dataSet.size() <= 0) {
            resultFlag = false;
        }

        return resultFlag;
    }


    private boolean isUseCondtion(Integer cndrSeq, String conditionFormula) {
        if (StringUtil.isNullOrEmpty(cndrSeq) || StringUtil.isNullOrEmpty(conditionFormula)) {
            return false;
        }
        return conditionFormula.indexOf("[" + String.valueOf(cndrSeq) + "]") > -1;
    }

    @Override
    public Result relCondConfDataByPro(String prplId, String dataId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        PropertyRelatedVo propertyRelatedCondition = getPropertyRelatedCondition(prplId);
        if (StringUtil.isNullOrEmpty(propertyRelatedCondition.getRelatedProperty()) || StringUtil.isNullOrEmpty(propertyRelatedCondition.getRelatedConditions())) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("错误的关联对象公式ID");
            return result;
        }
        TacPropertyRelated relatedProperty = propertyRelatedCondition.getRelatedProperty();
        List<TacConditionRelated> relatedConditions = propertyRelatedCondition.getRelatedConditions();
        //标志位，当触发条件的条件中不包含属性集计算时为true，采用原有处理逻辑判断触发条件，当条件中包含属性集运算时为false，需要单独计算
        boolean flag = true;
        for (TacConditionRelated relatedCondition : relatedConditions) {
            if (relatedCondition.getCndrPropOriginCode().indexOf(".") != -1) {
                flag = false;
                break;
            }
        }

        TacConditionRelated tac = new TacConditionRelated();
        String conditionFormula = relatedProperty.getPrplConditionFormula();
        log.info("关联触发条件 : propertyRelatedCondition : ", propertyRelatedCondition);
        // 查询被关联类 orm数据
        String prplClass1NameEn = relatedProperty.getPrplClass1NameEn();

         //List<ConditionsVo> conditionsPrplClass = getOrmConditionsByRelCondConf(relatedConditions, conditionFormula);
        List<Integer> conditionsSeqList = getConditionsSeqByFormula(relatedConditions, conditionFormula);
        Map<Integer, ConditionsVo> conditionsPrplMap = new HashMap<>();
        ConditionsVo cond;
        String paraVal;
        for (TacConditionRelated tacConditionRelated : relatedConditions) {
            paraVal = getParaVal(tacConditionRelated);

            cond = new ConditionsVo();
            cond.setAttr(tacConditionRelated.getCndrProp1Code());
            cond.setOperator(tacConditionRelated.getCndrOperate());
            cond.setValue(paraVal);
            conditionsPrplMap.put(tacConditionRelated.getCndrSeq(), cond);
        }
        //保存每个条件的布尔值
        Map<Integer, Boolean> ormResult = new HashMap<>();
        ConditionsVo conditionsVo;
        for (Integer seq : conditionsSeqList) {
            conditionsVo = conditionsPrplMap.get(seq);
            //包含属性集运算的条件，需要每个条件单独计算布尔值，再计算整个触发条件的布尔值
            List<ConditionsVo> conditionsPrpl = new ArrayList<ConditionsVo>();
            ConditionsVo condPrpl = new ConditionsVo();
            //根据属性编码字段判断属性集，属性集格式为：属性集编码.属性编码，普通属性格式为：属性编码
            if (conditionsVo.getAttr().indexOf(".") != -1) {
                String attr = conditionsVo.getAttr();
                //根据ORM的规范，属性集查询时edmName格式为：类名.属性集编码
                String edmName = prplClass1NameEn + "." + attr.substring(0, attr.lastIndexOf("."));
                String propId = attr.substring(attr.lastIndexOf(".") + 1, attr.length());
                if (!StringUtil.isNullOrEmpty(dataId)) {
                    //属性集pid对应类的id,由于目前ORM查询属性集时，只支持id和pid作为条件，不支持属性字段作为条件，所以条件设置仅为pid,需取返回结果进行比较，判断布尔值
                    condPrpl.setAttr("pid");
                    condPrpl.setOperator("=");
                    condPrpl.setValue(dataId);
                    conditionsPrpl.add(condPrpl);
                }
                EsHbaseCriteriaVo queryPrpl = ORMUtils.getEsHbaseCriteria(edmName, null, conditionsPrpl, null);

                log.info("ORM query condition vo : queryPrpl : ", queryPrpl);
                String restPrplClass = RestUtils.doPost(ORM_URL, queryPrpl);
                log.info("ORM query result vo : restPrplClass : ", restPrplClass);
                List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
                log.info("ORM query result vo : dataSet : ", dataSet);
                //需要取返回值比较结果，当取值中有一条结果匹配时即为true，否则false
                boolean dataResult = false;
                if (!StringUtil.isNullOrEmpty(dataSet) && dataSet.size() >= 0) {
                    for (Map map : dataSet) {
                        String value1 = map.get(propId).toString();
                        String value2 = conditionsVo.getValue();
                        double value3;
                        double value4;

                        try {
                            value3 = Double.parseDouble(value1);
                            value4 = Double.parseDouble(value2);
                            if ("=".equals(conditionsVo.getOperator())) {
                                if (value3 == value4) {
                                    dataResult = true;
                                    break;
                                }
                            } else if ("!=".equals(conditionsVo.getOperator())) {
                                if (value3 != value4) {
                                    dataResult = true;
                                    break;
                                }
                            } else if ("<".equals(conditionsVo.getOperator())) {
                                if (value3 < value4) {
                                    dataResult = true;
                                    break;
                                }
                            } else if (">".equals(conditionsVo.getOperator())) {
                                if (value3 > value4) {
                                    dataResult = true;
                                    break;
                                }
                            } else if ("<=".equals(conditionsVo.getOperator())) {
                                if (value3 <= value4) {
                                    dataResult = true;
                                    break;
                                }
                            } else if (">=".equals(conditionsVo.getOperator())) {
                                if (value3 >= value4) {
                                    dataResult = true;
                                    break;
                                }
                            } else if ("like".equals(conditionsVo.getOperator())) {
                                if (value3 == value4) {
                                    dataResult = true;
                                    break;
                                }
                            } else if ("not like".equals(conditionsVo.getOperator())) {
                                if (value3 != value4) {
                                    dataResult = true;
                                    break;
                                }
                            }
                        } catch (Exception e) {
                            if ("=".equals(conditionsVo.getOperator())) {
                                if (value1.equals(value2)) {
                                    dataResult = true;
                                    break;
                                }
                            } else if ("!=".equals(conditionsVo.getOperator())) {
                                if (!value1.equals(value2)) {
                                    dataResult = true;
                                    break;
                                }
                            } else if ("like".equals(conditionsVo.getOperator())) {
                                if (value1.equals(value2)) {
                                    dataResult = true;
                                    break;
                                }
                            } else if ("not like".equals(conditionsVo.getOperator())) {
                                if (!value1.equals(value2)) {
                                    dataResult = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                ormResult.put(seq, dataResult);
            } else {//普通属性查询
                if (!StringUtil.isNullOrEmpty(dataId)) {
                    condPrpl.setAttr("id");
                    condPrpl.setOperator("=");
                    condPrpl.setValue(dataId);
                }
                conditionsPrpl.add(conditionsVo);
                conditionsPrpl.add(condPrpl);
                EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria(prplClass1NameEn, null, conditionsPrpl, null);
                log.info("ORM query condition vo : queryPrplClass : ", queryPrplClass);
                String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
                log.info("ORM query result vo : restPrplClass : ", restPrplClass);
                List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
                log.info("ORM query result vo : dataSet : ", dataSet);
                ormResult.put(seq, !StringUtil.isNullOrEmpty(dataSet) && dataSet.size() > 0);
            }

        }

        String resultExpression = getExpression(ormResult, conditionsSeqList, conditionFormula);
        Map<String, Object> map = new HashMap<>();
        boolean resultData = (Boolean) JexlUtil.convertToCode(resultExpression, map);
//            for (boolean bool : ORMResult) {//判断所有条件的返回结果,如果其中一个条件为false，则最终结果为false，否则为true
//                if (bool == false) {
//                    resultData = false;
//                    break;
//                }
//            }
        result.setData(resultData);
        return result;
        // }
    }

    @Override
    public Result getObjectRelCondConfDataByPro(String prplId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        PropertyRelatedVo propertyRelatedCondition = getPropertyRelatedCondition(prplId);
        if (StringUtil.isNullOrEmpty(propertyRelatedCondition.getRelatedProperty()) || StringUtil.isNullOrEmpty(propertyRelatedCondition.getRelatedConditions())) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("错误的关联对象公式ID");
            return result;
        }
        log.info("关联触发条件 propertyRelatedCondition : ", propertyRelatedCondition);

        TacPropertyRelated relatedProperty = propertyRelatedCondition.getRelatedProperty();
        List<TacConditionRelated> relatedConditions = propertyRelatedCondition.getRelatedConditions();
        //标志位，当触发条件的条件中不包含属性集计算时为true，采用原有处理逻辑判断触发条件，当条件中包含属性集运算时为false，需要单独计算
        boolean flag = true;
        for (TacConditionRelated relatedCondition : relatedConditions) {
            // 包含属性集
            if (relatedCondition.getCndrPropOriginCode().indexOf(".") != -1) {
                flag = false;
                break;
            }
        }

        String conditionFormula = relatedProperty.getPrplConditionFormula();
        String conditionFormula2 = conditionFormula;
        // 查询被关联类 orm数据
        String prplClass1NameEn = relatedProperty.getPrplClass1NameEn();

       Map <Integer,TacConditionRelated> useCondtion = getUseCondtion(relatedConditions, conditionFormula);
        for (Integer i : useCondtion.keySet()) {
            String oldString = "\\[" + i + "]";
            conditionFormula = conditionFormula.replaceAll(oldString, getSql(useCondtion, i));
        }

        // 获取ORM查询条件
        List<ConditionsVo> conditionsPrplClass = getOrmConditionsByRelCondConf(relatedConditions, conditionFormula2);
        // 条件不包含属性集，直接计算
        if (flag) {
            //EsHbaseCriteriaVo queryPrpl = ORMUtils.getEsHbaseCriteria(prplClass1NameEn, null, conditionsPrplClass, null);
            SimpleCriteriaVo queryPrplClass = ORMUtils.getSimpleCriteriaVo(prplClass1NameEn, conditionFormula);
            log.info("ORM query condition vo : queryPrplClass : ", queryPrplClass);
            String restPrplClass = RestUtils.doPost(ORM_URL_RICH, queryPrplClass);
            log.info("ORM query result vo : restPrplClass : ", restPrplClass);
            List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
            log.info("ORM query result vo : dataSet : ", dataSet);
            result.setData(dataSet);
        } else { // 条件中包含属性集
            List<Map<String, Object>> dataSet1 = new ArrayList<Map<String, Object>>();
            List<Map<String, Object>> dataSet2 = new ArrayList<Map<String, Object>>();
            for (ConditionsVo conditionsVo : conditionsPrplClass) {
                // 包含属性集运算的条件，需要每个条件单独计算布尔值，再计算整个触发条件的布尔值
                List<ConditionsVo> conditionsPrpl = new ArrayList<ConditionsVo>();
                // 根据属性编码字段判断属性集，属性集格式为：属性集编码.属性编码，普通属性格式为：属性编码
                if (conditionsVo.getAttr().indexOf(".") != -1) {
                    String attr = conditionsVo.getAttr();
                    // 根据ORM的规范，属性集查询时edmName格式为：类名.属性集编码
                    String edmName = prplClass1NameEn + "." + attr.substring(0, attr.lastIndexOf("."));
                    conditionsPrpl.add(conditionsVo);
                    //EsHbaseCriteriaVo queryPrpl = ORMUtils.getEsHbaseCriteria(edmName, null, conditionsPrpl, null);
                    SimpleCriteriaVo queryPrpl = ORMUtils.getSimpleCriteriaVo(edmName, conditionFormula);
                    log.info("ORM query condition vo : queryPrpl : ", queryPrpl);
                    String restPrplClass = RestUtils.doPost(ORM_URL_RICH, queryPrpl);
                    log.info("ORM query result vo : restPrplClass : ", restPrplClass);
                    dataSet1 = ORMUtils.getDataSet(restPrplClass);
                    //属性集查询出的是属性集的字段，由于需要返回主类的字段，所以根据属性集结果的pid，再查询主类。
                    StringBuffer sb = new StringBuffer();
                    for(Map<String,Object> map : dataSet1){
                        sb.append(map.get("pid")).append(",");
                    }
                    if(sb.length()>0) {
                        sb.deleteCharAt(sb.length() - 1);
                    }
                    ConditionsVo conditionId = new ConditionsVo();
                    conditionId.setAttr("id");
                    conditionId.setOperator("in");
                    conditionId.setValue(sb.toString());
                    List<ConditionsVo>  conditionsId = new ArrayList<>();
                    conditionsId.add(conditionId);
                    EsHbaseCriteriaVo queryById = ORMUtils.getEsHbaseCriteria(prplClass1NameEn, null, conditionsId, null);
                    log.info("ORM query result vo : dataSet : ", dataSet1);
                    String restById = RestUtils.doPost(ORM_URL,queryById);
                    dataSet1 = ORMUtils.getDataSet(restById);
                } else { // 普通属性查询
                    conditionsPrpl.add(conditionsVo);
                    //EsHbaseCriteriaVo queryPrpl = ORMUtils.getEsHbaseCriteria(prplClass1NameEn, null, conditionsPrpl, null);
                    SimpleCriteriaVo queryPrplClass = ORMUtils.getSimpleCriteriaVo(prplClass1NameEn, conditionFormula);
                    log.info("ORM query condition vo : queryPrplClass : ", queryPrplClass);
                    String restPrplClass = RestUtils.doPost(ORM_URL_RICH, queryPrplClass);
                    log.info("ORM query result vo : restPrplClass : ", restPrplClass);
                    dataSet2 = ORMUtils.getDataSet(restPrplClass);
                    log.info("ORM query result vo : dataSet : ", dataSet2);
                }

                // 取交集
                List<Map<String, Object>> resultSet = ListUtils.getIntersection(dataSet1, dataSet2);
                if (null != resultSet && resultSet.size() > 0) {
                    result.setData(resultSet);
                }
            }
            return result;
        }
        return result;
    }

    @Override
    public Result relCondConfDataByPrplIdForClass(String prplId, String dataId) {

        Boolean resultFlag = true;
        //保存每个条件的布尔值
        Map<Integer, Boolean> ormResult = new HashMap<>();

        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        PropertyRelatedVo propertyRelatedCondition = getPropertyRelatedCondition(prplId);
        if (StringUtil.isNullOrEmpty(propertyRelatedCondition.getRelatedProperty()) || StringUtil.isNullOrEmpty(propertyRelatedCondition.getRelatedConditions())) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("错误的关联对象公式ID");
            return result;
        }
        TacPropertyRelated relatedProperty = propertyRelatedCondition.getRelatedProperty();
        List<TacConditionRelated> relatedConditions = propertyRelatedCondition.getRelatedConditions();
        String conditionFormula = relatedProperty.getPrplConditionFormula();

        String prplClass1NameEn = relatedProperty.getPrplClass1NameEn();
        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass = new ConditionsVo();
        condPrplClass.setAttr("id");
        condPrplClass.setOperator("=");
        condPrplClass.setValue(dataId);
        conditionsPrplClass.add(condPrplClass);

        EsHbaseCriteriaVo queryPrplClass1 = ORMUtils.getEsHbaseCriteria(prplClass1NameEn, null, conditionsPrplClass, null);
        String restPrplClass1 = RestUtils.doPost(ORM_URL, queryPrplClass1);

        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass1);
        if (StringUtil.isNullOrEmpty(dataSet) || dataSet.size() <= 0) {
            result.setData(false);
            return result;
        }


        result.setData(getOrmConditionsByrelatedConditionsForClass(prplClass1NameEn,relatedConditions, restPrplClass1, conditionFormula, dataId,dataSet));
        return result;
    }


    private List<ConditionsVo> getOrmConditionsByRelCondConf(List<TacConditionRelated> relatedConditions, String conditionFormula) {
        List<ConditionsVo> conditionsPrplClass = new ArrayList<ConditionsVo>();
        ConditionsVo condPrplClass;
        // 被关联表查询参数具体值
        String paraVal = "";
        for (TacConditionRelated tacConditionRelated : relatedConditions) {

            // 获取已设置的条件
            if (!isUseCondtion(tacConditionRelated.getCndrSeq(), conditionFormula)) {
                continue;
            }
            paraVal = getParaVal(tacConditionRelated);
            if (StringUtil.isNullOrEmpty(paraVal)) {
                continue;
            }
            condPrplClass = new ConditionsVo();
            condPrplClass.setAttr(tacConditionRelated.getCndrProp1Code());
            condPrplClass.setOperator(tacConditionRelated.getCndrOperate());
            condPrplClass.setValue(paraVal);
            conditionsPrplClass.add(condPrplClass);
        }
        return conditionsPrplClass;
    }

    /**
     * 获取已设置的条件列表
     *
     * @param relatedConditions
     * @param conditionFormula
     * @return
     */
    private Map<Integer,TacConditionRelated> getUseCondtion(List<TacConditionRelated> relatedConditions, String conditionFormula) {
        Map<Integer,TacConditionRelated> getUseCondtion = new HashMap<>();
        for (TacConditionRelated tacConditionRelated : relatedConditions) {
            // 获取已设置的条件
            if (!isUseCondtion(tacConditionRelated.getCndrSeq(), conditionFormula)) {
                continue;
            }
            getUseCondtion.put(tacConditionRelated.getCndrSeq(),tacConditionRelated);
        }
        return getUseCondtion;
    }

    /**
     * 获取拼接sql
     *
     * @param useCondtion
     * @param index
     * @return
     */
    private String getSql(Map <Integer,TacConditionRelated> useCondtion, int index) {
        StringBuffer sb = new StringBuffer();
        String likeOper = "like";
        String notLikeOper = "not like";
        String lLikeOper = "llike";
        String rLikeOper = "rlike";
        if (null != useCondtion && useCondtion.size() > 0) {
            // 被关联表查询参数具体值
            String paraVal = "";
            TacConditionRelated tacConditionRelated = useCondtion.get(index);
            paraVal = getParaVal(tacConditionRelated);
            if(tacConditionRelated.getCndrProp1Code().indexOf(".")!=-1){
                sb.append(tacConditionRelated.getCndrProp1Code().substring(tacConditionRelated.getCndrProp1Code().lastIndexOf(".")+1,tacConditionRelated.getCndrProp1Code().length()));
            }else {
                sb.append(tacConditionRelated.getCndrProp1Code());
            }
                    sb.append(" ");
                    if(lLikeOper.equals(tacConditionRelated.getCndrOperate())||rLikeOper.equals(tacConditionRelated.getCndrOperate())){
                        sb.append(likeOper);
                    }else {
                     sb.append(tacConditionRelated.getCndrOperate());
                    }
                    sb.append(" ")
                    .append("'");
                    if(likeOper.equals(tacConditionRelated.getCndrOperate())||notLikeOper.equals(tacConditionRelated.getCndrOperate())||lLikeOper.equals(tacConditionRelated.getCndrOperate())){
                        sb.append("%");
                    }
                    sb.append(paraVal);
                    if(likeOper.equals(tacConditionRelated.getCndrOperate())||notLikeOper.equals(tacConditionRelated.getCndrOperate())||rLikeOper.equals(tacConditionRelated.getCndrOperate()))     {
                        sb.append("%");
                    }
                    sb.append("'")
                    .append(" ");
        }
        return sb.toString();
    }

    private List<Integer> getConditionsSeqByFormula(List<TacConditionRelated> relatedConditions, String conditionFormula) {
        List<Integer> conditionsSeqList = new ArrayList<>();
        // 被关联表查询参数具体值
        String paraVal = "";
        for (TacConditionRelated tacConditionRelated : relatedConditions) {
            if (!isUseCondtion(tacConditionRelated.getCndrSeq(), conditionFormula)) {
                continue;
            }
//            paraVal = getParaVal(tacConditionRelated);
//            if (StringUtil.isNullOrEmpty(paraVal)) {
//                continue;
//            }
            conditionsSeqList.add(tacConditionRelated.getCndrSeq());
        }
        return conditionsSeqList;
    }

    private String getExpression(Map<Integer, Boolean> ormResult, List<Integer> seqList, String conditionFormula) {
        String expression = conditionFormula.replace("AND", "&&").replace("OR", "||");
        for (Integer i : seqList) {
            expression = expression.replace("[" + i + "]", String.valueOf(ormResult.get(i)));
        }
        return expression;
    }

    /**
     * 判断参数类型
     * @param propId
     * @return
     */
    private boolean isEnum(String propId){
        boolean flag = false;
        String enumType = "enum";
        String objectType = "object";
        Result propResult;
        propResult = bizModelerService.getEdmProp(propId);
        Map<String, String> propDatas = (Map<String, String>) propResult.getData();
        if(enumType.equals(propDatas.get("edmpValueType"))){
            flag = true;
        }
        return flag;
    }

    /**
     * 枚举类型根据id获取编码
     * @param enumId
     * @return
     */
    private String getEnumCode(String enumId){
        List<ConditionsVo> conditions = new ArrayList<ConditionsVo>();
        ConditionsVo condition = new ConditionsVo();
        condition.setAttr("id");
        condition.setOperator("=");
        condition.setValue(enumId);
        conditions.add(condition);
        EsHbaseCriteriaVo queryPrplClass = ORMUtils.getEsHbaseCriteria("wordlist", null, conditions, null);
        String restPrplClass = RestUtils.doPost(ORM_URL, queryPrplClass);
        List<Map<String, Object>> dataSet = ORMUtils.getDataSet(restPrplClass);
        String enumCode = "";
        if(!StringUtil.isNullOrEmpty(dataSet)&&dataSet.size()>0){
          enumCode =  (String) dataSet.get(0).get("info_code");
        }else{
            enumCode = enumId;
        }
        return enumCode;
    }

    /**
     * 获取条件对应的值
     * @param tacConditionRelated
     * @return
     */
    private String getParaVal(TacConditionRelated tacConditionRelated){


        String paraVal = "";
        try {
            //当参数类型为常量时，分为输入值,枚举值和对象链接,2为枚举，3为对象链接
            if (tacConditionRelated.getCndrValueType().equals("const")) {
                if (tacConditionRelated.getConstType() == 2) {
                    paraVal = getEnumCode(tacConditionRelated.getCndrProp2OriginCode());
                } else if (tacConditionRelated.getConstType() == 3) {
                    paraVal = getIdByClassIdAndObjectCode(tacConditionRelated.getCndrProp2OriginCode(), tacConditionRelated.getCndtObjectNumber());
                } else {

                    paraVal = String.valueOf(RelCondValType.getRelCondValType(tacConditionRelated.getCndrValueType()).getRelCondVal().getRelCondVal(tacConditionRelated.getCndrProp2()));
                }
            } else {
                paraVal = String.valueOf(RelCondValType.getRelCondValType(tacConditionRelated.getCndrValueType()).getRelCondVal().getRelCondVal(tacConditionRelated.getCndrProp2()));
            }
        }catch(Exception e){
            e.printStackTrace();
            paraVal = "";
        }
        return paraVal;
    }

    /**
     * 根据类id和对象编码获取对象id
     */
    private String getIdByClassIdAndObjectCode(String classId,String objectCode){
        String objectId ="";
        if(StringUtil.isNullOrEmpty(classId)||StringUtil.isNullOrEmpty(objectCode)){
            return objectId;
        }
        Result classResult = bizModelerService.getClassById(classId);
        Map<String, String> classDatas = (Map<String, String>) classResult.getData();
         String edmName = classDatas.get("edmcCode");
         List<ConditionsVo> conditionsVos = new ArrayList<>();
            ConditionsVo conditionsVo = new ConditionsVo();
            String parentCode ="edmd";
            if(!StringUtil.isNullOrEmpty(classDatas.get("edmcParentId"))) {
                parentCode = getParentClass(classDatas.get("edmcParentId"));
            }
        conditionsVo.setAttr(parentCode+"_code");
        conditionsVo.setOperator("=");
        conditionsVo.setValue(objectCode);
        conditionsVos.add(conditionsVo);
        EsHbaseCriteriaVo queryClass = ORMUtils.getEsHbaseCriteria(edmName,null,conditionsVos,null);
        String restPrplPropClass = RestUtils.doPost(ORM_URL,queryClass);
        List<Map<String,Object>> dataSet = ORMUtils.getDataSet(restPrplPropClass);
        if(!StringUtil.isNullOrEmpty(dataSet)&&dataSet.size()!=0){
            objectId = String.valueOf(dataSet.get(0).get("id"));
        }
        return objectId;
    }
    /**
     * 提取公式中的AND和OR运算符
     * @param formula
     * @param operList
     */
    private void getOpertor(String formula,List<String> operList){
        String regex = "(AND)|(OR)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(formula);
        while (matcher.find()){
            operList.add(matcher.group());
        }


    }
    /**
     * 获取父类类型
     */
    private  String getParentClass(String classId){
        Result classResult = bizModelerService.getClassById(classId);
        Map<String, String> classDatas = (Map<String, String>) classResult.getData();
        String parentCode = classDatas.get("edmcShortName");
        if(!StringUtil.isNullOrEmpty(classDatas.get("edmcParentId"))){
            parentCode = getParentClass(classDatas.get("edmcParentId"));
        }
        return parentCode;
    }
    /**
     * 提取公式中用到的条件列表
     * @param formula
     * @param seqList
     */
    private void getConditionSeqs(String formula,List<String> seqList){
        String regex = "\\[[0-9]+\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(formula);
        while(matcher.find()){
            seqList.add(matcher.group().substring(1,matcher.group().length()-1));
        }
    }

    /**
     * 根据条件以及运算符获取结果集
     * @param i
     * @param seqList
     * @param operList
     * @param resultMap
     */
    private void getResultData(int i,List<String> seqList,List<String> operList,Map<String,List<Object>> resultMap,List<Object> result){
            if(i+1<=seqList.size()||i<=operList.size()) {
                String oper = operList.get(i-1);
                String seq = seqList.get(i);
                if (oper.equals("OR")) {
                    result.addAll(resultMap.get(seq));
                }else if(oper.equals("AND")){
                    result.retainAll(resultMap.get(seq));
                }
                getResultData(i+1,seqList,operList,resultMap,result);
            }
    }

    /**
     *
     */
    private String getIdSqlsByFormula(String formula,Map<String,List<Object>> resultMap){
        String regex = "\\[[0-9]+\\]";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(formula);
        while(matcher.find()){
            String index = matcher.group().substring(1,matcher.group().length()-1);
            formula = formula.replace(matcher.group(),getIdSql(resultMap.get(index)));
        }
        return formula;
    }
    private String getIdSql(List<Object> ids){
        StringBuffer sb = new StringBuffer();

        if(ids.size()!=0) {
            sb.append("id in (");
            for (int i = 0; i < ids.size(); i++) {
                if (i == ids.size() - 1) {
                    sb.append("'").append(ids.get(i)).append("'");
                } else {
                    sb.append("'").append(ids.get(i)).append("'").append(",");
                }
            }
            sb.append(")");
        }else{
            sb.append("id is null");
        }

        return sb.toString();
    }
}