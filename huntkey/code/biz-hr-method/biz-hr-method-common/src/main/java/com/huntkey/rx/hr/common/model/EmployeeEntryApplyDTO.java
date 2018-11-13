package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;

import java.util.List;

/**
 * Created by weijian on 2017/11/17.
 */
public class EmployeeEntryApplyDTO extends OrderDTO {
    /**
     * ID
     */
    @JSONField(name = "id")
    String id;
    /**
     * 工号
     */
    @JSONField(name = "oeeo_code")
    String oeeoCode;
    /**
     * 姓名
     */
    @JSONField(name = "oeeo_name")
    String oeeoName;
    /**
     * 自然人
     */
    @JSONField(name = "oeeo_epeo")
    String oeeoEpeo;

    /**
     * 姓名拼音
     */
    @JSONField(name = "oeeo_name_cn")
    String oeeoNameCn;
    /**
     * 英文名
     */
    @JSONField(name = "oeeo_name_en")
    String oeeoNameEn;
    /**
     * 性别
     */
    @JSONField(name = "oeeo_gender")
    String oeeoGender;

    /**
     * 证件类型
     */
    @JSONField(name = "oeeo_card_type")
    String oeeoCardType;
    /**
     * 证件号
     */
    @JSONField(name = "oeeo_card_no")
    String oeeoCardNo;
    /**
     * 签发机关
     */
    @JSONField(name = "oeeo_card_org")
    String oeeoCardOrg;
    /**
     * 证件生效日期
     */
    @JSONField(name = "oeeo_card_beg")
    String oeeoCardBeg;
    /**
     * 证件失效日期
     */
    @JSONField(name = "oeeo_card_end")
    String oeeoCardEnd;
    /**
     * 出生日期
     */
    @JSONField(name = "oeeo_birth")
    String oeeoBirth;
    /**
     * 籍贯
     */
    @JSONField(name = "oeeo_origin")
    String oeeoOrigin;
    /**
     * 户籍地
     */
    @JSONField(name = "oeeo_rgt_pro")
    String oeeoRgtPro;
    /**
     * 户籍地址
     */
    @JSONField(name = "oeeo_residence")
    String oeeoResidence;
    /**
     * 现居省地址
     */
    @JSONField(name = "oeeo_addrp")
    String oeeoAddrp;
    /**
     * 现居市地址
     */
    @JSONField(name = "oeeo_addrc")
    String oeeoAddrc;
    /**
     * 现居区地址
     */
    @JSONField(name = "oeeo_addrl")
    String oeeoAddrl;
    /**
     * 现住址
     */
    @JSONField(name = "oeeo_addr")
    String oeeoAddr;
    /**
     * 家庭住址
     */
    @JSONField(name = "oeeo_home_addr")
    String oeeoHomeAddr;
    /**
     * 民族
     */
    @JSONField(name = "oeeo_nation")
    String oeeoNation;
    /**
     * 婚否
     */
    @JSONField(name = "oeeo_maried")
    String oeeoMaried;
    /**
     * 政治面貌
     */
    @JSONField(name = "oeeo_party")
    String oeeoParty;
    /**
     * 身高
     */
    @JSONField(name = "oeeo_height")
    String oeeoHeight;
    /**
     * 体重
     */
    @JSONField(name = "oeeo_weight")
    String oeeoWeight;
    /**
     * 血型
     */
    @JSONField(name = "oeeo_blood")
    String oeeoBlood;
    /**
     * 手机号码
     */
    @JSONField(name = "oeeo_tel")
    String oeeoTel;
    /**
     * E-mail
     */
    @JSONField(name = "oeeo_mail")
    String oeeoMail;
    /**
     * 家庭电话
     */
    @JSONField(name = "oeeo_htel")
    String oeeoHtel;
    /**
     * 紧急联系人
     */
    @JSONField(name = "oeeo_cont")
    String oeeoCont;
    /**
     * 紧急联系人电话
     */
    @JSONField(name = "oeeo_cont_tel")
    String oeeoContTel;
    /**
     * 首次工作时间
     */
    @JSONField(name = "oeeo_work_date")
    String oeeoWorkDate;
    /**
     * 担保人
     */
    @JSONField(name = "oeeo_assure")
    String oeeoAssure;
    /**
     * 担保人关系
     */
    @JSONField(name = "oeeo_assu_rela")
    String oeeoAssuRela;
    /**
     * 担保人电话
     */
    @JSONField(name = "oeeo_assu_tel")
    String oeeoAssuTel;
    /**
     * 介绍人
     */
    @JSONField(name = "oeeo_intr")
    String oeeoIntr;
    /**
     * 社保类型
     */
    @JSONField(name = "oeeo_secu_type")
    String oeeoSecuType;
    /**
     * 社保电脑号
     */
    @JSONField(name = "oeeo_secu_no")
    String oeeoSecuNo;
    /**
     * 任职部门
     */
    @JSONField(name = "oeeo_dept")
    String oeeoDept;
    /**
     * 任职岗位
     */
    @JSONField(name = "oeeo_post")
    String oeeoPost;
    /**
     * 试用期薪级
     */
    @JSONField(name = "oeeo_try_sgrade")
    String oeeoTrySgrade;
    /**
     * 入职日期
     */
    @JSONField(name = "oeeo_en_date")
    String oeeoEnDate;
    /**
     * 试用月数
     */
    @JSONField(name = "oeeo_try_mon")
    String oeeoTryMon;
    /**
     * 试用期开始日
     */
    @JSONField(name = "oeeo_try_beg")
    String oeeoTryBeg;
    /**
     * 试用期结束日
     */
    @JSONField(name = "oeeo_try_end")
    String oeeoTryEnd;
    /**
     * 转正日期
     */
    @JSONField(name = "oeeo_post_date")
    String oeeoPostDate;
    /**
     * 指引人
     */
    @JSONField(name = "oeeo_guide")
    String oeeoGuide;
    /**
     * 自我评价
     */
    @JSONField(name = "oeeo_self_eval")
    String oeeoSelfEval;
    /**
     * 单据类型
     */
    @JSONField(name = "oeeo_type")
    String oeeoType;
    /**
     * 任职岗级
     */
    @JSONField(name = "oeeo_pgrade")
    String oeeoPgrade;


