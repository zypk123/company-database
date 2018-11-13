package com.huntkey.rx.purchase.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.CurrencyProperty;
import com.huntkey.rx.edm.constant.RelaRelaAccountSuppSetbProperty;
import com.huntkey.rx.edm.constant.RelaRelaSupplierSetaProperty;
import com.huntkey.rx.edm.constant.TaxrateProperty;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.RelationConstants;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.currency.CurrencyDTO;
import com.huntkey.rx.purchase.common.model.relation.*;
import com.huntkey.rx.purchase.common.model.taxrate.TaxrateDTO;
import com.huntkey.rx.purchase.provider.dao.RelationDao;
import com.huntkey.rx.purchase.provider.service.RelationService;
import com.huntkey.rx.purchase.provider.service.SettleMenetService;
import com.huntkey.rx.purchase.provider.service.TaxrateService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * 伙伴类Service接口实现类
 *
 * @author zhangyu
 * @create 2017-12-29 11:02
 **/
@Service
@Transactional(rollbackFor = Exception.class)
public class RelationServiceImpl implements RelationService {

    private static final Logger logger = LoggerFactory.getLogger(RelationServiceImpl.class);

    @Autowired
    RelationDao relationDao;

    @Autowired
    SettleMenetService settleMenetService;

    @Autowired
    TaxrateService taxrateService;

    @Autowired
    private OrmService ormService;

