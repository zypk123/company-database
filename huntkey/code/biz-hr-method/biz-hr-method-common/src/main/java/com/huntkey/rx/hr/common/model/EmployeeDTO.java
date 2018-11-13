
package com.huntkey.rx.hr.common.model;

import com.alibaba.fastjson.annotation.JSONField;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

public class EmployeeDTO  extends EdmDTO{
	private static final long serialVersionUID = 1L;
	String id;
	@JSONField(name="remp_epeo_obj")
	String rempEpeoObj;//自然人对象
	@JSONField(name="remp_addr")
	String rempAddr;//现住址
	@JSONField(name="remp_assure")
	String rempAssure;//担保人
	@JSONField(name="remp_assu_rela")
	String rempAssuRela;//担保人关系
	@JSONField(name="remp_assu_tel")
	String rempAssuTel;//担保人电话
	@JSONField(name="remp_birth")
	String rempBirth;//出生日期
	@JSONField(name="remp_blood")
	String rempBlood;//血型
	@JSONField(name="remp_card_beg")
	String rempCardBeg;//证件生效日期
	@JSONField(name="remp_card_end")
	String rempCardEnd;//证件失效日期
	@JSONField(name="remp_card_id")
	String rempCardId;//身份证号
	@JSONField(name="remp_card_organ")
	String rempCardOrgan;//发证机关
	@JSONField(name="remp_dept")
	String rempDept;//所在部门
	@JSONField(name="mdep_name")
	String rempDeptName;//所在部门名称
	@JSONField(name="remp_gender")
	String rempGender;//性别
	@JSONField(name="remp_height")
	String rempHeight;//身高
	@JSONField(name="remp_home_addr")
	String rempHomeAddr;//家庭住址
	@JSONField(name="remp_htel")
	String rempHtel;//家庭电话
	@JSONField(name="remp_in_date")
	String rempInDate;//到职日期
	@JSONField(name="remp_lea_date")
	String rempLeaDate;//离职日期
	@JSONField(name="remp_lea_type")
	String rempLeaType;//离职类型
	@JSONField(name="remp_mail")
	String rempMail;//电子邮件
	@JSONField(name="remp_maried")
	String rempMaried;//婚否
	@JSONField(name="remp_mcop")
	String rempMcop;//法人公司
	@JSONField(name="rela_name")
	String rempMcopName;//法人公司名称
	@JSONField(name="remp_name")
	String rempName;//姓名
	@JSONField(name="remp_name_cn")
	String rempNameCn;//姓名拼音
	@JSONField(name="remp_name_en")
	String rempNameEn;//英文姓名
	@JSONField(name="remp_nation")
	String rempNation;//名族
	@JSONField(name="remp_no")
	String rempNo;//工号
	@JSONField(name="remp_pic_url")
	String rempPicUrl;//工作证照片
	@JSONField(name="remp_origin")
	String rempOrigin;//籍贯
	@JSONField(name="remp_party")
	String rempParty;//政治面貌
	@JSONField(name="remp_pgrade")
	String rempPgrade;//岗级
	@JSONField(name="remp_post")
	String rempPost;//岗位
	@JSONField(name="rpak_name")
	String rpakName;//园区名称
	@JSONField(name="rpos_name")
	String rempPostName;//岗位名称
	@JSONField(name="remp_post_date")
	String rempPostDate;//转正日
	@JSONField(name="remp_ref_comm")
	String rempRefComm;//荐才建言
	@JSONField(name="remp_ref_emp")
	String rempRefEmp;//推荐人
	@JSONField(name="remp_ref_name")
	String rempRefEmpName;//推荐人姓名
	@JSONField(name="remp_residence")
	String rempResidence;//户籍地址
	@JSONField(name="remp_rgt_pro")
	String rempRgtPro;//户籍所在地
	@JSONField(name="remp_rgt_type")
	String rempRgtType;//户籍类型
	@JSONField(name="remp_secu_no")
	String rempSecuNo;//社保号
	@JSONField(name="remp_secu_type")
	String rempSecuType;//社保类型
	@JSONField(name="remp_self_eval")
	String rempSelfEval;//自我评价
	@JSONField(name="remp_sgrade")
	String rempSgrade;//薪级
	@JSONField(name="remp_status")
	String rempStatus;//状态
	@JSONField(name="remp_tel")
	String rempTel;//联系电话
	@JSONField(name="remp_try_result")
	String rempTryResult;//试用结果
	@JSONField(name="remp_type")
	String rempType;//员工类型
	@JSONField(name="remp_weight")
	String rempWeight;//体重
	@JSONField(name="remp_work_date")
	String rempWorkDate;//首次工作日
	@JSONField(name="remp_post_set")
	List<EmpPostSetDTO> rempPostSet;//任岗经历
	@JSONField(name="remp_study_set")
	List<EmpStudyDTO> rempStudySet;//教育经历
	@JSONField(name="remp_skill_set")
	List<EmpSkillDTO> rempSkillSet;//专业技能
	@JSONField(name="remp_fam_set")
	List<EmpFamilyDTO> rempFamSet;//家庭成员
	@JSONField(name="remp_work_set")
	List<EmpWorkExpDTO> rempWorkSet;//工作经历