    @JSONField(name = "oeeo_rgt_type")
    String oeeoRgtType;//户籍类型

    @JSONField(name = "oeeo_type_enum")
    String oeeoTypeEnum;//单据类型

    @JSONField(name = "oeeo_emp_type")
    String oeeoEmpType; //员工类型

    @JSONField(name = "oeeo_num_tot")
    String oeeoNumTot; //入职人数

    @JSONField(name = "oeeo_pic_url")
    String oeeoPicUrl; //工作证照片

    String oeeoPicUrlNew; //工作证照片带服务器地址

    @JSONField(name = "oeeo_study_set")
    List<OeeoStudySetDTO> oeeoStudySet;//教育经历

    @JSONField(name = "oeeo_skill_set")
    List<OeeoSkillSetDTO> oeeoSkillSet;//专业技能


    @JSONField(name = "oeeo_work_set")
    List<OeeoWorkSetDTO> oeeoWorkSet;//工作经历

    @JSONField(name = "oeeo_fam_set")
    List<OeeoFamSetDTO> oeeoFamSet;//家庭成员

    String oeeoDeptName;//oeeoDeptName 部门名称
    String oeeoPostName;//oeeoPostName 岗位名称
    String oeeoMcopName;//oeeoMcopName 法人公司
    String campanyAge;//CampanyAge 司龄
    String rpakName;// rpakName 办公园区
    String oeeoIntrName;//介绍人
    String oeeoGuideName;//指引人名称
    String oeeoPgrad;//岗级
    String oeeoPpostName;//汇报上级名称

    @JSONField(name = "oeeo_first_name")
    String oeeoFirstName;//姓
    @JSONField(name = "oeeo_last_name")
    String oeeoLastName;//名

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOeeoCode() {
        return oeeoCode;
    }

    public void setOeeoCode(String oeeoCode) {
        this.oeeoCode = oeeoCode;
    }

    public String getOeeoName() {
        return oeeoName;
    }

    public String getOeeoEpeo() {
        return oeeoEpeo;
    }

    public void setOeeoEpeo(String oeeoEpeo) {
        this.oeeoEpeo = oeeoEpeo;
    }

    public void setOeeoName(String oeeoName) {
        this.oeeoName = oeeoName;
    }

    public String getOeeoNameCn() {
        return oeeoNameCn;
    }

    public void setOeeoNameCn(String oeeoNameCn) {
        this.oeeoNameCn = oeeoNameCn;
    }

    public String getOeeoNameEn() {
        return oeeoNameEn;
    }

    public void setOeeoNameEn(String oeeoNameEn) {
        this.oeeoNameEn = oeeoNameEn;
    }

