package cy.its.syscfg.domain.model.permission;

import java.util.List;

import cy.its.com.domain.AggregateRoot;

public class Menu extends AggregateRoot {

	/**
	 * 菜单编码
	 */
	private String menuCode;

	/**
	 * 菜单名称
	 */
	private String menuName;

	/**
	 * 菜单地址
	 */
	private String targetUrl;

	/**
	 * 父菜单编码
	 */
	public String parentCode;

	/**
	 * 排序索引
	 */
	private String sortIndex;

	/**
	 * 菜单启用标记
	 */
	private String menuEnableFlag;
	
	/**
	 * 图标名称
	 */
	private String menuImageUrl;

	/**
	 * 备注
	 */
	public String remark;

	/**
	 * 系统功能列表
	 */
	private List<MenuFunction> functions;
	
	public Menu(){
		
	}
	public Menu(String menuCode, String menuName, String targetUrl,
			String sortIndex, String menuEnableFlag,
			List<MenuFunction> functions
			) throws Exception {
		this.setMenuCode(menuCode);
		this.setMenuName(menuName);
		this.setTargetUrl(targetUrl);
		this.setSortIndex(sortIndex);
		this.setMenuEnableFlag(menuEnableFlag);
		this.functions = functions;
	}

	@Override
	public String getIdentityId() {
		return menuCode;
	}

	/**
	 * 查看所有菜单功能
	 * @return
	 */
	public List<MenuFunction> allFunctions() {
		return functions;
	}

	public void setMenuCode(String menuCode) throws Exception {
		NotNull(menuCode, "菜单编码");
		this.menuCode = menuCode;
	}

	public String getMenuCode() {
		return menuCode;
	}

	public void setMenuName(String menuName) throws Exception {
		NotNull(menuName, "菜单名称");
		this.menuName = menuName;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setTargetUrl(String targetUrl) throws Exception {
		//NotNull(targetUrl, "菜单地址");
		this.targetUrl = targetUrl;
	}

	public String getTargetUrl() {
		return targetUrl;
	}

	public void setSortIndex(String sortIndex) throws Exception {
		NotNull(sortIndex, "排序索引");
		this.sortIndex = sortIndex;
	}

	public String getSortIndex() {
		return sortIndex;
	}

	public void setMenuEnableFlag(String menuEnableFlag) throws Exception {
		NotNull(menuEnableFlag, "菜单启用标记");
		this.menuEnableFlag = menuEnableFlag;
	}

	public String getMenuEnableFlag() {
		return menuEnableFlag;
	}
	public String getMenuImageUrl() {
		return menuImageUrl;
	}
	public void setMenuImageUrl(String menuImageUrl) {
		this.menuImageUrl = menuImageUrl;
	}
	
}
