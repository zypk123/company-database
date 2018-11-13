package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.*;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.base.CurrentSessionEntity;
import com.huntkey.rx.purchase.common.model.goodsmaintorder.GomoDocDTO;
import com.huntkey.rx.purchase.common.model.goodsmaintorder.GomoFeaDTO;
import com.huntkey.rx.purchase.common.model.goodsmaintorder.GomoParkDTO;
import com.huntkey.rx.purchase.common.model.goodsmaintorder.GoodsMaintOrderDTO;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.service.BizFormService;
import com.huntkey.rx.purchase.provider.service.CommonService;
import com.huntkey.rx.purchase.provider.service.GoodsMaintOrderService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import joptsimple.internal.Strings;
import net.sf.json.JSONString;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author  liangh on 2017/12/27 0027.
 */
@Service
public class GoodsMaintOrderServiceImpl implements GoodsMaintOrderService {

    Logger logger = LoggerFactory.getLogger(GoodsMaintOrderServiceImpl.class);

    @Autowired
    OrmService ormService;

    @Autowired
    BizFormService bizFormService;

    @Autowired
    private CommonService commonService;

    /**
     * 查询物品维护单方法
     * @Param id
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result load(String id) {
        logger.info("加载物品维护单接口开始服务......");
        Result result = new Result();
        try{
            //查询主表信息
            GoodsMaintOrderDTO goodsMaintOrderDTO = new GoodsMaintOrderDTO();
            OrmParam ormParamMain = new OrmParam();
            ormParamMain.addColumn(EdmSysColumn.ID + "," + GoodsmaintorderProperty.GOMO_CODE + ","
                    + GoodsmaintorderProperty.GOMO_CLASS + "," + GoodsmaintorderProperty.GOMO_CLASS_OLD + ","
                    + GoodsmaintorderProperty.GOMO_NAME + "," + GoodsmaintorderProperty.GOMO_NAME_OLD + ","
                    + GoodsmaintorderProperty.GOMO_UNIT + "," + GoodsmaintorderProperty.GOMO_UNIT_OLD + ","
                    + GoodsmaintorderProperty.GOMO_UNIT_GROUP + "," + GoodsmaintorderProperty.GOMO_UNIT_GROUP_OLD + ","
                    + GoodsmaintorderProperty.GOMO_MODEL + "," + GoodsmaintorderProperty.GOMO_MODEL_OLD + ","
                    + GoodsmaintorderProperty.GOMO_BAR_CODE + "," + GoodsmaintorderProperty.GOMO_BAR_CODE_OLD + ","
                    + GoodsmaintorderProperty.GOMO_BRAND + "," + GoodsmaintorderProperty.GOMO_BRAND_OLD + ","
                    + GoodsmaintorderProperty.GOMO_ORIGIN + "," + GoodsmaintorderProperty.GOMO_ORIGIN_OLD + ","
                    + GoodsmaintorderProperty.GOMO_WEIGHT_GROSS + "," + GoodsmaintorderProperty.GOMO_WEIGHT_GROSS_OLD + ","
                    + GoodsmaintorderProperty.GOMO_WEIGHT_NET + "," + GoodsmaintorderProperty.GOMO_WEIGHT_NET_OLD + ","
                    + GoodsmaintorderProperty.GOMO_VOLUME_NET + "," + GoodsmaintorderProperty.GOMO_VOLUME_NET_OLD + ","
                    + GoodsmaintorderProperty.GOMO_VOLUME_BULK + "," + GoodsmaintorderProperty.GOMO_VOLUME_BULK_OLD + ","
                    + GoodsmaintorderProperty.GOMO_CHECK_CYC + "," + GoodsmaintorderProperty.GOMO_CHECK_CYC_OLD + ","
                    + GoodsmaintorderProperty.GOMO_SHELFLIFE + "," + GoodsmaintorderProperty.GOMO_SHELFLIFE_OLD + ","
                    + GoodsmaintorderProperty.GOMO_RECEIPT_TOL + "," + GoodsmaintorderProperty.GOMO_RECEIPT_TOL_OLD + ","
                    + GoodsmaintorderProperty.GOMO_ORDMULT_QTY + "," + GoodsmaintorderProperty.GOMO_ORDMULT_QTY_OLD + ","
                    + GoodsmaintorderProperty.GOMO_PALLET_QTY + "," + GoodsmaintorderProperty.GOMO_PALLET_QTY_OLD + ","
                    + GoodsmaintorderProperty.GOMO_DESC + "," + GoodsmaintorderProperty.GOMO_DESC_OLD + ","
                    + GoodsmaintorderProperty.GOMO_ORDMIN_QTY + "," + GoodsmaintorderProperty.GOMO_ORDMIN_QTY_OLD + ","
                    + OrderProperty.ORDE_STATUS + "," + OrderProperty.ORDE_NBR + "," + OrderProperty.ORDE_DATE + ","
                    + OrderProperty.ORDE_ADDUSER + "," + OrderProperty.ORDE_DUTY + "," + OrderProperty.ORDE_RODE_OBJ + ","
                    + OrderProperty.ORDE_DEPT + "," + OrderProperty.ORDE_PROXYUSER_POS + "," + EdmProperty.EDMD_CLASS + ","
                    + "cretime,creuser");
            ormParamMain.setWhereExp(ormParamMain.getEqualXML(NodeConstant.ID, id));
            List<GoodsmaintorderEntity> gmoeList = ormService.selectBeanList(GoodsmaintorderEntity.class, ormParamMain);
            if(!gmoeList.isEmpty()) {
                goodsMaintOrderDTO = JSONObject.parseObject(JSONObject.toJSONString(gmoeList.get(0)),GoodsMaintOrderDTO.class);
                goodsMaintOrderDTO.setGomoUnitName(gmoeList.get(0).getGomo_unit());
                //设置申请人名称
                OrmParam ormParamEmployee = new OrmParam();
                ormParamEmployee.addColumn(EdmSysColumn.ID + "," + EmployeeProperty.REMP_NAME);
                ormParamEmployee.setWhereExp(ormParamEmployee.getEqualXML(NodeConstant.ID, goodsMaintOrderDTO.getOrdeAdduser()));
                List<EmployeeEntity> employeeList = ormService.selectBeanList(EmployeeEntity.class, ormParamEmployee);
                if(!employeeList.isEmpty()) {
                    goodsMaintOrderDTO.setOrdeAdduserName(employeeList.get(0).getRemp_name());
                }
                //申请部门名称
                OrmParam ormParamDept = new OrmParam();
                ormParamDept.addColumn(EdmSysColumn.ID + "," + DepttreeProperty.MDEP_NAME);
                ormParamDept.setWhereExp(ormParamDept.getEqualXML(NodeConstant.ID, goodsMaintOrderDTO.getOrdeDept()));
                List<DepttreeEntity> depttreeList = ormService.selectBeanList(DepttreeEntity.class, ormParamDept);
                if(!depttreeList.isEmpty()) {
                    goodsMaintOrderDTO.setOrdeDeptName(depttreeList.get(0).getMdep_name());
                }
                //申请岗位名称
                OrmParam ormParamDuty = new OrmParam();
                ormParamDuty.addColumn(EdmSysColumn.ID + "," + JobpositionProperty.RPOS_NAME);
                ormParamDuty.setWhereExp(ormParamDuty.getEqualXML(NodeConstant.ID, goodsMaintOrderDTO.getOrdeDuty()));
                List<JobpositionEntity> jobpositionList = ormService.selectBeanList(JobpositionEntity.class, ormParamDuty);
                if(!jobpositionList.isEmpty()) {
                    goodsMaintOrderDTO.setOrdeDutyName(jobpositionList.get(0).getRpos_name());
                }
                //设置状态位
                //物品分类
                goodsMaintOrderDTO.setGomoClassFlag(getFlag(goodsMaintOrderDTO.getGomoClass(), goodsMaintOrderDTO.getGomoClassOld()));
                //物品名称
                goodsMaintOrderDTO.setGomoNameFlag(getFlag(goodsMaintOrderDTO.getGomoName(), goodsMaintOrderDTO.getGomoNameOld()));
                //单位
                goodsMaintOrderDTO.setGomoUnitFlag(getFlag(goodsMaintOrderDTO.getGomoUnit(), goodsMaintOrderDTO.getGomoUnitOld()));
                //计量单位类
                goodsMaintOrderDTO.setGomoUnitFlag(getFlag(goodsMaintOrderDTO.getGomoUnitGroup(), goodsMaintOrderDTO.getGomoUnitGroupOld()));
                //规格型号
                goodsMaintOrderDTO.setGomoModelFlag(getFlag(goodsMaintOrderDTO.getGomoModel(), goodsMaintOrderDTO.getGomoModelOld()));
                //物品条码
                goodsMaintOrderDTO.setGomoBarCodeFlag(getFlag(goodsMaintOrderDTO.getGomoBarCode(), goodsMaintOrderDTO.getGomoBarCodeOld()));
                //品牌
                goodsMaintOrderDTO.setGomoBrandFlag(getFlag(goodsMaintOrderDTO.getGomoBrand(), goodsMaintOrderDTO.getGomoBrandOld()));
                //原产地
                goodsMaintOrderDTO.setGomoOriginFlag(getFlag(goodsMaintOrderDTO.getGomoOrigin(), goodsMaintOrderDTO.getGomoOriginOld()));
                //单位毛重
                goodsMaintOrderDTO.setGomoWeightGrossFlag(getFlag(goodsMaintOrderDTO.getGomoWeightGross(), goodsMaintOrderDTO.getGomoWeightGrossOld()));
                //单位净重
                goodsMaintOrderDTO.setGomoWeightNetFlag(getFlag(goodsMaintOrderDTO.getGomoWeightNet(), goodsMaintOrderDTO.getGomoWeightNetOld()));
                //单位毛体积
                goodsMaintOrderDTO.setGomoVolumeBulkFlag(getFlag(goodsMaintOrderDTO.getGomoVolumeBulk(), goodsMaintOrderDTO.getGomoVolumeBulkOld()));
                //单位净体积
                goodsMaintOrderDTO.setGomoVolumeNetFlag(getFlag(goodsMaintOrderDTO.getGomoVolumeNet(), goodsMaintOrderDTO.getGomoVolumeNetOld()));
                //盘点间隔
                goodsMaintOrderDTO.setGomoCheckCycFlag(getFlag(goodsMaintOrderDTO.getGomoCheckCyc(), goodsMaintOrderDTO.getGomoCheckCycOld()));
                //保存期限
                goodsMaintOrderDTO.setGomoShelflifeFlag(getFlag(goodsMaintOrderDTO.getGomoShelflife(), goodsMaintOrderDTO.getGomoShelflifeOld()));
                //收货容差
                goodsMaintOrderDTO.setGomoReceiptTolFlag(getFlag(goodsMaintOrderDTO.getGomoReceiptTol(), goodsMaintOrderDTO.getGomoReceiptTolOld()));
                //最小包装数量
                goodsMaintOrderDTO.setGomoOrdmultQtyFlag(getFlag(goodsMaintOrderDTO.getGomoOrdmultQty(), goodsMaintOrderDTO.getGomoOrdmultQtyOld()));
                //栈板堆放数量
                goodsMaintOrderDTO.setGomoPalletQtyFlag(getFlag(goodsMaintOrderDTO.getGomoPalletQty(), goodsMaintOrderDTO.getGomoPalletQtyOld()));
                //物品介绍
                goodsMaintOrderDTO.setGomoDescFlag(getFlag(goodsMaintOrderDTO.getGomoDesc(), goodsMaintOrderDTO.getGomoDescOld()));
                //最小订单数量
                goodsMaintOrderDTO.setGomoOrdminQtyFlag(getFlag(goodsMaintOrderDTO.getGomoOrdminQty(), goodsMaintOrderDTO.getGomoOrdminQtyOld()));
            }else {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg("维护单id："+ id + " 不存在");
                return result;
            }
            //查询物品分类名称
            OrmParam ormParamClass = new OrmParam();
            ormParamClass.addColumn(MonitorProperty.MONI_NODE_NAME);
            ormParamClass.setWhereExp(ormParamClass.getEqualXML(NodeConstant.ID, goodsMaintOrderDTO.getGomoClass()));
            List<GoodsclasstreeEntity> goodsclasstreeList = ormService.selectBeanList(GoodsclasstreeEntity.class, ormParamClass);
            if(!goodsclasstreeList.isEmpty()) {
                goodsMaintOrderDTO.setGomoClassName(goodsclasstreeList.get(0).getMoni_node_name());
            }
            //查询文档资料表
            OrmParam ormParamDoc = new OrmParam();
            ormParamDoc.addColumn(EdmSysColumn.ID + "," + GomoGomoDocSetaProperty.GOMO_DOC_NAME + ","
                    + GomoGomoDocSetaProperty.GOMO_DOC_NAME_OLD + ","
                    + GomoGomoDocSetaProperty.GOMO_DOC_TYPE + "," + GomoGomoDocSetaProperty.GOMO_DOC_TYPE_OLD + ","
                    + GomoGomoDocSetaProperty.GOMO_DOC_URL + "," + GomoGomoDocSetaProperty.GOMO_DOC_URL_OLD);
            ormParamDoc.setWhereExp(ormParamDoc.getEqualXML(NodeConstant.PID, id));
            List<GomoGomoDocSetaEntity> ggdsList = ormService.selectBeanList(GomoGomoDocSetaEntity.class, ormParamDoc);
            List<GomoDocDTO> gomoDocDTOList = new ArrayList<>();
            if(!ggdsList.isEmpty()) {
                for(int i=0; i<ggdsList.size(); i++) {
                    //Entity转DTO
                    GomoDocDTO gomoDocDTO = JSONObject.parseObject(JSONObject.toJSONString(ggdsList.get(i)),GomoDocDTO.class);
                    //设置状态位，注意这里可能存在情况：添加、修改、删除
                    //文档名称
                    gomoDocDTO.setGomoDocNameFlag(getFlag(gomoDocDTO.getGomoDocName(), gomoDocDTO.getGomoDocNameOld()));
                    //文档类型
                    gomoDocDTO.setGomoDocTypeFlag(getFlag(gomoDocDTO.getGomoDocType(), gomoDocDTO.getGomoDocTypeOld()));
                    //文档地址
                    gomoDocDTO.setGomoDocUrlFlag(getFlag(gomoDocDTO.getGomoDocUrl(), gomoDocDTO.getGomoDocUrlOld()));
                    if(Strings.isNullOrEmpty(gomoDocDTO.getGomoDocName()) && Strings.isNullOrEmpty(gomoDocDTO.getGomoDocType()) && Strings.isNullOrEmpty(gomoDocDTO.getGomoDocUrl())) {
                        gomoDocDTO.setDocFlag(ContextStatusConstants.DELETE);
                    }else if(Strings.isNullOrEmpty(gomoDocDTO.getGomoDocNameOld()) && Strings.isNullOrEmpty(gomoDocDTO.getGomoDocTypeOld()) && Strings.isNullOrEmpty(gomoDocDTO.getGomoDocUrlOld())) {
                        gomoDocDTO.setDocFlag(ContextStatusConstants.ADD);
                    }else if((gomoDocDTO.getGomoDocNameFlag()==0) && (gomoDocDTO.getGomoDocTypeFlag()==0) && (gomoDocDTO.getGomoDocUrlFlag()==0)) {
                        gomoDocDTO.setDocFlag(ContextStatusConstants.UNMODIFY);
                    }else {
                        gomoDocDTO.setDocFlag(ContextStatusConstants.MODIFY);
                    }
                    //加入新list
                    gomoDocDTOList.add(gomoDocDTO);
                }
            }
            goodsMaintOrderDTO.setDocList(gomoDocDTOList);
            OrmParam ormParamPark = new OrmParam();
            ormParamPark.addColumn(EdmSysColumn.ID + ","
                    + GomoGomoParkSetaProperty.GOMO_STATUS + "," + GomoGomoParkSetaProperty.GOMO_STATUS_OLD + ","
                    + GomoGomoParkSetaProperty.GOMO_PM_CODE + "," + GomoGomoParkSetaProperty.GOMO_PM_CODE_OLD + ","
                    + GomoGomoParkSetaProperty.GOMO_SUPPLIER + "," + GomoGomoParkSetaProperty.GOMO_SUPPLIER_OLD + ","
                    + GomoGomoParkSetaProperty.GOMO_SUPPLIER_PART + "," + GomoGomoParkSetaProperty.GOMO_SUPPLIER_PART_OLD + ","
                    + GomoGomoParkSetaProperty.GOMO_STOCK_MAX + "," + GomoGomoParkSetaProperty.GOMO_STOCK_MAX_OLD + ","
                    + GomoGomoParkSetaProperty.GOMO_STOCK_MIN + "," + GomoGomoParkSetaProperty.GOMO_STOCK_MIN_OLD + ","
                    + GomoGomoParkSetaProperty.GOMO_STOCK_SFTY + "," + GomoGomoParkSetaProperty.GOMO_STOCK_SFTY_OLD + ","
                    + GomoGomoParkSetaProperty.GOMO_COST_TYPE + "," + GomoGomoParkSetaProperty.GOMO_COST_TYPE_OLD + ","
                    + GomoGomoParkSetaProperty.GOMO_PARK + "," + GomoGomoParkSetaProperty.GOMO_ISBATCHSPLIT + ","
                    + GomoGomoParkSetaProperty.GOMO_ISININSPECT + "," + GomoGomoParkSetaProperty.GOMO_ISININSPECT_OLD + ","
                    + GomoGomoParkSetaProperty.GOMO_ISOUTINSPECT);
            ormParamPark.setWhereExp(ormParamPark.getEqualXML(NodeConstant.PID, id));
            //查询园区属性表
            List<GomoGomoParkSetaEntity> ggpsList = ormService.selectBeanList(GomoGomoParkSetaEntity.class, ormParamPark);
            List<GomoParkDTO> gomoParkDTOList = new ArrayList<>();
            if(!ggpsList.isEmpty()) {
                for(int i=0; i<ggpsList.size(); i++) {
                    //Entity转DTO
                    GomoParkDTO gomoParkDTO = JSONObject.parseObject(JSONObject.toJSONString(ggpsList.get(i)),GomoParkDTO.class);
                    //设置供应商名称
                    if(gomoParkDTO.getGomoSupplier()!=null && !gomoParkDTO.getGomoSupplier().isEmpty()) {
                        OrmParam ormParamSup = new OrmParam();
                        ormParamSup.addColumn(EdmSysColumn.ID + "," + RelationProperty.RELA_NAME);
                        ormParamSup.setWhereExp(ormParamSup.getEqualXML(NodeConstant.ID, gomoParkDTO.getGomoSupplier()));
                        List<RelationEntity> relationList = ormService.selectBeanList(RelationEntity.class, ormParamSup);
                        if(!relationList.isEmpty()) {
                            gomoParkDTO.setGomoSupplierName(relationList.get(0).getRela_name());
                        }
                    }
                    //设置供应商名称_old
                    if(gomoParkDTO.getGomoSupplierOld()!=null && !gomoParkDTO.getGomoSupplierOld().isEmpty()) {
                        OrmParam ormParamSupOld = new OrmParam();
                        ormParamSupOld.addColumn(EdmSysColumn.ID + "," + RelationProperty.RELA_NAME);
                        ormParamSupOld.setWhereExp(ormParamSupOld.getEqualXML(NodeConstant.ID, gomoParkDTO.getGomoSupplierOld()));
                        List<RelationEntity> relationList = ormService.selectBeanList(RelationEntity.class, ormParamSupOld);
                        if(!relationList.isEmpty()) {
                            gomoParkDTO.setGomoSupplierNameOld(relationList.get(0).getRela_name());
                        }
                    }
                    //设置园区名称
                    if(gomoParkDTO.getGomoPark()!=null && !gomoParkDTO.getGomoPark().isEmpty()) {
                        OrmParam ormParamPk = new OrmParam();
                        ormParamPk.addColumn(EdmSysColumn.ID + "," + ParkProperty.RPAK_NAME + "," + ParkProperty.RPAK_ISDEFAULT);
                        ormParamPk.setWhereExp(ormParamPk.getEqualXML(NodeConstant.ID, gomoParkDTO.getGomoPark()));
                        List<ParkEntity> parkList = ormService.selectBeanList(ParkEntity.class, ormParamPk);
                        if(!parkList.isEmpty()) {
                            gomoParkDTO.setRpakName(parkList.get(0).getRpak_name());
                            gomoParkDTO.setRpak_isdefault(parkList.get(0).getRpak_isdefault());
                        }
                    }
                    //设置状态位
                    //物品状态
                    gomoParkDTO.setGomoStatusFlag(getFlag(gomoParkDTO.getGomoStatus(), gomoParkDTO.getGomoStatusOld()));
                    //PM码
                    gomoParkDTO.setGomoPmCodeFlag(getFlag(gomoParkDTO.getGomoPmCode(), gomoParkDTO.getGomoPmCodeOld()));
                    //供应商
                    gomoParkDTO.setGomoSupplierFlag(getFlag(gomoParkDTO.getGomoSupplier(), gomoParkDTO.getGomoSupplierOld()));
                    //供应商物品编号
                    gomoParkDTO.setGomoSupplierPartFlag(getFlag(gomoParkDTO.getGomoSupplierPart(), gomoParkDTO.getGomoSupplierPartOld()));
                    //最大库存
                    gomoParkDTO.setGomoStockMaxFlag(getFlag(gomoParkDTO.getGomoStockMax(), gomoParkDTO.getGomoStockMaxOld()));
                    //最小库存
                    gomoParkDTO.setGomoStockMinFlag(getFlag(gomoParkDTO.getGomoStockMin(), gomoParkDTO.getGomoStockMinOld()));
                    //安全库存
                    gomoParkDTO.setGomoStockSftyFlag(getFlag(gomoParkDTO.getGomoStockSfty(), gomoParkDTO.getGomoStockSftyOld()));
                    //成本计价方式
                    gomoParkDTO.setGomoCostTypeFlag(getFlag(gomoParkDTO.getGomoCostType(), gomoParkDTO.getGomoCostTypeOld()));
                    //加入新list
                    gomoParkDTOList.add(gomoParkDTO);
                }
            }
            goodsMaintOrderDTO.setParkList(gomoParkDTOList);
            //查询物品特征表
            OrmParam ormParamFea = new OrmParam();
            ormParamFea.addColumn(EdmSysColumn.ID + ","
                    + GomoGomoFeatureSetaProperty.GOMO_FEATURE + "," + GomoGomoFeatureSetaProperty.GOMO_FEATURE_OLD + ","
                    + GomoGomoFeatureSetaProperty.GOMO_FEATURE_VAL + "," + GomoGomoFeatureSetaProperty.GOMO_FEATURE_VAL_OLD);
            ormParamFea.setWhereExp(ormParamFea.getEqualXML(NodeConstant.PID, id));
            List<GomoGomoFeatureSetaEntity> ggfsList = ormService.selectBeanList(GomoGomoFeatureSetaEntity.class, ormParamFea);
            List<GomoFeaDTO> gomoFeaDTOList = new ArrayList<>();
            if(!ggfsList.isEmpty()) {
                for(int i=0; i<ggfsList.size(); i++) {
                    //Entity转DTO
                    GomoFeaDTO gomoFeaDTO = JSONObject.parseObject(JSONObject.toJSONString(ggfsList.get(i)),GomoFeaDTO.class);
                    //设置状态位
                    //特征对象
                    gomoFeaDTO.setGomoFeatureFlag(getFlag(gomoFeaDTO.getGomoFeature(), gomoFeaDTO.getGomoFeatureOld()));
                    //特征值
                    gomoFeaDTO.setGomoFeatureValFlag(getFlag(gomoFeaDTO.getGomoFeatureVal(), gomoFeaDTO.getGomoFeatureValOld()));
                    //根据物品特征id查询物品特征类型
                    OrmParam ormParamFeature = new OrmParam();
                    ormParamFeature.addColumn(EdmSysColumn.ID + "," + GoodsfeatureProperty.GOFT_TYPE + ","
                            + GoodsfeatureProperty.GOFT_NAME + "," + GoodsfeatureProperty.GOFT_ISMULT + "," + GoodsfeatureProperty.GOFT_SOURCE);
                    ormParamFeature.setWhereExp(ormParamFeature.getEqualXML(NodeConstant.ID, gomoFeaDTO.getGomoFeature()));
                    List<GoodsfeatureEntity> goodsfeatureList = ormService.selectBeanList(GoodsfeatureEntity.class, ormParamFeature);
                    if(!goodsfeatureList.isEmpty()) {
                        gomoFeaDTO.setGomoFeatureType(goodsfeatureList.get(0).getGoft_type());
                        gomoFeaDTO.setGomoWordListName(goodsfeatureList.get(0).getGoft_name());
                        gomoFeaDTO.setGoftIsmult(goodsfeatureList.get(0).getGoft_ismult());
                        //查询枚举type
                        if(!StringUtil.isNullOrEmpty(goodsfeatureList.get(0).getGoft_source())) {
                            OrmParam ormParamWord = new OrmParam();
                            ormParamWord.addColumn(EdmSysColumn.ID + "," + InformationProperty.INFO_CODE);
                            ormParamWord.setWhereExp(ormParamWord.getEqualXML(NodeConstant.ID, goodsfeatureList.get(0).getGoft_source()));
                            List<WordlistEntity> wordlist = ormService.selectBeanList(WordlistEntity.class, ormParamWord);
                            if(!wordlist.isEmpty()) {
                                gomoFeaDTO.setGomoSourceCode(wordlist.get(0).getInfo_code());
                            }
                        }
                    }
                    //加入新list
                    gomoFeaDTOList.add(gomoFeaDTO);
                }
            }
            goodsMaintOrderDTO.setFeaList(gomoFeaDTOList);
            //设置修改数
            goodsMaintOrderDTO.setBaseInfoTotalNum(getBaseInfoNum(goodsMaintOrderDTO));
            goodsMaintOrderDTO.setBusiPropTotalNum(getBusiPropNum(goodsMaintOrderDTO));
            goodsMaintOrderDTO.setDocumentTotalNum(getDocumentNum(goodsMaintOrderDTO));
            result.setData(goodsMaintOrderDTO);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_LOAD + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_LOAD + e.getMessage());
        }
        return result;
    }

    /**
     * 统计基础信息部分修改数
     *
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    private int getBaseInfoNum(GoodsMaintOrderDTO goodsMaintOrderDTO) {
        int num = 0;
        //物品表字段
        num = num + goodsMaintOrderDTO.getGomoBarCodeFlag() + goodsMaintOrderDTO.getGomoBrandFlag();
        num = num + goodsMaintOrderDTO.getGomoOriginFlag() + goodsMaintOrderDTO.getGomoDescFlag();
        //园区属性表字段
        List<GomoParkDTO> parkList = goodsMaintOrderDTO.getParkList();
        if(!parkList.isEmpty()) {
            for(GomoParkDTO gpd:parkList) {
                num = num + gpd.getGomoStatusFlag() + gpd.getGomoPmCodeFlag();
                num = num + gpd.getGomoSupplierFlag() + gpd.getGomoSupplierPartFlag();
            }
        }
        //物品特征表字段
        List<GomoFeaDTO> feaList = goodsMaintOrderDTO.getFeaList();
        if(!feaList.isEmpty()) {
            for(GomoFeaDTO gfd:feaList) {
                num = num +gfd.getGomoFeatureValFlag();
            }
        }
        return num;
    }

    /**
     * 统计业务属性部分修改数
     *
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    private int getBusiPropNum(GoodsMaintOrderDTO goodsMaintOrderDTO) {
        int num = 0;
        //物品表字段
        num = num + goodsMaintOrderDTO.getGomoCheckCycFlag() + goodsMaintOrderDTO.getGomoShelflifeFlag();
        num = num + goodsMaintOrderDTO.getGomoReceiptTolFlag() + goodsMaintOrderDTO.getGomoOrdmultQtyFlag();
        num = num + goodsMaintOrderDTO.getGomoOrdminQtyFlag() + goodsMaintOrderDTO.getGomoPalletQtyFlag();
        num = num + goodsMaintOrderDTO.getGomoWeightGrossFlag() + goodsMaintOrderDTO.getGomoWeightNetFlag();
        num = num + goodsMaintOrderDTO.getGomoVolumeBulkFlag() + goodsMaintOrderDTO.getGomoVolumeNetFlag();
        //园区属性表字段
        List<GomoParkDTO> parkList = goodsMaintOrderDTO.getParkList();
        if(!parkList.isEmpty()) {
            for(GomoParkDTO gpd:parkList) {
                num = num + gpd.getGomoCostTypeFlag() + gpd.getGomoStockSftyFlag();
                num = num + gpd.getGomoStockMaxFlag() + gpd.getGomoStockMinFlag();
            }
        }
        return num;
    }

    /**
     * 统计文档资料部分修改数
     *
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    private int getDocumentNum(GoodsMaintOrderDTO goodsMaintOrderDTO) {
        int num = 0;
        List<GomoDocDTO> docList = goodsMaintOrderDTO.getDocList();
        if(!docList.isEmpty()) {
            for(GomoDocDTO gdd:docList) {
                if(gdd.getDocFlag() != 0) {
                    num++;
                }
            }
        }
        return num;
    }

    /**
     * 判断s1和s2是否相同，返回
     * 未改：0
     * 新增：1
     * 修改：2
     * 删除：3
     *
     */
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public int getFlag(Object obj1, Object obj2) {
        if(obj1 == null && obj2 == null) {
            return ContextStatusConstants.UNMODIFY;
        }
        if((obj1 == null && obj2 != null) || (obj1 != null && obj2 == null)) {
            return ContextStatusConstants.MODIFY;
        }
        if("java.lang.String".equals(obj1.getClass().getName()) || "java.lang.BigDecimal".equals(obj1.getClass().getName())) {
            if(obj1.equals(obj2)) {
                return ContextStatusConstants.UNMODIFY;
            }else {
                return ContextStatusConstants.MODIFY;
            }
        }
        if("java.lang.Integer".equals(obj1.getClass().getName())) {
            if(obj1 == obj2) {
                return ContextStatusConstants.UNMODIFY;
            }else {
                return ContextStatusConstants.MODIFY;
            }
        }
        return 0;
    }

