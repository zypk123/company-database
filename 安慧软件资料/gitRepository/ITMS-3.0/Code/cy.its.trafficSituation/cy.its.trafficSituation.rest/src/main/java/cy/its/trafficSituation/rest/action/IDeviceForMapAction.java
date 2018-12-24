/**
 * @Title: IDeviceForTraffic.java
 * @Package cy.its.trafficSituation.rest.action
 * @Description: TODO(这里要填写用途)
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月13日 下午2:46:41
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.trafficSituation.rest.action;

import java.util.List;

import cy.its.trafficSituation.rest.dto.DeviceDto;

public interface IDeviceForMapAction {
	/**
	 * 
	  * @Title: selectByType
	  * @Description: 根据设备类型获取所有的设备
	  * @param @param deviceType
	  * @param @return
	  * @param @throws Exception    设定文件
	  * @return List<DeviceDto>    返回类型
	  * @throws
	 */
	List<DeviceDto> selectByType(String deviceType,String currentOrgPrivilegeCode) throws Exception;
}
