package cy.its.violation.domain.service;

import java.util.List;

import cy.its.violation.domain.criteria.ViolationCriteria;
import cy.its.violation.domain.model.violation.FilterStatistic;
import cy.its.violation.domain.model.violation.UnFilterStatistic;
import cy.its.violation.domain.model.violation.Violation;
import cy.its.violation.domain.model.violation.ViolationInputConfirm;
import cy.its.violation.domain.model.violation.ViolationInputDigital;

public interface IViolationService {

	/**
	 * 查询符合条件的违法记录列表
	 * 
	 * @param violationCriteria
	 *            查询条件
	 * @return 违法记录列表
	 */
	public List<Violation> findViolations(ViolationCriteria violationCriteria);

	/**
	 * 统计符合条件的违法记录列表
	 * 
	 * @param violationCriteria
	 *            查询条件
	 * 
	 * @return 符合条件的违法记录数
	 */
	public int countViolations(ViolationCriteria violationCriteria);

	/**
	 * 查询符合条件的违法记录列表
	 * 
	 * @param violationCriteria
	 *            查询条件
	 * @return 违法记录列表
	 */
	public String findViolationSql(ViolationCriteria violationCriteria);

	/**
	 * 锁定式查看违法详细
	 * 
	 * @param violationId
	 *            违法记录唯一标识ID
	 * @param lockUser
	 *            锁定用户
	 * @return 违法详细
	 * @throws Exception
	 */
	public Violation viewViolationOfIdWithLock(String violationId, String lockUser) throws Exception;

	/**
	 * 解除对指定违法记录的锁定
	 * 
	 * @param violationId
	 *            违法记录唯一标识ID
	 * @return
	 * @throws Exception
	 */
	public int unLockViolation(String violationId) throws Exception;

	/**
	 * 筛选通过指定的违法记录
	 * 
	 * @param violationId
	 *            违法记录唯一标识ID
	 * @throws Exception
	 */
	public void filterViolation(String violationId, String opUser) throws Exception;

	/**
	 * 重录指定的违法记录
	 * 
	 * @param violationId
	 * @return
	 * @throws Exception
	 */
	public int reInputViolation(String[] violationId, String updateBy) throws Exception;

	/**
	 * 重录指定的违法记录
	 * 
	 * @param violationCriteria
	 * @param updateBy
	 * @return
	 * @throws Exception
	 */
	public int reInputViolation(ViolationCriteria violationCriteria, String updateBy) throws Exception;

	/**
	 * 违法录入 适用于: 超速、闯红灯、大车占道、违停、 未系安全带、 客车 夜间禁行、 占用应急车道、其它等已有违法的 录入
	 * 
	 * @param confirm
	 * @return
	 * @throws Exception
	 */
	public int inputViolationForConfirm(ViolationInputConfirm confirm) throws Exception;

	/**
	 * 数码采集录入 包括： 数码取证录入;视频监控取证录入;视频录像取证录入;
	 * 
	 * @param inputDigital
	 *            数码采集信息
	 * @throws Exception
	 */
	public void inputViolationDigital(ViolationInputDigital inputDigital) throws Exception;

	/**
	 * 违法批量导入的单条入库
	 * 
	 * @param violation
	 *            违法信息
	 * @throws Exception
	 */
	public void inputViolationDigital(Violation violation) throws Exception;

	/**
	 * 违法录入:单条废弃处理
	 * 
	 * @param violationId
	 *            违法唯一标识ID
	 * @param specialVehType
	 *            特殊车辆类型
	 * @param discardedReason
	 *            废弃原因
	 * @param discardedBy
	 *            废弃人
	 * @return
	 * @throws Exception
	 */
	public int abandonViolation(String violationId, String specialVehType, String discardedReason, String discardedBy)
			throws Exception;

	/**
	 *
	 * 批量废弃未考虑
	 * 
	 * @param violationIds
	 *            违法唯一标识ID列表
	 * @param specialVehType
	 *            特殊车辆类型
	 * @param discardedReason
	 *            废弃原因
	 * @param discardedBy
	 *            废弃人
	 * @throws Exception
	 */
	public int abandonViolations(String[] violationIds, String discardedReason, String discardedBy) throws Exception;

	/**
	 * 批量废弃未考虑
	 * 
	 * @param violationCriteria
	 * @param specialVehType
	 * @param discardedReason
	 * @param discardedBy
	 * @return
	 * @throws Exception
	 */
	public int abandonViolations(ViolationCriteria violationCriteria, String specialVehType, String discardedReason,
			String discardedBy) throws Exception;

	/**
	 * 将符合条件的违法数据修改为待上传
	 * 
	 * @param violationIds
	 * @return
	 * @throws Exception
	 */
	public int uploadViolation(String[] violationIds, String uploadBy) throws Exception;

	/**
	 * 将符合条件的违法数据修改为待上传
	 * 
	 * @param violationCriteria
	 * @return
	 * @throws Exception
	 */
	public int uploadViolation(ViolationCriteria violationCriteria, String uploadBy) throws Exception;

	/**
	 * 更正指定的上传失败违法记录
	 * 
	 * @param violationId
	 *            违法记录唯一标识ID
	 * @param roadCode
	 *            更正后的道路代码
	 * @param orgCode
	 *            更正人的机构代码
	 * @param uploadBy
	 *            更正人
	 * @throws Exception
	 */
	public void verifyUploadFailViolation(String violationId, String roadCode, String orgCode, String uploadBy)
			throws Exception;

	/**
	 * 还原指定的已废弃违法记录
	 * 
	 * @param violationIds
	 * @return
	 * @throws Exception
	 */
	public int restoreAbandons(String[] violationIds) throws Exception;

	/**
	 * 还原指定条件的已废弃违法记录
	 * 
	 * @param violationCriteria
	 * @return
	 * @throws Exception
	 */
	public int restoreAbandons(ViolationCriteria violationCriteria) throws Exception;

	/**
	 * 删除指定的已废弃违法记录
	 * 
	 * @param violationIds
	 * @return
	 * @throws Exception
	 */
	public int deleteViolations(String[] violationIds) throws Exception;

	/**
	 * 删除指定条件的已废弃违法记录
	 * 
	 * @param violationCriteria
	 * @return
	 * @throws Exception
	 */
	public int deleteViolations(ViolationCriteria violationCriteria) throws Exception;

	/**
	 * 统计未筛选的违法记录
	 * 
	 * @param violationCriteria
	 * @return
	 */
	List<UnFilterStatistic> findUnFilterStatistic(ViolationCriteria violationCriteria);

	/**
	 * 统计已筛选的违法记录
	 * 
	 * @param violationCriteria
	 * @return
	 */
	List<FilterStatistic> findFilterStatistic(ViolationCriteria violationCriteria);

}