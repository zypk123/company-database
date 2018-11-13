package com.huntkey.rx.modeler.provider.service;


import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.common.model.EdmClassFormat;
import com.huntkey.rx.modeler.common.model.vo.EdmClassFormatVO;

import java.util.List;
import java.util.Map;

public interface EdmClassFormatService {

	/**
	 * 根据类Id查询对象呈现格式列表
	 * @param edmcId
	 * @return
	 */
	List<EdmClassFormatVO> selectClassFormatListByEdmcId(String edmcId);

	/**
	 * 批量新增对象呈现格式
	 * @param edmClassFormatList
	 * @return
	 */
	String insertBatch(List<EdmClassFormat> edmClassFormatList);

    /**
     * 批量删除对象呈现格式
     * @param ids
     * @return
     */
    void deleteBatch(String[] ids);

	/**
	 * 获取类的特征值及显示格式
	 *
	 * @param classId
	 * @return
	 */
	Map getCharacterAndFormat(String classId);

	/**
	 * 呈现对象与枚举对象
	 *
	 * @param classId
	 * @return
	 */
	Pagination<Map<String,String>> getAppearAndEnumObject(String classId,String name, int pageNum, int pageSize);

    Result selectWordlists(String wordName, int pageNum, int pageSize);
}