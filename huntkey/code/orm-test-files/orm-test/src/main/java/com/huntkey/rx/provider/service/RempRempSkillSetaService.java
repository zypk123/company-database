package com.huntkey.rx.provider.service;

import com.huntkey.rx.edm.entity.RempRempSkillSetaEntity;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;

import java.util.List;

/**
 * Created by linziy on 2017/11/22.
 */
public interface RempRempSkillSetaService {
    String insert(RempRempSkillSetaEntity rempRempSkillSetaEntity) throws Exception;

    String insertSelective(RempRempSkillSetaEntity rempRempSkillSetaEntity) throws Exception;

    int insert(List<RempRempSkillSetaEntity> rempRempSkillSetaEntities) throws Exception;

    int update(RempRempSkillSetaEntity rempRempSkillSetaEntity) throws Exception;

    int updateSelective(RempRempSkillSetaEntity rempRempSkillSetaEntity) throws Exception;

    int updateSelective(RempRempSkillSetaEntity rempRempSkillSetaEntity, OrmParam ormParam) throws Exception;

    int delete(String id) throws Exception;

    int delete(OrmParam ormParam) throws Exception;
    
}
