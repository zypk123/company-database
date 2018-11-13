package com.huntkey.rx.sceo.formula.client.design.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.design.feign.hystrix.ModelerScannerHystrixImpl;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Modeler扫描feign接口
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@FeignClient(value = "modeler-provider", url="10.3.98.154:2002", fallback = ModelerScannerHystrixImpl.class)
public interface ModelerScannerService {

    /**
     * 根据类的id获取类的信息，包含关联关系
     *
     * @param classId 类id
     * @return
     */
    @RequestMapping(value = "/classes/relate/{classId}", method = RequestMethod.GET)
    Result loadClass(@PathVariable(value = "classId") String classId);

    /**
     * 根据类的id获取类的信息，包含关联关系（包含父类关联关系）
     *
     * @param classId 类id
     * @return
     */
    @RequestMapping(value = "/classes/relateClasses/{classId}", method = RequestMethod.GET)
    Result loadrelateClasses(@PathVariable(value = "classId") String classId);

    /**
     * 根据类的id获取类的属性列表
     *
     * @param classId 类id
     * @param flag
     * @return
     */
    @RequestMapping(value = "/properties/tree/{classId}/{flag}", method = RequestMethod.GET)
    Result loadAttrCascade(@PathVariable(value = "classId") String classId, @PathVariable(value = "flag") int flag);

    /**
     * 根据模型id查找类树
     *
     * @param modelerId 模型id
     * @return
     */
    @RequestMapping(value = "/modelers/{modelerId}/classes", method = RequestMethod.GET)
    Result loadAllClasses(@PathVariable("modelerId") String modelerId);

    /**
     * 根据类的id获取类的信息，不包含关联关系
     *
     * @param classId 类id
     * @return
     */
    @RequestMapping(value = "/classes/{classId}", method = RequestMethod.GET)
    Result getClassById(@PathVariable(value = "classId") String classId);

    /**
     * 根据属性ID查询属性基本信息
     *
     * @param propId 属性ID
     * @return
     */
    @RequestMapping(value = "/properties/{propId}", method = RequestMethod.GET)
    Result getPropById(@PathVariable(value = "propId") String propId);

    /**
     * 根据方法id跟类id查询方法和类名
     *
     * @param methodId
     * @param classId
     * @return
     */
    @RequestMapping(value = "/methods/{methodId}/{classId}", method = RequestMethod.GET)
    Result getClassAndMethod(@PathVariable(value = "methodId") String methodId, @PathVariable(value = "classId") String classId);

    /**
     * 通过方法id获取方法
     * @param methodId
     * @return
     */
    @RequestMapping(value = "/methods/{methodId}", method = RequestMethod.GET)
    Result getMethod(@PathVariable(value = "methodId") String methodId);


}
