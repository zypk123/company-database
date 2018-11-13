package com.huntkey.rx.sceo.formula.client.property.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.property.feign.hystrix.PropertyLimitSettingHystrixImpl;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.TplPropertyLimit;
import com.huntkey.rx.sceo.formula.common.model.vo.PropLimitCndtVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 属性限值Feign接口
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = PropertyLimitSettingHystrixImpl.class)
public interface PropertyLimitSettingService {

    /**
     * 根据属性限值编号prprId 查询所有关联条件
     * @param prprId
     * @return
     */
    @RequestMapping(value = "/propertyLimit/queryAllConditions/{prprId}", method = RequestMethod.GET)
    Result queryAllConditions(@PathVariable("prprId") String prprId);

    /**
    * 新增属性限值关联条件
    * @param tplCondition
    * @return
    * */
    @RequestMapping( value = "/propertyLimit/saveCondition",method = RequestMethod.POST)
    Result saveCondition(@RequestBody TplCondition tplCondition);

    /**
     * 更新属性限值关联条件
     * @param tplCondition
     * @return
     * */
    @RequestMapping( value = "/propertyLimit/updateCondition",method = RequestMethod.PUT)
    Result updateCondition(@RequestBody TplCondition tplCondition);

    /**
    * 删除一条关联条件
     * @param cndtId
    * @return
    * */
    @RequestMapping(value = "/propertyLimit/removeCondition/{cndtId}", method = RequestMethod.DELETE)
    Result removeCondition(@PathVariable("cndtId") String cndtId);

    /**
    * 新增属性限值
    * @param tplPropertyLimit
    * @return
    * */
    @RequestMapping( value = "/propertyLimit/savePropLimit",method = RequestMethod.POST)
    Result savePropLimit(@RequestBody TplPropertyLimit tplPropertyLimit);

    /**
     * 更新属性限值
     * @param tplPropertyLimit
     * @return
     * */
    @RequestMapping( value = "/propertyLimit/updatePropLimit",method = RequestMethod.PUT)
    Result updatePropLimit(@RequestBody TplPropertyLimit tplPropertyLimit);

    /**
     * 根据属性限值标号prprId查询属性限值
     * @param prprId
     * @return
     */
    @RequestMapping(value = "/propertyLimit/getPropLimitById/{prprId}", method = RequestMethod.GET)
    Result getPropLimitById(@PathVariable("prprId") String prprId);

    /**
    * 保存属性限值及关联条件
    * @param vo
    * @return
    * */
    @RequestMapping( value = "/propertyLimit/saveOrUpdateLimitAndConditions",method = RequestMethod.POST)
    Result saveOrUpdateLimitAndConditions(@RequestBody PropLimitCndtVo vo);

    /**
   * 根据属性限值标号prprId 查询属性限值及关联条件
   * @param prprId
   * @return
   * */
    @RequestMapping( value = "/propertyLimit/queryLimitAndConditions/{prprId}",method = RequestMethod.GET)
    Result queryLimitAndConditions(@PathVariable("prprId") String prprId);

    /**
     * queryFormulasAndPropertyLimits
     * @param prprIds
     * @param formulaIds
     * @return
     */
    @RequestMapping(value = "/propertyLimit/queryFormulasAndPropertyLimits", method = RequestMethod.GET)
    Result queryFormulasAndPropertyLimits(@RequestParam(value = "prprIds", required = false) List<String> prprIds, @RequestParam(value = "formulaIds", required = false) List<String> formulaIds);

    /**
     * deletePropLimit
     * @param prprId
     * @return
     */
    @RequestMapping( value = "/propertyLimit/deletePropLimit/{prprId}",method = RequestMethod.DELETE)
    Result deletePropLimit(@PathVariable("prprId") String prprId);
}
