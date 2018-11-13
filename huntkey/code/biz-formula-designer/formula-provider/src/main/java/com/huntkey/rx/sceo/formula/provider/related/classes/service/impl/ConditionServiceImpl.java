package com.huntkey.rx.sceo.formula.provider.related.classes.service.impl;

import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.provider.related.classes.dao.TplConditionMapper;
import com.huntkey.rx.sceo.formula.provider.related.classes.service.ConditionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 相关类条件Service实现类
 *
 * @author zhangyu
 * @create 2017-07-04 17:26
 **/
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class ConditionServiceImpl<M extends TplCondition> extends BaseService<M> implements ConditionService {

    @Autowired
    TplConditionMapper conditionMapper;

    @Autowired
    ConditionServiceImpl conditionServiceImpl;

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int saveCondition(TplCondition tplCondition) {
        conditionServiceImpl.saveSetting(tplCondition);
        return conditionMapper.insertSelective(tplCondition);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updateCondition(TplCondition tplCondition) {
        conditionServiceImpl.updateSetting(tplCondition);
        return conditionMapper.updateByPrimaryKeySelective(tplCondition);
    }
}
