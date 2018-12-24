package cy.its.device.domain.service;

import java.util.List;

import cy.its.device.domain.model.Certification;
import cy.its.device.domain.model.Photo;
import cy.its.device.domain.model.SiteSys;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.model.SysComponent;

/**
 * 监控系统附属图片、组件、检定相关服务
 * @author STJ
 *
 */
public interface ISystemAttachService {
	
	/**
	 * 创建多张电子监控系统图片
	 * 
	 * @param photoes
	 *            多张电子监控系统图片
	 */
	public void createPhotoes(List<Photo> photoes);
	
	/**
	 * 查询指定电子监控系统的多张图片
	 * 
	 * @param deviceId
	 *            电子监控系统唯一标识
	 * @return 多张电子监控系统图片
	 */
	public List<Photo> photesOfDevice(String deviceId);
	/**
	 * 
	  * deletePhotoes(删除设备照片)
	  * @Title: deletePhotoes
	  * @Description: 删除设备照片
	  * @param @param photoIdlst    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public void deletePhotoes(List<String> photoIdlst);

	/**
	 * 创建电子监控系统年检信息
	 * 
	 * @param certification
	 *            电子监控系统年检信息
	 */
	public void createCertification(Certification certification);
	
	/**
	 * 查询指定电子监控系统的所有年检信息
	 * 
	 * @param deviceSysNbr
	 *            电子监控系统唯一标识
	 * @return 电子监控系统所有年检信息
	 */
	public List<Certification> certificationsOfSystem(String deviceSysNbr);
	
	/**
	 * 查询指定的电子监控系统年检信息
	 * 
	 * @param certificationId
	 *            年检唯一标识
	 * @return 电子监控系统年检信息
	 */
	public Certification certificationOfId(String certificationId);

	/**
	 * 删除鉴定证书
	 * @param deviceId 鉴定证书ID
	 */
	public void removeCertification(String  certificationId);
	
	/**
	 * 更新鉴定证书
	 * @param certification 鉴定证书
	 */
	public void updateCertification(Certification certification);
	
//	public Certification findLatesCertByDevId(String deviceId);
	
	public Certification findLatesCertByDevSysNbr(String deviceSysNbr);
	
	/**
	 * 创建电子监控系统部件
	 * 
	 * @param sysComponent
	 *            电子监控系统部件
	 */
	public void createSysComponent(SysComponent sysComponent);	
	
	/**
	 * 查询指定电子监控系统的所有部件信息
	 * 
	 * @param deviceId
	 *            电子监控系统唯一标识
	 * @return 电子监控系统所有部件信息
	 */
	public List<SysComponent> componentsOfSystem(String deviceId);

	/**
	 * 查询指定的部件明细
	 * 
	 * @param sysComponentId
	 *            部件唯一标识
	 * @return 部件明细
	 */
	public SysComponent componentOfId(String sysComponentId);
	
	/**
	 * 查询指定的部件明细
	 * 
	 * @param deviceKey
	 *            部件唯一标识
	 * @return 部件明细
	 */
	public SysComponent componentOfDeviceKey(String deviceKey);
	
	/**
	 * 删除部件
	 * @param deviceId 部件ID
	 */
	public void removeComponents(String  sysComponentId);
	
	/**
	 * 更新部件
	 * @param certification 部件
	 */
	public void updateComponents(SysComponent sysComponent);
	
//	/**
//	 * 更新部件中的车道
//	 * @param certification 部件
//	 */
//	public void updateLaneId(SiteSys siteSys);
	
	/**
	 * 删除指定设备下的所有部件
	 * @param deviceId 设备ID
	 */
	void removeComponentsOfDevice(String deviceId);
	
	/**
	 * 查询指定的部件明细
	 * 
	 * @param deviceNbr
	 *            部件唯一标识
	 * @return 部件明细
	 */
	SysComponent componentOfDeviceNbr(String deviceNbr);
}
