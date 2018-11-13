package com.huntkey.rx.hr.provider.client;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.provider.config.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * @author clark
 * 编码生成器正式接口
 */
//@FeignClient(value = "information-provider",url = "http://10.3.98.154:2006")
@FeignClient(value = "information-provider",configuration = FeignConfiguration.class)
public interface InformationClient {

    /**
     * 编码获取接口,nbrCode 传值参考：
     * 部门编码：Department
     * 工号:JobNumber
     * 职位编号：Position
     * 岗位编码：Quarters
     * 业务单号：HR01~HR19
     * @param nbrlCode
     * @param params
     * @return
     */
    @RequestMapping(value = "/getCode/callByActive",method = RequestMethod.POST )
    Result getNumbers(@RequestParam(value ="nbrlCode" ) String nbrlCode,
                        @RequestParam(value = "params",required = false) String params);

}
