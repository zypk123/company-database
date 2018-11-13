package com.huntkey.rx.provider.service.impl;


import com.huntkey.rx.edm.entity.RempRempSkillSetaEntity;
import com.huntkey.rx.provider.service.RempRempSkillSetaService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by linziy on 2017/11/22.
 */
//EmployeeService
@Service
public class RempRempSkillSetaServiceImpl implements RempRempSkillSetaService {

    private static Logger logger = LoggerFactory.getLogger(RempRempSkillSetaServiceImpl.class);

    @Autowired
    OrmService ormService;

    @Override
    public String insert(RempRempSkillSetaEntity rempRempSkillSetaEntity) throws Exception {
        return ormService.insert(rempRempSkillSetaEntity).toString();
    }

    @Override
    public String insertSelective(RempRempSkillSetaEntity rempRempSkillSetaEntity) throws Exception {
        return ormService.insertSelective(rempRempSkillSetaEntity).toString();
    }

    @Override
    public int insert(List<RempRempSkillSetaEntity> rempRempSkillSetaEntities) throws Exception {
        return ormService.insert(rempRempSkillSetaEntities);
    }

    @Override
    public int update(RempRempSkillSetaEntity rempRempSkillSetaEntity) throws Exception {
        return ormService.update(rempRempSkillSetaEntity);
    }

    @Override
    public int updateSelective(RempRempSkillSetaEntity rempRempSkillSetaEntity) throws Exception {
        return ormService.updateSelective(rempRempSkillSetaEntity);
    }

    @Override
    public int updateSelective(RempRempSkillSetaEntity rempRempSkillSetaEntity, OrmParam ormParam) throws Exception {
        return ormService.updateSelective(rempRempSkillSetaEntity, ormParam);
    }

    @Override
    public int delete(String id) throws Exception {
        return ormService.delete(RempRempSkillSetaEntity.class, id);
    }

    @Override
    public int delete(OrmParam ormParam) throws Exception {
        return ormService.delete(RempRempSkillSetaEntity.class, ormParam);
    }

}
