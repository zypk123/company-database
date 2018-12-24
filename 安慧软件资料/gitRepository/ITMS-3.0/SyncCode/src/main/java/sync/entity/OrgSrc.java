package sync.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * 6合1平台机构源数据表映射对象
 * @author zuop
 *
 */
@Entity
@Table(name="FRM_DEPARTMENT")
public class OrgSrc {
	/**
	 * 管理部门编码，对应管控平台org_code
	 */
	private String glbm;
	
	/**
	 * 上级部门编码，需要转化为org_id
	 */
	private String sjbm;
	/**
	 * 部门名称,对应管控平台org_name
	 */
	private String bmmc;
	
	/**
	 * 部门级别，对应管控平台org_level
	 */
	private String bmjb;
	
	/**
	 * 联系地址，对应管控平台addressDesc
	 */
	private String lxdz;
	
	/**
	 * 联系电话，对用管控平台org_phone_nbr
	 */
	private String lxdh;
	
	/**
	 * 负责人，对应管控平台header_name
	 */
	private String fzr;

	
	private String jlzt;
	@Id
	@Column
	public String getGlbm() {
		return glbm;
	}

	public void setGlbm(String glbm) {
		this.glbm = glbm;
	}

	@Column
	public String getBmmc() {
		return bmmc;
	}

	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}

	@Column
	public String getBmjb() {
		return bmjb;
	}

	public void setBmjb(String bmjb) {
		this.bmjb = bmjb;
	}

	@Column
	public String getLxdz() {
		return lxdz;
	}

	public void setLxdz(String lxdz) {
		this.lxdz = lxdz;
	}

	@Column
	public String getLxdh() {
		return lxdh;
	}

	public void setLxdh(String lxdh) {
		this.lxdh = lxdh;
	}

	@Column
	public String getFzr() {
		return fzr;
	}

	public void setFzr(String fzr) {
		this.fzr = fzr;
	}

	@Column
	public String getSjbm() {
		return sjbm;
	}

	public void setSjbm(String sjbm) {
		this.sjbm = sjbm;
	}

	@Column
	public String getJlzt() {
		return jlzt;
	}

	public void setJlzt(String jlzt) {
		this.jlzt = jlzt;
	}
	
}
