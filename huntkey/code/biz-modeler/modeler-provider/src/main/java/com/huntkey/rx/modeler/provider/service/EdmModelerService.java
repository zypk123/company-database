package com.huntkey.rx.modeler.provider.service;


import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.modeler.common.model.EdmModeler;
import com.huntkey.rx.modeler.common.model.EdmModelerExample;
import com.huntkey.rx.modeler.common.model.vo.EdmModelerVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

public interface EdmModelerService {

	/**
	 * 根据模型版本和模型更新说明查询模型
	 * @param edmdVer
	 * @param edmdUpdateDesc
	 * @param pageNum
	 * @param pageSize
	 * @return EdmModelerVO
	 */
	Pagination<EdmModelerVO> selectModelerListByExample(String edmdVer, String edmdUpdateDesc, int pageNum, int pageSize);

	/**
	 * 根据模型id查询模型
	 * @param id
	 * @return EdmModelerVO
	 */
	EdmModelerVO selectModelerByPrimaryKey(String id);

	List<EdmModelerVO> selectByPrimaryKey(String id);

	/**
	 * 新增模型
	 * @param edmModeler
	 * @return
	 */
	void insert(EdmModeler edmModeler);

	/**
	 * 更新模型
	 * @param edmModeler
	 * @return
	 */
	void updateByPrimaryKey(EdmModeler edmModeler);

	/**
	 * 删除模型
	 * @param id
	 * @return
	 */
	int updateIsDelByPrimaryKey(String id);

	/**
	 * 查询当前可用的最大版本号
	 * @param
	 * @return newModelerVer
	 */
	String selectModelerVer();

	/**
	 * 在进行新增模型之前的判断，如有处于编辑状态的模型时，addStatus返回0，否则返回1,当没有处于编辑状态的模型时，即可新增模型，
	 * 查询是否有已发布模型，如有，则查询出其id
	 * @param
	 * @return addStatus
	 */
	EdmModelerVO beforeAdd();

	/**
	 * 判断路径是否存在，如果不存在则新建路径
	 * @param fileUrl
	 * @return fileUrl
	 */
	String judgeFilePath(String fileUrl);

	/**
	 * 校验模型版本号唯一性
	 * @param edmdVer
	 * @return String
	 */
	String checkUnique(String edmdVer);

	/**
	 * 获取所有的版本名
	 * @return
	 */
	List<String> getAllVers();

	List<EdmModelerVO> getShowList(EdmModelerExample example);

	void insertAllData(String newModelerId, String oldModelerId);

	String queryModelerIdByVer(String ver);

    HSSFWorkbook exportExcel(String id);
}