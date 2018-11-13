package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.EdmConvoluteExtend;
import com.huntkey.rx.modeler.common.model.EdmProperty;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by licj on 2017/8/15.
 */
public interface EdmConvoluteExtendService {

    List<EdmConvoluteExtend> selectListByPropertyId(String id);

    @Transactional(readOnly = false)
    int insert(EdmConvoluteExtend edmConvoluteExtend);

    @Transactional(readOnly = false)
    int delete(String edceEdmpId);
}
