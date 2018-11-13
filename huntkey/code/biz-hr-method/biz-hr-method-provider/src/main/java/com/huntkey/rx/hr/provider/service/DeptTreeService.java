package com.huntkey.rx.hr.provider.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.DeptStuChangeOrderDTO;
import com.huntkey.rx.hr.common.model.DeptTreeDTO;
import com.huntkey.rx.hr.common.model.DeptTreeInfoDTO;
import com.huntkey.rx.hr.common.model.OdscChagSetDTO;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * 实例代码
 * @author xuyf
 */
public interface DeptTreeService {

    /**
     * 根据部门ID查询部门信息
     * @param deptId
     * @return
     */
    DeptTreeDTO getDept(String deptId, Date date);

    DeptTreeDTO getDeptTreeList(String deptId, Date beginDate, int level, int complement);

    /**
     * 根据日期获取最顶级部门
     */
    DeptTreeInfoDTO getRootDept();

    List<DeptTreeDTO> getDeptList(String[] deptIds, Date beginDate, int complement);
    
    List<DeptTreeDTO> getParentTreeById(String pid, Date beginDate);

    JSONObject saveDeptStuChangeOrder(DeptStuChangeOrderDTO deptStuChangeOrderDTO, String odscType);

    /**
     * 部门异动单新增直属校验
     * @param deptCodes
     * @param date
     * @return
     */
    List<DeptStuChangeOrderDTO> checkDeptChangeOrderByAdd(String[] deptCodes, Date date);

    /**
     * 部门异动单修改直属校验
     * @param deptCodes
     * @param date
     * @return
     */
    List<DeptStuChangeOrderDTO> checkDeptChangeOrderByModify(String[] deptCodes, Date date);

    /**
     * 部门异动单剪切调出校验
     * @param deptCodes
     * @param date
     * @return
     */
    List<DeptStuChangeOrderDTO> checkDeptChangeOrderByMove(String[] deptCodes, Date date);

    /**
     * 部门异动单注销直属校验
     * @param deptCodes
     * @param date
     * @return
     */
    List<DeptStuChangeOrderDTO> checkDeptChangeOrderByCancel(String[] deptCodes, Date date);

    /**
     * 部门异动单责任人任免校验
     * @param deptCodes
     * @param date
     * @return
     */
    List<DeptStuChangeOrderDTO> checkDeptChangeOrderByODCA(String[] deptCodes, Date date);


    DeptStuChangeOrderDTO getDeptChangeOrderById(String orderId);

    DeptStuChangeOrderDTO getDeptChangeOrderAddById(String orderId);

    DeptStuChangeOrderDTO getDeptChangeOrderEditById(String orderId);

    void deleteDeptByIds(String[] deptIds);

    void getDeptSuperiorIdList(List<String> deptIdList, String deptId, Date date, int superiorLevel);

    void getDeptLowerIdList(List<String> deptIdList, String deptId, Date date, int lowerLevel);


    /**
     * FIXME 递归查询所有的子部门及下下级部门列表
     * @param deptId  部门对象Id
     * @param deptTreeDTOList  接受部门对象的容器
     */
    void findAllChildDeptNodes(String deptId, List<DeptTreeInfoDTO> deptTreeDTOList);


    JSONObject getOrderType(String code);

    DeptTreeInfoDTO getDeptByCode(String code);

    /**
     * 审核部门单据
     *  @param auditSet
     * @author fyw
     */
    Result audit(JSONObject auditSet);

}
