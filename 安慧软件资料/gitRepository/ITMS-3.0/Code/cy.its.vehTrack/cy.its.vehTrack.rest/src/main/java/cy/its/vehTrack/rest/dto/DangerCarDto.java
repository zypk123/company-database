/**
 *name :危险驾驶车辆分析输入bean
 *author:wangyunqi
 *date:2016/03/14
 * 
 */
package cy.its.vehTrack.rest.dto;

import java.lang.reflect.Field;

import com.wordnik.swagger.annotations.ApiParam;

import cy.its.com.dto.BaseDto;

public class DangerCarDto extends BaseDto {

	/**
	 * 行政区划代码(007)
	 */
	private String districtCode;

	/**
	 * 开始时间
	 */
	@ApiParam(value = " 分析开始时间 格式：yyyy-MM-dd HH:mm:ss", required = true)
	private String startTime;

	/**
	 * 结束时间
	 */
	@ApiParam(value = " 分析截止时间 格式：yyyy-MM-dd HH:mm:ss", required = true)
	private String endTime;

	/**
	 * 罚分类型
	 */
	private String vioActionCodes;

	/**
	 * 总数
	 */
	private long tatal;

	/**
	 * 方向类型
	 */
	private String directionType;
	/**
	 * 时间间隔
	 */
	private String minInterval;
	
	/**
	 * 违法类型
	 */
	private String vioType;

	// 当前页数
	private int pageNumber;
	// 分页大小
	private int pageSize;

	/**
	 * topN
	 */
	private String topN;

	public String getTopN() {
		return topN;
	}

	public void setTopN(String topN) {
		this.topN = topN;
	}

	public long getTatal() {
		return tatal;
	}

	public void setTatal(long tatal) {
		this.tatal = tatal;
	}

	public String getVioActionCodes() {
		return vioActionCodes;
	}

	public void setVioActionCodes(String vioActionCodes) {
		this.vioActionCodes = vioActionCodes;
	}

	/**
	 * @return the startTime
	 */
	public String getStartTime() {
		return startTime;
	}

	/**
	 * @param startTime
	 *            the startTime to set
	 */
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	/**
	 * @return the endTime
	 */
	public String getEndTime() {
		return endTime;
	}

	/**
	 * @param endTime
	 *            the endTime to set
	 */
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getDistrictCode() {
		return districtCode;
	}

	public String getDirectionType() {
		return directionType;
	}

	public void setDirectionType(String directionType) {
		this.directionType = directionType;
	}

	public void setDistrictCode(String districtCode) {
		this.districtCode = districtCode;
	}

	public String getMinInterval() {
		return minInterval;
	}

	public void setMinInterval(String minInterval) {
		this.minInterval = minInterval;
	}

	public int getPageNumber() {
		return pageNumber;
	}

	public void setPageNumber(int pageNumber) {
		this.pageNumber = pageNumber;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	

	public String getVioType() {
		return vioType;
	}

	public void setVioType(String vioType) {
		this.vioType = vioType;
	}

	@Override
	public String toString() {
		StringBuilder params = new StringBuilder();
		Field[] fields = this.getClass().getDeclaredFields();
		for (Field field : fields) {
			try {
				params.append("&").append(field.getName()).append("=");
				Object value = field.get(this);
				if (value != null) {
					params.append(value);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return params.substring(1);
	}
	

}
