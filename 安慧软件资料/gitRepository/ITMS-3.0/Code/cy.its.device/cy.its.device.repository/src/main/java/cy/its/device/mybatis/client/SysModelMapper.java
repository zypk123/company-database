package cy.its.device.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.SysModel;

public interface SysModelMapper {
    int deleteByPrimaryKey(String deviceSysModelId);

    int insert(SysModel record);

    int insertSelective(SysModel record);

    SysModel selectByPrimaryKey(String deviceSysModelId);

    int updateByPrimaryKeySelective(SysModel record);

    int updateByPrimaryKey(SysModel record);

	List<SysModel> selectModels(@Param("sysModelName")String sysModelName);
}