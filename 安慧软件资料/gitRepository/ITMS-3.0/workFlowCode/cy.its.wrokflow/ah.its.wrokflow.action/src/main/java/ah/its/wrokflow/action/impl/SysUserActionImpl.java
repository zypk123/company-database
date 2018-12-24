package ah.its.wrokflow.action.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ah.its.wrokflow.action.SysUserActionI;
import ah.its.wrokflow.domain.SysUserDomainI;
import ah.its.wrokflow.domain.util.ReturnResultUtil;
import ah.its.wrokflow.dto.SysUserDto;
import ah.its.wrokflow.repository.dao.SysUser;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@RestController
@RequestMapping("/workFlow/sysUserService")
public class SysUserActionImpl implements SysUserActionI {

	
	@Autowired
	private SysUserDomainI sysUserDomainImpl;//用户操作领域类
	
	/* (non-Javadoc)
	 * 状态返回 0 验证失败 1验证成功 
	 * 返回成功后，要返回用户所在组信息，写入cookie
	 */
	@Override
	@RequestMapping(value = "/validateUser")
	public Map validateUser(@RequestParam("loginName") String loginName,@RequestParam("pwd") String pwd) {
		// TODO Auto-generated method stub
		return sysUserDomainImpl.queryUserShipById(loginName, pwd);
	}

	/**
	 * 查找用户
	 */
	@Override
	@RequestMapping(value = "/findUsers")
	public Map findUsers(@RequestBody SysUser user) {
		return sysUserDomainImpl.queryUsers(user);
	}

	@Override
	@RequestMapping(value = "/queryUsersByGroupId")
	public Map queryUsersByGroupId(@RequestParam("groupId") String groupId,@RequestParam("startPage") String startPage,@RequestParam("pageSize") String pageSize) {
		PageHelper.startPage(Integer.valueOf(startPage), Integer.valueOf(pageSize));
		List  list  = sysUserDomainImpl.queryUsersByGroupId(groupId);
		PageInfo page = new PageInfo(list);
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("data", list);
		result.put("totalsize", page.getTotal());
		return ReturnResultUtil.returnSuccessResult(result);
	}
	
	@Override
	@RequestMapping(value = "/queryUsersWithGroupinfo")
	public Map queryUsersWithGroupinfo(@RequestBody SysUserDto userDto) {
		SysUser  sysuser = JSONObject.parseObject(JSONObject.toJSONString(userDto),SysUser.class);
		PageHelper.startPage(userDto.getStartPage(), userDto.getPageSize());
		List  list  = sysUserDomainImpl.queryUsersWithGroupinfo(sysuser);
		PageInfo page = new PageInfo(list);
		Map<String,Object> result=new HashMap<String, Object>();
		result.put("data", list);
		result.put("totalsize", page.getTotal());
		return ReturnResultUtil.returnSuccessResult(result);
	}
}