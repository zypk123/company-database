package com.huntkey.rx.provider.service.impl;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.edm.entity.RempRempSkillSetaEntity;
import com.huntkey.rx.provider.service.EmployeeService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.util.PersistentUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author linziy
 */
@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static Logger logger = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    private OrmService ormService;

    @Override
    public Pagination<EmployeeEntity> getDataList(String rempName, int pageNum, int pageSize) {

        Pagination<EmployeeEntity> employeeEntityPagination = null;

        try {
            OrmParam ormParam = new OrmParam();
            ormParam.addWhereParam("remp_name", rempName);
            String whereExp = "remp_name = #{whereParam.remp_name}";
            ormParam.setWhereExp(whereExp);

            // 添加分页信息
            ormParam.setPageNo(pageNum);
            ormParam.setPageSize(pageSize);
            employeeEntityPagination = ormService.selectPagedBeanList(EmployeeEntity.class, ormParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return employeeEntityPagination;
    }

    @Override
    public List<Map<String, Object>> queryBySql(String sql) throws Exception {

        return ormService.getDataBySql(sql);
    }


    @Override
    public EmployeeEntity load(String id) throws Exception {
        return ormService.load(EmployeeEntity.class, id);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public String insert(EmployeeEntity employeeEntity) throws Exception {

        // 主表 employee
        // 新增操作 默认会新增,ID，Cretime,Modtime,Moduser
        employeeEntity.setCreuser("zypk123");
        String id = ormService.insert(employeeEntity).toString();

        // 属性集 remp_skill_set 专业技能
        // setPropertyBaseEntitiesSysColumns  根据操作的模式为属性集添加上属性集特有的系统信息 pid classname creuser moduser
        PersistentUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employeeEntity, employeeEntity.getRemp_skill_set(), SQLCurdEnum.INSERT);
        ormService.insert(employeeEntity.getRemp_skill_set());

        // 属性集 remp_cont_set 合同记录
        PersistentUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employeeEntity, employeeEntity.getRemp_cont_set(), SQLCurdEnum.INSERT);
        ormService.insert(employeeEntity.getRemp_cont_set());

        return id;
    }

    @Override
    public String insertSelective(EmployeeEntity employeeEntity) throws Exception {

        //主表
        employeeEntity.setCreuser("zypk123");
        String id = ormService.insert(employeeEntity).toString();

        //属性集 remp_skill_set 专业技能
        //setPropertyBaseEntitiesSysColumns  根据操作的模式为属性集添加上属性集特有的系统信息 pid classname creuser moduser
        PersistentUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employeeEntity, employeeEntity.getRemp_skill_set(), SQLCurdEnum.INSERT);
        ormService.insert(employeeEntity.getRemp_skill_set());

        // 属性集 remp_cont_set 合同记录
        PersistentUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employeeEntity, employeeEntity.getRemp_cont_set(), SQLCurdEnum.INSERT);
        ormService.insert(employeeEntity.getRemp_cont_set());

        return id;
    }

    @Override
    public int insertBatch(List<EmployeeEntity> employeeEntityList) throws Exception {

        // 主表
        int renInt = ormService.insert(employeeEntityList);

        for (EmployeeEntity employeeEntity : employeeEntityList) {
            // 属性集 remp_skill_set 专业技能
            PersistentUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employeeEntity, employeeEntity.getRemp_skill_set(), SQLCurdEnum.INSERT);
            ormService.insert(employeeEntity.getRemp_skill_set());

            // 属性集 remp_cont_set 合同记录
            PersistentUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employeeEntity, employeeEntity.getRemp_cont_set(), SQLCurdEnum.INSERT);
            ormService.insert(employeeEntity.getRemp_cont_set());
        }

        return renInt;
    }


    @Override
    public int update(EmployeeEntity employeeEntity) throws Exception {
        String user = "zypk123";
        employeeEntity.setModuser(user);
        employeeEntity.setModtime(new Date());

        PersistentUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employeeEntity, employeeEntity.getRemp_skill_set(), SQLCurdEnum.UPDATE);
        int count = 0;
        for (RempRempSkillSetaEntity entity : employeeEntity.getRemp_skill_set()) {
            count += ormService.update(entity);
        }
        return ormService.update(employeeEntity);
    }

    @Override
    public int updateSelective(EmployeeEntity employeeEntity) throws Exception {
        String user = "zypk123";
        employeeEntity.setModuser(user);
        employeeEntity.setModtime(new Date());

        PersistentUtil.setPropertyBaseEntitiesSysColumns(EmployeeEntity.class, employeeEntity, employeeEntity.getRemp_skill_set(), SQLCurdEnum.UPDATE);
        int count = 0;
        for (RempRempSkillSetaEntity entity : employeeEntity.getRemp_skill_set()) {
            count += ormService.updateSelective(entity);
        }
        return ormService.updateSelective(employeeEntity);
    }

    @Override
    public int updateSelective(EmployeeEntity EmployeeEntity, OrmParam ormParam) throws Exception {
        return ormService.updateSelective(EmployeeEntity, ormParam);
    }

    @Override
    public int delete(String id) throws Exception {
        return ormService.delete(EmployeeEntity.class, id);
    }

    @Override
    public int deleteByCondition(OrmParam ormParam) throws Exception {
        return ormService.delete(EmployeeEntity.class, ormParam);
    }

    @Override
    public int delete(OrmParam ormParam) throws Exception {
        return ormService.delete(EmployeeEntity.class, ormParam);
    }

    @Override
    public List<EmployeeEntity> query(OrmParam ormParam) throws Exception {
        return ormService.selectBeanList(EmployeeEntity.class, ormParam);
    }

    @Override
    public List<Map<String, Object>> queryEx(OrmParam ormParam) throws Exception {
        return ormService.selectMapList(EmployeeEntity.class, ormParam);
    }

    @Override
    public long count(OrmParam ormParam) throws Exception {
        return ormService.count(EmployeeEntity.class, ormParam);
    }

}
