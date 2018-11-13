package com.huntkey.rx.sceo.common.service.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.common.model.to.SimpleEmailTO;
import com.huntkey.rx.sceo.common.service.provider.service.EmailLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaomj on 2017/4/28.
 */
@RestController
@RequestMapping("/email")
public class EmailLogController {

    @Autowired
    private EmailLogService emailLogService;

    @RequestMapping(method = RequestMethod.POST)
    public Result sendSmsPost(@RequestBody SimpleEmailTO to){
    	return emailLogService.sendSimpleEmail(to);
    }
}
