package com.huntkey.rx.hr.common.exception;

import com.huntkey.rx.commons.utils.rest.Result;

/**
 * Created by clarkzhao on 2017/10/26.
 *
 * @author clarkzhao
 * @date 2017/10/26
 */
public class ApplicationException extends RuntimeException {

    private int code;

    public ApplicationException(String message) {
        super(message, null, true, true);
        this.code = Result.RECODE_VALIDATE_ERROR;
    }

    public ApplicationException(int code,
                                String message) {
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
