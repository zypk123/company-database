package com.huntkey.rx.modeler.provider.service.impl;


//

import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmConvoluteExtend;
import com.huntkey.rx.modeler.common.model.EdmConvoluteExtendExample;
import com.huntkey.rx.modeler.common.model.EdmProperty;
import com.huntkey.rx.modeler.provider.dao.EdmConvoluteExtendMapper;
import com.huntkey.rx.modeler.provider.service.EdmConvoluteExtendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by licj on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class EdmConvoluteExtendServiceImpl implements EdmConvoluteExtendService {

    private static Logger log = LoggerFactory.getLogger(EdmConvoluteExtendServiceImpl.class);

    @Autowired
    private EdmConvoluteExtendMapper edmConvoluteExtendMapperMapper;

    @Override
    public List<EdmConvoluteExtend> selectListByPropertyId(String id) {
        EdmConvoluteExtendExample example = new EdmConvoluteExtendExample();
        EdmConvoluteExtendExample.Criteria criteria = example.createCriteria();
        criteria.andEdceEdmpIdEqualTo(id).andIsDelNotEqualTo((byte)1);
        return edmConvoluteExtendMapperMapper.selectByExample(example);
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(EdmConvoluteExtend edmConvoluteExtend) {
        return edmConvoluteExtendMapperMapper.insert(edmConvoluteExtend);
    }

    @Override
    public int delete(String edceEdmpId) {
        return edmConvoluteExtendMapperMapper.deleteByEdceEdmpId(edceEdmpId);
    }
}
