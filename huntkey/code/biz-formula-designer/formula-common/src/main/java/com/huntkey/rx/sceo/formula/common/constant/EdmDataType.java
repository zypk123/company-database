package com.huntkey.rx.sceo.formula.common.constant;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author chenfei on 2017/7/5.
 */
public enum EdmDataType {

    /**
     * 数字
     */
    NUM("num", "数字", new NumTransfer()),
    /**
     * 数字
     */
    NUMBER("number", "数字", new NumberTransfer()),
    /**
     * 文本
     */
    TEXT("text", "文本", new TextTransfer()),
    /**
     * 日期
     */
    DATE("date", "日期", new DateTransfer()),
    /**
     * 逻辑
     */
    LOGIC("logic", "逻辑", new LogicTransfer()),
    /**
     * 布尔
     */
    BOOLEAN("boolean", "布尔", new BooleanTransfer()),
    /**
     * 集合
     */
    SET("set", "集合", new SetTransfer()),
    /**
     * 属性集
     */
    ASSEMBLE("assemble", "属性集", new AssembleTransfer()),
    /**
     * 整数
     */
    INT("int", "整数", new IntTransfer()),
    /**
     * 对象实例
     */
    INSTANTIATE("instantiate", "对象实例", new ObjectTransfer()),
    /**
     * 字符串
     */
    VARCHAR("varchar", "字符串", new VarcharTransfer()),
    /**
     * 浮点数
     */
    FLOAT("float", "浮点数", new FloatTransfer()),
    /**
     * 对象
     */
    OBJECT("object", "对象", new ObjectTransfer()),
    /**
     * 常量
     */
    CONST("const", "常量", new ConstTransfer()),
    /**
     * 浮点数
     */
    DECIMAL("decimal", "浮点数", new DecimalTransfer()),
    /**
     * 计量单位
     */
    MEASUREMENT("measurement", "计量单位", new MeasurementTransfer()),
    /**
     * 普通
     */
    NORMALOBJ("normalObj", "普通", new NormalObjTransfer()),
    /**
     * 对象链接
     */
    OBJECTLINK("objectLink", "对象链接", new ObjectLinkTransfer()),
    /**
     * 枚举
     */
    ENUM("enum", "枚举", new EnumTransfer()),
    /**
     * 属性集
     */
    CONVOLUTION("convolution", "属性集", new ConvolutionTransfer()),
    /**
     * 数值数组型
     */
    NUMBERARRAY("numberArray", "数值数组型", new NumberArrayTransfer()),
    /**
     * 任何
     */
    ANY("any", "任何", new AnyTransfer());

    /**
     * 数据类型
     */
    private String dataType;
    /**
     * 数据标签
     */
    private String dataLabel;

    /**
     * the interface to transfer something to another.
     */
    private Transable transable;

    EdmDataType(String dataType, String dataLabel, Transable transable) {
        this.dataType = dataType;
        this.dataLabel = dataLabel;
        this.transable = transable;
    }

    public String getDataType() {
        return dataType;
    }

    public Transable getTransable() {
        return transable;
    }

    public void setTransable(Transable transable) {
        this.transable = transable;
    }

    public static EdmDataType getDataType(String dataType) {
        EdmDataType[] types = EdmDataType.values();
        for (EdmDataType t : types) {
            if (t.getDataType().equalsIgnoreCase(dataType.trim())) {
                return t;
            }
        }
        throw new RuntimeException("The dataType: " + dataType + " had not been declared yet.");
    }

}

/**
 * nothing to do.
 */
class AnyTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        return dataVal;
    }
}

/**
 * nothing to do.
 */
class NumberArrayTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        return dataVal;
    }
}

/**
 * nothing to do.
 */
class ConvolutionTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        return dataVal;
    }
}

/**
 * nothing to do.
 */
class EnumTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        return dataVal;
    }
}

/**
 * nothing to do.
 */
class ObjectLinkTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        return dataVal;
    }
}

/**
 * nothing to do.
 */
class NormalObjTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        return dataVal;
    }
}

/**
 * nothing to do.
 */
class MeasurementTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        return dataVal;
    }
}

/**
 * transfer decimal to number(double).
 */
class DecimalTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        try {
            return Double.parseDouble(dataVal);
        } catch (NumberFormatException e) {
            throw new RuntimeException("num string: " + dataVal + " can not transfer to a num value.");
        }
    }
}

/**
 * transfer string to number(double).
 */
class NumTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        try {
            return Double.parseDouble(dataVal);
        } catch (NumberFormatException e) {
            throw new RuntimeException("num string: " + dataVal + " can not transfer to a num value.");
        }
    }
}

/**
 * transfer string to number(double).
 */
class NumberTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        try {
            return Double.parseDouble(dataVal);
        } catch (NumberFormatException e) {
            throw new RuntimeException("num string: " + dataVal + " can not transfer to a num value.");
        }
    }
}

/**
 * transfer any value to string.
 */
class TextTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        return dataVal;
    }
}

/**
 * transfer a date string to a date object.
 */
class DateTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        return new Date(Timestamp.valueOf(dataVal).getTime());
    }
}

/**
 * transfer a boolean string to a boolean Object.
 */
class LogicTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        try {
            return Boolean.parseBoolean(dataVal);
        } catch (Exception e) {
            throw new RuntimeException("logic string: " + dataVal + " can not transfer to a boolean value.");
        }
    }
}

/**
 * transfer a boolean string to a boolean Object.
 */
class BooleanTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        try {
            return Boolean.parseBoolean(dataVal);
        } catch (Exception e) {
            throw new RuntimeException("logic string: " + dataVal + " can not transfer to a boolean value.");
        }
    }
}

/**
 * transfer a set string to a string, just like below:
 * "[1, 2, 3]" => "1, 2, 3"
 * "['1', '2', '3']" => "'1', '2', '3'"
 */
class SetTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        try {
            return dataVal.replace("[", "").replace("]", "");
        } catch (Exception e) {
            throw new RuntimeException("set string: " + dataVal + " can not transfer to a set.");
        }
    }
}

/**
 * noting to transfer.
 */
class AssembleTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {

        // nothing to do.
        return dataVal;
    }
}

/**
 * transfer an int string to an int object.
 */
class IntTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        try {
            return Integer.parseInt(dataVal);
        } catch (NumberFormatException e) {
            throw new RuntimeException("int string: " + dataVal + " can not transfer to a int value.");
        }
    }
}

/**
 * nothing to do.
 */
class InstantiateTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        return dataVal;
    }
}

/**
 * transfer a varchar string to a string.
 */

/**
 * actually, here also nothing to do.
 */
class VarcharTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        return dataVal;
    }
}

/**
 * transfer a float string to a float object.
 */
class FloatTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        try {
            return Float.parseFloat(dataVal);
        } catch (NumberFormatException e) {
            throw new RuntimeException("float string: " + dataVal + " can not transfer to a float value.");
        }
    }
}

/**
 * nothing to do.
 */
class ObjectTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        return dataVal;
    }
}

/**
 * nothing to do.
 */
class ConstTransfer implements Transable {

    @Override
    public Object transfer(String dataVal) {
        return dataVal;
    }
}




