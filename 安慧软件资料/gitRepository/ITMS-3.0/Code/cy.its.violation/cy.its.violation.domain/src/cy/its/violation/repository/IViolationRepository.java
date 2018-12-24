package cy.its.violation.repository;

import java.util.List;

import cy.its.com.domain.IRepository;
import cy.its.violation.domain.criteria.ViolationCriteria;
import cy.its.violation.domain.model.violation.FilterStatistic;
import cy.its.violation.domain.model.violation.UnFilterStatistic;
import cy.its.violation.domain.model.violation.Violation;

public interface IViolationRepository extends IRepository<Violation> {
	/**
	 * @param violationCriteria
	 * @return
	 */
	List<Violation> findViolations(ViolationCriteria violationCriteria);

	int countViolation(ViolationCriteria violationCriteria);

	/**
	 * 更新违法记录表(T_VIO_VIOLATION) 更新条件是: 违法记录ID, 锁定状态为未锁定; 更新结果:
	 * 锁定状态变更为已锁定;锁定时间为指定锁定时间;锁定人为指定锁定人;
	 * 
	 * @param violation
	 * @return
	 */
	int updateForLock(Violation violation);

	/**
	 * 更新违法记录表(T_VIO_VIOLATION) 更新条件是: 违法记录ID, 锁定状态为已锁定; 更新结果:
	 * 锁定状态变更为未锁定;锁定时间为空;锁定人为空;
	 * 
	 * @param violation
	 * @return
	 */
	int updateForUnlock(Violation violation);

	/**
	 * 根据条件废弃违法记录
	 * 
	 * @param violationCriteria
	 * @return
	 */
	int updateVioByCondition(ViolationCriteria violationCriteria);

	/**
	 * 根据条件删除违法记录
	 * 
	 * @param violationCriteria
	 * @return
	 */
	int deleteVioByCondition(ViolationCriteria violationCriteria);

	/**
	 * 统计待筛选工作量
	 * 
	 * @param violationCriteria
	 * @return
	 */
	List<UnFilterStatistic> findUnFilterStatistic(ViolationCriteria violationCriteria);

	/**
	 * 统计已筛选工作量
	 * 
	 * @param violationCriteria
	 * @return
	 */
	public List<FilterStatistic> findFilterStatistic(ViolationCriteria violationCriteria);

}
