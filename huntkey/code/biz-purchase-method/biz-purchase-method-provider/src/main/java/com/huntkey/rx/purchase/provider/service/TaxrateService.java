package com.huntkey.rx.purchase.provider.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.TaxrateEntity;

/**
 * 税率类Service接口
 *
 * @author zhangyu
 * @create 2018-01-10 15:11
 **/
public interface TaxrateService {

    /**
     * 加载 结算方式
     *
     * @param id
     * @return
     */
    TaxrateEntity load(String id);

    /**
     * 查询所有税率类信息
     *
     * @return
     */
    Result getTaxrateList();


}
