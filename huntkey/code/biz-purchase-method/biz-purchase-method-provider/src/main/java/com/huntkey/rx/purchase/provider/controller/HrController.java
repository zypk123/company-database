package com.huntkey.rx.purchase.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.provider.client.HrClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * HR系统Controller
 *
 * @author zhangyu
 * @create 2018-01-13 14:41
 **/
@RestController
@RequestMapping("/pu/hr")
public class HrController {

    @Autowired
    private HrClient hrClient;

    /**
     * 模糊查询员工对象
     *
     * @param searchName
     * @return
     */
    @RequestMapping(value = "/queryEmployee", method = RequestMethod.GET)
    public Result queryEmployee(@RequestParam String searchName) {
        return hrClient.queryEmployee(searchName);
    }

    /**
     * 根据ID查询员工对象
     *
     * @param employeeId 员工id
     * @param type
     * @return
     */
    @RequestMapping(value = "/findEmployeeById", method = RequestMethod.GET)
    public Result findEmployeeById(@RequestParam String employeeId, @RequestParam int type) {
        return hrClient.findEmployeeById(employeeId, type);
    }

}
