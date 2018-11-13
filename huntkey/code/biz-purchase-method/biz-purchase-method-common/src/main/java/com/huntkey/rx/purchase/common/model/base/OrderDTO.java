package com.huntkey.rx.purchase.common.model.base;

import com.alibaba.fastjson.annotation.JSONField;
import com.huntkey.rx.purchase.common.constants.OrderConstants;
import com.huntkey.rx.purchase.common.model.base.EdmDTO;
import org.hibernate.validator.constraints.NotBlank;

import java.util.Date;

/**
 * Created by xuyf on 2017/11/17 0017.
 */
public class OrderDTO extends EdmDTO {

    @JSONField(name = OrderConstants.ORDE_ADDUSER)
    private String ordeAdduser;

    /**
     * 制单人名称
     */
    private String ordeAdduserName;

    /**
     * 制单人工号
     */
    private String ordeAdduserNo;
    
    @JSONField(name = OrderConstants.ORDE_DATE)
    private Date ordeDate;

    @JSONField(name = OrderConstants.ORDE_DUTY)
    private String ordeDuty;

    /**
     * 制单岗位名称
     */
    private String ordeDutyName;

    /**
     * 制单岗位编码
     */
    private String ordeDutyCode;

    @JSONField(name = OrderConstants.ORDE_PROXYUSER_POS)
    private String ordeProxyuserPos;

    @JSONField(name = OrderConstants.ORDE_EFFDATE)
    private Date ordeEffdate;

    @JSONField(name = OrderConstants.ORDE_EMP)
    private String ordeEmp;

    @JSONField(name = OrderConstants.ORDE_FORM_OBJ)
    private String ordeFormObj;

    @JSONField(name = OrderConstants.ORDE_LAST_VERSION_OBJ)
    private String ordeLastVersionObj;

    @JSONField(name = OrderConstants.ORDE_NBR)
    private String ordeNbr;

    @JSONField(name = OrderConstants.ORDE_PROCOBJ)
    private String ordeProcobj;

    @JSONField(name = OrderConstants.ORDE_PROXYUSER)
    private String ordeProxyuser;

    @JSONField(name =OrderConstants.ORDE_RODE_OBJ)
    private String ordeRodeObj;

    @JSONField(name = OrderConstants.ORDE_STATUS)
    private String ordeStatus;

    @JSONField(name = OrderConstants.ORDE_UPDATE_POS)
    private String ordeUpdatePos;

    @JSONField(name = OrderConstants.ORDE_VERSION)
    private String ordeVersion;

    @JSONField(name = OrderConstants.ORDE_DEPT)
    private String ordeDept;

    /**
     * 制单部门名称
     */
    private String ordeDeptName;

    /**
     * 制单部门编码
     */
    private String ordeDeptCode;


    @JSONField(name = OrderConstants.ORDE_ADDUSER)
    public String getOrdeAdduser() {
        return ordeAdduser;
    }
    @JSONField(name = OrderConstants.ORDE_ADDUSER)
    public void setOrdeAdduser(String ordeAdduser) {
        this.ordeAdduser = ordeAdduser;
    }

    @JSONField(name =OrderConstants.ORDE_DATE)
    public Date getOrdeDate() {
        return ordeDate;
    }
    @JSONField(name =OrderConstants.ORDE_DATE)
    public void setOrdeDate(Date ordeDate) {
        this.ordeDate = ordeDate;
    }

    @JSONField(name = OrderConstants.ORDE_DUTY)
    public String getOrdeDuty() {
        return ordeDuty;
    }
    @JSONField(name = OrderConstants.ORDE_DUTY)
    public void setOrdeDuty(String ordeDuty) {
        this.ordeDuty = ordeDuty;
    }

    @JSONField(name = OrderConstants.ORDE_PROXYUSER_POS)
    public String getOrdeProxyuserPos() {
        return ordeProxyuserPos;
    }
    @JSONField(name = OrderConstants.ORDE_PROXYUSER_POS)
    public void setOrdeProxyuserPos(String ordeProxyuserPos) {
        this.ordeProxyuserPos = ordeProxyuserPos;
    }

    @JSONField(name = OrderConstants.ORDE_EFFDATE)
    public Date getOrdeEffdate() {
        return ordeEffdate;
    }
    @JSONField(name = OrderConstants.ORDE_EFFDATE)
    public void setOrdeEffdate(Date ordeEffdate) {
        this.ordeEffdate = ordeEffdate;
    }

    @JSONField(name = OrderConstants.ORDE_EMP)
    public String getOrdeEmp() {
        return ordeEmp;
    }
    @JSONField(name = OrderConstants.ORDE_EMP)
    public void setOrdeEmp(String ordeEmp) {
        this.ordeEmp = ordeEmp;
    }

