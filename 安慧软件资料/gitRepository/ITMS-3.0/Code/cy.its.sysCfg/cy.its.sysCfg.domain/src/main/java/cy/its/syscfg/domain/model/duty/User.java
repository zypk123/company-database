package cy.its.syscfg.domain.model.duty;

import java.util.Date;
import java.util.List;

import cy.its.com.constant.SysCodeConstant;
import cy.its.com.domain.AggregateRoot;

/**
 * @author STJ
 *
 */
public final class User extends AggregateRoot {

	/**
	 * 用户ID
	 */
	private String userId;

	/**
	 * 外单位ID
	 */
	public String otherOrgId;

	/**
	 * 机构ID
	 */
	public String orgId;

	/**
	 * 警员
	 */
	private Police police;

	/**
	 * 登录名
	 */
	private String loginName;

	/**
	 * 登陆口令
	 */
	private String loginPassword;

	/**
	 * 许可IP或IP范围
	 */
	public String permissionIpList;

	/**
	 * 登录状态
	 */
	private String isOnline;

	/**
	 * 最近一次登录时间
	 */
	private Date latestLoginTime;

	/**
	 * 登录次数
	 */
	private Integer totalLoginCounts = 0;

	/**
	 * 姓名
	 */
	public String name;

	/**
	 * 有效期
	 */
	public Date validDate;

	/**
	 * 用户角色列表
	 */
	public List<String> roleIds;

	// static UserAssignRoleSpecification userAssignRoleSpecification = new
	// UserAssignRoleSpecification();

	public User(Police police, String loginName, String loginPassword,
			String permissionIpList, Date validDate, String orgId)
			throws Exception {

		this.police = police;
		this.setLoginName(loginName);
		this.setLoginPassword(loginPassword);
		this.permissionIpList = permissionIpList;
		this.validDate = validDate;
		this.orgId = orgId;
		this.isOnline = SysCodeConstant.ONLINE_STATUS_OFF;
		this.latestLoginTime = new Date();
	}

	public User(String userId, String orgId, Police police, String loginName,
			String loginPassword, String permissionIpList, String isOnline,
			Date latestLoginTime, Integer totalLoginCounts, String name,
			Date validDate, List<String> roleIds) throws Exception {

		this(police, loginName, loginPassword, permissionIpList, validDate,
				orgId);

		this.userId = userId;
		this.isOnline = isOnline;
		this.latestLoginTime = latestLoginTime;
		this.totalLoginCounts = totalLoginCounts;
		this.name = name;
		this.roleIds = roleIds;
		// roleIds.stream().map(
		// r-> new UserRole(this.userId, r)).
		// collect(Collectors.toList());
	}

	@Override
	public String getIdentityId() {
		return this.userId;
	}

	/**
	 * 分配角色
	 * 
	 * @param role
	 * @throws Exception
	 */
	public void assignRoles(String... roleIdArr) throws Exception {
		if (roleIds != null) {
			for (String roleId : roleIdArr) {
				if (roleIds.contains(roleId)) {
					throw new Exception("当前用户分配的角色重复");
				}

				roleIds.add(roleId);
			}
		}
	}

	/**
	 * 删除用户角色
	 * 
	 * @param role
	 */
	public void removeRoles(String... roleIdArr) {
		if (roleIds != null) {
			for (String roleId : roleIdArr) {
				if (roleIds.contains(roleId)) {
					roleIds.remove(roleId);
				}
			}
		}
	}

	/**
	 * 获取当前用户权限列表
	 * 
	 * @return
	 */
	public String[] roleIds() {
		return roleIds.toArray(new String[roleIds.size()]);
	}

	/**
	 * 认证
	 * 
	 * @param loginName
	 * @param passWord
	 * @return
	 * @throws Exception
	 */
	public boolean authorize(String loginName, String passWord)
			throws Exception {

		if (this.loginName.equals(loginName)
				&& this.loginPassword.equals(passWord)) {
			Date systime = new Date();

			if (systime.after(this.validDate)) {
				throw new Exception("过期用户");
			}

			this.totalLoginCounts++;
			this.latestLoginTime = systime;

			return true;
		}

		return false;
	}

	/**
	 * @return the latestLoginTime
	 */
	public java.util.Date getLatestLoginTime() {
		return latestLoginTime;
	}

	/**
	 * @return the totalLoginCounts
	 */
	public Integer getTotalLoginCounts() {
		return totalLoginCounts;
	}

	public void setUserId(String userId) throws Exception {
		NotNull(userId, "用户ID");
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setPolice(Police police) throws Exception {
		// NotNull(police, "警员");
		this.police = police;
	}

	public Police getPolice() {
		return police;
	}

	public void setLoginName(String loginName) throws Exception {
		NotNull(loginName, "登录名");
		this.loginName = loginName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginPassword(String loginPassword) throws Exception {
		//NotNull(loginPassword, "登陆口令");
		this.loginPassword = loginPassword;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	// public void setIsOnline( String isOnline) throws Exception {
	// NotNull(isOnline, "登录状态");
	// this.isOnline = isOnline;
	// }

	public String getIsOnline() {
		return isOnline;
	}

	// public void setLatestLoginTime( Date latestLoginTime) throws Exception {
	// NotNull(latestLoginTime, "最近一次登录时间");
	// this.latestLoginTime = latestLoginTime;
	// }

	// public Date getLatestLoginTime() {
	// return latestLoginTime;
	// }

	// public void setTotalLoginCounts( Integer totalLoginCounts) throws
	// Exception {
	// NotNull(totalLoginCounts, "登录次数");
	// this.totalLoginCounts = totalLoginCounts;
	// }

}
