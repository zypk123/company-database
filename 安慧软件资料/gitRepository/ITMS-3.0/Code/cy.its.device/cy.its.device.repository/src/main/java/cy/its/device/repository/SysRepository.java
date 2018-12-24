package cy.its.device.repository;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;

import cy.its.com.bus.EventBus;
import cy.its.com.constant.ConstValue;
import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
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
import cy.its.device.domain.repository.ISysRepository;
import cy.its.device.mybatis.client.BaseStationMapper;
import cy.its.device.mybatis.client.DeviceSysUseStatusMapper;
import cy.its.device.mybatis.client.EquipmentMapper;
import cy.its.device.mybatis.client.EventDetectionMapper;
import cy.its.device.mybatis.client.FlowSysMapper;
import cy.its.device.mybatis.client.MeteorologicMapper;
import cy.its.device.mybatis.client.SysMapper;
import cy.its.device.mybatis.client.TollgateSysMapper;
import cy.its.device.mybatis.client.VideoMapper;
import cy.its.device.mybatis.client.VioDeviceMapper;
import cy.its.device.mybatis.client.led.LedMapper;
import cy.its.platform.common.model.DeviceCertiStatusModel;
import cy.its.platform.common.utils.RedisPoolUtil;

@Service
public class SysRepository implements ISysRepository {

	@Autowired
	SysMapper sysMapper;
	
	@Autowired
	EquipmentMapper equipmentMapper;

	@Autowired
	EventDetectionMapper eventDetectionMapper;

	@Autowired
	MeteorologicMapper meteorologicMapper;

	@Autowired
	TollgateSysMapper tollgateSysMapper;

	@Autowired
	VideoMapper videoMapper;

	@Autowired
	VioDeviceMapper vioDeviceMapper;

	@Autowired
	DeviceSysUseStatusMapper deviceSysUseStatusMapper;
	
	@Autowired
	EventBus eventBus;
	
	@Autowired
	FlowSysMapper flowSysMapper;

	@Autowired
	LedMapper ledMapper;
	
	@Autowired
	BaseStationMapper baseStationMapper;
	
	public Sys aggregateOfId(String id) throws Exception {
		return sysMapper.selectByPrimaryKey(id);
	}
	public Sys selectByNBR(String deviceSysNbr){
		return sysMapper.selectByNBR(deviceSysNbr);
	}

	public String save(Sys obj) {
		obj.setDeviceId(StringUtil.generateUUID());
		sysMapper.insertSelective(obj);
		return obj.getDeviceId();
	}

	public int delete(String id) {
		return sysMapper.deleteByPrimaryKey(id);
	}

	public int update(Sys obj) {
		return sysMapper.updateByPrimaryKeySelective(obj);
	}
	
	public int updateSectionIdSys(SiteSys siteSys){
		return sysMapper.updateBySectionId(siteSys);
	}

	public List<Sys> findSystems(SystemCriteria criteria) {
		return sysMapper.selectSysAndEquipment(ParamUtil.parseParams(criteria));
	}

	public List<Sys> findSys(SystemCriteria criteria) {
		PageHelper.startPage(criteria.getPageNum(), criteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(criteria.getOrderName())){
			PageHelper.orderBy(criteria.getOrderName() + " " + criteria.getOrderType());
		}
		Page<Sys> page = (Page<Sys>) sysMapper.selectSys(ParamUtil.parseParams(criteria));
		criteria.setTotal(page.getTotal());
		return page.getResult();
	}


	public int countSystems(SystemCriteria criteria) {
		return sysMapper.countSysAndEquipment(ParamUtil.parseParams(criteria));
	}

	public int countSys(SystemCriteria criteria) {
		return sysMapper.countSys(ParamUtil.parseParams(criteria));
	}

	public String saveSysAndParam(Sys sys, SysParam sysParam) {
		String id = save(sys);
		if (sysParam != null) {
			sysParam.attatchSys(id);

			if (sysParam instanceof EventDetection) {
				eventDetectionMapper.insert((EventDetection) sysParam);
			} else if (sysParam instanceof Meteorologic) {
				meteorologicMapper.insert((Meteorologic) sysParam);
			} else if (sysParam instanceof TollgateSys) {
				tollgateSysMapper.insert((TollgateSys) sysParam);
			} else if (sysParam instanceof Video) {
				videoMapper.insert((Video) sysParam);
			} else if (sysParam instanceof VioDevice) {
				vioDeviceMapper.insert((VioDevice) sysParam);
			} else if (sysParam instanceof FlowSys) {
				flowSysMapper.insert((FlowSys) sysParam);
			} else if (sysParam instanceof Led){
				ledMapper.insertSelective((Led) sysParam);
			} else if (sysParam instanceof BaseStation){
				baseStationMapper.insertSelective((BaseStation) sysParam);
			} else {
			}
		}
		return id;
	}

