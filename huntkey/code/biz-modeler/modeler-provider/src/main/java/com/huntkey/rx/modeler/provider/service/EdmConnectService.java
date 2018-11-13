package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.modeler.common.model.EdmConnect;
import com.huntkey.rx.modeler.common.model.vo.EdmConnectVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Created by licj on 2017/4/13.
 */
public interface EdmConnectService {

    int insert(EdmConnect edmConnect);
    //删
    void delete(String id);
    //批量删除
    void deleteIds(String[] ids);
    //改
    int update(EdmConnect edmConnect);
    //查
    EdmConnect selectbyid(String id);

    //分页查
    Pagination<EdmConnect> selectByExample(String edcnEdmpId, Byte edcnLinkPreservable, Byte edcnUpdateType, int pageNum, int pageSize);

    EdmConnectVO getConnectByEdmpId(String edcnEdmpId);

    List<EdmConnect> getConnectsOfClass(String classId);
}
