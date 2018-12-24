package cy.its.violation.mybatis.client;

import java.util.List;
import java.util.Map;

import cy.its.violation.mybatis.model.T_VIO_FILTERSTATISTIC;
import cy.its.violation.mybatis.model.T_VIO_UNFILTERSTATISTIC;
import cy.its.violation.mybatis.model.T_Vio_Action;
import cy.its.violation.mybatis.model.T_Vio_Violation;

public interface CustomForVioMapper {
	List<T_Vio_Violation> selectViolations(Map<String, Object> params);

	Integer countViolations(Map<String, Object> params);

	Integer updateVioByCondition(Map<String, Object> params);

	Integer deleteVioByCondition(Map<String, Object> params);

	int updateVioForLockOrNot(Map<String, Object> params);

	List<T_Vio_Action> selectVioActions();

	List<T_VIO_UNFILTERSTATISTIC> getUnfilterStatistic(Map<String, Object> params);

	List<T_VIO_FILTERSTATISTIC> getFilterStatistic(Map<String, Object> params);
}
