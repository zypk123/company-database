package sync.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_sys_police",schema="itms3")
public class Police {
	
    private String policeId;

    private String orgId;

    private String policeNbr;

    private String policeType;

    private String policeCode;

    private String policeName;

    private String personId;

    private String authorizedType;

    private String eventLevel;

    private String leaderLevel;

    private Date birthDate;

    private String sex;

    private String policeRank;

    private String jobLevel;

    private String nativePlace;

    private String politicsStatus;

    private Date partyLeagueTime;

    private String race;

    private String education;

    private String major;

    private String position;

    private Date joinTime;

    private Date beginWork;

    private Date presentWork;

    private String familyAddress;

    private String pPhoneNbr;

    private String officePhone;

    private String homePhone;

    private String email;

    private String qualificationGrade;

    private String pRecordStatus;

    private Date pExpireDate;

    private String pEnableFlag;

    private String ssoFlag;

    private String createBy;

    private Date createTime;

    private String updateBy;

    private Date updateTime;

    private String businessPost;
    
    @Id
    @Column(name="police_id")
    public String getPoliceId() {
        return policeId;
    }

    public void setPoliceId(String policeId) {
        this.policeId = policeId;
    }

    @Column(name="org_id")
    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
    
    @Column(name="police_nbr")
    public String getPoliceNbr() {
        return policeNbr;
    }

    public void setPoliceNbr(String policeNbr) {
        this.policeNbr = policeNbr;
    }

    @Column(name="police_type")
    public String getPoliceType() {
        return policeType;
    }

    public void setPoliceType(String policeType) {
        this.policeType = policeType;
    }

    @Column(name="police_code")
    public String getPoliceCode() {
        return policeCode;
    }

    public void setPoliceCode(String policeCode) {
        this.policeCode = policeCode;
    }

    @Column(name="police_name")
    public String getPoliceName() {
        return policeName;
    }

    public void setPoliceName(String policeName) {
        this.policeName = policeName;
    }

    @Column(name="person_id")
    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Column(name="authorized_type")
    public String getAuthorizedType() {
        return authorizedType;
    }

    public void setAuthorizedType(String authorizedType) {
        this.authorizedType = authorizedType;
    }

    @Column(name="event_level")
    public String getEventLevel() {
        return eventLevel;
    }

    public void setEventLevel(String eventLevel) {
        this.eventLevel = eventLevel;
    }

    @Column(name="leader_level")
    public String getLeaderLevel() {
        return leaderLevel;
    }

    public void setLeaderLevel(String leaderLevel) {
        this.leaderLevel = leaderLevel;
    }

    @Column(name="birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name="sex")
    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    @Column(name="police_rank")
    public String getPoliceRank() {
        return policeRank;
    }

    public void setPoliceRank(String policeRank) {
        this.policeRank = policeRank;
    }

    @Column(name="job_level")
    public String getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(String jobLevel) {
        this.jobLevel = jobLevel;
    }

    @Column(name="native_place")
    public String getNativePlace() {
        return nativePlace;
    }

    public void setNativePlace(String nativePlace) {
        this.nativePlace = nativePlace;
    }

    @Column(name="politics_status")
    public String getPoliticsStatus() {
        return politicsStatus;
    }

    public void setPoliticsStatus(String politicsStatus) {
        this.politicsStatus = politicsStatus;
    }

    @Column(name="party_league_time")
    public Date getPartyLeagueTime() {
        return partyLeagueTime;
    }

    public void setPartyLeagueTime(Date partyLeagueTime) {
        this.partyLeagueTime = partyLeagueTime;
    }

    @Column(name="race")
    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    @Column(name="education")
    public String getEducation() {
        return education;
    }

    public void setEducation(String education) {
        this.education = education;
    }

    @Column(name="major")
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Column(name="position")
    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    @Column(name="join_time")
    public Date getJoinTime() {
        return joinTime;
    }

    public void setJoinTime(Date joinTime) {
        this.joinTime = joinTime;
    }

    @Column(name="begin_work")
    public Date getBeginWork() {
        return beginWork;
    }

    public void setBeginWork(Date beginWork) {
        this.beginWork = beginWork;
    }

    @Column(name="present_work")
    public Date getPresentWork() {
        return presentWork;
    }

    public void setPresentWork(Date presentWork) {
        this.presentWork = presentWork;
    }

    @Column(name="family_address")
    public String getFamilyAddress() {
        return familyAddress;
    }

    public void setFamilyAddress(String familyAddress) {
        this.familyAddress = familyAddress;
    }

    @Column(name="p_phone_nbr")
    public String getpPhoneNbr() {
        return pPhoneNbr;
    }

    public void setpPhoneNbr(String pPhoneNbr) {
        this.pPhoneNbr = pPhoneNbr;
    }

    @Column(name="office_phone")
    public String getOfficePhone() {
        return officePhone;
    }

    public void setOfficePhone(String officePhone) {
        this.officePhone = officePhone;
    }

    @Column(name="home_phone")
    public String getHomePhone() {
        return homePhone;
    }

    public void setHomePhone(String homePhone) {
        this.homePhone = homePhone;
    }
    
    @Column(name="email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name="qualification_grade")
    public String getQualificationGrade() {
        return qualificationGrade;
    }

    public void setQualificationGrade(String qualificationGrade) {
        this.qualificationGrade = qualificationGrade;
    }

    @Column(name="p_record_status")
    public String getpRecordStatus() {
        return pRecordStatus;
    }

    public void setpRecordStatus(String pRecordStatus) {
        this.pRecordStatus = pRecordStatus;
    }

    @Column(name="p_expire_date")
    public Date getpExpireDate() {
        return pExpireDate;
    }

    public void setpExpireDate(Date pExpireDate) {
        this.pExpireDate = pExpireDate;
    }

    @Column(name="p_enable_flag")
    public String getpEnableFlag() {
        return pEnableFlag;
    }

    public void setpEnableFlag(String pEnableFlag) {
        this.pEnableFlag = pEnableFlag;
    }

    @Column(name="sso_flag")
    public String getSsoFlag() {
        return ssoFlag;
    }

    public void setSsoFlag(String ssoFlag) {
        this.ssoFlag = ssoFlag;
    }

    @Column(name="create_by")
    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Column(name="create_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Column(name="update_by")
    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    @Column(name="update_time")
    @Temporal(TemporalType.TIMESTAMP)
    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Column(name="business_post")
	public String getBusinessPost() {
		return businessPost;
	}

	public void setBusinessPost(String businessPost) {
		this.businessPost = businessPost;
	}
    
}
