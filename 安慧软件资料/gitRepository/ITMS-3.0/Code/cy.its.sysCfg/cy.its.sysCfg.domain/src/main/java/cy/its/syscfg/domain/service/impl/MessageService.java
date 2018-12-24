package cy.its.syscfg.domain.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.syscfg.domain.criteria.MessageCriteria;
import cy.its.syscfg.domain.repository.sysConfig.IMessageRepository;
import cy.its.syscfg.domain.service.IMessageService;

@Service
public class MessageService implements IMessageService {
	
	@Autowired
	IMessageRepository messageRepository;

	@Override
	public List<Map<String, String>> getMessageList(MessageCriteria messageCriteria) throws Exception {
		return messageRepository.getMessageList(messageCriteria);
	}
}
