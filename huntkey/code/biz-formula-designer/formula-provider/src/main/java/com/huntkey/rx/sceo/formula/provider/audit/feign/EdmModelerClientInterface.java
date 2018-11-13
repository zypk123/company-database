package com.huntkey.rx.sceo.formula.provider.audit.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.audit.feign.edm.EdmHystrix;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author zhouyou on 2017/10/24.
 */

@FeignClient(value = "modeler-client",fallback = EdmHystrix.class)
public interface EdmModelerClientInterface {

     /**
      * selectGroups
      * @param edmcId
      * @param edmpId
      * @param isSource
      * @return
      */
     @RequestMapping(value = "/v1/properties/groups",method = RequestMethod.GET)
     Result selectGroups(@RequestParam(value = "edmcId") String edmcId,
                         @RequestParam(value = "edmpId") String edmpId,
                         @RequestParam(value = "isSource") String isSource);

     /**
      * getProperties
      * @param edmcId
      * @return
      */
     @RequestMapping(value = "/v1/classes/{edmcId}/properties",method = RequestMethod.GET)
     Result getProperties(@PathVariable(value = "edmcId") String edmcId);

     /**
      * 根据类id查询类属性
      * @param edmcId
      * @return
      */
     @RequestMapping(value = "/v1/classes/{edmcId}",method = RequestMethod.GET)
     Result getEdmClass(@PathVariable(value = "edmcId") String edmcId);

     /**
      * 根据类id查询本类以及父类的所有信息
      * @param classId
      * @return
      */
     @RequestMapping(value = "/v1/classes/{classId}/props",method = RequestMethod.GET)
     Result getPsClass(@PathVariable(value = "classId") String classId);

     /**
      * 根据属性id查询属性
      * @param edmpId
      * @return
      */
     @RequestMapping(value = "/v1/properties/{edmpId}",method = RequestMethod.GET)
     Result getEdmProp(@PathVariable(value = "edmpId") String edmpId);

}


