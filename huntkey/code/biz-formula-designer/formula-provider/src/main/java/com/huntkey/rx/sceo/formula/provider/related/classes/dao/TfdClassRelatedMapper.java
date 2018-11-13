package com.huntkey.rx.sceo.formula.provider.related.classes.dao;

import java.util.List;

import com.huntkey.rx.sceo.formula.common.model.TfdClassRelated;
import com.huntkey.rx.sceo.formula.common.model.TfdClassRelatedExample;
import org.apache.ibatis.annotations.Param;

/**
 * @author chenfei on 2017/5/15.
 */
public interface TfdClassRelatedMapper {

    /**
     * countByExample
     * @param example
     * @return
     */
    int countByExample(TfdClassRelatedExample example);

    /**
     * deleteByExample
     * @param example
     * @return
     */
    int deleteByExample(TfdClassRelatedExample example);

    /**
     * deleteByPrimaryKey
     * @param clssId
     * @return
     */
    int deleteByPrimaryKey(String clssId);

    /**
     * insert
     * @param record
     * @return
     */
    int insert(TfdClassRelated record);

    /**
     * insertSelective
     * @param record
     * @return
     */
    int insertSelective(TfdClassRelated record);

    /**
     * selectByExample
     * @param example
     * @return
     */
    List<TfdClassRelated> selectByExample(TfdClassRelatedExample example);

    /**
     * selectByPrimaryKey
     * @param clssId
     * @return
     */
    TfdClassRelated selectByPrimaryKey(String clssId);

    /**
     * updateByExampleSelective
     * @param record
     * @param example
     * @return
     */
    int updateByExampleSelective(@Param("record") TfdClassRelated record, @Param("example") TfdClassRelatedExample example);

    /**
     * updateByExample
     * @param record
     * @param example
     * @return
     */
    int updateByExample(@Param("record") TfdClassRelated record, @Param("example") TfdClassRelatedExample example);

    /**
     * updateByPrimaryKeySelective
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(TfdClassRelated record);

    /**
     * updateByPrimaryKey
     * @param record
     * @return
     */
    int updateByPrimaryKey(TfdClassRelated record);

    /**
     * 删除相关类之前判断是否有局部变量引用该相关类
     *
     * @param clssId 相关类ID
     * @return
     */
    List<TfdClassRelated> existLocalVar(String clssId);

    /**
     * 查询引用该相关类的局部变量的变量名
     *
     * @param clssId
     * @return
     */
    List<String> showLocalVarName(String clssId);

    /**
     * 得到当前相关类的公式ID
     *
     * @param clssId
     * @return
     */
    String getFormulaId(String clssId);

    /**
     * 通过公式id得到相关类集合,除去自身相关类
     *
     * @param formulaId
     * @param clssId
     * @return
     */
    List<TfdClassRelated> selectByFormulaIdExceptSelf(@Param("formulaId") String formulaId, @Param("clssId") String clssId);

    /**
     * 通过相关类ID得到相关类的名称
     *
     * @param clssId
     * @return
     */
    String getClassRelatedName(String clssId);

    /**
     * 通过公式id得到相关类id
     *
     * @param formulaId
     * @return
     */
    List<String> getClassRelatedIdByFormulaId(String formulaId);

    /**
     * 删除相关类(逻辑删除)
     *
     * @param clssId
     * @return
     */
    int updateIsenableByClssId(String clssId);

    /**
     * 通过相关类ID查找别名
     *
     * @param clssId
     * @return
     */
    String getAliasNameById(String clssId);

    /**
     * 别名唯一性校验
     *
     * @param clssAliasName
     * @param formulaId
     * @param clssId
     * @return
     */
    List<String> checkNameUnique(@Param("clssAliasName") String clssAliasName, @Param("formulaId") String formulaId, @Param("clssId") String clssId);
    /**
     * 别名唯一性校验根据
     *
     * @param clssAliasName
     * @param clssLinkClassId
     * @param clssId
     * @return
     */
    List<String> checkNameUniqueForClssLinkClassId(@Param("clssAliasName") String clssAliasName, @Param("clssLinkClassId") String clssLinkClassId, @Param("clssId") String clssId);

    /**
     * 根据相关类ID查找公式ID
     * @param clssId
     * @return
     */
    String getFormulaIdByClssId(String clssId);

}