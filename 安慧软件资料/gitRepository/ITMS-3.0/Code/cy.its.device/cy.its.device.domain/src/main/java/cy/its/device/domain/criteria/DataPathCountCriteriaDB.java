package cy.its.device.domain.criteria;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class DataPathCountCriteriaDB {

	// 道路代码
	public String roadCode;

	// 设备编号
	public String deviceSysNbr;

	// 点位代码
	public String siteCode;

	// 点位名称
	public String siteName;

	// 平均延迟 范围 最小值
	public Integer minDelay;

	// 平均延迟 范围 最小值
	public Integer maxDelay;

	// 起始过车时间
	public String passTimeFrom;

	// 结束过车时间
	public String passTimeTo;

	// 机构权限代码
	public String orgPrivilegeCode;

	/**
	 * 是否接入稽查布控系统 0 : 否 1 : 是 2 : 全
	 */
	public int isConnectTrackSys;

	/**
	 * 页数 从1开始递增计数
	 */
	public int pageNum = 1;

	/**
	 * 页面大小 从1开始递增计数
	 */
	public int pageSize;

	/**
	 * 总记录数
	 */
	public long total;

	public String orderName;

	public String orderType = "asc";

	public String getRoadCode() {
		return roadCode;
	}

	public void setRoadCode(String roadCode) {
		this.roadCode = roadCode;
	}

	public String getDeviceSysNbr() {
		return deviceSysNbr;
	}

	public void setDeviceSysNbr(String deviceSysNbr) {
		this.deviceSysNbr = deviceSysNbr;
	}

	public String getSiteCode() {
		return siteCode;
	}

	public void setSiteCode(String siteCode) {
		this.siteCode = siteCode;
	}

	public String getSiteName() {
		return siteName;
	}

	public void setSiteName(String siteName) {
		this.siteName = siteName;
	}

	public Integer getMinDelay() {
		return minDelay;
	}

	public void setMinDelay(Integer minDelay) {
		this.minDelay = minDelay;
	}

	public Integer getMaxDelay() {
		return maxDelay;
	}

	public void setMaxDelay(Integer maxDelay) {
		this.maxDelay = maxDelay;
	}

	public String getPassTimeFrom() {
		return passTimeFrom;
	}

	public void setPassTimeFrom(String passTimeFrom) {
		this.passTimeFrom = passTimeFrom;
	}

	public String getPassTimeTo() {
		return passTimeTo;
	}

	public void setPassTimeTo(String passTimeTo) {
		this.passTimeTo = passTimeTo;
	}

	public String getOrgPrivilegeCode() {
		return orgPrivilegeCode;
	}

	public void setOrgPrivilegeCode(String orgPrivilegeCode) {
		this.orgPrivilegeCode = orgPrivilegeCode;
	}

	public int getIsConnectTrackSys() {
		return isConnectTrackSys;
	}

	public void setIsConnectTrackSys(int isConnectTrackSys) {
		this.isConnectTrackSys = isConnectTrackSys;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public long getTotal() {
		return total;
	}

	public void setTotal(long total) {
		this.total = total;
	}

	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	
	public  DataPathCountCriteriaDB(DataPathCountCriteria criteria) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.setDeviceSysNbr(criteria.deviceSysNbr);
		this.setIsConnectTrackSys(criteria.isConnectTrackSys);
		this.setOrderName(criteria.getOrderName());
		this.setOrderType(criteria.getOrderType());
		this.setOrgPrivilegeCode(criteria.orgPrivilegeCode);
		this.setPageNum(this.getPageNum());
		this.setPageSize(this.getPageSize());
		this.setPassTimeFrom(sdf.format(criteria.passTimeFrom));
		this.setPassTimeTo(sdf.format(criteria.passTimeTo));
		this.setRoadCode(criteria.roadCode);
		this.setSiteCode(criteria.siteCode);
		this.setSiteName(criteria.siteName);
	}

	@Override
	public String toString() {
		// 获得当前类的的属性名称
		Field fields[] = this.getClass().getDeclaredFields();
		// 获得当前类父类的的属性名称
		List<Field> fieldsList = new ArrayList<Field>();
		StringBuffer str = new StringBuffer();
		for (Field field : fields) {
			try {
				str.append("&").append(field.getName()).append("=");
				Object value = field.get(this);
				if (value != null) {
					str.append(value);
				}
			} catch (IllegalArgumentException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		return str.toString();
	}

}
