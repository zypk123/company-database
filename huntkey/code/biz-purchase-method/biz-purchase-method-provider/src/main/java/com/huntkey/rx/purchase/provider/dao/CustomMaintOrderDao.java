package com.huntkey.rx.purchase.provider.dao;

import com.huntkey.rx.edm.entity.CustommaintorderEntity;

import java.util.List;

/**
 * Created by xuyf on 2018/2/10 0010.
 */
public interface CustomMaintOrderDao {

    /**
     * 通过唯一性属性条件查询客户维护单集合
     *
     * @param exceptCumoId
     * @param cumoUscc
     * @param cumoCode
     * @param cumoShortName
     * @return
     */
    List<CustommaintorderEntity> selectCustomMaintOrderUniqueCheck(String exceptCumoId, String cumoUscc, String cumoCode, String cumoShortName);
}
