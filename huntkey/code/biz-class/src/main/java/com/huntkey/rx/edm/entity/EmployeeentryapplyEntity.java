package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.math.BigDecimal;
import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 职员入职单类实体
 * 
 */
public class EmployeeentryapplyEntity extends OrderEntity implements Serializable {
    private static final long serialVersionUID = 3225604601792516L;
    
    /**工作证照片*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_pic_url", className="")
    private String oeeo_pic_url;
    /**家庭住址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_home_addr", className="")
    private String oeeo_home_addr;
    /**任职岗级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_pgrade", className="WordlistEntity")
    private String oeeo_pgrade;
    /**入职人数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_num_tot", className="")
    private Integer oeeo_num_tot;
    /**单据类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_type", className="WordlistEntity")
    private String oeeo_type;
    /**户籍类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_rgt_type", className="WordlistEntity")
    private String oeeo_rgt_type;
    /**工号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_code", className="")
    private String oeeo_code;
    /**姓名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_name", className="")
    private String oeeo_name;
    /**英文名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_name_en", className="")
    private String oeeo_name_en;
    /**姓名拼音*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_name_cn", className="")
    private String oeeo_name_cn;
    /**性别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_gender", className="WordlistEntity")
    private String oeeo_gender;
    /**证件类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_card_type", className="")
    private String oeeo_card_type;
    /**证件号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_card_no", className="")
    private String oeeo_card_no;
    /**签发机关*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_card_org", className="")
    private String oeeo_card_org;
    /**证件生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_card_beg", className="")
    private Date oeeo_card_beg;
    /**证件失效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_card_end", className="")
    private Date oeeo_card_end;
    /**出生日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_birth", className="")
    private Date oeeo_birth;
    /**籍贯*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_origin", className="AreaEntity")
    private String oeeo_origin;
    /**户籍所在地*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_rgt_pro", className="AreaEntity")
    private String oeeo_rgt_pro;
    /**户籍地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_residence", className="")
    private String oeeo_residence;
    /**现住址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_addr", className="")
    private String oeeo_addr;
    /**民族*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_nation", className="WordlistEntity")
    private String oeeo_nation;
    /**婚否*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_maried", className="WordlistEntity")
    private String oeeo_maried;
    /**政治面貌*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_party", className="WordlistEntity")
    private String oeeo_party;
    /**身高*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_height", className="")
    private BigDecimal oeeo_height;
    /**体重*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_weight", className="")
    private BigDecimal oeeo_weight;
    /**血型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_blood", className="WordlistEntity")
    private String oeeo_blood;
    /**手机号码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_tel", className="")
    private String oeeo_tel;
    /**e-mail*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_mail", className="")
    private String oeeo_mail;
    /**家庭电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_htel", className="")
    private String oeeo_htel;
    /**紧急联系人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_cont", className="")
    private String oeeo_cont;
    /**紧急联系电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_cont_tel", className="")
    private String oeeo_cont_tel;
    /**首次工作时间*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_work_date", className="")
    private Date oeeo_work_date;
    /**担保人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_assure", className="")
    private String oeeo_assure;
    /**担保人关系*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_assu_rela", className="")
    private String oeeo_assu_rela;
    /**介绍人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_intr", className="EmployeeEntity")
    private String oeeo_intr;
    /**社保类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_secu_type", className="WordlistEntity")
    private String oeeo_secu_type;
    /**社保电脑号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_secu_no", className="")
    private String oeeo_secu_no;
    /**任职部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_dept", className="DepttreeEntity")
    private String oeeo_dept;
    /**任职岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_post", className="JobpositionEntity")
    private String oeeo_post;
    /**试用期薪级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_try_sgrade", className="WordlistEntity")
    private String oeeo_try_sgrade;
    /**入职日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_en_date", className="")
    private Date oeeo_en_date;
    /**试用月数*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_try_mon", className="WordlistEntity")
    private String oeeo_try_mon;
    /**试用起始日*/
    @PropertyAnnotation(fomula="da093214a4d649beb6b31b04abaecefc", limitFomula="", fieldName="oeeo_try_beg", className="")
    private Date oeeo_try_beg;
    /**试用结束日*/
    @PropertyAnnotation(fomula="859a0404cae64bffb2941896bdf6d2c4", limitFomula="", fieldName="oeeo_try_end", className="")
    private Date oeeo_try_end;
    /**预转正日*/
    @PropertyAnnotation(fomula="4fdfd93aa8e5418c8f03fcf32ee012b0", limitFomula="", fieldName="oeeo_post_date", className="")
    private Date oeeo_post_date;
    /**指引人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_guide", className="EmployeeEntity")
    private String oeeo_guide;
    /**教育背景*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_study_set", className="OeeoOeeoStudySetaEntity")
    private List<OeeoOeeoStudySetaEntity> oeeo_study_set;
    /**专业技能*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_skill_set", className="OeeoOeeoSkillSetaEntity")
    private List<OeeoOeeoSkillSetaEntity> oeeo_skill_set;
    /**工作经历*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_work_set", className="OeeoOeeoWorkSetaEntity")
    private List<OeeoOeeoWorkSetaEntity> oeeo_work_set;
    /**家庭成员*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_fam_set", className="OeeoOeeoFamSetaEntity")
    private List<OeeoOeeoFamSetaEntity> oeeo_fam_set;
    /**自我评价*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_self_eval", className="")
    private String oeeo_self_eval;
    /**担保人电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_assu_tel", className="")
    private String oeeo_assu_tel;
    /**员工类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="oeeo_emp_type", className="")
    private String oeeo_emp_type;
    /**自然人*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="oeeo_epeo", className="PeopleEntity")
    private String oeeo_epeo;

    public String getOeeo_pic_url() {
        return oeeo_pic_url;
    }

    public void setOeeo_pic_url(String oeeo_pic_url) {
        this.oeeo_pic_url = oeeo_pic_url;
    }

    public String getOeeo_home_addr() {
        return oeeo_home_addr;
    }

    public void setOeeo_home_addr(String oeeo_home_addr) {
        this.oeeo_home_addr = oeeo_home_addr;
    }

    public String getOeeo_pgrade() {
        return oeeo_pgrade;
    }

    public void setOeeo_pgrade(String oeeo_pgrade) {
        this.oeeo_pgrade = oeeo_pgrade;
    }

    public WordlistEntity loadOeeo_pgrade() {
        String propertyName = "oeeo_pgrade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getOeeo_num_tot() {
        return oeeo_num_tot;
    }

    public void setOeeo_num_tot(Integer oeeo_num_tot) {
        this.oeeo_num_tot = oeeo_num_tot;
    }

    public String getOeeo_type() {
        return oeeo_type;
    }

    public void setOeeo_type(String oeeo_type) {
        this.oeeo_type = oeeo_type;
    }

    public WordlistEntity loadOeeo_type() {
        String propertyName = "oeeo_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_rgt_type() {
        return oeeo_rgt_type;
    }

    public void setOeeo_rgt_type(String oeeo_rgt_type) {
        this.oeeo_rgt_type = oeeo_rgt_type;
    }

    public WordlistEntity loadOeeo_rgt_type() {
        String propertyName = "oeeo_rgt_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_code() {
        return oeeo_code;
    }

    public void setOeeo_code(String oeeo_code) {
        this.oeeo_code = oeeo_code;
    }

    public String getOeeo_name() {
        return oeeo_name;
    }

    public void setOeeo_name(String oeeo_name) {
        this.oeeo_name = oeeo_name;
    }

    public String getOeeo_name_en() {
        return oeeo_name_en;
    }

    public void setOeeo_name_en(String oeeo_name_en) {
        this.oeeo_name_en = oeeo_name_en;
    }

    public String getOeeo_name_cn() {
        return oeeo_name_cn;
    }

    public void setOeeo_name_cn(String oeeo_name_cn) {
        this.oeeo_name_cn = oeeo_name_cn;
    }

    public String getOeeo_gender() {
        return oeeo_gender;
    }

    public void setOeeo_gender(String oeeo_gender) {
        this.oeeo_gender = oeeo_gender;
    }

    public WordlistEntity loadOeeo_gender() {
        String propertyName = "oeeo_gender";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_card_type() {
        return oeeo_card_type;
    }

    public void setOeeo_card_type(String oeeo_card_type) {
        this.oeeo_card_type = oeeo_card_type;
    }

    public String getOeeo_card_no() {
        return oeeo_card_no;
    }

    public void setOeeo_card_no(String oeeo_card_no) {
        this.oeeo_card_no = oeeo_card_no;
    }

    public String getOeeo_card_org() {
        return oeeo_card_org;
    }

    public void setOeeo_card_org(String oeeo_card_org) {
        this.oeeo_card_org = oeeo_card_org;
    }

    public Date getOeeo_card_beg() {
        return oeeo_card_beg;
    }

    public void setOeeo_card_beg(Date oeeo_card_beg) {
        this.oeeo_card_beg = oeeo_card_beg;
    }

    public Date getOeeo_card_end() {
        return oeeo_card_end;
    }

    public void setOeeo_card_end(Date oeeo_card_end) {
        this.oeeo_card_end = oeeo_card_end;
    }

    public Date getOeeo_birth() {
        return oeeo_birth;
    }

    public void setOeeo_birth(Date oeeo_birth) {
        this.oeeo_birth = oeeo_birth;
    }

    public String getOeeo_origin() {
        return oeeo_origin;
    }

    public void setOeeo_origin(String oeeo_origin) {
        this.oeeo_origin = oeeo_origin;
    }

    public AreaEntity loadOeeo_origin() {
        String propertyName = "oeeo_origin";
        return (AreaEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_rgt_pro() {
        return oeeo_rgt_pro;
    }

    public void setOeeo_rgt_pro(String oeeo_rgt_pro) {
        this.oeeo_rgt_pro = oeeo_rgt_pro;
    }

    public AreaEntity loadOeeo_rgt_pro() {
        String propertyName = "oeeo_rgt_pro";
        return (AreaEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_residence() {
        return oeeo_residence;
    }

    public void setOeeo_residence(String oeeo_residence) {
        this.oeeo_residence = oeeo_residence;
    }

    public String getOeeo_addr() {
        return oeeo_addr;
    }

    public void setOeeo_addr(String oeeo_addr) {
        this.oeeo_addr = oeeo_addr;
    }

    public String getOeeo_nation() {
        return oeeo_nation;
    }

    public void setOeeo_nation(String oeeo_nation) {
        this.oeeo_nation = oeeo_nation;
    }

    public WordlistEntity loadOeeo_nation() {
        String propertyName = "oeeo_nation";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_maried() {
        return oeeo_maried;
    }

    public void setOeeo_maried(String oeeo_maried) {
        this.oeeo_maried = oeeo_maried;
    }

    public WordlistEntity loadOeeo_maried() {
        String propertyName = "oeeo_maried";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_party() {
        return oeeo_party;
    }

    public void setOeeo_party(String oeeo_party) {
        this.oeeo_party = oeeo_party;
    }

    public WordlistEntity loadOeeo_party() {
        String propertyName = "oeeo_party";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public BigDecimal getOeeo_height() {
        return oeeo_height;
    }

    public void setOeeo_height(BigDecimal oeeo_height) {
        this.oeeo_height = oeeo_height;
    }

    public BigDecimal getOeeo_weight() {
        return oeeo_weight;
    }

    public void setOeeo_weight(BigDecimal oeeo_weight) {
        this.oeeo_weight = oeeo_weight;
    }

    public String getOeeo_blood() {
        return oeeo_blood;
    }

    public void setOeeo_blood(String oeeo_blood) {
        this.oeeo_blood = oeeo_blood;
    }

    public WordlistEntity loadOeeo_blood() {
        String propertyName = "oeeo_blood";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_tel() {
        return oeeo_tel;
    }

    public void setOeeo_tel(String oeeo_tel) {
        this.oeeo_tel = oeeo_tel;
    }

    public String getOeeo_mail() {
        return oeeo_mail;
    }

    public void setOeeo_mail(String oeeo_mail) {
        this.oeeo_mail = oeeo_mail;
    }

    public String getOeeo_htel() {
        return oeeo_htel;
    }

    public void setOeeo_htel(String oeeo_htel) {
        this.oeeo_htel = oeeo_htel;
    }

    public String getOeeo_cont() {
        return oeeo_cont;
    }

    public void setOeeo_cont(String oeeo_cont) {
        this.oeeo_cont = oeeo_cont;
    }

    public String getOeeo_cont_tel() {
        return oeeo_cont_tel;
    }

    public void setOeeo_cont_tel(String oeeo_cont_tel) {
        this.oeeo_cont_tel = oeeo_cont_tel;
    }

    public Date getOeeo_work_date() {
        return oeeo_work_date;
    }

    public void setOeeo_work_date(Date oeeo_work_date) {
        this.oeeo_work_date = oeeo_work_date;
    }

    public String getOeeo_assure() {
        return oeeo_assure;
    }

    public void setOeeo_assure(String oeeo_assure) {
        this.oeeo_assure = oeeo_assure;
    }

    public String getOeeo_assu_rela() {
        return oeeo_assu_rela;
    }

    public void setOeeo_assu_rela(String oeeo_assu_rela) {
        this.oeeo_assu_rela = oeeo_assu_rela;
    }

    public String getOeeo_intr() {
        return oeeo_intr;
    }

    public void setOeeo_intr(String oeeo_intr) {
        this.oeeo_intr = oeeo_intr;
    }

    public EmployeeEntity loadOeeo_intr() {
        String propertyName = "oeeo_intr";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_secu_type() {
        return oeeo_secu_type;
    }

    public void setOeeo_secu_type(String oeeo_secu_type) {
        this.oeeo_secu_type = oeeo_secu_type;
    }

    public WordlistEntity loadOeeo_secu_type() {
        String propertyName = "oeeo_secu_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_secu_no() {
        return oeeo_secu_no;
    }

    public void setOeeo_secu_no(String oeeo_secu_no) {
        this.oeeo_secu_no = oeeo_secu_no;
    }

    public String getOeeo_dept() {
        return oeeo_dept;
    }

    public void setOeeo_dept(String oeeo_dept) {
        this.oeeo_dept = oeeo_dept;
    }

    public DepttreeEntity loadOeeo_dept() {
        String propertyName = "oeeo_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_post() {
        return oeeo_post;
    }

    public void setOeeo_post(String oeeo_post) {
        this.oeeo_post = oeeo_post;
    }

    public JobpositionEntity loadOeeo_post() {
        String propertyName = "oeeo_post";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getOeeo_try_sgrade() {
        return oeeo_try_sgrade;
    }

    public void setOeeo_try_sgrade(String oeeo_try_sgrade) {
        this.oeeo_try_sgrade = oeeo_try_sgrade;
    }

    public WordlistEntity loadOeeo_try_sgrade() {
        String propertyName = "oeeo_try_sgrade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getOeeo_en_date() {
        return oeeo_en_date;
    }

    public void setOeeo_en_date(Date oeeo_en_date) {
        this.oeeo_en_date = oeeo_en_date;
    }

    public String getOeeo_try_mon() {
        return oeeo_try_mon;
    }

    public void setOeeo_try_mon(String oeeo_try_mon) {
        this.oeeo_try_mon = oeeo_try_mon;
    }

    public WordlistEntity loadOeeo_try_mon() {
        String propertyName = "oeeo_try_mon";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getOeeo_try_beg() {
        return oeeo_try_beg;
    }

    public void setOeeo_try_beg(Date oeeo_try_beg) {
        this.oeeo_try_beg = oeeo_try_beg;
    }

    public Date getOeeo_try_end() {
        return oeeo_try_end;
    }

    public void setOeeo_try_end(Date oeeo_try_end) {
        this.oeeo_try_end = oeeo_try_end;
    }

    public Date getOeeo_post_date() {
        return oeeo_post_date;
    }

    public void setOeeo_post_date(Date oeeo_post_date) {
        this.oeeo_post_date = oeeo_post_date;
    }

    public String getOeeo_guide() {
        return oeeo_guide;
    }

    public void setOeeo_guide(String oeeo_guide) {
        this.oeeo_guide = oeeo_guide;
    }

    public EmployeeEntity loadOeeo_guide() {
        String propertyName = "oeeo_guide";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<OeeoOeeoStudySetaEntity> loadOeeo_study_set() {
        String propertyName = "oeeo_study_set";
        String rootClass = "";
        return (List<OeeoOeeoStudySetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOeeo_study_set(List<OeeoOeeoStudySetaEntity> oeeo_study_set) {
        this.oeeo_study_set = oeeo_study_set;
    }

    public List<OeeoOeeoStudySetaEntity> getOeeo_study_set() {
        return oeeo_study_set;
    }

    public List<OeeoOeeoSkillSetaEntity> loadOeeo_skill_set() {
        String propertyName = "oeeo_skill_set";
        String rootClass = "";
        return (List<OeeoOeeoSkillSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOeeo_skill_set(List<OeeoOeeoSkillSetaEntity> oeeo_skill_set) {
        this.oeeo_skill_set = oeeo_skill_set;
    }

    public List<OeeoOeeoSkillSetaEntity> getOeeo_skill_set() {
        return oeeo_skill_set;
    }

    public List<OeeoOeeoWorkSetaEntity> loadOeeo_work_set() {
        String propertyName = "oeeo_work_set";
        String rootClass = "";
        return (List<OeeoOeeoWorkSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOeeo_work_set(List<OeeoOeeoWorkSetaEntity> oeeo_work_set) {
        this.oeeo_work_set = oeeo_work_set;
    }

    public List<OeeoOeeoWorkSetaEntity> getOeeo_work_set() {
        return oeeo_work_set;
    }

    public List<OeeoOeeoFamSetaEntity> loadOeeo_fam_set() {
        String propertyName = "oeeo_fam_set";
        String rootClass = "";
        return (List<OeeoOeeoFamSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setOeeo_fam_set(List<OeeoOeeoFamSetaEntity> oeeo_fam_set) {
        this.oeeo_fam_set = oeeo_fam_set;
    }

    public List<OeeoOeeoFamSetaEntity> getOeeo_fam_set() {
        return oeeo_fam_set;
    }

    public String getOeeo_self_eval() {
        return oeeo_self_eval;
    }

    public void setOeeo_self_eval(String oeeo_self_eval) {
        this.oeeo_self_eval = oeeo_self_eval;
    }

    public String getOeeo_assu_tel() {
        return oeeo_assu_tel;
    }

    public void setOeeo_assu_tel(String oeeo_assu_tel) {
        this.oeeo_assu_tel = oeeo_assu_tel;
    }

    public String getOeeo_emp_type() {
        return oeeo_emp_type;
    }

    public void setOeeo_emp_type(String oeeo_emp_type) {
        this.oeeo_emp_type = oeeo_emp_type;
    }

    public String getOeeo_epeo() {
        return oeeo_epeo;
    }

    public void setOeeo_epeo(String oeeo_epeo) {
        this.oeeo_epeo = oeeo_epeo;
    }

    public PeopleEntity loadOeeo_epeo() {
        String propertyName = "oeeo_epeo";
        return (PeopleEntity)EntityUtils.getPropertyObj(this, propertyName);
    }



}