package com.huntkey.rx.modeler.client.feign;

import org.springframework.cloud.netflix.feign.FeignClient;

/**
 * Created by zhuyj on 2017/4/16.
 */
@FeignClient(value = "modeler-provider")
public interface EdmIndexDetailService {
}
