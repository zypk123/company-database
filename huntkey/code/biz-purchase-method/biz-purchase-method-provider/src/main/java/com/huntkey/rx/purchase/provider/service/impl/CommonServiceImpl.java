package com.huntkey.rx.purchase.provider.service.impl;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.provider.client.InformationClient;
import com.huntkey.rx.purchase.provider.service.CommonService;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;

/**
 * 通用接口Service实现类
 *
 * @author zhangyu
 * @create 2018-01-02 11:53
 **/
@Service
public class CommonServiceImpl implements CommonService {

    Logger logger = LoggerFactory.getLogger(CommonServiceImpl.class);

    @Autowired
    private DefaultGenerateStorageClient defaultGenerateStorageClient;

    @Autowired
    private InformationClient informationClient;

    @Override
    public StorePath fastDFSUpload(String groupName, InputStream inputStream, long fileSize, String fileExtName) {
        return defaultGenerateStorageClient.uploadFile(groupName, inputStream, fileSize, fileExtName);
    }

    @Override
    public String getCode(String nbrlCode) {
        Result codeResult = informationClient.getNumbers(nbrlCode, null);
        if (codeResult.getData() == null) {
            throw new ApplicationException(Result.RECODE_ERROR, codeResult.getErrMsg());
        }
        return codeResult.getData().toString();
    }
}
