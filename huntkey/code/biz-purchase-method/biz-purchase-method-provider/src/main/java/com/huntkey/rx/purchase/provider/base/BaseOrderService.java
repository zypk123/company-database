package com.huntkey.rx.purchase.provider.base;

import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.base.PropertyBaseEntity;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.DepttreeProperty;
import com.huntkey.rx.edm.constant.EmployeeProperty;
import com.huntkey.rx.edm.constant.JobpositionProperty;
import com.huntkey.rx.edm.constant.OrderProperty;
import com.huntkey.rx.edm.entity.DepttreeEntity;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.edm.entity.OrderEntity;
import com.huntkey.rx.purchase.common.constants.OrderConstants;
import com.huntkey.rx.purchase.common.constants.WFHandlerTypeConstants;
import com.huntkey.rx.purchase.common.constants.WorkFlowConstants;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.base.CurrentSessionEntity;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.BizFormService;
import com.huntkey.rx.purchase.provider.service.CommonService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.model.OrmParamEx;
import com.huntkey.rx.sceo.orm.common.type.DataVailidEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLCurdEnum;
import com.huntkey.rx.sceo.orm.common.util.EdmUtil;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.ParameterizedType;
import java.sql.Timestamp;
import java.util.*;

/**
 * 这个BaseOrderService 只是一个通用处理单据的一般流程。
 *  这里的T和S分别表示 T：单据实体类 S：T这个单据实体类相应的属性集
 *   BaseOrderService并不是万能的。 一般需求能解决。复杂需要可以参考这个类来实现。
 *   所以 不要过分依赖这个类。 如果发现这个类不好用，甚至IBaseOrderService接口我都觉得不好用
 *   请直接自定义。但需要参考BaseOrderService，虽然不是万能的。但是有些返回数据是约定俗成的，不要随意更改。
 *
 *   BaseOrderService：
 *     完成了一下事情：
 *     save和submit 操作已经完全封装，在没有特殊要求情况下，我们只需要编写校验代码。
 *     也就是在doVaildLogic 编写校验代码，如果校验有什么不符合业务需求，请直接把需要展示的错误信息放到errMsgList里即可。
 *
 *     load方法，完成了单据的基本信息，
 *            单据id，单据单号，制单人id，制单人名字，制单人工号，制单部门id，制单人部门名字，制单岗位id，制单岗位名字
 *            单据状态，单据定义id，单据制单日期（已经格式化了）。共12个字段。（这12个字段是必须的，是基本的）
 *
 *     我们需要完成的是： 相应单据实体类的属性loadOrderEntityData和实体类对应的属性集loadOrderEntitySetData
 *                    具体的请参考DemoServiceImpl类相应方法的实现。
 *
 *     关于事务和分布式事务@Transactional和@TxTransaction； 这个类已经完全做好了（@Transactional和@TxTransaction都是可派生的，也就是说所有子类都会自动加上注解）
 *     所以 对于我们开发而言，不用在关心事务和事务的注解配置。 除非你需要自定义或者重写什么方法。
 *
 *     接下来还有两个大操作
 *     1. handlePassLogic方法，我们只需要处理审批通过的业务需求，主要是更新资源表里的数据了。
 *     2. getAuditVaildParamAndUpdateErrorMsg这个方法的作用
 *        当在审批过程中需要修改单据的一些信息，比如某些字段的话。
 *        我们需要对 前端传递的参数 进行一些必要的校验，比如单据id要传递吧，我要修改单据肯定需要id。
 *        如果发现参数没有，可以直接返回错误信息，也就是ErrorMsg；如果所有的参数都正确，那么返回“”或者null都行
 *        然后应在这个方法中 做一些关于 单据数据的更新操作。所以此方法 第一校验参数，第二更新数据。
 *
 *    其他方法的说明：
 *       getOrderNbrTempPrefix() 生成临时单
 *       getOrderNbrPrefix()     生成待审单
 *       用于生成单据的前缀，比如TEMP或者HR-12等等
 *
 *       getEntityEdmClassName 用于插入数据库edm_class字段的值，这个值一般是单据实体类的对应数据库的表名
 *        getEntitySetList 单据实体类与属性集的关联关系，如 entity.getOepc_chang_set()
 *
 *
 *
 * @author yaoss
 */
public abstract class BaseOrderService<T extends OrderEntity,S extends PropertyBaseEntity> implements IBaseOrderService<T> {

    private Class<T> entityClass;
    private Class<S> entitySetClass;

    @Autowired
    public OrmService ormService;
    @Autowired
    public BizFormService bizFormService;
    @Autowired
    public CommonService commonService;

