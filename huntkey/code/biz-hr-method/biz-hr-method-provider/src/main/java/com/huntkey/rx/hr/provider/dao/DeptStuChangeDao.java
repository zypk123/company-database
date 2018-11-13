/**
 * Project Name:biz-hr-method-provider
 * File Name:DeptStuChangeDao.java
 * Package Name:com.huntkey.rx.hr.provider.dao
 * Date:2017年11月16日下午2:09:21
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
*/

package com.huntkey.rx.hr.provider.dao;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.edm.entity.DeptstuchangeorderEntity;
import com.huntkey.rx.edm.entity.OdscOdscChagSetaEntity;
import com.huntkey.rx.hr.common.model.DeptStuChangeOrderDTO;
import com.huntkey.rx.hr.common.model.OdscChagSetDTO;

/**
 * ClassName:DeptStuChangeDao
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年11月16日 下午2:09:21
 * @author   lijie
 * @version  
 * @see 	 
 */
public interface DeptStuChangeDao {
    
    /**
     * 
     * queryOrder: 查询部门移动单信息
     * @author lijie
     * @param odsc_type 单据类型
     * @param orde_order_num 单据号
     * @param orde_status 单据状态
     * @param odsc_flag 节点标识
     * @return
     */
    DeptstuchangeorderEntity queryOrder(String odsc_type, String orde_order_num,
                                        String orde_status, String odsc_flag);

    OdscOdscChagSetaEntity queryNode(String pid, String lvl, String odsc_flag);
}

