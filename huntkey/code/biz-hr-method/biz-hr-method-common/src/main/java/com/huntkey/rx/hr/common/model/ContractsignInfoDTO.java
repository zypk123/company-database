package com.huntkey.rx.hr.common.model;

import java.util.List;

/**
 *
 * @author zhouyou
 * @date 2017/12/5
 */
public class ContractsignInfoDTO {
    //单据定义对象ID
    private String ordeObj;

    //申请员工id
    private String employeeId;

    //合同签订单id
    private String id;

    //合同签订单单号
    private String orderNumber;

    //申请人
    private String applicant;

    //申请部门id
    private String applicantOfDepartmentId;

    //申请部门
    private String applicantOfDepartment;

    //申请岗位
    private String applicantOfPost;


    //申请时间
    private String applicantDate;

    //签约部门
    private String signDepartment;

    //签约法人
    private String legalPerson;

    //签约日期
    private String signDate;

    //签约类型
    private String signType;

    //合同起始日期
    private String startDate;

    //合同结束日期
    private String endDate;

    //所属类
    private String edmdClass;

    //签约明细
    private List<StaffContractDTO> details;

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getApplicant() {
        return applicant;
    }

    public void setApplicant(String applicant) {
        this.applicant = applicant;
    }
    public String getApplicantOfDepartmentId() {
        return applicantOfDepartmentId;
    }

    public void setApplicantOfDepartmentId(String applicantOfDepartmentId) {
        this.applicantOfDepartmentId = applicantOfDepartmentId;
    }

    public String getApplicantOfDepartment() {
        return applicantOfDepartment;
    }

    public void setApplicantOfDepartment(String applicantOfDepartment) {
        this.applicantOfDepartment = applicantOfDepartment;
    }

    public String getApplicantDate() {
        return applicantDate;
    }

    public void setApplicantDate(String applicantDate) {
        this.applicantDate = applicantDate;
    }

    public String getSignDepartment() {
        return signDepartment;
    }

    public void setSignDepartment(String signDepartment) {
        this.signDepartment = signDepartment;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getSignDate() {
        return signDate;
    }

    public void setSignDate(String signDate) {
        this.signDate = signDate;
    }

    public String getSignType() {
        return signType;
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public List<StaffContractDTO> getDetails() {
        return details;
    }

    public void setDetails(List<StaffContractDTO> details) {
        this.details = details;
    }

    public String getOrdeObj() {
        return ordeObj;
    }

    public void setOrdeObj(String ordeObj) {
        this.ordeObj = ordeObj;
    }
    public String getApplicantOfPost() {
        return applicantOfPost;
    }

    public void setApplicantOfPost(String applicantOfPost) {
        this.applicantOfPost = applicantOfPost;
    }

    public String getEdmdClass() {
        return edmdClass;
    }

    public void setEdmdClass(String edmdClass) {
        this.edmdClass = edmdClass;
    }

    @Override
    public String toString() {
        return "ContractsignInfoDTO{" +
                "ordeObj='" + ordeObj + '\'' +
                ", employeeId='" + employeeId + '\'' +
                ", id='" + id + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", applicant='" + applicant + '\'' +
                ", applicantOfDepartmentId='" + applicantOfDepartmentId + '\'' +
                ", applicantOfDepartment='" + applicantOfDepartment + '\'' +
                ", applicantOfPost='" + applicantOfPost + '\'' +
                ", applicantDate='" + applicantDate + '\'' +
                ", signDepartment='" + signDepartment + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", signDate='" + signDate + '\'' +
                ", signType='" + signType + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", edmdClass='" + edmdClass + '\'' +
                ", details=" + details +
                '}';
    }
}
