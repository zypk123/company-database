package com.huntkey.rx.purchase.provider.dao;

import com.huntkey.rx.edm.entity.SuppliermaintorderEntity;

import java.util.List;

/**
 * Created by xuyf on 2018/2/10 0010.
 */
public interface SupplierMaintOrderDao {

    /**
     * 通过唯一性属性条件查询供应商维护单集合
     *
     * @param exceptSumoId
     * @param sumoUscc
     * @param sumoCode
     * @param sumoShortName
     * @return
     */
    List<SuppliermaintorderEntity> selectSupplierMaintOrderUniqueCheck(String exceptSumoId, String sumoUscc, String sumoCode, String sumoShortName);
}
