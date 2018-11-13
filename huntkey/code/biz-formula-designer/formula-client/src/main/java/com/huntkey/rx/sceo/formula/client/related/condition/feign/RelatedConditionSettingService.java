package com.huntkey.rx.sceo.formula.client.related.condition.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.related.condition.feign.hystrix.RelatedConditionSettingHystrixImpl;
import com.huntkey.rx.sceo.formula.common.model.TacConditionRelated;
import com.huntkey.rx.sceo.formula.common.model.TacPropertyRelated;
import com.huntkey.rx.sceo.formula.common.model.vo.PropertyRelatedVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 关联条件设置Feign接口
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = RelatedConditionSettingHystrixImpl.class)
public interface RelatedConditionSettingService {

    /**
     * 新增关联条件的条件
     *
     * @param tacConditionRelated
     * @return
     */
    @RequestMapping(value = "/relatedConditions/saveConditionRelated", method = RequestMethod.POST)
    Result saveConditionRelated(@RequestBody TacConditionRelated tacConditionRelated);

    /**
     * 修改关联条件的条件
     *
     * @param tacConditionRelated
     * @return
     */
    @RequestMapping(value = "/relatedConditions/updateConditionRelated", method = RequestMethod.PUT)
    Result updateConditionRelated(@RequestBody TacConditionRelated tacConditionRelated);

    /**
     * 查询关联条件的条件列表
     *
     * @return
     */
    @RequestMapping(value = "/relatedConditions/queryAllConditionsRelated", method = RequestMethod.GET)
    Result queryAllConditionsRelated();

    /**
     * 通过id查询关联条件的条件
     *
     * @param cndrId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/queryConditionsRelatedByCndrId/{cndrId}", method = RequestMethod.GET)
    Result queryConditionsRelatedByCndrId(@PathVariable("cndrId") String cndrId);

    /**
     * 删除关联条件的条件接口(逻辑删除)
     *
     * @param cndrId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/removeConditionRelated/{cndrId}", method = RequestMethod.DELETE)
    Result removeConditionRelated(@PathVariable("cndrId") String cndrId);

    /**
     * 新增关联条件
     *
     * @param tacPropertyRelated
     * @return
     */
    @RequestMapping(value = "/relatedConditions/savePropRelated", method = RequestMethod.POST)
    Result savePropRelated(@RequestBody TacPropertyRelated tacPropertyRelated);

    /**
     * 修改关联条件
     *
     * @param tacPropertyRelated
     * @return
     */
    @RequestMapping(value = "/relatedConditions/updatePropRelated", method = RequestMethod.PUT)
    Result updatePropRelated(@RequestBody TacPropertyRelated tacPropertyRelated);

    /**
     * 根据关联属性ID查找公式
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/getPrplConditionDescByPrplId/{prplId}", method = RequestMethod.GET)
    Result getPrplConditionDescByPrplId(@PathVariable("prplId") String prplId);

    /**
     * 根据关联属性ID数组查找公式
     *
     * @param prplIdArr1
     * @param prplIdArr2
     * @return
     */
    @RequestMapping(value = "/relatedConditions/getPrplConditionDescByPrplIdArr", method = RequestMethod.GET)
    Result getPrplConditionDescByPrplIdArr(@RequestParam(value = "prplIdArr1", required = false) List<String> prplIdArr1, @RequestParam(value = "prplIdArr2", required = false) List<String> prplIdArr2);

    /**
     * 通过属性关联ID查找关联属性及其条件列表
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/getPropertyRelatedCondition/{prplId}", method = RequestMethod.GET)
    Result getPropertyRelatedCondition(@PathVariable("prplId") String prplId);

    /**
     * 保存关联属性及其条件(关联条件)
     *
     * @param propertyRelatedVo
     * @return
     */
    @RequestMapping(value = "/relatedConditions/saveOrUpdatePropRelatedAndConditionsForRelCon", method = RequestMethod.POST)
    Result saveOrUpdatePropRelatedAndConditionsForRelCon(@RequestBody PropertyRelatedVo propertyRelatedVo);

