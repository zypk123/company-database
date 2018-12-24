/**
 * @Title: IOperationLog.java
 * @Package cy.its.sysCfg.rest.aciton
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年12月3日 上午9:11:04
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.action;

import java.util.Map;

import cy.its.sysCfg.rest.dto.OperationLogDto;

/**
  * @ClassName: IOperationLog
  * @Description: TODO(这里要填写用途)
  * @author wangyf wangyf@cychina.cn
  * @date 2015年12月3日 上午9:11:04
  *
  */

public interface IOperationLogAction {
	//查询操作日志
	public Map<String,Object> goFindByOperationLog(OperationLogDto operationLogDto) throws Exception;
}
