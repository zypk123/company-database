package com.huntkey.rx.sceo.common.service.client.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.client.feign.MenuService;
import com.huntkey.rx.sceo.common.service.common.model.Menu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:描述
 * @author: Wangning
 * @date: 2017年5月5日14:10:43
 */
@RestController
@RequestMapping("${adminPath}/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    @RequestMapping(method = RequestMethod.GET)
    public Result getAllMenu() {
        Result res = new Result();
        try {
            res = menuService.getAllMenu();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/parent/{pid}", method = RequestMethod.GET)
    public Result getSecAllMenu(@PathVariable("pid") String pid) {
        Result res = new Result();
        try {
            res = menuService.getSecAllMenu(pid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result getMenuById(@PathVariable("id") String id) {
        Result res = new Result();
        try {
            res = menuService.getMenuById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Menu menu) {
        Result res = new Result();
        try {
            res = menuService.save(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(method = RequestMethod.PUT)
    public Result update(@RequestBody Menu menu) {
        Result res = new Result();
        try {
            res = menuService.update(menu);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/child/{id}", method = RequestMethod.GET)
    public Result hasChild(@PathVariable("id") String id) {
        Result res = new Result();
        try {
            res = menuService.hasChild(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("id") String id) {
        Result res = new Result();
        try {
            res = menuService.delete(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return res;
    }
}
