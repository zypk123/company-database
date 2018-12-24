package cy.its.syscfg.domain.repository.permission;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.model.permission.Role;
import cy.its.syscfg.domain.model.permission.RoleFunction;


public interface IRoleRepository extends IRepository<Role> {
	List<Role> findAllRoles(String orgCode);
	
	void removeAllFunctions(String roleId);
	
	void saveAllFunctions(RoleFunction[] roleFunctions);

	List<String> findSubsribeKeyByRoleIds(List<String> roleIds);

	void deleteAlarmSubsribePermission(String roleId);

	void saveAlarmSubsribePermission(String roleId, String key);
}
