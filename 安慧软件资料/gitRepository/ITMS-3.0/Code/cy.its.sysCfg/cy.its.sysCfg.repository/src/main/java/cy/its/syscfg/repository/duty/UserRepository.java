package cy.its.syscfg.repository.duty;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.constant.ConstValue;
import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.platform.common.utils.RedisPoolUtil;
import cy.its.platform.common.utils.SqlHelper;
import cy.its.syscfg.data.OrgData;
import cy.its.syscfg.domain.criteria.UserCriteria;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.model.duty.UserCount;
import cy.its.syscfg.domain.model.duty.UserResourceTree;
import cy.its.syscfg.domain.model.duty.UserSignature;
import cy.its.syscfg.domain.repository.duty.IUserRepository;
import cy.its.syscfg.mybatis.client.CustomMapper;
import cy.its.syscfg.mybatis.client.T_Sys_CodeMapper;
import cy.its.syscfg.mybatis.client.T_Sys_PoliceMapper;
import cy.its.syscfg.mybatis.client.T_Sys_Role_UserMapper;
import cy.its.syscfg.mybatis.client.T_Sys_UserMapper;
import cy.its.syscfg.mybatis.client.UserSignatureMapper;
import cy.its.syscfg.mybatis.model.LoginTime;
import cy.its.syscfg.mybatis.model.T_Sys_Police;
import cy.its.syscfg.mybatis.model.T_Sys_Role_UserKey;
import cy.its.syscfg.mybatis.model.T_Sys_User;
import cy.its.syscfg.mybatis.model.UserResource;
import cy.its.syscfg.util.Convert;

@Service
public class UserRepository implements IUserRepository {

	@Autowired
	private T_Sys_CodeMapper t_Sys_CodeMapper;
	@Autowired
	private T_Sys_UserMapper t_Sys_UserMapper;
	@Autowired
	private CustomMapper customMapper;
	@Autowired
	private T_Sys_Role_UserMapper t_Sys_Role_UserMapper;
	@Autowired
	private OrgData orgData;
	@Autowired
	private T_Sys_PoliceMapper t_Sys_PoliceMapper;
	@Autowired
	private UserSignatureMapper userSignatureMapper;

	@Override
	public void removeUserRoles(String userId) {
		T_Sys_Role_UserKey userRoleKey = new T_Sys_Role_UserKey();
		userRoleKey.setUserId(userId);
		customMapper.deleteUserRole(userRoleKey);
	}

	@Override
	public void addUserRoles(String userId, String... roleIds) {
		T_Sys_Role_UserKey key = new T_Sys_Role_UserKey();
		key.setUserId(userId);
		for (String roleId : roleIds) {
			key.setRoleId(roleId);
			t_Sys_Role_UserMapper.insertSelective(key);
		}
	}

	@Override
	public List<User> findUsers(UserCriteria criteria) throws Exception {
		//查出用户信息
		Map<String, Object> paramMap = ParamUtil.parseParams(criteria);
		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize(), true);
		if(!StringUtil.isNullOrEmpty(criteria.getOrderName())){
			PageHelper.orderBy(criteria.getOrderName() + " " + criteria.getOrderType());
		}
		Page<T_Sys_User> page = (Page<T_Sys_User>) customMapper.selectUsers(paramMap);
		criteria.setTotal((int) page.getTotal());
		List<T_Sys_User> userList = page.getResult();
		//获取用户登录状态
		List<String> currentUserIds = RedisPoolUtil.getKeyList("VU:");
		//获取用户ID和警员ID列表
		List<String> userIdList = new ArrayList<String>();
		List<String> policeIdList = new ArrayList<String>();
		for(T_Sys_User user : userList){
			userIdList.add(user.getUserId());
			policeIdList.add(user.getPoliceId());
			//写入登录状态
			for(String userId : currentUserIds){
				if(user.getUserId().equals(userId)){
					user.setIsOnline("1");
				}
			}
		}
		
