package com.huntkey.rx.sceo.formula.client.property.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.property.feign.PropLimitModelerService;
import com.huntkey.rx.sceo.formula.client.property.feign.PropertyLimitSettingService;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.TplPropertyLimit;
import com.huntkey.rx.sceo.formula.common.model.vo.PropLimitCndtVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 属性限值Controller
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 **/
@RestController
@RequestMapping("/propertyLimit")
public class PropertyLimitSettingController {

    private static Logger log = LoggerFactory.getLogger(PropertyLimitSettingController.class);

    @Autowired
    private PropertyLimitSettingService propertyLimitSettingService;
    @Autowired
    private PropLimitModelerService propLimitModelerService;

    /**
     * 根据属性ID查询并返回edmp_value_limit字段  -----调用modeler接口
     * @param propertyId
     * @return
     */
    @RequestMapping(value = "/getValueLimitById/{propertyId}", method = RequestMethod.GET)
    public Result getValueLimitById(@PathVariable("propertyId") String propertyId) {
        Result result = null;
        try {
            result = propLimitModelerService.getValueLimitById(propertyId);

        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController getValueLimitById error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    /**
     * 更新指定Id的属性的edmp_value_limit，其中参数valueLimit为属性限值ID    -----调用modeler接口
     * @param id,valueLimit
     * @return
     */
    @RequestMapping(value = "/updateValueLimit", method = RequestMethod.PUT)
    public Result updateValueLimit(@RequestParam(required = false, value = "id", defaultValue = "") String id,
                                   @RequestParam(required = false, value = "edmpValueLimit", defaultValue = "") String edmpValueLimit
    )
    {
        Result result = null;
        try {
            result = propLimitModelerService.updateValueLimit(id, edmpValueLimit);
//            result.setRetCode(1);//测试
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController updateValueLimit error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    /**
     * 根据属性ID查询并返回属性相关的所有信息  -----调用modeler接口
     * @param propertyId
     * @return
     */
    @RequestMapping(value = "/queryBaseInfoOfProp/{propertyId}", method = RequestMethod.GET)
    public Result queryBaseInfoOfProp(@PathVariable("propertyId") String propertyId) {
        Result result = null;
        try {
            result = propLimitModelerService.queryBaseInfoOfProp(propertyId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController queryBaseInfoOfProp error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }


    /**
     * 根据属性限值编号prprId 查询所有关联条件
     * @param prprId
     * @return
     */
    @RequestMapping(value = "/queryAllConditions/{prprId}", method = RequestMethod.GET)
    public Result queryAllConditions(@PathVariable("prprId") String prprId) {
        Result result = null;
        try {
            result = propertyLimitSettingService.queryAllConditions(prprId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController queryAllConditions error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    /**
     * 新增属性限值关联条件
     * @param tplCondition
     * @return
     */
     @RequestMapping(value = "/saveCondition", method = RequestMethod.POST)
    public Result saveCondition(@RequestBody TplCondition tplCondition){
        Result result = null;
        try {
            result = propertyLimitSettingService.saveCondition(tplCondition);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController saveCondition error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    /**
     * 更新属性限值关联条件
     * @param tplCondition
     * @return
     */
    @RequestMapping(value = "/updateCondition", method = RequestMethod.PUT)
    public Result updateCondition(@RequestBody TplCondition tplCondition){
        Result result = null;
        try {
            result = propertyLimitSettingService.updateCondition(tplCondition);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController updateCondition error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    /**
     * 删除一条关联条件
     * @param cndtId
     * @return
     */
    @RequestMapping(value = "/removeCondition/{cndtId}", method = RequestMethod.DELETE)
    public Result removeCondition(@PathVariable("cndtId") String cndtId){
        Result result = null;
        try {
            result = propertyLimitSettingService.removeCondition(cndtId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController removeCondition error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }
    /**
     * 新增属性限值
     * @param tplPropertyLimit
     * @return
     */
    @RequestMapping(value = "/savePropLimit", method = RequestMethod.POST)
    public Result savePropLimit(@RequestBody TplPropertyLimit tplPropertyLimit){
        Result result = null;
        try {
            result = propertyLimitSettingService.savePropLimit(tplPropertyLimit);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController savePropLimit error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    /**
     * 更新属性限值
     * @param tplPropertyLimit
     * @return
     */
    @RequestMapping(value = "/updatePropLimit", method = RequestMethod.PUT)
    public Result updatePropLimit(@RequestBody TplPropertyLimit tplPropertyLimit){
        Result result = null;
        try {
            result = propertyLimitSettingService.updatePropLimit(tplPropertyLimit);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController updatePropLimit error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    /**
     * 根据属性限值标号prprId查询属性限值
     * @param prprId
     * @return
     */
    @RequestMapping(value = "/getPropLimitById/{prprId}", method = RequestMethod.GET)
    public Result getPropLimitById(@PathVariable("prprId") String prprId) {
        Result result = null;
        try {
            result = propertyLimitSettingService.getPropLimitById(prprId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController getPropLimitById error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    /**
     * 保存属性限值及关联条件
     * @param vo
     * @return
     */
    @RequestMapping(value = "/saveOrUpdateLimitAndConditions", method = RequestMethod.POST)
    public Result saveOrUpdateLimitAndConditions(@RequestBody PropLimitCndtVo vo)
    {
        Result result = null;
        try {
            result = propertyLimitSettingService.saveOrUpdateLimitAndConditions(vo);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController saveOrUpdateLimitAndConditions error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    /**
     * 根据属性限值标号prprId 查询属性限值及关联条件
     * @param prprId
     * @return
     */
    @RequestMapping(value = "/queryLimitAndConditions/{prprId}", method = RequestMethod.GET)
    public Result queryLimitAndConditions(@PathVariable("prprId") String prprId)
    {
        log.info("================================>>>>call client  queryLimitAndConditions");
        Result result = null;
        try {
            result = propertyLimitSettingService.queryLimitAndConditions(prprId);
        } catch (RuntimeException e) {
            e.printStackTrace();
            log.error("ERROR : call client PropertyLimitSettingController queryLimitAndConditions error!");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    /**
     *@desc 获取公式描述、属性限制描述
     *@pars [prprId, formulaId] 属性ID  公式ID
     *@date 2017/7/15 0015 上午 10:34 lulx
     *@return com.huntkey.rx.commons.utils.rest.Result
     **/
    @RequestMapping(value = "/queryFormulasAndPropertyLimits", method = RequestMethod.GET)
    public Result queryFormulasAndPropertyLimits(@RequestParam(value = "prprIds", required = false) List<String> prprIds,@RequestParam(value = "formulaIds", required = false) List<String> formulaIds)
    {
        Result result = null;
        try {
            result = propertyLimitSettingService.queryFormulasAndPropertyLimits(prprIds,formulaIds);
        } catch (RuntimeException e) {
            String msg = "获取公式描述、属性限制描述接口错误";
            log.error(msg,e);
            throw new RuntimeException(msg,e);
        }
        return result;
    }

    /**
     *  删除属性限制
     * @param prprId 属性限值ID
     * @return
     */
    @RequestMapping(value = "/deletePropLimit/{prprId}", method = RequestMethod.DELETE)
    public Result deletePropLimit(@PathVariable("prprId") String prprId)
    {
        Result result = null;
        try {
            result = propertyLimitSettingService.deletePropLimit(prprId);
        } catch (RuntimeException e) {
            String msg = "删除属性限制接口错误";
            log.error(msg,e);
            throw new RuntimeException(msg,e);
        }
        return result;
    }
}

