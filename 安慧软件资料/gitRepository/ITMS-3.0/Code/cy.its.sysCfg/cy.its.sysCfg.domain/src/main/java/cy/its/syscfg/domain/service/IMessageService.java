package cy.its.syscfg.domain.service;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.domain.criteria.MessageCriteria;

public interface IMessageService {
	/**
	 * 
	 * @param messageCriteria
	 * @return
	 * @throws Exception
	 */
	public List<Map<String, String>> getMessageList(MessageCriteria messageCriteria) throws Exception;
}
