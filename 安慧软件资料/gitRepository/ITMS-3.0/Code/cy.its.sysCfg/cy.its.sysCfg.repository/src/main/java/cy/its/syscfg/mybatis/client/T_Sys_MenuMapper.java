package cy.its.syscfg.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.syscfg.mybatis.model.T_Sys_Menu;

public interface T_Sys_MenuMapper {
    int deleteByPrimaryKey(String menuCode);

    int insert(T_Sys_Menu record);

    int insertSelective(T_Sys_Menu record);

    T_Sys_Menu selectByPrimaryKey(String menuCode);

    int updateByPrimaryKeySelective(T_Sys_Menu record);

    int updateByPrimaryKey(T_Sys_Menu record);

	List<T_Sys_Menu> selectMenusByUser(@Param("userId")String userId);
}