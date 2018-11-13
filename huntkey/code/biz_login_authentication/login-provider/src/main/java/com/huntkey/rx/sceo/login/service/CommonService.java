package com.huntkey.rx.sceo.login.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.common.entity.EmailVo;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 * 公共服务
 * 短信
 * 邮件
 * Created by lulx on 2017/12/25 0025 下午 14:32
 */
@Component
public interface CommonService {

    /**
     * 发送短信
     * @param tel 手机号
     * @param content 内容
     * @return
     */
    Result sendMsg(String tel, String content);

    /**
     * 发送邮件
     * @param emailVo
     * @return
     */
    Result sendEmail(EmailVo emailVo);

    /**
     * 文件上传
     * @param file
     * @param fileName
     * @return
     */
    Result uploadFile(MultipartFile file, String fileName);
}
