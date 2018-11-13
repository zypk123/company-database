package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.EdmNumber;

import java.util.List;

/**
 * @author liucs
 * 2017-11-21 13:33:35
 */
public interface EdmNumberService {
    /**
     * 修改流水号
     * @param edmNumber
     * @return
     */
   String update(EdmNumber edmNumber);

}
