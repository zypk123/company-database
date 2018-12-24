/**
 * @Title: IServerGroupManageAction.java
 * @Package cy.its.device.rest.action
 * @Description: TODO(这里要填写用途)
 * @author qianfuxing qianfx@cychina.cn
 * @date 2015年10月20日 下午1:37:05
 * @version V1.0
 * @Revision : $Rev: 9945 $
 * @Id: $Id: IServerGroupManageAction.java 9945 2015-12-23 09:24:05Z CY8283 $
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.rest.action;

import java.util.List;

import org.springframework.web.bind.annotation.RequestParam;

import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.ServerGroupDto;
import cy.its.device.rest.dto.TreeDto;

/**
  * @ClassName: IServerGroupManageAction
  * @Description: TODO(服务器类型组管理接口)
  * @author qianfuxing qianfx@cychina.cn
  * @date 2015年10月20日 下午1:37:05
  *
  */

public interface IServerGroupManageAction {
	
	/**
	 * 
	  * queryServerGroupInfo(查询服务器组信息列表)
	  * @Title: queryServerGroupInfo
	  * @Description: TODO
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return DataGridResult<ServerGroupDto>    返回类型
	  * @throws
	 */
	public DataGridResult<ServerGroupDto> queryServerGroupInfo(ServerGroupDto form) throws Exception;
	
	/**
	 * 
	  * queryGroupAssoOfId(查询服务器组信息列表)
	  * @Title: queryGroupAssoOfId
	  * @Description: TODO
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return List<ServerGroupDto>    返回类型
	  * @throws
	 */
	public List<ServerGroupDto> queryGroupAssoOfId(String serverGroupTypeId) throws Exception;
	
	/**
	 * 
	  * addServerGroupInfo(添加服务器类型组信息)
	  * @Title: addServerGroupInfo
	  * @Description: TODO
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	public int addServerGroupInfo(ServerGroupDto form) throws Exception;
	
	/**
	 * 
	  * removeServerGroupInfo(删除服务器类型组信息)
	  * @Title: removeServerGroupIfo
	  * @Description: TODO
	  * @param @param groupAssoId 服务器类型组关系ID
	  * @param @param serverTypeId 服务器类型ID
	  * @param @param serverGroupTypeId 服务器类型组ID
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	public int removeServerGroupInfo(String groupAssoId,String serverTypeId,String serverGroupTypeId) throws Exception;
	
	/**
	 * 
	  * removeSomeServerGroupInfo(批量删除服务器类型组信息)
	  * @Title: removeSomeServerGroupInfo
	  * @Description: TODO
	  * @param @param groupAssoIdStr  多个服务器类型组关系ID字符串
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	public int removeSomeServerGroupInfo(String groupAssoIdStr,String serverTypeIdStr,String serverGroupTypeId) throws Exception;
	
	/**
	 * 
	  * editServerGroupIfo(编辑服务器类型组信息)
	  * @Title: editServerGroupIfo
	  * @Description: TODO
	  * @param @param form
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	public int editServerGroupInfo(ServerGroupDto form) throws Exception;
	
	/**
	 * 
	  * queryAllServerGroup(查询所有服务器类型组)
	  * @Title: queryAllServerGroup
	  * @Description: TODO
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return List<TreeDto>    返回类型
	  * @throws
	 */
	public List<TreeDto> queryAllServerGroup() throws Exception;
	
	/**
	 * 
	  * addServerGroup(添加树服务器类型组)
	  * @Title: addServerGroup
	  * @Description: TODO
	  * @param @param form
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	public int addServerGroup(ServerGroupDto form) throws Exception;
	
	/**
	 * 
	  * removeServerGroup(删除树服务器类型组)
	  * @Title: removeServerGroup
	  * @Description: TODO
	  * @param @param serverGroupTypeId 服务器类型组ID
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	public int removeServerGroup(String serverGroupTypeId) throws Exception;
	
	/**
	 * 
	  * editServerGroup(编辑树服务器类型组)
	  * @Title: editServerGroup
	  * @Description: TODO
	  * @param @param form
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	public int editServerGroup(ServerGroupDto form) throws Exception;
	
}
