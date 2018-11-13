package com.huntkey.rx.purchase.provider.dao.impl;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.OrderProperty;
import com.huntkey.rx.edm.constant.SuppliermaintorderProperty;
import com.huntkey.rx.edm.entity.SuppliermaintorderEntity;
import com.huntkey.rx.purchase.common.constants.OrderConstants;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.provider.dao.SupplierMaintOrderDao;
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
public class SupplierMaintOrderDaoImpl implements SupplierMaintOrderDao {

    private static final Logger logger = LoggerFactory.getLogger(SupplierMaintOrderDaoImpl.class);

    @Autowired
    OrmService ormService;

    @Override
    public List<SuppliermaintorderEntity> selectSupplierMaintOrderUniqueCheck(String exceptSumoId, String sumoUscc, String sumoCode, String sumoShortName) {
        List<SuppliermaintorderEntity> suppliermaintorderEntityList;
        OrmParam ormParam = new OrmParam();

        List<String> conditionList = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(sumoUscc)) {
            conditionList.add(ormParam.getEqualXML(SuppliermaintorderProperty.SUMO_USCC, sumoUscc));
        }
        if (!StringUtil.isNullOrEmpty(sumoCode)) {
            conditionList.add(ormParam.getEqualXML(SuppliermaintorderProperty.SUMO_CODE, sumoCode));
        }
        if (!StringUtil.isNullOrEmpty(sumoShortName)) {
            conditionList.add(ormParam.getEqualXML(SuppliermaintorderProperty.SUMO_SHORT_NAME, sumoShortName));
        }
        if (conditionList.size() <= 0){
            return null;
        }
        //过滤单据已结束的数据
        String[] overStatus = {OrderConstants.ORDE_STATUS_5, OrderConstants.ORDE_STATUS_6};

        String whereExp = OrmParam.and(ormParam.getNotInXML(OrderProperty.ORDE_STATUS, overStatus), OrmParam.or(conditionList));

        if (!StringUtil.isNullOrEmpty(exceptSumoId)){
            whereExp = OrmParam.and(ormParam.getUnequalXML(EdmSysColumn.ID, exceptSumoId), whereExp);
        }
        ormParam.setWhereExp(whereExp);
        try {
            suppliermaintorderEntityList = ormService.selectBeanList(SuppliermaintorderEntity.class, ormParam);
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, "查询供应商维护单错误：" + e.getMessage());
        }

        if (suppliermaintorderEntityList == null || suppliermaintorderEntityList.size() <= 0) {
            suppliermaintorderEntityList = new ArrayList<>();
        }

        return suppliermaintorderEntityList;
    }
}
