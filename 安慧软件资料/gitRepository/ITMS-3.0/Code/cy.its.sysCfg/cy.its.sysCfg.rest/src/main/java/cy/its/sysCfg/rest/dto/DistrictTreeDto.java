package cy.its.sysCfg.rest.dto;

import java.util.List;
import java.util.Map;

public class DistrictTreeDto {
	 
	/**
	 * 机构ID
	 */
	private String id;
	
	/**
	 * 机构名称
	 */
	private String text;
	
	/**
	 * 子节点
	 */
	private List<DistrictTreeDto> children;

	/**
	 *  其他属性
	 */
	private Map<String,String> attribute;
	
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

	public List<DistrictTreeDto> getChildren() {
		return children;
	}

	public void setChildren(List<DistrictTreeDto> children) {
		this.children = children;
	}

	public Map<String, String> getAttribute() {
		return attribute;
	}

	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}
	
	
}
