package ah.its.wrokflow.action;

import java.util.Map;

import ah.its.wrokflow.dto.Message;

/**
* @Title: WfMessageActionI.java 
* @Package ah.its.wrokflow.action 
* @Description: 通知公告controller接口
* @author chengj@cychina.cn
* @version V1.0   
 */
public interface WfMessageActionI {
	/**
	 * 根据id查询
	 */
	public Map queryMessageById(String id);
	/**
	 * 根据id删除
	 */
	public Map deleteMessageById(String id);
	/**
	 * 根据message查找
	 */
	public Map queryMessagesByRecord(Message message);
	/**
	 * 添加
	 * @param message
	 * @return
	 */
	public Map addMessage(Message message);
	/**
	 * 修改
	 * @param message
	 * @return
	 */
	public Map updateMessage(Message message);
}