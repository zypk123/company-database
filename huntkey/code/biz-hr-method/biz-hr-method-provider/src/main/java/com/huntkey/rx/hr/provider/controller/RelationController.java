package com.huntkey.rx.hr.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.provider.service.RelationService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hr/relations")
public class RelationController {
    @Autowired
    RelationService relationService;

    /**
     * 查询伙伴对象清单
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    @MethodRegister(
            edmClass = "relation",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "查询伙伴类对象清单"
    )
    public Result queryRelations() {
        return relationService.getAllRelations();
    }
}
