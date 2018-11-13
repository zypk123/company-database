package com.huntkey.rx.purchase.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.settlemenet.SettleMenetDTO;
import com.huntkey.rx.purchase.provider.service.SettleMenetService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 结算方式Controller
 *
 * @author zhangyu
 * @create 2018-01-10 18:54
 **/
@RestController
@RequestMapping("/pu/settleMenet")
public class SettleMenetController {
    Logger logger = LoggerFactory.getLogger(SettleMenetController.class);

    @Autowired
    private SettleMenetService settleMenetService;

    /**
     * 保存结算方式
     *
     * @param settleMenetDTO
     * @return
     */
    @MethodRegister(
            edmClass = "settlemenet",
            methodCate = "采购系统方法",
            methodDesc = "保存结算方式方法"
    )
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public Result save(@RequestBody SettleMenetDTO settleMenetDTO) {
        logger.info("保存结算方式接口开始服务......");
        return settleMenetService.save(settleMenetDTO);
    }
    
}
