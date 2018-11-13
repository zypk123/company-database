package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.BlioBlioInitBalaSetaProperty;
import com.huntkey.rx.edm.constant.CurrencyProperty;
import com.huntkey.rx.edm.constant.ParkProperty;
import com.huntkey.rx.edm.constant.RelationProperty;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.NumberConstants;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.base.BaseOrderService;
import com.huntkey.rx.purchase.provider.service.ShouldPayOrReceiptOrderService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.model.OrmParamEx;
import com.huntkey.rx.sceo.orm.common.type.DataVailidEnum;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * 应收应付初始化单据管理
 * @author yaoss
 */
@Service
public class ShouldPayOrReceiptOrderServiceImpl extends BaseOrderService<BalanceinitoEntity,BlioBlioInitBalaSetaEntity> implements ShouldPayOrReceiptOrderService {

    @Override
    public void doVaildLogic(List<String> errMsgList, BalanceinitoEntity entity) {

    }

    @Override
    public String getOrderNbrTempPrefix() {
        return NumberConstants.PREFIX_PU_TEMP_ORDER;
    }

    @Override
    public String getOrderNbrPrefix() {
        return "FA21";
    }

    @Override
    public String getEntityEdmClassName() {
        return "balanceinito";
    }

    @Override
    public List<BlioBlioInitBalaSetaEntity> getEntitySetList(BalanceinitoEntity entity) {
        return entity.getBlio_init_bala_set();
    }

    @Override
    public JSONObject loadOrderEntityData(String orderId) throws Exception {
        // 这个单子没有自己的字段
        return null;
    }

