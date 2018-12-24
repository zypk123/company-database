package ah.its.wrokflow.dto;

import java.util.ArrayList;
import java.util.List;

public class Menu {
	private List<MenuNode> menu;

	public Menu() {
		super();
		menu=new ArrayList<MenuNode>();
	}

	public List<MenuNode> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuNode> menu) {
		this.menu = menu;
	}
	
	public static void addMenu(Menu menu,MenuNode subMenu){
		if(menu==null||subMenu==null){
			return;
		}
		if(subMenu.getMenuType().equals("1")){
			menu.getMenu().add(subMenu);
			return;
		}
		List<MenuNode> childMenu=menu.getMenu();
		if(childMenu!=null){
			for(MenuNode tmp:childMenu){
				addMenuNode(tmp,subMenu);
			}
		}
	}
	public static void addMenuNode(MenuNode parentMenuNode,MenuNode childMenuNode){
		if(parentMenuNode==null||childMenuNode==null){
			return;
		}
		if(childMenuNode.getMenuParentId().equals(parentMenuNode.getMenuId())){
			parentMenuNode.getChildNode().add(childMenuNode);
			return;
		}
		for(MenuNode tmp:parentMenuNode.getChildNode()){
			addMenuNode(tmp,childMenuNode);
		}
	}
}