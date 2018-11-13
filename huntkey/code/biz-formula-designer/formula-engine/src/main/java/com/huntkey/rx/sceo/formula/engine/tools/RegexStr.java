package com.huntkey.rx.sceo.formula.engine.tools;

import java.util.HashMap;

/**
 * @author lulx on 2017/6/2 0002.
 */
public class RegexStr {

    public static final String REGEX_COMMON_REPSTR = "_";
    private static final String REGEX_MATH_MINUS = "(?<=[\\+\\-\\*\\/])-\\w+(\\.\\w+)?";
    /**
     * 一参数
     */
    private static final String REGEX_MATH_PARA_ONE = "(ABS|INT)\\((-?)\\w+(\\.\\w)?\\)";
    /**
     * 多参数可为0
     */
    private static final String REGEX_MATH_PARA_MORE_ZERO = "(AVERAGE|COUNT|MIN|MAX|PRODUCT|SUM|AVG)\\((-?)\\w+(\\.\\w+)?(,(-?)\\w+(\\.\\w+)?)*\\)";
    private static final String REGEX_MATH_FIXED= "FIXED\\((-?)(\\w+)(\\.\\w+)?(,\\w+)?\\)";
    /**
     * 两参数
     */
    private static final String REGEX_MATH_PARA_TWO= "(FLOOR|CEILING|LARGE|ROUND|SMALL)\\((-?)\\w+(\\.\\w+)?,\\w+\\)";
    private static final String REGEX_MATH_LOG= "LOG\\((\\w+)(\\.\\w+)?(,\\w+)?\\)";
    private static final String REGEX_MATH_MOD = "MOD\\(-?(\\w+)(\\.\\w+)?,-?\\w+(\\.\\w+)?\\)";
    private static final String REGEX_MATH_POWER = "POWER\\(-?\\w+(\\.\\w+)?,-?\\w+(\\.\\w+)?\\)";
    private static final String REGEX_MATH_SQRT= "SQRT\\((\\w+)(\\.\\w+)?\\)";
    private static final String REGEX_MATH_SUMPRODUCT = "SUMPRODUCT\\((\\w+)(\\.\\w+)?(,(\\w+)(\\.\\w+)?)+\\)";
    private static final String REGEX_MATH_DIVISION = "\\w+\\/(?!0)\\w+";
    private static final String REGEX_MATH_ARITHMETIC = "(\\w+)([\\+\\<\\>\\-\\*])(\\w+)";

    /**
     * 一参数
     */
    private static final String REGEX_PARA_ONE = "(NOT|ISEMPTY|LEN|LOWER|TRIM|UPPER|VALUE|NUMBER|STRING|UPPERMONEY)\\(\\w+\\)";
    /**
     * 两参数
     */
    private static final String REGEX_PARA_TWO = "(EXACT|LEFT|REPT|RIGHT|CONTAINS)\\(\\w+,(\\w+)\\)";
    /**
     * 三参数
     */
    private static final String REGEX_PARA_THR = "(MID|SPLIT|IF)\\(\\w+(,(\\w+)){2}\\)";
    /**
     * 四参数
     */
    private static final String REGEX_PARA_FOUR = "REPLACE\\(\\w+(,(\\w+)){3}\\)";
    /**
     * 多参数可为0
     */
    private static final String REGEX_PARA_MORE_ZERO = "(TRUE|FALSE|AND|CONCATENATE|CONCAT)\\(\\w+(,(\\w+))*\\)";
    /**
     * 多参数可为1
     */
    private static final String REGEX_PARA_MORE = "(OR|XOR)\\(\\w+(,(\\w+))+\\)";
    /**
     * 可变参数
     */
    private static final String REGEX_TEXT_SEARCH = "SEARCH\\(\\w+(,(\\w+)){1,2}\\)";
    private static final String REGEX_TEXT_TEXT = "TEXT\\(\\w+,([\\w\\s\\-:#,\\.]+)\\)";

    private static final String REGEX_DATE_DATE = "DATE\\(\\w+\\)";
    private static final String REGEX_DATE_DATEDELTA = "DATEDELTA\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_DAY = "DAY\\(\\w+\\)";
    private static final String REGEX_DATE_DAYS = "DAYS\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_DAYS360 = "DAYS360\\(\\w+(,(\\w+)){1,2}\\)";
    private static final String REGEX_DATE_HOUR = "HOUR\\(\\w+\\)";
    private static final String REGEX_DATE_MINUTE = "MINUTE\\(\\w+\\)";
    private static final String REGEX_DATE_MONTH = "MONTH\\(\\w+\\)";
    private static final String REGEX_DATE_NOW = "NOW\\(\\)";
    private static final String REGEX_DATE_SECOND = "SECOND\\(\\w+\\)";
    private static final String REGEX_DATE_SYSTIME = "SYSTIME\\(\\w+\\)";
    private static final String REGEX_DATE_TIME = "TIME\\(\\w+(,(\\w+)){2}\\)";
    private static final String REGEX_DATE_TIMESTAMP = "TIMESTAMP\\(\\w+\\)";
    private static final String REGEX_DATE_TODAY = "TODAY\\(\\)";
    private static final String REGEX_DATE_WEEKNUM = "WEEKNUM\\(\\w+(,(\\w+))?\\)";
    private static final String REGEX_DATE_YEAR = "YEAR\\(\\w+\\)";

    private static final String REGEX_DATE_WEEK = "WEEK\\(\\w+(,(\\w+))?\\)";
    private static final String REGEX_DATE_DAYADD = "DAYADD\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_HOURADD = "HOURADD\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_HOURS = "HOURS\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_MINUTEADD = "MINUTEADD\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_MINUTES = "MINUTES\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_MONTHADD = "MONTHADD\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_MONTHS = "MONTHS\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_SECONDADD = "SECONDADD\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_SECONDS = "SECONDS\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_YEARADD = "YEARADD\\(\\w+,(\\w+)\\)";
    private static final String REGEX_DATE_YEARS = "YEARS\\(\\w+,(\\w+)\\)";

