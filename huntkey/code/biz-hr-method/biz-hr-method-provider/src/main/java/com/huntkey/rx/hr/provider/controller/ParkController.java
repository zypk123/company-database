/**
 * Project Name:biz-hr-method-provider
 * File Name:ParkController.java
 * Package Name:com.huntkey.rx.hr.provider.controller
 * Date:2017年11月22日上午10:14:28
 * Copyright (c) 2017 嘉源锐信 All Rights Reserved.
 */

package com.huntkey.rx.hr.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.ParkConstants;
import com.huntkey.rx.hr.provider.service.ParkService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * ClassName:ParkController
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年11月22日 上午10:14:28
 * @author lijie
 * @version
 * @see
 */
@RestController
@RequestMapping("/hr/park")
public class ParkController {

    @Autowired
    private ParkService parkSrv;

    @MethodRegister(
            edmClass = ParkConstants.EDM_PARK,
            methodCate = "园区类方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "获取所有园区信息")
    @RequestMapping(value = "/query/all", method = RequestMethod.GET)
    public Result getAll() {
        return parkSrv.getAll();
    }
}

