package com.huntkey.rx.hr.provider.service.impl;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.constants.ModelerConstants;
import com.huntkey.rx.hr.provider.dao.RelationDao;
import com.huntkey.rx.hr.provider.service.RelationService;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RelationServiceImpl implements RelationService {

    @Autowired
    RelationDao relationDao;

    @Override
    public Result getAllRelations() {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        SearchParam searchParam = new SearchParam(ModelerConstants.EDM_RELATION);
        result.setData(relationDao.query(searchParam));
        return result;
    }
}
