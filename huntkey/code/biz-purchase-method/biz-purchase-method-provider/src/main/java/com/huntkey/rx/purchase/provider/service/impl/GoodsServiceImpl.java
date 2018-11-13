package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.DefaultGenerateStorageClient;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.ErrorMsgConstants;
import com.huntkey.rx.purchase.common.constants.GoodsStatus;
import com.huntkey.rx.purchase.common.constants.OrderConstants;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.goods.*;
import com.huntkey.rx.purchase.common.model.park.ParkDTO;
import com.huntkey.rx.purchase.common.model.park.SimpleParkDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuqoLadderPriceSetDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuqoLadderPriceSetOldDTO;
import com.huntkey.rx.purchase.common.model.puquoteorder.SimplePuqoPriceSetDTO;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.GoodsService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 * @author  liangh on 2017/12/27 0027.
 */
@Service
public class GoodsServiceImpl implements GoodsService {

    Logger logger = LoggerFactory.getLogger(GoodsServiceImpl.class);

    @Autowired
    OrmService ormService;

    @Autowired
    private DefaultGenerateStorageClient defaultGenerateStorageClient;

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Result query(String godsCode, String godsName, String godsCodeName, String godsPmCode, String godsStatus, String godsPark,
                        String godsClass, String godsUnit, String godsModel, String godsSupplier, int pageNum, int pageSize) {
        logger.info("查询物品列表信息开始服务......");
        Result result = new Result();
        try {
            //设置查询字段，拼接查询物品表条件
            OrmParam ormParamGoods = joinSearchCondition(godsCode, godsName, godsCodeName, godsUnit, godsPmCode, godsStatus, godsPark, godsClass, godsModel);
            ormParamGoods.addOrderExpElement(SQLSortEnum.ASC,"gods_code");
            //当有子表查询字段时，且子表查询的结果为空，那么，主表也没有数据
            if(ormParamGoods == null) {
                Pagination<GoodsEntity> page = new Pagination<>(new ArrayList<>(),1,10,0);
                result.setData(page);
                return result;
            }
            List<GoodsEntity> goodsList = new ArrayList<>();
            //如果供应商不为空，则直接根据供应商查物品
            if(!StringUtil.isNullOrEmpty(godsSupplier)) {
                OrmParam ormParamSup = new OrmParam();
                ormParamSup.addColumn(EdmSysColumn.PID);
                ormParamSup.setWhereExp(ormParamSup.getEqualXML(GodsGodsParkSetaProperty.GODS_SUPPLIER, godsSupplier));
                List<GodsGodsParkSetaEntity> godsGodsParkSetaList = ormService.selectBeanList(GodsGodsParkSetaEntity.class, ormParamSup);
                if(!godsGodsParkSetaList.isEmpty()) {
                    OrmParam ormParamGd = new OrmParam();
                    for(GodsGodsParkSetaEntity ggps:godsGodsParkSetaList) {
                        ormParamGd.setWhereExp(OrmParam.or(ormParamGd.getWhereExp(),
                                ormParamGd.getEqualXML(NodeConstant.ID, ggps.getPid())));
                    }
                    goodsList = ormService.selectBeanList(GoodsEntity.class, ormParamGd);
                }
            }else {
                goodsList = ormService.selectBeanList(GoodsEntity.class, ormParamGoods);
            }
            if(goodsList!=null || goodsList.size()>0) {
                List<GoodsDTO> goodsDTOList = new ArrayList<>();
                for(GoodsEntity ge:goodsList) {
                    GoodsDTO gd = new GoodsDTO();
                    //把物品表需要展示的字段加上
                    gd.setId(ge.getId());
                    gd.setGodsCode(ge.getGods_code());
                    gd.setGodsName(ge.getGods_name());
                    gd.setGodsModel(ge.getGods_model());
                    gd.setCretime(ge.getCretime().toString());
                    //查询单位名称
                    if(!StringUtil.isNullOrEmpty(ge.getGods_unit_group())) {
                        OrmParam ormParam = new OrmParam();
                        ormParam.addColumn(EdmSysColumn.ID + "," + MeasureunitProperty.MEAS_NAME);
                        ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, ge.getGods_unit_group()));
                        List<MeasureunitEntity> list = ormService.selectBeanList(MeasureunitEntity.class, ormParam);
                        if(list.size()>0) {
                            gd.setGodsUnitName(list.get(0).getMeas_name());
                        }
                    }
                    //查询物品分类名称
                    if(!StringUtil.isNullOrEmpty(ge.getGods_class())) {
                        OrmParam ormParam = new OrmParam();
                        ormParam.addColumn(EdmSysColumn.ID + "," + MonitorProperty.MONI_NODE_NO + "," + MonitorProperty.MONI_NODE_NAME);
                        ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, ge.getGods_class()));
                        List<GoodsclasstreeEntity> list = ormService.selectBeanList(GoodsclasstreeEntity.class, ormParam);
                        if(list.size()>0) {
                            gd.setGodsClassName(list.get(0).getMoni_node_no()+"-"+list.get(0).getMoni_node_name());
                        }
                    }
                    //把园区属性表需要展示的字段加上
                    //查询PM码和状态
                    OrmParam ormParamPMStatus = new OrmParam();
                    ormParamPMStatus.addColumn(EdmSysColumn.ID + "," + GodsGodsParkSetaProperty.GODS_PARK
                            + "," + GodsGodsParkSetaProperty.GODS_PM_CODE + "," + GodsGodsParkSetaProperty.GODS_STATUS);
                    ormParamPMStatus.setWhereExp(ormParamPMStatus.getEqualXML(NodeConstant.PID, ge.getId()));
                    List<GodsGodsParkSetaEntity> ggpsList = ormService.selectBeanList(GodsGodsParkSetaEntity.class, ormParamPMStatus);
                    List<String> PMList = new ArrayList<>();
                    List<String> statusList = new ArrayList<>();
                    if(ggpsList.size()>0) {
                        for(GodsGodsParkSetaEntity ggps:ggpsList) {
                            //查询园区
                            String park = "";
                            OrmParam ormParamPark = new OrmParam();
                            ormParamPark.addColumn(EdmSysColumn.ID + "," + ParkProperty.RPAK_NAME);
                            ormParamPark.setWhereExp(ormParamPark.getEqualXML(NodeConstant.ID, ggps.getGods_park()));
                            List<ParkEntity> parkList = ormService.selectBeanList(ParkEntity.class, ormParamPark);
                            if(parkList.size()>0) {
                                park = parkList.get(0).getRpak_name();
                            }
                            if(!":".equals(park+":"+ggps.getGods_pm_code())) {
                                PMList.add(park+":"+ggps.getGods_pm_code());
                            }
                            if(!":".equals(park+":"+ggps.getGods_status())) {
                                statusList.add(park+":"+ggps.getGods_status());
                            }
                        }
                    }
                    //查询币别名称和单价
                    OrmParam ormParamPupric = new OrmParam();
                    ormParamPupric.addColumn(EdmSysColumn.ID + "," + GodsGodsPupricSetaProperty.GODS_PCURR + ","
                            + GodsGodsPupricSetaProperty.GODS_PPRICE);
                    ormParamPupric.setWhereExp(ormParamPupric.getEqualXML(NodeConstant.PID, ge.getId()));
                    ormParamPupric.addOrderExpElement(SQLSortEnum.ASC, GodsGodsPupricSetaProperty.GODS_PPRICE);
                    List<GodsGodsPupricSetaEntity> godsGodsPupricSetaList = ormService.selectBeanList(GodsGodsPupricSetaEntity.class, ormParamPupric);
                    if(!godsGodsPupricSetaList.isEmpty()) {
                        //设置币别id
                        gd.setGodsPcurr(godsGodsPupricSetaList.get(0).getGods_pcurr());
                        //查询币别名称
                        OrmParam ormParamCurr = new OrmParam();
                        ormParamCurr.addColumn(CurrencyProperty.CURR_NAME);
                        ormParamCurr.setWhereExp(ormParamCurr.getEqualXML(NodeConstant.ID, godsGodsPupricSetaList.get(0).getGods_pcurr()));
                        List<CurrencyEntity> currencyList = ormService.selectBeanList(CurrencyEntity.class, ormParamCurr);
                        if(!currencyList.isEmpty()) {
                            gd.setGodsPcurrName(currencyList.get(0).getCurr_name());
                        }
                        //查询单价
                        if(godsGodsPupricSetaList.size()==1) {
                            //一个单价的情况
                            gd.setGodsPprice(godsGodsPupricSetaList.get(0).getGods_pprice().toString());
                        }else{
                            //阶梯报价的情况
                            gd.setGodsPprice(godsGodsPupricSetaList.get(0).getGods_pprice().toString() + "~"
                                    + godsGodsPupricSetaList.get(godsGodsPupricSetaList.size()-1).getGods_pprice().toString());
                        }
                    }
                    gd.setGodsPmCodeList(PMList);
                    gd.setGodsStatusList(statusList);
                    goodsDTOList.add(gd);
                }
                Pagination<GoodsDTO> page = new Pagination<>(goodsDTOList,pageNum,pageSize,goodsList.size());
                result.setData(page);
            }else {
                Pagination<GoodsDTO> page = new Pagination<>(new ArrayList<>(),1,10,0);
                result.setData(page);
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODS_QUERY + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODS_QUERY + e.getMessage());
        }
        return result;
    }

    private OrmParam joinSearchCondition(String godsCode, String godsName, String godsCodeName, String godsUnit, String godsPmCode, String godsStatus,
                                         String godsPark, String godsClass, String godsModel) throws Exception {
        //拼接查询物品表条件
        OrmParam ormParamGoods = new OrmParam();
        //设置查询字段
        ormParamGoods.addColumn(EdmSysColumn.ID + ","
                + GoodsProperty.GODS_CODE + ","
                + GoodsProperty.GODS_NAME + ","
                + GoodsProperty.GODS_MODEL + ","
                + GoodsProperty.GODS_UNIT + ","
                + GoodsProperty.GODS_UNIT_GROUP + ","
                + GoodsProperty.GODS_CLASS + "," + "cretime"
        );
        //拼接查询园区属性表条件
        OrmParam ormParamGodsParkSet = new OrmParam();
        ormParamGodsParkSet.addColumn(EdmSysColumn.ID + "," + EdmSysColumn.PID);
        if(!StringUtil.isNullOrEmpty(godsPmCode)){
            ormParamGodsParkSet.setWhereExp(OrmParam.and(ormParamGodsParkSet.getWhereExp(),
                    ormParamGodsParkSet.getEqualXML(GodsGodsParkSetaProperty.GODS_PM_CODE, godsPmCode)));
        }
        if(!StringUtil.isNullOrEmpty(godsStatus)){
            ormParamGodsParkSet.setWhereExp(OrmParam.and(ormParamGodsParkSet.getWhereExp(),
                    ormParamGodsParkSet.getEqualXML(GodsGodsParkSetaProperty.GODS_STATUS, godsStatus)));
        }
        if(!StringUtil.isNullOrEmpty(godsPark)){
            ormParamGodsParkSet.setWhereExp(OrmParam.and(ormParamGodsParkSet.getWhereExp(),
                    ormParamGodsParkSet.getEqualXML(GodsGodsParkSetaProperty.GODS_PARK, godsPark)));
        }
        List<GodsGodsParkSetaEntity> godsGodsParkSetaEntities = ormService.selectBeanList(GodsGodsParkSetaEntity.class, ormParamGodsParkSet);
        if(godsGodsParkSetaEntities!=null && godsGodsParkSetaEntities.size()>0) {
            for(GodsGodsParkSetaEntity ggpse:godsGodsParkSetaEntities) {
                ormParamGoods.setWhereExp(OrmParam.or(ormParamGoods.getWhereExp(),
                        ormParamGoods.getEqualXML(NodeConstant.ID, ggpse.getPid())));
            }
        }else {
            if(!StringUtil.isNullOrEmpty(godsPmCode) || !StringUtil.isNullOrEmpty(godsStatus) || !StringUtil.isNullOrEmpty(godsPark)) {
                return null;
            }
        }
        //拼接查询条件，查询物品表信息
        if(!StringUtil.isNullOrEmpty(godsCode)){
            ormParamGoods.setWhereExp(OrmParam.and(ormParamGoods.getWhereExp(),
                    ormParamGoods.getMatchMiddleXML(GoodsProperty.GODS_CODE, godsCode)));
        }
        if(!StringUtil.isNullOrEmpty(godsName)){
            ormParamGoods.setWhereExp(OrmParam.and(ormParamGoods.getWhereExp(),
                    ormParamGoods.getMatchMiddleXML(GoodsProperty.GODS_NAME, godsName)));
        }
        if(!StringUtil.isNullOrEmpty(godsCodeName)){
            ormParamGoods.setWhereExp(OrmParam.and(ormParamGoods.getWhereExp(),
                    OrmParam.or(ormParamGoods.getMatchMiddleXML(GoodsProperty.GODS_CODE, godsCodeName),
                            ormParamGoods.getMatchMiddleXML(GoodsProperty.GODS_NAME, godsCodeName))));
        }
        if(!StringUtil.isNullOrEmpty(godsUnit)){
            ormParamGoods.setWhereExp(OrmParam.and(ormParamGoods.getWhereExp(),
                    ormParamGoods.getEqualXML(GoodsProperty.GODS_UNIT, godsUnit)));
        }
        if(!StringUtil.isNullOrEmpty(godsModel)){
            ormParamGoods.setWhereExp(OrmParam.and(ormParamGoods.getWhereExp(),
                    ormParamGoods.getEqualXML(GoodsProperty.GODS_MODEL, godsModel)));
        }
        if(!StringUtil.isNullOrEmpty(godsClass)){
            ormParamGoods.setWhereExp(OrmParam.and(ormParamGoods.getWhereExp(),
                    ormParamGoods.getEqualXML(GoodsProperty.GODS_CLASS, godsClass)));
        }
        return ormParamGoods;
    }

    /**
     * 根据物品类id加载物品信息接口
     * @param id 物品id
     */
    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Result load(String id) {
        logger.info("根据物品类id加载物品信息开始服务......");
        Result result = new Result();
        GoodsDTO gd = new GoodsDTO();
        gd.setId(id);
        try{
            //查询基本信息和默认园区的信息
            OrmParam ormParamGoods = new OrmParam();
            ormParamGoods.addColumn(EdmSysColumn.ID + "," + GoodsProperty.GODS_CODE + ","
                    + GoodsProperty.GODS_NAME + "," + GoodsProperty.GODS_MODEL + ","
                    + GoodsProperty.GODS_CLASS + "," + GoodsProperty.GODS_UNIT + ","
                    + GoodsProperty.GODS_UNIT_GROUP + "," + GoodsProperty.GODS_BAR_CODE + ","
                    + GoodsProperty.GODS_BRAND + "," + GoodsProperty.GODS_ORIGIN + ","
                    + GoodsProperty.GODS_WEIGHT_GROSS + "," + GoodsProperty.GODS_WEIGHT_NET + ","
                    + GoodsProperty.GODS_VOLUME_BULK + "," + GoodsProperty.GODS_VOLUME_NET + ","
                    + GoodsProperty.GODS_DESC + "," + GoodsProperty.GODS_CHECK_CYC + ","
                    + GoodsProperty.GODS_RECEIPT_TOL + "," + GoodsProperty.GODS_SHELFLIFE + ","
                    + GoodsProperty.GODS_ORDMULT_QTY + "," + GoodsProperty.GODS_PALLET_QTY + "," + GoodsProperty.GODS_ORDMIN_QTY);
            ormParamGoods.setWhereExp(ormParamGoods.getEqualXML(NodeConstant.ID, id));
            List<GoodsEntity> goodsList = ormService.selectBeanList(GoodsEntity.class, ormParamGoods);
            if(!goodsList.isEmpty()) {
                //物品表的数据
                GoodsEntity ge = goodsList.get(0);
                gd.setGodsCode(ge.getGods_code());
                gd.setGodsName(ge.getGods_name());
                gd.setGodsModel(ge.getGods_model());
                gd.setGodsClass(ge.getGods_class());
                gd.setGodsUnitName(ge.getGods_unit());
                gd.setGodsUnitGroup((String)ge.getGods_unit_group());
                gd.setGodsBarCode(ge.getGods_bar_code());
                gd.setGodsBrand(ge.getGods_brand());
                gd.setGodsOrigin(ge.getGods_origin());
                gd.setGodsWeightGross(ge.getGods_weight_gross());
                gd.setGodsWeightNet(ge.getGods_weight_net());
                gd.setGodsVolumeBulk(ge.getGods_volume_bulk());
                gd.setGodsVolumeNet(ge.getGods_volume_net());
                gd.setGodsDesc(ge.getGods_desc());
                gd.setGodsCheckCyc(ge.getGods_check_cyc());
                gd.setGodsShelflife(ge.getGods_shelflife());
                gd.setGodsReceiptTol(ge.getGods_receipt_tol());
                gd.setGodsOrdmultQty(ge.getGods_ordmult_qty());
                gd.setGodsOrdminQty(ge.getGods_ordmin_qty());
                gd.setGodsPalletQty(ge.getGods_pallet_qty());
                //查询物品单位id
                OrmParam ormParamMeasDef = new OrmParam();
                ormParamMeasDef.addColumn(EdmSysColumn.ID);
                ormParamMeasDef.setWhereExp(OrmParam.and(ormParamMeasDef.getEqualXML(NodeConstant.PID, ge.getGods_unit_group()),
                        ormParamMeasDef.getEqualXML(MeasMeasDefineSetaProperty.MEAS_DNAME, ge.getGods_unit())));
                List<MeasMeasDefineSetaEntity> measMeasDefineSetaList = ormService.selectBeanList(MeasMeasDefineSetaEntity.class, ormParamMeasDef);
                if(!measMeasDefineSetaList.isEmpty()) {
                    gd.setGodsUnit(measMeasDefineSetaList.get(0).getId());
                }
                //设置物品分类名称
                OrmParam ormParamGoodsClassTree = new OrmParam();
                ormParamGoodsClassTree.addColumn(EdmSysColumn.ID + "," + MonitorProperty.MONI_NODE_NAME);
                ormParamGoodsClassTree.setWhereExp(ormParamGoodsClassTree.getEqualXML(NodeConstant.ID, gd.getGodsClass()));
                List<GoodsclasstreeEntity> goodsclasstreeList = ormService.selectBeanList(GoodsclasstreeEntity.class, ormParamGoodsClassTree);
                if(!goodsclasstreeList.isEmpty()) {
                    gd.setGodsClassName(goodsclasstreeList.get(0).getMoni_node_name());
                }
                //设置物品特征列表
                OrmParam ormParamFea = new OrmParam();
                ormParamFea.addColumn(EdmSysColumn.ID + "," + GodsGodsFeatureSetaProperty.GODS_FEATURE + ","
                        + GodsGodsFeatureSetaProperty.GODS_FEATURE_VAL);
                ormParamFea.setWhereExp(ormParamFea.getEqualXML(NodeConstant.PID, id));
                List<GodsGodsFeatureSetaEntity> ggfsList = ormService.selectBeanList(GodsGodsFeatureSetaEntity.class, ormParamFea);
                List<GoodsFeaDTO> gfdList = new ArrayList<>();
                if(!ggfsList.isEmpty()) {
                    for(GodsGodsFeatureSetaEntity ggfs:ggfsList) {
                        GoodsFeaDTO gfd = new GoodsFeaDTO();
                        //根据物品特征字段存储的物品特征id去查询物品特征信息
                        OrmParam ormParam = new OrmParam();
                        ormParam.addColumn(EdmSysColumn.ID + "," + GoodsfeatureProperty.GOFT_NAME + ","
                                + GoodsfeatureProperty.GOFT_SOURCE + "," + GoodsfeatureProperty.GOFT_TYPE + "," + GoodsfeatureProperty.GOFT_ISMULT);
                        ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, ggfs.getGods_feature()));
                        List<GoodsfeatureEntity> list = ormService.selectBeanList(GoodsfeatureEntity.class, ormParam);
                        if(!list.isEmpty()) {
                            GoodsfeatureEntity gfe = list.get(0);
                            gfd.setGodsWordListName(gfe.getGoft_name());
                            //将枚举id转成枚举info_code
                            OrmParam ormParamWord = new OrmParam();
                            ormParamWord.setWhereExp(ormParamWord.getEqualXML(NodeConstant.ID, gfe.getGoft_source()));
                            List<WordlistEntity> wordList = ormService.selectBeanList(WordlistEntity.class, ormParamWord);
                            if(!wordList.isEmpty()) {
                                gfd.setGodsSourceCode(wordList.get(0).getInfo_code());
                            }
                            gfd.setGodsFeatureType(gfe.getGoft_type());
                            gfd.setGoftIsmult(gfe.getGoft_ismult());
                        }
                        gfd.setId(ggfs.getId());
                        gfd.setGodsFeature(ggfs.getGods_feature());
                        gfd.setGodsFeatureVal(ggfs.getGods_feature_val());
                        gfdList.add(gfd);
                    }
                }
                gd.setGodsFeaList(gfdList);
                //查询是否存在物品维护单，如果存在，则带回维护单id，维护单号，维护单状态
                OrmParam ormParamMaint = new OrmParam();
                ormParamMaint.addColumn(EdmSysColumn.ID + "," + OrderProperty.ORDE_NBR + "," + OrderProperty.ORDE_STATUS);
                ormParamMaint.setWhereExp(ormParamMaint.getEqualXML(GoodsmaintorderProperty.GOMO_CODE,ge.getGods_code()));
                List<GoodsmaintorderEntity> goodsmaintorderList = ormService.selectBeanList(GoodsmaintorderEntity.class, ormParamMaint);
                if(!goodsmaintorderList.isEmpty()) {
                    gd.setOrderId(goodsmaintorderList.get(0).getId());
                    gd.setOrderNbr(goodsmaintorderList.get(0).getOrde_nbr());
                    gd.setOrderStatus(goodsmaintorderList.get(0).getOrde_status());
                }
            }
            //查询默认园区信息
            OrmParam ormParamPk = new OrmParam();
            ormParamPk.addColumn(EdmSysColumn.ID + "," + ParkProperty.RPAK_ISDEFAULT + ","
                    + ParkProperty.RPAK_NAME);
            List<ParkEntity> parkList = ormService.selectBeanList(ParkEntity.class, ormParamPk);
            if(!parkList.isEmpty()) {
                List<GodsGodsParkSetaDTO> ggpsList = new ArrayList<>();
                for(ParkEntity pe:parkList) {
                    //根据园区id，查询物品类子表-园区信息表
                    OrmParam ormParam = new OrmParam();
                    ormParam.addColumn(EdmSysColumn.ID + "," + GodsGodsParkSetaProperty.GODS_COST_TYPE + ","
                            + GodsGodsParkSetaProperty.GODS_ISBATCHSPLIT + "," + GodsGodsParkSetaProperty.GODS_ISININSPECT + ","
                            + GodsGodsParkSetaProperty.GODS_ISOUTINSPECT + "," + GodsGodsParkSetaProperty.GODS_PARK + ","
                            + GodsGodsParkSetaProperty.GODS_PM_CODE + "," + GodsGodsParkSetaProperty.GODS_STATUS + ","
                            + GodsGodsParkSetaProperty.GODS_STOCK_MAX + "," + GodsGodsParkSetaProperty.GODS_STOCK_MIN + ","
                            + GodsGodsParkSetaProperty.GODS_STOCK_SFTY + "," + GodsGodsParkSetaProperty.GODS_SUPPLIER + ","
                            + GodsGodsParkSetaProperty.GODS_SUPPLIER_PART);
                    ormParam.setWhereExp(ormParam.getEqualXML(GodsGodsParkSetaProperty.GODS_PARK, pe.getId()));
                    ormParam.setWhereExp(OrmParam.and(ormParam.getWhereExp(),
                            ormParam.getEqualXML(NodeConstant.PID, id)));
                    List<GodsGodsParkSetaEntity> ggpseList = ormService.selectBeanList(GodsGodsParkSetaEntity.class, ormParam);
                    if(!ggpseList.isEmpty()) {
                        GodsGodsParkSetaEntity parkInfo = ggpseList.get(0);
                        GodsGodsParkSetaDTO ggps = new GodsGodsParkSetaDTO();
                        ggps.setId(parkInfo.getId());
                        ggps.setGodsCostType(parkInfo.getGods_cost_type());
                        ggps.setGodsIsbatchsplit(parkInfo.getGods_isbatchsplit());
                        ggps.setGodsIsininspect(parkInfo.getGods_isininspect());
                        ggps.setGodsIsoutinspect(parkInfo.getGods_isoutinspect());
                        ggps.setGodsPark(parkInfo.getGods_park());
                        ggps.setGodsPmCode(parkInfo.getGods_pm_code());
                        ggps.setGodsStatus(parkInfo.getGods_status());
                        ggps.setGodsStockMax(parkInfo.getGods_stock_max());
                        ggps.setGodsStockMin(parkInfo.getGods_stock_min());
                        ggps.setGodsStockSfty(parkInfo.getGods_stock_sfty());
                        ggps.setGodsSupplier(parkInfo.getGods_supplier());
                        //查询供应商名称
                        if(!StringUtil.isNullOrEmpty(parkInfo.getGods_supplier())) {
                            OrmParam ormParamSup = new OrmParam();
                            ormParamSup.addColumn(EdmSysColumn.ID + "," + RelationProperty.RELA_NAME);
                            ormParamSup.setWhereExp(ormParamSup.getEqualXML(NodeConstant.ID, parkInfo.getGods_supplier()));
                            List<RelationEntity> relationList = ormService.selectBeanList(RelationEntity.class, ormParamSup);
                            if(!relationList.isEmpty()) {
                                ggps.setGodsSupplierName(relationList.get(0).getRela_name());
                            }
                        }
                        ggps.setGodsSupplierPart(parkInfo.getGods_supplier_part());
                        ggps.setRpakName(pe.getRpak_name());
                        if(null!=pe.getRpak_isdefault()&&pe.getRpak_isdefault().intValue()==1){
                            ggps.setRpak_isdefault(1);
                        }else{
                            ggps.setRpak_isdefault(0);
                        }
                        ggpsList.add(ggps);
                    }
                }
                gd.setParkList(ggpsList);
            }
            //查询文档资料列表
            OrmParam ormParamDoc = new OrmParam();
            ormParamDoc.addColumn(EdmSysColumn.ID + "," + GodsGodsDocSetaProperty.GODS_DOC_NAME + ","
                    + GodsGodsDocSetaProperty.GODS_DOC_TYPE + "," + GodsGodsDocSetaProperty.GODS_DOC_URL);
            ormParamDoc.setWhereExp(ormParamDoc.getEqualXML(NodeConstant.PID, id));
            List<GodsGodsDocSetaEntity> docList = ormService.selectBeanList(GodsGodsDocSetaEntity.class, ormParamDoc);
            List<GoodsDocDTO> gddList = new ArrayList<>();
            if(!docList.isEmpty()) {
                for(GodsGodsDocSetaEntity ggds:docList) {
                    GoodsDocDTO goodsDocDTO = JSONObject.parseObject(JSONObject.toJSONString(ggds),GoodsDocDTO.class);
                    gddList.add(goodsDocDTO);
                }
            }
            gd.setDocList(gddList);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODS_LOAD + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODS_LOAD + e.getMessage());
        }
        result.setData(gd);
        return result;
    }

    /**
     * @ 查询所有的园区，如果该园区已经有物品信息，则id即为该物品信息的id
     * @               如果该园区没有物品信息，则id为空
     * @param id 物品id
     */
    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Result loadParkList(String id) {
        logger.info("查询所有的园区信息开始服务......");
        Result result = new Result();
        try {
            //查询所有的园区信息
            OrmParam ormParamPark = new OrmParam();
            ormParamPark.addColumn(EdmSysColumn.ID + "," + ParkProperty.RPAK_NAME + ","
                                    + ParkProperty.RPAK_ISDEFAULT + "," + ParkProperty.RPAK_CODE + ","
                                    + ParkProperty.RPAK_SEQ + "," + ParkProperty.RPAK_ADDR);
            ormParamPark.addOrderExpElement(SQLSortEnum.ASC, "rpak_seq");
            List<ParkEntity> list = ormService.selectBeanList(ParkEntity.class, ormParamPark);
            List<ParkDTO> resultList = new ArrayList<>();
            if("".equals(id) || id== null) {
                for(ParkEntity pe:list) {
                    ParkDTO parkDTO = JSONObject.parseObject(JSONObject.toJSONString(pe),ParkDTO.class);
                    parkDTO.setHaveGoods(false);
                    resultList.add(parkDTO);
                }
                result.setData(resultList);
            }else {
                //查询当前物品下是否有该园区，设置状态位,有则设置为true，否则设置为false
                if(!list.isEmpty()) {
                    for(ParkEntity pe:list) {
                        OrmParam ormParamGodParkSet = new OrmParam();
                        ormParamGodParkSet.addColumn(EdmSysColumn.ID);
                        ormParamGodParkSet.setWhereExp(ormParamGodParkSet.getEqualXML(NodeConstant.PID, id));
                        ormParamGodParkSet.setWhereExp(OrmParam.and(ormParamGodParkSet.getWhereExp(),
                                ormParamGodParkSet.getEqualXML(GodsGodsParkSetaProperty.GODS_PARK, pe.getId())));
                        List<GodsGodsParkSetaEntity> ggpsList = ormService.selectBeanList(GodsGodsParkSetaEntity.class, ormParamGodParkSet);
                        ParkDTO parkDTO = JSONObject.parseObject(JSONObject.toJSONString(pe),ParkDTO.class);
                        if(!ggpsList.isEmpty()) {
                            parkDTO.setHaveGoods(true);
                        }else {
                            parkDTO.setHaveGoods(false);
                        }
                        resultList.add(parkDTO);
                    }
                    result.setData(resultList);
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODS_PARK + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODS_PARK + e.getMessage());
        }
        return result;
    }

    @Override
    @Transactional(readOnly = true,rollbackFor = Exception.class)
    public Result queryUnitTree() {
        Result result = new Result();
        try {
            List<UnitTreeDTO> unitTreeDTOList = new ArrayList<>();
            OrmParam ormParamMea = new OrmParam();
            ormParamMea.addColumn(EdmSysColumn.ID + "," +MeasureunitProperty.MEAS_NAME);
            List<MeasureunitEntity> meaUnitList = ormService.selectBeanList(MeasureunitEntity.class, ormParamMea);
            if(!meaUnitList.isEmpty()) {
                for(MeasureunitEntity mue:meaUnitList) {
                    UnitTreeDTO unitTreeDTO = new UnitTreeDTO();
                    unitTreeDTO.setId(mue.getId());
                    unitTreeDTO.setLabel(mue.getMeas_name());
                    OrmParam ormParamDef = new OrmParam();//meas_define_set
                    ormParamDef.addColumn(EdmSysColumn.ID + "," + MeasMeasDefineSetaProperty.MEAS_DNAME);
                    ormParamDef.setWhereExp(ormParamDef.getEqualXML(NodeConstant.PID, mue.getId()));
                    List<MeasMeasDefineSetaEntity> measMeasDefineList = ormService.selectBeanList(MeasMeasDefineSetaEntity.class, ormParamDef);
                    if(!measMeasDefineList.isEmpty()) {
                        for(MeasMeasDefineSetaEntity mmds:measMeasDefineList) {
                            UnitTreeDTO temp = new UnitTreeDTO();
                            temp.setId(mmds.getId());
                            temp.setLabel(mmds.getMeas_dname());
                            List<UnitTreeDTO> tempList = unitTreeDTO.getChildren();
                            if(tempList == null || tempList.isEmpty()) {
                                tempList = new ArrayList<>();
                            }
                            tempList.add(temp);
                            unitTreeDTO.setChildren(tempList);
                        }
                    }
                    unitTreeDTOList.add(unitTreeDTO);
                }
            }
            result.setData(unitTreeDTOList);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_UNIT_TREE + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_UNIT_TREE + e.getMessage());
        }
        return result;
    }

    /**
     * 附件下载
     *
     * @param id
     * @param response
     * @return
     */
    @Override
    @Transactional(readOnly = false,rollbackFor = Exception.class)
    public ResponseEntity<byte[]> fastDFSDownload(String id, HttpServletResponse response) {
        String groupName = "group1";
        byte[] content = null;
        HttpHeaders headers = new HttpHeaders();

        try{
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID,id)));
            List<GodsGodsDocSetaEntity> ggdsList = ormService.selectBeanList(GodsGodsDocSetaEntity.class,ormParam);

            if(!ggdsList.isEmpty()){
                String edmPath = NullUtils.valueOf(ggdsList.get(0).getGods_doc_url()).replace(groupName + "/", "");
                String filename =  NullUtils.valueOf(ggdsList.get(0).getGods_doc_name());
                DownloadByteArray callback = new DownloadByteArray();
                content = defaultGenerateStorageClient.downloadFile(groupName, edmPath, callback);
                try {
                    headers.setContentDispositionFormData("attachment", new String(filename.getBytes("UTF-8"), "iso-8859-1"));
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
                return new ResponseEntity<byte[]>(content, headers, HttpStatus.CREATED);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 根据供应商ID和物品编码模糊查询匹配的物品编码列表
     * @param supplierId 供应商ID
     * @param goodsCode 物品编码
     * @return
     */
    @Override
    public Result queryGoodsCode(String supplierId, String goodsCode) {
        Result result = new Result();
        try {
            //供应商id、商品状态查询物品类园区属性
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(GodsGodsParkSetaProperty.GODS_SUPPLIER, supplierId),
                    ormParam.getInXML(GodsGodsParkSetaProperty.GODS_STATUS, new String[]{GoodsStatus.UP, GoodsStatus.AC}),
                    ormParam.getIsNotNull(GodsGodsParkSetaProperty.GODS_PARK)));
            ormParam.addColumn(NodeConstant.PID).addColumn(GodsGodsParkSetaProperty.GODS_PARK);
            List<Map<String, Object>> list = ormService.selectMapList(GodsGodsParkSetaEntity.class, ormParam);

            //根据园区是否存在获取园区属性pid集合
            List<Object> pids = new ArrayList<>();
            if (list != null && list.size() > 0) {
                for (Map<String, Object> item : list) {
                    if (item != null && !StringUtil.isNullOrEmpty(item.get(NodeConstant.PID))
                            && !StringUtil.isNullOrEmpty(item.get(GodsGodsParkSetaProperty.GODS_PARK))) {
                        ParkEntity parkEntity = loadParkById(item.get(GodsGodsParkSetaProperty.GODS_PARK).toString());
                        if (parkEntity != null) {
                            pids.add(item.get(NodeConstant.PID));
                        }
                    }
                }
            }

            //获取物品编号
            List<GoodsCodeDTO> resultList = new ArrayList<>();
            if (pids.size() > 0) {
                ormParam = new OrmParam();
                //根据园区属性的pid 查询物品类
                ormParam.setWhereExp(ormParam.getInXML(NodeConstant.ID, pids.toArray()));
                if(!StringUtil.isNullOrEmpty(goodsCode)){
                    //如果模糊查询的商品编码填写
                    ormParam.setWhereExp(ormParam.and(ormParam.getWhereExp(),
                            ormParam.getMatchMiddleXML(GoodsProperty.GODS_CODE, goodsCode)));
                }
                ormParam.addColumn(NodeConstant.ID).addColumn(GoodsProperty.GODS_CODE);
                List<Map<String, Object>> mapList = ormService.selectMapList(GoodsEntity.class, ormParam);
                resultList = JSONObject.parseArray(JSONObject.toJSONString(mapList), GoodsCodeDTO.class);
            }

            result.setData(resultList);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }

    /**
     * 获取可选园区和对应的采购价格，如果单据号非空，则可选园区包含单据类中的园区及对应的采购价格，否则，可选园区为资源类中的园区及对应的采购价格
     * @param orderId    单据ID
     * @param supplierId 供应商ID
     * @param currencyId 币别ID
     * @param taxrateId  税率ID
     * @param goodsId    物品ID
     * @return
     * @throws Exception
     */
    @Override
    @NotNull
    public List<SimplePuqoPriceSetDTO> getParkAndPrice(String orderId, String supplierId, String currencyId, String taxrateId, String goodsId) throws Exception {
        List<SimplePuqoPriceSetDTO> simplePuqoPriceSetDTOS = new ArrayList<>();

        //获取物品类中的可选园区
        List<SimpleParkDTO> simpleParkDTOS = getSimpleParkDTOS(supplierId, goodsId);
        //如果有可选园区，则设置园区对应的采购价格
        if (simpleParkDTOS.size() > 0) {
            for (SimpleParkDTO simpleParkDTO : simpleParkDTOS) {
                SimplePuqoPriceSetDTO simplePuqoPriceSetDTO = getSimplePuqoPriceSetDTO(orderId, supplierId, currencyId, taxrateId,
                        goodsId, simpleParkDTO.getPuqoPark());
                simplePuqoPriceSetDTO.setParkName(simpleParkDTO.getParkName());
                simplePuqoPriceSetDTO.setPuqoPark(simpleParkDTO.getPuqoPark());
                simplePuqoPriceSetDTOS.add(simplePuqoPriceSetDTO);
            }
        }

        return simplePuqoPriceSetDTOS;
    }

    /**
     * 获取物品类中的可选园区
     * @param supplierId 供应商ID
     * @param goodsId    物品ID
     * @return
     * @throws Exception
     */
    @Override
    @NotNull
    public List<SimpleParkDTO> getSimpleParkDTOS(String supplierId, String goodsId) throws Exception {
        List<SimpleParkDTO> simpleParkDTOS = new ArrayList<>();
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, goodsId),
                ormParam.getEqualXML(GodsGodsParkSetaProperty.GODS_SUPPLIER, supplierId),
                ormParam.getInXML(GodsGodsParkSetaProperty.GODS_STATUS, new String[]{GoodsStatus.UP, GoodsStatus.AC})));
        ormParam.addColumn(GodsGodsParkSetaProperty.GODS_PARK);
        List<Map<String, Object>> list = ormService.selectMapList(GodsGodsParkSetaEntity.class, ormParam);
        if (list != null && list.size() > 0) {
            for (Map<String, Object> objectMap : list) {
                if (objectMap != null && objectMap.get(GodsGodsParkSetaProperty.GODS_PARK) != null) {
                    ParkEntity parkEntity = loadParkById(objectMap.get(GodsGodsParkSetaProperty.GODS_PARK).toString());
                    if (parkEntity != null) {
                        SimpleParkDTO simpleParkDTO = new SimpleParkDTO();
                        simpleParkDTO.setPuqoPark(parkEntity.getId());
                        simpleParkDTO.setParkName(parkEntity.getRpak_name());
                        if (!simpleParkDTOS.contains(simpleParkDTO)) {
                            simpleParkDTOS.add(simpleParkDTO);
                        }
                    }
                }
            }
        }

        return simpleParkDTOS;
    }

    /**
     * 获取物品的采购价格信息
     * @param supplierId 供应商ID
     * @param currencyId 币别ID
     * @param taxrateId  税率ID
     * @param goodsId    物品ID
     * @return
     */
    @Override
    public Result queryPriceChangeDetail(String orderId, String supplierId, String currencyId,
                                         String taxrateId, String goodsId) {
        Result result = new Result();
        try {
            //获取物品
            OrmParam param = new OrmParam();
            param.setWhereExp(param.getEqualXML(NodeConstant.ID, goodsId));
            param.addColumn(GoodsProperty.GODS_NAME).addColumn(GoodsProperty.GODS_CODE)
                    .addColumn(GoodsProperty.GODS_MODEL).addColumn(GoodsProperty.GODS_UNIT);
            List<GoodsEntity> goodsList = ormService.selectBeanList(GoodsEntity.class, param);

            //获取可选园区和对应的采购价格
            List<SimplePuqoPriceSetDTO> simplePuqoPriceSetDTOS = getParkAndPrice(orderId, supplierId, currencyId, taxrateId, goodsId);

            //组装结果
            GoodsInfoAndParkDTO goodsInfoAndParkDTO = new GoodsInfoAndParkDTO();
            if (goodsList != null && goodsList.size() > 0) {
                GoodsEntity goods = goodsList.get(0);
                goodsInfoAndParkDTO.setPuqoGoods(goodsId);
                goodsInfoAndParkDTO.setGodsCode(goods.getGods_code());
                goodsInfoAndParkDTO.setGodsModel(goods.getGods_model());
                goodsInfoAndParkDTO.setGodsName(goods.getGods_name());
                goodsInfoAndParkDTO.setPuqoUm(goods.getGods_unit());
            }
            if (simplePuqoPriceSetDTOS.size() > 0) {
                goodsInfoAndParkDTO.setParkName(simplePuqoPriceSetDTOS.get(0).getParkName());
                goodsInfoAndParkDTO.setPuqoPark(simplePuqoPriceSetDTOS.get(0).getPuqoPark());
                //设置默认园区的采购价格
                setPriceOfDefaultPark(goodsInfoAndParkDTO, simplePuqoPriceSetDTOS.get(0));
                //设置可选园区和对应的采购价格
                goodsInfoAndParkDTO.setSelectableParks(simplePuqoPriceSetDTOS);
            }

            result.setData(goodsInfoAndParkDTO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }

        return result;
    }

    @NotNull
    private SimplePuqoPriceSetDTO getSimplePuqoPriceSetDTO(String orderId, String supplierId, String currencyId,
                                                           String taxrateId, String goodsId, String parkId) throws Exception {
        SimplePuqoPriceSetDTO simplePuqoPriceSetDTO = new SimplePuqoPriceSetDTO();
        //查询已存在的临时单和待审单的单号
        String orderNumber = getExistOrder(orderId, supplierId, currencyId, taxrateId, goodsId, parkId);
        if (!StringUtil.isNullOrEmpty(orderNumber)) {
            simplePuqoPriceSetDTO.setOrder(orderNumber);
            return simplePuqoPriceSetDTO;
        }

        boolean existOrder = isExistOrder(orderId, goodsId, parkId);
        if (!existOrder) {
            //设置资源类园区对应的采购价格
            String goodsStatus = getGoodsStatus(supplierId, goodsId, parkId);
            OrmParam ormParam = getOrmParam(supplierId, currencyId, taxrateId, goodsId, parkId, goodsStatus);
            List<GodsGodsPupricSetaEntity> pupricSetaEntityList = ormService.selectBeanList(GodsGodsPupricSetaEntity.class, ormParam);
            if (pupricSetaEntityList != null && pupricSetaEntityList.size() > 0) {
                setPrice(simplePuqoPriceSetDTO, pupricSetaEntityList.get(0));
            }
        } else {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, orderId),
                    ormParam.getEqualXML(PuqoPuqoPriceSetaProperty.PUQO_GOODS, goodsId),
                    ormParam.getEqualXML(PuqoPuqoPriceSetaProperty.PUQO_PARK, parkId)));
            List<PuqoPuqoPriceSetaEntity> priceSetaEntityList = ormService.selectBeanList(PuqoPuqoPriceSetaEntity.class, ormParam);
            if (priceSetaEntityList != null && priceSetaEntityList.size() > 0) {
                setPrice(simplePuqoPriceSetDTO, priceSetaEntityList.get(0));
            }
        }

        return simplePuqoPriceSetDTO;
    }

    /**
     * 获取物品的状态
     * @param supplierId 供应商ID
     * @param goodsId    物品ID
     * @param parkId     园区ID
     * @return
     * @throws Exception
     */
    private String getGoodsStatus(String supplierId, String goodsId, String parkId) throws Exception {
        String goodsStatus = "";
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, goodsId),
                ormParam.getEqualXML(GodsGodsParkSetaProperty.GODS_SUPPLIER, supplierId),
                ormParam.getEqualXML(GodsGodsParkSetaProperty.GODS_PARK, parkId),
                ormParam.getInXML(GodsGodsParkSetaProperty.GODS_STATUS, new String[]{GoodsStatus.UP, GoodsStatus.AC,GoodsStatus.ST})));
        ormParam.addColumn(GodsGodsParkSetaProperty.GODS_STATUS);
        List<Map<String, Object>> list = ormService.selectMapList(GodsGodsParkSetaEntity.class, ormParam);
        if (list != null && list.size() > 0) {
            goodsStatus = list.get(0).get(GodsGodsParkSetaProperty.GODS_STATUS).toString();
        }

        return goodsStatus;
    }

    /**
     * 获取某物品是否已存在临时和待审的报价单，返回已存在的单号
     * @param supplierId 供应商ID
     * @param currencyId 币别ID
     * @param taxrateId  税率ID
     * @param goodsId    物品ID
     * @param parkId     园区ID
     * @return
     * @throws Exception
     */
    public String getExistOrder(String orderId, String supplierId, String currencyId,
                                String taxrateId, String goodsId, String parkId) throws Exception {
        String orderNumber = "";
        OrmParam ormParam = new OrmParam();
        String[] orderStatusArray = new String[]{OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2,
                OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4};
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(PuquoteorderProperty.PUQO_CODE_SUPP, supplierId),
                ormParam.getEqualXML(PuquoteorderProperty.PUQO_CURR, currencyId),
                ormParam.getEqualXML(PuquoteorderProperty.PUQO_TAX, taxrateId),
                ormParam.getInXML(OrderProperty.ORDE_STATUS, orderStatusArray)));
        ormParam.addColumn(NodeConstant.ID).addColumn(OrderProperty.ORDE_NBR);
        List<PuquoteorderEntity> entityList = ormService.selectBeanList(PuquoteorderEntity.class, ormParam);

        if (entityList != null && entityList.size() > 0) {
            for (PuquoteorderEntity puquoteorderEntity : entityList) {
                ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, puquoteorderEntity.getId()),
                        ormParam.getEqualXML(PuqoPuqoPriceSetaProperty.PUQO_GOODS, goodsId),
                        ormParam.getEqualXML(PuqoPuqoPriceSetaProperty.PUQO_PARK, parkId)));
                long num = ormService.count(PuqoPuqoPriceSetaEntity.class, ormParam);
                if (num > 0) {
                    if (StringUtil.isNullOrEmpty(orderId)) {
                        return puquoteorderEntity.getOrde_nbr();
                    } else {
                        if (!orderId.equals(puquoteorderEntity.getId())) {
                            return puquoteorderEntity.getOrde_nbr();
                        }
                    }
                }
            }
        }

        return orderNumber;
    }

    private boolean isExistOrder(String orderId, String goodsId, String parkId) throws Exception {
        OrmParam ormParam = new OrmParam();
        String[] orderStatusArray = new String[]{OrderConstants.ORDE_STATUS_1, OrderConstants.ORDE_STATUS_2,
                OrderConstants.ORDE_STATUS_3, OrderConstants.ORDE_STATUS_4};
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.ID, orderId),
                ormParam.getInXML(OrderProperty.ORDE_STATUS, orderStatusArray)));
        ormParam.addColumn(NodeConstant.ID).addColumn(OrderProperty.ORDE_NBR);
        long num = ormService.count(PuquoteorderEntity.class, ormParam);
        if (num > 0) {
            ormParam = new OrmParam();
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, orderId),
                    ormParam.getEqualXML(PuqoPuqoPriceSetaProperty.PUQO_GOODS, goodsId),
                    ormParam.getEqualXML(PuqoPuqoPriceSetaProperty.PUQO_PARK, parkId)));
            return ormService.count(PuqoPuqoPriceSetaEntity.class, ormParam) > 0;
        }
        return false;
    }

    private ParkEntity loadParkById(String id) throws Exception {
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, id));
        ormParam.addGroupByColumn(NodeConstant.ID, ParkProperty.RPAK_NAME);
        List<ParkEntity> parkEntityList = ormService.selectBeanList(ParkEntity.class, ormParam);
        if (parkEntityList != null && parkEntityList.size() > 0) {
            return parkEntityList.get(0);
        }
        return null;
    }

    private OrmParam getOrmParam(String supplierId, String currencyId, String taxrateId,
                                 String goodsId, String parkId, String goodsStatus) throws Exception {
        OrmParam ormParam = new OrmParam();
        String exp = OrmParam.and(ormParam.getEqualXML(NodeConstant.PID, goodsId),
                ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_PPARK, parkId),
                ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_SUPP, supplierId),
                ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_PTAX_RATE, taxrateId),
                ormParam.getEqualXML(GodsGodsPupricSetaProperty.GODS_PCURR, currencyId));
        if (GoodsStatus.AC.equals(goodsStatus)) {
            Date date = new Date();
            exp = OrmParam.and(exp, ormParam.getLessThanAndEqualXML(GodsGodsPupricSetaProperty.GODS_PDATE_BEG, date),
                    OrmParam.or(ormParam.getGreaterThanAndEqualXML(GodsGodsPupricSetaProperty.GODS_PDATE_END,date),ormParam.getIsNull(GodsGodsPupricSetaProperty.GODS_PDATE_END))
//                  ormParam.getGreaterThanAndEqualXML(GodsGodsPupricSetaProperty.GODS_PDATE_END, date)
            );
        }
        ormParam.setWhereExp(exp);
        ormParam.setOrderExp(SQLSortEnum.DESC, GodsGodsPupricSetaProperty.GODS_PDATE_BEG);
        ormParam.addGroupByColumn(GodsGodsPupricSetaProperty.GODS_PDATE_BEG, GodsGodsPupricSetaProperty.GODS_PDATE_END,
                GodsGodsPupricSetaProperty.GODS_PISLADDER, GodsGodsPupricSetaProperty.GODS_PPRICE,
                GodsGodsPupricSetaProperty.GODS_PREBATE);
        return ormParam;
    }

    /**
     * 资源类采购价格设置
     * @param simplePuqoPriceSetDTO
     * @param pupricSetaEntity
     * @throws Exception
     */
    private void setPrice(SimplePuqoPriceSetDTO simplePuqoPriceSetDTO, GodsGodsPupricSetaEntity pupricSetaEntity)
            throws Exception {
        if (pupricSetaEntity != null) {
            if (pupricSetaEntity.getGods_pisladder() == 1) {
                //获取阶梯报价明细
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.PID, pupricSetaEntity.getId()));
                ormParam.addGroupByColumn(GodsGodsPladderSetbProperty.GODS_LUPDOWN_RATE, GodsGodsPladderSetbProperty.GODS_PLPRICE,
                        GodsGodsPladderSetbProperty.GODS_POQTY_MAX, GodsGodsPladderSetbProperty.GODS_POQTY_MIN);
                ormParam.setOrderExp(SQLSortEnum.ASC, GodsGodsPladderSetbProperty.GODS_POQTY_MIN);
                List<GodsGodsPladderSetbEntity> entityList = ormService.selectBeanList(GodsGodsPladderSetbEntity.class, ormParam);

                //设置阶梯报价明细
                if (entityList != null && entityList.size() > 0) {
                    List<PuqoLadderPriceSetOldDTO> puqoLadderSetOld = new ArrayList<>();
                    for (GodsGodsPladderSetbEntity entity : entityList) {
                        PuqoLadderPriceSetOldDTO puqoLadderPriceSetOldDTO = new PuqoLadderPriceSetOldDTO();
                        puqoLadderPriceSetOldDTO.setPuqoLpriceOld(entity.getGods_plprice());
                        puqoLadderPriceSetOldDTO.setPuqoOqtyMinOld(entity.getGods_poqty_min());
                        puqoLadderPriceSetOldDTO.setPuqoOqtyMaxOld(entity.getGods_poqty_max());
                        puqoLadderSetOld.add(puqoLadderPriceSetOldDTO);
                    }
                    simplePuqoPriceSetDTO.setPuqoLadderSetOld(puqoLadderSetOld);
                }
            } else {
                simplePuqoPriceSetDTO.setPuqoPriceOld(pupricSetaEntity.getGods_pprice());
            }
            simplePuqoPriceSetDTO.setPuqoIsladderOld(pupricSetaEntity.getGods_pisladder());
            simplePuqoPriceSetDTO.setPuqoDateBegOld(new Date());
            simplePuqoPriceSetDTO.setPuqoRebateOld(pupricSetaEntity.getGods_prebate());
            simplePuqoPriceSetDTO.setPuqoDateEndOld("");
        }
    }

    /**
     * 单据类采购价格设置
     * @param simplePuqoPriceSetDTO
     * @param pupricSetaEntity
     * @throws Exception
     */
    private void setPrice(SimplePuqoPriceSetDTO simplePuqoPriceSetDTO, PuqoPuqoPriceSetaEntity pupricSetaEntity)
            throws Exception {
        if (pupricSetaEntity != null) {
            if (pupricSetaEntity.getPuqo_isladder() == 1) {
                //获取新阶梯报价明细
                OrmParam ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.PID, pupricSetaEntity.getId()));
                ormParam.addGroupByColumn(PuqoPuqoLadderSetbProperty.PUQO_LPRICE, PuqoPuqoLadderSetbProperty.PUQO_LUPDOWN_RATE,
                        PuqoPuqoLadderSetbProperty.PUQO_OQTY_MAX, PuqoPuqoLadderSetbProperty.PUQO_OQTY_MIN);
                ormParam.setOrderExp(SQLSortEnum.ASC, PuqoPuqoLadderSetbProperty.PUQO_OQTY_MIN);
                List<PuqoPuqoLadderSetbEntity> ladderList = ormService.selectBeanList(PuqoPuqoLadderSetbEntity.class, ormParam);
                //获取旧阶梯报价明细
                ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.PID, pupricSetaEntity.getId()));
                ormParam.addGroupByColumn(PuqoPuqoLadderSetOldbProperty.PUQO_LPRICE_OLD, PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MAX_OLD,
                        PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MIN_OLD);
                ormParam.setOrderExp(SQLSortEnum.ASC, PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MIN_OLD);
                List<PuqoPuqoLadderSetOldbEntity> oldLadderList = ormService.selectBeanList(PuqoPuqoLadderSetOldbEntity.class, ormParam);

                //设置新阶梯报价明细
                if (ladderList != null && ladderList.size() > 0) {
                    List<PuqoLadderPriceSetDTO> puqoLadderSet = new ArrayList<>();
                    for (PuqoPuqoLadderSetbEntity entity : ladderList) {
                        PuqoLadderPriceSetDTO puqoLadderPriceSetDTO = new PuqoLadderPriceSetDTO();
                        puqoLadderPriceSetDTO.setPuqoLprice(entity.getPuqo_lprice());
                        puqoLadderPriceSetDTO.setPuqoLupdownRate(entity.getPuqo_lupdown_rate());
                        puqoLadderPriceSetDTO.setPuqoOqtyMin(entity.getPuqo_oqty_min());
                        puqoLadderPriceSetDTO.setPuqoOqtyMax(entity.getPuqo_oqty_max());
                        puqoLadderSet.add(puqoLadderPriceSetDTO);
                    }
                    simplePuqoPriceSetDTO.setPuqoLadderSet(puqoLadderSet);
                }
                //设置旧阶梯报价明细
                if (oldLadderList != null && oldLadderList.size() > 0) {
                    List<PuqoLadderPriceSetOldDTO> puqoLadderSetOld = new ArrayList<>();
                    for (PuqoPuqoLadderSetOldbEntity entity : oldLadderList) {
                        PuqoLadderPriceSetOldDTO puqoLadderPriceSetOldDTO = new PuqoLadderPriceSetOldDTO();
                        puqoLadderPriceSetOldDTO.setPuqoLpriceOld(entity.getPuqo_lprice_old());
                        puqoLadderPriceSetOldDTO.setPuqoOqtyMinOld(entity.getPuqo_oqty_min_old());
                        puqoLadderPriceSetOldDTO.setPuqoOqtyMaxOld(entity.getPuqo_oqty_max_old());
                        puqoLadderSetOld.add(puqoLadderPriceSetOldDTO);
                    }
                    simplePuqoPriceSetDTO.setPuqoLadderSetOld(puqoLadderSetOld);
                }
            } else {
                simplePuqoPriceSetDTO.setPuqoPriceOld(pupricSetaEntity.getPuqo_price_old());
                simplePuqoPriceSetDTO.setPuqoPrice(pupricSetaEntity.getPuqo_price());
            }
            simplePuqoPriceSetDTO.setPuqoIsladderOld(pupricSetaEntity.getPuqo_isladder_old());
            simplePuqoPriceSetDTO.setPuqoDateBegOld(pupricSetaEntity.getPuqo_date_beg_old());
            simplePuqoPriceSetDTO.setPuqoRebateOld(pupricSetaEntity.getPuqo_rebate_old());
            simplePuqoPriceSetDTO.setPuqoDateEndOld(pupricSetaEntity.getPuqo_date_end_old());
            simplePuqoPriceSetDTO.setPuqoIsladder(pupricSetaEntity.getPuqo_isladder());
            simplePuqoPriceSetDTO.setPuqoDateBeg(pupricSetaEntity.getPuqo_date_beg());
            simplePuqoPriceSetDTO.setPuqoRebate(pupricSetaEntity.getPuqo_rebate());
            simplePuqoPriceSetDTO.setPuqoDateEnd(pupricSetaEntity.getPuqo_date_end());
        }
    }

    private void setPriceOfDefaultPark(GoodsInfoAndParkDTO goodsInfoAndParkDTO, SimplePuqoPriceSetDTO simplePuqoPriceSetDTO) {
        if (goodsInfoAndParkDTO != null && simplePuqoPriceSetDTO != null) {
            goodsInfoAndParkDTO.setOrder(simplePuqoPriceSetDTO.getOrder());
            goodsInfoAndParkDTO.setPuqoDateBegOld(simplePuqoPriceSetDTO.getPuqoDateBegOld());
            goodsInfoAndParkDTO.setPuqoDateEndOld(simplePuqoPriceSetDTO.getPuqoDateEndOld());
            goodsInfoAndParkDTO.setPuqoIsladderOld(simplePuqoPriceSetDTO.getPuqoIsladderOld());
            goodsInfoAndParkDTO.setPuqoLadderSetOld(simplePuqoPriceSetDTO.getPuqoLadderSetOld());
            goodsInfoAndParkDTO.setPuqoRebateOld(simplePuqoPriceSetDTO.getPuqoRebateOld());
            goodsInfoAndParkDTO.setPuqoPriceOld(simplePuqoPriceSetDTO.getPuqoPriceOld());
        }
    }

    @Override
    public Result getOrderIdById(String id) {
        Result result = new Result();
        JSONObject dataObj = new JSONObject();
        try {
            OrmParam ormParamGood = new OrmParam();
            ormParamGood.addColumn(NodeConstant.ID + "," + GoodsProperty.GODS_CODE);
            ormParamGood.setWhereExp(ormParamGood.getEqualXML(NodeConstant.ID, id));
            List<GoodsEntity> goodsList = ormService.selectBeanList(GoodsEntity.class, ormParamGood);
            if(!goodsList.isEmpty()) {
                OrmParam ormParamGoodMaint = new OrmParam();
                ormParamGoodMaint.addColumn(NodeConstant.ID + "," + OrderProperty.ORDE_NBR + "," + OrderProperty.ORDE_STATUS);
                ormParamGoodMaint.setWhereExp(ormParamGoodMaint.getEqualXML(GoodsmaintorderProperty.GOMO_CODE, goodsList.get(0).getGods_code()));
                List<GoodsmaintorderEntity> goodsmaintorderList = ormService.selectBeanList(GoodsmaintorderEntity.class, ormParamGoodMaint);
                if(!goodsmaintorderList.isEmpty()) {
                    dataObj.put("orderId", goodsmaintorderList.get(0).getId());
                    dataObj.put("orderNbr", goodsmaintorderList.get(0).getOrde_nbr());
                    dataObj.put("orderStatus", goodsmaintorderList.get(0).getOrde_status());
                }else {
                    dataObj.put("orderId", "");
                    dataObj.put("orderNbr", "");
                    dataObj.put("orderStatus", "");
                }
            }
            result.setData(dataObj);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return result;
    }
}
