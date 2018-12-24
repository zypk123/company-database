package cy.its.device.mybatis.client;

import cy.its.device.domain.model.SysUseStatus;

public interface SysUseStatusMapper {
    int deleteByPrimaryKey(String sysUseStatusId);

    int insert(SysUseStatus record);

    int insertSelective(SysUseStatus record);

    SysUseStatus selectByPrimaryKey(String sysUseStatusId);

    int updateByPrimaryKeySelective(SysUseStatus record);

    int updateByPrimaryKey(SysUseStatus record);
}