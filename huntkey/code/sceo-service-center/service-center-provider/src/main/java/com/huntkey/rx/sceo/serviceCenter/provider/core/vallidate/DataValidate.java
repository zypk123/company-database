package com.huntkey.rx.sceo.serviceCenter.provider.core.vallidate;

import com.alibaba.fastjson.JSONObject;

/***********************************************************************
 * @author chenxj
 *
 * @email: kaleson@163.com
 *
 * @date : 2017年7月21日 下午2:09:52
 *
 **********************************************************************/
public interface DataValidate {
    /**
     * 新增对象的参数校验
     *
     * @param data 入参jsonObject
     * @return boolean
     */
    boolean validate4add(JSONObject data);

    /**
     * 修改对象的参数校验
     *
     * @param data 入参jsonObject
     * @return boolean
     */
    boolean validate4update(JSONObject data);

    /**
     * 删除对象的参数校验
     *
     * @param data 入参jsonObject
     * @return boolean
     */
    boolean validate4remove(JSONObject data);

    /**
     * 查询对象的参数校验
     *
     * @param data 入参jsonObject
     * @return boolean
     */
    boolean validate4search(JSONObject data);

    boolean validate4richfind(JSONObject serviceData);
}
