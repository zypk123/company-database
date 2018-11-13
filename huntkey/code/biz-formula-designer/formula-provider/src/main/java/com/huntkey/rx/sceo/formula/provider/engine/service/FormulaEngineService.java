package com.huntkey.rx.sceo.formula.provider.engine.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.PPIFormula;
import com.huntkey.rx.sceo.formula.common.model.TfdFormula;
import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import com.huntkey.rx.sceo.formula.common.model.vo.ShowFormulaVo;
import com.huntkey.rx.sceo.formula.common.model.vo.SimplePPIParasVo;
import com.huntkey.rx.sceo.formula.common.model.vo.SimpleParasVo;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.expression.Expression;

import java.util.List;
import java.util.Map;

/**
 * 公式引擎服务接口
 *
 * @author zhangyu
 * @create 2017-06-14 17:13
 **/
public interface FormulaEngineService {

    /**
     * 保存公式
     *
     * @param formula
     * @return
     */
    String saveFormula(TfdFormula formula);

    /**
     * 修改公式
     *
     * @param formula
     * @return
     */
    int updateFormula(TfdFormula formula);

    /**
     *
     * 公式求值
     *
     * @param formulaId
     * @param provider
     * @return
     */
    Object calc(String formulaId, DataProvider provider);

    /**
     * 变量求值
     *
     * @deprecated
     * @param variantId
     * @param provider
     * @return
     */
    Object variantCalc(String variantId, DataProvider provider);

    /**
     * calculate value of variant.
     *
     * @param variant
     * @param provider
     * @return
     */
    Object variantCalc(TvmVariant variant, DataProvider provider);

    /**
     * 从缓存中获取表达式
     *
     * @param formulaId
     * @return
     */
    Expression getExpressionFromCache(String formulaId);

    /**
     * 将编译好的表达式缓存
     *
     * @param formulaId
     * @param expr
     * @return
     */
    void putExpression2Cache(String formulaId, Expression expr);

    /**
     * 函数编译
     *
     * @param functionContent
     * @return
     */
    boolean functionCompile(String functionContent);

    /**
     * 公式预计算
     *
     * @param formulaContent
     * @param provider
     * @return
     */
    Object formulaPreCalc(String formulaContent, DataProvider provider);

    /**
     * 属性限值公式计算
     *
     * @param propLimitId
     * @param provider
     * @return
     */
    Boolean propLimitFormulaCalc(String propLimitId, DataProvider provider);

    /**
     * getTfdFormulaById
     * @param formulaId
     * @return
     */
    TfdFormula getTfdFormulaById(String formulaId);

    /**
     * initNewFormula
     * @return
     */
    TfdFormula initNewFormula();

    /**
     * initFormula
     * @param params
     * @return
     */
    Map initFormula(SimpleParasVo params);

    /**
     * initPPIFormula
     * @param params
     * @return
     */
    Map initPPIFormula(SimplePPIParasVo params);

    /**
     * getFormulaElement
     * @param varid
     * @param userid
     * @param classId
     * @return
     */
    Result getFormulaElement(String varid, String userid, String classId);

    /**
     * getFormulaFunction
     * @param userId
     * @return
     */
    Result getFormulaFunction(String userId);

    /**
     * 根据公式ids查询公式
     *@param formulaIds id数组
     *@date 2017/7/15 0015 上午 11:10 lulx
     *@return java.util.List<com.huntkey.rx.sceo.formula.common.model.TfdFormula>
     **/
    List<TfdFormula> getFormulasByids(List<String> formulaIds);

    /**
     * 保存PPI公式
     *
     * @param formula
     * @return
     */
    String savePPIFormula(PPIFormula formula);

    /**
     * 修改PPI公式
     *
     * @param formula
     * @return
     */
    int updatePPIFormula(PPIFormula formula);

    /**
     *
     * PPI公式求值
     *
     * @param formulaContent
     * @param provider
     * @return
     */
    Object calcPPI(String formulaContent, DataProvider provider);

    /**
     * 对象呈现公式求值
     * @param showFormulaVo
     * @return
     */
    Object calcShow(ShowFormulaVo showFormulaVo);

}
