package com.huntkey.rx.purchase.provider.client;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.provider.client.fallback.ModelerClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author zhangyu
 */
//开发调试可以指定URL，不允许提交到GIT
//@FeignClient(value = "modeler-provider", url = "http://10.3.98.154:2002")
@FeignClient(value = "modeler-provider")
public interface ModelerClient {

    /**
     * 获取编号方法
     *
     * @param object {"edmnEncode":"HR01","edmnType":"1"}
     * @return
     */
    @RequestMapping(value = "/numbers", method = RequestMethod.POST)
    Result getNumbers(@RequestBody JSONObject object);

}