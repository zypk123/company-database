package cy.its.syscfg.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.config.SysConfig;
import cy.its.syscfg.domain.model.duty.OrgHeader;
import cy.its.syscfg.domain.model.duty.OrgSeat;
import cy.its.syscfg.domain.model.duty.Organization;
import cy.its.syscfg.domain.model.duty.Police;
import cy.its.syscfg.domain.model.duty.User;
import cy.its.syscfg.domain.model.permission.Menu;
import cy.its.syscfg.domain.model.permission.MenuFunction;
import cy.its.syscfg.domain.model.permission.Role;
import cy.its.syscfg.domain.model.permission.RoleFunction;
import cy.its.syscfg.domain.model.permission.RoleFunctionKey;
import cy.its.syscfg.domain.model.sysCode.Code;
import cy.its.syscfg.domain.model.sysCode.CodeType;
import cy.its.syscfg.mybatis.model.Sys_Menu_Function;
import cy.its.syscfg.mybatis.model.T_Sys_Code;
import cy.its.syscfg.mybatis.model.T_Sys_Code_Type;
import cy.its.syscfg.mybatis.model.T_Sys_Config;
import cy.its.syscfg.mybatis.model.T_Sys_Menu;
import cy.its.syscfg.mybatis.model.T_Sys_Org;
import cy.its.syscfg.mybatis.model.T_Sys_OrgWithBLOBs;
import cy.its.syscfg.mybatis.model.T_Sys_Police;
import cy.its.syscfg.mybatis.model.T_Sys_Role;
import cy.its.syscfg.mybatis.model.T_Sys_Role_Permission;
import cy.its.syscfg.mybatis.model.T_Sys_Role_UserKey;
import cy.its.syscfg.mybatis.model.T_Sys_User;

public class Convert {
	/**
	 * 
	 * 
	 * @param sysOrg
	 * @return
	 */
	public static Organization convert(T_Sys_Org sysOrg) {
		if (sysOrg == null) {
			return null;
		}

		OrgHeader orgHeader = new OrgHeader(sysOrg.getOrgHeaderName(),
				sysOrg.getOrgHeaderPhone());
		OrgSeat orgSeat = new OrgSeat();
		Organization organization = new Organization(orgHeader, orgSeat,
				sysOrg.getCreateBy(), sysOrg.getUpdateBy());

		ObjectMapUtils.parseObject(orgSeat, sysOrg);
		ObjectMapUtils.parseObject(organization, sysOrg);

		return organization;
	}

	/**
	 * 
	 * 
	 * @param obj
	 * @return
	 */
	public static T_Sys_OrgWithBLOBs convert(Organization obj) {
		T_Sys_OrgWithBLOBs org = new T_Sys_OrgWithBLOBs();
		ObjectMapUtils.parseObject(org, obj);
		ObjectMapUtils.parseObject(org, obj.orgSeat);
		ObjectMapUtils.parseObject(org, obj.orgHeader);
		return org;
	}
	
	public static User convert(
			final Map<String, List<T_Sys_Role_UserKey>> userRoleMap,
			Map<String, T_Sys_Police> policeMap,
			final T_Sys_User sysUser) throws Exception {

		Police police = new Police();
		ObjectMapUtils.parseObject(police, policeMap.get(sysUser.getPoliceId()));
		
		String userId = sysUser.getUserId();
		List<String> roleIds = userRoleMap.containsKey(userId) ? userRoleMap
				.get(userId).stream().map(c -> c.getRoleId())
				.collect(Collectors.toList()) : null;

		User user = new User(userId, sysUser.getOrgId(), police,
				sysUser.getLoginName(), sysUser.getLoginPassword(),
				sysUser.getPermissionIpList(), sysUser.getIsOnline(),
				sysUser.getLatestLoginTime(), sysUser.getTotalLoginCounts(),
				sysUser.getName(), sysUser.getValidDate(), roleIds);

		ObjectMapUtils.parseObject(user, sysUser);

		return user;
	}

