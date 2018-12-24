package cy.its.syscfg.mybatis.client;

import java.util.List;

import cy.its.syscfg.domain.model.traffic.SysArea;

public interface SysAreaMapper {
    int deleteByPrimaryKey(String areaId);

    int insert(SysArea record);

    int insertSelective(SysArea record);

    SysArea selectByPrimaryKey(String areaId);

    int updateByPrimaryKeySelective(SysArea record);

    int updateByPrimaryKey(SysArea record);
    
    List<SysArea> selectAreaList(SysArea record);
}