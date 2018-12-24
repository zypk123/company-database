package cy.its.sysLog.domain;

import java.util.Date;

/**
* @Title: OperateMenu.java 
* @Package cy.its.sysLog.domain 
* @Description: 用户操作菜单记录
* @author lil@cychina.cn
* @date 2016年3月11日 下午3:46:19 
* @version V1.0   
 */
public class OperateMenu {
	
	private String userId;

	private String menuId;

	private String menName;

	private Date operateTime;

	private String remark;

	private String orgId;

	private String operIp;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getMenName() {
        return menName;
    }

    public void setMenName(String menName) {
        this.menName = menName;
    }

    public Date getOperateTime() {
        return operateTime;
    }

    public void setOperateTime(Date operateTime) {
        this.operateTime = operateTime;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
    
	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOperIp() {
		return operIp;
	}

	public void setOperIp(String operIp) {
		this.operIp = operIp;
	}
}