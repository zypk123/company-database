package cy.its.service.standardization.validator;

import cy.its.service.common.StringUtil;
import cy.its.service.common.dataModel.MsgInfo;

public class MsgInfoValidator extends BaseValidator<MsgInfo> {

	@Override
	void fillFilters() {
		addFilter(u -> dbLen(u.getSender()) > 32, "Sender不能大于32位;");
		addFilter(u -> u.getSendTime() == null, "SendTime不可为空;");
		addFilter(u -> is1HMoreThanNow(u.getSendTime()), "SendTime时间超过当前时间1个小时,请校时;");
		addFilter(u -> dbLen(u.getSendStatus()) > 1, "SendStatus不能大于1位;");
		addFilter(u -> dbLen(u.getMsgType()) > 2, "MsgType不能大于2位;");
		addFilter(u -> dbLen(u.getMsgTitle()) > 128, "MsgTitle不能大于128位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getMsgContent()), "MsgContent不能为空;");
		addFilter(u -> dbLen(u.getMsgContent()) > 2048, "MsgContent不能大于2048位;");
		addFilter(u -> StringUtil.isNullOrEmpty(u.getReceiver()), "Receiver不能为空;");
		addFilter(u -> dbLen(u.getReceiver()) > 32, "Receiver不能大于32位;");
		addFilter(u -> dbLen(u.getRemark()) > 256, "Remark不能大于256位;");
	}
}
