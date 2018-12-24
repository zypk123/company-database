package cy.its.device.domain.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import cy.its.device.domain.criteria.DataPathCountCriteria;
import cy.its.device.domain.criteria.DeviceDataPathCriteria;
import cy.its.device.domain.model.DataPathBasic;
import cy.its.device.domain.model.DataPathDetail;
import cy.its.device.domain.model.DataPathDynamicDetail;
import cy.its.device.domain.model.DeviceDataPath;

public interface IDeviceDataPathService {
	/**
	 * 查询符合条件的数据轨迹列表
	 * 
	 * @param criteria
	 *            查询条件
	 * @return 设备承建商
	 */
	public List<DeviceDataPath> findDataPath(DeviceDataPathCriteria criteria);

	
	/**
	 * 统计过车传输轨迹
	 * @param parseParams
	 * @return
	 */
	public List<DataPathBasic> countDataPathBasics(DataPathCountCriteria criteria);
    
	/**
	 * 查看指定设备的指定时间内数据轨迹统计情况
	 * @param parseParams
	 * @return
	 */
	public DataPathDetail dataPathDetailOfDevice(String deviceSysNbr, Date passTimeBegin,
			Date passTimeEnd);


	/**
	 * 查看指定设备的指定时间内经过稽查布控系统数据轨迹统计情况(经过节点动态)
	 * @param deviceSysNbr
	 * @param passTimeBegin
	 * @param passTimeEnd
	 * @return
	 */
	DataPathDynamicDetail dynamicDataPathDetailOfDevice(String deviceSysNbr, Date passTimeBegin, Date passTimeEnd);

	/**
	 * 查看指定设备的指定时间内到管控平台数据轨迹统计情况(经过节点动态)
	 * @param deviceSysNbr
	 * @param passTimeBegin
	 * @param passTimeEnd
	 * @return
	 */
	DataPathDynamicDetail dynamicDataPathToItmsDetailOfDevice(String deviceSysNbr, Date passTimeBegin, Date passTimeEnd);
	
	/**
	 * 查看卡口轨迹统计列表
	 * @param criteria
	 * @return
	 */
	public List<DataPathBasic> countAllDataPathBasics(DataPathCountCriteria criteria);
}
