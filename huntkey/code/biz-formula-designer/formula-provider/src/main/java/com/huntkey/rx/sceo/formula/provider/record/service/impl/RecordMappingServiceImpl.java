package com.huntkey.rx.sceo.formula.provider.record.service.impl;

import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.model.*;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.provider.engine.dao.TfdFormulaMapper;
import com.huntkey.rx.sceo.formula.provider.property.dao.TplPropertyLimitMapper;
import com.huntkey.rx.sceo.formula.provider.record.dao.SourceSystemRecordMappingMapper;
import com.huntkey.rx.sceo.formula.provider.record.service.RecordMappingService;
import com.huntkey.rx.sceo.formula.provider.related.classes.dao.TfdClassRelatedMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author nidongx on 2017/7/21.
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class RecordMappingServiceImpl<M extends SourceSystemRecordMapping> extends BaseService<M> implements RecordMappingService {

    private Logger logger = LoggerFactory.getLogger(RecordMappingServiceImpl.class);

    @Autowired
    SourceSystemRecordMappingMapper recordMappingMapper;

    @Autowired
    RecordMappingServiceImpl recordMappingServiceImpl;

    @Autowired
    TplPropertyLimitMapper tplPropertyLimitMapper;

    @Autowired
    TfdClassRelatedMapper tfdClassRelatedMapper;

    @Autowired
    TfdFormulaMapper tfdFormulaMapper;


    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int addSourceSystemRecordMapping(SourceSystemRecordMapping sourceSystemRecordMapping) {
        SourceSystemRecordMapping sourceSystemRecordMappingDb =  queryRecord(sourceSystemRecordMapping.getSourceMappingId(),sourceSystemRecordMapping.getSourceMappingType(),Constant.ISENABLE_NO);
        if(!StringUtil.isNullOrEmpty(sourceSystemRecordMappingDb)){
            sourceSystemRecordMapping.setRecdId(sourceSystemRecordMappingDb.getRecdId());
            return 1;
        }
        recordMappingServiceImpl.saveSetting(sourceSystemRecordMapping);
        // 新增时默认状态为不可用
        sourceSystemRecordMapping.setIsenable((byte) 0);
        return recordMappingMapper.insertSelective(sourceSystemRecordMapping);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updateSourceSystemRecordMapping(SourceSystemRecordMapping sourceSystemRecordMapping) {
        recordMappingServiceImpl.updateSetting(sourceSystemRecordMapping);
        // 将状态修改成可用
        sourceSystemRecordMapping.setIsenable((byte) 1);
        return recordMappingMapper.updateByPrimaryKeySelective(sourceSystemRecordMapping);
    }

    @Override
    public String queryFormularDec(String sourceMappingId, String sourceMappingType) {
//        1:属性公式、2:属性限值、3:关联条件
        String retStr = null;
        SourceSystemRecordMappingExample example = new SourceSystemRecordMappingExample();
        SourceSystemRecordMappingExample.Criteria criteria = example.createCriteria();
        criteria.andSourceMappingIdEqualTo(sourceMappingId);
        criteria.andSourceMappingTypeEqualTo(sourceMappingType);
        List<SourceSystemRecordMapping> list = recordMappingMapper.selectByExample(example);
        if (list != null && list.size() > 0) {
            String insideId = list.get(0).getInsideId();
            if ("1".equals(sourceMappingType)) {
                TfdFormula tfdFormula = tfdFormulaMapper.selectByPrimaryKey(insideId);
                retStr = (tfdFormula == null ? null : tfdFormula.getFrmuFormulaContent());
            } else if ("2".equals(sourceMappingType)) {
                TplPropertyLimit tplPropertyLimit = tplPropertyLimitMapper.selectByPrimaryKey(insideId);
                retStr = (tplPropertyLimit == null ? null : tplPropertyLimit.getPrprConditionDesc());
            } else if ("3".equals(sourceMappingType)) {
                TfdClassRelated tfdClassRelated = tfdClassRelatedMapper.selectByPrimaryKey(insideId);
                retStr = (tfdClassRelated == null ? null : tfdClassRelated.getClssConditionDesc());
            }
        }
        return retStr;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int insert(SourceSystemRecordMapping sourceSystemRecordMapping) {
        recordMappingServiceImpl.saveSetting(sourceSystemRecordMapping);
        return recordMappingMapper.insertSelective(sourceSystemRecordMapping);
    }

    @Override
    public SourceSystemRecordMapping queryBySourceMappingId(String sourceMappingId) {
        SourceSystemRecordMappingExample example = new SourceSystemRecordMappingExample();
        SourceSystemRecordMappingExample.Criteria criteria = example.createCriteria();
        criteria.andSourceMappingIdEqualTo(sourceMappingId);
        List<SourceSystemRecordMapping> list = recordMappingMapper.selectByExample(example);
        if(list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    public SourceSystemRecordMapping queryRecord(String sourceMappingId, String sourceMappingType, Byte isEnable) {
        SourceSystemRecordMappingExample example = new SourceSystemRecordMappingExample();
        SourceSystemRecordMappingExample.Criteria criteria = example.createCriteria();
        criteria.andSourceMappingIdEqualTo(sourceMappingId);
        criteria.andSourceMappingTypeEqualTo(sourceMappingType);
        if(!StringUtil.isNullOrEmpty(isEnable)){
            criteria.andIsenableEqualTo(isEnable);
        }
        List<SourceSystemRecordMapping> list = recordMappingMapper.selectByExample(example);
        if(list.size() > 0){
            return list.get(0);
        }else{
            return null;
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int delRecordMapping(String insideId) {
        return recordMappingMapper.delRecordMapping(insideId);
    }
}
