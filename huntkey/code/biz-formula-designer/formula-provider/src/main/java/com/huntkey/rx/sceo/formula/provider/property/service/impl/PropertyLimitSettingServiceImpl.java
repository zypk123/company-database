package com.huntkey.rx.sceo.formula.provider.property.service.impl;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.model.*;
import com.huntkey.rx.sceo.formula.common.model.vo.PropLimitCndtVo;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.provider.engine.feign.BizModelerService;
import com.huntkey.rx.sceo.formula.provider.engine.service.FormulaEngineService;
import com.huntkey.rx.sceo.formula.provider.property.dao.TplPropertyLimitMapper;
import com.huntkey.rx.sceo.formula.provider.property.service.PropertyLimitSettingService;
import com.huntkey.rx.sceo.formula.provider.record.service.RecordMappingService;
import com.huntkey.rx.sceo.formula.provider.related.classes.dao.TplConditionMapper;
import com.huntkey.rx.sceo.formula.provider.variant.service.VariantMgrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 属性限值Service接口实现类
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 * @modify  by nidongxu  on 2017-06-19
 **/
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class PropertyLimitSettingServiceImpl<M extends TplPropertyLimit> extends BaseService<M> implements PropertyLimitSettingService {

    private Logger logger = LoggerFactory.getLogger(PropertyLimitSettingServiceImpl.class);

    @Autowired
    TplPropertyLimitMapper tplPropertyLimitMapper;
    @Autowired
    TplConditionMapper tplConditionMapper;
    @Autowired
    private FormulaEngineService formulaEngineService;
    @Autowired
    PropertyLimitSettingServiceImpl propertyLimitSettingServiceImpl;
    @Autowired
    private VariantMgrService variantMgrService;
    @Autowired
    private RecordMappingService recordMappingService;
    @Autowired
    private BizModelerService bizModelerService;

    /**
     * 根据属性限值编号prprId 查询所有关联条件
     * 属性或者相关类编号cndt_prop_clss_id   是条件表的外键，故根据相关类id的查询方法，当传入prprId可获取所有属性的条件。
     * @param prprId
     * @return
     */
    @Override
    public List<TplCondition> queryAllConditions(String prprId) {
        List<TplCondition> list =tplConditionMapper.selectByClssId(prprId);
        return list;
    }

    /**
     * 新增属性限值关联条件
     * @param tplCondition
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveCondition(TplCondition tplCondition) {
        propertyLimitSettingServiceImpl.saveSetting(tplCondition);
        return tplConditionMapper.insertSelective(tplCondition);
    }

    /**
     * 更新属性限值关联条件
     * @param tplCondition
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updateCondition(TplCondition tplCondition) {
        propertyLimitSettingServiceImpl.updateSetting(tplCondition);
        return tplConditionMapper.updateByPrimaryKeySelective(tplCondition);
    }

    /**
     * 删除一条关联条件
     * @param cndtId
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int removeCondition(String cndtId) {
        return tplConditionMapper.updateIsenableByPrimaryKey(cndtId);
    }

    /**
     * 新增属性限值
     * @param tplPropertyLimit
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int savePropLimit(TplPropertyLimit tplPropertyLimit) {
        propertyLimitSettingServiceImpl.saveSetting(tplPropertyLimit);
        return tplPropertyLimitMapper.insertSelective(tplPropertyLimit);
    }

    /**
     * 更新属性限值
     * @param tplPropertyLimit
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updatePropLimit(TplPropertyLimit tplPropertyLimit) {
        propertyLimitSettingServiceImpl.updateSetting(tplPropertyLimit);
        return tplPropertyLimitMapper.updateByPrimaryKeySelective(tplPropertyLimit);
    }

    /**
     * 根据属性限值标号prprId查询属性限值
     * @param prprId
     * @return
     */
    @Override
    public TplPropertyLimit getPropLimitById(String prprId) {
        return tplPropertyLimitMapper.selectByPrimaryKey(prprId);
    }

    /**
     * 根据属性限值标号prprId 查询属性限值及关联条件
     * @param prprId
     * @return
     */
    @Override
    public PropLimitCndtVo queryLimitAndConditions(String prprId) {
        PropLimitCndtVo vo = new PropLimitCndtVo();
        vo.setTplPropertyLimit(getPropLimitById(prprId));
        vo.setTplConditions(queryAllConditions(prprId));
        return vo;
    }

    @Override
    public Map<String, Object> queryFormulasAndPropertyLimits(List<String> prprIds, List<String> variantIds) {
        Map<String,Object> retMap = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(variantIds)&&variantIds.size()>0){
            List<TvmVariant> variants = variantMgrService.getVariantByIds(variantIds);
            Map<String,String> retMapFormulas = new HashMap<String, String>();
            if(!StringUtil.isNullOrEmpty(variants) && variants.size()>0){
                List<String> formulaIds = new ArrayList<String>();
                Map<String,String> variantIdMapFormulaId = new HashMap<String, String>();
                for(TvmVariant variant : variants){
                    formulaIds.add(variant.getVrntFormulaId());
                    variantIdMapFormulaId.put(variant.getVrntFormulaId(), variant.getVrntId());
                }
                List<TfdFormula> tfdFormulas = formulaEngineService.getFormulasByids(formulaIds);
                for(TfdFormula tfdFormula : tfdFormulas){
                    retMapFormulas.put(variantIdMapFormulaId.get(tfdFormula.getFrmuId()),tfdFormula.getFrmuFormulaText());
                }
            }
            retMap.put("propertyFormulas",retMapFormulas);
        }
        if(!StringUtil.isNullOrEmpty(prprIds)&&prprIds.size()>0){
            List<TplPropertyLimit> tplPropertyLimits = getPropertyLimits(prprIds);
            Map<String,String> retMapPropertyLimits = new HashMap<String, String>();
            Map<String,String> retMapPropertyDescs = new HashMap<String, String>();
            for(TplPropertyLimit tplPropertyLimit : tplPropertyLimits){
                retMapPropertyLimits.put(tplPropertyLimit.getPrprId(),tplPropertyLimit.getPrprConditionName());
                retMapPropertyDescs.put(tplPropertyLimit.getPrprId(),tplPropertyLimit.getPrprConditionDesc());
            }
            retMap.put("propertyLimitNames",retMapPropertyLimits);
            retMap.put("propertyLimitDescs",retMapPropertyDescs);
        }
        return retMap;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Result deletePropLimit(String prprId) {
        Result result = new Result();
        int retInt = Result.RECODE_SUCCESS;
        try {
            beforeDeletePropLimit(prprId);
        }catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            logger.error("modeler 删除属性公式 错误：", e);
            return result;
        }
        retInt = tplPropertyLimitMapper.deleteByPrimaryKey(prprId);
        result.setRetCode(retInt);
        return result;
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    private void beforeDeletePropLimit(String prprId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("edmpValueLimit",prprId);
        Result result = bizModelerService.deleteLimitOrFormula(map);
        if(!Result.RECODE_SUCCESS.equals(result.getRetCode())){
            throw new RuntimeException("modeler接口/v1/properties/limitAndFormula调用错误： 删除属性限值失败");
        }
        deleteConditionByPrprId(prprId);
        recordMappingService.delRecordMapping(prprId);
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public void deleteConditionByPrprId(String prprId) {
        tplConditionMapper.updateIsenableByPropClssId(prprId);
    }


    /**
     *@desc  根据属性限值ids查询属性限值
     *@pars [prprIds] id数组
     *@date 2017/7/15 0015 上午 11:12 lulx
     *@return java.util.List<com.huntkey.rx.sceo.formula.common.model.TplPropertyLimit>
     **/
    private List<TplPropertyLimit> getPropertyLimits(List<String> prprIds) {
        TplPropertyLimitExample example = new TplPropertyLimitExample();
        TplPropertyLimitExample.Criteria criteria = example.createCriteria();
        criteria.andPrprIdIn(prprIds);
        return tplPropertyLimitMapper.selectByExample(example);
    }

    /**
     * 保存属性限值及关联条件
     * @param vo
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdateLimitAndConditions(PropLimitCndtVo vo) {
        int retIntSaveLimit = -1;
        int retIntSaveCndt = -1;
        retIntSaveLimit =  updatePropLimit(vo.getTplPropertyLimit());
        for (TplCondition tplCondition: vo.getTplConditions()) {
            retIntSaveCndt = saveOrUpdateCondition(tplCondition);
        }
        if(retIntSaveLimit == 1 && retIntSaveCndt == 1){
            return 1;
        }else {
            return -1;
        }
    }

    /**
     * 新增/更新属性限值关联条件
     * @param tplCondition
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdateCondition(TplCondition tplCondition) {
        String cndtId = tplCondition.getCndtId();
        int retInt = -1;
        //如果id不为空，就是修改
        if (!StringUtil.isNullOrEmpty(cndtId)) {
            tplCondition.setModtime(new Date());
            tplCondition.setModuser(Constant.MODUSER);
            retInt = tplConditionMapper.updateByPrimaryKeySelective(tplCondition);
        } else { // 新增
            tplCondition.setCndtId(UuidCreater.uuid());
            tplCondition.setAddtime(new Date());
            tplCondition.setIsenable((byte) 1);
            tplCondition.setAdduser(Constant.ADDUSER);
            tplCondition.setModtime(new Date());
            tplCondition.setModuser(Constant.MODUSER);
            retInt = tplConditionMapper.insertSelective(tplCondition);
        }
        return retInt;
    }

    /**
     * 新增/更新属性限值
     * @param tplPropertyLimit
     * @return
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdatePropLimit(TplPropertyLimit tplPropertyLimit) {
        String prprId = tplPropertyLimit.getPrprId();
        int retInt = -1;
        //如果id不为空，就是修改
        if (!StringUtil.isNullOrEmpty(prprId)) {
            tplPropertyLimit.setModtime(new Date());
            tplPropertyLimit.setModuser(Constant.MODUSER);
            retInt = tplPropertyLimitMapper.updateByPrimaryKeySelective(tplPropertyLimit);
        } else { // 新增
            tplPropertyLimit.setPrprId(UuidCreater.uuid());
            tplPropertyLimit.setAddtime(new Date());
            tplPropertyLimit.setIsenable((byte) 1);
            tplPropertyLimit.setAdduser(Constant.ADDUSER);
            tplPropertyLimit.setModtime(new Date());
            tplPropertyLimit.setModuser(Constant.MODUSER);
            retInt = tplPropertyLimitMapper.insertSelective(tplPropertyLimit);
        }
        return retInt;
    }

}