    private static final HashMap<String,String> REGEX_MAP = new HashMap<String,String>();

    static {
        REGEX_MAP.put("REGEX_MATH_MINUS",REGEX_MATH_MINUS);
        REGEX_MAP.put("REGEX_MATH_PARA_ONE",REGEX_MATH_PARA_ONE);
        REGEX_MAP.put("REGEX_MATH_PARA_MORE_ZERO",REGEX_MATH_PARA_MORE_ZERO);
        REGEX_MAP.put("REGEX_MATH_PARA_TWO",REGEX_MATH_PARA_TWO);
        REGEX_MAP.put("REGEX_MATH_FIXED",REGEX_MATH_FIXED);
        REGEX_MAP.put("REGEX_MATH_LOG",REGEX_MATH_LOG);
        REGEX_MAP.put("REGEX_MATH_MOD",REGEX_MATH_MOD);
        REGEX_MAP.put("REGEX_MATH_POWER",REGEX_MATH_POWER);
        REGEX_MAP.put("REGEX_MATH_SQRT",REGEX_MATH_SQRT);
        REGEX_MAP.put("REGEX_MATH_SUMPRODUCT",REGEX_MATH_SUMPRODUCT);
        REGEX_MAP.put("REGEX_MATH_DIVISION",REGEX_MATH_DIVISION);
        REGEX_MAP.put("REGEX_MATH_ARITHMETIC",REGEX_MATH_ARITHMETIC);

        REGEX_MAP.put("REGEX_PARA_MORE",REGEX_PARA_MORE);

        REGEX_MAP.put("REGEX_PARA_ONE",REGEX_PARA_ONE);
        REGEX_MAP.put("REGEX_PARA_TWO",REGEX_PARA_TWO);
        REGEX_MAP.put("REGEX_PARA_THR",REGEX_PARA_THR);
        REGEX_MAP.put("REGEX_PARA_MORE_ZERO",REGEX_PARA_MORE_ZERO);
        REGEX_MAP.put("REGEX_PARA_FOUR",REGEX_PARA_FOUR);
        REGEX_MAP.put("REGEX_TEXT_SEARCH",REGEX_TEXT_SEARCH);
        REGEX_MAP.put("REGEX_TEXT_TEXT",REGEX_TEXT_TEXT);

        REGEX_MAP.put("REGEX_DATE_DATE",REGEX_DATE_DATE);
        REGEX_MAP.put("REGEX_DATE_DATEDELTA",REGEX_DATE_DATEDELTA);
        REGEX_MAP.put("REGEX_DATE_DAY",REGEX_DATE_DAY);
        REGEX_MAP.put("REGEX_DATE_DAYS",REGEX_DATE_DAYS);
        REGEX_MAP.put("REGEX_DATE_DAYS360",REGEX_DATE_DAYS360);
        REGEX_MAP.put("REGEX_DATE_HOUR",REGEX_DATE_HOUR);
        REGEX_MAP.put("REGEX_DATE_MINUTE",REGEX_DATE_MINUTE);
        REGEX_MAP.put("REGEX_DATE_MONTH",REGEX_DATE_MONTH);
        REGEX_MAP.put("REGEX_DATE_NOW",REGEX_DATE_NOW);
        REGEX_MAP.put("REGEX_DATE_SECOND",REGEX_DATE_SECOND);
        REGEX_MAP.put("REGEX_DATE_SYSTIME",REGEX_DATE_SYSTIME);
        REGEX_MAP.put("REGEX_DATE_TIME",REGEX_DATE_TIME);
        REGEX_MAP.put("REGEX_DATE_TIMESTAMP",REGEX_DATE_TIMESTAMP);
        REGEX_MAP.put("REGEX_DATE_TODAY",REGEX_DATE_TODAY);
        REGEX_MAP.put("REGEX_DATE_WEEKNUM",REGEX_DATE_WEEKNUM);
        REGEX_MAP.put("REGEX_DATE_YEAR",REGEX_DATE_YEAR);

        REGEX_MAP.put("REGEX_DATE_WEEK",REGEX_DATE_WEEK);
        REGEX_MAP.put("REGEX_DATE_DAYADD",REGEX_DATE_DAYADD);
        REGEX_MAP.put("REGEX_DATE_HOURADD",REGEX_DATE_HOURADD);
        REGEX_MAP.put("REGEX_DATE_HOURS",REGEX_DATE_HOURS);
        REGEX_MAP.put("REGEX_DATE_MINUTEADD",REGEX_DATE_MINUTEADD);
        REGEX_MAP.put("REGEX_DATE_MINUTES",REGEX_DATE_MINUTES);
        REGEX_MAP.put("REGEX_DATE_MONTHADD",REGEX_DATE_MONTHADD);
        REGEX_MAP.put("REGEX_DATE_MONTHS",REGEX_DATE_MONTHS);
        REGEX_MAP.put("REGEX_DATE_SECONDADD",REGEX_DATE_SECONDADD);
        REGEX_MAP.put("REGEX_DATE_SECONDS",REGEX_DATE_SECONDS);
        REGEX_MAP.put("REGEX_DATE_YEARADD",REGEX_DATE_YEARADD);
        REGEX_MAP.put("REGEX_DATE_YEARS",REGEX_DATE_YEARS);
    }

    public HashMap<String,String> getAllRegex(){
        return REGEX_MAP;
    }
}
