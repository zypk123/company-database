package com.huntkey.rx.modeler.common.util;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;

/**
 * Created by licj on 2017/4/18.
 */
public interface Constant {

    public static final String PROPERTY_CONVOLUTE = "卷积";

    public static final String PROPERTY_LINK = "关联";

    public static final String PROPERTY_CONNECT = "联动";

    public static final String PROPERTY_UNIT = "单位";

    public static final String PROPERTY_EXTEND = "扩展";

    //public static final String FILE_UPLOAD_PATH = "/root/file/";

    public static final String FILE_GROUP_NAME = "group1";

    public static final String CLASS_MEAS = "meas";//计量单位类简称

    public static final String CLASS_WORD = "word";//枚举类简称

    public static final String CLASS_STAT = "stat";//统计类简称

    public static final String PROPERTY_TYPE_ASSEMBLE = "assemble";//属性集

    public static final String PROPERTY_TYPE_CONST = "const";//属性集

    public static final String PROPERTY_DATA_CLASS = "class";//属性数据类型为类

    public static final  String EDM_OBJECTLINK = "objectLink";

    public static final  String EDM_CONVOLUTION = "convolution";

    public static final  String EDM_OBJECT = "object";

    public static final  String EDM_MEASUREMENT = "measurement";

    String EDM_WORDLIST = "enum";

    String EDM_WORDLIST_NAME = "wordlist";

    public static final  String EDM_MEASUREUNIT_NAME = "measureunit";

    public static final  String EDM_STATISTICS_NAME = "statistics";

    public static final  String SCHEDULE_JOBID = "349680164c634717891b70f74effa36e";//联动的调度任务id

    public static final  String LINK_RECORDS  ="_link_records";

    public static final  String EDM_RESOURCE_NAME  ="resource";

    String INT_MODELER = "int";

    String INT_DB = "INT";

    String VARCHAR_MODELER = "varchar";

    String VARCHAR_DB = "VARCHAR";

    String DECIMAL_MODELER = "decimal";

    String DECIMAL_DB = "DECIMAL";

    String TEXT_MODELER = "text";

    String TEXT_DB = "TEXT";

    String DATE_MODELER = "date";

    String DATE_DB = "DATETIME";

    String OBJECT_MODELER = "object";

    String OBJECT_DB = "CHAR";

    String MEASUREMENT_MODELER = "measurement";

    String MEASUREMENT_DB = "VARCHAR";

    String OBJECT_LINK_MODELER = "objectLink";

    String OBJECT_LINK_DB = "CHAR";

    String ENUM_MODELER = "enum";

    String ENUM_DB = "VARCHAR";

    String CONVOLUTION_MODELER = "convolution";

    String CONVOLUTION_DB = "VARCHAR";

    String CLASS_FLAG = "0";

    String PROPERTY_FLAG = "1";

    String LINK_NAME = "_linkdetail";

    String NORMAL_OBJ = "normalObj";

    String OBJECT_DB_SIZE = "32";

    String MEASUREMENT_DB_SIZE = "50";

    String OBJECT_LINK_DB_SIZE = "32";

    String ENUM_DB_SIZE = "100";

    String CONVOLUTION_DB_SIZE = "100";

    String INT_DB_SIZE = "10";

    String TEXT_DB_SIZE = "16777215";

    String DATE_DB_SIZE = "6";

    String ORDINARY = "ordinary";

    String INDEX = "INDEX";

    String ONLY = "only";

    String UNIQUE = "UNIQUE";

    String COMBINATION = "INDEX";

    String PRIMARY = "primary";

    String PRIMARY_KEY = "PRIMARY KEY";

    String CLASS_MODELER = "class";

    String CLASS_DB_SIZE = "100";

    String CLASS_DB = "VARCHAR";

    String VARCHAR_DB_SIZE = "200";

    String DECIMAL_DB_SIZE = "19,7";
}
