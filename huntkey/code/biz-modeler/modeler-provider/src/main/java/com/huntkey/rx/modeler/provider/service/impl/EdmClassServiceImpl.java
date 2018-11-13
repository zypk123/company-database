package com.huntkey.rx.modeler.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.stuxuhai.jpinyin.PinyinFormat;
import com.github.stuxuhai.jpinyin.PinyinHelper;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.*;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.*;
import com.huntkey.rx.modeler.provider.feign.FormulaClient;
import com.huntkey.rx.modeler.provider.feign.OrmClient;
import com.huntkey.rx.modeler.provider.service.EdmClassFormatService;
import com.huntkey.rx.modeler.provider.service.EdmClassService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import redis.clients.jedis.JedisCluster;

import java.util.*;

/**
 * Created by licj on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class EdmClassServiceImpl implements EdmClassService {

    private static Logger log = LoggerFactory.getLogger(EdmModelerServiceImpl.class);
    // RedisClusterUtils rcu = new
    // RedisClusterUtils(ConfigFactory.getString("redisUrl"));

    @Autowired
    private EdmClassMapper edmClassMapper;

    @Autowired
    private EdmAttachmentMapper edmAttachmentMapper;

    @Autowired
    private EdmPropertyMapper edmPropertyMapper;

    @Autowired
    private EdmConvoluteMapper edmConvoluteMapper;

    @Autowired
    private EdmLinkMapper edmLinkMapper;

    @Autowired
    private EdmConnectMapper edmConnectMapper;

    @Autowired
    private EdmUnitMapper edmUnitMapper;

    @Autowired
    private EdmMethodMapper edmMethodMapper;

    @Autowired
    private EdmCodeMapper edmCodeMapper;

    @Autowired
    private EdmMethodTypeMapper edmMethodTypeMapper;

    @Autowired
    private EdmModelerMapper edmModelerMapper;

    @Autowired
    private EdmClassFormatMapper edmClassFormatMapper;

    @Autowired
    private EdmCondMapper edmCondMapper;

    @Autowired
    public EdmClassFormatService edmClassFormatService;

    @Autowired
    private OrmClient ormClient;

    @Autowired
    private JedisCluster jedisCluster;

    @Autowired
    private EdmIndexMapper edmIndexMapper;

    @Autowired
    private EdmIndexDetailMapper edmIndexDetailMapper;

    @Autowired
    private FormulaClient formulaClient;

    @Value("${modeler.version}")
    private String version;

    @Override
    public EdmClass selectEdmClassById(String id) {
        return edmClassMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<EdmClassVO> selectEdmClassesByMid(String mid) throws Exception {

        List<EdmClass> edmClassList = edmClassMapper.selectEdmClassesByMid(mid);

        List<EdmClassVO> retVOList = new ArrayList<EdmClassVO>();
        if (null != edmClassList && edmClassList.size() > 0) {
            List<EdmClassVO> edmClassVOList = new ArrayList<EdmClassVO>();
            for (EdmClass edmClass : edmClassList) {
                EdmClassVO edmClassVO = ModelToVO.edmClassToVO(edmClass);
                edmClassVO.setSpell(PinyinHelper.convertToPinyinString(edmClass.getEdmcName(), "", PinyinFormat.WITHOUT_TONE));
                edmClassVOList.add(edmClassVO);
            }
            if (null != edmClassVOList && edmClassVOList.size() > 0) {
                retVOList = new ArrayList<EdmClassVO>();
                for (EdmClassVO childNode : edmClassVOList) {
                    boolean mark = false;
                    for (EdmClassVO parentNode : edmClassVOList) {
                        if (!StringUtil.isNullOrEmpty(childNode.getEdmcParentId())
                                && childNode.getEdmcParentId().equals(parentNode.getId())) {
                            mark = true;
                            parentNode.setLeaf(false);
                            if (parentNode.getChildren() == null)
                                parentNode.setChildren(new ArrayList<EdmClassVO>());
                            parentNode.getChildren().add(childNode);
                            break;
                        }
                    }
                    if (!mark) {
                        retVOList.add(childNode);
                    }
                }
            }
        }
        return retVOList;
    }

    @Override
    public List<EdmClassVO> getRelateClass(String id) {
        List<EdmClassVO> retList = new ArrayList<EdmClassVO>();
        //获取本类对象
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(id);
        if (edmClass != null) {
            retList.add(ModelToVO.edmClassToVO(edmClass));
            //获取相关类对象
            EdmPropertyExample example = new EdmPropertyExample();
            example.createCriteria().andEdmpEdmcIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
            example.setOrderByClause("edmp_seq asc");
            List<EdmProperty> edmPropertyList = edmPropertyMapper.selectByExample(example);
            //去重edmpObjEdmcId
            if (null != edmPropertyList && edmPropertyList.size() > 0) {
                List<String> edmpObjEdmcIdList = distinctEdmpObjEdmcId(edmPropertyList);
                for (String classId : edmpObjEdmcIdList) {
                    if (classId != null && classId.equals(id)) { continue; }
                    EdmClass temp = edmClassMapper.selectByPrimaryKey(classId);
                    if (temp != null) {
                        retList.add(ModelToVO.edmClassToVO(temp));
                    }
                }
            }
        }
        return retList;
    }

    @Override
    public List<EdmClassVO> getRelateClasses(String id) {
        //获取本类对象
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(id);
        //获取父类
        List<EdmClassVO> edmClassVOS = new ArrayList<>();
        if (edmClass != null) {
            edmClassVOS.add(ModelToVO.edmClassToVO(edmClass));
            while (edmClass != null) {
                edmClass = edmClassMapper.selectByPrimaryKey(edmClass.getEdmcParentId());
                if (edmClass != null) {
                    edmClassVOS.add(ModelToVO.edmClassToVO(edmClass));
                }
            }
        }

        //获取相关类
        if (edmClassVOS != null && edmClassVOS.size() > 0) {
            for (EdmClassVO edmClassVO : edmClassVOS) {
                List<EdmClassVO> retList = new ArrayList<EdmClassVO>();
                EdmPropertyExample example = new EdmPropertyExample();
                List<String> valueTypes = new ArrayList<>();
                valueTypes.add(Constant.EDM_OBJECT);
                valueTypes.add(Constant.EDM_OBJECTLINK);
                example.createCriteria().andEdmpEdmcIdEqualTo(edmClassVO.getId())
                        .andIsDelNotEqualTo((byte) 1).andEdmpValueTypeIn(valueTypes);
                example.setOrderByClause("edmp_seq asc");
                List<EdmProperty> relateProps = edmPropertyMapper.selectByExample(example);

                if (null != relateProps && relateProps.size() > 0) {
                    for (EdmProperty edmProperty : relateProps) {
                        EdmClass temp = edmClassMapper.selectByPrimaryKey(edmProperty.getEdmpDataType());
                        if (temp != null) {
                            EdmClassVO vo = ModelToVO.edmClassToVO(temp);
                            if (StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {
                                vo.setEdmcName(vo.getEdmcName()
                                        .concat("(")
                                        .concat(edmProperty.getEdmpName())
                                        .concat(")"));
                            } else {
                                EdmProperty property = edmPropertyMapper.selectByPrimaryKey(edmProperty.getEdmpParentId());
                                vo.setEdmcName(vo.getEdmcName()
                                        .concat("(")
                                        .concat(property.getEdmpName())
                                        .concat(".")
                                        .concat(edmProperty.getEdmpName())
                                        .concat(")"));
                            }
                            retList.add(vo);
                        }
                    }
                }
                edmClassVO.setChildren(retList);
            }
        }

        return edmClassVOS;
    }

    private List<String> distinctEdmpObjEdmcId(List<EdmProperty> edmPropertyList) {
        List<String> edmpObjEdmcIdList = new ArrayList<String>();
        for (EdmProperty edmProperty : edmPropertyList) {
            String valueType = edmProperty.getEdmpValueType();
            if (!StringUtil.isNullOrEmpty(valueType)
                    && (Constant.EDM_OBJECT.equals(valueType) || Constant.EDM_OBJECTLINK.equals(valueType))) {
                if (!edmpObjEdmcIdList.contains(edmProperty.getEdmpObjEdmcId())) {
                    edmpObjEdmcIdList.add(edmProperty.getEdmpDataType());
                }
            }
        }
        return edmpObjEdmcIdList;
    }

    @Override
    public EdmClassVO selectById(String id) {
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(id);
        EdmClassVO edmClassVo = null;
        if (edmClass != null) {
            String parentid = edmClass.getEdmcParentId();
            String showId = edmClass.getEdmcShowId();
            String objectId = edmClass.getNormalPresent();
            edmClassVo = ModelToVO.edmClassToVO(edmClass);
            EdmCode edmCode = edmCodeMapper.selectIndustryBycodeValue(edmClass.getEdmcIndustryCode());
            if (!StringUtil.isNullOrEmpty(parentid)) {
                edmClassVo.setParentName(edmClassMapper.selectParentNameByEdmcParentId(parentid));
            }
            if (edmCode != null) {
                edmClassVo.setIndustry(edmCode.getCodeName());
            }
            if (!StringUtil.isNullOrEmpty(showId) && !StringUtil.isNullOrEmpty(objectId)) {
                edmClassVo.setShowName(getObjectValue(showId, objectId));
            }
            List<EdmCode> edmCodes = new ArrayList<EdmCode>();
            edmCodes = getEdmCodes(edmCodes, edmClass.getEdmcIndustryCode());
            String names = "";
            String values = "";
            if (null != edmCodes && edmCodes.size() > 0) {
                StringBuffer sb = new StringBuffer();
                for (EdmCode edmCode1 : edmCodes) {
                    names = names + edmCode1.getCodeName() + "/";
                    // values = values + edmCode1.getCodeValue() + ",";
                    sb.append(edmCode1.getCodeValue());
                    sb.append(",");
                }
                values = sb.toString();
                if (!StringUtil.isNullOrEmpty(names)) {
                    names = names.substring(0, names.length() - 1);
                    values = values.substring(0, values.length() - 1);
                }
                edmClassVo.setIndustryNames(names);
                edmClassVo.setIndustryValues(values);
            }
        }

		/*
         * rcu.setValue("huntkeytest","lichunjie");
		 * System.out.println(rcu.getValue("huntkeytest"));
		 */
        return edmClassVo;
    }

    @Override
    public EdmClassVO selectSimpleClassById(String id) {
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(id);
        EdmClassVO edmClassVO = null;
        if (edmClass != null) {
            String parentid = edmClass.getEdmcParentId();
            String showId = edmClass.getEdmcShowId();
            String objectId = edmClass.getNormalPresent();
            edmClassVO = ModelToVO.edmClassToVO(edmClass);

            if (!StringUtil.isNullOrEmpty(parentid)) {
                edmClassVO.setParentName(edmClassMapper.selectParentNameByEdmcParentId(parentid));
            }
            List<EdmClassFormat> edmClassFormats = edmClassFormatMapper.selectClassFormatListByEdmcId(id);
            String formatValue = "";//对象特征值
            if (null != edmClassFormats && edmClassFormats.size() > 0) {
                for (EdmClassFormat edmClassFormat : edmClassFormats) {
                    formatValue = formatValue + edmPropertyMapper.getEdmpNameById(edmClassFormat.getEdmfEdmpId()) + edmClassFormat.getEdmfConnector();
                }
            }
            if (!StringUtil.isNullOrEmpty(showId) && !StringUtil.isNullOrEmpty(objectId)) {
                edmClassVO.setShowName(getObjectValue(showId, objectId));
            }
            edmClassVO.setFormatValue(formatValue);
        }
        return edmClassVO;
    }

    public List<EdmCode> getEdmCodes(List<EdmCode> edmCodes, String code) {
        EdmCode edmCode = edmCodeMapper.selectIndustryBycodeValue(code);
        if (null != edmCode) {
            if (!StringUtil.isNullOrEmpty(edmCode.getCodeExt1())) {
                edmCodes = getEdmCodes(edmCodes, edmCode.getCodeExt1());
            }
            edmCodes.add(edmCode);
        }
        return edmCodes;
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(String id) {
        if (!StringUtils.isEmpty(id)) {
            EdmClassExample edmClassExample = new EdmClassExample();
            EdmClassExample.Criteria criteria = edmClassExample.createCriteria();
            criteria.andEdmcParentIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
            List<EdmClass> list = edmClassMapper.selectByExample(edmClassExample);
            if (list.size() > 0) {
                for (EdmClass ec : list) {
                    delete(ec.getId());
                    edmClassMapper.updateIsDelByPrimaryKey(ec.getId());
                }
            }
        }
        return edmClassMapper.updateIsDelByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(EdmClass edmClass) {
        edmClass.setId(UuidCreater.uuid());
        String parentid = edmClass.getEdmcParentId();
        Date date = new Date();
        if (!StringUtil.isNullOrEmpty(parentid)) {
            Integer seq = edmClassMapper.getMaxSeqByPid(parentid);
            if (null != seq) {
                seq = seq + 1;
            } else {
                seq = 1;
            }
            edmClass.setEdmcSeq(seq);
        }
        edmClass.setEdmcShortName(edmClass.getEdmcShortName().toLowerCase());
        edmClass.setEdmcNameEn(edmClass.getEdmcNameEn().toLowerCase());
        edmClass.setTablename(edmClass.getEdmcNameEn());
        edmClass.setAddtime(date);
        edmClass.setModtime(date);
        edmClass.setIsDel((byte) 0);
        return edmClassMapper.insertSelective(edmClass);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(EdmClass edmClass) {
        edmClass.setModtime(new Date());
        edmClass.setEdmcShortName(edmClass.getEdmcShortName().toLowerCase());
        edmClass.setEdmcNameEn(edmClass.getEdmcNameEn().toLowerCase());
        edmClass.setTablename(edmClass.getEdmcNameEn());
        return edmClassMapper.updateByPrimaryKeySelective(edmClass);
    }

    @Override
    @Transactional(readOnly = false)
    public void updateBatchIsDelByClassIdAndMethodIds(String[] methodIds) {
        if (methodIds != null && methodIds.length > 0) {
            for (String methodId : methodIds) {
                edmMethodMapper.updateEdmcIdPrimaryKey(methodId);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void copy(EdmClass edmClass) {
        if (edmClass == null) {
            return;
        }

        Date date = new Date();

        //复制类
        EdmClass tempEdmClass = new EdmClass(edmClass);
        tempEdmClass.setId(UuidCreater.uuid());
        tempEdmClass.setAddtime(date);
        tempEdmClass.setModtime(date);
        edmClassMapper.insertSelective(tempEdmClass);

        //复制方法
        List<EdmMethod> edmMethods = edmMethodMapper.selectEdmMethodByCid(edmClass.getId());
        List<EdmMethod> edmMethodsAll = null;
        if (null != edmMethods && edmMethods.size() > 0) {//处理类方法
            edmMethodsAll = new ArrayList<>();
            for (EdmMethod edmMethod : edmMethods) {
                edmMethod.setId(UuidCreater.uuid());
                edmMethod.setEdmmEdmcId(tempEdmClass.getId());
                edmMethod.setAddtime(date);
                edmMethod.setModtime(date);
                edmMethodsAll.add(edmMethod);
            }
            edmMethodMapper.insertBatch(edmMethodsAll);
        }

        // 复制附件
        List<EdmAttachment> edmAttachments = edmAttachmentMapper.selectEdmAttachmentListByClassId(edmClass.getId());
        List<EdmAttachment> edmAttachmentAll = null;
        if (null != edmAttachments && edmAttachments.size() > 0) {//处理类附件
            edmAttachmentAll = new ArrayList<EdmAttachment>();
            for (EdmAttachment edmAttachment : edmAttachments) {
                edmAttachment.setId(UuidCreater.uuid());
                edmAttachment.setEdmaEdmcId(tempEdmClass.getId());
                edmAttachment.setAddtime(date);
                edmAttachment.setModtime(date);
                edmAttachmentAll.add(edmAttachment);
            }
            edmAttachmentMapper.insertBatch(edmAttachmentAll);
        }

        //复制属性
        List<EdmProperty> edmProperties = edmPropertyMapper.selectEdmPropertiesByCid(edmClass.getId());
        List<EdmConvolute> edmConvolutes = new ArrayList<>();
        List<EdmConnect> edmConnects = new ArrayList<>();
        List<EdmLink> edmLinks = new ArrayList<>();
        List<EdmUnit> edmUnits = new ArrayList<>();
        List<EdmCond> edmConds = new ArrayList<>();
        Map<String, String> idMap = new HashMap<>();

        if (edmProperties != null && edmProperties.size() > 0) {
            for (EdmProperty edmProperty : edmProperties) {
                idMap.put(edmProperty.getId(), UuidCreater.uuid());

                // 获取卷积属性
                EdmConvolute edmConvolute = edmConvoluteMapper.selectEdmConvoluteByPropertyId(edmProperty.getId());
                if (edmConvolute != null) { edmConvolutes.add(edmConvolute); }

                // 获取关联属性
                List<EdmLink> edmLinkList = edmLinkMapper.selectEdmLinkListByPropertyId(edmProperty.getId());
                if (edmLinkList != null) { edmLinks.addAll(edmLinkList); }

                // 获取联动属性
                EdmConnect edmConnect = edmConnectMapper.getEdmConnectPropertieByEdmpId(edmProperty.getId());
                if (edmConnect != null) { edmConnects.add(edmConnect); }

                //获取单位
                List<EdmUnit> edmUnitList = edmUnitMapper.selectEdmUnitListByPropertyId(edmProperty.getId());
                if (edmUnitList != null) { edmUnits.addAll(edmUnitList); }

                //获取触发条件
                List<EdmCond> edmCondList = edmCondMapper.getCondsByPropertyId(edmProperty.getId());
                if (edmCondList != null) { edmConds.addAll(edmCondList); }
            }
        }

        if (edmProperties != null && edmProperties.size() > 0) {
            for (EdmProperty edmProperty : edmProperties) {
                if (Constant.PROPERTY_TYPE_ASSEMBLE.equals(edmProperty.getEdmpValueType())) {
                    for (EdmProperty edmPropertySet : edmProperties) {// 处理属性集的关系
                        if (!StringUtil.isNullOrEmpty(edmPropertySet.getEdmpParentId())
                                && edmPropertySet.getEdmpParentId().equals(edmProperty.getId())) {
                            edmPropertySet.setEdmpParentId(idMap.get(edmProperty.getId()));
                        }
                    }
                }
            }
            for (EdmProperty edmProperty : edmProperties) {
                String edmpCode = edmProperty.getEdmpCode();
                if(!StringUtil.isNullOrEmpty(edmpCode) && edmpCode.length()>3){
                    edmProperty.setEdmpCode(edmpCode.replaceAll(edmpCode.substring(0,edmpCode.length()-3), tempEdmClass.getEdmcShortName()));
                }
                edmProperty.setId(idMap.get(edmProperty.getId()));
                edmProperty.setEdmpEdmcId(tempEdmClass.getId());
                edmProperty.setAddtime(date);
                edmProperty.setModtime(date);
            }
            edmPropertyMapper.insertBatch(edmProperties);
        }

        if (edmConvolutes != null && edmConvolutes.size() > 0) {
            for (EdmConvolute edmConvolute : edmConvolutes) {
                edmConvolute.setId(UuidCreater.uuid());
                edmConvolute.setEdcoEdmpId(idMap.get(edmConvolute.getEdcoEdmpId()));
                edmConvolute.setAddtime(date);
                edmConvolute.setModtime(date);
            }
            edmConvoluteMapper.insertBatch(edmConvolutes);
        }

        if (edmConds != null && edmConds.size() > 0) {
            for (EdmCond edmCond : edmConds) {
                idMap.put(edmCond.getId(), UuidCreater.uuid());
            }
        }
        if (edmConds != null && edmConds.size() > 0) {
            int i = 0;
            Date time = new Date();
            for (EdmCond edmCond : edmConds) {
                time.setTime(System.currentTimeMillis() + (i++)*1000);
                edmCond.setId(idMap.get(edmCond.getId()));
                edmCond.setEdcoEdmpId(idMap.get(edmCond.getEdcoEdmpId()));
                edmCond.setAddtime(time);
                edmCond.setModtime(time);
            }
            edmCondMapper.insertBatch(edmConds);
        }

        if (edmLinks != null && edmLinks.size() > 0) {
            int i = 0;
            Date time = new Date();
            for (EdmLink edmLink : edmLinks) {
                time.setTime(System.currentTimeMillis() + (i++)*1000);
                if (idMap.get(edmLink.getEdmlEdmpLinkId()) != null) {
                    edmLink.setEdmlEdmpLinkId(idMap.get(edmLink.getEdmlEdmpLinkId()));
                }
                edmLink.setId(UuidCreater.uuid());
                edmLink.setEdmlEdmpId(idMap.get(edmLink.getEdmlEdmpId()));
                edmLink.setEdmlCond(idMap.get(edmLink.getEdmlCond()));
                edmLink.setAddtime(time);
                edmLink.setModtime(time);
            }
            edmLinkMapper.insertBatch(edmLinks);
        }

        if (edmConnects != null && edmConnects.size() > 0) {
            for (EdmConnect edmConnect : edmConnects) {
                edmConnect.setId(UuidCreater.uuid());
                edmConnect.setEdcnEdmpId(idMap.get(edmConnect.getEdcnEdmpId()));
                edmConnect.setAddtime(date);
                edmConnect.setModtime(date);
            }
            edmConnectMapper.insertBatch(edmConnects);
        }

        if (edmUnits != null && edmUnits.size() > 0) {
            for (EdmUnit edmUnit : edmUnits) {
                edmUnit.setId(UuidCreater.uuid());
                edmUnit.setEdunEdmpId(idMap.get(edmUnit.getEdunEdmpId()));
                edmUnit.setAddtime(date);
                edmUnit.setModtime(date);
            }
            edmUnitMapper.insertBatch(edmUnits);
        }

        //复制索引
        List<EdmIndex> indexList = edmIndexMapper.selectIndexsByEdmcId(edmClass.getId());
        List<EdmIndexDetail> edmIndexDetailList = new ArrayList<>();
        List<EdmIndexDetail> edmIndexDetails = new ArrayList<>();
        if (null != indexList && indexList.size() > 0) {
            int i = 0;
            Date time = new Date();
            for (EdmIndex edmIndex : indexList) {
                idMap.put(edmIndex.getId(), UuidCreater.uuid());
                List<EdmIndexDetail> temp = edmIndexDetailMapper.selectByIndexId(edmIndex.getId());
                if (temp != null) {
                    edmIndexDetailList.addAll(temp);
                }
            }
            for (EdmIndex edmIndex : indexList) {
                time.setTime(System.currentTimeMillis() + (i++)*1000);
                edmIndex.setId(idMap.get(edmIndex.getId()));
                edmIndex.setEdmcId(tempEdmClass.getId());
                edmIndex.setEdmpAssId(idMap.get(edmIndex.getEdmpAssId()));
                edmIndex.setAddtime(time);
                edmIndex.setModtime(time);
            }
            if (edmIndexDetailList != null && edmIndexDetailList.size() > 0) {
                for (EdmIndexDetail edmIndexDetail : edmIndexDetailList) {
                    if (idMap.get(edmIndexDetail.getEdmpId()) != null) {
                        edmIndexDetail.setId(UuidCreater.uuid());
                        edmIndexDetail.setIndexId(idMap.get(edmIndexDetail.getIndexId()));
                        edmIndexDetail.setEdmpId(idMap.get(edmIndexDetail.getEdmpId()));
                        edmIndexDetail.setAddtime(date);
                        edmIndexDetail.setModtime(date);
                        edmIndexDetails.add(edmIndexDetail);
                    }
                }
            }
            edmIndexMapper.insertBatch(indexList);
            edmIndexDetailMapper.insertList(edmIndexDetails);
        }

        //复制对象呈现格式
        List<EdmClassFormat> edmClassFormats = edmClassFormatMapper.selectClassFormatListByEdmcId(edmClass.getId());
        if (edmClassFormats != null && edmClassFormats.size() > 0) {
            for (EdmClassFormat edmClassFormat : edmClassFormats) {
                edmClassFormat.setId(UuidCreater.uuid());
                edmClassFormat.setEdmfEdmcId(tempEdmClass.getId());
                edmClassFormat.setEdmfEdmpId(idMap.get(edmClassFormat.getEdmfEdmpId()));
                edmClassFormat.setAddtime(date);
                edmClassFormat.setModtime(date);
            }
            edmClassFormatMapper.insertBatch(edmClassFormats);
        }
    }

    @Override
    public List<EdmMethodVO> selectEdmMethodByClassId(String id) {
        EdmMethodExample example = new EdmMethodExample();
        EdmMethodExample.Criteria criteria = example.createCriteria();
        criteria.andEdmmEdmcIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
        example.setOrderByClause("edmm_seq asc");
        List<EdmMethod> list = edmMethodMapper.selectByExample(example); // 得到该类所有方法的集合
        List<EdmMethodVO> edmMethodVOList = null;
        if (null != list && list.size() > 0) {
            edmMethodVOList = new ArrayList<>();
            for (EdmMethod edmMethod : list) {
                EdmMethodVO edmMethodVO = ModelToVO.edmMethodToVO(edmMethod);
                //EdmClass edmClass = edmClassMapper.selectByPrimaryKey(id);
               /* if (edmClass != null) {
                    edmMethodVO.setEdmcName(edmClass.getEdmcName());
                }*/
                edmMethodVO.setEdmmTypeName(edmMethodTypeMapper.selectEdmtNameById(edmMethod.getEdmmType()));// 设置方法分类名
                //设置方法触发条件名称
                String condId = edmMethod.getEdmmTriggerCond();
                if (!StringUtil.isNullOrEmpty(condId)) {
                    Result result = formulaClient.selectByPrimaryKey(condId);
                    if (result != null) {
                        Map<String, String> data = (Map<String, String>) result.getData();
                        if (data != null) {
                            edmMethodVO.setEdmTriggerCondName(data.get("prplConditionName"));
                            edmMethodVO.setEdmTriggerCondNameDesc(data.get("prplConditionDesc"));
                        }
                    }
                }
                edmMethodVOList.add(edmMethodVO);
            }
        }
        return edmMethodVOList;
    }

    /*
     * 获取该类的所有父类的方法集合
     */
    @Override
    public List<EdmMethodVO> selectEdmFatherMethodByClassId(String id) {
        List<EdmMethodVO> retList = null;
        List<String> idList = new ArrayList<String>();
        idList = getParentIdById(idList, id);
        if (idList.size() > 0) {
            retList = new ArrayList<>();
            for (String s : idList) {
                if (!StringUtils.isEmpty(s)) {
                    List<EdmMethodVO> temp = selectEdmMethodByClassId(s);
                    if (temp != null) {
                        retList.addAll(temp);
                    }
                }
            }
        }
        return retList;
    }

    public List<String> getParentIdById(List<String> idList, String id) {
        EdmClassExample example = new EdmClassExample();
        EdmClassExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
        example.setOrderByClause("edmc_seq asc");
        List<EdmClass> edmClassList = edmClassMapper.selectByExample(example);
        if (edmClassList != null && edmClassList.size() > 0) {
            EdmClass edmClass = edmClassList.get(0);
            if (!StringUtils.isEmpty(edmClass.getEdmcParentId())) {
                idList.add(edmClass.getEdmcParentId());
                idList = getParentIdById(idList, edmClass.getEdmcParentId());
            }
        }
        return idList;
    }

    @Override
    public String checkEdmcCode(String edmcCode, String edmcEdmdId) {
        String errorStr = null;
        if (StringUtil.isNullOrEmpty(edmcCode)) {
            errorStr = "类编码不能为空";
            return errorStr;
        }
        if (StringUtil.isNullOrEmpty(edmcEdmdId)) {
            errorStr = "模型ID不能为空";
            return errorStr;
        }
        EdmClassExample edmClassExample = new EdmClassExample();
        edmClassExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmcCodeEqualTo(edmcCode).andEdmcEdmdIdEqualTo(edmcEdmdId);
        List<EdmClass> edmClassList = edmClassMapper.selectByExample(edmClassExample);
        if (null != edmClassList && edmClassList.size() > 0) {
            errorStr = "类编码已存在";
            return errorStr;
        }
        return errorStr;
    }

    @Override
    public String checkEdmcName(String edmcName, String edmcEdmdId) {
        String errorStr = null;
        if (StringUtil.isNullOrEmpty(edmcName)) {
            errorStr = "类名不能为空";
            return errorStr;
        }
        if (StringUtil.isNullOrEmpty(edmcEdmdId)) {
            errorStr = "模型ID不能为空";
            return errorStr;
        }
        EdmClassExample edmClassExample = new EdmClassExample();
        edmClassExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmcNameEqualTo(edmcName).andEdmcEdmdIdEqualTo(edmcEdmdId);
        List<EdmClass> edmClassList = edmClassMapper.selectByExample(edmClassExample);
        if (edmClassList != null && edmClassList.size() > 0) {
            errorStr = "类名已存在";
            return errorStr;
        }
        return errorStr;
    }

    @Override
    public String checkEdmcNameEn(String edmcNameEn, String edmcEdmdId) {
        String errorStr = null;
        if (StringUtil.isNullOrEmpty(edmcNameEn)) {
            errorStr = "类英文名不能为空";
            return errorStr;
        }
        if (StringUtil.isNullOrEmpty(edmcEdmdId)) {
            errorStr = "模型ID不能为空";
            return errorStr;
        }
        EdmClassExample edmClassExample = new EdmClassExample();
        edmClassExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmcNameEnEqualTo(edmcNameEn).andEdmcEdmdIdEqualTo(edmcEdmdId);
        List<EdmClass> edmClassList = edmClassMapper.selectByExample(edmClassExample);
        if (edmClassList != null && edmClassList.size() > 0) {
            errorStr = "类英文名已存在";
            return errorStr;
        }
        return errorStr;
    }

    @Override
    public String checkEdmcShortName(String edmcShortName, String edmcEdmdId) {
        String errorStr = null;
        if (StringUtil.isNullOrEmpty(edmcShortName)) {
            errorStr = "类简称不能为空";
            return errorStr;
        } else if (edmcShortName.length() > 4) {
            errorStr = "类简称长度不能大于4";
            return errorStr;
        }
        if (StringUtil.isNullOrEmpty(edmcEdmdId)) {
            errorStr = "模型ID不能为空";
            return errorStr;
        }
        //类简称唯一性验证
        EdmClassExample edmClassExample = new EdmClassExample();
        edmClassExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmcShortNameEqualTo(edmcShortName).andEdmcEdmdIdEqualTo(edmcEdmdId);
        List<EdmClass> edmClassList = edmClassMapper.selectByExample(edmClassExample);
        if (edmClassList != null && edmClassList.size() > 0) {
            errorStr = "类简称已存在";
            return errorStr;
        }
        return errorStr;
    }

    @Override
    public boolean isChild(String id, String sid) {
        boolean isChild = false;
        List<EdmClass> edmClassList = new ArrayList<EdmClass>();
        edmClassList = findChildrenById(edmClassList, id);
        if (null != edmClassList && edmClassList.size() > 0) {
            for (EdmClass edmClass : edmClassList) {
                if (edmClass.getId().equals(sid)) {
                    isChild = true;
                    break;
                }
            }
        }
        return isChild;
    }

    /**
     * 递归获取某个类id下面的所有子类
     */
    private List<EdmClass> findChildrenById(List<EdmClass> edmClasses, String id) {
        List<EdmClass> edmClasses1 = edmClassMapper.getEdmClassNameByPid(null, id);
        if (null != edmClasses1 && edmClasses1.size() > 0) {
            edmClasses.addAll(edmClasses1);
            for (EdmClass edmClass : edmClasses1) {
                edmClasses = findChildrenById(edmClasses, edmClass.getId());
            }
        }
        return edmClasses;
    }

    @Override
    @Transactional(readOnly = false)
    public void move(String[] ids) {
        int seq = 1;
        if (null != ids && ids.length > 0) {
            for (String id : ids) {
                if (!StringUtil.isNullOrEmpty(id)) {
                    edmClassMapper.updateSeqById(id, seq);
                    seq++;
                }
            }
        }
    }

    /**
     * 类树查询接口
     *
     * @param modelerId
     * @param edmcNameEns
     * @return
     */
    @Override
    public EdmClassVO queryClassTree(String modelerId, String[] edmcNameEns) {
        EdmClassVO ret = new EdmClassVO();
        if (StringUtils.isEmpty(modelerId)) {//模型版本为空的情况
            return null;
        } else {//模型id查询为空的情况
            EdmClassExample example = new EdmClassExample();
            EdmClassExample.Criteria criteria = example.createCriteria();
            criteria.andEdmcEdmdIdEqualTo(modelerId).andIsDelNotEqualTo((byte) 1);
            List<EdmClass> list = new ArrayList<EdmClass>();
            list = edmClassMapper.selectByExample(example);
            if (list.size() < 1) {
                return null;
            }
        }
        //类数组为空的情况
        if (edmcNameEns.length < 1) {
            return null;
        }
        //查询一个类树的情况
        if (edmcNameEns.length == 1) {
            ret = getTree(modelerId, edmcNameEns[0]);
        }
        //查询多个类树的情况，需要加上EDM根类
        if (edmcNameEns.length > 1) {
            //ret就是EDM
            EdmClassExample example = new EdmClassExample();
            EdmClassExample.Criteria criteria = example.createCriteria();
            criteria.andEdmcEdmdIdEqualTo(modelerId).andEdmcNameEqualTo("EDM").andIsDelNotEqualTo((byte) 1);
            List<EdmClass> edmClassList = edmClassMapper.selectByExample(example);
            if (edmClassList.size() == 0) {
                return null;
            } else {
                ret = ModelToVO.edmClassToVO(edmClassList.get(0));
            }
            //设置Child
            for (String ene : edmcNameEns) {
                EdmClassVO newChild = new EdmClassVO();
                newChild = getTree(modelerId, ene);
                List<EdmClassVO> haveChildren = ret.getChildren();
                if (haveChildren == null) {
                    haveChildren = new ArrayList<EdmClassVO>();
                }
                if (newChild != null) {
                    haveChildren.add(newChild);
                    ret.setChildren(haveChildren);
                }
            }
        }
        return ret;
    }

    @Override
    public EdmClass selectSpecialClass(String id, String shortName) {
        return edmClassMapper.selectSpecialClass(id, shortName);
    }

    public EdmClassVO getTree(String modelerId, String edmcNameEn) {
        EdmClassVO edmClassVO = null;
        if (!StringUtils.isEmpty(modelerId) && !StringUtils.isEmpty(edmcNameEn)) {
            EdmClassExample example = new EdmClassExample();
            EdmClassExample.Criteria criteria = example.createCriteria();
            criteria.andEdmcEdmdIdEqualTo(modelerId).andEdmcNameEnEqualTo(edmcNameEn).andIsDelNotEqualTo((byte) 1);
            List<EdmClass> list = edmClassMapper.selectByExample(example);
            if (list.size() > 0) {
                edmClassVO = ModelToVO.edmClassToVO(list.get(0));
                edmClassVO = digui(edmClassVO);
            }
        }
        return edmClassVO;
    }

    public EdmClassVO digui(EdmClassVO edmClassVO) {
        //查找下级节点
        EdmClassExample example = new EdmClassExample();
        EdmClassExample.Criteria criteria = example.createCriteria();
        criteria.andEdmcParentIdEqualTo(edmClassVO.getId()).andIsDelNotEqualTo((byte) 1);
        List<EdmClass> list = edmClassMapper.selectByExample(example);
        if (list.size() > 0) {
            for (EdmClass edmClass : list) {
                EdmClassVO ecv = ModelToVO.edmClassToVO(edmClass);
                ecv = digui(ecv);
                List<EdmClassVO> haveChildren = edmClassVO.getChildren();
                if (haveChildren == null) {
                    haveChildren = new ArrayList<EdmClassVO>();
                }
                if (ecv != null) {
                    haveChildren.add(ecv);
                    edmClassVO.setChildren(haveChildren);
                }
            }
        }
        return edmClassVO;
    }

    /**
     * 查询已发布模型下面的所有类
     *
     * @return
     */
    @Override
    public List<EdmClass> getPublishModelerClass() {
        EdmModelerExample edmModelerExample = new EdmModelerExample();
        EdmModelerExample.Criteria criteria1 = edmModelerExample.createCriteria();
        criteria1.andEdmdStatusEqualTo((byte) 6).andIsDelNotEqualTo((byte) 1);
        List<EdmModeler> list = edmModelerMapper.selectByExample(edmModelerExample);
        if (list.size() == 0) {
            return null;
        } else {
            EdmClassExample edmClassExample = new EdmClassExample();
            EdmClassExample.Criteria criteria2 = edmClassExample.createCriteria();
            criteria2.andEdmcEdmdIdEqualTo(list.get(0).getId()).andIsDelNotEqualTo((byte) 1);
            return edmClassMapper.selectByExample(edmClassExample);
        }
    }

    @Override
    public Map getClassAndRelateClass(String classId, String[] classIdArray) {
        Map ret = new HashMap();
        //根据类id获取相关类及其属性
        List<EdmClassVO> list1 = getRelateClass(classId);//未设置属性
        if (list1.size() > 0) {
            //遍历类集合设置属性
            for (EdmClassVO edmClassVO : list1) {
                EdmPropertyExample example = new EdmPropertyExample();
                EdmPropertyExample.Criteria criteria = example.createCriteria();
                criteria.andEdmpEdmcIdEqualTo(edmClassVO.getId()).andIsDelNotEqualTo((byte) 1);
                List<EdmProperty> propertyList = edmPropertyMapper.selectByExample(example);
                edmClassVO.setClassProperties(propertyList);
            }
            ret.put("relateClass", list1);
        }
        //根据类id数组获取类及其属性
        if (classIdArray.length > 0) {
            List<EdmClassVO> list2 = new ArrayList<>();
            for (String id : classIdArray) {
                EdmClass edmClass = edmClassMapper.selectByPrimaryKey(id);
                EdmClassVO edmClassVO = ModelToVO.edmClassToVO(edmClass);
                EdmPropertyExample example = new EdmPropertyExample();
                EdmPropertyExample.Criteria criteria = example.createCriteria();
                criteria.andEdmpEdmcIdEqualTo(edmClassVO.getId()).andIsDelNotEqualTo((byte) 1);
                List<EdmProperty> propertyList = edmPropertyMapper.selectByExample(example);
                edmClassVO.setClassProperties(propertyList);
                list2.add(edmClassVO);
            }
            ret.put("class", list2);
        }
        return ret;
    }

    //	@Override
    public List<Map<String, List<String>>> getOrmFeilds(String edmdVer, String name) {
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        List<Map<String, List<String>>> tableList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(edmdId)) {
            String edmcId = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmdId, name);
            if (!StringUtil.isNullOrEmpty(edmcId)) {
                List<EdmProperty> edmProperties = edmPropertyMapper.selectEdmPropertiesByCid(edmcId);
                List<String> codeList = new ArrayList<>();
                Map<String, List<String>> tempMap = new HashMap<>();
                if (null != edmProperties && edmProperties.size() > 0) {
                    for (EdmProperty edmProperty : edmProperties) {
                        if ("assemble".equals(edmProperty.getEdmpValueType())) { //判断是不是属性集
                            tempMap = new HashMap<>();
                            codeList = new ArrayList<>();
                            for (EdmProperty edmProperty2 : edmProperties) {
                                if (edmProperty2.getEdmpParentId().equals(edmProperty.getId())) {
                                    codeList.add(edmProperty2.getEdmpCode());
                                }
                            }
                            tempMap.put(edmProperty.getEdmpCode(), codeList);
                            tableList.add(tempMap);
                        } else if (StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {
                            codeList.add(edmProperty.getEdmpCode());
                        }
                    }
                    tempMap.put(name, codeList);
                    tableList.add(tempMap);
                }
            }
        }
        return tableList;
    }

    @Override
    public Map<String, Object> getOrmFeilds(String edmdVer, String name, String attr) {
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        Map<String, Object> retMap = new HashMap<>();
        if (!StringUtil.isNullOrEmpty(edmdId)) {
            String edmcId = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmdId, name);
            if (!StringUtil.isNullOrEmpty(edmcId)) {
                List<EdmProperty> edmProperties;
                if (!StringUtil.isNullOrEmpty(attr)) {
                    String id = edmPropertyMapper.selectEdmPropertiesByCidAndEdmpCode(edmcId, attr);
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByParentId(id);
                } else {
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByCid(edmcId);
                }

                Map<String, Object> edmCodeMap = new HashMap();
                if (null != edmProperties && edmProperties.size() > 0) {
                    if (edmProperties != null) {
                        for (EdmProperty edmProperty : edmProperties) {
                            Map<String, String> map = new HashMap<>();
                            //如果是属性集，向上递归查询edmProperty的edm_code
                            String edmCode = edmProperty.getEdmpCode();
                            if ("assemble".equals(edmProperty.getEdmpValueType())) {
                                edmCode = queryEdmCode(edmCode, edmProperty.getEdmpParentId());
                                log.info("----------edmCode={}", edmCode);
                            }
                            edmProperty.setEdmpCode(edmCode);

                            map.put("edmpName", edmProperty.getEdmpName());
                            map.put("edmpDesc", edmProperty.getEdmpDesc());
                            map.put("edmpValueType", edmProperty.getEdmpValueType());
                            map.put("edmpDataType", edmProperty.getEdmpDataType());
                            map.put("edmpValueSize", edmProperty.getEdmpValueSize());
                            map.put("edmpValueLimit", edmProperty.getEdmpValueLimit());
                            map.put("edmpFormula", edmProperty.getEdmpFormula());
                            map.put("edmpValue", edmProperty.getEdmpValue());
                            edmCodeMap.put(edmProperty.getEdmpCode(), map);
                        }
                    }
                }
                retMap.put(name, edmCodeMap);
            }
        }
        return retMap;
    }

    @Override
    public Map<String, List<String>> getOrmBaseEdmCode(String edmdVer, String name) {
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        String attr = "";
        String tempAttr = "";
        String tempName = name;
        Map<String, List<String>> retMap = new HashMap<>();
        List<String> edmCodeList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(edmdId)) {
            if (name != null) {
                if (name.contains(".")) {
                    name = name.substring(0, name.indexOf("."));
                    attr = tempName.substring(tempName.lastIndexOf(".") + 1, tempName.length());
                    tempAttr = tempName.substring(tempName.indexOf(".") + 1, tempName.length());
                }

            }
            String edmcId = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmdId, name);
            if (!StringUtil.isNullOrEmpty(edmcId)) {

                List<EdmProperty> edmProperties;
                if (!StringUtil.isNullOrEmpty(attr)) {
                    String id = edmPropertyMapper.selectEdmPropertiesByCidAndEdmpCode(edmcId, attr);
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByParentId(id);
                } else {
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByCid(edmcId);
                }


                if (null != edmProperties && edmProperties.size() > 0) {
                    if (edmProperties != null) {
                        for (EdmProperty edmProperty : edmProperties) {
                            Map<String, String> map = new HashMap<>();
                            String edmCode = edmProperty.getEdmpCode();
                            if (!Constant.PROPERTY_TYPE_ASSEMBLE.equals(edmProperty.getEdmpValueType())) {
                                log.info("----------edmCode={}", edmCode);
                                if (StringUtil.isNullOrEmpty(attr)) {
                                    //取第一层的基本属性
                                    if (StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {
                                        edmCodeList.add(edmCode);
                                    }

                                } else {
                                    edmCodeList.add(edmCode);
                                }
                            }

                        }
                    }
                }

            }
            //查询是否有继承的父类属性
            List<EdmProperty> parentAttrList = selectFatherPropertiesByCid(edmcId, false);
            List<EdmProperty> childList = parentAttrList;
            if (!StringUtil.isNullOrEmpty(parentAttrList)) {
                for (EdmProperty e : parentAttrList) {
                    if (StringUtil.isNullOrEmpty(tempAttr)) {
                        if (!Constant.PROPERTY_TYPE_ASSEMBLE.equals(e.getEdmpValueType()) && StringUtil.isNullOrEmpty(e.getEdmpParentId())) {
                            edmCodeList.add(e.getEdmpCode());
                        }
                    } else {
                        //继承父类属性集的属性
                        if (tempAttr.equals(e.getEdmpCode())) {
                            String id = e.getId();
                            for (EdmProperty child : childList) {
                                if (id.equals(child.getEdmpParentId()) && !Constant.PROPERTY_TYPE_ASSEMBLE.equals(child.getEdmpValueType())) {
                                    edmCodeList.add(child.getEdmpCode().substring(tempAttr.length() + 1));
                                }
                            }
                        }

                    }

                }
            }
            retMap.put(name, edmCodeList);
        }


        return retMap;
    }

    public List<EdmProperty> selectFatherPropertiesByCid(String edmcId, boolean isConstOrNot) {
        List<EdmProperty> edmPropertyVOList = new ArrayList<EdmProperty>();
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmcId);
        if (edmClass != null) {
            if (!StringUtil.isNullOrEmpty(edmClass.getEdmcParentId())) {
                edmPropertyVOList = queryPropertyWithParent(edmPropertyVOList, edmClass.getEdmcParentId(), "", isConstOrNot);
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
    public List<EdmProperty> queryPropertyWithParent(List<EdmProperty> edmPropertyVOList, String edmcId, String edmcName, boolean isConstOrNot) {
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
            criteria.andEdmpValueTypeEqualTo("const");
        } else {
            criteria.andEdmpValueTypeNotEqualTo("const");
//			criteria.andEdmpParentIdIsNull();
        }
        criteria.andIsDelNotEqualTo((byte) 1);
        example.setOrderByClause("edmp_seq asc");
        List<EdmProperty> edmPropertiyList = edmPropertyMapper.selectByExample(example);
        if (null != edmPropertiyList && edmPropertiyList.size() > 0) {
            for (EdmProperty edmProperty : edmPropertiyList) {
                log.info("-----------edmProperty.getEdmpParentId()={}", edmProperty.getEdmpParentId());
                String edmCode = edmProperty.getEdmpCode();
                if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {
                    edmCode = queryEdmCode(edmCode, edmProperty.getEdmpParentId());
//					edmCode = queryParentEdmCode(edmCode, edmProperty.getEdmpParentId(), edmPropertiyList);
                }
                log.info("----------edmCode={}", edmCode);
                edmProperty.setEdmpCode(edmCode);
                edmPropertyVOList.add(edmProperty);
            }
        }
        return edmPropertyVOList;
    }

    @Override
    public Map<String, List<String>> getOrmAttrEdmCode(String edmdVer, String name, String attr) {
        Map<String, List<String>> map = new HashMap<>();
        String tempName = name;
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        if (!StringUtil.isNullOrEmpty(edmdId)) {
            //判断name是否是属性集
            if (name != null) {
                if (name.contains(".")) {
                    name = name.substring(0, name.indexOf("."));
                    attr = tempName.substring(tempName.lastIndexOf(".") + 1, tempName.length());
                }

            }
            List<String> codeList = new ArrayList<>();
            String edmcId = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmdId, name);
            if (!StringUtil.isNullOrEmpty(edmcId)) {

                List<EdmProperty> edmProperties;
                if (!StringUtil.isNullOrEmpty(attr)) {
                    String id = edmPropertyMapper.selectEdmPropertiesByCidAndEdmpCode(edmcId, attr);
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByParentId(id);
                } else {
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByCid(edmcId);
                }
                if (null != edmProperties && edmProperties.size() > 0) {
                    for (EdmProperty edmProperty : edmProperties) {
                        String edmCode = edmProperty.getEdmpCode();
                        if ("assemble".equals(edmProperty.getEdmpValueType())) { //判断是不是属性集
                            log.info("----------edmCode={}", edmCode);
                            if (StringUtil.isNullOrEmpty(attr)) {
                                //取第一层的属性集
                                if (StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {
                                    codeList.add(edmCode);
                                }

                            } else {
                                codeList.add(edmCode);
                            }


                        }
                    }

                }

                //查询是否有继承的父类属性集
                List<EdmProperty> parentAttrList = selectFatherPropertiesByCid(edmcId, false);
                List<EdmProperty> childList = parentAttrList;
                if (!StringUtil.isNullOrEmpty(parentAttrList)) {
//				edmCodeList.addAll(parentAttrList);
                    for (EdmProperty e : parentAttrList) {
                        if (StringUtil.isNullOrEmpty(attr)) {
                            if (Constant.PROPERTY_TYPE_ASSEMBLE.equals(e.getEdmpValueType()) && StringUtil.isNullOrEmpty(e.getEdmpParentId())) {
                                codeList.add(e.getEdmpCode());
                            }
                        } else {
                            //继承父类属性集的属性属性集
                            if (attr.equals(e.getEdmpCode())) {
                                String id = e.getId();
                                for (EdmProperty child : childList) {
                                    if (id.equals(child.getEdmpParentId()) && Constant.PROPERTY_TYPE_ASSEMBLE.equals(child.getEdmpValueType())) {
                                        codeList.add(child.getEdmpCode().substring(attr.length() + 1));
                                    }
                                }
                            }

                        }

                    }
                }

                map.put(tempName, codeList);
            }
        }
        return map;
    }


    @Override
    public Map<String, List<String>> getAllEdmCode(String edmdVer, String name, String attr) {
        Map<String, List<String>> map = new HashMap<>();
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        if (!StringUtil.isNullOrEmpty(edmdId)) {
            //判断name是否是属性集
            if (name != null) {
                if (name.contains(".")) {
                    name = name.substring(0, name.indexOf("."));
                }
            }
            log.info("name=========={}", name);

            String edmcId = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmdId, name);
            List<String> codeList = new ArrayList<>();
            if (!StringUtil.isNullOrEmpty(edmcId)) {

                List<EdmProperty> edmProperties;
                if (!StringUtil.isNullOrEmpty(attr)) {
                    String id = edmPropertyMapper.selectEdmPropertiesByCidAndEdmpCode(edmcId, attr);
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByParentId(id);
                    codeList.add(attr);
                } else {
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByCid(edmcId);
                }
                if (null != edmProperties && edmProperties.size() > 0) {
                    for (EdmProperty edmProperty : edmProperties) {
                        String edmCode = edmProperty.getEdmpCode();
//						if("assemble".equals(edmProperty.getEdmpValueType())){ //判断是不是属性集
                        if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {
                            edmCode = queryEdmCode(edmCode, edmProperty.getEdmpParentId());
//							edmCode = queryParentEdmCode(edmCode, edmProperty.getEdmpParentId(), edmProperties);
                            log.info("----------edmCode={}", edmCode);
                        }
                        codeList.add(name + "." + edmCode);
                    }
                }
            }

            //查询是否有继承的父类属性
            List<EdmProperty> parentAttrList = selectFatherPropertiesByCid(edmcId, false);
            if (!StringUtil.isNullOrEmpty(parentAttrList)) {
//				codeList.addAll(parentAttrList);
                for (EdmProperty e : parentAttrList) {
                    codeList.add(name + "." + e.getEdmpCode());
                }
            }
            map.put(name, codeList);
        }
        return map;
    }

    @Override
    public String getEdmShortName(String edmdVer, String name) {
        String str = getStringFromRedis(name, "shortname");
        if (!StringUtil.isNullOrEmpty(str)) {
            return str;
        }
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        EdmClass edmClass = new EdmClass();
        if (!StringUtil.isNullOrEmpty(edmdId)) {
            //判断name是否是属性集
            if (name != null) {
                if (name.contains(".")) {
                    name = name.substring(0, name.indexOf("."));
                }
            }
            log.info("name=========={}", name);

            String edmcId = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmdId, name);

            edmClass = edmClassMapper.selectByPrimaryKey(edmcId);

        }
        if (edmClass != null) {
            setStringToRedis(name, edmClass.getEdmcShortName(), "shortname");
            return edmClass.getEdmcShortName();
        }
        return null;

    }

    @Override
    public String getEdmNameByShortName(String edmdVer, String shortName) {
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        if(!StringUtil.isNullOrEmpty(edmdId)){
            EdmClass edmClass = edmClassMapper.selectSpecialClass(edmdId, shortName);
            if(edmClass != null){
                return edmClass.getEdmcNameEn();
            }
        }
        return null;
    }

    @Override
    public String getDbType(String edmdVer, String name) {
        String str = getStringFromRedis(name, "dbtype");
        if (!StringUtil.isNullOrEmpty(str)) {
//            return str;
        }
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        if (name.contains(".")) {
            name = name.substring(0, name.indexOf("."));
        }
        String dataBaseType = edmClassMapper.getDataBaseTypeByEdmdId(edmdId, name);
        if (!StringUtil.isNullOrEmpty(dataBaseType)) {
            setStringToRedis(name, dataBaseType, "dbtype");
        }
        return dataBaseType;
    }

    /**
     * 递归查询属性集的edmCode集合
     *
     * @param edmCode
     * @param edmcId
     * @return edmCode
     */
    public String queryEdmCode(String edmCode, String edmcId) {
        EdmProperty edmProperty = edmPropertyMapper.selectByPrimaryKey(edmcId);
        if (edmProperty != null) {
            if (Constant.PROPERTY_TYPE_ASSEMBLE.equals(edmProperty.getEdmpValueType())) {
                edmCode = edmProperty.getEdmpCode() + "." + edmCode;
                if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {
                    log.info("edmCode====={}", edmCode);
                    log.info("edmProperty.getEdmpParentId()====={}", edmProperty.getEdmpParentId());
                    edmCode = queryEdmCode(edmCode, edmProperty.getEdmpParentId());
                } else {
                    return edmCode;
                }
            }
        }
        log.info("============end");
        return edmCode;
    }

    @Override
    public Map<String, List<String>> getOrmIndexs(String edmdVer, String name) {
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        Map<String, List<String>> tempMap = new HashMap<>();
        if (!StringUtil.isNullOrEmpty(edmdId)) {
            //判断name是否是属性集
            String attr = "";
            String tempAttr = "";
            String tempName = name;
            if (name != null) {
                if (name.contains(".")) {
                    name = name.substring(0, name.indexOf("."));
                    attr = tempName.substring(tempName.lastIndexOf(".") + 1, tempName.length());
                    tempAttr = tempName.substring(tempName.indexOf(".") + 1, tempName.length());
                }
            }
            List<String> codeList = new ArrayList<>();
            String edmcId = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmdId, name);
            if (!StringUtil.isNullOrEmpty(edmcId)) {
                List<EdmProperty> edmProperties;
                if (!StringUtil.isNullOrEmpty(attr)) {
                    String id = edmPropertyMapper.selectEdmPropertiesByCidAndEdmpCode(edmcId, attr);
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByParentId(id);
                } else {
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByCid(edmcId);
                }
                tempMap = new HashMap<>();

                if (null != edmProperties && edmProperties.size() > 0) {
                    for (EdmProperty edmProperty : edmProperties) {
                        if (edmProperty.getIsIndex() == 1) {
                            codeList.add(edmProperty.getEdmpCode());
                        }
                    }

                }
            }
            //查询继承父类的索引
            List<EdmProperty> parentAttrList = selectFatherPropertiesByCid(edmcId, false);
            List<EdmProperty> childList = parentAttrList;
            if (!StringUtil.isNullOrEmpty(parentAttrList)) {
                for (EdmProperty e : parentAttrList) {
                    if (StringUtil.isNullOrEmpty(tempAttr)) {
                        if (!Constant.PROPERTY_TYPE_ASSEMBLE.equals(e.getEdmpValueType()) && StringUtil.isNullOrEmpty(e.getEdmpParentId()) && 1 == e.getIsIndex()) {
                            codeList.add(e.getEdmpCode());
                        }
                    } else {
                        //继承父类属性集的属性
                        if (tempAttr.equals(e.getEdmpCode())) {
                            String id = e.getId();
                            for (EdmProperty child : childList) {
                                if (id.equals(child.getEdmpParentId()) && !Constant.PROPERTY_TYPE_ASSEMBLE.equals(child.getEdmpValueType()) && 1 == child.getIsIndex()) {
                                    codeList.add(child.getEdmpCode().substring(tempAttr.length() + 1));
                                }
                            }
                        }
                    }

                }
            }
            tempMap.put(tempName, codeList);
        }
        return tempMap;
    }

    /**
     * 根据类id查询可选的关联属性
     *
     * @param classId
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public List<EdmPropertyVO> getChooseRelateProperty(String classId) {

        if (org.apache.commons.lang.StringUtils.isEmpty(classId)) {
            return null;
        }

        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpEdmcIdEqualTo(classId).andEdmpParentIdIsNull().andIsDelNotEqualTo((byte) 1);
        List<EdmProperty> list = edmPropertyMapper.selectByExample(example);
        if (CollectionUtils.isEmpty(list)) {
            return null;
        }

        List<EdmPropertyVO> newList = new ArrayList<>();
        for (EdmProperty edmProperty : list) {
            EdmPropertyVO edmPropertyVO = new EdmPropertyVO();
            edmPropertyVO.setId(edmProperty.getId());
            edmPropertyVO.setEdmpName(edmProperty.getEdmpName());
            if (com.alibaba.druid.util.StringUtils.equals(Constant.PROPERTY_TYPE_ASSEMBLE, edmProperty.getEdmpValueType())) {
                //根据当前属性查询是否存在属性集列表
                List<EdmProperty> childrenList = edmPropertyMapper.selectEdmPropertiesByParentId(edmProperty.getId());
                edmPropertyVO.setChildren(recursiveEdmPropertyChildren(childrenList));
            }

            newList.add(edmPropertyVO);
        }
        return newList;
    }

    /**
     * 递归查询属性中的属性集
     * @param list
     * @return
     */
    private List<EdmPropertyVO> recursiveEdmPropertyChildren(List<EdmProperty> list) {

        //判断childrenList是否为空
        if (list == null || list.size() == 0) {
            return null;
        }

        List<EdmPropertyVO> childrenNewList = new ArrayList<>();
        for (EdmProperty edmProperty : list) {
            EdmPropertyVO childrenVo = new EdmPropertyVO();
            childrenVo.setId(edmProperty.getId());
            childrenVo.setEdmpName(edmProperty.getEdmpName());
            if (com.alibaba.druid.util.StringUtils.equals(Constant.PROPERTY_TYPE_ASSEMBLE, edmProperty.getEdmpValueType())) {
                List<EdmProperty> childrenList = edmPropertyMapper.selectEdmPropertiesByParentId(edmProperty.getId());
                childrenVo.setChildren(recursiveEdmPropertyChildren(childrenList));
            }
            childrenNewList.add(childrenVo);
        }

        return childrenNewList;
    }

    @Override
    public boolean isSet(String id) {
        EdmClassFormatExample edmClassFormatExample = new EdmClassFormatExample();
        EdmClassFormatExample.Criteria criteria = edmClassFormatExample.createCriteria();
        criteria.andEdmfEdmcIdEqualTo(id).andIsDelNotEqualTo((byte) 1);
        long count = edmClassFormatMapper.countByExample(edmClassFormatExample);
        System.out.println(count);
        if (count > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据类id与对象id查询对象值
     *
     * @param classId
     * @param objectId
     * @return
     */
    public String getObjectValue(String classId, String objectId) {
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(classId);
        String charactorFormat = null;
        if (edmClass != null) {
            Map map = edmClassFormatService.getCharacterAndFormat(classId);//查询特征值
            if (map != null) {
                JSONObject datas = new JSONObject();
                String classNameEn = edmClass.getEdmcNameEn();
                datas.put("edmName", classNameEn);
                List<String> characters = (List<String>) map.get("character");//获取特征属性
                if (null != characters && characters.size() > 0) {
                    //组装参数
                    JSONObject search = new JSONObject();
                    JSONArray conditions = new JSONArray();
                    JSONObject condition = new JSONObject();
                    condition.put("attr", "id");
                    condition.put("operator", "=");
                    condition.put("value", objectId);
                    conditions.add(condition);
                    search.put("columns", characters);
                    search.put("conditions", conditions);
                    datas.put("search", search);
                    System.out.println(datas.toJSONString());
                    //查询
                    Result result = ormClient.find(datas.toJSONString());
                    if (result.getRetCode() != 1) {
                        throw new RuntimeException("orm异常");
                    }
                    JSONObject data = (JSONObject) JSONObject.toJSON(result.getData());
                    JSONArray dataSet = (JSONArray) data.get("dataset");
                    if (dataSet != null && dataSet.size() > 0) {
                        JSONObject object = dataSet.getJSONObject(0);
                        charactorFormat = (String) map.get("format");
                        for (String edmpCode : characters) {
                            String value = object.getString(edmpCode);
                            if (!StringUtil.isNullOrEmpty(value)) {
                                charactorFormat = charactorFormat.replace(edmpCode, value);
                            }
                        }
                    }
                }
            }
        }
        return charactorFormat;
    }

    /**
     * 根据模型的版本号和类的英文名称获取所属类子孙类中的实体类
     *
     * @param edmdVer
     * @param edmcNameEn
     * @return
     */
    public List<EdmClass> getEntityByVersionAndEnglishName(String edmdVer, String edmcNameEn) {
        List<EdmClass> edmClassList = null;
        List<EdmClass> newEdmClassList = new ArrayList<>();

        //根据模型号和类的英文名称获取类
        EdmClass edmClass = getEdmClassByVersionAndEnName(edmdVer, edmcNameEn);
        if (edmClass == null) {
            return null;
        }

        //根据类id获取类的所有子孙类
        String id = edmClass.getId();
        edmClassList = selectEdmClassParentIdIsNotNull(edmClass.getEdmcEdmdId());
        edmClassList = getAllChildrenById(edmClassList, id);

        //获取所有子孙类中的实体类
        for (EdmClass e : edmClassList) {
            if (!StringUtil.isNullOrEmpty(e.getIsEntity()) && e.getIsEntity() == (byte) 1) {
                newEdmClassList.add(e);
            }
        }

        return newEdmClassList;
    }

    @Override
    public Map<String, Map<String, Object>> getEdmAttrObject(String edmdVer, String name) {
        Map<String, Map<String, Object>> retMap = new HashMap<>();
        Map<String, Object> map = new HashMap<>();
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        String tempName = name;
        String attr = "";
        if (!StringUtil.isNullOrEmpty(edmdId)) {
            //判断name是否是属性集
            if (name != null) {
                if (name.contains(".")) {
                    name = name.substring(0, name.indexOf("."));
                    attr = tempName.substring(tempName.lastIndexOf(".") + 1, tempName.length());
                }
            }
            log.info("name=========={}", name);

            String edmcId = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmdId, name);

            if (!StringUtil.isNullOrEmpty(edmcId)) {
                List<EdmProperty> edmProperties;
                if (!StringUtil.isNullOrEmpty(attr)) {
                    String id = edmPropertyMapper.selectEdmPropertiesByCidAndEdmpCode(edmcId, attr);
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByParentId(id);
                } else {
                    edmProperties = edmPropertyMapper.selectEdmPropertiesByCid(edmcId);
                }

                if (null != edmProperties && edmProperties.size() > 0) {
                    for (EdmProperty edmProperty : edmProperties) {
                        String edmCode = edmProperty.getEdmpCode();
                        if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {
                            if(StringUtil.isNullOrEmpty(attr)){
                                edmCode = queryEdmCode(edmCode, edmProperty.getEdmpParentId());
                            }
                            log.info("----------edmCode={}", edmCode);
                        }

                        if (Constant.EDM_OBJECTLINK.equals(edmProperty.getEdmpValueType()) || Constant.EDM_CONVOLUTION.equals(edmProperty.getEdmpValueType()) || Constant.EDM_OBJECT.equals(edmProperty.getEdmpValueType()) || Constant.EDM_MEASUREMENT.equals(edmProperty.getEdmpValueType())) {
                            String edmClassId = edmProperty.getEdmpDataType();
                            EdmClass edmClass = getEdmClassById(edmClassId);
                            if (!StringUtil.isNullOrEmpty(edmClass)) {
                                map.put(edmCode, edmClass.getEdmcNameEn());
                            }
                        }
                    }
                }
            }

            if(StringUtil.isNullOrEmpty(attr)){
                //查询是否有继承的父类属性
                List<EdmProperty> parentAttrList = selectFatherPropertiesByCid(edmcId, false);
                if (!StringUtil.isNullOrEmpty(parentAttrList)) {
                    for (EdmProperty e : parentAttrList) {
                        String edmClassId = "";
                        if (Constant.EDM_OBJECTLINK.equals(e.getEdmpValueType()) || Constant.EDM_CONVOLUTION.equals(e.getEdmpValueType()) || Constant.EDM_OBJECT.equals(e.getEdmpValueType()) || Constant.EDM_MEASUREMENT.equals(e.getEdmpValueType())) {
                            edmClassId = e.getEdmpDataType();
                        }
                        EdmClass edmClass = getEdmClassById(edmClassId);
                        if (!StringUtil.isNullOrEmpty(edmClass)) {
                            map.put(e.getEdmpCode(), edmClass.getEdmcNameEn());
                        }

                    }
                }
            }
        }
        retMap.put(tempName, map);
        return retMap;
    }

    @Override
    public List<Map<String, Object>> getEdmDataType(String edmdVer, String name) {
        List<Map<String, Object>> codeList = new ArrayList<>();
        Map<String, Object> map = new HashMap<>();
        String edmdId = edmModelerMapper.selectEdmdIdByVer(edmdVer);
        if (!StringUtil.isNullOrEmpty(edmdId)) {
            //判断name是否是属性集
            if (name != null) {
                if (name.contains(".")) {
                    name = name.substring(0, name.indexOf("."));
                }
            }
            log.info("name=========={}", name);
            List<Map<String, Object>> redisList = getEdmRedisValue(name);
            if (!StringUtil.isNullOrEmpty(redisList) && redisList.size() > 0) {
                return redisList;
            }

            String edmcId = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmdId, name);

            if (!StringUtil.isNullOrEmpty(edmcId)) {
                List<EdmProperty> edmProperties = edmPropertyMapper.selectEdmPropertiesByCid(edmcId);
                if (null != edmProperties && edmProperties.size() > 0) {
                    for (EdmProperty edmProperty : edmProperties) {
                        String edmCode = edmProperty.getEdmpCode();
                        if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {
                            edmCode = queryEdmCode(edmCode, edmProperty.getEdmpParentId());
//							edmCode = queryParentEdmCode(edmCode, edmProperty.getEdmpParentId(), edmProperties);
                            log.info("----------edmCode={}", edmCode);
                        }
                        if (Constant.EDM_OBJECTLINK.equals(edmProperty.getEdmpValueType()) || Constant.EDM_CONVOLUTION.equals(edmProperty.getEdmpValueType()) || Constant.EDM_OBJECT.equals(edmProperty.getEdmpValueType()) || Constant.EDM_MEASUREMENT.equals(edmProperty.getEdmpValueType())) {
                            map.put(name + "." + edmCode, edmProperty.getEdmpValueType());
                        } else {
                            map.put(name + "." + edmCode, edmProperty.getEdmpDataType());
                        }

                    }
                }
            }

            //查询是否有继承的父类属性
            List<EdmProperty> parentAttrList = selectFatherPropertiesByCid(edmcId, false);
            if (!StringUtil.isNullOrEmpty(parentAttrList)) {
                for (EdmProperty e : parentAttrList) {
                    if (Constant.EDM_OBJECTLINK.equals(e.getEdmpValueType()) || Constant.EDM_CONVOLUTION.equals(e.getEdmpValueType()) || Constant.EDM_OBJECT.equals(e.getEdmpValueType()) || Constant.EDM_MEASUREMENT.equals(e.getEdmpValueType())) {
                        map.put(name + "." + e.getEdmpCode(), e.getEdmpValueType());
                    } else {
                        map.put(name + "." + e.getEdmpCode(), e.getEdmpDataType());
                    }
                }
            }
        }
        codeList.add(map);
        setValueToRedis(name, codeList);
        return codeList;
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
     * 获取指定版本下ParentId不为null的类集合
     *
     * @return
     */
    private List<EdmClass> selectEdmClassParentIdIsNotNull(String edmdId) {
        List<EdmClass> edmClassList = null;

        EdmClassExample example = new EdmClassExample();
        EdmClassExample.Criteria criteria = example.createCriteria();
        criteria.andEdmcParentIdIsNotNull().andEdmcEdmdIdEqualTo(edmdId).andIsDelNotEqualTo((byte) 1);
        edmClassList = edmClassMapper.selectByExample(example);

        return edmClassList;
    }

    /**
     * 通过给定的类id，返回给定类集合中该类的所有子孙类
     *
     * @param edmClassList
     * @param id
     * @return
     */
    private List<EdmClass> getAllChildrenById(List<EdmClass> edmClassList, String id) {
        List<EdmClass> classList = new ArrayList<>();
        List<EdmClass> tempList = new ArrayList<>();

        //获取直接子类
        for (EdmClass anEdmClassList : edmClassList) {
            if (anEdmClassList.getEdmcParentId().equals(id)) {
                tempList.add(anEdmClassList);
            }
        }
        classList.addAll(tempList);

        //获取直接子类的子孙类
        while (!tempList.isEmpty()) {
            List<EdmClass> classes = new ArrayList<>();
            classes.addAll(tempList);
            tempList.clear();

            for (EdmClass aClass : classes) {
                for (EdmClass anEdmClassList : edmClassList) {
                    if (anEdmClassList.getEdmcParentId().equals(aClass.getId())) {
                        tempList.add(anEdmClassList);
                    }
                }
            }
            classList.addAll(tempList);
        }

        return classList;
    }

    private EdmClass getEdmClassById(String id) {
        return edmClassMapper.selectByPrimaryKey(id);
    }

    /**
     * 向上查询父属性集的edmCode
     *
     * @param edmCode       edmCode
     * @param edmcId        edmcId
     * @param edmProperties edmProperty列表
     * @return edmCode
     */
    private String queryParentEdmCode(String edmCode, String edmcId, List<EdmProperty> edmProperties) {

        for (EdmProperty edmProperty : edmProperties) {
            if (!StringUtil.isNullOrEmpty(edmProperty)) {
                if (Constant.PROPERTY_TYPE_ASSEMBLE.equals(edmProperty.getEdmpValueType()) && edmcId.equals(edmProperty.getId())) {
                    edmCode = edmProperty.getEdmpCode() + "." + edmCode;
                    if (!StringUtil.isNullOrEmpty(edmProperty.getEdmpParentId())) {
                        //edmpParentId不为空说明向上还有父属性集
                        edmCode = queryParentEdmCode(edmCode, edmProperty.getEdmpParentId() == null ? "" : edmProperty.getEdmpParentId(), edmProperties);
                    }
                }
            }
        }
        return edmCode;
    }

    /**
     * 根据类英文名和版本id来查询类
     *
     * @param edmcNameEn 类英文名
     * @param edmcVer    版本号
     * @return
     */
    @Override
    public String getClassByEdmNameEn(String edmcNameEn, String edmcVer) {

        String modelerId = edmModelerMapper.selectEdmdIdByVer(edmcVer);
        if (StringUtil.isNullOrEmpty(modelerId)) {
            return null;
        }

        return edmClassMapper.selectEdmcIdByEdmdIdAndName(modelerId, edmcNameEn);
    }

    /**
     * 保存数据到redis
     *
     * @param name
     * @param codeList
     */
    private void setValueToRedis(String name, List<Map<String, Object>> codeList) {
        if (!jedisCluster.exists(name)) {
            String jsonStr = JSONArray.toJSONString(codeList);
            String retStr = jedisCluster.set(name, jsonStr);
            log.info("========retStr={}", retStr);
//			ByteArrayOutputStream bos = new ByteArrayOutputStream();
//			ObjectOutputStream oos = null;
//			try {
//				oos = new ObjectOutputStream(bos);
//				oos.writeObject(codeList);
//				byte[] byteArray = bos.toByteArray();
//				String retStr = jedisCluster.set(name.getBytes(), byteArray);
//				log.info("========retStr={}", retStr);
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally{
//				try {
//					bos.close();
//					oos.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}

        }
    }

    /**
     * 根据key从redis查询
     * @param name
     * @return list
     */
    public List<Map<String, Object>> getEdmRedisValue(String name) {
        List<Map<String, Object>> list = new ArrayList<>();
        if (jedisCluster.exists(name)) {
            String jsonStr = jedisCluster.get(name);
            list = JSONArray.parseObject(jsonStr, List.class);
//			byte[] bs = jedisCluster.get(name.getBytes());
//			ByteArrayInputStream bis = new ByteArrayInputStream(bs);
//			ObjectInputStream inputStream = null;
//			List<Map<String, Object>> readObject = null;
//			try {
//				inputStream = new ObjectInputStream(bis);
//				readObject = (List<Map<String, Object>>) inputStream.readObject();
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					inputStream.close();
//					bis.close();
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//			if(!StringUtil.isNullOrEmpty(readObject)){
//				list = (List<Map<String, Object>>)readObject;
//			}
        }
        log.info(" read object return list.size()={}", list.size());
        return list;
    }


    /**
     * 保存数据到redis
     *
     * @param name  edm名称
     * @param value 存到redis的value
     * @param type  存入redis类型
     */
    private boolean setStringToRedis(String name, String value, String type) {
        String retStr = "";
        name = name + "_" + type;
        if (!jedisCluster.exists(name)) {
            retStr = jedisCluster.set(name, value);
            log.info("========retStr={}", retStr);
        }
        if ("OK".equals(retStr)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 根据key从redis查询
     *
     * @param name edmname
     * @param type
     * @return String
     */
    public String getStringFromRedis(String name, String type) {
        String str = "";
        name = name + "_" + type;
        if (jedisCluster.exists(name)) {
            str = jedisCluster.get(name);
        }
        log.info(" read object return str={}", str);
        return str;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Map<String, String>> selectAssemblesByEdmcId(String edmcId) {
        if (StringUtil.isNullOrEmpty(edmcId)) {
            return null;
        }
        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpEdmcIdEqualTo(edmcId).andEdmpValueTypeEqualTo(Constant.PROPERTY_TYPE_ASSEMBLE).andIsDelNotEqualTo((byte)1);
        example.setOrderByClause("edmp_seq asc");
        List<EdmProperty> edmPropertyList = edmPropertyMapper.selectByExample(example);

        List<Map<String, String>> mapList = new ArrayList<>();
        for (EdmProperty edmProperty : edmPropertyList) {
            Map<String, String> map = new HashMap<>();
            map.put("edmpName", edmProperty.getEdmpName());
            map.put("id", edmProperty.getId());
            mapList.add(map);
        }
        log.info(edmPropertyList.toString());
        return mapList;
    }

    @Override
    public List<Map<String, String>> selectProperties(String edmcId, String assembleId) {
        if (StringUtil.isNullOrEmpty(edmcId)) {
            return null;
        }
        List<EdmProperty> edmPropertyList = null;
        if (StringUtil.isNullOrEmpty(assembleId)) {
            EdmPropertyExample example = new EdmPropertyExample();
            EdmPropertyExample.Criteria criteria = example.createCriteria();
            criteria.andEdmpEdmcIdEqualTo(edmcId).andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_ASSEMBLE)
                    .andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_CONST)
                    .andEdmpParentIdIsNull().andIsDelNotEqualTo((byte)1);
            example.setOrderByClause("edmp_seq asc");
            edmPropertyList = edmPropertyMapper.selectByExample(example);
        } else {
            EdmPropertyExample example = new EdmPropertyExample();
            EdmPropertyExample.Criteria criteria = example.createCriteria();
            criteria.andEdmpEdmcIdEqualTo(edmcId).andEdmpParentIdEqualTo(assembleId)
                    .andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_ASSEMBLE).andIsDelNotEqualTo((byte)1);
            example.setOrderByClause("edmp_seq asc");
            edmPropertyList = edmPropertyMapper.selectByExample(example);
        }

        List<Map<String, String>> mapList = new ArrayList<>();
        for (EdmProperty edmProperty : edmPropertyList) {
            Map<String, String> map = new HashMap<>();
            map.put("edmpCode", edmProperty.getEdmpCode());
            map.put("edmpName", edmProperty.getEdmpName());
            map.put("id", edmProperty.getId());
            mapList.add(map);
        }
        log.info(edmPropertyList.toString());
        return mapList;
    }


    @Override
    public List<EdmIndexVO> selectIndexByEdmcId(String edmcId) {
        if (StringUtil.isNullOrEmpty(edmcId)) {
            return null;
        }
        List<EdmIndexVO> edmIndexVOS = new ArrayList<>();
        List<EdmIndex> edmIndexList = edmIndexMapper.selectIndexsByEdmcId(edmcId);
        for (EdmIndex edmIndex : edmIndexList) {
            EdmIndexVO edmIndexVO = getEdmIndexVO(edmIndex);
            edmIndexVOS.add(edmIndexVO);
        }

        return edmIndexVOS;
    }

    @Override
    public List<EdmIndexVO> getParentIndexByEdmcId(String edmcId) {
        if (StringUtil.isNullOrEmpty(edmcId)) {
            return null;
        }

        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmcId);
        if (edmClass == null) {
            return null;
        }

        List<String> edmcIds = new ArrayList<>();
        String parentId = edmClass.getEdmcParentId();
        while (!StringUtil.isNullOrEmpty(parentId)) {
            EdmClass t = edmClassMapper.selectByPrimaryKey(parentId);
            if (t != null) {
                parentId = t.getEdmcParentId();
                edmcIds.add(t.getId());
            } else {
                parentId = null;
            }
        }

        List<EdmIndexVO> edmIndexVOS = new ArrayList<>();
        for (String id : edmcIds) {
            List<EdmIndex> edmIndexList = edmIndexMapper.selectIndexsByEdmcId(id);
            for (EdmIndex edmIndex : edmIndexList) {
                EdmIndexVO edmIndexVO = getEdmIndexVO(edmIndex);
                edmIndexVOS.add(edmIndexVO);
            }
        }

        return edmIndexVOS;
    }

    @Override
    public EdmMethod getMethodsByEdmcNameVer(String ver, String className, String methodName) {
        String edmdId = edmModelerMapper.selectEdmdIdByVer(ver);//根据版本号获取模型id
        EdmClassExample example = new EdmClassExample();
        EdmClassExample.Criteria criteria = example.createCriteria();
        criteria.andIsDelNotEqualTo((byte)1);
        criteria.andEdmcNameEnEqualTo(className);
        criteria.andEdmcEdmdIdEqualTo(edmdId);
        List<EdmClass> list = edmClassMapper.selectByExample(example);
        if(list != null && list.size()>0){
            EdmMethodExample edmMethodExample = new EdmMethodExample();
            EdmMethodExample.Criteria criteria1 = edmMethodExample.createCriteria();
            criteria1.andIsDelNotEqualTo((byte)1);
            criteria1.andEdmmNameEqualTo(methodName);
            criteria1.andEdmmEdmcIdEqualTo(list.get(0).getId());
            List<EdmMethod> edmMethods = edmMethodMapper.selectByExample(edmMethodExample);
            if(null != edmMethods && edmMethods.size()>0){
                return edmMethods.get(0);
            }
        }
        return null;
    }

    @Override
    public List<EdmClass> getEdmClasses(String[] ids) {
        List<EdmClass> list = new ArrayList<>();
        for(String id :ids){
            list.add(edmClassMapper.selectByPrimaryKey(id));
        }
        return list;
    }

    @Override
    public List<EdmClass> getMonitorClasses(String[] ids) {
        if (ids == null || ids.length <= 0) { return null; }
        List<String> idList = edmPropertyMapper.getEdmpEdmcIdByIds(ids);

        if (idList == null || idList.size() == 0) { return null; }
        return getEdmClasses(idList.toArray(new String[0]));
    }

    @Override
    public List<EdmClass> getResourcesClasses(String version) {
        if (StringUtil.isNullOrEmpty(version)) { return null; }

        //获取版本id
        String edmdId = edmModelerMapper.selectEdmdIdByVer(version);

        //获取当前版本监管类id
        if (StringUtil.isNullOrEmpty(edmdId)) { return null; }
        String id = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmdId, "monitor");

        //获取当前版本监管类的所有子孙类
        if (StringUtil.isNullOrEmpty(id)) { return null; }
        List<EdmClass> edmClassList = new ArrayList<>();
        edmClassList = selectEdmClassParentIdIsNotNull(edmdId);
        edmClassList = getAllChildrenById(edmClassList, id);

        //获取监管类子类id集合
        if (edmClassList == null || edmClassList.size() == 0) { return null; }
        List<String> ids = new ArrayList<>();
        ids.add(id); //监管类id
        for (EdmClass edmClass : edmClassList) {
            if (edmClass == null || StringUtil.isNullOrEmpty(edmClass.getId())) { continue; }
            ids.add(edmClass.getId());
        }

        //获取被监管的资源类id集合
        if (ids == null || ids.size() == 0) { return null; }
        List<String> idList = new ArrayList<>();
        idList = edmPropertyMapper.getEdmpvalueByIds(ids.toArray(new String[0]));

        //获取被监管的资源类
        if (idList == null || idList.size() == 0) { return null; }
        return getEdmClasses(idList.toArray(new String[0]));
    }

    private EdmIndexVO getEdmIndexVO(EdmIndex edmIndex) {
        String edmcName = edmClassMapper.getEdmcNameById(edmIndex.getEdmcId());
        String edmpName = edmPropertyMapper.getEdmpNameById(edmIndex.getEdmpAssId());
        List<EdmIndexDetail> edmIndexDetailList = edmIndexDetailMapper.selectByIndexId(edmIndex.getId());

        String indexProppertyIds = "";
        for (EdmIndexDetail edmIndexDetail : edmIndexDetailList) {
            indexProppertyIds += edmIndexDetail.getEdmpId() + ",";
        }
        //去掉尾部逗号
        if (indexProppertyIds.length() > 0) {
            indexProppertyIds = indexProppertyIds.substring(0, indexProppertyIds.length() - 1);
        }

        List<String> stringList = new ArrayList<>();
        String ids[] = indexProppertyIds.split(",");
        for (String id : ids) {
            String name = edmPropertyMapper.getEdmpNameById(id);
            if (!StringUtil.isNullOrEmpty(name)) {
                stringList.add(name);
            }
        }
        String indexPropertyNames = "";
        for (String s : stringList) {
            indexPropertyNames += s + ",";
        }
        //去掉尾部逗号
        if (indexPropertyNames.length() > 0) {
            indexPropertyNames = indexPropertyNames.substring(0, indexPropertyNames.length() - 1);
        }

        EdmIndexVO edmIndexVO = ModelToVO.edmIndexToVO(edmIndex);
        edmIndexVO.setIndexPropertyNames(indexPropertyNames);
        edmIndexVO.setIndexProppertyIds(indexProppertyIds);
        edmIndexVO.setEdmcName(edmcName);
        edmIndexVO.setEdmpName(edmpName);
        return edmIndexVO;
    }

    /**
     * 根据类编码获取类及其子类
     * @param edmcCode
     * @return
     */
    @Override
    public EdmClassVO getEdmClassesByEdmcCode(String edmcCode) {
        if (StringUtil.isNullOrEmpty(edmcCode)) { return null; }
        String modelerId = edmModelerMapper.selectEdmdIdByVer(version);
        if (StringUtil.isNullOrEmpty(modelerId)) { return null; }
        List<EdmClass> edmClassList = new ArrayList<>();
        EdmClass edmClass = edmClassMapper.getEdmClassByEdmcCode(edmcCode, modelerId);
        if (edmClass == null) { return null; }
        edmClassList.add(edmClass);
        edmClassList = findChildrenById(edmClassList, edmClass.getId());

        List<EdmClassVO> edmClassVOList = new ArrayList<>();
        for (EdmClass aClass : edmClassList) {
            edmClassVOList.add(ModelToVO.edmClassToVO(aClass));
        }

        for (EdmClassVO edmClassVO : edmClassVOList) {
            if (edmClassVO == null || StringUtil.isNullOrEmpty(edmClassVO.getId())) { continue; }
            for (EdmClassVO q : edmClassVOList) {
                if (q == null || StringUtil.isNullOrEmpty(q.getEdmcParentId())) { continue; }
                if (edmClassVO.getId().equals(q.getEdmcParentId())) {
                    if (edmClassVO.getChildren() == null ) {
                        List<EdmClassVO> t = new ArrayList<>();
                        edmClassVO.setChildren(t);
                    }
                    edmClassVO.getChildren().add(q);
                }
            }
        }

        for (EdmClassVO edmClassVO : edmClassVOList) {
            if (edmClassVO == null || StringUtil.isNullOrEmpty(edmClassVO.getEdmcCode())) { continue; }
            if (edmcCode.equals(edmClassVO.getEdmcCode())) { return edmClassVO; }
        }
        return null;
    }

    /**
     * 获取类及其上一级父类属性中数据类型为岗位类和部门类的属性
     * @param classId
     * @return
     */
    @Override
    public List<EdmProperty> getPropertiesOfEdmClass(String classId) {
        if (StringUtil.isNullOrEmpty(classId)) { return null; }
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(classId);
        if (edmClass == null || StringUtil.isNullOrEmpty(edmClass.getEdmcParentId())) { return null; }
        EdmClass parentClass = edmClassMapper.selectByPrimaryKey(edmClass.getEdmcParentId());
        if (parentClass == null || StringUtil.isNullOrEmpty(parentClass.getId())) { return null; }
        String modelerId = edmModelerMapper.selectEdmdIdByVer(version);
        if (StringUtil.isNullOrEmpty(modelerId)) { return null; }
        String jobPositionId = edmClassMapper.selectEdmcIdByEdmdIdAndName(modelerId, "jobposition");
        String deptTreeId = edmClassMapper.selectEdmcIdByEdmdIdAndName(modelerId, "depttree");

        List<String> dataTypes = new ArrayList<>();
        dataTypes.add(jobPositionId);
        dataTypes.add(deptTreeId);

        List<String> classIds = new ArrayList<>();
        classIds.add(classId);
        classIds.add(parentClass.getId());

        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpEdmcIdIn(classIds).andEdmpDataTypeIn(dataTypes)
                .andIsDelNotEqualTo((byte) 1);

        return edmPropertyMapper.selectByExample(example);
    }

    @Override
    public String getClassNamesByIds(String ids) {
        StringBuilder sb = new StringBuilder();
        String[] idArr = ids.split(",");
        for (String s : idArr) {
            String str = edmClassMapper.getEdmcNameById(s);
            if (!StringUtil.isNullOrEmpty(str)) {
                sb.append(str + ",");
            }
        }
        if (!StringUtil.isNullOrEmpty(sb)) {
            sb.delete(sb.length()-1, sb.length());
        }
        return sb.toString();
    }

    /**
     * 根据类编码获取类Id
     * @param edmcCode
     * @return
     */
    @Override
    public Map<String,String> getIdByEdmcCode(String edmcCode){
        if(StringUtil.isNullOrEmpty(edmcCode)){
            return null;
        }
        Map<String,String> map = edmClassMapper.getIdByEdmcCode(edmcCode);
        return map;
    }

    /**
     * 根据类id获取关联方法、卷积方法
     * @param classId 类id
     * @param edmmMethodType 1：关联方法 2:卷积方法
     * @return
     */
    @Override
    public List<Map<String, String>> getLinkMethod(String classId, String edmmMethodType) {
        List<String> parentIds = getParentId(classId);
        parentIds.add(classId);

        List<EdmMethod> edmMethods = new ArrayList<>();
        if (parentIds != null && parentIds.size() > 0) {
            for (String parentId : parentIds) {
                List<EdmMethod> methodList = edmMethodMapper.selectEdmMethodByCid(parentId);
                if (methodList != null && methodList.size() > 0) {
                    edmMethods.addAll(methodList);
                }
            }
        }

        List<Map<String, String>> mapList = new ArrayList<>();
        if (edmMethods != null && edmMethods.size() > 0) {
            for (EdmMethod edmMethod : edmMethods) {
                if (!StringUtil.isNullOrEmpty(edmmMethodType)) {
                    if (edmmMethodType.equals(edmMethod.getEdmmMethodType())) {
                        Map<String, String> map = new HashMap<>(4);
                        map.put("id", edmMethod.getId());
                        map.put("codeValue", edmMethod.getEdmmDesc());
                        map.put("codeName", edmMethod.getEdmmName());
                        map.put("codeType", "");
                        mapList.add(map);
                    }
                }
            }
        }

        return mapList;
    }

    @Override
    public List<EdmProperty> getAllProperties(String id) {
        EdmPropertyExample example = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = example.createCriteria();
        criteria.andEdmpEdmcIdEqualTo(id).andIsDelNotEqualTo((byte)1)
                .andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_ASSEMBLE)
                .andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_CONST);

        return edmPropertyMapper.selectByExample(example);
    }

    @Override
    public List<EdmClassVO> selectEdmClassesAndProps(String ids) throws Exception {
        if (StringUtil.isNullOrEmpty(ids)) { return null; }

        EdmClassExample example = new EdmClassExample();
        EdmClassExample.Criteria criteria = example.createCriteria();
        criteria.andIdIn(Arrays.asList(ids.split(","))).andIsDelNotEqualTo((byte) 1);
        List<EdmClass> edmClasses = edmClassMapper.selectByExample(example);

        List<EdmClassVO> edmClassVOS = new ArrayList<>();
        if (edmClasses != null && edmClasses.size() > 0) {
            for (EdmClass edmClass : edmClasses) {
                if (edmClass == null || StringUtil.isNullOrEmpty(edmClass.getId())) {
                    continue;
                }

                EdmClassVO edmClassVO = ModelToVO.edmClassToVO(edmClass);

                EdmPropertyExample edmPropertyExample = new EdmPropertyExample();
                EdmPropertyExample.Criteria c = edmPropertyExample.createCriteria();
                c.andEdmpEdmcIdEqualTo(edmClass.getId()).andIsDelNotEqualTo((byte) 1);
                List<EdmProperty> edmPropertys = edmPropertyMapper.selectByExample(edmPropertyExample);

                List<EdmPropertyVO> props = new ArrayList<>();
                if (edmPropertys != null && edmPropertys.size() > 0) {
                    List<EdmPropertyVO> edmPropertyVOS = new ArrayList<>();
                    for (EdmProperty edmProperty : edmPropertys) {
                        if (edmProperty != null) {
                            edmPropertyVOS.add(ModelToVO.edmPropertyToVO(edmProperty));
                        }
                    }

                    for (EdmPropertyVO edmPropertyVO : edmPropertyVOS) {
                        Boolean addFlag = false;
                        if (edmPropertyVO == null) {
                            continue;
                        }
                        for (EdmPropertyVO propertyVO : edmPropertyVOS) {
                            if (propertyVO == null) {
                                continue;
                            }
                            if (!StringUtil.isNullOrEmpty(edmPropertyVO.getEdmpParentId())
                                    && edmPropertyVO.getEdmpParentId().equals(propertyVO.getId())) {
                                addFlag = true;
                                if (propertyVO.getChildren() == null) {
                                    propertyVO.setChildren(new ArrayList<>());
                                }
                                propertyVO.getChildren().add(edmPropertyVO);
                            }
                        }

                        if (!addFlag) {
                            props.add(edmPropertyVO);
                        }
                    }
                }
                edmClassVO.setEdmPropertyVOS(props);
                edmClassVOS.add(edmClassVO);
            }
        }

        return edmClassVOS;
    }

    /**
     * 根据类id获取父类的id集合
     * @param classId
     * @return
     */
    private List<String> getParentId(String classId) {
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(classId);
        List<String> ids = new ArrayList<>();
        while (edmClass != null) {
            edmClass = edmClassMapper.selectByPrimaryKey(edmClass.getEdmcParentId());
            if (edmClass != null) {
                ids.add(edmClass.getId());
            }
        }

        return ids;
    }
}
