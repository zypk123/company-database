package cy.its.syscfg.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.syscfg.domain.model.home.SysDailyMenu;

public interface SysDailyMenuMapper {
    int deleteByPrimaryKey(String dailyMenuId);

    int insert(SysDailyMenu record);

    int insertSelective(SysDailyMenu record);

    SysDailyMenu selectByPrimaryKey(String dailyMenuId);

    int updateByPrimaryKeySelective(SysDailyMenu record);

    int updateByPrimaryKey(SysDailyMenu record);

	List<SysDailyMenu> selectMenusByUserId(@Param("userId")String userId);

	int deleteByUserPermmission(); 
}