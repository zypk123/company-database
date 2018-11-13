package com.huntkey.rx.sceo.serviceCenter.client.biz.common.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.client.biz.common.feign.EnumService;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by clarkzhao on 2017/10/24.
 *
 * @author clarkzhao
 * @date 2017/10/24
 */
@RestController
@RequestMapping("/v1/enums")
public class EnumController {

    @Autowired
    private EnumService enumService;

    @GetMapping("/{enumCode}/objects")
    public Result getEnumsObjects(
            @PathVariable(value = "enumCode")
            @NotBlank(message = "枚举编号不能为空") String enumCode) {
        return enumService.getEnumsObjects(enumCode);
    }
}
