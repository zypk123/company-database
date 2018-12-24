/**
 * @Title: FalutMaintainAsso.java
 * @Package cy.its.device.domain.model
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年11月30日 下午5:38:31
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.model;

/**
  * @ClassName: FalutMaintainAsso
  * @Description: TODO(这里要填写用途)
  * @author Administrator Administrator@cychina.cn
  * @date 2015年11月30日 下午5:38:31
  *
  */

public class FalutMaintainAsso {
 private String id;
 private String faultId;
 private String maintainId;
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
 * @return the faultId
 */

public String getFaultId() {
	return faultId;
}
/**
 * setter method
 * @param faultId the faultId to set
 */

public void setFaultId(String faultId) {
	this.faultId = faultId;
}
/**
 * getter method
 * @return the maintainId
 */

public String getMaintainId() {
	return maintainId;
}
/**
 * setter method
 * @param maintainId the maintainId to set
 */

public void setMaintainId(String maintainId) {
	this.maintainId = maintainId;
}
}
