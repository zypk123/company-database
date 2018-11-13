package com.huntkey.rx.sceo.orm.service;

import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.base.LinkdetailEntity;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by licj on 2017/11/17.
 */
public interface OrmService {

    ////////////////////////////////主表与属性集类操作的接口///////////////////////////////////////////////////////////////////

    /**
     * 对象加载
     *
     * @param entityClazz 实体类型
     * @param entityID:   对象主键ID
     * @return 对象数据
     * @throws Exception
     */
    <T extends BaseEntity> T load(Class<T> entityClazz, Serializable entityID) throws Exception;


    /**
     * 插入数据（包括数值为null字段）
     *
     * @param t 实体数据
     * @return 主键 ID
     * @throws Exception
     */
    <T extends BaseEntity> Serializable insert(T t) throws Exception;

    /**
     * 插入数据（不包括数值为null字段）
     *
     * @param <T> 实体数据
     * @return 主键 ID
     * @throws Exception
     */
    <T extends BaseEntity> Serializable insertSelective(T t) throws Exception;

    /**
     * 批量插入
     *
     * @param list 实体数据列表
     * @param <T>  泛型类型
     * @return 新增数据总行数
     * @throws Exception
     */
    <T extends BaseEntity> int insert(List<T> list) throws Exception;


    /**
     * 根据主键ID 逻辑删除单条数据
     *
     * @param entityClazz 实体数据对应的类型，如:edm 中employee 对应 EmployeeEntity 实体类型
     * @param entityID:   主键 ID
     * @return 返回删除的记录数
     * @throws Exception
     */
    <T extends BaseEntity> int delete(Class<T> entityClazz, Serializable entityID) throws Exception;

    /**
     * 根据条件删除数据
     *
     * @param entityClazz 泛型的对应类型
     * @param ormParam    条件变量
     * @param <T>         泛型类型
     * @return 返回删除的记录数
     * @throws Exception
     */
    <T extends BaseEntity> int delete(Class<T> entityClazz, OrmParam ormParam) throws Exception;

    /**
     * 对象更新（包括值为null的字段）
     *
     * @param t   更新的实体数据
     * @param <T>
     * @return 返回更新的记录数
     * @throws Exception
     */
    <T extends BaseEntity> int update(T t) throws Exception;

    /**
     * 根据主键更新（不包括值为null的字段）
     *
     * @param t   更新的实体对象
     * @param <T> 实体对象类型
     * @return 返回更新的记录行数
     * @throws Exception
     */
    <T extends BaseEntity> int updateSelective(T t) throws Exception;

    /**
     * 根据条件更新（跟新指定的字段集）
     *
     * @param t        更新实体数据
     * @param ormParam 条件参数
     * @param <T>      更新的实体类型
     * @return 返回更新的记录行数
     * @throws Exception
     */
    <T extends BaseEntity> int updateSelective(T t, OrmParam ormParam) throws Exception;

    /**
     * 高级查询,根据条件查询返回全列普通属性
     *
     * @param entityClazz 实体类
     * @param ormParam    条件参数
     * @param <T>
     * @return
     * @throws Exception
     */
    <T extends BaseEntity> List<T> selectBeanList(Class<T> entityClazz, OrmParam ormParam) throws Exception;

    /**
     * 高级查询,根据条件查询指定返回列
     *
     * @param entityClazz 实体类的类型
     * @param ormParam    查询条件参数
     * @param <T>
     * @return
     * @throws Exception
     */
    <T extends BaseEntity> List<Map<String, Object>> selectMapList(Class<T> entityClazz, OrmParam ormParam) throws Exception;

    /**
     * 根据条件统计数量
     *
     * @param entityClazz 实体类的类型
     * @param ormParam    查询条件参数
     * @param <T>
     * @return
     * @throws Exception
     */
    <T extends BaseEntity> long count(Class<T> entityClazz, OrmParam ormParam) throws Exception;

    //############################# 属性集表操作#############################################

    /**
     * 根据外键 pid 到属性集表中查找指定类型的属性集数据；
     *
     * @param pid:              主类管理属性集表的外键
     * @param entityClazz：属性集类型
     * @param mainClazz：主类
     * @return 属性集的对象列表
     * @throws Exception
     */
    <T extends BaseEntity> List<T> getAttrubuteSetByPID(Serializable pid, Class<T> entityClazz, Class<? extends BaseEntity> mainClazz) throws Exception;
    //############################# link表操作###############################################

    /**
     * 加载 linkRecords 数据
     *
     * @param edmClazz link 表对应的主类
     * @param entityID link 表的主键id
     * @return
     * @throws Exception
     */
    LinkdetailEntity loadLink(Class<? extends BaseEntity> edmClazz, Serializable entityID) throws Exception;

    /**
     * 新增记录集数据
     *
     * @param edmClazz link 表对应的主类
     * @param entity   link 表数据实体类
     * @return
     * @throws Exception
     */
    Serializable insertLink(Class<? extends BaseEntity> edmClazz, LinkdetailEntity entity) throws Exception;

    /**
     * 高级查询
     *
     * @param edmClazz link 表对应的主类
     * @param ormParam 查询条件
     * @return
     * @throws Exception
     */
    List<LinkdetailEntity> selectBeanListLink(Class<? extends BaseEntity> edmClazz, OrmParam ormParam) throws Exception;

    /**
     * 分页查询
     *
     * @param entityClazz 实体类
     * @param ormParam    条件参数
     * @return page对象
     * @throws Exception 抛出异常
     */
    <T extends BaseEntity> Pagination<T> selectPagedBeanList(Class<T> entityClazz, OrmParam ormParam) throws Exception;


    /**
     * 分页查询,主要返回的是List<Map>
     *
     * @param entityClazz 实体类
     * @param ormParam    条件参数
     * @return page对象
     * @throws Exception 抛出异常
     */
    <T extends BaseEntity> Pagination<T> selectPagedMapList(Class<T> entityClazz, OrmParam ormParam) throws Exception;

    /**
     * 更加sql查询数据
     *
     * @param sql 可执行sql
     * @return List 返回 List<Map<String,Object>>
     * @throws Exception 抛出异常
     */
    List<Map<String, Object>> getDataBySql(String sql) throws Exception;
}
