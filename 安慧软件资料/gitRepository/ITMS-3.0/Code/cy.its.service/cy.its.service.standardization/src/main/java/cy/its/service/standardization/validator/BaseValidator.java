package cy.its.service.standardization.validator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.function.Predicate;

import cy.its.service.common.dataModel.Model;
import cy.its.service.common.log.LogManager;
import cy.its.service.common.ConstValue;
import cy.its.service.common.DateUtil;
import cy.its.service.common.StringUtil;

public abstract class BaseValidator<T extends Model> {

	public BaseValidator() {
		filters = new ArrayList<SingleFilter<T>>();
		fillFilters();
		filters.trimToSize();
	}

	abstract void fillFilters();

	private ArrayList<SingleFilter<T>> filters = new ArrayList<SingleFilter<T>>();

	public Boolean check(T entity) {
		try {
			for (SingleFilter<T> item : filters) {
				if (item.test(entity)) {
					entity.validateResult = item.getErrorMessage();
					return ConstValue.BOOL_FALSE;
				}
			}
		} catch (Throwable e) {
			LogManager.error(e);
			entity.validateResult = e.toString();
			return ConstValue.BOOL_FALSE;
		}

		return ConstValue.BOOL_TRUE;
	}

	public void addFilter(Predicate<T> filter, String errorMessage) {
		filters.add(new SingleFilter<T>(filter, errorMessage));
	}

	public static int dbLen(String str) {
		return StringUtil.getDefaultByteCount(str);
	}

	public static Date maxDate() {
		return DateUtil.addDateByHour(new Date(), ConstValue.INT_1);
	}
	
	final static int MillsOfOneHour = 3600000;
	public static boolean is1HMoreThanNow(Date date){
		if(date != null){
			return date.getTime() - System.currentTimeMillis() > MillsOfOneHour;
		}
		
		return ConstValue.BOOL_FALSE;
	} 

	public static boolean isRightDbNum(double num, int maxAllDigits, int maxDigits) {
		if (Double.isNaN(num)) {
			return ConstValue.BOOL_FALSE;
		}

		return isRightDbNum(new BigDecimal(String.valueOf(num)), maxAllDigits, maxDigits);
	}

	public static boolean isRightDbNum(float num, int maxAllDigits, int maxDigits) {
		if (Float.isNaN(num)) {
			return ConstValue.BOOL_FALSE;
		}

		return isRightDbNum(new BigDecimal(String.valueOf(num)), maxAllDigits, maxDigits);
	}

	public static boolean isRightDbNum(int num, int maxAllDigits) {		
		return isRightDbNum(new BigDecimal(num), maxAllDigits, ConstValue.INT_0);
	}
	
	public static boolean isRightDbNum(Integer num, int maxAllDigits) {
		if(num == null){
			return ConstValue.BOOL_TRUE;
		}
		return isRightDbNum(new BigDecimal(num), maxAllDigits, ConstValue.INT_0);
	}
	
	public static boolean isRightDbNum(long num, int maxAllDigits) {		
		return isRightDbNum(new BigDecimal(num), maxAllDigits, ConstValue.INT_0);
	}
	
	public static boolean isRightDbNum(Long num, int maxAllDigits) {
		if(num == null){
			return ConstValue.BOOL_TRUE;
		}
		return isRightDbNum(new BigDecimal(num), maxAllDigits, ConstValue.INT_0);
	}
	
	public static boolean isRightDbNum(BigDecimal num, int maxAllDigits, int maxDigits) {
		if (maxAllDigits < maxDigits) {
			return ConstValue.BOOL_FALSE;
		}

		if (num == null) {
			return ConstValue.BOOL_TRUE;
		}

		return (num.precision() - num.scale() <= maxAllDigits - maxDigits) && num.scale() <= maxDigits;
	}

}
