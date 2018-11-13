/**
 * Project Name:biz-hr-method-provider
 * File Name:ParkDao.java
 * Package Name:com.huntkey.rx.hr.provider.dao
 * Date:2017年11月22日上午9:50:51
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
 */

package com.huntkey.rx.hr.provider.dao;

import java.util.List;

import com.huntkey.rx.hr.common.model.ParkDTO;

/**
 * ClassName:ParkDao 园区类信息
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年11月22日 上午9:50:51
 * @author   lijie
 * @version
 * @see
 */
public interface ParkDao {

    List<ParkDTO> getAll();

    ParkDTO findById(String id);
}

