package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.*;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.base.CurrentSessionEntity;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuqoOrderQueryDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuquGodsQueryDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuquoteOrderDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.SimplePuqoPriceSetDTO;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.BizFormService;
import com.huntkey.rx.purchase.provider.service.CommonService;
import com.huntkey.rx.purchase.provider.service.GoodsService;
import com.huntkey.rx.purchase.provider.service.PurchaseQuoteOrderService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.model.OrmParamEx;
import com.huntkey.rx.sceo.orm.common.type.DataVailidEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.*;


/**
 * 采购报价单service实现类
 * <p>
 * 使用分布式事务，不能在类上面加事务注解，请分别在方法上加。因为提交方法是不能加事务注解的
 *
 * @author yaoss
 */
@Service
public class PurchaseQuoteOrderServiceImpl implements PurchaseQuoteOrderService {

    @Autowired
    OrmService ormService;
    @Autowired
    BizFormService bizFormService;
    @Autowired
    CommonService commonService;
    @Autowired
    GoodsService goodsService;

    public static final Integer INT_1 = Integer.valueOf(1);
    public static final Integer INT_0 = Integer.valueOf(0);
    public static final BigDecimal BIG_0 = BigDecimal.valueOf(0);
    public static final BigDecimal BIG_99 = BigDecimal.valueOf(999999999);

    @Override
    public Result savePuquoteOrder(PuquoteOrderDTO params) {
        PuquoteorderEntity entity = JSON.parseObject(JSON.toJSONString(params), PuquoteorderEntity.class);
        // 提交校验
        Result validResult = validSaveOrSubmit(entity);
        if (validResult.getRetCode().equals(Result.RECODE_VALIDATE_ERROR)) {
            validResult.setErrMsg(validResult.getErrMsg() + ",当前临时单保存失败！");
            return validResult;
        }
        formateDateNoTime(entity);
        return insertData(entity);
    }

