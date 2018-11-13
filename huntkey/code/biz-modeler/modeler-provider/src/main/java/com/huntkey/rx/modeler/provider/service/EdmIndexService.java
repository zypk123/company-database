package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.vo.EdmIndexVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.cloud.netflix.feign.FeignClient;

import java.util.List;
import java.util.Map;

/**
 * Created by zhuyj on 2017/4/16.
 */
public interface EdmIndexService {
    int insert(EdmIndexVO edmIndexVO);
    int update(EdmIndexVO edmIndexVO);
    int deletes(String ids[]);
    String checkIndexName(String indexName,String edmcId,String id);
    String checkIndexIndexProppertyIds(String indexProppertyIds);
    String checkPrimaryIndex(String edmClassId, String edmIndexId);
}

