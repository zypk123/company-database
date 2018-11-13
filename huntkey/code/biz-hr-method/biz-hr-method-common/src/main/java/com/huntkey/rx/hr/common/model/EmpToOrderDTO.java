
package com.huntkey.rx.hr.common.model;

import java.io.Serializable;
import java.util.List;

import com.alibaba.fastjson.annotation.JSONField;

public class EmpToOrderDTO implements Serializable {
    private static final long serialVersionUID = 1L;
    @JSONField(name = "remp_addr")
    String oeeoAddr;//现住址
    @JSONField(name = "remp_assure")
    String oeeoAssure;//担保人
    @JSONField(name = "remp_assu_rela")
    String oeeoAssuRela;//担保人关系
    @JSONField(name = "remp_assu_tel")
    String oeeoAssuTel;//担保人电话
    @JSONField(name = "remp_birth")
    String oeeoBirth;//出生日期
    @JSONField(name = "remp_blood")
    String oeeoBlood;//血型
    @JSONField(name = "remp_card_beg")
    String oeeoCardBeg;//证件生效日期
    @JSONField(name = "remp_card_end")
    String oeeoCardEnd;//证件失效日期
    @JSONField(name = "remp_card_type")
    String oeeoCardType;//证件类型
    @JSONField(name = "remp_card_id")
    String oeeoCardNo;//身份证号
    @JSONField(name = "remp_card_organ")
    String oeeoCardOrg;//发证机关
    @JSONField(name = "remp_dept")
    String oeeoDept;//所在部门
    @JSONField(name = "rempDeptName")
    String oeeoDeptName  ;//所在部门名称  
    @JSONField(name = "remp_gender")
    String oeeoGender;//性别
    @JSONField(name = "remp_height")
    String oeeoHeight;//身高
    @JSONField(name = "remp_home_addr")
    String oeeoHomeAddr;//家庭住址
    @JSONField(name = "remp_htel")
    String oeeoHtel;//家庭电话
    @JSONField(name = "remp_in_date")
    String oeeoEnDate;//到职日期
    @JSONField(name = "remp_mail")
    String oeeoMail;//电子邮件
    @JSONField(name = "remp_maried")
    String oeeoMaried;//婚否
    @JSONField(name = "rempMcopName")
    String oeeoMcopName;//法人公司名称
    @JSONField(name = "remp_name")
    String oeeoName;//姓名
    @JSONField(name = "remp_name_cn")
    String oeeoNameCn;//姓名拼音
    @JSONField(name = "remp_name_en")
    String oeeoNameEn;//英文姓名
    @JSONField(name = "remp_nation")
    String oeeoNation;//名族
    @JSONField(name = "remp_no")
    String oeeoCode;//工号
    @JSONField(name = "remp_pic_url")
    String oeeoPicUrl;//工作证照片
    String oeeoPicUrlNew;//工作证照片带服务器地址
    @JSONField(name = "remp_origin")
    String oeeoOrigin;//籍贯
    @JSONField(name = "remp_party")
    String oeeoParty;//政治面貌
    @JSONField(name = "remp_pgrade")
    String oeeoPgrade;//岗级
    @JSONField(name = "remp_post")
    String oeeoPost;//岗位
    @JSONField(name = "rpakName")
    String rpakName;//园区名称
    @JSONField(name = "companyYears")
    String campanyAge;//司龄
    @JSONField(name = "rempPostName")
    String oeeoPostName;//岗位名称
    @JSONField(name = "remp_ref_emp")
    String oeeoIntr;//介绍人
    @JSONField(name = "rempRefName")
    String oeeoIntrName;//介绍人姓名
    @JSONField(name = "remp_residence")
    String oeeoResidence;//户籍地址
    @JSONField(name = "remp_rgt_pro")
    String oeeoRgtPro;//户籍所在地
    @JSONField(name = "remp_rgt_type")
    String oeeoRgtType;//户籍类型
    @JSONField(name = "remp_secu_no")
    String oeeoSecuNo;//社保号
    @JSONField(name = "remp_secu_type")
    String oeeoSecuType;//社保类型
    @JSONField(name = "remp_self_eval")
    String oeeoSelfEval;//自我评价
    @JSONField(name = "remp_tel")
    String oeeoTel;//联系电话
    @JSONField(name = "remp_type")
    String oeeoEmpType;//员工类型
    @JSONField(name = "remp_weight")
    String oeeoWeight;//体重
    @JSONField(name = "remp_work_date")
    String oeeoWorkDate;//首次工作日
    @JSONField(name = "remp_study_set")
    List<EmpStudyToOrderDTO> oeeoStudySet;//教育经历
    @JSONField(name = "remp_skill_set")
    List<EmpSkillToOrderDTO> oeeoSkillSet;//专业技能
    @JSONField(name = "remp_fam_set")
    List<EmpFamToOrderDTO> oeeoFamSet;//家庭成员
    @JSONField(name = "remp_work_set")
    List<EmpWorkToOrderDTO> oeeoWorkSet;//工作经历

