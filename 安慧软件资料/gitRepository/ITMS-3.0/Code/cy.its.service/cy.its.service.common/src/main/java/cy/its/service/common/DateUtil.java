package cy.its.service.common;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	private final static ThreadLocal<SimpleDateFormat> local = new ThreadLocal<SimpleDateFormat>();

	public static String formatDate(Date date) {
		return getDataFormat().format(date);
	}

	public static Date parseDate(String dataStr) {
		if (!StringUtil.isNullOrEmpty(dataStr)) {
			try {
				return getDataFormat().parse(dataStr);
			} catch (Exception e) {
				return new Date();
			}
		}
		return null;
	}

	public static long parseDateTime(String dataStr) {
		if (!StringUtil.isNullOrEmpty(dataStr)) {
			try {
				return getDataFormat().parse(dataStr).getTime();
			} catch (Exception e) {
				return new Date().getTime();
			}
		}
		return Long.MIN_VALUE;
	}
	
	private static SimpleDateFormat getDataFormat() {
		SimpleDateFormat sdf = local.get();
		if (sdf == null) {
			sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			local.set(sdf);
		}
		return sdf;
	}

	public static Date addDateByHour(Date date, int hour) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.HOUR, hour);
		return calendar.getTime();
	}
}
