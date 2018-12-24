package cy.its.sysCfg.rest.dto;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import cy.its.com.constant.SysCodeConstant;
import cy.its.com.dto.BaseDto;
import cy.its.com.util.DateUtil;
import cy.its.com.util.ObjectMapUtils;
import cy.its.syscfg.domain.model.duty.Police;
import cy.its.syscfg.domain.model.duty.User;

/**
 * 用户信息
 */
public class UserDto extends BaseDto{
	
	private String userId;
	//机构Id
	private String orgId;
	//访问页数
	private int pageNumber;
	//每页记录条数
	private int pageSize;
	//姓名
	private String policeName;
	//警员ID
	private String policeId;
	//警号
	private String policeCode;
	//警员编号
	private String policeNbr;
	//是否在线状态
	private String isOnline;
	//警员类型
	private String policeType;
	//业务岗位
	private String businessPost;
	//身份证号码
	private String personId;
	//编制类型
	private String authorizedType;
	//事务处理等级
	private String eventLevel;
	//领导级别
	private String leaderLevel;
	//出生年月
	private String birthDate;
	//性别
	private String sex;
	//警衔
	private String policeRank;
	//职级
	private String jobLevel;
	//职位
	private String position;
	//联系电话
	private String pPhoneNbr;
	//办公电话
	private String officePhone;
	//家庭住址
	private String familyAddress;
	//电子邮箱
	private String email;
	//用户名
	private String loginName;
	//密码
	private String loginPassword;
	//有效时间
	private String validDate;
	//许可Ip
	private String permissionIpList;
	
	private String loginTime;
	
	private String lastLoginTime;
	
	//角色Id
	private String roleIds;
	
