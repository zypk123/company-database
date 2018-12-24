package cy.its.service.device.statusChecker.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import cy.its.service.common.ConstValue;
import cy.its.service.common.StringUtil;
import cy.its.service.common.config.ITSConfig;
import cy.its.service.device.statusChecker.data.StatusLogWriter;
import cy.its.service.device.statusChecker.model.Period;
import cy.its.service.device.statusChecker.model.StatusLog;

public class StatusUtil {

	public final static ExecutorService Exec = Executors.newCachedThreadPool();

	/**
	 * 异常故障定义
	 */
	static Map<String, String> errorFaults;
	static {
		String errorCodes = ITSConfig.findValue("ErrorFaultCodes");		
		if(!StringUtil.isNullOrEmpty(errorCodes) && !StringUtil.isNullOrEmpty(errorCodes.trim()) ){
			String[] cArr = errorCodes.trim().split(",");
			errorFaults = new HashMap<String, String>(cArr.length);
			for (String fault : cArr) {
				if(!errorFaults.containsKey(fault)){
					errorFaults.put(fault, fault);
				}
			}
		}else{
			errorFaults = null;
		}
	}
	
	/**
	 * 接卸故障类型
	 * @param statusCode
	 * @param faultCodes
	 * @return
	 * @throws Exception 
	 */
	public static int getStatusType(int statusCode, List<String> faultCodes) throws Exception {
		switch (statusCode) {
		case ConstValue.INT_0:
			// 在线
			// 判断故障列表是否包含故障
			if ( faultCodes == null ||
			     faultCodes.size() == ConstValue.INT_0 || 
			     (faultCodes.size() == ConstValue.INT_1 && ConstValue.STR_ZERO.equals(faultCodes.get(ConstValue.INT_0)))) {
				return ConstValue.DEV_STATUS_TYPE_NORMAL;
			} else {
				return faultAnalysis(faultCodes);
			}
		case ConstValue.INT_1:
			// 离线
			return ConstValue.DEV_STATUS_TYPE_OFFLINE;
		default:
			break;
		}

		throw new Exception("状态类型和故障列表无法对应上");
	}

	public static long writeStatusLog(String devId, int statusType, long begin, long end) {		
		List<Period> periods = splitDateByDay(new Date(begin), new Date(end));
		if(periods != null) {
			Date newBegin = null;
			for (Period period : periods) {
				StatusLogWriter.insert(
						new StatusLog(devId, String.valueOf(statusType), period.getBeginTime(), period.getEndTime()));
				newBegin = period.getBeginTime();
			}
			
			return newBegin.getTime();
		}
		
		return begin;

	}
	
	/**
	 * 分析故障
	 * @param faultCodes
	 * @return
	 */
	private static int faultAnalysis(List<String> faultCodes) {		
		if (errorFaults == null) {
			// 异常故障未定义: 设定为为故障
			return ConstValue.DEV_STATUS_TYPE_FAULT;
		} else {
			// 检查故障列表中是否包含异常故障
			for (String code : faultCodes) {
				if (errorFaults.containsKey(code)) {
					// 包含: 设定为异常
					return ConstValue.DEV_STATUS_TYPE_ERROR;
				}
			}

			// 故障列表中不包含异常故障: 设定为故障
			return ConstValue.DEV_STATUS_TYPE_FAULT;
		}
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
}
