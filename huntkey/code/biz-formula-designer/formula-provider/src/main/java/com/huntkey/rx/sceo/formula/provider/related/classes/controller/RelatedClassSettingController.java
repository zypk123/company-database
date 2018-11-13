package com.huntkey.rx.sceo.formula.provider.related.classes.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.model.TfdClassRelated;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.vo.ClassRelatedVo;
import com.huntkey.rx.sceo.formula.provider.related.classes.service.RelatedClassSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    RelatedClassSettingService relatedClassSettingService;

    /**
     * 根据关联公式id查询相关类列表
     *
     * @param formulaId 关联公式id
     * @return
     */
    @RequestMapping(value = "/{formulaId}", method = RequestMethod.GET)
    public Result loadRelatedClasses(@PathVariable("formulaId") String formulaId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call relatedClassSetting loadRelatedClasses service");
            List<TfdClassRelated> tfdClassRelateds = relatedClassSettingService.loadRelatedClasses(formulaId);
            result.setData(tfdClassRelateds);
        } catch (Exception e) {
            log.error("根据关联公式id查询相关类列表发生错误", e);
            throw new RuntimeException("根据关联公式id查询相关类列表发生错误", e);
        }
        return result;
    }

    /**
     * 根据关联clssClassId和type查询相关类列表
     *
     * @param clssClassId 关联类id
     * @param type        2 查询新增的类型
     * @return
     */
    @RequestMapping(value = "/loadRelatedClassesByClssClassIdAndType/{clssLinkClassId}/{type}", method = RequestMethod.GET)
    public Result loadRelatedClassesByClssClassIdAndType(@PathVariable("clssLinkClassId") String clssLinkClassId, @PathVariable("type") Integer type) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call relatedClassSetting loadRelatedClasses service");
            List<TfdClassRelated> tfdClassRelateds = relatedClassSettingService.loadRelatedClassesByClssClassIdAndType(clssLinkClassId, type);
            result.setData(tfdClassRelateds);
        } catch (Exception e) {
            log.error("根据关联公式id查询相关类列表发生错误", e);
            throw new RuntimeException("根据关联公式id查询相关类列表发生错误", e);
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
    public Result saveRrelatedClasses(@Validated @RequestBody TfdClassRelated tfdClassRelated) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call relatedClassSetting saveRrelatedClasses service");
            // 相关类ID
            String clssId = tfdClassRelated.getClssId();
            // 待校验的相关类别名名称
            String clssAliasName = tfdClassRelated.getClssAliasName();
            // 相关类所在公式ID
            String clssFormulaId = tfdClassRelated.getClssFormulaId();
            String errorStr = relatedClassSettingService.checkNameUnique(clssAliasName, clssFormulaId, clssId);
            // 校验失败
            if (!StringUtil.isNullOrEmpty(errorStr)) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }
            relatedClassSettingService.saveRrelatedClasses(tfdClassRelated);
            // 返回ID
            result.setData(tfdClassRelated.getClssId());
        } catch (Exception e) {
            log.error("新增相关类发生错误", e);
            throw new RuntimeException("新增相关类发生错误", e);
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
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call relatedClassSetting saveCondition service");
            relatedClassSettingService.saveCondition(tplCondition);
            // 返回ID
            result.setData(tplCondition.getCndtId());
        } catch (Exception e) {
            log.error("新增条件发生错误", e);
            throw new RuntimeException("新增条件发生错误", e);
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
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call relatedClassSetting updateCondition service");
            relatedClassSettingService.updateCondition(tplCondition);
            // 返回ID
            result.setData(tplCondition.getCndtId());
        } catch (Exception e) {
            log.error("修改条件发生错误", e);
            throw new RuntimeException("修改条件发生错误", e);
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
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            relatedClassSettingService.removeCondition(cndtId);
        } catch (Exception e) {
            log.error("根据条件id删除条件发生错误", e);
            throw new RuntimeException("根据条件id删除条件发生错误", e);
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
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            relatedClassSettingService.removeAllConditions(cndtIdArr);
        } catch (Exception e) {
            log.error("根据条件id数组删除所有条件发生错误", e);
            throw new RuntimeException("根据条件id数组删除所有条件发生错误", e);
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
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            List<TplCondition> tplConditions = relatedClassSettingService.queryCondisionsByClssId(clssId);
            result.setData(tplConditions);
        } catch (Exception e) {
            log.error("根据公式相关类id查找条件列表发生错误", e);
            throw new RuntimeException("根据公式相关类id查找条件列表条件发生错误", e);
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
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            if (!StringUtil.isNullOrEmpty(classIdArr) && classIdArr.length > 0) {
                relatedClassSettingService.updateRelatedClassesWithFormula(classIdArr, formulaId);
            } else {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("classIdArr不能为空");
            }
        } catch (Exception e) {
            log.error("相关类关联公式更新接口发生错误", e);
            throw new RuntimeException("相关类关联公式更新接口发生错误", e);
        }
        return result;
    }

    /**
     * 在删除相关类之前校验
     *
     * @param clssId 相关类ID
     * @return
     */
    @RequestMapping(value = "/checkDelete/{clssId}", method = RequestMethod.GET)
    public Result checkDelete(@PathVariable("clssId") String clssId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String errMsg = null;
        try {
            errMsg = relatedClassSettingService.existLocalVar(clssId);
            if (null != errMsg) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errMsg);
                return result;
            }
            errMsg = relatedClassSettingService.existClassRelated(clssId);
            if (null != errMsg) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errMsg);
                return result;
            }
        } catch (Exception e) {
            log.error("在删除相关类之前校验接口发生错误", e);
            throw new RuntimeException("在删除相关类之前校验接口发生错误", e);
        }
        return result;
    }

    /**
     * 删除相关类
     *
     * @param clssId
     * @return
     */
    @RequestMapping(value = "/deleteClassRelated/{clssId}", method = RequestMethod.DELETE)
    public Result deleteClassRelated(@PathVariable("clssId") String clssId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            // 删除之前的校验
            Result result1 = this.checkDelete(clssId);
            // 校验不通过
            if (result1.getRetCode() == -2) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(result1.getErrMsg());
                return result;
            } else {
                // 校验通过，执行删除
                relatedClassSettingService.deleteClassRelated(clssId);
            }
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            log.error("删除相关类接口发生错误", e);
            throw new RuntimeException("删除相关类接口发生错误", e);
        }
        return result;
    }

    /**
     * 根据formulaId初始化相关类
     *
     * @param formulaId
     * @return
     */
    @RequestMapping(value = "/initRelateClasses/{formulaId}", method = RequestMethod.POST)
    public Result initRelatedClasses(@PathVariable("formulaId") String formulaId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            TfdClassRelated classRelated = relatedClassSettingService.initRelatedClasses(formulaId);
            result.setData(classRelated);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            log.error("根据formulaId初始化相关类接口出错! :", e);
            throw new RuntimeException("根据formulaId初始化相关类接口出错! :", e);
        }
        return result;
    }

    /**
     * 根据clssLinkClassId初始化相关类
     *
     * @param formulaId
     * @return
     */
    @RequestMapping(value = "/initRelatedClassesByClssLinkClassId/{clssLinkClassId}", method = RequestMethod.POST)
    public Result initRelatedClassesByClssLinkClassId(@PathVariable("clssLinkClassId") String clssLinkClassId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            TfdClassRelated classRelated = relatedClassSettingService.initRelatedClassesByClssLinkClassId(clssLinkClassId);
            result.setData(classRelated);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            log.error("根据formulaId初始化相关类接口出错! :", e);
            throw new RuntimeException("根据formulaId初始化相关类接口出错! :", e);
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
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            relatedClassSettingService.updateClassRelated(tfdClassRelated);
            result.setData(tfdClassRelated.getClssId());
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            log.error("修改相关类接口出错! :", e);
            throw new RuntimeException("修改相关类接口出错! :", e);
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
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            ClassRelatedVo classRelatedVo = relatedClassSettingService.getClassRelatedAndConditionsByClssId(clssId);
            result.setData(classRelatedVo);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            log.error("根据相关类ID查找相关类及其条件列表接口出错! :", e);
            throw new RuntimeException("根据相关类ID查找相关类及其条件列表接口出错! :", e);
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
    public Result saveOrUpdateClassRelatedAndConditions(@Validated @RequestBody ClassRelatedVo classRelatedVo) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            // 相关类ID
            String clssId = classRelatedVo.getClassRelated().getClssId();
            // 待校验的相关类别名名称
            String clssAliasName = classRelatedVo.getClassRelated().getClssAliasName();
            // 相关类所在公式ID
            String clssFormulaId = classRelatedVo.getClassRelated().getClssFormulaId();
            String clssLinkClassId = classRelatedVo.getClassRelated().getClssLinkClassId();
            // 类型
            Integer type = classRelatedVo.getClassRelated().getType();
            String errorStr = "";
            if (type != null && type != 2) {
                // 相关类名称唯一性校验
                errorStr = relatedClassSettingService.checkNameUnique(clssAliasName, clssFormulaId, clssId);
            } else {
                errorStr = relatedClassSettingService.checkNameUniqueForClssLinkClassId(clssAliasName, clssLinkClassId, clssId);
            }

            if (!StringUtil.isNullOrEmpty(errorStr)) {
                // 校验失败
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg(errorStr);
                return result;
            }

            int retInt = relatedClassSettingService.saveOrUpdateClassRelatedAndConditions(classRelatedVo);
            result.setData(retInt);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            log.error("保存相关类及其条件列表接口出错! :", e);
            throw new RuntimeException("保存相关类及其条件列表接口出错! :", e);
        }
        return result;
    }

    @RequestMapping(value = "/physicalDeleteClassRelated/{clssId}",method = RequestMethod.DELETE)
    public Result physicalDeleteClassRelated(@PathVariable("clssId") String clssId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            relatedClassSettingService.physicalDeleteClassRelated(clssId);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            log.error("物理删除相关类接口发生错误", e);
            throw new RuntimeException("物理删除相关类接口发生错误", e);
        }
        return result;
    }

}
