package com.huntkey.rx.sceo.formula.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * @author lulx on 2017/6/23 0023.
 */
public class SerializeUtils {

    /**
     *@desc 反序列化
     *@pars [bytes]
     *@date 2017/6/23 0023 下午 2:41 lulx
     *@return java.lang.Object
     **/
    public static Object deserialize(byte[] bytes){
        try {
            ByteArrayInputStream byteInt=new ByteArrayInputStream(bytes);
            ObjectInputStream objInt=new ObjectInputStream(byteInt);
            return objInt.readObject();
        }catch (Exception e){
            throw new RuntimeException("反序列化错误", e);
        }
    }

    /**
     *@desc 序列化
     *@pars [o]
     *@date 2017/6/23 0023 下午 2:42 lulx
     *@return byte[]
     **/
    public static byte[] serialize(Object o){
        try {
            ByteArrayOutputStream byt=new ByteArrayOutputStream();
            ObjectOutputStream obj=new ObjectOutputStream(byt);
            obj.writeObject(o);
            return byt.toByteArray();
        }catch (Exception e){
            throw new RuntimeException("序列化错误", e);
        }
    }
}
