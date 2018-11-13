package com.huntkey.rx.purchase.common.exception;

/**
 * Created by liangh on 2017/11/26.
 *
 * @author liangh
 * @date 2017/11/26
 */
public class ApplicationException extends RuntimeException {

    private int code;

    public ApplicationException(int code, String message) {
        super(message, null, true, true);
        this.code = code;
    }

    /**
     * @return the code
     */
    public int getCode() {
        return code;
    }

}
