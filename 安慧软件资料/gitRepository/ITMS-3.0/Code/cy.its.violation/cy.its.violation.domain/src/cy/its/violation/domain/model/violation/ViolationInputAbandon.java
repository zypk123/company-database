package cy.its.violation.domain.model.violation;

import java.util.Date;

import cy.its.violation.domain.criteria.ConstantCode;

/**
 * 违法录入类型_废弃录入
 * 
 * @author STJ
 *
 */
public class ViolationInputAbandon extends ViolationInput {

	/**
	 * 特殊车辆类型（311）
	 */
	private String specialVehType;

	/**
	 * 废弃原因（312）
	 */
	private String discardedReason;

	/**
	 * 废弃类别（306）
	 */
	private String discardedType;

	/**
	 * 废弃人
	 */
	private String discardedBy;

	/**
	 * 废弃时间
	 */
	private Date discardedTime;

	public ViolationInputAbandon(String specialVehType, String discardedReason, String discardedType,
			String discardedBy, Date discardedTime) {
		this.specialVehType = specialVehType;
		this.discardedReason = discardedReason;
		this.discardedType = discardedType;
		this.discardedBy = discardedBy;
		this.discardedTime = discardedTime;
	}

	@Override
	void ExcuteInput(Violation violation) throws Exception {

		if (stringIsEmpty(this.specialVehType)) {
			if (stringIsEmpty(this.discardedReason)) {
				throw new Exception("特殊车辆为空时,废弃原因不可为空!");
			}

			violation.discardedReason = this.discardedReason;

		} else {
			violation.specialVehType = this.specialVehType;
			violation.discardedReason = "02"; // 02 特殊车辆
		}

		violation.discardedType = this.discardedType;
		violation.setDiscardedBy(this.discardedBy);
		violation.setDiscardedTime(this.discardedTime);
		violation.setStatusFlag(ConstantCode.StatusFlag.DISCARDED);
		violation.setUploadFlag(ConstantCode.UploadFlag.NEW);

	}

	@Override
	protected boolean IsEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}