    @JSONField(name = OrderConstants.ORDE_FORM_OBJ)
    public String getOrdeFormObj() {
        return ordeFormObj;
    }
    @JSONField(name = OrderConstants.ORDE_FORM_OBJ)
    public void setOrdeFormObj(String ordeFormObj) {
        this.ordeFormObj = ordeFormObj;
    }

    @JSONField(name = OrderConstants.ORDE_LAST_VERSION_OBJ)
    public String getOrdeLastVersionObj() {
        return ordeLastVersionObj;
    }
    @JSONField(name = OrderConstants.ORDE_LAST_VERSION_OBJ)
    public void setOrdeLastVersionObj(String ordeLastVersionObj) {
        this.ordeLastVersionObj = ordeLastVersionObj;
    }

    @JSONField(name = OrderConstants.ORDE_NBR)
    public String getOrdeNbr() {
        return ordeNbr;
    }
    @JSONField(name = OrderConstants.ORDE_NBR)
    public void setOrdeNbr(String ordeNbr) {
        this.ordeNbr = ordeNbr;
    }

    @JSONField(name = OrderConstants.ORDE_PROCOBJ)
    public String getOrdeProcobj() {
        return ordeProcobj;
    }
    @JSONField(name = OrderConstants.ORDE_PROCOBJ)
    public void setOrdeProcobj(String ordeProcobj) {
        this.ordeProcobj = ordeProcobj;
    }

    @JSONField(name = OrderConstants.ORDE_PROXYUSER)
    public String getOrdeProxyuser() {
        return ordeProxyuser;
    }
    @JSONField(name = OrderConstants.ORDE_PROXYUSER)
    public void setOrdeProxyuser(String ordeProxyuser) {
        this.ordeProxyuser = ordeProxyuser;
    }

    @JSONField(name =OrderConstants.ORDE_RODE_OBJ)
    public String getOrdeRodeObj() {
        return ordeRodeObj;
    }
    @JSONField(name =OrderConstants.ORDE_RODE_OBJ)
    public void setOrdeRodeObj(String ordeRodeObj) {
        this.ordeRodeObj = ordeRodeObj;
    }

    @JSONField(name = OrderConstants.ORDE_STATUS)
    public String getOrdeStatus() {
        return ordeStatus;
    }
    @JSONField(name = OrderConstants.ORDE_STATUS)
    public void setOrdeStatus(String ordeStatus) {
        this.ordeStatus = ordeStatus;
    }

    @JSONField(name = OrderConstants.ORDE_UPDATE_POS)
    public String getOrdeUpdatePos() {
        return ordeUpdatePos;
    }
    @JSONField(name = OrderConstants.ORDE_UPDATE_POS)
    public void setOrdeUpdatePos(String ordeUpdatePos) {
        this.ordeUpdatePos = ordeUpdatePos;
    }

    @JSONField(name = OrderConstants.ORDE_VERSION)
    public String getOrdeVersion() {
        return ordeVersion;
    }
    @JSONField(name = OrderConstants.ORDE_VERSION)
    public void setOrdeVersion(String ordeVersion) {
        this.ordeVersion = ordeVersion;
    }

    @JSONField(name = OrderConstants.ORDE_DEPT)
    public String getOrdeDept() {
        return ordeDept;
    }
    @JSONField(name = OrderConstants.ORDE_DEPT)
    public void setOrdeDept(String ordeDept) {
        this.ordeDept = ordeDept;
    }

    public String getOrdeAdduserName() {
        return ordeAdduserName;
    }

    public void setOrdeAdduserName(String ordeAdduserName) {
        this.ordeAdduserName = ordeAdduserName;
    }

    public String getOrdeDutyName() {
        return ordeDutyName;
    }

    public void setOrdeDutyName(String ordeDutyName) {
        this.ordeDutyName = ordeDutyName;
    }

    public String getOrdeDeptName() {
        return ordeDeptName;
    }

    public void setOrdeDeptName(String ordeDeptName) {
        this.ordeDeptName = ordeDeptName;
    }

    public String getOrdeAdduserNo() {
        return ordeAdduserNo;
    }

    public void setOrdeAdduserNo(String ordeAdduserNo) {
        this.ordeAdduserNo = ordeAdduserNo;
    }

    public String getOrdeDutyCode() {
        return ordeDutyCode;
    }

    public void setOrdeDutyCode(String ordeDutyCode) {
        this.ordeDutyCode = ordeDutyCode;
    }

    public String getOrdeDeptCode() {
        return ordeDeptCode;
    }

    public void setOrdeDeptCode(String ordeDeptCode) {
        this.ordeDeptCode = ordeDeptCode;
    }
}