    @JSONField(name = "remp_cont")
    String oeeoCont;//紧急联系人
    @JSONField(name = "remp_cont_tel")
    String oeeoContTel;//紧急联系方式
    @JSONField(name = "remp_first_name")
    String oeeoFirstName;//姓
    @JSONField(name = "remp_epeo_obj")
    String oeeoEpeo;//自然人

    String oeeoPpostName;//汇报上级
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

	@JSONField(name = "remp_last_name")
    String oeeoLastName;//名

    public String getOeeoAddr() {
        return oeeoAddr;
    }

    public void setOeeoAddr(String oeeoAddr) {
        this.oeeoAddr = oeeoAddr;
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

    public String getOeeoBirth() {
        return oeeoBirth;
    }

    public void setOeeoBirth(String oeeoBirth) {
        this.oeeoBirth = oeeoBirth;
    }

    public String getOeeoBlood() {
        return oeeoBlood;
    }

    public void setOeeoBlood(String oeeoBlood) {
        this.oeeoBlood = oeeoBlood;
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

    public String getOeeoDept() {
        return oeeoDept;
    }

    public void setOeeoDept(String oeeoDept) {
        this.oeeoDept = oeeoDept;
    }

    public String getOeeoDeptName() {
        return oeeoDeptName;
    }

    public void setOeeoDeptName(String oeeoDeptName) {
        this.oeeoDeptName = oeeoDeptName;
    }

    public String getOeeoGender() {
        return oeeoGender;
    }

    public void setOeeoGender(String oeeoGender) {
        this.oeeoGender = oeeoGender;
    }

    public String getOeeoHeight() {
        return oeeoHeight;
    }

    public void setOeeoHeight(String oeeoHeight) {
        this.oeeoHeight = oeeoHeight;
    }

    public String getOeeoHomeAddr() {
        return oeeoHomeAddr;
    }

    public void setOeeoHomeAddr(String oeeoHomeAddr) {
        this.oeeoHomeAddr = oeeoHomeAddr;
    }

    public String getOeeoHtel() {
        return oeeoHtel;
    }

    public void setOeeoHtel(String oeeoHtel) {
        this.oeeoHtel = oeeoHtel;
    }

    public String getOeeoEnDate() {
        return oeeoEnDate;
    }

    public void setOeeoEnDate(String oeeoEnDate) {
        this.oeeoEnDate = oeeoEnDate;
    }

    public String getOeeoMail() {
        return oeeoMail;
    }

    public void setOeeoMail(String oeeoMail) {
        this.oeeoMail = oeeoMail;
    }

    public String getOeeoMaried() {
        return oeeoMaried;
    }

    public void setOeeoMaried(String oeeoMaried) {
        this.oeeoMaried = oeeoMaried;
    }

    public String getOeeoMcopName() {
        return oeeoMcopName;
    }

    public void setOeeoMcopName(String oeeoMcopName) {
        this.oeeoMcopName = oeeoMcopName;
    }

    public String getOeeoName() {
        return oeeoName;
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

    public String getOeeoNation() {
        return oeeoNation;
    }

    public void setOeeoNation(String oeeoNation) {
        this.oeeoNation = oeeoNation;
    }

    public String getOeeoCode() {
        return oeeoCode;
    }

    public void setOeeoCode(String oeeoCode) {
        this.oeeoCode = oeeoCode;
    }

    public String getOeeoPicUrl() {
        return oeeoPicUrl;
    }

    public void setOeeoPicUrl(String oeeoPicUrl) {
        this.oeeoPicUrl = oeeoPicUrl;
    }

    public String getOeeoOrigin() {
        return oeeoOrigin;
    }

    public void setOeeoOrigin(String oeeoOrigin) {
        this.oeeoOrigin = oeeoOrigin;
    }

    public String getOeeoParty() {
        return oeeoParty;
    }

    public void setOeeoParty(String oeeoParty) {
        this.oeeoParty = oeeoParty;
    }

    public String getOeeoPgrade() {
        return oeeoPgrade;
    }

    public void setOeeoPgrade(String oeeoPgrade) {
        this.oeeoPgrade = oeeoPgrade;
    }

    public String getOeeoPost() {
        return oeeoPost;
    }

    public void setOeeoPost(String oeeoPost) {
        this.oeeoPost = oeeoPost;
    }

    public String getRpakName() {
        return rpakName;
    }

    public void setRpakName(String rpakName) {
        this.rpakName = rpakName;
    }

    public String getCampanyAge() {
        return campanyAge;
    }

    public void setCampanyAge(String campanyAge) {
        this.campanyAge = campanyAge;
    }

    public String getOeeoPostName() {
        return oeeoPostName;
    }

    public void setOeeoPostName(String oeeoPostName) {
        this.oeeoPostName = oeeoPostName;
    }

    public String getOeeoIntr() {
        return oeeoIntr;
    }

    public void setOeeoIntr(String oeeoIntr) {
        this.oeeoIntr = oeeoIntr;
    }

    public String getOeeoIntrName() {
        return oeeoIntrName;
    }

    public void setOeeoIntrName(String oeeoIntrName) {
        this.oeeoIntrName = oeeoIntrName;
    }

    public String getOeeoResidence() {
        return oeeoResidence;
    }

    public void setOeeoResidence(String oeeoResidence) {
        this.oeeoResidence = oeeoResidence;
    }

    public String getOeeoRgtPro() {
        return oeeoRgtPro;
    }

    public void setOeeoRgtPro(String oeeoRgtPro) {
        this.oeeoRgtPro = oeeoRgtPro;
    }

    public String getOeeoRgtType() {
        return oeeoRgtType;
    }

    public void setOeeoRgtType(String oeeoRgtType) {
        this.oeeoRgtType = oeeoRgtType;
    }

    public String getOeeoSecuNo() {
        return oeeoSecuNo;
    }

    public void setOeeoSecuNo(String oeeoSecuNo) {
        this.oeeoSecuNo = oeeoSecuNo;
    }

    public String getOeeoSecuType() {
        return oeeoSecuType;
    }

    public void setOeeoSecuType(String oeeoSecuType) {
        this.oeeoSecuType = oeeoSecuType;
    }

    public String getOeeoSelfEval() {
        return oeeoSelfEval;
    }

    public void setOeeoSelfEval(String oeeoSelfEval) {
        this.oeeoSelfEval = oeeoSelfEval;
    }

    public String getOeeoTel() {
        return oeeoTel;
    }

    public void setOeeoTel(String oeeoTel) {
        this.oeeoTel = oeeoTel;
    }

    public String getOeeoEmpType() {
        return oeeoEmpType;
    }

    public void setOeeoEmpType(String oeeoEmpType) {
        this.oeeoEmpType = oeeoEmpType;
    }

    public String getOeeoWeight() {
        return oeeoWeight;
    }

    public void setOeeoWeight(String oeeoWeight) {
        this.oeeoWeight = oeeoWeight;
    }

    public String getOeeoWorkDate() {
        return oeeoWorkDate;
    }

    public void setOeeoWorkDate(String oeeoWorkDate) {
        this.oeeoWorkDate = oeeoWorkDate;
    }

    public List<EmpStudyToOrderDTO> getOeeoStudySet() {
        return oeeoStudySet;
    }

    public void setOeeoStudySet(List<EmpStudyToOrderDTO> oeeoStudySet) {
        this.oeeoStudySet = oeeoStudySet;
    }

    public List<EmpSkillToOrderDTO> getOeeoSkillSet() {
        return oeeoSkillSet;
    }

    public void setOeeoSkillSet(List<EmpSkillToOrderDTO> oeeoSkillSet) {
        this.oeeoSkillSet = oeeoSkillSet;
    }

    public List<EmpFamToOrderDTO> getOeeoFamSet() {
        return oeeoFamSet;
    }

    public void setOeeoFamSet(List<EmpFamToOrderDTO> oeeoFamSet) {
        this.oeeoFamSet = oeeoFamSet;
    }

    public List<EmpWorkToOrderDTO> getOeeoWorkSet() {
        return oeeoWorkSet;
    }

    public void setOeeoWorkSet(List<EmpWorkToOrderDTO> oeeoWorkSet) {
        this.oeeoWorkSet = oeeoWorkSet;
    }

    public String getOeeoCardType() {
        return oeeoCardType;
    }

    public void setOeeoCardType(String oeeoCardType) {
        this.oeeoCardType = oeeoCardType;
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

    public String getOeeoEpeo() {
        return oeeoEpeo;
    }

    public void setOeeoEpeo(String oeeoEpeo) {
        this.oeeoEpeo = oeeoEpeo;
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
}

