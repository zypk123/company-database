/**
 * @Title: InfoTreeDto.java
 * @Package cy.its.sysCfg.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月30日 下午3:24:46
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.dto;

import java.util.List;
import java.util.Map;


public class InfoTreeDto {
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
	Object attribute;
	
	/**
	 * 子节点
	 */
	List<InfoTreeDto> children;

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

	public List<InfoTreeDto> getChildren() {
		return children;
	}

	public void setChildren(List<InfoTreeDto> children) {
		this.children = children;
	}

	public Object getAttribute() {
		return attribute;
	}

	public void setAttribute(Object attribute) {
		this.attribute = attribute;
	}
}
