/**
 * Project Name:biz-hr-method-provider
 * File Name:DeptStuChangeDaoImpl.java
 * Package Name:com.huntkey.rx.hr.provider.dao.impl
 * Date:2017年11月16日下午2:13:31
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
*/

package com.huntkey.rx.hr.provider.dao.impl;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.DeptstuchangeorderProperty;
import com.huntkey.rx.edm.constant.OdscOdscChagSetaProperty;
import com.huntkey.rx.edm.entity.DeptstuchangeorderEntity;
import com.huntkey.rx.edm.entity.OdscOdscChagSetaEntity;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.provider.dao.DeptStuChangeDao;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSymbolEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * ClassName:DeptStuChangeDaoImpl
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年11月16日 下午2:13:31
 * @author   lijie
 * @version  
 * @see 	 
 */
@Component
public class DeptStuChangeDaoImpl implements DeptStuChangeDao{

    @Autowired
    OrmService ormService;
    
    @Override
    public DeptstuchangeorderEntity queryOrder(String odsc_type, String orderId, String orde_status, String odsc_flag) {
        // 查询异动单信息
        OrmParam ormParam = new OrmParam();

        /*String whereCondition = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.ID, orderId),
                ormParam.getEqualXML(DeptstuchangeorderProperty.ODSC_TYPE, odsc_type),
                ormParam.getEqualXML(OrderProperty.ORDE_STATUS, orde_status));*/
        String whereCondition = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.ID, orderId),
                ormParam.getEqualXML(DeptstuchangeorderProperty.ODSC_TYPE, odsc_type));

        ormParam.setWhereExp(whereCondition);

        List<DeptstuchangeorderEntity> deptstuchangeorderEntityList = null;
        try {
            deptstuchangeorderEntityList = ormService.selectBeanList(DeptstuchangeorderEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门结构异动单错误：" + e.getMessage());
        }

        if (deptstuchangeorderEntityList == null || deptstuchangeorderEntityList.size() <= 0){
            return null;
        }
        DeptstuchangeorderEntity deptstuchangeorderEntity = deptstuchangeorderEntityList.get(0);

        // 查询异动单下异动列表信息
        OrmParam ormParam_ = new OrmParam();
        ormParam_.addColumn(SQLSymbolEnum.ALLCOLUMNS.getSymbol());
        String whereCondition_ = OrmParam.and(ormParam_.getEqualXML(EdmSysColumn.PID, deptstuchangeorderEntity.getId()));
        // odsc_flag为空时查询当前异动单下所有异动列表
        if (!StringUtil.isNullOrEmpty(odsc_flag)){
            whereCondition_ = OrmParam.and(ormParam_.getEqualXML(EdmSysColumn.PID, deptstuchangeorderEntity.getId()),
                    ormParam_.getEqualXML(OdscOdscChagSetaProperty.ODSC_FLAG, odsc_flag));
        }

        ormParam_.setWhereExp(whereCondition_);
        ormParam_.setOrderExp(SQLSortEnum.ASC, OdscOdscChagSetaProperty.ODSC_LVL);
        List<OdscOdscChagSetaEntity> odscChagSetaEntityList = null;
        try {
            odscChagSetaEntityList = ormService.selectBeanList(OdscOdscChagSetaEntity.class, ormParam_);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门结构异动单异动列表错误：" + e.getMessage());
        }

        if (odscChagSetaEntityList == null || odscChagSetaEntityList.size() <= 0){
            return deptstuchangeorderEntity;
        }

        deptstuchangeorderEntity.setOdsc_chag_set(odscChagSetaEntityList);
        
        return deptstuchangeorderEntity;
    }

    
    @Override
    public OdscOdscChagSetaEntity queryNode(String pid, String lvl, String odsc_flag) {
        OrmParam ormParam = new OrmParam();
        String whereCondition = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, pid),
                ormParam.getEqualXML(OdscOdscChagSetaProperty.ODSC_FLAG, odsc_flag),
                ormParam.getEqualXML(OdscOdscChagSetaProperty.ODSC_LVL, lvl));
        ormParam.setWhereExp(whereCondition);

        List<OdscOdscChagSetaEntity> odscChagSetaEntityList = null;
        try {
            odscChagSetaEntityList = ormService.selectBeanList(OdscOdscChagSetaEntity.class, ormParam);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "查询部门结构异动单异动列表错误：" + e.getMessage());
        }

        if (odscChagSetaEntityList == null || odscChagSetaEntityList.size() <= 0){
            return null;
        }
        return odscChagSetaEntityList.get(0);
    }

}

