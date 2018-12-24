package cy.its.syscfg.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.syscfg.mybatis.model.T_Sys_Festival;

public interface T_Sys_FestivalMapper {
    int deleteByPrimaryKey(String festivalId);

    int insert(T_Sys_Festival record);

    int insertSelective(T_Sys_Festival record);

    T_Sys_Festival selectByPrimaryKey(String festivalId);

    int updateByPrimaryKeySelective(T_Sys_Festival record);

    int updateByPrimaryKey(T_Sys_Festival record);
    
    List<T_Sys_Festival> selectAllFestivals(Map yearmap);
}