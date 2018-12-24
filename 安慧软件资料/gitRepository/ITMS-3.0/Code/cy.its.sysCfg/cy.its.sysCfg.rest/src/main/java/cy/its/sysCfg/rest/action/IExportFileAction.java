/**
 * @Title: IExportFileAction.java
 * @Package cy.its.sysCfg.rest.aciton
 * @Description: 下载中心 
 * @author gyf guanyf@cychina.cn
 * @date 2015年11月27日 上午9:28:08
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.action;


import java.text.ParseException;

import cy.its.sysCfg.rest.dto.ExportDto;

public interface IExportFileAction {
	Object selectAll(ExportDto exportDto) throws ParseException;
	int delete(String id);
}
