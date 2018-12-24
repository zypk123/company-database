package sync.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 6合1平台警员元数据表映射对象
 * @author dell
 *
 */
@Entity
@Table(name="BAS_POLICE")
public class PoliceSrc {
	/**
	 * 警员编号
	 */
	private String jybh;
	
	/**
	 * 部门代码
	 */
	private String bmdm;
	
	/**
	 * 身份证号码
	 */
	private String sfzmhm;
	
	/**
	 * 性别
	 */
	private String xb;
	
	/**
	 * 姓名
	 */
	private String xm;
	
	/**
	 * 出生日期
	 */
	private Date Csrq;
	
	/**
	 * 记录状态
	 */
	private String jlzt;

	@Id
	@Column
	public String getJybh() {
		return jybh;
	}

	public void setJybh(String jybh) {
		this.jybh = jybh;
	}

	@Column
	public String getBmdm() {
		return bmdm;
	}

	public void setBmdm(String bmdm) {
		this.bmdm = bmdm;
	}

	@Column
	public String getSfzmhm() {
		return sfzmhm;
	}

	public void setSfzmhm(String sfzmhm) {
		this.sfzmhm = sfzmhm;
	}

	@Column
	public String getXb() {
		return xb;
	}

	public void setXb(String xb) {
		this.xb = xb;
	}

	@Column
	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	@Column
	public Date getCsrq() {
		return Csrq;
	}

	public void setCsrq(Date csrq) {
		Csrq = csrq;
	}

	@Column
	public String getJlzt() {
		return jlzt;
	}

	public void setJlzt(String jlzt) {
		this.jlzt = jlzt;
	}
	
	
	
}
