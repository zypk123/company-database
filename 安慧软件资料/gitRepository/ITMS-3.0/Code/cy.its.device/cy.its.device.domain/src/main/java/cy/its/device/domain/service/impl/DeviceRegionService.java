/**
 * @Title: DeviceRegionService.java
 * @Package cy.its.device.domain.service.impl
 * @Description: 区间系统领域服务接口实现类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月19日 下午3:19:15
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.domain.service.impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.DeviceRegionCriteria;
import cy.its.device.domain.model.DeviceRegion;
import cy.its.device.domain.model.DeviceRegionEnableFlag;
import cy.its.device.domain.model.DeviceSysUseStatus;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.repository.IDeviceRegionRepository;
import cy.its.device.domain.repository.IUseStatusRepository;
import cy.its.device.domain.service.IDeviceRegionService;

/**
  * @ClassName: DeviceRegionService
  * @Description: 区间系统领域服务接口实现类
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年11月19日 下午3:19:15
  *
  */
@Service
public class DeviceRegionService implements IDeviceRegionService {

	@Autowired
	IDeviceRegionRepository deviceRegionRepository;

	@Autowired
	IUseStatusRepository useStatusRepository;
	
	/*
	  * <p>Title: createDeviceRegion</p>
	  * <p>Description: </p>
	  * @param deviceRegion
	  * @return
	  * @see cy.its.device.domain.service.IDeviceRegionService#createDeviceRegion(cy.its.device.domain.model.DeviceRegion)
	  */
	
	
	@Override
	public String createDeviceRegion(DeviceRegion deviceRegion) throws Exception {
		// 判定相同编号的区间是否存在
		if(existedRegionOfCode(deviceRegion)){
			throw new Exception("已存在相同区间编号的区间");
		}
		
		// 设置区间标记： 备案
		deviceRegion.setEnableFlag(DeviceRegionEnableFlag.NEW);
		
		// 保存区间
		return deviceRegionRepository.save(deviceRegion);
	}


	/*
	  * <p>Title: removeDeviceRegion</p>
	  * <p>Description: </p>
	  * @param regionId
	  * @see cy.its.device.domain.service.IDeviceRegionService#removeDeviceRegion(java.lang.String)
	  */
	
	
	@Override
	public void removeDeviceRegion(String regionId) {
		deviceRegionRepository.delete(regionId);
	}

	/*
	  * <p>Title: updateDeviceRegion</p>
	  * <p>Description: </p>
	  * @param deviceRegion
	  * @see cy.its.device.domain.service.IDeviceRegionService#updateDeviceRegion(cy.its.device.domain.model.DeviceRegion)
	  */
	
	
	@Override
	public void updateDeviceRegion(DeviceRegion deviceRegion) throws Exception {
		DeviceRegion oldRegion = deviceRegionRepository.aggregateOfId(deviceRegion.getRegionalId());
		
		if(!oldRegion.getRegionalCode().equals(deviceRegion.getRegionalCode())){
			if(existedRegionOfCode(deviceRegion)){
				throw new Exception("变更后的区间编号存在相同的记录");
			}
		}
		
		deviceRegionRepository.update(deviceRegion);
	}

	/*
	  * <p>Title: deviceRegionOfId</p>
	  * <p>Description: </p>
	  * @param reionId
	  * @return
	  * @throws Exception
	  * @see cy.its.device.domain.service.IDeviceRegionService#deviceRegionOfId(java.lang.String)
	  */
	
	
	@Override
	public DeviceRegion deviceRegionOfId(String reionId) throws Exception {
		return deviceRegionRepository.aggregateOfId(reionId);
	}

	/*
	  * <p>Title: findDeviceRegion</p>
	  * <p>Description: </p>
	  * @param criteria
	  * @return
	  * @see cy.its.device.domain.service.IDeviceRegionService#findDeviceRegion(cy.its.device.domain.criteria.DeviceRegionCriteria)
	  */
	
	
	@Override
	public List<DeviceRegion> findDeviceRegion(DeviceRegionCriteria criteria) {
		return deviceRegionRepository.findDeviceRegion(criteria);
	}

	/*
	  * <p>Title: changeDeviceRegionUseStauts</p>
	  * <p>Description: </p>
	  * @param devRegionId
	  * @param useStatus
	  * @param userName
	  * @throws RuntimeException
	  * @see cy.its.device.domain.service.IDeviceRegionService#changeDeviceRegionUseStauts(java.lang.String, java.lang.String, java.lang.String)
	  */
	
	
//	@Override
	public void changeDeviceRegionUseStauts(String devRegionId, String useStatus, String userName)
			throws RuntimeException {
		deviceRegionRepository.changeDeviceRegionUseStauts(devRegionId, useStatus, userName);
	}

	/**
	 * 设备区间变更通知
	 * 设备区间修改、删除和增加时,并且在变更提交到数据库后才可调用本接口
	 */
	@Override
	public void deviceRegionChanged(){
		deviceRegionRepository.deviceRegionChanged();
	}
	
	@Override
	public void enableDeviceRegion(String devRegionId, String userName) throws Exception {
		changeEnableFlag(devRegionId, userName, DeviceRegionEnableFlag.ENABLED, "启用设备区间");
	}

	@Override
	public void disableDeviceRegion(String devRegionId, String userName) throws Exception {		
		changeEnableFlag(devRegionId, userName, DeviceRegionEnableFlag.DISABLED, "停用设备区间");
	}

	@Override
	public void discardDeviceRegion(String devRegionId, String userName) throws Exception {
		changeEnableFlag(devRegionId, userName, DeviceRegionEnableFlag.DISCARDED, "废弃设备区间");
	}
	
	/**
	 * 辅助方法
	 * @param devRegionId
	 * @param userName
	 * @param flag
	 * @throws Exception
	 */
	private void changeEnableFlag(String devRegionId, String userName, DeviceRegionEnableFlag flag, String reason) throws Exception {
		String oldStatus, newStatus;
		Date sysTime = new Date();
		DeviceRegion region = deviceRegionRepository.aggregateOfId(devRegionId);
		oldStatus = region.getEnableFlag();
		region.setEnableFlag(flag);
		newStatus = region.getEnableFlag();   
		region.setUpdateBy(userName);
		region.setEnableUpdateDate(sysTime);
		
		deviceRegionRepository.update(region);
		
		DeviceSysUseStatus useStatusLog = new DeviceSysUseStatus();
		useStatusLog.setDeviceId(devRegionId);
		useStatusLog.setOriginalStatus(oldStatus);
		useStatusLog.setUpdateStatus(newStatus);
		useStatusLog.setUpdateBy(userName);
		useStatusLog.setUpdateTime(sysTime);
		useStatusLog.setDeviceOrRegion("2");
		useStatusLog.setUpdateReason(reason);
        useStatusRepository.save(useStatusLog);
	}
	
	/**
	 * 检查区间代码是否存在
	 * @param deviceRegion
	 * @return
	 */
	private boolean existedRegionOfCode(DeviceRegion deviceRegion) {
		DeviceRegionCriteria c = new DeviceRegionCriteria();
		c.regionalCode = deviceRegion.getRegionalCode();
		boolean isExisted = deviceRegionRepository.countDeviceRegion(c) > 0;
		return isExisted;
	}

}