	public UserDto(){
		
	}
	/**
	
	  * 创建一个新的实例 UserDto. 用领域对象来转化
	  * <p>Title: </p>
	  * <p>Description: </p>
	  * @param user
	  */
	public UserDto(User user) {
		SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
		this.userId = user.getUserId();
		this.orgId = user.getPolice().getOrgId();
		this.policeId = user.getPolice().getIdentityId();
		this.policeName = user.getPolice().getPoliceName();
		this.policeCode = user.getPolice().getPoliceCode();
		this.policeNbr = user.getPolice().getPoliceNbr();
		this.isOnline = user.getIsOnline();
		this.policeType = user.getPolice().getPoliceType();
		this.businessPost = user.getPolice().getBusinessPost();
		this.personId = user.getPolice().getPersonId();
		this.authorizedType = user.getPolice().getAuthorizedType();
		this.eventLevel = user.getPolice().getEventLevel();
		this.leaderLevel = user.getPolice().getLeaderLevel();
		if(user.getPolice().getBirthDate() != null){
			this.birthDate = sf.format(user.getPolice().getBirthDate());
		}
		this.sex = user.getPolice().getSex();
		this.policeRank = user.getPolice().getPoliceRank();
		this.jobLevel = user.getPolice().getJobLevel();
		this.position = user.getPolice().getPosition();
		this.pPhoneNbr = user.getPolice().getpPhoneNbr();
		this.officePhone = user.getPolice().getOfficePhone();
		this.familyAddress = user.getPolice().getFamilyAddress();
		this.email = user.getPolice().getEmail();
		this.loginName = user.getLoginName();
		if(user.getLatestLoginTime() != null){
			this.lastLoginTime = DateUtil.formatDate(user.getLatestLoginTime());
		}
		if(user.getTotalLoginCounts() != null){
			this.loginTime = user.getTotalLoginCounts() + "";
		}
		this.loginPassword = user.getLoginPassword();
		if(user.validDate != null){
			this.validDate = sf.format(user.validDate);
		}
		this.permissionIpList = user.permissionIpList;
		
		if(user.roleIds != null && user.roleIds.size() > 0){
			StringBuilder sb = new StringBuilder();
			for(String roleId : user.roleIds()){
				sb.append(roleId).append(",");
			}
			this.roleIds = sb.substring(0, sb.length()-1);
		}
	}
	
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getIsOnline() {
		return isOnline;
	}
	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}
	public int getPageNumber() {
		return pageNumber;
	}
	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	public String getPoliceName() {
		return policeName;
	}
	public void setPoliceName(String policeName) {
		this.policeName = policeName;
	}
	public String getPoliceId() {
		return policeId;
	}
	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}
	
	public String getPoliceCode() {
		return policeCode;
	}
	public void setPoliceCode(String policeCode) {
		this.policeCode = policeCode;
	}
	public String getPoliceNbr() {
		return policeNbr;
	}
	public void setPoliceNbr(String policeNbr) {
		this.policeNbr = policeNbr;
	}
	public String getPoliceType() {
		return policeType;
	}
	public void setPoliceType(String policeType) {
		this.policeType = policeType;
	}
	public String getBusinessPost() {
		return businessPost;
	}
	public void setBusinessPost(String businessPost) {
		this.businessPost = businessPost;
	}
	public String getPersonId() {
		return personId;
	}
	public void setPersonId(String personId) {
		this.personId = personId;
	}
	public String getAuthorizedType() {
		return authorizedType;
	}
	public void setAuthorizedType(String authorizedType) {
		this.authorizedType = authorizedType;
	}
	public String getEventLevel() {
		return eventLevel;
	}
	public void setEventLevel(String eventLevel) {
		this.eventLevel = eventLevel;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getPoliceRank() {
		return policeRank;
	}
	public void setPoliceRank(String policeRank) {
		this.policeRank = policeRank;
	}
	public String getJobLevel() {
		return jobLevel;
	}
	public void setJobLevel(String jobLevel) {
		this.jobLevel = jobLevel;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	
	public String getpPhoneNbr() {
		return pPhoneNbr;
	}
	public void setpPhoneNbr(String pPhoneNbr) {
		this.pPhoneNbr = pPhoneNbr;
	}
	public String getOfficePhone() {
		return officePhone;
	}
	public void setOfficePhone(String officePhone) {
		this.officePhone = officePhone;
	}
	public String getFamilyAddress() {
		return familyAddress;
	}
	public void setFamilyAddress(String familyAddress) {
		this.familyAddress = familyAddress;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getLoginName() {
		return loginName;
	}
	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}
	
	public String getLoginPassword() {
		return loginPassword;
	}
	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}
	public String getValidDate() {
		return validDate;
	}
	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}
	public String getPermissionIpList() {
		return permissionIpList;
	}
	public void setPermissionIpList(String permissionIpList) {
		this.permissionIpList = permissionIpList;
	}
	public String getLeaderLevel() {
		return leaderLevel;
	}
	public void setLeaderLevel(String leaderLevel) {
		this.leaderLevel = leaderLevel;
	}
	
	/**
	 * getter method
	 * @return the roleIds
	 */
	
	public String getRoleIds() {
		return roleIds;
	}
	/**
	 * setter method
	 * @param roleIds the roleIds to set
	 */
	
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	
	/**
	 * getter method
	 * @return the userId
	 */
	
	public String getUserId() {
		return userId;
	}
	/**
	 * setter method
	 * @param userId the userId to set
	 */
	
	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	public String getLoginTime() {
		return loginTime;
	}
	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}
	public String getLastLoginTime() {
		return lastLoginTime;
	}
	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
	/**
	 * @throws Exception 
	 * @throws ParseException 
	  * convertToUser      转换成领域对象
	  * @Title: convertToUser
	  * @Description: TODO
	  * @param @return    设定文件
	  * @return User    返回类型
	  * @throws
	  */
	public User convertToUser() throws Exception {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Police police = new Police();
		police.setBirthDate(df.parse(this.getBirthDate()));
		if(this.policeId == null || "".equals(this.policeId)){
			//创建时初始化字段
			police.setpRecordStatus(SysCodeConstant.P_RECORD_STATUS_NORMAL);
			police.setpEnableFlag(SysCodeConstant.ENABLE_FLAG_ENANLE);
		}
		ObjectMapUtils.parseObject(police, this);
		Date validateTime = null;
		if(this.validDate != null && !"".equals(this.getValidDate())){
			validateTime = df.parse(this.getValidDate());
		}
		User user = new User(police, this.getLoginName().toUpperCase(), this.getLoginPassword(),
				this.getPermissionIpList(), validateTime, this.getOrgId());
		user.name = this.getPoliceName();
		if(this.userId != null && !"".equals(this.userId)){
			user.setUserId(userId);
		}
		return user;
	}
}





