package com.huntkey.rx.sceo.formula.provider.variant.service;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.TvmVariant;

import java.util.List;
import java.util.Map;

/**
 * @author zhangyu
 * @create 2017-06-14 17:39
 **/
public interface VariantMgrService {

    /**
     * loadSystemVariants
     * @param varName
     * @return
     */
    List<TvmVariant> loadSystemVariants(String varName);

    /**
     * loadSystemVariantsWithFormula
     * @param formulaId
     * @return
     */
    List<TvmVariant> loadSystemVariantsWithFormula(String formulaId);

    /**
     * loadLocalVariantsWithFormula
     * @param formulaId
     * @return
     */
    List<TvmVariant> loadLocalVariantsWithFormula(String formulaId);

    /**
     * queryVariants
     * @param varName
     * @param state
     * @return
     */
    List<TvmVariant> queryVariants(String varName, String state);

    /**
     * removeVariant
     * @param varId
     * @return
     */
    Result removeVariant(String varId);

    /**
     * saveVariant
     * @param variant
     * @return
     */
    String saveVariant(TvmVariant variant);

    /**
     * updateVariant
     * @param variant
     * @return
     */
    int updateVariant(TvmVariant variant);

    /**
     * disableVariant
     * @param varId
     * @return
     */
    int disableVariant(String varId);

    /**
     * saveOrUpdateLocalVariantsWithFormula
     * @param localVarIdArr
     * @param formulaId
     * @return
     */
    int saveOrUpdateLocalVariantsWithFormula(String[] localVarIdArr, String formulaId);

    /**
     * saveOrUpdateSystemVariantsWithFormula
     * @param systemVarIdArr
     * @param formulaId
     * @return
     */
    int saveOrUpdateSystemVariantsWithFormula(String[] systemVarIdArr, String formulaId);

    /**
     * querySystemVariants
     * @param varName
     * @param varStatus
     * @param sortName
     * @param sortOrder
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pagination<TvmVariant> querySystemVariants(String varName, String varStatus, String sortName, String sortOrder, int pageNum, int pageSize);

    /**
     * getVariantByName
     * @param varName
     * @return
     */
    TvmVariant getVariantByName(String varName);

    /**
     * getVariantListByName
     * @param varName
     * @return
     */
    List<TvmVariant> getVariantListByName(String varName);

    /**
     * getVariantById
     * @param varId
     * @return
     */
    TvmVariant getVariantById(String varId);

    /**
     * getByFormulaId
     * @param formulaId
     * @param varScope
     * @return
     */
    List<TvmVariant> getByFormulaId(String formulaId,String varScope);

    /**
     * 删除局部变量之前的校验，判断是否有局部变量引用该局部变量
     *
     * @param varId 待删除局部变量的ID
     * @return
     */
    String existLocalVar(String varId);

    /**
     * 删除局部变量之前的校验，判断是否有相关类引用该局部变量
     *
     * @param varId 待删除局部变量的ID
     * @return
     */
    String existRelatedClass(String varId);

    /**
     * initByformulaId
     * @param formulaId
     * @param varType
     * @param varName
     * @return
     */
    TvmVariant initByformulaId(String formulaId, String varType, String varName);

    /**
     * initLocalVariant
     * @param formulaId
     * @return
     */
    Map<String,Object> initLocalVariant(String formulaId);

    /**
     * checkSysVarIsEnabled
     * @param sysVarId
     * @return
     */
    Result checkSysVarIsEnabled(String sysVarId);

    /**
     * initSystVariant
     * @return
     */
    TvmVariant initSystVariant();

    /**
     * 更新变量之前检查变量的值是否合法
     *  校验通过 返回true 不通过返回false
     *@param variant
     *@date 2017/7/18 0018 上午 9:32 lulx
     *@return java.lang.Boolean
     **/
    Result checkVarVal(TvmVariant variant);

    /**
     * getVariantByIds
     * @param variantIds
     * @return
     */
    List<TvmVariant> getVariantByIds(List<String> variantIds);
}
