package cy.its.device.rest.dto;

import java.util.HashMap;
import java.util.Map;

public class Dto {
	private String id;		//树ID
	private String text;	//树名称
	private Map<String,String> attribute = new HashMap<String,String>();	//属性
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
	public Map<String, String> getAttribute() {
		return attribute;
	}
	public void setAttribute(Map<String, String> attribute) {
		this.attribute = attribute;
	}
	
	/**
	 * 获取设备类型
	 * @return
	 */
	public String getDeviceType(){
		return this.attribute.get("deviceType");
	}
}
