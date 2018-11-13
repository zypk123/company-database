package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.EdmClass;
import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.common.model.EdmProperty;
import com.huntkey.rx.modeler.common.model.vo.EdmClassVO;
import com.huntkey.rx.modeler.common.model.vo.EdmIndexVO;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodVO;
import com.huntkey.rx.modeler.common.model.vo.EdmPropertyVO;

import java.util.List;
import java.util.Map;

/**
 * Created by licj on 2017/4/13.
 */
public interface EdmClassService {

	List<EdmClassVO> selectEdmClassesByMid (String mid) throws Exception;

    List<EdmClassVO> getRelateClasses(String id);

    EdmClassVO selectById(String id);

	EdmClassVO selectSimpleClassById(String id);

	int delete(String id);

	int insert(EdmClass edmClass);

	int update(EdmClass edmClass);

	/**
	 * 通过类的ID查找该类的方法列表
	 * 
	 * @param id
	 *            类的id
	 * @return
	 */
	List<EdmMethodVO> selectEdmMethodByClassId(String id);

	/**
	 * 通过类的ID查找该类的所有父类的方法列表
	 *
	 * @param id
	 *            类的id
	 * @return
	 */
	List<EdmMethodVO> selectEdmFatherMethodByClassId(String id);

    void copy(EdmClass edmClass);
	
	/**
	 * 唯一性校验
	 * @param edmcCode
	 * @param edmcEdmdId 模型id
	 * @return
	 */
	String checkEdmcCode(String edmcCode, String edmcEdmdId);

	String checkEdmcName(String edmcName, String edmcEdmdId);

	String checkEdmcShortName(String edmcShortName, String edmcEdmdId);

	boolean isChild(String id, String sid);

	/**
	 * 根据类id和方法id批量删除方法
	 * @param methodIds
	 */
	void updateBatchIsDelByClassIdAndMethodIds(String[] methodIds);

	/**
	 * 根据类id查找类自身及其相关类
	 *
	 * @param id
	 * @return
	 */
	List<EdmClassVO> getRelateClass(String id);

	/**
	 * 类的上移下移
	 * @param ids
	 */
	void move(String[] ids);

	/**
	 * 类树查询接口
	 *
	 * @param modelerId
	 * @param edmcNameEns
	 * @return
	 */
	EdmClassVO queryClassTree(String modelerId, String[] edmcNameEns);

	EdmClass selectSpecialClass(String id, String shortName);

    String checkEdmcNameEn(String edmcNameEn, String edmcEdmdId);

	EdmClass selectEdmClassById(String id);

	List<EdmClass> getPublishModelerClass();

	Map getClassAndRelateClass(String classId, String[] classIdArray);

	Map<String,List<String>> getOrmIndexs(String edmdVer, String name);

	Map<String, Object> getOrmFeilds(String edmdVer, String name, String attr);

	Map<String,List<String>> getOrmBaseEdmCode(String edmdVer, String name);

	Map<String,List<String>> getOrmAttrEdmCode(String edmdVer, String name, String attr);

	String getDbType(String edmdVer, String name);

	/**
	 * 根据类id查询可选的关联属性
	 * @param classId
	 * @return
	 */
	List<EdmPropertyVO> getChooseRelateProperty(String classId);

	Map<String,List<String>> getAllEdmCode(String edmdVer, String name, String attr);
	
	boolean isSet(String id);
	String getEdmShortName(String edmdVer, String name);

	List<EdmClass> getEntityByVersionAndEnglishName(String edmdVer, String edmcNameEn);

	/**
	 * 查询引用对象的类英文名
	 * @param edmdVer
	 * @param name
	 * @return
	 */
	Map<String, Map<String, Object>> getEdmAttrObject(String edmdVer, String name);

	/**
	 * 查询edm的属性数据类型
	 * @param edmdVer
	 * @param name
	 * @return
	 */
	List<Map<String, Object>> getEdmDataType(String edmdVer, String name);

	/**
	 * 根据类英文名和版本id来查询类
	 * @param edmcNameEn 类英文名
	 * @param edmcVer 类版本号
	 * @return
	 */
	String getClassByEdmNameEn(String edmcNameEn,String edmcVer);


	/**
	 * 根据类id获取类下的属性集和属性集中属性集
	 * @param edmcId
	 * @return
	 */
	List<Map<String, String>> selectAssemblesByEdmcId(String edmcId);


	List<Map<String,String>> selectProperties(String edmcId, String assembleId);

	List<EdmIndexVO> selectIndexByEdmcId(String edmcId);
	List<EdmIndexVO> getParentIndexByEdmcId(String edmcId);

	EdmMethod getMethodsByEdmcNameVer(String ver, String className, String methodName);

	List<EdmClass> getEdmClasses(String[] ids);

    List<EdmClass> getMonitorClasses(String[] ids);

	List<EdmClass> getResourcesClasses(String version);

	String getEdmNameByShortName(String edmdVer, String name);

	EdmClassVO getEdmClassesByEdmcCode(String edmcCode);

    List<EdmProperty> getPropertiesOfEdmClass(String classId);

    String getClassNamesByIds(String ids);

	/**
	 *根据类编码获取类Id
	 * @param edmcCode
	 * @return
	 */
	Map<String,String> getIdByEdmcCode(String edmcCode);

	List<Map<String, String>> getLinkMethod(String classId, String edmmMethodType);

    List<EdmProperty> getAllProperties(String id);

	List<EdmClassVO> selectEdmClassesAndProps(String ids) throws Exception;
}