    @Override
    public Pagination<RelationPageListDTO> getRelationPageList(RelationPageParamDTO relationPageParamDTO) {
        String[] relationIds = null;
        if (!StringUtil.isNullOrEmpty(relationPageParamDTO.getRelaStatus()) ||
                (relationPageParamDTO.getRelaType() != null && relationPageParamDTO.getRelaType().length > 0)) {
            List<String> relationIdList = relationDao.selectRelaIdByRelaStatus(relationPageParamDTO.getRelaStatus(),
                    relationPageParamDTO.getRelaType());
            if (relationIdList.size() <= 0){
                return null;
            }
            String[] ids = new String[relationIdList.size()];
            for (int i = 0; i < relationIdList.size(); i++) {
                ids[i] = relationIdList.get(i);
            }
            relationIds = ids;
        }

        Pagination<RelationPageListDTO> relationPagination = null;
        Pagination<RelationEntity> relationEntityPagination = relationDao.selectRelationPageList(relationPageParamDTO.getRelaCode(),
                relationPageParamDTO.getRelaShortName(), null, relationIds,
                relationPageParamDTO.getCurrentPage(), relationPageParamDTO.getPageSize());

        String[] relaStatus = null;
        if (!StringUtil.isNullOrEmpty(relationPageParamDTO.getRelaStatus())){
            relaStatus = new String[]{relationPageParamDTO.getRelaStatus()};
        }

        if (relationEntityPagination  != null){
            List<RelationEntity> relationEntityList = relationEntityPagination.getList();

            List<RelationPageListDTO> relationPageListDTOList = new ArrayList<>();

            for (RelationEntity relationEntity : relationEntityList) {
                List<RelaRelaTypesSetaEntity> relaTypesSetaEntityList = relationDao.selectRelaTypesSet(relationEntity.getId(), null);
                relationEntity.setRela_types_set(relaTypesSetaEntityList);
                RelationPageListDTO relationPageListDTO = JSONObject.parseObject(JSON.toJSONString(relationEntity), RelationPageListDTO.class);

                List<RelationStatusDTO> relationStatusList = new ArrayList<>();

                for (RelaRelaTypesSetaEntity relaTypesSetaEntity : relaTypesSetaEntityList) {
                    RelationStatusDTO relationStatusDTO = new RelationStatusDTO();
                    relationStatusDTO.setRelaType(relaTypesSetaEntity.getRela_type());

                    //客户
                    if (relaTypesSetaEntity.getRela_type().equals(RelationConstants.RELA_TYPE_CUST)) {
                        List<RelaRelaCustSetaEntity> relaCustSetaEntityList = relationDao.selectRelaCustSet(relationEntity.getId(),
                                relaStatus, null, null, null);
                        if (relaCustSetaEntityList.size() > 0) {
                            RelaRelaCustSetaEntity relaRelaCustSetaEntity = relaCustSetaEntityList.get(0);
                            //状态
                            relationStatusDTO.setRelaStatus(relaRelaCustSetaEntity.getRela_stat_cust());
                            //服务部门id
                            relationStatusDTO.setRelaServdept(relaRelaCustSetaEntity.getRela_servdept_cust());
                            //服务部门名称查询
                            DepttreeEntity depttreeEntity = relationDao.selectDepttreeEntityById(relaRelaCustSetaEntity.getRela_servdept_cust());
                            if (depttreeEntity != null) {
                                relationStatusDTO.setRelaServdeptName(depttreeEntity.getMdep_name());
                            }
                            //服务团队查询 客户服务人员写入销售负责人
                            List<RelaRelaServtCustSetbEntity> servtCustSetbEntityList = relationDao.selectRelaServtCustSet(relaRelaCustSetaEntity.getId(),
                                    new String[]{RelationConstants.TEAM_TYPE_BUY}, null);
                            if (servtCustSetbEntityList.size() > 0) {
                                RelaRelaServtCustSetbEntity servtCustSetbEntity = servtCustSetbEntityList.get(0);
                                //服务团队类型
                                relationStatusDTO.setRelaSttype(servtCustSetbEntity.getRela_sttype_cust());
                                //服务人员id
                                relationStatusDTO.setRelaStemp(servtCustSetbEntity.getRela_stemp_cust());
                                //服务人员名称
                                EmployeeEntity employeeEntity = relationDao.selectEmployeeEntityById(servtCustSetbEntity.getRela_stemp_cust());
                                if (employeeEntity != null) {
                                    relationStatusDTO.setRelaStempName(employeeEntity.getRemp_name());
                                }
                            }
                        }

                    }

                    //供应商
                    if (relaTypesSetaEntity.getRela_type().equals(RelationConstants.RELA_TYPE_SUPPLIER)) {
                        List<RelaRelaSupplierSetaEntity> relaSupplierSetaEntityList = relationDao.selectRelaSupplierSet(relationEntity.getId(),
                                null, relaStatus, null, null, null);
                        if (relaSupplierSetaEntityList.size() > 0) {
                            RelaRelaSupplierSetaEntity relaSupplierSetaEntity = relaSupplierSetaEntityList.get(0);
                            //状态
                            relationStatusDTO.setRelaStatus(relaSupplierSetaEntity.getRela_stat_supp());
                            //服务部门id
                            relationStatusDTO.setRelaServdept(relaSupplierSetaEntity.getRela_servdept_supp());
                            //服务部门名称查询
                            DepttreeEntity depttreeEntity = relationDao.selectDepttreeEntityById(relaSupplierSetaEntity.getRela_servdept_supp());
                            if (depttreeEntity != null) {
                                relationStatusDTO.setRelaServdeptName(depttreeEntity.getMdep_name());
                            }
                            //服务团队查询 供应商服务人员写入采购负责人
                            List<RelaRelaServtSuppSetbEntity> servtSuppSetbEntityList = relationDao.selectRelaServtSuppSet(relaSupplierSetaEntity.getId(),
                                    new String[]{RelationConstants.TEAM_TYPE_BUY}, null);
                            if (servtSuppSetbEntityList.size() > 0) {
                                RelaRelaServtSuppSetbEntity servtSuppSetbEntity = servtSuppSetbEntityList.get(0);
                                //服务团队类型
                                relationStatusDTO.setRelaSttype(servtSuppSetbEntity.getRela_sttype_supp());
                                //服务人员id
                                relationStatusDTO.setRelaStemp(servtSuppSetbEntity.getRela_stemp_supp());
                                //服务人员名称
                                EmployeeEntity employeeEntity = relationDao.selectEmployeeEntityById(servtSuppSetbEntity.getRela_stemp_supp());
                                if (employeeEntity != null) {
                                    relationStatusDTO.setRelaStempName(employeeEntity.getRemp_name());
                                }
                            }
                        }
                    }
                    //添加状态及服务团队等数据
                    relationStatusList.add(relationStatusDTO);
                }
                relationPageListDTO.setRelationStatusList(relationStatusList);
                //维护人员名称
                EmployeeEntity moduserEnity = relationDao.selectEmployeeEntityById(relationPageListDTO.getModuser());
                if (moduserEnity != null) {
                    relationPageListDTO.setModuserName(moduserEnity.getRemp_name());
                }
                relationPageListDTOList.add(relationPageListDTO);
            }

            relationPagination = new Pagination<>(relationPageListDTOList,
                    relationEntityPagination.getPageNum(), relationEntityPagination.getPageSize(), relationEntityPagination.getTotal());
        }
        return relationPagination;
    }