	//全部合同记录
	@JSONField(name = "remp_cont_set")
	List<ContractRecordDTO> rempContSet;

	//根据条件过滤后的合同记录
	List<ContractRecordDTO> resultContractRecords;

	@JSONField(name="remp_card_type")
	String rempCardType;//证件类型
	@JSONField(name="remp_cont")
	String rempCont;//紧急联络人
	@JSONField(name="remp_cont_tel")
	String rempContTel;//紧急联络方式
	@JSONField(name="remp_first_name")
	String rempFirstName;//姓
	@JSONField(name="remp_last_name")
	String rempLastName;//名
	//合同状态
	private String recondState;

	public String getRecondState() {
		return recondState;
	}

	public void setRecondState(String recondState) {
		this.recondState = recondState;
	}

	public String getCompanyAge() {
		return companyAge;
	}

	public void setCompanyAge(String companyAge) {
		this.companyAge = companyAge;
	}
	@JSONField(name="companyAge")
	String companyAge;//司龄
	public String getRempDeptName() {
		return rempDeptName;
	}

	public void setRempDeptName(String rempDeptName) {
		this.rempDeptName = rempDeptName;
	}

	public String getRempMcopName() {
		return rempMcopName;
	}

	public void setRempMcopName(String rempMcopName) {
		this.rempMcopName = rempMcopName;
	}

	public String getRempPostName() {
		return rempPostName;
	}

	public void setRempPostName(String rempPostName) {
		this.rempPostName = rempPostName;
	}

	public String getRempRefEmpName() {
		return rempRefEmpName;
	}

