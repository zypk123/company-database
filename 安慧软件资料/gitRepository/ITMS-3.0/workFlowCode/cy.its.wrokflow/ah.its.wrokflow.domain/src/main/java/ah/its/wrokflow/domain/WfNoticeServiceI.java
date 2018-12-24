package ah.its.wrokflow.domain;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.WfNotice;

/**
* @Title: WfMessageServiceI.java 
* @Package ah.its.wrokflow.domain 
* @Description:信息处理类
* @author lil@cychina.cn
* @date 2016年5月28日 下午4:46:18 
* @version V1.0   
 */
public interface WfNoticeServiceI {
	
	/**
	 * 根据notice查找
	 */
	public List<WfNotice> queryNoticesByRecord(WfNotice notice);
	/**
	 * 添加
	 * @param message
	 * @return
	 */
	public int insertBatch(Map map);
}
