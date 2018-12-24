package cy.its.road.rest.dto;

import java.util.List;
import java.util.Map;

public class RoadTreeDto {
	
	/**
	 * 编码
	 */
	String id;
	
	/**
	 * 文本
	 */
	String text;
	
	/**
	 * 属性
	 */
	Map<String,String> attribute;
	
	/**
	 * 子节点
	 */
	List<RoadTreeDto> children;

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

	public List<RoadTreeDto> getChildren() {
		return children;
	}

	public void setChildren(List<RoadTreeDto> children) {
		this.children = children;
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}
	
}