    private void formateDateNoTime(PuquoteorderEntity entity) {
        try {
            List<PuqoPuqoPriceSetaEntity> priceSetaEntities = entity.getPuqo_price_set();
            for (int i = 0; i < priceSetaEntities.size(); i++) {
                PuqoPuqoPriceSetaEntity e1 = priceSetaEntities.get(i);
                Date begDateOld = e1.getPuqo_date_beg_old();
                Date begDate = e1.getPuqo_date_beg();
                Date endDate = e1.getPuqo_date_end();
                Date endDateOld = e1.getPuqo_date_end_old();
                if (null != endDate) {
                    e1.setPuqo_date_end(NullUtils.formatDateNoTime(endDate));
                }
                if (null != endDateOld) {
                    e1.setPuqo_date_end_old(NullUtils.formatDateNoTime(endDateOld));
                }
                if (null != begDate) {
                    e1.setPuqo_date_beg(NullUtils.formatDateNoTime(begDate));
                }
                if (null != begDateOld) {
                    e1.setPuqo_date_beg_old(NullUtils.formatDateNoTime(begDateOld));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result insertData(PuquoteorderEntity entity) {
        Result result = new Result();
        entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
        String orderNbr = "";
        try {
            entity.setEdmd_class("puquoteorder");
            if (StringUtil.isNullOrEmpty(entity.getId())) {
                entity.setCretime(new Date());
                entity.setOrde_date(entity.getCretime());
                //TODO Session信息的插入
                //更新Session信息
                CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
                entity.setCreuser(sessionEntity.getEmployeeId());
                // 插入新记录 临时单
                orderNbr = commonService.getCode(NumberConstants.PREFIX_PU_TEMP_ORDER);
                entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                entity.setOrde_nbr(orderNbr);
                // 插入主表
                String insertId = (String) ormService.insertSelective(entity);
                entity.setId(insertId);
                // 插入关联表
                List<PuqoPuqoPriceSetaEntity> priceSetaEntities = entity.getPuqo_price_set();
                //  关联表更新主表的主键setPid,setClassName,setCreuser
                EdmUtil.setPropertyBaseEntitiesSysColumns(PuqoPuqoPriceSetaEntity.class, entity,
                        priceSetaEntities, SQLCurdEnum.INSERT);
                saveOrUpdatePriceSetCommon(priceSetaEntities);

            } else {
                entity.setModtime(new Date());
                entity.setOrde_date(entity.getModtime());
                //TODO Session信息的更新
                //更新Session信息
                CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
                entity.setModuser(sessionEntity.getEmployeeId());
                entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                // 更新主表记录
                ormService.updateSelective(entity);
                // 删除原先保存的关联表记录
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, entity.getId())));
                ormService.delete(PuqoPuqoPriceSetaEntity.class, ormParam);
                // 插入新的关联表记录
                List<PuqoPuqoPriceSetaEntity> priceSetaEntities = entity.getPuqo_price_set();
                EdmUtil.setPropertyBaseEntitiesSysColumns(PuqoPuqoPriceSetaEntity.class, entity,
                        priceSetaEntities, SQLCurdEnum.UPDATE);
                // 插入属性集
                saveOrUpdatePriceSetCommon(priceSetaEntities);
                // 取出记录中的单据单号返回
                ormParam.reset();
                ormParam.addColumn(NodeConstant.ID).addColumn(OrderProperty.ORDE_NBR);
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, entity.getId())));
                List<PuquoteorderEntity> queryList = ormService.selectBeanList(PuquoteorderEntity.class, ormParam);
                if (null != queryList && queryList.size() > 0) {
                    PuquoteorderEntity e1 = queryList.get(0);
                    orderNbr = e1.getOrde_nbr();
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        result.setErrMsg("保存或更新单据成功");
        JSONObject jsonObject = new JSONObject();
        jsonObject.put(NodeConstant.ID, entity.getId());
        jsonObject.put(OrderProperty.ORDE_NBR, orderNbr);
        jsonObject.put(OrderProperty.ORDE_RODE_OBJ, NullUtils.valueOf(entity.getOrde_rode_obj()));

        JsonUtils.underLine2Camel(jsonObject);
        result.setData(jsonObject);
        return result;

    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Result validSaveOrSubmit(PuquoteorderEntity entity) {
        //TODO 校验保存单据
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        try {
            List<String> errMsgList = new ArrayList<>();
            String ordeRodeObj = entity.getOrde_rode_obj();
            String ordeAdduser = entity.getOrde_adduser();
            String ordeDept = entity.getOrde_dept();
            String ordeDuty = entity.getOrde_duty();
            if (StringUtil.isNullOrEmpty(ordeRodeObj)) {
                errMsgList.add("单据定义ID ordeRodeObj 参数必须传递 ");
            }
            if (StringUtil.isNullOrEmpty(ordeAdduser)) {
                errMsgList.add("制单人ID ordeAdduser 参数必须传递 ");
            }
            if (StringUtil.isNullOrEmpty(ordeDept)) {
                errMsgList.add("制单部门ID ordeDept 参数必须传递 ");
            }
            if (StringUtil.isNullOrEmpty(ordeDuty)) {
                errMsgList.add("制单岗位ID ordeDuty 参数必须传递 ");
            }
            if (errMsgList.size() > 0) {
                result.setErrMsg(Arrays.toString(errMsgList.toArray(new String[0])).replace("[", "").replace("]", ""));
                return result;
            }

            // 校验报价单是否是否存在重复的行
            validFiveStep(errMsgList, entity);
            if (errMsgList.size() > 0) {
                result.setErrMsg(Arrays.toString(errMsgList.toArray(new String[0])).replace("[", "").replace("]", ""));
                return result;
            }
            //6、校验列表各行（物品、供应商、币别、园区），是否存在待审单据，否则提示：第***行存在**单据（单据号：****），不可提交。
            validSixStep(errMsgList, entity);
            if (errMsgList.size() > 0) {
                result.setErrMsg(Arrays.toString(errMsgList.toArray(new String[0])).replace("[", "").replace("]", ""));
                return result;
            }

            //必填项校验，提示：**信息填写不完整，不可提交。
            String currId = entity.getPuqo_curr();
            String suppId = entity.getPuqo_code_supp();
            String taxId = entity.getPuqo_tax();
            if (StringUtil.isNullOrEmpty(suppId)) {
                errMsgList.add("请填写供应商");
            }
            if (StringUtil.isNullOrEmpty(currId)) {
                errMsgList.add("请选择币别");
            }
            if (StringUtil.isNullOrEmpty(taxId)) {
                errMsgList.add("请选择税率");
            }

            List<PuqoPuqoPriceSetaEntity> priceSetaEntities = entity.getPuqo_price_set();

            Date nowDate = NullUtils.formatDateNoTime(new Date());
            for (int i = 0; i < priceSetaEntities.size(); i++) {
                PuqoPuqoPriceSetaEntity e1 = priceSetaEntities.get(i);
                String goodsId = e1.getPuqo_goods();
                String parkId = e1.getPuqo_park();
                if (StringUtil.isNullOrEmpty(goodsId)) {
                    errMsgList.add("第"+(i+1)+"行，请填写有效物品");
                }
                if (StringUtil.isNullOrEmpty(parkId)) {
                    errMsgList.add("第"+(i+1)+"行，请填写有效园区");
                }
                validOneStepDate(errMsgList, nowDate, i, e1);
                validTwoStep(errMsgList, i, goodsId, suppId, parkId);
            }
            if (errMsgList.size() > 0) {
                result.setErrMsg(Arrays.toString(errMsgList.toArray(new String[0])).replace("[", "").replace("]", ""));
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }

        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }


    private void saveOrUpdatePriceSetCommon(List<PuqoPuqoPriceSetaEntity> priceSetaEntities) throws Exception {
        for (PuqoPuqoPriceSetaEntity e1 : priceSetaEntities) {
            e1.setId(null);
            e1.setClassName("puquoteorder");
            String pid = (String) ormService.insertSelective(e1);
            // 是阶梯报价
            if (INT_1.equals(e1.getPuqo_isladder()) || INT_1.equals(e1.getPuqo_isladder_old())) {
                List<PuqoPuqoLadderSetbEntity> ladderList = e1.getPuqo_ladder_set();
                List<PuqoPuqoLadderSetOldbEntity> ladderOldList = e1.getPuqo_ladder_set_old();
                if (ladderList.size() > 0) {
                    for (PuqoPuqoLadderSetbEntity e2 : ladderList) {
                        e2.setId(null);
                        e2.setPid(pid);
                        e2.setClassName("puquoteorder");
                        ormService.insertSelective(e2);
                    }
                }
                if (ladderOldList.size() > 0) {
                    for (PuqoPuqoLadderSetOldbEntity e3 : ladderOldList) {
                        e3.setId(null);
                        e3.setPid(pid);
                        e3.setClassName("puquoteorder");
                        ormService.insertSelective(e3);
                    }
                }
            }
        }
    }

    /**
     * 不加事务注解，由save和提交流程两个方法分别加注解
     */
    @Override
    public Result submitPuquoteOrder(PuquoteOrderDTO params) {
        PuquoteorderEntity entity = JSON.parseObject(JSON.toJSONString(params), PuquoteorderEntity.class);
        String paramsOrderId = entity.getId();
        // 提交校验
        Result validResult = validSaveOrSubmit(entity);
        if (validResult.getRetCode().equals(Result.RECODE_VALIDATE_ERROR)) {
            validResult.setErrMsg(validResult.getErrMsg() + ",当前临时单提交失败！");
            return validResult;
        }
        formateDateNoTime(entity);
        Result saveResult = insertData(entity);
        if (saveResult.getRetCode().equals(Result.RECODE_SUCCESS)) {
            //上述saveResult 如果有值一定是success的，否则ApplicationException是runtimeException，后面代码不会执行
            JSONObject saveData = (JSONObject) saveResult.getData();
            // 单据id
            String orderId = saveData.getString(NodeConstant.ID);
            // 单据定义id
            String orderRodeObj = saveData.getString(OrderConstants.ORDE_RODE_OBJ_UPCASE);
            // 提交流程和更新单据状态
            String submitNbr = submitFlow(orderId, orderRodeObj, StringUtil.isNullOrEmpty(paramsOrderId));
            saveData.put(OrderConstants.ORDE_NBR_UPCASE, submitNbr);
            saveResult.setErrMsg("提交单据成功！");
        }
        return saveResult;
    }

    /**
     * 校验物品状态，供应商状态，为IA或关闭时，提示’**状态为**，不可提交！
     */
    private void validTwoStep(List<String> errMsgList, int i, String goodsId, String suppId, String parkId) throws Exception {

        OrmParamEx ormParamEx = new OrmParamEx();
        ormParamEx.addColumn(GodsGodsParkSetaProperty.GODS_STATUS).addColumn(RelaRelaSupplierSetaProperty.RELA_STAT_SUPP);

        ormParamEx.leftJoin(GodsGodsParkSetaEntity.class,
                OrmParamEx.joinLinkInDifferentTable(GodsGodsParkSetaEntity.class, NodeConstant.PID, GoodsEntity.class, NodeConstant.ID), DataVailidEnum.NOMATTER);
        ormParamEx.leftJoin(RelaRelaSupplierSetaEntity.class,
                OrmParamEx.joinLinkInDifferentTable(RelaRelaSupplierSetaEntity.class, NodeConstant.PID, GodsGodsParkSetaEntity.class, GodsGodsParkSetaProperty.GODS_SUPPLIER), DataVailidEnum.NOMATTER);

        ormParamEx.setWhereExp(OrmParam.and(ormParamEx.getEqualXML(OrmParamEx.column(GoodsEntity.class, "is_del"), 0),
                ormParamEx.getEqualXML(OrmParamEx.column(GoodsEntity.class, NodeConstant.ID), goodsId),
                ormParamEx.getEqualXML(OrmParamEx.column(GodsGodsParkSetaEntity.class, GodsGodsParkSetaProperty.GODS_PARK), parkId),
                ormParamEx.getEqualXML(OrmParamEx.column(GodsGodsParkSetaEntity.class, GodsGodsParkSetaProperty.GODS_SUPPLIER), suppId)
        ));

        List<Map<String, Object>> list = ormService.selectMapListEx(GoodsEntity.class, ormParamEx);

        if (null != list && list.size() > 0) {
            Map<String, Object> map = list.get(0);
            String godsStatus = NullUtils.valueOf(map.get(GodsGodsParkSetaProperty.GODS_STATUS));
            String suppStatus = NullUtils.valueOf(map.get(RelaRelaSupplierSetaProperty.RELA_STAT_SUPP));
            if (StringUtil.isNullOrEmpty(godsStatus) || godsStatus.equals(GoodsStatus.IA)) {
                errMsgList.add("第" + (i + 1) + "行数据物品状态无效");
            }
            if (StringUtil.isNullOrEmpty(suppStatus) || suppStatus.equals("3")) {
                errMsgList.add("第" + (i + 1) + "行数据供应商状态为关闭或无效");
            }
        } else {
            errMsgList.add("第" + (i + 1) + "行数据无效,请检查供应商，园区，物品是否关联并有效");
        }
    }


    /**
     * 生效和失效日期校验：
     * 1、生效和失效日期校验：生效日期不能大于或等于失效日期，且不能小于当天，否则提示：第**行信息输入有误，不可提交！（存在多行数据时以逗号隔开)
     */
    private void validOneStepDate(List<String> errMsgList, Date nowDate, int i, PuqoPuqoPriceSetaEntity e1) throws Exception {
        boolean baseNotNullVaild = true;
        boolean isLadderNull = false;
        // 价格
        BigDecimal price = e1.getPuqo_price();
        Integer isLadderNew = e1.getPuqo_isladder();
        Integer isLadderOld = e1.getPuqo_isladder_old();
        if (null == isLadderNew) {
            errMsgList.add("第" + (i + 1) + "行信息有误，请设置是否是阶梯报价");
            isLadderNull = true;
        } else if (null == isLadderOld) {
            isLadderOld = INT_0;
        } else {
            if (!(isLadderNew.equals(INT_0) || isLadderNew.equals(INT_1))) {
                errMsgList.add("第" + (i + 1) + "行信息有误，请设置（新）有效阶梯报价值0或者1");
            } else if (!(isLadderOld.equals(INT_0) || isLadderOld.equals(INT_1))) {
                errMsgList.add("第" + (i + 1) + "行信息有误，请设置（旧）有效阶梯报价值0或者1");
            }
        }
        if (!isLadderNull) {
            if (isLadderNew.equals(INT_1)) {
                if (null == price) {
                    errMsgList.add("第" + (i + 1) + "行信息有误，请设置单价(新);");
                    baseNotNullVaild = false;
                }
            }

            //生效日期
            Date begDate = e1.getPuqo_date_beg();
            Date endDate = e1.getPuqo_date_end();
            if (null == endDate) {
                endDate = NullUtils.nullEndDate();
            } else {
                endDate = NullUtils.formatDateNoTime(endDate);
            }

            if (null == begDate) {
                errMsgList.add("第" + (i + 1) + "行信息有误，生效日期未设置;");
                baseNotNullVaild = false;
            }
            if (null != begDate) {
                begDate = NullUtils.formatDateNoTime(begDate);
            }
            if (baseNotNullVaild) {
                if (isLadderNew.equals(INT_0) && isLadderOld.equals(INT_0)) {

                } else if (isLadderNew.equals(INT_1) && isLadderOld.equals(INT_1)) {
                    List<PuqoPuqoLadderSetbEntity> list1 = e1.getPuqo_ladder_set();
                    List<PuqoPuqoLadderSetOldbEntity> list2 = e1.getPuqo_ladder_set_old();
                    if (list1.size() < 2 || list2.size() < 2) {
                        errMsgList.add("第" + (i + 1) + "行信息有误，阶梯报价个数至少是2个;");
                    } else if (list1.size() >= 2 && list2.size() >= 2) {
                        // 个数
                        Map<BigDecimal, Integer> mapMax1 = new HashMap<>();
                        Map<BigDecimal, Integer> mapMax2 = new HashMap<>();
                        Map<BigDecimal, Integer> mapMin1 = new HashMap<>();
                        Map<BigDecimal, Integer> mapMin2 = new HashMap<>();
                        // ---------------新------------------------------------
                        for (PuqoPuqoLadderSetbEntity a : list1) {
                            BigDecimal b1 = a.getPuqo_lprice();
                            BigDecimal b3 = a.getPuqo_oqty_max();
                            BigDecimal b4 = a.getPuqo_oqty_min();
                            if (null == b1 || null == b3 || null == b4) {
                                errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(新)，有必要数据未设置，请检查;");
                            } else {
                                boolean isValid = true;
                                if (b1.compareTo(BIG_0) < 0) {
                                    errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(新)，单价小于0;");
                                    isValid = false;
                                }
                                if (b3.compareTo(BIG_0) < 0 || b3.compareTo(BIG_99) > 0) {
                                    errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(新)，订单量（大）无效;");
                                    isValid = false;
                                }
                                if (b4.compareTo(BIG_0) < 0 || b4.compareTo(BIG_99) > 0) {
                                    errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(新)，订单量（小）无效;");
                                    isValid = false;
                                }
                                if (isValid) {
                                    putMap(mapMax1, b3);
                                    putMap(mapMin1, b4);
                                }
                            }
                        }
                        if (mapMax1.size() > 0) {
                            if (mapMax1.containsKey(BIG_99)) {
                                int a = mapMax1.get(BIG_99);
                                if (a > 1) {
                                    errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(新)，999,999,999有且仅有一个;");
                                }
                                Set<BigDecimal> set1 = mapMax1.keySet();
                                for (BigDecimal b1 : set1) {
                                    int a1 = mapMax1.get(b1);
                                    if (a1 > 1) {
                                        errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(新)，订单量（大）" + b1 + "的值不能有多个;");
                                    }
                                }
                            } else {
                                errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(新)，最后一档请设置为999,999,999;");
                            }
                        }
                        if (mapMin1.size() > 0) {
                            if (mapMin1.containsKey(BIG_99)) {
                                errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(新)，订单量（小）不能设置为999,999,999;");
                            } else {
                                Set<BigDecimal> set1 = mapMin1.keySet();
                                for (BigDecimal b1 : set1) {
                                    int a1 = mapMin1.get(b1);
                                    if (a1 > 1) {
                                        errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(新)，订单量（小）" + b1 + "的值不能有多个;");
                                    }
                                }
                            }
                        }
                        // ---------------旧------------------------------------
                        for (PuqoPuqoLadderSetOldbEntity a : list2) {
                            BigDecimal b1 = a.getPuqo_lprice_old();
                            BigDecimal b2 = a.getPuqo_oqty_max_old();
                            BigDecimal b3 = a.getPuqo_oqty_min_old();
                            if (null == b1 || null == b2 || null == b3) {
                                errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(旧)，有必要数据未设置，请检查;");
                            } else {
                                boolean isValid = true;
                                if (b1.compareTo(BIG_0) < 0) {
                                    errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(旧)，单价小于0;");
                                    isValid = false;
                                }
                                if (b2.compareTo(BIG_0) < 0 || b2.compareTo(BIG_99) > 0) {
                                    errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(旧)，订单量（大）无效;");
                                    isValid = false;
                                }
                                if (b3.compareTo(BIG_0) < 0 || b3.compareTo(BIG_99) > 0) {
                                    errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(旧)，订单量（小）无效;");
                                    isValid = false;
                                }
                                if (isValid) {
                                    putMap(mapMax2, b2);
                                    putMap(mapMin2, b3);
                                }
                            }
                        }
                        if (mapMax2.size() > 0) {
                            if (mapMax2.containsKey(BIG_99)) {
                                int a = mapMax2.get(BIG_99);
                                if (a > 1) {
                                    errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(旧)，999,999,999有且仅有一个;");
                                }
                                Set<BigDecimal> set1 = mapMax2.keySet();
                                for (BigDecimal b1 : set1) {
                                    int a1 = mapMax2.get(b1);
                                    if (a1 > 1) {
                                        errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(旧)，订单量（大）" + b1 + "的值不能有多个;");
                                    }
                                }
                            } else {
                                errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(旧)，最后一档请设置为999,999,999;");
                            }
                        }
                        if (mapMin2.size() > 0) {
                            if (mapMin2.containsKey(BIG_99)) {
                                errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(旧)，订单量（小）不能设置为999,999,999;");
                            } else {
                                Set<BigDecimal> set1 = mapMin2.keySet();
                                for (BigDecimal b1 : set1) {
                                    int a1 = mapMin2.get(b1);
                                    if (a1 > 1) {
                                        errMsgList.add("第" + (i + 1) + "行数据中阶梯报价集(旧)，订单量（小）" + b1 + "的值不能有多个;");
                                    }
                                }
                            }
                        }
                    }
                }
                // 判断日期是否有效
                if (begDate.compareTo(endDate) <= 0 && begDate.compareTo(nowDate) < 0) {
                    errMsgList.add("第" + (i + 1) + "行信息有误，生效日期不能大于或等于失效日期,且不能小于当天;");
                }
            }
        }
    }

    private void putMap(Map<BigDecimal, Integer> map, BigDecimal b) {
        if (map.containsKey(b)) {
            map.put(b, map.get(b) + 1);
        } else {
            map.put(b, 1);
        }
    }

    private void validFiveStep(List<String> errMsgList, PuquoteorderEntity entity) {
        List<PuqoPuqoPriceSetaEntity> priceSetaEntities = entity.getPuqo_price_set();
        for (int i = 0; i < priceSetaEntities.size(); i++) {
            PuqoPuqoPriceSetaEntity e1 = priceSetaEntities.get(i);
            for (int j = i + 1; j < priceSetaEntities.size(); j++) {
                PuqoPuqoPriceSetaEntity e2 = priceSetaEntities.get(j);
                if (null != e1.getPuqo_goods() && null != e1.getPuqo_park()) {
                    if (e1.getPuqo_goods().equals(e2.getPuqo_goods())) {
                        if (e1.getPuqo_park().equals(e2.getPuqo_park())) {
                            errMsgList.add("第" + (i + 1) + "，" + (j + 1) + "行存在重复提交的报价");
                        }
                    }
                }
            }
        }
    }

    private void validSixStep(List<String> errMsgList, PuquoteorderEntity entity) throws Exception {

        String suppId = entity.getPuqo_code_supp();
        String currId = entity.getPuqo_curr();
        if (!(StringUtil.isNullOrEmpty(suppId) || StringUtil.isNullOrEmpty(currId))) {
            List<PuqoPuqoPriceSetaEntity> priceSetaEntities = entity.getPuqo_price_set();
            for (int i = 0; i < priceSetaEntities.size(); i++) {
                PuqoPuqoPriceSetaEntity e1 = priceSetaEntities.get(i);
                String goodsId = e1.getPuqo_goods();
                String parkId = e1.getPuqo_park();
                if (!(StringUtil.isNullOrEmpty(goodsId) || StringUtil.isNullOrEmpty(parkId))) {

                    OrmParamEx ormParamEx = new OrmParamEx();
                    ormParamEx.addColumn(OrderProperty.ORDE_NBR);

                    ormParamEx.leftJoin(PuqoPuqoPriceSetaEntity.class,
                            OrmParamEx.joinLinkInDifferentTable(PuqoPuqoPriceSetaEntity.class, NodeConstant.PID, PuquoteorderEntity.class, NodeConstant.ID)
                            , DataVailidEnum.NOMATTER);

                    ormParamEx.setWhereExp(OrmParam.and(ormParamEx.getNotInXML(OrderProperty.ORDE_STATUS, new String[]{"1", "5", "6"}),
                            ormParamEx.getEqualXML(PuquoteorderProperty.PUQO_CODE_SUPP, suppId),
                            ormParamEx.getEqualXML(PuquoteorderProperty.PUQO_CURR, currId),
                            ormParamEx.getEqualXML(PuqoPuqoPriceSetaProperty.PUQO_GOODS, goodsId),
                            ormParamEx.getEqualXML(PuqoPuqoPriceSetaProperty.PUQO_PARK, parkId),
                            ormParamEx.getEqualXML(OrmParamEx.column(PuquoteorderEntity.class, "is_del"), 0),
                            ormParamEx.getEqualXML(OrmParamEx.column(PuqoPuqoPriceSetaEntity.class, "is_del"), 0)
                    ));

                    List<Map<String, Object>> list = ormService.selectMapListEx(PuquoteorderEntity.class, ormParamEx);

                    if (null != list && list.size() > 0) {
                        String nbr = NullUtils.valueOf(list.get(0).get(OrderProperty.ORDE_NBR));
                        errMsgList.add("第" + (i + 1) + "行存在待审单据,单号：" + nbr);
                    }
                }
            }
        }
    }


    /**
     * 注意此处 调用流程事务和 执行更新的本地服务，所以加上 @TxTransaction 注解
     */
    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public String submitFlow(String ordeId, String orderRodeObj, boolean errorRollBack) {
        try {
            // TODO 提交流程，调用流程服务
            // 提交流程，调用流程服务
            bizFormService.submitWorkFlow(orderRodeObj, ordeId);
            // 更改单据状态
            PuquoteorderEntity entity = new PuquoteorderEntity();
            String nbr = commonService.getCode(NumberConstants.PREFIX_PUQUOTE_ORDER);
            entity.setOrde_nbr(nbr);
            entity.setOrde_status(OrderConstants.ORDE_STATUS_2);
            entity.setId(ordeId);
            ormService.updateSelective(entity);
            return nbr;
        } catch (Exception e) {
            try {
                if (errorRollBack) {
                    // 如果提交流程出错，如果提交保存的数据没有单据id，说明是新插入的，手动删除，相当于回滚。
                    ormService.delete(PuquoteorderEntity.class, ordeId);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }

            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Result loadOrder(String id, boolean isLoadParks) {
        Result result = new Result();
        JSONObject returnData = new JSONObject();
        try {
            JSONObject orderBaseData = getOrderBaseData(id);
            if (null == orderBaseData) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg("未找到单据 '" + id + "'" + "相关信息");
                return result;
            }
            // 单据基本信息
            returnData.putAll(orderBaseData);
            if (isLoadParks) {
                String suppId = orderBaseData.getString(PuquoteorderProperty.PUQO_CODE_SUPP);
                String taxrateId = orderBaseData.getString(PuquoteorderProperty.PUQO_TAX);
                String currId = orderBaseData.getString(PuquoteorderProperty.PUQO_CURR);
                JSONObject orderSetData = getOrderSetDataWithPark(id, suppId, currId, taxrateId);
                // 单据属性集信息
                returnData.putAll(orderSetData);
            } else {
                JSONObject orderSetData = getOrderSetData(id);
                // 单据属性集信息
                returnData.putAll(orderSetData);
            }
            JSONArray array = returnData.getJSONArray(PuquoteorderProperty.PUQO_PRICE_SET);
            for (Object o : array) {
                JSONObject jo = (JSONObject) o;
                // 币别code
                jo.put(CurrencyProperty.CURR_CODE, NullUtils.valueOf(returnData.get(CurrencyProperty.CURR_CODE)));
                // 币别name
                jo.put(CurrencyProperty.CURR_NAME, NullUtils.valueOf(returnData.get(CurrencyProperty.CURR_NAME)));
            }

            JsonUtils.underLine2Camel(returnData);
            result.setErrMsg("加载物品采购报价单数据成功！");
            result.setData(returnData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    /**
     * 得到单据 基本信息
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    protected JSONObject getOrderBaseData(String orderId) throws Exception {
        OrmParamEx ormParamEx = new OrmParamEx();
        String orderColumnId = OrmParamEx.column(PuquoteorderEntity.class, NodeConstant.ID);
        ormParamEx.addColumn(orderColumnId)
                .addColumn(OrderProperty.ORDE_ADDUSER)
                .addColumn(OrderProperty.ORDE_DATE)
                .addColumn(OrderProperty.ORDE_DEPT)
                .addColumn(OrderProperty.ORDE_DUTY)
                .addColumn(OrderProperty.ORDE_STATUS)
                .addColumn(OrderProperty.ORDE_NBR)
                .addColumn(OrderProperty.ORDE_RODE_OBJ)
                .addColumn(OrderProperty.ORDE_PROCOBJ)
                .addColumn(EmployeeProperty.REMP_NO)
                .addColumn(EmployeeProperty.REMP_NAME)
                .addColumn(RelationProperty.RELA_CODE)
                .addColumn(DepttreeProperty.MDEP_NAME)
                .addColumn(JobpositionProperty.RPOS_NAME)
                .addColumn(PuquoteorderProperty.PUQO_CODE_SUPP)
                .addColumn(PuquoteorderProperty.PUQO_SNAME_SUPP)
                .addColumn(PuquoteorderProperty.PUQO_CURR)
                .addColumn(CurrencyProperty.CURR_NAME)
                .addColumn(CurrencyProperty.CURR_CODE)
                .addColumn(PuquoteorderProperty.PUQO_TAX)
                .addColumn(TaxrateProperty.TAXR_NAME)
                .addColumn(PuquoteorderProperty.PUQO_REMARK);

        ormParamEx
                .leftJoin(EmployeeEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (EmployeeEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_ADDUSER), DataVailidEnum.NOMATTER)
                .leftJoin(DepttreeEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (DepttreeEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_DEPT), DataVailidEnum.NOMATTER)
                .leftJoin(JobpositionEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (JobpositionEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_DUTY), DataVailidEnum.NOMATTER)
                .leftJoin(CurrencyEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (CurrencyEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_CURR), DataVailidEnum.NOMATTER)
                .leftJoin(TaxrateEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (TaxrateEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_TAX), DataVailidEnum.NOMATTER)
                .leftJoin(RelationEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (RelationEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_CODE_SUPP), DataVailidEnum.NOMATTER)
        ;

        ormParamEx.setWhereExp(OrmParamEx.and(
                ormParamEx.getEqualXML(orderColumnId, orderId), ormParamEx.getEqualXML(OrmParamEx.column(PuquoteorderEntity.class, "is_del"), "0")
        ));
        List<Map<String, Object>> list = ormService.selectMapListEx(PuquoteorderEntity.class, ormParamEx);
        if (null != list && list.size() > 0) {
            JSONObject data = new JSONObject();
            Map<String, Object> map = list.get(0);
            // 单据id
            data.put(NodeConstant.ID, NullUtils.valueOf(map.get(orderColumnId.replaceAll("\\.", "_"))));
            // 申请人id
            String employeeId = NullUtils.valueOf(map.get(OrderProperty.ORDE_ADDUSER));
            data.put(OrderProperty.ORDE_ADDUSER, employeeId);
            // 申请部门id
            String deptId = NullUtils.valueOf(map.get(OrderProperty.ORDE_DEPT));
            data.put(OrderProperty.ORDE_DEPT, deptId);
            // 申请岗位id
            String postId = NullUtils.valueOf(map.get(OrderProperty.ORDE_DUTY));
            data.put(OrderProperty.ORDE_DUTY, postId);
            // 单据单号
            data.put(OrderProperty.ORDE_NBR, NullUtils.valueOf(map.get(OrderProperty.ORDE_NBR)));
            // 单据状态
            data.put(OrderProperty.ORDE_STATUS, NullUtils.valueOf(map.get(OrderProperty.ORDE_STATUS)));
            // 单据定义id
            data.put(OrderProperty.ORDE_RODE_OBJ, NullUtils.valueOf(map.get(OrderProperty.ORDE_RODE_OBJ)));
            // 流程实例id
            data.put(OrderProperty.ORDE_PROCOBJ, NullUtils.valueOf(map.get(OrderProperty.ORDE_PROCOBJ)));
            // 供应商id
            data.put(PuquoteorderProperty.PUQO_CODE_SUPP, NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_CODE_SUPP)));
            // 供应商简称
            data.put(PuquoteorderProperty.PUQO_SNAME_SUPP, NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_SNAME_SUPP)));
            // 供应商编号
            data.put(RelationProperty.RELA_CODE, NullUtils.valueOf(map.get(RelationProperty.RELA_CODE)));
            // 币别id
            String currencyId = NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_CURR));
            data.put(PuquoteorderProperty.PUQO_CURR, currencyId);
            // 税率id
            String taxId = NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_TAX));
            data.put(PuquoteorderProperty.PUQO_TAX, taxId);
            // 备注
            data.put(PuquoteorderProperty.PUQO_REMARK, NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_REMARK)));
            // 申请单据日期
            Object ordeDateStr = map.get(OrderProperty.ORDE_DATE);
            if (!StringUtil.isNullOrEmpty(ordeDateStr)) {
                Timestamp ordeDate = (Timestamp) ordeDateStr;
                data.put(OrderProperty.ORDE_DATE, DateUtil.formatDate(new Date(ordeDate.getTime())));
            } else {
                data.put(OrderProperty.ORDE_DATE, "");
            }
            // 申请人工号
            data.put(EmployeeProperty.REMP_NO, NullUtils.valueOf(map.get(EmployeeProperty.REMP_NO)));
            // 申请人名字
            data.put(EmployeeProperty.REMP_NAME, NullUtils.valueOf(map.get(EmployeeProperty.REMP_NAME)));
            //  申请部门名称
            data.put(DepttreeProperty.MDEP_NAME, NullUtils.valueOf(map.get(DepttreeProperty.MDEP_NAME)));
            // 申请岗位名称
            data.put(JobpositionProperty.RPOS_NAME, NullUtils.valueOf(map.get(JobpositionProperty.RPOS_NAME)));
            // 币别code
            data.put(CurrencyProperty.CURR_CODE, NullUtils.valueOf(map.get(CurrencyProperty.CURR_CODE)));
            // 币别name
            data.put(CurrencyProperty.CURR_NAME, NullUtils.valueOf(map.get(CurrencyProperty.CURR_NAME)));
            // 税率名称
            data.put(TaxrateProperty.TAXR_NAME, NullUtils.valueOf(map.get(TaxrateProperty.TAXR_NAME)));

            return data;
        }
        return null;
    }

    /**
     * 得到 采购单据 的属性集 即价格异动明细
     *
     * @param orderId
     * @return
     * @throws Exception
     */
    protected JSONObject getOrderSetDataWithPark(String orderId, String suppId, String currId, String taxrateId) throws Exception {
        JSONObject returnData = getOrderSetData(orderId);
        JSONArray array = returnData.getJSONArray(PuquoteorderProperty.PUQO_PRICE_SET);
        for (Object o : array) {
            JSONObject jo = (JSONObject) o;
            String goodsId = jo.getString(PuqoPuqoPriceSetaProperty.PUQO_GOODS);
            JSONObject re = loadParksData(orderId, suppId, currId, taxrateId, goodsId);
            jo.putAll(re);
        }
        return returnData;
    }

    protected JSONObject getOrderSetData(String orderId) throws Exception {

        OrmParamEx ormParamEx = new OrmParamEx();
        List<String> columns = new ArrayList<>();
        List<String> columnsSetb = new ArrayList<>();
        List<String> columnsSetOldb = new ArrayList<>();

        columnsSetb.add(PuqoPuqoLadderSetbProperty.PUQO_LUPDOWN_RATE);
        columnsSetb.add(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MAX);
        columnsSetb.add(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MIN);
        columnsSetb.add(PuqoPuqoLadderSetbProperty.PUQO_LPRICE);

        columnsSetOldb.add(PuqoPuqoLadderSetOldbProperty.PUQO_LPRICE_OLD);
        columnsSetOldb.add(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MIN_OLD);
        columnsSetOldb.add(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MAX_OLD);

        String columnId = OrmParamEx.column(PuqoPuqoPriceSetaEntity.class, NodeConstant.ID);
        columns.add(columnId);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG_OLD);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER_OLD);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_GOODS);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_UPDOWN_RATE);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_REBATE_OLD);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_PRICE);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_REBATE);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_END_OLD);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_PARK);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_END);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_UM_GROUP);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_UM);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_PRICE_OLD);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_ISTATUS);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG_OLD);
        columns.add(GoodsProperty.GODS_CODE);
        columns.add(GoodsProperty.GODS_NAME);
        columns.add(GoodsProperty.GODS_MODEL);
        columns.add(ParkProperty.RPAK_CODE);
        columns.add(ParkProperty.RPAK_NAME);
        ormParamEx.setColumns(columns);

        ormParamEx
                .leftJoin(GoodsEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (GoodsEntity.class, NodeConstant.ID, PuqoPuqoPriceSetaEntity.class, PuqoPuqoPriceSetaProperty.PUQO_GOODS), DataVailidEnum.NOMATTER)
                .leftJoin(ParkEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (ParkEntity.class, NodeConstant.ID, PuqoPuqoPriceSetaEntity.class, PuqoPuqoPriceSetaProperty.PUQO_PARK), DataVailidEnum.NOMATTER);

        ormParamEx.setWhereExp(OrmParamEx.and(
                ormParamEx.getEqualXML(
                        OrmParamEx.column(PuqoPuqoPriceSetaEntity.class, NodeConstant.PID), orderId),
                ormParamEx.getEqualXML(
                        OrmParamEx.column(PuqoPuqoPriceSetaEntity.class, "is_del"), "0")
        ));

        ormParamEx.setOrderExp(SQLSortEnum.ASC, GoodsProperty.GODS_CODE);

        List<Map<String, Object>> list = ormService.selectMapListEx(PuqoPuqoPriceSetaEntity.class, ormParamEx);

        JSONObject returnData = new JSONObject();
        JSONArray array = new JSONArray();
        if (null != list && list.size() > 0) {
            for (Map<String, Object> map : list) {
                JSONObject data = new JSONObject();
                String id = NullUtils.valueOf(map.get(columnId.replaceAll("\\.", "_")));
                // 属性集主键id
                data.put(NodeConstant.ID, id);
                // 物品id
                String goodsId = NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_GOODS));
                data.put(PuqoPuqoPriceSetaProperty.PUQO_GOODS, goodsId);
                // 物品编号// 物品名称// 物品规格
                // 物品编号
                String goodsCode = NullUtils.valueOf(map.get(GoodsProperty.GODS_CODE));
                data.put(GoodsProperty.GODS_CODE, goodsCode);
                // 物品名称
                data.put(GoodsProperty.GODS_NAME, NullUtils.valueOf(map.get(GoodsProperty.GODS_NAME)));
                // 物品规格
                data.put(GoodsProperty.GODS_MODEL, NullUtils.valueOf(map.get(GoodsProperty.GODS_MODEL)));
                // 园区id
                String parkId = NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_PARK));
                data.put(PuqoPuqoPriceSetaProperty.PUQO_PARK, parkId);
                // 园区名称// 园区编码
                // 园区名称
                data.put(ParkProperty.RPAK_NAME, NullUtils.valueOf(map.get(ParkProperty.RPAK_NAME)));
                // 园区编码
                data.put(ParkProperty.RPAK_CODE, NullUtils.valueOf(map.get(ParkProperty.RPAK_CODE)));
                // 单位
                data.put(PuqoPuqoPriceSetaProperty.PUQO_UM, NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_UM)));
                // 涨跌率
                data.put(PuqoPuqoPriceSetaProperty.PUQO_UPDOWN_RATE, NullUtils.doubleValueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_UPDOWN_RATE)));
                // 单价
                data.put(PuqoPuqoPriceSetaProperty.PUQO_PRICE, NullUtils.doubleValueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_PRICE)));
                // 返利额
                data.put(PuqoPuqoPriceSetaProperty.PUQO_REBATE, NullUtils.doubleValueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_REBATE)));
                // 生效日期
                Object str = map.get(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG);
                if (!StringUtil.isNullOrEmpty(str)) {
                    Timestamp date = (Timestamp) str;
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG, DateUtil.formatDate(new Date(date.getTime())));
                } else {
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG, "");
                }
                //失效日期
                str = map.get(PuqoPuqoPriceSetaProperty.PUQO_DATE_END);
                if (!StringUtil.isNullOrEmpty(str)) {
                    Timestamp date = (Timestamp) str;
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_END, DateUtil.formatDate(new Date(date.getTime())));
                } else {
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_END, "");
                }
                // 是否阶梯报价
                String isLadder = NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER));
                data.put(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER, isLadder);
                // 单价_旧
                data.put(PuqoPuqoPriceSetaProperty.PUQO_PRICE_OLD, NullUtils.doubleValueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_PRICE_OLD)));
                // 返利额_旧
                data.put(PuqoPuqoPriceSetaProperty.PUQO_REBATE_OLD, NullUtils.doubleValueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_REBATE_OLD)));
                // 生效日期_旧
                str = map.get(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG_OLD);
                if (!StringUtil.isNullOrEmpty(str)) {
                    Timestamp date = (Timestamp) str;
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG_OLD, DateUtil.formatDate(new Date(date.getTime())));
                } else {
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG_OLD, "");
                }
                //失效日期_旧
                str = map.get(PuqoPuqoPriceSetaProperty.PUQO_DATE_END_OLD);
                if (!StringUtil.isNullOrEmpty(str)) {
                    Timestamp date = (Timestamp) str;
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_END_OLD, DateUtil.formatDate(new Date(date.getTime())));
                } else {
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_END_OLD, "");
                }
                // 是否阶梯报价_旧
                String isLadderOld = NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER_OLD));
                data.put(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER_OLD, isLadderOld);
                // 审批状态
                data.put(PuqoPuqoPriceSetaProperty.PUQO_ISTATUS, NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_ISTATUS)));

                // 阶梯报价属性集
                JSONArray array1 = new JSONArray();
                JSONArray arrayOld1 = new JSONArray();
                if (isLadder.equals("1") || isLadderOld.equals("1")) {
                    OrmParam ormParam = new OrmParam();
                    ormParam.setColumns(columnsSetb);
                    ormParam.setWhereExp(OrmParamEx.and(ormParam.getEqualXML(NodeConstant.PID, id)));
                    ormParam.setOrderExp(SQLSortEnum.ASC, PuqoPuqoLadderSetbProperty.PUQO_OQTY_MAX);
                    List<Map<String, Object>> mapList = ormService.selectMapList(PuqoPuqoLadderSetbEntity.class, ormParam);
                    for (Map<String, Object> map1 : mapList) {
                        JSONObject o = new JSONObject();
                        // 单价
                        o.put(PuqoPuqoLadderSetbProperty.PUQO_LPRICE, NullUtils.doubleValueOf(map1.get(PuqoPuqoLadderSetbProperty.PUQO_LPRICE)));
                        // 阶梯订单量小值
                        o.put(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MIN, NullUtils.intValueOf(map1.get(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MIN)));
                        // 阶梯订单量大值
                        o.put(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MAX, NullUtils.intValueOf(map1.get(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MAX)));
                        // 涨跌率
                        o.put(PuqoPuqoLadderSetbProperty.PUQO_LUPDOWN_RATE, NullUtils.doubleValueOf(map1.get(PuqoPuqoLadderSetbProperty.PUQO_LUPDOWN_RATE)));
                        array1.add(o);
                    }
                    ormParam.reset();
                    ormParam.setColumns(columnsSetOldb);
                    ormParam.setWhereExp(OrmParamEx.and(ormParam.getEqualXML(NodeConstant.PID, id)));
                    ormParam.setOrderExp(SQLSortEnum.ASC, PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MAX_OLD);
                    mapList = ormService.selectMapList(PuqoPuqoLadderSetOldbEntity.class, ormParam);
                    for (Map<String, Object> map1 : mapList) {
                        JSONObject o = new JSONObject();
                        // 单价
                        o.put(PuqoPuqoLadderSetOldbProperty.PUQO_LPRICE_OLD, NullUtils.doubleValueOf(map1.get(PuqoPuqoLadderSetOldbProperty.PUQO_LPRICE_OLD)));
                        // 阶梯订单量小值
                        o.put(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MIN_OLD, NullUtils.intValueOf(map1.get(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MIN_OLD)));
                        // 阶梯订单量大值
                        o.put(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MAX_OLD, NullUtils.intValueOf(map1.get(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MAX_OLD)));
                        arrayOld1.add(o);
                    }
                }
                data.put(PuqoPuqoPriceSetaProperty.PUQO_LADDER_SET, array1);
                data.put(PuqoPuqoPriceSetaProperty.PUQO_LADDER_SET_OLD, arrayOld1);
                array.add(data);
            }
        }
        returnData.put(PuquoteorderProperty.PUQO_PRICE_SET, array);
        return returnData;
    }

    private JSONObject loadParksData(String orderId, String supplierId, String currencyId, String taxrateId, String goodsId) throws Exception {
        List<SimplePuqoPriceSetDTO> list = goodsService.getParkAndPrice(orderId, supplierId, currencyId, taxrateId, goodsId);
        JSONObject returnData = new JSONObject();
        JSONArray array = JSON.parseArray(JSONArray.toJSONString(list));
        returnData.put("selectableParks", array);
        return returnData;
    }

    /**
     * 变更历史；报价单查询页面
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Result queryPuquOrders(PuqoOrderQueryDTO queryDTO) {

        try {
            OrmParamEx ormParamEx = new OrmParamEx();
            String columnId = OrmParamEx.column(PuquoteorderEntity.class, NodeConstant.ID);
            ormParamEx.addColumn(columnId)
                    .addColumn(OrderProperty.ORDE_NBR).addColumn(OrderProperty.ORDE_ADDUSER).addColumn(OrderProperty.ORDE_RODE_OBJ)
                    .addColumn(EmployeeProperty.REMP_NAME).addColumn(OrderProperty.ORDE_STATUS).addColumn(OrderProperty.ORDE_PROCOBJ)
                    .addColumn(OrderProperty.ORDE_DATE).addColumn(PuquoteorderProperty.PUQO_REMARK)
                    .addColumn(PuquoteorderProperty.PUQO_CURR)
                    .addColumn(CurrencyProperty.CURR_CODE).addColumn(CurrencyProperty.CURR_NAME)
                    .addColumn(PuquoteorderProperty.PUQO_CODE_SUPP).addColumn(PuquoteorderProperty.PUQO_SNAME_SUPP)
                    .addColumn(RelationProperty.RELA_CODE);

            ormParamEx.leftJoin(RelationEntity.class,
                    OrmParamEx.joinLinkInDifferentTable(RelationEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_CODE_SUPP), DataVailidEnum.NOMATTER);
            ormParamEx.leftJoin(EmployeeEntity.class,
                    OrmParamEx.joinLinkInDifferentTable(EmployeeEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_ADDUSER), DataVailidEnum.NOMATTER);
            ormParamEx.leftJoin(CurrencyEntity.class,
                    OrmParamEx.joinLinkInDifferentTable(CurrencyEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_CURR), DataVailidEnum.NOMATTER);

            List<String> conditions = new ArrayList<>();
            // is_del=0
            conditions.add(ormParamEx.getEqualXML(OrmParamEx.column(PuquoteorderEntity.class, "is_del"), 0));
            //单据单号 模糊查询关键字
            String orderNbrKey = queryDTO.getOrderNbrKey();
            if (!StringUtil.isNullOrEmpty(orderNbrKey)) {
                conditions.add(ormParamEx.getMatchMiddleXML(OrderProperty.ORDE_NBR, orderNbrKey));
            }
            // 供应商模糊查询关键字
            String suppKey = queryDTO.getSuppKey();
            if (!StringUtil.isNullOrEmpty(suppKey)) {
                conditions.add(OrmParamEx.or(ormParamEx.getMatchMiddleXML(RelationProperty.RELA_CODE, suppKey), ormParamEx.getMatchMiddleXML(PuquoteorderProperty.PUQO_SNAME_SUPP, suppKey)));
            }
            //申请时间开始日期
            String startTime = queryDTO.getStartTime();
            if (!StringUtil.isNullOrEmpty(startTime)) {
                conditions.add(ormParamEx.getGreaterThanAndEqualXML(OrderProperty.ORDE_DATE, NullUtils.convertStartTime(startTime)));
            }
            // 申请时间结束时间
            String endTime = queryDTO.getEndTime();
            if (!StringUtil.isNullOrEmpty(endTime)) {
                conditions.add(ormParamEx.getLessThanAndEqualXML(OrderProperty.ORDE_DATE, NullUtils.convertEndTime(endTime)));
            }
            // 报价单状态
            String ordeStatus = queryDTO.getOrdeStatus();
            if (!StringUtil.isNullOrEmpty(ordeStatus)) {
                conditions.add(ormParamEx.getEqualXML(OrderProperty.ORDE_STATUS, ordeStatus));
            }
            //币别id
            String currId = queryDTO.getCurrenyId();
            if (!StringUtil.isNullOrEmpty(currId)) {
                conditions.add(ormParamEx.getEqualXML(PuquoteorderProperty.PUQO_CURR, currId));
            }
            // 申请人模糊查询关键字
            String ordeUserNameKey = queryDTO.getOrdeUserNameKey();
            if (!StringUtil.isNullOrEmpty(ordeUserNameKey)) {
                conditions.add(ormParamEx.getMatchMiddleXML(EmployeeProperty.REMP_NAME, ordeUserNameKey));
            }

            String[] conditionArray = conditions.toArray(new String[0]);
            ormParamEx.setWhereExp(OrmParamEx.and(conditionArray));
            ormParamEx.setOrderExp(SQLSortEnum.DESC, OrderProperty.ORDE_DATE);

            int page = queryDTO.getPage();
            int rows = queryDTO.getRows();

            PageHelper.startPage(page, rows, false);
            List<Map<String, Object>> dataList = ormService.selectMapListEx(PuquoteorderEntity.class, ormParamEx);
            long totalSize = ormService.countEx(PuquoteorderEntity.class, ormParamEx);
            Result result = new Result();
            JSONObject returnData = new JSONObject();
            JSONArray array = new JSONArray();
            if (null != dataList && dataList.size() > 0) {
                for (Map<String, Object> map : dataList) {
                    JSONObject object = new JSONObject();
                    // 单据id
                    object.put(NodeConstant.ID, NullUtils.valueOf(map.get(columnId.replaceAll("\\.", "_"))));
                    // 单据单号
                    object.put(OrderProperty.ORDE_NBR, NullUtils.valueOf(map.get(OrderProperty.ORDE_NBR)));
                    // 单据定义id
                    object.put(OrderProperty.ORDE_RODE_OBJ, NullUtils.valueOf(map.get(OrderProperty.ORDE_RODE_OBJ)));
                    // 流程实例id
                    object.put(OrderProperty.ORDE_PROCOBJ, NullUtils.valueOf(map.get(OrderProperty.ORDE_PROCOBJ)));
                    // 申请人id
                    object.put(OrderProperty.ORDE_ADDUSER, NullUtils.valueOf(map.get(OrderProperty.ORDE_ADDUSER)));
                    // 申请人姓名
                    object.put(EmployeeProperty.REMP_NAME, NullUtils.valueOf(map.get(EmployeeProperty.REMP_NAME)));
                    //单据状态
                    object.put(OrderProperty.ORDE_STATUS, NullUtils.valueOf(map.get(OrderProperty.ORDE_STATUS)));
                    // 申请日期
                    Object str = map.get(OrderProperty.ORDE_DATE);
                    if (!StringUtil.isNullOrEmpty(str)) {
                        Timestamp date = (Timestamp) str;
                        object.put(OrderProperty.ORDE_DATE, DateUtil.formatDate(new Date(date.getTime())));
                    } else {
                        object.put(OrderProperty.ORDE_DATE, "");
                    }
                    // 单据备注
                    object.put(PuquoteorderProperty.PUQO_REMARK, NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_REMARK)));
                    // 币别id
                    String currObjId = NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_CURR));
                    object.put(PuquoteorderProperty.PUQO_CURR, currObjId);
                    // 币别名称和code
                    object.put(CurrencyProperty.CURR_CODE, NullUtils.valueOf(map.get(CurrencyProperty.CURR_CODE)));
                    object.put(CurrencyProperty.CURR_NAME, NullUtils.valueOf(map.get(CurrencyProperty.CURR_NAME)));
                    // 供应商id
                    object.put(PuquoteorderProperty.PUQO_CODE_SUPP, NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_CODE_SUPP)));
                    // 供应商编号
                    object.put(RelationProperty.RELA_CODE, NullUtils.valueOf(map.get(RelationProperty.RELA_CODE)));
                    // 供应商简称
                    object.put(PuquoteorderProperty.PUQO_SNAME_SUPP, NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_SNAME_SUPP)));

                    array.add(object);
                }
            }
            returnData.put("total", totalSize);
            returnData.put("list", array);
            returnData.put("page", page);
            returnData.put("rows", rows);
            JsonUtils.underLine2Camel(returnData);

            result.setErrMsg("查询数据成功");
            result.setData(returnData);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }

    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result passOrder(String orderInstanceId, String handlerType) {
        try {
            Result result = new Result();
            switch (handlerType) {
                case WFHandlerTypeConstants.PASS: {
                    return passHandlerTypePass(orderInstanceId);
                }
                case WFHandlerTypeConstants.REVOKE: {
                    return updateOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_1);
                }
                case WFHandlerTypeConstants.RETURN_BACK: {
                    return updateOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_6);
                }
                default: {
                    result.setRetCode(Result.RECODE_SUCCESS);
                    result.setErrMsg("未找到" + handlerType + "处理方式");
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
    }

    protected Result passHandlerTypePass(String orderInstanceId) throws Exception {
        Result result = new Result();
        //TODO  这个pass逻辑较复杂
        List<PuqoPuqoPriceSetaEntity> priceSetaEntities = new ArrayList<>();

        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, orderInstanceId)));
        List<PuquoteorderEntity> orderEntityList = ormService.selectBeanList(PuquoteorderEntity.class, ormParam);

        ormParam.reset();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, orderInstanceId)));
        priceSetaEntities.addAll(ormService.selectBeanList(PuqoPuqoPriceSetaEntity.class, ormParam));

        PuquoteorderEntity orderEntity = orderEntityList.get(0);
        orderEntity.setPuqo_price_set(priceSetaEntities);
        formateDateNoTime(orderEntity);

        Date now = NullUtils.formatDateNoTime(new Date());

        for (PuqoPuqoPriceSetaEntity e1 : priceSetaEntities) {
            List<PuqoPuqoLadderSetbEntity> priceLadderSetbList = new ArrayList<>();
            List<PuqoPuqoLadderSetOldbEntity> priceLadderSetOldbList = new ArrayList<>();
            JSONObject godsParkStatus = new JSONObject();
            ormParam.reset();
            ormParam.addColumn(NodeConstant.ID).addColumn(GodsGodsParkSetaProperty.GODS_STATUS);
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, e1.getPuqo_goods())
                    , ormParam.getEqualXML(GodsGodsParkSetaProperty.GODS_PARK, e1.getPuqo_park())));
            List<Map<String, Object>> list = ormService.selectMapList(GodsGodsParkSetaEntity.class, ormParam);
            if (null != list && list.size() > 0) {
                Map<String, Object> map = list.get(0);
                godsParkStatus.put(NodeConstant.ID, NullUtils.valueOf(map.get(NodeConstant.ID)));
                godsParkStatus.put(GodsGodsParkSetaProperty.GODS_STATUS, NullUtils.valueOf(map.get(GodsGodsParkSetaProperty.GODS_STATUS)));
            }

            if (INT_1.equals(e1.getPuqo_isladder())) {
                ormParam.reset();
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, e1.getId())));
                ormParam.setOrderExp(SQLSortEnum.ASC, PuqoPuqoLadderSetbProperty.PUQO_OQTY_MAX);
                priceLadderSetbList.addAll(ormService.selectBeanList(PuqoPuqoLadderSetbEntity.class, ormParam));
            }
            if (INT_1.equals(e1.getPuqo_isladder_old())) {
                ormParam.reset();
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, e1.getId())));
                ormParam.setOrderExp(SQLSortEnum.ASC, PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MAX_OLD);
                priceLadderSetOldbList.addAll(ormService.selectBeanList(PuqoPuqoLadderSetOldbEntity.class, ormParam));
            }

            Integer status = e1.getPuqo_istatus();
            if (INT_1.equals(status)) {
                // 审批通过
                Date begDate = e1.getPuqo_date_beg();
                //   1. 审批完成时间<生效日期,正常写入一行价格数据.
                if (now.compareTo(begDate) < 0) {
                    GodsGodsPupricSetaEntity ggpse = passGetGodsGodsPupricSetaEntity(orderEntity, e1);
                    // 插入数据
                    String pid = passInsertGoods(orderEntity, e1, ggpse);
                    // 是阶梯报价
                    passInsertLadder(orderEntity, e1, priceLadderSetbList, ggpse, pid);

                } else if (now.compareTo(begDate) >= 0) {
                    //   2、生效日期<=审批完成日期<失效日期，写入一行价格数据，生效日期写入审批完成时间，园区状态更新为AC。(原状态为AC的不更新)
                        Date endDate = e1.getPuqo_date_end();
                        if (null == endDate) {
                            endDate = NullUtils.nullEndDate();
                        }
                        if (now.compareTo(endDate) < 0) {
                            //  生效日期<=审批完成日期<失效日期，则写入一行新的价格数据，生效日期写入审批完成时间，园区状态更新为AC。(原状态为AC的不更新)
                            GodsGodsPupricSetaEntity ggpse = passGetGodsGodsPupricSetaEntity(orderEntity, e1);
                            ggpse.setGods_pdate_beg(now);
                            // 插入数据
                            String pid = passInsertGoods(orderEntity, e1, ggpse);
                            // 是阶梯报价
                            passInsertLadder(orderEntity, e1, priceLadderSetbList, ggpse, pid);

                            String godsStatus = NullUtils.valueOf(godsParkStatus.get(GodsGodsParkSetaProperty.GODS_STATUS));
                            String godsParkSetaId = NullUtils.valueOf(godsParkStatus.get(NodeConstant.ID));
                            if (!StringUtil.isNullOrEmpty(godsParkSetaId)) {
                                if (!("AC".equals(godsStatus))) {
                                    GodsGodsParkSetaEntity entity = new GodsGodsParkSetaEntity();
                                    entity.setId(godsParkSetaId);
                                    entity.setGods_status("AC");
                                    ormService.updateSelective(entity);
                                }
                            }
                        }else{
                           // 3. 审批完成日期>=失效日期，写入一行价格数据，生效日期和失效日期均写入审批完成时间，园区状态更新为UP。(原状态为UP的不更新)
                            GodsGodsPupricSetaEntity ggpse = passGetGodsGodsPupricSetaEntity(orderEntity, e1);
                            ggpse.setGods_pdate_beg(now);
                            ggpse.setGods_pdate_end(now);
                            // 插入数据
                            String pid = passInsertGoods(orderEntity, e1, ggpse);
                            // 是阶梯报价
                            passInsertLadder(orderEntity, e1, priceLadderSetbList, ggpse, pid);

                            String godsStatus = NullUtils.valueOf(godsParkStatus.get(GodsGodsParkSetaProperty.GODS_STATUS));
                            String godsParkSetaId = NullUtils.valueOf(godsParkStatus.get(NodeConstant.ID));
                            if (!StringUtil.isNullOrEmpty(godsParkSetaId)) {
                                if (!("UP".equals(godsStatus))) {
                                    GodsGodsParkSetaEntity entity = new GodsGodsParkSetaEntity();
                                    entity.setId(godsParkSetaId);
                                    entity.setGods_status("UP");
                                    ormService.updateSelective(entity);
                                }
                            }
                        }
                    }
            }
        }
        updateOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_5);
        result.setErrMsg("审核单据状态成功");
        return result;
    }

    private String passInsertGoods(PuquoteorderEntity orderEntity, PuqoPuqoPriceSetaEntity e1, GodsGodsPupricSetaEntity ggpse) throws Exception {

        Date now = NullUtils.formatDateNoTime(new Date());
        // 4、重新写入一行数据时，按照物品、供应商、币别、园区查找属性集里有效期内报价（生效日期<=当天<失效日期），如其失效日期为空或>新生效日期，将旧价格失效日期更新为新生效日期；否则不更新。
        // 4-------------start
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(NodeConstant.ID).addColumn(GodsGodsPupricSetaProperty.GODS_PDATE_END);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, e1.getPuqo_goods()),
                ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_PCURR, orderEntity.getPuqo_curr()),
                ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_SUPP, orderEntity.getPuqo_code_supp()),
                ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_PPARK, e1.getPuqo_park()),
                ormParam.getLessThanAndEqualXML(GodsGodsPupricSetaProperty.GODS_PDATE_BEG, now),
                OrmParam.or(ormParam.getGreaterThanXML(GodsGodsPupricSetaProperty.GODS_PDATE_END, now),
                        ormParam.getIsNull(GodsGodsPupricSetaProperty.GODS_PDATE_END))
        ));

        List<Map<String, Object>> list = ormService.selectMapList(GodsGodsPupricSetaEntity.class, ormParam);
        if (null != list && list.size() > 0) {
            for (Map<String, Object> map : list) {
                String id = NullUtils.valueOf(map.get(NodeConstant.ID));
                //失效日期
                boolean isRight = false;
                Object str = map.get(PuqoPuqoPriceSetaProperty.PUQO_DATE_END);
                if (!StringUtil.isNullOrEmpty(str)) {
                    Timestamp date = (Timestamp) str;
                    Date endDateRe = new Date(date.getTime());
                    if (endDateRe.compareTo(e1.getPuqo_date_beg()) > 0) {
                        isRight = true;
                    }
                } else {
                    isRight = true;
                }
                if (isRight) {
                    GodsGodsPupricSetaEntity eee1 = new GodsGodsPupricSetaEntity();
                    eee1.setId(id);
                    eee1.setGods_pdate_end(e1.getPuqo_date_beg());
                    ormService.updateSelective(eee1);
                }
            }
            // 4-------------end
            // 5，6------------------------start
            ormParam.reset();
            ormParam.addColumn(NodeConstant.ID).addColumn(GodsGodsPupricSetaProperty.GODS_PDATE_END);
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, e1.getPuqo_goods()),
                    ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_PCURR, orderEntity.getPuqo_curr()),
                    ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_SUPP, orderEntity.getPuqo_code_supp()),
                    ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_PPARK, e1.getPuqo_park()),
                    ormParam.getGreaterThanXML(GodsGodsPupricSetaProperty.GODS_PDATE_BEG, now)
            ));
            list = ormService.selectMapList(GodsGodsPupricSetaEntity.class, ormParam);
            //   5、重新写入一行未来生效的数据时，按照物品、供应商、币别、园区删除采购价格属性集里是生效日期大于当天的记录。（未来生效报价，只留一笔记录）
            if (e1.getPuqo_date_beg().compareTo(now) > 0) {
                if (null != list && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                        String id = NullUtils.valueOf(map.get(NodeConstant.ID));
                        ormService.delete(GodsGodsPupricSetaEntity.class, id);
                    }
                }
            }
            Date endDateNew2 = e1.getPuqo_date_end();
            if (null == endDateNew2) {
                endDateNew2 = NullUtils.nullEndDate();
            }
            if (e1.getPuqo_date_beg().compareTo(now) <= 0 && now.compareTo(endDateNew2) < 0) {
                // 6、重新写入一行当前有效的数据时，按照物品、供应商、币别、园区检索采购价格属性集里是生效日期大于当天的记录。
                if (null != list && list.size() > 0) {
                    for (Map<String, Object> map : list) {
                        String id = NullUtils.valueOf(map.get(NodeConstant.ID));
                        Date endDateNew = e1.getPuqo_date_end();
                        Date endDateOld = e1.getPuqo_date_end_old();
                        Date begDateOld = e1.getPuqo_date_beg_old();
                        if (null == endDateOld) {
                            endDateOld = NullUtils.nullEndDate();
                        }
                        // 1) 如果新的失效日期为空，或者新失效日期>=旧失效日期，则删除原未来生效的记录。
                        if (null == endDateNew || endDateNew.compareTo(endDateOld) >= 0) {
                            ormService.delete(GodsGodsPupricSetaEntity.class, id);
                        }
                       // 2) 如果旧生效日期<新失效日期<旧失效日期，则把旧的生效日期刷新为新失效日期。
                        if (null != endDateNew) {
                            if (endDateNew.compareTo(begDateOld) > 0 && endDateNew.compareTo(endDateOld) < 0) {
                                GodsGodsPupricSetaEntity eee1 = new GodsGodsPupricSetaEntity();
                                eee1.setId(id);
                                eee1.setGods_pdate_beg(endDateNew);
                                ormService.updateSelective(eee1);
                            }
                        }
                    }
                }
            }
            // 5，6------------------------end
        }
        return NullUtils.valueOf(ormService.insertSelective(ggpse));
    }


    private void passInsertLadder(PuquoteorderEntity orderEntity, PuqoPuqoPriceSetaEntity e1, List<PuqoPuqoLadderSetbEntity> priceLadderSetbList, GodsGodsPupricSetaEntity ggpse, String pid) throws Exception {
        if (INT_1.equals(e1.getPuqo_isladder())) {
            for (PuqoPuqoLadderSetbEntity e2 : priceLadderSetbList) {
                GodsGodsPladderSetbEntity ggladder = new GodsGodsPladderSetbEntity();
                ggladder.setGods_lupdown_rate(e2.getPuqo_lupdown_rate());
                ggladder.setGods_plprice(e2.getPuqo_lprice());
                ggladder.setGods_poqty_max(e2.getPuqo_oqty_max());
                ggladder.setGods_poqty_min(e2.getPuqo_oqty_min());
                ggladder.setClassName("goods");
                ggladder.setCreuser(NullUtils.valueOf(orderEntity.getOrde_adduser()));
                ggladder.setCretime(ggpse.getCretime());
                ggladder.setPid(pid);
                ormService.insertSelective(ggladder);
            }
        }

    }

    @NotNull
    private GodsGodsPupricSetaEntity passGetGodsGodsPupricSetaEntity(PuquoteorderEntity orderEntity, PuqoPuqoPriceSetaEntity e1) {
        String goodsId = e1.getPuqo_goods();
        GodsGodsPupricSetaEntity ggpse = new GodsGodsPupricSetaEntity();
        ggpse.setClassName("goods");
        ggpse.setCretime(new Date());
        ggpse.setCreuser(NullUtils.valueOf(orderEntity.getOrde_adduser()));
        ggpse.setPid(goodsId);
        ggpse.setGods_pprice_ord(orderEntity.getId());
        ggpse.setGods_supp(orderEntity.getPuqo_code_supp());
        ggpse.setGods_pcurr(orderEntity.getPuqo_curr());
        ggpse.setGods_ptax_rate(orderEntity.getPuqo_tax());
        ggpse.setGods_ppark(e1.getPuqo_park());
        ggpse.setGods_pum(e1.getPuqo_um());
        ggpse.setGods_pprice(e1.getPuqo_price());
        ggpse.setGods_prebate(e1.getPuqo_rebate());
        ggpse.setGods_pdate_beg(e1.getPuqo_date_beg());
        ggpse.setGods_pdate_end(e1.getPuqo_date_end());
        ggpse.setGods_pisladder(e1.getPuqo_isladder());
        ggpse.setGods_updown_rate(e1.getPuqo_updown_rate());
        return ggpse;
    }

    @Override
    public Result auditOrder(JSONObject auditSet) {
        try {
            Result result = new Result();
            String auditKey = auditSet.getString(WorkFlowConstants.PARAM_AUDITKEY);
            String formState = auditSet.getString(WorkFlowConstants.PARAM_FORMSTATE);
            String actInstanceId = auditSet.getString(WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
            String opinion = NullUtils.valueOf(auditSet.getString(WorkFlowConstants.PARAM_OPINION));
            JSONObject ordeParamObj = auditSet.getJSONObject(WorkFlowConstants.PARAM_ORDER_OBJ);

            JSONObject returnData = new JSONObject();
            if (WorkFlowConstants.FormState.EDITABLE.equals(formState) && WorkFlowConstants.AuditKey.PASS.equals(auditKey)) {
                JSONObject jsonObject = auditUpdateOrder(ordeParamObj);
                Result res = (Result) jsonObject.get("error");
                if (res.getRetCode().equals(Result.RECODE_SUCCESS)) {
                    jsonObject.remove("error");
                    returnData.putAll(jsonObject);
                } else {
                    return res;
                }
            }
            audiSubmitFlowOrder(actInstanceId, opinion, auditKey);

            result.setData(returnData);
            result.setErrMsg("提交审核意见成功");
            result.setRetCode(Result.RECODE_SUCCESS);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void audiSubmitFlowOrder(String actInstanceId, String opinion, String auditKey) throws Exception {
        // 调用流程
        bizFormService.audit(actInstanceId, opinion, auditKey);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject auditUpdateOrder(JSONObject ordeParamObj) throws Exception {
        JSONObject returnData = new JSONObject();
        // TODO 更新属性集 审批状态，所以需要 属性集 每个元素的主键id。
        Result result = new Result();
        if (null != ordeParamObj) {
            //TODO 更新属性集 审批状态

            JSONArray array = ordeParamObj.getJSONArray("puqoPriceSet");
            if (null != array && array.size() > 0) {

                for (int i = 0; i < array.size(); i++) {
                    Object o = array.get(0);
                    if (o instanceof JSONObject) {
                        JSONObject jo = (JSONObject) o;
                        String setProId = jo.getString("id");
                        String setProIsStatus = jo.getString("puqoIstatus");
                        if (StringUtil.isNullOrEmpty(setProId)) {
                            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                            result.setErrMsg("在单据对象" + WorkFlowConstants.PARAM_ORDER_OBJ + "中未找到属性集主键 id 参数");
                            returnData.put("error", result);
                            return returnData;
                        }
                        if (StringUtil.isNullOrEmpty(setProIsStatus)) {
                            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                            result.setErrMsg("在单据对象" + WorkFlowConstants.PARAM_ORDER_OBJ + "中未找到属性集对象审批状态 puqoIstatus 参数");
                            returnData.put("error", result);
                            return returnData;
                        } else if (!(setProIsStatus.equals(INT_0.toString()) || setProIsStatus.equals(INT_1.toString()))) {
                            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                            result.setErrMsg("在单据对象" + WorkFlowConstants.PARAM_ORDER_OBJ + "中 puqoIstatus 参数值无效，只能是0或者1");
                            returnData.put("error", result);
                            return returnData;
                        }
                    } else {
                        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                        result.setErrMsg("在单据对象" + WorkFlowConstants.PARAM_ORDER_OBJ + "中未找到属性集puqoPriceSet中有数据无效");
                        returnData.put("error", result);
                    }
                }

                for (int i = 0; i < array.size(); i++) {
                    JSONObject o = array.getJSONObject(0);
                    String setProId = o.getString("id");
                    String setProIsStatus = o.getString("puqoIstatus");
                    PuqoPuqoPriceSetaEntity setaEntity = new PuqoPuqoPriceSetaEntity();
                    setaEntity.setId(setProId);
                    setaEntity.setPuqo_istatus(Integer.parseInt(setProIsStatus));
                    ormService.updateSelective(setaEntity);
                }

            } else {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg("在单据对象" + WorkFlowConstants.PARAM_ORDER_OBJ + "中未找到属性集puqoPriceSet 参数或者数组长度无效");
                returnData.put("error", result);
                return returnData;
            }

        } else {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg("未找到单据对象参数" + WorkFlowConstants.PARAM_ORDER_OBJ);
            returnData.put("error", result);
            return returnData;
        }
        returnData.put("error", result);
        return returnData;
    }

    protected Result updateOrderStatus(String orderInstanceId, String status) throws Exception {
        Result result = new Result();
        PuquoteorderEntity entity = new PuquoteorderEntity();
        entity.setId(orderInstanceId);
        entity.setOrde_status(status);
        ormService.updateSelective(entity);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setErrMsg("更新单据状态成功");
        return result;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Result queryPuquPriceByGoods(PuquGodsQueryDTO queryDTO) {
        try {
            OrmParamEx ormParamEx = new OrmParamEx();
            String columnGoodsId = OrmParamEx.column(GoodsEntity.class, NodeConstant.ID);
            String columnGodsPriceId = OrmParamEx.column(GodsGodsPupricSetaEntity.class, NodeConstant.ID);
            ormParamEx.addColumn(columnGoodsId).addColumn(columnGodsPriceId)
                    .addColumn(GoodsProperty.GODS_CODE).addColumn(GoodsProperty.GODS_MODEL).addColumn(GoodsProperty.GODS_NAME)
                    .addColumn(GodsGodsParkSetaProperty.GODS_STATUS).addColumn(GoodsProperty.GODS_UNIT)
                    .addColumn(GodsGodsPupricSetaProperty.GODS_SUPP).addColumn(RelationProperty.RELA_CODE)
                    .addColumn(RelationProperty.RELA_SHORT_NAME).addColumn(RelaRelaSupplierSetaProperty.RELA_STAT_SUPP)
                    .addColumn(GodsGodsPupricSetaProperty.GODS_PPARK)
                    .addColumn(ParkProperty.RPAK_NAME).addColumn(GodsGodsPupricSetaProperty.GODS_PCURR)
                    .addColumn(CurrencyProperty.CURR_CODE).addColumn(CurrencyProperty.CURR_NAME)
                    .addColumn(GodsGodsPupricSetaProperty.GODS_PPRICE_ORD)
                    .addColumn(OrderProperty.ORDE_NBR).addColumn(OrderProperty.ORDE_RODE_OBJ).addColumn(OrderProperty.ORDE_PROCOBJ)
                    .addColumn(GodsGodsPupricSetaProperty.GODS_PDATE_BEG).addColumn(GodsGodsPupricSetaProperty.GODS_PDATE_END)
                    .addColumn(GodsGodsPupricSetaProperty.GODS_PISLADDER).addColumn(GodsGodsPupricSetaProperty.GODS_PPRICE).addColumn(GodsGodsPupricSetaProperty.GODS_PREBATE)
            ;

            ormParamEx.leftJoin(GodsGodsParkSetaEntity.class,
                    OrmParamEx.joinLinkInDifferentTable(GodsGodsParkSetaEntity.class, NodeConstant.PID, GoodsEntity.class, NodeConstant.ID), DataVailidEnum.NOMATTER)
                    .leftJoin(GodsGodsPupricSetaEntity.class,
                            OrmParamEx.joinLinkInDifferentTable(GodsGodsPupricSetaEntity.class, NodeConstant.PID, GoodsEntity.class, NodeConstant.ID), DataVailidEnum.NOMATTER)
                    .leftJoin(RelationEntity.class,
                            OrmParamEx.joinLinkInDifferentTable(RelationEntity.class, NodeConstant.ID, GodsGodsPupricSetaEntity.class, GodsGodsPupricSetaProperty.GODS_SUPP), DataVailidEnum.NOMATTER)
                    .leftJoin(RelaRelaSupplierSetaEntity.class,
                            OrmParamEx.joinLinkInDifferentTable(RelationEntity.class, NodeConstant.ID, RelaRelaSupplierSetaEntity.class, NodeConstant.PID), DataVailidEnum.NOMATTER)
                    .leftJoin(ParkEntity.class,
                            OrmParamEx.joinLinkInDifferentTable(ParkEntity.class, NodeConstant.ID, GodsGodsPupricSetaEntity.class, GodsGodsPupricSetaProperty.GODS_PPARK), DataVailidEnum.NOMATTER)
                    .leftJoin(CurrencyEntity.class,
                            OrmParamEx.joinLinkInDifferentTable(CurrencyEntity.class, NodeConstant.ID, GodsGodsPupricSetaEntity.class, GodsGodsPupricSetaProperty.GODS_PCURR), DataVailidEnum.NOMATTER)
                    .leftJoin(PuquoteorderEntity.class,
                            OrmParamEx.joinLinkInDifferentTable(PuquoteorderEntity.class, NodeConstant.ID, GodsGodsPupricSetaEntity.class, GodsGodsPupricSetaProperty.GODS_PPRICE_ORD), DataVailidEnum.NOMATTER)
            ;

            List<String> conditions = new ArrayList<>();
            // is_del=0
            conditions.add(ormParamEx.getEqualXML(OrmParamEx.column(PuquoteorderEntity.class, "is_del"), 0));
            conditions.add(ormParamEx.getEqualXML(OrmParamEx.column(GoodsEntity.class, "is_del"), 0));
            // 供应商模糊查询关键字
            String suppKey = queryDTO.getSuppKey();
            if (!StringUtil.isNullOrEmpty(suppKey)) {
                conditions.add(OrmParamEx.or(ormParamEx.getMatchMiddleXML(RelationProperty.RELA_CODE, suppKey), ormParamEx.getMatchMiddleXML(RelationProperty.RELA_SHORT_NAME, suppKey)));
            }
            //生效日期开始日期
            String startTime = queryDTO.getStartTime();
            if (!StringUtil.isNullOrEmpty(startTime)) {
                conditions.add(ormParamEx.getGreaterThanAndEqualXML(GodsGodsPupricSetaProperty.GODS_PDATE_BEG, NullUtils.convertStartTime(startTime)));
            }
            // 生效时间结束时间
            String endTime = queryDTO.getEndTime();
            if (!StringUtil.isNullOrEmpty(endTime)) {
                conditions.add(ormParamEx.getLessThanAndEqualXML(GodsGodsPupricSetaProperty.GODS_PDATE_BEG, NullUtils.convertEndTime(endTime)));
            }
            //  物品编号或名称关键字
            String goodsKey = queryDTO.getGodsKey();
            if (!StringUtil.isNullOrEmpty(goodsKey)) {
                conditions.add(OrmParamEx.or(ormParamEx.getMatchMiddleXML(GoodsProperty.GODS_CODE, goodsKey), ormParamEx.getMatchMiddleXML(GoodsProperty.GODS_NAME, goodsKey)));
            }
            // 物品状态
            String godsStatus = queryDTO.getGodsStatus();
            if (!StringUtil.isNullOrEmpty(godsStatus)) {
                conditions.add(ormParamEx.getEqualXML(GodsGodsParkSetaProperty.GODS_STATUS, godsStatus));
            }
            // 园区id
            String parkId = queryDTO.getParkId();
            if (!StringUtil.isNullOrEmpty(parkId)) {
                conditions.add(ormParamEx.getEqualXML(GodsGodsPupricSetaProperty.GODS_PPARK, parkId));
            }
            // 币别id
            String currId = queryDTO.getCurrId();
            if (!StringUtil.isNullOrEmpty(currId)) {
                conditions.add(ormParamEx.getEqualXML(GodsGodsPupricSetaProperty.GODS_PCURR, currId));
            }
            // 物品分类
            String classId = queryDTO.getGodsClassId();
            if (!StringUtil.isNullOrEmpty(classId)) {
                conditions.add(ormParamEx.getEqualXML(GoodsProperty.GODS_CLASS, classId));
            }

            String[] conditionArray = conditions.toArray(new String[0]);
            ormParamEx.setWhereExp(OrmParamEx.and(conditionArray));

            int page = queryDTO.getPage();
            int rows = queryDTO.getRows();

            PageHelper.startPage(page, rows, false);
            List<Map<String, Object>> dataList = ormService.selectMapListEx(GoodsEntity.class, ormParamEx);

            long totalSize = ormService.countEx(GoodsEntity.class, ormParamEx);
            Result result = new Result();
            JSONObject returnData = new JSONObject();
            JSONArray array = new JSONArray();
            if (null != dataList && dataList.size() > 0) {
                for (Map<String, Object> map : dataList) {
                    JSONObject object = new JSONObject();
                    // 物品id
                    object.put(NodeConstant.ID, NullUtils.valueOf(map.get(columnGoodsId.replaceAll("\\.", "_"))));
                    // 属性集id
                    String godsPriceId = NullUtils.valueOf(map.get(columnGodsPriceId.replaceAll("\\.", "_")));
                    // 物品编号
                    object.put(GoodsProperty.GODS_CODE, NullUtils.valueOf(map.get(GoodsProperty.GODS_CODE)));
                    // 物品名称
                    object.put(GoodsProperty.GODS_NAME, NullUtils.valueOf(map.get(GoodsProperty.GODS_NAME)));
                    // 规格型号
                    object.put(GoodsProperty.GODS_MODEL, NullUtils.valueOf(map.get(GoodsProperty.GODS_MODEL)));
                    // 状态
                    object.put(GodsGodsParkSetaProperty.GODS_STATUS, NullUtils.valueOf(map.get(GodsGodsParkSetaProperty.GODS_STATUS)));
                    // 单位
                    object.put(GoodsProperty.GODS_UNIT, NullUtils.valueOf(map.get(GoodsProperty.GODS_UNIT)));
                    // 供应商id
                    object.put(GodsGodsPupricSetaProperty.GODS_SUPP, NullUtils.valueOf(map.get(GodsGodsPupricSetaProperty.GODS_SUPP)));
                    // 供应商编号
                    object.put(RelationProperty.RELA_CODE, NullUtils.valueOf(map.get(RelationProperty.RELA_CODE)));
                    // 供应商简称
                    object.put(RelationProperty.RELA_SHORT_NAME, NullUtils.valueOf(map.get(RelationProperty.RELA_SHORT_NAME)));
                    // 供应商状态
                    object.put(RelaRelaSupplierSetaProperty.RELA_STAT_SUPP, NullUtils.valueOf(map.get(RelaRelaSupplierSetaProperty.RELA_STAT_SUPP)));
                    // 园区id
                    object.put(GodsGodsPupricSetaProperty.GODS_PPARK, NullUtils.valueOf(map.get(GodsGodsPupricSetaProperty.GODS_PPARK)));
                    // 园区名称
                    object.put(ParkProperty.RPAK_NAME, NullUtils.valueOf(map.get(ParkProperty.RPAK_NAME)));
                    // 币别id
                    object.put(GodsGodsPupricSetaProperty.GODS_PCURR, NullUtils.valueOf(map.get(GodsGodsPupricSetaProperty.GODS_PCURR)));
                    // 币别编号
                    object.put(CurrencyProperty.CURR_CODE, NullUtils.valueOf(map.get(CurrencyProperty.CURR_CODE)));
                    // 币别名称
                    object.put(CurrencyProperty.CURR_NAME, NullUtils.valueOf(map.get(CurrencyProperty.CURR_NAME)));
                    // 报价单单据id
                    object.put(GodsGodsPupricSetaProperty.GODS_PPRICE_ORD, NullUtils.valueOf(map.get(GodsGodsPupricSetaProperty.GODS_PPRICE_ORD)));
                    // 报价单单号
                    object.put(OrderProperty.ORDE_NBR, NullUtils.valueOf(map.get(OrderProperty.ORDE_NBR)));
                    // 单据定义id
                    object.put(OrderProperty.ORDE_RODE_OBJ, NullUtils.valueOf(map.get(OrderProperty.ORDE_RODE_OBJ)));
                    // 流程实例id
                    object.put(OrderProperty.ORDE_PROCOBJ, NullUtils.valueOf(map.get(OrderProperty.ORDE_PROCOBJ)));
                    // 生效日期
                    Object str = map.get(GodsGodsPupricSetaProperty.GODS_PDATE_BEG);
                    if (!StringUtil.isNullOrEmpty(str)) {
                        Timestamp date = (Timestamp) str;
                        object.put(GodsGodsPupricSetaProperty.GODS_PDATE_BEG, DateUtil.formatDate(new Date(date.getTime())));
                    } else {
                        object.put(GodsGodsPupricSetaProperty.GODS_PDATE_BEG, "");
                    }
                    // 失效日期
                    str = map.get(GodsGodsPupricSetaProperty.GODS_PDATE_END);
                    if (!StringUtil.isNullOrEmpty(str)) {
                        Timestamp date = (Timestamp) str;
                        object.put(GodsGodsPupricSetaProperty.GODS_PDATE_END, DateUtil.formatDate(new Date(date.getTime())));
                    } else {
                        object.put(GodsGodsPupricSetaProperty.GODS_PDATE_END, "");
                    }
                    // 是否是阶梯报价
                    String isLadder = NullUtils.valueOf(map.get(GodsGodsPupricSetaProperty.GODS_PISLADDER));
                    object.put(GodsGodsPupricSetaProperty.GODS_PISLADDER, isLadder);
                    // 返利额
                    object.put(GodsGodsPupricSetaProperty.GODS_PREBATE, NullUtils.doubleValueOf(map.get(GodsGodsPupricSetaProperty.GODS_PREBATE)));
                    if (isLadder.equals(INT_0.toString())) {
                        // 单价
                        object.put(GodsGodsPupricSetaProperty.GODS_PPRICE, NullUtils.doubleValueOf(map.get(GodsGodsPupricSetaProperty.GODS_PPRICE)));
                        object.put("gods_pladder_set", new JSONArray());
                    } else if (isLadder.equals(INT_1.toString())) {
                        // 单价
                        object.put(GodsGodsPupricSetaProperty.GODS_PPRICE, "0");
                        OrmParam ormParam = new OrmParam();
                        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, godsPriceId)));
                        ormParam.addColumn(GodsGodsPladderSetbProperty.GODS_LUPDOWN_RATE).addColumn(GodsGodsPladderSetbProperty.GODS_PLPRICE)
                                .addColumn(GodsGodsPladderSetbProperty.GODS_POQTY_MAX).addColumn(GodsGodsPladderSetbProperty.GODS_POQTY_MIN);
                        ormParam.setOrderExp(SQLSortEnum.ASC, GodsGodsPladderSetbProperty.GODS_POQTY_MAX);

                        List<GodsGodsPladderSetbEntity> list = ormService.selectBeanList(GodsGodsPladderSetbEntity.class, ormParam);
                        JSONArray listArray = JSONArray.parseArray(JSON.toJSONString(list));
                        object.put("gods_pladder_set", listArray);
                    } else {
                        object.put(GodsGodsPupricSetaProperty.GODS_PPRICE, "0");
                        object.put("gods_pladder_set", new JSONArray());
                    }

                    array.add(object);
                }
            }
            returnData.put("total", totalSize);
            returnData.put("list", array);
            returnData.put("page", page);
            returnData.put("rows", rows);
            JsonUtils.underLine2Camel(returnData);

            result.setErrMsg("查询数据成功");
            result.setData(returnData);
            return result;

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
    }

}
