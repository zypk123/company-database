/**
 * Project Name:service-center-provider
 * File Name:MeasureUnitController.java
 * Package Name:com.huntkey.rx.sceo.serviceCenter.provider.biz.common.controller
 * Date:2017年11月14日上午11:22:58
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 *
*/

package com.huntkey.rx.sceo.serviceCenter.provider.biz.common.controller;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service.MeasureUnitService;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;

/**
 * ClassName:MeasureUnitController
 * Function: 计量单位
 * Date:     2017年11月14日 上午11:22:58
 * @author   caozhenx
 */
@RestController
@RequestMapping("/biz/unit")
public class MeasureUnitController {

    @Autowired
    MeasureUnitService measureUnitService;
    
    /**
     * getBaseUnitObjects:获取基本计量对象数据
     * @author caozhenx
     * @throws DBException
     */
    @GetMapping("/base/objects")
    public Result getBaseUnitObjects() throws DBException {
        return measureUnitService.getBaseUnitObjects();
    }
    
    /**
     * getBaseUnitObjects:获取基本计量单位数据
     * @author caozhenx
     * @throws DBException
     */
    @GetMapping("/base")
    public Result getBaseUnit() throws DBException {
        return measureUnitService.getBaseUnit();
    }
    
    /**
     * getUnitObjects:根据基本计量单的id，获取同类计量单位数据
     * @author caozhenx
     * @param pid
     * @return
     * @throws DBException
     */
    @GetMapping("/{pid}/objects")
    public Result getUnitObjects(@PathVariable(value = "pid") @NotBlank(message = "基本计量单位id不能为空") String pid) throws DBException {
        return measureUnitService.getUnitObjects(pid);
    }
}

