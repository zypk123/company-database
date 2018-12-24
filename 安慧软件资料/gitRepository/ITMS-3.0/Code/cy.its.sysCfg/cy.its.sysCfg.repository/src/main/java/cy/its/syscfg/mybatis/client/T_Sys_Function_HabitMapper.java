package cy.its.syscfg.mybatis.client;

import cy.its.syscfg.mybatis.model.T_Sys_Function_Habit;

public interface T_Sys_Function_HabitMapper {
    int deleteByPrimaryKey(String useHabitsId);

    int insert(T_Sys_Function_Habit record);

    int insertSelective(T_Sys_Function_Habit record);

    T_Sys_Function_Habit selectByPrimaryKey(String useHabitsId);

    int updateByPrimaryKeySelective(T_Sys_Function_Habit record);

    int updateByPrimaryKey(T_Sys_Function_Habit record);
}