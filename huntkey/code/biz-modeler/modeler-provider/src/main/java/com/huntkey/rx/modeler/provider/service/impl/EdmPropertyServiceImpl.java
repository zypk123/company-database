package com.huntkey.rx.modeler.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.*;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.common.util.EnumEdmData;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.*;
import com.huntkey.rx.modeler.provider.feign.BaseInfoClient;
import com.huntkey.rx.modeler.provider.feign.FormulaClient;
import com.huntkey.rx.modeler.provider.feign.OrmClient;
import com.huntkey.rx.modeler.provider.service.EdmPropertyService;
import com.huntkey.rx.sceo.formula.common.params.IdMapper;
import com.huntkey.rx.sceo.formula.common.params.VariantBatchParam;
import com.huntkey.rx.sceo.serviceCenter.common.emun.ReferanceType;
import com.huntkey.rx.sceo.serviceCenter.common.model.LoadParam;
import com.huntkey.rx.sceo.serviceCenter.common.model.MergeParam;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//linziy add

/**
 * Created by licj on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class EdmPropertyServiceImpl implements EdmPropertyService {

    private static Logger log = LoggerFactory.getLogger(EdmPropertyServiceImpl.class);

    @Autowired
    private EdmPropertyMapper edmPropertyMapper;

    @Autowired
    private EdmCodeMapper edmCodeMapper;

    @Autowired
    private EdmMethodMapper edmMethodMapper;

    @Autowired
    private EdmClassMapper edmClassMapper;

    @Autowired
    private EdmConnectMapper edmConnectMapper;

    @Autowired
    private EdmConvoluteMapper edmConvoluteMapper;

    @Autowired
    private EdmLinkMapper edmLinkMapper;

    @Autowired
    private EdmUnitMapper edmUnitMapper;

    @Autowired
    private FormulaClient formulaClient;

    @Autowired
    private EdmModelerMapper edmModelerMapper;

    @Autowired
    private EdmConvoluteExtendMapper edmConvoluteExtendMapper;

    @Autowired
    private EdmCondMapper edmCondMapper;

    @Autowired
    private BaseInfoClient baseInfoClient;

    @Autowired
    private OrmClient ormClient;

    @Autowired
    private EdmPropertyGroupMapper edmPropertyGroupMapper;

    @Value("${modeler.version}")
    private String version;

    @Override
    @Transactional(readOnly = false)
    public int delete(String id) {
        return edmPropertyMapper.updateIsDelByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteIds(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                //当删除属性时,删除属性集下面所有的内容
                List<EdmProperty> subList = getAllSubProperties(id);
                if (subList.size() > 0) {
                    for (EdmProperty ep : subList) {
                        edmPropertyMapper.updateIsDelByPrimaryKey(ep.getId());
                    }
                }
                //删除属性
                edmPropertyMapper.updateIsDelByPrimaryKey(id);
                //删除四类扩展属性
                deleteExtendProperty(id);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteExtendProperty(String id) {
        //删除卷积
        EdmConvoluteExample convoluteExample = new EdmConvoluteExample();
        EdmConvoluteExample.Criteria convoluteExampleCriteria = convoluteExample.createCriteria();
        convoluteExampleCriteria.andEdcoEdmpIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
        List<EdmConvolute> convoluteList = edmConvoluteMapper.selectByExample(convoluteExample);
        if (convoluteList.size() > 0) {
            for (EdmConvolute edmConvolute : convoluteList) {
                edmConvoluteMapper.LogicDeleteByPrimaryKey(edmConvolute.getId());
            }
        }
        //删除联动
        EdmLinkExample linkExample = new EdmLinkExample();
        EdmLinkExample.Criteria linkExampleCriteria = linkExample.createCriteria();
        linkExampleCriteria.andEdmlEdmpIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
        List<EdmLink> linkList = edmLinkMapper.selectByExample(linkExample);
        if (linkList.size() > 0) {
            for (EdmLink edmLink : linkList) {
                edmLinkMapper.LogicDeleteByPrimaryKey(edmLink.getId());
            }
        }
        //删除单位
        EdmUnitExample unitExample = new EdmUnitExample();
        EdmUnitExample.Criteria unitExampleCriteria = unitExample.createCriteria();
        unitExampleCriteria.andEdunEdmpIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
        List<EdmUnit> unitList = edmUnitMapper.selectByExample(unitExample);
        if (unitList.size() > 0) {
            for (EdmUnit edmUnit : unitList) {
                edmUnitMapper.LogicDeleteByPrimaryKey(edmUnit.getId());
            }
        }
        //删除关联
        EdmConnectExample connectExample = new EdmConnectExample();
        EdmConnectExample.Criteria connectExampleCriteria = connectExample.createCriteria();
        connectExampleCriteria.andEdcnEdmpIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
        List<EdmConnect> connectList = edmConnectMapper.selectByExample(connectExample);
        if (connectList.size() > 0) {
            for (EdmConnect edmConnect : connectList) {
                edmConnectMapper.LogicDeleteByPrimaryKey(edmConnect.getId());
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(EdmProperty edmProperty) {
        String cid = edmProperty.getEdmpEdmcId();
        Date date = new Date();
        //设置排序字段的值
        Integer seq = null;
        if (!StringUtil.isNullOrEmpty(cid)) {
            seq = edmPropertyMapper.getMaxSeqByCid(cid);
            if (null != seq) {
                seq = seq + 1;
            } else {
                seq = 1;
            }
        }

        edmProperty.setId(UuidCreater.uuid());
        edmProperty.setIsVisible((byte) 1);//默认设置为1，可视
        edmProperty.setIsDel((byte) 0);
        edmProperty.setEdmpSeq(seq);
        edmProperty.setAddtime(date);
        edmProperty.setModtime(date);
        if (Constant.PROPERTY_TYPE_ASSEMBLE.equals(edmProperty.getEdmpValueType())) {
            edmProperty.setTablename(getTablename(edmProperty));
        }

        //如果属性类型是计量单位或者卷积 设置edmp_data_type
        String edmpValueType = edmProperty.getEdmpValueType();
        if (Constant.EDM_CONVOLUTION.equals(edmpValueType)) {
            edmProperty.setEdmpDataType(getEdmdId(edmProperty.getEdmpEdmcId(), Constant.EDM_STATISTICS_NAME));
            //831演示临时使用 如果属性类型是卷积，添加信息到edm_convolution_extends表
            addInfo(edmProperty);
        }else if (Constant.EDM_MEASUREMENT.equals(edmpValueType)) {
            edmProperty.setEdmpDataType(getEdmdId(edmProperty.getEdmpEdmcId(), Constant.EDM_MEASUREUNIT_NAME));
        }
        return edmPropertyMapper.insertSelective(edmProperty);
    }
    //获取计量单位、统计类的id
    private String getEdmdId (String classid,String nameEn){
        String edmcid=null;
        if (!StringUtil.isNullOrEmpty(classid)){
            EdmClass edmClass = edmClassMapper.selectByPrimaryKey(classid);
            if(edmClass != null){
                edmcid =  edmClassMapper.selectEdmcIdByEdmdIdAndName(edmClass.getEdmcEdmdId(),nameEn);
            }
        }
        return edmcid;
    }

    private void addInfo(EdmProperty edmProperty) {
        EdmConvoluteExtend edmConvoluteExtend = new EdmConvoluteExtend();
        edmConvoluteExtend.setEdceEdmpId(edmProperty.getId());
        edmConvoluteExtend.setId(UuidCreater.uuid());
        edmConvoluteExtend.setIsDel((byte) 0);
        edmConvoluteExtend.setEdceEnumValue("Y");
        edmConvoluteExtendMapper.insertSelective(edmConvoluteExtend);//插入第一条
        edmConvoluteExtend.setId(UuidCreater.uuid());
        edmConvoluteExtend.setEdceEnumValue("M");
        edmConvoluteExtendMapper.insertSelective(edmConvoluteExtend);//插入第二条
    }

    @Override
    @Transactional(readOnly = false)
    public int update(EdmProperty edmProperty) {
        edmProperty.setModtime(new Date());
        if (Constant.PROPERTY_TYPE_ASSEMBLE.equals(edmProperty.getEdmpValueType())) {
            edmProperty.setTablename(getTablename(edmProperty));
        }
        //当属性类型修改为非属性集后，做删除属性集下面内容的操作
        if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpValueType()) && !Constant.PROPERTY_TYPE_ASSEMBLE.equals(edmProperty.getEdmpValueType())) {
            //递归删除所有的子类
            List<EdmProperty> subList = getAllSubProperties(edmProperty.getId());
            if (subList.size() > 0) {
                for (EdmProperty ep : subList) {
                    edmPropertyMapper.updateIsDelByPrimaryKey(ep.getId());
                }
            }
        }
        edmConvoluteExtendMapper.deleteByEdceEdmpId(edmProperty.getId());//删除属性周期关系

        //831演示临时使用 如果属性类型是卷积，添加信息到edm_convolution_extends表
        String edmpValueType = edmProperty.getEdmpValueType();
        if (Constant.EDM_CONVOLUTION.equals(edmpValueType)) {
            edmProperty.setEdmpDataType(getEdmdId(edmProperty.getEdmpEdmcId(), Constant.EDM_STATISTICS_NAME));
            addInfo(edmProperty);
        }else if (Constant.EDM_MEASUREMENT.equals(edmpValueType)) {
            edmProperty.setEdmpDataType(getEdmdId(edmProperty.getEdmpEdmcId(), Constant.EDM_MEASUREUNIT_NAME));
        }
        return edmPropertyMapper.updateByPrimaryKeySelective(edmProperty);
    }

    @Override
    @Transactional(readOnly = false)
    public List<EdmProperty> getAllSubProperties(String id) {
        List<EdmProperty> ret = new ArrayList<>();
        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpParentIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
        List<EdmProperty> list = edmPropertyMapper.selectByExample(example);
        if (list.size() > 0) {
            ret.addAll(list);
            for (EdmProperty edmProperty : list) {
                List<EdmProperty> tempList = getAllSubProperties(edmProperty.getId());
                if (tempList.size() > 0) {
                    ret.addAll(tempList);
                }
            }
        }
        return ret;
    }

    @Override
    @Transactional(readOnly = false)
    public void move(String[] ids) {
        int seq = 1;
        if (null != ids && ids.length > 0) {
            for (String id : ids) {
                if (!StringUtils.isEmpty(id)) {
                    edmPropertyMapper.updateSeqById(id, seq);
                    seq++;
                }
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateMethodById(String id, String mid) {
        edmPropertyMapper.updateMethodById(id, mid);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateTriggerCond(String id, String triggerCondId) {
        EdmProperty edmProperty = new EdmProperty();
        edmProperty.setId(id);
        edmProperty.setTriggerCond(triggerCondId);
        edmPropertyMapper.updateByPrimaryKey(edmProperty);
    }

    @Override
    public List<EdmPropertyVO> selectFatherPropertiesByCid(String edmcId, boolean isConstOrNot) {
        List<EdmPropertyVO> edmPropertyVOList = new ArrayList<EdmPropertyVO>();
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmcId);
        if (edmClass != null) {
            if (!StringUtil.isNullOrEmpty(edmClass.getEdmcParentId())) {
                edmPropertyVOList = queryPropertyWithParent(edmPropertyVOList, edmClass.getEdmcParentId(), "", isConstOrNot);
                //先关掉与公式的联调
                if (edmPropertyVOList.size() > 0) {
                    List<String> prprIds = new ArrayList<>();//属性限值
                    List<String> formulaIds = new ArrayList<>();//属性公式
                    List<String> enumInfoCodes = new ArrayList<>();//枚举对象id
                    for (EdmPropertyVO edmPropertyVO : edmPropertyVOList) {
                        String limit = edmPropertyVO.getEdmpValueLimit();
                        String formula = edmPropertyVO.getEdmpFormula();
                        if (!StringUtil.isNullOrEmpty(limit)) {
                            prprIds.add(limit);
                        }
                        if (!StringUtil.isNullOrEmpty(formula)) {
                            formulaIds.add(formula);
                        }
                        if(Constant.EDM_WORDLIST.equals(edmPropertyVO.getEdmpValueType())){
                            if (!StringUtil.isNullOrEmpty(edmPropertyVO.getEdmpDataType())){
                                enumInfoCodes.add(edmPropertyVO.getEdmpDataType());
                            }
                        }
                    }
                    Result enumResult = baseInfoClient.selectByInfoCodes(enumInfoCodes);
                    //先关掉ORM异常
                    if (enumResult.getRetCode() != 1) {
                        //throw new RuntimeException("ORM服务降级处理！");
                    }
                    Map<String,String> enumResultData = (Map<String,String>)enumResult.getData() ;
                    Result result = formulaClient.queryFormulasAndPropertyLimits(prprIds, formulaIds);
                    if (result.getRetCode() != 1) {
                        //throw new RuntimeException("公式设计器服务降级处理！");
                    }
                    Map<String, Map<String, String>> data = (Map<String, Map<String, String>>) result.getData();
                    setFormulas(edmPropertyVOList, data,enumResultData);
                }
            }
        }
        return edmPropertyVOList;
    }

    /**
     * 递归获取类的属性与继承属性
     *
     * @param edmPropertyVOList
     * @param edmcId
     * @param edmcName
     * @return
     */
    public List<EdmPropertyVO> queryPropertyWithParent(List<EdmPropertyVO> edmPropertyVOList, String edmcId, String edmcName, boolean isConstOrNot) {
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmcId);
        String edmClassName = null;
        if (edmClass != null) {
            edmClassName = edmClass.getEdmcName();
            if (!StringUtil.isNullOrEmpty(edmClass.getEdmcParentId())) {
                edmPropertyVOList = queryPropertyWithParent(edmPropertyVOList, edmClass.getEdmcParentId(), edmClassName, isConstOrNot);
            }
        }
        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        if (!StringUtil.isNullOrEmpty(edmcId)) {
            criteria.andEdmpEdmcIdEqualTo(edmcId);
        }
        if (isConstOrNot) {
            criteria.andEdmpValueTypeEqualTo(Constant.PROPERTY_TYPE_CONST);
        } else {
            criteria.andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_CONST);
            criteria.andEdmpParentIdIsNull();
        }
        criteria.andIsDelNotEqualTo((byte) 1);
        example.setOrderByClause("edmp_seq asc");
        List<EdmProperty> edmPropertiyList = edmPropertyMapper.selectByExample(example);
        if (null != edmPropertiyList && edmPropertiyList.size() > 0) {
            if (isConstOrNot) {
                for (EdmProperty edmProperty : edmPropertiyList) {
                    EdmPropertyVO edmPropertyVO = setConstExtend(edmProperty);
                    edmPropertyVO.setEdmpEdmcName(edmClassName);
                    edmPropertyVOList.add(edmPropertyVO);
                }
            } else {
                for (EdmProperty edmProperty : edmPropertiyList) {
                    EdmPropertyVO edmPropertyVO = setEdmPropertyVO(edmProperty);
                    edmPropertyVO.setEdmpEdmcName(edmClassName);
                    edmPropertyVOList.add(edmPropertyVO);
                }
            }
        }
        return edmPropertyVOList;
    }

    @Override
    public List<EdmPropertyVO> selectEdmPropertiesByCid(String edmcId) {
        List<EdmPropertyVO> edmPropertyVOList = null;
        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andIsDelNotEqualTo((byte) 1).andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_CONST).andEdmpEdmcIdEqualTo(edmcId).andEdmpParentIdIsNull();
        example.setOrderByClause("edmp_seq asc");
        List<EdmProperty> edmPropertyList = edmPropertyMapper.selectByExample(example);
        if (null != edmPropertyList && edmPropertyList.size() > 0) {
            List<String> prprIds = new ArrayList<>();//属性限值
            List<String> formulaIds = new ArrayList<>();//属性公式
            List<String> enumInfoCodes = new ArrayList<>();//枚举对象id
            edmPropertyVOList = new ArrayList<>();
            for (EdmProperty edmProperty : edmPropertyList) {
                String limit = edmProperty.getEdmpValueLimit();
                String formula = edmProperty.getEdmpFormula();
                if (!StringUtil.isNullOrEmpty(limit)) {
                    prprIds.add(limit);
                }
                if (!StringUtil.isNullOrEmpty(formula)) {
                    formulaIds.add(formula);
                }
                if(Constant.EDM_WORDLIST.equals(edmProperty.getEdmpValueType())){
                    if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpDataType())){
                        enumInfoCodes.add(edmProperty.getEdmpDataType());
                    }
                }
                EdmPropertyVO edmPropertyVO = setEdmPropertyVO(edmProperty);
                edmPropertyVOList.add(edmPropertyVO);
            }
            Result enumResult = baseInfoClient.selectByInfoCodes(enumInfoCodes);
            //先关掉ORM异常
            if (enumResult.getRetCode() != 1) {
                //throw new RuntimeException("ORM服务降级处理！");
            }
            Map<String,String> enumResultData = (Map<String,String>)enumResult.getData() ;
            //先关掉与公式的联调
            Result result = formulaClient.queryFormulasAndPropertyLimits(prprIds, formulaIds);
            if (result.getRetCode() != 1) {
                //throw new RuntimeException("公式设计器服务降级处理！");
            }
            Map<String, Map<String, String>> data = (Map<String, Map<String, String>>) result.getData();
            setFormulas(edmPropertyVOList, data,enumResultData);
        }
        return edmPropertyVOList;
    }

    //设置属性限值与属性公式
    private void setFormulas(List<EdmPropertyVO> edmPropertyVOList, Map<String, Map<String, String>> data,Map<String,String> enumResultData) {
        if( null != enumResultData && enumResultData.size()>0) {
            if (data == null) {
                data = new HashMap<>();
            }
        }
        if (data != null) {
            //Map<String,Map<String,String>> data = ( Map<String,Map<String,String>>)map.getData();
            Map<String, String> limitMap = data.get("propertyLimitNames");
            Map<String, String> formulaMap = data.get("propertyFormulas");
            Map<String, String> limitDescMap = data.get("propertyLimitDescs");
            //Map<String, String> enumMap = data.get("enumMap");
            for (EdmPropertyVO edmPropertyVO : edmPropertyVOList) {
                String limit = edmPropertyVO.getEdmpValueLimit();
                String formula = edmPropertyVO.getEdmpFormula();
                if (!StringUtil.isNullOrEmpty(limit)) {
                    if (limitMap != null) {
                        edmPropertyVO.setPropertLimit(limitMap.get(limit));
                    }
                    if (limitDescMap != null) {
                        edmPropertyVO.setPropertLimitDesc(limitDescMap.get(limit));
                    }
                }
                if (!StringUtil.isNullOrEmpty(formula) && formulaMap != null) {
                    edmPropertyVO.setPropertFormula(formulaMap.get(formula));
                }
                if(Constant.EDM_WORDLIST.equals(edmPropertyVO.getEdmpValueType())){
                    if (!StringUtil.isNullOrEmpty(edmPropertyVO.getEdmpDataType()) && enumResultData !=null){
                        edmPropertyVO.setEnumName(enumResultData.get(edmPropertyVO.getEdmpDataType()));
                    }
                }
            }
        }
    }

  /*  private Map<String,String> getEnumMap(JSONArray dataSet){
        Map<String, String> enumMap = null;
        if(dataSet != null && dataSet.size()>0){
            enumMap = new HashMap<>();
            for(int i=0;i<dataSet.size();i++){
                JSONObject jsonObject = dataSet.getJSONObject(i);  // 遍历 jsonarray 数组
                enumMap.put((String) jsonObject.get("info_code"),(String) jsonObject.get("word_name"));
            }
        }
        return  enumMap;
    }*/

    @Override
    public List<EdmProperty> selectDataPropertiesByCid(String edmcId, String edmpId) {
        Map<String, String> map = new HashMap<>();
        map.put("edmcId", edmcId);
        map.put("parentId", edmpId);
        List<EdmProperty> edmPropertyList = edmPropertyMapper.selectDataPropertiesByCid(map);
        return edmPropertyList;
    }

    @Override
    public List<EdmPropertyVO> selectEdmConstByCid(String edmcId) {
        List<EdmPropertyVO> edmPropertyVOList = null;
        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpValueTypeEqualTo(Constant.PROPERTY_TYPE_CONST).andIsDelNotEqualTo((byte) 1).andEdmpEdmcIdEqualTo(edmcId);
        example.setOrderByClause("edmp_seq asc");
        List<EdmProperty> edmPropertyList = edmPropertyMapper.selectByExample(example);
        if (null != edmPropertyList && edmPropertyList.size() > 0) {
            edmPropertyVOList = new ArrayList<>();
            for (EdmProperty edmProperty : edmPropertyList) {
                EdmPropertyVO edmPropertyVO = setConstExtend(edmProperty);
                edmPropertyVOList.add(edmPropertyVO);
            }
        }
        return edmPropertyVOList;
    }

    //设置常量扩展值
    private EdmPropertyVO setConstExtend(EdmProperty edmProperty) {
        EdmPropertyVO edmPropertyVO = ModelToVO.edmPropertyToVO(edmProperty);
        edmPropertyVO.setEdmpValueName(edmCodeMapper.selectCodeNameByTypeAndValue("edm_const_type", edmPropertyVO.getEdmpDataType()));
        if (Constant.PROPERTY_DATA_CLASS.equals(edmProperty.getEdmpDataType())) {//属性类型为类时
            //System.out.println(edmClassMapper.getEdmClassNameById(edmProperty.getEdmpValue()));
            edmPropertyVO.setEdmpDataName(edmClassMapper.getEdmcNameById(edmProperty.getEdmpValue()));
        } else {
            edmPropertyVO.setEdmpDataName(edmProperty.getEdmpValue());
        }
        return edmPropertyVO;
    }

    @Override
    public List<EdmPropertyVO> getProperties(String id) {
        List<EdmProperty> edmPropertyList = edmPropertyMapper.selectEdmPropertiesByPid(id);
        List<EdmPropertyVO> edmPropertyVOList = null;
        if (null != edmPropertyList && edmPropertyList.size() > 0) {
            List<String> prprIds = new ArrayList<>();//属性限值
            List<String> formulaIds = new ArrayList<>();//属性公式
            List<String> enumInfoCodes = new ArrayList<>();//枚举对象id
            edmPropertyVOList = new ArrayList<EdmPropertyVO>();
            for (EdmProperty edmProperty : edmPropertyList) {
                String limit = edmProperty.getEdmpValueLimit();
                String formula = edmProperty.getEdmpFormula();
                if (!StringUtil.isNullOrEmpty(limit)) {
                    prprIds.add(limit);
                }
                if (!StringUtil.isNullOrEmpty(formula)) {
                    formulaIds.add(formula);
                }
                if(Constant.EDM_WORDLIST.equals(edmProperty.getEdmpValueType())){
                    if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpDataType())){
                        enumInfoCodes.add(edmProperty.getEdmpDataType());
                    }
                }
                EdmPropertyVO edmPropertyVO = setEdmPropertyVO(edmProperty);
                //edmPropertyVO.setEdmpEdmcName(edmClassMapper.getEdmClassNameById(edmProperty.getEdmpEdmcId()));
                edmPropertyVOList.add(edmPropertyVO);
            }
            Result enumResult = baseInfoClient.selectByInfoCodes(enumInfoCodes);
            //先关掉ORM异常
            if (enumResult.getRetCode() != 1) {
                //throw new RuntimeException("ORM服务降级处理！");
            }
            Map<String,String> enumResultData = (Map<String,String>)enumResult.getData() ;
            //先关掉与公式的联调
            Result result = formulaClient.queryFormulasAndPropertyLimits(prprIds, formulaIds);
            if (result.getRetCode() != 1) {
                //throw new RuntimeException("公式设计器服务降级处理！");
            }
            Map<String, Map<String, String>> data = (Map<String, Map<String, String>>) result.getData();
            setFormulas(edmPropertyVOList, data,enumResultData);
        }
        return edmPropertyVOList;
    }

    public EdmPropertyVO setEdmPropertyVO(EdmProperty edmProperty) {
        String attrVal = "";
        EdmPropertyVO edmPropertyVO = ModelToVO.edmPropertyToVO(edmProperty);
        edmPropertyVO.setEdmpValueName(edmCodeMapper.selectPropertyValueBycodeValue(edmPropertyVO.getEdmpValueType()));
        String edmmId = edmPropertyVO.getEdmpEdmmId();
        if (!StringUtil.isNullOrEmpty(edmmId)) {
            String[] edmmIds = edmmId.split(",");
            String edmmName = "";
            for (String id : edmmIds) {
                EdmMethod edmMethod = edmMethodMapper.selectByPrimaryKey(id);
                if (edmMethod != null){
                    edmmName = edmmName + edmMethod.getEdmmName() + ",";
                }
            }
            if (!StringUtil.isNullOrEmpty(edmmName)) {
                edmmName = edmmName.substring(0, edmmName.length() - 1);
            }
            edmPropertyVO.setEdmpEdmmName(edmmName);
        }
        int convolute = edmPropertyMapper.selectConvoluteByPropertyId(edmPropertyVO.getId());//卷积属性个数
        int link = edmPropertyMapper.selectLinkByPropertyId(edmPropertyVO.getId());//关联属性个数
        int connect = edmPropertyMapper.selectConnectByPropertyId(edmPropertyVO.getId());//联动属性个数
        int unit = edmPropertyMapper.selectUnitByPropertyId(edmPropertyVO.getId());//单位属性个数
        if (convolute > 0) {
            attrVal = attrVal + Constant.PROPERTY_CONVOLUTE + "，";
        }
        if (link > 0) {
            attrVal = attrVal + Constant.PROPERTY_CONNECT + "，";
        }

        if (connect > 0) {
            attrVal = attrVal + Constant.PROPERTY_LINK + "，";
        }
        if (unit > 0) {
            attrVal = attrVal + Constant.PROPERTY_UNIT + "，";
        }
        if (attrVal.length() > 0) {
            attrVal = attrVal.substring(0, attrVal.length() - 1);
        }
        if (StringUtil.isNullOrEmpty(attrVal)) {
            attrVal = Constant.PROPERTY_EXTEND;
        }
        edmPropertyVO.setEdmpExtendProperty(attrVal);
        return edmPropertyVO;
    }

    //linziy Add
    //2017.04.25
    /*
    @ 根据属性ID 查找属性
    */
    @Override
    public List<EdmLinkVO> getConnects(String id) {
        //--------查找类与属性------------------------------------------------------------------------------------------
        EnumMap<EnumEdmData, Object> resEnumMap = getClassAndProperty(id);
        EdmClass edmClass = (EdmClass) resEnumMap.get(EnumEdmData.EDMCLASS);
        EdmProperty edmProperty = (EdmProperty) resEnumMap.get(EnumEdmData.EDMPROPERTY);

        String edmClassNameStr = null;
        String edmPropertyNameStr = null;
        String edmClassIdStr = null;
        if (edmClass != null) {
            edmClassNameStr = edmClass.getEdmcName();
            edmClassIdStr = edmClass.getId();
        }
        if (edmProperty != null) {
            edmPropertyNameStr = edmProperty.getEdmpName();
        }
        //----------------a parting line -------------------------------------------------------------------------------
        List<EdmLinkVO> resultList = new ArrayList<EdmLinkVO>();
        List<EdmLink> sourceList = edmLinkMapper.selectEdmLinkPropertiesListByEdmlEdmpLinkId(id);//被关联的数据
        for (EdmLink epv : sourceList) {
            //-----------------------------------------------------
            EdmLinkVO ec = ModelToVO.edmLinkToVO(epv);
            ec.setEdmlEdmcIdLink(edmClassIdStr);
            ec.setEdmlEdmcNameLink(edmClassNameStr);
            ec.setEdmlEdmpNameLink(edmPropertyNameStr);
            //-----------------------------------------------------------------------------
            EnumMap<EnumEdmData, Object> resEnumMapLink = getClassAndProperty(epv.getEdmlEdmpId());//根据edmlEdmplinkId 查数
            EdmClass edmClassLink = (EdmClass) resEnumMapLink.get(EnumEdmData.EDMCLASS);
            EdmProperty edmPropertyLink = (EdmProperty) resEnumMapLink.get(EnumEdmData.EDMPROPERTY);
            String edmcIdStr = null;
            String edmcNameStr = null;
            String edmpNameStr = null;
            if (edmClass != null) {
                edmcNameStr = edmClassLink.getEdmcName();
                edmcIdStr = edmClassLink.getId();
            }

            if (edmProperty != null) {
                edmpNameStr = edmPropertyLink.getEdmpName();
            }
            ec.setEdmcId(edmcIdStr);
            ec.setEdmcName(edmcNameStr);
            ec.setEdmpName(edmpNameStr);
            //查找
            resultList.add(ec);
        }
        return resultList;
    }

    //linziy Add
    //2017.04.25
    /*
    @ 根据属性ID 查找属性
    */
    @Override
    public List<EdmLinkVO> getConnectsByPropertyId(String id) {
        List<EdmLinkVO> edmLinkVOS = null;
        List<EdmLink> edmLinks = edmLinkMapper.selectEdmLinkPropertiesListByEdmlEdmpLinkId(id);//被关联的数据
        if (null != edmLinks && edmLinks.size() > 0) {
            edmLinkVOS = new ArrayList<>();
            List<String> formulas = new ArrayList<>();
            List<String> conds = new ArrayList<>();
            List<String> edmpIdList = new ArrayList<>();
            for (EdmLink edmLink : edmLinks) {
                if (!StringUtil.isNullOrEmpty(edmLink.getEdmlFormula())) {
                    formulas.add(edmLink.getEdmlFormula());
                }
                EdmLinkVO edmLinkVO = ModelToVO.edmLinkToVO(edmLink);
                String edmpEdmpId = edmLink.getEdmlEdmpId();
                if (!StringUtil.isNullOrEmpty(edmpEdmpId)) {
                    edmpIdList.add(edmpEdmpId);
                    EdmProperty edmProperty = edmPropertyMapper.selectByPrimaryKey(edmpEdmpId);
                    if (edmProperty != null) {
                        edmLinkVO.setEdmpName(edmProperty.getEdmpName());
                        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmProperty.getEdmpEdmcId());
                        if (edmClass != null) {
                            edmLinkVO.setEdmcName(edmClass.getEdmcName());
                        }
                    }

                }
                edmLinkVOS.add(edmLinkVO);
            }
            Map<String, String> map = new HashMap<>();
            if (edmpIdList.size() > 0) {
                List<EdmCond> edmConds = edmCondMapper.selectByEdmpIds(edmpIdList);
                if (null != edmConds && edmConds.size() > 0) {
                    for (EdmCond edmCond : edmConds) {
                        conds.add(edmCond.getEdcoCond());
                        map.put(edmCond.getEdcoEdmpId(), edmCond.getEdcoCond());
                    }
                }
            }
            //先关掉与公式的联调
            Result result = formulaClient.getPrplConditionDescByPrplIdArr(conds, formulas);
            if (result.getRetCode() != 1) {
                // throw new RuntimeException("公式设计器服务降级处理！");
            }
            Map<String, Map<String, String>> data = (Map<String, Map<String, String>>) result.getData();
            setLinkFormulas(edmLinkVOS, data, map);
        }
        return edmLinkVOS;
    }

    //设置对象定位公式
    private void setLinkFormulas(List<EdmLinkVO> edmLinkVOS, Map<String, Map<String, String>> data, Map<String, String> map) {
        if (data != null) {

            Map<String, String> formulaMap = data.get("formulas");
            Map<String, String> formulaDescMap = data.get("formulaDescs");
            Map<String, String> condsMap = data.get("conds");
            Map<String, String> condDescsMap = data.get("condDescs");
            for (EdmLinkVO edmLinkVO : edmLinkVOS) {
                if (!StringUtil.isNullOrEmpty(edmLinkVO.getEdmlFormula()) && formulaMap != null) {
                    edmLinkVO.setFormula(formulaMap.get(edmLinkVO.getEdmlFormula()));
                }
                if (!StringUtil.isNullOrEmpty(edmLinkVO.getEdmlFormula()) && formulaDescMap != null) {
                    edmLinkVO.setFormulaDesc(formulaDescMap.get(edmLinkVO.getEdmlFormula()));
                }
                if (map != null) {
                    if (condsMap != null) {
                        edmLinkVO.setCondName(condsMap.get(map.get(edmLinkVO.getEdmlEdmpId())));
                    }
                    if (condDescsMap != null) {
                        edmLinkVO.setCondDesc(condDescsMap.get(map.get(edmLinkVO.getEdmlEdmpId())));
                    }
                }
            }
        }
    }

    /**
     * 根据属性id 查找卷积
     * @param id 属性id
     * @return 返回最新修改的卷积
     */
    @Override
    public EdmConvoluteVO getConvolute(String id) {
        //--------查找类与属性------------------------------------------------------------------------------------------
        EnumMap<EnumEdmData, Object> resEnumMap = getClassAndProperty(id);
        EdmClass edmClass = (EdmClass) resEnumMap.get(EnumEdmData.EDMCLASS);
        EdmProperty edmProperty = (EdmProperty) resEnumMap.get(EnumEdmData.EDMPROPERTY);

        String edmClassNameStr = null;
        String edmPropertyNameStr = null;
        String edmClassIdStr = null;
        if (edmClass != null) {
            edmClassNameStr = edmClass.getEdmcName();
            edmClassIdStr = edmClass.getId();
        }
        if (edmProperty != null) {
            edmPropertyNameStr = edmProperty.getEdmpName();
            //edmClassIdStr = edmProperty.getEdmpEdmcId();
        }
        //----------------a parting line -------------------------------------------------------------------------------
        EdmConvoluteVO edmConvoluteVO = new EdmConvoluteVO();
        EdmConvolute edmConvolute = edmConvoluteMapper.selectEdmConvoluteByPropertyId(id);
        if (edmConvolute == null) {
            return null;
        }
        edmConvoluteVO = ModelToVO.edmConvoluteToVO(edmConvolute);
        //-------------------------------------------------
        edmConvoluteVO.setClassId(edmClassIdStr);
        edmConvoluteVO.setClassName(edmClassNameStr);
        edmConvoluteVO.setPropertyName(edmPropertyNameStr);
        //-------------------------------------------------
        return edmConvoluteVO;
    }

    @Override
    public EdmConvoluteVO getConvoluteByPropertyId(String id) {
        EdmConvolute edmConvolute = edmConvoluteMapper.selectEdmConvoluteByPropertyId(id);
        EdmConvoluteVO edmConvoluteVO = null;
        if (edmConvolute != null) {
            edmConvoluteVO = ModelToVO.edmConvoluteToVO(edmConvolute);
            EdmMethod edmMethod = edmMethodMapper.selectByPrimaryKey(edmConvolute.getEdcoConvoluteFormula());
            if (edmMethod != null) {
                edmConvoluteVO.setEdcoConvoluteFormula(edmMethod.getEdmmName());
                edmConvoluteVO.setMethodDesc(edmMethod.getEdmmDesc());
            }
        }

        return edmConvoluteVO;
    }

    @Override
    @Transactional(readOnly = false)
    public List<EdmLinkVO> getLinksByPropertyId(String id) {
        List<EdmLinkVO> edmLinkVOS = null;
        List<EdmLink> edmLinks = edmLinkMapper.getEdmLinkProperties(id);
        if (null != edmLinks && edmLinks.size() > 0) {
            edmLinkVOS = new ArrayList<>();
            List<String> formulas = new ArrayList<>();
            for (EdmLink edmLink : edmLinks) {
                if (!StringUtil.isNullOrEmpty(edmLink.getEdmlFormula())) {
                    formulas.add(edmLink.getEdmlFormula());
                }
                EdmLinkVO edmLinkVO = ModelToVO.edmLinkToVO(edmLink);
                EdmConnect edmConnect = edmConnectMapper.getEdmConnectPropertieByEdmpId(edmLink.getEdmlEdmpLinkId());
                if (edmConnect != null) {
                    edmLinkVO.setEdclUpdateType(edmConnect.getEdcnUpdateType());
                    edmLinkVO.setEdcnLinkPreservable(edmConnect.getEdcnLinkPreservable());
                    edmLinkVO.setAsyncTypePriority(edmConnect.getAsyncTypePriority());
                    edmLinkVO.setEdclUpdateTime(edmConnect.getEdcnUpdateTime());
                    edmLinkVO.setEdclType(edmConnect.getEdcnType());
                }
                EdmProperty edmProperty = edmPropertyMapper.selectByPrimaryKey(edmLink.getEdmlEdmpLinkId());
                if (edmProperty != null) {
                    edmLinkVO.setEdmpName(edmProperty.getEdmpName());
                    String edmcName = edmClassMapper.getEdmcNameById(edmProperty.getEdmpEdmcId());
                    edmLinkVO.setEdmcId(edmProperty.getEdmpEdmcId());
                    edmLinkVO.setEdmcName(edmcName);
                }
                edmLinkVOS.add(edmLinkVO);
            }
            //先关掉与公式的联调
            Result result = formulaClient.getPrplConditionDescByPrplIdArr(null, formulas);
            if (result.getRetCode() != 1) {
                // throw new RuntimeException("公式设计器服务降级处理！");
            }
            Map<String, Map<String, String>> data = (Map<String, Map<String, String>>) result.getData();
            setLinkFormulas(edmLinkVOS, data, null);
        }
        return edmLinkVOS;
    }

    @Override
    public List<EdmUnitVO> getUnits(String id) {
        //--------查找类与属性------------------------------------------------------------------------------------------
        EnumMap<EnumEdmData, Object> resEnumMap = getClassAndProperty(id);
        EdmClass edmClass = (EdmClass) resEnumMap.get(EnumEdmData.EDMCLASS);
        EdmProperty edmProperty = (EdmProperty) resEnumMap.get(EnumEdmData.EDMPROPERTY);

        String edmClassNameStr = null;
        String edmPropertyNameStr = null;
        String edmClassIdStr = null;
        if (edmClass != null) {
            edmClassNameStr = edmClass.getEdmcName();
            edmClassIdStr = edmClass.getId();
        }
        if (edmProperty != null) {
            edmPropertyNameStr = edmProperty.getEdmpName();
            //edmClassIdStr = edmProperty.getEdmpEdmcId();
        }
        //----------------a parting line -------------------------------------------------------------------------------
        List<EdmUnitVO> resultList = new ArrayList<EdmUnitVO>();
        List<EdmUnit> sourceList = edmUnitMapper.selectEdmUnitListByPropertyId(id);
        for (EdmUnit epv : sourceList) {
            EdmUnitVO ec = ModelToVO.edmUnitToVO(epv);
            //-------------------------------------------------
            ec.setClassId(edmClassIdStr);
            ec.setClassName(edmClassNameStr);
            ec.setPropertyName(edmPropertyNameStr);
            //-------------------------------------------------
            resultList.add(ec);
        }
        return resultList;
    }

    @Override
    public List<EdmUnitVO> getUnitsByPropertyId(String id) {
        List<EdmUnitVO> edmUnitVOS = null;
        List<EdmUnit> edmUnits = edmUnitMapper.selectEdmUnitListByPropertyId(id);
        if (edmUnits != null && edmUnits.size() > 0) {
            edmUnitVOS = new ArrayList<EdmUnitVO>();
            for (EdmUnit edmUnit : edmUnits) {
                EdmUnitVO edmUnitVO = ModelToVO.edmUnitToVO(edmUnit);
                if (edmUnit != null) {
                    edmUnitVO.setEdmpName(edmPropertyMapper.getEdmpNameById(edmUnit.getEdunQtyEdmpId()));
                }
                edmUnitVOS.add(edmUnitVO);
            }
        }

        return edmUnitVOS;
    }

    /**
     * 根据类id 查找属性名称与id列表
     *
     * @param edmpEdmcId 类id
     * @return
     */
    @Override
    public List<EdmProperty> getPropertyNameAndByEdmClassId(String edmpEdmcId, String edmpName) {
        EdmPropertyExample edmPropertyExample = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = edmPropertyExample.createCriteria();
        if (!edmpEdmcId.isEmpty()) {
            criteria.andEdmpEdmcIdEqualTo(edmpEdmcId);
        }
        if (!edmpName.isEmpty()) {
            edmpName = edmpName.trim() + "%";
            criteria.andEdmpNameLike(edmpName);
        }
        criteria.andIsDelNotEqualTo((byte) 1);
        List<EdmProperty> list = edmPropertyMapper.selectEdmNameByExample(edmPropertyExample);
        return list;
    }
    /**
     * autor :linziy
     * time:2017.04.28
     * 查找同类的单位属性
     *
     * @param id 当前属性id
     * @return
     */
    @Override
    public List<EdmProperty> findPropertyNameByIdInSameClass(String id) {
        EdmProperty edmProperty = edmPropertyMapper.selectByPrimaryKey(id);
        String strClassId = edmProperty.getEdmpEdmcId();//类id
        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpEdmcIdEqualTo(strClassId);
        criteria.andIsDelNotEqualTo((byte) 1);
        List<EdmProperty> resultlist = edmPropertyMapper.selectEdmNameByExample(example);
        return resultlist;
    }

    @Override
    public EdmPropertyVO getProperty(String id) {
        EdmProperty edmProperty = edmPropertyMapper.selectByPrimaryKey(id);
        EdmPropertyVO edmPropertyVO = null;
        if (edmProperty != null) {
            edmPropertyVO = ModelToVO.edmPropertyToVO(edmProperty);
            //edmPropertyVO.setEdmpName(edmProperty.getEdmpName());
            EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmProperty.getEdmpEdmcId());
            if (null != edmClass) {
                edmPropertyVO.setEdmpEdmcName(edmClass.getEdmcName());
                edmPropertyVO.setEdmpEdmcNameEn(edmClass.getEdmcNameEn());
            }
        }
        return edmPropertyVO;
    }

    /**
     * 根据属性id 查找类EdmClass与属性EdmProperty
     *
     * @param id
     * @return
     */
    private EnumMap<EnumEdmData, Object> getClassAndProperty(String id) {
        EnumMap<EnumEdmData, Object> retMap = new EnumMap<EnumEdmData, Object>(EnumEdmData.class);
        EdmProperty edmProperty = edmPropertyMapper.selectByPrimaryKey(id);

        if (edmProperty == null) {
            retMap.put(EnumEdmData.EDMPROPERTY, null);
            retMap.put(EnumEdmData.EDMCLASS, null);
            return retMap;
        }
        EdmClass edmClass = new EdmClass();
        String strClassId = edmProperty.getEdmpEdmcId();//类id
        if (!strClassId.isEmpty()) {
            edmClass = edmClassMapper.selectByPrimaryKey(strClassId);
        }
        retMap.put(EnumEdmData.EDMCLASS, edmClass);
        retMap.put(EnumEdmData.EDMPROPERTY, edmProperty);
        return retMap;
    }

    @Override
    public String checkEdmpCode(String edmpCode, String edmpEdmcId,String edmpParentId) {
        String errorStr = null;
        if (StringUtil.isNullOrEmpty(edmpCode)) {
            errorStr = "编码不能为空";
            return errorStr;
        }
        if (StringUtil.isNullOrEmpty(edmpEdmcId)) {
            errorStr = "类ID不能为空";
            return errorStr;
        }
        EdmPropertyExample edmPropertyExample = new EdmPropertyExample();
        //edmPropertyExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmpCodeEqualTo(edmpCode).andEdmpEdmcIdEqualTo(edmpEdmcId);// 同一个类中
        List<EdmProperty> edmPropertyList;
        if (!StringUtil.isNullOrEmpty(edmpParentId)) {
            edmPropertyExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmpCodeEqualTo(edmpCode).andEdmpEdmcIdEqualTo(edmpEdmcId).andEdmpParentIdEqualTo(edmpParentId);// 同一个属性集中
            edmPropertyList = edmPropertyMapper.selectByExample(edmPropertyExample);
        } else {
            edmPropertyExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmpCodeEqualTo(edmpCode).andEdmpEdmcIdEqualTo(edmpEdmcId).andEdmpParentIdIsNull();// 同一个类中
            edmPropertyList = edmPropertyMapper.selectByExample(edmPropertyExample);
        }
        if (null != edmPropertyList && edmPropertyList.size() > 0) {
            errorStr = "编码已存在";
            return errorStr;
        }
        return errorStr;
    }

    @Override
    public String checkEdmpName(String edmpName, String edmpEdmcId, String edmpParentId) {
        String errorStr = null;
        if (StringUtil.isNullOrEmpty(edmpName)) {
            errorStr = "名称不能为空";
            return errorStr;
        }
        if (StringUtil.isNullOrEmpty(edmpEdmcId)) {
            errorStr = "类ID不能为空";
            return errorStr;
        }

        //获取同类中未删除的同名属性集合
        EdmPropertyExample edmPropertyExample = new EdmPropertyExample();
        List<EdmProperty> edmPropertyList;
        //edmpParentId为空时，为类中属性，非空时为属性集中属性
        if (!StringUtil.isNullOrEmpty(edmpParentId)) {
            edmPropertyExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmpNameEqualTo(edmpName).andEdmpEdmcIdEqualTo(edmpEdmcId).andEdmpParentIdEqualTo(edmpParentId);// 同一个属性集中
            edmPropertyList = edmPropertyMapper.selectByExample(edmPropertyExample);
        } else {
            edmPropertyExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmpNameEqualTo(edmpName).andEdmpEdmcIdEqualTo(edmpEdmcId).andEdmpParentIdIsNull();// 同一个类中
            edmPropertyList = edmPropertyMapper.selectByExample(edmPropertyExample);
        }
        if (null != edmPropertyList && edmPropertyList.size() > 0) {
            errorStr = "属性名已存在";
            return errorStr;
        }
        return errorStr;
    }

    /**
     * 获取类下唯一的属性编码
     *
     * @param edmpEdmcId
     * @return
     */
    @Override
    public String getUniqueEdmpCodeByClassId(String edmpEdmcId) {
        String edmpCode = "";
        String classShortName = "";
        //获取类简称
        EdmClassExample example1 = new EdmClassExample();
        example1.createCriteria().andIdEqualTo(edmpEdmcId).andIsDelNotEqualTo((byte) 1);
        List<EdmClass> edmClassList = edmClassMapper.selectByExample(example1);
        if (!edmClassList.isEmpty()) {
            if (edmClassList.get(0).getEdmcShortName() != null) {
                classShortName = edmClassList.get(0).getEdmcShortName();
            }
        }
        //获取类下属性集合
        EdmPropertyExample example2 = new EdmPropertyExample();
        example2.createCriteria().andEdmpEdmcIdEqualTo(edmpEdmcId).andIsDelNotEqualTo((byte) 1);
        List<EdmProperty> edmPropertyList = edmPropertyMapper.selectByExample(example2);
        String propertyMaxCode = "";
        if (!edmPropertyList.isEmpty()) {
            propertyMaxCode = getMaxPropertyEdmpCode(edmPropertyList);
        }
        if (StringUtils.isEmpty(propertyMaxCode)) {
            edmpCode = classShortName + "001";
        } else {
            edmpCode = classShortName + propertyMaxCode;
        }
        return edmpCode;
    }

    /**
     * 根据类id获取属性表最大编码
     *
     * @param edmPropertyList
     * @return
     */
    public String getMaxPropertyEdmpCode(List<EdmProperty> edmPropertyList) {
        int maxEdmpCode = 0;
        for (EdmProperty edmProperty : edmPropertyList) {
            String edmpCode = edmProperty.getEdmpCode();
            if (edmpCode.length() > 2) {
                String code = edmpCode.substring(edmpCode.length() - 3, edmpCode.length());
                int edmpCodeNum = NumberUtils.toInt(code);
                if (edmpCodeNum > maxEdmpCode) {
                    maxEdmpCode = edmpCodeNum;
                }
            }
        }
        //判断是否是111
        if (maxEdmpCode == 999) {
            return "999";
        }
        maxEdmpCode = maxEdmpCode + 1;
        //补0
        return appendZero(maxEdmpCode);
    }

    public String appendZero(int maxEdmpCode) {
        if (Integer.toString(maxEdmpCode).length() == 1) {
            return "00" + Integer.toString(maxEdmpCode);
        } else if (Integer.toString(maxEdmpCode).length() == 2) {
            return "0" + Integer.toString(maxEdmpCode);
        } else {
            return Integer.toString(maxEdmpCode);
        }
    }


    /**
     * 根据类id获取属性树
     * @param id
     * @return
     */
    @Override
    public List<EdmPropertyVO> getPropertyTreeByClassId(String id, int flag) {
        List<EdmPropertyVO> retList = new ArrayList<EdmPropertyVO>();
        EdmPropertyExample example = new EdmPropertyExample();
        List<EdmProperty> edmPropertyList = new ArrayList<>();
        String edmcid = id;
        if (flag == 1) {
            example.createCriteria().andEdmpEdmcIdEqualTo(edmcid).andIsDelNotEqualTo((byte) 1)
                    .andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_CONST);
            edmPropertyList = edmPropertyMapper.selectByExample(example);
            edmPropertyList = getAllFatherProperties(edmPropertyList, edmcid,false);//isConst是否取出常量
            //edmPropertyList.addAll();
        } else if (flag == 2) {
            EdmClassExample edmClassExample = new EdmClassExample();
            EdmClassExample.Criteria criteria = edmClassExample.createCriteria();
            criteria.andEdmcNameEnEqualTo(id).andIsDelNotEqualTo((byte) 1);
            List<EdmClass> edmClassList = edmClassMapper.selectByExample(edmClassExample);
            if (!edmClassList.isEmpty()) {
                edmcid = edmClassList.get(0).getId();
                example.createCriteria().andEdmpEdmcIdEqualTo(edmcid).andIsDelNotEqualTo((byte) 1)
                        .andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_CONST);
                edmPropertyList = edmPropertyMapper.selectByExample(example);
                edmPropertyList = getAllFatherProperties(edmPropertyList, edmcid,false);
                //edmPropertyList.addAll();
            }
        }
        //实体转VO
        List<EdmPropertyVO> edmPropertyVOList = null;
        //查找出全部的根节点
        List<EdmPropertyVO> rootList = null;
        if (null != edmPropertyList && edmPropertyList.size() > 0) {
            edmPropertyVOList = new ArrayList<EdmPropertyVO>();
            rootList = new ArrayList<EdmPropertyVO>();
            for (EdmProperty edmProperty : edmPropertyList) {
                if ("edm_code".equals(edmProperty.getEdmpCode()) || "ecos_code".equals(edmProperty.getEdmpCode())) {
                    edmProperty.setEdmpCode("id");
                }
                EdmPropertyVO edmPropertyVO = ModelToVO.edmPropertyToVO(edmProperty);
                if (edmcid.equals(edmProperty.getEdmpEdmcId())) {
                    edmPropertyVO.setFather(false);
                } else {
                    edmPropertyVO.setFather(true);
                }
                edmPropertyVOList.add(edmPropertyVO);
                if (StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {//parentId为空说明是根节点
                    rootList.add(edmPropertyVO);
                }
            }
        }
        if (null != rootList && rootList.size() > 0) {
            for (EdmPropertyVO edmPropertyVO : rootList) {
                //设置孩子节点
                EdmPropertyVO epv = setChilds(edmPropertyVO, edmPropertyVOList);
                //设置level
                epv.setCasEdmpCode(epv.getEdmpCode());
                epv.setCasEdmpName(epv.getEdmpName());
                epv = setLevelsAndCas(epv, 1, edmPropertyVO.getCasEdmpCode(), edmPropertyVO.getCasEdmpName());
                retList.add(epv);
            }
        }
        return retList;
    }

    //根据类id获取所有父类属性
    public List<EdmProperty> getAllFatherProperties(List<EdmProperty> edmPropertyList, String edmcId,boolean isConst) {
        //List<EdmProperty> edmPropertiyList = new ArrayList<>();
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmcId);
        if (edmClass != null) {
            String parentid = edmClass.getEdmcParentId();
            if (!StringUtil.isNullOrEmpty(parentid)) {
                EdmPropertyExample example = new EdmPropertyExample();
                EdmPropertyExample.Criteria criteria = example.createCriteria();
                criteria.andEdmpEdmcIdEqualTo(parentid);
                if(!isConst){
                    criteria.andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_CONST);
                }
                criteria.andIsDelNotEqualTo((byte) 1);
                List<EdmProperty> tmpList = edmPropertyMapper.selectByExample(example);
                if (tmpList != null && tmpList.size() > 0) {
                    edmPropertyList.addAll(tmpList);
                }
                edmPropertyList = getAllFatherProperties(edmPropertyList, parentid,isConst);//isConst表示不取常量类型的属性
            }
        }
        return edmPropertyList;
    }

    public EdmPropertyVO setChilds(EdmPropertyVO edmPropertyVO, List<EdmPropertyVO> edmPropertyVOList) {
        for (EdmPropertyVO e : edmPropertyVOList) {
            if (edmPropertyVO.getId().equals(e.getEdmpParentId())) {
                List<EdmPropertyVO> t = null;
                if (edmPropertyVO.getChildren() == null) {
                    t = new ArrayList<EdmPropertyVO>();
                } else {
                    t = edmPropertyVO.getChildren();
                }
                t.add(setChilds(e, edmPropertyVOList));
                edmPropertyVO.setChildren(t);
            }
        }
        return edmPropertyVO;
    }

    public EdmPropertyVO setLevelsAndCas(EdmPropertyVO edmPropertyVO, int level, String casEdmpCode, String casEdmpName) {
        edmPropertyVO.setLevel(level);
        if (edmPropertyVO.getChildren() != null) {
            List<EdmPropertyVO> childList = new ArrayList<EdmPropertyVO>();
            for (EdmPropertyVO e : edmPropertyVO.getChildren()) {
                childList.add(e);
            }
            for (EdmPropertyVO epv : childList) {
                String cec = "";
                String cen = "";
                if (!StringUtils.isEmpty(casEdmpCode) && !StringUtils.isEmpty(epv.getEdmpCode())) {
                    cec = casEdmpCode + "." + epv.getEdmpCode();
                } else if (!StringUtils.isEmpty(casEdmpCode)) {
                    cec = casEdmpCode;
                } else if (!StringUtils.isEmpty(epv.getEdmpCode())) {
                    cec = epv.getEdmpCode();
                } else {
                    cec = "";
                }
                if (!StringUtils.isEmpty(casEdmpName) && !StringUtils.isEmpty(epv.getEdmpName())) {
                    cen = casEdmpName + "." + epv.getEdmpName();
                } else if (!StringUtils.isEmpty(casEdmpName)) {
                    cen = casEdmpName;
                } else if (!StringUtils.isEmpty(epv.getEdmpName())) {
                    cen = epv.getEdmpName();
                } else {
                    cen = "";
                }
                epv.setCasEdmpCode(cec);
                epv.setCasEdmpName(cen);
                EdmPropertyVO temp = setLevelsAndCas(epv, level + 1, cec, cen);
                List<EdmPropertyVO> epvList = edmPropertyVO.getChildren();
                epvList.remove(epv);
                epvList.add(temp);
                edmPropertyVO.setChildren(epvList);
            }
        }
        return edmPropertyVO;
    }

    //-----------------------------------------------------------------------
    @Override
    public String getValueLimitById(String id) {
        EdmProperty edmProperty = edmPropertyMapper.selectByPrimaryKey(id);
        if (edmProperty != null) {
            return edmProperty.getEdmpValueLimit();
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void updateProperty(EdmProperty edmProperty) {
        edmPropertyMapper.updateByPrimaryKeySelective(edmProperty);
    }

    /**
     * 根据类id查询类的是特征值的属性
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<EdmProperty> getTwoProperties(String id) {
        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpEdmcIdEqualTo(id).andIsDelNotEqualTo((byte) 1).andIsCharacterEqualTo((byte) 1);
        example.setOrderByClause("edmp_seq asc");
        List<EdmProperty> list = edmPropertyMapper.selectByExample(example);
        return list;
    }

    /**
     * 根据模型id查询枚举类的是特征值的属性
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<EdmProperty> getEnumTwoProperties(String id) {
        EdmClass enumClass = edmClassMapper.selectSpecialClass(id, Constant.CLASS_WORD);
        if (enumClass == null) {
            return null;
        } else {
            return getTwoProperties(enumClass.getId());
        }
    }

    /**
     * 根据属性id查询属性和所属类
     * @param propertyId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public HashMap<String, Object> getPropertyAndClass(String propertyId) {
        HashMap<String, Object> ret = null;
        if (!StringUtils.isEmpty(propertyId)) {
            //查询属性
            EdmPropertyExample example = new EdmPropertyExample();
            EdmPropertyExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(propertyId).andIsDelNotEqualTo((byte) 1);
            List<EdmProperty> propertyList = edmPropertyMapper.selectByExample(example);
            if (propertyList.size() > 0) {
                ret = new HashMap<String, Object>();
                ret.put("property", propertyList.get(0));
                //查询类
                if (!StringUtils.isEmpty(propertyList.get(0).getEdmpEdmcId())) {
                    EdmClassExample exampleClass = new EdmClassExample();
                    EdmClassExample.Criteria criteriaClass = exampleClass.createCriteria();
                    criteriaClass.andIdEqualTo(propertyList.get(0).getEdmpEdmcId()).andIsDelNotEqualTo((byte) 1);
                    List<EdmClass> classList = edmClassMapper.selectByExample(exampleClass);
                    if (classList.size() > 0) {
                        ret.put("class", classList.get(0));
                    }
                }
                return ret;
            }
        }
        return ret;
    }

    /**
     * 根据类id查询特征值的属性列表
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<EdmProperty> selectIsCharacterByCid(String id) {
        // 根据edmp_edmc_id 查询 对象呈现格式的属性
        List<EdmProperty> edmPropertyList= edmPropertyMapper.selectIsCharacterByCid(id);
        // 根据edmp_edmc_id查询所有父类属性
        List<EdmPropertyVO> edmProperties = selectFatherPropertiesByCid(id,false);
        //遍历父类属性
        for (EdmPropertyVO edmPropertyVO:edmProperties) {
            //筛选 将对象呈现属性的数据放入结果集
            if (!StringUtil.isNullOrEmpty(edmPropertyVO.getIsCharacter()) && edmPropertyVO.getIsCharacter() == ((byte) 1)){
                EdmProperty edmProperty = new EdmProperty();
                edmProperty.setId(edmPropertyVO.getId());
                edmProperty.setEdmpName(edmPropertyVO.getEdmpName());
                edmPropertyList.add(edmProperty);
            }
        }
        return edmPropertyList;
    }

    @Override
    public Map<String, Object> getPropertyTypeAndValue(String classId, String edmpCode) {
        Map<String, Object> map = null;
        if (!StringUtil.isNullOrEmpty(classId) && !StringUtil.isNullOrEmpty(edmpCode)) {
            EdmProperty edmProperty = edmPropertyMapper.getPropertyTypeAndValue(classId, edmpCode);
            if (edmProperty != null) {
                map = new HashMap<>();
                String dataType = edmProperty.getEdmpDataType();
                String value = edmProperty.getEdmpValue();
                if (!StringUtil.isNullOrEmpty(dataType) && !StringUtil.isNullOrEmpty(value)) {
                    map.put("dataType", dataType);
                    if ("value".equals(dataType) || "object".equals(dataType)) {
                        map.put("value", value);
                    }
                    if ("class".equals(dataType)) {
                        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(value);
                        map.put("value", edmClass);
                    }
                }
            }
        }
        return map;
    }

    /**
     * 根据模型版本号和类的英文名称获取类及其父类的所有卷积属性
     * @param edmdVer
     * @param edmcNameEn
     * @return
     */
    @Override
    public List<EdmProperty> getConvolutionPropertiesByVersionAndEnName(String edmdVer, String edmcNameEn) {
        //根据模型号和类的英文名称获取类
        EdmClass edmClass = getEdmClassByVersionAndEnName(edmdVer, edmcNameEn);
        if (edmClass == null) {
            return null;
        }

        List<String> parentIds = new ArrayList<>();
        List<EdmProperty> properties = new ArrayList<>();
        String id = edmClass.getId();
        parentIds.add(id);

        //根据类id获取所有父类id
        parentIds = getParentIdById(parentIds, id);

        //获取所有的卷积属性
        for (String i : parentIds) {
            //根据类id获取类的卷积属性
            EdmPropertyExample example = new EdmPropertyExample();
            EdmPropertyExample.Criteria criteria = example.createCriteria();
            criteria.andEdmpEdmcIdEqualTo(i).andEdmpValueTypeEqualTo("convolution").andIsDelNotEqualTo((byte) 1);
            properties.addAll(edmPropertyMapper.selectByExample(example));
        }

        return properties;
    }

    private List<String> getParentIdById(List<String> idList, String id) {
        EdmClassExample example = new EdmClassExample();
        EdmClassExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
        example.setOrderByClause("edmc_seq asc");
        List<EdmClass> edmClassList = edmClassMapper.selectByExample(example);
        if (edmClassList != null) {
            EdmClass edmClass = edmClassList.get(0);
            if (!org.springframework.util.StringUtils.isEmpty(edmClass.getEdmcParentId())) {
                idList.add(edmClass.getEdmcParentId());
                idList = getParentIdById(idList, edmClass.getEdmcParentId());
            }
        }
        return idList;
    }

    private EdmClass getEdmClassByVersionAndEnName(String edmdVer, String edmcNameEn) {
        List<EdmClass> edmClassList = null;

        //根据模型号获取模型id
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        if (StringUtil.isNullOrEmpty(edmdId)) {
            return null;
        }

        //根据模型id和类的英文名称获取类id
        EdmClassExample example = new EdmClassExample();
        EdmClassExample.Criteria criteria = example.createCriteria();
        criteria.andEdmcEdmdIdEqualTo(edmdId).andEdmcNameEnEqualTo(edmcNameEn).andIsDelNotEqualTo((byte) 1);
        edmClassList = edmClassMapper.selectByExample(example);
        EdmClass edmClass = edmClassList.get(0);
        String id = edmClass.getId();

        return edmClass;
    }

    /**
     * 将指定id的所有属性的is_visible字段更改为指定数值
     *
     * @param modelerVO
     */
    @Override
    @Transactional(readOnly = false)
    public void changePropertiesVisible(ModelerVO modelerVO) {
        String[] idsList = modelerVO.getIds();
        byte visible = modelerVO.getVisible();
        if (idsList != null && idsList.length > 0) {
            edmPropertyMapper.updateBatchByid(idsList, visible);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void clearLimitAndFormula(Map<String, String> map) {
        edmPropertyMapper.clearEdmpValueLimitAndFormula(map);
    }
    @Override
    @Transactional(readOnly = false)
    public void setDefaultValue(String classid, String objectId) {
        List<EdmProperty> edmPropertyList;
        //JSONObject object = new JSONObject();
        EdmClass edmClass= edmClassMapper.selectByPrimaryKey(classid);
        if(edmClass != null){
            //edmPropertyList = new ArrayList<>();
            EdmPropertyExample example = new EdmPropertyExample();
            example.createCriteria().andEdmpEdmcIdEqualTo(classid).andIsDelNotEqualTo((byte) 1).andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_CONST);
            edmPropertyList = edmPropertyMapper.selectByExample(example);
            edmPropertyList = getAllFatherProperties(edmPropertyList, classid,false);//不取常量类型的属性
            LoadParam load = new LoadParam(edmClass.getEdmcNameEn());
            load.addLoadType(ReferanceType.Base).addIDs(objectId);
            Result result = ormClient.load(load.toJSONString());
            if(result.getRetCode() != 1){
                //throw new RuntimeException("ORM查询异常");
            }
            JSONObject data = (JSONObject)JSONObject.toJSON(result.getData()) ;
            JSONArray dataSet = (JSONArray)data.get("dataset");
            if(dataSet != null){
                JSONObject ormObject = dataSet.getJSONObject(0);
                //Map<String,String> map = new HashMap<>();
                if(ormObject != null && edmPropertyList.size()>0){
                    List<IdMapper> idMappers = new ArrayList<>();
                    for(EdmProperty edmProperty :edmPropertyList){
                        String edmpValue = edmProperty.getEdmpValue();
                        String edmpCode = edmProperty.getEdmpCode();
                        if(!StringUtil.isNullOrEmpty(edmpValue) && StringUtil.isNullOrEmpty(ormObject.get(edmpCode))){
                            ormObject.put(edmProperty.getEdmpCode(),edmProperty.getEdmpValue());
                        }
                        if(!StringUtil.isNullOrEmpty(edmProperty.getEdmpFormula())){
                            IdMapper idMapper = new IdMapper();
                            //map.put(edmProperty.getEdmpCode(),edmProperty.getEdmpFormula());
                            idMapper.setFieldId(edmpCode);
                            idMapper.setVariantId(edmProperty.getEdmpFormula());
                            idMappers.add(idMapper);
                            //list.add(edmProperty.getEdmpFormula());
                        }
                    }
                    VariantBatchParam variantBatchParam = new VariantBatchParam();
                    variantBatchParam.setEdmName(edmClass.getEdmcNameEn());
                    variantBatchParam.setIdMappers(idMappers);
                    variantBatchParam.setDataMap(ormObject);
                    Result formulaResult = formulaClient.variantCalcBatch(variantBatchParam);
                    if(formulaResult.getRetCode() != 1){
                        //throw new RuntimeException("Formula公式异常");
                    }
                    Map<String, Object> mapData = (Map<String, Object>)formulaResult.getData();
                    if(mapData != null) {
                        ormObject.putAll(mapData);
                    }
                    MergeParam merge = new MergeParam(edmClass.getEdmcNameEn());
                    ormObject.put("modtime",ormObject.get("addtime"));
                    ormObject.put("moduser","admin");
                    merge.addData(ormObject);
                    Result result1 = ormClient.update(merge.toJSONString());
                    if(result1.getRetCode() != 1){
                        //throw new RuntimeException("Orm更新异常");
                    }
                    //}
                }
            }
        }
    }

    @Override
    public EdmProperty selectById(String id) {
        return edmPropertyMapper.selectByPrimaryKey(id);
    }


    @Override
    public List<EdmPropertyGroup> selectGroups(String edmcId, String edmpId, String isSource) {
        if (StringUtil.isNullOrEmpty(edmcId) || StringUtil.isNullOrEmpty(edmpId)
                || StringUtil.isNullOrEmpty(isSource)) {
            return null;
        }

        byte b = (byte) Integer.parseInt(isSource);
        EdmPropertyGroupExample example = new EdmPropertyGroupExample();
        EdmPropertyGroupExample.Criteria criteria = example.createCriteria();
        criteria.andEdpgEdmcIdEqualTo(edmcId).andEdpgEdmpIdEqualTo(edmpId).andIsSourceEqualTo(b)
                .andIsDelNotEqualTo((byte) 1);
        List<EdmPropertyGroup> list = edmPropertyGroupMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<EdmPropertyVO> getProptreeByCid(String edmcId) {
        if (StringUtil.isNullOrEmpty(edmcId)) { return null; }
        List<EdmPropertyVO> propList = getPropertyTreeByClassId(edmcId, 1);

        //查找本类属性
        List<EdmPropertyVO> list = new ArrayList<>();
        for (EdmPropertyVO edmPropertyVO : propList) {
            if (edmPropertyVO == null || StringUtil.isNullOrEmpty(edmPropertyVO.getEdmpEdmcId())) { continue; }
            if (edmcId.equals(edmPropertyVO.getEdmpEdmcId())) {
                list.add(edmPropertyVO);
            }
        }

        return list;
    }

    /**
     * 根据属性的数据类型获取属性所属类id
     * @param dataType
     * @return
     */
    @Override
    public List<String> getEdmcIdsByDataType(String dataType) {
        List<String> stringList = edmPropertyMapper.getEdmcIdsByDataType(dataType);
        return stringList;
    }

    /**
     * 根据类名和属性编号获取属性对应的枚举编码
     * @param className
     * @param propCode
     * @return
     */
    @Override
    public String getEnumInfoCode(String className, String propCode) {
        String modelerId = edmModelerMapper.selectEdmdIdByVer(version);
        String classId = edmClassMapper.selectEdmcIdByEdmdIdAndName(modelerId, className);

        return edmPropertyMapper.getEdmpDataType(classId, propCode);
    }

    /**
     * 根据类id获取枚举属性的数据类型，多个值间用逗号隔开
     * @param className
     * @return
     */
    @Override
    public String getInfoCodes(String className) {
        String modelerId = edmModelerMapper.selectEdmdIdByVer(version);
        String classId = edmClassMapper.selectEdmcIdByEdmdIdAndEdmcName(modelerId, className);

        List<String> dataTypes = edmPropertyMapper.getDataTypesByClassId(classId);

        StringBuilder sb = new StringBuilder();
        if (dataTypes != null && dataTypes.size() > 0) {
            for (String dataType : dataTypes) {
                sb.append(dataType + ",");
            }

            if (!StringUtil.isNullOrEmpty(sb)) {
                sb.delete(sb.length() - 1, sb.length());
            }
        }

        return sb.toString();
    }

    private String getTablename(EdmProperty edmProperty) {
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmProperty.getEdmpEdmcId());
        String parentId = edmProperty.getEdmpParentId();

        int suffix = 'a';
        while (!StringUtil.isNullOrEmpty(parentId)) {
            suffix++;
            EdmProperty property = edmPropertyMapper.selectByPrimaryKey(parentId);
            if (property == null) { break; }
            parentId = property.getEdmpParentId();
        }
        return edmClass.getEdmcShortName().concat("_"+edmProperty.getEdmpCode().toLowerCase().concat(""+(char)(suffix)));
    }
}
