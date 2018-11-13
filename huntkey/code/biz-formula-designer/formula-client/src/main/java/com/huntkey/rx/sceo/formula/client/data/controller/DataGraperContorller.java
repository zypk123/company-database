package com.huntkey.rx.sceo.formula.client.data.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.data.feign.DataGraperService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lulx on 2017/6/14 0014.
 */
@RestController
public class DataGraperContorller {

    private static Logger log = LoggerFactory.getLogger(DataGraperContorller.class);

    @Autowired
    private DataGraperService dataGraperService;

    public Result grapData(){
        Result result = new Result();
        try {
            result = dataGraperService.grapData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

}
