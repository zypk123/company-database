package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.base.BaseService;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.datetime.DateUtils;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.*;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.base.CurrentSessionEntity;
import com.huntkey.rx.purchase.common.model.currency.CurrencyDTO;
import com.huntkey.rx.purchase.common.model.puodorder.GodsGodsPladderSetbDTO;
import com.huntkey.rx.purchase.common.model.puodorder.PuodOrderSetDTO;
import com.huntkey.rx.purchase.common.model.puodorder.PuorderDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.GodsGodsPupricSetaDTO;
import com.huntkey.rx.purchase.common.model.relation.RelationDTO;
import com.huntkey.rx.purchase.common.model.settlemenet.SettleMenetDTO;
import com.huntkey.rx.purchase.common.model.taxrate.TaxrateDTO;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.provider.service.BizFormService;
import com.huntkey.rx.purchase.provider.service.CommonService;
import com.huntkey.rx.purchase.provider.service.PuorderService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.apache.commons.collections.map.HashedMap;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 采购订单接口实现类
 *
 * Created by fangyou on 2018年1月18日 0018.
 */

@Service
public class PuorderServiceImpl extends BaseService implements PuorderService{

    Logger logger = LoggerFactory.getLogger(PuorderServiceImpl.class);

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    private OrmService ormService;

    @Autowired
    private BizFormService bizFormService;

    @Autowired
    CommonService commonService;

    /**
     * 采购订单保存方法
     * @param puorderDTO
     * @param isSubmit true表示是提交，false表示只是保存
     * @return
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public Result saveOrderService(PuorderDTO puorderDTO , boolean isSubmit)
    {
        Result result = new Result();
        try
        {
            JSONObject returnData = new JSONObject();

            //1、获取session信息,成功获取方可进入下一步
            CurrentSessionEntity sessionEntity = getSession();

            //2、页面传来的数据校验，校验通过后返回下划线格式的数据，进入下一步
            Result checkResult = dto2Entity(puorderDTO , isSubmit);

            if(!Result.RECODE_SUCCESS.equals(checkResult.getRetCode()))
            {
                return checkResult;
            }

            /**
             * 3、操作主表：
             * 先根据isSubmit判断是提交还是保存：如果是提交，单据号用正式编号生成器生成；如果是保存，单据号用临时编号生成器生成
             * 判断传入的单据ID是否为空：如果为空则为新增；如果不为空，则为更新；
             */
            //给主表赋值
            PuorderEntity entity = (PuorderEntity) checkResult.getData();
            //根据session中的信息赋值企业对象
            entity.setEdmd_ente(sessionEntity.getEnterpriseId());
            //所属类
            entity.setEdmd_class(PuorderConstants.PUORDER);
            //modtime、moduser不管是新增还是修改都要赋值
            //修改时间
            entity.setModtime(new Date());
            //修改人
            entity.setModuser(sessionEntity.getEmployeeId());
            //单据Id
            String orderId = entity.getId();
            // 单据单号
            String ordeNbr = null;

            //默认是新增操作，只有当存在单据Id后，才做修改操作
            boolean isAddOpt = StringUtil.isNullOrEmpty(orderId);
            //主表记录的保存或更新（主表记录操作成功后，再保存明细列表）
            if(!isAddOpt)
            {
                /******修改采购订单操作*****/
                // 判断是否 直接提交
                if (isSubmit)
                {
                    // 直接提交，生成正式单号，并更新到数据库
                    ordeNbr = commonService.getCode(NumberConstants.PREFIX_PUORDER);
                    entity.setOrde_nbr(ordeNbr);
                }
                //主表修改操作
                ormService.updateSelective(entity);
            }
            else
            {
                /******新增采购订单操作*****/
                // 判断是否 直接提交 : isSubmit==true,直接提交，生成正式单号 ; 否则，生成临时单号
                ordeNbr = isSubmit==true ? commonService.getCode(NumberConstants.PREFIX_PUORDER) : commonService.getCode(NumberConstants.PREFIX_PU_TEMP_ORDER);
                //单据单号
                entity.setOrde_nbr(ordeNbr);
                //单据状态
                entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                //订单状态：1-临时
                entity.setPuod_status(PuorderConstants.PUOD_STATUS_1);
                //制单时间
                entity.setOrde_date(new Date());
                //创建时间
                entity.setCretime(new Date());
                //创建人
                entity.setCreuser(sessionEntity.getEmployeeId());
                //保存并返回ID
                orderId = (String) ormService.insertSelective(entity);
            }

            //根据单据ID查询单据单号
            List<PuorderEntity> puorderEntities = this.findPuorderById(orderId);
            if(!puorderEntities.isEmpty()){
                ordeNbr = puorderEntities.get(0).getOrde_nbr();
            }

