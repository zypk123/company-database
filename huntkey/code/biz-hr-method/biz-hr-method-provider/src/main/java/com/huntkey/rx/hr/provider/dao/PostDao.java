package com.huntkey.rx.hr.provider.dao;

import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.edm.entity.OdpsOdpsDpostSetaEntity;
import com.huntkey.rx.edm.entity.RposRposChagSetaEntity;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import java.util.List;

/**
 * @author zhanggj
 * @createTime 2017/12/12
 * @desc
 */
public interface PostDao {
    List<JobpositionEntity> query(OrmParam ormParam) throws Exception;

    /**
     * 查询部门列表下所有岗位信息
     * @param deptIds 当前部门及子部门的ID
     * @return 岗位信息列表
     */
    List<JobpositionEntity> queryPostsByDeptIds(String[] deptIds) throws Exception;

    //根据某一列的值，查找POST信息
    List<JobpositionEntity> queryPostsByColId(String colName, String value) throws Exception;

    //查询所有的岗位信息
    List<JobpositionEntity> queryAllPosts() throws Exception;

    //根据岗位ID查询岗位信息
    List<JobpositionEntity> queryPostsById(String[] postIds) throws Exception;

    /**
     * 根据单据ID查询岗位设置单据的属性集岗位信息
     * @param pid 单据ID
     * @return 岗位属性集
     */
    List<OdpsOdpsDpostSetaEntity> queryOrderPostsByPid(String pid) throws Exception;

    //根据className中的某一列，查找记录
    <T extends BaseEntity> List<T> queryRecordById(Class<T> className, String columnName, String columnValue) throws Exception;

    //查询岗位的有效（失效日期大于当前时间）变更记录集
    List<RposRposChagSetaEntity> queryPostsChagSetByPid(String pid) throws Exception;

    //更改岗位的有效（失效日期大于当前时间）变更记录集的失效日期等于当前时间
    int updatePostsChagSetByPid(String pid) throws Exception;

    //删除岗位设置单据下的所有岗位记录
    int deleteOrderPostsByOrderId(String orderId) throws Exception;
}
