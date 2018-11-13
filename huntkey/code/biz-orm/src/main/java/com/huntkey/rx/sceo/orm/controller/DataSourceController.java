package com.huntkey.rx.sceo.orm.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.orm.common.model.DataSourceEntity;
import com.huntkey.rx.sceo.orm.service.DataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dataSourceController")
public class DataSourceController {

    @Autowired
    private DataSourceService dataSourceService;

    @RequestMapping("/addDynamicDataSource")
    public Result addDynamicDataSource(@RequestBody DataSourceEntity dataSourceEntity) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            dataSourceService.addDynamicDataSource(dataSourceEntity);
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        return result;
    }

}