	public void updateSysParam(SysParam sysParam) {
		if (sysParam instanceof EventDetection) {
			eventDetectionMapper.updateByPrimaryKey((EventDetection) sysParam);
		} else if (sysParam instanceof Meteorologic) {
			meteorologicMapper.updateByPrimaryKey((Meteorologic) sysParam);
		} else if (sysParam instanceof TollgateSys) {
			tollgateSysMapper.updateByPrimaryKey((TollgateSys) sysParam);
		} else if (sysParam instanceof Video) {
			videoMapper.updateByPrimaryKey((Video) sysParam);
		} else if (sysParam instanceof VioDevice) {
			vioDeviceMapper.updateByPrimaryKey((VioDevice) sysParam);
		} else if (sysParam instanceof FlowSys) {
			flowSysMapper.updateByPrimaryKey((FlowSys) sysParam);
		} else if (sysParam instanceof Led) {
			ledMapper.updateByPrimaryKey((Led) sysParam);
		} else if (sysParam instanceof BaseStation){
//			baseStationMapper.updateByPrimaryKey((BaseStation) sysParam);
		} else {
		}
	}

	@SuppressWarnings("rawtypes")
	public SysParam sysParamOfId(String deviceId, Class c) {
		if (c == EventDetection.class) {
			return eventDetectionMapper.selectByPrimaryKey(deviceId);
		} else if (c == Meteorologic.class) {
			return meteorologicMapper.selectByPrimaryKey(deviceId);
		} else if (c == TollgateSys.class) {
			return tollgateSysMapper.selectByPrimaryKey(deviceId);
		} else if (c == Video.class) {
			return videoMapper.selectByPrimaryKey(deviceId);
		} else if (c == VioDevice.class) {
			return vioDeviceMapper.selectByPrimaryKey(deviceId);
		} else if (c == FlowSys.class) {
			return flowSysMapper.selectByPrimaryKey(deviceId);
		}  else if (c == Led.class) {
			return ledMapper.selectByPrimaryKey(deviceId);
		}  else if (c == BaseStation.class) {
			return baseStationMapper.selectByPrimaryKey(deviceId);
		}  else {
			return null;
		}
	}

	/**
	 * 
	  * changeSysUseStauts(更改设备的使用状态)
	  * @Title: changeSysUseStauts
	  * @Description: 更改设备的使用状态：1、向设备使用状态记录表中插入一条记录；2、更新设备表中使用状态字段已经时间相关字段
	  * @param @param devId
	  * @param @param useStatus
	  * @param @param updateReason
	  * @param @param userName
	  * @param @throws RuntimeException    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	@Override
	public void changeSysUseStauts(String devId, String useStatus, String updateReason, String userName)
			throws RuntimeException {
		Date date = new Date();
		insertUseStatus(devId, useStatus, updateReason, userName, date);
		updateSysUseStatus(devId, useStatus, date);
	}


	/**
	 * 设备系统和组件变更通知
	 */
	@Override
	public void deviceSysChanged() {
		eventBus.publish(ConstValue.ROUTE_KEY_CACHECHANGE_DEVICESYS, "");
	}
	
