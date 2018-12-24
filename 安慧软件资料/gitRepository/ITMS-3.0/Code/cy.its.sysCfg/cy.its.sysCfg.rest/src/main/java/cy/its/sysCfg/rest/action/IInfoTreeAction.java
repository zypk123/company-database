/**
 * @Title: IInfoTreeAction.java
 * @Package cy.its.sysCfg.rest.aciton.impl
 * @Description: 交通基础信息树
 * @author gyf guanyf@cychina.cn
 * @date 2015年10月30日 下午3:29:16
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.sysCfg.rest.action;

import java.util.List;

import cy.its.sysCfg.rest.dto.InfoTreeDto;

public interface IInfoTreeAction {
	/**
	 * 获取数据树数据
	 * @return 数据树对象
	 * @throws Exception
	 */
	List<InfoTreeDto> getInfoTree()throws Exception;
	String createTree();
}
