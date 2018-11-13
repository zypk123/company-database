package com.huntkey.rx.purchase.provider.client;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.provider.client.fallback.HrClientFallback;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

/**
 * HR系统接口
 *
 * @author zhangyu
 * @create 2018-01-13 14:33
 **/
//开发调试可以指定URL，不允许提交到GIT
//@FeignClient(value = "biz-hr-method", url = "http://10.3.98.155:9000")
@FeignClient(value = "biz-hr-method")
public interface HrClient {

    /**
     * 模糊查询员工对象
     *
     * @param searchName
     * @return
     */
    @RequestMapping(value = "/hr/employee/queryEmployee", method = RequestMethod.GET)
    Result queryEmployee(@RequestParam(value = "searchName") String searchName);

    /**
     * 根据ID查询员工对象
     *
     * @param employeeId 员工id
     * @param type
     * @return
     */
    @RequestMapping(value = "/hr/employee/findEmployeeById", method = RequestMethod.GET)
    Result findEmployeeById(@RequestParam(value = "employeeId") String employeeId, @RequestParam(value = "type") int type);
}
