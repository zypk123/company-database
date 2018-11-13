package com.huntkey.rx.sceo.serviceCenter.common.emun;

/**
 * Created by linziy on 2017/8/29.
 *  edm 数据类型
 */
public enum EdmType {
    EDM_OBJECT(0,"object","对象"),
    EDM_CONST(1,"const","常量"),
    EDM_OBJECTLINK(2,"objectLink","对象链接"),
    EDM_NORMALOBJ(3,"normalObj","普通对象"),
    EDM_ASSEMBLE(4,"assemble","属性集"), //
    EDM_CLASS(5,"class","类型"),
    EDM_VALUE(9,"value","数值"),
    ///////////////////////////////////////////////////////////
    EDM_INT(6,"int","int"),
    EDM_DATE(7,"date","date"),
    EDM_TEXT(8,"text","text"),
    EDM_FLOAT(10,"float","float"),
    EDM_LONG(11,"long","long"),
    EDM_VARCHAR(12,"varchar","字符串"),
    EDM_DECIMAL(13,"decimal","带小数点的");

    private int index; //索引
    private String dataType;//数据类型
    private String desc; //属性类别

    private EdmType(int index,String dataType,String desc){
        this.index = index;
        this.dataType = dataType;
        this.desc = desc;
    }

    public String getDataType(){
        return this.dataType;
    }

}
