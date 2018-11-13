package com.huntkey.rx.sceo.formula.provider.property.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.TplPropertyLimit;
import com.huntkey.rx.sceo.formula.common.model.vo.PropLimitCndtVo;
import com.huntkey.rx.sceo.formula.provider.property.service.PropertyLimitSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 属性限值Controller
 *
 * @author zhangyu
 * @create 2017-06-14 17:50
 * @modify  by nidongxu  on 2017-06-19
 **/
@RestController
@RequestMapping("/propertyLimit")
public class PropertyLimitSettingController {

    private static Logger log = LoggerFactory.getLogger(PropertyLimitSettingController.class);

    @Autowired
    PropertyLimitSettingService propertyLimitSettingService;

    /**
     * 根据属性限值编号prprId 查询所有关联条件
     * @param prprId
     * @return
     */
    @RequestMapping(value = "/queryAllConditions/{prprId}", method = RequestMethod.GET)
    public Result queryAllConditions(@PathVariable("prprId") String prprId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call propertyLimitSetting queryAllConditions service");
            List<TplCondition> tplCondition = propertyLimitSettingService.queryAllConditions(prprId);
            result.setData(tplCondition);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("call propertyLimitSetting queryAllConditions service fail");
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
    public Result saveCondition(@RequestBody TplCondition tplCondition) {
        Result result = new Result();
        //默认成功
        result.setRetCode(Result.RECODE_SUCCESS);
        try{
            log.info("call propertyLimitSetting saveCondition service");
            propertyLimitSettingService.saveCondition(tplCondition);
            //返回ID
            result.setData(tplCondition.getCndtId());
        }catch(Exception e){
            e.printStackTrace();
            log.error("call propertyLimitSetting saveCondition service fail");
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
    public Result updateCondition(@RequestBody TplCondition tplCondition) {
        Result result = new Result();
        //默认成功
        result.setRetCode(Result.RECODE_SUCCESS);
        try{
            log.info("call propertyLimitSetting updateCondition service");
            propertyLimitSettingService.updateCondition(tplCondition);
            //返回ID
            result.setData(tplCondition.getCndtId());
        }catch(Exception e){
            e.printStackTrace();
            log.error("call propertyLimitSetting updateCondition service fail");
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
    public Result removeCondition(@PathVariable("cndtId") String cndtId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            log.info("call propertyLimitSetting removeCondition service");
            propertyLimitSettingService.removeCondition(cndtId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("call propertyLimitSetting removeCondition service fail");
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
    public Result savePropLimit(@RequestBody TplPropertyLimit tplPropertyLimit) {
        Result result = new Result();
        //默认成功
        result.setRetCode(Result.RECODE_SUCCESS);
        try{
            log.info("call propertyLimitSetting savePropLimit service");
            propertyLimitSettingService.savePropLimit(tplPropertyLimit);
            //返回ID
            result.setData(tplPropertyLimit.getPrprId());
        }catch(Exception e){
            e.printStackTrace();
            log.error("call propertyLimitSetting savePropLimit service fail");
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
    public Result saveOrUpdatePropLimit(@RequestBody TplPropertyLimit tplPropertyLimit) {
        Result result = new Result();
        //默认成功
        result.setRetCode(Result.RECODE_SUCCESS);
        try{
            log.info("call propertyLimitSetting updatePropLimit service");
            propertyLimitSettingService.updatePropLimit(tplPropertyLimit);
            //返回ID
            result.setData(tplPropertyLimit.getPrprId());
        }catch(Exception e){
            e.printStackTrace();
            log.error("call propertyLimitSetting updatePropLimit service fail");
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
        Result result = new Result();
        //默认成功
        result.setRetCode(Result.RECODE_SUCCESS);
        try{
            log.info("call propertyLimitSetting getPropLimitById service");
            TplPropertyLimit tplPropertyLimit = propertyLimitSettingService.getPropLimitById(prprId);
            result.setData(tplPropertyLimit);
        }catch(Exception e){
            e.printStackTrace();
            log.error("call propertyLimitSetting getPropLimitById service fail");
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
        Result result = new Result();
        //默认成功
        result.setRetCode(Result.RECODE_SUCCESS);
        try{
            log.info("call propertyLimitSetting saveOrUpdateLimitAndConditions service");
            propertyLimitSettingService.saveOrUpdateLimitAndConditions(vo);
            //返回ID
            result.setData(vo.getTplPropertyLimit().getPrprId());
        }catch(Exception e){
            e.printStackTrace();
            log.error("call propertyLimitSetting saveOrUpdateLimitAndConditions service fail");
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
        Result result = new Result();
        //默认成功
        result.setRetCode(Result.RECODE_SUCCESS);
        try{
            log.info("call propertyLimitSetting saveOrUpdateLimitAndConditions service");
            PropLimitCndtVo vo = propertyLimitSettingService.queryLimitAndConditions(prprId);
            //返回ID
            result.setData(vo);
        }catch(Exception e){
            e.printStackTrace();
            log.error("call propertyLimitSetting queryLimitAndConditions service fail");
            throw new RuntimeException("系统异常!",e);
        }
        return result;
    }

    @RequestMapping(value = "/queryFormulasAndPropertyLimits", method = RequestMethod.GET)
    public Result queryFormulasAndPropertyLimits(@RequestParam(value = "prprIds", required = false) List<String> prprIds,@RequestParam(value = "formulaIds", required = false) List<String> formulaIds)
    {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            Map<String,Object> map= propertyLimitSettingService.queryFormulasAndPropertyLimits(prprIds,formulaIds);
            result.setData(map);
        } catch (RuntimeException e) {
            String msg = "获取公式描述、属性限制描述接口错误!";
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
