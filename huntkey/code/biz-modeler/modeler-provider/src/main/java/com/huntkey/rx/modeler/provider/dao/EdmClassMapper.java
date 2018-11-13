package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmClass;
import com.huntkey.rx.modeler.common.model.EdmClassExample;
import java.util.List;
import java.util.Map;

import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.provider.annotation.RedisCache;
import com.huntkey.rx.modeler.provider.annotation.RedisEvict;
import org.apache.ibatis.annotations.Param;

public interface EdmClassMapper {
    @RedisCache(type = EdmClass.class)
    long countByExample(EdmClassExample example);

    @RedisEvict(type = EdmClass.class)
    int deleteByExample(EdmClassExample example);

    @RedisEvict(type = EdmClass.class)
    int deleteByPrimaryKey(String id);

    @RedisEvict(type = EdmClass.class)
    int insert(EdmClass record);

    @RedisEvict(type = EdmClass.class)
    int insertSelective(EdmClass record);

    @RedisCache(type = EdmClass.class)
    List<EdmClass> selectByExample(EdmClassExample example);

    @RedisCache(type = EdmClass.class)
    EdmClass selectByPrimaryKey(String id);

    @RedisEvict(type = EdmClass.class)
    int updateByExampleSelective(@Param("record") EdmClass record, @Param("example") EdmClassExample example);

    @RedisEvict(type = EdmClass.class)
    int updateByExample(@Param("record") EdmClass record, @Param("example") EdmClassExample example);

    @RedisEvict(type = EdmClass.class)
    int updateByPrimaryKeySelective(EdmClass record);

    @RedisEvict(type = EdmClass.class)
    int updateByPrimaryKey(EdmClass record);

    //-------------------------新增--------------------------------------------------------------------------------
    /**
     * 获取类的列表
     * @param mid
     * @return
     */
    @RedisCache(type = EdmClass.class)
    List<EdmClass> selectEdmClassesByMid(@Param("mid") String mid);

    @RedisEvict(type = EdmClass.class)
    int updateIsDelByPrimaryKey(String id);

    @RedisCache(type = EdmClass.class)
    String selectParentNameByEdmcParentId(String edmcParentId);

    @RedisCache(type = EdmClass.class)
    String getEdmNameEnById(String edmpEdmcId);

    /**
     * 修改排序字段
     * @param id
     * @param seq
     */
    @RedisEvict(type = EdmClass.class)
    void updateSeqById(@Param("id")String id, @Param("seq")Integer seq);

    @RedisCache(type = EdmClass.class)
    Integer getMaxSeqByPid(String parentid);

    /**
     * 根据父id查出所有子类
     * @param edmcParentId
     * @return
     */
    @RedisCache(type = EdmClass.class)
    List<EdmClass> getEdmClassNameByPid(@Param("mid") String edcmEdmcId,@Param("pid") String edmcParentId);

    /**
     * 通过ID查找类编码
     * @param id
     * @return
     */
    @RedisCache(type = EdmClass.class)
    String getEdmcCodeById(String id);

    /**
     * 通过ID查找类名称
     * @param id
     * @return
     */
    @RedisCache(type = EdmClass.class)
    String getEdmcNameById(String id);

    /**
     * 通过模型id与类简称查找具体类
     * @param id
     * @param shortName
     * @return
     */
    @RedisCache(type = EdmClass.class)
    EdmClass selectSpecialClass(@Param("edmdId") String id, @Param("shortName")String shortName);

    /**
     * 根据模型id与类英文名获取类id
     * @param edmdId
     * @param name
     * @return
     */
    @RedisCache(type = EdmClass.class)
    String selectEdmcIdByEdmdIdAndName(@Param("edmdId") String edmdId, @Param("edmcNameEn")String name);

    @RedisCache(type = EdmClass.class)
    String getDataBaseTypeByEdmdId(@Param("edmdId") String edmdId, @Param("edmcNameEn")String name);

    List<EdmClass> getAllEdmcClassByEdmdId(String edmdId);

    EdmClass getEdmClassByEdmcCode(@Param("edmcCode") String edmcCode, @Param("edmdId") String edmdId);

    /**
     * 根据类编码获取类id
     * @param edmcCode
     * @return
     */
    Map<String,String> getIdByEdmcCode(String edmcCode);

    /**
     * 根据模型id与类名获取类id
     * @param edmdId
     * @param name
     * @return
     */
    @RedisCache(type = EdmClass.class)
    String selectEdmcIdByEdmdIdAndEdmcName(@Param("edmdId") String edmdId, @Param("edmcName")String name);

    /**
     * 根据id获取类英文名称
     * @param id
     * @return
     */
    String getEdmcEnNameById(String id);
}