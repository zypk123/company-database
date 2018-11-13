package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmUnit;
import com.huntkey.rx.modeler.common.model.vo.EdmUnitVO;
import com.huntkey.rx.modeler.provider.service.EdmUnitService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by linziy on 2017/4/18.
 */
@RestController
@RequestMapping("/units")
public class EdmUnitController {

    private static Logger log = LoggerFactory.getLogger(EdmUnitController.class);
    @Autowired
    private EdmUnitService edmUnitService;

    /**
     * 新增单位数值属性
     * @param edmUnit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmUnit edmUnit) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmUnitService.insert(edmUnit);
        result.setData(edmUnit.getId());
        return result;
    }

    /**
     * 删除单位数值属性（废弃）
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value="id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmUnitService.delete(id);
        return result;
    }

    /**
     * 批量删除单位数值属性
     * @param ids
     * @return
     */
    @RequestMapping(value = "/batch/{ids}", method = RequestMethod.DELETE)
    public Result deleteIds(@PathVariable(value="ids") String[] ids) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmUnitService.deleteIds(ids);
        return result;
    }

    /**
     * 更新单位数值属性
     * @param edmUnit
     * @return
     */
    @RequestMapping( method = RequestMethod.PUT)
    public Result update(@RequestBody EdmUnit edmUnit){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmUnitService.update(edmUnit);
        return result;
    }

    /**
     * 根据id查询单位数值属性
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getbyid(@PathVariable(value="id") String id){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        EdmUnit edmUnit = edmUnitService.selectbyid(id);
        result.setData(edmUnit);
        return result;
    }

}
