/**
 * @Title: IDeviceHomeAction.java
 * @Package cy.its.device.rest.action
 * @Description: TODO(这里要填写用途)
 * @author Administrator Administrator@cychina.cn
 * @date 2015年12月18日 上午10:31:56
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.device.rest.action;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
  * @ClassName: IDeviceHomeAction
  * @Description: TODO(这里要填写用途)
  * @author Administrator Administrator@cychina.cn
  * @date 2015年12月18日 上午10:31:56
  *
  */

public interface IDeviceHomeAction {	

	Map getDevGenalSituation(String currentOrgPrivilegeCode, String currentOrgId) throws Exception;

	List<Map<String, Object>> getDevGenalSituationIntervalTime(String orgPrivCodes, String columns, Boolean isNeetCerti,
			String currentOrgPrivilegeCode);

	Map getDevGenalSituationForBigTeam(String currentOrgPrivilegeCode) throws Exception;

	Map getDevGenalSituationForBigTeamInterval(Boolean isNeetCerti, String currentOrgPrivilegeCode) throws Exception;
}
