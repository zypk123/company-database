package com.huntkey.rx.purchase.provider.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.SettlemenetEntity;
import com.huntkey.rx.purchase.common.model.settlemenet.SettleMenetDTO;

/**
 * 结算方式Service接口
 *
 * @author zhangyu
 * @create 2018-01-10 16:58
 **/
public interface SettleMenetService {

    /**
     * 加载 结算方式
     * @param id
     * @return
     */
    SettlemenetEntity load(String id);

    /**
     * 新增 结算方式
     *
     * @param settleMenetDTO
     * @return
     */
    String insert(SettleMenetDTO settleMenetDTO);

    /**
     * 保存 结算方式
     *
     * @param settleMenetDTO
     * @return
     */
    Result save(SettleMenetDTO settleMenetDTO);

}
