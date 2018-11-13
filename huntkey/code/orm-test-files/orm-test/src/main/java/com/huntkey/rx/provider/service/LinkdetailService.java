package com.huntkey.rx.provider.service;

import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.base.LinkdetailEntity;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linziy on 2017/11/24.
 */
public interface LinkdetailService {
    LinkdetailEntity loadLink(Class<? extends BaseEntity> edmClazz, Serializable entityID) throws Exception;

    Serializable insertLink(Class<? extends BaseEntity> edmClazz, LinkdetailEntity entity) throws Exception;

    List<LinkdetailEntity> selectBeanListLink(Class<? extends BaseEntity> edmClazz, OrmParam ormParam) throws Exception;

}
