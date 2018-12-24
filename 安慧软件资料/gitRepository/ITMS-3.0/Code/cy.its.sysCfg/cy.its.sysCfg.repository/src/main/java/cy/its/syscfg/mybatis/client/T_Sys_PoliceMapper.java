package cy.its.syscfg.mybatis.client;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cy.its.syscfg.mybatis.model.T_Sys_Police;

public interface T_Sys_PoliceMapper {
    int deleteByPrimaryKey(String policeId);

    int insert(T_Sys_Police record);

    int insertSelective(T_Sys_Police record);

    T_Sys_Police selectByPrimaryKey(String policeId);

    int updateByPrimaryKeySelective(T_Sys_Police record);

    int updateByPrimaryKey(T_Sys_Police record);
    
    List<T_Sys_Police> selectPolices(@Param("policeIds")List<String> policeIds);
}