package com.huntkey.rx.modeler.common.exception;

/**
 * Created by xuyf on 2017/5/24 0024.
 */
public class BusinessValidateException extends RuntimeException {

    public BusinessValidateException(){
        super();
    }

    public BusinessValidateException(String msg) {
        super(msg);
    }
}