	public void setRempRefEmpName(String rempRefEmpName) {
		this.rempRefEmpName = rempRefEmpName;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<EmpPostSetDTO> getRempPostSet() {
		return rempPostSet;
	}
	public void setRempPostSet(List<EmpPostSetDTO> rempPostSet) {
		this.rempPostSet = rempPostSet;
	}
	public List<EmpStudyDTO> getRempStudySet() {
		return rempStudySet;
	}
	public void setRempStudySet(List<EmpStudyDTO> rempStudySet) {
		this.rempStudySet = rempStudySet;
	}
	public List<EmpSkillDTO> getRempSkillSet() {
		return rempSkillSet;
	}
	public void setRempSkillSet(List<EmpSkillDTO> rempSkillSet) {
		this.rempSkillSet = rempSkillSet;
	}
	public List<EmpFamilyDTO> getRempFamSet() {
		return rempFamSet;
	}
	public void setRempFamSet(List<EmpFamilyDTO> rempFamSet) {
		this.rempFamSet = rempFamSet;
	}
	public List<EmpWorkExpDTO> getRempWorkSet() {
		return rempWorkSet;
	}
	public void setRempWorkSet(List<EmpWorkExpDTO> rempWorkSet) {
		this.rempWorkSet = rempWorkSet;
	}
	public String getRempAddr() {
		return rempAddr;
	}
	public void setRempAddr(String rempAddr) {
		this.rempAddr = rempAddr;
	}
	public String getRempAssure() {
		return rempAssure;
	}
	public void setRempAssure(String rempAssure) {
		this.rempAssure = rempAssure;
	}
	public String getRempAssuRela() {
		return rempAssuRela;
	}
	public void setRempAssuRela(String rempAssuRela) {
		this.rempAssuRela = rempAssuRela;
	}
	public String getRempAssuTel() {
		return rempAssuTel;
	}
	public void setRempAssuTel(String rempAssuTel) {
		this.rempAssuTel = rempAssuTel;
	}
	public String getRempBirth() {
		return rempBirth;
	}
	public void setRempBirth(String rempBirth) {
		this.rempBirth = rempBirth;
	}
	public String getRempBlood() {
		return rempBlood;
	}
	public void setRempBlood(String rempBlood) {
		this.rempBlood = rempBlood;
	}
	public String getRempCardBeg() {
		return rempCardBeg;
	}
	public void setRempCardBeg(String rempCardBeg) {
		this.rempCardBeg = rempCardBeg;
	}
	public String getRempCardEnd() {
		return rempCardEnd;
	}
	public void setRempCardEnd(String rempCardEnd) {
		this.rempCardEnd = rempCardEnd;
	}
	public String getRempCardId() {
		return rempCardId;
	}
	public void setRempCardId(String rempCardId) {
		this.rempCardId = rempCardId;
	}
	public String getRempCardOrgan() {
		return rempCardOrgan;
	}
	public void setRempCardOrgan(String rempCardOrgan) {
		this.rempCardOrgan = rempCardOrgan;
	}
	public String getRempDept() {
		return rempDept;
	}
	public void setRempDept(String rempDept) {
		this.rempDept = rempDept;
	}
	public String getRempGender() {
		return rempGender;
	}
	public void setRempGender(String rempGender) {
		this.rempGender = rempGender;
	}
	public String getRempHeight() {
		return rempHeight;
	}
	public void setRempHeight(String rempHeight) {
		this.rempHeight = rempHeight;
	}
	public String getRempHomeAddr() {
		return rempHomeAddr;
	}
	public void setRempHomeAddr(String rempHomeAddr) {
		this.rempHomeAddr = rempHomeAddr;
	}
	public String getRempHtel() {
		return rempHtel;
	}
	public void setRempHtel(String rempHtel) {
		this.rempHtel = rempHtel;
	}
	public String getRempInDate() {
		return rempInDate;
	}
	public void setRempInDate(String rempInDate) {
		this.rempInDate = rempInDate;
	}
	public String getRempLeaDate() {
		return rempLeaDate;
	}
	public void setRempLeaDate(String rempLeaDate) {
		this.rempLeaDate = rempLeaDate;
	}
	public String getRempLeaType() {
		return rempLeaType;
	}
	public void setRempLeaType(String rempLeaType) {
		this.rempLeaType = rempLeaType;
	}
	public String getRempMail() {
		return rempMail;
	}
	public void setRempMail(String rempMail) {
		this.rempMail = rempMail;
	}
	public String getRempMaried() {
		return rempMaried;
	}
	public void setRempMaried(String rempMaried) {
		this.rempMaried = rempMaried;
	}
	public String getRempMcop() {
		return rempMcop;
	}
	public void setRempMcop(String rempMcop) {
		this.rempMcop = rempMcop;
	}
	public String getRempName() {
		return rempName;
	}
	public void setRempName(String rempName) {
		this.rempName = rempName;
	}
	public String getRempNameCn() {
		return rempNameCn;
	}
	public void setRempNameCn(String rempNameCn) {
		this.rempNameCn = rempNameCn;
	}
	public String getRempNameEn() {
		return rempNameEn;
	}
	public void setRempNameEn(String rempNameEn) {
		this.rempNameEn = rempNameEn;
	}
	public String getRempNation() {
		return rempNation;
	}
	public void setRempNation(String rempNation) {
		this.rempNation = rempNation;
	}
	public String getRempNo() {
		return rempNo;
	}
	public void setRempNo(String rempNo) {
		this.rempNo = rempNo;
	}
	public String getRempOrigin() {
		return rempOrigin;
	}
	public void setRempOrigin(String rempOrigin) {
		this.rempOrigin = rempOrigin;
	}
	public String getRempParty() {
		return rempParty;
	}
	public void setRempParty(String rempParty) {
		this.rempParty = rempParty;
	}
	public String getRempPgrade() {
		return rempPgrade;
	}
	public void setRempPgrade(String rempPgrade) {
		this.rempPgrade = rempPgrade;
	}
	public String getRempPost() {
		return rempPost;
	}
	public void setRempPost(String rempPost) {
		this.rempPost = rempPost;
	}
	public String getRempPostDate() {
		return rempPostDate;
	}
	public void setRempPostDate(String rempPostDate) {
		this.rempPostDate = rempPostDate;
	}
	public String getRempRefComm() {
		return rempRefComm;
	}
	public void setRempRefComm(String rempRefComm) {
		this.rempRefComm = rempRefComm;
	}
	public String getRempRefEmp() {
		return rempRefEmp;
	}
	public void setRempRefEmp(String rempRefEmp) {
		this.rempRefEmp = rempRefEmp;
	}
	public String getRempResidence() {
		return rempResidence;
	}
	public void setRempResidence(String rempResidence) {
		this.rempResidence = rempResidence;
	}
	public String getRempRgtPro() {
		return rempRgtPro;
	}
	public void setRempRgtPro(String rempRgtPro) {
		this.rempRgtPro = rempRgtPro;
	}
	public String getRempSecuNo() {
		return rempSecuNo;
	}
	public void setRempSecuNo(String rempSecuNo) {
		this.rempSecuNo = rempSecuNo;
	}
	public String getRempSecuType() {
		return rempSecuType;
	}
	public void setRempSecuType(String rempSecuType) {
		this.rempSecuType = rempSecuType;
	}
	public String getRempSelfEval() {
		return rempSelfEval;
	}
	public void setRempSelfEval(String rempSelfEval) {
		this.rempSelfEval = rempSelfEval;
	}
	public String getRempSgrade() {
		return rempSgrade;
	}
	public void setRempSgrade(String rempSgrade) {
		this.rempSgrade = rempSgrade;
	}
	public String getRempStatus() {
		return rempStatus;
	}
	public void setRempStatus(String rempStatus) {
		this.rempStatus = rempStatus;
	}
	public String getRempTel() {
		return rempTel;
	}
	public void setRempTel(String rempTel) {
		this.rempTel = rempTel;
	}
	public String getRempTryResult() {
		return rempTryResult;
	}
	public void setRempTryResult(String rempTryResult) {
		this.rempTryResult = rempTryResult;
	}
	public String getRempType() {
		return rempType;
	}
	public void setRempType(String rempType) {
		this.rempType = rempType;
	}
	public String getRempWeight() {
		return rempWeight;
	}
	public void setRempWeight(String rempWeight) {
		this.rempWeight = rempWeight;
	}
	public String getRempWorkDate() {
		return rempWorkDate;
	}
	public void setRempWorkDate(String rempWorkDate) {
		this.rempWorkDate = rempWorkDate;
	}
	public String getRempPicUrl() {
		return rempPicUrl;
	}

	public void setRempPicUrl(String rempPicUrl) {
		this.rempPicUrl = rempPicUrl;
	}
	public String getRempRgtType() {
		return rempRgtType;
	}

	public void setRempRgtType(String rempRgtType) {
		this.rempRgtType = rempRgtType;
	}
	public String getRpakName() {
		return rpakName;
	}

	public void setRpakName(String rpakName) {
		this.rpakName = rpakName;
	}

	public List<ContractRecordDTO> getRempContSet() {
		return rempContSet;
	}

	public void setRempContSet(List<ContractRecordDTO> rempContSet) {
		this.rempContSet = rempContSet;
	}

	public List<ContractRecordDTO> getResultContractRecords() {
		return resultContractRecords;
	}

	public void setResultContractRecords(List<ContractRecordDTO> resultContractRecords) {
		this.resultContractRecords = resultContractRecords;
	}

	public String getRempEpeoObj() {
		return rempEpeoObj;
	}

	public void setRempEpeoObj(String rempEpeoObj) {
		this.rempEpeoObj = rempEpeoObj;
	}

	public String getRempCardType() {
		return rempCardType;
	}

	public void setRempCardType(String rempCardType) {
		this.rempCardType = rempCardType;
	}

	public String getRempCont() {
		return rempCont;
	}

	public void setRempCont(String rempCont) {
		this.rempCont = rempCont;
	}

	public String getRempContTel() {
		return rempContTel;
	}

	public void setRempContTel(String rempContTel) {
		this.rempContTel = rempContTel;
	}

	public String getRempFirstName() {
		return rempFirstName;
	}

	public void setRempFirstName(String rempFirstName) {
		this.rempFirstName = rempFirstName;
	}

	public String getRempLastName() {
		return rempLastName;
	}

	public void setRempLastName(String rempLastName) {
		this.rempLastName = rempLastName;
	}
}