    @Override
    public JSONObject loadOrderEntitySetData(String orderId) throws Exception {
        JSONObject returnData = new JSONObject();

        OrmParamEx ormParamEx = new OrmParamEx();
        ormParamEx.addColumn(BlioBlioInitBalaSetaProperty.BLIOI_RELATION)
        .addColumn(RelationProperty.RELA_CODE).addColumn(RelationProperty.RELA_SHORT_NAME)
        .addColumn(BlioBlioInitBalaSetaProperty.BLIOI_PARK)
        .addColumn(ParkProperty.RPAK_NAME).addColumn(ParkProperty.RPAK_CODE)
        .addColumn(BlioBlioInitBalaSetaProperty.BLIOI_CURRENCY)
        .addColumn(CurrencyProperty.CURR_NAME).addColumn(CurrencyProperty.CURR_CODE)
        .addColumn(BlioBlioInitBalaSetaProperty.BLIOI_CORP)
        .addColumn(BlioBlioInitBalaSetaProperty.BLIOI_TYPE)
        .addColumn(BlioBlioInitBalaSetaProperty.BLIOI_BALA_AMT)
        .addColumn(BlioBlioInitBalaSetaProperty.BLIOI_ACCT_DATE)
        .addColumn(BlioBlioInitBalaSetaProperty.BLIOI_REMARK)
        .addColumn(BlioBlioInitBalaSetaProperty.BLIOI_STATUS);

        ormParamEx
                .leftJoin(RelationEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (RelationEntity.class, NodeConstant.ID, BlioBlioInitBalaSetaEntity.class, BlioBlioInitBalaSetaProperty.BLIOI_RELATION), DataVailidEnum.NOMATTER)
                .leftJoin(ParkEntity.class,OrmParamEx.joinLinkInDifferentTable
                        (ParkEntity.class, NodeConstant.ID, BlioBlioInitBalaSetaEntity.class, BlioBlioInitBalaSetaProperty.BLIOI_PARK), DataVailidEnum.NOMATTER)
                .leftJoin(CurrencyEntity.class,OrmParamEx.joinLinkInDifferentTable
                        (CurrencyEntity.class, NodeConstant.ID, BlioBlioInitBalaSetaEntity.class, BlioBlioInitBalaSetaProperty.BLIOI_CURRENCY), DataVailidEnum.NOMATTER);

        ormParamEx.setWhereExp(OrmParam.and(ormParamEx.getEqualXML(OrmParamEx.column(BlioBlioInitBalaSetaEntity.class, "is_del"), 0)
        ,ormParamEx.getEqualXML(OrmParamEx.column(BlioBlioInitBalaSetaEntity.class, NodeConstant.PID), orderId)
        ));

        List<Map<String, Object>> list = ormService.selectMapListEx(BlioBlioInitBalaSetaEntity.class, ormParamEx);
        JSONArray array = new JSONArray();
        if (null != list && list.size() > 0) {
            for(Map<String,Object> map:list){
                JSONObject object = new JSONObject();
                // 伙伴id
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_RELATION, NullUtils.valueOf(map.get(BlioBlioInitBalaSetaProperty.BLIOI_RELATION)));
                // 伙伴编码
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_RELATION+"_code", NullUtils.valueOf(map.get(RelationProperty.RELA_CODE)));
                // 伙伴简称
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_RELATION+"_short_name", NullUtils.valueOf(map.get(RelationProperty.RELA_SHORT_NAME)));
                // 园区id
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_PARK, NullUtils.valueOf(map.get(BlioBlioInitBalaSetaProperty.BLIOI_PARK)));
                // 园区编码
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_PARK+"_code", NullUtils.valueOf(map.get(ParkProperty.RPAK_CODE)));
                // 园区名字
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_PARK+"_name", NullUtils.valueOf(map.get(ParkProperty.RPAK_NAME)));
                // 币别id
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_CURRENCY, NullUtils.valueOf(map.get(BlioBlioInitBalaSetaProperty.BLIOI_CURRENCY)));
                // 币别code
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_CURRENCY+"_code", NullUtils.valueOf(map.get(CurrencyProperty.CURR_CODE)));
                // 币别名字
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_CURRENCY+"_name", NullUtils.valueOf(map.get(CurrencyProperty.CURR_NAME)));
                // 法人id
                String corpId = NullUtils.valueOf(map.get(BlioBlioInitBalaSetaProperty.BLIOI_CORP));
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_CORP, corpId);
                // 法人code
                JSONObject corp = getRelationCodeAndShortName(corpId);
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_CORP+"_code",corp.getString(RelationProperty.RELA_CODE));
                // 法人name
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_CORP+"_short_name",corp.getString(RelationProperty.RELA_SHORT_NAME));
                // 类型
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_TYPE, NullUtils.valueOf(map.get(BlioBlioInitBalaSetaProperty.BLIOI_TYPE)));
                // 期初余额
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_BALA_AMT,NullUtils.doubleValueOf(map.get(BlioBlioInitBalaSetaProperty.BLIOI_BALA_AMT)));
                // 开账日期
                Object dateStr = map.get(BlioBlioInitBalaSetaProperty.BLIOI_ACCT_DATE);
                if (!StringUtil.isNullOrEmpty(dateStr)) {
                    Timestamp date = (Timestamp) dateStr;
                    object.put(BlioBlioInitBalaSetaProperty.BLIOI_ACCT_DATE, DateUtil.transferDate(date.getTime(),"yyyy-MM-dd"));
                } else {
                    object.put(BlioBlioInitBalaSetaProperty.BLIOI_ACCT_DATE, "");
                }
                // 备注
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_REMARK, NullUtils.valueOf(map.get(BlioBlioInitBalaSetaProperty.BLIOI_REMARK)));
                // 状态
                object.put(BlioBlioInitBalaSetaProperty.BLIOI_STATUS, NullUtils.valueOf(map.get(BlioBlioInitBalaSetaProperty.BLIOI_STATUS)));

                array.add(object);
            }
        }
        returnData.put("blio_init_bala_set",array);

        return returnData;
    }

    private JSONObject getRelationCodeAndShortName(String relaId) throws Exception{
        JSONObject object = new JSONObject();
        if(!StringUtil.isNullOrEmpty(relaId)){
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(RelationProperty.RELA_CODE).addColumn(RelationProperty.RELA_SHORT_NAME);
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID,relaId)));
            List<Map<String, Object>> list = ormService.selectMapList(RelationEntity.class,ormParam);
            if(null!=list&&list.size()>0){
                Map<String,Object> map = list.get(0);
                object.put(RelationProperty.RELA_CODE,NullUtils.valueOf(map.get(RelationProperty.RELA_CODE)));
                object.put(RelationProperty.RELA_SHORT_NAME,NullUtils.valueOf(map.get(RelationProperty.RELA_SHORT_NAME)));
            }
        }else{
            object.put(RelationProperty.RELA_CODE,"");
            object.put(RelationProperty.RELA_SHORT_NAME,"");
        }
        return object;
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
