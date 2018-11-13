package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.ErrorMsgConstants;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.base.CurrentSessionEntity;
import com.huntkey.rx.purchase.common.model.goods.GoodsFeaDTO;
import com.huntkey.rx.purchase.common.model.goodsfeature.GoodsFeatureDTO;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.BizFormService;
import com.huntkey.rx.purchase.provider.service.GoodsFeatureService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author  liangh on 2017/12/27 0027.
 */
@Service
public class GoodsFeatureServiceImpl implements GoodsFeatureService {

    Logger logger = LoggerFactory.getLogger(GoodsFeatureServiceImpl.class);

    @Autowired
    OrmService ormService;

    @Autowired
    BizFormService bizFormService;
    /**
     * 查询物品特征类列表接口
     * @param
     */
    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Result query() {
        logger.info("查询物品特征类列表接口开始服务......");
        Result result = new Result();
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID + "," + GoodsfeatureProperty.GOFT_SOURCE + ","
                    + GoodsfeatureProperty.GOFT_NAME + "," + GoodsfeatureProperty.GOFT_ISMULT + ","
                    + GoodsfeatureProperty.GOFT_TYPE + "," + GoodsfeatureProperty.GOFT_SEQ + ","
                    + "cretime," + "creuser,modtime,moduser");
            ormParam.addOrderExpElement(SQLSortEnum.ASC, "goft_seq");
            List<GoodsfeatureEntity> list = ormService.selectBeanList(GoodsfeatureEntity.class, ormParam);
            JSONArray array = new JSONArray();
            //将“数据来源”字段对应的枚举id转成枚举的具体名称
            if(!list.isEmpty()) {
                for(GoodsfeatureEntity gfe:list) {
                    JSONObject object = new JSONObject();
                    GoodsFeatureDTO gfd = new GoodsFeatureDTO();
                    OrmParam ormParamWord = new OrmParam();
                    ormParamWord.addColumn(EdmSysColumn.ID + "," + InformationProperty.INFO_NAME + ","
                            + InformationProperty.INFO_DESC + "," + WordlistProperty.WORD_NAME);
                    ormParamWord.setWhereExp(ormParamWord.getEqualXML(NodeConstant.ID, gfe.getGoft_source()));
                    List<WordlistEntity> wordList = ormService.selectBeanList(WordlistEntity.class, ormParamWord);
                    if(!wordList.isEmpty()) {
                        gfd.setGoftSource(wordList.get(0).getInfo_desc());
                        gfd.setGoftSourceName(wordList.get(0).getWord_name());
                    }
                    //Entity转DTO
                    gfd.setId(gfe.getId());
                    gfd.setGoftName(gfe.getGoft_name());
                    gfd.setGoftIsmult(gfe.getGoft_ismult());
                    gfd.setGoftType(gfe.getGoft_type());
                    gfd.setGoftSeq(gfe.getGoft_seq());
                    gfd.setCretime(NullUtils.valueOf(gfe.getCretime()));
                    gfd.setCreuser(gfe.getCreuser());
                    gfd.setModtime(NullUtils.valueOf(gfe.getModtime()));
                    gfd.setModuser(gfe.getModuser());

                   JSONObject o1=JSONObject.parseObject(JSONObject.toJSONString(gfd));
                   object.putAll(o1);
                   JSONObject creO = loadEmpNameNo(gfe.getCreuser());
                   object.put("creuserNo",creO.getString(EmployeeProperty.REMP_NO));
                   object.put("creuserName",creO.getString(EmployeeProperty.REMP_NAME));
                    JSONObject modO = loadEmpNameNo(gfe.getModuser());
                    object.put("moduserNo",modO.getString(EmployeeProperty.REMP_NO));
                    object.put("moduserName",modO.getString(EmployeeProperty.REMP_NAME));
                    array.add(object);
                }
            }
            JsonUtils.underLine2Camel(array);
            result.setData(array);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_QUERY + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_QUERY + e.getMessage());
        }
        return result;
    }

    private JSONObject loadEmpNameNo(String empId) throws Exception {
        JSONObject jsonObject = new JSONObject();
        if(!StringUtil.isNullOrEmpty(empId)){
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EmployeeProperty.REMP_NAME).addColumn(EmployeeProperty.REMP_NO);
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, empId)));
            List<Map<String, Object>> queryList = ormService.selectMapList(EmployeeEntity.class, ormParam);
            if (null != queryList && queryList.size() > 0) {
                Map<String, Object> map = queryList.get(0);
                jsonObject.put(EmployeeProperty.REMP_NAME,NullUtils.valueOf(map.get(EmployeeProperty.REMP_NAME)));
                jsonObject.put(EmployeeProperty.REMP_NO,NullUtils.valueOf(map.get(EmployeeProperty.REMP_NO)));
            }else{
                jsonObject.put(EmployeeProperty.REMP_NAME,"");
                jsonObject.put(EmployeeProperty.REMP_NO,"");
            }
        }else{
            jsonObject.put(EmployeeProperty.REMP_NAME,"");
            jsonObject.put(EmployeeProperty.REMP_NO,"");
        }
        return jsonObject;
    }

    /**
     * 根据物品特性类id加载物品特征信息接口
     * @param id 物品特性类id
     */
    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Result load(String id) {
        logger.info("根据物品特性类id: "+ id +"加载物品特征信息接口开始服务......");
        Result result = new Result();
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID + "," + GoodsfeatureProperty.GOFT_NAME + ","
                    + GoodsfeatureProperty.GOFT_TYPE + "," + GoodsfeatureProperty.GOFT_SOURCE + ","
                    + GoodsfeatureProperty.GOFT_ISMULT + "," + GoodsfeatureProperty.GOFT_SEQ + ","
                    + "cretime," + "creuser");
            ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, id));
            List<GoodsfeatureEntity> list = ormService.selectBeanList(GoodsfeatureEntity.class, ormParam);
            GoodsFeatureDTO gfd = new GoodsFeatureDTO();
            if(!list.isEmpty()) {
                GoodsfeatureEntity gfe = list.get(0);
                gfd.setId(gfe.getId());
                gfd.setGoftName(gfe.getGoft_name());
                gfd.setGoftSource(gfe.getGoft_source());
                //获取枚举名称
                OrmParam ormParamWord = new OrmParam();
                ormParamWord.addColumn(EdmSysColumn.ID + "," + WordlistProperty.WORD_NAME);
                ormParamWord.setWhereExp(ormParamWord.getEqualXML(NodeConstant.ID, gfe.getGoft_source()));
                List<WordlistEntity> wordList = ormService.selectBeanList(WordlistEntity.class, ormParamWord);
                if(!wordList.isEmpty()) {
                    gfd.setGoftSourceName(wordList.get(0).getWord_name());
                }
                gfd.setGoftIsmult(gfe.getGoft_ismult());
                gfd.setGoftType(gfe.getGoft_type());
                gfd.setGoftSeq(gfe.getGoft_seq());
                gfd.setCretime(gfe.getCretime().toString());
                gfd.setCreuser(gfe.getCreuser());
            }
            result.setData(gfd);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_LOAD + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_LOAD + e.getMessage());
        }
        return result;
    }

    /**
     * 新增或者更新物品特征信息接口
     * @param goodsFeatureDTO
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Result save(GoodsFeatureDTO goodsFeatureDTO) {
        logger.info("保存物品特征信息接口开始服务......");
        Result result = new Result();

        try {
            JSONObject param = JSONObject.parseObject(JSON.toJSONString(goodsFeatureDTO));
            GoodsfeatureEntity goodsfeatureEntity = JSONObject.parseObject(JSONObject.toJSONString(param), GoodsfeatureEntity.class);
            String id = goodsfeatureEntity.getId();
            CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
            if(StringUtil.isNullOrEmpty(id)) {
                //新增的情况
                //设置下排序字段值goft_seq
                int maxGoftSeq = getMaxGoftSeq();
                goodsfeatureEntity.setGoft_seq(maxGoftSeq+1);
                //企业对象
                goodsfeatureEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
                //对象类
                goodsfeatureEntity.setEdmd_class("goodsfeature");
                goodsfeatureEntity.setCretime(new Date());
                goodsfeatureEntity.setCreuser(sessionEntity.getEmployeeId());
                goodsfeatureEntity.setModtime(goodsfeatureEntity.getCretime());
                goodsfeatureEntity.setModuser(sessionEntity.getEmployeeId());
                ormService.insertSelective(goodsfeatureEntity);
            }else {
                //更新的情况
                goodsfeatureEntity.setModtime(new Date());
                goodsfeatureEntity.setModuser(sessionEntity.getEmployeeId());
                ormService.updateSelective(goodsfeatureEntity);
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_SAVE + ":" +e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_SAVE + e.getMessage());
        }
        return result;
    }

    private int getMaxGoftSeq() {
        int maxGoftSeq = 0;
        try{
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID + "," + GoodsfeatureProperty.GOFT_SEQ);
            List<GoodsfeatureEntity> goodsfeatureList = ormService.selectBeanList(GoodsfeatureEntity.class, ormParam);
            if(!goodsfeatureList.isEmpty()) {
                for(GoodsfeatureEntity gfe:goodsfeatureList) {
                    if(gfe.getGoft_seq() > maxGoftSeq) {
                        maxGoftSeq = gfe.getGoft_seq();
                    }
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return maxGoftSeq;
    }

    /**
     * @ 根据物品特征id集合删除物品特征信息
     * @param ids 物品特征id集合
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Result delete(List<String> ids) {
        logger.info("根据物品特征id集合删除物品特征信息接口开始服务......");
        Result result = new Result();
        if(ids.size()>0) {
            for(int i=0; i<ids.size(); i++) {
                try {
                    OrmParam ormParamDel = new OrmParam();
                    ormParamDel.setWhereExp(ormParamDel.getEqualXML(NodeConstant.ID, ids.get(i)));
                    ormService.delete(GoodsfeatureEntity.class, ormParamDel);
                }catch (Exception e) {
                    e.printStackTrace();
                    logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_DELETE + ":" + e.getMessage());
                    throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_DELETE + e.getMessage());
                }
            }
        }
        return result;
    }

    /**
     * 验证物品特征名称唯一性
     * @param goftName 物品特征名称
     */
    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Result check(String goftName) {
        logger.info("验证物品特征名称唯一性接口开始服务......");
        Result result = new Result();
        result.setData("isok");
        if(StringUtil.isNullOrEmpty(goftName)) {
            result.setData("fail");
        }else {
            try {
                OrmParam ormParam = new OrmParam();
                ormParam.addColumn(EdmSysColumn.ID);
                ormParam.setWhereExp(ormParam.getEqualXML(GoodsfeatureProperty.GOFT_NAME, goftName));
                List<GoodsfeatureEntity> goodsfeatureList = ormService.selectBeanList(GoodsfeatureEntity.class, ormParam);
                if(!goodsfeatureList.isEmpty()) {
                    result.setData("fail");
                }
            }catch (Exception e) {
                e.printStackTrace();
                logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_CHECK + ":" + e.getMessage());
                throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_CHECK + e.getMessage());
            }
        }
        return result;
    }

    /**
     * 根据枚举名称查询枚举列表方法
     * @param wordName 枚举名称
     */
    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Result queryWordList(String wordName) {
        logger.info("根据枚举名称查询枚举列表接口开始服务......");
        Result result = new Result();
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID + "," + WordlistProperty.WORD_NAME);
        ormParam.setWhereExp(ormParam.getEqualXML(WordlistProperty.WORD_SEQ, 0));
        List<GoodsFeaDTO> goodsFeaDTO = new ArrayList<>();
        try{
            if(!"".equals(wordName) && wordName != null) {
                //根据枚举名称模糊查询枚举id和名称，否则就是查询全部枚举id和名称
                ormParam.setWhereExp(OrmParam.and(ormParam.getWhereExp(),
                        ormParam.getMatchMiddleXML(WordlistProperty.WORD_NAME, wordName)));
            }
            List<WordlistEntity> wordList = ormService.selectBeanList(WordlistEntity.class, ormParam);
            if(!wordList.isEmpty()) {
                for(WordlistEntity wle:wordList) {
                    GoodsFeaDTO gfd = new GoodsFeaDTO();
                    gfd.setId(wle.getId());
                    gfd.setGodsWordListName(wle.getWord_name());
                    goodsFeaDTO.add(gfd);
                }
            }
            result.setData(goodsFeaDTO);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_WORDNAME + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_WORDNAME + e.getMessage());
        }
        return result;
    }


    /**
     * 上移下移
     * @param ids 物品特征id集合
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Result move(List<String> ids) {
        logger.info("上移下移接口开始服务......");
        Result result = new Result();
        try{
            int seq = 1;
            for(String id:ids) {
                OrmParam ormParam = new OrmParam();
                ormParam.addColumn(EdmSysColumn.ID + "," + GoodsfeatureProperty.GOFT_SEQ);
                ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, id));
                List<GoodsfeatureEntity> goodsfeatureList = ormService.selectBeanList(GoodsfeatureEntity.class, ormParam);
                if(!goodsfeatureList.isEmpty()) {
                    GoodsfeatureEntity gfe = goodsfeatureList.get(0);
                    gfe.setGoft_seq(seq);
                    ormService.update(gfe);
                    seq++;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_MOVE + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSFEA_MOVE + e.getMessage());
        }

        return result;
    }


}
