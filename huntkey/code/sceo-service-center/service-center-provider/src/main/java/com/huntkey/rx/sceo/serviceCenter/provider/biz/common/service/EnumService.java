package com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;

/**
 * Created by clarkzhao on 2017/10/23.
 */
public interface EnumService {
    /**
     * 根据枚举对象编码查询枚举项列表
     * @param enumCode 枚举对象编码
     * @return 枚举对象列表
     */
    Result getEnumsObjects(String enumCode) throws DBException;

    /**
     * 根据枚举对象ID查询枚举对象
     * @param enumObjId 枚举对象ID
     * @return 枚举对象
     */
    Result getEnumObjectById(String enumObjId) throws DBException;
}
