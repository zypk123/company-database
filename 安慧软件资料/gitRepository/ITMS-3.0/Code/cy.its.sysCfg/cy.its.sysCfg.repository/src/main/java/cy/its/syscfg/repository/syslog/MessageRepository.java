package cy.its.syscfg.repository.syslog;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;

import cy.its.com.util.ParamUtil;
import cy.its.com.util.StringUtil;
import cy.its.syscfg.domain.criteria.MessageCriteria;
import cy.its.syscfg.domain.model.sysLog.MessageInfo;
import cy.its.syscfg.domain.repository.sysConfig.IMessageRepository;
import cy.its.syscfg.mybatis.client.T_Msg_InfoMapper;

@Service
public class MessageRepository implements IMessageRepository {
	@Autowired
	T_Msg_InfoMapper t_Msg_InfoMapper;

	@Override
	public List<Map<String, String>> getMessageList(MessageCriteria messageCriteria) throws Exception {
		// TODO Auto-generated method stub
		if(!StringUtil.isNullOrEmpty(messageCriteria.getOrderName())){
			PageHelper.orderBy(messageCriteria.getOrderName() + " " + messageCriteria.getOrderType());
		}
		return t_Msg_InfoMapper.getMessageList(ParamUtil.parseParams(messageCriteria));
	}

	@Override
	public MessageInfo aggregateOfId(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String save(MessageInfo obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int update(MessageInfo obj) {
		// TODO Auto-generated method stub
		return 0;
	}
}
