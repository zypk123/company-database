package ah.its.wrokflow.repository;

import java.util.List;

import ah.its.wrokflow.repository.dao.WfGroupMenu;
import ah.its.wrokflow.repository.dao.WfMenu;

/**
* @Title: WfMenuRepositoryI.java 
* @Package ah.its.wrokflow.repository 
* @Description:
* @author chengj@cychina.cn
* @version V1.0   
 */
public interface WfMenuRepositoryI {
	/**
	 * @param groupId
	 * @return
	 */
	public List<WfMenu> queryMenuByGroupId(String groupId);

	public int addGroupMenuMapRecord(List<WfGroupMenu> records);

	public int delGroupMenuMapRecord(String groupId);
}
