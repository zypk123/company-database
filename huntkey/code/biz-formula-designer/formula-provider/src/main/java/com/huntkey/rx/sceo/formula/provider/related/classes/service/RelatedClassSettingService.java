package com.huntkey.rx.sceo.formula.provider.related.classes.service;

import com.huntkey.rx.sceo.formula.common.model.TfdClassRelated;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.vo.ClassRelatedVo;

import java.util.List;

/**
 * 相关类设置Service接口
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
public interface RelatedClassSettingService {

    /**
     * 根据关联公式id查找公式相关类列表
     *
     * @param formulaId 关联公式id
     * @return
     */
    List<TfdClassRelated> loadRelatedClasses(String formulaId);

    /**
     * 根据关联classid和type查找公式相关类列表
     *
     * @param clssLinkClassId 关联类id
     * @param type 2 查询新增的类型
     * @return
     */
    List<TfdClassRelated> loadRelatedClassesByClssClassIdAndType(String clssLinkClassId,Integer type);


    /**
     * 新增相关类
     *
     * @param tfdClassRelated
     * @return
     */
    int saveRrelatedClasses(TfdClassRelated tfdClassRelated);

    /**
     * 保存条件
     *
     * @param tplCondition
     * @return
     */
    int saveCondition(TplCondition tplCondition);

    /**
     * 修改条件
     *
     * @param tplCondition
     * @return
     */
    int updateCondition(TplCondition tplCondition);

    /**
     * 根据条件id删除条件
     *
     * @param cndtId
     * @return
     */
    int removeCondition(String cndtId);

    /**
     * 根据条件id数组删除所有条件
     *
     * @param cndtIdArr
     * @return
     */
    int removeAllConditions(String[] cndtIdArr);

    /**
     * 相关类关联公式更新接口
     *
     * @param classIdArr 相关类id数组
     * @param formulaId  公式id
     * @return
     */
    int updateRelatedClassesWithFormula(String[] classIdArr, String formulaId);

    /**
     * 根据公式相关类id查找条件列表
     *
     * @param clssId 公式相关类id
     * @return
     */
    List<TplCondition> queryCondisionsByClssId(String clssId);

    /**
     * 删除相关类之前判断是否有局部变量引用该相关类
     *
     * @param clssId 相关类ID
     * @return
     */
    String existLocalVar(String clssId);

    /**
     * 删除相关类之前判断是否有相关类引用该相关类
     *
     * @param clssId 相关类ID
     * @return
     */
    String existClassRelated(String clssId);

    /**
     * 相关类删除接口
     *
     * @param clssId
     * @return
     */
    int deleteClassRelated(String clssId);

    /**
     * 修改相关类
     *
     * @param tfdClassRelated
     * @return
     */
    int updateClassRelated(TfdClassRelated tfdClassRelated);

    /**
     * 根据公式ID初始化相关类
     *
     * @param formulaId
     * @return
     */
    TfdClassRelated initRelatedClasses(String formulaId);

    /**
     * 根据clssLinkClassId初始化相关类
     *
     * @param clssLinkClassId
     * @return
     */
    TfdClassRelated initRelatedClassesByClssLinkClassId(String clssLinkClassId);

    /**
     * 通过相关类ID查找相关类及其条件列表
     *
     * @param clssId
     * @return
     */
    ClassRelatedVo getClassRelatedAndConditionsByClssId(String clssId);

    /**
     * 保存相关类及其条件列表
     *
     * @param classRelatedVo
     * @return
     */
    int saveOrUpdateClassRelatedAndConditions(ClassRelatedVo classRelatedVo);

    /**
     * 保存相关类
     *
     * @param tfdClassRelated
     * @return
     */
    int saveOrUpdateClassRelated(TfdClassRelated tfdClassRelated);

    /**
     * 保存条件
     *
     * @param tplCondition
     * @return
     */
    int saveOrUpdateCondition(TplCondition tplCondition);

    /**
     * 别名唯一性校验
     *
     * @param clssAliasName
     * @param formulaId
     * @param clssId
     * @return
     */
    String checkNameUnique(String clssAliasName, String formulaId, String clssId);
    /**
     * 别名唯一性校验
     *
     * @param clssAliasName
     * @param clssLinkClassId
     * @param clssId
     * @return
     */
    String checkNameUniqueForClssLinkClassId(String clssAliasName, String clssLinkClassId, String clssId);

    /**
     * 通过相关类ID查找别名
     *
     * @param clssId
     * @return
     */
    String getAliasNameById(String clssId);

    /**
     * 物理删除相关类id
     * @param clssId
     * @return
     */
    int physicalDeleteClassRelated(String clssId);
}
