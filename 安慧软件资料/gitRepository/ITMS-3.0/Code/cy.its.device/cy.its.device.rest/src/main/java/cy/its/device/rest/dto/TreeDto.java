package cy.its.device.rest.dto;

import java.util.List;
import java.util.Map;

public class TreeDto {
	private String id;		//树ID
	private String text;	//树名称
	private String state;	
	List<TreeDto> children;//子节点
	Map<String,String> attribute;//其他属性
	
	
	
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
	public List<TreeDto> getChildren() {
		return children;
	}
	public void setChildren(List<TreeDto> children) {
		this.children = children;
	}
	public Map<String, String> getAttribute() {
		return attribute;
	}
	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
}
