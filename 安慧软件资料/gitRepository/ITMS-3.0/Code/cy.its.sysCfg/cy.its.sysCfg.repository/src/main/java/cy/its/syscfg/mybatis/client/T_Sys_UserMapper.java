package cy.its.syscfg.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;

import cy.its.syscfg.domain.model.duty.UserCount;
import cy.its.syscfg.mybatis.model.LoginTime;
import cy.its.syscfg.mybatis.model.T_Sys_User;
import cy.its.syscfg.mybatis.model.UserResource;

public interface T_Sys_UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(T_Sys_User record);

    int insertSelective(T_Sys_User record);

    T_Sys_User selectByPrimaryKey(String userId);

    int updateByPrimaryKeySelective(T_Sys_User record);

    int updateByPrimaryKey(T_Sys_User record);

	/**
	  * updateLoginPasswordToDefault 修改密码sql
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
	void updateLoginPassword(@Param("userId") String userId, @Param("password") String password);
	
	/**
	 * 
	  * selectUserResource 根据用户ID查找用户可用的系统资源（菜单，功能点）
	  * @Title: selectUserResource
	  * @Description: TODO
	  * @param @param userId
	  * @param @return    设定文件
	  * @return List<UserResource>    返回类型
	  * @throws
	 */
	List<UserResource> selectUserResource(@Param("userId") String userId);

	/**
	 * 统计所给用户最近一周登录次数
	 * @param userNameList
	 * @return
	 */
	List<LoginTime> countUserLoginTimeInWeek(@Param("userNameList") List<String> userNameList);

	int countLoginTimesInCurMonth(@Param("userId") String userId);

	int countLoginTotal(@Param("userId") String userId);

	List<UserCount> countMenuCurrentMonthTop10();

	List<UserCount> countOrgLoginCurrentMonthTop10();

	List<UserCount> countIpLoginCurrentMonthTop10();

	List<UserCount> countLoginTimeByDay();

}