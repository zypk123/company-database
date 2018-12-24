/**
 * @Title: IDeviceGroupAction.java
 * @Package cy.its.violation.rest.action
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月9日 下午7:14:53
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.violation.rest.action;

import java.util.ArrayList;
import java.util.Map;

import cy.its.violation.rest.dto.DeviceGroupDto;

/**
  * @ClassName: IDeviceGroupAction
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年11月9日 下午7:14:53
  *
  */

public interface IDeviceGroupAction {
	
	ArrayList<DeviceGroupDto> findDeviceAction(DeviceGroupDto deviceGroupDto) throws Exception;
	Map findDGroupAction() throws Exception;
	Map findDeviceList(String  orgId);
	public String createDGroupAction( String deviceSysNbrStr , String userId);
	public String updateDGroupAction(DeviceGroupDto deviceGroupDto);
	public String deleteDGroupAction(String deviceGroupStrId);
	public String removeDGroupAction(String userId);
}
