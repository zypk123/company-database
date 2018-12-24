package cy.its.device.domain.service;

import java.util.List;

import cy.its.device.domain.criteria.SurveySystemCriteria;
import cy.its.device.domain.model.BackupService;
import cy.its.device.domain.model.DataSheet;
import cy.its.device.domain.model.Server;
import cy.its.device.domain.model.ServerApplication;
import cy.its.device.domain.model.ServerPlatform;
import cy.its.device.domain.model.ServerPlatformAppAsso;
import cy.its.device.domain.model.SurveySys;

public interface ISurveySystemService {

	/**
	 * 创建新监控中心
	 * 
	 * @param surveySystem
	 *            监控中心
	 */
	public void createSurveySystem(SurveySys surveySystem);

	/**
	 * 查询符合条件的监控中心列表
	 * 
	 * @param criteria
	 *            查询条件
	 * @return 监控中心列表
	 */
	public List<SurveySys> findSurveySystems(SurveySystemCriteria criteria);

	/**
	 * 查询指定监控中心的详细信息
	 * 
	 * @param surveySystemId
	 *            监控中心唯一标识
	 * @return 监控中心详细信息
	 * @throws Exception
	 */
	public SurveySys surveySystemOfId(String surveySystemId) throws Exception;
	
	/**
	 * 
	  * updateSurveySystem(更新监控中心的详细信息)
	  * @Title: updateSurveySystem
	  * @Description: TODO
	  * @param @param surveySystemId    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public void updateSurveySystem(SurveySys surveySystem);

	/**
	 * 删除指定的监控中心
	 * 
	 * @param surveySystemId
	 *            监控中心唯一标识
	 * @throws Exception 
	 */
	public void deleteSurveySystem(String surveySystemId) throws Exception;

	
	/**
	 * 创建接入平台
	 * @param serverPlatform
	 * @throws Exception 
	 */
	public void createServerPlatform(ServerPlatform serverPlatform) throws Exception;
	
	
	/**
	 * 更新接入平台
	 * @param serverPlatform
	 * @throws Exception 
	 */
	public void updateServerPlatform(ServerPlatform serverPlatform) throws Exception;
	
	/**
	 * 删除接入平台
	 * @param serverPlatform
	 */
	public void deleteServerPlatform(String serverPlatId);
	
	/**
	 * 查询指定的接入平台
	 * @param accessPlatId
	 * @return
	 * @throws Exception 
	 */
	public ServerPlatform serverPlatformOfId(String serverPlatId) throws Exception;
	
	/**
	 * 查询指定监控中心下的接入平台
	 * @return
	 */
	public List<ServerPlatform> platformsOfSurveySystem(String surveySystemId);
	
	/**
	 * 获取指定接入平台下的服务器组件
	 * @param accessPlatId
	 * @return
	 */
	public List<ServerApplication> serverAppsOfServerPlat(String serverPlatId);
	
	/**
	 * 获取指定物理服务器的服务器组件
	 * @param serverId
	 * @return
	 */
	public List<ServerApplication> serverAppsOfServer(String serverId);
	
	/**
	 * 获取监控中心下的服务器组件
	 * @param surveySystemId
	 * @return
	 */
	public List<ServerApplication> serverAppsOfSurveySystem(String surveySystemId);
	
	/**
	 * 获取指定的服务器组件
	 * @param serverAppId
	 * @return
	 * @throws Exception 
	 */
	public ServerApplication serverApplicationOfId(String serverAppId) throws Exception;
	
	/**
	 * 创建服务器组件
	 * @param serverApp
	 */
	public void createServerApplication(ServerApplication serverApp);
	
	/**
	 * 更新服务器组件
	 * @param serverApp
	 */
	public void updateServerApplication(ServerApplication serverApp);
	
	/**
	 * 删除服务器组件
	 * @param serverAppId
	 */
	public void deleteServerApplication(String serverAppId);
	
	/**
	 * 创建新服务器
	 * 
	 * @param server
	 *            服务器
	 */
	public void createServer(Server server);

	/**
	 * 查询指定的服务器
	 * 
	 * @param serverId
	 *            服务器唯一标识
	 * @return 服务器信息
	 * @throws Exception
	 */
	public Server serverOfId(String serverId) throws Exception;
	
