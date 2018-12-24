package cy.its.syscfg.domain.repository.permission;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.model.permission.Menu;
import cy.its.syscfg.domain.model.permission.MenuFunction;

public interface IMenuRepository extends IRepository<Menu> {
	List<Menu> findMenuFunctions();

	void addFunction(MenuFunction menuFunction);
	void deleteFunctionOfCode(String functionCode);
	void deleteFunctionsOfMenu(String menuCode);
	void modifyFunction(MenuFunction menuFunction);
	/**
	 * 
	  * findAllMenus 查找所有的菜单
	  * @Title: findAllMenus
	  * @Description: TODO
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	 */
	List<Menu> findAllMenus();

	List<Menu> menusOfUser(String userId);
	
	/**
	 * 根据全新ID查找功能点集合
	 * @param roleIds
	 * @return
	 */
	List<String> findFunctionCodesByRoleIds(List<String> roleIds);

	
	List<MenuFunction> allFuctions();
}