		//查找权限信息
		List<T_Sys_Role_UserKey> lstRoleUsers = null;
		if(!userIdList.isEmpty()){
			lstRoleUsers = customMapper.selectUserRolesByUserIds(userIdList);
		}else{
			lstRoleUsers = new ArrayList<T_Sys_Role_UserKey>();
		}
		//查询警员信息
		List<T_Sys_Police> lstPolices = null;
		if(!policeIdList.isEmpty()){
			lstPolices = t_Sys_PoliceMapper.selectPolices(policeIdList);
		}else{
			lstPolices = new ArrayList<T_Sys_Police>();
		}
		Map<String, List<T_Sys_Role_UserKey>> userRoleMap = getUserRoleMap(lstRoleUsers);
		Map<String, T_Sys_Police> policeMap = lstPolices.stream().collect(
				Collectors.toMap(T_Sys_Police::getPoliceId, (p) -> p));

		return page.getResult().stream().map(sysUser -> {
			try {
				return Convert.convert(userRoleMap, policeMap, sysUser);
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}).collect(Collectors.toList());
	}

	@Override
	public int countUsers(UserCriteria criteria) {
		Map<String, Object> paramMap = ParamUtil.parseParams(criteria);
		return customMapper.countUsers(paramMap);
	}

	@Override
	public User aggregateOfId(String id) throws Exception {
		T_Sys_User sysUser = t_Sys_UserMapper.selectByPrimaryKey(id);
		if(sysUser == null){
			return null;
		}
		T_Sys_Role_UserKey key = new T_Sys_Role_UserKey();
		key.setUserId(id);
		List<T_Sys_Role_UserKey> lstRoleUsers = customMapper
				.selectUserRoles(key);

		T_Sys_Police t_Sys_Police = t_Sys_PoliceMapper
				.selectByPrimaryKey(sysUser.getPoliceId());
		Map<String, T_Sys_Police> policeMap = new HashMap<String, T_Sys_Police>(1);
		if (t_Sys_Police != null) {
			policeMap.put(t_Sys_Police.getPoliceId(), t_Sys_Police);
		}

		return Convert
				.convert(getUserRoleMap(lstRoleUsers), policeMap, sysUser);
	}

	@Override
	public String save(User obj) {
		try {
			obj.setUserId(StringUtil.generateUUID());
		} catch (Exception e) {
		}
		
		if (obj.getPolice() != null) {
			obj.getPolice().setPoliceId(StringUtil.generateUUID());
			T_Sys_Police police = new T_Sys_Police();
			ObjectMapUtils.parseObject(police, obj.getPolice());
			t_Sys_PoliceMapper.insertSelective(police);
		}

		T_Sys_User sysUser = new T_Sys_User();
		ObjectMapUtils.parseObject(sysUser, obj);
		ObjectMapUtils.parseObject(sysUser, obj.getPolice());
		t_Sys_UserMapper.insertSelective(sysUser);

		return sysUser.getUserId();
	}

