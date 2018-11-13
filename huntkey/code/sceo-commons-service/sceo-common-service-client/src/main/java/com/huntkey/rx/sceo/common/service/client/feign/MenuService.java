package com.huntkey.rx.sceo.common.service.client.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.service.client.feign.hystrix.MenuServiceHystrix;
import com.huntkey.rx.sceo.common.service.common.model.Menu;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Description:菜单操作
 * @author: Wangning
 * @date: 2017年5月5日14:13:00
 */
@FeignClient(value = "commonService-provider", fallback = MenuServiceHystrix.class)
public interface MenuService {

    @RequestMapping(value = "/menu", method = RequestMethod.GET)
    Result getAllMenu();

    /**
     * 根据ID查询一条菜单具体信息
     *
     * @param id 菜单ID
     * @return
     */
    @RequestMapping(value = "/menu/{id}", method = RequestMethod.GET)
    Result getMenuById(@PathVariable("id") String id);

    /**
     * 保存菜单信息
     *
     * @param menu 菜单信息
     * @return
     */
    @RequestMapping(value = "/menu", method = RequestMethod.POST)
    Result save(@RequestBody Menu menu);

    /**
     * 更新菜单信息
     *
     * @param menu 菜单信息
     * @return
     */
    @RequestMapping(value = "/menu", method = RequestMethod.PUT)
    Result update(@RequestBody Menu menu);

    @RequestMapping(value = "/menu/child/{id}", method = RequestMethod.GET)
    Result hasChild(@PathVariable("id") String id);

    @RequestMapping(value = "/menu/{id}", method = RequestMethod.DELETE)
    Result delete(@PathVariable("id") String id);

    @RequestMapping(value = "/menu/parent/{pid}", method = RequestMethod.GET)
    Result getSecAllMenu(@PathVariable("pid") String pid);

}
