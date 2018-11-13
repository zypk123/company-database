package com.huntkey.rx.sceo.formula.client.related.condition.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.related.condition.feign.RelatedConditionSettingService;
import com.huntkey.rx.sceo.formula.common.model.TacConditionRelated;
import com.huntkey.rx.sceo.formula.common.model.TacPropertyRelated;
import com.huntkey.rx.sceo.formula.common.model.vo.PropertyRelatedVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关联条件设置Controller
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@RestController
@RequestMapping("/relatedConditions")
public class RelatedConditionSettingController {

    private static Logger log = LoggerFactory.getLogger(RelatedConditionSettingController.class);

    @Autowired
    RelatedConditionSettingService relatedConditionSettingService;

    /**
     * 新增属性关联条件的条件
     *
     * @param tacConditionRelated
     * @return
     */
    @RequestMapping(value = "/saveConditionRelated", method = RequestMethod.POST)
    public Result saveConditionRelated(@RequestBody TacConditionRelated tacConditionRelated) {
        Result result = null;
        try {
            result = relatedConditionSettingService.saveConditionRelated(tacConditionRelated);
        } catch (Exception e) {
            log.error("新增关联条件的条件出现错误:", e);
            throw new RuntimeException("新增关联条件的条件出现错误:", e);
        }
        return result;
    }

    /**
     * 修改属性关联条件的条件
     *
     * @param tacConditionRelated
     * @return
     */
    @RequestMapping(value = "/updateConditionRelated", method = RequestMethod.PUT)
    public Result updateConditionRelated(@RequestBody TacConditionRelated tacConditionRelated) {
        Result result = null;
        try {
            result = relatedConditionSettingService.updateConditionRelated(tacConditionRelated);
        } catch (Exception e) {
            log.error("修改关联条件的条件出现错误:", e);
            throw new RuntimeException("修改关联条件的条件出现错误:", e);
        }
        return result;
    }

    /**
     * 查询属性关联条件的条件列表
     *
     * @return
     */
    @RequestMapping(value = "/queryAllConditionsRelated", method = RequestMethod.GET)
    public Result queryAllConditionsRelated() {
        Result result = null;
        try {
            result = relatedConditionSettingService.queryAllConditionsRelated();
        } catch (Exception e) {
            log.error("查询关联条件的条件列表出现错误:", e);
            throw new RuntimeException("查询关联条件的条件列表出现错误:", e);
        }
        return result;
    }

