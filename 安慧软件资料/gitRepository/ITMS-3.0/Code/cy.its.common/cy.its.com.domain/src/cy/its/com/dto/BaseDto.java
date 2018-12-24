package cy.its.com.dto;

public abstract class BaseDto {

	// 当前用户Id
	private String currentUserId;
	//当前用户名
	private String currentUserName;
	//当前用户登录名
	private String currentUserLoginName;
	// 当前用户所属机构
	private String currentOrgId;
	//当前用户所属机构编码
	private String currentOrgCode;
	// 机构权限代码
	private String currentOrgPrivilegeCode;
	
	
	// 当前页数
	private int pageNumber;
	// 分页大小
	private int pageSize;
	
	private String orderName;
	
	private String orderType;
	
//	public BaseDto(){
//		this.validate();
//	}
//
//	public abstract boolean validate();
	
	public String getCurrentUserId() {
		return currentUserId;
	}

	public void setCurrentUserId(String currentUserId) {
		this.currentUserId = currentUserId;
	}

	public String getCurrentOrgId() {
		return currentOrgId;
	}

	public void setCurrentOrgId(String currentOrgId) {
		this.currentOrgId = currentOrgId;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		if(pageNumber < 1){
			pageNumber = 1;
		}
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		if(pageSize < 1){
			pageSize = 1;
		}
		this.pageSize = pageSize;
	}

	public String getCurrentOrgPrivilegeCode() {
		return currentOrgPrivilegeCode;
	}

	public void setCurrentOrgPrivilegeCode(String currentOrgPrivilegeCode) {
		this.currentOrgPrivilegeCode = currentOrgPrivilegeCode;
	}

	/**
	 * getter method
	 * @return the currentUserName
	 */
	
	public String getCurrentUserName() {
		return currentUserName;
	}

	/**
	 * setter method
	 * @param currentUserName the currentUserName to set
	 */
	
	public void setCurrentUserName(String currentUserName) {
		this.currentUserName = currentUserName;
	}

	/**
	 * getter method
	 * @return the currentUserLoginName
	 */
	
	public String getCurrentUserLoginName() {
		return currentUserLoginName;
	}

	/**
	 * setter method
	 * @param currentUserLoginName the currentUserLoginName to set
	 */
	
	public void setCurrentUserLoginName(String currentUserLoginName) {
		this.currentUserLoginName = currentUserLoginName;
	}

	/**
	 * getter method
	 * @return the currentOrgCode
	 */
	
	public String getCurrentOrgCode() {
		return currentOrgCode;
	}

	/**
	 * setter method
	 * @param currentOrgCode the currentOrgCode to set
	 */
	
	public void setCurrentOrgCode(String currentOrgCode) {
		this.currentOrgCode = currentOrgCode;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

}
