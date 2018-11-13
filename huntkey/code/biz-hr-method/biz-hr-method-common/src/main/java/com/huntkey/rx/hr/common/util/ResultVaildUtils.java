package com.huntkey.rx.hr.common.util;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;


/**
 * @author yaoss
 * @Date 2017/12/7 14:25
 * @@Description
 */
public class ResultVaildUtils {

    public static void vaildResult(Result result){
        if(null==result){
            throw new ApplicationException(MsgConstants.MSG_HR_ORM_CALL_EXCEPTION);
        }
        if (!Result.RECODE_SUCCESS.equals(result.getRetCode())) {
            throw new ApplicationException(Result.RECODE_ERROR, MsgConstants.MSG_HR_ORM_CALL_EXCEPTION + result.getErrMsg());
        }
    }

}
