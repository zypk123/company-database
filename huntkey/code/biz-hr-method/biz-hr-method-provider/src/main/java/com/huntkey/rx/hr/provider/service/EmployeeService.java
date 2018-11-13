package com.huntkey.rx.hr.provider.service;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.EmployeeEntity;
/**
 * 实例代码
 */
public interface EmployeeService {
    /**
     * 根据部门名称模糊查询
     * @param deptName
     * @return
     */

    Result queryEmployee(String deptId,String searchName, int removeLeav,
    		int startPage, int rows);
    /***
     * 查询员工任岗经历
     * @param employeeId员工ID
     * @return
     */
    JSONArray getEmployeePositions(String employeeId,String employeeNo);
    /***
     * 根据员工ID查询员工对象
     * @param employeeId 员工ID
     * @param needTrans 员工对象链接字段是否需要转换成名称
     * @param containSet 是否需要查询出属性集
     * @return
     */
    JSONObject queryEmployeeById(String employeeId,Boolean needTrans,Boolean containSet);
    /***
     * 根据ID删除员工对象
     * @param employeeId 员工ID
     * @return
     */
    Result deleteEmployee(String employeeId);
    /***
     * 保存员工对象
     * @param employee 员工类
     * @return
     */
    Result saveEmployee(EmployeeEntity employee);
    
    /***
     * 根据部门ID查询待岗员工
     * @param employee 员工类
     * @return
     */
    JSONObject loadEmpEmpty(String deptId,int subDept,int rows,int startPage);
    
    /***
     * 根据部门ID查询空岗
     * @param employee 员工类
     * @return
     */
    JSONObject loadEmptyPost(String deptId,int subDept,int rows,int startPage);
    
    /**
     * 根据部门ID 查询部门员工
     * @param deptId 部门ID 
     * @param subFlag 是否查询下级部门
     * @param rows 每页记录数
     * @param startPage 当前页
     * @return
     */
    Pagination<EmployeeEntity> queryEmployeeByDeptId(String deptId,int subFlag,int rows,int startPage );
    /**
     * 根据部门ID查询部门空岗位
     * @param deptId
     * @return
     */
    List<Map<String, Object>> deptEmptyPost(String deptId,int containSub);
    
    /***
     * 根据员工Id查询部门岗位需要数据
     * @param empId
     * @return
     */
    JSONObject queryByDeptEmp(String empId);
    /***
     * 查询有岗有人情况
     * @param deptId
     * @param subDept
     * @param rows
     * @param startPage
     * @return
     */
    JSONObject queryEmpPost(String deptId,int subDept,int rows,int startPage);
    
    /***
     * 
     * @param cardNo
     * @return
     */
    int checkCardNoInEnte(String cardNo);
    
    /***
     * 
     * @param cardNo
     * @return
     */
    int checkEmail(String entryOrderId,String people,String email);
    
}
