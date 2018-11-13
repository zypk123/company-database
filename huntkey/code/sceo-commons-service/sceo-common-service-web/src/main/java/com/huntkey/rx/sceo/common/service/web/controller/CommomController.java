package com.huntkey.rx.sceo.common.service.web.controller;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:描述
 * @author: DongHuan
 * @date: 2017年4月20日 上午10:44:49
 */
@Controller
@EnableAutoConfiguration
@RequestMapping("/")
public class CommomController {

    @RequestMapping("/index")
    public String mgrIndex() {
        return "mgrIndex";
    }

    @RequestMapping("/oldIndex")
    public String index() {
        return "index";
    }

    @RequestMapping("/menu/list")
    public String menu() {
        return "menuMgr/menuList";
    }

    @RequestMapping("/dict/list")
    public String dictList() {
        return "dictMgr/dictList";
    }

    @RequestMapping("/form/list")
    public String formList() {
        return "autoForm/formList";
    }
}
