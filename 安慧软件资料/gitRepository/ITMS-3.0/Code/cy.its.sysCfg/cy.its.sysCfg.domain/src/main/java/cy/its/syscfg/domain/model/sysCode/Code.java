package cy.its.syscfg.domain.model.sysCode;

import cy.its.com.domain.Entity;

public class Code extends Entity<String> {

	/**
	 * 系统代码id
	 */
	private java.lang.String codeId;

	/**
	 * 代码编号
	 */
	public java.lang.String codeNo;
	
	/**
	 * 代码类型
	 */
	public java.lang.String codeType;

	/**
	 * 代码名称
	 */
	public java.lang.String codeName;

	/**
	 * 编辑标识
	 */
	public java.lang.String editable;

	/**
	 * 排序索引
	 */
	public java.lang.String sortIndex;

	/**
	 * 备注
	 */
	public java.lang.String remark;

	/**
	 * 启用标识
	 */
	public java.lang.String enableFlag;
	
	public Code(String codeNo, String codeType,
			String codeName, String remark, String enableFlag) {
		this.codeNo = codeNo;
		this.codeType = codeType;
		this.codeName = codeName;
		this.remark = remark;
		this.enableFlag = enableFlag;
	}
	
	public Code(String codeId, String codeNo, String codeType,
			String codeName, String remark, String enableFlag) {
		this(codeNo, codeType, codeName, remark, enableFlag);
		this.codeId = codeId;
	}

	@Override
	public String getIdentityId() {
		return codeId;
	}
	
	public void setCodeId(String codeId) {
		this.codeId = codeId;
	}
	
	public String getCodeNo() {
		return this.codeNo;
	}
	
	public String getCodeType() {
		return this.codeType;
	}
}
