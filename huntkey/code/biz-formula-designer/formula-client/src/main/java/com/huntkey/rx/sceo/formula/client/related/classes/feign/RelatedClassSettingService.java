package com.huntkey.rx.sceo.formula.client.related.classes.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.related.classes.feign.hystrix.RelatedClassSettingHystrixImpl;
import com.huntkey.rx.sceo.formula.common.model.TfdClassRelated;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.vo.ClassRelatedVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * 相关类设置Feign接口
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = RelatedClassSettingHystrixImpl.class)
public interface RelatedClassSettingService {

    /**
     * 根据关联公式id查询相关类列表
     *
     * @param formulaId 关联公式id
     * @return
     */
    @RequestMapping(value = "/relatedClasses/{formulaId}", method = RequestMethod.GET)
    Result loadRelatedClasses(@PathVariable("formulaId") String formulaId);

    /**
     * 根据关联classid和type查找公式相关类列表
     *
     * @param clssLinkClassId 关联类id
     * @param type 2 查询新增的类型
     * @return
     */
    @RequestMapping(value = "/relatedClasses/loadRelatedClassesByClssClassIdAndType/{clssLinkClassId}/{type}", method = RequestMethod.GET)
    Result loadRelatedClassesByClssClassIdAndType(@PathVariable("clssLinkClassId") String clssLinkClassId,@PathVariable("type") Integer type);

    /**
     * 新增相关类
     *
     * @param tfdClassRelated
     * @return
     */
    @RequestMapping(value = "/relatedClasses/saveRrelatedClasses", method = RequestMethod.POST)
    Result saveRrelatedClasses(@RequestBody TfdClassRelated tfdClassRelated);

    /**
     * 新增条件
     *
     * @param tplCondition
     * @return
     */
    @RequestMapping(value = "/relatedClasses/conditions", method = RequestMethod.POST)
    Result saveCondition(@RequestBody TplCondition tplCondition);

    /**
     * 修改条件
     *
     * @param tplCondition
     * @return
     */
    @RequestMapping(value = "/relatedClasses/conditions", method = RequestMethod.PUT)
    Result updateCondition(@RequestBody TplCondition tplCondition);

    /**
     * 变量查询(可通过变量名模糊查询)
     *
     * @param varName
     * @return
     */
    @RequestMapping(value = "/variantMgr/loadSystemVariants", method = RequestMethod.GET)
    Result queryVariants(@RequestParam(required = false, defaultValue = "", value = "varName") String varName);

    /**
     * 根据条件id删除条件
     *
     * @param cndtId
     * @return
     */
    @RequestMapping(value = "/relatedClasses/removeCondition/{cndtId}", method = RequestMethod.DELETE)
    Result removeCondition(@PathVariable("cndtId") String cndtId);

    /**
     * 根据条件id数组删除所有条件
     *
     * @param cndtIdArr
     * @return
     */
    @RequestMapping(value = "/relatedClasses/removeAllConditions", method = RequestMethod.DELETE)
    Result removeAllConditions(@RequestParam(required = false, value = "cndtIdArr", defaultValue = "") String[] cndtIdArr);

    /**
     * 相关类关联公式更新接口
     *
     * @param classIdArr 相关类id数组
     * @param formulaId  公式id
     * @return
     */
    @RequestMapping(value = "/relatedClasses/{formulaId}/updateRelatedClassesWithFormula", method = RequestMethod.PUT)
    Result updateRelatedClassesWithFormula(@RequestParam(required = true, value = "classIdArr", defaultValue = "") String[] classIdArr, @PathVariable(value = "formulaId") String formulaId);


    /**
     * 根据公式相关类id查找条件列表
     *
     * @param clssId 相关类id
     * @return
     */
    @RequestMapping(value = "/relatedClasses/queryCondisionsByClssId/{clssId}", method = RequestMethod.GET)
    Result queryCondisionsByClssId(@PathVariable("clssId") String clssId);

    /**
     * 删除相关类
     *
     * @param clssId
     * @return
     */
    @RequestMapping(value = "/relatedClasses/deleteClassRelated/{clssId}", method = RequestMethod.DELETE)
    Result deleteClassRelated(@PathVariable("clssId") String clssId);

    /**
     * 根据公式ID初始化相关类
     *
     * @param formulaId
     * @return
     */
    @RequestMapping(value = "/relatedClasses/initRelateClasses/{formulaId}", method = RequestMethod.POST)
    Result initRelatedClasses(@PathVariable("formulaId") String formulaId);

    /**
     * 根据clssLinkClassId初始化相关类
     *
     * @param clssLinkClassId
     * @return
     */
    @RequestMapping(value = "/relatedClasses/initRelatedClassesByClssLinkClassId/{clssLinkClassId}", method = RequestMethod.POST)
    Result initRelatedClassesByClssLinkClassId(@PathVariable("clssLinkClassId") String clssLinkClassId);


    /**
     * 修改相关类
     *
     * @param tfdClassRelated
     * @return
     */
    @RequestMapping(value = "/relatedClasses/updateClassRelated", method = RequestMethod.PUT)
    Result updateClassRelated(@RequestBody TfdClassRelated tfdClassRelated);


    /**
     * 根据相关类ID查找相关类及其条件列表
     *
     * @param clssId
     * @return
     */
    @RequestMapping(value = "/relatedClasses/getClassRelatedAndConditionsByClssId/{clssId}", method = RequestMethod.GET)
    Result getClassRelatedAndConditionsByClssId(@PathVariable("clssId") String clssId);

    /**
     * 保存相关类及其条件列表
     *
     * @param classRelatedVo
     * @return
     */
    @RequestMapping(value = "/relatedClasses/saveOrUpdateClassRelatedAndConditions", method = RequestMethod.POST)
    Result saveOrUpdateClassRelatedAndConditions(@RequestBody ClassRelatedVo classRelatedVo);


}
