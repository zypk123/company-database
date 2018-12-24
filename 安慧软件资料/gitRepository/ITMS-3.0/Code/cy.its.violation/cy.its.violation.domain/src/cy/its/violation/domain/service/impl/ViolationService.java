package cy.its.violation.domain.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cy.its.com.util.StringUtil;
import cy.its.violation.domain.criteria.ConstantCode;
import cy.its.violation.domain.criteria.ViolationCriteria;
import cy.its.violation.domain.model.violation.FilterStatistic;
import cy.its.violation.domain.model.violation.UnFilterStatistic;
import cy.its.violation.domain.model.violation.Violation;
import cy.its.violation.domain.model.violation.ViolationInputAbandon;
import cy.its.violation.domain.model.violation.ViolationInputConfirm;
import cy.its.violation.domain.model.violation.ViolationInputDigital;
import cy.its.violation.domain.service.IViolationService;
import cy.its.violation.repository.IViolationRepository;

/**
 * 违法服务
 * 
 * @author STJ
 *
 */
@Service
public class ViolationService implements IViolationService {

	private SimpleDateFormat longTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

	@Autowired
	IViolationRepository violationRepository;

	@Override
	public List<Violation> findViolations(ViolationCriteria violationCriteria) {
		return violationRepository.findViolations(violationCriteria);
	}

	@Override
	public String findViolationSql(ViolationCriteria violationCriteria) {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from T_VIO_VIOLATION where 1=1");

		if (!StringUtil.isNullOrEmpty(violationCriteria.plateNbr)) {
			sql.append(String.format("AND PLATE_NBR = '%s'", violationCriteria.plateNbr));
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.fuzzyPlateNbr))
			sql.append(String.format(" and PLATE_NBR like '%% %s %%'", violationCriteria.fuzzyPlateNbr));

		if (!StringUtil.isNullOrEmpty(violationCriteria.plateType))
			sql.append(String.format("AND PLATE_TYPE = '%s'", violationCriteria.plateType));

		if (!StringUtil.isNullOrEmpty(violationCriteria.plateColor))
			sql.append(String.format("AND PLATE_COLOR = '%s'", violationCriteria.plateColor));

		if (!StringUtil.isNullOrEmpty(violationCriteria.orgCode))
			sql.append(String.format("AND ORG_CODE like '%% %s %%'", violationCriteria.orgCode));

		if (!StringUtil.isNullOrEmpty(violationCriteria.deviceSysNbr))
			sql.append(String.format("AND DEVICE_SYS_NBR = '%s'", violationCriteria.deviceSysNbr));

		if (!StringUtil.isNullOrEmpty(violationCriteria.LstSiteCode))
			sql.append(String.format("AND VIO_SITE_CODE in ('%s')", violationCriteria.LstSiteCode));

		if (!StringUtil.isNullOrEmpty(violationCriteria.violationBeginTime))
			sql.append(String.format("AND VIOLATION_TIME >= to_date('%s','yyyy-MM-dd hh24:mi:ss')",
					violationCriteria.violationBeginTime));

		if (!StringUtil.isNullOrEmpty(violationCriteria.violationEndTime))
			sql.append(String.format("AND VIOLATION_TIME < to_date('%s','yyyy-MM-dd hh24:mi:ss')",
					violationCriteria.violationEndTime));

		if (!StringUtil.isNullOrEmpty(violationCriteria.uploadBeginTime))
			sql.append(String.format("AND UPLOAD_TIME >= to_date('%s','yyyy-MM-dd hh24:mi:ss')",
					violationCriteria.uploadBeginTime));

		if (!StringUtil.isNullOrEmpty(violationCriteria.uploadEndTime))
			sql.append(String.format("AND UPLOAD_TIME < to_date('%s','yyyy-MM-dd hh24:mi:ss')",
					violationCriteria.uploadEndTime));

