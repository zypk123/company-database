package com.huntkey.rx.sceo.formula.provider.variant.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.constant.FormulaCode;
import com.huntkey.rx.sceo.formula.common.model.*;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.common.util.Scope;
import com.huntkey.rx.sceo.formula.common.util.State;
import com.huntkey.rx.sceo.formula.common.util.StringUtils;
import com.huntkey.rx.sceo.formula.provider.engine.dao.TfdFormulaMapper;
import com.huntkey.rx.sceo.formula.provider.engine.feign.BizModelerService;
import com.huntkey.rx.sceo.formula.provider.engine.service.FormulaEngineService;
import com.huntkey.rx.sceo.formula.provider.record.service.RecordMappingService;
import com.huntkey.rx.sceo.formula.provider.related.classes.dao.TfdClassRelatedMapper;
import com.huntkey.rx.sceo.formula.provider.related.classes.dao.TplConditionMapper;
import com.huntkey.rx.sceo.formula.provider.variant.dao.TvmLocaleVariantMappingMapper;
import com.huntkey.rx.sceo.formula.provider.variant.dao.TvmSystemVariantMappingMapper;
import com.huntkey.rx.sceo.formula.provider.variant.dao.TvmVariantMapper;
import com.huntkey.rx.sceo.formula.provider.variant.service.VariantMgrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author lulx on 2017/6/15 0015.
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class VariantMgrServiceImpl extends BaseService<TvmVariant> implements VariantMgrService {

    private final Logger log = LoggerFactory.getLogger(VariantMgrServiceImpl.class);

    @Autowired
    private TvmVariantMapper tvmVariantMapper;

    @Autowired
    private TvmSystemVariantMappingMapper tvmSystemVariantMappingMapper;

    @Autowired
    private TvmLocaleVariantMappingMapper tvmLocaleVariantMappingMapper;

    @Autowired
    private TfdClassRelatedMapper tfdClassRelatedMapper;

    @Autowired
    TplConditionMapper tplConditionMapper;

    @Autowired
    TfdFormulaMapper tfdFormulaMapper;

    @Autowired
    private FormulaEngineService formulaEngineService;

    @Autowired
    private RecordMappingService recordMappingService;

    @Autowired
    private BizModelerService bizModelerService;

    @Override
    public TvmVariant getVariantByName(String varName) {
        List<TvmVariant> list = getVariantListByName(varName);
        if(!StringUtil.isNullOrEmpty(list) && list.size()>0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public List<TvmVariant> getVariantListByName(String varName) {
        TvmVariantExample tvmVariantExample = new TvmVariantExample();
        TvmVariantExample.Criteria criteria = tvmVariantExample.createCriteria();
        criteria.andVrntVarNameEqualTo(varName);
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        criteria.andVrntVarScopeEqualTo(Scope.VARIANT_SCOPE_SYSTEM.getScope());
        List<TvmVariant> list = tvmVariantMapper.selectByExample(tvmVariantExample);
        return list;
    }


    @Override
    public TvmVariant getVariantById(String varId) {
        return tvmVariantMapper.selectByPrimaryKey(varId);
    }

    @Override
    public List<TvmVariant> getByFormulaId(String formulaId, String varScope) {
        List<TvmVariant> list = new ArrayList<TvmVariant>();
        if (Scope.VARIANT_SCOPE_LOCAL.getScope().equalsIgnoreCase(varScope)) {
            list = getLocVarByFormulaId(formulaId, varScope, "");
        } else if (Scope.VARIANT_SCOPE_SYSTEM.getScope().equalsIgnoreCase(varScope)) {
            list = getSysVarByFormulaId(formulaId, varScope);
        }
        return list;
    }

    private List<TvmVariant> getLocVarByFormulaId(String formulaId, String varScope, String varName) {
        //获取公式编号与局部变量的映射关系
        List<TvmLocaleVariantMapping> tvmLocaleVariantMappingList = getLocVariantMappingByVarIdOrFormulaId("",formulaId,Constant.ISENABLE_YSE);
        List<String> listStr = new ArrayList<String>();
        for (TvmLocaleVariantMapping tvmLocaleVariantMapping : tvmLocaleVariantMappingList) {
            listStr.add(tvmLocaleVariantMapping.getLclvVarId());
        }
        if (listStr.isEmpty()) {
            return new ArrayList<TvmVariant>();
        }
        //查询局部变量
        TvmVariantExample tvmVariantExample = new TvmVariantExample();
        TvmVariantExample.Criteria tvmVariantExampleCriteria = tvmVariantExample.createCriteria();
        tvmVariantExampleCriteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        tvmVariantExampleCriteria.andVrntVarScopeEqualTo(varScope);
        if(!StringUtil.isNullOrEmpty(varName)){
            tvmVariantExampleCriteria.andVrntVarNameEqualTo(varName);
        }
        tvmVariantExampleCriteria.andVrntIdIn(listStr);
        List<TvmVariant> list = tvmVariantMapper.selectByExample(tvmVariantExample);
        return list;
    }

    private List<TvmVariant> getSysVarByFormulaId(String formulaId, String varScope) {
        //查询系统变量
        TvmVariantExample tvmVariantExample = new TvmVariantExample();
        TvmVariantExample.Criteria tvmVariantExampleCriteria = tvmVariantExample.createCriteria();
        tvmVariantExampleCriteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        tvmVariantExampleCriteria.andVrntVarScopeEqualTo(varScope);
        tvmVariantExampleCriteria.andVrntStateEqualTo(State.VARIANT_STATE_INUSING.getState());
        List<TvmVariant> list = tvmVariantMapper.selectByExample(tvmVariantExample);
        return list;
    }

    @Override
    public List<TvmVariant> loadSystemVariants(String varName) {
        TvmVariantExample tvmVariantExample = new TvmVariantExample();
        TvmVariantExample.Criteria criteria = tvmVariantExample.createCriteria();
        if (!StringUtil.isNullOrEmpty(varName)) {
            // 可采用模糊查询
            varName = "%" + varName.trim() + "%";
            criteria.andVrntVarNameLike(varName);
        }
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        criteria.andVrntVarScopeEqualTo(Scope.VARIANT_SCOPE_SYSTEM.getScope());
        List<TvmVariant> list = tvmVariantMapper.selectByExample(tvmVariantExample);
        return list;
    }

    @Override
    public List<TvmVariant> loadSystemVariantsWithFormula(String formulaId) {
        TvmVariantExample tvmVariantExample = new TvmVariantExample();
        TvmVariantExample.Criteria criteria = tvmVariantExample.createCriteria();
        if (!StringUtil.isNullOrEmpty(formulaId)) {
            criteria.andFormulaIdEqualTo(formulaId);
        }
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        criteria.andVrntVarScopeEqualTo(Scope.VARIANT_SCOPE_SYSTEM.getScope());
        criteria.andVrntStateEqualTo(State.VARIANT_STATE_INUSING.getState());
        List<TvmVariant> list = tvmVariantMapper.selectByExample(tvmVariantExample);
        return list;
    }

    @Override
    public List<TvmVariant> loadLocalVariantsWithFormula(String formulaId) {
        return getByFormulaId(formulaId, Scope.VARIANT_SCOPE_LOCAL.getScope());
    }

    @Override
    public List<TvmVariant> queryVariants(String varName, String state) {
        TvmVariantExample tvmVariantExample = new TvmVariantExample();
        TvmVariantExample.Criteria criteria = tvmVariantExample.createCriteria();
        if (!StringUtil.isNullOrEmpty(varName)) {
            criteria.andVrntVarNameEqualTo(varName);
        }
        if (!StringUtil.isNullOrEmpty(state)) {
            criteria.andVrntStateEqualTo(state);
        }
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        criteria.andVrntStateEqualTo(State.VARIANT_STATE_INUSING.getState());
        criteria.andVrntVarScopeEqualTo(Scope.VARIANT_SCOPE_SYSTEM.getScope());
        List<TvmVariant> list = tvmVariantMapper.selectByExample(tvmVariantExample);
        return list;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Result removeVariant(String varId) {
        Result result = new Result();
        int retInt = Result.RECODE_SUCCESS;
        TvmVariant tvmVariant = tvmVariantMapper.selectByPrimaryKey(varId);
        if (!StringUtil.isNullOrEmpty(tvmVariant)) {
            // 变量公式
            if(StringUtil.isNullOrEmpty(tvmVariant.getVrntVarScope())){
                try {
                    beforeDeleteVariantFormula(varId);
                }catch (Exception e){
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg(e.getMessage());
                    log.error("modeler 删除属性公式 错误：", e);
                    return result;
                }
            }
            tvmVariant.setIsenable(Constant.ISENABLE_NO);
            tvmVariant.setModtime(new Date());
            tvmVariant.setModuser(Constant.MODUSER);
            retInt = tvmVariantMapper.updateByPrimaryKey(tvmVariant);
        }
        result.setRetCode(retInt);
        return result;
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    private void beforeDeleteVariantFormula(String varId) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("edmpFormula",varId);
        Result result = bizModelerService.deleteLimitOrFormula(map);
        if(!Result.RECODE_SUCCESS.equals(result.getRetCode())){
            throw new RuntimeException("modeler接口/v1/properties/limitAndFormula调用错误： 删除公式失败");
        }
        recordMappingService.delRecordMapping(varId);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updateVariant(TvmVariant variant) {
        updateSetting(variant);
        if(Scope.VARIANT_SCOPE_LOCAL.getScope().equals(variant.getVrntVarScope())){
            beforeUpdateLocVar(variant);
        }else if (Scope.VARIANT_SCOPE_SYSTEM.getScope().equals(variant.getVrntVarScope())){
            // do nothing
        }
        return tvmVariantMapper.updateByPrimaryKeySelective(variant);
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    private void beforeUpdateLocVar(TvmVariant variant) {
        String vaiId = variant.getVrntId();
        List<TvmLocaleVariantMapping> list = getLocVariantMappingByVarIdOrFormulaId(vaiId,"",Constant.ISENABLE_TEMP);
        for(TvmLocaleVariantMapping localeVariantMapping : list){
            localeVariantMapping.setIsenable(Constant.ISENABLE_YSE);
            tvmLocaleVariantMappingMapper.updateByPrimaryKeySelective(localeVariantMapping);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public String saveVariant(TvmVariant variant) {
        saveSetting(variant);
        tvmVariantMapper.insertSelective(variant);
        return variant.getVrntId();
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int disableVariant(String varId) {
        int retInt = Result.RECODE_ERROR;
        TvmVariant tvmVariant = tvmVariantMapper.selectByPrimaryKey(varId);
        if (!StringUtil.isNullOrEmpty(tvmVariant)) {
            tvmVariant.setVrntState(State.VARIANT_STATE_PROHIBIT.getState());
            tvmVariant.setModtime(new Date());
            tvmVariant.setModuser(Constant.MODUSER);
            retInt = tvmVariantMapper.updateByPrimaryKey(tvmVariant);
        }
        return retInt;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdateLocalVariantsWithFormula(String[] localVarIdArr, String formulaId) {
        int retInt = 0;
        TvmLocaleVariantMapping tvmLocaleVariantMapping = new TvmLocaleVariantMapping();
        tvmLocaleVariantMapping.setIsenable(Constant.ISENABLE_YSE);
        tvmLocaleVariantMapping.setAddtime(new Date());
        tvmLocaleVariantMapping.setAdduser(Constant.ADDUSER);
        tvmLocaleVariantMapping.setModtime(new Date());
        tvmLocaleVariantMapping.setModuser(Constant.MODUSER);
        for (String localVarId : localVarIdArr) {
            tvmLocaleVariantMapping.setLclvId(UuidCreater.uuid());
            tvmLocaleVariantMapping.setLclvFormulaId(formulaId);
            tvmLocaleVariantMapping.setLclvVarId(localVarId);
            tvmLocaleVariantMappingMapper.insertSelective(tvmLocaleVariantMapping);
            retInt++;
        }
        return retInt;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdateSystemVariantsWithFormula(String[] systemVarIdArr, String formulaId) {
        int retInt = 0;
        TvmSystemVariantMapping tvmSystemVariantMapping = new TvmSystemVariantMapping();
        tvmSystemVariantMapping.setIsenable(Constant.ISENABLE_YSE);
        tvmSystemVariantMapping.setAddtime(new Date());
        tvmSystemVariantMapping.setAdduser(Constant.ADDUSER);
        tvmSystemVariantMapping.setModtime(new Date());
        tvmSystemVariantMapping.setModuser(Constant.MODUSER);
        for (String systemVarId : systemVarIdArr) {
            tvmSystemVariantMapping.setSystId(UuidCreater.uuid());
            tvmSystemVariantMapping.setSystFormulaId(formulaId);
            tvmSystemVariantMapping.setSystVarId(systemVarId);
            tvmSystemVariantMappingMapper.insertSelective(tvmSystemVariantMapping);
            retInt++;
        }
        return retInt;
    }

    @Override
    public Pagination<TvmVariant> querySystemVariants(String varName, String varStatus, String sortName, String sortOrder, int pageNum, int pageSize) {
        TvmVariantExample tvmVariantExample = new TvmVariantExample();
        TvmVariantExample.Criteria criteria = tvmVariantExample.createCriteria();

        if (!StringUtil.isNullOrEmpty(varName)) {
            // 可采用模糊查询
            varName = "%" + varName.trim() + "%";
            criteria.andVrntVarNameLike(varName);
        }

        if (!StringUtil.isNullOrEmpty(varStatus)) {
            criteria.andVrntStateEqualTo(varStatus);
        }

        if (!StringUtil.isNullOrEmpty(sortName) && !StringUtil.isNullOrEmpty(sortOrder)) {
            tvmVariantExample.setOrderByClause(StringUtils.underscoreName(sortName) + " " + sortOrder);
        }
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        criteria.andVrntVarScopeEqualTo(Scope.VARIANT_SCOPE_SYSTEM.getScope());

        Page page = PageHelper.startPage(pageNum, pageSize);

        List<TvmVariant> list = tvmVariantMapper.selectByExample(tvmVariantExample);
        Pagination<TvmVariant> pageInfo = new Pagination<TvmVariant>(list, pageNum, pageSize, page.getTotal());
        return pageInfo;
    }

    @Override
    public String existLocalVar(String varId) {
        String errMsg = null;
        // 获取该变量的名称
        String varName = tvmVariantMapper.getVarNameByVrntId(varId);
        // 通过待删除的变量ID得到它所在的公式ID
        String formualId = tvmVariantMapper.getFormualIdByVrntId(varId);
        // 通过公式ID的到它所有的局部变量的ID集合，除去自身
        List<String> localVarIdList = tvmVariantMapper.getLocalVarIdByFormualIdExceptSelf(formualId, varId);
        for (String localVarId : localVarIdList) {
            // 遍历局部变量ID得到公式ID
            String formualId1 = tvmVariantMapper.getFormualIdByVrntId(localVarId);
            // 通过公式ID的得到公式内容
            String formulaContent = tfdFormulaMapper.getFormulaContentByFormulaId(formualId1);
            // 如果内容中包含该变量的变量名，则说明被引用
            if (formulaContent.contains(varName)) {
                // 得到变量名称
                String localVarName = tvmVariantMapper.getVarNameByVrntId(localVarId);
                errMsg = "变量删除校验失败，变量" + localVarName + "引用了该变量！";
                return errMsg;
            }
        }
        return errMsg;
    }

    @Override
    public String existRelatedClass(String varId) {
        String errMsg = null;
        // 获取该变量的名称
        String varName = tvmVariantMapper.getVarNameByVrntId(varId);
        // 通过待删除的变量ID得到它所在的公式ID
        String formualId = tvmVariantMapper.getFormualIdByVrntId(varId);
        // 通过公式ID得到相关类id集合
        List<String> classRelatedIdList = tfdClassRelatedMapper.getClassRelatedIdByFormulaId(formualId);
        for (String classRelatedId : classRelatedIdList) {
            // 得到相关类的条件列表
            List<TplCondition> tplConditionList = tplConditionMapper.selectByClssId(classRelatedId);
            for (TplCondition condition : tplConditionList) {
                // 如果条件中的值等于该变量的名称，则该变量被引用
                String cndtValue = condition.getCndtValue();
                if (cndtValue.equals(varName)) {
                    String classRelatedName = tfdClassRelatedMapper.getClassRelatedName(classRelatedId);
                    errMsg = "变量删除校验失败，相关类" + classRelatedName + "引用了该变量！";
                    return errMsg;
                }
            }
        }
        return errMsg;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public TvmVariant initByformulaId(String formulaId, String varType, String varName) {
        TvmVariant variant = new TvmVariant();
        variant.setVrntFormulaId(formulaId);
        variant.setAcctid(1);
        variant.setVrntVarType(varType);
        variant.setVrntVarName(varName);
        saveSetting(variant);
        tvmVariantMapper.insert(variant);
        return variant;
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public TvmVariant initLocVarByformulaId(String formulaId) {
        TvmVariant variant = new TvmVariant();
        variant.setVrntFormulaId(formulaId);
        variant.setAcctid(1);
        variant.setVrntVarScope(Scope.VARIANT_SCOPE_LOCAL.getScope());
        saveSetting(variant);
        tvmVariantMapper.insert(variant);
        return variant;
    }

    /**
     *@desc 生成一个暂存的局部变量与公式的映射关系
     *@pars [formulaId, initLocVarId]
     *@date 2017/7/6 0006 下午 3:41 lulx
     *@return com.huntkey.rx.sceo.formula.common.model.TvmLocaleVariantMapping
     **/
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public TvmLocaleVariantMapping initTempLocVarMap(String formulaId, String initLocVarId){
        TvmLocaleVariantMapping localeVariantMapping = new TvmLocaleVariantMapping();
        localeVariantMapping.setLclvFormulaId(formulaId);
        localeVariantMapping.setLclvVarId(initLocVarId);
        localeVariantMapping.setIsenable(Constant.ISENABLE_TEMP);
        localeVariantMapping.setAddtime(new Date());
        localeVariantMapping.setAdduser(Constant.ADDUSER);
        localeVariantMapping.setModtime(new Date());
        localeVariantMapping.setModuser(Constant.MODUSER);
        localeVariantMapping.setLclvId(UuidCreater.uuid());
        localeVariantMapping.setAcctid(1);
        tvmLocaleVariantMappingMapper.insert(localeVariantMapping);
        return localeVariantMapping;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Map<String,Object> initLocalVariant(String formulaId) {
        TvmLocaleVariantMapping localeVariantMapping = getTempLocalVarMapByformulaId(formulaId);
        TfdFormula initformula = null;
        TvmVariant initLocVar = null;
        if(StringUtil.isNullOrEmpty(localeVariantMapping)){
            // 初始化一个公式--公式ID-->初始化一个局部变量--传入的formulaId-->产生一个暂存的映射关系
            initformula = formulaEngineService.initNewFormula();
            initLocVar = initLocVarByformulaId(initformula.getFrmuId());
        }else {
            String tempLocVarId = localeVariantMapping.getLclvVarId();
            initLocVar = getVariantById(tempLocVarId);
            String tempformulaId = initLocVar.getVrntFormulaId();
            initformula = formulaEngineService.getTfdFormulaById(tempformulaId);
        }
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("initformula",initformula);
        map.put("initLocVar",initLocVar);
        return map;
    }

    @Override
    public Result checkSysVarIsEnabled(String sysVarId) {
        Result result = new Result();
        TvmVariant variant = getVariantById(sysVarId);
        if(StringUtil.isNullOrEmpty(variant) || (!StringUtil.isNullOrEmpty(variant) &&
                (!Scope.VARIANT_SCOPE_SYSTEM.getScope().equalsIgnoreCase(variant.getVrntVarScope()) ||
                variant.getIsenable() != Constant.ISENABLE_YSE ))){
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("错误的系统变量编号");
            return result;
        }
        List<TvmSystemVariantMapping> systemVariantMapping = getSysVarMapBySysVarId(sysVarId);
        if(!StringUtil.isNullOrEmpty(systemVariantMapping) && systemVariantMapping.size() > 0){
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg("系统变量正在使用中！");
            return result;
        }
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public TvmVariant initSystVariant() {
        TvmVariant variant = getTempVariant();
        if(StringUtil.isNullOrEmpty(variant)) {
            variant = initTempSystVariant();
        }
        return variant;
    }

    @Override
    public Result checkVarVal(TvmVariant variant) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        if(StringUtil.isNullOrEmpty(variant) || StringUtil.isNullOrEmpty(variant.getVrntId())){
            result.setErrMsg("variant can't be null");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        TvmVariant variantDb = tvmVariantMapper.selectByPrimaryKey(variant.getVrntId());
        if(StringUtil.isNullOrEmpty(variantDb)){
            result.setErrMsg("variant can't be null");
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        String varScope = variantDb.getVrntVarScope();
        variant.setVrntVarScope(varScope);
        if(Scope.VARIANT_SCOPE_LOCAL.getScope().equals(variant.getVrntVarScope())){
            return checkLocVarVal(variant);
        }else if (Scope.VARIANT_SCOPE_SYSTEM.getScope().equals(variant.getVrntVarScope())){
            return checkSysVarVal(variant);
        }
        return result;
    }

    /**
     *@desc 校验系统变量值的合法性
     * 1、校验变量名是否重复
     *@pars [variant]
     *@date 2017/7/18 0018 上午 9:53 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    private Result checkSysVarVal(TvmVariant variant) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<TvmVariant> variants = getVariantListByName(variant.getVrntVarName());
        if(!StringUtil.isNullOrEmpty(variants)){
            if((variants.size() == 1 && !variants.get(0).getVrntId().equals(variant.getVrntId()))
                    || variants.size() > 1){
                result.setRetCode(FormulaCode.FORMULA_VALIDATE_ERR.getStateCode());
                result.setErrMsg("重复的变量名称");
                return result;
            }
        }
        return result;
    }

    /**
     *@desc 校验局部变量值得合法性
     * 1、变量名是否重复
     *@pars [variant]
     *@date 2017/7/18 0018 上午 9:54 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    private Result checkLocVarVal(TvmVariant variant) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<TvmLocaleVariantMapping> tvmLocaleVariantMappingList = getLocVariantMappingByVarIdOrFormulaId(variant.getVrntId(),null,null);
        String formulaId = !StringUtil.isNullOrEmpty(tvmLocaleVariantMappingList) && tvmLocaleVariantMappingList.size()>0 ? tvmLocaleVariantMappingList.get(0).getLclvFormulaId():"";
        List<TvmVariant> variants = getLocVarByFormulaId(formulaId,Scope.VARIANT_SCOPE_LOCAL.getScope(),variant.getVrntVarName());
        if(!StringUtil.isNullOrEmpty(variants)){
            if((variants.size() == 1 && !variants.get(0).getVrntId().equals(variant.getVrntId()))
                    || variants.size() > 1){
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("重复的变量名称");
                return result;
            }
        }
        return result;
    }

    private List<TvmLocaleVariantMapping> getLocVariantMappingByVarIdOrFormulaId(String varId, String formulaId,Byte isEnalbe) {
        TvmLocaleVariantMappingExample tvmLocaleVariantMappingExample = new TvmLocaleVariantMappingExample();
        TvmLocaleVariantMappingExample.Criteria criteria = tvmLocaleVariantMappingExample.createCriteria();
        if(!StringUtil.isNullOrEmpty(formulaId)){
            criteria.andLclvFormulaIdEqualTo(formulaId);
        }
        if(!StringUtil.isNullOrEmpty(varId)){
            criteria.andLclvVarIdEqualTo(varId);
        }
        if(!StringUtil.isNullOrEmpty(isEnalbe)){
            criteria.andIsenableEqualTo(isEnalbe);
        }
        List<TvmLocaleVariantMapping> tvmLocaleVariantMappingList = tvmLocaleVariantMappingMapper.selectByExample(tvmLocaleVariantMappingExample);
        return tvmLocaleVariantMappingList;
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    private TvmVariant initTempSystVariant(){
        TfdFormula initformula = formulaEngineService.initNewFormula();
        TvmVariant variant = new TvmVariant();
        variant.setAcctid(1);
        variant.setVrntVarScope(Scope.VARIANT_SCOPE_SYSTEM.getScope());
        variant.setVrntFormulaId(initformula.getFrmuId());
        saveSetting(variant);
        variant.setIsenable(Constant.ISENABLE_TEMP);
        tvmVariantMapper.insert(variant);
        return variant;
    }

    private TvmVariant getTempVariant(){
        TvmVariantExample tvmVariantExample = new TvmVariantExample();
        TvmVariantExample.Criteria criteria = tvmVariantExample.createCriteria();
        criteria.andIsenableEqualTo(Constant.ISENABLE_TEMP);
        criteria.andVrntVarScopeEqualTo(Scope.VARIANT_SCOPE_SYSTEM.getScope());
        List<TvmVariant> list = tvmVariantMapper.selectByExample(tvmVariantExample);
        if(!StringUtil.isNullOrEmpty(list)&&list.size()>0){
            return list.get(0);
        }
        return null;
    }

    /**
     *@desc 根据系统变量Id获取与公式的映射关系
     *@pars [sysVarId] 系统变量id
     *@date 2017/7/7 0007 上午 10:32 lulx
     *@return java.util.List<com.huntkey.rx.sceo.formula.common.model.TvmSystemVariantMapping>
     **/
    public List<TvmSystemVariantMapping> getSysVarMapBySysVarId(String sysVarId){
        TvmSystemVariantMappingExample systemVariantMappingExample = new TvmSystemVariantMappingExample();
        TvmSystemVariantMappingExample.Criteria criteria = systemVariantMappingExample.createCriteria();
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        criteria.andSystVarIdEqualTo(sysVarId);
        return tvmSystemVariantMappingMapper.selectByExample(systemVariantMappingExample);
    }

    /**
     *@desc 根据formulaId获取暂存中的局部变量映射关系
     *@pars [formulaId] 公式ID
     *@date 2017/7/6 0006 下午 2:38 lulx
     *@return com.huntkey.rx.sceo.formula.common.model.TvmLocaleVariantMapping
     **/
    public TvmLocaleVariantMapping getTempLocalVarMapByformulaId(String formulaId) {
        TvmLocaleVariantMappingExample localeVariantMappingExample = new TvmLocaleVariantMappingExample();
        TvmLocaleVariantMappingExample.Criteria criteria = localeVariantMappingExample.createCriteria();
        criteria.andIsenableEqualTo(Constant.ISENABLE_TEMP);
        criteria.andLclvFormulaIdEqualTo(formulaId);
        List<TvmLocaleVariantMapping> list = tvmLocaleVariantMappingMapper.selectByExample(localeVariantMappingExample);
        if(!StringUtil.isNullOrEmpty(list)&&list.size()>0){
            return list.get(0);
        }
        return null;
    }


    @Override
    public List<TvmVariant> getVariantByIds(List<String> variantIds) {
        TvmVariantExample tvmVariantExample = new TvmVariantExample();
        TvmVariantExample.Criteria tvmVariantExampleCriteria = tvmVariantExample.createCriteria();
        tvmVariantExampleCriteria.andVrntIdIn(variantIds);
        return tvmVariantMapper.selectByExample(tvmVariantExample);
    }
}
