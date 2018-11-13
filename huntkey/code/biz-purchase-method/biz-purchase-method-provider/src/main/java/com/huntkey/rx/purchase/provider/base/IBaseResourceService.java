package com.huntkey.rx.purchase.provider.base;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;

/**
 *  资源类 接口
 * @param <T>
 */
public interface IBaseResourceService {

    /**
     * 保存
     * @param params
     * @return 结果
     */
    Result saveResource(JSONObject params);



}