		if (violationCriteria.lstViolationType != null && violationCriteria.lstViolationType.size() > 0) {
			String lstViolationType = "";

			for (String type : violationCriteria.lstViolationType) {
				lstViolationType += "," + type;
			}
			lstViolationType = lstViolationType.substring(1);
			sql.append(String.format("AND VIOLATION_TYPE IN (%s)", lstViolationType));
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.statusFlag))
			sql.append(String.format("AND STATUS_FLAG ='%s'", violationCriteria.statusFlag));

		if (violationCriteria.lstStatusFlag != null && violationCriteria.lstStatusFlag.size() > 0) {
			String lstStatusFlag = "";

			for (String type : violationCriteria.lstStatusFlag) {
				lstStatusFlag += "," + type;
			}
			lstStatusFlag = lstStatusFlag.substring(1);
			sql.append(String.format("AND STATUS_FLAG IN (%s)", lstStatusFlag));
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.discardedReason)) {
			sql.append(String.format("AND DISCARDED_REASON =  '%s'", violationCriteria.discardedReason));
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.discardedType)) {
			sql.append(String.format("AND DISCARDED_TYPE =  '%s'", violationCriteria.discardedReason));
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.speedMin)) {
			sql.append(String.format("AND SPEED >= %s", violationCriteria.speedMin));
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.speedMax)) {
			sql.append(String.format("AND SPEED <= %s", violationCriteria.speedMax));
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.collectionType)) {
			sql.append(String.format("AND COLLECTION_TYPE = '%s'", violationCriteria.collectionType));
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.speedingType)) {
			sql.append(String.format("AND SPEEDING_TYPE = '%s'", violationCriteria.speedingType));
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.uploadFlag)) {
			sql.append(String.format("AND UPLOAD_FLAG =  '%s'", violationCriteria.uploadFlag));

		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.specialVehType)) {
			sql.append(String.format("AND SPECIAL_VEH_TYPE =  '%s'", violationCriteria.specialVehType));
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.isIncludeSpecial)) {
			sql.append("AND SPECIAL_VEH_TYPE > '0'");
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.isArmyPlate)) {
			sql.append("AND ((SUBSTR(ASCIISTR(PLATE_NBR),1,1) > '\' OR SUBSTR(ASCIISTR(PLATE_NBR),1,1) < '\' )"
					+ "OR SUBSTR(REVERSE(PLATE_NBR),1,1) = REVERSE('警') )");
		}

		if (!StringUtil.isNullOrEmpty(violationCriteria.isUnknownPlate)) {
			sql.append("AND PLATE_NBR='未知'");
		}
		return sql.toString();
	}

	@Override
	public Violation viewViolationOfIdWithLock(String violationId, String lockUser) throws Exception {
		Violation violation = violationRepository.aggregateOfId(violationId);
		if (StringUtil.isNullOrEmpty(violation.lockUser)) {
			violation.lockForInput(lockUser);
			int result = violationRepository.updateForLock(violation);
			if (result < 1) {
				throw new Exception("当前违法记录已被锁定,禁止继续录入!");
			}
		} else if (!violation.lockUser.equals(lockUser)) {
			throw new Exception("当前记录已被" + violation.lockUser + "锁定");
		}
		return violation;
	}

	@Override
	public int inputViolationForConfirm(ViolationInputConfirm confirm) throws Exception {
		// 未实现 重复违法校验处理
		Violation violation = violationRepository.aggregateOfId(confirm.getViolationId());
		violation.excuteInput(confirm);
		return violationRepository.updateForUnlock(violation);
	}

	@Override
	public void inputViolationDigital(ViolationInputDigital inputDigital) throws Exception {
		Violation violation = new Violation(inputDigital);
		violationRepository.save(violation);
	}

	@Override
	public void inputViolationDigital(Violation violation) throws Exception {
		violation.setExportFlag("0");// 默认为未导出
		violation.setLocalPunishFlag("1");// 默认为未处罚
		violation.setLockFlag("0");
		violation.uploadFlag = "0";
		violation.setStatusFlag(ConstantCode.StatusFlag.NEW);
		violation.setExportFlag(ConstantCode.BooleanFlag.NONE);
		violation.createTime = new Date();
		violationRepository.save(violation);
	}

	@Override
	public int abandonViolation(String violationId, String specialVehType, String discardedReason, String discardedBy)
			throws Exception {
		// 废弃类型: 单张废弃
		String dicardedType = "1";
		Date discardedTime = new Date();
		Violation violation = violationRepository.aggregateOfId(violationId);
		violation.excuteInput(
				new ViolationInputAbandon(specialVehType, discardedReason, dicardedType, discardedBy, discardedTime));
		return violationRepository.update(violation);
	}

	@Override
	public int abandonViolations(ViolationCriteria violationCriteria, String specialVehType, String discardedReason,
			String discardedBy) throws Exception {

		// 废弃类型: 批量废弃
		violationCriteria.o_discardedType = "3";
		violationCriteria.o_specialVehType = specialVehType;
		violationCriteria.o_discardedReason = discardedReason;
		violationCriteria.o_discardedBy = discardedBy;
		violationCriteria.o_statusFlag = "9";
		violationCriteria.o_updateTime = "";
		violationCriteria.o_discardedTime = longTimeFormat.format(new Date());
		violationCriteria.o_updateTime = longTimeFormat.format(new Date());
		return violationRepository.updateVioByCondition(violationCriteria);
	}

	@Override
	public int abandonViolations(String[] violationIds, String discardedReason, String discardedBy) throws Exception {
		int i = 0;
		for (String violationId : violationIds) {
			Violation violation = violationRepository.aggregateOfId(violationId);
			violation.setStatusFlag(ConstantCode.StatusFlag.DISCARDED);
			violation.specialVehType = discardedReason.startsWith("2") ? discardedReason.substring(1, 2) : "";
			violation.discardedReason = discardedReason;
			violation.setDiscardedType(ConstantCode.DiscardType.SINGLE);
			violation.setDiscardedBy(discardedBy);
			violation.setDiscardedTime(new Date());
			violation.setUpdateTime(new Date());
			i += violationRepository.update(violation);
		}
		return i;
	}

	@Override
	public int uploadViolation(String[] violationIds, String uploadBy) throws Exception {
		int i = 0;
		for (String violationId : violationIds) {
			Violation violation = violationRepository.aggregateOfId(violationId);
			violation.readyUpload(uploadBy);
			i += violationRepository.update(violation);
		}
		return i;
	}

	@Override
	public int uploadViolation(ViolationCriteria creteria, String uploadBy) {
		creteria.o_uploadFlag = ConstantCode.UploadFlag.WAITING;
		creteria.o_uploadBy = uploadBy;
		creteria.o_updateTime = longTimeFormat.format(new Date());
		return violationRepository.updateVioByCondition(creteria);
	}

	@Override
	public void verifyUploadFailViolation(String violationId, String roadCode, String orgCode, String uploadBy)
			throws Exception {
		Violation violation = violationRepository.aggregateOfId(violationId);
		violation.verifyForReadyUpload(roadCode, orgCode, uploadBy);
		violationRepository.update(violation);
	}

	@Override
	public int countViolations(ViolationCriteria violationCriteria) {
		return violationRepository.countViolation(violationCriteria);
	}

	@Override
	public int unLockViolation(String violationId) throws Exception {
		Violation violation = violationRepository.aggregateOfId(violationId);
		violation.unlockForInput();
		return violationRepository.updateForUnlock(violation);
	}

	@Override
	public void filterViolation(String violationId, String opUser) throws Exception {
		Violation violation = violationRepository.aggregateOfId(violationId);
		violation.filter(opUser);
		violationRepository.update(violation);
	}
	// 图像数据查询和存储

	@Override
	public List<UnFilterStatistic> findUnFilterStatistic(ViolationCriteria violationCriteria) {
		return violationRepository.findUnFilterStatistic(violationCriteria);
	}

	@Override
	public List<FilterStatistic> findFilterStatistic(ViolationCriteria violationCriteria) {
		return violationRepository.findFilterStatistic(violationCriteria);
	}

	@Override
	public int restoreAbandons(String[] violationIds) throws Exception {
		int i = 0;
		for (String violationId : violationIds) {
			Violation violation = violationRepository.aggregateOfId(violationId);
			violation.restoreAbandon();
			i += violationRepository.update(violation);
		}
		return i;
	}

	@Override
	public int restoreAbandons(ViolationCriteria violationCriteria) throws Exception {

		violationCriteria.o_statusFlag = ConstantCode.StatusFlag.NEW;
		violationCriteria.o_discardedTime = "";
		violationCriteria.o_discardedBy = "";
		violationCriteria.o_specialVehType = "";
		violationCriteria.o_discardedReason = "";
		violationCriteria.o_updateTime = longTimeFormat.format(new Date());
		violationCriteria.o_uploadFlag = ConstantCode.UploadFlag.NEW;
		violationCriteria.o_uploadBy = "";
		violationCriteria.o_uploadTime = "";
		return violationRepository.updateVioByCondition(violationCriteria);
	}

	@Override
	public int reInputViolation(String[] violationIds, String updateBy) throws Exception {
		int i = 0;
		for (String violationId : violationIds) {
			Violation violation = violationRepository.aggregateOfId(violationId);
			violation.reInputViolation(updateBy);
			i += violationRepository.update(violation);
		}
		return i;
	}

	@Override
	public int reInputViolation(ViolationCriteria violationCriteria, String updateBy) throws Exception {
		violationCriteria.o_statusFlag = ConstantCode.StatusFlag.RECONFIRM;
		violationCriteria.o_updateTime = longTimeFormat.format(new Date());
		return violationRepository.updateVioByCondition(violationCriteria);
	}

	@Override
	public int deleteViolations(String[] violationIds) throws Exception {
		int i = 0;
		for (String violationId : violationIds) {
			i += violationRepository.delete(violationId);
		}
		return i;
	}

	@Override
	public int deleteViolations(ViolationCriteria violationCriteria) throws Exception {
		return violationRepository.deleteVioByCondition(violationCriteria);
	}
}
