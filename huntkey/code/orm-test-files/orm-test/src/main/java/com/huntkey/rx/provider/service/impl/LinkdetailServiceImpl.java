package com.huntkey.rx.provider.service.impl;

import com.huntkey.rx.base.BaseEntity;
import com.huntkey.rx.base.LinkdetailEntity;
import com.huntkey.rx.provider.service.LinkdetailService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;

/**
 * Created by linziy on 2017/11/24.
 */
@Service
public class LinkdetailServiceImpl implements LinkdetailService {
    @Autowired
    OrmService ormService;

    @Override
    public LinkdetailEntity loadLink(Class<? extends BaseEntity> edmClazz, Serializable entityID) throws Exception {
        return ormService.loadLink(edmClazz, entityID);
    }

    @Override
    public Serializable insertLink(Class<? extends BaseEntity> edmClazz, LinkdetailEntity entity) throws Exception {
        return ormService.insertLink(edmClazz, entity);
    }

    @Override
    public List<LinkdetailEntity> selectBeanListLink(Class<? extends BaseEntity> edmClazz, OrmParam ormParam) throws Exception {
        return ormService.selectBeanListLink(edmClazz, ormParam);
    }

}
