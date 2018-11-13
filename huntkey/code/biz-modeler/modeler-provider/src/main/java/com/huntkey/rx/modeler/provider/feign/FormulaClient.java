package com.huntkey.rx.modeler.provider.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.provider.feign.hystrix.FormulaHystrix;
import com.huntkey.rx.modeler.provider.feign.hystrix.OrmHystrix;
import com.huntkey.rx.sceo.formula.common.params.VariantBatchParam;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by licj on 2017/7/20.
 */
@FeignClient(value = "formula-provider", fallback = FormulaHystrix.class)
public interface FormulaClient {

    /**
     * 查找属性限值与属性公式（属性列表使用）
     * @param prprIds
     * @param formulaIds
     * @return
     */
    @RequestMapping(value = "/propertyLimit/queryFormulasAndPropertyLimits", method = RequestMethod.GET)
    Result queryFormulasAndPropertyLimits(@RequestParam(value = "prprIds",required = false) List<String> prprIds,
                                          @RequestParam(value = "formulaIds",required = false) List<String> formulaIds);

    /**
     * 根据id查找触发条件(触发条件回显使用)
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/selectByPrimaryKey/{prplId}", method = RequestMethod.GET)
    Result selectByPrimaryKey(@PathVariable(value = "prplId") String prplId);

    /**
     * 查找对象定位公式（关联与联动列表使用）
     * @param prplIdArr1
     * @param prplIdArr2
     * @return
     */
    @RequestMapping(value = "/relatedConditions/getPrplConditionDescByPrplIdArr", method = RequestMethod.GET)
    Result getPrplConditionDescByPrplIdArr(@RequestParam(required = false,value = "prplIdArr1") List<String> prplIdArr1,
                                           @RequestParam(required = false,value = "prplIdArr2") List<String> prplIdArr2);

    /**
     * 解析属性公式
     * @param variantBatchParam
     * @return
     */
    @RequestMapping(value = "/formula/variantCalcBatch", method = RequestMethod.POST)
    Result variantCalcBatch(@RequestBody VariantBatchParam variantBatchParam);

    /**
     * 删除触发条件公式
     *
     * @param prplId
     * @return
     */
    @RequestMapping(value = "/relatedConditions/delRelTrigger/{prplId}", method = RequestMethod.DELETE)
    Result delRelTrigger(@PathVariable("prplId") String prplId);

    /**
     * 删除一条关联条件公式
     * @param cndtId
     * @return
     */
    @RequestMapping(value = "/propertyLimit/removeCondition/{cndtId}", method = RequestMethod.DELETE)
    Result removeCondition(@PathVariable("cndtId") String cndtId);
}