	/**
	 * 查询指定的服务器
	 * 
	 * @param serverIp
	 *            服务器唯一标识
	 * @return 服务器信息
	 * @throws Exception
	 */
	public List<Server> serverOfIp(String serverIp) throws Exception;

	/**
	 * 查询指定监控中心的服务器列表
	 * 
	 * @param surveySystemId
	 *            监控中心唯一标识
	 * @return 服务器列表
	 */
	public List<Server> serversOfSurveySystem(String surveySystemId);

	/**
	 * 删除指定的服务器
	 * 
	 * @param serverId
	 *            服务器唯一标识
	 */
	public void deleteServer(String serverId);

	/**
	 * 更新服务器
	 * 
	 * @param server
	 *            服务器信息
	 */
	public void updateServer(Server server);
	
	
	
	
	//public List<ServerType> serverTypesOfServer(String serverId);
	

	//public List<ServerType> serverTypesOfSurveySystem(String surveySystemId);
	
	
	/**
	 * 
	 * createServerType(创建新服务器类型) 
	 * @Title: createServerType 
	 * @Description: 创建新服务器类型
	 * @param @param serverType 服务器类型对象
	 * @return void 返回类型 @throws
	 */
	//public void createServerType(ServerType serverType);
	
	/**
	 * 
	 * deleteServerType(删除指定的服务器) @Title: deleteServerType @Description:
	 * 删除指定的服务器 @param @param serverTypeId 服务器类型ID @return void 返回类型 @throws
	 */
	//public void deleteServerType(String serverTypeId);
	
	/**
	 * 
	 * updateServerType(更新服务器类型) @Title: updateServerType @Description:
	 * 更新服务器类型 @param @param serverType 要更新的服务器类型对象 @return void @throws
	 */
	//public void updateServerType(ServerType serverType);

	/**
	 * 
	 * serverTypeOfId(根据服务器类型ID查询指定的服务器类型对象)
	 *
	 * @Title: serverTypeOfId @Description: 根据服务器类型ID查询指定的服务器类型对象 @param @param
	 *         serverTypeId 服务器类型ID @param @return @param @throws
	 *         Exception @return ServerType 返回类型 @throws
	 */
	//public ServerType serverTypeOfId(String serverTypeId) throws Exception;

	/**
	 * 
	 * findAllServerTypes(查询所有的服务器类型)
	 *
	 * @Title: findAllServerTypes @Description:
	 *         查询所有的服务器类型 @param @return @return List<ServerType> 返回类型 @throws
	 */
	//public List<ServerType> findAllServerTypes();
	


	/**
	 * 创建接入平台
	 * 
	 * @param accessPlatform
	 *            接入平台
	 */
	//void createAccessPlat(AccessPlatform accessPlatform);

	/**
	 * 删除指定的接入平台
	 * 
	 * @param accessPlatformId
	 *            接入平台唯一标识
	 */
	//public void deleteAccessPlat(String accessPlatformId);
	

	/**
	 * 更新接入平台
	 * 
	 * @param accessPlatform
	 *            接入平台信息
	 */
	//public void updateAccessPlat(AccessPlatform accessPlatform);
	
	
	
	/**
	 * 查询指定监控中心的接入平台列表
	 * 
	 * @param surveySystemId
	 *            监控中心唯一标识
	 * @return 接入平台列表
	 */
	//List<AccessPlatform> accessPlatsOfSurveySystem(String surveySystemId);
	
	//List<ServerType> serverTypesOfPlat(String platId);
	
	//void createPlatAssoType(ServerTypePlatAsso asso);
	
	//void deletePlatAssoType(ServerTypePlatAsso asso);
	
	//void updatePlatAssoType(ServerTypePlatAsso oldAsso, ServerTypePlatAsso newAsso);
	
	
//	/**
//	 * 为接入平台添加服务器
//	 * 
//	 * @param PlatServer
//	 *            平台服务器
//	 */
//	void createPlatServer(PlatServer platServer);




	/**
	 * 查询指定服务器上的后台服务状态列表
	 * 
	 * @param serverId
	 *            服务器
	 * @return 后台服务状态列表
	 */
	public List<BackupService> backupServiceOfServer(String serverId);

