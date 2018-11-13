package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 固定资产类实体
 * 
 */
public class FixedassetEntity extends GoodsEntity implements Serializable {
    private static final long serialVersionUID = 6705731301020661L;
    
    /**购置合同号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_contract_no", className="")
    private String fixa_contract_no;
    /**供应商*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_supp", className="")
    private String fixa_supp;
    /**制造商*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_mfrs", className="")
    private String fixa_mfrs;
    /**产权单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_org_ente", className="EnterpriseEntity")
    private String fixa_org_ente;
    /**物料编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_part_no", className="")
    private String fixa_part_no;
    /**验收报告单号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_check_no", className="")
    private String fixa_check_no;
    /**采购员工号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_buyer_no", className="")
    private String fixa_buyer_no;
    /**资产名称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_asset_name", className="")
    private String fixa_asset_name;
    /**规格型号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_model", className="")
    private String fixa_model;
    /**铭牌编号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_nameplate_no", className="")
    private String fixa_nameplate_no;
    /**单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_unit", className="MeasureunitEntity")
    private String fixa_unit;
    /**分类*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_class", className="")
    private String fixa_class;
    /**数量*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_qty", className="")
    private Integer fixa_qty;
    /**购置日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_purc_date", className="")
    private Date fixa_purc_date;
    /**购置方式*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_purc_mode", className="WordlistEntity")
    private String fixa_purc_mode;
    /**海关监管期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_ciq_monidate", className="")
    private Integer fixa_ciq_monidate;
    /**保修单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_org_supp", className="")
    private String fixa_org_supp;
    /**基本信息备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_info_remark", className="")
    private String fixa_info_remark;
    /**保修期(月)*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_fixdate_month", className="")
    private Integer fixa_fixdate_month;
    /**购进原值*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_purc_value", className="")
    private BigDecimal fixa_purc_value;
    /**折旧起始日*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_depr_date", className="")
    private Date fixa_depr_date;
    /**折旧月数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_depr_months", className="")
    private Integer fixa_depr_months;
    /**残值率*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_depr_rate", className="")
    private BigDecimal fixa_depr_rate;
    /**税额*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_tax", className="")
    private BigDecimal fixa_tax;
    /**折旧方法*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_depr_way", className="WordlistEntity")
    private String fixa_depr_way;
    /**折旧部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_depr_dept", className="")
    private String fixa_depr_dept;
    /**月计费额*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_month_fee", className="")
    private BigDecimal fixa_month_fee;
    /**使用部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_use_dept", className="")
    private String fixa_use_dept;
    /**存放地点*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_stog", className="")
    private String fixa_stog;
    /**责任人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_leader", className="")
    private String fixa_leader;
    /**使用状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_use_status", className="WordlistEntity")
    private String fixa_use_status;
    /**使用起始日*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_use_date", className="")
    private Date fixa_use_date;
    /**备注*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_remark", className="")
    private String fixa_remark;
    /**管理部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_mana_dept", className="")
    private String fixa_mana_dept;
    /**资产照片路径*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_pict_path", className="")
    private String fixa_pict_path;
    /**项目*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_item", className="")
    private String fixa_item;
    /**归属类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_vest_type", className="WordlistEntity")
    private String fixa_vest_type;
    /**归属单位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_vest_unit", className="")
    private String fixa_vest_unit;
    /**固定资产维护列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_mate_set", className="FixaFixaMateSetaEntity")
    private List<FixaFixaMateSetaEntity> fixa_mate_set;
    /**固定资产序列号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="fixa_sequ_no", className="")
    private String fixa_sequ_no;

    public String getFixa_contract_no() {
        return fixa_contract_no;
    }

    public void setFixa_contract_no(String fixa_contract_no) {
        this.fixa_contract_no = fixa_contract_no;
    }

    public String getFixa_supp() {
        return fixa_supp;
    }

    public void setFixa_supp(String fixa_supp) {
        this.fixa_supp = fixa_supp;
    }

    public String getFixa_mfrs() {
        return fixa_mfrs;
    }

    public void setFixa_mfrs(String fixa_mfrs) {
        this.fixa_mfrs = fixa_mfrs;
    }

    public String getFixa_org_ente() {
        return fixa_org_ente;
    }

    public void setFixa_org_ente(String fixa_org_ente) {
        this.fixa_org_ente = fixa_org_ente;
    }

    public EnterpriseEntity loadFixa_org_ente() {
        String propertyName = "fixa_org_ente";
        return (EnterpriseEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getFixa_part_no() {
        return fixa_part_no;
    }

    public void setFixa_part_no(String fixa_part_no) {
        this.fixa_part_no = fixa_part_no;
    }

    public String getFixa_check_no() {
        return fixa_check_no;
    }

    public void setFixa_check_no(String fixa_check_no) {
        this.fixa_check_no = fixa_check_no;
    }

    public String getFixa_buyer_no() {
        return fixa_buyer_no;
    }

    public void setFixa_buyer_no(String fixa_buyer_no) {
        this.fixa_buyer_no = fixa_buyer_no;
    }

    public String getFixa_asset_name() {
        return fixa_asset_name;
    }

    public void setFixa_asset_name(String fixa_asset_name) {
        this.fixa_asset_name = fixa_asset_name;
    }

    public String getFixa_model() {
        return fixa_model;
    }

    public void setFixa_model(String fixa_model) {
        this.fixa_model = fixa_model;
    }

    public String getFixa_nameplate_no() {
        return fixa_nameplate_no;
    }

    public void setFixa_nameplate_no(String fixa_nameplate_no) {
        this.fixa_nameplate_no = fixa_nameplate_no;
    }

    public String getFixa_unit() {
        return fixa_unit;
    }

    public void setFixa_unit(String fixa_unit) {
        this.fixa_unit = fixa_unit;
    }

    public MeasureunitEntity loadFixa_unit() {
        String propertyName = "fixa_unit";
        return (MeasureunitEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getFixa_class() {
        return fixa_class;
    }

    public void setFixa_class(String fixa_class) {
        this.fixa_class = fixa_class;
    }

    public Integer getFixa_qty() {
        return fixa_qty;
    }

    public void setFixa_qty(Integer fixa_qty) {
        this.fixa_qty = fixa_qty;
    }

    public Date getFixa_purc_date() {
        return fixa_purc_date;
    }

    public void setFixa_purc_date(Date fixa_purc_date) {
        this.fixa_purc_date = fixa_purc_date;
    }

    public String getFixa_purc_mode() {
        return fixa_purc_mode;
    }

    public void setFixa_purc_mode(String fixa_purc_mode) {
        this.fixa_purc_mode = fixa_purc_mode;
    }

    public WordlistEntity loadFixa_purc_mode() {
        String propertyName = "fixa_purc_mode";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getFixa_ciq_monidate() {
        return fixa_ciq_monidate;
    }

    public void setFixa_ciq_monidate(Integer fixa_ciq_monidate) {
        this.fixa_ciq_monidate = fixa_ciq_monidate;
    }

    public String getFixa_org_supp() {
        return fixa_org_supp;
    }

    public void setFixa_org_supp(String fixa_org_supp) {
        this.fixa_org_supp = fixa_org_supp;
    }

    public String getFixa_info_remark() {
        return fixa_info_remark;
    }

    public void setFixa_info_remark(String fixa_info_remark) {
        this.fixa_info_remark = fixa_info_remark;
    }

    public Integer getFixa_fixdate_month() {
        return fixa_fixdate_month;
    }

    public void setFixa_fixdate_month(Integer fixa_fixdate_month) {
        this.fixa_fixdate_month = fixa_fixdate_month;
    }

    public BigDecimal getFixa_purc_value() {
        return fixa_purc_value;
    }

    public void setFixa_purc_value(BigDecimal fixa_purc_value) {
        this.fixa_purc_value = fixa_purc_value;
    }

    public Date getFixa_depr_date() {
        return fixa_depr_date;
    }

    public void setFixa_depr_date(Date fixa_depr_date) {
        this.fixa_depr_date = fixa_depr_date;
    }

    public Integer getFixa_depr_months() {
        return fixa_depr_months;
    }

    public void setFixa_depr_months(Integer fixa_depr_months) {
        this.fixa_depr_months = fixa_depr_months;
    }

    public BigDecimal getFixa_depr_rate() {
        return fixa_depr_rate;
    }

    public void setFixa_depr_rate(BigDecimal fixa_depr_rate) {
        this.fixa_depr_rate = fixa_depr_rate;
    }

    public BigDecimal getFixa_tax() {
        return fixa_tax;
    }

    public void setFixa_tax(BigDecimal fixa_tax) {
        this.fixa_tax = fixa_tax;
    }

    public String getFixa_depr_way() {
        return fixa_depr_way;
    }

    public void setFixa_depr_way(String fixa_depr_way) {
        this.fixa_depr_way = fixa_depr_way;
    }

    public WordlistEntity loadFixa_depr_way() {
        String propertyName = "fixa_depr_way";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getFixa_depr_dept() {
        return fixa_depr_dept;
    }

    public void setFixa_depr_dept(String fixa_depr_dept) {
        this.fixa_depr_dept = fixa_depr_dept;
    }

    public BigDecimal getFixa_month_fee() {
        return fixa_month_fee;
    }

    public void setFixa_month_fee(BigDecimal fixa_month_fee) {
        this.fixa_month_fee = fixa_month_fee;
    }

    public String getFixa_use_dept() {
        return fixa_use_dept;
    }

    public void setFixa_use_dept(String fixa_use_dept) {
        this.fixa_use_dept = fixa_use_dept;
    }

    public String getFixa_stog() {
        return fixa_stog;
    }

    public void setFixa_stog(String fixa_stog) {
        this.fixa_stog = fixa_stog;
    }

    public String getFixa_leader() {
        return fixa_leader;
    }

    public void setFixa_leader(String fixa_leader) {
        this.fixa_leader = fixa_leader;
    }

    public String getFixa_use_status() {
        return fixa_use_status;
    }

    public void setFixa_use_status(String fixa_use_status) {
        this.fixa_use_status = fixa_use_status;
    }

    public WordlistEntity loadFixa_use_status() {
        String propertyName = "fixa_use_status";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getFixa_use_date() {
        return fixa_use_date;
    }

    public void setFixa_use_date(Date fixa_use_date) {
        this.fixa_use_date = fixa_use_date;
    }

    public String getFixa_remark() {
        return fixa_remark;
    }

    public void setFixa_remark(String fixa_remark) {
        this.fixa_remark = fixa_remark;
    }

    public String getFixa_mana_dept() {
        return fixa_mana_dept;
    }

    public void setFixa_mana_dept(String fixa_mana_dept) {
        this.fixa_mana_dept = fixa_mana_dept;
    }

    public String getFixa_pict_path() {
        return fixa_pict_path;
    }

    public void setFixa_pict_path(String fixa_pict_path) {
        this.fixa_pict_path = fixa_pict_path;
    }

    public String getFixa_item() {
        return fixa_item;
    }

    public void setFixa_item(String fixa_item) {
        this.fixa_item = fixa_item;
    }

    public String getFixa_vest_type() {
        return fixa_vest_type;
    }

    public void setFixa_vest_type(String fixa_vest_type) {
        this.fixa_vest_type = fixa_vest_type;
    }

    public WordlistEntity loadFixa_vest_type() {
        String propertyName = "fixa_vest_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getFixa_vest_unit() {
        return fixa_vest_unit;
    }

    public void setFixa_vest_unit(String fixa_vest_unit) {
        this.fixa_vest_unit = fixa_vest_unit;
    }

    public List<FixaFixaMateSetaEntity> loadFixa_mate_set() {
        String propertyName = "fixa_mate_set";
        String rootClass = "";
        return (List<FixaFixaMateSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setFixa_mate_set(List<FixaFixaMateSetaEntity> fixa_mate_set) {
        this.fixa_mate_set = fixa_mate_set;
    }

    public List<FixaFixaMateSetaEntity> getFixa_mate_set() {
        return fixa_mate_set;
    }

    public String getFixa_sequ_no() {
        return fixa_sequ_no;
    }

    public void setFixa_sequ_no(String fixa_sequ_no) {
        this.fixa_sequ_no = fixa_sequ_no;
    }



}