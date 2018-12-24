/**
 * @Title: IWhiteListAction.java
 * @Package cy.its.violation.rest.action
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月4日 下午2:56:33
 * @version V1.0
 * @Revision : $Rev$
 * @Id: $Id$
 *
 * Company: 安徽超远信息技术有限公司
 * Copyright: Copyright (c) 2015 
 */

package cy.its.violation.rest.action;

import java.text.ParseException;
import java.util.Map;

import cy.its.violation.rest.dto.WhiteListDto;

/**
 * @ClassName: IWhiteListAction
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月4日 下午2:56:33
 *
 */

public interface IWhiteListAction {
	// 查询
	Map searchWhiteList(WhiteListDto whiteListDto);

	// 查询
	Map searchWhiteVioList(WhiteListDto whiteListDto);

	// 添加
	String createWhiteList(WhiteListDto whiteListDto) throws ParseException;

	// 更新
	public String goUpdateWhiteList(WhiteListDto whiteListDto) throws ParseException;

	// 批量删除
	public String goDeleteWhiteList(String whiteListStrId);

	// 单个删除
	public String goRemoveWhiteList(String whiteListId);
}
