package com.huntkey.rx.purchase.provider.dao.impl;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.CustommaintorderProperty;
import com.huntkey.rx.edm.constant.OrderProperty;
import com.huntkey.rx.edm.entity.CustommaintorderEntity;
import com.huntkey.rx.purchase.common.constants.OrderConstants;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.provider.dao.CustomMaintOrderDao;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuyf on 2018/2/10 0010.
 */
@Repository
public class CustomMaintOrderDaoImpl implements CustomMaintOrderDao {

    private static final Logger logger = LoggerFactory.getLogger(CustomMaintOrderDaoImpl.class);

    @Autowired
    OrmService ormService;

    @Override
    public List<CustommaintorderEntity> selectCustomMaintOrderUniqueCheck(String exceptCumoId, String cumoUscc, String cumoCode, String cumoShortName) {
        List<CustommaintorderEntity> custommaintorderEntityList;
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(cumoUscc)) {
            conditionList.add(ormParam.getEqualXML(CustommaintorderProperty.CUMO_USCC, cumoUscc));
        }
        if (!StringUtil.isNullOrEmpty(cumoCode)) {
            conditionList.add(ormParam.getEqualXML(CustommaintorderProperty.CUMO_CODE, cumoCode));
        }
        if (!StringUtil.isNullOrEmpty(cumoShortName)) {
            conditionList.add(ormParam.getEqualXML(CustommaintorderProperty.CUMO_SHORT_NAME, cumoShortName));
        }
        if (conditionList.size() <= 0){
            return null;
        }
        //过滤单据已结束的数据
        String[] overStatus = {OrderConstants.ORDE_STATUS_5, OrderConstants.ORDE_STATUS_6};

        String whereExp = OrmParam.and(ormParam.getNotInXML(OrderProperty.ORDE_STATUS, overStatus), OrmParam.or(conditionList));

        if (!StringUtil.isNullOrEmpty(exceptCumoId)){
            whereExp = OrmParam.and(ormParam.getUnequalXML(EdmSysColumn.ID, exceptCumoId), whereExp);
        }
        ormParam.setWhereExp(whereExp);
        try {
            custommaintorderEntityList = ormService.selectBeanList(CustommaintorderEntity.class, ormParam);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询客户维护单错误：" + e.getMessage());
        }

        if (custommaintorderEntityList == null || custommaintorderEntityList.size() <= 0) {
            custommaintorderEntityList = new ArrayList<>();
        }

        return custommaintorderEntityList;
    }
}
