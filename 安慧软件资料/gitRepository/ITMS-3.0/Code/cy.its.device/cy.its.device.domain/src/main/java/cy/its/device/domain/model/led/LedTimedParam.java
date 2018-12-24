package cy.its.device.domain.model.led;

import java.util.ArrayList;
import java.util.List;

import cy.its.com.util.StringUtil;

public class LedTimedParam {

	/**
	 * 播放模式：1-按时间播放，2-按日期，4-按星期，3-按日期加时间，5-按星期加时间，6-按星期加日期，7-按日期加星期加时间
	 */
	private Short timeMode;

	/**
	 * 开始日期-年/月/日
	 */
	private String startDate;

	/**
	 * 结束日期-年/月/日
	 */
	private String endDate;

	/**
	 * 开始时间: 时/分/秒
	 */
	private String startTime;

	/**
	 * 结束时间-时/分/秒
	 */
	private String endTime;

	/**
	 * 星期参数: 周一~周日, 0-不播放,1-播放 如:1,1,1,1,1,1,1 表示周一至周日都播放
	 */
	private String week;

	public LedTimedParam(Short timeMode, String startDate, String endDate, String startTime, String endTime,
			String week) {
		super();
		this.timeMode = timeMode;
		this.startDate = startDate;
		this.endDate = endDate;
		this.startTime = startTime;
		this.endTime = endTime;
		this.week = week;

		if (timeMode == null) {
			generateTimeMode();
		}
	}

	public LedTimedParam(String startDate, String endDate, String startTime, String endTime, String week) {
		this(null, startDate, endDate, startTime, endTime, week);
	}

	private void generateTimeMode() {
		String binStr = "";
		if (!StringUtil.isNullOrEmpty(week) && week.contains("1")) {
			binStr = "1";
		} else {
			binStr = "0";
		}

		if (!StringUtil.isNullOrEmpty(startDate) && !StringUtil.isNullOrEmpty(endDate)) {
			binStr = binStr + "1";
		} else {
			binStr = binStr + "0";
		}

		if (!StringUtil.isNullOrEmpty(startTime) && !StringUtil.isNullOrEmpty(endTime)) {
			binStr = binStr + "1";
		} else {
			binStr = binStr + "0";
		}

		binStr = StringUtil.trimStart(binStr, '0');

		if (!StringUtil.isNullOrEmpty(binStr)) {
			this.timeMode = Short.parseShort(binStr, 2);
		} else {
			this.timeMode = null;
		}
	}

	public Short getTimeMode() {
		return timeMode;
	}

	// public void setTimeMode(Short timeMode) {
	// this.timeMode = timeMode;
	// }

	public String getStartDate() {
		return startDate;
	}

	// public void setStartDate(String startDate) {
	// this.startDate = startDate;
	// }

	public String getEndDate() {
		return endDate;
	}

	// public void setEndDate(String endDate) {
	// this.endDate = endDate;
	// }

	public String getStartTime() {
		return startTime;
	}

	// public void setStartTime(String startTime) {
	// this.startTime = startTime;
	// }

	public String getEndTime() {
		return endTime;
	}

	// public void setEndTime(String endTime) {
	// this.endTime = endTime;
	// }

	public String getWeek() {
		return week;
	}

	// public void setWeek(String week) {
	// this.week = week;
	// }

	public void check() throws Exception {
		if (timeMode == null) {
			throw new Exception("未设置定时参数");
		}
		// if(timeMode == null || timeMode < 1 || timeMode > 7){
		// throw new Exception("定时参数设置有误");
		// }
		//
		// String str = StringUtil.padLeft(Integer.toBinaryString(timeMode), 3,
		// '0');
		//
		// if (!StringUtil.isNullOrEmpty(week)) {
		// binStr = "1";
		// } else {
		// binStr = "0";
		// }
		//
		// if (!StringUtil.isNullOrEmpty(startDate) &&
		// !StringUtil.isNullOrEmpty(endDate)) {
		// binStr = binStr + "1";
		// } else {
		// binStr = binStr + "0";
		// }
		//
		// if (!StringUtil.isNullOrEmpty(startTime) &&
		// !StringUtil.isNullOrEmpty(endTime)) {
		// binStr = binStr + "1";
		// } else {
		// binStr = binStr + "0";
		// }
		//
		// if(str.charAt(0) == '1'){
		// // 按星期
		// if (StringUtil.isNullOrEmpty(week)){
		// throw new Exception("未设置星期范围");
		// }
		// }
		//
		// if(str.charAt(1) == '1'){
		// // 按日期
		// if (StringUtil.isNullOrEmpty(startDate) ||
		// StringUtil.isNullOrEmpty(endDate)) {
		// throw new Exception("未设置日期范围");
		// }
		// }
		//
		// if(str.charAt(2) == '1'){
		// // 按时间
		// if (StringUtil.isNullOrEmpty(startTime) &&
		// StringUtil.isNullOrEmpty(endTime)) {
		// throw new Exception();
		// }
		// }
	}

	public String getParamString() {
		String binStr = "";

		List<String> lstP = new ArrayList<String>();

		if (!StringUtil.isNullOrEmpty(startDate) && !StringUtil.isNullOrEmpty(endDate)) {
			String[] s1 = startDate.split("/");
			String[] s2 = endDate.split("/");
			lstP.add(String.format("从%s年%s月%s日到%s年%s月%s日", s1[0], s1[1], s1[2], s2[0], s2[1], s2[2]));
		}

		if (!StringUtil.isNullOrEmpty(week) && week.contains("1")) {
			String[] wn = new String[] { "周一", "周二", "周三", "周四", "周五", "周六", "周日" };
			String[] w = week.split(",");
			List<String> lst = new ArrayList<String>(7);
			for (int i = 0; i < w.length; i++) {
				if ("1".equals(w[i])) {
					lst.add(wn[i]);
				}
			}

			if (lst.size() > 0) {
				lstP.add("每" + String.join("、", lst));
			}
		}

		if (!StringUtil.isNullOrEmpty(startTime) && !StringUtil.isNullOrEmpty(endTime)) {
			String[] s1 = startTime.split("/");
			String[] s2 = endTime.split("/");
			lstP.add(String.format("每日%s时%s分%s秒到%s时%s分%s秒", s1[0], s1[1], s1[2], s2[0], s2[1], s2[2]));
		}

		return lstP.size() > 0 ? String.join("的", lstP) : null;
	}
}