    @Override
    public RelationDTO getRelationById(String relationId) {
        RelationEntity relationEntity = relationDao.selectRelationentityById(relationId);
        if (relationEntity == null){
            return null;
        }

        RelationDTO relationDTO = JSONObject.parseObject(JSON.toJSONString(relationEntity), RelationDTO.class);

        //股东集
        List<RelaRelaHolderSetaEntity> relaHolderSetaEntityList = relationDao.selectRelaHolderSet(relationId, null, null);
        List<RelaRelaHolderSetaDTO> relaHolderSetaDTOList = JSONArray.parseArray(JSON.toJSONString(relaHolderSetaEntityList), RelaRelaHolderSetaDTO.class);
        relationDTO.setRelaHolderSet(relaHolderSetaDTOList);

        //体系认证集
        List<RelaRelaSystemSetaEntity> relaSystemSetaEntityList = relationDao.selectRelaSystemSet(relationId, null);
        List<RelaRelaSystemSetaDTO> relaSystemSetaDTOList = JSONArray.parseArray(JSON.toJSONString(relaSystemSetaEntityList), RelaRelaSystemSetaDTO.class);
        relationDTO.setRelaSystemSet(relaSystemSetaDTOList);

        //伙伴类型集
        List<RelaRelaTypesSetaEntity> relaTypesSetaEntityList = relationDao.selectRelaTypesSet(relationId, null);
        List<RelaRelaTypesSetaDTO> relaTypesSetaDTOList = JSONArray.parseArray(JSON.toJSONString(relaTypesSetaEntityList), RelaRelaTypesSetaDTO.class);
        relationDTO.setRelaTypesSet(relaTypesSetaDTOList);

        for (RelaRelaTypesSetaDTO relaTypesSetaDTO : relaTypesSetaDTOList) {
            //客户
            if (relaTypesSetaDTO.getRelaType().equals(RelationConstants.RELA_TYPE_CUST)) {
                List<RelaRelaCustSetaEntity> relaCustSetaEntityList = relationDao.selectRelaCustSet(relationEntity.getId(), null,
                        null, null, null);
                if (relaCustSetaEntityList.size() > 0) {
                    List<RelaRelaCustSetaDTO> relaCustSetaDTOList = JSONArray.parseArray(JSON.toJSONString(relaCustSetaEntityList), RelaRelaCustSetaDTO.class);
                    //服务部门
                    DepttreeEntity servDepttreeEntityCust = relationDao.selectDepttreeEntityById(relaCustSetaDTOList.get(0).getRelaServdeptCust());
                    if (servDepttreeEntityCust != null){
                        relaCustSetaDTOList.get(0).setRelaServdeptCode(servDepttreeEntityCust.getMdep_code());
                        relaCustSetaDTOList.get(0).setRelaServdeptName(servDepttreeEntityCust.getMdep_name());
                    }

                    //结算方式
                    SettlemenetEntity settlemenetEntityCust = settleMenetService.load(relaCustSetaDTOList.get(0).getRelaSettWayc());
                    if (settlemenetEntityCust != null){
                        relaCustSetaDTOList.get(0).setRelaSettWaycStr(settlemenetEntityCust.getSett_way());
                    }

                    //联系人
                    List<RelaRelaContactCustSetbEntity> relaContactCustSetbEntityList = relationDao.selectRelaContactCustSet(relaCustSetaDTOList.get(0).getId(),
                            null, null, null, null);
                    List<RelaRelaContactCustSetbDTO> relaContactCustSetbDTOList = JSONArray.parseArray(
                            JSON.toJSONString(relaContactCustSetbEntityList), RelaRelaContactCustSetbDTO.class);
                    relaCustSetaDTOList.get(0).setRelaContactCustSet(relaContactCustSetbDTOList);

                    //附件资料
                    List<RelaRelaAttachCustSetbEntity> relaAttachCustSetbEntityList = relationDao.selectRelaAttachCustSet(relaCustSetaDTOList.get(0).getId(),
                            null, null);
                    List<RelaRelaAttachCustSetbDTO> relaAttachCustSetbDTOList = JSONArray.parseArray(
                            JSON.toJSONString(relaAttachCustSetbEntityList), RelaRelaAttachCustSetbDTO.class);
                    relaCustSetaDTOList.get(0).setRelaAttachCustSet(relaAttachCustSetbDTOList);


                    //服务团队
                    List<RelaRelaServtCustSetbEntity> relaServtCustSetbEntityList = relationDao.selectRelaServtCustSet(relaCustSetaDTOList.get(0).getId(),
                            null, null);
                    List<RelaRelaServtCustSetbDTO> relaServtCustSetbDTOList = JSONArray.parseArray(
                            JSON.toJSONString(relaServtCustSetbEntityList), RelaRelaServtCustSetbDTO.class);
                    for (RelaRelaServtCustSetbDTO servtCustSetbDTO : relaServtCustSetbDTOList) {
                        EmployeeEntity employeeCustEntity = relationDao.selectEmployeeEntityById(servtCustSetbDTO.getRelaStempCust());
                        if (employeeCustEntity != null){
                            servtCustSetbDTO.setRelaStempNo(employeeCustEntity.getRemp_no());
                            servtCustSetbDTO.setRelaStempName(employeeCustEntity.getRemp_name());
                            servtCustSetbDTO.setRelaStempTel(employeeCustEntity.getRemp_tel());

                            JobpositionEntity jobpositionCustEntity = relationDao.selectJobpositionEntityById(employeeCustEntity.getRemp_post());
                            if (jobpositionCustEntity != null){
                                servtCustSetbDTO.setRelaStempPost(jobpositionCustEntity.getId());
                                servtCustSetbDTO.setRelaStempPostCode(jobpositionCustEntity.getRpos_code());
                                servtCustSetbDTO.setRelaStempPostName(jobpositionCustEntity.getRpos_name());
                            }

                            DepttreeEntity depttreeCustEntity = relationDao.selectDepttreeEntityById(employeeCustEntity.getRemp_dept());
                            if (depttreeCustEntity != null) {
                                servtCustSetbDTO.setRelaStempDept(depttreeCustEntity.getId());
                                servtCustSetbDTO.setRelaStempDeptCode(depttreeCustEntity.getMdep_code());
                                servtCustSetbDTO.setRelaStempDeptName(depttreeCustEntity.getMdep_name());
                            }
                        }


                    }
                    relaCustSetaDTOList.get(0).setRelaServtCustSet(relaServtCustSetbDTOList);

                    //账户管理
                    List<RelaRelaAccountCustSetbEntity> relaAccountCustSetbEntityList = relationDao.selectRelaAccountCustSet(relaCustSetaDTOList.get(0).getId(),
                            null, null, null, null, null);
                    List<RelaRelaAccountCustSetbDTO> relaAccountCustSetbDTOList = JSONArray.parseArray(
                            JSON.toJSONString(relaAccountCustSetbEntityList), RelaRelaAccountCustSetbDTO.class);
                    relaCustSetaDTOList.get(0).setRelaAccountCustSet(relaAccountCustSetbDTOList);

                    //交货管理
                    List<RelaRelaDeliCustSetbEntity> relaDeliCustSetbEntityList = relationDao.selectRelaDeliCustSet(relaCustSetaDTOList.get(0).getId(),
                            null, null, null, null);
                    List<RelaRelaDeliCustSetbDTO> relaDeliCustSetbDTOList = JSONArray.parseArray(
                            JSON.toJSONString(relaDeliCustSetbEntityList), RelaRelaDeliCustSetbDTO.class);
                    relaCustSetaDTOList.get(0).setRelaDeliCustSet(relaDeliCustSetbDTOList);


                    relationDTO.setRelaCustSet(relaCustSetaDTOList);
                }

            }

            //供应商
            if (relaTypesSetaDTO.getRelaType().equals(RelationConstants.RELA_TYPE_SUPPLIER)) {
                List<RelaRelaSupplierSetaEntity> relaSupplierSetaEntityList = relationDao.selectRelaSupplierSet(relationEntity.getId(),
                        null, null, null, null, null);
                if (relaSupplierSetaEntityList.size() > 0) {
                    List<RelaRelaSupplierSetaDTO> relaSupplierSetaDTOList = JSONArray.parseArray(JSON.toJSONString(relaSupplierSetaEntityList), RelaRelaSupplierSetaDTO.class);
                    //服务部门
                    DepttreeEntity servDepttreeEntitySupp = relationDao.selectDepttreeEntityById(relaSupplierSetaDTOList.get(0).getRelaServdeptSupp());
                    if (servDepttreeEntitySupp != null) {
                        relaSupplierSetaDTOList.get(0).setRelaServdeptCode(servDepttreeEntitySupp.getMdep_code());
                        relaSupplierSetaDTOList.get(0).setRelaServdeptName(servDepttreeEntitySupp.getMdep_name());
                    }

                    //结算方式
                    SettlemenetEntity settlemenetEntitySupp = settleMenetService.load(relaSupplierSetaDTOList.get(0).getRelaSettWays());
                    if (settlemenetEntitySupp != null) {
                        relaSupplierSetaDTOList.get(0).setRelaSettWaycStr(settlemenetEntitySupp.getSett_way());
                    }

                    //税率
                    TaxrateEntity taxrateEntitySupp = taxrateService.load(relaSupplierSetaDTOList.get(0).getRelaTaxrate());
                    if (taxrateEntitySupp != null){
                        relaSupplierSetaDTOList.get(0).setRelaTaxrateStr(taxrateEntitySupp.getTaxr_name());
                    }

                    //联系人
                    List<RelaRelaContactSuppSetbEntity> relaContactSuppSetbEntityList = relationDao.selectRelaContactSuppSet(relaSupplierSetaDTOList.get(0).getId(),
                            null, null, null, null);
                    List<RelaRelaContactSuppSetbDTO> relaContactSuppSetbDTOList = JSONArray.parseArray(
                            JSON.toJSONString(relaContactSuppSetbEntityList), RelaRelaContactSuppSetbDTO.class);
                    relaSupplierSetaDTOList.get(0).setRelaContactSuppSet(relaContactSuppSetbDTOList);

                    //附件资料
                    List<RelaRelaAttachSuppSetbEntity> relaAttachSuppSetbEntityList = relationDao.selectRelaAttachSuppSet(relaSupplierSetaDTOList.get(0).getId(),
                            null, null);
                    List<RelaRelaAttachSuppSetbDTO> relaAttachSuppSetbDTOList = JSONArray.parseArray(
                            JSON.toJSONString(relaAttachSuppSetbEntityList), RelaRelaAttachSuppSetbDTO.class);
                    relaSupplierSetaDTOList.get(0).setRelaAttachSuppSet(relaAttachSuppSetbDTOList);


                    //服务团队
                    List<RelaRelaServtSuppSetbEntity> relaServtSuppSetbEntityList = relationDao.selectRelaServtSuppSet(relaSupplierSetaDTOList.get(0).getId(),
                            null, null);
                    List<RelaRelaServtSuppSetbDTO> relaServtSuppSetbDTOList = JSONArray.parseArray(
                            JSON.toJSONString(relaServtSuppSetbEntityList), RelaRelaServtSuppSetbDTO.class);
                    for (RelaRelaServtSuppSetbDTO servtSuppSetbDTO : relaServtSuppSetbDTOList) {
                        EmployeeEntity employeeSuppEntity = relationDao.selectEmployeeEntityById(servtSuppSetbDTO.getRelaStempSupp());
                        if (employeeSuppEntity != null) {
                            servtSuppSetbDTO.setRelaStempNo(employeeSuppEntity.getRemp_no());
                            servtSuppSetbDTO.setRelaStempName(employeeSuppEntity.getRemp_name());
                            servtSuppSetbDTO.setRelaStempTel(employeeSuppEntity.getRemp_tel());

                            JobpositionEntity jobpositionSuppEntity = relationDao.selectJobpositionEntityById(employeeSuppEntity.getRemp_post());
                            if (jobpositionSuppEntity != null) {
                                servtSuppSetbDTO.setRelaStempPost(jobpositionSuppEntity.getId());
                                servtSuppSetbDTO.setRelaStempPostCode(jobpositionSuppEntity.getRpos_code());
                                servtSuppSetbDTO.setRelaStempPostName(jobpositionSuppEntity.getRpos_name());
                            }

                            DepttreeEntity depttreeSuppEntity = relationDao.selectDepttreeEntityById(employeeSuppEntity.getRemp_dept());
                            if (depttreeSuppEntity != null) {
                                servtSuppSetbDTO.setRelaStempDept(depttreeSuppEntity.getId());
                                servtSuppSetbDTO.setRelaStempDeptCode(depttreeSuppEntity.getMdep_code());
                                servtSuppSetbDTO.setRelaStempDeptName(depttreeSuppEntity.getMdep_name());
                            }
                        }


                    }
                    relaSupplierSetaDTOList.get(0).setRelaServtSuppSet(relaServtSuppSetbDTOList);

                    //账户管理
                    List<RelaRelaAccountSuppSetbEntity> relaAccountSuppSetbEntityList = relationDao.selectRelaAccountSuppSet(relaSupplierSetaDTOList.get(0).getId(),
                            null, null, null, null, null);
                    List<RelaRelaAccountSuppSetbDTO> relaAccountSuppSetbDTOList = JSONArray.parseArray(
                            JSON.toJSONString(relaAccountSuppSetbEntityList), RelaRelaAccountSuppSetbDTO.class);
                    relaSupplierSetaDTOList.get(0).setRelaAccountSuppSet(relaAccountSuppSetbDTOList);

                    //交货管理
                    List<RelaRelaDeliSuppSetbEntity> relaDeliSuppSetbEntityList = relationDao.selectRelaDeliSuppSet(relaSupplierSetaDTOList.get(0).getId(),
                            null, null, null, null);
                    List<RelaRelaDeliSuppSetbDTO> relaDeliSuppSetbDTOList = JSONArray.parseArray(
                            JSON.toJSONString(relaDeliSuppSetbEntityList), RelaRelaDeliSuppSetbDTO.class);
                    relaSupplierSetaDTOList.get(0).setRelaDeliSuppSet(relaDeliSuppSetbDTOList);

                    relationDTO.setRelaSupplierSet(relaSupplierSetaDTOList);
                }

            }
        }

        return relationDTO;
    }

