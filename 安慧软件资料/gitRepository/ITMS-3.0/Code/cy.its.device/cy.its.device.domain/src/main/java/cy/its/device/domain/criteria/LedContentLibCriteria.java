package cy.its.device.domain.criteria;

import cy.its.com.domain.Criteria;

public class LedContentLibCriteria extends Criteria{

	/**
	 * 内容
	 */
	public String content;
	
	/**
	 * 信息类型  1 宣传文字、 2 违法警示、3 交通管制、4 交通路况、5 交通事故、6 交通法规、7 安全提示、8 气象信息
	 */
	public String messageType;
}
