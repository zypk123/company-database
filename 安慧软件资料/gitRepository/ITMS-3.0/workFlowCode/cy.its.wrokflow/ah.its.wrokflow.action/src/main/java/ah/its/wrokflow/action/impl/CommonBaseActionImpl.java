package ah.its.wrokflow.action.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import ah.its.wrokflow.action.CommonBaseActionI;
import ah.its.wrokflow.repository.CommonBaseRepositoryI;
import ah.its.wrokflow.repository.dao.DeviceApprove;

@RestController
@RequestMapping("/workFlow/commonBaseService")
public class CommonBaseActionImpl implements CommonBaseActionI {

	@Autowired
	private CommonBaseRepositoryI  commonBaseRepositoryImpl;
	
	@RequestMapping(value = "/selectAllData")
	@Override
	public Map selectAllData(@RequestBody Map<String, Object> map) {
		
		//是否分页
		String  pageInfo = (String) map.get("pageInfo");
		//调用MAP 方法
		String method = (String)map.get("serviceMethod");
		Map  returnMap  = new HashMap();
		if("1".equals(pageInfo)){//需要分页
			int currentPage =  (int) map.get("currentPage");
			int pageSize =  (int) map.get("pageSize");
			PageHelper.startPage(Integer.valueOf(currentPage), Integer.valueOf(pageSize));
			List list;
			try {
				list = commonBaseRepositoryImpl.selectAllData(map, method);
				PageInfo page = new PageInfo(list);
				returnMap.put("status", "1");
				returnMap.put("data", list);
				returnMap.put("total", page.getTotal());
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				returnMap.put("status", "0");
				returnMap.put("error", "获取数据出现问题！");
			}
		}else{//直接获取数据
			List list;
			try {
				list = commonBaseRepositoryImpl.selectAllData(map,method);
				returnMap.put("data", list);
			} catch (NoSuchMethodException | SecurityException
					| IllegalAccessException | IllegalArgumentException
					| InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				returnMap.put("status", "0");
				returnMap.put("error", "获取数据出现问题！");
			}
		}
		return returnMap;
	}

}
