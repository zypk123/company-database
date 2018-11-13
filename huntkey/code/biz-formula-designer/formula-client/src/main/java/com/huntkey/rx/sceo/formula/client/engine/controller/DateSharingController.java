package com.huntkey.rx.sceo.formula.client.engine.controller;


import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.engine.feign.DataSharingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author chenfei on 2017/5/15.
 */
@RestController
@RequestMapping("/dateSharing")
public class DateSharingController {

    private static Logger log = LoggerFactory.getLogger(DateSharingController.class);

    @Autowired
    private DataSharingService dataSharingService;

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public Result search(@RequestParam(required = false, defaultValue = "", value = "id") String id) {
        Result result = null;
        try {
            result = dataSharingService.findWordListById(id);
        } catch (Exception e) {
            log.error("检查公式合法性接口出错", e);
            throw new RuntimeException("检查公式合法性接口出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/findClassById/{id}", method = RequestMethod.GET)
    public Result findClassById(@PathVariable("id") String id) {
        Result result = null;
        try {
            result = dataSharingService.getClassById(id);
        } catch (Exception e) {
            log.error("检查公式合法性接口出错", e);
            throw new RuntimeException("检查公式合法性接口出错", e);
        }
        return result;
    }

}
