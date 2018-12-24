package ah.its.wrokflow.domain;

import java.util.List;

import ah.its.wrokflow.repository.dao.WfMessage;

/**
* @Title: WfMessageDomainI.java 
* @Package ah.its.wrokflow.domain 
* @Description: 通知公告的领域 
* @author chengj@cychina.cn
* @version V1.0   
 */
public interface WfMessageDomainI {
	/**
	 * 根据id查询
	 */
	public WfMessage queryMessageById(String id);
	/**
	 * 根据message查找
	 */
	public List<WfMessage> queryMessagesByRecord(WfMessage message);
	/**
	 * 添加
	 * @param message
	 * @return
	 */
	public int addMessage(WfMessage message);
	/**
	 * 修改
	 * @param message
	 * @return
	 */
	public int updateMessage(WfMessage message);
	public int deleteMessageById(String messageId);
}
