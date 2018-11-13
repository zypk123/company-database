package com.huntkey.rx.sceo.formula.provider.audit.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.audit.feign.edm.EdmHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhouyou on 2017/10/24.
 */
@FeignClient(value = "serviceCenter-provider",fallback = EdmHystrix.class)
@Service
public interface EdmInterface {

     /**
      * addResources
      * @param data
      * @return
      */
     @RequestMapping(value = "/servicecenter/add",method = RequestMethod.POST)
     Result addResources(@RequestBody String data);

     /**
      * getResources
      * @param data
      * @return
      */
     @RequestMapping(value = "/servicecenter/find",method = RequestMethod.POST)
     Result getResources(@RequestBody String data);
}