	/**
	 * 
	  * insertUseStatus(向使用状态记录表中插入一条记录)
	  * @Title: insertUseStatus
	  * @Description: 向使用状态记录表中插入一条记录
	  * @param @param devId
	  * @param @param useStatus
	  * @param @param updateReason
	  * @param @param userName
	  * @param @param dt    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private void insertUseStatus(String devId, String useStatus, String updateReason, String userName, Date dt) {
		Sys sys = sysMapper.selectByPrimaryKey(devId);		
		insertUseStatus(devId, sys != null ? sys.getUseStatusFlag() : null, useStatus, updateReason, userName, dt);
	}
	
	public void insertUseStatus(String devId, String originalStatus, String useStatus, String updateReason, String userName, Date dt) {
		// 向使用状态记录表中插入一条记录
		DeviceSysUseStatus deviceSysUseStatus = new DeviceSysUseStatus();
		deviceSysUseStatus.setSysUseStatusId(StringUtil.generateUUID());
		deviceSysUseStatus.setDeviceId(devId);
		deviceSysUseStatus.setOriginalStatus(originalStatus);
		deviceSysUseStatus.setUpdateStatus(useStatus);
		deviceSysUseStatus.setUpdateBy(userName);
		deviceSysUseStatus.setUpdateReason(updateReason);
		deviceSysUseStatus.setUpdateTime(dt);
		deviceSysUseStatus.setDeviceOrRegion("1");
		try {
			deviceSysUseStatusMapper.insertSelective(deviceSysUseStatus);
		} catch (Exception e) {
			throw new RuntimeException("插入使用状态记录出错！错误详细：" + e.getMessage());
		}
	}

	/**
	 * 
	  * updateSysUseStatus(更改设备的使用状态)
	  * @Title: updateSysUseStatus
	  * @Description: 更改设备的使用状态
	  * @param @param devId
	  * @param @param useStatus
	  * @param @param dt    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private void updateSysUseStatus(String devId, String useStatus, Date dt) {
		// 更新设备系统表
		Sys sys = new Sys();
		sys.setDeviceId(devId);
		sys.setUseStatusFlag(useStatus);
		sys.setEnableUpdateDate(dt);		
		try {
			sysMapper.updateByPrimaryKeySelective(sys);
		} catch (Exception e) {
			throw new RuntimeException("更改设备的使用状态出错！错误详细：" + e.getMessage());
		}
	}
	
	@Override
	public List<DeviceSysUseStatus> useStatusesOfDevice(String deviceId, Date updateTimeFrom, Date updateTimeTo){
		Map<String, Object> p = new HashMap<String, Object>(3);
		p.put("deviceId", deviceId);
		p.put("updateTimeFrom", updateTimeFrom);
		p.put("updateTimeTo", updateTimeTo);
		return deviceSysUseStatusMapper.selectUseStatus(p);
	}
	
	public Equipment getEquipmentOfId(String id){
		return equipmentMapper.selectByPrimaryKey(id);
	}	
	@Override
	public Equipment getEquipmentOfNbr(String equipmentNbr) {
		// TODO Auto-generated method stub
		return equipmentMapper.selectByEquipmentNbr(equipmentNbr);
	}    
	public String saveEquipment(Equipment obj) {
		obj.setEquipmentId(StringUtil.generateUUID());
		equipmentMapper.insertSelective(obj);
		return obj.getEquipmentId();
	}

	public int deleteEquipment(String id) {
		return equipmentMapper.deleteByPrimaryKey(id);
	}

	public int updateEquipment(Equipment obj) {
		return equipmentMapper.updateByPrimaryKeySelective(obj);
	}
	
	public void changeEquipmentUseStauts(String id, String useStatus, String updateReason, String userName)
			throws RuntimeException {
		Date date = new Date();
		insertUseStatus(id, useStatus, updateReason, userName, date);
		updateEquipmentUseStatus(id, useStatus, date);
	}
	
	/**
	 * 
	  * updateEquipmentUseStatus(更改装备的使用状态)
	  * @Title: updateEquipmentUseStatus
	  * @Description: 更改装备的使用状态
	  * @param @param id
	  * @param @param useStatus
	  * @param @param dt    设定文件
	  * @return void    返回类型
	  * @throws
	 */
	private void updateEquipmentUseStatus(String id, String useStatus, Date dt) {
		// 更新设备系统表
		Equipment equipment = new Equipment();
		equipment.setEquipmentId(id);
		equipment.setUseStatusFlag(useStatus);
		equipment.setEnableUpdateDate(dt);		
		try {
			equipmentMapper.updateByPrimaryKeySelective(equipment);
		} catch (Exception e) {
			throw new RuntimeException("更改装备的使用状态出错！错误详细：" + e.getMessage());
		}
	}
	
	public List<DeviceGenalSituation> getDevGeneralSituation(String orgPrevilegeCode)
	{
//		return sysMapper.getDevGeneralSituation(orgPrevilegeCode);
		return null;
	}
	
	/**
	 * 
	  * getAllCertiDevice
	  * @Title: getAllCertiDevice
	  * @Description: 获取所有检定设备的当前的检定情况
	  * @param @return    设定文件
	  * @return List<DeviceCertiStatusModel>    返回类型
	  * @throws
	 */
	public List<DeviceCertiStatusModel> getAllCertiDevice(){
		return sysMapper.getAllCertiDevice();	
	}
	
