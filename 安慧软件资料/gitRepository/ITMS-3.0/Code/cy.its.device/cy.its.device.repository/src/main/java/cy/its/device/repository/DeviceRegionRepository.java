/**
 * @Title: DeviceRegionRepository.java
 * @Package cy.its.device.repository
 * @Description: 区间系统repository接口实现类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月19日 下午2:28:52
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.repository;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.bus.EventBus;
import cy.its.com.constant.ConstValue;
import cy.its.com.constant.SysCodeConstant;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.DeviceRegionCriteria;
import cy.its.device.domain.model.DeviceRegion;
import cy.its.device.domain.model.Sys;
import cy.its.device.domain.repository.IDeviceRegionRepository;
import cy.its.device.mybatis.client.DeviceRegionMapper;
import cy.its.platform.common.utils.SqlHelper;

/**
  * @ClassName: DeviceRegionRepository
  * @Description: 区间系统repository接口实现类
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年11月19日 下午2:28:52
  *
  */
@Service
public class DeviceRegionRepository implements IDeviceRegionRepository{

	@Autowired
	DeviceRegionMapper deviceRegionMapper;
	
	@Autowired
	EventBus eventBus;
	
	/*
	  * <p>Title: aggregateOfId</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @throws Exception
	  * @see cy.its.com.domain.IRepository#aggregateOfId(java.lang.String)
	  */
	
	
	@Override
	public DeviceRegion aggregateOfId(String id) throws Exception {
		return deviceRegionMapper.selectByPrimaryKey(id);
	}

	/*
	  * <p>Title: save</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#save(java.lang.Object)
	  */
	
	
	@Override
	public String save(DeviceRegion obj) {
		obj.setRegionalId(StringUtil.generateUUID());
		deviceRegionMapper.insertSelective(obj);
		return obj.getRegionalId();
	}

	/*
	  * <p>Title: delete</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @see cy.its.com.domain.IRepository#delete(java.lang.String)
	  */
	
	
	@Override
	public int delete(String id) {
		return deviceRegionMapper.deleteByPrimaryKey(id);
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#update(java.lang.Object)
	  */
	
	
	@Override
	public int update(DeviceRegion obj) {
		return deviceRegionMapper.updateByPrimaryKeySelective(obj);
	}

	/*
	  * <p>Title: findDeviceRegion</p>
	  * <p>Description: </p>
	  * @param criteria
	  * @return
	  * @see cy.its.device.domain.repository.IDeviceRegionRepository#findDeviceRegion(cy.its.device.domain.criteria.DeviceRegionCriteria)
	  */	
	@Override
	public List<DeviceRegion> findDeviceRegion(DeviceRegionCriteria criteria) {	
		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(criteria.getOrderName())){
			PageHelper.orderBy(criteria.getOrderName() + " " + criteria.getOrderType());
		}
		Page<DeviceRegion> page = (Page<DeviceRegion>) deviceRegionMapper.findDeviceRegions(ParamUtil.parseParams(criteria));
		criteria.setTotal(page.getTotal());
		return page.getResult();
	}

	/*
	  * <p>Title: countDeviceRegion</p>
	  * <p>Description: </p>
	  * @param criteria
	  * @return
	  * @see cy.its.device.domain.repository.IDeviceRegionRepository#countDeviceRegion(cy.its.device.domain.criteria.DeviceRegionCriteria)
	  */	
	@Override
	public int countDeviceRegion(DeviceRegionCriteria criteria) {
		return deviceRegionMapper.countDeviceRegion(ParamUtil.parseParams(criteria));
	}

	/*
	  * <p>Title: changeDeviceRegionUseStauts</p>
	  * <p>Description: </p>
	  * @param devRegionId
	  * @param useStatus
	  * @param userName
	  * @throws RuntimeException
	  * @see cy.its.device.domain.repository.IDeviceRegionRepository#changeDeviceRegionUseStauts(java.lang.String, java.lang.String, java.lang.String)
	  */	
	@Override
	public void changeDeviceRegionUseStauts(String devRegionId, String useStatus, String userName)
			throws RuntimeException {
		DeviceRegion deviceRegion = new DeviceRegion();
		deviceRegion.setRegionalId(devRegionId);
		deviceRegion.setEnableFlag(useStatus);
		deviceRegion.setUpdateBy(userName);		
		try {
			deviceRegionMapper.updateByPrimaryKeySelective(deviceRegion);
		} catch (Exception e) {
			throw new RuntimeException("更改区间系统的启用状态出错！错误详细：" + e.getMessage());
		}		
	}
	
	/**
	 * 设备区间变更通知
	 */
	@Override
	public void deviceRegionChanged() {
		eventBus.publish(ConstValue.ROUTE_KEY_CACHECHANGE_DEVICEREGION, "");
	}

}
