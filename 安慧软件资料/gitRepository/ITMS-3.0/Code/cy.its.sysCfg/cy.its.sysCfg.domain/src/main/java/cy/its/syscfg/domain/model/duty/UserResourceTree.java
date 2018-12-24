/**
 * @Title: UserResource.java
 * @Package cy.its.syscfg.domain.model.duty
 * @Description: TODO(这里要填写用途)
 * @author zuop zuop@cychina.cn
 * @date 2015年11月18日 下午2:51:00
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.syscfg.domain.model.duty;

import java.util.ArrayList;
import java.util.List;

/**
  * @ClassName: UserResource
  * @Description: TODO 用户资源数（菜单，功能点树）
  * @author zuop zuop@cychina.cn
  * @date 2015年11月18日 下午2:51:00
  *
  */

public class UserResourceTree {
	
	/**
	 * 资源编码
	 */
	private String resourceCode;
	
	/**
	 * 资源名称
	 */
	private String resourceName;
	
	/**
	 * 资源类型，菜单或者功能点
	 */
	private String resourceType;
	
	/**
	 * 资源链接
	 */
	private String resourceURL;
	
	/**
	 * 资源图片链接
	 */
	private String resourceImageURL;
	
	/**
	 * 子资源
	 */
	List<UserResourceTree> subResource= new ArrayList<UserResourceTree>();
	
	/**
	 * getter method
	 * @return the resourceType
	 */
	
	public String getResourceType() {
		return resourceType;
	}

	/**
	 * setter method
	 * @param resourceType the resourceType to set
	 */
	
	public void setResourceType(String resourceType) {
		this.resourceType = resourceType;
	}

	/**
	 * getter method
	 * @return the resourceCode
	 */
	
	public String getResourceCode() {
		return resourceCode;
	}

	/**
	 * setter method
	 * @param resourceCode the resourceCode to set
	 */
	
	public void setResourceCode(String resourceCode) {
		this.resourceCode = resourceCode;
	}

	/**
	 * getter method
	 * @return the resourceName
	 */
	
	public String getResourceName() {
		return resourceName;
	}

	/**
	 * setter method
	 * @param resourceName the resourceName to set
	 */
	
	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	/**
	 * getter method
	 * @return the resourceURL
	 */
	
	public String getResourceURL() {
		return resourceURL;
	}

	/**
	 * setter method
	 * @param resourceURL the resourceURL to set
	 */
	
	public void setResourceURL(String resourceURL) {
		this.resourceURL = resourceURL;
	}

	/**
	 * getter method
	 * @return the resourceImageURL
	 */
	
	public String getResourceImageURL() {
		return resourceImageURL;
	}

	/**
	 * setter method
	 * @param resourceImageURL the resourceImageURL to set
	 */
	
	public void setResourceImageURL(String resourceImageURL) {
		this.resourceImageURL = resourceImageURL;
	}

	/**
	 * getter method
	 * @return the subResource
	 */
	
	public List<UserResourceTree> getSubResource() {
		return subResource;
	}

	/**
	 * setter method
	 * @param subResource the subResource to set
	 */
	
	public void setSubResource(List<UserResourceTree> subResource) {
		this.subResource = subResource;
	}
}