	@Override
	public int delete(String id) {
		return t_Sys_UserMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(User obj) {
		if (obj.getPolice() != null) {
			T_Sys_Police police = new T_Sys_Police();
			ObjectMapUtils.parseObject(police, obj.getPolice());
			t_Sys_PoliceMapper.updateByPrimaryKeySelective(police);
		}

		T_Sys_User sys_User = Convert.convert(obj);
		return t_Sys_UserMapper.updateByPrimaryKeySelective(sys_User);
	}

	@Override
	public void removeUserRolesByRoleId(String roleId) {
		T_Sys_Role_UserKey userRoleKey = new T_Sys_Role_UserKey();
		userRoleKey.setRoleId(roleId);
		customMapper.deleteUserRole(userRoleKey);
	}

	@Override
	public void removeUserRoles(String userId, String... roleIds) {
		T_Sys_Role_UserKey key = new T_Sys_Role_UserKey();
		key.setUserId(userId);
		for (String roleId : roleIds) {
			key.setRoleId(roleId);
			t_Sys_Role_UserMapper.deleteByPrimaryKey(key);
		}
	}

	// private Map<String, Object> parseParam(UserCriteria criteria) {
	// Map<String, Object> paramMap = ParamUtil.parseParams(criteria);
	// String orgPrivilegeCode = "";
	// if(!StringUtil.isNullOrEmpty(criteria.orgId)){
	// Organization org = orgData.dataOfId(criteria.orgId);
	// if(org != null){
	// orgPrivilegeCode = org.orgPrivilegeCode;
	// }
	// }
	//
	// paramMap.put("orgPrivilegeCode", orgPrivilegeCode);
	//
	// return paramMap;
	// }

	private Map<String, List<T_Sys_Role_UserKey>> getUserRoleMap(
			List<T_Sys_Role_UserKey> lstRoleUsers) {
		return lstRoleUsers.stream().collect(
				Collectors.groupingBy(T_Sys_Role_UserKey::getUserId));
	}

	/*
	  * <p>Title: updateLoginPasswordToDefault</p>
	  * <p>Description: </p>
	  * @param userId
	  * @param password
	  * @see cy.its.syscfg.domain.repository.duty.IUserRepository#updateLoginPasswordToDefault(java.lang.String, java.lang.String)
	  */
	
	
	@Override
	public void updateLoginPassword(String userId, String password) {
		// TODO Auto-generated method stub
		t_Sys_UserMapper.updateLoginPassword(userId,password);
	}
	
	@Override
	public List<UserResourceTree> findUserResourceByUserId(String userId){
		//定义好返回列表
		List<UserResourceTree> returnList = new ArrayList<UserResourceTree>();
		//查询车
		List<UserResource> resourceList = t_Sys_UserMapper.selectUserResource(userId);
		//分组一级菜单
		Map<String,List<UserResource>> menuLevel1Map = new LinkedHashMap<String,List<UserResource>>();
		for(UserResource item : resourceList){
			String key = item.getMenuLevel1Code();
			if(key == null || "".equals(key)){
				continue;
			}
			if(menuLevel1Map.containsKey(key)){
				menuLevel1Map.get(key).add(item);
			}else{
				List<UserResource> itemList = new ArrayList<UserResource>();
				itemList.add(item);
				menuLevel1Map.put(key, itemList);
			}
		}
		Iterator<Entry<String,List<UserResource>>> itor1 = menuLevel1Map.entrySet().iterator();
		while(itor1.hasNext()){
			List<UserResource> subList1 = itor1.next().getValue();
			//创建一级资源
			UserResourceTree resourceTreeLevel1 = new UserResourceTree();
			resourceTreeLevel1.setResourceCode(subList1.get(0).getMenuLevel1Code());
			resourceTreeLevel1.setResourceName(subList1.get(0).getMenuLevel1Name());
			resourceTreeLevel1.setResourceImageURL(subList1.get(0).getMenuLevel1Image());
			resourceTreeLevel1.setResourceType(ConstValue.USER_RESOURCE_TYPE_MENU);
			resourceTreeLevel1.setResourceURL(subList1.get(0).getTargetURL1());
			//二级菜单分组
			Map<String,List<UserResource>> menuLevel2Map = new LinkedHashMap<String,List<UserResource>>();
			for(UserResource item : subList1){
				String key = item.getMenuLevel2Code();
				if(key == null || "".equals(key)){
					continue;
				}
				if(menuLevel2Map.containsKey(key)){
					menuLevel2Map.get(key).add(item);
				}else{
					List<UserResource> itemList = new ArrayList<UserResource>();
					itemList.add(item);
					menuLevel2Map.put(key, itemList);
				}
			}
			Iterator<Entry<String,List<UserResource>>> itor2 = menuLevel2Map.entrySet().iterator();
			while(itor2.hasNext()){
				List<UserResource> subList2 = itor2.next().getValue();
				//创建二级资源
				UserResourceTree resourceTreeLevel2 = new UserResourceTree();
				resourceTreeLevel2.setResourceCode(subList2.get(0).getMenuLevel2Code());
				resourceTreeLevel2.setResourceName(subList2.get(0).getMenuLevel2Name());
				resourceTreeLevel2.setResourceImageURL(subList2.get(0).getMenuLevel2Image());
				resourceTreeLevel2.setResourceType(ConstValue.USER_RESOURCE_TYPE_MENU);
				resourceTreeLevel2.setResourceURL(subList2.get(0).getTargetURL2());
				//三级菜单分组
				Map<String,List<UserResource>> menuLevel3Map = new LinkedHashMap<String,List<UserResource>>();
				for(UserResource item : subList2){
					String key = item.getMenuLevel3Code();
					if(key == null || "".equals(key)){
						continue;
					}
					if(menuLevel3Map.containsKey(key)){
						menuLevel3Map.get(key).add(item);
					}else{
						List<UserResource> itemList = new ArrayList<UserResource>();
						itemList.add(item);
						menuLevel3Map.put(key, itemList);
					}
				}
				Iterator<Entry<String,List<UserResource>>> itor3 = menuLevel3Map.entrySet().iterator();
				while(itor3.hasNext()){
					List<UserResource> subList3 = itor3.next().getValue();
					//创建三级资源
					UserResourceTree resourceTreeLevel3 = new UserResourceTree();
					resourceTreeLevel3.setResourceCode(subList3.get(0).getMenuLevel3Code());
					resourceTreeLevel3.setResourceName(subList3.get(0).getMenuLevel3Name());
					resourceTreeLevel3.setResourceImageURL(subList3.get(0).getMenuLevel3Image());
					resourceTreeLevel3.setResourceType(ConstValue.USER_RESOURCE_TYPE_MENU);
					resourceTreeLevel3.setResourceURL(subList3.get(0).getTargetURL3());
					//创建功能点资源
					for(UserResource functionResource : subList3){
						UserResourceTree resourceTreeFunction = new UserResourceTree();
						resourceTreeFunction.setResourceCode(functionResource.getFunctionCode());
						resourceTreeFunction.setResourceName(functionResource.getFunctionName()); 
						resourceTreeFunction.setResourceType(ConstValue.USER_RESOURCE_TYPE_FUNCTION);
						resourceTreeLevel3.getSubResource().add(resourceTreeFunction);
					}
					resourceTreeLevel2.getSubResource().add(resourceTreeLevel3);
				}
				resourceTreeLevel1.getSubResource().add(resourceTreeLevel2);
			}
			returnList.add(resourceTreeLevel1);
		}
		return returnList;
	}

	@Override
	public List<UserSignature> findUserSignatureByUserId(String userId) {
		return userSignatureMapper.findSignatureByUserId(userId);
	}

	@Override
	public void saveUserSignature(UserSignature sign) {
		sign.setSignatureId(StringUtil.generateUUID());
		userSignatureMapper.saveUserSignature(sign);
	}
	
	@Override
	public void removeUserSignature(String userId){
		userSignatureMapper.removeUserSignature(userId);
	}

	@Override
	public Map<String, Object[]> getUserLoingTimeInWeek(List<String> userNameList) {
		List<LoginTime> resultList = t_Sys_UserMapper.countUserLoginTimeInWeek(userNameList);
		Map<String,Object[]> returnMap = new HashMap<String,Object[]>();
		for(LoginTime time : resultList){
			returnMap.put(time.getUserName(), new Object[]{time.getLoginTime(),time.getLastLoginTime()});
		}
		return returnMap;
	}

	@Override
	public int loginCountCurMonthOfUser(String userId) {
		return t_Sys_UserMapper.countLoginTimesInCurMonth(userId);
	}

	@Override
	public int loginTotalOfUser(String userId) {
		return t_Sys_UserMapper.countLoginTotal(userId);
	}

	@Override
	public List<UserCount> countLoginTimeByDay() {
		return t_Sys_UserMapper.countLoginTimeByDay();
	}

	@Override
	public List<UserCount> countIpLoginCurrentMonthTop10() {
		PageHelper.startPage(1, 10, false);
		Page<UserCount> page = (Page<UserCount>) t_Sys_UserMapper.countIpLoginCurrentMonthTop10();
		return page.getResult();
	}

	@Override
	public List<UserCount> countOrgLoginCurrentMonthTop10() {
		PageHelper.startPage(1, 10, false);
		Page<UserCount> page = (Page<UserCount>) t_Sys_UserMapper.countOrgLoginCurrentMonthTop10();
		return page.getResult();
	}

	@Override
	public List<UserCount> countMenuCurrentMonthTop10() {
		PageHelper.startPage(1, 10, false);
		Page<UserCount> page = (Page<UserCount>) t_Sys_UserMapper.countMenuCurrentMonthTop10();
		return page.getResult();
	}
}
