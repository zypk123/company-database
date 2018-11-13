package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmCond;
import com.huntkey.rx.modeler.common.model.EdmLink;
import com.huntkey.rx.modeler.common.model.EdmLinkExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EdmLinkMapper {
    long countByExample(EdmLinkExample example);

    int LogicDeleteByPrimaryKey(String id);

    int insert(EdmLink record);

    int insertSelective(EdmLink record);

    List<EdmLink> selectByExample(EdmLinkExample example);

    EdmLink selectByPrimaryKey(String id);

    int updateByExampleSelective(@Param("record") EdmLink record, @Param("example") EdmLinkExample example);

    int updateByExample(@Param("record") EdmLink record, @Param("example") EdmLinkExample example);

    int updateByPrimaryKeySelective(EdmLink record);

    int updateByPrimaryKey(EdmLink record);

    /**
     * 根据属性id 查找关联属性
     * @param propertyId
     * @return
     */
    List<EdmLink> selectEdmLinkListByPropertyId(String propertyId);

    /**
     * 批量插入关联属性
     * @param edmLinksAll
     */
    void insertBatch(List<EdmLink> edmLinksAll);

    /**
     * 根据属性ID查找关联属性
     * @param id  edml_edmp_id 属性id
     * @return
     */
    List<EdmLink> getEdmLinkProperties(String id);

    /**
     * 查找本属性id被关联的属性
     * @param id edml_edmp_id_link 被关联的属性id
     * @return
     */
    List<EdmLink> selectEdmLinkPropertiesListByEdmlEdmpLinkId(String id);

    /**
     * 检查关系是否重复
     * @param example
     * @return
     */
    long checkUnique(EdmLinkExample example);

    String selectLinkidById(String id);

    int logicDeleteByEdmlEdmpLinkId(String edmlEdmpLinkId);

    void clearEdmlFormula(String id);

    /**
     * 根据属性id和触发条件id获取关联条件
     * @param edmpId
     * @param edmlCond
     * @return
     */
    List<EdmLink> getEdmLinks(@Param(value = "edmpId") String edmpId, @Param(value = "edmlCond") String edmlCond);

    void moveLink(@Param(value = "ids")String[] ids, @Param(value = "edmCond")EdmCond edmCond);

    /**
     * 根据触发条件id删除关联
     * @param condId
     */
    void logicDeleteByCondId(String condId);
}