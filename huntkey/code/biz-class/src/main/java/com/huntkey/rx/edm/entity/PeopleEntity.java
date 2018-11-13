package com.huntkey.rx.edm.entity;
import java.io.Serializable;
import java.util.*;

import java.util.Date;
import com.huntkey.rx.base.PropertyAnnotation;
import com.huntkey.rx.util.EntityUtils;


/**
 * 
 * 自然人类实体
 * 
 */
public class PeopleEntity extends EcosystemEntity implements Serializable {
    private static final long serialVersionUID = 9573846268071567L;
    
    /**国籍*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="epeo_nationality", className="")
    private String epeo_nationality;
    /**识别码*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_code", className="")
    private String epeo_code;
    /**英文名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_name_en", className="")
    private String epeo_name_en;
    /**姓名拼音*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_name_cn", className="")
    private String epeo_name_cn;
    /**昵称*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_name_ni", className="")
    private String epeo_name_ni;
    /**姓*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_fist_name", className="")
    private String epeo_fist_name;
    /**名*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_last_name", className="")
    private String epeo_last_name;
    /**性别*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_gender", className="WordlistEntity")
    private String epeo_gender;
    /**身份证号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_card_no", className="")
    private String epeo_card_no;
    /**证件列表*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_card_set", className="EpeoEpeoCardSetaEntity")
    private List<EpeoEpeoCardSetaEntity> epeo_card_set;
    /**出生日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_birth", className="")
    private Date epeo_birth;
    /**政治面貌*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_party", className="WordlistEntity")
    private String epeo_party;
    /**民族*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_nation", className="WordlistEntity")
    private String epeo_nation;
    /**婚否*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_maried", className="WordlistEntity")
    private String epeo_maried;
    /**身高*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_height", className="")
    private Integer epeo_height;
    /**体重*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_weight", className="")
    private Integer epeo_weight;
    /**血型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_blood", className="WordlistEntity")
    private String epeo_blood;
    /**户籍所在地*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_rgt_pro", className="")
    private String epeo_rgt_pro;
    /**籍贯*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_origin", className="")
    private String epeo_origin;
    /**户籍地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_residence", className="")
    private String epeo_residence;
    /**家庭住址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_home_addr", className="")
    private String epeo_home_addr;
    /**现住地址*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_addr", className="")
    private String epeo_addr;
    /**家庭成员*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_fam_set", className="EpeoEpeoFamSetaEntity")
    private List<EpeoEpeoFamSetaEntity> epeo_fam_set;
    /**社保类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_secu_type", className="WordlistEntity")
    private String epeo_secu_type;
    /**社保电脑号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_secu_no", className="")
    private String epeo_secu_no;
    /**手机号*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_tel", className="")
    private String epeo_tel;
    /**家庭电话*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_htel", className="")
    private String epeo_htel;
    /**email*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_mail", className="")
    private String epeo_mail;
    /**自我评价*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_self_eval", className="")
    private String epeo_self_eval;
    /**首次工作日期*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_work_date", className="")
    private Date epeo_work_date;
    /**求职意向*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_ajob_set", className="EpeoEpeoAjobSetaEntity")
    private List<EpeoEpeoAjobSetaEntity> epeo_ajob_set;
    /**教育背景*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_stu_set", className="EpeoEpeoStuSetaEntity")
    private List<EpeoEpeoStuSetaEntity> epeo_stu_set;
    /**专业技能*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_skill_set", className="EpeoEpeoSkillSetaEntity")
    private List<EpeoEpeoSkillSetaEntity> epeo_skill_set;
    /**工作经历*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_work_set", className="EpeoEpeoWorkSetaEntity")
    private List<EpeoEpeoWorkSetaEntity> epeo_work_set;
    /**户籍类型*/
    @PropertyAnnotation(fomula="", limitFomula="", fieldName="epeo_rgt_type", className="WordlistEntity")
    private String epeo_rgt_type;
    /**锐信号*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="epeo_rxnbr", className="")
    private String epeo_rxnbr;
    /**密码*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="epeo_password", className="")
    private String epeo_password;
    /**照片*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="epeo_photourl", className="")
    private String epeo_photourl;
    /**我的职场集合*/
    @PropertyAnnotation(fomula="null", limitFomula="null", fieldName="epeo_ente_set", className="EpeoEpeoEnteSetaEntity")
    private List<EpeoEpeoEnteSetaEntity> epeo_ente_set;

    public String getEpeo_nationality() {
        return epeo_nationality;
    }

    public void setEpeo_nationality(String epeo_nationality) {
        this.epeo_nationality = epeo_nationality;
    }

    public String getEpeo_code() {
        return epeo_code;
    }

    public void setEpeo_code(String epeo_code) {
        this.epeo_code = epeo_code;
    }

    public String getEpeo_name_en() {
        return epeo_name_en;
    }

