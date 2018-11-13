package com.huntkey.rx.sceo.formula.provider.engine.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.vo.SimpleParasVo;
import com.huntkey.rx.sceo.formula.provider.engine.feign.hystrix.BizModelerServiceHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author lulx on 2017/7/17 0017 上午 9:12
 */
@Component
@FeignClient(value = "modeler-client", fallback = BizModelerServiceHystrixImpl.class)
public interface BizModelerService {
    /**
     * 根据属性id查询属性和所属类
     * @param propertyId
     * @return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/v1/properties/getPropertyAndClass", method = RequestMethod.GET)
    Result getPropertyAndClass(@RequestParam(value = "propertyId") String propertyId);

    /**
     * 公式设计器类初始化   获取类及类属性 相关类及属性
     * @param classId 类id
     * @param classIdArray 相关类ids数组
     * @return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/v1/classes/getClassAndRelateClass", method = RequestMethod.GET)
    Result getClassAndRelateClass(@RequestParam(value = "classId") String classId, @RequestParam(value = "classIdArray") String[] classIdArray);

    /**
     * 修改属性
     * @param simpleParasVo
     * @date 2017/7/17 0017 上午 11:15 lulx
     * @return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/v1/properties", method = RequestMethod.PUT)
    Result updatePropertyFormula(@RequestBody SimpleParasVo simpleParasVo);

    /**
     * 删除触发条件
     *
     * @param condId
     * @return
     */
    @RequestMapping(value = "/v1/conds/deleteByEdcoCondId/{condId}", method = RequestMethod.DELETE)
    Result deleteRelTrigger(@PathVariable("condId") String condId);

    /**
     * 删除关联条件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/v1/links/formula/{id}", method = RequestMethod.PUT)
    Result deleteRelCond(@PathVariable("id") String id);

    /**
     * 删除属性限值
     *
     * @param map
     * @return
     */
    @RequestMapping(value = "/v1/properties/limitAndFormula", method = RequestMethod.PUT)
    Result deleteLimitOrFormula(@RequestBody Map<String, Object> map);

    /**
     * 获取类的基本信息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/v1/classes/{id}", method = RequestMethod.GET)
    Result getClassById(@PathVariable("id") String id);


    /**
     * 根据id获取类的属性列表
     */
    @RequestMapping(value = "/v1/classes/{id}/properties",method = RequestMethod.GET)
    Result getClassPropsById(@PathVariable("id")String id);
    /**
     * 根据属性id查询属性
     * @param edmpId
     * @return
     */
    @RequestMapping(value = "/v1/properties/{edmpId}",method = RequestMethod.GET)
    Result getEdmProp(@PathVariable(value = "edmpId") String edmpId);



    @RequestMapping(value = " /v1/classes/{id}/choose/properties",method = RequestMethod.GET)
    Result getChooseProperties(@PathVariable("id") String id);


}
