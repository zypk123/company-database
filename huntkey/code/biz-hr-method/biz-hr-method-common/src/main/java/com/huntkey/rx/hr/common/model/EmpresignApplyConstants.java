package com.huntkey.rx.hr.common.model;

/**
 * 离职申请单类常量
 *
 * @author zhangyu
 * @create 2017-11-17 9:36
 **/
public interface EmpresignApplyConstants {

    /**
     * EDM类名： 离职申请单类
     */

    String EDM_EMPRESIGNAPPLY = "empresignapply";

    /**
     * EDM类名： 离职申请单类.离职明细类
     */
    String EDM_EMPRESIGNAPPLY_OERA_EMP_SET = "empresignapply.oera_emp_set";

    /**
     * EDM类名： 员工类
     */
    String EDM_EMPLOYEE = "employee";

    /**
     * EDM类名： 员工类-合同记录
     */
    String EDM_EMPLOYEE_REMP_CONT_SET = "employee.remp_cont_set";

    /**
     * EDM类名：员工类：任岗记录
     */
    String EDM_EMPLOYEE_REMP_POST_SET = "employee.remp_post_set";

    /**
     * EDM类名： 部门类
     */
    String EDM_DEPTTREE = "depttree";

    /**
     * EDM类名：岗位类
     */
    String EDM_JOBPOSITION = "jobposition";

    /**
     * 申请单号
     */
    String ORDE_NBR = "orde_nbr";

    /**
     * PID
     */
    String PID = "pid";

    /**
     * 离职人(员工类数据的id)
     */
    String OERA_EMP = "oera_emp";

    /**
     * 姓名
     */
    String REMP_NAME = "remp_name";

    /**
     * 工号
     */
    String REMP_NO = "remp_no";

    /**
     * 申请离职日期
     */
    String CRETIME = "cretime";

    /**
     * 预离职日期
     */
    String OERA_APP_DATE = "oera_app_date";

    /**
     * 批准离职日期
     */
    String OERA_APPR_DATE = "oera_appr_date";

    /**
     * 实际离职日期
     */
    String OERA_REAL_DATE = "oera_real_date";

    /**
     * 性别
     */
    String REMP_GENDER = "remp_gender";

    // FIXME 这里的出生日期是为了计算年龄
    /**
     * 出生日期
     */
    String REMP_BIRTH= "remp_birth";

    /**
     * 年龄
     */
    String REMP_AGE = "remp_age";

    /**
     * 岗级
     */
    String OERA_PGRADE = "oera_pgrade";

    // FIXME 这里首次工作时间是为了计算工龄(年)
    /**
     * 首次工作时间
     */
    String REMP_WORK_DATE = "remp_work_date";

    // FIXME 这里到职日是为了计算在职(月)
    /**
     * 到职日
     */
    String REMP_IN_DATE = "remp_in_date";

    // FIXME 这里离职日是为了计算在职(月)
    /**
     * 离职日
     */
    String REMP_LEA_DATE = "remp_lea_date";

    // TODO 在岗(月) 暂时不理解是什么意思

    /**
     * 部门审核意见
     */
    String OERA_AUDIT_DESC = "oera_audit_desc";

    /**
     * 离职申请数
     */
    String OERA_NUM_AP = "oera_num_ap";

    /**
     * 所属部门
     */
    String OERA_DEPT = "oera_dept";

    /**
     * 上级部门
     */
    String MDEP_PAR = "mdep_par";

    /**
     * 部门名称
     */
    String MDEP_NAME = "mdep_name";

    /**
     * 直属上级
     */
    String MDEP_LEADER = "mdep_leader";

    /**
     * 直属上级姓名
     */
    String MDEP_LEADER_NAME = "mdep_leader_name";

    /**
     * 岗位
     */
    String RPOS_NAME = "rpos_name";

    // TODO 直属上级暂时没添加

    /**
     * 离职类型
     */
    String OERA_TYPE = "oera_type";

    /**
     * 离职原因
     */
    String OERA_RESON = "oera_reson";

    /**
     * 备注
     */
    String OERA_REMARK = "oera_remark";

    /**
     * 岗级
     */
    String REMP_PGRADE = "remp_pgrade";

    /**
     * 工龄
     */
    String REMP_WORK_AGE = "remp_work_age";

    /**
     * 在职(月)
     */
    String REMP_IN_WORK = "remp_in_work";

    /**
     * 在岗(月)
     */
    String REMP_IN_JOB = "remp_in_job";

    /**
     * 审批说明/步骤职责
     */
    String PARAM_OPINION = "opinion";

    /**
     * 审批意见 pass，枚举
     */
    String PARAM_AUDITKEY = "auditKey";

    /**
     * 单据编辑状态：0不可以编辑，1可以编辑
     */
    String PARAM_FORMSTATE = "formState";

    /**
     * 流程实例ID
     */
    String PARAM_ACT_INSTANCE_ID = "actInstanceId";

    /**
     * 批准离职日期
     */
    String PARAM_OERA_APPR_DATE = "oeraApprDate";

    /**
     * 单据编号
     */
    String ORDERNBR = "orderNbr";






}
