package cy.its.syscfg.domain.service.impl;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.criteria.OrgCriteria;
import cy.its.syscfg.domain.criteria.UserCriteria;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.model.duty.UserCount;
import cy.its.syscfg.domain.model.duty.UserResourceTree;
import cy.its.syscfg.domain.model.duty.UserSignature;
import cy.its.syscfg.domain.repository.duty.IOrgRepository;
import cy.its.syscfg.domain.repository.duty.IUserRepository;
import cy.its.syscfg.domain.repository.home.IDailyMenuRepository;
import cy.its.syscfg.domain.service.IDutyService;

/**
 * 勤务服务
 *
 */
@Service
public class DutyService implements IDutyService {

	@Autowired
	IOrgRepository orgRepository;

	@Autowired
	IUserRepository userRepository;
	
	@Autowired
	IDailyMenuRepository dailyMenuRepository;

	/**
	 * 创建新机构
	 * 
	 * @param organization
	 *            新机构详细
	 */
	public void createOrganization(Organization organization) {
		orgRepository.save(organization);
	}

	/**
	 * 查询指定的机构详细信息
	 * 
	 * @param orgId
	 * 机构唯一标识ID
	 * @return 机构详细信息
	 * @throws Exception
	 */
	public Organization organizationOfId(String orgId) throws Exception {
		return orgRepository.aggregateOfId(orgId);
	}

	/**
	 * 获取指定父机构下的子机构列表
	 * 
	 * @param orgPrivilegeCode
	 *            机构权限代码
	 * @return 子机构列表(含父机构)
	 * @throws Exception
	 */
	public List<Organization> findSonOrgsOfParent(String orgId) throws Exception {
		OrgCriteria criteria = new OrgCriteria();
		criteria.setNoPage();
		criteria.orgId = orgId;
		return orgRepository.findOrganizations(criteria);
	}

	/**
	 * 获取指定父机构下的子机构列表 注: 不包含子机构下的子机构
	 * 
	 * @param orgId
	 *            父机构机构
	 * @return 子机构列表(不含父机构)
	 * @throws Exception
	 */
	public List<Organization> findOrgsOfParent(String orgId) throws Exception {
		List<Organization> lst = findSonOrgsOfParent(orgId);
		Organization org = this.organizationOfId(orgId);
		return lst != null ? lst.stream().filter((c) -> 
			!c.orgPrivilegeCode.equals(org.orgPrivilegeCode) 
			&& c.orgPrivilegeCode.startsWith(org.orgPrivilegeCode)
			&& c.orgPrivilegeCode.length() == org.orgPrivilegeCode.length() + 2
			).collect(Collectors.toList())
				: null;
	}

	/**
	 * 查询符合条件的机构信息列表
	 * 
	 * @param orgCriteria
	 *            查询条件
	 * @return 机构信息列表
	 */
	public List<Organization> findOrganizations(OrgCriteria orgCriteria) {
		return orgRepository.findOrganizations(orgCriteria);
	}
	
	public List<Organization> findOrgOfParent(String parentOrgPrivilegeCode)
	{
		return orgRepository.findOrgOfParent(parentOrgPrivilegeCode);
	}

	/**
	 * 更新机构信息
	 * 
	 * @param organization
	 *            机构信息
	 * @throws Exception
	 */
	public void updateOrganiztion(Organization organization) throws Exception {
		if (StringUtil.isNullOrEmpty(organization.getIdentityId())) {
			throw new Exception("未指定要新的机构唯一标识");
		}
		orgRepository.update(organization);
	}

	/**
	 * 删除指定的机构
	 * 
	 * @param orgId
	 *            机构唯一标识ID
	 * @throws Exception
	 */
	public void deleteOrganization(String orgId) throws Exception {
		Organization org = orgRepository.aggregateOfId(orgId);
		List<Organization> lstSonOrg = findSonOrgsOfParent(org.orgCode);
		if (lstSonOrg != null && lstSonOrg.size() != 0) {
			for (Organization sonOrg : lstSonOrg) {
				deleteOrgAndUsers(sonOrg);
			}
		}
		deleteOrgAndUsers(org);
	}

	/**
	 * 创建新用户
	 * 
	 * @param user
	 *            新用户信息
	 */
	public void createUser(User user) {
		userRepository.save(user);
	}

	/**
	 * 删除指定的用户
	 * 
	 * @param userId
	 *            用户唯一标识ID
	 */
	public void deleteUser(String userId) {
		userRepository.removeUserRoles(userId);
		userRepository.delete(userId);
		dailyMenuRepository.deleteByUserPermmission();
	}

