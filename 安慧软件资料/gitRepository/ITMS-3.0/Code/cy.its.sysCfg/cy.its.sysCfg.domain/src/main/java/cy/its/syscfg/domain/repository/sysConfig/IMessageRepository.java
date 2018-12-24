package cy.its.syscfg.domain.repository.sysConfig;

import java.util.List;
import java.util.Map;

import cy.its.com.domain.IRepository;
import cy.its.syscfg.domain.criteria.MessageCriteria;
import cy.its.syscfg.domain.model.sysLog.MessageInfo;

public interface IMessageRepository extends IRepository<MessageInfo> {
	List<Map<String, String>> getMessageList(MessageCriteria messageCriteria) throws Exception;
}
