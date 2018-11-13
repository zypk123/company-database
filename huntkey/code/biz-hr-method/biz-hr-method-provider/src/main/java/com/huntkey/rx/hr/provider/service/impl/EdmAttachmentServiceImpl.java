package com.huntkey.rx.hr.provider.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.EmppostchangeapplyEntity;
import com.huntkey.rx.edm.entity.EmppostiveapplyEntity;
import com.huntkey.rx.hr.common.model.DeptTreeConstants;
import com.huntkey.rx.hr.common.model.EdmApplyPositiveConstants;
import com.huntkey.rx.hr.common.model.EdmAttachment;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.client.ORMClient;
import com.huntkey.rx.hr.provider.service.EdmAttachmentService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.emun.OperatorType;
import com.huntkey.rx.sceo.serviceCenter.common.model.ConditionNode;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.List;
import java.util.Map;


/**
 * Created by licj on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class EdmAttachmentServiceImpl implements EdmAttachmentService {

    private static Logger log = LoggerFactory.getLogger(EdmAttachmentServiceImpl.class);

    @Autowired
    private DefaultGenerateStorageClient defaultGenerateStorageClient;

    @Autowired
    OrmService ormService;


    @Override
    public ResponseEntity<byte[]> fastDFSDownload(String id, HttpServletResponse response) {
        String groupName = "group1";

        byte[] content = null;
        HttpHeaders headers = new HttpHeaders();

        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmApplyPositiveConstants.OEPA_REPORT_URL).addColumn(EdmApplyPositiveConstants.OEPA_FILE_NAME);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID,id)));

        try{
            List<Map<String,Object>> mapList = ormService.selectMapList(EmppostiveapplyEntity.class,ormParam);
            if(null!=mapList&&mapList.size()>0){
                String edmPath = NullUtils.valueOf(mapList.get(0).get(EdmApplyPositiveConstants.OEPA_REPORT_URL)).replace(groupName + "/", "");;
                String filename =  NullUtils.valueOf(mapList.get(0).get(EdmApplyPositiveConstants.OEPA_FILE_NAME));
                DownloadByteArray callback = new DownloadByteArray();
                content = defaultGenerateStorageClient.downloadFile(groupName, edmPath, callback);
                try {
                    headers.setContentDispositionFormData("attachment", new String(filename.getBytes("UTF-8"), "iso-8859-1"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);

                return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

}
