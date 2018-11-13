package com.huntkey.rx.hr.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.provider.service.OrderListService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;

@RestController
@RequestMapping("/hr/order")
public class OrderListController {
    @Autowired
    OrderListService orderList;

    @RequestMapping(value = "/orderList", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = "order",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"orderNo", "orderType", "applicant", "orderStatus", "pageNum", "pageSize"},
            methodDesc = "查询单据列表"
    )
    Result orderList(@RequestParam String orderNo,
                     @RequestParam @NotBlank(message = MsgConstants.MSG_ORDER_NOT_BLANK_TYPE) String orderType,
                     @RequestParam String applicant,
                     @RequestParam @NotBlank(message = MsgConstants.MSG_ORDER_NOT_BLANK_STATUS) String orderStatus,
                     @RequestParam(required = false) @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_START_MIN) int pageNum,
                     @RequestParam(required = false) @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_ROWS_MIN) int pageSize) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(orderList.orderList(orderNo, orderType, applicant, orderStatus, pageNum, pageSize));
        return result;
    }


    @RequestMapping(value = "/deleteOrder", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = "order",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            		getReqParamsNameNoPathVariable = {"orderTable", "orderId"},
            methodDesc = "删除单据"
    )
    Result deleteOrder(@RequestParam @NotBlank(message = "单据类名不能为空") String orderTable,
                       @RequestParam @NotBlank(message = "单据ID不能为空") String orderId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(orderList.deleteOrder(orderTable, orderId));
        return result;
    }
}
