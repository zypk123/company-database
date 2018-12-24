package cy.its.video.rest.dto;

import java.util.List;
import java.util.Map;

/**
 * easyui的tree结构体
 * 
 * @author jinhaibo
 *
 */
public class easyUITree {

	/**
	 * 编码
	 */
	private String id;

	/**
	 * 文本
	 */
	private String text;

	/**
	 * 状态
	 */
	private String state;

	/**
	 * 图标
	 */
	private String iconCls;

	/**
	 * 子节点
	 */
	private List<easyUITree> children;

	/**
	 * 其他属性
	 */
	private Map<String, String> attribute;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<easyUITree> getChildren() {
		return children;
	}

	public void setChildren(List<easyUITree> children) {
		this.children = children;
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}

	public String getIconCls() {
		return iconCls;
	}

	public void setIconCls(String iconCls) {
		this.iconCls = iconCls;
	}
}
