package cy.its.device.domain.service;

import java.util.Date;
import java.util.List;

import cy.its.device.domain.criteria.LedCriteria;
import cy.its.device.domain.criteria.MeteorologicCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.criteria.VTollgateCriteria;
import cy.its.device.domain.model.BaseStation;
import cy.its.device.domain.model.DeviceGenalSituation;
import cy.its.device.domain.model.DeviceSysCapability;
import cy.its.device.domain.model.DeviceSysUseStatus;
import cy.its.device.domain.model.Equipment;
import cy.its.device.domain.model.EventDetection;
import cy.its.device.domain.model.FlowSys;
import cy.its.device.domain.model.Meteorologic;
import cy.its.device.domain.model.MeteorologicSys;
import cy.its.device.domain.model.SiteSys;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.SysParam;
import cy.its.device.domain.model.TollgateSys;
import cy.its.device.domain.model.VTollgate;
import cy.its.device.domain.model.Video;
import cy.its.device.domain.model.VioDevice;
import cy.its.device.domain.model.led.Led;
import cy.its.device.domain.model.led.LedSys;
import cy.its.platform.common.model.DeviceCertiStatusModel;

/**
 * 电子监控系统
 * @author STJ
 *
 */
public interface ISystemService {

	/**
	 *  创建电子监控系统
	 *  @param sys 电子监控系统信息
	 * @throws Exception 
	 */
	String createSystem(Sys sys, SysParam sysParam) throws Exception;
		
	/**
	 * 删除电子监控系统
	 * @param deviceId 电子监控系统ID
	 * @throws Exception 
	 */
	void removeSystem(String  deviceId) throws Exception;
	
	/**
	 * 更新电子监控系统
	 * @param sys 电子监控系统
	 * @throws Exception 
	 */
	void updateSystem(Sys sys) throws Exception;
	
	/**
	 * 根据断面ID更新电子监控系统断面ID
	 * @param siteSys
	 */
	void updateSectionIdSys(SiteSys  siteSys);
	
	/**
	 * 更新电子监控系统参数
	 * @param SysParam 电子监控系统参数
	 * @throws Exception 
	 */
	void updateSystem(SysParam sysParam) throws Exception;
	
	/**
	 * 查询指定的电子监控系统
	 * @param deviceId 电子监控系统ID
	 * @return 电子监控系统
	 * @throws Exception 
	 */
	Sys systemOfId(String  deviceId) throws Exception;
	
	/**
	 * 查询指定的电子监控系统
	 * @param deviceSysNbr 电子监控系统nbr
	 * @return 电子监控系统
	 * @throws Exception 
	 */
	Sys selectByNBR(String  deviceSysNbr) ;
	
	/**
	 * 查询符合条件的电子监控系统信息列表(包括设备和装备)
	 * 
	 * @param criteria
	 *            查询条件
	 * @return 电子监控系统信息列表
	 */
	List<Sys> findSystems(SystemCriteria criteria);
	
	/**
	 * 查询符合条件的电子监控系统信息列表(仅设备)
	 * 
	 * @param criteria
	 *            查询条件
	 * @return 电子监控系统信息列表
	 */
	List<Sys> findSys(SystemCriteria criteria);
	
	/**
	 * 根据deviceKey查询设备
	 * @param deviceKey
	 * @return
	 */
//	Sys sysOfDeviceKey(String deviceKey);
	
	/**
	 * 根据cameraSn查询视频参数
	 * @param cameraSn
	 * @return
	 */
	Video sysVideoOfCameraSn(String cameraSn);
	
	/**
	 * 查询指定设备系统的事件检测系统参数
	 * @param deviceId 设备系统唯一标识
	 * @return 事件检测系统参数
	 */
	public EventDetection eventDetectionOfId(String deviceId) ;

	/**
	 * 查询指定设备系统的气象监测系统参数
	 * @param deviceId 设备系统唯一标识
	 * @return 气象监测系统参数
	 */
	public Meteorologic meteorologicOfId(String deviceId);

