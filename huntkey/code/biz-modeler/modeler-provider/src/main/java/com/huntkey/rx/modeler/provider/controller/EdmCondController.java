package com.huntkey.rx.modeler.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.common.model.EdmCond;
import com.huntkey.rx.modeler.provider.service.EdmCondService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by linziy on 2017/4/18.
 */
@RestController
@RequestMapping("/conds")
public class EdmCondController {

    private static Logger log = LoggerFactory.getLogger(EdmCondController.class);
    @Autowired
    private EdmCondService edmCondService;

    //插入触发条件
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@Validated @RequestBody EdmCond edmCond) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmCondService.insert(edmCond);
        result.setData(edmCond.getId());
        return result;
    }

    //更新触发条件
    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody EdmCond edmCond) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmCondService.update(edmCond);
        return result;
    }

    /**
     * 根据触发条件id逻辑上删除触发条件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmCondService.delete(id);
        return result;
    }

    @RequestMapping(value = "/deleteByEdcoCondId/{condId}", method = RequestMethod.DELETE)
    public Result deleteByEdcoCondId(@PathVariable(value = "condId") String condId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmCondService.deleteByEdcoCondId(condId);
        return result;
    }


    @RequestMapping(value = "/{id}/cond", method = RequestMethod.PUT)
    public Result clearCond(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        edmCondService.clearCond(id);
        return result;
    }

    @RequestMapping(value = "/cond/{id}", method = RequestMethod.GET)
    public Result selectByPrimaryKey(@PathVariable(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(edmCondService.selectByPrimaryKey(id));
        return result;
    }
}
