package com.huntkey.rx.sceo.serviceCenter.client.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.client.feign.CenterDataService;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

/**
 * Created by zhanglb on 2017/6/24.
 */
@RestController
@RequestMapping("/centerdata")
public class CenterDataController {


    @Autowired
    private CenterDataService centerDataService;

    /**
     * 合并数据，新增或者修改
     * @param data
     * @return result
     */
    @PostMapping("/add")
    public Result add(@RequestBody String data){
        RequestAttributes ra = RequestContextHolder.getRequestAttributes();
        ra.setAttribute("userId", "123456789", 24);
        return centerDataService.add(data);
    }

    /**
     * 删除数据
     * @param data
     * @return result
     */
    @PostMapping("/delete")
    public Result delete(@RequestBody String data){
        return centerDataService.delete(data);
    }

    /**
     * 修改数据
     * @param data
     * @return result
     */
    @PostMapping("/update")
    public Result update(@RequestBody String data){
        return centerDataService.update(data);
    }

    /**
     * 查询数据
     * @param data
     * @return result
     */
    @PostMapping("/find")
    public Result find(@RequestBody String data){
        return centerDataService.find(data);
    }


}
