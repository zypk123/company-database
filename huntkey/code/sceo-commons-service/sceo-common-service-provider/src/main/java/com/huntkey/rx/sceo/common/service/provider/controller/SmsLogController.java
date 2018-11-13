package com.huntkey.rx.sceo.common.service.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.common.model.to.SimpleSmsTO;
import com.huntkey.rx.sceo.common.service.provider.service.SmsLogService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by zhaomj on 2017/4/27.
 */
@RestController
@RequestMapping("/sms")  
public class SmsLogController {
    @Autowired
    private SmsLogService smsLogService;
    //post方式发送短信
    @RequestMapping(method = RequestMethod.POST)
    public Result sendSmsPost(@RequestBody SimpleSmsTO to){
    	return smsLogService.sendMessageByPost(to);
    }
}
