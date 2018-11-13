package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.PuqoPuqoLadderSetOldbEntity;
import com.huntkey.rx.edm.entity.PuqoPuqoLadderSetbEntity;
import com.huntkey.rx.edm.entity.PuqoPuqoPriceSetaEntity;
import com.huntkey.rx.edm.entity.PuquoteorderEntity;
import com.huntkey.rx.purchase.common.constants.NumberConstants;
import com.huntkey.rx.purchase.provider.base.BaseOrderService;
import com.huntkey.rx.purchase.provider.service.PuquTestService;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class PuquTestServiceImpl extends BaseOrderService<PuquoteorderEntity,PuqoPuqoPriceSetaEntity> implements PuquTestService {

    public static final Integer INT_1 = Integer.valueOf(1);
    public static final Integer INT_0 = Integer.valueOf(0);
    public static final BigDecimal BIG_0 = BigDecimal.valueOf(0);
    public static final BigDecimal BIG_99 = BigDecimal.valueOf(999999999);

    @Override
    public void doVaildLogic(List<String> errMsgList, PuquoteorderEntity entity) {

    }

    @Override
    public String getOrderNbrTempPrefix() {
        return NumberConstants.PREFIX_PU_TEMP_ORDER;
    }

    @Override
    public String getOrderNbrPrefix() {
        return NumberConstants.PREFIX_PUQUOTE_ORDER;
    }

    @Override
    public String getEntityEdmClassName() {
        return "puquoteorder";
    }

    @Override
    public List<PuqoPuqoPriceSetaEntity> getEntitySetList(PuquoteorderEntity entity) {
        return entity.getPuqo_price_set();
    }


    /**
     * 这里特殊情况，需要重写。
     * @param entity
     * @throws Exception
     */
    @Override
    public void insertEntitySet(PuquoteorderEntity entity) throws Exception{
        List<PuqoPuqoPriceSetaEntity> priceSetaEntities = entity.getPuqo_price_set();
        if(StringUtil.isNullOrEmpty(entity.getId())){
            EdmUtil.setPropertyBaseEntitiesSysColumns(PuqoPuqoPriceSetaEntity.class, entity,
                    priceSetaEntities, SQLCurdEnum.INSERT);
        }else{
            EdmUtil.setPropertyBaseEntitiesSysColumns(PuqoPuqoPriceSetaEntity.class, entity,
                    priceSetaEntities, SQLCurdEnum.UPDATE);
        }
        for (PuqoPuqoPriceSetaEntity e1 : priceSetaEntities) {
            e1.setId(null);
            e1.setClassName(getEntityEdmClassName());
            String pid = (String) ormService.insertSelective(e1);
            // 是阶梯报价
            if (INT_1.equals(e1.getPuqo_isladder()) || INT_1.equals(e1.getPuqo_isladder_old())) {
                List<PuqoPuqoLadderSetbEntity> ladderList = e1.getPuqo_ladder_set();
                List<PuqoPuqoLadderSetOldbEntity> ladderOldList = e1.getPuqo_ladder_set_old();
                if (ladderList.size() > 0) {
                    for (PuqoPuqoLadderSetbEntity e2 : ladderList) {
                        e2.setId(null);
                        e2.setPid(pid);
                        e2.setClassName(getEntityEdmClassName());
                        ormService.insertSelective(e2);
                    }
                }
                if (ladderOldList.size() > 0) {
                    for (PuqoPuqoLadderSetOldbEntity e3 : ladderOldList) {
                        e3.setId(null);
                        e3.setPid(pid);
                        e3.setClassName(getEntityEdmClassName());
                        ormService.insertSelective(e3);
                    }
                }
            }
        }

    }

    @Override
    public JSONObject loadOrderEntityData(String orderId) throws Exception {
        return null;
    }

    @Override
    public JSONObject loadOrderEntitySetData(String orderId) throws Exception {
        return null;
    }

    @Override
    protected Result handlePassLogic(String orderInstanceId) throws Exception {
        return null;
    }

    @Override
    public String getAuditVaildParamAndUpdateErrorMsg(JSONObject ordeParamObj) {
        return null;
    }

    @Override
    public Result queryOrderList(JSONObject params) {
        return null;
    }
}
