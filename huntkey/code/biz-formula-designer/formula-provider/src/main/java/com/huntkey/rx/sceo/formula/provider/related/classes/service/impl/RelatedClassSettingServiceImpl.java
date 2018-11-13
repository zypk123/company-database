package com.huntkey.rx.sceo.formula.provider.related.classes.service.impl;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.model.TfdClassRelated;
import com.huntkey.rx.sceo.formula.common.model.TfdClassRelatedExample;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.vo.ClassRelatedVo;
import com.huntkey.rx.sceo.formula.common.util.ConditionUtils;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.provider.engine.dao.TfdFormulaMapper;
import com.huntkey.rx.sceo.formula.provider.related.classes.dao.TfdClassRelatedMapper;
import com.huntkey.rx.sceo.formula.provider.related.classes.dao.TplConditionMapper;
import com.huntkey.rx.sceo.formula.provider.related.classes.service.ConditionService;
import com.huntkey.rx.sceo.formula.provider.related.classes.service.RelatedClassSettingService;
import com.huntkey.rx.sceo.formula.provider.variant.dao.TvmLocaleVariantMappingMapper;
import com.huntkey.rx.sceo.formula.provider.variant.dao.TvmVariantMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * 相关类设置Service接口实现类
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class RelatedClassSettingServiceImpl<M extends TfdClassRelated> extends BaseService implements RelatedClassSettingService {

    @Autowired
    TfdClassRelatedMapper tfdClassRelatedMapper;

    @Autowired
    TplConditionMapper tplConditionMapper;

    @Autowired
    RelatedClassSettingServiceImpl relatedClassSettingServiceImpl;

    @Autowired
    ConditionService conditionService;

    @Autowired
    private TvmLocaleVariantMappingMapper localeVariantMappingMapper;

    @Autowired
    private TvmVariantMapper variantMapper;

    @Autowired
    private TfdFormulaMapper formulaMapper;

    @Override
    public List<TfdClassRelated> loadRelatedClasses(String formulaId) {
        TfdClassRelatedExample example = new TfdClassRelatedExample();
        // 创建查询条件对象
        TfdClassRelatedExample.Criteria criteria = example.createCriteria();
        // 设置查询条件为formulaId
        criteria.andClssFormulaIdEqualTo(formulaId);
        // 查询状态是未删除的数据
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        List<TfdClassRelated> tfdClassRelateds = tfdClassRelatedMapper.selectByExample(example);
        return tfdClassRelateds;
    }


    @Override
    public List<TfdClassRelated> loadRelatedClassesByClssClassIdAndType(String clssLinkClassId,Integer type) {
        TfdClassRelatedExample example = new TfdClassRelatedExample();
        // 创建查询条件对象
        TfdClassRelatedExample.Criteria criteria = example.createCriteria();
        // 设置查询条件为type
        criteria.andClssLinkClassIdEqualTo(clssLinkClassId);
        criteria.andTypeEqualTo(type);
        // 查询状态是未删除的数据
        criteria.andIsenableEqualTo(Constant.ISENABLE_YSE);
        List<TfdClassRelated> tfdClassRelateds = tfdClassRelatedMapper.selectByExample(example);
        return tfdClassRelateds;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveRrelatedClasses(TfdClassRelated tfdClassRelated) {
        relatedClassSettingServiceImpl.saveSetting(tfdClassRelated);
        return tfdClassRelatedMapper.insertSelective(tfdClassRelated);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveCondition(TplCondition tplCondition) {
        // 当值类型是"class"时，合并值编码
        //ConditionUtils.mergeValueCode(tplCondition);
        return conditionService.saveCondition(tplCondition);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updateCondition(TplCondition tplCondition) {
        // 当值类型是"class"时，合并值编码
        //ConditionUtils.mergeValueCode(tplCondition);
        return conditionService.updateCondition(tplCondition);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int removeCondition(String cndtId) {
        return tplConditionMapper.updateIsenableByPrimaryKey(cndtId);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int removeAllConditions(String[] cndtIdArr) {
        int retInt = -1;
        // 遍历条件id数组
        for (String cndtId : cndtIdArr) {
            retInt = tplConditionMapper.updateIsenableByPrimaryKey(cndtId);
        }
        return retInt;
    }

    @Override
    public List<TplCondition> queryCondisionsByClssId(String clssId) {
        List<TplCondition> tplConditions = tplConditionMapper.selectByClssId(clssId);
//         当值类型选择的是"类"，拆分值编码
//        for (TplCondition tplCondition : tplConditions) {
//            if ("class".equals(tplCondition.getCndtValueType())) {
//                ConditionUtils.splitValueCode(tplCondition);
//            }
//        }
        return tplConditions;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updateRelatedClassesWithFormula(String[] classIdArr, String formulaId) {
        int retInt = 0;
        TfdClassRelated tfdClassRelated = new TfdClassRelated();
        tfdClassRelated.setModuser(Constant.MODUSER);
        tfdClassRelated.setModtime(new Date());
        for (String classId : classIdArr) {
            tfdClassRelated.setClssId(classId);
            tfdClassRelated.setClssFormulaId(formulaId);
            retInt = tfdClassRelatedMapper.updateByPrimaryKeySelective(tfdClassRelated);
        }
        return retInt;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updateClassRelated(TfdClassRelated tfdClassRelated) {
        relatedClassSettingServiceImpl.updateSetting(tfdClassRelated);
        return tfdClassRelatedMapper.updateByPrimaryKeySelective(tfdClassRelated);
    }

    @Override
    public String existLocalVar(String clssId) {
        String errMsg = null;
        // 1. 根据待删除的相关类ID找到公式ID
        String formulaId = tfdClassRelatedMapper.getFormulaIdByClssId(clssId);
        // 2. 通过公式ID在局部变量映射表中找到映射的变量ID集合
        List<String> lclvVarIdList = localeVariantMappingMapper.getlclvVarIdByFormulaId(formulaId);
        // 3. 在变量表中通过变量ID找到对应的公式ID
        for (String lclvVarId : lclvVarIdList) {
            // 这里的lclvVarId其实等于变量表中的ID
            String formualId = variantMapper.getFormualIdByVrntId(lclvVarId);
            // 4. 通过公式ID在公式表中查找公式内容
            String formulaContent = formulaMapper.getFormulaContentByFormulaId(formualId);
            // 5.判断该公式内容是否包含待删除的相关类ID
            boolean result = formulaContent.contains(clssId);
            // 包含
            if (result) {
                String varName = variantMapper.getVarNameByVrntId(lclvVarId);
                errMsg = "相关类删除校验失败，局部变量" + varName + "引用了该相关类!";
            }
        }
        return errMsg;
    }

    @Override
    public String existClassRelated(String clssId) {
        String errMsg = null;
        // 得到相关类的名称
        String classRelatedName = tfdClassRelatedMapper.getClassRelatedName(clssId);
        // 得到该相关类所在的公式ID
        String formulaId = tfdClassRelatedMapper.getFormulaId(clssId);
        // 得到该公式下的所有相关类集合，除去了自身
        List<TfdClassRelated> tfdClassRelatedList = tfdClassRelatedMapper.selectByFormulaIdExceptSelf(formulaId, clssId);
        // 遍历所有相关类，得到他们的id,然后根据相关类的id得到他们的条件
        for (TfdClassRelated tfdClassRelated : tfdClassRelatedList) {
            String cId = tfdClassRelated.getClssId();
            // 得到条件列表
            List<TplCondition> tplConditionList = tplConditionMapper.selectByClssId(cId);
            // 遍历条件列表，如果条件中的值包含该类的类名，则说明该相关类已经被其他相关类引用
            for (TplCondition tplCondition : tplConditionList) {
                String cndtPropClssId = tplCondition.getCndtPropClssId();
                String name = tfdClassRelatedMapper.getClassRelatedName(cndtPropClssId);
                // 得到条件列表的值,判断是否包含该相关类的名称
                String cndtValue = tplCondition.getCndtValue();
                if (cndtValue.contains(classRelatedName)) {
                    errMsg = "相关类删除校验失败,该相关类被" + name + "相关类所引用!";
                }
            }
        }
        return errMsg;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int deleteClassRelated(String clssId) {
        return tfdClassRelatedMapper.updateIsenableByClssId(clssId);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public TfdClassRelated initRelatedClasses(String formulaId) {
        TfdClassRelated tfdClassRelated = getTempRelatedClasses(formulaId);
        if (StringUtil.isNullOrEmpty(tfdClassRelated)) {
            tfdClassRelated = new TfdClassRelated();
            saveSetting(tfdClassRelated);
            tfdClassRelated.setIsenable(Constant.ISENABLE_TEMP);
            tfdClassRelated.setClssFormulaId(formulaId);
            tfdClassRelatedMapper.insertSelective(tfdClassRelated);
        }
        return tfdClassRelated;
    }
    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public TfdClassRelated initRelatedClassesByClssLinkClassId(String clssLinkClassId) {
        TfdClassRelated tfdClassRelated = null;
        TfdClassRelatedExample example = new TfdClassRelatedExample();
        // 创建查询条件对象
        TfdClassRelatedExample.Criteria criteria = example.createCriteria();
        // 设置查询条件为clssLinkClassId
        criteria.andClssLinkClassIdEqualTo(clssLinkClassId);
        // 暂存状态
        criteria.andIsenableEqualTo(Constant.ISENABLE_TEMP);
        List<TfdClassRelated> tfdClassRelateds = tfdClassRelatedMapper.selectByExample(example);
        if (!StringUtil.isNullOrEmpty(tfdClassRelateds) && tfdClassRelateds.size() > 0) {
            tfdClassRelated = tfdClassRelateds.get(0);
        }
        if (StringUtil.isNullOrEmpty(tfdClassRelated)) {
            tfdClassRelated = new TfdClassRelated();
            saveSetting(tfdClassRelated);
            tfdClassRelated.setIsenable(Constant.ISENABLE_TEMP);
            tfdClassRelated.setClssLinkClassId(clssLinkClassId);
            tfdClassRelatedMapper.insertSelective(tfdClassRelated);
        }
        return tfdClassRelated;
    }

    @Override
    public ClassRelatedVo getClassRelatedAndConditionsByClssId(String clssId) {
        ClassRelatedVo classRelatedVo = new ClassRelatedVo();
        classRelatedVo.setClassRelated(tfdClassRelatedMapper.selectByPrimaryKey(clssId));
        // 得到条件列表
        List<TplCondition> tplConditionList = tplConditionMapper.selectByClssId(clssId);
        for (TplCondition condition : tplConditionList) {
            // 当值为"class"时，拆分值编码
            if ("class".equals(condition.getCndtValueType())) {
                ConditionUtils.splitValueCode(condition);
            }
        }
        classRelatedVo.setTplConditions(tplConditionList);
        return classRelatedVo;
    }


    public ClassRelatedVo getClassRelatedAndConditionsByClssClassIdAndClssLinkClassId(String clssClassId,String clssLinkClassId) {
        ClassRelatedVo classRelatedVo = new ClassRelatedVo();

        TfdClassRelatedExample example = new TfdClassRelatedExample();
        // 创建查询条件对象
        TfdClassRelatedExample.Criteria criteria = example.createCriteria();
        // 设置查询条件为clssClassId
        criteria.andClssClassIdEqualTo(clssClassId);
        // 设置查询条件为clssClassId
        criteria.andClssLinkClassIdEqualTo(clssLinkClassId);
        // 暂存状态
        criteria.andIsenableEqualTo(Constant.ISENABLE_TEMP);
        List<TfdClassRelated> tfdClassRelateds = tfdClassRelatedMapper.selectByExample(example);
        if (!StringUtil.isNullOrEmpty(tfdClassRelateds) && tfdClassRelateds.size() > 0) {
            classRelatedVo.setClassRelated(tfdClassRelateds.get(0));
            // 得到条件列表
            List<TplCondition> tplConditionList = tplConditionMapper.selectByClssId(tfdClassRelateds.get(0).getClssId());
            for (TplCondition condition : tplConditionList) {
                // 当值为"class"时，拆分值编码
                if ("class".equals(condition.getCndtValueType())) {
                    ConditionUtils.splitValueCode(condition);
                }
            }
            classRelatedVo.setTplConditions(tplConditionList);
        }
        return classRelatedVo;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdateClassRelatedAndConditions(ClassRelatedVo classRelatedVo) {
        int retIntSaveClassRelated = Result.RECODE_ERROR;
        int retIntSaveCndt = Result.RECODE_ERROR;
        // 得到待添加的条件列表
        List<TplCondition> tplConditions = classRelatedVo.getTplConditions();
        // 得到待添加的相关类的ID
        String clssId = classRelatedVo.getClassRelated().getClssId();
        // 得到原始的条件列表
        List<TplCondition> tplConditions1 = tplConditionMapper.selectByClssId(clssId);

        // 条件不存在，就直接更新相关类
        if (null == tplConditions || tplConditions.size() == 0) {
            tplConditionMapper.updateIsenableByPropClssId(clssId);
            retIntSaveClassRelated = this.saveOrUpdateClassRelated(classRelatedVo.getClassRelated());
        } else {
            // 条件不为空，遍历待添加的条件
            for (TplCondition condition : tplConditions) {
                String cndtId = condition.getCndtId();
                // id为空，新增条件
                if (StringUtil.isNullOrEmpty(cndtId)) {
                    condition.setCndtPropClssId(classRelatedVo.getClassRelated().getClssId());
                    retIntSaveCndt = this.saveCondition(condition);
                } else {
                    // update条件
                    retIntSaveCndt = this.updateCondition(condition);
                    Iterator<TplCondition> it = tplConditions1.iterator();
                    while (it.hasNext()) {
                        TplCondition next = it.next();
                        String cndtId1 = next.getCndtId();
                        if (cndtId1.equals(cndtId)) {
                            it.remove();
                        }
                    }
                }
            }
            for (TplCondition condition1 : tplConditions1) {
                String cndtId1 = condition1.getCndtId();
                tplConditionMapper.updateIsenableByPrimaryKey(cndtId1);
            }
            this.saveOrUpdateClassRelated(classRelatedVo.getClassRelated());
        }
        if (retIntSaveCndt != 0 && retIntSaveClassRelated != 0) {
            return 1;
        }
        return 0;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdateClassRelated(TfdClassRelated tfdClassRelated) {
        String clssId = tfdClassRelated.getClssId();
        // 新增
        if (StringUtil.isNullOrEmpty(clssId)) {
            return this.saveRrelatedClasses(tfdClassRelated);
        } else {
            return this.updateClassRelated(tfdClassRelated);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveOrUpdateCondition(TplCondition tplCondition) {
        String cndtId = tplCondition.getCndtId();
        // 新增
        if (StringUtil.isNullOrEmpty(cndtId)) {
            return this.saveCondition(tplCondition);
        } else {
            return this.updateCondition(tplCondition);
        }
    }

    @Override
    public String checkNameUnique(String clssAliasName, String formulaId, String clssId) {
        String errorStr = "";
        List<String> clssAliasNameList = tfdClassRelatedMapper.checkNameUnique(clssAliasName, formulaId, clssId);
        if (null != clssAliasNameList && clssAliasNameList.size() > 0) {
            errorStr = "相关类名称已存在!";
            return errorStr;
        }
        return errorStr;
    }

    @Override
    public String checkNameUniqueForClssLinkClassId(String clssAliasName, String clssLinkClassId, String clssId) {
        String errorStr = "";
        List<String> clssAliasNameList = tfdClassRelatedMapper.checkNameUniqueForClssLinkClassId(clssAliasName, clssLinkClassId, clssId);
        if (null != clssAliasNameList && clssAliasNameList.size() > 0) {
            errorStr = "相关类名称已存在!";
            return errorStr;
        }
        return errorStr;
    }

    @Override
    public String getAliasNameById(String clssId) {
        return tfdClassRelatedMapper.getAliasNameById(clssId);
    }

    /**
     * @return com.huntkey.rx.sceo.formula.common.model.TfdClassRelated
     * @desc 根据formulaId获取暂存状态的相关类
     * 暂存状态 Isenable 为 Constant.TEMP_ISENABLE
     * @pars [formulaId] 公式ID
     * @date 2017/7/6 0006 上午 11:38 lulx
     **/
    public TfdClassRelated getTempRelatedClasses(String formulaId) {
        TfdClassRelatedExample example = new TfdClassRelatedExample();
        // 创建查询条件对象
        TfdClassRelatedExample.Criteria criteria = example.createCriteria();
        // 设置查询条件为formulaId
        criteria.andClssFormulaIdEqualTo(formulaId);
        // 暂存状态
        criteria.andIsenableEqualTo(Constant.ISENABLE_TEMP);
        List<TfdClassRelated> tfdClassRelateds = tfdClassRelatedMapper.selectByExample(example);
        if (!StringUtil.isNullOrEmpty(tfdClassRelateds) && tfdClassRelateds.size() > 0) {
            return tfdClassRelateds.get(0);
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int physicalDeleteClassRelated(String clssId) {
        return tfdClassRelatedMapper.deleteByPrimaryKey(clssId);
    }
}
