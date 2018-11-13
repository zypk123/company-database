package com.huntkey.rx.provider.service;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;

import java.util.List;
import java.util.Map;

/**
 * Created by linziy on 2017/11/22.
 */
public interface EmployeeService {

    /**
     * 对象加载(根据ID查询)
     *
     * @param id
     * @return
     * @throws Exception
     */
    EmployeeEntity load(String id) throws Exception;

    /**
     * 新增(全部字段都插入)
     *
     * @param employeeEntity
     * @return 新增数据的ID
     * @throws Exception
     */
    String insert(EmployeeEntity employeeEntity) throws Exception;

    /**
     * 新增(只插入非空字段)
     *
     * @param employeeEntity
     * @return 新增数据的ID
     * @throws Exception
     */
    String insertSelective(EmployeeEntity employeeEntity) throws Exception;

    /**
     * 批量插入
     *
     * @param employeeEntityList
     * @return
     * @throws Exception
     */
    int insertBatch(List<EmployeeEntity> employeeEntityList) throws Exception;

    /**
     * 删除数据
     *
     * @param id
     * @return
     * @throws Exception
     */
    int delete(String id) throws Exception;

    /**
     * 根据条件删除数据
     *
     * @param ormParam
     * @return
     * @throws Exception
     */
    int deleteByCondition(OrmParam ormParam) throws Exception;

    /**
     * 更新数据(全部字段都更新)
     *
     * @param employeeEntity
     * @return
     * @throws Exception
     */
    int update(EmployeeEntity employeeEntity) throws Exception;

    /**
     * 更新数据(只更新非空字段)
     *
     * @param mployeeEntity
     * @return
     * @throws Exception
     */
    int updateSelective(EmployeeEntity mployeeEntity) throws Exception;

    /**
     * @param EmployeeEntity
     * @param ormParam
     * @return
     * @throws Exception
     */
    int updateSelective(EmployeeEntity EmployeeEntity, OrmParam ormParam) throws Exception;

    /**
     * @param ormParam
     * @return
     * @throws Exception
     */
    int delete(OrmParam ormParam) throws Exception;

    /**
     * @param ormParam
     * @return
     * @throws Exception
     */
    List<EmployeeEntity> query(OrmParam ormParam) throws Exception;

    /**
     * 条件查询
     *
     * @param ormParam
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> queryEx(OrmParam ormParam) throws Exception;

    /**
     * 统计符合条件的记录条数
     *
     * @param ormParam
     * @return
     * @throws Exception
     */
    long count(OrmParam ormParam) throws Exception;

    /**
     * 分页查询数据
     *
     * @param rempName
     * @param pageNum
     * @param pageSize
     * @return
     */
    Pagination<EmployeeEntity> getDataList(String rempName, int pageNum, int pageSize);

    /**
     * 自定义sql语句查询
     *
     * @param sql
     * @return
     * @throws Exception
     */
    List<Map<String, Object>> queryBySql(String sql) throws Exception;


}
