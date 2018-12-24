/**
 * @Title: MenuTreeDto.java
 * @Package cy.its.sysCfg.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author zuop zuop@cychina.cn
 * @date 2015年10月29日 上午11:23:17
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.dto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.model.permission.Menu;

/**
  * @ClassName: MenuTreeDto
  * @Description: 菜单树对象
  * @author zuop zuop@cychina.cn
  * @date 2015年10月29日 上午11:23:17
  *
  */

public class MenuTreeDto {
	
	/**
	 * 菜单id
	 */
	private String id;
	/**
	 * 菜单名称
	 */
	private String text;
	
	/**
	 * 子节点
	 */
	private List<MenuTreeDto> children;
	
	/**
	 * 其他属性
	 */
	private Map<String,String> attribute = new HashMap<String,String>();

	/**
	
	  * 创建一个新的实例 MenuTreeDto. 利用菜单和功能点集合
	  * <p>Title: </p>
	  * <p>Description: </p>
	  * @param menuList 菜单领域对象集合
	 * @param functionList 功能点集合
	  */
	public MenuTreeDto(List<Menu> menuList, List<Menu> functionList) {
		//初始化根节点
		this.id = "root";
		this.text = "管控平台3.0";
		//组装子节点
		List<MenuTreeDto> children = new ArrayList<MenuTreeDto>();
		//初始化菜单节点
		setMenuInfoChildren(this.id,children,menuList);
		this.setChildren(children);
		//初始化功能点节点
		setFunctionInfoChildren(this,functionList);
	}
	/**
	  * setFunctionInfoChildren 将函数添加到树节点中
	  *
	  * @Title: setFunctionInfoChildren
	  * @Description: TODO
	  * @param @param menuTreeDto
	  * @param @param functionList    设定文件
	  * @return void    返回类型
	  * @throws
	  */
	private void setFunctionInfoChildren(MenuTreeDto menuTreeDto, List<Menu> functionList) {
		List<MenuTreeDto> fnChildren = menuTreeDto.getChildren();
		if(fnChildren != null && !fnChildren.isEmpty()){
			fnChildren.forEach(item -> {
				setFunctionInfoChildren(item,functionList);
			});
		}else{
			functionList.forEach(item -> {
				if(item.getMenuCode().equals(menuTreeDto.getId())){
					item.allFunctions().stream().forEach(fn -> {
						MenuTreeDto fnNode = new MenuTreeDto();
						fnNode.setId(fn.getIdentityId());
						fnNode.setText(fn.getFunctionName());
						fnChildren.add(fnNode);
					});
				}
			});
		}
	}
	/**
	  * 无参构造函数
	  * <p>Title: </p>
	  * <p>Description: </p>
	 */
	public MenuTreeDto(){
		
	}
	/**
	 * 
	  * setMenuInfoChildren 将菜单添加到子节点中
	  * @Title: setMenuInfoChildren
	  * @Description: TODO
	  * @param @param children
	  * @param @param menuList    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private void setMenuInfoChildren(String parentId,List<MenuTreeDto> children,List<Menu> menuList){
		menuList.forEach(item ->{
			if(parentId.equals(item.parentCode)){
				MenuTreeDto treeItem = new MenuTreeDto();
				treeItem.setId(item.getIdentityId());
				treeItem.setText(item.getMenuName());
				treeItem.getAttribute().put("url", item.getTargetUrl());
				treeItem.getAttribute().put("image", item.getMenuImageUrl());
				List<MenuTreeDto> itemChildren = new ArrayList<MenuTreeDto>();
				setMenuInfoChildren(item.getIdentityId(),itemChildren,menuList);
				treeItem.setChildren(itemChildren);
				children.add(treeItem);
			}
		});
	}
	/**
	 * getter method
	 * @return the id
	 */
	
	public String getId() {
		return id;
	}

	/**
	 * setter method
	 * @param id the id to set
	 */
	
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * getter method
	 * @return the text
	 */
	
	public String getText() {
		return text;
	}

	/**
	 * setter method
	 * @param text the text to set
	 */
	
	public void setText(String text) {
		this.text = text;
	}

	/**
	 * getter method
	 * @return the children
	 */
	
	public List<MenuTreeDto> getChildren() {
		return children;
	}

	/**
	 * setter method
	 * @param children the children to set
	 */
	
	public void setChildren(List<MenuTreeDto> children) {
		this.children = children;
	}
	public Map<String, String> getAttribute() {
		return attribute;
	}
	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}
	
}
