package com.huntkey.rx.modeler.provider.dao;

import com.huntkey.rx.modeler.common.model.EdmMethodType;
import com.huntkey.rx.modeler.common.model.EdmMethodTypeExample;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodTypeVO;
import com.huntkey.rx.modeler.provider.annotation.RedisCache;
import com.huntkey.rx.modeler.provider.annotation.RedisEvict;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EdmMethodTypeMapper {

    @RedisCache(type = EdmMethodType.class )
    long countByExample(EdmMethodTypeExample example);

    @RedisEvict(type = EdmMethodType.class )
    int deleteByExample(EdmMethodTypeExample example);

    @RedisEvict(type = EdmMethodType.class )
    int deleteByPrimaryKey(String id);

    @RedisEvict(type = EdmMethodType.class )
    int insert(EdmMethodType record);

    @RedisEvict(type = EdmMethodType.class )
    int insertSelective(EdmMethodType record);

    @RedisCache(type = EdmMethodType.class )
    List<EdmMethodType> selectByExample(EdmMethodTypeExample example);

    @RedisCache(type = EdmMethodType.class )
    EdmMethodType selectByPrimaryKey(String id);

    @RedisEvict(type = EdmMethodType.class )
    int updateByExampleSelective(@Param("record") EdmMethodType record, @Param("example") EdmMethodTypeExample example);

    @RedisEvict(type = EdmMethodType.class )
    int updateByExample(@Param("record") EdmMethodType record, @Param("example") EdmMethodTypeExample example);

    @RedisEvict(type = EdmMethodType.class )
    int updateByPrimaryKeySelective(EdmMethodType record);

    @RedisEvict(type = EdmMethodType.class )
    int updateByPrimaryKey(EdmMethodType record);

    @RedisEvict(type = EdmMethodType.class )
    int updateIsDelByPrimaryKey(String id);

    @RedisCache(type = EdmMethodType.class )
    String selectTypeNameById(String edmmType);

    @RedisCache(type = EdmMethodType.class )
    List<String> selectEdmMethodTypeIdByParentId(String parentId);

    /**
     * 通过方法分类ID查找方法分类名称
     * @param id
     * @return
     */
    @RedisCache(type = EdmMethodType.class )
    String selectEdmtNameById(String id);

    /**
     * 根据方法分类名查询方法分类id
     * @param edmtName
     * @return
     */
    String selectEdmtIdByEdmtName(String edmtName);
}