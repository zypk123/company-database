package ah.its.wrokflow.repository;

import java.util.List;
import java.util.Map;

import ah.its.wrokflow.repository.dao.WfNotice;

/**
* @Title: WfNoticeRepositoryI.java 
* @Package ah.its.wrokflow.repository 
* @Description: TODO(用一句话描述该文件做什么) 
* @author lil@cychina.cn
* @date 2016年5月28日 下午4:28:46 
* @version V1.0   
 */
public interface WfNoticeRepositoryI {
	
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
