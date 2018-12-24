package cy.its.sysCfg.rest.action.impl;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.wordnik.swagger.annotations.Api;
import com.wordnik.swagger.annotations.ApiOperation;
import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.constant.ConstValue;
import cy.its.platform.common.exception.ItmsAppException;
import cy.its.platform.common.utils.MD5Util;
import cy.its.sysCfg.rest.dto.DataGridResult;
import cy.its.sysCfg.rest.dto.Results;
import cy.its.sysCfg.rest.dto.UserDto;
import cy.its.syscfg.domain.criteria.UserCriteria;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.model.duty.UserCount;
import cy.its.syscfg.domain.model.duty.UserResourceTree;
import cy.its.syscfg.domain.model.duty.UserSignature;
import cy.its.syscfg.domain.service.IDutyService;

@RestController
@RequestMapping("/sysCfg/userAction")
@Api(description="用户操作", value = "UserAction")
public class UserAction {

	@Autowired
	IDutyService dutyService;

	/**
	 * 创建用户信息操作
	 */
	@RequestMapping(value="/saveUser",method=RequestMethod.POST)
	@ApiOperation(value="save",notes="创建用户信息操作",httpMethod="POST")
	public Object save(@ModelAttribute(value = "userInfo") UserDto userDto) throws Exception {
		//验证用户名是否存在
		UserCriteria criteria = new UserCriteria();
		criteria.loginName = userDto.getLoginName().toUpperCase();
		List<User> result = dutyService.findUsers(criteria);
		
		if(!result.isEmpty()){
			throw new ItmsAppException("用户名已经存在！");
		}
		
		//验证警员编号是否存在
		criteria = new UserCriteria();
		criteria.policeCode = userDto.getPoliceCode();
		result = dutyService.findUsers(criteria);
		if(!result.isEmpty()){
			throw new ItmsAppException("警员编号已经存在！");
		}
		
		User user = userDto.convertToUser();
		user.name = userDto.getPoliceName();
		user.setLoginPassword(MD5Util.md5(ConstValue.DEFAULT_LOGIN_PASSWORD));
		dutyService.createUser(user);
		if(userDto.getRoleIds() != null && !"".equals(userDto.getRoleIds())){
			dutyService.addRolesToUser(user.getIdentityId(), userDto.getRoleIds().split(","));
		}
		return "success";
	}

	/**
	 * 
	  * updateUser 更新用户
	  * @Title: updateUser
	  * @Description: TODO
	  * @param @param userDto
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value="/updateUser",method=RequestMethod.POST)
	@ApiOperation(value="updateUser",notes="更新用户",httpMethod="POST")
	public String updateUser(UserDto userDto) throws Exception {
		User user = dutyService.userOfId(userDto.getUserId());
		//验证用户名是否存在
		if(!user.getLoginName().equals(userDto.getLoginName())){
			UserCriteria criteria = new UserCriteria();
			criteria.loginName = userDto.getLoginName().toUpperCase();
			List<User> result = dutyService.findUsers(criteria);
			if(!result.isEmpty()){
				throw new ItmsAppException("用户名已经存在！");
			}
		}
		if(!user.getPolice().getPoliceCode().equals(userDto.getPoliceCode())){
			//验证警员编号是否存在
			UserCriteria criteria = new UserCriteria();
			criteria.policeCode = userDto.getPoliceCode();
			List<User> result = dutyService.findUsers(criteria);
			if(!result.isEmpty()){
				throw new ItmsAppException("警员编号已经存在！");
			}
		}
		user = userDto.convertToUser();
		//更改user
		dutyService.updateUser(user);
		user = dutyService.userOfId(user.getUserId());
		//删除全部的角色
		if(user.roleIds != null && user.roleIds.size() > 0){
			dutyService.removeUserRoles(user.getUserId(), user.roleIds());
		}	
		//添加新的角色
		if(userDto.getRoleIds() != null && !"".equals(userDto.getRoleIds())){
		dutyService.addRolesToUser(user.getUserId(), userDto.getRoleIds().split(","));
		}
		return "success";
	}

	/**
	 * 删除用户
	 */
	@RequestMapping(value="/deleteUser",method=RequestMethod.POST)
	@ApiOperation(value="delete",notes="删除用户",httpMethod="POST")
	public String delete(String ids) {
		String[] removeIds = ids.split(",");
		for(String id : removeIds){
			dutyService.deleteUser(id);
		}
		return "success";
	}

	@RequestMapping(value="/selectUser",method=RequestMethod.POST)
	@ApiOperation(value="findUser",notes="查找用户",httpMethod="POST")
	public DataGridResult<UserDto> findUser(@ModelAttribute(value = "userDto") UserDto userDto) throws Exception {

		// 构造查询条件
		UserCriteria userCriteria = new UserCriteria();
		// 设置查询页数
		userCriteria.setPageNum(userDto.getPageNumber());
		// 设置每页的最大记录数
		userCriteria.setPageSize(userDto.getPageSize());
		// 设置是否需要统计总数: 是
		userCriteria.setNeedTotal(true); 
		// 获取和设置UI端的输入条件
		userCriteria.orgId = userDto.getOrgId();
		userCriteria.isOnline = userDto.getIsOnline();
		userCriteria.policeCode = userDto.getPoliceCode();
		userCriteria.name = userDto.getPoliceName();
		userCriteria.setOrderName(userDto.getOrderName());
		userCriteria.setOrderType(userDto.getOrderType());
		// 查询用户信息列表
		List<User> list = dutyService.findUsers(userCriteria);
		//查询用户最近最近一周登录次数
		List<String> userNameList = new ArrayList<String>();
		for(User u : list){
			userNameList.add(u.getLoginName());
		}
		
		// 把查询结果转换成查询列表行
		List<UserDto> lstView = new ArrayList<UserDto>();
		for(User item : list){
			UserDto dto = new UserDto(item);
			lstView.add(dto);
		}
		// 创建一个表格对象
		DataGridResult<UserDto> result = new DataGridResult<UserDto>();
		// 设置行数为空
		result.setError("");
		// 设置结果对象
		result.setResult(new Results<UserDto>(userCriteria.getTotal(), lstView));

		return result;
	}
	
