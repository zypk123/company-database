package com.huntkey.rx.purchase.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.constants.SupplierMaintOrderConstants;
import com.huntkey.rx.purchase.provider.service.CurrencyService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 币别类Controller
 *
 * @author zhangyu
 * @create 2018-01-10 15:23
 **/
@RestController
@RequestMapping("/pu/currency")
public class CurrencyController {

    Logger logger = LoggerFactory.getLogger(CurrencyController.class);

    @Autowired
    private CurrencyService currencyService;

    /**
     * 查询所有币别类信息
     *
     * @return
     */
    @MethodRegister(
            edmClass = "currency",
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询所有币别类信息方法"
    )
    @RequestMapping(value = "/getList", method = RequestMethod.GET)
    public Result getList() {
        logger.info("查询所有币别类信息接口服务......");
        return currencyService.getList();
    }

}