	/**
	 * 更新用户
	 * 
	 * @param user
	 *            用户信息
	 */
	public void updateUser(User user) {
		userRepository.update(user);
	}

	/**
	 * 查询指定的用户
	 * 
	 * @param userId
	 *            用户唯一标识ID
	 * @return 用户信息
	 * @throws Exception
	 */
	public User userOfId(String userId) throws Exception {
		return userRepository.aggregateOfId(userId);
	}

	/**
	 * 查询指定机构下的所有用户
	 * 
	 * @param orgCode
	 *            机构代码
	 * @return 用户信息列表
	 * @throws Exception
	 */
	public List<User> findUsers(UserCriteria userCriteria) throws Exception {
		return userRepository.findUsers(userCriteria);
	}

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
	public void addRolesToUser(String userId, String... roleIds) throws Exception {
		User user = userRepository.aggregateOfId(userId);
		if (user == null) {
			throw new Exception("用户不存在");
		}
		user.assignRoles(roleIds);
		// userRepository.removeUserRoles(userId);
		userRepository.addUserRoles(userId, roleIds);
	}

	/**
	 * 删除指定用户的指定多个角色
	 * 
	 * @param userId
	 *            用户唯一标识ID
	 * @param roleIds
	 *            多个角色唯一标识ID
	 */
	public void removeUserRoles(String userId, String... roleIds) {
		userRepository.removeUserRoles(userId, roleIds);
		dailyMenuRepository.deleteByUserPermmission();
	}

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
	public User authorize(String loginName, String passWord) throws Exception {
		UserCriteria criteria = new UserCriteria();
		criteria.loginName = loginName;
		List<User> users = userRepository.findUsers(criteria);
		if (users != null && users.size() == 1) {
			if (users.get(0).authorize(loginName, passWord)) {
				userRepository.update(users.get(0));
				return users.get(0);
			}
			throw new Exception("登录失败:密码错误!");
		}
		throw new Exception("登录失败:用户不存在!");
	}

	/**
	 * 删除指定机构及其下的所有用户
	 * 
	 * @param org
	 * @throws Exception
	 */
	private void deleteOrgAndUsers(Organization org) throws Exception {
		UserCriteria criteria = new UserCriteria();
		criteria.orgId = org.getIdentityId();

		List<User> users = findUsers(criteria);
		for (User user : users) {
			deleteUser(user.getUserId());
		}
		orgRepository.delete(org.getIdentityId());
		dailyMenuRepository.deleteByUserPermmission();
	}

	/**
	 * 机构变更通知
	 */
	public void organizationChanged() {
		orgRepository.organizationChanged();
	}

	/**
	 * 重置密码
	 */
	@Override
	public void updateLoginPassword(String userId,String password) {
		userRepository.updateLoginPassword(userId,password);
	}
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
	@Override
	public List<UserResourceTree> findUserResourceByUserId(String userId){
		return userRepository.findUserResourceByUserId(userId);
	}

	@Override
	public List<UserSignature> findUserSigntureByUserId(String userId) {
		return userRepository.findUserSignatureByUserId(userId);
	}

	@Override
	public void createUserSignature(UserSignature sign) {
		//保存新的签章记录
		userRepository.saveUserSignature(sign);
	}
	
	@Override
	public void removeUserSignatureByUserId(String userId){
		//删除原有的签章文件
		
		//删除原有的签章记录
		userRepository.removeUserSignature(userId);
	}

	@Override
	public Map<String, Object[]> getUserLoingTimeInWeek(List<String> userNameList) {
		return userRepository.getUserLoingTimeInWeek(userNameList);
	}

	@Override
	public int loginCountCurMonthOfUser(String userId) {
		return userRepository.loginCountCurMonthOfUser(userId);
	}

	@Override
	public int loginTotalOfUser(String userId) {
		return userRepository.loginTotalOfUser(userId);
	}

	@Override
	public List<Organization> organizationsOfCode(String orgCode) {
		return orgRepository.organizationsOfCode(orgCode);
	}

	@Override
	public List<UserCount> countMenuCurrentMonthTop10() {
		return userRepository.countMenuCurrentMonthTop10();
	}

	@Override
	public List<UserCount> countOrgLoginCurrentMonthTop10() {
		return userRepository.countOrgLoginCurrentMonthTop10();
	}

	@Override
	public List<UserCount> countIpLoginCurrentMonthTop10() {
		return userRepository.countIpLoginCurrentMonthTop10();
	}

	@Override
	public List<UserCount> countLoginTimeByDay() {
		return userRepository.countLoginTimeByDay();
	}
}