    @Override
    public List<RelationDTO> fuzzyQueryRelationList(String keyword, String type, String[] status) {
        List<RelationEntity> relationEntityfuzzyList = relationDao.selectRelationByKeyword(keyword);
        List<RelationEntity> relationEntityList = new ArrayList<>();
        for (RelationEntity relationEntity : relationEntityfuzzyList){
            if (type.equals(RelationConstants.RELA_TYPE_CUST)){
                List<RelaRelaCustSetaEntity> relaCustSetaEntityList = relationDao.selectRelaCustSet(relationEntity.getId(), status,
                        null, null, null);
                if (relaCustSetaEntityList.size() > 0){
                    relationEntity.setRela_cust_set(relaCustSetaEntityList);
                    relationEntityList.add(relationEntity);
                }
            } else if (type.equals(RelationConstants.RELA_TYPE_SUPPLIER)){
                List<RelaRelaSupplierSetaEntity> relaSupplierSetaEntityList = relationDao.selectRelaSupplierSet(relationEntity.getId(),
                        null, status, null, null, null);
                if (relaSupplierSetaEntityList.size() > 0){
                    relationEntity.setRela_supplier_set(relaSupplierSetaEntityList);
                    relationEntityList.add(relationEntity);
                }
            }
        }
        List<RelationDTO> relationDTOList = JSONArray.parseArray(JSON.toJSONString(relationEntityList), RelationDTO.class);
        return relationDTOList;
    }

