package cy.its.device.rest.action;

import cy.its.device.rest.dto.DataGridResult;
import cy.its.device.rest.dto.ServerApplicationDto;

/**
 * @author qianfuxing
 *
 */
public interface IServerTypeManageAction {
	
	/**
	 * 
	  * queryServerType(查询所有服务器类型信息)
	  * @Title: queryServerType
	  * @Description: TODO
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return DataGridResult<ServerTypeDto>    返回类型
	  * @throws
	 */
	DataGridResult<ServerApplicationDto> queryServerType() throws Exception;
	
	/**
	 * 
	  * addServerType(添加服务器类型信息)
	  * @Title: addServerType
	  * @Description: TODO
	  * @param @param form 服务器类型信息
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int addServerType(ServerApplicationDto form) throws Exception;
	
	/**
	 * 
	  * addServerType(添加服务器类型信息)
	  * @Title: editServerType
	  * @Description: TODO
	  * @param @param form 服务器类型信息
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int editServerType(ServerApplicationDto form) throws Exception;
	
	/**
	 * 
	  * addServerType(删除服务器类型信息)
	  * @Title: removeServerType
	  * @Description: TODO
	  * @param @param serverTypeId 服务器类型ID
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int removeServerType(String serverTypeId) throws Exception;
	
	
	/**
	 * 
	  * addServerType(批量删除服务器类型信息)
	  * @Title: removeSomeServerType
	  * @Description: TODO
	  * @param @param serverTypeIdStr 多个服务器类型ID字符串
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return int    返回类型
	  * @throws
	 */
	int removeSomeServerType(String serverTypeIdStr) throws Exception;
	
	
}
