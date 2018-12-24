package cy.its.syscfg.mybatis.model;

public class LoginTime {

	/**
	 * 用户名
	 */
	private String userName;
	
	/**
	 * 登录次数
	 */
	private Long loginTime;
	
	/**
	 * 最后登录时间
	 */
	private String lastLoginTime; 

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public Long getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(Long loginTime) {
		this.loginTime = loginTime;
	}

	public String getLastLoginTime() {
		return lastLoginTime;
	}

	public void setLastLoginTime(String lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}
}
