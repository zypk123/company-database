package com.huntkey.rx.hr.common.model;

/**
 *
 * @author zhouyou
 * @date 2017/12/2
 */
public class StaffContractDTO {
    //员工id
    private String employeeId;

    //姓名和工号
    private String nameAndNumber;

    //性别
    private String gender;

    //年龄
    private String age;

    //工龄
    private String workAge;

    //司龄
    private String companyAge;

    //在岗（月）
    private String onGuardTime;

    //员工类型
    private String employeeType;

    //办公园区
    private String parkName;

    //合同法人
    private String legalPerson;

    //部门
    private String departmentName;

    //身份证号
    private String idCardNumber;

    //合同次数
    private String recordsNum;

    //合同年限
    private String recordTerm;

    //入职日期
    private String entryDate;

    //合同实际结束日期
    private String realEndTime;

    //合同状态
    private String recordState;

    //起始日期
    private String startDate;

    //结束日期
    private String endDate;

    //合同单据状态
    private String ordeStatus;

    //合同单据编号
    private String ordeNbr;


    @Override
    public String toString() {
        return "StaffContractDTO{" +
                "employeeId='" + employeeId + '\'' +
                ", nameAndNumber='" + nameAndNumber + '\'' +
                ", gender='" + gender + '\'' +
                ", age='" + age + '\'' +
                ", workAge='" + workAge + '\'' +
                ", companyAge='" + companyAge + '\'' +
                ", onGuardTime='" + onGuardTime + '\'' +
                ", employeeType='" + employeeType + '\'' +
                ", parkName='" + parkName + '\'' +
                ", legalPerson='" + legalPerson + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", idCardNumber='" + idCardNumber + '\'' +
                ", recordsNum='" + recordsNum + '\'' +
                ", recordTerm='" + recordTerm + '\'' +
                ", entryDate='" + entryDate + '\'' +
                ", realEndTime='" + realEndTime + '\'' +
                ", recordState='" + recordState + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", ordeStatus='" + ordeStatus + '\'' +
                ", ordeNbr='" + ordeNbr + '\'' +
                '}';
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getNameAndNumber() {
        return nameAndNumber;
    }

    public void setNameAndNumber(String nameAndNumber) {
        this.nameAndNumber = nameAndNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getWorkAge() {
        return workAge;
    }

    public void setWorkAge(String workAge) {
        this.workAge = workAge;
    }

    public String getCompanyAge() {
        return companyAge;
    }

    public void setCompanyAge(String companyAge) {
        this.companyAge = companyAge;
    }

    public String getOnGuardTime() {
        return onGuardTime;
    }

    public void setOnGuardTime(String onGuardTime) {
        this.onGuardTime = onGuardTime;
    }

    public String getEmployeeType() {
        return employeeType;
    }

    public void setEmployeeType(String employeeType) {
        this.employeeType = employeeType;
    }

    public String getParkName() {
        return parkName;
    }

    public void setParkName(String parkName) {
        this.parkName = parkName;
    }

    public String getLegalPerson() {
        return legalPerson;
    }

    public void setLegalPerson(String legalPerson) {
        this.legalPerson = legalPerson;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getIdCardNumber() {
        return idCardNumber;
    }

    public void setIdCardNumber(String idCardNumber) {
        this.idCardNumber = idCardNumber;
    }

    public String getRecordsNum() {
        return recordsNum;
    }

    public void setRecordsNum(String recordsNum) {
        this.recordsNum = recordsNum;
    }

    public String getRecordTerm() {
        return recordTerm;
    }

    public void setRecordTerm(String recordTerm) {
        this.recordTerm = recordTerm;
    }


    public String getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(String entryDate) {
        this.entryDate = entryDate;
    }

    public String getRecordState() {
        return recordState;
    }

    public void setRecordState(String recordState) {
        this.recordState = recordState;
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

    public String getRealEndTime() {
        return realEndTime;
    }

    public void setRealEndTime(String realEndTime) {
        this.realEndTime = realEndTime;
    }
    public String getOrdeStatus() {
        return ordeStatus;
    }

    public void setOrdeStatus(String ordeStatus) {
        this.ordeStatus = ordeStatus;
    }

    public String getOrdeNbr() {
        return ordeNbr;
    }

    public void setOrdeNbr(String ordeNbr) {
        this.ordeNbr = ordeNbr;
    }
    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
}
