package cy.its.service.device.statusAnalysis.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import cy.its.service.common.ConstValue;
import cy.its.service.common.dataModel.DeviceStatus;
import cy.its.service.device.statusAnalysis.model.Period;

public class StatusUtil {

	public static List<Integer> splitStatus(int statusCode) {
		if (statusCode < ConstValue.INT_0) {
			return null;
		}

		if (statusCode == ConstValue.INT_0) {
			return Arrays.asList(statusCode);
		} else {
			String strBase2 = Integer.toBinaryString(statusCode);
			int len = strBase2.length();
			ArrayList<Integer> lst = new ArrayList<Integer>(len);
			int t = ConstValue.INT_1;
			for (int i = ConstValue.INT_0; i < len; i++) {
				if ((statusCode & t) > ConstValue.INT_0) {
					lst.add(t);
				}

				t = t << ConstValue.INT_1;
			}

			lst.trimToSize();
			return lst;
		}
	}

	public static int sumListStatus(List<Integer> statusDetails) {
		if (statusDetails != null && statusDetails.size() > ConstValue.INT_0) {
			int statusCode = ConstValue.INT_0;
			for (Integer i : statusDetails) {
				statusCode += i;
			}
			return statusCode;
		}

		return ConstValue.INT_1_MINUS;
	}

	public static int getStatusType(int realStatus) {
		if (realStatus == ConstValue.INT_0) {
			// 正常
			return ConstValue.DEV_STATUS_TYPE_NORMAL;
		} else if ((realStatus & ConstValue.INT_128) > ConstValue.INT_0) {
			// 离线
			return ConstValue.DEV_STATUS_TYPE_OFFLINE;
		} else if ((realStatus & ConstValue.INT_65536) > ConstValue.INT_0) {
			// 异常
			return ConstValue.DEV_STATUS_TYPE_ERROR;
		}

		// 故障
		return ConstValue.DEV_STATUS_TYPE_FAULT;
	}

	public static int formatStatus(int realStatus) {
		return realStatus < ConstValue.INT_0 ? realStatus & ConstValue.INT_AIMAND : realStatus;
	}

	static long DAY_MILL_SECONDS = 86400000;
	static int I_23 = 23;
	static int I_59 = 59;
	static int I_1 = 1;
	@SuppressWarnings("deprecation")
	public static List<Period> splitDateByDay(Date begin, Date end) {
		if (begin.compareTo(end) > ConstValue.INT_0) {
			return null;
		}

		if (begin.getDate() == end.getDate()) {
			return Arrays.asList(new Period(begin, end));
		} else {
			ArrayList<Period> lst = new ArrayList<Period>();
			
			Calendar m = Calendar.getInstance();
			m.setTime(begin);
			m.set(Calendar.HOUR_OF_DAY, I_23);
			m.set(Calendar.MINUTE, I_59);
			m.set(Calendar.SECOND, I_59);
			m.add(Calendar.SECOND, I_1);
			long from = m.getTimeInMillis();
			long to = end.getTime();

			lst.add(new Period(begin, new Date(from)));
			if (from != to) {
				while (true) {
					from += DAY_MILL_SECONDS;
					if (from >= to) {
						lst.add(new Period(new Date(from - DAY_MILL_SECONDS), new Date(to)));
						break;
					} else {
						lst.add(new Period(new Date(from - DAY_MILL_SECONDS), new Date(from)));
					}

				}
			}

			lst.trimToSize();

			return lst;
		}
	}

	public static int getVehicleTotal(DeviceStatus status) {
		return status.getStatsInfo() != null ? status.getStatsInfo().get(ConstValue.VEHICLE_TOTAL) : ConstValue.INT_0;
	}

	static long maxSpan = 60 * 60 * 1000;

	public static Boolean isOldData(Date time) {
		long span = new Date().getTime() - time.getTime();
		return span > ConstValue.INT_0 && span > maxSpan;
	}

	public static Boolean isOldData(long timeMills) {
		return System.currentTimeMillis() - timeMills > maxSpan;
	}

	public static Set<String> intersect(Set<String> s1, Set<String> s2) {
		if (s1 == null || s2 == null) {
			return null;
		}

		Set<String> base = copy(s1);
		base.retainAll(s2);

		return base;
	}

	public static Set<String> union(Set<String> s1, Set<String> s2) {
		if (s1 == null || s2 == null) {
			if (s1 == null) {
				return copy(s2);
			} else {
				return copy(s1);
			}
		}

		Set<String> base = copy(s1);
		base.addAll(s2);
		return base;
	}

	public static Set<String> sutract(Set<String> src, Set<String> exclude) {
		if (exclude == null || src == null) {
			return copy(src);
		}

		Set<String> base = copy(src);
		base.removeAll(exclude);
		return base;
	}

	static Set<String> copy(Set<String> s) {
		if (s != null) {
			Set<String> s0 = new HashSet<String>(s.size());
			s0.addAll(s);
			return s0;
		}

		return null;
	}

	public static int offLineOrAbnormal(long now, long lastBeatTime, long lastUploadTime, int offLinePeriod,
			int nodataPeriod) {
		int statusType = Integer.MIN_VALUE;
		long spanUpld = now - lastUploadTime;
		if (now - lastBeatTime > offLinePeriod) {
			// 最近无心跳
			if (spanUpld < nodataPeriod) {
				// 最近有监测数据
				// 判断为异常
				statusType = ConstValue.DEV_STATUS_TYPE_ERROR;
			} else if (spanUpld > nodataPeriod) {
				// 最近无监测数据
				// 判断为离线
				statusType = ConstValue.DEV_STATUS_TYPE_OFFLINE;
			}
		} else {
			// 最近有心跳
			if (spanUpld > nodataPeriod) {
				// 最近无监测数据
				// 判断为异常
				statusType = ConstValue.DEV_STATUS_TYPE_ERROR;
			}
		}

		return statusType;
	}

}
