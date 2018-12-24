package cy.its.sysCfg.rest.dto;

import java.util.ArrayList;
import java.util.List;

import cy.its.com.dto.BaseDto;
import cy.its.com.util.ObjectMapUtils;
import cy.its.syscfg.domain.model.permission.Role;
import cy.its.syscfg.domain.model.permission.RoleFunction;
import cy.its.syscfg.domain.model.permission.RoleFunctionKey;

public class RoleDto extends BaseDto{
	
	public RoleDto(){
		
	}
	
	/**
	 *  用领域对象创建dto
	
	  * 创建一个新的实例 RoleDto. 
	  * <p>Title: </p>
	  * <p>Description: </p>
	  * @param role
	 */
	public RoleDto(Role role){
		ObjectMapUtils.parseObject(this, role);
		StringBuilder builder = new StringBuilder();
		for(RoleFunction foleFunction : role.allFunctions()){
			builder.append(foleFunction.getRoleFunctionKey().getFunctionCode()).append(",");
		}
		if(builder.length() > 0){
			this.functions = builder.substring(0, builder.length()-1);
		}
	}
	
	//角色Id
	private String roleId;
	//角色名称
	private String roleName;
	//启用状态
	private String roleEnableFlag;
	//备注
	private String roleRemark;
	
	private String dataAccessType;
	
	//功能点id字符串
	private String functions;
	
	/**
	  * @throws Exception 
	  * 
	  * convertToRole 转化成领域对象
	  * @Title: convertToRole
	  * @Description: TODO
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public Role convertToRole() throws Exception{
		Role role = null;
		if(this.roleId != null && !"".equals(this.roleId)){
			role = new Role(this.roleId, this.roleName, this.roleEnableFlag, convertToRoleFunctions(this.roleId));
		}else{
			role = new Role(this.roleName,this.roleEnableFlag);
		}
		role.roleRemark = this.roleRemark;
		role.setDataAccessType(this.dataAccessType);
		role.setOrgCode(this.getCurrentOrgCode());
		return role;
	}
	/**
	 * 
	  * convertToRoleFunctions 转化为角色权限对象
	  * @Title: convertToRoleFunctions
	  * @Description: TODO
	  * @param @param roleId
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return RoleFunction[]    返回类型
	  * @throws
	 */
	public List<RoleFunction>  convertToRoleFunctions(String roleId) throws Exception{
		//将functions字符串转化为RoleFunction列表
		List<RoleFunction> roleFunctions = new ArrayList<RoleFunction>();
		String[] functionIds = this.functions.split(",");
		for(String functionId : functionIds){
			RoleFunctionKey roleFunctionKey = new RoleFunctionKey(roleId, functionId);
			RoleFunction roleFunction = new RoleFunction(roleFunctionKey);
			roleFunctions.add(roleFunction);
		}
		//将列表转化为数组
		return roleFunctions;
	}
	
	/**
	 * getter method
	 * @return the functions
	 */
	
	public String getFunctions() {
		return functions;
	}

	/**
	 * setter method
	 * @param functions the functions to set
	 */
	
	public void setFunctions(String functions) {
		this.functions = functions;
	}

	/**
	 * getter method
	 * @return the dataAccessType
	 */
	
	public String getDataAccessType() {
		return dataAccessType;
	}

	/**
	 * setter method
	 * @param dataAccessType the dataAccessType to set
	 */
	
	public void setDataAccessType(String dataAccessType) {
		this.dataAccessType = dataAccessType;
	}

	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	public String getRoleEnableFlag() {
		return roleEnableFlag;
	}
	public void setRoleEnableFlag(String roleEnableFlag) {
		this.roleEnableFlag = roleEnableFlag;
	}
	public String getRoleRemark() {
		return roleRemark;
	}
	public void setRoleRemark(String roleRemark) {
		this.roleRemark = roleRemark;
	}
	
}
