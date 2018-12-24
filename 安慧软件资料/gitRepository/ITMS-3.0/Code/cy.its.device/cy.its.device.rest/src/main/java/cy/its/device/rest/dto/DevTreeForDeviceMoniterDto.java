/**
 * @Title: DevTreeForDeviceMoniterDto.java
 * @Package cy.its.device.rest.dto
 * @Description: 地图监控页面设备点位树节点对象
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月9日 上午9:29:54
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.rest.dto;

import java.util.List;
import java.util.Map;

/**
  * @ClassName: DevTreeForDeviceMoniterDto
  * @Description: 地图监控页面设备点位树节点对象
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年11月9日 上午9:29:54
  *
  */

public class DevTreeForDeviceMoniterDto {
	private String id;		//树ID,一级节点表示设备类型代码，二级节点表示设备编号
	private String text;	//树名称，一级节点表示设备类型名称，二级节点表示设备名称
	private String iconCls; //节点图标css
	private boolean checked=true;
	public boolean getChecked() {
		return checked;
	}
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	private List<DevTreeForDeviceMoniterDto> children;
	/**
	 * 其他属性
	 */
	private  Map<String,Object> attribute;//设备节点时候存放设备状态
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
	 * @return the iconCls
	 */
	
	public String getIconCls() {
		return iconCls;
	}
	/**
	 * setter method
	 * @param iconCls the iconCls to set
	 */
	
	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
	/**
	 * getter method
	 * @return the children
	 */
	
	public List<DevTreeForDeviceMoniterDto> getChildren() {
		return children;
	}
	/**
	 * setter method
	 * @param children the children to set
	 */
	
	public void setChildren(List<DevTreeForDeviceMoniterDto> children) {
		this.children = children;
	}
	/**
	 * getter method
	 * @return the attribute
	 */
	
	public Map<String, Object> getAttribute() {
		return attribute;
	}
	/**
	 * setter method
	 * @param attribute the attribute to set
	 */
	
	public void setAttribute(Map<String, Object> attribute) {
		this.attribute = attribute;
	}
	
}


