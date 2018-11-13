package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmProperty;
import com.huntkey.rx.modeler.common.model.EdmPropertyExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface EdmPropertyMapper {
    long countByExample(EdmPropertyExample example);

    int deleteByExample(EdmPropertyExample example);

    int deleteByPrimaryKey(String id);

    int insert(EdmProperty record);

    int insertSelective(EdmProperty record);

    List<EdmProperty> selectByExample(EdmPropertyExample example);

    EdmProperty selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmProperty record, @Param("example") EdmPropertyExample example);

    int updateByExample(@Param("record") EdmProperty record, @Param("example") EdmPropertyExample example);

    int updateByPrimaryKeySelective(EdmProperty record);

    int updateByPrimaryKey(EdmProperty record);

    int updateIsDelByPrimaryKey(String id);

    List<EdmProperty> selectEdmPropertiesByCid(String id);

    String selectEdmPropertiesByCidAndEdmpCode(@Param(value = "id") String id, @Param(value = "edmpCode")String edmpCode);

    /**
     * 根据类的id获取该类属性的最大排序值
     *
     * @param cid
     * @return
     */
    Integer getMaxSeqByCid(String cid);

    /**
     * 修改排序字段
     *
     * @param id
     * @param seq
     */
    void updateSeqById(@Param("id") String id, @Param("seq") Integer seq);

    /**
     * 修改属性响应方法
     *
     * @param id
     * @param mid
     */
    void updateMethodById(@Param("id") String id, @Param("mid") String mid);

    // export
    String selectEdmpEdmcNameByEdmpEdmcId(String edmpEdmcId);

    String selectEdmpValueNameByEdmpValueType(String edmpValueType);

    String selectEdmpEdmmNameByEdmpEdmmId(String edmpEdmmId);

    String selectEdmpParentNameByEdmpParentId(String edmpParentId);

    int selectConvoluteByPropertyId(String id);

    int selectLinkByPropertyId(String id);

    int selectConnectByPropertyId(String id);

    List<EdmProperty> selectEdmPropertiesByPid(String id);

    void insertBatch(List<EdmProperty> edmProperties);

    /**
     * 通过属性ID查找属性编码
     *
     * @param id
     * @return
     */
    String getEdmpCodeById(String id);

    /**
     * 通过属性ID查找属性名称
     *
     * @param id
     * @return
     */
    String getEdmpNameById(String id);

    //查找属性名称
    List<EdmProperty> selectEdmNameByExample(EdmPropertyExample example);

    int selectUnitByPropertyId(String id);

    /**
     * 通过parentId查询属性(集)
     *
     * @param id
     * @return
     */
    List<EdmProperty> selectEdmPropertiesByParentId(String id);

    /**
     * 获取数值型属性(整型与字符型)
     * @param map
     * @return
     */
    List<EdmProperty> selectDataPropertiesByCid(Map<String,String> map);

    /**
     * 根据类id查询特征值的属性列表
     * @param id
     * @return
     */
    List<EdmProperty> selectIsCharacterByCid(String id);

    /**
     * 根据类id与属性编码查询属性值（监管树用）
     * @param classId
     * @param edmpCode
     * @return
     */
    EdmProperty getPropertyTypeAndValue(@Param("classId") String classId, @Param("edmpCode") String edmpCode);

    void updateBatchByid(@Param("idList") String[] idList,@Param("isVisible") byte b);


    /**
     * 根据属性限值id或者属性公式i置空属性限值字段或者性公式字段
     * @param map
     */
    void clearEdmpValueLimitAndFormula(Map<String, String> map);

    /**
     * 根据edmp_value获取edmp_edmc_id
     */
    List<String> getEdmpEdmcIdByIds(@Param("idList") String[] idList);

    /**
     * 根据edmp_edmc_id获取edmp_value
     * @param idList
     * @return
     */
    List<String> getEdmpvalueByIds(@Param("idList") String[] idList);

    List<String> selectPropertyIdByClassId(String classId);

    /**
     * 根据属性的数据类型获取属性所属类id
     * @param dataType
     * @return
     */
    List<String> getEdmcIdsByDataType(String dataType);

    /**
     * 根据类id和属性编码获取属性的数据类型
     * @param edmpEdmcId
     * @param edmpCode
     * @return
     */
    String getEdmpDataType(@Param("edmpEdmcId") String edmpEdmcId,
                           @Param("edmpCode") String edmpCode);

    /**
     * 根据类id获取枚举属性的数据类型
     * @param edmpEdmcId
     */
    List<String> getDataTypesByClassId(@Param("edmpEdmcId") String edmpEdmcId);

    String selectEdmpValueName(String edmpValueType);
}