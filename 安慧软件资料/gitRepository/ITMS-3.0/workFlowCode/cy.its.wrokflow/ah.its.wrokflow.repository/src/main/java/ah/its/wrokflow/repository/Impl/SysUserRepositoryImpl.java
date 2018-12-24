package ah.its.wrokflow.repository.Impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ah.its.wrokflow.repository.SysUserRepositoryI;
import ah.its.wrokflow.repository.dao.SysUser;
import ah.its.wrokflow.repository.domain.SysUserMapper;

/**
* @Title: SysUserRepositoryImpl.java 
* @Package ah.its.wrokflow.repository.Impl 
* @Description: 用户操作类
* @author lil@cychina.cn
* @date 2016年4月5日 下午6:36:13 
* @version V1.0   
 */
@Service
public class SysUserRepositoryImpl implements SysUserRepositoryI {

	
	
	@Autowired
	private SysUserMapper sysUserMapper;//用户操作接口
	
	
	@Override
	public SysUser queryUserShipById(String userId) {
		
		List<SysUser> list = sysUserMapper.queryUserShipById(userId);
		if(list.size() > 0){
			return list.get(0);
		}else{
			return null;
		}
		
		
	}


	@Override
	public List<SysUser> queryUsers(SysUser user) {
		List<SysUser> list = sysUserMapper.queryUsers(user);
		return list;
	}


	@Override
	public List<SysUser> queryUsersByGroupId(String groupId) {
		List<SysUser> list = sysUserMapper.queryUsersByGroupId(groupId);
		return list;
	}


	@Override
	public List<SysUser> queryUsersWithGroupinfo(SysUser user) {
		List<SysUser> list = sysUserMapper.queryUsersWithGroupinfo(user);
		return list;
	}
}
