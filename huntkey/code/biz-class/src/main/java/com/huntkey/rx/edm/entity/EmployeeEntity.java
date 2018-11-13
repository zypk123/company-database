package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 员工类实体
 * 
 */
public class EmployeeEntity extends ResourceEntity implements Serializable {
    private static final long serialVersionUID = 4361439344541288L;
    
    /**工作证照片*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_pic_url", className="")
    private String remp_pic_url;
    /**自然人对象*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="remp_epeo_obj", className="PeopleEntity")
    private String remp_epeo_obj;
    /**户籍类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_rgt_type", className="WordlistEntity")
    private String remp_rgt_type;
    /**工号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_no", className="")
    private String remp_no;
    /**姓名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_name", className="")
    private String remp_name;
    /**英文名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_name_en", className="")
    private String remp_name_en;
    /**姓名拼音*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_name_cn", className="")
    private String remp_name_cn;
    /**性别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_gender", className="WordlistEntity")
    private String remp_gender;
    /**状态*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_status", className="WordlistEntity")
    private String remp_status;
    /**到职日*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_in_date", className="")
    private Date remp_in_date;
    /**离职日*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_lea_date", className="")
    private Date remp_lea_date;
    /**离职类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_lea_type", className="WordlistEntity")
    private String remp_lea_type;
    /**试用结果*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_try_result", className="WordlistEntity")
    private String remp_try_result;
    /**转正日*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_post_date", className="")
    private Date remp_post_date;
    /**法人公司*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_mcop", className="RelationEntity")
    private String remp_mcop;
    /**担保人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_assure", className="")
    private String remp_assure;
    /**担保人关系*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_assu_rela", className="")
    private String remp_assu_rela;
    /**担保人电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_assu_tel", className="")
    private String remp_assu_tel;
    /**推荐人*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_ref_emp", className="EmployeeEntity")
    private String remp_ref_emp;
    /**荐才建言*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_ref_comm", className="")
    private String remp_ref_comm;
    /**部门*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_dept", className="DepttreeEntity")
    private String remp_dept;
    /**岗位*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_post", className="JobpositionEntity")
    private String remp_post;
    /**岗级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_pgrade", className="WordlistEntity")
    private String remp_pgrade;
    /**员工类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_type", className="WordlistEntity")
    private String remp_type;
    /**薪级*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_sgrade", className="WordlistEntity")
    private String remp_sgrade;
    /**任岗经历*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_post_set", className="RempRempPostSetaEntity")
    private List<RempRempPostSetaEntity> remp_post_set;
    /**合同记录*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_cont_set", className="RempRempContSetaEntity")
    private List<RempRempContSetaEntity> remp_cont_set;
    /**身份证号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_card_id", className="")
    private String remp_card_id;
    /**发证机关*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_card_organ", className="")
    private String remp_card_organ;
    /**证件生效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_card_beg", className="")
    private Date remp_card_beg;
    /**证件失效日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_card_end", className="")
    private Date remp_card_end;
    /**出生日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_birth", className="")
    private Date remp_birth;
    /**政治面貌*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_party", className="WordlistEntity")
    private String remp_party;
    /**民族*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_nation", className="WordlistEntity")
    private String remp_nation;
    /**婚否*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_maried", className="WordlistEntity")
    private String remp_maried;
    /**身高*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_height", className="")
    private Integer remp_height;
    /**体重*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_weight", className="")
    private Integer remp_weight;
    /**血型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_blood", className="WordlistEntity")
    private String remp_blood;
    /**户籍所在地*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_rgt_pro", className="")
    private String remp_rgt_pro;
    /**籍贯*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_origin", className="")
    private String remp_origin;
    /**户籍地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_residence", className="")
    private String remp_residence;
    /**家庭住址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_home_addr", className="")
    private String remp_home_addr;
    /**现住址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_addr", className="")
    private String remp_addr;
    /**家庭成员*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_fam_set", className="RempRempFamSetaEntity")
    private List<RempRempFamSetaEntity> remp_fam_set;
    /**社保类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_secu_type", className="WordlistEntity")
    private String remp_secu_type;
    /**社保电脑号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_secu_no", className="")
    private String remp_secu_no;
    /**手机号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_tel", className="")
    private String remp_tel;
    /**家庭电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_htel", className="")
    private String remp_htel;
    /**电子邮箱*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_mail", className="")
    private String remp_mail;
    /**自我评价*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_self_eval", className="")
    private String remp_self_eval;
    /**首次工作日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_work_date", className="")
    private Date remp_work_date;
    /**教育背景*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_study_set", className="RempRempStudySetaEntity")
    private List<RempRempStudySetaEntity> remp_study_set;
    /**专业技能*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_skill_set", className="RempRempSkillSetaEntity")
    private List<RempRempSkillSetaEntity> remp_skill_set;
    /**工作经历*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="remp_work_set", className="RempRempWorkSetaEntity")
    private List<RempRempWorkSetaEntity> remp_work_set;

    public String getRemp_pic_url() {
        return remp_pic_url;
    }

    public void setRemp_pic_url(String remp_pic_url) {
        this.remp_pic_url = remp_pic_url;
    }

    public String getRemp_epeo_obj() {
        return remp_epeo_obj;
    }

    public void setRemp_epeo_obj(String remp_epeo_obj) {
        this.remp_epeo_obj = remp_epeo_obj;
    }

    public PeopleEntity loadRemp_epeo_obj() {
        String propertyName = "remp_epeo_obj";
        return (PeopleEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_rgt_type() {
        return remp_rgt_type;
    }

    public void setRemp_rgt_type(String remp_rgt_type) {
        this.remp_rgt_type = remp_rgt_type;
    }

    public WordlistEntity loadRemp_rgt_type() {
        String propertyName = "remp_rgt_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_no() {
        return remp_no;
    }

    public void setRemp_no(String remp_no) {
        this.remp_no = remp_no;
    }

    public String getRemp_name() {
        return remp_name;
    }

    public void setRemp_name(String remp_name) {
        this.remp_name = remp_name;
    }

    public String getRemp_name_en() {
        return remp_name_en;
    }

    public void setRemp_name_en(String remp_name_en) {
        this.remp_name_en = remp_name_en;
    }

    public String getRemp_name_cn() {
        return remp_name_cn;
    }

    public void setRemp_name_cn(String remp_name_cn) {
        this.remp_name_cn = remp_name_cn;
    }

    public String getRemp_gender() {
        return remp_gender;
    }

    public void setRemp_gender(String remp_gender) {
        this.remp_gender = remp_gender;
    }

    public WordlistEntity loadRemp_gender() {
        String propertyName = "remp_gender";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_status() {
        return remp_status;
    }

    public void setRemp_status(String remp_status) {
        this.remp_status = remp_status;
    }

    public WordlistEntity loadRemp_status() {
        String propertyName = "remp_status";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getRemp_in_date() {
        return remp_in_date;
    }

    public void setRemp_in_date(Date remp_in_date) {
        this.remp_in_date = remp_in_date;
    }

    public Date getRemp_lea_date() {
        return remp_lea_date;
    }

    public void setRemp_lea_date(Date remp_lea_date) {
        this.remp_lea_date = remp_lea_date;
    }

    public String getRemp_lea_type() {
        return remp_lea_type;
    }

    public void setRemp_lea_type(String remp_lea_type) {
        this.remp_lea_type = remp_lea_type;
    }

    public WordlistEntity loadRemp_lea_type() {
        String propertyName = "remp_lea_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_try_result() {
        return remp_try_result;
    }

    public void setRemp_try_result(String remp_try_result) {
        this.remp_try_result = remp_try_result;
    }

    public WordlistEntity loadRemp_try_result() {
        String propertyName = "remp_try_result";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Date getRemp_post_date() {
        return remp_post_date;
    }

    public void setRemp_post_date(Date remp_post_date) {
        this.remp_post_date = remp_post_date;
    }

    public String getRemp_mcop() {
        return remp_mcop;
    }

    public void setRemp_mcop(String remp_mcop) {
        this.remp_mcop = remp_mcop;
    }

    public RelationEntity loadRemp_mcop() {
        String propertyName = "remp_mcop";
        return (RelationEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_assure() {
        return remp_assure;
    }

    public void setRemp_assure(String remp_assure) {
        this.remp_assure = remp_assure;
    }

    public String getRemp_assu_rela() {
        return remp_assu_rela;
    }

    public void setRemp_assu_rela(String remp_assu_rela) {
        this.remp_assu_rela = remp_assu_rela;
    }

    public String getRemp_assu_tel() {
        return remp_assu_tel;
    }

    public void setRemp_assu_tel(String remp_assu_tel) {
        this.remp_assu_tel = remp_assu_tel;
    }

    public String getRemp_ref_emp() {
        return remp_ref_emp;
    }

    public void setRemp_ref_emp(String remp_ref_emp) {
        this.remp_ref_emp = remp_ref_emp;
    }

    public EmployeeEntity loadRemp_ref_emp() {
        String propertyName = "remp_ref_emp";
        return (EmployeeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_ref_comm() {
        return remp_ref_comm;
    }

    public void setRemp_ref_comm(String remp_ref_comm) {
        this.remp_ref_comm = remp_ref_comm;
    }

    public String getRemp_dept() {
        return remp_dept;
    }

    public void setRemp_dept(String remp_dept) {
        this.remp_dept = remp_dept;
    }

    public DepttreeEntity loadRemp_dept() {
        String propertyName = "remp_dept";
        return (DepttreeEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_post() {
        return remp_post;
    }

    public void setRemp_post(String remp_post) {
        this.remp_post = remp_post;
    }

    public JobpositionEntity loadRemp_post() {
        String propertyName = "remp_post";
        return (JobpositionEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_pgrade() {
        return remp_pgrade;
    }

    public void setRemp_pgrade(String remp_pgrade) {
        this.remp_pgrade = remp_pgrade;
    }

    public WordlistEntity loadRemp_pgrade() {
        String propertyName = "remp_pgrade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_type() {
        return remp_type;
    }

    public void setRemp_type(String remp_type) {
        this.remp_type = remp_type;
    }

    public WordlistEntity loadRemp_type() {
        String propertyName = "remp_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_sgrade() {
        return remp_sgrade;
    }

    public void setRemp_sgrade(String remp_sgrade) {
        this.remp_sgrade = remp_sgrade;
    }

    public WordlistEntity loadRemp_sgrade() {
        String propertyName = "remp_sgrade";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public List<RempRempPostSetaEntity> loadRemp_post_set() {
        String propertyName = "remp_post_set";
        String rootClass = "";
        return (List<RempRempPostSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRemp_post_set(List<RempRempPostSetaEntity> remp_post_set) {
        this.remp_post_set = remp_post_set;
    }

    public List<RempRempPostSetaEntity> getRemp_post_set() {
        return remp_post_set;
    }

    public List<RempRempContSetaEntity> loadRemp_cont_set() {
        String propertyName = "remp_cont_set";
        String rootClass = "";
        return (List<RempRempContSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRemp_cont_set(List<RempRempContSetaEntity> remp_cont_set) {
        this.remp_cont_set = remp_cont_set;
    }

    public List<RempRempContSetaEntity> getRemp_cont_set() {
        return remp_cont_set;
    }

    public String getRemp_card_id() {
        return remp_card_id;
    }

    public void setRemp_card_id(String remp_card_id) {
        this.remp_card_id = remp_card_id;
    }

    public String getRemp_card_organ() {
        return remp_card_organ;
    }

    public void setRemp_card_organ(String remp_card_organ) {
        this.remp_card_organ = remp_card_organ;
    }

    public Date getRemp_card_beg() {
        return remp_card_beg;
    }

    public void setRemp_card_beg(Date remp_card_beg) {
        this.remp_card_beg = remp_card_beg;
    }

    public Date getRemp_card_end() {
        return remp_card_end;
    }

    public void setRemp_card_end(Date remp_card_end) {
        this.remp_card_end = remp_card_end;
    }

    public Date getRemp_birth() {
        return remp_birth;
    }

    public void setRemp_birth(Date remp_birth) {
        this.remp_birth = remp_birth;
    }

    public String getRemp_party() {
        return remp_party;
    }

    public void setRemp_party(String remp_party) {
        this.remp_party = remp_party;
    }

    public WordlistEntity loadRemp_party() {
        String propertyName = "remp_party";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_nation() {
        return remp_nation;
    }

    public void setRemp_nation(String remp_nation) {
        this.remp_nation = remp_nation;
    }

    public WordlistEntity loadRemp_nation() {
        String propertyName = "remp_nation";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_maried() {
        return remp_maried;
    }

    public void setRemp_maried(String remp_maried) {
        this.remp_maried = remp_maried;
    }

    public WordlistEntity loadRemp_maried() {
        String propertyName = "remp_maried";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getRemp_height() {
        return remp_height;
    }

    public void setRemp_height(Integer remp_height) {
        this.remp_height = remp_height;
    }

    public Integer getRemp_weight() {
        return remp_weight;
    }

    public void setRemp_weight(Integer remp_weight) {
        this.remp_weight = remp_weight;
    }

    public String getRemp_blood() {
        return remp_blood;
    }

    public void setRemp_blood(String remp_blood) {
        this.remp_blood = remp_blood;
    }

    public WordlistEntity loadRemp_blood() {
        String propertyName = "remp_blood";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_rgt_pro() {
        return remp_rgt_pro;
    }

    public void setRemp_rgt_pro(String remp_rgt_pro) {
        this.remp_rgt_pro = remp_rgt_pro;
    }

    public String getRemp_origin() {
        return remp_origin;
    }

    public void setRemp_origin(String remp_origin) {
        this.remp_origin = remp_origin;
    }

    public String getRemp_residence() {
        return remp_residence;
    }

    public void setRemp_residence(String remp_residence) {
        this.remp_residence = remp_residence;
    }

    public String getRemp_home_addr() {
        return remp_home_addr;
    }

    public void setRemp_home_addr(String remp_home_addr) {
        this.remp_home_addr = remp_home_addr;
    }

    public String getRemp_addr() {
        return remp_addr;
    }

    public void setRemp_addr(String remp_addr) {
        this.remp_addr = remp_addr;
    }

    public List<RempRempFamSetaEntity> loadRemp_fam_set() {
        String propertyName = "remp_fam_set";
        String rootClass = "";
        return (List<RempRempFamSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRemp_fam_set(List<RempRempFamSetaEntity> remp_fam_set) {
        this.remp_fam_set = remp_fam_set;
    }

    public List<RempRempFamSetaEntity> getRemp_fam_set() {
        return remp_fam_set;
    }

    public String getRemp_secu_type() {
        return remp_secu_type;
    }

    public void setRemp_secu_type(String remp_secu_type) {
        this.remp_secu_type = remp_secu_type;
    }

    public WordlistEntity loadRemp_secu_type() {
        String propertyName = "remp_secu_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getRemp_secu_no() {
        return remp_secu_no;
    }

    public void setRemp_secu_no(String remp_secu_no) {
        this.remp_secu_no = remp_secu_no;
    }

    public String getRemp_tel() {
        return remp_tel;
    }

    public void setRemp_tel(String remp_tel) {
        this.remp_tel = remp_tel;
    }

    public String getRemp_htel() {
        return remp_htel;
    }

    public void setRemp_htel(String remp_htel) {
        this.remp_htel = remp_htel;
    }

    public String getRemp_mail() {
        return remp_mail;
    }

    public void setRemp_mail(String remp_mail) {
        this.remp_mail = remp_mail;
    }

    public String getRemp_self_eval() {
        return remp_self_eval;
    }

    public void setRemp_self_eval(String remp_self_eval) {
        this.remp_self_eval = remp_self_eval;
    }

    public Date getRemp_work_date() {
        return remp_work_date;
    }

    public void setRemp_work_date(Date remp_work_date) {
        this.remp_work_date = remp_work_date;
    }

    public List<RempRempStudySetaEntity> loadRemp_study_set() {
        String propertyName = "remp_study_set";
        String rootClass = "";
        return (List<RempRempStudySetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRemp_study_set(List<RempRempStudySetaEntity> remp_study_set) {
        this.remp_study_set = remp_study_set;
    }

    public List<RempRempStudySetaEntity> getRemp_study_set() {
        return remp_study_set;
    }

    public List<RempRempSkillSetaEntity> loadRemp_skill_set() {
        String propertyName = "remp_skill_set";
        String rootClass = "";
        return (List<RempRempSkillSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRemp_skill_set(List<RempRempSkillSetaEntity> remp_skill_set) {
        this.remp_skill_set = remp_skill_set;
    }

    public List<RempRempSkillSetaEntity> getRemp_skill_set() {
        return remp_skill_set;
    }

    public List<RempRempWorkSetaEntity> loadRemp_work_set() {
        String propertyName = "remp_work_set";
        String rootClass = "";
        return (List<RempRempWorkSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setRemp_work_set(List<RempRempWorkSetaEntity> remp_work_set) {
        this.remp_work_set = remp_work_set;
    }

    public List<RempRempWorkSetaEntity> getRemp_work_set() {
        return remp_work_set;
    }



}