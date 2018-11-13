package com.huntkey.rx.modeler.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.client.feign.EdmCondService;
import com.huntkey.rx.modeler.common.model.EdmCond;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by liangh on 2017/7/12.
 */

@RestController
@RequestMapping("/v1/conds")
public class EdmCondController {

    private static Logger log = LoggerFactory.getLogger(EdmCondController.class);

    @Autowired
    private EdmCondService edmCondService;

    /**
     * 插入触发条件
     *
     * @param edmCond
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result addConds(@Validated @RequestBody EdmCond edmCond) {
        Result result = edmCondService.addConds(edmCond);
        return result;
    }

    /**
     * 更新触发条件
     *
     * @param edmCond
     * @return
     */
    @RequestMapping(method = RequestMethod.PUT)
    public Result updateConds(@RequestBody EdmCond edmCond) {
        Result result = edmCondService.updateConds(edmCond);
        return result;
    }

    /**
     * 根据触发条件id逻辑上删除触发条件
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable String id) {
        Result result = edmCondService.delete(id);
        return result;
    }

    @RequestMapping(value = "/deleteByEdcoCondId/{condId}", method = RequestMethod.DELETE)
    public Result deleteByEdcoCondId(@PathVariable(value = "condId") String condId) {
        Result result = edmCondService.deleteByEdcoCondId(condId);
        return result;
    }

    @RequestMapping(value = "/{id}/cond", method = RequestMethod.PUT)
    Result clearCond(@PathVariable(value = "id") String id) {
        Result result = edmCondService.clearCond(id);
        return result;
    }

    @RequestMapping(value = "/cond/{id}", method = RequestMethod.GET)
    public Result selectByPrimaryKey(@PathVariable(value = "id") String id) {
        Result result = edmCondService.selectByPrimaryKey(id);
        return result;
    }
}
