package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 伙伴类实体
 * 
 */
public class RelationEntity extends ResourceEntity implements Serializable {
    private static final long serialVersionUID = 5790361708590535L;
    
    /**法人数据集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="rela_corp_set", className="RelaRelaCorpSetaEntity")
    private List<RelaRelaCorpSetaEntity> rela_corp_set;
    /**伙伴编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rela_code", className="")
    private String rela_code;
    /**伙伴名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rela_name", className="")
    private String rela_name;
    /**供应商数据集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="rela_supplier_set", className="RelaRelaSupplierSetaEntity")
    private List<RelaRelaSupplierSetaEntity> rela_supplier_set;
    /**客户数据集*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="rela_cust_set", className="RelaRelaCustSetaEntity")
    private List<RelaRelaCustSetaEntity> rela_cust_set;
    /**股东数据集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="rela_owner_set", className="RelaRelaOwnerSetaEntity")
    private List<RelaRelaOwnerSetaEntity> rela_owner_set;
    /**伙伴简称*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="rela_short_name", className="")
    private String rela_short_name;
    /**银行数据集*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="rela_bank_set", className="RelaRelaBankSetaEntity")
    private List<RelaRelaBankSetaEntity> rela_bank_set;

    public List<RelaRelaCorpSetaEntity> loadRela_corp_set() {
        String propertyName = "rela_corp_set";
        String rootClass = "";
        return (List<RelaRelaCorpSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRela_corp_set(List<RelaRelaCorpSetaEntity> rela_corp_set) {
        this.rela_corp_set = rela_corp_set;
    }

    public List<RelaRelaCorpSetaEntity> getRela_corp_set() {
        return rela_corp_set;
    }

    public String getRela_code() {
        return rela_code;
    }

    public void setRela_code(String rela_code) {
        this.rela_code = rela_code;
    }

    public String getRela_name() {
        return rela_name;
    }

    public void setRela_name(String rela_name) {
        this.rela_name = rela_name;
    }

    public List<RelaRelaSupplierSetaEntity> loadRela_supplier_set() {
        String propertyName = "rela_supplier_set";
        String rootClass = "";
        return (List<RelaRelaSupplierSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRela_supplier_set(List<RelaRelaSupplierSetaEntity> rela_supplier_set) {
        this.rela_supplier_set = rela_supplier_set;
    }

    public List<RelaRelaSupplierSetaEntity> getRela_supplier_set() {
        return rela_supplier_set;
    }

    public List<RelaRelaCustSetaEntity> loadRela_cust_set() {
        String propertyName = "rela_cust_set";
        String rootClass = "";
        return (List<RelaRelaCustSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRela_cust_set(List<RelaRelaCustSetaEntity> rela_cust_set) {
        this.rela_cust_set = rela_cust_set;
    }

    public List<RelaRelaCustSetaEntity> getRela_cust_set() {
        return rela_cust_set;
    }

    public List<RelaRelaOwnerSetaEntity> loadRela_owner_set() {
        String propertyName = "rela_owner_set";
        String rootClass = "";
        return (List<RelaRelaOwnerSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRela_owner_set(List<RelaRelaOwnerSetaEntity> rela_owner_set) {
        this.rela_owner_set = rela_owner_set;
    }

    public List<RelaRelaOwnerSetaEntity> getRela_owner_set() {
        return rela_owner_set;
    }

    public String getRela_short_name() {
        return rela_short_name;
    }

    public void setRela_short_name(String rela_short_name) {
        this.rela_short_name = rela_short_name;
    }

    public List<RelaRelaBankSetaEntity> loadRela_bank_set() {
        String propertyName = "rela_bank_set";
        String rootClass = "";
        return (List<RelaRelaBankSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRela_bank_set(List<RelaRelaBankSetaEntity> rela_bank_set) {
        this.rela_bank_set = rela_bank_set;
    }

    public List<RelaRelaBankSetaEntity> getRela_bank_set() {
        return rela_bank_set;
    }



}