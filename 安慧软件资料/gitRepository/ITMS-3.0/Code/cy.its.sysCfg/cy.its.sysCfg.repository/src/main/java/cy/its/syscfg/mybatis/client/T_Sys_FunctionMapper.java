package cy.its.syscfg.mybatis.client;

import java.util.List;

import cy.its.syscfg.mybatis.model.T_Sys_Function;

public interface T_Sys_FunctionMapper {
    int deleteByPrimaryKey(String functionCode);

    int insert(T_Sys_Function record);

    int insertSelective(T_Sys_Function record);

    T_Sys_Function selectByPrimaryKey(String functionCode);

    int updateByPrimaryKeySelective(T_Sys_Function record);

    int updateByPrimaryKey(T_Sys_Function record);
    
    List<T_Sys_Function> selectAll();
}