package com.huntkey.rx.sceo.formula.engine;

import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.engine.tools.DateUtils;

import java.util.Date;

/**
 * 表达式值
 * <p>
 * 支持六种类型：long、string、double、boolean、date、object
 * @author chenfei on 2017/5/15.
 */
public class ExprValue implements Comparable<ExprValue> {

    protected Object value = null;

    /**
     * 值的数据类型
     */
    protected DataType dataType = DataType.Void;

    /**
     * 获取值的数据类型
     *
     * @return
     */
    public DataType getDataType() {
        return dataType;
    }

    /**
     * 是否是数组
     *
     * @return
     */
    public boolean isArray() {

        return dataType == DataType.ARR ? true : false;
    }

    /**
     * 表达式值构造器
     */
    public ExprValue() {
    }

    /**
     * 表达式值构造器
     */
    public ExprValue(Object object) {

        if (object instanceof Long) {
            setLong((Long) object);
        } else if (object instanceof String) {
            setString((String) object);
        } else if (object instanceof Date) {
            setDate((Date) object);
        } else if (object instanceof Boolean) {
            setBoolean((Boolean) object);
        } else if (object instanceof Double) {
            setDouble((Double) object);
        } else if (object instanceof Float) {
            setDouble(((Float) object).doubleValue());
        } else if (object instanceof Integer) {
            setLong(((Integer) object).longValue());
        } else if (object.getClass().isArray()) {
            setArray(object);
        } else {
            throw new FormulaException("The object: " + object + " which can not be supported, and it's class is " + object.getClass().getName() + ".");
        }

    }

    /**
     * 表达式值构造器
     *
     * @param longValue
     */
    public ExprValue(long longValue) {
        setLong(longValue);
    }

    /**
     * 表达式值构造器
     *
     * @param stringValue
     */
    public ExprValue(String stringValue) {
        setString(stringValue);
    }

    /**
     * 表达式值构造器
     *
     * @param dateValue
     */
    public ExprValue(Date dateValue) {
        setDate(dateValue);
    }

    /**
     * 表达式值构造器
     *
     * @param boolValue
     */
    public ExprValue(boolean boolValue) {
        setBoolean(boolValue);
    }

    /**
     * 表达式值构造器
     *
     * @param doubleValue
     */
    public ExprValue(double doubleValue) {
        setDouble(doubleValue);
    }

    /**
     * 设置Long数据值以及其类型
     *
     * @param longValue
     * @return
     */
    public ExprValue setLong(long longValue) {
        value = Long.valueOf(longValue);
        dataType = DataType.Long;
        return this;
    }

    /**
     * 设置数组值以及其类型
     *
     * @param arrValue
     * @return
     */
    public ExprValue setArray(Object arrValue) {
        value = arrValue;
        dataType = DataType.ARR;
        return this;
    }

    /**
     * 设置Double数据值以及其类型
     *
     * @param doubleValue
     * @return
     */
    public ExprValue setDouble(double doubleValue) {
        value = Double.valueOf(doubleValue);
        dataType = DataType.Double;
        return this;
    }

    /**
     * 设置Boolean数据值以及其类型
     *
     * @param boolValue
     * @return
     */
    public ExprValue setBoolean(boolean boolValue) {
        value = Boolean.valueOf(boolValue);
        dataType = DataType.Boolean;
        return this;
    }

    /**
     * 设置Date数据值以及其类型
     *
     * @param dateValue
     * @return
     */
    public ExprValue setDate(Date dateValue) {
        value = dateValue;
        dataType = DataType.Date;
        return this;
    }

    /**
     * 设置String数据值以及其类型
     *
     * @param stringValue
     * @return
     */
    public ExprValue setString(String stringValue) {
        value = stringValue;
        dataType = DataType.String;
        return this;
    }

    /**
     * 从表达式值中获取Long值
     *
     * @return
     * @throws FormulaException
     */
    public long getLong() {

        switch (dataType) {
            case Long:
                return ((Long) value).longValue();
            case Double:
                return ((Double) value).longValue();
            default:
                throw new FormulaException("Can not get a long value from " + dataType.toString());
        }
    }

