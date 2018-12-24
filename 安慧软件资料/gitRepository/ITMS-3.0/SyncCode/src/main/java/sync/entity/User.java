package sync.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="t_sys_user",schema="itms3")
public class User {
	private String userId;

    private String otherOrgId;

    private String orgId;

    private String policeId;

    private String loginName;

    private String loginPassword;

    private String permissionIpList;

    private String isOnline;

    private Date latestLoginTime;

    private Integer totalLoginCounts;

    private String name;

    private Date validDate;
    
//    private Set<HibernateRole> roles = new LinkedHashSet<HibernateRole>();
    
    @Id
    @Column(name="USER_ID")
    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
	
	@Column(name="OTHER_ORG_ID")
	public String getOtherOrgId() {
		return otherOrgId;
	}

	public void setOtherOrgId(String otherOrgId) {
		this.otherOrgId = otherOrgId;
	}

	@Column(name="ORG_ID")
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	@Column(name="login_name")
	public String getLoginName() {
		return loginName;
	}
	
	@Column(name="police_id")
	public String getPoliceId() {
		return policeId;
	}

	public void setPoliceId(String policeId) {
		this.policeId = policeId;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	@Column(name="login_password")
	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	@Column(name="permission_ip_list")
	public String getPermissionIpList() {
		return permissionIpList;
	}

	public void setPermissionIpList(String permissionIpList) {
		this.permissionIpList = permissionIpList;
	}

	@Column(name="is_online")
	public String getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(String isOnline) {
		this.isOnline = isOnline;
	}

	@Column(name="latest_login_time")
	@Temporal(TemporalType.TIMESTAMP)  
	public Date getLatestLoginTime() {
		return latestLoginTime;
	}

	public void setLatestLoginTime(Date latestLoginTime) {
		this.latestLoginTime = latestLoginTime;
	}

	@Column(name="total_login_counts")
	public Integer getTotalLoginCounts() {
		return totalLoginCounts;
	}

	public void setTotalLoginCounts(Integer totalLoginCounts) {
		this.totalLoginCounts = totalLoginCounts;
	}

	@Column(name="name")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name="valid_date")
	@Temporal(TemporalType.DATE)  
	public Date getValidDate() {
		return validDate;
	}

	public void setValidDate(Date validDate) {
		this.validDate = validDate;
	}

//	@ManyToMany
//	@JoinTable(name = "T_SYS_ROLE_USER",
//	joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "userId")},
//	inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName ="roleId")})
//	public Set<HibernateRole> getRoles() {
//		return roles;
//	}

//	public void setRoles(Set<HibernateRole> roles) {
//		this.roles = roles;
//	}
//    
}
