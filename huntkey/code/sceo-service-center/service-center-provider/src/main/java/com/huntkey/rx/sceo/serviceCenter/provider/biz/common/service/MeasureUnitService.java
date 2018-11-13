/**
 * Project Name:service-center-provider
 * File Name:MeasureUnitService.java
 * Package Name:com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service
 * Date:2017年11月14日下午1:37:47
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
*/

package com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;

/**
 * ClassName:MeasureUnitService
 * Function: 计量单位类方法定义
 * Date:     2017年11月14日 下午1:37:47
 * @author   caozhenx
 */
public interface MeasureUnitService {

    Result getBaseUnitObjects() throws DBException;
    
    Result getUnitObjects(String pid) throws DBException;

    Result getBaseUnit() throws DBException;

}

