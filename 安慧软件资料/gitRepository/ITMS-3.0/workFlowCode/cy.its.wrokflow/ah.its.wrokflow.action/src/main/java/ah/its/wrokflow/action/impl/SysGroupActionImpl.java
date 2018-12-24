package ah.its.wrokflow.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ah.its.wrokflow.action.SysGroupActionI;
import ah.its.wrokflow.domain.SysGroupDomainI;
import ah.its.wrokflow.domain.util.ReturnResultUtil;
import ah.its.wrokflow.dto.SysGroupDto;
import ah.its.wrokflow.repository.dao.SysGroup;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/workFlow/sysGroupService")
public class SysGroupActionImpl implements SysGroupActionI {
	@Autowired
	private SysGroupDomainI sysGroupDomainImpl;

	@Override
	@RequestMapping("/queryGroups")
	public Map queryGroups(@RequestBody SysGroupDto groupDto) {
		SysGroup group = JSONObject.parseObject(JSONObject.toJSONString(groupDto),SysGroup.class);
		PageHelper.startPage(groupDto.getStartPage(), groupDto.getPageSize());
		List  list  = sysGroupDomainImpl.querygroups(group);
		PageInfo page = new PageInfo(list);
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("data", list);
		result.put("totalsize", page.getTotal());
		return ReturnResultUtil.returnSuccessResult(result);
	}
}