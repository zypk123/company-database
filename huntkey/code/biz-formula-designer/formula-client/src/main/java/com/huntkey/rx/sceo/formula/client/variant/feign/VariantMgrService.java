package com.huntkey.rx.sceo.formula.client.variant.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.variant.feign.hystrix.VariantMgrHystrixImpl;
import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

/**
 * @author zhangyu
 * @create 2017-06-14 17:39
 **/
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = VariantMgrHystrixImpl.class)
public interface VariantMgrService {

    /**
     * loadSystemVariants
     * @param varName
     * @return
     */
    @RequestMapping(value = "/variantMgr/loadSystemVariants", method = RequestMethod.GET)
    Result loadSystemVariants(@RequestParam(required = false, defaultValue = "", value = "varName") String varName);

    /**
     * loadSystemVariantsWithFormula
     * @param formulaId
     * @return
     */
    @RequestMapping(value = "/variantMgr/loadSystemVariantsWithFormula", method = RequestMethod.GET)
    Result loadSystemVariantsWithFormula(@RequestParam(required = false, value = "formulaId", defaultValue = "") String formulaId);

    /**
     * loadLocalVariantsWithFormula
     * @param formulaId
     * @return
     */
    @RequestMapping(value = "/variantMgr/loadLocalVariantsWithFormula", method = RequestMethod.GET)
    Result loadLocalVariantsWithFormula(@RequestParam(required = false, value = "formulaId", defaultValue = "") String formulaId);

    /**
     * queryVariants
     * @param varName
     * @param state
     * @return
     */
    @RequestMapping(value = "/variantMgr/queryVariants", method = RequestMethod.GET)
    Result queryVariants(@RequestParam(required = false, value = "varName", defaultValue = "") String varName, @RequestParam(required = false, value = "state", defaultValue = "") String state);

    /**
     * removeVariant
     * @param varId
     * @return
     */
    @RequestMapping(value = "/variantMgr/removeVariant/{varId}", method = RequestMethod.DELETE)
    Result removeVariant(@PathVariable("varId") String varId);

    /**
     * saveVariant
     * @param variant
     * @return
     */
    @RequestMapping(value = "/variantMgr/saveVariant", method = RequestMethod.POST)
    Result saveVariant(@RequestBody TvmVariant variant);

    /**
     * updateVariant
     * @param variant
     * @return
     */
    @RequestMapping(value = "/variantMgr/updateVariant", method = RequestMethod.PUT)
    Result updateVariant(@RequestBody TvmVariant variant);

    /**
     * disableVariant
     * @param varId
     * @return
     */
    @RequestMapping(value = "/variantMgr/disableVariant/{varId}", method = RequestMethod.GET)
    Result disableVariant(@PathVariable("varId") String varId);

    /**
     * saveOrUpdateLocalVariantsWithFormula
     * @param localVarIdArr
     * @param formulaId
     * @return
     */
    @RequestMapping(value = "/variantMgr/{formulaId}/saveOrUpdateLocalVariantsWithFormula", method = RequestMethod.POST)
    Result saveOrUpdateLocalVariantsWithFormula(@RequestParam(required = true, value = "localVarIdArr", defaultValue = "") String[] localVarIdArr, @PathVariable(value = "formulaId") String formulaId);

    /**
     * saveOrUpdateSystemVariantsWithFormula
     * @param systemVarIdArr
     * @param formulaId
     * @return
     */
    @RequestMapping(value = "/variantMgr/{formulaId}/saveOrUpdateSystemVariantsWithFormula", method = RequestMethod.POST)
    Result saveOrUpdateSystemVariantsWithFormula(@RequestParam(required = true, value = "systemVarIdArr", defaultValue = "") String[] systemVarIdArr, @PathVariable(value = "formulaId") String formulaId);

    /**
     * getSystemVariantsById
     * @param vrntId
     * @return
     */
    @RequestMapping(value = "/variantMgr/getSystemVariantsById/{vrntId}", method = RequestMethod.GET)
    Result getSystemVariantsById(@PathVariable(value = "vrntId") String vrntId);

    /**
     * querySystemVariants
     * @param varName
     * @param varStatus
     * @param sortName
     * @param sortOrder
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/variantMgr/querySystemVariants", method = RequestMethod.GET)
    Result querySystemVariants(@RequestParam(required = false, value = "varName") String varName,
                               @RequestParam(required = false, value = "varStatus") String varStatus,
                               @RequestParam(required = false, value = "sortName") String sortName,
                               @RequestParam(required = false, value = "sortOrder") String sortOrder,
                               @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum,
                               @RequestParam(required = false, defaultValue = "10", value = "pageSize") int pageSize);

    /**
     * initLocalVariant
     * @param formulaId
     * @return
     */
    @RequestMapping(value = "/variantMgr/initLocalVariant/{formulaId}", method = RequestMethod.GET)
    Result initLocalVariant(@PathVariable("formulaId") String formulaId);

    /**
     * checkSysVarIsEnabled
     * @param sysVarId
     * @return
     */
    @RequestMapping(value = "/variantMgr/checkSysVarInUse/{sysVarId}", method = RequestMethod.GET)
    Result checkSysVarIsEnabled(@PathVariable("sysVarId") String sysVarId);

    /**
     * initSystVariant
     * @return
     */
    @RequestMapping(value = "/variantMgr/initSystVariant", method = RequestMethod.GET)
    Result initSystVariant();
}
