package cy.its.syscfg.mybatis.client;

import java.util.List;
import java.util.Map;


import cy.its.syscfg.mybatis.model.T_Sys_District;


public interface T_Sys_DistrictMapper {
	
    int deleteByPrimaryKey(String districtCode);

    int insert(T_Sys_District record);

    int insertSelective(T_Sys_District record);

    T_Sys_District selectByPrimaryKey(String districtCode);

    int updateByPrimaryKeySelective(T_Sys_District record);

    int updateByPrimaryKey(T_Sys_District record);
    
    List<T_Sys_District> selectDistricts(Map<String, Object> params);
    
    Integer countDistricts(Map<String, Object> params);
}