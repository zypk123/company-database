package com.huntkey.rx.hr.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.provider.service.SchoolService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by weijian on 2017/12/14.
 */
@RestController
@RequestMapping("/hr/school")
public class SchoolController {
    @Autowired
    private SchoolService schoolService;
    /**
     * 查询伙伴对象清单
     *
     * @return
     */
    @MethodRegister(
            edmClass = "school",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "根据学校名称或代码查寻学校",
            getReqParamsNameNoPathVariable = {"serchContent"}
    )
    @RequestMapping(value = "/querySchools", method = RequestMethod.GET)
    public Result querySchools(@RequestParam(value = "serchContent") String serchContent) {
        return schoolService.getSchools(serchContent);
    }

}