    public BaseOrderService(){
        entityClass =  (Class <T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        entitySetClass =  (Class <S>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    /**
     * 真正的校验逻辑
     * @param errMsgList
     * @param entity
     */
    public abstract void doVaildLogic(List<String> errMsgList,T entity);

    /**
     * 获得临时单据的单据前缀
     * @return
     */
    public abstract String getOrderNbrTempPrefix();

    /**
     * 获得提交单据的单据前缀
     * @return
     */
    public abstract String getOrderNbrPrefix();

    /**
     * 设置单据类的entity的EdmClass
     * @return
     */
    public abstract String getEntityEdmClassName();

    /**
     * 设置属性集class
     * @return
     */
    public abstract List<S> getEntitySetList(T entity);

    /**
     * 对属性集的插入
     */
    public void insertEntitySet(T entity) throws Exception{
        List<S> setEntities = getEntitySetList(entity);
        if(null!=setEntities&&setEntities.size()>0){
            if(StringUtil.isNullOrEmpty(entity.getId())){
                EdmUtil.setPropertyBaseEntitiesSysColumns(entitySetClass, entity,
                        setEntities, SQLCurdEnum.INSERT);
            }else{
                EdmUtil.setPropertyBaseEntitiesSysColumns(entitySetClass, entity,
                        setEntities, SQLCurdEnum.UPDATE);
            }
            for (S e1 : setEntities) {
                e1.setId(null);
                e1.setClassName(NullUtils.valueOf(getEntityEdmClassName()));
                ormService.insertSelective(e1);
            }
        }
    }

    /**
     * load单据实体类信息
     * @param orderId
     * @return
     * @throws Exception
     */
    public abstract JSONObject loadOrderEntityData(String orderId) throws Exception;

    /**
     * load单据实体类所关联的属性集信息
     * @param orderId
     * @return
     * @throws Exception
     */
    public abstract JSONObject loadOrderEntitySetData(String orderId) throws Exception;

    /**
     * 真正处理审批通过逻辑
     * @param orderInstanceId
     * @return
     * @throws Exception
     */
    protected abstract Result handlePassLogic(String orderInstanceId) throws Exception;

    /**
     * 对ordeParamObj里面的参数进行校验，如果参数全部正确，进行更新单据或属性集的操作，比如某些单据或属性集字段的更新
     * 如果没有错，返回null或者""
     * 注意，如果该单据审批不涉及单据的更新，这个方法可以直接返回null。
     * @param ordeParamObj
     * @return
     */
    public abstract String getAuditVaildParamAndUpdateErrorMsg(JSONObject ordeParamObj);

    /**
     * 保存
     * @param params
     * @return
     */
    @Override
    public Result saveOrder(JSONObject params){
        String json = params.toJSONString();
        Object paramsObj = JSONObject.parse(json);
        // 驼峰转下划线
        JsonUtils.camel2UnderLine(paramsObj);
        T entity = JSONObject.parseObject(JSONObject.toJSONString(paramsObj), entityClass);
        // 提交校验
        Result validResult = vaildOrder(entity);
        if (validResult.getRetCode().equals(Result.RECODE_VALIDATE_ERROR)) {
            validResult.setErrMsg(validResult.getErrMsg() + ",当前临时单保存失败！");
            return validResult;
        }
        return insertData(entity);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result insertData(T entity){
        Result result = new Result();
        entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
        String orderNbr = "";
        try {
            entity.setEdmd_class(NullUtils.valueOf(getEntityEdmClassName()));
            if (StringUtil.isNullOrEmpty(entity.getId())) {
                entity.setCretime(new Date());
                entity.setOrde_date(entity.getCretime());
                //更新Session信息
                CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
                entity.setCreuser(sessionEntity.getEmployeeId());
                // 插入新记录 临时单
                orderNbr = commonService.getCode(NullUtils.valueOf(getOrderNbrTempPrefix()));
                entity.setOrde_nbr(orderNbr);
                // 插入主表
                String insertId = (String) ormService.insertSelective(entity);
                entity.setId(insertId);
                // 插入关联表
                insertEntitySet(entity);
            } else {
                entity.setModtime(new Date());
                entity.setOrde_date(entity.getModtime());
                //更新Session信息
                CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
                entity.setModuser(sessionEntity.getEmployeeId());
                // 更新主表记录
                ormService.updateSelective(entity);
                // 删除原先保存的关联表记录
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, entity.getId())));
                ormService.delete(entitySetClass, ormParam);
                // 插入新的关联表记录
                insertEntitySet(entity);
                // 取出记录中的单据单号返回
                ormParam.reset();
                ormParam.addColumn(NodeConstant.ID).addColumn(OrderProperty.ORDE_NBR);
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, entity.getId())));
                List<T> queryList = ormService.selectBeanList(entityClass, ormParam);
                if (null != queryList && queryList.size() > 0) {
                    T e1 = queryList.get(0);
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

    @Override
    public Result vaildOrder(T entity) {
        //TODO 校验保存单据
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);

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

        doVaildLogic(errMsgList, entity);
        if (errMsgList.size() > 0) {
            result.setErrMsg(Arrays.toString(errMsgList.toArray(new String[0])).replace("[", "").replace("]", ""));
            return result;
        }

        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }

    @Override
    public Result submitOrder(JSONObject params) {
        String json = params.toJSONString();
        Object paramsObj = JSONObject.parse(json);
        // 驼峰转下划线
        JsonUtils.camel2UnderLine(paramsObj);
        T entity = JSONObject.parseObject(JSONObject.toJSONString(paramsObj), entityClass);
        String paramsOrderId = entity.getId();
        // 提交校验
        Result validResult = vaildOrder(entity);
        if (validResult.getRetCode().equals(Result.RECODE_VALIDATE_ERROR)) {
            validResult.setErrMsg(validResult.getErrMsg() + ",当前临时单提交失败！");
            return validResult;
        }
        Result saveResult = insertData(entity);
        if (saveResult.getRetCode().equals(Result.RECODE_SUCCESS)) {
            //上述saveResult 如果有值一定是success的，否则ApplicationException是runtimeException，后面代码不会执行
            JSONObject saveData = (JSONObject) saveResult.getData();
            // 单据id
            String orderId = saveData.getString(NodeConstant.ID);
            // 单据定义id
            String orderRodeObj = saveData.getString("ordeRodeObj");
            // 提交流程和更新单据状态
            String submitNbr = submitFlow(orderId, orderRodeObj, StringUtil.isNullOrEmpty(paramsOrderId));
            saveData.put("ordeNbr", submitNbr);
            saveResult.setErrMsg("提交单据成功！");
        }
        return saveResult;

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
            T entity = entityClass.newInstance();
            String nbr = commonService.getCode(NullUtils.valueOf(getOrderNbrPrefix()));
            entity.setOrde_nbr(nbr);
            entity.setOrde_status(OrderConstants.ORDE_STATUS_2);
            entity.setId(ordeId);
            ormService.updateSelective(entity);
            return nbr;
        } catch (Exception e) {
            try {
                if (errorRollBack) {
                    // 如果提交流程出错，如果提交保存的数据没有单据id，说明是新插入的，手动删除，相当于回滚。
                    ormService.delete(entityClass, ordeId);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    @Override
    public Result loadOrder(String orderId) {
        Result result = new Result();
        JSONObject returnData = new JSONObject();
        try {
            JSONObject orderBaseData = loadOrderBaseData(orderId);
            if (null == orderBaseData) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg("未找到单据 '" + orderId + "'" + "相关信息");
                return result;
            }
            // 单据基本信息
            returnData.putAll(orderBaseData);
            // 单据entity信息
            JSONObject entityData = loadOrderEntityData(orderId);
            if(null!=entityData){
                returnData.putAll(entityData);
            }
            // 单据属性集信息
            JSONObject entitySetData = loadOrderEntitySetData(orderId);
            if(null!=entitySetData){
                returnData.putAll(entitySetData);
            }
            JsonUtils.underLine2Camel(returnData);
            result.setErrMsg("加载单据数据成功！");
            result.setData(returnData);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    protected JSONObject loadOrderBaseData(String orderId) throws Exception{
        OrmParamEx ormParamEx = new OrmParamEx();
        String orderColumnId = OrmParamEx.column(entityClass, NodeConstant.ID);
        ormParamEx.addColumn(orderColumnId)
                .addColumn(OrderProperty.ORDE_ADDUSER)
                .addColumn(OrderProperty.ORDE_DATE)
                .addColumn(OrderProperty.ORDE_DEPT)
                .addColumn(OrderProperty.ORDE_DUTY)
                .addColumn(OrderProperty.ORDE_STATUS)
                .addColumn(OrderProperty.ORDE_NBR)
                .addColumn(OrderProperty.ORDE_RODE_OBJ)
                .addColumn(EmployeeProperty.REMP_NO)
                .addColumn(EmployeeProperty.REMP_NAME)
                .addColumn(DepttreeProperty.MDEP_NAME)
                .addColumn(JobpositionProperty.RPOS_NAME);
        ormParamEx
                .leftJoin(EmployeeEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (EmployeeEntity.class, NodeConstant.ID, entityClass, OrderProperty.ORDE_ADDUSER), DataVailidEnum.NOMATTER)
                .leftJoin(DepttreeEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (DepttreeEntity.class, NodeConstant.ID, entityClass, OrderProperty.ORDE_DEPT), DataVailidEnum.NOMATTER)
                .leftJoin(JobpositionEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (JobpositionEntity.class, NodeConstant.ID, entityClass, OrderProperty.ORDE_DUTY), DataVailidEnum.NOMATTER)
        ;
        ormParamEx.setWhereExp(OrmParamEx.and(
                ormParamEx.getEqualXML(orderColumnId, orderId), ormParamEx.getEqualXML(OrmParamEx.column(entityClass, "is_del"), "0")
        ));
        List<Map<String, Object>> list = ormService.selectMapListEx(entityClass, ormParamEx);
        if (null != list && list.size() > 0) {
            JSONObject data = new JSONObject();
            Map<String, Object> map = list.get(0);
            // 单据id
            data.put(NodeConstant.ID, orderId);
            // 申请人id
            data.put(OrderProperty.ORDE_ADDUSER, NullUtils.valueOf(map.get(OrderProperty.ORDE_ADDUSER)));
            // 申请部门id
            data.put(OrderProperty.ORDE_DEPT, NullUtils.valueOf(map.get(OrderProperty.ORDE_DEPT)));
            // 申请岗位id
            data.put(OrderProperty.ORDE_DUTY, NullUtils.valueOf(map.get(OrderProperty.ORDE_DUTY)));
            // 单据单号
            data.put(OrderProperty.ORDE_NBR, NullUtils.valueOf(map.get(OrderProperty.ORDE_NBR)));
            // 单据状态
            data.put(OrderProperty.ORDE_STATUS, NullUtils.valueOf(map.get(OrderProperty.ORDE_STATUS)));
            // 单据定义id
            data.put(OrderProperty.ORDE_RODE_OBJ, NullUtils.valueOf(map.get(OrderProperty.ORDE_RODE_OBJ)));
            // 申请单据日期
            Object ordeDateStr = map.get(OrderProperty.ORDE_DATE);
            if (!StringUtil.isNullOrEmpty(ordeDateStr)) {
                Timestamp ordeDate = (Timestamp) ordeDateStr;
                data.put(OrderProperty.ORDE_DATE, DateUtil.formatDate(new Date(ordeDate.getTime())));
            } else {
                data.put(OrderProperty.ORDE_DATE, "");
            }
            // 申请人工号
            data.put(OrderProperty.ORDE_ADDUSER+"_no", NullUtils.valueOf(map.get(EmployeeProperty.REMP_NO)));
            // 申请人名字
            data.put(OrderProperty.ORDE_ADDUSER+"_name", NullUtils.valueOf(map.get(EmployeeProperty.REMP_NAME)));
            //  申请部门名称
            data.put(OrderProperty.ORDE_DEPT+"_name", NullUtils.valueOf(map.get(DepttreeProperty.MDEP_NAME)));
            // 申请岗位名称
            data.put(OrderProperty.ORDE_DUTY+"_name", NullUtils.valueOf(map.get(JobpositionProperty.RPOS_NAME)));
            return data;
        }
        return null;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result passOrder(String orderInstanceId, String handlerType) {
        try {
            Result result = new Result();
            switch (handlerType) {
                case WFHandlerTypeConstants.PASS: {
                    return handlePassLogic(orderInstanceId);
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

    protected Result updateOrderStatus(String orderInstanceId, String status) throws Exception {
        Result result = new Result();
        T entity = entityClass.newInstance();
        entity.setId(orderInstanceId);
        entity.setOrde_status(status);
        ormService.updateSelective(entity);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setErrMsg("更新单据状态成功");
        return result;
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
                if (Result.RECODE_SUCCESS.equals(res.getRetCode())) {
                    jsonObject.remove("error");
                    returnData.putAll(jsonObject);
                } else {
                    return res;
                }
            }
            auditSubmitFlowOrder(actInstanceId, opinion, auditKey);

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
    public void auditSubmitFlowOrder(String actInstanceId, String opinion, String auditKey) throws Exception {
        // 调用流程
        bizFormService.audit(actInstanceId, opinion, auditKey);
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public JSONObject auditUpdateOrder(JSONObject ordeParamObj) throws Exception {
        JSONObject returnData = new JSONObject();
        String errorMsg = getAuditVaildParamAndUpdateErrorMsg(ordeParamObj);
        Result result = new Result();
        if(!StringUtil.isNullOrEmpty(errorMsg)){
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(errorMsg);
            returnData.put("error", result);
        }else{
            result.setRetCode(Result.RECODE_SUCCESS);
            returnData.put("error", result);
        }
        return returnData;
    }

}