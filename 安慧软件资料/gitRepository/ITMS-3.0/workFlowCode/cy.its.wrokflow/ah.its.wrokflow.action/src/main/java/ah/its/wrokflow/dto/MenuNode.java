package ah.its.wrokflow.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MenuNode {
    private String menuId;
    /**
     * 1 一级菜单 2 子菜单
     */
    private String menuType;

    private String menuName;

    private String menuUrl;

    private String menuParentId;

    private String remark;

    private Date createTime;

    private Date updateTime;

    private String operater;
    
    private List<MenuNode> childNode;
    
    public MenuNode() {
		super();
		childNode=new ArrayList<MenuNode>();
	}

	public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenuType() {
        return menuType;
    }

    public void setMenuType(String menuType) {
        this.menuType = menuType;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }

    public String getMenuParentId() {
        return menuParentId;
    }

    public void setMenuParentId(String menuParentId) {
        this.menuParentId = menuParentId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getOperater() {
        return operater;
    }

    public void setOperater(String operater) {
        this.operater = operater;
    }

	public List<MenuNode> getChildNode() {
		return childNode;
	}

	public void setChildNode(List<MenuNode> childNode) {
		this.childNode = childNode;
	}
}