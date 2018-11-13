package com.huntkey.rx.modeler.common.util;

import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodVO;

/**
 * Created by yexin on 2017/9/28.
 */
public class VOToModel {

    public static EdmMethod VOToEdmMethod (EdmMethodVO edmMethodVO) {

        EdmMethod edmMethod = new EdmMethod();
        edmMethod.setId(edmMethodVO.getId());
        edmMethod.setEdmmEdmcId(edmMethodVO.getEdmmEdmcId());
        edmMethod.setEdmmPlanId(edmMethodVO.getEdmmPlanId());
        edmMethod.setIsCover(edmMethodVO.getIsCover());
        edmMethod.setIsDefined(edmMethodVO.getIsDefined());
        edmMethod.setIsDel(edmMethodVO.getIsDel());
        edmMethod.setAcctid(edmMethodVO.getAcctid());
        edmMethod.setAddtime(edmMethodVO.getAddtime());
        edmMethod.setAdduser(edmMethodVO.getAdduser());
        edmMethod.setModtime(edmMethodVO.getModtime());
        edmMethod.setModuser(edmMethodVO.getModuser());
        edmMethod.setEdmmType(edmMethodVO.getEdmmType());
        edmMethod.setEdmmTriggerCond(edmMethodVO.getEdmmTriggerCond());
        edmMethod.setEdmmArithmeticDesc(edmMethodVO.getEdmmArithmeticDesc());
        edmMethod.setEdmmArithmeticSourceName(edmMethodVO.getEdmmArithmeticSourceName());
        edmMethod.setEdmmArithmeticStorage(edmMethodVO.getEdmmArithmeticStorage());
        edmMethod.setEdmmDesc(edmMethodVO.getEdmmDesc());
        edmMethod.setEdmmDevelopDept(edmMethodVO.getEdmmDevelopDept());
        edmMethod.setEdmmName(edmMethodVO.getEdmmName());
        edmMethod.setEdmmProgrammer(edmMethodVO.getEdmmProgrammer());
        edmMethod.setEdmmProgramSourceName(edmMethodVO.getEdmmProgramSourceName());
        edmMethod.setEdmmProgramType(edmMethodVO.getEdmmProgramType());
        edmMethod.setEdmmUpdateDesc(edmMethodVO.getEdmmUpdateDesc());
        edmMethod.setEdmmVer(edmMethodVO.getEdmmVer());
        edmMethod.setEdmmSeq(edmMethodVO.getEdmmSeq());
        edmMethod.setEdmmStatus(edmMethodVO.getEdmmStatus());
        edmMethod.setEdmmRequestType(edmMethodVO.getEdmmRequestType());
        edmMethod.setEdmmExecRate(edmMethodVO.getEdmmExecRate());
        edmMethod.setEdmmMethodType(edmMethodVO.getEdmmMethodType());
        edmMethod.setEdmmJobId(edmMethodVO.getEdmmJobId());
        edmMethod.setEdmmExecuteType(edmMethodVO.getEdmmExecuteType());
        edmMethod.setTimeout(edmMethodVO.getTimeout());
        return edmMethod;
    }
}
