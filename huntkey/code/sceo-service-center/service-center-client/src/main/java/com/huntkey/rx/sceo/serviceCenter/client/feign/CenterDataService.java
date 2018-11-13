package com.huntkey.rx.sceo.serviceCenter.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.client.feign.hystrix.CenterDataHystix;

/**
 * Created by zhanglb on 2017/6/24.
 */
@FeignClient(value = "serviceCenter-provider", fallback = CenterDataHystix.class)
public interface  CenterDataService {
    /**
     * 合并数据，新增或者修改
     * @param data
     * @return
     */
    @RequestMapping(value = "/testController/add", method = RequestMethod.POST)
    Result add(@RequestBody String data);

    /**
     * 删除数据
     * @param data
     * @return result
     */
    @RequestMapping(value = "/centerdata/delete", method = RequestMethod.POST)
    Result delete(@RequestBody String data);

    /**
     * 查询数据
     * @param data
     * @return result
     */
    @RequestMapping(value = "/centerdata/find", method = RequestMethod.POST)
    Result find(@RequestBody String data);

    /**
     * 修改
     * @param data
     * @return
     */
    @RequestMapping(value = "/centerdata/update", method = RequestMethod.POST)
    Result update(@RequestBody String data);
}
