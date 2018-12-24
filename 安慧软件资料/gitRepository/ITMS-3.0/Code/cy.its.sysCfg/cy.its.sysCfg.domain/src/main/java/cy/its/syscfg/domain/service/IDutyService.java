package cy.its.syscfg.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.criteria.OrgCriteria;
import cy.its.syscfg.domain.criteria.UserCriteria;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.model.duty.UserCount;
import cy.its.syscfg.domain.model.duty.UserResourceTree;
import cy.its.syscfg.domain.model.duty.UserSignature;

/**
 * @author STJ
 *
 */
/**
 * @author STJ
 *
 */
public interface IDutyService {

	/**
	 * 创建新机构
	 * 
	 * @param organization
	 *            新机构详细
	 */
	public void createOrganization(Organization organization);

	/**
	 * 查询指定的机构详细信息
	 * 
	 * @param orgId
	 *            机构唯一标识ID
	 * @return 机构详细信息
	 * @throws Exception
	 */
	public Organization organizationOfId(String orgId) throws Exception;

	/**
	 * 获取指定父机构下的子机构列表
	 * 注: 所有子机构
	 * @param orgId
	 *            父机构ID
	 * @return 子机构列表(含父机构)
	 * @throws Exception 
	 */
	public List<Organization> findSonOrgsOfParent(String orgId) throws Exception;
	
	/**
	 * 获取指定父机构下的子机构列表
	 * 注: 不包含子机构下的子机构
	 * @param orgId
	 *            父机构机构
	 * @return 子机构列表(不含父机构)
	 * @throws Exception 
	 */
	public List<Organization> findOrgsOfParent(String orgId) throws Exception;
	

	/**
	 * 查询符合条件的机构信息列表
	 * 注: 带分页
	 * @param orgCriteria
	 *            查询条件
	 * @return 机构信息列表
	 */
	public List<Organization> findOrganizations(OrgCriteria orgCriteria);

	List<Organization> findOrgOfParent(String parentOrgPrivilegeCode);
	
	List<Organization> organizationsOfCode(String orgCode);
	
	/**
	 * 更新机构信息
	 * 
	 * @param organization
	 *            机构信息
	 * @throws Exception
	 */
	public void updateOrganiztion(Organization organization) throws Exception;

	/**
	 * 删除指定的机构
	 * 
	 * @param orgId
	 *            机构唯一标识ID
	 * @throws Exception
	 */
	public void deleteOrganization(String orgId) throws Exception;

	/**
	 * 创建新用户
	 * 
	 * @param user
	 *            新用户信息
	 */
	public void createUser(User user);

	/**
	 * 删除指定的用户
	 * 
	 * @param userId
	 *            用户唯一标识ID
	 */
	public void deleteUser(String userId);

	/**
	 * 更新用户
	 * 
	 * @param user
	 *            用户信息
	 */
	public void updateUser(User user);

	/**
	 * 查询指定的用户
	 * 
	 * @param userId
	 *            用户唯一标识ID
	 * @return 用户信息
	 * @throws Exception
	 */
	public User userOfId(String userId) throws Exception;

	/**
	 * 查询符合条件的用户信息列表
	 * 
	 * @param userCriteria
	 *            查询条件
	 * @return 用户信息列表
	 * @throws Exception
	 */
	public List<User> findUsers(UserCriteria userCriteria) throws Exception;

	/**
	 * 为指定用户分配多个角色
	 * 
	 * @param userId
	 *            用户唯一标识ID
	 * @param roleIds
	 *            多个角色唯一标识ID
	 * @throws Exception
	 *             指定用户不存在
	 */
	public void addRolesToUser(String userId, String... roleIds)
			throws Exception;

	/**
	 * 删除指定用户的指定多个角色
	 * 
	 * @param userId
	 *            用户唯一标识ID
	 * @param roleIds
	 *            多个角色唯一标识ID
	 */
	public void removeUserRoles(String userId, String... roleIds);

	/**
	 * 用户认证
	 * 
	 * @param loginName
	 *            登录名
	 * @param passWord
	 *            登录密码
	 * @return 认证通过对应的用户信息
	 * @throws Exception
	 *             登录失败: 密码错误; 登录失败:用户不存在!
	 */
	public User authorize(String loginName, String passWord) throws Exception;
	
	/**
	 * 机构变更通知
	 */
	public void organizationChanged();

	/**
	  * updateLoginPasswordToDefault(这里用一句话描述这个方法的作用)
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: updateLoginPasswordToDefault
	  * @Description: TODO
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	  */
	
	
	/**
	  * updateLoginPasswordToDefault 重置密码
	  * @Title: updateLoginPasswordToDefault
	  * @Description: TODO
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public void updateLoginPassword(String userId, String password);
	
	/**
	  * 
	  * findUserResourceByUserId 根据用户Id查找用户资源（菜单，功能点）
	  * @Title: findUserResourceByUserId
	  * @Description: TODO
	  * @param @param userId
	  * @param @return    设定文件
	  * @return List<UserResourceTree>    返回类型
	  * @throws
	 */
	public List<UserResourceTree> findUserResourceByUserId(String userId);
	
	/**
	 *  根据用户ID找到用户签章信息
	 * @param userId
	 * @return
	 */
	public List<UserSignature> findUserSigntureByUserId(String userId);

	/**
	 * 保存用户签章信息
	 * @param sign
	 * @return
	 */
	public void createUserSignature(UserSignature sign);

	/**
	 * 删除用户签章信息
	 * @param userId
	 */
	public void removeUserSignatureByUserId(String userId);
	
	/**
	 * 查找用户最近一周登录次数
	 * @param userNameList
	 * @return
	 */
	public Map<String, Object[]> getUserLoingTimeInWeek(List<String> userNameList);
	
	/**
	 * 查询用户当前月份其实时间至当前时间为止总共的登陆次数
	 * @param userId
	 * @return
	 */
	int loginCountCurMonthOfUser(String userId);
	
	
	/**
	 * 查询指定用户自注册以来总的登陆次数
	 * @param userId
	 * @return
	 */
	int loginTotalOfUser(String userId);
	
	
	/**
	 * 统计本月常用功能前十位
	 * @return
	 */
	List<UserCount> countMenuCurrentMonthTop10();
	
	/**
	 * 统计本月登录机构排名前十位
	 * @return
	 */
	List<UserCount> countOrgLoginCurrentMonthTop10();
	
	/**
	 * 统计本月登录IP排名前十位
	 * @return
	 */
	List<UserCount> countIpLoginCurrentMonthTop10();
	
	/**
	 * 按天统计本月登录次数
	 * @return
	 */
	List<UserCount> countLoginTimeByDay();
	
}