package cy.its.service.standardization.dataMaker;

import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.dataModel.MsgInfo;
import cy.its.service.common.ioc.Export;
import cy.its.service.standardization.dataMaker.originalModel.SMSInfo;
import cy.its.service.standardization.validator.BaseValidator;
import cy.its.service.standardization.validator.MsgInfoValidator;

@Export
public class MsgInfoMaker extends DataMaker<SMSInfo, MsgInfo> {

	public MsgInfoMaker() {
		super("¶ÌÐÅ¼ÇÂ¼", SMSInfo.class, MsgInfo.class, ConstValue.ROUTE_KEY_ORIGINAL_SMS,
				ConstValue.ROUTE_KEY_STANDARD_SMS, "original_sms_info", true);
	}

	@Override
	MsgInfo translate(SMSInfo s) {
		MsgInfo msgInfo = new MsgInfo();
		msgInfo.setSender(s.getSender());
		msgInfo.setSendTime(DateUtil.parseDate(s.getSendTime()));
		if (s.getSendStatus() != null) {
			msgInfo.setSendStatus(s.getSendStatus().toString());
		}

		if (s.getType() != null) {
			msgInfo.setMsgType(s.getType().toString());
		}
		msgInfo.setMsgContent(s.getContent());
		msgInfo.setReceiver(s.getReceiver());
		return msgInfo;
	}

	@Override
	BaseValidator<MsgInfo> validator() {
		return new MsgInfoValidator();
	}

}
