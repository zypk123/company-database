package cy.its.violation.mybatis.client;

import java.util.List;

import cy.its.violation.mybatis.model.T_Vio_Asso_Action;

public interface T_Vio_Asso_ActionMapper {
	int deleteByPrimaryKey(String vioActionMatchId);

	int insert(T_Vio_Asso_Action record);

	int insertSelective(T_Vio_Asso_Action record);

	T_Vio_Asso_Action selectByPrimaryKey(String vioActionMatchId);

	int updateByPrimaryKeySelective(T_Vio_Asso_Action record);

	int updateByPrimaryKey(T_Vio_Asso_Action record);

	List<T_Vio_Asso_Action> selectByViolationCode(String violationCode);
}