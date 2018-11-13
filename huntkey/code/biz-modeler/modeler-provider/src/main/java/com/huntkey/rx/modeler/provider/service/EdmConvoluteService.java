package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.modeler.common.model.EdmConvolute;
import com.huntkey.rx.modeler.common.model.vo.EdmConvoluteVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Created by licj on 2017/4/13.
 */
public interface EdmConvoluteService {

    int insert(EdmConvolute edmConvolute);
    //删
    int delete(String id);
    //改
    int update(EdmConvolute edmConvolute);
    //分页查询
    Pagination<EdmConvolute> selectByExample(String edcoEdmpId, Byte edcoUpdateType, String edcoUpdateTimeliness, String edcoUpdateTime,
                                             String edcoConvoluteFormula, int pageNum, int pageSize);

    /**
     * 卷积公式与模型关系查询
     * @param edcoConvoluteFormula
     * @param edmpName
     * @param edmcName
     * @return
     */
    List<EdmConvoluteVO> selectConvoluteVOByFormulaEdmdnameEdmpname(String edcoConvoluteFormula,String edmpName, String edmcName,String edmdVer);

    /**
     * 根据属性ID取属性的扩展属性中的卷积公式
     * @param edmpId
     * @return
     */
    String selectConvoluteFormulaByEdmpId(String edmpId);
}
