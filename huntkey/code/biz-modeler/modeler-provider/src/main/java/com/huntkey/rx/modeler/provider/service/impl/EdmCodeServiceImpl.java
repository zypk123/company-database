package com.huntkey.rx.modeler.provider.service.impl;

import com.huntkey.rx.modeler.common.model.EdmCode;
import com.huntkey.rx.modeler.common.model.EdmCodeExample;
import com.huntkey.rx.modeler.provider.dao.EdmCodeMapper;
import com.huntkey.rx.modeler.provider.service.EdmCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liangh on 2017/4/12.
 */
@Service
@Transactional(readOnly = true)
public class EdmCodeServiceImpl implements EdmCodeService {

    private static Logger log = LoggerFactory.getLogger(EdmCodeServiceImpl.class);

    @Autowired
    private EdmCodeMapper edmCodeMapper;

    @Override
    public List<EdmCode> queryEdmCodeListByCodeType(String codeType){
        EdmCodeExample example = new EdmCodeExample();
        EdmCodeExample.Criteria criteria = example.createCriteria();
        criteria.andCodeTypeEqualTo(codeType);
        List<EdmCode> list = edmCodeMapper.selectByExample(example);
        return list;
    }

    @Override
    public List<EdmCode> queryIndustries() {
        List<EdmCode> list = edmCodeMapper.selectIndustriesByCode();
        List<EdmCode> edmCodes = null;
        if (null != list && list.size() > 0) {
            edmCodes = new ArrayList<>();
            for (EdmCode childNode : list) {
                boolean mark = false;
                for (EdmCode parentNode : list) {
                    if (childNode.getCodeExt1() != null && childNode.getCodeExt1().equals(parentNode.getCodeValue())) {
                        mark = true;
                        if (parentNode.getChildren() == null)
                            parentNode.setChildren(new ArrayList<>());
                        parentNode.getChildren().add(childNode);
                        break;
                    }
                }
                if (!mark) {
                    edmCodes.add(childNode);
                }
            }
        }
        return edmCodes;
    }

    @Override
    public List<EdmCode> getDataTypeByPropertyValue(String codeValue) {
        List<EdmCode> retList = new ArrayList<EdmCode>();
        if("normalObj".equals(codeValue)) {
            EdmCodeExample example = new EdmCodeExample();
            EdmCodeExample.Criteria criteria = example.createCriteria();
            criteria.andCodeTypeEqualTo("edm_data_type");
            retList = edmCodeMapper.selectByExample(example);
        }
        return retList;
    }

    @Override
    public Map<String,List<EdmCode>> getDictsByCodes(String [] codeTypes) {
        Map<String,List<EdmCode>> result = new HashMap<String,List<EdmCode>>();
        for(String codeType:codeTypes) {
            if(!StringUtils.isEmpty(codeType)) {
                EdmCodeExample example = new EdmCodeExample();
                EdmCodeExample.Criteria criteria = example.createCriteria();
                criteria.andCodeTypeEqualTo(codeType);
                example.setOrderByClause("code_seq asc");
                List<EdmCode> list = edmCodeMapper.selectByExample(example);
                if(list.size()>0) {
                    result.put(codeType,list);
                }
            }
        }
        return result;
    }

    @Override
    public List<EdmCode> getTreeData(String codeType) {
        List<EdmCode> edmCodeList = new ArrayList<EdmCode>();
        EdmCodeExample example = new EdmCodeExample();
        EdmCodeExample.Criteria criteria = example.createCriteria();
        criteria.andCodeTypeEqualTo(codeType);
        example.setOrderByClause("code_seq asc");
        List<EdmCode> list = edmCodeMapper.selectByExample(example);
        if(list.size()>0) {
            for(EdmCode ec:list) {
                if(ec != null) {
                    if(StringUtils.isEmpty(ec.getCodeExt3())) {//说明是根节点
                        ec = setChildren(ec);
                        edmCodeList.add(ec);
                    }
                }
            }
        }
        return edmCodeList;
    }

    public EdmCode setChildren(EdmCode edmCode) {
        EdmCodeExample example = new EdmCodeExample();
        EdmCodeExample.Criteria criteria = example.createCriteria();
        criteria.andCodeExt3EqualTo(edmCode.getCodeValue());
        example.setOrderByClause("code_seq asc");
        List<EdmCode> list = edmCodeMapper.selectByExample(example);
        List<EdmCode> copyList = new ArrayList<EdmCode>();
        if(list.size()>0) {
            for(EdmCode ec:list) {
                copyList.add(ec);
            }
        }
        if(list.size()>0) {
            for(EdmCode e:list) {
                EdmCode temp = setChildren(e);
                copyList.remove(e);
                copyList.add(temp);
            }
        }
        edmCode.setChildren(list);
        return edmCode;
    }
}
