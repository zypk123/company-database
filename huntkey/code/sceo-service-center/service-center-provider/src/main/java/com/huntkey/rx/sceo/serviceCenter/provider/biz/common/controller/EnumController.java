package com.huntkey.rx.sceo.serviceCenter.provider.biz.common.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service.EnumService;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by clarkzhao on 2017/10/23.
 */
@RestController
@RequestMapping("/biz/enums")
public class EnumController {

    @Autowired
    private EnumService enumService;

    @GetMapping("/objects")
    public Result getEnumsObjects(
            @RequestParam(value = "enumCodes")
            @NotBlank(message = "枚举编号不能为空") String enumCodes) throws DBException {
        return enumService.getEnumsObjects(enumCodes);
    }

    /**
     * 根据枚举对象ID查询枚举对象信息
     * @param enumId
     * @return
     * @throws DBException
     */
    @GetMapping("/{enumId}/object")
    public Result getEnumObject(@PathVariable(value = "enumId") @NotBlank(message = "枚举对象ID不能为空") String enumId) throws DBException {
        return enumService.getEnumObjectById(enumId);
    }
}
