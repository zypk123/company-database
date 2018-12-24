package ah.its.wrokflow.action.impl;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import ah.its.wrokflow.action.WfMenuActionI;
import ah.its.wrokflow.domain.WfMenuDomainI;
import ah.its.wrokflow.domain.util.ReturnResultUtil;
import ah.its.wrokflow.dto.Menu;
import ah.its.wrokflow.dto.MenuNode;
import ah.its.wrokflow.repository.dao.WfGroupMenu;
import ah.its.wrokflow.repository.dao.WfMenu;
/**
* @Title: WfMenuAction.java 
* @Package ah.its.wrokflow.action.impl
* @Description: 菜单controller
* @author chengj@cychina.cn
* @version V1.0   
 */
@RestController
@RequestMapping("/workFlow/wfMenuService")
public class WfMenuAction implements WfMenuActionI{
	@Autowired
	private WfMenuDomainI wfMenuDomainImpl;
	
	@Override
	@RequestMapping(value="/queryMenuByGroupId/{groupId}")
	public Map queryMenuByGroupId(@PathVariable String groupId) {
		List<WfMenu> list = wfMenuDomainImpl.queryMenuByGroupId(groupId);
		Comparator<WfMenu> comparator = Comparator.comparing(WfMenu::getMenuType);
		list.sort(comparator);
		Menu menu=new Menu();
		for(WfMenu tmp:list){
			MenuNode  menuNode = JSONObject.parseObject(JSONObject.toJSONString(tmp),MenuNode.class);
			menu.addMenu(menu, menuNode);
		}
		JSONObject jsonMenu=(JSONObject) JSONObject.toJSON(menu);
		return ReturnResultUtil.returnSuccessResult(jsonMenu);
	}

	@Override
	@RequestMapping(value="/queryMenuListByGroupId")
	public Map queryMenuListByGroupId(@RequestBody WfGroupMenu wfGroupMenu) {
		String groupId=wfGroupMenu.getGroupId();
		if(groupId==null||groupId.trim().equals("")){
			return ReturnResultUtil.returnErrorResult("缺少参数groupId");
		}
		List<WfMenu> list = wfMenuDomainImpl.queryMenuByGroupId(groupId);
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("data", list);
		result.put("totalsize", list==null ? 0:list.size());
		return ReturnResultUtil.returnSuccessResult(result);
	}
	
	@Override
	@RequestMapping(value="/addGroupMenuMapRecord")
	public Map addGroupMenuMapRecord(@RequestBody WfGroupMenu wfGroupMenu) {
		String msg=null;
		if(wfGroupMenu.getMenuId()==null||wfGroupMenu.getMenuId().trim().equals("")){
			msg="缺少menuId。";
		}else if(wfGroupMenu.getGroupId()==null||wfGroupMenu.getGroupId().trim().equals("")){
			msg="缺少groupId。";
		}
		if(msg!=null){
			return ReturnResultUtil.returnErrorResult(msg);
		}
		List<WfGroupMenu> records=new ArrayList<WfGroupMenu>();
		String[] menus=wfGroupMenu.getMenuId().split(",");
		for(int i=0;i<menus.length;i++){
			WfGroupMenu record=new WfGroupMenu();
			String uuid=UUID.randomUUID().toString();
			record.setId(uuid.replaceAll("-", ""));
			record.setGroupId(wfGroupMenu.getGroupId());
			record.setMenuId(menus[i]);
			records.add(record);
		}
		wfMenuDomainImpl.delGroupMenuMapRecord(wfGroupMenu.getGroupId());
		int successCount=wfMenuDomainImpl.addGroupMenuMapRecord(records);
		return ReturnResultUtil.returnResult("AAAAAA", "添加成功数："+successCount, null);
	}

	@Override
	@RequestMapping(value="/findMenuByGroupId")
	public JSONArray findMenuByGroupId(@RequestParam(value="menuId",required=false) String menuId) {
		List<WfMenu> list = wfMenuDomainImpl.queryMenuByGroupId(menuId);
		JSONArray arr = new JSONArray();
		for(WfMenu tmp:list){
			JSONObject obj = new JSONObject();
			obj.put("value", tmp.getMenuId());
			obj.put("name", tmp.getMenuName());
			obj.put("parentCode", tmp.getMenuParentId());
			obj.put("spare", tmp.getMenuUrl());
			if(tmp.getMenuParentId()==null){
				obj.put("pId",0);
			}else{
				obj.put("pId",obj.get("parentCode"));
			}
			obj.put("id",obj.get("value"));
			arr.add(obj);
		}
		return arr;
	}
}