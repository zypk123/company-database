package cy.its.device.rest.dto;

import java.util.List;
import java.util.Map;

public class SiteTreeDto {

	/**
	 * id
	 */
	private String id;
	
	/**
	 * 名称
	 */
	private String text;
	
	/**
	 * 状态
	 */
	private String state;
	
	/**
	 * 选中状态
	 */
	boolean checked;
	
	/**
	 * 子节点
	 */
	private List<SiteTreeDto> children;
	
	
	/**
	 * 属性
	 */
	private  Map<String,String> attribute;

	public boolean isChecked() {
		return checked;
	}

	public void setChecked(boolean checked) {
		this.checked = checked;
	}

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

	public List<SiteTreeDto> getChildren() {
		return children;
	}

	public void setChildren(List<SiteTreeDto> children) {
		this.children = children;
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}

	
	
}
