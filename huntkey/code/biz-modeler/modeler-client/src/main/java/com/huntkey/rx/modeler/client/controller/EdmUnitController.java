package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmUnitService;
import com.huntkey.rx.modeler.common.model.EdmUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by linziy on 2017/4/19.
 */
@RestController
@RequestMapping("/v1/units")
public class EdmUnitController {
    private static Logger log = LoggerFactory.getLogger(EdmUnitController.class);

    @Autowired
    private EdmUnitService edmUnitService;

    /**
     * 新增单位数值属性
     * @param edmUnit
     * @return
     */
    @RequestMapping(method= RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmUnit edmUnit){
        Result result = edmUnitService.add(edmUnit);
        return result;
    }

    /**
     * 删除单位数值属性（废弃）
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id){
        Result result = edmUnitService.delete(id);
        return result;
    }

    /**
     * 批量删除单位数值属性
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
    public Result deleteIds(@PathVariable String[] ids){
        Result result = edmUnitService.deleteIds(ids);
        return result;
    }

    /**
     * 更新单位数值属性
     * @param edmUnit
     * @return
     */
    @RequestMapping( method = RequestMethod.PUT)
    public Result update(@RequestBody EdmUnit edmUnit){
        Result result = edmUnitService.update(edmUnit);
        return result;
    }

    /**
     * 根据id查询单位数值属性
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getbyid(@PathVariable(value="id") String id) {
        Result result = edmUnitService.getbyid(id);
        return result;
    }

    /**
     * 条件查询,带分页（废弃）
     * @param edunEdmpId
     * @param edunQtyEdmpId
     * @param pageNum
     * @param pageSize
     * @return
     */
    @RequestMapping( method=RequestMethod.GET)
    public Result query(@RequestParam(required = false,value = "edunEdmpId")String edunEdmpId,
                        @RequestParam(required = false,value = "edunQtyEdmpId")String edunQtyEdmpId,
                        @RequestParam(required = false,defaultValue = "1",value="pageNum")int pageNum,@RequestParam(required = false,defaultValue = "10") int pageSize) {
        Result result = edmUnitService.select(edunEdmpId,edunQtyEdmpId,pageNum,pageSize);
        return result;
    }
}
