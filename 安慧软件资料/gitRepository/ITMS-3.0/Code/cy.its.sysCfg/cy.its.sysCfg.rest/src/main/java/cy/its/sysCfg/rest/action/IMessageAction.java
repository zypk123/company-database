package cy.its.sysCfg.rest.action;

import cy.its.sysCfg.rest.dto.MessageDto;

public interface IMessageAction {
	Object getMessageList(MessageDto messageDto) throws Exception;
}