    /**
     * 根据pid查子表信息
     *
     */
    /*private <T extends BaseEntity> List<T> selectByPid(String pid, Class<T> t) {
        List<T> list = new ArrayList<>();
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, pid)));
            list = ormService.selectBeanList(t, ormParam);
        } catch (Exception e) {
            logger.error("根据pid查询出现异常：", e);
            e.printStackTrace();
            throw new RuntimeException("根据pid查询出现异常：", e);
        }
        return list;
    }*/

    /**
     * 新增、更新物品维护单方法
     * 注意更新物品维护单时，存old数据
     * @Param
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result saveGoodsMaintOrder(GoodsMaintOrderDTO goodsMaintOrderDTO) {
        logger.info("保存物品维护单接口开始服务......");
        Result result = new Result();
        String goodsMaintOrderId = goodsMaintOrderDTO.getId();
        //单号
        String ordeNbr = "";
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        try{
            //驼峰格式转下划线
            GoodsmaintorderEntity goodsmaintorderEntity = JSONObject.parseObject(JSONObject.toJSONString(goodsMaintOrderDTO), GoodsmaintorderEntity.class);
            if(StringUtil.isNullOrEmpty(goodsmaintorderEntity.getId())) {
                //设置制单时间
                goodsmaintorderEntity.setOrde_date(new Date());
            }
            // 提交校验
            Result validResult = validSaveOrder(goodsmaintorderEntity);
            if (validResult.getRetCode().equals(Result.RECODE_VALIDATE_ERROR)) {
                validResult.setErrMsg(validResult.getErrMsg() + ",当前临时单保存失败！");
                throw new ApplicationException(Result.RECODE_ERROR, " " + validResult.getErrMsg());
            }
            goodsmaintorderEntity.setGomo_unit(goodsMaintOrderDTO.getGomoUnitName());
            //企业对象
            goodsmaintorderEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
            //对象类
            goodsmaintorderEntity.setEdmd_class("goodsmaintorder");
            //制单时间和制单人
            goodsmaintorderEntity.setOrde_date(new Date());
            goodsmaintorderEntity.setOrde_adduser(sessionEntity.getEmployeeId());
            //判断是新增还是更新
            if(StringUtil.isNullOrEmpty(goodsMaintOrderId)) {
                if(goodsMaintOrderDTO.isSubmit()) {
                    //没有保存维护单，直接点击提交按钮的情况，调用编号生产器，按照物品正式单号规则，生成单号
                    ordeNbr = commonService.getCode(NumberConstants.PREFIX_PU_GOODS_ORDER);
                    goodsmaintorderEntity.setOrde_nbr(ordeNbr);
                    goodsmaintorderEntity.setOrde_status(OrderConstants.ORDE_STATUS_2);
                }else {
                    //第一次保存维护单信息时，调用编号生产器，生成临时单号
                    ordeNbr = commonService.getCode(NumberConstants.PREFIX_PU_TEMP_ORDER);
                    goodsmaintorderEntity.setOrde_nbr(ordeNbr);
                    goodsmaintorderEntity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                }
                ///新增主表，注意主表返回id
                //校验物品编码是否在维护单表和物品表有重复
                Result tempResult = check(goodsMaintOrderDTO.getGomoCode());
                if(tempResult.getRetCode() != 1) {
                    return tempResult;
                }
                goodsmaintorderEntity.setCretime(new Date());
                goodsmaintorderEntity.setCreuser(sessionEntity.getEmployeeId());
                goodsmaintorderEntity.setModtime(goodsmaintorderEntity.getCretime());
                goodsmaintorderEntity.setOrde_date(goodsmaintorderEntity.getCretime());
                goodsmaintorderEntity.setModuser(sessionEntity.getEmployeeId());
                goodsMaintOrderId = ormService.insert(goodsmaintorderEntity).toString();
                goodsmaintorderEntity.setId(goodsMaintOrderId);
            }else {
                //判断是否是先保存，后提交维护单的情况，如果是，则需要重新生成单号
                if(goodsMaintOrderDTO.isSubmit()) {
                    //调用编号生产器，按照物品正式单号规则，生成单号
                    ordeNbr = commonService.getCode(NumberConstants.PREFIX_PU_GOODS_ORDER);
                    goodsmaintorderEntity.setOrde_nbr(ordeNbr);
                }
                //更新主表，处理新旧值
                goodsmaintorderEntity.setModtime(new Date());
                goodsmaintorderEntity.setOrde_date(goodsmaintorderEntity.getModtime());
                goodsmaintorderEntity.setModuser(sessionEntity.getEmployeeId());
                //处理旧值,查询原数据，新字段赋值到旧字段上
                GoodsmaintorderEntity newGoodsmaintorderEntity = getGoodsmaintorderOldData(goodsmaintorderEntity);
                //更新新值
                ormService.update(newGoodsmaintorderEntity);
            }
            //删除文档资料表数据
            deleteSets(goodsMaintOrderDTO.getId(), GomoGomoDocSetaEntity.class);
            //保存文档资料
            List<GomoDocDTO> docList = goodsMaintOrderDTO.getDocList();
            if(docList != null && !docList.isEmpty()) {
                for(int i=0; i<docList.size(); i++) {
                    //驼峰格式转下划线
                    JSONObject paramDoc = JSONObject.parseObject(JSON.toJSONString(docList.get(i)));
                    GomoGomoDocSetaEntity gomoGomoDocSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(paramDoc), GomoGomoDocSetaEntity.class);
                    gomoGomoDocSetaEntity.setPid(goodsmaintorderEntity.getId());
                    //处理旧值,查询原数据，新字段赋值到旧字段上
                    GomoGomoDocSetaEntity newGomoGomoDocSetaEntity = getGomoGomoDocSetOldData(gomoGomoDocSetaEntity);
                    newGomoGomoDocSetaEntity.setCretime(new Date());
                    newGomoGomoDocSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                    newGomoGomoDocSetaEntity.setModtime(newGomoGomoDocSetaEntity.getCretime());
                    newGomoGomoDocSetaEntity.setModuser(sessionEntity.getEmployeeId());
                    newGomoGomoDocSetaEntity.setClassName(GoodsMaintOrderConstants.GOMODOCSET);
                    //插入新值
                    ormService.insertSelective(newGomoGomoDocSetaEntity);
                }
            }
            //保存物品特性
            List<GomoFeaDTO> goodsFeaList = goodsMaintOrderDTO.getFeaList();
            if(goodsFeaList != null && !goodsFeaList.isEmpty()) {
                for(int i=0; i<goodsFeaList.size(); i++) {
                    //驼峰格式转下划线
                    JSONObject paramGoodsFea = JSONObject.parseObject(JSON.toJSONString(goodsFeaList.get(i)));
                    GomoGomoFeatureSetaEntity gomoGomoFeatureSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(paramGoodsFea), GomoGomoFeatureSetaEntity.class);
                    gomoGomoFeatureSetaEntity.setPid(goodsmaintorderEntity.getId());
                    //处理旧值,查询原数据，新字段赋值到旧字段上
                    GomoGomoFeatureSetaEntity newGomoGomoFeatureSetaEntity = getGomoGomoFeatureSetOldData(gomoGomoFeatureSetaEntity);
                    newGomoGomoFeatureSetaEntity.setCretime(new Date());
                    newGomoGomoFeatureSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                    newGomoGomoFeatureSetaEntity.setModtime(newGomoGomoFeatureSetaEntity.getCretime());
                    newGomoGomoFeatureSetaEntity.setModuser(sessionEntity.getEmployeeId());
                    newGomoGomoFeatureSetaEntity.setClassName(GoodsMaintOrderConstants.GOMOFEATURESET);
                    //插入新值
                    ormService.insertSelective(newGomoGomoFeatureSetaEntity);
                }
            }
            //删除物品特性表数据
            deleteSets(goodsMaintOrderDTO.getId(), GomoGomoFeatureSetaEntity.class);
            //保存园区属性
            List<GomoParkDTO> goodsParkList = goodsMaintOrderDTO.getParkList();
            if(goodsParkList != null && !goodsParkList.isEmpty()) {
                for(int i=0; i<goodsParkList.size(); i++) {
                    //驼峰格式转下划线
                    JSONObject paramGoodsPark = JSONObject.parseObject(JSON.toJSONString(goodsParkList.get(i)));
                    GomoGomoParkSetaEntity gomoGomoParkSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(paramGoodsPark), GomoGomoParkSetaEntity.class);
                    //处理旧值,查询原数据，新字段赋值到旧字段上
                    GomoGomoParkSetaEntity newGomoGomoParkSetaEntity = new GomoGomoParkSetaEntity();
                    if(!StringUtil.isNullOrEmpty(gomoGomoParkSetaEntity.getId())){
                        newGomoGomoParkSetaEntity = getGomoGomoParkSetOldData(gomoGomoParkSetaEntity);
                    }else {
                        newGomoGomoParkSetaEntity = gomoGomoParkSetaEntity;
                    }
                    newGomoGomoParkSetaEntity.setCretime(new Date());
                    newGomoGomoParkSetaEntity.setPid(goodsmaintorderEntity.getId());
                    newGomoGomoParkSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                    newGomoGomoParkSetaEntity.setModtime(newGomoGomoParkSetaEntity.getCretime());
                    newGomoGomoParkSetaEntity.setModuser(sessionEntity.getEmployeeId());
                    newGomoGomoParkSetaEntity.setClassName(GoodsMaintOrderConstants.GOMOPARKSET);
                    //删除园区属性表数据
                    deleteSets(goodsMaintOrderDTO.getId(), GomoGomoParkSetaEntity.class);
                    //插入新值
                    ormService.insertSelective(newGomoGomoParkSetaEntity);
                }
            }else {
                //删除园区属性表数据
                deleteSets(goodsMaintOrderDTO.getId(), GomoGomoParkSetaEntity.class);
            }
            //返回单据id：orderId和单号：ordeNbr
            JSONObject dataObj = new JSONObject();
            dataObj.put("orderId", goodsmaintorderEntity.getId());
            dataObj.put("ordeNbr", ordeNbr);
            result.setData(dataObj);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_SAVE + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_SAVE + e.getMessage());
        }
        return result;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    private Result validSaveOrder(GoodsmaintorderEntity goodsmaintorderEntity) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_VALIDATE_ERROR);
        try {
            if(StringUtil.isNullOrEmpty(goodsmaintorderEntity.getOrde_adduser())) {
                result.setErrMsg("制单人不能为空 ");
                return result;
            }
            if(StringUtil.isNullOrEmpty(goodsmaintorderEntity.getOrde_dept())) {
                result.setErrMsg("制单部门不能为空 ");
                return result;
            }
            if(StringUtil.isNullOrEmpty(goodsmaintorderEntity.getOrde_duty())) {
                result.setErrMsg("制单岗位不能为空 ");
                return result;
            }
            if(StringUtil.isNullOrEmpty(goodsmaintorderEntity.getOrde_date())) {
                result.setErrMsg("制单时间不能为空 ");
                return result;
            }
        }catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    private GoodsmaintorderEntity getGoodsmaintorderOldData(GoodsmaintorderEntity goodsmaintorderEntity) {
        //根据id查询整条旧数据，往新的实体中赋值，注意赋值创建人和时间，另外加上修改人和时间
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID + ","
                            + GoodsmaintorderProperty.GOMO_NAME + "," + GoodsmaintorderProperty.GOMO_CLASS + ","
                            + GoodsmaintorderProperty.GOMO_UNIT + "," + GoodsmaintorderProperty.GOMO_UNIT_GROUP + ","
                            + GoodsmaintorderProperty.GOMO_MODEL + "," + GoodsmaintorderProperty.GOMO_BAR_CODE + ","
                            + GoodsmaintorderProperty.GOMO_BRAND + "," + GoodsmaintorderProperty.GOMO_ORIGIN + ","
                            + GoodsmaintorderProperty.GOMO_WEIGHT_NET + "," + GoodsmaintorderProperty.GOMO_WEIGHT_GROSS + ","
                            + GoodsmaintorderProperty.GOMO_VOLUME_NET + "," + GoodsmaintorderProperty.GOMO_VOLUME_BULK + ","
                            + GoodsmaintorderProperty.GOMO_CHECK_CYC + "," + GoodsmaintorderProperty.GOMO_SHELFLIFE + ","
                            + GoodsmaintorderProperty.GOMO_RECEIPT_TOL + "," + GoodsmaintorderProperty.GOMO_ORDMULT_QTY + ","
                            + GoodsmaintorderProperty.GOMO_PALLET_QTY + "," + GoodsmaintorderProperty.GOMO_DESC + ","
                            + "cretime" + "," + "creuser");
        ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, goodsmaintorderEntity.getId()));
        try{
            List<GoodsmaintorderEntity> gmoeList = ormService.selectBeanList(GoodsmaintorderEntity.class, ormParam);
            if(!gmoeList.isEmpty()) {
                GoodsmaintorderEntity gmoe = gmoeList.get(0);
                //处理字段
                goodsmaintorderEntity.setGomo_name_old(gmoe.getGomo_name());
                goodsmaintorderEntity.setGomo_class_old(gmoe.getGomo_class());
                goodsmaintorderEntity.setGomo_unit_old(gmoe.getGomo_unit());
                goodsmaintorderEntity.setGomo_unit_group_old(gmoe.getGomo_unit_group());
                goodsmaintorderEntity.setGomo_model_old(gmoe.getGomo_model());
                goodsmaintorderEntity.setGomo_bar_code_old(gmoe.getGomo_bar_code());
                goodsmaintorderEntity.setGomo_brand_old(gmoe.getGomo_brand());
                goodsmaintorderEntity.setGomo_origin_old(gmoe.getGomo_origin());
                goodsmaintorderEntity.setGomo_weight_gross_old(gmoe.getGomo_weight_gross());
                goodsmaintorderEntity.setGomo_weight_net_old(gmoe.getGomo_weight_net());
                goodsmaintorderEntity.setGomo_volume_bulk_old(gmoe.getGomo_volume_bulk());
                goodsmaintorderEntity.setGomo_volume_net_old(gmoe.getGomo_volume_net());
                goodsmaintorderEntity.setGomo_check_cyc_old(gmoe.getGomo_check_cyc());
                goodsmaintorderEntity.setGomo_shelflife_old(gmoe.getGomo_shelflife());
                goodsmaintorderEntity.setGomo_receipt_tol_old(gmoe.getGomo_receipt_tol());
                goodsmaintorderEntity.setGomo_ordmult_qty_old(gmoe.getGomo_ordmult_qty());
                goodsmaintorderEntity.setGomo_pallet_qty_old(gmoe.getGomo_pallet_qty());
                goodsmaintorderEntity.setGomo_desc_old(gmoe.getGomo_desc());
                //人和时间
                goodsmaintorderEntity.setCretime(gmoe.getCretime());
                goodsmaintorderEntity.setCreuser(gmoe.getCreuser());
                goodsmaintorderEntity.setModtime(new Date());
                goodsmaintorderEntity.setModuser(sessionEntity.getEmployeeId());
            }else {
                goodsmaintorderEntity.setCreuser(sessionEntity.getEmployeeId());
                goodsmaintorderEntity.setCretime(new Date());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return goodsmaintorderEntity;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    private GomoGomoDocSetaEntity getGomoGomoDocSetOldData(GomoGomoDocSetaEntity gomoGomoDocSetaEntity) {
        //根据id查询整条旧数据，往新的实体中赋值，注意赋值创建人和时间，另外加上修改人和时间
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID + "," + GomoGomoDocSetaProperty.GOMO_DOC_URL + ","
                            + GomoGomoDocSetaProperty.GOMO_DOC_NAME + "," + GomoGomoDocSetaProperty.GOMO_DOC_TYPE + ","
                            + "cretime" + "," + "creuser");
        ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, gomoGomoDocSetaEntity.getId()));
        try {
            List<GomoGomoDocSetaEntity> ggdsList = ormService.selectBeanList(GomoGomoDocSetaEntity.class, ormParam);
            if(!ggdsList.isEmpty()) {
                GomoGomoDocSetaEntity ggds = ggdsList.get(0);
                //处理字段
                gomoGomoDocSetaEntity.setGomo_doc_type_old(ggds.getGomo_doc_type());
                gomoGomoDocSetaEntity.setGomo_doc_name_old(ggds.getGomo_doc_name());
                gomoGomoDocSetaEntity.setGomo_doc_url_old(ggds.getGomo_doc_url());
                //人和时间
                gomoGomoDocSetaEntity.setCretime(ggds.getCretime());
                gomoGomoDocSetaEntity.setCreuser(ggds.getCreuser());
                gomoGomoDocSetaEntity.setModtime(new Date());
                gomoGomoDocSetaEntity.setModuser(sessionEntity.getEmployeeId());
            }else {
                gomoGomoDocSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                gomoGomoDocSetaEntity.setCretime(new Date());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return gomoGomoDocSetaEntity;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    private GomoGomoFeatureSetaEntity getGomoGomoFeatureSetOldData(GomoGomoFeatureSetaEntity gomoGomoFeatureSetaEntity) {
        try {
            List<GomoGomoFeatureSetaEntity> ggfsList = new ArrayList<>();
            //根据id查询整条旧数据，往新的实体中赋值，注意赋值创建人和时间，另外加上修改人和时间
            CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
            if(!StringUtil.isNullOrEmpty(gomoGomoFeatureSetaEntity.getId())) {
                OrmParam ormParam = new OrmParam();
                ormParam.addColumn(EdmSysColumn.ID + "," + GomoGomoFeatureSetaProperty.GOMO_FEATURE + ","
                        + GomoGomoFeatureSetaProperty.GOMO_FEATURE_VAL + "," + "cretime," + "creuser");
                ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, gomoGomoFeatureSetaEntity.getId()));
                ggfsList = ormService.selectBeanList(GomoGomoFeatureSetaEntity.class, ormParam);
            }
            if(!ggfsList.isEmpty()) {
                GomoGomoFeatureSetaEntity ggfs = ggfsList.get(0);
                //处理字段
                gomoGomoFeatureSetaEntity.setGomo_feature_old(ggfs.getGomo_feature());
                gomoGomoFeatureSetaEntity.setGomo_feature_val_old(ggfs.getGomo_feature_val());
                //人和时间
                gomoGomoFeatureSetaEntity.setCretime(ggfs.getCretime());
                gomoGomoFeatureSetaEntity.setCreuser(ggfs.getCreuser());
                gomoGomoFeatureSetaEntity.setModtime(new Date());
                gomoGomoFeatureSetaEntity.setModuser(sessionEntity.getEmployeeId());
            }else {
                gomoGomoFeatureSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                gomoGomoFeatureSetaEntity.setCretime(new Date());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return gomoGomoFeatureSetaEntity;
    }

    @Transactional(readOnly = true, rollbackFor = Exception.class)
    private GomoGomoParkSetaEntity getGomoGomoParkSetOldData(GomoGomoParkSetaEntity gomoGomoParkSetaEntity) {
        //根据id查询整条旧数据，往新的实体中赋值，注意赋值创建人和时间，另外加上修改人和时间
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID + "," + GomoGomoParkSetaProperty.GOMO_STATUS + ","
                            + GomoGomoParkSetaProperty.GOMO_PM_CODE + "," + GomoGomoParkSetaProperty.GOMO_SUPPLIER + ","
                            + GomoGomoParkSetaProperty.GOMO_SUPPLIER_PART + "," + GomoGomoParkSetaProperty.GOMO_STOCK_MAX + ","
                            + GomoGomoParkSetaProperty.GOMO_STOCK_SFTY + "," + GomoGomoParkSetaProperty.GOMO_COST_TYPE + ",cretime,creuser");
        ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, gomoGomoParkSetaEntity.getId()));
        try {
            List<GomoGomoParkSetaEntity> ggpsList = ormService.selectBeanList(GomoGomoParkSetaEntity.class, ormParam);
            if(!ggpsList.isEmpty()) {
                GomoGomoParkSetaEntity ggps = ggpsList.get(0);
                //处理字段
                gomoGomoParkSetaEntity.setGomo_status_old(ggps.getGomo_status());
                gomoGomoParkSetaEntity.setGomo_pm_code_old(ggps.getGomo_pm_code());
                gomoGomoParkSetaEntity.setGomo_supplier_old(ggps.getGomo_supplier());
                gomoGomoParkSetaEntity.setGomo_supplier_part_old(ggps.getGomo_supplier_part());
                gomoGomoParkSetaEntity.setGomo_stock_max_old(ggps.getGomo_stock_max());
                gomoGomoParkSetaEntity.setGomo_stock_min_old(ggps.getGomo_stock_min());
                gomoGomoParkSetaEntity.setGomo_stock_sfty_old(ggps.getGomo_stock_sfty());
                gomoGomoParkSetaEntity.setGomo_cost_type_old(ggps.getGomo_cost_type());
                //人和时间
                gomoGomoParkSetaEntity.setCretime(ggps.getCretime());
                gomoGomoParkSetaEntity.setCreuser(ggps.getCreuser());
                gomoGomoParkSetaEntity.setModtime(new Date());
                gomoGomoParkSetaEntity.setModuser(sessionEntity.getEmployeeId());
            }else {
                gomoGomoParkSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                gomoGomoParkSetaEntity.setCretime(new Date());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
        return gomoGomoParkSetaEntity;
    }

    /**
     * 根据pid删除属性集表
     * @Param
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    private void deleteSets(String pid, Class cla){
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID, pid)));
        try {
            ormService.delete(cla, ormParam);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 物品维护单删除方法
     * 注意这个方法只是删除维护单，不是删除物品信息
     * @Param
     */
    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result deleteGoodsMaintOrder(String id) {
        logger.info("物品维护单删除接口开始服务......");
        Result result = new Result();
        try{
            //删除物品维护单主表信息
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.ID, id));
            ormService.delete(GoodsmaintorderEntity.class, ormParam);
            //删除物品维护单-文档资料
            OrmParam ormParamDoc = new OrmParam();
            ormParamDoc.setWhereExp(ormParamDoc.getEqualXML(NodeConstant.PID, id));
            ormService.delete(GomoGomoDocSetaEntity.class, ormParamDoc);
            //删除物品维护单-物品特征
            OrmParam ormParamFea = new OrmParam();
            ormParamFea.setWhereExp(ormParamFea.getEqualXML(NodeConstant.PID, id));
            ormService.delete(GomoGomoFeatureSetaEntity.class, ormParamFea);
            //删除物品维护单-园区属性
            OrmParam ormParamPark = new OrmParam();
            ormParamPark.setWhereExp(ormParamPark.getEqualXML(NodeConstant.PID, id));
            ormService.delete(GomoGomoParkSetaEntity.class, ormParamPark);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_DELETE + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_DELETE + e.getMessage());
        }
        return new Result();
    }

    /**
     * 物品维护单提交方法
     * @Param
     */
    @Override
    public Result submitGoodsMaintOrder(GoodsMaintOrderDTO goodsMaintOrderDTO) {
        logger.info("物品维护单提交接口开始服务......");
        Result result = new Result();
        try{
            goodsMaintOrderDTO.setSubmit(true);
            JSONObject resultObj = (JSONObject)saveGoodsMaintOrder(goodsMaintOrderDTO).getData();
            //提交流程
            String orderInstanceId = resultObj.getString("orderId");
            String orderDefId = goodsMaintOrderDTO.getOrdeRodeObj();
            Submit(orderDefId, orderInstanceId);
            result.setData(resultObj);
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_SUBMIT + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_SUBMIT + e.getMessage());
        }
        return result;
    }

    /**
     * 单独做提交和改单据状态操作，加事务
     * @param orderDefId
     * @param orderInstanceId
     */
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void Submit(String orderDefId, String orderInstanceId) {
        bizFormService.submitWorkFlow(orderDefId, orderInstanceId);
        updateGoodsOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_2);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result approveGoodsMaintOrder(String orderInstanceId, String handlerType) {
        logger.info("物品维护单批准通过接口开始服务：orderInstanceId：" + orderInstanceId + ",handlerType:" + handlerType);
        Result result = new Result();
        try{
            switch (handlerType) {
                case WFHandlerTypeConstants.PASS: {
                    saveGoods(orderInstanceId);
                    updateGoodsOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_5);
                    break;
                }
                case WFHandlerTypeConstants.REVOKE: {
                    updateGoodsOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_1);
                    break;
                }
                case WFHandlerTypeConstants.RETURN_BACK: {
                    updateGoodsOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_6);
                    break;
                }
                default: {
                    break;
                }
            }
        }catch (Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_PASS + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_PASS + e.getMessage());
        }
        return result;
    }

    @Override
    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result audit(JSONObject auditSet) {
        Result result = new Result();
        String auditKey = auditSet.getString(WorkFlowConstants.PARAM_AUDITKEY);
        String formState = auditSet.getString(WorkFlowConstants.PARAM_FORMSTATE);
        String actInstanceId = auditSet.getString(WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
        String opinion = NullUtils.valueOf(auditSet.getString(WorkFlowConstants.PARAM_OPINION));
        if (StringUtil.isNullOrEmpty(auditKey)) {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_AUDITKEY);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        } else if (StringUtil.isNullOrEmpty(formState)) {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_FORMSTATE);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        } else if (StringUtil.isNullOrEmpty(actInstanceId)) {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        }
        // 调用流程
        bizFormService.audit(actInstanceId, opinion, auditKey);
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public void saveGoods(String orderInstanceId) {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        try{
            //查询物品维护单表
            OrmParam ormParamMain = new OrmParam();
            ormParamMain.addColumn(EdmSysColumn.ID + "," + GoodsmaintorderProperty.GOMO_CODE + ","
                                    + GoodsmaintorderProperty.GOMO_NAME + "," + GoodsmaintorderProperty.GOMO_CLASS + ","
                                    + GoodsmaintorderProperty.GOMO_UNIT + "," + GoodsmaintorderProperty.GOMO_UNIT_GROUP + ","
                                    + GoodsmaintorderProperty.GOMO_MODEL + "," + GoodsmaintorderProperty.GOMO_BAR_CODE + ","
                                    + GoodsmaintorderProperty.GOMO_BRAND + "," + GoodsmaintorderProperty.GOMO_ORIGIN + ","
                                    + GoodsmaintorderProperty.GOMO_WEIGHT_NET + "," + GoodsmaintorderProperty.GOMO_WEIGHT_GROSS + ","
                                    + GoodsmaintorderProperty.GOMO_VOLUME_NET + "," + GoodsmaintorderProperty.GOMO_VOLUME_BULK + ","
                                    + GoodsmaintorderProperty.GOMO_CHECK_CYC + "," + GoodsmaintorderProperty.GOMO_SHELFLIFE + ","
                                    + GoodsmaintorderProperty.GOMO_RECEIPT_TOL + "," + GoodsmaintorderProperty.GOMO_ORDMULT_QTY + ","
                                    + GoodsmaintorderProperty.GOMO_PALLET_QTY + "," + GoodsmaintorderProperty.GOMO_DESC + ","
                                    + GoodsmaintorderProperty.GOMO_ORDMIN_QTY);
            ormParamMain.setWhereExp(ormParamMain.getEqualXML(NodeConstant.ID, orderInstanceId));
            List<GoodsmaintorderEntity> gmorList = ormService.selectBeanList(GoodsmaintorderEntity.class, ormParamMain);
            if(!gmorList.isEmpty()) {
                String goodsId = "";
                //物品表实体转换
                GoodsEntity goodsEntity = new GoodsEntity();
                GoodsmaintorderEntity gmor = gmorList.get(0);
                goodsEntity.setGods_code(gmor.getGomo_code());
                goodsEntity.setGods_name(gmor.getGomo_name());
                goodsEntity.setGods_class(gmor.getGomo_class());
                goodsEntity.setGods_unit(gmor.getGomo_unit());
                goodsEntity.setGods_unit_group(gmor.getGomo_unit_group());
                goodsEntity.setGods_model(gmor.getGomo_model());
                goodsEntity.setGods_bar_code(gmor.getGomo_bar_code());
                goodsEntity.setGods_brand(gmor.getGomo_brand());
                goodsEntity.setGods_origin(gmor.getGomo_origin());
                goodsEntity.setGods_weight_gross(gmor.getGomo_weight_gross());
                goodsEntity.setGods_weight_net(gmor.getGomo_weight_net());
                goodsEntity.setGods_volume_bulk(gmor.getGomo_volume_bulk());
                goodsEntity.setGods_volume_net(gmor.getGomo_volume_net());
                goodsEntity.setGods_check_cyc(gmor.getGomo_check_cyc());
                goodsEntity.setGods_shelflife(gmor.getGomo_shelflife());
                goodsEntity.setGods_receipt_tol(gmor.getGomo_receipt_tol());
                goodsEntity.setGods_ordmult_qty(gmor.getGomo_ordmult_qty());
                goodsEntity.setGods_pallet_qty(gmor.getGomo_pallet_qty());
                goodsEntity.setGods_desc(gmor.getGomo_desc());
                goodsEntity.setGods_ordmin_qty(gmor.getGomo_ordmin_qty());
                //企业对象
                goodsEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
                //对象类
                goodsEntity.setEdmd_class("goods");
                //判断是否存在物品编码重复
                OrmParam ormParamGoods = new OrmParam();
                ormParamGoods.addColumn(EdmSysColumn.ID);
                ormParamGoods.setWhereExp(ormParamGoods.getEqualXML(GoodsProperty.GODS_CODE, gmorList.get(0).getGomo_code()));
                List<GoodsEntity> goodsList = ormService.selectBeanList(GoodsEntity.class, ormParamGoods);
                if(!goodsList.isEmpty()) {
                    goodsEntity.setModtime(new Date());
                    goodsEntity.setModuser(sessionEntity.getEmployeeId());
                    //如果物品编码重复，则进行更新
                    OrmParam ormParam = new OrmParam();
                    ormParam.setWhereExp(ormParam.getEqualXML(GoodsProperty.GODS_CODE, gmorList.get(0).getGomo_code()));
                    ormService.updateSelective(goodsEntity, ormParam);
                    goodsId = goodsList.get(0).getId();
                }else {
                    //如果物品编码没有重复，则进行新增
                    goodsEntity.setCretime(new Date());
                    goodsEntity.setCreuser(sessionEntity.getEmployeeId());
                    goodsEntity.setModtime(goodsEntity.getCretime());
                    goodsEntity.setModuser(sessionEntity.getEmployeeId());
                    goodsId = ormService.insert(goodsEntity).toString();
                }
                OrmParam ormParamDoc = new OrmParam();
                ormParamDoc.addColumn(EdmSysColumn.ID + "," + GomoGomoDocSetaProperty.GOMO_DOC_NAME + ","
                                        + GomoGomoDocSetaProperty.GOMO_DOC_TYPE + "," + GomoGomoDocSetaProperty.GOMO_DOC_URL);
                ormParamDoc.setWhereExp(ormParamDoc.getEqualXML(NodeConstant.PID, orderInstanceId));
                //查询文档资料表
                List<GomoGomoDocSetaEntity> ggdsList = ormService.selectBeanList(GomoGomoDocSetaEntity.class, ormParamDoc);
                if(!ggdsList.isEmpty()) {
                    //删除旧数据
                    deleteSets(goodsId,GodsGodsDocSetaEntity.class);
                    for(GomoGomoDocSetaEntity ggds:ggdsList) {
                        //文档资料表实体转换
                        GodsGodsDocSetaEntity godsGodsDocSetaEntity = new GodsGodsDocSetaEntity();
                        godsGodsDocSetaEntity.setPid(goodsId);
                        godsGodsDocSetaEntity.setGods_doc_name(ggds.getGomo_doc_name());
                        godsGodsDocSetaEntity.setGods_doc_type(ggds.getGomo_doc_type());
                        godsGodsDocSetaEntity.setGods_doc_url(ggds.getGomo_doc_url());
                        godsGodsDocSetaEntity.setCretime(new Date());
                        godsGodsDocSetaEntity.setClassName("gods_doc_set");
                        godsGodsDocSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                        godsGodsDocSetaEntity.setModtime(godsGodsDocSetaEntity.getCretime());
                        godsGodsDocSetaEntity.setModuser(sessionEntity.getEmployeeId());
                        //插入新数据
                        ormService.insertSelective(godsGodsDocSetaEntity);
                    }
                }
                //查询物品特性表
                OrmParam ormParamFea = new OrmParam();
                ormParamFea.addColumn(EdmSysColumn.ID + "," + GomoGomoFeatureSetaProperty.GOMO_FEATURE + "," + GomoGomoFeatureSetaProperty.GOMO_FEATURE_VAL);
                ormParamFea.setWhereExp(ormParamFea.getEqualXML(NodeConstant.PID, orderInstanceId));
                List<GomoGomoFeatureSetaEntity> ggfsList = ormService.selectBeanList(GomoGomoFeatureSetaEntity.class, ormParamFea);
                if(!ggfsList.isEmpty()) {
                    //删除旧数据
                    deleteSets(goodsId,GodsGodsFeatureSetaEntity.class);
                    for(GomoGomoFeatureSetaEntity ggfs:ggfsList) {
                        //物品特性表实体转换
                        GodsGodsFeatureSetaEntity godsGodsFeatureSetaEntity = new GodsGodsFeatureSetaEntity();
                        godsGodsFeatureSetaEntity.setPid(goodsId);
                        godsGodsFeatureSetaEntity.setGods_feature(ggfs.getGomo_feature());
                        godsGodsFeatureSetaEntity.setGods_feature_val(ggfs.getGomo_feature_val());
                        godsGodsFeatureSetaEntity.setCretime(new Date());
                        godsGodsFeatureSetaEntity.setClassName("gods_feature_set");
                        godsGodsFeatureSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                        godsGodsFeatureSetaEntity.setModtime(godsGodsFeatureSetaEntity.getCretime());
                        godsGodsFeatureSetaEntity.setModuser(sessionEntity.getEmployeeId());
                        //插入新数据
                        ormService.insertSelective(godsGodsFeatureSetaEntity);
                    }
                }
                //查询园区属性表
                OrmParam ormParamPark = new OrmParam();
                ormParamPark.addColumn(EdmSysColumn.ID + ","
                                        + GomoGomoParkSetaProperty.GOMO_PARK + "," + GomoGomoParkSetaProperty.GOMO_STATUS + ","
                                        + GomoGomoParkSetaProperty.GOMO_PM_CODE + "," + GomoGomoParkSetaProperty.GOMO_SUPPLIER + ","
                                        + GomoGomoParkSetaProperty.GOMO_SUPPLIER_PART + "," + GomoGomoParkSetaProperty.GOMO_STOCK_MAX + ","
                                        + GomoGomoParkSetaProperty.GOMO_STOCK_MIN + "," + GomoGomoParkSetaProperty.GOMO_STOCK_SFTY + ","
                                        + GomoGomoParkSetaProperty.GOMO_COST_TYPE + "," + GomoGomoParkSetaProperty.GOMO_ISBATCHSPLIT + ","
                                        + GomoGomoParkSetaProperty.GOMO_ISININSPECT + "," + GomoGomoParkSetaProperty.GOMO_ISOUTINSPECT);
                ormParamPark.setWhereExp(ormParamPark.getEqualXML(NodeConstant.PID, orderInstanceId));
                List<GomoGomoParkSetaEntity> ggpsList = ormService.selectBeanList(GomoGomoParkSetaEntity.class, ormParamPark);
                if(!ggpsList.isEmpty()) {
                    //删除旧数据
                    deleteSets(goodsId,GodsGodsParkSetaEntity.class);
                    for(GomoGomoParkSetaEntity ggps:ggpsList) {
                        //园区属性表实体转换
                        GodsGodsParkSetaEntity godsGodsParkSetaEntity = new GodsGodsParkSetaEntity();
                        godsGodsParkSetaEntity.setPid(goodsId);
                        godsGodsParkSetaEntity.setGods_park(ggps.getGomo_park());
                        godsGodsParkSetaEntity.setGods_status(ggps.getGomo_status());
                        godsGodsParkSetaEntity.setGods_pm_code(ggps.getGomo_pm_code());
                        godsGodsParkSetaEntity.setGods_supplier(ggps.getGomo_supplier());
                        godsGodsParkSetaEntity.setGods_supplier_part(ggps.getGomo_supplier_part());
                        godsGodsParkSetaEntity.setGods_stock_max(ggps.getGomo_stock_max());
                        godsGodsParkSetaEntity.setGods_stock_min(ggps.getGomo_stock_min());
                        godsGodsParkSetaEntity.setGods_stock_sfty(ggps.getGomo_stock_sfty());
                        godsGodsParkSetaEntity.setGods_cost_type(ggps.getGomo_cost_type());
                        godsGodsParkSetaEntity.setGods_isbatchsplit(ggps.getGomo_isbatchsplit());
                        godsGodsParkSetaEntity.setGods_isininspect(ggps.getGomo_isininspect());
                        godsGodsParkSetaEntity.setGods_isoutinspect(ggps.getGomo_isoutinspect());
                        godsGodsParkSetaEntity.setCretime(new Date());
                        godsGodsParkSetaEntity.setClassName("gods_park_set");
                        godsGodsParkSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                        godsGodsParkSetaEntity.setModtime(godsGodsParkSetaEntity.getCretime());
                        godsGodsParkSetaEntity.setModuser(sessionEntity.getEmployeeId());
                        //插入新数据
                        ormService.insertSelective(godsGodsParkSetaEntity);
                    }
                }
            }else {
                logger.error("批准通过保存操作出错，维护单id："+orderInstanceId+" 不存在");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Transactional(readOnly = false, rollbackFor = Exception.class)
    private void updateGoodsOrderStatus(String orderInstanceId, String orderStatus) {
        //准备数据
        GoodsmaintorderEntity goodsmaintorderEntity = new GoodsmaintorderEntity();
        goodsmaintorderEntity.setId(orderInstanceId);
        goodsmaintorderEntity.setOrde_status(orderStatus);
        //更新表操作
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        //制单时间
        goodsmaintorderEntity.setOrde_date(new Date());
        //所属企业对象id
        goodsmaintorderEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
        goodsmaintorderEntity.setModuser(sessionEntity.getEmployeeId());
        goodsmaintorderEntity.setModtime(goodsmaintorderEntity.getOrde_date());
        try {
            ormService.updateSelective(goodsmaintorderEntity);
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR, "更新物品维护单状态错误：" + e.getMessage());
        }
    }

    /**
     * 查物品表和物品维护单表，物品编码是否有重复
     * 如果有，则返回状态码为0,给出错误信息
     * 如果没有，则返回状态码为1
     * @param godsCode 物品编码godsCode
     */
    @Override
    @Transactional(readOnly = true, rollbackFor = Exception.class)
    public Result check(String godsCode) {
        logger.info("物品编码校验接口开始服务......");
        Result result = new Result();
        JSONObject dataObj = new JSONObject();
        dataObj.put("flag", "isok");
        dataObj.put("goodsId", "");
        try {
            if(!"".equals(godsCode) && godsCode!=null) {
                //查询物品表
                OrmParam ormParamGoods = new OrmParam();
                ormParamGoods.addColumn(EdmSysColumn.ID);
                ormParamGoods.setWhereExp(ormParamGoods.getEqualXML(GoodsProperty.GODS_CODE, godsCode));
                List<GoodsEntity> list = ormService.selectBeanList(GoodsEntity.class, ormParamGoods);
                if(!list.isEmpty()) {
                    dataObj.put("flag", "fail");
                    dataObj.put("goodsId", list.get(0).getId());
                    dataObj.put("orderId", "");
                    dataObj.put("orderNbr", "");
                    dataObj.put("orderType", "");
                }
                //查询物品维护单表
                OrmParam ormParamGoodsMaint = new OrmParam();
                ormParamGoodsMaint.addColumn(EdmSysColumn.ID + "," + OrderProperty.ORDE_NBR);
                ormParamGoodsMaint.setWhereExp(OrmParam.or(
                        ormParamGoodsMaint.getEqualXML(OrderProperty.ORDE_STATUS, OrderConstants.ORDE_STATUS_1),
                        ormParamGoodsMaint.getEqualXML(OrderProperty.ORDE_STATUS, OrderConstants.ORDE_STATUS_2),
                        ormParamGoodsMaint.getEqualXML(OrderProperty.ORDE_STATUS, OrderConstants.ORDE_STATUS_3),
                        ormParamGoodsMaint.getEqualXML(OrderProperty.ORDE_STATUS, OrderConstants.ORDE_STATUS_4)));
                ormParamGoodsMaint.setWhereExp(OrmParam.and(ormParamGoodsMaint.getWhereExp(),
                        ormParamGoodsMaint.getEqualXML(GoodsmaintorderProperty.GOMO_CODE, godsCode)));
                List<GoodsmaintorderEntity> gmoeList = ormService.selectBeanList(GoodsmaintorderEntity.class, ormParamGoodsMaint);
                if(!gmoeList.isEmpty()) {
                    dataObj.put("flag", "fail");
                    dataObj.put("orderId", gmoeList.get(0).getId());
                    dataObj.put("orderNbr", gmoeList.get(0).getOrde_nbr());
                    if("1".equals(gmoeList.get(0).getOrde_status()) || gmoeList.get(0).getOrde_status() == "1") {
                        dataObj.put("orderType", "temp");
                    }else {
                        dataObj.put("orderType", "normal");
                    }
                }
            }
        }catch(Exception e) {
            e.printStackTrace();
            logger.error(ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_CHECK + ":" + e.getMessage());
            throw new ApplicationException(Result.RECODE_ERROR, ErrorMsgConstants.ERROR_MSG_PU_GOODSMAINT_CHECK + e.getMessage());
        }
        result.setData(dataObj);
        return result;
    }

}
