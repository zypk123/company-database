package com.huntkey.rx.purchase.provider.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.base.EdmAttachment;
import com.huntkey.rx.purchase.provider.service.CommonService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * 通用服务Controller
 *
 * @author zhangyu
 * @create 2018-01-02 14:32
 **/
@RestController
@RequestMapping("/pu/commonService")
public class CommonServiceController {

    Logger logger = LoggerFactory.getLogger(CommonServiceController.class);

    @Autowired
    private CommonService commonService;

    /**
     * 附件上传
     *
     * @param file
     * @return
     */
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    public Result uploadFile(@RequestParam("file") MultipartFile file) {
        Result result = new Result();
        try {
            EdmAttachment edmAttachment = new EdmAttachment();
            InputStream inputStream = file.getInputStream();
            String groupName = "group1";
            //文件大小
            long fileSize = inputStream.available();
            //文件扩展名
            String fileExtName = file.getOriginalFilename().substring(file.getOriginalFilename().indexOf(".") + 1);
            //文件上传操作
            StorePath path = commonService.fastDFSUpload(groupName, inputStream, fileSize, fileExtName);
            //返回文件原名称和文件上传路径
            edmAttachment.setEdmaSourceName(file.getOriginalFilename());
            edmAttachment.setEdmaPath(path.getFullPath());
            result.setData(edmAttachment);
        } catch (Exception e) {
            logger.error("附件上传接口发生错误：", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

}
