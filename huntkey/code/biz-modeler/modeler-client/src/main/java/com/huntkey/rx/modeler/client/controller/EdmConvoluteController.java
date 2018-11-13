package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmConvoluteService;
import com.huntkey.rx.modeler.common.model.EdmConvolute;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by linziy on 2017/4/17.
 */
@RestController
@RequestMapping("/v1/convolutes")
//@Api(value = "卷积属性维护服务", description = "提供卷积属性增加、修改、删除和查询服务")
public class EdmConvoluteController {
    private static Logger log = LoggerFactory.getLogger(EdmConvoluteController.class);

    //注入Service - 服务生产端的映射类
    @Autowired
    private EdmConvoluteService edmConvoluteService;

    /**
     * 新增卷积属性
     * @param edmConvolute
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmConvolute edmConvolute) {
        Result result = edmConvoluteService.add(edmConvolute);
        return result;
    }

    /**
     * 根据id（逻辑删除）
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Result result = edmConvoluteService.delete(id);
        return result;
    }

    /**
     * 修改卷积属性
     * @param edmConvolute
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody EdmConvolute edmConvolute) {
        Result result = edmConvoluteService.update(edmConvolute);
        return result;
    }

    /**
     * 卷积属性条件查询,带分页（废弃）
     * @param edcoEdmpId
     * @param edcoUpdateType
     * @param edcoUpdateTimeliness
     * @param edcoUpdateTime
     * @param edcoConvoluteFormula
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result query(@RequestParam(required = false, value = "edcoEdmpId") String edcoEdmpId,
                        @RequestParam(required = false, value = "edcoUpdateType") Byte edcoUpdateType,
                        @RequestParam(required = false, value = "edcoUpdateTimeliness") String edcoUpdateTimeliness,
                        @RequestParam(required = false, value = "edcoUpdateTime") String edcoUpdateTime,
                        @RequestParam(required = false, value = "edcoConvoluteFormula") String edcoConvoluteFormula,
                        @RequestParam(required = false, defaultValue = "1", value = "pageNum") int pageNum, @RequestParam(required =false,defaultValue = "10") int pageSize) {
        Result result = edmConvoluteService.select(edcoEdmpId, edcoUpdateType, edcoUpdateTimeliness, edcoUpdateTime, edcoConvoluteFormula, pageNum, pageSize);//接受服务抛出的result
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
    public Result queryResultWithModeler ( @RequestParam(required = false, value = "edcoConvoluteFormula") String edcoConvoluteFormula,
                                           @RequestParam(required = false, value = "edmpName") String edmpName,
                                           @RequestParam(required = false, value = "edmcName") String edmcName,
                                           @RequestParam(required = false, value = "edmdVer") String edmdVer
    ) {
        Result result = edmConvoluteService.queryResultWithModeler(edcoConvoluteFormula, edmpName, edmcName,edmdVer);//接受服务抛出的result
        return result;
    }

    /**
     * 根据属性ID取属性的扩展属性中的卷积公式
     * @param edmpId
     * @return
     */
    @RequestMapping(value = "/formula/{edmpId}", method = RequestMethod.GET)
    public Result selectConvoluteFormulaByEdmpId(@PathVariable String edmpId) {
       return edmConvoluteService.selectConvoluteFormulaByEdmpId(edmpId);
    }
}
