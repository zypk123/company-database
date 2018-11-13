package com.huntkey.rx.sceo.formula.provider.audit.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.AuditRoles;
import com.huntkey.rx.sceo.formula.common.model.vo.Dept;
import com.huntkey.rx.sceo.formula.common.model.vo.DeptTree;
import com.huntkey.rx.sceo.formula.common.model.vo.Jobposition;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author zhouyou on 2017/10/28.
 */
public interface AuditRolesSetService {
    /**
     * 方式列表
     *
     * @param classId
     * @return
     */
    List getmode(String classId);

    /**
     * 方式汇报岗位集合
     *
     * @return
     */
    List getRootReportPosition();

    /**
     * 根据传入条件操作符以及内容 获取到所属部门id
     *
     * @param jobGrad   岗位级别
     * @param operator1 操作符1
     * @param value1    值1
     * @param deptGrad  部门级别
     * @param operator2 操作符2
     * @param value2    值2
     * @return 通过岗位级别条件 获取到对应的所属部门
     */
    List getRposdept(String jobGrad, String operator1, String value1,
                     String deptGrad, String operator2, String value2);

    /**
     * 部门树
     *
     * @return
     */
    List<DeptTree> getOnlyDeptTree();

    /**
     * 返回查询出部门级别满足一定条件的 部门树结构
     *
     * @param pGrade
     * @param poperater
     * @param dGrade
     * @param doperater
     * @return
     */
    List<DeptTree> getDeptTree(String pGrade, String poperater, String dGrade, String doperater);

    /**
     * 获得部门所有人
     *
     * @param deptId
     * @return
     */
    List getDeptAllPeople(String deptId);

    /**
     * 获得部门主管人
     *
     * @param deptId
     * @return
     */
    String getDeptMatter(String deptId);

    /**
     * getCurrentDeptUnderJobposition
     *
     * @param str
     * @return
     */
    List<String> getCurrentDeptUnderJobposition(String str);

    /**
     * getCurrentDeptUnderJobposition2
     *
     * @param deptID
     * @param allorOne
     * @return
     */
    Set<String> getCurrentDeptUnderJobposition2(String deptID, String allorOne, String pGrade, String poperater, String dGrade, String doperater);

    /**
     * 根据岗位id判断所属部门是否有效
     *
     * @return
     */
    Boolean isEffectivePost(String postId);

    /**
     * 根据部门id判断是否为有效部门
     *
     * @param deptId
     * @return
     */
    Boolean isEffectiveDept(String deptId);


    /**
     * 判断岗位任职人是否为空
     * @param id
     * @return
     */
    Boolean rposEmpIsNull(String id);
    /**
     * 获得部门主管人 上级主管人  上上级主管人
     *
     * @param id
     * @param level
     * @param pGrade
     * @param poperater
     * @param dGrade
     * @param doperater
     * @return
     */
    Result getSuper(String id, String level, String pGrade, String poperater, String dGrade, String doperater);

    /**
     * 判断部门是否符合等级
     * @param id
     * @param dGrade
     * @param doperater
     * @return
     */
    Boolean inTheLevel(String id,String dGrade, String doperater);

    /**
     * 根据部门id获取有效的主责人岗位
     *
     * @param department
     * @param level
     * @param pGrade
     * @param doperater
     * @param poperater
     * @param dGrade
     * @return
     */
    List getDepartmentSuperior(String department, String level, String pGrade, String doperater, String poperater, String dGrade);

    /**
     * 跟据相应部门id从负责人集合获取有效负责人岗位
     *
     * @param id
     * @param poperater
     * @param doperater
     * @param dGrade
     * @param pGrade
     * @return
     */
    List<Object> getDirectorSet(String id, String poperater, String doperater, String dGrade, String pGrade);

    /**
     * 根据部门id返回部门层级
     *
     * @param id
     * @return
     */
    String office(String id);

    /**
     * 根据岗位id获取有效的某相应等级岗位
     *
     * @param postId
     * @param level
     * @param pGrade
     * @param poperater
     * @param dGrade
     * @param doperater
     * @return
     */
    Result getPostSuperior(String postId, String level, String pGrade, String poperater, String dGrade, String doperater);



    //审核角色表操作//

    /**
     * 保存审核角色信息
     * 单条记录保存
     *
     * @param auditRoles
     * @return
     */
    int saveAuditRoles(AuditRoles auditRoles);


    /**
     * 更新审核角色信息
     *
     * @param auditRoles
     * @return
     */

    int updateAuditRoles(AuditRoles auditRoles);

    /**
     * 通过审核角色ID查找审核角色属性
     *
     * @param auditId
     * @return
     */
    AuditRoles selectByPrimaryKey(String auditId);


    /**
     * 通过审核角色ID查找审核角色属性
     *
     * @param classId
     * @return
     */
    List<AuditRoles> selectByClassId(String classId);

    /**
     * 删除审核角色设置信息（标记字段不可用）
     *
     * @param auditId
     * @return
     * @throws Exception
     */
    int delAuditRolesInfo(String auditId);

    /**
     * 根据类名， 表单id， 属性编码code
     *
     * @param classId
     * @param formId
     * @param chooseCode
     * @return 部门或者岗位对应的id  (岗位和部门类型都是对象连接 所以查出来是一个uuid)
     */
    List<String> getChooseId(String classId, String superClassId, String formId, String chooseCode);


    /**
     * 判断岗位是否有效
     *
     * @param postId 岗位id
     * @return 返回岗位id
     */
    String getEffectivePost(String postId);

    /**
     * 判断部门是否有效
     *
     * @param deptId 岗位id
     * @return 返回部门id
     */
    String getEffectiveDept(String deptId);


}
