package ah.its.wrokflow.repository.domain;

import java.util.List;

import ah.its.wrokflow.repository.dao.WfGroupMenu;
import ah.its.wrokflow.repository.dao.WfMenu;

public interface WfMenuMapper {
    int deleteByPrimaryKey(String menuId);

    int insert(WfMenu record);

    int insertSelective(WfMenu record);

    WfMenu selectByPrimaryKey(String menuId);

    int updateByPrimaryKeySelective(WfMenu record);

    int updateByPrimaryKey(WfMenu record);
    
    List<WfMenu> queryMenuByGroupId(String groupId);

	int addGroupMenuMapRecord(List<WfGroupMenu> records);

	int delGroupMenuMapRecord(String groupId);
}