    public String getOeeoGender() {
        return oeeoGender;
    }

    public void setOeeoGender(String oeeoGender) {
        this.oeeoGender = oeeoGender;
    }

    public String getOeeoCardType() {
        return oeeoCardType;
    }

    public void setOeeoCardType(String oeeoCardType) {
        this.oeeoCardType = oeeoCardType;
    }

    public String getOeeoCardNo() {
        return oeeoCardNo;
    }

    public void setOeeoCardNo(String oeeoCardNo) {
        this.oeeoCardNo = oeeoCardNo;
    }

    public String getOeeoCardOrg() {
        return oeeoCardOrg;
    }

    public void setOeeoCardOrg(String oeeoCardOrg) {
        this.oeeoCardOrg = oeeoCardOrg;
    }

    public String getOeeoCardBeg() {
        return oeeoCardBeg;
    }

    public void setOeeoCardBeg(String oeeoCardBeg) {
        this.oeeoCardBeg = oeeoCardBeg;
    }

    public String getOeeoCardEnd() {
        return oeeoCardEnd;
    }

    public void setOeeoCardEnd(String oeeoCardEnd) {
        this.oeeoCardEnd = oeeoCardEnd;
    }

    public String getOeeoBirth() {
        return oeeoBirth;
    }

    public void setOeeoBirth(String oeeoBirth) {
        this.oeeoBirth = oeeoBirth;
    }

    public String getOeeoOrigin() {
        return oeeoOrigin;
    }

    public void setOeeoOrigin(String oeeoOrigin) {
        this.oeeoOrigin = oeeoOrigin;
    }

    public String getOeeoRgtPro() {
        return oeeoRgtPro;
    }

    public void setOeeoRgtPro(String oeeoRgtPro) {
        this.oeeoRgtPro = oeeoRgtPro;
    }

    public String getOeeoResidence() {
        return oeeoResidence;
    }

    public void setOeeoResidence(String oeeoResidence) {
        this.oeeoResidence = oeeoResidence;
    }

    public String getOeeoAddrp() {
        return oeeoAddrp;
    }

    public void setOeeoAddrp(String oeeoAddrp) {
        this.oeeoAddrp = oeeoAddrp;
    }

    public String getOeeoAddrc() {
        return oeeoAddrc;
    }

    public void setOeeoAddrc(String oeeoAddrc) {
        this.oeeoAddrc = oeeoAddrc;
    }

    public String getOeeoAddrl() {
        return oeeoAddrl;
    }

    public void setOeeoAddrl(String oeeoAddrl) {
        this.oeeoAddrl = oeeoAddrl;
    }

    public String getOeeoAddr() {
        return oeeoAddr;
    }

    public void setOeeoAddr(String oeeoAddr) {
        this.oeeoAddr = oeeoAddr;
    }

    public String getOeeoHomeAddr() {
        return oeeoHomeAddr;
    }

    public void setOeeoHomeAddr(String oeeoHomeAddr) {
        this.oeeoHomeAddr = oeeoHomeAddr;
    }

    public String getOeeoNation() {
        return oeeoNation;
    }

    public void setOeeoNation(String oeeoNation) {
        this.oeeoNation = oeeoNation;
    }

    public String getOeeoMaried() {
        return oeeoMaried;
    }

    public void setOeeoMaried(String oeeoMaried) {
        this.oeeoMaried = oeeoMaried;
    }

    public String getOeeoParty() {
        return oeeoParty;
    }

    public void setOeeoParty(String oeeoParty) {
        this.oeeoParty = oeeoParty;
    }

    public String getOeeoHeight() {
        return oeeoHeight;
    }

    public void setOeeoHeight(String oeeoHeight) {
        this.oeeoHeight = oeeoHeight;
    }

    public String getOeeoWeight() {
        return oeeoWeight;
    }

    public void setOeeoWeight(String oeeoWeight) {
        this.oeeoWeight = oeeoWeight;
    }

    public String getOeeoBlood() {
        return oeeoBlood;
    }

    public void setOeeoBlood(String oeeoBlood) {
        this.oeeoBlood = oeeoBlood;
    }

    public String getOeeoTel() {
        return oeeoTel;
    }

    public void setOeeoTel(String oeeoTel) {
        this.oeeoTel = oeeoTel;
    }

    public String getOeeoMail() {
        return oeeoMail;
    }

    public void setOeeoMail(String oeeoMail) {
        this.oeeoMail = oeeoMail;
    }

