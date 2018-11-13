package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.modeler.common.model.EdmUnit;
import com.huntkey.rx.modeler.common.model.vo.EdmUnitVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Created by licj on 2017/4/13.
 */
public interface EdmUnitService {

    //增
    int insert(EdmUnit edmUnit);
    //删
    int delete(String id);
    //批量删除
    void deleteIds(String[] ids);
    //改
    int update(EdmUnit edmUnit);
    //查
    EdmUnit selectbyid(String id);
    //分页查询
    Pagination<EdmUnit> selectByExample(String edunEdmpId, String edunQtyEdmpId, int pageNum, int pageSize);

}