    /**
     * 从表达式值中获取int值
     *
     * @return
     */
    public int getInt() {
        return (int) getLong();
    }

    /**
     * 从表达式值中获取Double值
     *
     * @return
     * @throws FormulaException
     */
    public double getDouble() {
        switch (dataType) {
            case Long:
                return ((Long) value).doubleValue();
            case Double:
                return ((Double) value).doubleValue();
            case String:
                return Double.parseDouble(value.toString());
            default:
                throw new FormulaException("Can not get a double value from " + dataType.toString());
        }

    }

    /**
     * 从表达式值中获取Float值
     *
     * @return
     */
    public float getFloat() {
        return (float) getDouble();
    }

    /**
     * 从表达式值中获取String值
     *
     * @return
     * @throws FormulaException
     */
    public String getString() {
        return value.toString();
    }

    @Override
    public String toString() {
        return value.toString();
    }

    /**
     * 从表达式值中获取Boolean值
     *
     * @return
     * @throws FormulaException
     */
    public boolean getBoolean() {
        switch (dataType) {
            case Long:
                return ((Long) value).longValue() != 0;
            case Boolean:
                return ((Boolean) value).booleanValue();
            default:
                throw new FormulaException("Can not get a boolean value from " + dataType.toString());
        }
    }

    /**
     * 从表达式值中获取Date值
     *
     * @return
     * @throws FormulaException
     */
    public Date getDate() {
        switch (dataType) {
            case Date:
                return (Date) value;
            default:
                throw new FormulaException("Can not get a date value from " + dataType.toString());
        }
    }

    /**
     * 从表达式值中获取origin value
     *
     * @return
     */
    public Object getOriginValue() {

        // FIXME hack for ORM date type data.
        Object dateObj = DateUtils.format(value.toString());
        if (null != dateObj) {
            return dateObj;
        }

        return value;
    }

    /**
     * 表达式值相加操作
     *
     * @param other
     * @return
     * @throws FormulaException
     */
    public ExprValue add(ExprValue other) {
        switch (dataType) {
            case Long:
                if (other.dataType == DataType.Long) {
                    setLong(getLong() + other.getLong());
                    return this;
                } else {
                    if (other.dataType == DataType.Double) {
                        setDouble(getLong() + other.getDouble());
                        return this;
                    }
                }
                break;
            case Double:
                if (other.dataType == DataType.Long || other.dataType == DataType.Double) {
                    setDouble(getDouble() + other.getDouble());
                    return this;
                }
                break;
            case String:
                setString(getString() + other.getString());
                return this;
            default:
                throw new FormulaException("Can not add value between " + dataType.toString() + " and " + other.dataType.toString());
        }

        throw new FormulaException("Can not add value between " + dataType.toString() + " and " + other.dataType.toString());
    }

    /**
     * 表达式值相减操作
     *
     * @param other
     * @return
     * @throws FormulaException
     */
    public ExprValue sub(ExprValue other) {
        switch (dataType) {
            case Long:
                if (other.dataType == DataType.Long) {
                    setLong(getLong() - other.getLong());
                    return this;
                } else {
                    if (other.dataType == DataType.Double) {
                        setDouble(getLong() - other.getDouble());
                        return this;
                    }
                }
                break;
            case Double:
                if (other.dataType == DataType.Long || other.dataType == DataType.Double) {
                    setDouble(getDouble() - other.getDouble());
                    return this;
                }
                break;
            default:
                throw new FormulaException("Can not sub value between " + dataType.toString() + " and " + other.dataType.toString());
        }
        throw new FormulaException("Can not sub value between " + dataType.toString() + " and " + other.dataType.toString());
    }

    /**
     * 表达式值相乘操作
     *
     * @param other
     * @return
     * @throws FormulaException
     */
    public ExprValue mul(ExprValue other) {
        switch (dataType) {
            case Long:
                if (other.dataType == DataType.Long) {
                    setLong(getLong() * other.getLong());
                    return this;
                } else {
                    if (other.dataType == DataType.Double) {
                        setDouble(getLong() * other.getDouble());
                        return this;
                    }
                }
                break;
            case Double:
                if (other.dataType == DataType.Long || other.dataType == DataType.Double) {
                    setDouble(getDouble() * other.getDouble());
                    return this;
                }
                break;
            default:
                throw new FormulaException("Can not mul value between " + dataType.toString() + " and " + other.dataType.toString());
        }
        throw new FormulaException("Can not mul value between " + dataType.toString() + " and " + other.dataType.toString());
    }

