package com.huntkey.rx.sceo.formula.client.related.classes.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.related.classes.feign.RelatedClassSettingService;
import com.huntkey.rx.sceo.formula.common.model.TfdClassRelated;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.vo.ClassRelatedVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 相关类设置Controller
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@RestController
@RequestMapping("/relatedClasses")
public class RelatedClassSettingController {

    private static Logger log = LoggerFactory.getLogger(RelatedClassSettingController.class);

    @Autowired
    private RelatedClassSettingService relatedClassSettingService;

    /**
     * 相关类加载
     *
     * @param formulaId 关联公式id
     * @return
     */
    @RequestMapping(value = "/{formulaId}", method = RequestMethod.GET)
    public Result loadRelatedClasses(@PathVariable("formulaId") String formulaId) {
        Result result = null;
        try {
            result = relatedClassSettingService.loadRelatedClasses(formulaId);
        } catch (Exception e) {
            log.error("相关类加载出现错误:", e);
            throw new RuntimeException("相关类加载出现错误:", e);
        }
        return result;
    }

    /**
     * 根据关联clssClassId和type查询相关类列表
     *
     * @param clssClassId 关联类id
     * @param type 2 查询新增的类型
     * @return
     */
    @RequestMapping(value = "/loadRelatedClassesByClssClassIdAndType/{clssLinkClassId}/{type}", method = RequestMethod.GET)
    public Result loadRelatedClassesByClssClassIdAndType(@PathVariable("clssLinkClassId") String clssLinkClassId,@PathVariable("type") Integer type) {
        Result result = null;
        try {
            result = relatedClassSettingService.loadRelatedClassesByClssClassIdAndType(clssLinkClassId,type);
        } catch (Exception e) {
            log.error("相关类加载出现错误:", e);
            throw new RuntimeException("相关类加载出现错误:", e);
        }
        return result;
    }

    /**
     * 新增相关类
     *
     * @param tfdClassRelated
     * @return
     */
    @RequestMapping(value = "/saveRrelatedClasses", method = RequestMethod.POST)
    public Result saveRrelatedClasses(@RequestBody TfdClassRelated tfdClassRelated) {
        Result result = null;
        try {
            result = relatedClassSettingService.saveRrelatedClasses(tfdClassRelated);
        } catch (Exception e) {
            log.error("新增相关类出现错误:", e);
            throw new RuntimeException("相新增相关类出现错误:", e);
        }
        return result;
    }

    /**
     * 新增条件
     *
     * @param tplCondition
     * @return
     */
    @RequestMapping(value = "/conditions", method = RequestMethod.POST)
    public Result saveCondition(@RequestBody TplCondition tplCondition) {
        Result result = null;
        try {
            result = relatedClassSettingService.saveCondition(tplCondition);
        } catch (Exception e) {
            log.error("新增条件出现错误:", e);
            throw new RuntimeException("新增条件出现错误:", e);
        }
        return result;
    }

    /**
     * 修改条件
     *
     * @param tplCondition
     * @return
     */
    @RequestMapping(value = "/conditions", method = RequestMethod.PUT)
    public Result updateCondition(@RequestBody TplCondition tplCondition) {
        Result result = null;
        try {
            result = relatedClassSettingService.updateCondition(tplCondition);
        } catch (Exception e) {
            log.error("新增条件出现错误:", e);
            throw new RuntimeException("新增条件出现错误:", e);
        }
        return result;
    }

    /**
     * 变量查询(可通过变量名模糊查询)
     *
     * @param varName
     * @return
     */
    @RequestMapping(value = "/queryVariants", method = RequestMethod.GET)
    public Result queryVariants(@RequestParam(required = false, defaultValue = "", value = "varName") String varName) {
        Result result = null;
        try {
            result = relatedClassSettingService.queryVariants(varName);
        } catch (Exception e) {
            log.error("变量查询出现错误:", e);
            throw new RuntimeException("变量查询出现错误:", e);
        }
        return result;
    }

    /**
     * 根据条件id删除条件
     *
     * @param cndtId
     * @return
     */
    @RequestMapping(value = "/removeCondition/{cndtId}", method = RequestMethod.DELETE)
    public Result removeCondition(@PathVariable("cndtId") String cndtId) {
        Result result = null;
        try {
            result = relatedClassSettingService.removeCondition(cndtId);
        } catch (Exception e) {
            log.error("根据条件id删除条件出现错误:", e);
            throw new RuntimeException("根据条件id删除条件出现错误:", e);
        }
        return result;
    }

    /**
     * 根据条件id数组删除所有条件
     *
     * @param cndtIdArr
     * @return
     */
    @RequestMapping(value = "/removeAllConditions", method = RequestMethod.DELETE)
    public Result removeAllConditions(@RequestParam(required = false, value = "cndtIdArr", defaultValue = "") String[] cndtIdArr) {
        Result result = null;
        try {
            result = relatedClassSettingService.removeAllConditions(cndtIdArr);
        } catch (Exception e) {
            log.error("根据条件id数组删除所有条件出现错误:", e);
            throw new RuntimeException("根据条件id数组删除所有条件出现错误:", e);
        }
        return result;
    }

