package cy.its.device.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.device.domain.model.SysFunction;

public interface SysFunctionMapper {
    int deleteByPrimaryKey(String deviceSysFunctionId);

    int insert(SysFunction record);

    int insertSelective(SysFunction record);

    SysFunction selectByPrimaryKey(String deviceSysFunctionId);

    int updateByPrimaryKeySelective(SysFunction record);

    int updateByPrimaryKey(SysFunction record);
    
    List<SysFunction> selectFunctions(@Param("modelId") String modelId);
}