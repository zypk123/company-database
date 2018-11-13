package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.modeler.common.model.EdmMethodType;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodTypeVO;

import java.util.List;

/**
 * Created by gujing on 2017/4/19 0019.
 */


public interface EdmMethodTypeService {

    /**
     * 根据方法分类id查询，结果集包含子节点。id为空时，返回所有节点。
     * @param id
     * @return
     */
    List<EdmMethodTypeVO> selectEdmMethodTypeVOWithSonById(String id);

    /**
     * 根据id删除方法分类，不能删除有子节点的方法分类，不能删除存在方法的方法分类。
     * @param id
     * @return
     */
    int delete(String id);

    /**
     * 新增方法树节点
     * @param edmMethodType
     * @return
     */
    int insert(EdmMethodType edmMethodType);

    /**
     * 修改方法分类节点
     * @param edmMethodType
     * @return
     */
    int update(EdmMethodType edmMethodType);

    /**
     * 方法分类本级节点包含的所有方法移动到指定节点检验
     * @param edmtParent
     * @param id
     * @return
     */
    String checkFatherNode(String edmtParent ,String id);

    /**
     * 方法分类本级节点包含的所有方法移动到指定节点
     * @param sourceMethodtypeId
     * @param aimMethodtypeId
     */
    void moveMethodtypeMethodToMethodtype(String sourceMethodtypeId, String aimMethodtypeId);


    /**
     * 检查方法分类代码唯一性
     * @param code
     * @return
     */
    String checkCodeUnique(String code);

    /**
     * 检查方法分类名称唯一性
     * @param name
     * @return
     */
    String checkNameUnique(String name);

    /**
     * 删除时验证方法分类节点下是否有子节点，是否有方法
     * @param id
     * @return
     */
    String deleteValidate(String id);

    EdmMethodType selectMethodTypeById(String id);
}