	/**
	 * 查询指定服务器上的数据表使用情况列表
	 * 
	 * @param serverId
	 *            服务器信息
	 * @return 服务器上数据表使用情况列表
	 */
	public List<DataSheet> tableStatusOfServer(String serverId);

	
	

//	/***************************start 服务器类型组 *********************************/
//	/**
//	 * 
//	  * createServerTypeGroup(新增服务器类型组)
//	  * @Title: createServerTypeGroup
//	  * @Description: 新增服务器类型组
//	  * @param @param serverTypeGroup
//	  * @param @return    设定文件
//	  * @return String    返回服务器类型组ID
//	  * @throws
//	 */
//	public String createServerTypeGroup(ServerTypeGroup serverTypeGroup);
//
//	/**
//	 * 
//	  * serverTypeGroupOfId(根据服务器类型组ID查询服务器类型组记录)
//	  * @Title: serverTypeGroupOfId
//	  * @Description: 根据服务器类型组ID查询服务器类型组记录
//	  * @param @param serverTypeGroupId
//	  * @param @return
//	  * @param @throws Exception    设定文件
//	  * @return ServerTypeGroup    返回类型
//	  * @throws
//	 */
//	public ServerTypeGroup serverTypeGroupOfId(String serverTypeGroupId) throws Exception;
//
//	/**
//	 * 
//	  * findAllServerTypeGroups(查询所有的服务类型组记录)
//	  * @Title: findAllServerTypeGroups
//	  * @Description: 查询所有的服务类型组记录
//	  * @param @return    设定文件
//	  * @return List<ServerTypeGroup>    返回类型
//	  * @throws
//	 */
//	public List<ServerTypeGroup> findAllServerTypeGroups();
//
//	/**
//	 * 
//	  * deleteServerTypeGroup(根据服务类型组ID删除服务器类型组记录)
//	  * @Title: deleteServerTypeGroup
//	  * @Description: 根据服务类型组ID删除服务器类型组记录
//	  * @param @param serverTypeGroupId    设定文件
//	  * @return void    返回类型
//	  * @throws
//	 */
//	public void deleteServerTypeGroup(String serverTypeGroupId);
//
//	/**
//	 * 
//	  * updateServerTypeGroup(更新服务器类型组记录)
//	  * @Title: updateServerTypeGroup
//	  * @Description: 更新服务器类型组记录
//	  * @param @param serverTypeGroup    设定文件
//	  * @return void    返回类型
//	  * @throws
//	 */
//	public void updateServerTypeGroup(ServerTypeGroup serverTypeGroup);
//
//	/**
//	 * 
//	  * createGroupAsso(新增服务器类型组与服务器类型关联记录)
//	  * @Title: createGroupAsso
//	  * @Description: 新增服务器类型组与服务器类型关联记录
//	  * @param @param serverTypeGroupAsso
//	  * @param @return    设定文件
//	  * @throws
//	 */
//	public void createGroupAsso(ServerTypeGroupAsso serverTypeGroupAsso);
//	/**
//	 * 
//	  * findGroupAssoOfId(根据服务类型组ID查询该组与服务器类型关联关系记录)
//	  * @Title: findGroupAssoOfId
//	  * @Description: 根据服务类型组ID查询该组与服务器类型关联关系记录
//	  * @param @param serverTypeGroupId
//	  * @param @return
//	  * @param @throws Exception    设定文件
//	  * @return List<ServerTypeGroupAsso>    返回类型
//	  * @throws
//	 */
//	public List<ServerTypeGroupAsso> findGroupAssoOfId(String serverTypeGroupId) throws Exception;
//
//	/**
//	 * 
//	  * findAllGroupAssos(查询所有的服务器类型组与服务器类型关联关系记录)
//	  * @Title: findAllGroupAssos
//	  * @Description: 查询所有的服务器类型组与服务器类型关联关系记录
//	  * @param @return    设定文件
//	  * @return List<ServerTypeGroupAsso>    返回类型
//	  * @throws
//	 */
//	public List<ServerTypeGroupAsso> findAllGroupAssos();
//
//	/**
//	 * 
//	  * deleteGroupAsso(根据服务器类型组与服务器类型关联关系ID，删除服务器类型组与服务器类型关联关系记录)
//	  * @Title: deleteGroupAsso
//	  * @Description: 根据服务器类型组与服务器类型关联关系ID，删除服务器类型组与服务器类型关联关系记录
//	  * @param @param serverTypeGroupAssoId    设定文件
//	  * @return void    返回类型
//	  * @throws
//	 */
//	public void deleteGroupAsso(String serverTypeGroupAssoId);
//
//	/**
//	 * 
//	  * deleteGroupAssoByGrpId(根据服务器类型组ID，删除服务器类型组与服务器类型关联关系记录)
//	  * @Title: deleteGroupAssoByGrpId
//	  * @Description: 根据服务器类型组ID，删除服务器类型组与服务器类型关联关系记录
//	  * @param @param serverTypeGroupId    设定文件
//	  * @return void    返回类型
//	  * @throws
//	 */
//	public void deleteGroupAssoByGrpId(String serverTypeGroupId);
//
//	/**
//	 * 
//	  * findGroupAssoBySeverTypeId(根据服务器类型ID查询该服务器类型是否被服务器组使用过)
//	  * @Title: findGroupAssoBySeverTypeId
//	  * @Description: 根据服务器类型ID查询该服务器类型是否被服务器组使用过
//	  * @param @param severTypeId
//	  * @param @return    设定文件
//	  * @return int    返回类型
//	  * @throws
//	 */
//	public List<ServerTypeGroupAsso> findGroupAssoBySeverTypeId(String severTypeId);
//	/**
//	 * 
//	  * updateGroupAsso(更新服务器类型组与服务器类型关联关系记录)
//	  * @Title: updateGroupAsso
//	  * @Description: 更新服务器类型组与服务器类型关联关系记录
//	  * @param @param serverTypeGroupAsso    设定文件
//	  * @return void    返回类型
//	  * @throws
//	 */
//
//	public void updateGroupAsso(ServerTypeGroupAsso serverTypeGroupAsso);
//	
//	/***************************end 服务器类型组 *********************************/
//	/**
//	  * createDeviceAssoServerType(增加设备与服务器类型关系记录)
//	  * @Title: createDeviceAssoServerType
//	  * @Description: 增加设备与服务器类型关系记录
//	  * @param @param deviceAssoServerType    设定文件
//	  * @return void    返回类型
//	  * @throws
//	 */
//	public void createDeviceAssoServerType(DeviceAssoServerType deviceAssoServerType);
//	/**
//	  * deleteAssoByDeviceId(根据设备ID，删除设备与服务类型的关系)
//	  * @Title: deleteAssoByDeviceId
//	  * @Description: 根据设备ID，删除设备与服务类型的关系
//	  * @param @param deviceId    设定文件
//	  * @return void    返回类型
//	  * @throws
//	 */
//	public void deleteAssoByDeviceId(String deviceId);
//
//	/**
//	 * 
//	  * deleteAssoByServerTypeGrpId(根据服务器类型组ID，删除设备与服务器类型关联关系)
//	  * @Title: deleteAssoByServerTypeGrpId
//	  * @Description: TODO
//	  * @param @param serverTypeGrpId
//	  * @param @return    设定文件
//	  * @return int    返回类型
//	  * @throws
//	 */
//	int deleteAssoByServerTypeGrpId(ServerTypeGroupAsso serverTypeGroupAsso);
//	
//	/**
//	 * queryDeviceIdByServerTypeGrpId(根据服务器类型组ID查相关设备ID)
//	 * @param serverTypeGrpId 服务器类型组ID
//	 * @return
//	 */
//	List<Sys> findDeviceIdByServerTypeGrpId(String serverTypeGrpId);
	
	void serverOrSurveyChanged();

	void createServerPlatAppAsso(ServerPlatformAppAsso asso) throws Exception;

	void deleteServerPlatAppAsso(ServerPlatformAppAsso asso);

	void updateServerPlatAppAsso(ServerPlatformAppAsso oldAsso, ServerPlatformAppAsso newAsso);
	
	/**
	 * 查询服务器组件涉及到的与平台的绑定关系
	 * @param serverAppId
	 * @return
	 */
	List<ServerPlatformAppAsso> platAppAssoOfApp(String serverAppId);
	
	/**
	 * 查询平台下的与组件的绑定关系
	 * @param serverAppId
	 * @return
	 */
	List<ServerPlatformAppAsso> platAppAssoOfPlat(String serverPlatId);

	/**
	 * 查询指定平台下指定服务器组件类型的服务器IP
	 * @param serverPlatId
	 * @param appType
	 * @return
	 * @throws Exception
	 */
	String serverIpOfPlatId(String serverPlatId, String appType) throws Exception;
}