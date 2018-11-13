package com.huntkey.rx.sceo.formula.provider.related.condition.service.impl;

import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.model.TacPropertyRelated;
import com.huntkey.rx.sceo.formula.provider.related.condition.dao.TacConditionRelatedMapper;
import com.huntkey.rx.sceo.formula.provider.related.condition.dao.TacPropertyRelatedMapper;
import com.huntkey.rx.sceo.formula.provider.related.condition.service.PropRelatedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 属性关联Service实现类
 *
 * @author zhangyu
 * @create 2017-07-04 14:23
 **/
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class PropRelatedServiceImpl<M extends TacPropertyRelated> extends BaseService<M> implements PropRelatedService {

    @Autowired
    TacPropertyRelatedMapper propertyRelatedMapper;

    @Autowired
    private TacConditionRelatedMapper conditionRelatedMapper;

    @Autowired
    PropRelatedServiceImpl propRelatedServiceImpl;

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int savePropRelated(TacPropertyRelated tacPropertyRelated) {
        propRelatedServiceImpl.saveSetting(tacPropertyRelated);
        return propertyRelatedMapper.insertSelective(tacPropertyRelated);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updatePropRelated(TacPropertyRelated tacPropertyRelated) {
        return propertyRelatedMapper.updateByPrimaryKeySelective(tacPropertyRelated);
    }

    @Override
    public TacPropertyRelated selectByPrimaryKey(String prplId) {
        return propertyRelatedMapper.selectByPrimaryKey(prplId);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int delRelCondOrRelTrigger(String prplId) {
        propertyRelatedMapper.delRelCondOrRelTrigger(prplId);
        conditionRelatedMapper.updateIsEnaleByPropRelatedId(prplId);
        return 1;
    }

}
