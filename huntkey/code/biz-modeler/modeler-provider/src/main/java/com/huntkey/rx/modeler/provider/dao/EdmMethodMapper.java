package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmClass;
import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.common.model.EdmMethodExample;
import com.huntkey.rx.modeler.provider.annotation.RedisCache;
import com.huntkey.rx.modeler.provider.annotation.RedisEvict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EdmMethodMapper {
    @RedisCache(type = EdmMethod.class)
    long countByExample(EdmMethodExample example);

    /**
     * 通过id删除方法(逻辑删除)
     *
     * @param id
     * @return
     */
    @RedisEvict(type = EdmMethod.class )
    int updateIsDelByPrimaryKey(String id);

    /**
     * 新增方法
     *
     * @param edmMethod
     * @return
     */
    @RedisEvict(type = EdmMethod.class )
    int insertSelective(EdmMethod edmMethod);

    /**
     * 查询(可带查询条件)
     *
     * @param example
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    List<EdmMethod> selectByExample(EdmMethodExample example);

    /**
     * 通过id查询方法
     *
     *
     * @param id
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    EdmMethod selectByPrimaryKey(String id);

    /**
     * 更新方法
     *
     * @param edmMethod
     * @return
     */
    @RedisEvict(type = EdmMethod.class )
    int updateByPrimaryKeySelective(EdmMethod edmMethod);

    /**
     * 通过程序类型代码查找程序类型名称
     *
     * @param edmmProgramType
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    String selectProgramTypeNameByProgramTypeCode(String edmmProgramType);

    /**
     * 通过方法状态码查找方法状态名
     *
     * @param edmmStatus
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    String selectEdmtStatusNameByEdmtStatusCode(String edmmStatus);

    /**
     * 修改排序字段
     *
     * @param id
     * @param seq
     */
    @RedisEvict(type = EdmMethod.class )
    void updateSeqById(@Param("id") String id, @Param("seq") Integer seq);

    /**
     * 通过类的id查找该类的方法id集合
     * @param edmmEdmcId
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    List<String> selectMethodIdListByClassId(String edmmEdmcId);

    /**
     * 通过方法id查找方法名
     * @param id
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    String geMethodNameById(String id);

    /**
     * 通过方法程序类型代码查找代码表对应的代码含义值
     * @param edmmProgramType  方法程序类型代码
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    String selectEdmProgramTypeNameByEdmmProgramType(String edmmProgramType);


    /**
     * 通过方法id查找输入参数的参数名称
     * @param edmaEdmmId
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    List<String> selectEdmEdmdInsertArgNameByEdmaEdmmId(String edmaEdmmId);

    /**
     * 通过方法id查找输出参数的参数名称
     * @param edmaEdmmId
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    String selectEdmEdmdReturnNameByEdmaEdmmId(String edmaEdmmId);

    /**
     * 通过方法id查找输入参数的参数描述
     * @param edmaEdmmId
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    List<String> selectEdmEdmaInsertArgDescByEdmaEdmmId(String edmaEdmmId);

    /**
     * 通过方法id查找返回的参数描述
     * @param edmaEdmmId
     * @return
     */
    @RedisCache(type = EdmMethod.class)
    String selectEdmEdmaReturnDescByEdmaEdmmId(String edmaEdmmId);

    /**
     * 把本级方法分类下的方法移到目标方法分类
     * @param sourceMethodtypeId
     * @param aimMethodtypeId
     */
    int moveMethodtypeMethodToMethodtype(@Param("sourceMethodtypeId")String sourceMethodtypeId, @Param("aimMethodtypeId")String aimMethodtypeId);

    @RedisCache(type = EdmMethod.class)
    List<EdmMethod> selectEdmMethodByIdStr(String[] item);

    /**
     * 查询方法列表
     * @param edmmType
     * @param edmmProgramType
     * @param edmmName
     * @param edmmCls
     * @param edmmStatus
     * @return
     */
//    @RedisCache(type = EdmMethod.class)
    List<EdmMethod> selectEdmMethodByCondition(@Param(value = "edmmType")String edmmType, @Param(value = "edmmProgramType")String edmmProgramType,
                                               @Param(value = "edmmName")String edmmName, @Param(value = "edmmCls")String[] edmmCls,
                                               @Param(value = "edmmStatus")String edmmStatus );

    /**
     * 禁用、启用方法
     * @param id
     * @param edmmStatus
     */
    @RedisEvict(type = EdmMethod.class )
    void updateEdmmStatus(@Param(value = "id")String id, @Param(value = "edmmStatus")String edmmStatus);

    @RedisEvict(type = EdmMethod.class )
    void updateTriggerCond(@Param(value = "id") String id,@Param(value = "edmmTriggerCond")String condId);

    @RedisEvict(type = EdmMethod.class )
    void deleteTriggerCond(@Param(value = "id") String id);

    /**
     * 根据类id获取方法最大排序号
     * @param edmmEdmcId
     * @return
     */
    Integer getMaxSeqByCid(String edmmEdmcId);

    List<EdmMethod> selectEdmMethodByCid(String classId);

    void insertBatch(List<EdmMethod> edmMethods);

    void updateEdmcIdPrimaryKey(String id);

    /**
     * 根据类id与方法名称获取方法
     * @param edmcId
     * @param edmmName
     * @return
     */
    EdmMethod selectByEdmcIdAndEdmmName(@Param(value = "edmcId")String edmcId,@Param(value = "edmmName")String edmmName);
}