    /**
     * 根据供应商id获取币别类、税率类集合
     * @param supplierId 供应商ID
     * @return
     */
    @Override
    public Result queryCurrRate(String supplierId) {
        Result result = new Result();
        try {
            OrmParam ormParam = new OrmParam();

            //获取供应商数据集
            ormParam.setWhereExp(ormParam.getEqualXML(NodeConstant.PID, supplierId));
            ormParam.addColumn(NodeConstant.ID).addColumn(RelaRelaSupplierSetaProperty.RELA_TAXRATE);
            List<Map<String, Object>> supplierList = ormService.selectMapList(RelaRelaSupplierSetaEntity.class, ormParam);

            //获取税率类id集合和供应商数据id集合
            List<Object> taxRateIds = new ArrayList<>();
            List<Object> supplierDataIds = new ArrayList<>();
            if (supplierList != null && supplierList.size() > 0) {
                for (Map<String, Object> supplier : supplierList) {
                    if (supplier != null) {
                        if (supplier.get(RelaRelaSupplierSetaProperty.RELA_TAXRATE) != null) {
                            taxRateIds.add(supplier.get(RelaRelaSupplierSetaProperty.RELA_TAXRATE));
                        }
                        if (supplier.get(NodeConstant.ID) != null) {
                            supplierDataIds.add(supplier.get(NodeConstant.ID));
                        }
                    }
                }
            }

            //获取币别类id集合
            List<String> currencyIds = new ArrayList<>();
            if (supplierDataIds.size() > 0) {
                //获取供应商账户管理集
                Date date = new Date();
                ormParam = new OrmParam();
                ormParam.setWhereExp(OrmParam.and(ormParam.getInXML(NodeConstant.PID, supplierDataIds.toArray()),
                        ormParam.getLessThanAndEqualXML(RelaRelaAccountSuppSetbProperty.RELA_ACCOBEG_SUPP, date),
                        ormParam.getGreaterThanAndEqualXML(RelaRelaAccountSuppSetbProperty.RELA_ACCOEND_SUPP, date)));
                ormParam.addColumn(RelaRelaAccountSuppSetbProperty.RELA_ACCOCURR_SUPP);
                List<Map<String, Object>> accounts = ormService.selectMapList(RelaRelaAccountSuppSetbEntity.class, ormParam);
                //获取币别类id集合
                if (accounts != null && accounts.size() > 0) {
                    for (Map<String, Object> account : accounts) {
                        if (account != null) {
                            if (account.get(RelaRelaAccountSuppSetbProperty.RELA_ACCOCURR_SUPP) != null) {
                                currencyIds.add(account.get(RelaRelaAccountSuppSetbProperty.RELA_ACCOCURR_SUPP).toString());
                            }
                        }
                    }
                }
            }

            //获取币别类集合
            List<CurrencyDTO> currencyList = new ArrayList<>();
            if (currencyIds.size() > 0) {
                ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getInXML(NodeConstant.ID, currencyIds.toArray()));
                ormParam.addGroupByColumn(NodeConstant.ID, CurrencyProperty.CURR_CODE, CurrencyProperty.CURR_DESC,
                        CurrencyProperty.CURR_NAME);
                List<CurrencyEntity> list = ormService.selectBeanList(CurrencyEntity.class, ormParam);
                //转DTO
                if(list != null && list.size() > 0) {
                    currencyList = JSONObject.parseArray(JSONObject.toJSONString(list), CurrencyDTO.class);
                }
            }

            //获取税率类集合
            List<TaxrateDTO> taxrateList = new ArrayList<>();
            if (taxRateIds.size() > 0) {
                ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getInXML(NodeConstant.ID, taxRateIds.toArray()));
                ormParam.addGroupByColumn(NodeConstant.ID, TaxrateProperty.TAXR_NAME,
                        TaxrateProperty.TAXR_DETAIL, TaxrateProperty.TAXR_ISDEDUCT);
                List<TaxrateEntity> list = ormService.selectBeanList(TaxrateEntity.class, ormParam);
                //转DTO
                if(list != null && list.size() > 0) {
                    taxrateList = JSONObject.parseArray(JSONObject.toJSONString(list), TaxrateDTO.class);
                }
            }
            //返回结果
            Map<String, List> map = new HashMap<>();
            map.put("taxrateList", taxrateList);
            map.put("currencyList", currencyList);
            result.setData(map);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }

        return result;
    }

}
