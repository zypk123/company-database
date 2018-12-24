/**
 * @Title: DeviceFaultRepository.java
 * @Package cy.its.device.repository
 * @Description: 设备故障repository接口实现类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月30日 下午9:05:22
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.device.domain.criteria.DeviceFaultCriteria;
import cy.its.device.domain.criteria.SystemCriteria;
import cy.its.device.domain.model.DeviceFault;
import cy.its.device.domain.model.DeviceFaultList;
import cy.its.device.domain.repository.maintain.IDeviceFaultRepository;
import cy.its.device.mybatis.client.DeviceFaultMapper;
import cy.its.platform.common.utils.SqlHelper;

/**
 * @ClassName: DeviceFaultRepository
 * @Description: 设备故障repository接口实现类
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年11月30日 下午9:05:22
 *
 */
@Service
public class DeviceFaultRepository implements IDeviceFaultRepository {

	@Autowired
	DeviceFaultMapper deviceFaultMapper;
	/*
	 * <p>Title: aggregateOfId</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @throws Exception
	 * 
	 * @see cy.its.com.domain.IRepository#aggregateOfId(java.lang.String)
	 */

	@Override
	public DeviceFault aggregateOfId(String id) {
		return deviceFaultMapper.selectByPrimaryKey(id);
	}

	/*
	 * <p>Title: save</p> <p>Description: </p>
	 * 
	 * @param obj
	 * 
	 * @return
	 * 
	 * @see cy.its.com.domain.IRepository#save(java.lang.Object)
	 */

	@Override
	public String save(DeviceFault obj) {
		obj.setFaultId(StringUtil.generateUUID());
		deviceFaultMapper.insertSelective(obj);
		return obj.getFaultId();
	}

	/*
	 * <p>Title: delete</p> <p>Description: </p>
	 * 
	 * @param id
	 * 
	 * @return
	 * 
	 * @see cy.its.com.domain.IRepository#delete(java.lang.String)
	 */

	@Override
	public int delete(String id) {
		return deviceFaultMapper.deleteByPrimaryKey(id);
	}

	/*
	 * <p>Title: update</p> <p>Description: </p>
	 * 
	 * @param obj
	 * 
	 * @return
	 * 
	 * @see cy.its.com.domain.IRepository#update(java.lang.Object)
	 */

	@Override
	public int update(DeviceFault obj) {
		return deviceFaultMapper.updateByPrimaryKeySelective(obj);
	}

	/*
	 * <p>Title: findDeviceFaultsList</p> <p>Description: </p>
	 * 
	 * @param deviceFaultCriteria
	 * 
	 * @return
	 * 
	 * @see cy.its.device.domain.repository.maintain.IDeviceFaultRepository#
	 * findDeviceFaultsList(cy.its.device.domain.criteria.DeviceFaultCriteria)
	 */

	@Override
	public List<DeviceFaultList> findDeviceFaultsList(DeviceFaultCriteria deviceFaultCriteria) {
		PageHelper.startPage(deviceFaultCriteria.getPageNum(),deviceFaultCriteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(deviceFaultCriteria.getOrderName())){
			PageHelper.orderBy(deviceFaultCriteria.getOrderName() + " " + deviceFaultCriteria.getOrderType());
		}
		Page<DeviceFaultList> page = (Page<DeviceFaultList>) deviceFaultMapper.selectFaultList(ParamUtil.parseParams(deviceFaultCriteria));
		deviceFaultCriteria.setTotal(page.getTotal());
		return  page.getResult();
	}

	/*
	 * <p>Title: countDeviceFaults</p> <p>Description: </p>
	 * 
	 * @param deviceFaultCriteria
	 * 
	 * @return
	 * 
	 * @see cy.its.device.domain.repository.maintain.IDeviceFaultRepository#
	 * countDeviceFaults(cy.its.device.domain.criteria.DeviceFaultCriteria)
	 */

	@Override
	public int countDeviceFaults(DeviceFaultCriteria deviceFaultCriteria) {
		return deviceFaultMapper.countDeviceFaults(ParamUtil.parseParams(deviceFaultCriteria));
	}

	/*
	 * <p>Title: deleteNoValidFaults</p> <p>Description: </p>
	 * 
	 * @param faultIdLst
	 * 
	 * @return
	 * 
	 * @see cy.its.device.domain.repository.maintain.IDeviceFaultRepository#
	 * deleteNoValidFaults(java.util.List)
	 */

	@Override
	public int deleteNoValidFaults(List<String> faultIdLst) {
		String faultIdstr = listToString(faultIdLst, ',');
		return deviceFaultMapper.deleteNoValidFaults(faultIdstr);
	}

	private String listToString(List list, char separator) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			sb.append(list.get(i)).append(separator);
		}
		return sb.toString().substring(0, sb.toString().length() - 1);
	}

	/*
	  * <p>Title: selectDeviceFaultById</p>
	  * <p>Description: </p>
	  * @param faultId
	  * @return
	  * @see cy.its.device.domain.repository.maintain.IDeviceFaultRepository#selectDeviceFaultById(java.lang.String)
	  */
	
	
	@Override
	public DeviceFault selectDeviceFaultById(String id) {
		return deviceFaultMapper.selectByPrimaryKey(id);
	}
	
	@Override
	public List<DeviceFault> findDevFaultsMataince(String deviceId,List<String> faultTypeLst) {
		Map<String,Object> map=new HashMap<String,Object>();
		map.put("deviceId", deviceId);
		map.put("faultTypeLst", faultTypeLst);
		return deviceFaultMapper.findDevFaultsMataince(map);
	}
	
	@Override
	public List<DeviceFault> findDevLatestFaultsMataince(SystemCriteria criteria) {		
		return deviceFaultMapper.findDevLatestFaultsMataince(ParamUtil.parseParams(criteria));
	}

	@Override
	public List<DeviceFault> findDeviceFaults(DeviceFaultCriteria deviceFaultCriteria) {
		PageHelper.startPage(deviceFaultCriteria.getPageNum(),deviceFaultCriteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(deviceFaultCriteria.getOrderName())){
			PageHelper.orderBy(deviceFaultCriteria.getOrderName() + " " + deviceFaultCriteria.getOrderType());
		}
		Page<DeviceFault> page = (Page<DeviceFault>) deviceFaultMapper.findDeviceFaults(ParamUtil.parseParams(deviceFaultCriteria));
		page.setTotal(page.getTotal());
		return  page.getResult();
	}

}
