/**
 * @Title: ServerGroupDto.java
 * @Package cy.its.device.rest.dto
 * @Description: TODO(这里要填写用途)
 * @author qianfuxing qianfx@cychina.cn
 * @date 2015年10月20日 下午1:35:21
 * @version V1.0
 * @Revision : $Rev: 6833 $
 * @Id: $Id: ServerGroupDto.java 6833 2015-10-22 05:37:02Z CY8283 $
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.rest.dto;

import cy.its.com.dto.BaseDto;

/**
  * @ClassName: ServerGroupDto
  * @Description: TODO(服务器组管理Dto)
  * @author qianfuxing qianfx@cychina.cn
  * @date 2015年10月20日 下午1:35:21
  *
  */

public class ServerGroupDto extends BaseDto{
	private String groupAssoId;				//服务器类型组关系ID
	private String serverTypeId;			//服务器类型ID
	private String serverType;				//服务器类型
	private String serverGroupTypeId;		//服务器类型组ID
	private String serverGroupTypeName;		//服务器类型组名称
	private String serverId;				//服务器ID
	
	
	public String getGroupAssoId() {
		return groupAssoId;
	}
	public void setGroupAssoId(String groupAssoId) {
		this.groupAssoId = groupAssoId;
	}
	public String getServerTypeId() {
		return serverTypeId;
	}
	public void setServerTypeId(String serverTypeId) {
		this.serverTypeId = serverTypeId;
	}
	public String getServerType() {
		return serverType;
	}
	public void setServerType(String serverType) {
		this.serverType = serverType;
	}
	public String getServerGroupTypeId() {
		return serverGroupTypeId;
	}
	public void setServerGroupTypeId(String serverGroupTypeId) {
		this.serverGroupTypeId = serverGroupTypeId;
	}
	public String getServerGroupTypeName() {
		return serverGroupTypeName;
	}
	public void setServerGroupTypeName(String serverGroupTypeName) {
		this.serverGroupTypeName = serverGroupTypeName;
	}
	public String getServerId() {
		return serverId;
	}
	public void setServerId(String serverId) {
		this.serverId = serverId;
	}

	
}
