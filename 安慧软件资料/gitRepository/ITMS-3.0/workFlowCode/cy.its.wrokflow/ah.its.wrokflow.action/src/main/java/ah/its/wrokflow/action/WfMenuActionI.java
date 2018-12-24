package ah.its.wrokflow.action;

import java.util.Map;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ah.its.wrokflow.repository.dao.WfGroupMenu;

/**
* @Title: WfMenuActionI.java 
* @Package ah.its.wrokflow.action 
* @Description: 菜单controller接口
* @author chengj@cychina.cn
* @version V1.0   
 */
public interface WfMenuActionI {
	/**
	 * 根据groupId查找菜单(菜单层级关系已整理)
	 * @param groupId
	 * @return
	 */
	public Map queryMenuByGroupId(String groupId);
	/**
	 * 根据groupId查找菜单（直接返回菜单列表）
	 * @param groupId
	 * @return
	 */
	public Map queryMenuListByGroupId(WfGroupMenu wfGroupMenu);
	/**
	 * 添加组织、菜单对应关系记录
	 * @param groupId
	 * @param menuIds menuId1，menuId2，menuId3，，，
	 * @return
	 */
	public Map addGroupMenuMapRecord(WfGroupMenu wfGroupMenu);
	JSONArray findMenuByGroupId(String menuId);
}