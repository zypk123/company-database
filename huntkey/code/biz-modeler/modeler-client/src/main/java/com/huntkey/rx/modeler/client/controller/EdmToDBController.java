package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmToDBService;
import feign.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 *
 * @ClassName: EdmIndexController
 * @Description: 索引接口Controller
 * @author: zhangyu
 * @date: 2017年4月12日上午11:35:15
 *
 */
@RestController
@RequestMapping("/v1")
public class EdmToDBController {

    @Autowired
    public EdmToDBService edmToDBService;



    /**
     * 根据类编号生成表
     * @param classIds
     * @return
     */
    @RequestMapping(value = "/dbCreator", method = RequestMethod.POST)
    public Result dbCreator(@RequestBody String [] classIds) {
        Result result = edmToDBService.dbCreator(classIds);
        return result;
    }

    /**
     * 根据版本号生成表
     * @param version
     * @return
     */
    @RequestMapping(value = "/dbAllCreator", method = RequestMethod.GET)
    public Result dbAllCreator(@RequestParam(value = "version") String version) {
        Result result = edmToDBService.dbAllCreator(version);
        return result;
    }
}