    public String getOeeoHtel() {
        return oeeoHtel;
    }

    public void setOeeoHtel(String oeeoHtel) {
        this.oeeoHtel = oeeoHtel;
    }

    public String getOeeoCont() {
        return oeeoCont;
    }

    public void setOeeoCont(String oeeoCont) {
        this.oeeoCont = oeeoCont;
    }

    public String getOeeoContTel() {
        return oeeoContTel;
    }

    public void setOeeoContTel(String oeeoContTel) {
        this.oeeoContTel = oeeoContTel;
    }

    public String getOeeoWorkDate() {
        return oeeoWorkDate;
    }

    public void setOeeoWorkDate(String oeeoWorkDate) {
        this.oeeoWorkDate = oeeoWorkDate;
    }

    public String getOeeoAssure() {
        return oeeoAssure;
    }

    public void setOeeoAssure(String oeeoAssure) {
        this.oeeoAssure = oeeoAssure;
    }

    public String getOeeoAssuRela() {
        return oeeoAssuRela;
    }

    public void setOeeoAssuRela(String oeeoAssuRela) {
        this.oeeoAssuRela = oeeoAssuRela;
    }

    public String getOeeoAssuTel() {
        return oeeoAssuTel;
    }

    public void setOeeoAssuTel(String oeeoAssuTel) {
        this.oeeoAssuTel = oeeoAssuTel;
    }

    public String getOeeoIntr() {
        return oeeoIntr;
    }

    public void setOeeoIntr(String oeeoIntr) {
        this.oeeoIntr = oeeoIntr;
    }

    public String getOeeoSecuType() {
        return oeeoSecuType;
    }

    public void setOeeoSecuType(String oeeoSecuType) {
        this.oeeoSecuType = oeeoSecuType;
    }

    public String getOeeoSecuNo() {
        return oeeoSecuNo;
    }

    public void setOeeoSecuNo(String oeeoSecuNo) {
        this.oeeoSecuNo = oeeoSecuNo;
    }

    public String getOeeoDept() {
        return oeeoDept;
    }

    public void setOeeoDept(String oeeoDept) {
        this.oeeoDept = oeeoDept;
    }

    public String getOeeoPost() {
        return oeeoPost;
    }

    public void setOeeoPost(String oeeoPost) {
        this.oeeoPost = oeeoPost;
    }

    public String getOeeoTrySgrade() {
        return oeeoTrySgrade;
    }

    public void setOeeoTrySgrade(String oeeoTrySgrade) {
        this.oeeoTrySgrade = oeeoTrySgrade;
    }

    public String getOeeoEnDate() {
        return oeeoEnDate;
    }

    public void setOeeoEnDate(String oeeoEnDate) {
        this.oeeoEnDate = oeeoEnDate;
    }

    public String getOeeoTryMon() {
        return oeeoTryMon;
    }

    public void setOeeoTryMon(String oeeoTryMon) {
        this.oeeoTryMon = oeeoTryMon;
    }

    public String getOeeoTryBeg() {
        return oeeoTryBeg;
    }

    public void setOeeoTryBeg(String oeeoTryBeg) {
        this.oeeoTryBeg = oeeoTryBeg;
    }

    public String getOeeoTryEnd() {
        return oeeoTryEnd;
    }

    public void setOeeoTryEnd(String oeeoTryEnd) {
        this.oeeoTryEnd = oeeoTryEnd;
    }

    public String getOeeoPostDate() {
        return oeeoPostDate;
    }

    public void setOeeoPostDate(String oeeoPostDate) {
        this.oeeoPostDate = oeeoPostDate;
    }

    public String getOeeoGuide() {
        return oeeoGuide;
    }

    public void setOeeoGuide(String oeeoGuide) {
        this.oeeoGuide = oeeoGuide;
    }

    public String getOeeoSelfEval() {
        return oeeoSelfEval;
    }

    public void setOeeoSelfEval(String oeeoSelfEval) {
        this.oeeoSelfEval = oeeoSelfEval;
    }

    public String getOeeoType() {
        return oeeoType;
    }

    public void setOeeoType(String oeeoType) {
        this.oeeoType = oeeoType;
    }

    public String getOeeoPgrade() {
        return oeeoPgrade;
    }

    public void setOeeoPgrade(String oeeoPgrade) {
        this.oeeoPgrade = oeeoPgrade;
    }

    public String getOeeoRgtType() {
        return oeeoRgtType;
    }

