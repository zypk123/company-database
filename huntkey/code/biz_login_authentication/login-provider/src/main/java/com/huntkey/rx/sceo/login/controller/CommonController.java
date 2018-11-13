package com.huntkey.rx.sceo.login.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.login.service.CommonService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * Created by lulx on 2018/1/24 0024 下午 14:41
 */
@RestController
@RequestMapping("/common")
public class CommonController {

    private static final Logger log = LoggerFactory.getLogger(CommonController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 上传文件
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    @MethodRegister(
            edmClass = "people",
            methodCate = "ID系统",
            methodDesc = "文件上传"
    )
    public Result uploadFile(@RequestParam("file") MultipartFile file, @RequestParam String fileName) {
        Result result = null;
        try {
            result = commonService.uploadFile(file, fileName);
        } catch (Exception e) {
            log.error("uploadFile error : " + e.getMessage(), e);
            throw new RuntimeException("uploadFile error : " + e.getMessage(), e);
        }
        return result;
    }
}
