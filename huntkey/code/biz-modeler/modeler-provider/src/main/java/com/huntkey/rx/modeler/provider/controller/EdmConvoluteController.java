package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.common.model.EdmConvolute;
import com.huntkey.rx.modeler.common.model.vo.EdmConvoluteVO;
import com.huntkey.rx.modeler.provider.service.EdmConvoluteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by linziy on 2017/4/16.
 */
//卷积表服务借口
@RestController
@RequestMapping("/convolutes")
public class EdmConvoluteController {

    private static Logger log = LoggerFactory.getLogger(EdmConvoluteController.class);

    @Autowired
    public EdmConvoluteService edmConvoluteService;
    /**
     * 新增卷积属性
     * @param edmConvolute
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmConvolute edmConvolute) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmConvoluteService.insert(edmConvolute);
        result.setData(edmConvolute.getId());
        return result;
    }

    /**
     * 根据id（逻辑删除）
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmConvoluteService.delete(id);
        return result;
    }

    /**
     * 修改卷积属性
     * @param edmConvolute
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody EdmConvolute edmConvolute) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmConvoluteService.update(edmConvolute);
        return result;
    }

    /*
    * 卷积属性条件查询,带分页（废弃）
    * @param edcoEdmpId (edco_edmp_id)  属性id
    * @param edcoUpdateType(edco_update_type) 更新类型：1.同步 2.异步 3.定时
    * @param edcoUpdateTimeliness(edco_update_timeliness) 更新时效果
    * @param edcoUpdateTime(edco_update_time)更新时间
    * @param edcoConvoluteFormula(edco_convolute_formula)卷积公式
    * @oaram pageNum
    * @param pageSize
    * @return RESTFul 接口返回对象格式
    * */
    @RequestMapping(method = RequestMethod.GET)
    public Result query(@RequestParam(required = false,value = "edcoEdmpId")String edcoEdmpId,
                        @RequestParam(required = false,value = "edcoUpdateType") Byte edcoUpdateType,
                        @RequestParam(required = false,value = "edcoUpdateTimeliness") String edcoUpdateTimeliness,
                        @RequestParam(required = false,value = "edcoUpdateTime" ) String edcoUpdateTime,
                        @RequestParam(required = false,value = "edcoConvoluteFormula") String edcoConvoluteFormula,
                        @RequestParam(required = false,defaultValue = "1") int pageNum, @RequestParam(required =false,defaultValue = "10") int pageSize) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Pagination<EdmConvolute> edmConvoluteList = edmConvoluteService.selectByExample(edcoEdmpId, edcoUpdateType, edcoUpdateTimeliness, edcoUpdateTime, edcoConvoluteFormula, pageNum, pageSize);
        result.setData(edmConvoluteList);
        return result;
    }

    /**
     * 卷积公式与模型关系查询
     * @param edcoConvoluteFormula
     * @param edmpName
     * @param edmcName
     * @param edmdVer
     * @return
     */
    @RequestMapping(value = "/properties", method = RequestMethod.GET)
    public Result queryResultWithModeler(@RequestParam(required = false,value = "edcoConvoluteFormula") String edcoConvoluteFormula,
                                         @RequestParam(required = false,value = "edmpName") String edmpName,
                                         @RequestParam(required = false,value = "edmcName") String edmcName,
                                         @RequestParam(required = false,value = "edmdVer") String edmdVer) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        List<EdmConvoluteVO> edmConvoluteVOList = edmConvoluteService.selectConvoluteVOByFormulaEdmdnameEdmpname(edcoConvoluteFormula, edmpName, edmcName,edmdVer);
        result.setData(edmConvoluteVOList);
        return result;
    }

    /**
     * 根据属性ID取属性的扩展属性中的卷积公式
     * @param edmpId
     * @return
     */
    @RequestMapping(value = "/formula/{edmpId}", method = RequestMethod.GET)
    public Result selectConvoluteFormulaByEdmpId(@PathVariable String edmpId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String convoluteFormula = edmConvoluteService.selectConvoluteFormulaByEdmpId(edmpId);
        result.setData(convoluteFormula);
        return result;
    }

}