	/**
	  * cacheCertiDevice
	  * @Title: cacheCertiDevice
	  * @Description: 缓存检定设备的最新检定信息
	  * @param     设定文件
	  * @return void    返回类型
	  * @throws
	 */
	public void cacheCertiDevice() {
		List<DeviceCertiStatusModel> lStatusModels = getAllCertiDevice();
		if (lStatusModels == null || lStatusModels.size() == 0) {
			return;
		}
		synchronized (SysRepository.class) {
			RedisPoolUtil.del("deviceCertiStatus");
			for (DeviceCertiStatusModel obj : lStatusModels) {
				try {
					RedisPoolUtil.hset("deviceCertiStatus", obj.getDeviceId(), JSONObject.toJSONString(obj));
				} catch (Throwable e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void changeCertiDevice() {
		cacheCertiDevice();
	}
	
	public Sys sysOfDeviceKey(String deviceKey) {
//		return sysMapper.sysOfDeviceKey(deviceKey);
		return null;
	}
	
	@Override
	public Video sysVideoOfCameraSn(String cameraSn) {
		return videoMapper.sysVideoOfCameraSn(cameraSn);
	}
	@Override
	public int countVTollgate(VTollgateCriteria criteria) {
		return tollgateSysMapper.countVTollgate(ParamUtil.parseParams(criteria));
	}
	
	@Override
	public List<VTollgate> selectVTollgate(VTollgateCriteria criteria) {
		PageHelper.startPage(criteria.getPageNum(),criteria.getPageSize());
		if(!StringUtil.isNullOrEmpty(criteria.getOrderName())){
			PageHelper.orderBy(criteria.getOrderName() + " " + criteria.getOrderType());
		}
		Page<VTollgate> page = (Page<VTollgate>)tollgateSysMapper.selectVTollgate(ParamUtil.parseParams(criteria));
		criteria.setTotal(page.getTotal());
		return page.getResult();
	}
	
	@Override
	public void createSys(Sys sys) {
		sysMapper.insertSelective(sys);
	}
	
	@Override
	public void createSysParam(SysParam sysParam) {
		if (sysParam instanceof EventDetection) {
			eventDetectionMapper.insert((EventDetection) sysParam);
		} else if (sysParam instanceof Meteorologic) {
			meteorologicMapper.insert((Meteorologic) sysParam);
		} else if (sysParam instanceof TollgateSys) {
			tollgateSysMapper.insert((TollgateSys) sysParam);
		} else if (sysParam instanceof Video) {
			videoMapper.insert((Video) sysParam);
		} else if (sysParam instanceof VioDevice) {
			vioDeviceMapper.insert((VioDevice) sysParam);
		} else if (sysParam instanceof FlowSys) {
			flowSysMapper.insert((FlowSys) sysParam);
		} else if (sysParam instanceof Led){
			ledMapper.insertSelective((Led) sysParam);
		} else if (sysParam instanceof BaseStation) {
			baseStationMapper.insertSelective((BaseStation) sysParam);
		}  
	}

	@Override
	public DeviceSysCapability deviceSysCapabilityOfId(String deviceId) {
		List<DeviceSysCapability> lst = sysMapper.selectSysCapability(deviceId);
		return lst != null && lst.size() > 0 ? lst.get(0) : null;
	}
	@Override
	public long countMeteorologics(MeteorologicCriteria criteria) {
		return meteorologicMapper.countMeteorologics(ParamUtil.parseParams(criteria));
	}
	@Override
	public List<Meteorologic> selectMeteorologics(MeteorologicCriteria criteria) {
		return meteorologicMapper.selectMeteorologics(ParamUtil.parseParams(criteria));
	}
	
	@Override
	public List<MeteorologicSys> selectMeteorSys(MeteorologicCriteria criteria) {
		return sysMapper.selectMeteorSys(ParamUtil.parseParams(criteria));
	}
	
	@Override
	public List<Led> findLeds(LedCriteria criteria) {
		return ledMapper.selectLeds(ParamUtil.parseParams(criteria));
	}
	@Override
	public List<LedSys> findLedSyses(LedCriteria criteria) {
		return ledMapper.selectLedSys(ParamUtil.parseParams(criteria));
	}
	
}
