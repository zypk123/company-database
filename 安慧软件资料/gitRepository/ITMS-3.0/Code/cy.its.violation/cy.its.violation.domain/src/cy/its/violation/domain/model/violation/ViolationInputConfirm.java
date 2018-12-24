package cy.its.violation.domain.model.violation;

import java.util.Date;

import cy.its.violation.domain.criteria.ConstantCode;

/**
 * 违法录入类型_校验后录入 适用于:超速、闯红灯、大车占道、违停、未系安全带、客车夜间禁行、占用应急车道、其它等已有违法的 验证后录入
 * 
 * @author STJ
 *
 */
public class ViolationInputConfirm extends ViolationInput {

	/**
	 * 违法唯一标识
	 */
	private String violationId;

	/**
	 * 号牌号码
	 */
	private String plateNbr;

	/**
	 * 号牌类型(002)
	 */
	private String plateType;

	/**
	 * 号牌颜色(003)
	 */
	private String plateColor;

	/**
	 * 违法代码
	 */
	private String violationCode;

	/**
	 * 违法描述
	 */
	private String violationDesc;

	/**
	 * 录入人
	 */
	private String entryBy;

	/**
	 * 录入时间
	 */
	private Date entryTime;

	/**
	 * @param violationId
	 * @param plateNbr
	 * @param plateType
	 * @param plateColor
	 * @param violationCode
	 * @param violationDesc
	 * @param entryBy
	 * @param entryTime
	 */
	public ViolationInputConfirm(String violationId, String plateNbr, String plateType, String plateColor,
			String violationCode, String violationDesc, String entryBy, Date entryTime) {
		this.violationId = violationId;
		this.plateNbr = plateNbr;
		this.plateType = plateType;
		this.plateColor = plateColor;
		this.violationCode = violationCode;
		this.violationDesc = violationDesc;
		this.entryBy = entryBy;
		this.entryTime = entryTime;
	}

	@Override
	void ExcuteInput(Violation violation) throws Exception {
		violation.setViolationId(this.violationId);
		violation.setPlateNbr(this.plateNbr);
		violation.setPlateType(this.plateType);
		violation.setPlateColor(this.plateColor);
		violation.setViolationCode(this.violationCode);
		violation.setViolationDesc(this.violationDesc);
		violation.entryBy = this.entryBy;
		violation.entryTime = this.entryTime;
		violation.setStatusFlag(ConstantCode.StatusFlag.CONFIRMED);
	}

	public String getViolationId() {
		return violationId;
	}

	public void setViolationId(String violationId) {
		this.violationId = violationId;
	}

	public String getPlateNbr() {
		return plateNbr;
	}

	public String getPlateType() {
		return plateType;
	}

	public String getPlateColor() {
		return plateColor;
	}

	public String getViolationCode() {
		return violationCode;
	}

	public String getViolationDesc() {
		return violationDesc;
	}

	public String getEntryBy() {
		return entryBy;
	}

	public void setEntryBy(String entryBy) {
		this.entryBy = entryBy;
	}

	public Date getEntryTime() {
		return entryTime;
	}

	public void setEntryTime(Date entryTime) {
		this.entryTime = entryTime;
	}

	@Override
	protected boolean IsEmpty() {
		return false;
	}
}
