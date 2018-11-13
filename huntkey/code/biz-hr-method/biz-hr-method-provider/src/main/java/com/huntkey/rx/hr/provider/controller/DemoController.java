package com.huntkey.rx.hr.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.common.constants.NumberConstants;
import com.huntkey.rx.hr.provider.client.InformationClient;
import com.huntkey.rx.hr.provider.service.DeptTreeService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.Min;
import java.util.Collection;
import java.util.Collections;

@RestController
@Validated
@RequestMapping("/hr/demo")
public class DemoController {

    @Autowired
    InformationClient informationClient;

    @RequestMapping("/depts")
    Result query(){
        return informationClient.getNumbers(NumberConstants.PREFIX_DEPT_CHARGER_APPLY_ORDER,null);
    }
}
