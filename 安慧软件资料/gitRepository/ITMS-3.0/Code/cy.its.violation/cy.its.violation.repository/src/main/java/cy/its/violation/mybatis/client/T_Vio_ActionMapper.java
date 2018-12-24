package cy.its.violation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.violation.mybatis.model.T_Vio_Action;

public interface T_Vio_ActionMapper {
	int deleteByPrimaryKey(String vioActionCode);

	int insert(T_Vio_Action record);

	int insertSelective(T_Vio_Action record);

	T_Vio_Action selectByPrimaryKey(String vioActionCode);

	int updateByPrimaryKeySelective(T_Vio_Action record);

	int updateByPrimaryKey(T_Vio_Action record);

	List<T_Vio_Action> selectVioActions(Map<String, Object> params);
}