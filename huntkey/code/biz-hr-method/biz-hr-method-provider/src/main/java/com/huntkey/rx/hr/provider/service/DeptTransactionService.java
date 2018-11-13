package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.DepttreeEntity;
import com.huntkey.rx.hr.common.model.DeptStuChangeOrderDTO;
import com.huntkey.rx.hr.common.model.OdscChagSetDTO;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 部门调动服务接口
 * @author Created by liuwens on 2017/11/15.
 */
public interface DeptTransactionService {

    /**
     * 查询部门调动列表
     * @param orderIdValue
     * @param orderTypeValue
     * @return
     */
    Result loadMoveDeptOrderService(String orderIdValue, String orderTypeValue);

    /**
     * 加载待调动部门信息列表
     * @param odscBeg
     * @param deptIdList
     * @return
     */
    Result queryDeptListService(String odscBeg , List<String> deptIdList);

    /**
     * 部门结构异动单保存接口
     * @param jsonObject
     * @return
     */
    Result saveDeptTransactionOrderService(JSONObject jsonObject);

    /**
     * 部门调动单据批准通过方法
     * @param orderInstanceId 部门调动异动单id
     * @return
     */
    Result passMoveOrder(String orderInstanceId);

    /**
     * 部门注销单据批准通过方法
     * @param orderInstanceId 部门调动异动单id
     * @return
     */
    Result passDeptDeleteOrder(String orderInstanceId);

    /**
     * 调动单调动校验方法
     * @param newPdeptIdValue
     * @param delayMoveDeptIdList
     * @param odscBeg 有效时间
     * @return
     */
    Result deptMoveValidateService(String newPdeptIdValue, List<String> delayMoveDeptIdList, Date odscBeg);


    /**
     * 部门注销单调动校验方法
     * @param delayMoveDeptIdList
     * @param odscBeg 有效时间
     * @return
     */
    Result deptDeleteValidateService(List<String> delayMoveDeptIdList, Date odscBeg);

    /**
     * FIXME 递归查询所有的子部门及下下级部门列表
     * @param deptId  部门对象Id
     * @param deptLvCode [可选] 当前部门的层级码，作为其下级部门的层级码-上级层级码段，如：[001,002],001
     * @param childMap  接受部门对象的容器
     */
    void findAllChildDeptNodes(String deptId, String deptLvCode, Map<String, DepttreeEntity> childMap);

    /**
     * 递归查询上级部门及上上级部门，追溯到根节点, 返回平行的列表，不是树形结构
     * @param pDeptId  直属上级部门对象Id
     * @param dtoMap
     */

    void findAllParentDeptList(String pDeptId, Map<String, DepttreeEntity> dtoMap);

    /**
     *
     * @param deptStuChangeOrderDTO
     * @return
     * @throws Exception
     */
    Result submit(DeptStuChangeOrderDTO deptStuChangeOrderDTO);

    List<Map<String, Object>> getBaseInfoByConditions(Map<String,Object> paramMap, List<String> columnList, Class c);

    /**
     * 查询部门结构异动单据
     * @param ormParam
     * @param odscType
     * @return
     */
    List<DeptStuChangeOrderDTO> findDeptStuChangeOrderList(OrmParam ormParam , String odscType);

    /**
     * 根据单据对象id和单据类型，查找单据对象
     * @param orderIdValue
     * @param odscType
     * @return
     */
    DeptStuChangeOrderDTO findDeptStuChangeOrderById(String orderIdValue , String odscType);

    /**
     * 查询异动列表
     * @param ormParam
     * @return
     */
    List<OdscChagSetDTO> findDeptOdscChagSetList(OrmParam ormParam);

    /**
     * 由于异动列表可能有部门被删除，先执行清空异动列表所有记录，再做新增
     * @param delOdscChagSetIdList 待删除的异动列表
     */
    boolean deleteOdscChagSetById(List<String> delOdscChagSetIdList);

    /**
     * 如果该单据已提交，retCode为0；没有提交，retCode为1
     * @param orderId
     * @return
     */
    Result checkIsSubmit(String orderId);

}
