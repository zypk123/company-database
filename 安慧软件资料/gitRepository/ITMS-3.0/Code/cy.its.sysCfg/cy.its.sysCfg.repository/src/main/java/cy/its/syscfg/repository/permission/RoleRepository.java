package cy.its.syscfg.repository.permission;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.ObjectMapUtils;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.model.permission.Role;
import cy.its.syscfg.domain.model.permission.RoleFunction;
import cy.its.syscfg.domain.repository.permission.IRoleRepository;
import cy.its.syscfg.mybatis.client.CustomMapper;
import cy.its.syscfg.mybatis.client.T_Sys_RoleMapper;
import cy.its.syscfg.mybatis.client.T_Sys_Role_PermissionMapper;
import cy.its.syscfg.mybatis.model.T_Sys_Role;
import cy.its.syscfg.mybatis.model.T_Sys_Role_Permission;
import cy.its.syscfg.mybatis.model.T_Sys_Role_PermissionKey;
import cy.its.syscfg.util.Convert;

@Service
public class RoleRepository implements IRoleRepository {

	@Autowired
	T_Sys_RoleMapper t_Sys_RoleMapper;
	
	@Autowired
	T_Sys_Role_PermissionMapper t_Sys_Role_PermissionMapper;

	@Autowired
	CustomMapper customMapper;

	@Override
	public Role aggregateOfId(String id) {
		T_Sys_Role sysRole = t_Sys_RoleMapper.selectByPrimaryKey(id);
		
		T_Sys_Role_PermissionKey key = new T_Sys_Role_PermissionKey();
		key.setRoleId(id);
		List<T_Sys_Role_Permission> lst = customMapper
				.selectRolePermissions(key);
		
		return Convert.convert(sysRole, lst);
	}

	@Override
	public String save(Role obj) {
		obj.setRoleId(StringUtil.generateUUID());
		T_Sys_Role sysRole = new T_Sys_Role();
		ObjectMapUtils.parseObject(sysRole, obj);
		t_Sys_RoleMapper.insertSelective(sysRole);

		return sysRole.getRoleId();
	}

	@Override
	public int delete(String id) {
		return t_Sys_RoleMapper.deleteByPrimaryKey(id);
	}

	@Override
	public int update(Role obj) {
		T_Sys_Role sysRole = new T_Sys_Role();
		ObjectMapUtils.parseObject(sysRole, obj);

		return t_Sys_RoleMapper.updateByPrimaryKeySelective(sysRole);
	}

	@Override
	public List<Role> findAllRoles(String orgCode) {
		List<T_Sys_Role> lstSysRole = customMapper.selectAllRoles(orgCode);
		List<T_Sys_Role_Permission> lstRolePermission = customMapper
				.selectRolePermissions(new T_Sys_Role_PermissionKey());
		Map<String, List<T_Sys_Role_Permission>> mapRolePermissionMap = lstRolePermission
				.stream()
				.collect(
						Collectors.groupingBy(T_Sys_Role_Permission::getRoleId));

		return lstSysRole
				.stream()
				.map(sysRole -> {
					String id = sysRole.getRoleId();
					List<T_Sys_Role_Permission> lst = mapRolePermissionMap
							.containsKey(id) ? mapRolePermissionMap.get(id)
							: null;
							
					return Convert.convert(sysRole, lst);

				}).collect(Collectors.toList());
	}


	@Override
	public void removeAllFunctions(String roleId) {
		T_Sys_Role_PermissionKey key = new T_Sys_Role_PermissionKey();
		key.setRoleId(roleId);
		customMapper.deleteRolePermissions(key);
	}

	@Override
	public void saveAllFunctions(RoleFunction[] roleFunctions) {
		for (RoleFunction roleFunction : roleFunctions) {			
			T_Sys_Role_Permission rolePermission = new T_Sys_Role_Permission();			
			ObjectMapUtils.parseObject(rolePermission, roleFunction);
			ObjectMapUtils.parseObject(rolePermission, roleFunction.getRoleFunctionKey());
			t_Sys_Role_PermissionMapper.insertSelective(rolePermission);
		}
	}

	@Override
	public List<String> findSubsribeKeyByRoleIds(List<String> roleIds) {
		return t_Sys_RoleMapper.findSubsribeKeyByRoleIds(roleIds);
	}

	@Override
	public void deleteAlarmSubsribePermission(String roleId) {
		t_Sys_RoleMapper.deleteAlarmSubsribePermission(roleId);
	}

	@Override
	public void saveAlarmSubsribePermission(String roleId, String key) {
		t_Sys_RoleMapper.saveAlarmSubsribePermission(roleId,key);
	}
}
