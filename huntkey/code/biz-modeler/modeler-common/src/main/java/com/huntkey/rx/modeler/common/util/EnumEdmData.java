package com.huntkey.rx.modeler.common.util;

/**
 * Created by linziy on 2017/5/3.
 */
/**
 * 用作EnumMap的key 返回值
 * EnumEdmData
 */
public enum EnumEdmData{
    EDMCLASS("类",1),
    EDMPROPERTY("属性",2);
    // EDMCONVOLUTEVO("卷积扩展",3);

    private String name;
    int index;
    private EnumEdmData(String strName,int index){
        this.name = strName;
        this.index = index;
    }
}
