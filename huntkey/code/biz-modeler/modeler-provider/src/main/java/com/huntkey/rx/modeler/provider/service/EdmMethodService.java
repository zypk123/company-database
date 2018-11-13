package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodAndArgVO;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodTypeVO;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodVO;

import java.util.List;

/**
 * 
 * @ClassName: EdmMethodService
 * @Description: 方法Service接口
 * @author: zhangyu
 * @date: 2017年4月12日上午10:17:47
 *
 */
public interface EdmMethodService {

	/**
	 * 新增方法
	 * 
	 * @param edmMethod
	 * @return
	 */
	int insert(EdmMethod edmMethod);

	/**
	 * 查询,带分页和条件查询
	 * 
	 * @param edmmType
	 *            方法分类
	 * @param edmmProgramType
	 *            程序类型
	 * @param edmmName
	 *            方法名称
	 * @param edmmClasses
	 *            所属类
	 * @param edmmStatus
	 *            方法状态
	 * @param pageNum
	 *            分页参数 (当前页)
	 * @param pageSize
	 *            分页参数(每页的大小)
	 * @return
	 */
	Pagination<EdmMethodVO> selectByExample(String edmmType, String edmmProgramType, String edmmName, String edmmClasses,
											String edmmStatus,int pageNum, int pageSize);

	/**
	 * 通过id查询
	 * @param id
	 * @return
	 */
	EdmMethodAndArgVO selectByPrimaryKey(String id);

	/**
	 * 更新方法
	 * 
	 * @param edmMethod
	 * @return
	 */
	int updateByPrimaryKey(EdmMethod edmMethod);

	/**
	 * 删除方法
	 * 
	 * @param id
	 * @return
	 */
	int deleteByPrimaryKey(String id);

	/**
	 * 方法的上移、下移
	 * 
	 * @param ids
	 */
	void move(String[] ids);

	/**
	 * 查询方法分类结构。
	 *
	 * @return edmMethodTypeVO
	 */
	List<EdmMethodTypeVO> queryClassMethodTypeTree();

	/**
	 * 方法与类的关系查询
	 * @param type
	 * @param name
	 * @param version
	 * @param className
	 * @return
	 */
	List<EdmMethodVO> queryMethodsAndClasses(String type, String name, String version, String className);

	/**
	 * 根据类id查方法id
	 * @param classId
	 * @return
	 */
	String[] selectMethodIdsByClassId(String classId);
	
	/**
	 * 检验方法名称唯一性
	 * @param edmmName
	 * @return
	 */
	String checkUnique(String edmcId,String edmmName);
	
	/**
	 * 通过方法id查找方法名
	 * 
	 * @param id
	 * @return
	 */
	String geMethodNameById(String id);

	/**
	 * 批量删除方法
	 * @param ids
	 */
	void deleteMethods(String[] ids);

	/**
	 * 根据方法名获取方法体
	 * @param edmmName
	 * @return
	 */
	StringBuffer getMethodBody(String edmmName);

	/**
	 * 插入方法、方法参数、方法返回值
	 * @param edmMethodAndArgVO
	 * @return
	 */
	void insertEdmMethodAndArgVO(EdmMethodAndArgVO edmMethodAndArgVO);

	void updateMethodAndArg(EdmMethodAndArgVO edmMethodAndArgVO);

	/**
	 * 通过方法id禁/启用方法
	 * @param id
	 * @param edmmStatus
	 * @return
	 */
	void updateEdmmStatus(String id,String edmmStatus);


	/**
	 * 通过方法id 类id返回方法名和类名
	 * @param id
	 * @param edmClassId
	 * @return
	 */
	String getMethodByIdClassId(String id,String edmClassId);


	void updateTriggerCond(String id,String condId);

	void deleteTriggerCond(String id);

	EdmMethod getSimpleMethodById(String id);

	String getEdmcIdByEdmcName(String edmcName, String version);

	EdmMethodAndArgVO getEdmMethodByEdmcNameAndEdmmName(String edmcName, String edmmName);

	String checkEdmmName(String edmcName, String edmmName);
}
