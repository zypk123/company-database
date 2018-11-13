package com.huntkey.rx.hr.common.model;

/**
 * Created by zhouyou on 2017/11/18.
 */
public interface ContractSignApplyConstants {
    /**
     * ID
     */
    String ID = "id";
    /**
     * PID
     */
    String PID = "pid";
    /**
     * EDM类名： 合同签订单
     */
    String EDM_CTR_SIGN_APPLY_ORDER = "contractsignapply";

    /**
     * 合同签订单属性：部门
     */
    String OCSO_DEPT = "ocso_dept";
    /**
     * 合同签订单属性：签约法人
     */
    String OCSO_MCOP = "ocso_mcop";
    /**
     * 合同签订单属性：签约类型
     */
    String OCSO_TYPE = "ocso_type";
    /**
     * 合同签订单属性：签订日期
     */
    String OCSO_SIGN_DATE = "ocso_sign_date";


    //TODO 签约人员属性集
    /**
     * EDM类名：合同签订单.签约人员
     */
    String EDM_OCSO_EMP_SET = "contractsignapply.ocso_emp_set";


    /**
     * 签约人员属性：员工部门
     */
    String OCSO_EMP_DEPT = "ocso_emp_dept";

    /**
     * 签约人员属性：员工
     */
    String OCSO_EMP = "ocso_emp";

    /**
     * 签约人员属性：合同开始日期
     */
    String OCSO_CONT_BEG = "ocso_cont_beg";

    /**
     * 签约人员属性：合同结束日期
     */
    String OCSO_CONT_END = "ocso_cont_end";

    //TODO 员工类
    /**
     * EDM类名： 员工类
     */
    String EDM_EMPLOYEE = "employee";

    String REMP_ID = "id";
    /**
     * 员工类属性：工号
     */
    String REMP_NO = "remp_no";
    /**
     * 员工类属性：姓名
     */
    String REMP_NAME = "remp_name";
    /**
     * 员工类属性：状态
     */
    String REMP_STATUS = "remp_status";
    /**
     * 员工类属性：部门
     */
    String REMP_DEPT = "remp_dept";
    /**
     * 员工类属性：岗位
     */
    String REMP_POST = "remp_post";

    /**
     * 员工类：转正日期
     */
    String REMP_PSOT_DATE = "remp_post_date";

    /**
     * 员工类：试用结果
     */
    String REMP_TRY_RESULT = "remp_try_result";

    /**
     * 员工类：岗级
     */
    String REMP_PGRADE = "remp_pgrade";

    /**
     * 员工类：员工到职日
     */
    String REMP_IN_DATE = "remp_in_date";

    /**
     * 员工类：性别
     */
    String REMP_GENDER = "remp_gender";


    /**
     *员工类：员工类型
     */

    String REMP_TYPE = "remp_type";

    /**
     * 员工类：身份证号
     */
    String REMP_CARD_ID = "remp_card_id";


    /**
     * 员工类：出生日期
     */
    String REMP_BITRH = "remp_bitrh";

    /**
     * 员工类：员工岗位调整集
     */
    String REMP_POST_SET = "remp_post_set";


    String REMP_REMP_POST_SET = "employee.remp_post_set";

    String REMP_REMP_CONT_SET = "employee.remp_cont_set";


    /**
     * 任岗经历：岗位
     */
    String REMP_POST_HIS = "remp_post_his";

    /**
     * 任岗经历：岗级
     */
    String REMP_PGRAD_HIS = "remp_pgrad_his";


    /**
     * 任岗经历：汇报上级
     */
    String REMP_PEMP_HIS = "remp_pemp_his";

    /**
     * 任岗经历：生效日期
     */
    String REMP_POST_BEG = "remp_post_beg";

    /**
     * 任岗经历：失效日期
     */
    String REMP_POST_END = "remp_post_end";

    /**
     * 员工类：法人公司
     */
    String REMP_MCOP = "remp_mcop";

    /**
     * 员工类：教育背景
     */
    String REMP_STUDY_SET = "employee.remp_study_set";

    /**
     * 教育背景：学历
     */
    String REMP_DEGREE = "remp_degree";

    /**
     * EDM类：学校类
     */
    String EDM_SCHOOL = "SCHOOL";
    /**
     * 学校类：学校名称
     */
    String RSCH_NAME = "rsch_name";

    /**
     * 教育背景：学校
     */
    String REMP_RSCH = "remp_rsch";

    /**
     * 专业
     */
    String REMP_MAJOR = "remp_major";

    /**
     * 教育背景：结束时间
     */
    String REMP_STU_END = "remp_stu_end";

    /**
     * 员工类：首次工作日期
     */
    String REMP_WORK_DATE = "remp_work_date";

    /**
     * 离职日
     */
    String REMP_LEA_DATE = "remp_lea_date";

    //TODO 部门表
    /**
     *  EDM类名： 部门
     */
    String EDM_DEPTTREE = "depttree";
    /**
     * 部门类属性：编码
     */
    String MDEP_CODE = "mdep_code";
    /**
     * 部门类属性：名称
     */
    String MDEP_NAME = "mdep_name";

    /**
     * 上级部门
     */
    String MDEP_PAR = "mdep_par";

    /**
     * 层级编码
     */
    String MDEP_LVL_CODE = "mdep_lvl_code";

    /**
     * 部门类属性：简称
     */
    String MDEP_SNAME ="mdep_sname";

    /**
     * 部门全称
     */
    String MDEP_LNAME = "mdep_lname";

    /**
     * 部门类属性：法人公司
     */
    String MDEP_MCOP = "mdep_mcop";

    /**
     * 部门类属性：部门级别
     */
    String MDEP_GRADE = "mdep_grade";

    /**
     * 排序
     */
    String MDEP_SEQ = "mdep_seq";

    /**
     * 部门类属性：办公园区
     */
    String MDEP_RPAK = "mdep_rpak";

    /**
     * 部门类属性：部门职责
     */
    String MDEP_DUTY = "mdep_duty";

    /**
     * 部门类属性：部门编制
     */
    String MDEP_TL_NUM = "mdep_tl_num";

    /**
     * 部门类属性：下层编制
     */
    String MDEP_LL_NUM = "mdep_ll_num";

    /**
     * 部门类属性：主责岗位
     */
    String MDEP_LEADER_POST = "mdep_leader_post";

    /**
     * 部门类属性：负责人
     */
    String MDEP_LEADER = "mdep_leader";

    /**
     * 职员人数
     */
    String MDEP_NUM_TOT = "mdep_num_tot";

    /**
     * 生效时间
     */
    String MDEP_BEG = "mdep_beg";

    /**
     * 失效时间
     */
    String MDEP_END = "mdep_end";

    /**
     * 逻辑删除
     */
    String IS_DEL = "is_del";

    /**
     * 变更集合
     */
    String MDEP_CHAG_SET = "mdep_chag_set";

    /**
     * 部门类id
     */
    String MDEP_ID = "id";




}
