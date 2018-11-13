package com.huntkey.rx.sceo.serviceCenter.provider.biz.ppi.service;

import com.huntkey.rx.commons.utils.rest.Result;

public interface IndexDefineOrderService {

    /**
     * 指标公式设置流程通过，将单据表公式和变量数据写入指标定义表
     * @param formulaOrderId 公式设置子流程单据ID
     * @return
     */
    Result formulaOrderPass(String formulaOrderId) throws Exception;
}
