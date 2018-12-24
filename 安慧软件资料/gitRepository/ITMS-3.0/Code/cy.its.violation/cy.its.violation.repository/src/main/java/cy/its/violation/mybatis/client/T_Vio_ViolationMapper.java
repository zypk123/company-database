package cy.its.violation.mybatis.client;

import cy.its.violation.mybatis.model.T_Vio_Violation;

public interface T_Vio_ViolationMapper {
    int deleteByPrimaryKey(String violationId);

    int insert(T_Vio_Violation record);

    int insertSelective(T_Vio_Violation record);

    T_Vio_Violation selectByPrimaryKey(String violationId);

    int updateByPrimaryKeySelective(T_Vio_Violation record);

    int updateByPrimaryKey(T_Vio_Violation record);
}