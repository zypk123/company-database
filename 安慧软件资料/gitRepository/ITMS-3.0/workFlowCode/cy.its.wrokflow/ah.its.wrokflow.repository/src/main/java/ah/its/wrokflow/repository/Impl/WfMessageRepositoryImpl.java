package ah.its.wrokflow.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.repository.WfMessageRepositoryI;
import ah.its.wrokflow.repository.dao.WfMessage;
import ah.its.wrokflow.repository.domain.WfMessageMapper;

/**
* @Title: WfMessageRepositoryImpl.java 
* @Package ah.its.wrokflow.repository.Impl 
* @Description: 通知公告操作类
* @author chengj@cychina.cn
* @date 2016年5月20日 上午11:36:13 
* @version V1.0   
 */
@Service
public class WfMessageRepositoryImpl implements WfMessageRepositoryI {
	@Autowired
	private WfMessageMapper wfMessageMapper;
	
	@Override
	public WfMessage queryMessageById(String id) {
		return wfMessageMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<WfMessage> queryMessagesByRecord(WfMessage message) {
		return wfMessageMapper.queryMessagesByRecord(message);
	}

	@Override
	public int addMessage(WfMessage message) {
		return wfMessageMapper.insert(message);
	}

	@Override
	public int updateMessage(WfMessage message) {
		return wfMessageMapper.updateByPrimaryKey(message);
	}

	@Override
	public int deleteMessageById(String messageId) {
		return wfMessageMapper.deleteByPrimaryKey(messageId);
	}
}