    /**
     * 根据公式相关类id查找条件列表
     *
     * @param clssId 相关类id
     * @return
     */
    @RequestMapping(value = "/queryCondisionsByClssId/{clssId}", method = RequestMethod.GET)
    public Result queryCondisionsByClssId(@PathVariable("clssId") String clssId) {
        Result result = null;
        try {
            result = relatedClassSettingService.queryCondisionsByClssId(clssId);
        } catch (Exception e) {
            log.error("根据公式相关类id查找条件列表出现错误:", e);
            throw new RuntimeException("根据公式相关类id查找条件列表出现错误:", e);
        }
        return result;
    }

    /**
     * 相关类关联公式更新接口
     *
     * @param classIdArr 相关类id数组
     * @param formulaId  公式id
     * @return
     */
    @RequestMapping(value = "/{formulaId}/updateRelatedClassesWithFormula", method = RequestMethod.PUT)
    public Result updateRelatedClassesWithFormula(@RequestParam(required = true, value = "classIdArr", defaultValue = "") String[] classIdArr, @PathVariable(value = "formulaId") String formulaId) {
        Result result = null;
        try {
            result = relatedClassSettingService.updateRelatedClassesWithFormula(classIdArr, formulaId);
        } catch (Exception e) {
            log.error("相关类关联公式更新接口出现错误:", e);
            throw new RuntimeException("相关类关联公式更新接口出现错误:", e);
        }
        return result;
    }

    /**
     * 删除相关类接口
     *
     * @param clssId 相关类ID
     * @return
     */
    @RequestMapping(value = "/deleteClassRelated/{clssId}", method = RequestMethod.DELETE)
    public Result deleteClassRelated(@PathVariable("clssId") String clssId) {
        Result result = null;
        try {
            result = relatedClassSettingService.deleteClassRelated(clssId);
        } catch (Exception e) {
            log.error("删除相关类接口出现错误:", e);
            throw new RuntimeException("删除相关类接口出现错误:", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 根据formulaId初始化相关类
     * 取暂存或新建相关类
     * @pars [formulaId] 公式ID
     * @date 2017/7/6 0006 上午 11:10 lulx
     **/
    @RequestMapping(value = "/initRelateClasses/{formulaId}", method = RequestMethod.POST)
    public Result initRelatedClasses(@PathVariable("formulaId") String formulaId) {
        Result result = null;
        try {
            result = relatedClassSettingService.initRelatedClasses(formulaId);
        } catch (Exception e) {
            String errMsg = "根据formulaId初始化相关类接口出错! :";
            log.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc 根据clssLinkClassId初始化相关类
     * 取暂存或新建相关类
     * @pars [clssLinkClassId] 公式ID
     * @date 2017/7/6 0006 上午 11:10 刘佳
     **/
    @RequestMapping(value = "/initRelatedClassesByClssLinkClassId/{clssLinkClassId}", method = RequestMethod.POST)
    public Result initRelatedClassesByClssLinkClassId(@PathVariable("clssLinkClassId") String clssLinkClassId) {
        Result result = null;
        try {
            result = relatedClassSettingService.initRelatedClassesByClssLinkClassId(clssLinkClassId);
        } catch (Exception e) {
            String errMsg = "根据formulaId初始化相关类接口出错! :";
            log.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }
        return result;
    }

    /**
     * 修改相关类
     *
     * @param tfdClassRelated
     * @return
     */
    @RequestMapping(value = "/updateClassRelated", method = RequestMethod.PUT)
    public Result updateClassRelated(@RequestBody TfdClassRelated tfdClassRelated) {
        Result result = null;
        try {
            result = relatedClassSettingService.updateClassRelated(tfdClassRelated);
        } catch (Exception e) {
            log.error("修改相关类接口出错!", e);
            throw new RuntimeException("修改相关类接口出错!", e);
        }
        return result;
    }

    /**
     * 根据相关类ID查找相关类及其条件列表
     *
     * @param clssId
     * @return
     */
    @RequestMapping(value = "/getClassRelatedAndConditionsByClssId/{clssId}", method = RequestMethod.GET)
    public Result getClassRelatedAndConditionsByClssId(@PathVariable("clssId") String clssId) {
        Result result = null;
        try {
            result = relatedClassSettingService.getClassRelatedAndConditionsByClssId(clssId);
        } catch (Exception e) {
            log.error("根据相关类ID查找相关类及其条件列表接口出错!", e);
            throw new RuntimeException("根据相关类ID查找相关类及其条件列表接口出错!", e);
        }
        return result;
    }

    /**
     * 保存相关类及其条件列表
     *
     * @param classRelatedVo
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateClassRelatedAndConditions", method = RequestMethod.POST)
    public Result saveOrUpdateClassRelatedAndConditions(@RequestBody ClassRelatedVo classRelatedVo) {
        Result result = null;
        try {
            result = relatedClassSettingService.saveOrUpdateClassRelatedAndConditions(classRelatedVo);
        } catch (Exception e) {
            log.error("保存相关类及其条件列表接口出错!", e);
            throw new RuntimeException("保存相关类及其条件列表接口出错!", e);
        }
        return result;
    }
}