    /**
     * 表达式值相除操作
     *
     * @param other
     * @return
     * @throws FormulaException
     */
    public ExprValue div(ExprValue other) {
        switch (dataType) {
            case Long:
                if (other.dataType == DataType.Long) {
                    if (other.getLong() == 0) {
                        throw new FormulaException("divided by zero");
                    }
                    setLong(getLong() / other.getLong());
                    return this;
                } else {
                    if (other.dataType == DataType.Double) {
                        if (other.getDouble() == 0) {
                            throw new FormulaException("divided by zero");
                        }
                        setDouble(getLong() / other.getDouble());
                        return this;
                    }
                }
                break;
            case Double:
                if (other.dataType == DataType.Long || other.dataType == DataType.Double) {
                    if (other.getDouble() == 0) {
                        throw new FormulaException("divided by zero");
                    }
                    setDouble(getDouble() / other.getDouble());
                    return this;
                }
                break;
            default:
                throw new FormulaException("Can not div value between " + dataType.toString() + " and " + other.dataType.toString());
        }
        throw new FormulaException("Can not div value between " + dataType.toString() + " and " + other.dataType.toString());
    }

    /**
     * 表达式值取模操作
     *
     * @param other
     * @return
     * @throws FormulaException
     */
    public ExprValue mod(ExprValue other) {
        switch (dataType) {
            case Long:
                if (other.dataType == DataType.Long) {
                    setLong(getLong() % other.getLong());
                    return this;
                }
            default:
                throw new FormulaException("Can not mod value between " + dataType.toString() + " and " + other.dataType.toString());
        }
    }

    /**
     * 获取表达式值
     *
     * @return
     */
    public Object getValue() {
        return value;
    }

    /**
     * 表达式值类型
     */
    public enum DataType {
        /**
         * Long
         */
        Long,

        /**
         * String
         */
        String,

        /**
         * Date
         */
        Date,

        /**
         * Boolean
         */
        Boolean,

        /**
         * Double
         */
        Double,

        /**
         * Void
         */
        Void,

        /**
         * ARR
         */
        ARR
    }

    /**
     * 表达式值比较
     *
     * @param other
     * @return
     */
    @Override
    public int compareTo(ExprValue other) {

        switch (dataType) {
            case String:
                if (other.dataType == DataType.String) {
                    return value.toString().compareTo(other.value.toString());
                }
                break;
            case Boolean:
                if (other.dataType == DataType.Boolean) {
                    return ((Boolean) value).compareTo((Boolean) other.value);
                }
                break;
            case Date:
                if (other.dataType == DataType.Date) {
                    return ((Date) value).compareTo((Date) other.value);
                }
                break;
            case Long:
                switch (other.dataType) {
                    case Long:
                        return ((Long) value).compareTo((Long) other.value);
                    case Double:
                        boolean compare = getLong() > other.getDouble();
                        if (compare) {
                            return 1;
                        } else {
                            return other.getDouble() > getLong() ? -1 : 0;
                        }
                    default:
                        throw new FormulaException("Can not compare between " + dataType.toString() + " and " + other.dataType.toString());
                }
            case Double:
                switch (other.dataType) {
                    case Double:
                        return ((Double) value).compareTo((Double) other.value);
                    case Long:
                        boolean compare = getDouble() > other.getLong();
                        if (compare) {
                            return 1;
                        } else {
                            return getDouble() > other.getLong() ? -1 : 0;
                        }
                    default:
                        throw new FormulaException("Can not compare between " + dataType.toString() + " and " + other.dataType.toString());
                }
            default:
                throw new FormulaException("Can not compare between " + dataType.toString() + " and " + other.dataType.toString());
        }
        throw new FormulaException("Can not compare between " + dataType.toString() + " and " + other.dataType.toString());
    }
}
