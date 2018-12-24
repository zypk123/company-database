/**
 * @Title: SysCodeConstant.java
 * @Package cy.its.com.constant
 * @Description: 系统所有代码常量定义
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年10月29日 下午2:50:55
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.com.constant;

/**
 * @ClassName: SysCodeConstant
 * @Description: 定义系统中用到的所有数据字典代码，与数据库中保持一直，
 * 如果数据库中对代码值进行了修改直接改该文件中相应常量值就可以了，不影响系统其他代码的使用
 * @author chenzhiying chenzy@cychina.cn
 * @date 2015年10月29日 下午2:50:55
 *
 */

public class SysCodeConstant {
	
	/**
	 * 使用状态：未启用
	 */
	public static final String USE_STATUS_NO_USE = "0";
	/**
	 * 使用状态：启用
	 */
	public static final String USE_STATUS_USE = "1";
	/**
	 * 使用状态：报废
	 */
	public static final String USE_STATUS_BROKEN = "3";
	/**
	 * 使用状态：停用
	 */
	public static final String USE_STATUS_STOP= "2";
	
	/**
	 * 在线状态：在线
	 */
	public static final String ONLINE_STATUS_ON = "1";
	/**
	 * 在线状态：离线
	 */
	public static final String ONLINE_STATUS_OFF = "2";
	
	/**
	 * 启用状态：启用
	 */
	public static final String ENABLE_FLAG_ENANLE = "1";
	/**
	 * 启用状态：禁用
	 */
	public static final String ENABLE_FLAG_DISABLE = "0";
	
	/**
	 * 警员表记录状态：已删除
	 */
	public static final String P_RECORD_STATUS_DROPED = "0";
	/**
	 * 警员表记录状态: 正常
	 */
	public static final String P_RECORD_STATUS_NORMAL = "1";
	
	/**
	 * 设备类型
	 */
	public static final String DEVICE_TYPE = "400";    
	public static final String DEVICE_TYPE_TOLLGATE = "01";    
	public static final String DEVICE_TYPE_ELE_POLICE = "02";    
	public static final String DEVICE_TYPE_VIDEO = "03";    
	public static final String DEVICE_TYPE_MEASURE_SPEED = "04";    
	public static final String DEVICE_TYPE_METEOR = "05";    
	public static final String DEVICE_TYPE_SPEED_PLATE = "06";    
	public static final String DEVICE_TYPE_LED = "07";    
	public static final String DEVICE_TYPE_EVENT = "08";    
	public static final String DEVICE_TYPE_FLOW_DETECT = "09";    
	public static final String DEVICE_TYPE_MSG_STATION = "10";    
	public static final String DEVICE_TYPE_VIO_STOP = "11";    
	public static final String DEVICE_TYPE_VIO_HOLD = "12";    
	public static final String DEVICE_TYPE_RETROGRADE = "13";    
	public static final String DEVICE_TYPE_SIGNAL = "14";    
	public static final String DEVICE_TYPE_ZD_SIGNAL = "15";    
	public static final String DEVICE_TYPE_VEH_PLAT = "16";    
	
	/**
	 * 设备状态
	 */
	public static final String DEVICE_STATUS = "412";
	/**
	 * 正常
	 */
	public static final String DEVICE_STATUS_NOMAL = "1";
	/**
	 * 离线
	 */
	public static final String DEVICE_STATUS_OFFLINE = "2";
	/**
	 * 故障
	 */
	public static final String DEVICE_STATUS_BUG = "3";
	
	/**
	 * 异常
	 */
	public static final String DEVICE_STATUS_EXCEPTION = "4";
	
}
