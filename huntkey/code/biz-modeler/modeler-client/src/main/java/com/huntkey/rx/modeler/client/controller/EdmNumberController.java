package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmNumberService;
import com.huntkey.rx.modeler.common.model.EdmNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/numbers")
public class EdmNumberController {
    private static Logger log = LoggerFactory.getLogger(EdmNumberController.class);

    @Autowired
    private EdmNumberService edmNumberService;

    /**
     * 修改流水号
     * @param edmNumber
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result update(@RequestBody EdmNumber edmNumber){
        Result result = edmNumberService.update(edmNumber);
        return result;
    }

}
