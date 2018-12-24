package ah.its.wrokflow.domain;

import java.util.List;

import ah.its.wrokflow.repository.dao.WfGroupMenu;
import ah.its.wrokflow.repository.dao.WfMenu;

/**
* @Title: WfMenuDomainI.java 
* @Package ah.its.wrokflow.domain 
* @Description: 菜单操作的领域 
* @author chengj@cychina.cn
* @version V1.0   
 */
public interface WfMenuDomainI {
	/**
	 * 按groupId查询菜单
	 * @param groupId
	 * @return
	 */
	public List<WfMenu> queryMenuByGroupId(String groupId);
	/**
	 * 批量添加Group Menu对应关系
	 * @param records
	 * @return
	 */
	public int addGroupMenuMapRecord(List<WfGroupMenu> records);
	public int delGroupMenuMapRecord(String groupId);
}
