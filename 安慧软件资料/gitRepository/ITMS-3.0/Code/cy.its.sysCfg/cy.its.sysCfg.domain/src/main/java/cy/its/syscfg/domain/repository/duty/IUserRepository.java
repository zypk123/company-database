package cy.its.syscfg.domain.repository.duty;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.criteria.UserCriteria;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.model.duty.UserCount;
import cy.its.syscfg.domain.model.duty.UserResourceTree;
import cy.its.syscfg.domain.model.duty.UserSignature;

public interface IUserRepository extends IRepository<User> {
	void removeUserRoles(String userId);
	void removeUserRolesByRoleId(String rodleId);
	void addUserRoles(String userId, String...roleIds);
	void removeUserRoles(String userId, String...roleIds);
	List<User> findUsers(UserCriteria criteria) throws Exception;
	int countUsers(UserCriteria criteria);
	/**
	  * updateLoginPasswordToDefault 重置密码
	  * TODO(这里描述这个方法适用条件 – 可选)
	  * TODO(这里描述这个方法的执行流程 – 可选)
	  * TODO(这里描述这个方法的使用方法 – 可选)
	  * TODO(这里描述这个方法的注意事项 – 可选)
	  *
	  * @Title: updateLoginPasswordToDefault
	  * @Description: TODO
	  * @param @param userId
	  * @param @param password    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	void updateLoginPassword(String userId, String password);
	
	/**
	  * findUserResourceByUserId 根据用户Id，查找用户所拥有的资源（菜单，功能点）
	  *
	  * @Title: findUserResourceByUserId
	  * @Description: TODO
	  * @param @param userId
	  * @param @return    设定文件
	  * @return List<UserResourceTree>    返回类型
	  * @throws
	 */
	List<UserResourceTree> findUserResourceByUserId(String userId);
	
	/**
	 * 根据用户ID查找用户签章
	 * @param userId
	 * @return
	 */
	List<UserSignature> findUserSignatureByUserId(String userId);
	
	/**
	 * 保存用户签章信息
	 * @param sign
	 * @return
	 */
	void saveUserSignature(UserSignature sign);
	
	/**
	 * 删除用户签章
	 * @param userId
	 */
	void removeUserSignature(String userId);
	
	/**
	 * 查找用户最近一周登录次数
	 * @param userNameList
	 * @return
	 */
	Map<String, Object[]> getUserLoingTimeInWeek(List<String> userNameList);
	
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
	 * 按天统计本月登录次数
	 * @return
	 */
	List<UserCount> countLoginTimeByDay();
	
	/**
	 * 统计本月登录IP前十名
	 * @return
	 */
	List<UserCount> countIpLoginCurrentMonthTop10();
	
	/**
	 * 统计当月机构登录次数前十名
	 * @return
	 */
	List<UserCount> countOrgLoginCurrentMonthTop10();
	
	/**
	 * 统计当月菜单使用次数前十名
	 * @return
	 */
	List<UserCount> countMenuCurrentMonthTop10();
}
