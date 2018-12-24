/**
 * @Title: DevMaintainRepository.java
 * @Package cy.its.device.repository
 * @Description: 维护单repository接口实现类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年12月1日 下午1:43:36
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.repository;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.DeviceMaintainCriteria;
import cy.its.device.domain.model.DeviceMaintainRegister;
import cy.its.device.domain.model.FalutMaintainAsso;
import cy.its.device.domain.repository.maintain.IDevMaintainRepository;
import cy.its.device.mybatis.client.DeviceMaintainRegisterMapper;

/**
  * @ClassName: DevMaintainRepository
  * @Description: 维护单repository接口实现类
  * @author chenzhiying chenzy@cychina.cn
  * @date 2015年12月1日 下午1:43:36
  *
  */
@Service
public class DevMaintainRepository implements IDevMaintainRepository {

	@Autowired
	DeviceMaintainRegisterMapper deviceMaintainRegisterMapper;
	/*
	  * <p>Title: aggregateOfId</p>
	  * <p>Description: </p>
	  * @param id
	  * @return
	  * @throws Exception
	  * @see cy.its.com.domain.IRepository#aggregateOfId(java.lang.String)
	  */
	
	
	@Override
	public DeviceMaintainRegister aggregateOfId(String id) throws Exception {
		return deviceMaintainRegisterMapper.selectByPrimaryKey(id);
	}

	/*
	  * <p>Title: save</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#save(java.lang.Object)
	  */
	
	
	@Override
	public String save(DeviceMaintainRegister obj) {
		obj.setMaintenanceId(StringUtil.generateUUID());
		deviceMaintainRegisterMapper.insertSelective(obj);
		return obj.getMaintenanceId();
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
		return deviceMaintainRegisterMapper.deleteByPrimaryKey(id);
	}

	/*
	  * <p>Title: update</p>
	  * <p>Description: </p>
	  * @param obj
	  * @return
	  * @see cy.its.com.domain.IRepository#update(java.lang.Object)
	  */
	
	
	@Override
	public int update(DeviceMaintainRegister obj) {
		return deviceMaintainRegisterMapper.updateByPrimaryKeySelective(obj);
	}

	/*
	  * <p>Title: findMaintain</p>
	  * <p>Description: </p>
	  * @param params
	  * @return
	  * @see cy.its.device.domain.repository.maintain.IDevMaintainRepository#findMaintain(java.util.Map)
	  */
	
	
	@Override
	public List<DeviceMaintainRegister> findMaintain(DeviceMaintainCriteria deviceMaintainCriteria) {
		PageHelper.startPage(deviceMaintainCriteria.getPageNum(),deviceMaintainCriteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(deviceMaintainCriteria.getOrderName())){
			PageHelper.orderBy(deviceMaintainCriteria.getOrderName() + " " + deviceMaintainCriteria.getOrderType());
		}
		Page<DeviceMaintainRegister> page = (Page<DeviceMaintainRegister>) deviceMaintainRegisterMapper.findMaintain(ParamUtil.parseParams(deviceMaintainCriteria));
		deviceMaintainCriteria.setTotal(page.getTotal());
		return page.getResult();
	}

	/*
	  * <p>Title: countMaintain</p>
	  * <p>Description: </p>
	  * @param params
	  * @return
	  * @see cy.its.device.domain.repository.maintain.IDevMaintainRepository#countMaintain(java.util.Map)
	  */
	
	
	@Override
	public int countMaintain(DeviceMaintainCriteria deviceMaintainCriteria) {
		return deviceMaintainRegisterMapper.countMaintain(ParamUtil.parseParams(deviceMaintainCriteria));
	}

	/*
	  * <p>Title: createMainFaultAsso</p>
	  * <p>Description: </p>
	  * @param falutMaintainAsso
	  * @return
	  * @see cy.its.device.domain.repository.maintain.IDevMaintainRepository#createMainFaultAsso(cy.its.device.domain.model.FalutMaintainAsso)
	  */
	
	
	@Override
	public int createMainFaultAsso(FalutMaintainAsso falutMaintainAsso) {
		falutMaintainAsso.setId(StringUtil.generateUUID());
		return deviceMaintainRegisterMapper.createMainFaultAsso(falutMaintainAsso);
	}

	/*
	  * <p>Title: selectMaintainAndFaultByKey</p>
	  * <p>Description: </p>
	  * @param maintenanceId
	  * @return
	  * @see cy.its.device.domain.repository.maintain.IDevMaintainRepository#selectMaintainAndFaultByKey(java.lang.String)
	  */
	
	
	@Override
	public DeviceMaintainRegister selectMaintainAndFaultByKey(String maintenanceId) {
		return deviceMaintainRegisterMapper.selectMaintainAndFaultByKey(maintenanceId);
	}

	/*
	  * <p>Title: deleteMainFaultAsso</p>
	  * <p>Description: </p>
	  * @param maintenanceId
	  * @return
	  * @see cy.its.device.domain.repository.maintain.IDevMaintainRepository#deleteMainFaultAsso(java.lang.String)
	  */
	
	
	@Override
	public int deleteMainFaultAsso(FalutMaintainAsso falutMaintainAsso) {
		return deviceMaintainRegisterMapper.deleteMainFaultAsso(falutMaintainAsso);
	}

	/*
	  * <p>Title: selectMaintainByKey</p>
	  * <p>Description: </p>
	  * @param maintenanceId
	  * @return
	  * @see cy.its.device.domain.repository.maintain.IDevMaintainRepository#selectMaintainByKey(java.lang.String)
	  */
	
	
	@Override
	public DeviceMaintainRegister selectMaintainByKey(String maintenanceId) {
		return deviceMaintainRegisterMapper.selectByPrimaryKey(maintenanceId);
	}

	@Override
	public List<DeviceMaintainRegister> maintainsWithOpenFaultOfDevice(String deviceId) {
		return deviceMaintainRegisterMapper.selectWithOpenFault(deviceId);
	}

}
