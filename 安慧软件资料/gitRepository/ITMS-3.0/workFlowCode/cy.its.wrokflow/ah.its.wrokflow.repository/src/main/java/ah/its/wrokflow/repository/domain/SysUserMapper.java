package ah.its.wrokflow.repository.domain;

import java.util.List;

import ah.its.wrokflow.repository.dao.SysUser;

public interface SysUserMapper {
    int deleteByPrimaryKey(Object id);

    int insert(SysUser record);

    int insertSelective(SysUser record);

    SysUser selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(SysUser record);

    int updateByPrimaryKey(SysUser record);
    
    List<SysUser> queryUserShipById(Object id);
    
    List<SysUser> queryUsers(SysUser user);
    List<SysUser> queryUsersWithGroupinfo(SysUser user);
    
    List<SysUser> queryUsersByGroupId(String groupId);
}