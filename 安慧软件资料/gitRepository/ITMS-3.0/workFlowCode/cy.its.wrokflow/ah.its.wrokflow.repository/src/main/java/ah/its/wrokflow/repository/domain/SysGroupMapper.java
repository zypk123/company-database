package ah.its.wrokflow.repository.domain;

import java.util.List;

import ah.its.wrokflow.repository.dao.SysGroup;

public interface SysGroupMapper {
    int deleteByPrimaryKey(Object id);

    int insert(SysGroup record);

    int insertSelective(SysGroup record);

    SysGroup selectByPrimaryKey(Object id);

    int updateByPrimaryKeySelective(SysGroup record);

    int updateByPrimaryKey(SysGroup record);
    
    List<SysGroup> querygroups(SysGroup record);
}