    /**
     * 保存关联属性及其条件(关联触发条件)
     *
     * @param propertyRelatedVo
     * @return
     */
    @RequestMapping(value = "/relatedConditions/saveOrUpdatePropRelatedAndConditionsForTriCon", method = RequestMethod.POST)
    Result saveOrUpdatePropRelatedAndConditionsForTriCon(@RequestBody PropertyRelatedVo propertyRelatedVo);
    /**
     * 保存关联属性及其条件(关联触发条件)
     *
     * @param propertyRelatedVo
     * @return
     */
    @RequestMapping(value = "/relatedConditions/saveOrUpdatePropRelatedAndConditionsForClassCon", method = RequestMethod.POST)
    Result saveOrUpdatePropRelatedAndConditionsForClassCon(@RequestBody PropertyRelatedVo propertyRelatedVo);

    /**
     * 保存关联属性及其条件(方法关联触发条件)
     *
     * @param propertyRelatedVo
     * @return
     */
    @RequestMapping(value = "/relatedConditions/saveOrUpdatePropRelatedAndConditionsForMethodTriCon", method = RequestMethod.POST)
    Result saveOrUpdatePropRelatedAndConditionsForMethodTriCon(@RequestBody PropertyRelatedVo propertyRelatedVo);

    /**
     * 初始化关联触发条件
     *
     * @param propId 属性ID
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/initRelTriggerCond/{propId}/{prplId}", method = RequestMethod.GET)
    Result initRelTriggerCond(@PathVariable("propId") String propId, @PathVariable("prplId") String prplId);

    /**
     * 初始化方法关联触发条件
     *
     * @param propId 属性ID
     * @return
     */
    @RequestMapping(value = "/relatedConditions/initMethodRelTriggerCond/{propId}", method = RequestMethod.GET)
    Result initMethodRelTriggerCond(@PathVariable("propId") String propId);

    /**
     * 初始化关联条件
     *
     * @param propId  属性ID
     * @param propId2 属性2ID
     * @return
     */
    @RequestMapping(value = "/relatedConditions/initRelCond/{propId}/{propId2}", method = RequestMethod.GET)
    Result initRelCond(@PathVariable("propId") String propId, @PathVariable("propId2") String propId2);

    /**
     * relCondDataByPro
     * @param prplId
     * @param dataId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/relCondDataByPro/{prplId}/{dataId}", method = RequestMethod.GET)
    Result relCondDataByPro(@PathVariable("prplId") String prplId, @PathVariable("dataId") String dataId);

    /**
     * 通过ID查找关联属性(触发条件)
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/selectByPrimaryKey/{prplId}", method = RequestMethod.GET)
    Result selectByPrimaryKey(@PathVariable("prplId") String prplId);

    /**
     * relCondConfDataByPro
     * @param prplId
     * @param dataId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/relCondConfDataByPro/{prplId}/{dataId}", method = RequestMethod.GET)
    Result relCondConfDataByPro(@PathVariable("prplId") String prplId, @PathVariable("dataId") String dataId);

    /**
     * 关联触发条件根据orm数据获取结果
     *
     * @param prplId
     * @param dataId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/relCondConfDataByPrplIdForClass/{prplId}/{dataId}", method = RequestMethod.GET)
    Result relCondConfDataByPrplIdForClass(@PathVariable("prplId") String prplId,@PathVariable("dataId") String dataId);


    /**
     * 删除关联条件(条件也删除)
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/delRelCond/{prplId}", method = RequestMethod.DELETE)
    Result delRelCond(@PathVariable("prplId") String prplId);

    /**
     * 删除触发条件(条件也删除)
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/delRelTrigger/{prplId}", method = RequestMethod.DELETE)
    Result delRelTrigger(@PathVariable("prplId") String prplId);

    /**
     * 关联触发条件根据orm数据获取object
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/getObjectRelCondConfDataByPro/{prplId}", method = RequestMethod.GET)
    Result getObjectRelCondConfDataByPro(@PathVariable("prplId") String prplId);

}
