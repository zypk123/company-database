/**
 * 
 */
package cy.its.syscfg.domain.model.duty;

import java.math.BigDecimal;

import cy.its.com.domain.Value;

/**
 * @author STJ 机构驻地信息
 */
public class OrgSeat extends Value {
	/**
	 * 机构驻地地址
	 */
	public java.lang.String addressDesc;
	
	/**
	 * 机构驻地行政区划(007)
	 */
	public java.lang.String districtCode;
	
	/**
	 * 机构驻地所在城市
	 */
	public java.lang.String city;
	
	/**
	 * 机构驻地坐标的经度
	 */
	public BigDecimal orgLongitude;
	
	/**
	 * 机构驻地地坐标的纬度
	 */
	public BigDecimal orgLatitude;
	
	
	
	@Override
	protected boolean IsEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
}
