package com.huntkey.rx.sceo.formula.client.design.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.design.feign.ModelerScannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Modeler扫描Controller
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@RestController
@RequestMapping("/modelerScanner")
public class ModelerScannerController {

    private static Logger log = LoggerFactory.getLogger(ModelerScannerController.class);

    @Autowired
    ModelerScannerService modelerScannerService;

    /**
     * 预定义类加载
     *
     * @param classId 类的id
     * @return
     */
    @RequestMapping(value = "/classes/{classId}", method = RequestMethod.GET)
    public Result loadClass(@PathVariable(value = "classId") String classId) {
        Result result = null;
        try {
            result = modelerScannerService.loadClass(classId);
            if (result.getRetCode() == 0) {
                result.setErrMsg("Modeler接口出现错误...");
            }
        } catch (Exception e) {
            log.error("预定义类加载出现错误:", e);
            throw new RuntimeException("预定义类加载出现错误:", e);
        }
        return result;
    }

    /**
     * 属性级联加载
     *
     * @param classId 类的id
     * @return
     */
    @RequestMapping(value = "/classes/{classId}/{flag}/properties", method = RequestMethod.GET)
    public Result loadAttrCascade(@PathVariable(value = "classId") String classId, @PathVariable(value = "flag") int flag) {
        Result result = null;
        try {
            result = modelerScannerService.loadAttrCascade(classId, flag);
            if (result.getRetCode() == 0) {
                result.setErrMsg("Modeler接口出现错误...");
            }
        } catch (Exception e) {
            log.error("属性级联加载出现错误:", e);
            throw new RuntimeException("属性级联加载出现错误:", e);
        }
        return result;
    }

    /**
     * 类树加载
     *
     * @param modelerId
     * @return
     */
    @RequestMapping(value = "/{modelerId}/classes", method = RequestMethod.GET)
    public Result loadAllClasses(@PathVariable("modelerId") String modelerId) {
        Result result = null;
        try {
            result = modelerScannerService.loadAllClasses(modelerId);
            if (result.getRetCode() == 0) {
                result.setErrMsg("Modeler接口出现错误...");
            }
        } catch (Exception e) {
            log.error("类树联加载出现错误:", e);
            throw new RuntimeException("类树联加载出现错误:", e);
        }
        return result;
    }

    /**
     * 根据类的id获取类的信息(不包含关联关系)
     *
     * @param classId 类id
     * @return
     */
    @RequestMapping(value = "/getClassById/{classId}", method = RequestMethod.GET)
    public Result getClassById(@PathVariable(value = "classId") String classId) {
        Result result = null;
        try {
            result = modelerScannerService.getClassById(classId);
            if (result.getRetCode() == 0) {
                result.setErrMsg("Modeler接口出现错误...");
            }
        } catch (Exception e) {
            log.error("根据类的id获取类的信息(不包含关联关系)出现错误:", e);
            throw new RuntimeException("根据类的id获取类的信息(不包含关联关系)出现错误:", e);
        }
        return result;
    }

    /**
     * 根据属性ID查询属性基本信息
     *
     * @param propId 属性id
     * @return
     */
    @RequestMapping(value = "/getPropById/{propId}", method = RequestMethod.GET)
    public Result getPropById(@PathVariable(value = "propId") String propId) {
        Result result = null;
        try {
            result = modelerScannerService.getPropById(propId);
            if (result.getRetCode() == 0) {
                result.setErrMsg("Modeler接口出现错误...");
            }
        } catch (Exception e) {
            log.error("根据属性ID查询属性基本信息出现错误:", e);
            throw new RuntimeException("根据属性ID查询属性基本信息出现错误:", e);
        }
        return result;
    }

    /**
     * 根据方法id跟类id查询方法和类名
     *
     * @param methodId
     * @param classId
     * @return
     */
    @RequestMapping(value = "/getClassAndMethod/{methodId}/{classId}", method = RequestMethod.GET)
    public Result getClassAndMethod(@PathVariable(value = "methodId") String methodId, @PathVariable(value = "classId") String classId) {
        Result result = null;
        try {
            result = modelerScannerService.getClassAndMethod(methodId, classId);
            if (result.getRetCode() == 0) {
                result.setErrMsg("Modeler接口出现错误...");
            }
        } catch (Exception e) {
            log.error("根据方法id跟类id查询方法和类名出现错误:", e);
            throw new RuntimeException("根据方法id跟类id查询方法和类名出现错误:", e);
        }
        return result;
    }

    /**
     * 根据方法id获取方法
     *
     * @param methodId
     * @return
     */
    @RequestMapping(value = "/getMethod/{methodId}", method = RequestMethod.GET)
    public Result getMethod(@PathVariable(value = "methodId") String methodId) {
        Result result = null;
        try {
            result = modelerScannerService.getMethod(methodId);
            if (result.getRetCode() == 0) {
                result.setErrMsg("Modeler接口出现错误...");
            }
        } catch (Exception e) {
            log.error("根据方法id获取方法出现错误:", e);
            throw new RuntimeException("根据方法id获取方法出现错误:", e);
        }
        return result;
    }

    /**
     *预定义类加载
     */
    @RequestMapping(value = "/classes/relateClasses/{classId}", method = RequestMethod.GET)
    public Result loadrelateClasses(@PathVariable(value = "classId") String classId) {
        Result result = null;
        try {
            result = modelerScannerService.loadrelateClasses(classId);
            if (result.getRetCode() == 0) {
                result.setErrMsg("Modeler接口出现错误...");
            }
        } catch (Exception e) {
            log.error("预定义类加载出现错误:", e);
            throw new RuntimeException("预定义类加载出现错误:", e);
        }
        return result;
    }

}