	/**
	 * 查询指定设备系统的卡口系统参数
	 * @param deviceId 设备系统唯一标识
	 * @return 卡口系统参数
	 */
	public TollgateSys tollgateSysOfId(String deviceId) ;

	/**
	 * 查询指定设备系统的视频监控系统参数
	 * @param deviceId 设备系统唯一标识
	 * @return 视频监控系统参数
	 */
	public Video videoOfId(String deviceId) ;

	/**
	 * 查询指定设备系统的视频监控系统参数
	 * @param deviceId 设备系统唯一标识
	 * @return 视频监控系统参数
	 */
	public VioDevice vioDeviceOfId(String deviceId) ;
	
	/**
	 * 查询指定设备系统的流量系统参数
	 * @param deviceId
	 * @return
	 */
	public FlowSys flowSysOfId(String deviceId);
	
	/**
	 *  查询指定设备系统的LED系统参数
	 * @param deviceId
	 * @return
	 */
	Led ledOfId(String deviceId);
	
	/**
	 *  查询指定设备系统的短信基站
	 * @param deviceId
	 * @return
	 */
	BaseStation baseStationOfId(String deviceId);
	
	/**
	 * @throws Exception 
	 * 
	  * enableDevice(启用设备)
	  * @Title: enableDevice
	  * @Description: 启用设备
	  * @param @param devId
	  * @param @param reason
	  * @param @param userName
	  * @param @throws RuntimeException    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public void enableDevice(String devId, String reason, String userName) throws RuntimeException, Exception;
	
	/**
	 * @throws Exception 
	 * 
	  * stopDevice(停用设备)
	  * @Title: stopDevice
	  * @Description: 停用设备
	  * @param @param devId
	  * @param @param reason
	  * @param @param userName
	  * @param @throws RuntimeException    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public void stopDevice(String devId, String reason, String userName) throws RuntimeException, Exception ;
	
	/**
	 * @throws Exception 
	 * 
	  * brokenDevice(报废设备)
	  * @Title: brokenDevice
	  * @Description: 报废设备
	  * @param @param devId
	  * @param @param reason
	  * @param @param userName
	  * @param @throws RuntimeException    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public void brokenDevice(String devId, String reason, String userName) throws RuntimeException, Exception ;

	/**
	 * 根据指定条件查询卡口系统列表
	 * @param criteria
	 * @return
	 */
	public List<VTollgate> findVTollgates(VTollgateCriteria criteria);
	
	/**
	 * 加载指定设备的指定更新时间范围的使用变更记录
	 * @param deviceId
	 * @param updateTimeFrom
	 * @param updateTimeTo
	 * @return
	 */
	List<DeviceSysUseStatus> useStatusesOfDevice(String deviceId, Date updateTimeFrom, Date updateTimeTo);
	
	/**
	 * 设备系统货部件变更通知
	 * 设备系统或部件 修改、删除和增加时,并且在变更提交到数据库后才可调用本接口
	 */
	void deviceSysChanged();	
	
	/**
	 * 获取指定设备系统的所能支持的最大功能
	 * @param deviceId
	 * @return
	 */
	DeviceSysCapability deviceSysCapabilityOfId(String deviceId);
	
	Equipment getEquipmentOfId(String id);	

	Equipment getEquipmentOfNbr(String equipmentNbr);
	
	String saveEquipment(Equipment obj);

	int deleteEquipment(String id);

	int updateEquipment(Equipment obj) ;
	
	void changeEquipmentUseStauts(String id, String useStatus, String updateReason, String userName);
	
//	List<DeviceGenalSituation> getDevGeneralSituation(String orgPrevilegeCode);
	
	void changeCertiDevice();
	
	List<Meteorologic> findMeteorologics(MeteorologicCriteria criteria);

	List<MeteorologicSys> findMeteorologicSys(MeteorologicCriteria criteria);
	
	/**
	 * 查询符合条件的LED设备（含系统）
	 * @param criteria
	 * @return
	 */
	List<LedSys> findLedSyses(LedCriteria criteria);
}
