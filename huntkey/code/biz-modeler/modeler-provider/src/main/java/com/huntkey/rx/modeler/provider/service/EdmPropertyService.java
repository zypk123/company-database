package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.EdmProperty;
import com.huntkey.rx.modeler.common.model.EdmPropertyGroup;
import com.huntkey.rx.modeler.common.model.vo.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

//linziy 添加


/**
 * Created by licj on 2017/4/13.
 */
public interface EdmPropertyService {

    int delete(String id);

    void deleteIds(String[] ids);

    int insert(EdmProperty edmProperty);

    int update(EdmProperty edmProperty);

    void move(String[] ids);

    void updateMethodById(String id,String mid);

    void updateTriggerCond(String id,String triggerCondId);

    List<EdmPropertyVO> selectEdmPropertiesByCid(String edmcId);

    List<EdmPropertyVO> selectFatherPropertiesByCid(String edmcId, boolean isConstOrNot);

    List<EdmPropertyVO> selectEdmConstByCid(String edmcId);

    List<EdmPropertyVO> getProperties(String id);

    //linziy Add--------------------------------------------------------------------------
    //根据属性查找联动属性的表头
    List<EdmLinkVO> getConnects(String id);

    List<EdmLinkVO> getConnectsByPropertyId(String id);

    EdmConvoluteVO getConvolute(String id);

    /**
     * 获取单位列表
     * @param id
     * @return
     */
    List<EdmUnitVO> getUnits(String id);
    /**
     * 根据传入的classId 与属性名模糊查询属性列表
     * @param id,edmpName
     * @return
     */
    List<EdmProperty> getPropertyNameAndByEdmClassId(String id,String edmpName);
    /**
     * 批量属性下的单位属性的类型
     * @param edunUnitType
     * @param edunEdmpId
     * @return
     */

    /**
     * 在同类中查找相似属性
     * @param Id
     */
    List<EdmProperty> findPropertyNameByIdInSameClass(String Id);
    /* ------------------------------------------------------------------------------------------------------- */

    EdmPropertyVO getProperty(String id);
    
    /**
     * 唯一性校验
     * @param edmpCode
     * @param edmpEdmcId
     * @return
     */
	String checkEdmpCode(String edmpCode, String edmpEdmcId,String edmpParentId);

    /**
     * 唯一性校验
     * @param edmpName
     * @param edmpEdmcId
     * @return
     */
    public String checkEdmpName(String edmpName,String edmpEdmcId, String edmpParentId);

	/**
     * 获取类下唯一的属性编码
     * @param edmpEdmcId
     * @return
	 */
    String getUniqueEdmpCodeByClassId(String edmpEdmcId);

    /**
     * 根据类id获取属性树
     *
     * @param id
     * @return
     */
    List<EdmPropertyVO> getPropertyTreeByClassId(String id, int flag);

    List<EdmProperty> selectDataPropertiesByCid(String edmcId,String edmpId);

    EdmConvoluteVO getConvoluteByPropertyId(String id);

    List<EdmUnitVO> getUnitsByPropertyId(String id);

    List<EdmLinkVO> getLinksByPropertyId(String id);

    String getValueLimitById(String id);

    void updateProperty(EdmProperty edmProperty);

    /**
     * 根据类id查询类的是特征值的属性
     * @param id
     * @return
     */
    List<EdmProperty> getTwoProperties(String id);

    /**
     * 根据模型id查询枚举类的是特征值的属性
     * @param id
     * @return
     */
    List<EdmProperty> getEnumTwoProperties(String id);

    HashMap<String,Object> getPropertyAndClass(String propertyId);

    List<EdmProperty> getAllSubProperties(String id);

    void deleteExtendProperty(String id);

    /**
     * 根据类id查询特征值的属性列表
     * @param id
     * @return
     */
    List<EdmProperty> selectIsCharacterByCid(String id);

    Map<String,Object> getPropertyTypeAndValue(String classId, String edmpCode);

    /**
     * 根据模型版本号和类的英文名称获取类及其父类的所有卷积属性
     * @param edmdVer
     * @param edmcNameEn
     * @return
     */
    List<EdmProperty> getConvolutionPropertiesByVersionAndEnName(String edmdVer, String edmcNameEn);

    /**
     * 将指定id的所有属性的is_visible字段更改为指定数值
     * @param modelerVO
     */
    void changePropertiesVisible(ModelerVO modelerVO);

	void clearLimitAndFormula(Map<String, String> map);
	
	void setDefaultValue(String classid, String objectId);

    EdmProperty selectById(String id);

    List<EdmPropertyGroup> selectGroups(String edmcId, String edmpId, String isSource);

    List<EdmPropertyVO> getProptreeByCid(String edmcId);

    List<String> getEdmcIdsByDataType(String dataType);

    String getEnumInfoCode(String className, String propName);

    String getInfoCodes(String className);
}
