package com.huntkey.rx.sceo.serviceCenter.provider.biz.ppi.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.ppi.service.IndexDefineOrderService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/biz/opfm")
public class IndexDefineOrderController {

    @Autowired
    IndexDefineOrderService defineOrderService;
    /**
     * 指标定义公式设置流程回调接口
     * @param formulaOrderId
     * @return
     */
    @PostMapping("/formula/pass/{formulaOrderId}")
    public Result fornulaOrderPass(@PathVariable @NotBlank(message = "单据ID不能为空") String formulaOrderId) throws Exception{
        return defineOrderService.formulaOrderPass(formulaOrderId);
    }
}
