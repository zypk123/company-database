package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 企业类实体
 * 
 */
public class EnterpriseEntity extends EcosystemEntity implements Serializable {
    private static final long serialVersionUID = 3668510010027472L;
    
    /**企业识别码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_idcode", className="")
    private String ente_idcode;
    /**组织机构代码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_org_code", className="")
    private String ente_org_code;
    /**企业全称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_name_cn", className="")
    private String ente_name_cn;
    /**企业全称(外文)*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_name_en", className="")
    private String ente_name_en;
    /**企业简称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_nickname", className="")
    private String ente_nickname;
    /**注册金额*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_regi_money", className="")
    private BigDecimal ente_regi_money;
    /**注册货币*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_regi_currency", className="")
    private String ente_regi_currency;
    /**企业性质*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_quality", className="")
    private String ente_quality;
    /**纳税归类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_ratepaying", className="")
    private String ente_ratepaying;
    /**注册时间起*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_reg_beg", className="")
    private Date ente_reg_beg;
    /**注册时间止*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_reg_end", className="")
    private Date ente_reg_end;
    /**注册有效期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_reg_deadline", className="")
    private BigDecimal ente_reg_deadline;
    /**工商注册号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_regi_num", className="")
    private String ente_regi_num;
    /**注册机构*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_regi_org", className="")
    private String ente_regi_org;
    /**注册类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_regi_type", className="")
    private String ente_regi_type;
    /**工商年审日*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_examdate", className="")
    private String ente_examdate;
    /**国税登记号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_ntax_num", className="")
    private String ente_ntax_num;
    /**国税纳税编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_ntax_code", className="")
    private String ente_ntax_code;
    /**地税登记号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_ltax_num", className="")
    private String ente_ltax_num;
    /**地税纳税编码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_ltax_code", className="")
    private String ente_ltax_code;
    /**机构登记号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_org_num", className="")
    private String ente_org_num;
    /**颁发单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_org_unit", className="")
    private String ente_org_unit;
    /**机构类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_org_type", className="")
    private String ente_org_type;
    /**机构年审日*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_org_examdate", className="")
    private String ente_org_examdate;
    /**所属行业*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_industry", className="")
    private String ente_industry;
    /**行业识别码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_idus_code", className="")
    private String ente_idus_code;
    /**经营范围*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_scope", className="")
    private String ente_scope;
    /**财年起始时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_fina_beg", className="")
    private String ente_fina_beg;
    /**网址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_url", className="")
    private String ente_url;
    /**通讯地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_addr", className="")
    private String ente_addr;
    /**地图定位地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_map_addr", className="")
    private String ente_map_addr;
    /**企业法人代表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_fict_peop", className="")
    private String ente_fict_peop;
    /**财务代表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_fina_peop", className="")
    private String ente_fina_peop;
    /**管理代表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_mang_peop", className="")
    private String ente_mang_peop;
    /**控股投资法人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_inve_ente", className="EnterpriseEntity")
    private String ente_inve_ente;
    /**自然人投资列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_inve_set", className="EnteEnteInveSetaEntity")
    private List<EnteEnteInveSetaEntity> ente_inve_set;
    /**企业法人投资列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_fina_set", className="EnteEnteFinaSetaEntity")
    private List<EnteEnteFinaSetaEntity> ente_fina_set;
    /**企业关系圈*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_rela_set", className="EnteEnteRelaSetaEntity")
    private List<EnteEnteRelaSetaEntity> ente_rela_set;
    /**行业的勾选*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_inds_set", className="EnteEnteIndsSetaEntity")
    private List<EnteEnteIndsSetaEntity> ente_inds_set;
    /**行业类的勾选*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_class_set", className="EnteEnteClassSetaEntity")
    private List<EnteEnteClassSetaEntity> ente_class_set;
    /**数据库地址*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ente_dbaddress", className="")
    private String ente_dbaddress;
    /**sceo网址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="ente_sceo_url", className="")
    private String ente_sceo_url;
    /**数据库用户*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ente_dbuser", className="")
    private String ente_dbuser;
    /**数据库密码*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="ente_dbpassword", className="")
    private String ente_dbpassword;

    public String getEnte_idcode() {
        return ente_idcode;
    }

    public void setEnte_idcode(String ente_idcode) {
        this.ente_idcode = ente_idcode;
    }

    public String getEnte_org_code() {
        return ente_org_code;
    }

    public void setEnte_org_code(String ente_org_code) {
        this.ente_org_code = ente_org_code;
    }

    public String getEnte_name_cn() {
        return ente_name_cn;
    }

    public void setEnte_name_cn(String ente_name_cn) {
        this.ente_name_cn = ente_name_cn;
    }

    public String getEnte_name_en() {
        return ente_name_en;
    }

    public void setEnte_name_en(String ente_name_en) {
        this.ente_name_en = ente_name_en;
    }

    public String getEnte_nickname() {
        return ente_nickname;
    }

    public void setEnte_nickname(String ente_nickname) {
        this.ente_nickname = ente_nickname;
    }

    public BigDecimal getEnte_regi_money() {
        return ente_regi_money;
    }

    public void setEnte_regi_money(BigDecimal ente_regi_money) {
        this.ente_regi_money = ente_regi_money;
    }

    public String getEnte_regi_currency() {
        return ente_regi_currency;
    }

    public void setEnte_regi_currency(String ente_regi_currency) {
        this.ente_regi_currency = ente_regi_currency;
    }

    public String getEnte_quality() {
        return ente_quality;
    }

    public void setEnte_quality(String ente_quality) {
        this.ente_quality = ente_quality;
    }

    public String getEnte_ratepaying() {
        return ente_ratepaying;
    }

    public void setEnte_ratepaying(String ente_ratepaying) {
        this.ente_ratepaying = ente_ratepaying;
    }

    public Date getEnte_reg_beg() {
        return ente_reg_beg;
    }

    public void setEnte_reg_beg(Date ente_reg_beg) {
        this.ente_reg_beg = ente_reg_beg;
    }

    public Date getEnte_reg_end() {
        return ente_reg_end;
    }

    public void setEnte_reg_end(Date ente_reg_end) {
        this.ente_reg_end = ente_reg_end;
    }

    public BigDecimal getEnte_reg_deadline() {
        return ente_reg_deadline;
    }

    public void setEnte_reg_deadline(BigDecimal ente_reg_deadline) {
        this.ente_reg_deadline = ente_reg_deadline;
    }

    public String getEnte_regi_num() {
        return ente_regi_num;
    }

    public void setEnte_regi_num(String ente_regi_num) {
        this.ente_regi_num = ente_regi_num;
    }

    public String getEnte_regi_org() {
        return ente_regi_org;
    }

    public void setEnte_regi_org(String ente_regi_org) {
        this.ente_regi_org = ente_regi_org;
    }

    public String getEnte_regi_type() {
        return ente_regi_type;
    }

    public void setEnte_regi_type(String ente_regi_type) {
        this.ente_regi_type = ente_regi_type;
    }

    public String getEnte_examdate() {
        return ente_examdate;
    }

    public void setEnte_examdate(String ente_examdate) {
        this.ente_examdate = ente_examdate;
    }

    public String getEnte_ntax_num() {
        return ente_ntax_num;
    }

    public void setEnte_ntax_num(String ente_ntax_num) {
        this.ente_ntax_num = ente_ntax_num;
    }

    public String getEnte_ntax_code() {
        return ente_ntax_code;
    }

    public void setEnte_ntax_code(String ente_ntax_code) {
        this.ente_ntax_code = ente_ntax_code;
    }

    public String getEnte_ltax_num() {
        return ente_ltax_num;
    }

    public void setEnte_ltax_num(String ente_ltax_num) {
        this.ente_ltax_num = ente_ltax_num;
    }

    public String getEnte_ltax_code() {
        return ente_ltax_code;
    }

    public void setEnte_ltax_code(String ente_ltax_code) {
        this.ente_ltax_code = ente_ltax_code;
    }

    public String getEnte_org_num() {
        return ente_org_num;
    }

    public void setEnte_org_num(String ente_org_num) {
        this.ente_org_num = ente_org_num;
    }

    public String getEnte_org_unit() {
        return ente_org_unit;
    }

    public void setEnte_org_unit(String ente_org_unit) {
        this.ente_org_unit = ente_org_unit;
    }

    public String getEnte_org_type() {
        return ente_org_type;
    }

    public void setEnte_org_type(String ente_org_type) {
        this.ente_org_type = ente_org_type;
    }

    public String getEnte_org_examdate() {
        return ente_org_examdate;
    }

    public void setEnte_org_examdate(String ente_org_examdate) {
        this.ente_org_examdate = ente_org_examdate;
    }

    public String getEnte_industry() {
        return ente_industry;
    }

    public void setEnte_industry(String ente_industry) {
        this.ente_industry = ente_industry;
    }

    public String getEnte_idus_code() {
        return ente_idus_code;
    }

    public void setEnte_idus_code(String ente_idus_code) {
        this.ente_idus_code = ente_idus_code;
    }

    public String getEnte_scope() {
        return ente_scope;
    }

    public void setEnte_scope(String ente_scope) {
        this.ente_scope = ente_scope;
    }

    public String getEnte_fina_beg() {
        return ente_fina_beg;
    }

    public void setEnte_fina_beg(String ente_fina_beg) {
        this.ente_fina_beg = ente_fina_beg;
    }

    public String getEnte_url() {
        return ente_url;
    }

    public void setEnte_url(String ente_url) {
        this.ente_url = ente_url;
    }

    public String getEnte_addr() {
        return ente_addr;
    }

    public void setEnte_addr(String ente_addr) {
        this.ente_addr = ente_addr;
    }

    public String getEnte_map_addr() {
        return ente_map_addr;
    }

    public void setEnte_map_addr(String ente_map_addr) {
        this.ente_map_addr = ente_map_addr;
    }

    public String getEnte_fict_peop() {
        return ente_fict_peop;
    }

    public void setEnte_fict_peop(String ente_fict_peop) {
        this.ente_fict_peop = ente_fict_peop;
    }

    public String getEnte_fina_peop() {
        return ente_fina_peop;
    }

    public void setEnte_fina_peop(String ente_fina_peop) {
        this.ente_fina_peop = ente_fina_peop;
    }

    public String getEnte_mang_peop() {
        return ente_mang_peop;
    }

    public void setEnte_mang_peop(String ente_mang_peop) {
        this.ente_mang_peop = ente_mang_peop;
    }

    public String getEnte_inve_ente() {
        return ente_inve_ente;
    }

    public void setEnte_inve_ente(String ente_inve_ente) {
        this.ente_inve_ente = ente_inve_ente;
    }

    public EnterpriseEntity loadEnte_inve_ente() {
        String propertyName = "ente_inve_ente";
        return (EnterpriseEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<EnteEnteInveSetaEntity> loadEnte_inve_set() {
        String propertyName = "ente_inve_set";
        String rootClass = "";
        return (List<EnteEnteInveSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEnte_inve_set(List<EnteEnteInveSetaEntity> ente_inve_set) {
        this.ente_inve_set = ente_inve_set;
    }

    public List<EnteEnteInveSetaEntity> getEnte_inve_set() {
        return ente_inve_set;
    }

    public List<EnteEnteFinaSetaEntity> loadEnte_fina_set() {
        String propertyName = "ente_fina_set";
        String rootClass = "";
        return (List<EnteEnteFinaSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEnte_fina_set(List<EnteEnteFinaSetaEntity> ente_fina_set) {
        this.ente_fina_set = ente_fina_set;
    }

    public List<EnteEnteFinaSetaEntity> getEnte_fina_set() {
        return ente_fina_set;
    }

    public List<EnteEnteRelaSetaEntity> loadEnte_rela_set() {
        String propertyName = "ente_rela_set";
        String rootClass = "";
        return (List<EnteEnteRelaSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEnte_rela_set(List<EnteEnteRelaSetaEntity> ente_rela_set) {
        this.ente_rela_set = ente_rela_set;
    }

    public List<EnteEnteRelaSetaEntity> getEnte_rela_set() {
        return ente_rela_set;
    }

    public List<EnteEnteIndsSetaEntity> loadEnte_inds_set() {
        String propertyName = "ente_inds_set";
        String rootClass = "";
        return (List<EnteEnteIndsSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEnte_inds_set(List<EnteEnteIndsSetaEntity> ente_inds_set) {
        this.ente_inds_set = ente_inds_set;
    }

    public List<EnteEnteIndsSetaEntity> getEnte_inds_set() {
        return ente_inds_set;
    }

    public List<EnteEnteClassSetaEntity> loadEnte_class_set() {
        String propertyName = "ente_class_set";
        String rootClass = "";
        return (List<EnteEnteClassSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEnte_class_set(List<EnteEnteClassSetaEntity> ente_class_set) {
        this.ente_class_set = ente_class_set;
    }

    public List<EnteEnteClassSetaEntity> getEnte_class_set() {
        return ente_class_set;
    }

    public String getEnte_dbaddress() {
        return ente_dbaddress;
    }

    public void setEnte_dbaddress(String ente_dbaddress) {
        this.ente_dbaddress = ente_dbaddress;
    }

    public String getEnte_sceo_url() {
        return ente_sceo_url;
    }

    public void setEnte_sceo_url(String ente_sceo_url) {
        this.ente_sceo_url = ente_sceo_url;
    }

    public String getEnte_dbuser() {
        return ente_dbuser;
    }

    public void setEnte_dbuser(String ente_dbuser) {
        this.ente_dbuser = ente_dbuser;
    }

    public String getEnte_dbpassword() {
        return ente_dbpassword;
    }

    public void setEnte_dbpassword(String ente_dbpassword) {
        this.ente_dbpassword = ente_dbpassword;
    }



}