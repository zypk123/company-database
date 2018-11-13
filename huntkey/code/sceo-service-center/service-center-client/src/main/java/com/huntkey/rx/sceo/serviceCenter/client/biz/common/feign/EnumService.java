package com.huntkey.rx.sceo.serviceCenter.client.biz.common.feign;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.client.biz.common.feign.hystrix.EnumServiceFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by clarkzhao on 2017/10/24.
 *
 * @author clarkzhao
 * @date 2017/10/24
 */
@FeignClient(name = "serviceCenter-provider", fallback = EnumServiceFallback.class)
public interface EnumService {


    /**
     * feign 客户端 枚举编码对应枚举对象清单
     * @param enumCode
     * @return 枚举项
     */
    @RequestMapping("/biz/enums/{enumCode}/objects")
    Result getEnumsObjects(@RequestParam(value = "enumCode") String enumCode);

}
