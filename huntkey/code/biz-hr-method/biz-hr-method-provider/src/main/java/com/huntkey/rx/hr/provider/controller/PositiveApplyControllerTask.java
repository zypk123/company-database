package com.huntkey.rx.hr.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.provider.service.PositiveApplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yaoss
 */
@RestController
@RequestMapping(value = "/task/positive")
public class PositiveApplyControllerTask {

    @Autowired
    PositiveApplyService positiveApplyService;

    /**
     * 定时转正
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/taskUpdateEmpPostive", method = RequestMethod.GET)
    public Result taskUpdateEmpPostive() throws Exception{
        return positiveApplyService.updateEmpPostive();
    }
}
