package com.huntkey.rx.hr.provider.dao.impl;

import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.edm.constant.JobpositionProperty;
import com.huntkey.rx.edm.constant.OdpsOdpsDpostSetaProperty;
import com.huntkey.rx.edm.constant.RposRposChagSetaProperty;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.edm.entity.OdpsOdpsDpostSetaEntity;
import com.huntkey.rx.edm.entity.RposRposChagSetaEntity;
import com.huntkey.rx.hr.provider.dao.PostDao;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

/**
 * @author zhanggj
 * @createTime 2017/12/12
 * @desc
 */
@Component
public class PostDaoImpl implements PostDao {

    @Autowired
    OrmService ormService;

    @Override
    public List<JobpositionEntity> query(OrmParam ormParam) throws Exception {
        return null;
    }

    /**
     * 查询部门列表下所有岗位信息
     *
     * @param deptIds 当前部门及子部门的ID
     * @return 岗位信息列表
     */
    @Override
    public List<JobpositionEntity> queryPostsByDeptIds(String[] deptIds) throws Exception {

        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getInXML(JobpositionProperty.RPOS_DEPT, deptIds),
                ormParam.getGreaterThanAndEqualXML(JobpositionProperty.RPOS_END, DateUtil.formatDate(new Date())));
        ormParam.setWhereExp(whereCondition)
                .addOrderExpElement(SQLSortEnum.ASC, JobpositionProperty.RPOS_GRADE)
                .addOrderExpElement(SQLSortEnum.ASC, JobpositionProperty.RPOS_CODE);
        List<JobpositionEntity> posts = ormService.selectBeanList(JobpositionEntity.class, ormParam);
        return posts;
    }

    //根据某一列的值，查找POST信息
    @Override
    public List<JobpositionEntity> queryPostsByColId(String colName, String value) throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(colName, value),
                ormParam.getGreaterThanAndEqualXML(JobpositionProperty.RPOS_END, DateUtil.formatDate(new Date())));
        ormParam.setWhereExp(whereCondition)
                .addOrderExpElement(SQLSortEnum.ASC, JobpositionProperty.RPOS_GRADE)
                .addOrderExpElement(SQLSortEnum.ASC, JobpositionProperty.RPOS_CODE);
        List<JobpositionEntity> posts = ormService.selectBeanList(JobpositionEntity.class, ormParam);
        return posts;
    }

    @Override
    public List<JobpositionEntity> queryAllPosts() throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereCondition = ormParam.getGreaterThanAndEqualXML(JobpositionProperty.RPOS_END, DateUtil.formatDate(new Date()));
        ormParam.setWhereExp(whereCondition)
                .addOrderExpElement(SQLSortEnum.ASC, JobpositionProperty.RPOS_GRADE)
                .addOrderExpElement(SQLSortEnum.ASC, JobpositionProperty.RPOS_CODE);
        List<JobpositionEntity> posts = ormService.selectBeanList(JobpositionEntity.class, ormParam);
        return posts;
    }

    //根据岗位ID查询岗位信息
    @Override
    public List<JobpositionEntity> queryPostsById(String[] postIds) throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getInXML(NodeConstant.ID, postIds),
                ormParam.getGreaterThanAndEqualXML(JobpositionProperty.RPOS_END, DateUtil.formatDate(new Date())));
        ormParam.setWhereExp(whereCondition)
                .addOrderExpElement(SQLSortEnum.ASC, JobpositionProperty.RPOS_GRADE)
                .addOrderExpElement(SQLSortEnum.ASC, JobpositionProperty.RPOS_CODE);
        List<JobpositionEntity> posts = ormService.selectBeanList(JobpositionEntity.class, ormParam);
        return posts;
    }

    //根据单据ID查询岗位设置单据的属性集岗位信息
    @Override
    public List<OdpsOdpsDpostSetaEntity> queryOrderPostsByPid(String pid) throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereCondition = ormParam.getEqualXML(NodeConstant.PID, pid);
        ormParam.setWhereExp(whereCondition)
                .addOrderExpElement(SQLSortEnum.ASC, OdpsOdpsDpostSetaProperty.ODPS_PGRADE)
                .addOrderExpElement(SQLSortEnum.ASC, OdpsOdpsDpostSetaProperty.ODPS_POST);
        List<OdpsOdpsDpostSetaEntity> posts = ormService.selectBeanList(OdpsOdpsDpostSetaEntity.class, ormParam);
        return posts;
    }

    //根据className中的某一列，查找记录
    @Override
    public <T extends BaseEntity> List<T> queryRecordById(Class<T> className, String columnName, String columnValue) throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereCondition = ormParam.getEqualXML(columnName, columnValue);
        ormParam.setWhereExp(whereCondition);
        List<T> record = ormService.selectBeanList(className, ormParam);
        return record;
    }

    //查询岗位的有效变更记录集 失效日期大于当前时间
    @Override
    public List<RposRposChagSetaEntity> queryPostsChagSetByPid(String pid) throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, pid),
                ormParam.getGreaterThanAndEqualXML(RposRposChagSetaProperty.RPOS_END_HIS, DateUtil.formatDate(new Date())));
        ormParam.setWhereExp(whereCondition);
        List<RposRposChagSetaEntity> postChagSets = ormService.selectBeanList(RposRposChagSetaEntity.class, ormParam);
        return postChagSets;
    }

    //更改岗位的有效（失效日期大于当前时间）变更记录集的失效日期等于当前时间
    @Override
    public int updatePostsChagSetByPid(String pid) throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, pid),
                ormParam.getGreaterThanAndEqualXML(RposRposChagSetaProperty.RPOS_END_HIS, DateUtil.formatDate(new Date())));
        ormParam.setWhereExp(whereCondition);
        RposRposChagSetaEntity rposRposChagSetaEntity = new RposRposChagSetaEntity();
        rposRposChagSetaEntity.setRpos_end_his(new Date());
        return ormService.updateSelective(rposRposChagSetaEntity, ormParam);
    }

    //删除岗位设置单据下的所有岗位记录
    @Override
    public int deleteOrderPostsByOrderId(String orderId) throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereCondition = ormParam.getEqualXML(NodeConstant.PID, orderId);
        ormParam.setWhereExp(whereCondition);
        return ormService.delete(OdpsOdpsDpostSetaEntity.class, ormParam);
    }

}
