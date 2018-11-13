package com.huntkey.rx.modeler.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhuyj on 2017/4/16.
 */
@FeignClient(value = "modeler-provider")
public interface EdmToDBService {


    @RequestMapping(value = "/dbCreator", method = RequestMethod.POST)
    Result dbCreator(@RequestBody String [] classIds);


    @RequestMapping(value = "/dbAllCreator", method = RequestMethod.GET)
    Result dbAllCreator(@RequestParam(value = "version") String version);


}