    public void setEpeo_name_en(String epeo_name_en) {
        this.epeo_name_en = epeo_name_en;
    }

    public String getEpeo_name_cn() {
        return epeo_name_cn;
    }

    public void setEpeo_name_cn(String epeo_name_cn) {
        this.epeo_name_cn = epeo_name_cn;
    }

    public String getEpeo_name_ni() {
        return epeo_name_ni;
    }

    public void setEpeo_name_ni(String epeo_name_ni) {
        this.epeo_name_ni = epeo_name_ni;
    }

    public String getEpeo_fist_name() {
        return epeo_fist_name;
    }

    public void setEpeo_fist_name(String epeo_fist_name) {
        this.epeo_fist_name = epeo_fist_name;
    }

    public String getEpeo_last_name() {
        return epeo_last_name;
    }

    public void setEpeo_last_name(String epeo_last_name) {
        this.epeo_last_name = epeo_last_name;
    }

    public String getEpeo_gender() {
        return epeo_gender;
    }

    public void setEpeo_gender(String epeo_gender) {
        this.epeo_gender = epeo_gender;
    }

    public WordlistEntity loadEpeo_gender() {
        String propertyName = "epeo_gender";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_card_no() {
        return epeo_card_no;
    }

    public void setEpeo_card_no(String epeo_card_no) {
        this.epeo_card_no = epeo_card_no;
    }

    public List<EpeoEpeoCardSetaEntity> loadEpeo_card_set() {
        String propertyName = "epeo_card_set";
        String rootClass = "";
        return (List<EpeoEpeoCardSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEpeo_card_set(List<EpeoEpeoCardSetaEntity> epeo_card_set) {
        this.epeo_card_set = epeo_card_set;
    }

    public List<EpeoEpeoCardSetaEntity> getEpeo_card_set() {
        return epeo_card_set;
    }

    public Date getEpeo_birth() {
        return epeo_birth;
    }

    public void setEpeo_birth(Date epeo_birth) {
        this.epeo_birth = epeo_birth;
    }

    public String getEpeo_party() {
        return epeo_party;
    }

    public void setEpeo_party(String epeo_party) {
        this.epeo_party = epeo_party;
    }

    public WordlistEntity loadEpeo_party() {
        String propertyName = "epeo_party";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_nation() {
        return epeo_nation;
    }

    public void setEpeo_nation(String epeo_nation) {
        this.epeo_nation = epeo_nation;
    }

    public WordlistEntity loadEpeo_nation() {
        String propertyName = "epeo_nation";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_maried() {
        return epeo_maried;
    }

    public void setEpeo_maried(String epeo_maried) {
        this.epeo_maried = epeo_maried;
    }

    public WordlistEntity loadEpeo_maried() {
        String propertyName = "epeo_maried";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public Integer getEpeo_height() {
        return epeo_height;
    }

    public void setEpeo_height(Integer epeo_height) {
        this.epeo_height = epeo_height;
    }

    public Integer getEpeo_weight() {
        return epeo_weight;
    }

    public void setEpeo_weight(Integer epeo_weight) {
        this.epeo_weight = epeo_weight;
    }

    public String getEpeo_blood() {
        return epeo_blood;
    }

    public void setEpeo_blood(String epeo_blood) {
        this.epeo_blood = epeo_blood;
    }

    public WordlistEntity loadEpeo_blood() {
        String propertyName = "epeo_blood";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_rgt_pro() {
        return epeo_rgt_pro;
    }

    public void setEpeo_rgt_pro(String epeo_rgt_pro) {
        this.epeo_rgt_pro = epeo_rgt_pro;
    }

    public String getEpeo_origin() {
        return epeo_origin;
    }

    public void setEpeo_origin(String epeo_origin) {
        this.epeo_origin = epeo_origin;
    }

    public String getEpeo_residence() {
        return epeo_residence;
    }

    public void setEpeo_residence(String epeo_residence) {
        this.epeo_residence = epeo_residence;
    }

    public String getEpeo_home_addr() {
        return epeo_home_addr;
    }

    public void setEpeo_home_addr(String epeo_home_addr) {
        this.epeo_home_addr = epeo_home_addr;
    }

    public String getEpeo_addr() {
        return epeo_addr;
    }

    public void setEpeo_addr(String epeo_addr) {
        this.epeo_addr = epeo_addr;
    }

    public List<EpeoEpeoFamSetaEntity> loadEpeo_fam_set() {
        String propertyName = "epeo_fam_set";
        String rootClass = "";
        return (List<EpeoEpeoFamSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEpeo_fam_set(List<EpeoEpeoFamSetaEntity> epeo_fam_set) {
        this.epeo_fam_set = epeo_fam_set;
    }

    public List<EpeoEpeoFamSetaEntity> getEpeo_fam_set() {
        return epeo_fam_set;
    }

    public String getEpeo_secu_type() {
        return epeo_secu_type;
    }

    public void setEpeo_secu_type(String epeo_secu_type) {
        this.epeo_secu_type = epeo_secu_type;
    }

    public WordlistEntity loadEpeo_secu_type() {
        String propertyName = "epeo_secu_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_secu_no() {
        return epeo_secu_no;
    }

    public void setEpeo_secu_no(String epeo_secu_no) {
        this.epeo_secu_no = epeo_secu_no;
    }

    public String getEpeo_tel() {
        return epeo_tel;
    }

    public void setEpeo_tel(String epeo_tel) {
        this.epeo_tel = epeo_tel;
    }

    public String getEpeo_htel() {
        return epeo_htel;
    }

    public void setEpeo_htel(String epeo_htel) {
        this.epeo_htel = epeo_htel;
    }

    public String getEpeo_mail() {
        return epeo_mail;
    }

    public void setEpeo_mail(String epeo_mail) {
        this.epeo_mail = epeo_mail;
    }

    public String getEpeo_self_eval() {
        return epeo_self_eval;
    }

    public void setEpeo_self_eval(String epeo_self_eval) {
        this.epeo_self_eval = epeo_self_eval;
    }

    public Date getEpeo_work_date() {
        return epeo_work_date;
    }

    public void setEpeo_work_date(Date epeo_work_date) {
        this.epeo_work_date = epeo_work_date;
    }

    public List<EpeoEpeoAjobSetaEntity> loadEpeo_ajob_set() {
        String propertyName = "epeo_ajob_set";
        String rootClass = "";
        return (List<EpeoEpeoAjobSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEpeo_ajob_set(List<EpeoEpeoAjobSetaEntity> epeo_ajob_set) {
        this.epeo_ajob_set = epeo_ajob_set;
    }

    public List<EpeoEpeoAjobSetaEntity> getEpeo_ajob_set() {
        return epeo_ajob_set;
    }

    public List<EpeoEpeoStuSetaEntity> loadEpeo_stu_set() {
        String propertyName = "epeo_stu_set";
        String rootClass = "";
        return (List<EpeoEpeoStuSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEpeo_stu_set(List<EpeoEpeoStuSetaEntity> epeo_stu_set) {
        this.epeo_stu_set = epeo_stu_set;
    }

    public List<EpeoEpeoStuSetaEntity> getEpeo_stu_set() {
        return epeo_stu_set;
    }

    public List<EpeoEpeoSkillSetaEntity> loadEpeo_skill_set() {
        String propertyName = "epeo_skill_set";
        String rootClass = "";
        return (List<EpeoEpeoSkillSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEpeo_skill_set(List<EpeoEpeoSkillSetaEntity> epeo_skill_set) {
        this.epeo_skill_set = epeo_skill_set;
    }

    public List<EpeoEpeoSkillSetaEntity> getEpeo_skill_set() {
        return epeo_skill_set;
    }

    public List<EpeoEpeoWorkSetaEntity> loadEpeo_work_set() {
        String propertyName = "epeo_work_set";
        String rootClass = "";
        return (List<EpeoEpeoWorkSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEpeo_work_set(List<EpeoEpeoWorkSetaEntity> epeo_work_set) {
        this.epeo_work_set = epeo_work_set;
    }

    public List<EpeoEpeoWorkSetaEntity> getEpeo_work_set() {
        return epeo_work_set;
    }

    public String getEpeo_rgt_type() {
        return epeo_rgt_type;
    }

    public void setEpeo_rgt_type(String epeo_rgt_type) {
        this.epeo_rgt_type = epeo_rgt_type;
    }

    public WordlistEntity loadEpeo_rgt_type() {
        String propertyName = "epeo_rgt_type";
        return (WordlistEntity)EntityUtils.getPropertyObj(this, propertyName);
    }

    public String getEpeo_rxnbr() {
        return epeo_rxnbr;
    }

    public void setEpeo_rxnbr(String epeo_rxnbr) {
        this.epeo_rxnbr = epeo_rxnbr;
    }

    public String getEpeo_password() {
        return epeo_password;
    }

    public void setEpeo_password(String epeo_password) {
        this.epeo_password = epeo_password;
    }

    public String getEpeo_photourl() {
        return epeo_photourl;
    }

    public void setEpeo_photourl(String epeo_photourl) {
        this.epeo_photourl = epeo_photourl;
    }

    public List<EpeoEpeoEnteSetaEntity> loadEpeo_ente_set() {
        String propertyName = "epeo_ente_set";
        String rootClass = "";
        return (List<EpeoEpeoEnteSetaEntity>)EntityUtils.getPropertySetObjList(this, propertyName, rootClass);
    }

    public void setEpeo_ente_set(List<EpeoEpeoEnteSetaEntity> epeo_ente_set) {
        this.epeo_ente_set = epeo_ente_set;
    }

    public List<EpeoEpeoEnteSetaEntity> getEpeo_ente_set() {
        return epeo_ente_set;
    }



}