	/**
	 * @throws NoSuchAlgorithmException 
	 * 
	  * updateLoginPasswordToDefault  重置用户密码
	  * @Title: updateLoginPasswordToDefault
	  * @Description: TODO
	  * @param @param userId
	  * @param @return    设定文件
	  * @return String    返回类型
	  * @throws
	 */
	@RequestMapping(value="/updateLoginPasswordToDefault",method=RequestMethod.POST)
	@ApiOperation(value="updateLoginPasswordToDefault",notes="重置用户密码",httpMethod="POST")
	public String updateLoginPasswordToDefault(String userId) throws NoSuchAlgorithmException{
		dutyService.updateLoginPassword(userId,MD5Util.md5(ConstValue.DEFAULT_LOGIN_PASSWORD));
		return "success";
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
	@RequestMapping(value="/findUserResource",method=RequestMethod.POST)
	@ApiOperation(value="findUserResourceById",notes="根据用户Id查找用户资源（菜单，功能点）",httpMethod="POST")
	public List<UserResourceTree> findUserResourceById(String userId){
		return dutyService.findUserResourceByUserId(userId);
	}
	
	/**
	 * 根据用户Id查找用户信息
	 * @param currentUserId
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping(value="/findUserById",method=RequestMethod.POST)
	@ApiOperation(value="findUserById",notes="根据用户Id查找用户基本信息",httpMethod="POST")
	public User findUserById(@ApiParam(name="currentUserId",required=true)String currentUserId) throws Exception{
		User user = dutyService.userOfId(currentUserId);
		return user;
	}
	
	@RequestMapping(value="/updatePassword",method=RequestMethod.POST)
	@ApiOperation(value="updatePassword",notes="修改用户密码",httpMethod="POST")
	public String updatePassword(@ApiParam(name="password",required=true)String password,@ApiParam(name="currentUserId",required=true)String currentUserId) throws NoSuchAlgorithmException{
		dutyService.updateLoginPassword(currentUserId,MD5Util.md5(password));
		return "success";
	}
	
	@RequestMapping(value="/findUserSign",method=RequestMethod.POST)
	@ApiOperation(value="findUserSign",notes="获得当前用户的用户签章信息",httpMethod="POST")
	public List<UserSignature> findUserSign(@ApiParam(name="currentUserId",required=true)String currentUserId){
		return dutyService.findUserSigntureByUserId(currentUserId);
	}
	
	@RequestMapping(value="/saveUserSign",method=RequestMethod.POST)
	@ApiOperation(value="saveUserSign",notes="保存用户签章信息",httpMethod="POST")
	public String saveUserSign(@ApiParam(name="currentUserId",required=true)String currentUserId,
			@ApiParam(name="imageUrl",required=true)String imageUrl){
		//删除原有的签章信息
		dutyService.removeUserSignatureByUserId(currentUserId);
		//添加新的签章信息
		UserSignature sign = new UserSignature();
		sign.setUserId(currentUserId);
		sign.setSignatureIndex(1);
		sign.setSignatureImage(imageUrl);
		dutyService.createUserSignature(sign);
		return "success";
	}
	
	@RequestMapping(value="/removeUserSign",method=RequestMethod.POST)
	@ApiOperation(value="removeUserSign",notes="删除用户签章信息",httpMethod="POST")
	public String removeUserSign(@ApiParam(name="currentUserId",required=true)String currentUserId){
		//删除原有的签章信息
		dutyService.removeUserSignatureByUserId(currentUserId);
		return "success";
	}
	
	@RequestMapping(value="/countMenuCurrentMonthTop10")
	public List<UserCount> countMenuCurrentMonthTop10(){
		return dutyService.countMenuCurrentMonthTop10();
	}
	
	@RequestMapping(value="/countOrgLoginCurrentMonthTop10")
	public List<UserCount> countOrgLoginCurrentMonthTop10(){
		return dutyService.countOrgLoginCurrentMonthTop10();
	}
	
	@RequestMapping(value="/countIpLoginCurrentMonthTop10")
	public Map<String,Object> countIpLoginCurrentMonthTop10(){
		List<UserCount> countList = dutyService.countIpLoginCurrentMonthTop10();
		Map<String,Object> result = new HashMap<String,Object>();
		Map<String,Object> dataResult = new HashMap<String,Object>();
		result.put("error", "");
		result.put("result", dataResult);
		dataResult.put("total", 0);
		dataResult.put("rows", countList);
		return result;
	}
	
	@RequestMapping(value="/countLoginTimeByDay")
	public List<UserCount> countLoginTimeByDay(){
		return dutyService.countLoginTimeByDay();
	}
	
}