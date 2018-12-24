package ah.its.wrokflow.domain.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.domain.WfMessageDomainI;
import ah.its.wrokflow.repository.WfMessageRepositoryI;
import ah.its.wrokflow.repository.dao.WfMessage;

/**
* @Title: WfMessageDomainImpl.java 
* @Package ah.its.wrokflow.domain.Impl 
* @Description: 通知公告的领域处理类
* @author chengj@cychina.cn
* @date 2016年5月20日 上午11:23:17 
* @version V1.0   
 */
@Service
public class WfMessageDomainImpl implements WfMessageDomainI {

	@Autowired
	private WfMessageRepositoryI wfMessageRepositoryImpl;
	
	@Override
	public WfMessage queryMessageById(String id) {
		return wfMessageRepositoryImpl.queryMessageById(id);
	}

	@Override
	public List<WfMessage> queryMessagesByRecord(WfMessage message) {
		return wfMessageRepositoryImpl.queryMessagesByRecord(message);
	}

	@Override
	public int addMessage(WfMessage message) {
		return wfMessageRepositoryImpl.addMessage(message);
	}

	@Override
	public int updateMessage(WfMessage message) {
		return wfMessageRepositoryImpl.updateMessage(message);
	}

	@Override
	public int deleteMessageById(String messageId) {
		return wfMessageRepositoryImpl.deleteMessageById(messageId);
	}
}
