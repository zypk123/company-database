package com.huntkey.rx.purchase.provider.service;

import com.github.tobato.fastdfs.domain.StorePath;

import java.io.InputStream;

/**
 * 通用Service接口
 *
 * @author zhangyu
 * @create 2018-01-02 11:49
 **/
public interface CommonService {

    /**
     * fastDFS文件上传
     *
     * @param groupName
     * @param inputStream
     * @param fileSize
     * @param fileExtName
     * @return
     */
    StorePath fastDFSUpload(String groupName, InputStream inputStream, long fileSize, String fileExtName);
    
    /**
     * 获取编号(职位编码，单据编号)
     *
     * @param nbrlCode
     * @return
     */
    String getCode(String nbrlCode);

}