            //4、采购订单明细表数据维护：先根据单据ID（pid）删除记录，再新增记录
            //获取订单明细
            List<PuodPuodOrderSetaEntity> puodOrderSet = entity.getPuod_order_set();
            //4-1：如果是更新，根据PID查询明细表，并删除
            if(!isAddOpt)
            {
                OrmParam ormParam = new OrmParam();
                ormParam.addColumn(EdmSysColumn.ID);
                ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, orderId));
                List<PuodPuodOrderSetaEntity> orderSetList = ormService.selectBeanList(PuodPuodOrderSetaEntity.class, ormParam);
                if (!orderSetList.isEmpty())
                {
                    List<String> ids = new ArrayList<>();
                    orderSetList.forEach(item -> {
                        ids.add(item.getId());
                    });
                    ormParam = new OrmParam();
                    ormParam.setWhereExp(ormParam.getInXML(EdmSysColumn.ID, ids.toArray()));
                    ormService.delete(PuodPuodOrderSetaEntity.class, ormParam);
                }
            }

            //4-2：新增订单明细记录到订单明细表
            //接收保存订单明细列表返回的id
            List<String> saveOrderSetIdList = new ArrayList<>();

            //保存操作,并返回异动列表最新的id
            for(PuodPuodOrderSetaEntity setaEntity : puodOrderSet)
            {
                //根据前台传入的相关字段值来填充数据库中其他字段的值
                //填充价格类型字段的值
                List<GodsGodsPupricSetaEntity> godsPupricSet = getGodsPupricSet(setaEntity.getPuod_gods(),entity.getPuod_park(),DateUtil.parseFormatDate(DateUtil.parseFormatDate(new Date(),DateUtils.DATETIME_FORMAT),DateUtils.DATETIME_FORMAT));
                if(!godsPupricSet.isEmpty())
                {
                    GodsGodsPupricSetaEntity godsPupricSetaEntity = godsPupricSet.get(0);
                    //价格类型默认写入0，单价为阶梯报价的写入1，为修改的单价写入2；
                    if(setaEntity.getPuod_price().compareTo(setaEntity.getPuod_price_src())!=0){
                        //单价和原单价不等，说明是修改的单价
                        setaEntity.setPuod_price_type(PuorderConstants.PUOD_PRICE_TYPE_2);
                    }else{
                        //是否阶梯报价：1-是；0-否
                        if(godsPupricSetaEntity.getGods_pisladder() == PuorderConstants.PUOD_PRICE_TYPE_1)
                        {
                            setaEntity.setPuod_price_type(PuorderConstants.PUOD_PRICE_TYPE_1);
                        }
                        else
                        {
                            setaEntity.setPuod_price_type(PuorderConstants.PUOD_PRICE_TYPE_0);
                        }
                    }
                }
                setaEntity.setId(null);
                setaEntity.setPid(orderId);
                setaEntity.setClassName(PuorderConstants.PUODORDERSET);
                setaEntity.setCreuser(sessionEntity.getEmployeeId());
                setaEntity.setCretime(new Date());
                setaEntity.setModuser(sessionEntity.getEmployeeId());
                setaEntity.setModtime(new Date());

                //是否补货项默认为0
                setaEntity.setPuod_isrepl(PuorderConstants.PUOD_ISREPL_0);
                //状态默认为1
                setaEntity.setPuod_istatus(PuorderConstants.PUOD_ISTATUS_1);
                //已收货数量、待收货数量、已退货数量、待退货数量全部写入0
                //已退货数量
                setaEntity.setPuod_rqty(new BigDecimal(0));
                //待退货数量
                setaEntity.setPuod_rwqty(new BigDecimal(0));
                //已收货数量
                setaEntity.setPuod_dqty(new BigDecimal(0));
                //待收货数量
                setaEntity.setPuod_dwqty(new BigDecimal(0));

                String orderSetId = ormService.insert(setaEntity).toString();
                saveOrderSetIdList.add(orderSetId);
            }

            if(saveOrderSetIdList.isEmpty())
            {
                return getResult(result , Result.RECODE_ERROR, "保存采购订单明细失败");
            }
            else
            {
                //返回给前端的操作结果，采购订单明细中的记录ID
                returnData.put(PuorderConstants.PUOD_ORDER_SET_ID, saveOrderSetIdList);
            }

            //设置返回数据
            returnData.put(PuorderConstants.ORDER_ID, orderId);
            returnData.put(OrderConstants.ORDE_NBR_UPCASE, ordeNbr);
            result.setData(returnData);
            result.setErrMsg("保存采购订单成功");
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getResult(result , Result.RECODE_ERROR, "保存或提交采购订单失败，" + e.getMessage());
        }
        return result;
    }


    /**
     * 获取session用户信息
     * @return
     */
    private CurrentSessionEntity getSession()
    {
        CurrentSessionEntity sessionEntity = null;
        try
        {
            sessionEntity = bizFormService.getCurrentSessionInfo();
            //先判断有没有登录，没有登录要先进行登录
            if(null == sessionEntity)
            {
                throw new ApplicationException(Result.RECODE_ERROR , "登录超时，请重新登录");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR,"登录超时，请重新登录");
        }
        return sessionEntity;
    }

    /**
     * 将DTO对象转化为Entity，验证通过后返回Entity
     * @param puorderDTO
     * @return
     * @throws Exception
     */
    private Result dto2Entity(PuorderDTO puorderDTO , boolean isSubmit)
    {
        Result result = new Result();
        try
        {
            // 驼峰转下划线
            PuorderEntity puorder =JSONObject.parseObject(JSONObject.toJSONString(puorderDTO),PuorderEntity.class);
            //入参校验
            //1、采购订单基本信息校验
            if (StringUtil.isNullOrEmpty(puorder.getOrde_rode_obj()))
            {
                return getResult(result , Result.RECODE_ERROR,"必须传递参数ordeRodeObj的值作为单据类型定义id保存");
            }
            if (StringUtil.isNullOrEmpty(puorder.getOrde_adduser()))
            {
                return getResult(result , Result.RECODE_ERROR,"必须传递参数ordeAddUser的值作为制单人id保存");
            }
            if (StringUtil.isNullOrEmpty(puorder.getOrde_duty()))
            {
                return getResult(result , Result.RECODE_ERROR,"必须传递参数ordeDuty的值作为制单岗位id保存");
            }
            if (StringUtil.isNullOrEmpty(puorder.getOrde_dept()))
            {
                return getResult(result , Result.RECODE_ERROR,"必须传递参数ordeDept的值作为制单部门id保存");
            }
            if (StringUtil.isNullOrEmpty(puorder.getPuod_code_supp()))
            {
                return getResult(result , Result.RECODE_ERROR,"供应商填写不完整，不可" + (isSubmit==true?"提交":"保存"));
            }
            if (StringUtil.isNullOrEmpty(puorder.getPuod_park()))
            {
                return getResult(result , Result.RECODE_ERROR,"园区填写不完整，不可" + (isSubmit==true?"提交":"保存"));
            }
            if (StringUtil.isNullOrEmpty(puorder.getPuod_corp()))
            {
                return getResult(result , Result.RECODE_ERROR,"采购法人填写不完整，不可" + (isSubmit==true?"提交":"保存"));
            }
            if (StringUtil.isNullOrEmpty(puorder.getPuod_emp()))
            {
                return getResult(result , Result.RECODE_ERROR,"采购员填写不完整，不可" + (isSubmit==true?"提交":"保存"));
            }
            if (StringUtil.isNullOrEmpty(puorder.getPuod_priceff_type()))
            {
                return getResult(result , Result.RECODE_ERROR,"生效方式填写不完整，不可" + (isSubmit==true?"提交":"保存"));
            }
            if (StringUtil.isNullOrEmpty(puorder.getPuod_curr()))
            {
                return getResult(result , Result.RECODE_ERROR,"币别填写不完整，不可" + (isSubmit==true?"提交":"保存"));
            }
            if (StringUtil.isNullOrEmpty(puorder.getPuod_settle_supp()))
            {
                return getResult(result , Result.RECODE_ERROR,"结算方式填写不完整，不可" + (isSubmit==true?"提交":"保存"));
            }
            if (StringUtil.isNullOrEmpty(puorder.getPuod_deli_addr())
                    || StringUtil.isNullOrEmpty(puorder.getPuod_deli_linkman())
                    || StringUtil.isNullOrEmpty(puorder.getPuod_deli_contway()))
            {
                return getResult(result , Result.RECODE_ERROR,"交货地址填写不完整，不可" + (isSubmit==true?"提交":"保存"));
            }

            //供应商状态校验：不能为关闭状态
            // 根据ID去伙伴类中查找供应商数据集的状态【伙伴类和供应商数据集是1对1的关系】
            String sumoId = puorder.getPuod_code_supp();
            RelationEntity relationEntity = puorder.loadPuod_code_supp();
            if (null == relationEntity){
                return getResult(result , Result.RECODE_ERROR,"没有找到供应商对象["+sumoId+"]对应的供应商，不可" + (isSubmit==true?"提交":"保存"));
            }

            List<RelaRelaSupplierSetaEntity> relaSupplierSet = relationEntity.getRela_supplier_set();
            if(null != relaSupplierSet && !relaSupplierSet.isEmpty()){
                if(SupplierMaintOrderConstants.RELA_STAT_3.equals(relationEntity.getRela_supplier_set().get(0).getRela_stat_supp())){
                    return getResult(result , Result.RECODE_ERROR,relationEntity.getRela_name() +"为关闭状态，不可" + (isSubmit==true?"提交":"保存"));
                }
            }

            //2、采购订单明细信息校验：物品列表信息校验
            List<PuodPuodOrderSetaEntity> list = puorder.getPuod_order_set();

            if(null == list || list.isEmpty())
            {
                return getResult(result , Result.RECODE_ERROR,"没有找到任何订单明细信息，不可" + (isSubmit==true?"提交":"保存"));
            }

            for (PuodPuodOrderSetaEntity puodOrderSetaEntity : list){
                //物品ID
                String godsId = puodOrderSetaEntity.getPuod_gods();

                if(StringUtil.isNullOrEmpty(godsId)){
                    return getResult(result , Result.RECODE_ERROR,"物品编号填写不完整，不可" + (isSubmit==true?"提交":"保存"));
                }
                if(StringUtil.isNullOrEmpty(puodOrderSetaEntity.getPuod_price())){
                    return getResult(result , Result.RECODE_ERROR,"单价填写不完整，不可" + (isSubmit==true?"提交":"保存"));
                }
                if(StringUtil.isNullOrEmpty(puodOrderSetaEntity.getPuod_qty())){
                    return getResult(result , Result.RECODE_ERROR,"订货数量填写不完整，不可" + (isSubmit==true?"提交":"保存"));
                }
                if(null == puodOrderSetaEntity.getPuod_date()){
                    return getResult(result , Result.RECODE_ERROR,"交货日期填写不完整，不可" + (isSubmit==true?"提交":"保存"));
                }
                if(puodOrderSetaEntity.getPuod_date().before(DateUtil.parseFormatDate(DateUtil.parseFormatDate(new Date(),DateUtils.DATE_FORMAT)))){
                    return getResult(result , Result.RECODE_ERROR,"交货日期小于"+ DateUtils.getDate(new Date(),DateUtils.DATE_FORMAT) +"，不可" + (isSubmit==true?"提交":"保存"));
                }

                OrmParam ormParam = new OrmParam();
                ormParam.addColumn(EdmSysColumn.ID);
                ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID , puodOrderSetaEntity.getPuod_gods()));
                List<GoodsEntity> goodsEntityList = ormService.selectBeanList(GoodsEntity.class , ormParam);
                if (goodsEntityList.isEmpty())
                {
                    return getResult(result , Result.RECODE_ERROR,"没有找到物品对象["+godsId+"]对应的物品，不可" + (isSubmit==true?"提交":"保存"));
                }
                GoodsEntity goodsEntity = goodsEntityList.get(0);

                //物品状态校验：不能为IA作废状态
                ormParam = new OrmParam();
                ormParam.addColumn(GodsGodsParkSetaProperty.GODS_STATUS);
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID , godsId) , ormParam.getEqualXML(GodsGodsParkSetaProperty.GODS_PARK,puorder.getPuod_park())));
                List<GodsGodsParkSetaEntity> godsParkSetEntityList = ormService.selectBeanList(GodsGodsParkSetaEntity.class , ormParam);
                if(!godsParkSetEntityList.isEmpty()){
                    if(GoodsConstants.GODS_STATUS_IA.equals(godsParkSetEntityList.get(0).getGods_status())){
                        return getResult(result , Result.RECODE_ERROR,goodsEntity.getGods_name()+"为作废状态，不可" + (isSubmit==true?"提交":"保存"));
                    }
                }
            }
            result.setData(puorder);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getResult(result , Result.RECODE_ERROR,"校验采购订单信息异常，" + e.getMessage());
        }
        return result;
    }

    /**
     * 采购订单管理列表查询方法
     * @param ordeNbr  订单编号
     * @param sumoNameValue     供应商名称
     * @param startTime     收货日期：开始时间
     * @param endTime       收货日期：结束时间
     * @param puodStatus    订单状态
     * @param parkId        园区Id
     * @param currId        币别Id
     * @param deptNameValue      采购部门名称
     * @param empNameValue       采购员名称
     * @param pageNum       当前页码
     * @param pageSize      每页最大记录数
     * @return
     */
    @Override
    public Result queryOrderListService(String ordeNbr, String sumoNameValue, String startTime, String endTime, String puodStatus, String parkId,String currId,String deptNameValue,String empNameValue, int pageNum, int pageSize)
    {
        Result result = new Result();

        //查询供应商名称模糊查询出供应商id集合
        List<String> sumoIds = new ArrayList<>();
        if(!StringUtil.isNullOrEmpty(sumoNameValue))
        {
            sumoIds = getIdsByName(RelationProperty.RELA_NAME , sumoNameValue , RelationEntity.class);
            if(sumoIds.isEmpty())
            {
                return emptyPageQueryResult(pageNum,pageSize);
            }
        }

        //查询采购部门名称模糊查询部门id集合
        List<String> deptIds = new ArrayList<>();
        if(!StringUtil.isNullOrEmpty(deptNameValue))
        {
            deptIds = getIdsByName(DepttreeProperty.MDEP_NAME , deptNameValue , DepttreeEntity.class);
            if(deptIds.isEmpty())
            {
                return emptyPageQueryResult(pageNum,pageSize);
            }
        }

        //查询采购员名称模糊查询采购员id集合
        List<String> empIds = new ArrayList<>();
        if(!StringUtil.isNullOrEmpty(empNameValue))
        {
            empIds = getIdsByName(EmployeeProperty.REMP_NAME , empNameValue , EmployeeEntity.class);
            if(empIds.isEmpty())
            {
                return emptyPageQueryResult(pageNum,pageSize);
            }
        }

        //key是pid，value也是pid
        Map<String,String> pidMap = new HashMap();

        if(!StringUtil.isNullOrEmpty(startTime) || !StringUtil.isNullOrEmpty(endTime))
        {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.PID);
            String whereExp = "";
            if(!StringUtil.isNullOrEmpty(startTime) && StringUtil.isNullOrEmpty(endTime))
            {
                whereExp = ormParam.getGreaterThanAndEqualXML(PuodPuodOrderSetaProperty.PUOD_DATE , startTime);
            }
            else if(StringUtil.isNullOrEmpty(startTime) && !StringUtil.isNullOrEmpty(endTime))
            {
                whereExp = ormParam.getLessThanAndEqualXML(PuodPuodOrderSetaProperty.PUOD_DATE , endTime);
            }
            else
            {
                whereExp = OrmParam.and(ormParam.getGreaterThanAndEqualXML(PuodPuodOrderSetaProperty.PUOD_DATE,startTime),ormParam.getLessThanAndEqualXML(PuodPuodOrderSetaProperty.PUOD_DATE,endTime));
            }
            ormParam.setWhereExp(whereExp);
            List<PuodPuodOrderSetaEntity> puodOrderSetaEntityList = null;
            try
            {
                puodOrderSetaEntityList = ormService.selectBeanList(PuodPuodOrderSetaEntity.class , ormParam);
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return getResult(result , Result.RECODE_ERROR , "查询采购订单失败，" + e.getMessage());
            }

            for(PuodPuodOrderSetaEntity entity : puodOrderSetaEntityList)
            {
                pidMap.put(entity.getPid(),entity.getPid());
            }
        }

        List<String> pids = new ArrayList<>();
        for (Map.Entry entry : pidMap.entrySet())
        {
            pids.add(entry.getKey().toString());
        }

        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID)
                .addColumn(OrderProperty.ORDE_NBR)
                .addColumn(PuorderProperty.PUOD_CODE_SUPP)
                .addColumn(PuorderProperty.PUOD_SNAME_SUPP)
                .addColumn(PuorderProperty.PUOD_PARK)
                .addColumn(PuorderProperty.PUOD_CURR)
                .addColumn(PuorderProperty.PUOD_STATUS)
                .addColumn(PuorderProperty.PUOD_EMP)
                .addColumn(PuorderProperty.PUOD_DEPT)
                .addColumn(OrderProperty.ORDE_STATUS)
                .addColumn(OrderProperty.ORDE_ADDUSER)
                .addColumn(OrderProperty.ORDE_DATE);

        List<String> conditions = new ArrayList<>();

        //单据id
        if(!pids.isEmpty()){
            conditions.add(ormParam.getInXML(EdmSysColumn.ID , pids.toArray()));
        }
        //订单编号
        if(!StringUtil.isNullOrEmpty(ordeNbr))
        {
            conditions.add(ormParam.getMatchMiddleXML(OrderProperty.ORDE_NBR,ordeNbr));
        }
        //供应商
        if(!StringUtil.isNullOrEmpty(sumoNameValue))
        {
            conditions.add(ormParam.getInXML(PuorderProperty.PUOD_CODE_SUPP , sumoIds.toArray()));
        }
        //订单状态或单据
        if(!StringUtil.isNullOrEmpty(puodStatus))
        {
            conditions.add(OrmParam.or(ormParam.getEqualXML(OrderProperty.ORDE_STATUS , puodStatus),ormParam.getEqualXML(PuorderProperty.PUOD_STATUS , puodStatus)));
        }
        //园区
        if(!StringUtil.isNullOrEmpty(parkId)){
            conditions.add(ormParam.getEqualXML(PuorderProperty.PUOD_PARK , parkId));
        }
        //币别
        if(!StringUtil.isNullOrEmpty(currId)){
            conditions.add(ormParam.getEqualXML(PuorderProperty.PUOD_CURR , currId));
        }
        //采购部门
        if(!StringUtil.isNullOrEmpty(deptNameValue))
        {
            conditions.add(ormParam.getInXML(PuorderProperty.PUOD_DEPT , deptIds.toArray()));
        }
        //采购员
        if(!StringUtil.isNullOrEmpty(empNameValue))
        {
            conditions.add(ormParam.getInXML(PuorderProperty.PUOD_EMP , empIds.toArray()));
        }

        ormParam.setWhereExp(OrmParam.and(conditions));
        ormParam.setPageSize(pageSize);
        ormParam.setPageNo(pageNum);
        ormParam.setOrderExp(SQLSortEnum.DESC , EdmSysColumn.CRETIME);
        Pagination<PuorderEntity> pagination = null;
        try
        {
            pagination = ormService.selectPagedBeanList(PuorderEntity.class , ormParam);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getResult(result , Result.RECODE_ERROR , "查询采购订单失败，" + e.getMessage());
        }

        List<PuorderEntity> puorderEntityList = pagination.getList();
        //分页查询返回的结果集
        JSONArray resultData = new JSONArray();
        if(!puorderEntityList.isEmpty()){
            puorderEntityList.forEach(item->{
                Object param = JSONObject.parse(JSON.toJSONString(item));
                JsonUtils.underLine2Camel(param);
                JSONObject jsonObject = JSONObject.parseObject(JSON.toJSONString(param));

                OrmParam ormParam1 = new OrmParam();
                ormParam1.addColumn(EdmSysColumn.ID).addColumn(EdmSysColumn.PID).addColumn(PuodPuodOrderSetaProperty.PUOD_QTY).addColumn(PuodPuodOrderSetaProperty.PUOD_PRICE);
                ormParam1.setWhereExp(ormParam1.getEqualXML(EdmSysColumn.PID , jsonObject.get(EdmSysColumn.ID)));
                try {
                    List<PuodPuodOrderSetaEntity> puodOrderSetaEntityList = ormService.selectBeanList(PuodPuodOrderSetaEntity.class , ormParam1);

                    BigDecimal tMoney = new BigDecimal(0);
                    for(PuodPuodOrderSetaEntity entity : puodOrderSetaEntityList){
                        BigDecimal qty = entity.getPuod_qty();
                        BigDecimal price = entity.getPuod_price();
                        tMoney = tMoney.add(qty.multiply(price).setScale(7,BigDecimal.ROUND_HALF_UP));
                    }
                    jsonObject.put(PuorderConstants.PUOD_T_MONEY , tMoney);

                    //查询供应商代码
                    RelationEntity relationEntity = item.loadPuod_code_supp();
                    jsonObject.put(PuorderConstants.PUOD_SUMO_CODE , null != relationEntity ? relationEntity.getRela_code() : "");

                    //查询园区名称
                    ParkEntity parkEntity = item.loadPuod_park();
                    jsonObject.put(PuorderConstants.PUOD_PARK_NAME , null != parkEntity ? parkEntity.getRpak_name() : "");

                    //查询币别Code
                    CurrencyEntity currencyEntity = item.loadPuod_curr();
                    jsonObject.put(PuorderConstants.PUOD_CURR_CODE , null != currencyEntity ? currencyEntity.getCurr_code() : "");

                    //查询采购员名称
                    EmployeeEntity employeeEntity = item.loadPuod_emp();
                    jsonObject.put(PuorderConstants.PUOD_EMP_NAME , null != employeeEntity ? employeeEntity.getRemp_name() : "");

                    //查询申请人名称
                    EmployeeEntity employeeEntity1 = item.loadOrde_adduser();
                    jsonObject.put(PuorderConstants.ORDE_ADDUSER_NAME , null != employeeEntity1 ? employeeEntity1.getRemp_name() : "");

                    //查询采购部门名称
                    DepttreeEntity depttreeEntity = item.loadPuod_dept();
                    jsonObject.put(PuorderConstants.PUOD_DEPT_NAME , null != depttreeEntity ? depttreeEntity.getMdep_name() : "");

                    resultData.add(jsonObject);
                } catch (Exception e) {
                    e.printStackTrace();
                    throw new ApplicationException(Result.RECODE_ERROR , "查询采购订单失败，" + e.getMessage());
                }
            });
        }
        else
        {
            return emptyPageQueryResult(pageNum,pageSize);
        }
        //组装返回的数据
        JSONObject obj = new JSONObject();
        obj.put("pageNum", pageNum);
        obj.put("pageSize", pageSize);
        obj.put("total", pagination.getTotal());
        obj.put("list", resultData);
        result.setData(obj);
        return result;
    }

    /**
     * 封装查询单据列表无数据的情况下返回的结果
     * @param pageNum
     * @param pageSize
     * @return
     */
    private Result emptyPageQueryResult(int pageNum , int pageSize){
        Result result = new Result();
        JSONObject obj = new JSONObject();
        obj.put("pageNum", pageNum);
        obj.put("pageSize", pageSize);
        obj.put("total", 0);
        obj.put("list", new ArrayList<>());
        result.setData(obj);
        return result;
    }

    /**
     * 根据名称模糊查询出id
     * @param paramColumn
     * @param columnValue
     * @param c
     * @return
     */
    private List<String> getIdsByName(String paramColumn , String columnValue , Class c ) {
        List<String> ids = new ArrayList<>();
        //设置查询参数
        HashMap paramMap = new HashMap<>();
        paramMap.put(paramColumn + ",matchMiddle" , columnValue);
        //设置返回参数
        List<String> columnList = new ArrayList<>();;
        columnList.add(EdmSysColumn.ID);
        List<Map<String,Object>> mapList= getBaseInfoByConditions(paramMap,columnList,c);

        if(!mapList.isEmpty())
        {
            Map<String,Object> baseMap = mapList.get(0);
            baseMap.forEach((k,v)->{
                ids.add(v.toString());
            });
        }
        return ids;
    }

    @Transactional(rollbackFor = Exception.class)
    public void submit(String orderDefId , String orderInstanceId) throws Exception {
        bizFormService.submitWorkFlow(orderDefId, orderInstanceId);
        //提交流程成功了才更新单据状态
        updatePuorder(orderInstanceId);
    }

    /**
     * 采购订单提交方法
     *
     * @param puorderDTO
     * @return
     */
    @Override
    public Result submitOrder(PuorderDTO puorderDTO)
    {
        Result result = new Result();
        try
        {
            String orderIdValue = puorderDTO.getId();
            //1、提交之前，先要判断该单据是否已经提交过了，如果已经提交过了，不给提交，以防止重复提交
            if(!StringUtil.isNullOrEmpty(orderIdValue) && !StringUtils.isBlank(orderIdValue))
            {
                Result checkResult = checkIsSubmit(puorderDTO.getId());
                if(!Result.RECODE_SUCCESS.equals(checkResult.getRetCode()))
                {
                    return checkResult;
                }
            }

            //2、保存采购订单，如果存在单据Id，则修改，没有则新增
            Result saveResult = saveOrderService(puorderDTO , true);
            if (!Result.RECODE_SUCCESS.equals(saveResult.getRetCode()))
            {
                return saveResult;
            }

            //3、提交工作流
            JSONObject resultObj = (JSONObject) saveResult.getData();
            String orderInstanceId = resultObj.getString(PuorderConstants.ORDER_ID);
            //提交流程方法
            String orderDefId = puorderDTO.getOrdeRodeObj();
            //单据单号
            String ordeNbr = resultObj.getString(OrderConstants.ORDE_NBR_UPCASE);

            if(StringUtil.isNullOrEmpty(orderInstanceId) || StringUtil.isNullOrEmpty(orderDefId))
            {
                return getResult(result , Result.RECODE_ERROR,"单据实例ID或单据定义对象ID为空，不能提交流程");
            }

            submit(orderDefId, orderInstanceId);

            //封装返回值
            JSONObject jo = new JSONObject();
            jo.put(PuorderConstants.ORDER_ID,orderInstanceId);
            jo.put(OrderConstants.ORDE_NBR_UPCASE,ordeNbr);
            result.setData(jo);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getResult(result , Result.RECODE_ERROR,e.getMessage());
        }
        return result;
    }

    @Transactional(rollbackFor = Exception.class)
    public void updatePuorder(String orderId) throws Exception {
        //提交流程成功了才更新单据状态
        PuorderEntity entity = new PuorderEntity();
        entity.setId(orderId);
        //单据状态修改为：待审
        entity.setOrde_status(OrderConstants.ORDE_STATUS_2);
        //订单状态也修改为：待审
        entity.setPuod_status(PuorderConstants.PUOD_STATUS_2 );
        entity.setModtime(new Date());
        //TODO 添加修改人
        ormService.updateSelective(entity);
    }

    /**
     * 根据Id删除采购订单
     * @param id
     * @return
     */
    @Transactional
    @Override
    public Result deleteOrderService(String id)
    {
        Result result = new Result();

        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID , id));
        try
        {
            //删除明细表数据
            ormService.delete(PuodPuodOrderSetaEntity.class , ormParam);
            //删除采购订单表数据
            ormService.delete(PuorderEntity.class , id);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getResult(result , Result.RECODE_ERROR, "删除采购订单失败，" + e.getMessage());
        }
        result.setErrMsg("删除采购订单成功");
        return result;
    }

    /**
     * 审核单据
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @Transactional
    @Override
    public Result approve(String orderInstanceId,String handlerType)
    {
        Result result = new Result();
        PuorderEntity entity = new PuorderEntity();
        entity.setId(orderInstanceId);
        logger.info(String.format("采购订单approve方法：orderInstanceId=%s   handlerType=%s" , orderInstanceId , handlerType));
        switch (handlerType)
        {
            case WFHandlerTypeConstants.PASS:
            {
                //通过，修改单据状态和订单状态
                pass(orderInstanceId);
                break;
            }
            case WFHandlerTypeConstants.REVOKE:
            {
                try {
                    //撤销：修改单据状态、订单为临时单状态
                    entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                    entity.setPuod_status(PuorderConstants.PUOD_STATUS_1);
                    entity.setModtime(new Date());
                    update(entity);
                    //撤销：修改订单明细状态为1-正常状态
                    updatePuorderSetStatus(orderInstanceId, PuorderConstants.PUOD_ISTATUS_1);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return getResult(result , Result.RECODE_ERROR , "撤销审核采购订单失败，" + e.getMessage());
                }
                break;
            }
            case WFHandlerTypeConstants.RETURN_BACK:
            {
                try {
                    //退回：修改单据状态为退回状态
                    entity.setOrde_status(OrderConstants.ORDE_STATUS_6);
                    entity.setPuod_status(PuorderConstants.PUOD_STATUS_6);
                    entity.setModtime(new Date());
                    update(entity);
                    //退回修改订单明细状态为0-拒绝状态
                    updatePuorderSetStatus(orderInstanceId , PuorderConstants.PUOD_ISTATUS_0);
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                    return getResult(result , Result.RECODE_ERROR , "退回审核采购订单失败，" + e.getMessage());
                }
                break;
            }
            default:
            {
                break;
            }
        }
        return result;
    }

    /**
     * 根据单据id修改订单明细项状态
     * @param pid
     * @param status
     */
    private void updatePuorderSetStatus(String pid , String status) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID);
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID , pid));
        List<PuodPuodOrderSetaEntity> entityList = ormService.selectBeanList(PuodPuodOrderSetaEntity.class,ormParam);
        for(PuodPuodOrderSetaEntity entity : entityList){
            entity.setPuod_istatus(status);
            entity.setModtime(new Date());
            //TODO 添加修改人

            ormService.updateSelective(entity);
        }
    }

    /**
     *   采购订单审批通过接口
     * @param id 单据类对象Id
     * @return
     */
    @Override
    public void pass(String id)
    {
        try
        {
            PuorderEntity updateEntity = new PuorderEntity();
            updateEntity.setId(id);
            //修改采购订单单据状态为5-通过
            updateEntity.setOrde_status(OrderConstants.ORDE_STATUS_5);
            //修改采购订单订单状态为5-待收货
            updateEntity.setPuod_status(PuorderConstants.PUOD_STATUS_5);
            updateEntity.setModtime(new Date());
            //TODO 添加修改人

            ormService.updateSelective(updateEntity);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR,String.format("采购订单[%s]审批失败,%s", id , e.getMessage()));
        }
    }

    /**
     * 根据ID判断单据是否已经提交过了，如果提交过了不能再次提交
     * @param orderId
     * @return
     */
    @Override
    public Result checkIsSubmit(String orderId)
    {
        Result result = new Result();
        try
        {
            //判断单据是否已经提交，如果已经提交了，不能再次提交
            PuorderEntity entity = ormService.load(PuorderEntity.class,orderId);

            if(null != entity && OrderConstants.ORDE_STATUS_2.equals(entity.getOrde_status()))
            {
                return getResult(result , Result.RECODE_ERROR, String.format("单据%s已经提交过，不能再次提交" , entity.getOrde_nbr()));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getResult(result , Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    /**
     * 根据单据ID加载单据信息
     * @param id
     * @return
     */
    @Override
    public Result loadOrder(String id) {
        {
            //待返回的结果
            Result result = new Result();
            // 待返回的Data数据
            JSONObject returnData = new JSONObject();

            //查询采购订单基本信息
            JSONObject baseInfoData = loadOrderBaseInfo(id);

            if (baseInfoData.isEmpty())
            {
                return getResult(result , Result.RECODE_ERROR, String.format("未找到单据id为%s的采购订单信息",id));
            }

            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID)
                    .addColumn(EdmSysColumn.PID)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_RATE)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_ISREPL)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_GODS_MODEL)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_UM_GROUP)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_ISTATUS)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_QTY)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_UM)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_PRICE_TYPE)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_GODS)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_RQTY)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_PRICE_SRC)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_RWQTY)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_PRICE)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_GODS_NAME)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_QTY_MIN)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_DQTY)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_DWQTY)
                    .addColumn(PuodPuodOrderSetaProperty.PUOD_DATE);
            ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID , id));

            List<PuodOrderSetDTO> puodOrderSet = new ArrayList<>();
            try {
                List<PuodPuodOrderSetaEntity> puodOrderSetEntityList = ormService.selectBeanList(PuodPuodOrderSetaEntity.class , ormParam);
                puodOrderSet = JSONArray.parseArray(JSONArray.toJSONString(puodOrderSetEntityList,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty),PuodOrderSetDTO.class);
                //总数量
                BigDecimal totalAmount = new BigDecimal(0);
                //总金额
                BigDecimal totalMoney = new BigDecimal(0);
                //原总金额
                BigDecimal oldTotalMoney = new BigDecimal(0);
                //总税额
                BigDecimal totalTax = new BigDecimal(0);

                for(PuodOrderSetDTO dto : puodOrderSet){
                    //计算行记录总金额
                    BigDecimal tMoney = dto.getPuodPrice().multiply(dto.getPuodQty()).setScale(7,BigDecimal.ROUND_HALF_UP);
                    BigDecimal oldTMoney = dto.getPuodPriceSrc().multiply(dto.getPuodQty()).setScale(7,BigDecimal.ROUND_HALF_UP);
                    dto.settMoney(tMoney);
                    //计算总订单总数量和总金额
                    totalAmount = totalAmount.add(dto.getPuodQty());
                    totalMoney = totalMoney.add(tMoney);
                    oldTotalMoney = oldTotalMoney.add(oldTMoney);

                    //查询并返回物品编码
                    OrmParam ormParam1 = new OrmParam();
                    ormParam1.addColumn(GoodsProperty.GODS_CODE);
                    ormParam1.setWhereExp(ormParam1.getEqualXML(EdmSysColumn.ID , dto.getPuodGods()));
                    List<GoodsEntity> list = ormService.selectBeanList(GoodsEntity.class , ormParam1);
                    if(!list.isEmpty())
                    {
                        dto.setPuodGodsCode(list.get(0).getGods_code());
                    }

                    //查询税率值
                    ormParam1 = new OrmParam();
                    ormParam1.addColumn(TaxrateProperty.TAXR_NAME)
                            .addColumn(TaxrateProperty.TAXR_DETAIL);
                    ormParam1.setWhereExp(ormParam1.getEqualXML(EdmSysColumn.ID , dto.getPuodRate()));
                    List<TaxrateEntity> taxrateEntities = ormService.selectBeanList(TaxrateEntity.class , ormParam1);

                    if(!taxrateEntities.isEmpty())
                    {
                        TaxrateEntity taxrateEntity = taxrateEntities.get(0);
                        //设置税率名称
                        dto.setPuodRateName(taxrateEntity.getTaxr_name());
                        //设置税率值
                        dto.setPuodRateValue(taxrateEntity.getTaxr_detail());
                        //计算税额
                        BigDecimal tTax = tMoney.multiply(taxrateEntity.getTaxr_detail()).setScale(7,BigDecimal.ROUND_HALF_UP);
                        dto.settTax(tTax);
                        //计算总税额
                        totalTax = totalTax.add(tTax);
                    }
                }
                //总数量
                baseInfoData.put(PuorderConstants.TOTAL_AMOUNT,totalAmount);
                //总金额
                baseInfoData.put(PuorderConstants.TOTAL_MONEY,totalMoney);
                // 价格调整差异=总金额-原总金额
                BigDecimal totalMoneyChange = totalMoney.subtract(oldTotalMoney).setScale(2,BigDecimal.ROUND_HALF_UP);
                baseInfoData.put("totalMoneyChange",totalMoneyChange);
                //价格差异率
                if(oldTotalMoney.equals(BigDecimal.ZERO)){
                    baseInfoData.put("totalMoneyChangeRate","∞");
                }
                else
                {
                    baseInfoData.put("totalMoneyChangeRate",Math.round(Math.abs(totalMoneyChange.floatValue()/oldTotalMoney.floatValue())*100)*0.01);
                }
                //总税额
                baseInfoData.put(PuorderConstants.TOTAL_TAX,totalTax);
            } catch (Exception e) {
                e.printStackTrace();
                return getResult(result , Result.RECODE_ERROR, "根据单据对象"+id+"未找到采购订单物品列表信息" + e.getMessage());
            }
            returnData.putAll(baseInfoData);
            returnData.put(PuorderConstants.PUODORDERSET,puodOrderSet);
            result.setData(returnData);
            return result;
        }
    }

    /**
     * 采购订单审核方法
     * @param jsonObject
     * @return
     */
    @Override
    public Result auditOrder(JSONObject jsonObject)
    {
        Result result = new Result();
        //流程处理
        String auditKey = jsonObject.getString(WorkFlowConstants.PARAM_AUDITKEY);
        //流程实例ID
        String actInstanceId = jsonObject.getString(WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
        //审批说明/步骤职责
        String opinion = jsonObject.getString(WorkFlowConstants.PARAM_OPINION);
        //单据编辑状态
        String formState = jsonObject.getString(WorkFlowConstants.PARAM_FORMSTATE);
        JSONObject auditMsg = null;
        try{
            auditMsg = jsonObject.getJSONObject(WorkFlowConstants.PARAM_ORDER_OBJ);
            if(null == auditMsg){
                return getResult(result , Result.RECODE_ERROR, "请传入参数" + WorkFlowConstants.PARAM_AUDITKEY);
            }
        }catch(Exception e){
            e.printStackTrace();
            return getResult(result , Result.RECODE_ERROR, "参数" + WorkFlowConstants.PARAM_AUDITKEY + "的格式错误，请确认参数格式为JSON串格式");
        }

        //入参校验
        if (StringUtil.isNullOrEmpty(auditKey))
        {
            return getResult(result , Result.RECODE_ERROR, "请传入参数" + WorkFlowConstants.PARAM_AUDITKEY);
        }
        if (StringUtil.isNullOrEmpty(actInstanceId))
        {
            return getResult(result , Result.RECODE_ERROR, "请传入参数" + WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
        }
        if (StringUtil.isNullOrEmpty(opinion))
        {
            return getResult(result , Result.RECODE_ERROR, "请传入参数" + WorkFlowConstants.PARAM_OPINION);
        }
        if (StringUtil.isNullOrEmpty(formState))
        {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_FORMSTATE);
            return result;
        }

        JSONObject jsonData = new JSONObject();

        if (WorkFlowConstants.FormState.EDITABLE.equals(formState)&&WorkFlowConstants.AuditKey.PASS.equals(auditKey))
        {
            //子表数据只有这里有，需在此更新行状态
            jsonData = auditUpdatePuorderSet(auditMsg);
        }

        //调用审批流程接口
        auditSubmitFlow(actInstanceId,opinion,auditKey);
        result.setData(jsonData);
        result.setErrMsg("提交采购订单审核意见成功");
        return result;
    }

    @Transactional
    @Override
    public Result closeOrder(PuorderDTO puorderDTO)
    {
        Result result = new Result();
        try
        {
            List<PuodOrderSetDTO> dtoList = puorderDTO.getPuodOrderSet();
            if(dtoList.isEmpty())
            {
                return getResult(result , Result.RECODE_ERROR, "未传递物品明细信息");
            }
            //
            for(PuodOrderSetDTO item : dtoList)
            {
                PuodPuodOrderSetaEntity puodOrderSetaEntity = new PuodPuodOrderSetaEntity();
                puodOrderSetaEntity.setId(item.getId());
                //更新状态为前台传过来的状态
                puodOrderSetaEntity.setPuod_istatus(item.getPuodIstatus());
                puodOrderSetaEntity.setModtime(new Date());
                //TODO 添加修改人

                ormService.updateSelective(puodOrderSetaEntity);
            }

            //更新主表订单状态：如果子表记录全为完成状态，则主表的订单状态修改为完成；否则修改为拒绝
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(PuodPuodOrderSetaProperty.PUOD_ISTATUS);
            ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID , puorderDTO.getId()));
            List<PuodPuodOrderSetaEntity> list =  ormService.selectBeanList(PuodPuodOrderSetaEntity.class , ormParam);
            if(!list.isEmpty()){
                //完成-3
                int count_3 = 0;
                for (PuodPuodOrderSetaEntity entity : list){
                    if (PuorderConstants.PUOD_ISTATUS_3.equals(entity.getPuod_istatus())){
                        count_3++;
                    }
                }
                //更新订单状态
                PuorderEntity  updateEntity = new PuorderEntity();
                updateEntity.setId(puorderDTO.getId());
                updateEntity.setModtime(new Date());
                updateEntity.setPuod_status(count_3==list.size()?PuorderConstants.PUOD_STATUS_11:PuorderConstants.PUOD_STATUS_12);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return getResult(result , Result.RECODE_ERROR, "关闭采购订单失败，" + e.getMessage());
        }
        return result;
    }

    /**
     * 调用采购订单审批流程
     * @param actInstanceId
     * @param opinion
     * @param auditKey
     */
    @TxTransaction
    private void auditSubmitFlow(String actInstanceId, String opinion, String auditKey)
    {
        //调用流程
        try
        {
            bizFormService.audit(actInstanceId, opinion, auditKey);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR , "调用采购订单审批流程失败," + e.getMessage());
        }
    }

    /**
     * 更新采购订单明细数据
     * @param auditMsg
     * @return
     */
    @Transactional
    private JSONObject auditUpdatePuorderSet(JSONObject auditMsg){
        JSONObject returnData = new JSONObject();
        try
        {
            if(!auditMsg.containsKey(EdmSysColumn.ID))
            {
                throw new ApplicationException(Result.RECODE_ERROR,"未传递参数id的值，请先传递参数");
            }

            if(!auditMsg.containsKey(OrderConstants.ORDE_NBR_UPCASE))
            {
                throw new ApplicationException(Result.RECODE_ERROR,"未传递参数" + OrderConstants.ORDE_NBR_UPCASE + "的值，请先传递参数");
            }

            if(!auditMsg.containsKey(PuorderConstants.PUODORDERSET))
            {
                throw new ApplicationException(Result.RECODE_ERROR,"未传递参数" + PuorderConstants.PUODORDERSET + "的值，请先传递参数");
            }

            JSONArray jsonArray = (JSONArray) auditMsg.get(PuorderConstants.PUODORDERSET);

            if(jsonArray.isEmpty())
            {
                throw new ApplicationException(Result.RECODE_ERROR,"无订单明细数据，请先传递订单明细数据");
            }

            String orderId = auditMsg.getString(EdmSysColumn.ID);
            String ordeNbr = auditMsg.getString(OrderConstants.ORDE_NBR_UPCASE);

            //step1：操作子表
            for(Object obj : jsonArray){
                JSONObject jsonObject = (JSONObject) obj;
                String id = jsonObject.getString(EdmSysColumn.ID);
                String status = jsonObject.getString(PuorderConstants.PUOD_ISTATUS);
                if(!StringUtil.isNullOrEmpty(id) && !StringUtil.isNullOrEmpty(status)){
                    PuodPuodOrderSetaEntity entity = new PuodPuodOrderSetaEntity();
                    entity.setId(id);
                    entity.setPuod_istatus(status);
                    entity.setModtime(new Date());
                    //TODO 添加修改人

                    ormService.updateSelective(entity);
                }
            }

            //step2：返回returnData
            returnData.put(PuorderConstants.ORDER_ID, orderId);
            returnData.put(OrderConstants.ORDE_NBR_UPCASE, ordeNbr);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR , "更新采购订单明细数据失败，" + e.getMessage());
        }
        return returnData;
    }

    /**
     * 获取单据的基本信息
     *
     * @param id
     * @return
     * @throws Exception
     */
    private JSONObject loadOrderBaseInfo(String id)
    {
        // 待返回的数据
        JSONObject result = new JSONObject();
        try
        {
            OrmParam ormParam = getOrderPropertys();
            // 单据id,生效日期,单据类型,单据备注信息,单据单号
            ormParam.addColumn(EdmSysColumn.ID)
                    .addColumn(PuorderProperty.PUOD_CURR)
                    .addColumn(PuorderProperty.PUOD_CORP)
                    .addColumn(PuorderProperty.PUOD_PRICEFF_TYPE)
                    .addColumn(PuorderProperty.PUOD_EMP)
                    .addColumn(PuorderProperty.PUOD_STATUS)
                    .addColumn(PuorderProperty.PUOD_SETTLE_SUPP)
                    .addColumn(PuorderProperty.PUOD_CODE_SUPP)
                    .addColumn(PuorderProperty.PUOD_DELI_CONTWAY)
                    .addColumn(PuorderProperty.PUOD_DELI_ADDR)
                    .addColumn(PuorderProperty.PUOD_DELI_LINKMAN)
                    .addColumn(PuorderProperty.PUOD_DEPT)
                    .addColumn(PuorderProperty.PUOD_PARK)
                    .addColumn(PuorderProperty.PUOD_REMARK)
                    .addColumn(PuorderProperty.PUOD_SNAME_SUPP)
                    .addColumn(EdmProperty.EDMD_CLASS);
            ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID,id));

            //查询采购订单
            List<PuorderEntity> entityList = ormService.selectBeanList(PuorderEntity.class,ormParam);
            if (!entityList.isEmpty())
            {
                PuorderEntity entity = entityList.get(0);
                PuorderDTO dto = JSONObject.parseObject(JSONObject.toJSONString(entity),PuorderDTO.class);

                //查询员工的姓名和工号，作为制单人名字和制单人工号
                EmployeeEntity employeeEntity = entity.loadOrde_adduser();
                if(null != employeeEntity)
                {
                    // 制单人名字
                    dto.setOrdeAdduserName(employeeEntity.getRemp_name());
                    // 制单人工号
                    dto.setOrdeAdduserNo(employeeEntity.getRemp_no());
                }
//
                //查询制单部门名称
                DepttreeEntity depttreeEntity = entity.loadOrde_dept();
                dto.setOrdeDeptName(null != depttreeEntity ? depttreeEntity.getMdep_name() : "");

                //查询制单岗位名称
                JobpositionEntity jobpositionEntity = entity.loadOrde_duty();
                dto.setOrdeDutyName(null != jobpositionEntity ? jobpositionEntity.getRpos_name() : "");

                //object转JSON对象或字符串时，会丢失字段值为空null/""的属性，为了保证属性不丢失,
                // 需要加上SerializerFeature.WriteMapNullValue和SerializerFeature.WriteNullStringAsEmpty这两个属性
                String json = JSONObject.toJSONString(dto, SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
                Object param = JSONObject.parse(json);
                JsonUtils.underLine2Camel(param);
                result = JSONObject.parseObject(JSON.toJSONString(param));

                //供应商代码
                RelationEntity relationEntity = entity.loadPuod_code_supp();
                result.put(PuorderConstants.PUOD_SUMO_CODE , null != relationEntity ? relationEntity.getRela_code() : "");

                //币别名称
                CurrencyEntity currencyEntity = entity.loadPuod_curr();
                result.put(PuorderConstants.PUOD_CURR_CODE , null != currencyEntity ? currencyEntity.getCurr_code() : "");

                //采购法人名称【来源于伙伴类】
                RelationEntity relationEntity1 = entity.loadPuod_corp();
                result.put(PuorderConstants.PUOD_CORP_NAME , null != relationEntity1 ? relationEntity1.getRela_name() : "");

                //园区名称
                ParkEntity parkEntity = entity.loadPuod_park();
                result.put(PuorderConstants.PUOD_PARK_NAME , null != parkEntity ? parkEntity.getRpak_name() : "");

                //采购员名称
                EmployeeEntity employeeEntity1 = entity.loadPuod_emp();
                result.put(PuorderConstants.PUOD_EMP_NAME , null != employeeEntity1 ? employeeEntity1.getRemp_name() : "");

                //采购部门名称
                DepttreeEntity depttreeEntity1 = entity.loadPuod_dept();
                result.put(PuorderConstants.PUOD_DEPT_NAME , null != depttreeEntity1 ? depttreeEntity1.getMdep_name() : "");

                //结算方式名称
                SettlemenetEntity settlemenetEntity = entity.loadPuod_settle_supp();
                result.put(PuorderConstants.PUOD_SETTLE_NAME , null != settlemenetEntity ? settlemenetEntity.getSett_way() : "");
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR , "获取单据基本信息失败，" + e.getMessage());
        }
        return result;
    }

    /**
     * 获取单据的字段，并封装到OrmParam对象中
     * @return
     */
    private OrmParam getOrderPropertys()
    {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(OrderProperty.ORDE_ADDUSER)
                .addColumn(OrderProperty.ORDE_DATE)
                .addColumn(OrderProperty.ORDE_DUTY)
                .addColumn(OrderProperty.ORDE_PROXYUSER_POS)
                .addColumn(OrderProperty.ORDE_EFFDATE)
                .addColumn(OrderProperty.ORDE_EMP)
                .addColumn(OrderProperty.ORDE_FORM_OBJ)
                .addColumn(OrderProperty.ORDE_LAST_VERSION_OBJ)
                .addColumn(OrderProperty.ORDE_NBR)
                .addColumn(OrderProperty.ORDE_PROCOBJ)
                .addColumn(OrderProperty.ORDE_PROXYUSER)
                .addColumn(OrderProperty.ORDE_RODE_OBJ)
                .addColumn(OrderProperty.ORDE_STATUS)
                .addColumn(OrderProperty.ORDE_UPDATE_POS)
                .addColumn(OrderProperty.ORDE_VERSION)
                .addColumn(OrderProperty.ORDE_DEPT);
        return ormParam;
    }

    /**
     * 多参数查询
     * 根据传入的参数和返回的字段查询Class对象的信息
     * @param paramMap 传入的参数,入参字符串：column + 符号（equal、lessThan、greaterThan、in等等）
     * @param columnList 返回的字段
     * @return
     * @throws Exception
     */
    private List<Map<String, Object>> getBaseInfoByConditions(Map<String,Object> paramMap, List<String> columnList, Class c)
    {
        List<Map<String, Object>> result = null;
        try
        {
            OrmParam ormParam = new OrmParam();
            if(null == columnList)
            {
                throw new ApplicationException(Result.RECODE_ERROR,"未明确定义需要查询的列");
            }
            else
            {
                columnList.forEach(column -> { ormParam.addColumn(column); });
            }

            List<String> paramList = new ArrayList<>();
            for (Map.Entry<String, Object> entry : paramMap.entrySet())
            {
                String[] conditions = entry.getKey().split(",");
                String whereExp = "";
                if("equal".equalsIgnoreCase(conditions[1]))
                {
                    whereExp = ormParam.getEqualXML(conditions[0],entry.getValue());
                }
                else if("lessThan".equalsIgnoreCase(conditions[1]))
                {
                    whereExp = ormParam.getLessThanXML(conditions[0],entry.getValue());
                }
                else if("greaterThan".equalsIgnoreCase(conditions[1]))
                {
                    whereExp = ormParam.getGreaterThanXML(conditions[0], entry.getValue());
                }
                else if("in".equalsIgnoreCase(conditions[1]))
                {
                    whereExp = ormParam.getInXML(conditions[0], (entry.getValue().toString()).split(","));
                }
                else if("matchMiddle".equalsIgnoreCase(conditions[1]))
                {
                    whereExp = ormParam.getMatchMiddleXML(conditions[0], entry.getValue().toString());
                }
                else
                {
                    //TODO 此处可以添加更多代码
                }
                paramList.add(whereExp);
            }
            ormParam.setWhereExp(OrmParam.and(paramList));
            result = ormService.selectMapList(c, ormParam);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return result;
    }

    /**
     * 根据物品ID和园区ID获取物品类的采购价格集的有效记录
     * @param goodsId
     * @param parkId
     * @return
     */
    private List<GodsGodsPupricSetaEntity> getGodsPupricSet(String goodsId , String parkId ,Date puodBeg){
        List<String> list = new ArrayList<>();
        List<GodsGodsPupricSetaEntity> godsPupricSetaEntityList = new ArrayList<>();
        OrmParam ormParam = new OrmParam();
        //设置查询列
        ormParam.addColumn(GodsGodsPupricSetaProperty.GODS_PPRICE_ORD)
                .addColumn(GodsGodsPupricSetaProperty.GODS_SUPP)
                .addColumn(GodsGodsPupricSetaProperty.GODS_PCURR)
                .addColumn(GodsGodsPupricSetaProperty.GODS_PTAX_RATE)
                .addColumn(GodsGodsPupricSetaProperty.GODS_PPARK)
                .addColumn(GodsGodsPupricSetaProperty.GODS_PUM_GROUP)
                .addColumn(GodsGodsPupricSetaProperty.GODS_PUM)
                .addColumn(GodsGodsPupricSetaProperty.GODS_PPRICE)
                .addColumn(GodsGodsPupricSetaProperty.GODS_PREBATE)
                .addColumn(GodsGodsPupricSetaProperty.GODS_PDATE_BEG)
                .addColumn(GodsGodsPupricSetaProperty.GODS_PDATE_END)
                .addColumn(GodsGodsPupricSetaProperty.GODS_PISLADDER)
                //.addColumn(GodsGodsPupricSetaProperty.GODS_POQTY_MIN)
                //.addColumn(GodsGodsPupricSetaProperty.GODS_POQTY_MAX)
                .addColumn(GodsGodsPupricSetaProperty.GODS_UPDOWN_RATE);
        //组装查询条件
        //物品ID
        if(!StringUtil.isNullOrEmpty(goodsId))
        {
            list.add(ormParam.getEqualXML(EdmSysColumn.PID , goodsId));
        }
        //园区ID
        if(!StringUtil.isNullOrEmpty(parkId))
        {
            list.add(ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_PPARK , parkId));
        }
        //查询有效记录
        if(null != puodBeg){
            list.add(ormParam.getLessThanAndEqualXML(GodsGodsPupricSetaProperty.GODS_PDATE_BEG , puodBeg));
            list.add(ormParam.getGreaterThanXML(GodsGodsPupricSetaProperty.GODS_PDATE_END , puodBeg));
        }
        ormParam.setWhereExp(OrmParam.and(list));
        //根据查询条件查询结果集并返回
        try {
            godsPupricSetaEntityList = ormService.selectBeanList(GodsGodsPupricSetaEntity.class , ormParam);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR , "未查询到任何采购价格集信息，" + e.getMessage());
        }
        return godsPupricSetaEntityList;
    }

    private Result getResult(Result result , int retCode,String errMsg){
        result.setRetCode(retCode);
        result.setErrMsg(errMsg);
        return result;
    }

    /**
     * 根据供应商id,园区id 查询物品编号
     * @param supplierId
     * @param parkId
     * @param godsCode
     * @return
     */
    @Override
    public Result queryGoodsCode(String supplierId,String parkId,String godsCode) {
        Result result = new Result();
        try {
            //根据供应商id、物品状态、园区id 查询物品类.园区属性集合
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(
                    OrmParam.and(ormParam.getEqualXML("gods_supplier",supplierId),
                            ormParam.getInXML("gods_status",new String[]{"UP","AC"}),
                            ormParam.getIsNotNull("gods_park"),
                            ormParam.getEqualXML("gods_park",parkId)));
            ormParam.addColumn(NodeConstant.PID);
            logger.info("采购订单获取园区属性集sql语句 1"+ormParam.toString());
            List<Map<String, Object>> list = ormService.selectMapList(GodsGodsParkSetaEntity.class,ormParam);
            //获取园区属性pid集合
            List<Object> pids = new ArrayList<>();
            if(list != null && list.size() > 0){
                for (Map<String, Object> stringObjectMap : list) {
                    if(stringObjectMap != null && !StringUtil.isNullOrEmpty(stringObjectMap.get(NodeConstant.PID))){
                        pids.add(stringObjectMap.get(NodeConstant.PID));
                    }
                }
            }
            //获取物品编号
            List<Map<String,Object>> mapList = new ArrayList<>();
            List<Map<String,Object>> resultList = new ArrayList<>();
            if(pids.size() > 0){
                ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getInXML(NodeConstant.ID,pids.toArray()));
                ormParam.addColumn(NodeConstant.ID).addColumn("gods_code");
                mapList = ormService.selectMapList(GoodsEntity.class,ormParam);
                logger.info("采购订单获取物品编号sql语句 2"+ormParam.toString());
            }
            //根据物品编码做模糊匹配
            if(mapList !=null && mapList.size() > 0){
                for (Map<String, Object> objectMap : mapList) {
                    if(objectMap.get("gods_code")!=null && objectMap.get("gods_code").toString().startsWith(godsCode)){
                        resultList.add(objectMap);
                    }
                }
            }
            result.setData(resultList);
        } catch (Exception e) {
            logger.error("采购订单物品编号加载方法出现错误....", e);
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    /**
     * 根据物品编号 获取采购订单明细
     * @param godsCode   物品编号
     * @param supplierId 供应商
     * @param parkId     园区
     * @param currId     币别
     * @param goodsCodes 物品编码集[,,]
     * @return
     */
    @Override
    public Result getPurchaseOrderDetails(String godsCode,String supplierId,String parkId,String currId,String goodsCodes){
        Result result = new Result();
        Result getTaxRateResult = new Result();
        String defaultParkId = "";
        try {

            OrmParam ormParam = new OrmParam();

            //根据物品编号 查询物品id
            if(!StringUtil.isNullOrEmpty(goodsCodes)) {
                String godsCodes[] = goodsCodes.split(",");
                for(String goodsCode:godsCodes) {
                    ormParam.setWhereExp(OrmParam.or(ormParam.getWhereExp(), ormParam.getEqualXML("gods_code",goodsCode)));
                }
            }else {
                ormParam.setWhereExp(ormParam.getEqualXML("gods_code",godsCode));
            }

            //物品名称 规格型号 单位 由物品编号带入
            ormParam.addColumn(NodeConstant.ID).
                    //物品编号
                            addColumn("gods_code").
                    //物品名称
                            addColumn("gods_name").
                    //规格型号
                            addColumn("gods_model").
                    //单位组
                            addColumn("gods_unit_group").
                    //单位
                            addColumn("gods_unit");
            logger.info("采购订单获取物品名称 规格型号 单位组 单位的sql语句"+ormParam.toString());
            List<GoodsEntity> list = ormService.selectBeanList(GoodsEntity.class,ormParam);
            List<PuodOrderSetDTO>  puodOrderSetDTOS = new ArrayList<>();

            if(list != null && list.size() > 0){
                for (GoodsEntity goodsEntity : list) {
                    //获取到物品类
                    if(goodsEntity != null){
                        //获取物品id
                        String goodsId = goodsEntity.getId();
                        //物品编号
                        String goodsCode = goodsEntity.getGods_code();
                        PuodOrderSetDTO puodOrderSetDTO = new PuodOrderSetDTO();
                        //获取采购报价集合
                        List<GodsGodsPupricSetaDTO> godsGodsPupricSeta = new ArrayList<>();
                        //获取阶梯报价集合
                        List<GodsGodsPladderSetbDTO> godsGodsPladderSetbDTOS = new ArrayList<>();
                        //获取税率结合
                        List<TaxrateDTO> templateTaxs = new ArrayList<>();
                        //单位组id
                        String groupId = goodsEntity.getGods_unit_group().toString();
                        //单位
                        String unit = goodsEntity.getGods_unit();
                        Map<String,String> unitMap = new HashedMap();
                        //TODO:调用方法getNumAndPut方法 获取 单位的精度以及归整方法的
                        unitMap = getNumAndPut(groupId,unit);
                        //小数点位数
                        int num = 0;
                        if(!StringUtil.isNullOrEmpty(unitMap.get("num"))) {
                            num = Integer.valueOf(unitMap.get("num"));
                        }
                        //规整方法
                        String putMethod = null;
                        if(!StringUtil.isNullOrEmpty(unitMap.get("put"))) {
                            putMethod = unitMap.get("put");
                        }
                        //物品id
                        puodOrderSetDTO.setId(goodsId);
                        //物品编号
                        puodOrderSetDTO.setPuodGods(goodsCode);
                        //物品名称
                        puodOrderSetDTO.setPuodGodsName(goodsEntity.getGods_name());
                        //规格型号
                        puodOrderSetDTO.setPuodGodsModel(goodsEntity.getGods_model());
                        //小数点位数
                        puodOrderSetDTO.setMeasDradixNum(num);
                        //小数点规整方法
                        puodOrderSetDTO.setMeasDradixPut(putMethod);
                        //单位组
                        puodOrderSetDTO.setPuodUmGroup(goodsEntity.getGods_unit_group().toString());
                        //单位
                        puodOrderSetDTO.setPuodUm(goodsEntity.getGods_unit());
                        //交货日期
                        puodOrderSetDTO.setPuodDate(DateUtil.parseFormatDate(sdf.format(new Date())));
                        //TODO:单价->  根据物品类的id 查询采购价格集  2018-2-8  业务逻辑变动 无需判断是否为已经园区还是未见园区 根据供应商 币别 园区 直接查询单价以及税率
                        if(!StringUtil.isNullOrEmpty(goodsId)){
                            ormParam = new OrmParam();
                            //条件
                            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID,goodsId),
                                    //供应商
                                    ormParam.getEqualXML("gods_supp",supplierId),
                                    //币别
                                    ormParam.getEqualXML("gods_pcurr",currId),
                                    //园区
                                    ormParam.getEqualXML("gods_ppark",parkId),
                                    //生效日期 生效日期 <= 当前日期
                                    ormParam.getLessThanAndEqualXML("gods_pdate_beg",new Date()),
                                    //失效日期 失效日期 > 当前日期 或者失效日期为null
                                    OrmParam.or(ormParam.getGreaterThanAndEqualXML("gods_pdate_end",new Date()),ormParam.getIsNull("gods_pdate_end"))));
                                    //查询单价 查询采购报价集合的Id
                            ormParam.addColumn(NodeConstant.ID)
                                    //查询单价
                                    .addColumn("gods_pprice")
                                    //查询税率id
                                    .addColumn("gods_ptax_rate")
                                    //查询单位
                                    .addColumn("gods_pum")
                                    //查询是否是阶梯报价 [1 是] [0 不是]
                                    .addColumn("gods_pisladder")
                                    //币别
                                    .addColumn("gods_pcurr")
                                    //返利额
                                    .addColumn("gods_prebate")
                                    //生效日期
                                    .addColumn("gods_pdate_beg")
                                    //失效日期
                                    .addColumn("gods_pdate_end");
                            logger.info("采购订单获取采购价格集的sql语句"+ormParam.toString());
                            //TODO :一个物品对应多个采购报价集合根据过滤条件 生效日期 只会有一个有效单价
                            List<GodsGodsPupricSetaEntity> godsPriceList = ormService.selectBeanList(GodsGodsPupricSetaEntity.class,ormParam);
                            //转DTO
                            if(godsPriceList != null && godsPriceList.size() > 0) {
                                godsGodsPupricSeta = JSONObject.parseArray(JSONObject.toJSONString(godsPriceList), GodsGodsPupricSetaDTO.class);
                            }
                            //TODO: 税率 根据价格是否有效
                            //TODO: 如果存在价格 按需带入 税率
                            if(godsGodsPupricSeta != null && godsGodsPupricSeta.size() > 0) {
                                if (!StringUtil.isNullOrEmpty(godsGodsPupricSeta.get(0).getGodsPprice())) {
                                    templateTaxs = getByRateId(godsGodsPupricSeta.get(0).getGodsPtaxRate());
                                    if (templateTaxs != null && templateTaxs.size() > 0) {
                                        //设置税率名称
//                                      puodOrderSetDTO.setPuodRateName(templateTaxs.get(0).getTaxrName());
                                        puodOrderSetDTO.setTaxrateDTOs(templateTaxs);
                                    }
                                } else
                                //TODO: 如果不存在有效价格 从伙伴类.供应商数据集带入
                                {
                                    getTaxRateResult = queryBySupplier(supplierId);
                                    Map<String, List> map = (Map<String, List>) getTaxRateResult.getData();
                                    List<TaxrateDTO> taxrateDTOList = map.get("TaxRate");
                                    puodOrderSetDTO.setTaxrateDTOs(taxrateDTOList);
                                }
                            }
                            //===获取采购报价集合判断
                            if(godsGodsPupricSeta != null && godsGodsPupricSeta.size() > 0){
                                //判断是否是阶梯报价
                                if(String.valueOf(godsGodsPupricSeta.get(0).getGodsPisladder()) != null && (0 == godsGodsPupricSeta.get(0).getGodsPisladder())){
                                    //TODO:不是阶梯报价 普通报价 设置价格
                                    puodOrderSetDTO.setPuodPrice(godsGodsPupricSeta.get(0).getGodsPprice());
                                } else
                                    if(String.valueOf(godsGodsPupricSeta.get(0).getGodsPisladder()) != null && (1 == godsGodsPupricSeta.get(0).getGodsPisladder())){
                                    //是阶梯报价 查询阶梯报价集合
                                    ormParam = new OrmParam();
                                    //条件
                                    ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.PID,godsGodsPupricSeta.get(0).getId()));
                                    //按照阶梯订单数量最大值升序排列
                                    ormParam.setOrderExp(SQLSortEnum.ASC, "gods_poqty_max");
                                    //查询物品类.采购报价集合.阶梯报价中的单价
                                    ormParam.addColumn("gods_plprice")
                                            //查询阶梯订单量最小值
                                            .addColumn("gods_poqty_min")
                                            //查询阶梯订单量最大值
                                            .addColumn("gods_poqty_max")
                                            //查询涨跌率
                                            .addColumn("gods_lupdown_rate");
                                    logger.info("获取物品类.采购报价集.阶梯报价集的sql语句"+ormParam.toString());
                                    //TODO :一个采购报价有可能对应多个阶梯报价
                                    List<GodsGodsPladderSetbEntity> godsGodsPladderSetbEntities = ormService.selectBeanList(GodsGodsPladderSetbEntity.class,ormParam);
                                    //转DTO
                                    if(godsGodsPladderSetbEntities != null && godsGodsPladderSetbEntities.size() > 0) {
                                        godsGodsPladderSetbDTOS = JSONObject.parseArray(JSONObject.toJSONString(godsGodsPladderSetbEntities),GodsGodsPladderSetbDTO.class);
                                        //阶梯报价集合
                                        puodOrderSetDTO.setGodsGodsPladderSetbDTOS(godsGodsPladderSetbDTOS);
                                        }
                                    }
                                }
                            //===获取采购报价集合判断结束
                            }
                            puodOrderSetDTO.setGodsGodsPupricSetaDTOS(godsGodsPupricSeta);
                        //取单价结束位置
                        puodOrderSetDTOS.add(puodOrderSetDTO);
                    }
                }
            }
            result.setData(puodOrderSetDTOS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    /**
     * 获取默认园区id
     */
    public String getDefaultParkId(){
        String defaultParkId ="";
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML("rpak_isdefault",1));
            ormParam.addColumn("id");
            logger.info("查询是否是默认园区的id的sql语句"+ormParam.toString());
            List<ParkEntity> parkList = ormService.selectBeanList(ParkEntity.class,ormParam);
            if(parkList !=null && parkList.size() > 0){
                //TODO:只存在一个默认的园区
                defaultParkId = parkList.get(0).getId();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return defaultParkId;
    }

    /**
     * 根据供应商ID获取 币别集合 结算方式集合 税率集合
     * @param supplierId
     * @return
     */
    @Override
    public Result queryBySupplier(String supplierId) {
        Result result = new Result();
        OrmParam ormParam = new OrmParam();
        try {
            //条件根据供应商ID
            ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.PID,supplierId));
            //TODO:查询的列 伙伴类.供应商数据集的ID 和 结算方式 以及 税率
            ormParam.addColumn(NodeConstant.ID).addColumn(RelaRelaSupplierSetaProperty.RELA_SETT_WAYS).addColumn(RelaRelaSupplierSetaProperty.RELA_TAXRATE);
            List<Map<String,Object>> supplierList = ormService.selectMapList(RelaRelaSupplierSetaEntity.class,ormParam);

            //获取结算方式类集合和供应商数据id集合以及税率集合
            List<Object> settWaysIds = new ArrayList<>();
            List<Object> supplierDateIds = new ArrayList<>();
            List<Object> taxRateIds = new ArrayList<>();
            if(supplierList != null && supplierList.size()>0){
                for (Map<String, Object> supplier : supplierList) {
                    if(supplier != null ){
                        //结算方式Ids集合
                        if(supplier.get(RelaRelaSupplierSetaProperty.RELA_SETT_WAYS) != null){
                            settWaysIds.add(supplier.get(RelaRelaSupplierSetaProperty.RELA_SETT_WAYS));
                        }
                        //供应商属性Ids集集合
                        if(supplier.get(NodeConstant.ID) != null){
                            supplierDateIds.add(supplier.get(NodeConstant.ID));
                        }
                        //税率Ids集合
                        if(supplier.get(RelaRelaSupplierSetaProperty.RELA_TAXRATE) != null){
                            taxRateIds.add(supplier.get(RelaRelaSupplierSetaProperty.RELA_TAXRATE));
                        }
                    }
                }
            }
            //获取币别类id集合
            List<String> currencyIds = new ArrayList<>();
            if(supplierDateIds != null && supplierDateIds.size() > 0){
                //获取供应商账户管理集
                Date date = new Date();
                ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getInXML(NodeConstant.PID,supplierDateIds.toArray()),
                        ormParam.getLessThanAndEqualXML(RelaRelaAccountSuppSetbProperty.RELA_ACCOBEG_SUPP,date),
                        ormParam.getGreaterThanAndEqualXML(RelaRelaAccountSuppSetbProperty.RELA_ACCOEND_SUPP,date)));
                ormParam.addColumn(RelaRelaAccountSuppSetbProperty.RELA_ACCOCURR_SUPP);
                //查询供应商账户管理集
                List<Map<String,Object>> accounts = ormService.selectMapList(RelaRelaAccountSuppSetbEntity.class,ormParam);
                //获取币别类id集合
                if(accounts != null && accounts.size() > 0){
                    for (Map<String, Object> account : accounts) {
                        if(account != null){
                            if(account.get(RelaRelaAccountSuppSetbProperty.RELA_ACCOCURR_SUPP) != null){
                                currencyIds.add(account.get(RelaRelaAccountSuppSetbProperty.RELA_ACCOCURR_SUPP).toString());
                            }
                        }
                    }
                }
            }

            //获取币别类集合
            List<CurrencyDTO> currencyDTOList = new ArrayList<>();
            if(currencyIds != null && currencyIds.size() > 0){
                ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getInXML(NodeConstant.ID,currencyIds.toArray())));
                ormParam.addGroupByColumn(NodeConstant.ID,CurrencyProperty.CURR_CODE,CurrencyProperty.CURR_DESC,
                        CurrencyProperty.CURR_NAME);
                List<CurrencyEntity> currList = ormService.selectBeanList(CurrencyEntity.class,ormParam);
                //Entity 转DTO
                if(currList != null && currList.size() > 0){
                    currencyDTOList = JSONObject.parseArray(JSONObject.toJSONString(currList),CurrencyDTO.class);
                }
            }

            //获取结算方式类集合
            List<SettleMenetDTO> settleMenetDTOList = new ArrayList<>();
            if(settWaysIds != null && settWaysIds.size() > 0){
                ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getInXML(NodeConstant.ID,settWaysIds.toArray()));
                ormParam.addGroupByColumn(NodeConstant.ID,SettlemenetProperty.SETT_WAY,SettlemenetProperty.SETT_DESC,SettlemenetProperty.SETT_TYPE);
                List<SettlemenetEntity> settleList = ormService.selectBeanList(SettlemenetEntity.class,ormParam);
                //Entity 转DTO
                if(settleList != null && settleList.size() > 0){
                    settleMenetDTOList = JSONObject.parseArray(JSONObject.toJSONString(settleList),SettleMenetDTO.class);
                }
            }
            //获取税率类集合
            List<TaxrateDTO> taxrateDTOList = new ArrayList<>();
            if(taxRateIds != null && taxRateIds.size() > 0){
                ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getInXML(NodeConstant.ID,taxRateIds.toArray()));
                ormParam.addGroupByColumn(NodeConstant.ID,TaxrateProperty.TAXR_NAME,TaxrateProperty.TAXR_ISDEDUCT,TaxrateProperty.TAXR_DETAIL);
                List<TaxrateEntity> taxList = ormService.selectBeanList(TaxrateEntity.class,ormParam);
                //Entity 转DTO
                if(taxList != null && taxList.size() > 0){
                    taxrateDTOList = JSONObject.parseArray(JSONObject.toJSONString(taxList),TaxrateDTO.class);
                }
            }

            //返回结果
            Map<String,List> map = new HashedMap();
            //币别
            map.put("Currency",currencyDTOList);
            //结算方式
            map.put("SettlementMethod",settleMenetDTOList);
            //税率
            map.put("TaxRate",taxrateDTOList);
            result.setData(map);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());

        }

        return result;
    }

    /**
     * 根据园区载入交货地址
     * @param parkId
     * @return
     */
    @Override
    public Result queryByPark(String parkId) {
        Result result = new Result();
        List<Map<String,Object>> addrList = new ArrayList<>();
        List<Map<String,String>> stringList = new ArrayList<>();
        try {
            OrmParam ormParam = new OrmParam();
            //条件根据园区id
            ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.PID,parkId));
            //TODO:查询的列 交货省 交货市 交货区  详细地址 联系人 联系方式
            ormParam.addColumn(NodeConstant.ID)
                    .addColumn(RpakRpakAddrSetaProperty.RPAK_ADDRP)
                    .addColumn(RpakRpakAddrSetaProperty.RPAK_ADDRC)
                    .addColumn(RpakRpakAddrSetaProperty.RPAK_ADDRL)
                    .addColumn(RpakRpakAddrSetaProperty.RPAK_DADDR)
                    .addColumn(RpakRpakAddrSetaProperty.RPAK_CONTACT)
                    .addColumn(RpakRpakAddrSetaProperty.RPAK_CWAY);
            logger.info("根据园区id查询园区.交货地址集的sql语句"+ormParam.toString());
            addrList = ormService.selectMapList(RpakRpakAddrSetaEntity.class,ormParam);
            //获取 省 市 交货区
            int index = 0;
            if(addrList != null && addrList.size()>0){
                Map<String,String> map = null;
                for (Map<String, Object> addr : addrList) {
                    index ++;
                    if(addr != null ){
                        if(addr.get(RpakRpakAddrSetaProperty.RPAK_ADDRP) != null && addr.get(RpakRpakAddrSetaProperty.RPAK_ADDRC) != null && addr.get(RpakRpakAddrSetaProperty.RPAK_ADDRL) != null){
                            //组合交货地址信息
                            map = new HashedMap();
                            StringBuilder sb = new StringBuilder();
                            String provience = getAreaName(addr.get(RpakRpakAddrSetaProperty.RPAK_ADDRP).toString());
                            String city = getAreaName(addr.get(RpakRpakAddrSetaProperty.RPAK_ADDRC).toString());
                            String dealArea = getAreaName(addr.get(RpakRpakAddrSetaProperty.RPAK_ADDRL).toString());
                            String rpakDaddr = addr.get(RpakRpakAddrSetaProperty.RPAK_DADDR).toString();
                            String contact = addr.get(RpakRpakAddrSetaProperty.RPAK_CONTACT).toString();
                            String cway = addr.get(RpakRpakAddrSetaProperty.RPAK_CWAY).toString();
                            sb.append(provience)
                                    .append(city)
                                    .append(dealArea)
                                    .append(rpakDaddr)
                                    .append("/")
                                    .append(contact)
                                    .append("/")
                                    .append(cway);

                            map.put(addr.get(NodeConstant.ID).toString(),sb.toString());
                            stringList.add(map);
                        }
                    }

                }
            }
            result.setData(stringList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 根据区域id获取区域类对应的名称
     * @param areaId 区域id
     * @return
     */
    public String getAreaName (String areaId){
        String areaName = "";
        try {
            if(StringUtil.isNullOrEmpty(areaId)){return null;}
            AreaEntity areaEntity = ormService.load(AreaEntity.class,areaId);
            if(areaEntity!=null){areaName = areaEntity.getArea_name();}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return areaName;
    }

    /**
     * 获取采购法人列表接口
     * @return
     */
    @Override
    public Result getPuorderLeagerPerson() {
        Result  result = new Result();
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML(RelaRelaTypesSetaProperty.RELA_TYPE,5));
            ormParam.addColumn(NodeConstant.PID);
            //伙伴类型为法人 枚举值为5
            List<Map<String, Object>> lists = ormService.selectMapList(RelaRelaTypesSetaEntity.class,ormParam);
            List<Object> pIds = new  ArrayList<>();
            if(lists != null && lists.size() > 0){
                for (Map<String, Object> list : lists) {
                    if(list != null){
                        //伙伴类型Pids集合
                        if(list.get(NodeConstant.PID) != null ){
                            pIds.add(list.get(NodeConstant.PID));
                        }
                    }
                }
            }
            //法人状态不为关闭即伙伴状态 1-正常 2-观察 3-关闭
            OrmParam ormParamSupplier = new OrmParam();
            ormParamSupplier.setWhereExp(
                    OrmParam.or(ormParamSupplier.getEqualXML("rela_stat_corp",1),
                            ormParamSupplier.getEqualXML("rela_stat_corp",2)));
            ormParamSupplier.addColumn(NodeConstant.PID);
            ormParamSupplier.setOrderExp(SQLSortEnum.ASC,"cretime");
            List<Map<String, Object>> listCorps = ormService.selectMapList(RelaRelaCorpSetaEntity.class,ormParamSupplier);
            List<Object> corpIds = new  ArrayList<>();
            if(listCorps != null && listCorps.size() > 0){
                for (Map<String, Object> corp : listCorps) {
                    if(corp != null){
                        //伙伴类型corpIds集合
                        if(corp.get(NodeConstant.PID) != null ){
                            corpIds.add(corp.get(NodeConstant.PID));
                        }
                    }
                }
            }
            //取交集 伙伴类型含有供应商的pids与法人状态不为关闭的集合
            corpIds.retainAll(pIds);

            //获取伙伴类法人[采购法人]简称集合
            List<RelationDTO> relationDTOS = new ArrayList<>();
            if(corpIds != null && corpIds.size() > 0){
                ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getInXML(NodeConstant.ID,corpIds.toArray()));
                ormParam.addColumn(NodeConstant.ID).addColumn(RelationProperty.RELA_SHORT_NAME).addColumn(RelationProperty.RELA_CODE);
//              ormParam.addGroupByColumn(NodeConstant.ID,RelationProperty.RELA_SHORT_NAME);
                List<RelationEntity> relationList = ormService.selectBeanList(RelationEntity.class,ormParam);
                List<RelationEntity> resultList = new ArrayList<>();
                for (Object corpId : corpIds) {
                    for (RelationEntity relationEntity : relationList) {
                        if (corpId.equals(relationEntity.getId())) {
                            resultList.add(relationEntity);
                            break;
                        }
                    }
                }
                //Entity 转DTO
                if(relationList != null && relationList.size() > 0){
                    relationDTOS = JSONObject.parseArray(JSONObject.toJSONString(resultList),RelationDTO.class);
                }
            }
            //返回结果
            Map<String,List> map = new HashedMap();
            map.put("PurchaseLegalPerson",relationDTOS);
            result.setData(map);

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return result;
    }

    /**
     * 根据税率id 获取税率类信息
     * @param id
     * @return
     */
    private List<TaxrateDTO> getByRateId(String id) throws Exception {
        List<TaxrateDTO> taxrateDTOS = new ArrayList<>();
        if (id != null) {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, id));
            //id
            ormParam.addColumn(NodeConstant.ID)
                    //名称
                    .addColumn(TaxrateProperty.TAXR_NAME)
                    //是否可抵扣
                    .addColumn(TaxrateProperty.TAXR_ISDEDUCT)
                    //详细税率
                    .addColumn(TaxrateProperty.TAXR_DETAIL);
            logger.info("获取物品类.采购报价集.税率id.税率类的sql语句 rows1994" + ormParam.toString());
            List<TaxrateEntity> taxrateEntities = ormService.selectBeanList(TaxrateEntity.class, ormParam);
            if (!taxrateEntities.isEmpty() && taxrateEntities.size() > 0) {
                //转DTO
                taxrateDTOS = JSONObject.parseArray(JSONObject.toJSONString(taxrateEntities), TaxrateDTO.class);
            }
        }
        return taxrateDTOS;
    }

    private List<PuorderEntity> findPuorderById(String orderId) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID)
                .addColumn(OrderProperty.ORDE_NBR)
                .addColumn(OrderProperty.ORDE_STATUS)
                .addColumn(PuorderProperty.PUOD_STATUS);
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID , orderId));
        List<PuorderEntity> puorderEntities = ormService.selectBeanList(PuorderEntity.class , ormParam);
        return puorderEntities;
    }

    /**
     * 根据单位组id 单位 获取小数点位数 小数点规整方法字段信息
     * @param groupId
     * @param name
     * @return
     * @throws Exception
     */
    public Map<String, String> getNumAndPut(String groupId, String name) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, groupId),
                ormParam.getEqualXML(MeasMeasDefineSetaProperty.MEAS_DNAME, name),
                ormParam.getEqualXML(MeasMeasDefineSetaProperty.MEAS_DENABLE, 1)));
        ormParam.addColumn(MeasMeasDefineSetaProperty.MEAS_DRADIX_NUM)
                .addColumn(MeasMeasDefineSetaProperty.MEAS_DRADIX_PUT);
        List<MeasMeasDefineSetaEntity> entities = ormService.selectBeanList(MeasMeasDefineSetaEntity.class, ormParam);
        Map<String, String> map = new HashMap<>();
        if (entities != null && entities.size() > 0) {
            map.put("num", entities.get(0).getMeas_dradix_num().toString());
            map.put("put", entities.get(0).getMeas_dradix_put());
        }
        return map;

    }

}