	public static T_Sys_User convert(User obj) {
		T_Sys_User sys_User = new T_Sys_User();
		ObjectMapUtils.parseObject(sys_User, obj);
		sys_User.setPoliceId(obj.getPolice() != null ? obj.getPolice()
				.getIdentityId() : null);
		return sys_User;
	}
	
	public static T_Sys_Police convert(Police obj) {
		T_Sys_Police police = new T_Sys_Police();
		ObjectMapUtils.parseObject(police, obj);
		return police;
	}
	
	public static Role convert(T_Sys_Role sysRole,
			List<T_Sys_Role_Permission> lst) {
		String id = sysRole.getRoleId();
		Role role = new Role(id, sysRole.getRoleName(),
				sysRole.getRoleEnableFlag(), lst != null ? lst
						.stream()
						.map(c -> {
							try {
								return new RoleFunction(new RoleFunctionKey(id,
										c.getFunctionCode()));
							} catch (Exception e) {
								e.printStackTrace();
							}
							return null;
						}).collect(Collectors.toList()) : null);

		role.roleRemark = sysRole.getRoleRemark();
		role.setDataAccessType(sysRole.getDataAccessType());
		return role;
	}

	public static T_Sys_Code_Type convert(CodeType obj) {
		T_Sys_Code_Type codeType = new T_Sys_Code_Type();
		ObjectMapUtils.parseObject(codeType, obj);
		return codeType;
	}

	public static T_Sys_Code convert(Code code) {
		T_Sys_Code sysCode = new T_Sys_Code();
		ObjectMapUtils.parseObject(sysCode, code);
		return sysCode;
	}

	public static CodeType convert(List<Code> lstCode,
			T_Sys_Code_Type ct) {
		try {
			CodeType codeType = new CodeType(ct.getCodeType(),
					ct.getCodeTypeName(), lstCode);
			ObjectMapUtils.parseObject(codeType, ct);
			return codeType;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Code convert(T_Sys_Code c) {
		Code code = new Code(c.getCodeId(), c.getCodeNo(), c.getCodeType(),
				c.getCodeName(), c.getRemark(), c.getEnableFlag());
		ObjectMapUtils.parseObject(code, c);
		return code;
	}
	
	public static SysConfig convert(T_Sys_Config config){
		SysConfig sysConfig=new SysConfig(config.getConfigId(),
				config.getConfigName()
				,config.getConfigRemark()
				,config.getKey()
				,config.getValue()
				,config.getValueType()
				,config.getSelectedValue()
				,config.getSelectedValueDesc()
				,config.getDefaultValue()
				,config.getType());
		return sysConfig;
	}
	
	public static Menu convert(List<Sys_Menu_Function> lstMf) {
		try {
			if (lstMf == null || lstMf.size() == 0) {
				return null;
			}
			Sys_Menu_Function sysMF = lstMf.get(0);
			Menu menu = new Menu(sysMF.getMenuCode(), sysMF.getMenuName(),
					sysMF.getTargetUrl(), sysMF.getSortIndex(),
					sysMF.getMenuEnableFlag(), lstMf
							.stream()
							.map((mf) -> {
								try {
									return new MenuFunction(mf
											.getFunctionCode(), mf
											.getFunctionName(), mf
											.getFunctionFlag(), mf
											.getMenuCode());
								} catch (Exception e) {
									return null;
								}
							}).collect(Collectors.toList()));

			ObjectMapUtils.parseObject(menu, sysMF);

			return menu;

		} catch (Exception e) {
			e.printStackTrace();
		}

		return null;
	}
	

	public static T_Sys_Menu convert(Menu obj) {
		T_Sys_Menu sysMenu = new T_Sys_Menu();
		ObjectMapUtils.parseObject(sysMenu, obj);
		return sysMenu;
	}

}