    public void setOeeoRgtType(String oeeoRgtType) {
        this.oeeoRgtType = oeeoRgtType;
    }

    public String getOeeoTypeEnum() {
        return oeeoTypeEnum;
    }

    public void setOeeoTypeEnum(String oeeoTypeEnum) {
        this.oeeoTypeEnum = oeeoTypeEnum;
    }

    public String getOeeoEmpType() {
        return oeeoEmpType;
    }

    public void setOeeoEmpType(String oeeoEmpType) {
        this.oeeoEmpType = oeeoEmpType;
    }

    public String getOeeoNumTot() {
        return oeeoNumTot;
    }

    public void setOeeoNumTot(String oeeoNumTot) {
        this.oeeoNumTot = oeeoNumTot;
    }

    public String getOeeoPicUrl() {
        return oeeoPicUrl;
    }

    public void setOeeoPicUrl(String oeeoPicUrl) {
        this.oeeoPicUrl = oeeoPicUrl;
    }

    public List<OeeoStudySetDTO> getOeeoStudySet() {
        return oeeoStudySet;
    }

    public void setOeeoStudySet(List<OeeoStudySetDTO> oeeoStudySet) {
        this.oeeoStudySet = oeeoStudySet;
    }

    public List<OeeoSkillSetDTO> getOeeoSkillSet() {
        return oeeoSkillSet;
    }

    public void setOeeoSkillSet(List<OeeoSkillSetDTO> oeeoSkillSet) {
        this.oeeoSkillSet = oeeoSkillSet;
    }

    public List<OeeoWorkSetDTO> getOeeoWorkSet() {
        return oeeoWorkSet;
    }

    public void setOeeoWorkSet(List<OeeoWorkSetDTO> oeeoWorkSet) {
        this.oeeoWorkSet = oeeoWorkSet;
    }

    public List<OeeoFamSetDTO> getOeeoFamSet() {
        return oeeoFamSet;
    }

    public void setOeeoFamSet(List<OeeoFamSetDTO> oeeoFamSet) {
        this.oeeoFamSet = oeeoFamSet;
    }

    public String getOeeoDeptName() {
        return oeeoDeptName;
    }

    public void setOeeoDeptName(String oeeoDeptName) {
        this.oeeoDeptName = oeeoDeptName;
    }

    public String getOeeoPostName() {
        return oeeoPostName;
    }

    public void setOeeoPostName(String oeeoPostName) {
        this.oeeoPostName = oeeoPostName;
    }

    public String getOeeoMcopName() {
        return oeeoMcopName;
    }

    public void setOeeoMcopName(String oeeoMcopName) {
        this.oeeoMcopName = oeeoMcopName;
    }

    public String getCampanyAge() {
        return campanyAge;
    }

    public void setCampanyAge(String campanyAge) {
        this.campanyAge = campanyAge;
    }

    public String getRpakName() {
        return rpakName;
    }

    public void setRpakName(String rpakName) {
        this.rpakName = rpakName;
    }

    public String getOeeoIntrName() {
        return oeeoIntrName;
    }

    public void setOeeoIntrName(String oeeoIntrName) {
        this.oeeoIntrName = oeeoIntrName;
    }

    public String getOeeoGuideName() {
        return oeeoGuideName;
    }

    public void setOeeoGuideName(String oeeoGuideName) {
        this.oeeoGuideName = oeeoGuideName;
    }

    public String getOeeoFirstName() {
        return oeeoFirstName;
    }

    public void setOeeoFirstName(String oeeoFirstName) {
        this.oeeoFirstName = oeeoFirstName;
    }

    public String getOeeoLastName() {
        return oeeoLastName;
    }

    public void setOeeoLastName(String oeeoLastName) {
        this.oeeoLastName = oeeoLastName;
    }

    public String getOeeoPpostName() {
        return oeeoPpostName;
    }

    public void setOeeoPpostName(String oeeoPpostName) {
        this.oeeoPpostName = oeeoPpostName;
    }

    public String getOeeoPicUrlNew() {
        return oeeoPicUrlNew;
    }

    public void setOeeoPicUrlNew(String oeeoPicUrlNew) {
        this.oeeoPicUrlNew = oeeoPicUrlNew;
    }

    public String getOeeoPgrad() {
        return oeeoPgrad;
    }

    public void setOeeoPgrad(String oeeoPgrad) {
        this.oeeoPgrad = oeeoPgrad;
    }
}
