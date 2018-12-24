/**
 * @Title: IViolationCodeAction.java
 * @Package cy.its.violation.rest.action
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月13日 下午4:40:24
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

import org.springframework.web.bind.annotation.ModelAttribute;

import cy.its.violation.rest.dto.ViolationCodeDto;

/**
 * @ClassName: IViolationCodeAction
 * @Description: TODO(这里要填写用途)
 * @author wangyf wangyf@cychina.cn
 * @date 2015年11月13日 下午4:40:24
 *
 */

public interface IViolationCodeAction {
	// 查询
	Map findViolationCode(ViolationCodeDto vioationCodeDto) throws Exception;

	// 添加
	String createVioationCode(ViolationCodeDto vioActionDto) throws ParseException, Exception;

	// 更新
	public String updateVioationCode(ViolationCodeDto vioationCodeDto) throws ParseException, Exception;

	// 批量删除
	public String deleteVioationCode(String vioActionCodeStr , String vioActionMatchIdStr);

	// 单个删除
	public String removeVioationCode(String vioationCode , String vioActionMatchId);
}