    /**
     * 通过id查询属性关联条件的条件
     *
     * @param cndrId
     * @return
     */
    @RequestMapping(value = "/queryConditionsRelatedByCndrId/{cndrId}", method = RequestMethod.GET)
    public Result queryConditionsRelatedByCndrId(@PathVariable("cndrId") String cndrId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.queryConditionsRelatedByCndrId(cndrId);
        } catch (Exception e) {
            log.error("通过id查询关联条件的条件出现错误:", e);
            throw new RuntimeException("通过id查询关联条件的条件出现错误:", e);
        }
        return result;
    }

    /**
     * 删除属性关联条件的条件
     *
     * @param cndrId
     * @return
     */
    @RequestMapping(value = "/removeConditionRelated/{cndrId}", method = RequestMethod.DELETE)
    public Result removeConditionRelated(@PathVariable("cndrId") String cndrId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.removeConditionRelated(cndrId);
        } catch (Exception e) {
            log.error("删除关联条件的条件接口出现错误:", e);
            throw new RuntimeException("删除关联条件的条件接口出现错误:", e);
        }
        return result;
    }

    /**
     * 新增属性关联条件
     *
     * @param tacPropertyRelated
     * @return
     */
    @RequestMapping(value = "/savePropRelated", method = RequestMethod.POST)
    public Result savePropRelated(@RequestBody TacPropertyRelated tacPropertyRelated) {
        Result result = null;

        try {
            result = relatedConditionSettingService.savePropRelated(tacPropertyRelated);
        } catch (Exception e) {
            log.error("新增关联条件出现错误:", e);
            throw new RuntimeException("新增关联条件出现错误:", e);
        }
        return result;
    }

    /**
     * 修改属性关联条件
     *
     * @param tacPropertyRelated
     * @return
     */
    @RequestMapping(value = "/updatePropRelated", method = RequestMethod.PUT)
    public Result updatePropRelated(@RequestBody TacPropertyRelated tacPropertyRelated) {
        Result result = null;
        try {
            result = relatedConditionSettingService.updatePropRelated(tacPropertyRelated);
        } catch (Exception e) {
            log.error("修改关联条件出现错误:", e);
            throw new RuntimeException("修改关联条件出现错误:", e);
        }
        return result;
    }

    /**
     * 通过属性关联ID查找关联属性及其条件列表
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/getPropertyRelatedCondition/{prplId}", method = RequestMethod.GET)
    public Result getPropertyRelatedCondition(@PathVariable("prplId") String prplId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.getPropertyRelatedCondition(prplId);
        } catch (Exception e) {
            log.error("通过属性关联ID查找关联属性及其条件列表出现错误:", e);
            throw new RuntimeException("通过属性关联ID查找关联属性及其条件列表出现错误:", e);
        }
        return result;
    }

    /**
     * 保存关联属性及其条件(关联条件)
     *
     * @param propertyRelatedVo
     * @return
     */
    @RequestMapping(value = "/saveOrUpdatePropRelatedAndConditionsForRelCon", method = RequestMethod.POST)
    public Result saveOrUpdatePropRelatedAndConditionsForRelCon(@RequestBody PropertyRelatedVo propertyRelatedVo) {
        Result result = null;
        try {
            result = relatedConditionSettingService.saveOrUpdatePropRelatedAndConditionsForRelCon(propertyRelatedVo);
        } catch (Exception e) {
            log.error("保存关联属性及其条件发生错误", e);
            throw new RuntimeException("保存关联属性及其条件发生错误", e);
        }
        return result;
    }

    /**
     * 保存关联属性及其条件(触发关联条件)
     *
     * @param propertyRelatedVo
     * @return
     */
    @RequestMapping(value = "/saveOrUpdatePropRelatedAndConditionsForTriCon", method = RequestMethod.POST)
    public Result saveOrUpdatePropRelatedAndConditionsForTriCon(@RequestBody PropertyRelatedVo propertyRelatedVo) {
        Result result = null;
        try {
            result = relatedConditionSettingService.saveOrUpdatePropRelatedAndConditionsForTriCon(propertyRelatedVo);
        } catch (Exception e) {
            log.error("保存关联属性及其条件发生错误", e);
            throw new RuntimeException("保存关联属性及其条件发生错误", e);
        }
        return result;
    }

    /**
     * 保存关联属性及其条件(触发关联条件)
     *
     * @param propertyRelatedVo
     * @return
     */
    @RequestMapping(value = "/saveOrUpdatePropRelatedAndConditionsForClassCon", method = RequestMethod.POST)
    public Result saveOrUpdatePropRelatedAndConditionsForClassCon(@RequestBody PropertyRelatedVo propertyRelatedVo) {
        Result result = null;
        try {
            result = relatedConditionSettingService.saveOrUpdatePropRelatedAndConditionsForClassCon(propertyRelatedVo);
        } catch (Exception e) {
            log.error("保存关联属性及其条件发生错误", e);
            throw new RuntimeException("保存关联属性及其条件发生错误", e);
        }
        return result;
    }

    /**
     * 保存关联属性及其条件(方法触发关联条件)
     *
     * @param propertyRelatedVo
     * @return
     */
    @RequestMapping(value = "/saveOrUpdatePropRelatedAndConditionsForMethodTriCon", method = RequestMethod.POST)
    public Result saveOrUpdatePropRelatedAndConditionsForMethodTriCon(@RequestBody PropertyRelatedVo propertyRelatedVo) {
        Result result = null;
        try {
            result = relatedConditionSettingService.saveOrUpdatePropRelatedAndConditionsForMethodTriCon(propertyRelatedVo);
        } catch (Exception e) {
            log.error("保存关联属性及其条件发生错误", e);
            throw new RuntimeException("保存关联属性及其条件发生错误", e);
        }
        return result;
    }

    /**
     * 根据关联属性ID查找公式
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/getPrplConditionDescByPrplId/{prplId}", method = RequestMethod.GET)
    public Result getPrplConditionDescByPrplId(@PathVariable("prplId") String prplId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.getPrplConditionDescByPrplId(prplId);
        } catch (Exception e) {
            log.error("根据关联属性ID查找公式发生错误", e);
            throw new RuntimeException("根据关联属性ID查找公式发生错误", e);
        }
        return result;
    }

    /**
     * 根据关联属性ID数组查找公式
     *
     * @param prplIdArr1
     * @param prplIdArr2
     * @return
     */
    @RequestMapping(value = "/getPrplConditionDescByPrplIdArr", method = RequestMethod.GET)
    public Result getPrplConditionDescByPrplIdArr(@RequestParam(required = false, value = "prplIdArr1") List<String> prplIdArr1, @RequestParam(required = false, value = "prplIdArr2") List<String> prplIdArr2) {
        Result result = null;
        try {
            result = relatedConditionSettingService.getPrplConditionDescByPrplIdArr(prplIdArr1, prplIdArr2);
        } catch (Exception e) {
            log.error("根据关联属性ID数组查找公式发生错误", e);
            throw new RuntimeException("根据关联属性ID数组查找公式发生错误", e);
        }
        return result;
    }

    /**
     * 初始化关联触发条件
     *
     * @param propId 属性ID
     * @return
     */
    @RequestMapping(value = "/initRelTriggerCond/{propId}/{prplId}", method = RequestMethod.GET)
    public Result initRelTriggerCond(@PathVariable("propId") String propId, @PathVariable("prplId") String prplId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.initRelTriggerCond(propId, prplId);
        } catch (Exception e) {
            log.error("初始化关联触发条件发生错误", e);
            throw new RuntimeException("初始化关联触发条件发生错误", e);
        }
        return result;
    }

    /**
     * 初始化方法关联触发条件
     *
     * @param propId 属性ID
     * @return
     */
    @RequestMapping(value = "/initMethodRelTriggerCond/{propId}", method = RequestMethod.GET)
    public Result initMethodRelTriggerCond(@PathVariable("propId") String propId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.initMethodRelTriggerCond(propId);
        } catch (Exception e) {
            log.error("初始化方法关联触发条件错误", e);
            throw new RuntimeException("初始化方法关联触发条件发生错误", e);
        }
        return result;
    }

    /**
     * 初始化关联条件
     *
     * @param propId  属性ID
     * @param propId2 属性2ID
     * @return
     */
    @RequestMapping(value = "/initRelCond/{propId}/{propId2}", method = RequestMethod.GET)
    public Result initRelCond(@PathVariable("propId") String propId, @PathVariable("propId2") String propId2) {
        Result result = null;
        try {
            result = relatedConditionSettingService.initRelCond(propId, propId2);
        } catch (Exception e) {
            log.error("根据关联属性ID数组查找公式发生错误", e);
            throw new RuntimeException("根据关联属性ID数组查找公式发生错误", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc modeler 根据属性id及对应的orm数据id查询关联类的数据
     * @pars [prplId, dataId] 关联公式ID  orm数据ID
     * @date 2017/8/18 0018 上午 9:42 lulx
     **/
    @RequestMapping(value = "/relCondDataByPro/{prplId}/{dataId}", method = RequestMethod.GET)
    public Result relCondDataByPro(@PathVariable("prplId") String prplId, @PathVariable("dataId") String dataId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.relCondDataByPro(prplId, dataId);
        } catch (Exception e) {
            log.error("modeler 根据属性id及对应的orm数据id查询关联类的数据 接口错误", e);
            throw new RuntimeException("modeler 根据属性id及对应的orm数据id查询关联类的数据 接口错误", e);
        }
        return result;
    }

    /**
     * 通过ID查找关联属性(触发条件)
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/selectByPrimaryKey/{prplId}", method = RequestMethod.GET)
    public Result selectByPrimaryKey(@PathVariable("prplId") String prplId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.selectByPrimaryKey(prplId);
        } catch (Exception e) {
            log.error("通过ID查找关联属性(触发条件)发生错误", e);
            throw new RuntimeException("通过ID查找关联属性(触发条件)发生错误", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc modeler 关联触发条件根据orm数据获取结果
     * @pars [prplId, dataId] 关联公式ID  orm数据ID
     * @date 2017/8/21 0021 下午 5:08 lulx
     **/
    @RequestMapping(value = "/relCondConfDataByPro/{prplId}/{dataId}", method = RequestMethod.GET)
    public Result relCondConfDataByPro(@PathVariable("prplId") String prplId, @PathVariable("dataId") String dataId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.relCondConfDataByPro(prplId, dataId);
        } catch (Exception e) {
            log.error("modeler 关联触发条件根据orm数据获取结果 接口错误", e);
            throw new RuntimeException("modeler 关联触发条件根据orm数据获取结果 接口错误", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc modeler 关联触发条件根据orm数据获取结果object
     * @pars [prplId, dataId] 关联公式ID  orm数据ID
     * @date 2017/10/27  下午 5:08 cjq
     **/
    @RequestMapping(value = "/getObjectRelCondConfDataByPro/{prplId}", method = RequestMethod.GET)
    public Result getObjectRelCondConfDataByPro(@PathVariable("prplId") String prplId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.getObjectRelCondConfDataByPro(prplId);
        } catch (Exception e) {
            log.error("modeler 关联触发条件根据orm数据获取object 接口错误", e);
            throw new RuntimeException("modeler 关联触发条件根据orm数据获取object 接口错误", e);
        }
        return result;
    }

    /**
     * @return com.huntkey.rx.commons.utils.rest.Result
     * @desc modeler 关联触发条件根据orm数据获取结果
     * @pars [prplId] 关联公式ID
     * @date 2017/10/26 0021 下午 5:08
     * @author liujia
     **/
    @RequestMapping(value = "/relCondConfDataByPrplIdForClass/{prplId}/{dataId}", method = RequestMethod.GET)
    public Result relCondConfDataByPrplIdForClass(@PathVariable("prplId") String prplId, @PathVariable("dataId") String dataId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.relCondConfDataByPrplIdForClass(prplId, dataId);
        } catch (Exception e) {
            log.error("modeler 关联触发条件根据orm数据获取结果 接口错误", e);
            throw new RuntimeException("modeler 关联触发条件根据orm数据获取结果 接口错误", e);
        }
        return result;
    }

    /**
     * 删除关联条件(条件也删除)
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/delRelCond/{prplId}", method = RequestMethod.DELETE)
    public Result delRelCond(@PathVariable("prplId") String prplId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.delRelCond(prplId);
        } catch (Exception e) {
            log.error("删除关联条件接口错误", e);
            throw new RuntimeException("删除关联条件接口错误", e);
        }
        return result;
    }

    /**
     * 删除触发条件(条件也删除)
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/delRelTrigger/{prplId}", method = RequestMethod.DELETE)
    public Result delRelTrigger(@PathVariable("prplId") String prplId) {
        Result result = null;
        try {
            result = relatedConditionSettingService.delRelTrigger(prplId);
        } catch (Exception e) {
            log.error("删除触发条件接口错误", e);
            throw new RuntimeException("删除触发条件接口错误", e);
        }
        return result;
    }
}
