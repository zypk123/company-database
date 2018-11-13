package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.JobpositionProperty;
import com.huntkey.rx.edm.entity.DepttreeEntity;
import com.huntkey.rx.edm.entity.JobpositionEntity;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.*;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.common.util.DateDiff;
import com.huntkey.rx.hr.common.util.DateUtils;
import com.huntkey.rx.hr.common.util.JsonUtils;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.dao.DeptTreeDao;
import com.huntkey.rx.hr.provider.service.BaseService;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.DeptTransactionService;
import com.huntkey.rx.hr.provider.service.EmpresignApplyService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.model.OrmParamEx;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 离职申请单service实现类
 *
 * @author zhangyu
 */
@Service
public class EmpresignApplyServiceImpl extends BaseService implements EmpresignApplyService {
    Logger LOGGER = LoggerFactory.getLogger(EmpresignApplyServiceImpl.class);

    @Autowired
    DeptTransactionService deptTransactionService;

    @Autowired
    OrmService ormService;

    @Autowired
    DeptTreeDao deptTreeDao;

    @Autowired
    BizFormService bizFormService;

    String formatStr = DateUtils.DATE_YYYY_MM_DD_HH_MM_SS;

    /**
     * 查询离职申请单列表：如果有与子表关联的查询条件，先查询子表，获取子表的PID，然后再根据主表查询条件和主表id in 子表PID作为查询条件来查询申请单
     * 否则，只根据主表的查询条件来查询主表信息
     *
     * @param deptId    部门
     * @param type        日期类型 申请离职日期cretime  预离职日期oera_app_date  批准离职日期oera_appr_date  实际离职日期oera_real_date
     * @param startTime   开始日期
     * @param endTime     结束日期
     * @param auditStatus 审核状态: 临时 1; 待审 2; 待核 3; 待批 4; 完成 5; 退回 6
     * @param staffInfo   员工查询信息：员工对象Id
     * @return
     */
    @Override
    public Result query(String deptId, String type, String startTime, String endTime, String auditStatus, String staffInfo, int startPage, int rows) throws ParseException
    {
        Result result = new Result();
        JSONArray employeeEmpresignApplyInfoList = new JSONArray();
        try {
            String baseSql = "SELECT apply.id , apply.orde_status , apply.orde_nbr , apply.oera_remark , apply.orde_adduser , apply.orde_dept , apply.orde_duty " +
                    "FROM empresignapply as apply  " +
                    "LEFT JOIN oera_oera_emp_seta as oera ON  (apply.id=oera.pid and oera.is_del=0)  " +
                    "where apply.is_del=0  ";

            String countSQL = "SELECT COUNT(apply.id) as count  " +
                    "FROM empresignapply as apply  " +
                    "LEFT JOIN oera_oera_emp_seta as oera ON  (apply.id=oera.pid and oera.is_del=0)  " +
                    "where apply.is_del=0  ";

            String whereSql = "";

            //部门条件组装
            if(!StringUtil.isNullOrEmpty(deptId)&&!StringUtils.isBlank(deptId))
            {
                String deptIds = "";
                HashMap<String ,String> childMap = new HashMap<>();
                childMap.put(deptId , deptId);
                findAllChildDeptIdById(deptId , childMap);
                childMap.put(deptId,deptId);
                for (Map.Entry entry: childMap.entrySet())
                {
                    deptIds += "'"+entry.getValue()+"',";
                }
                whereSql += " and oera.oera_dept in (" + deptIds.substring(0,deptIds.length()-1) + ")";
            }

            //组装时间条件
            if (!StringUtil.isNullOrEmpty(startTime))
            {
                whereSql += " and "+type+" >= '" + startTime + "'";
            }
            if (!StringUtil.isNullOrEmpty(endTime))
            {
                whereSql += " and "+type+" <= '" + endTime + "'";
            }

            //组装离职申请单表状态条件
            if(!StringUtil.isNullOrEmpty(auditStatus))
            {
                whereSql += " and  apply.orde_status= '" + auditStatus.trim() + "'";
            }

            //组装离职申请单明细表离职员工id条件
            if(!StringUtil.isNullOrEmpty(staffInfo))
            {
                whereSql += " and  oera.oera_emp = '" + staffInfo.trim() + "'";
            }

            if (startPage < 1) {
                startPage = 1;
            }
            String limit = " ORDER BY apply.cretime desc  LIMIT " + (startPage - 1) * rows + "," + rows;

            String querySql = baseSql + whereSql + limit;
            String countQuerySQL = baseSql + whereSql;
            List<Map<String, Object>> list = ormService.getDataBySql(querySql);
            if(list.isEmpty())
            {
                JSONObject obj = new JSONObject();
                obj.put("pageNum", startPage);
                obj.put("pageSize", rows);
                obj.put("total", 0);
                obj.put("list", employeeEmpresignApplyInfoList);
                result.setData(obj);
                return result;
            }

            List<Map<String, Object>> count = ormService.getDataBySql(countQuerySQL);
            int totalSize = count.size();

            List<EmpresignApplyDTO> empresignApplyDTOList = new ArrayList<>();

            if (null != list && list.size() > 0) {
                for (Map<String, Object> map : list) {
                    EmpresignApplyDTO dto = new EmpresignApplyDTO();
                    String id = NullUtils.valueOf(map.get(EdmSysColumn.ID));
                    String ordeStatus = NullUtils.valueOf(map.get(OrderProperty.ORDE_STATUS));
                    String ordeNbr = NullUtils.valueOf(map.get(OrderProperty.ORDE_NBR));
                    String ordeAdduser = NullUtils.valueOf(map.get(OrderProperty.ORDE_ADDUSER));
                    String ordeDept = NullUtils.valueOf(map.get(OrderProperty.ORDE_DEPT));
                    String ordeDuty = NullUtils.valueOf(map.get(OrderProperty.ORDE_DUTY));
                    String oeraRemark = NullUtils.valueOf(map.get(EmpresignapplyProperty.OERA_REMARK));
                    dto.setId(id);
                    dto.setOrdeStatus(ordeStatus);
                    dto.setOrdeNbr(ordeNbr);
                    dto.setOrdeAdduser(ordeAdduser);
                    dto.setOrdeDept(ordeDept);
                    dto.setOrdeDuty(ordeDuty);
                    dto.setOeraRemark(oeraRemark);
                    empresignApplyDTOList.add(dto);
                }
            }

            //2、遍历离职申请单数据-封装单个离职人详细信息
            for (EmpresignApplyDTO empresignApplyDTO : empresignApplyDTOList)
            {
                //封装单个离职人员详细信息
                JSONObject employeeEmpresignApplyInfo = new JSONObject();
                //离职申请单对象Id
                String empresignApplyId = empresignApplyDTO.getId();
                // 离职申请单对象Id
                employeeEmpresignApplyInfo.put(EdmSysColumn.ID, empresignApplyId);
                // 离职申请单单据编码号
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_NBR, empresignApplyDTO.getOrdeNbr());
                //申请单状态
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_STATUS, empresignApplyDTO.getOrdeStatus());
                // 备注
                employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_REMARK, empresignApplyDTO.getOeraRemark());
                //制单人
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_ADDUSER, empresignApplyDTO.getOrdeAdduser());
                //制单部门
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_DEPT, empresignApplyDTO.getOrdeDept());
                //制单岗位
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_DUTY, empresignApplyDTO.getOrdeDuty());
                // 单据定义ID
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_RODE_OBJ, empresignApplyDTO.getOrdeRodeObj());
                //  获取制单人名称、制单部门名称、制单岗位名称
                employeeEmpresignApplyInfo = getExtendInfo(employeeEmpresignApplyInfo , empresignApplyDTO);

                // 查询离职明细--对查询条件进行设置
                OeraOeraEmpSetaEntity deleteOrderDetil = findEmpresignInfo(empresignApplyId , null, type, startTime, endTime, staffInfo);

                //符合【查询时间】和【单据审核状态】、【所属部门】、【员工工号/姓名】的数据进行处理
                if (null != deleteOrderDetil)
                {
                    //组装离职人员的详细信息：员工任岗、部门、教育、学校等信息
                    packageEmployeeDimissionInfo(employeeEmpresignApplyInfo, deleteOrderDetil);
                    Object params = JSONObject.parse(employeeEmpresignApplyInfo.toJSONString());
                    //下划线转驼峰
                    JsonUtils.underLine2Camel(params);
                    //将符合查询条件的离职申请人加入列表
                    employeeEmpresignApplyInfoList.add(params);
                }
            }

            JSONObject obj = new JSONObject();
            obj.put("pageNum", startPage);
            obj.put("pageSize", rows);
            obj.put("total", totalSize);
            obj.put("list", employeeEmpresignApplyInfoList);
            result.setData(obj);
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return result;
    }

    /**
     * 获取制单人名称、制单岗位名称、制单部门名称
     * @param employeeEmpresignApplyInfo
     * @param empresignApplyDTO
     * @return
     */
    private JSONObject getExtendInfo(JSONObject employeeEmpresignApplyInfo , EmpresignApplyDTO empresignApplyDTO)
    {
        //查询条件参数
        Map<String,Object> paramMap = null;
        //返回值参数
        List<String>  columnList = null;

        //申请人姓名(制单人)
        if(!StringUtil.isNullOrEmpty(empresignApplyDTO.getOrdeAdduser()))
        {
            //设置查询参数,必须每次重新new，不然参数不会被清空
            paramMap = new HashMap<>();
            paramMap.put(EdmSysColumn.ID+",equal",empresignApplyDTO.getOrdeAdduser());
            //设置返回参数,必须每次重新new，不然参数不会被清空
            columnList = new ArrayList<>();
            columnList.add(EmployeeProperty.REMP_NAME);
            //查询员工信息
            List<Map<String,Object>> mapList = deptTransactionService.getBaseInfoByConditions(paramMap,columnList,EmployeeEntity.class);
            if(!mapList.isEmpty())
            {
                Map<String,Object> baseMap = mapList.get(0);
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_ADDUSER + "_name",baseMap.get(EmployeeProperty.REMP_NAME));
            }
            else
            {
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_ADDUSER + "_name","");
            }
        }
        else
        {
            employeeEmpresignApplyInfo.put(OrderProperty.ORDE_ADDUSER + "_name","");
        }

        //申请人部门(制单部门)
        if(!StringUtil.isNullOrEmpty(empresignApplyDTO.getOrdeDept()))
        {
            //设置查询参数
            paramMap = new HashMap<>();
            paramMap.put(EdmSysColumn.ID+",equal",empresignApplyDTO.getOrdeDept());
            //设置返回参数
            columnList = new ArrayList<>();
            columnList.add(DepttreeProperty.MDEP_NAME);
            //申请部门名称
            List<Map<String,Object>> mapList= deptTransactionService.getBaseInfoByConditions(paramMap,columnList,DepttreeEntity.class);
            if(!mapList.isEmpty())
            {
                Map<String,Object> baseMap = mapList.get(0);
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_DEPT + "_name",baseMap.get(DepttreeProperty.MDEP_NAME));
            }
            else
            {
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_DEPT + "_name","");
            }
        }
        else
        {
            employeeEmpresignApplyInfo.put(OrderProperty.ORDE_DEPT + "_name","");
        }

        //申请人岗位（制单岗位）
        if(!StringUtil.isNullOrEmpty(empresignApplyDTO.getOrdeDuty()))
        {
            //设置查询参数
            paramMap = new HashMap<>();
            paramMap.put(EdmSysColumn.ID+",equal",empresignApplyDTO.getOrdeDuty());
            //设置返回参数
            columnList = new ArrayList<>();
            columnList.add(JobpositionProperty.RPOS_NAME);
            //申请岗位名称
            List<Map<String,Object>> mapList= deptTransactionService.getBaseInfoByConditions(paramMap,columnList,JobpositionEntity.class);
            if(!mapList.isEmpty())
            {
                Map<String,Object> baseMap = mapList.get(0);
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_DUTY+ "_name",baseMap.get(JobpositionProperty.RPOS_NAME));
            }
            else
            {
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_DUTY + "_name", "");
            }
        }
        else
        {
            employeeEmpresignApplyInfo.put(OrderProperty.ORDE_DUTY + "_name","");
        }
        return employeeEmpresignApplyInfo;
    }

    /**
     * 根据单据id查询单据信息
     * @param orderIdValue
     * @return
     * @throws ParseException
     */
    @Override
    public Result load(String orderIdValue) throws ParseException
    {
        //待返回的结果
        Result result = new Result();
        JSONObject employeeEmpresignApplyInfo = new JSONObject();

        if (StringUtil.isNullOrEmpty(orderIdValue) || StringUtils.isBlank(orderIdValue))
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("加载员工离职申请单失败，单据对象Id不能为空");
            return result;
        }
        else
        {
            //查询所有的离职申请单列表, 审核状态: 临时 1; 待审 2; 待核 3; 待批 4; 完成 5; 退回 6
            List<EmpresignApplyDTO> empresignApplyDTOList = findEmpresignApplyOrderList(orderIdValue, null, 0, 10);

            if (!empresignApplyDTOList.isEmpty())
            {

                //根据单据ID只能查到一条离职申请单
                EmpresignApplyDTO empresignApplyDTO = empresignApplyDTOList.get(0);

                String orderId = empresignApplyDTO.getId();

                // 离职申请单对象Id
                employeeEmpresignApplyInfo.put(EdmSysColumn.ID, orderId);

                // 离职申请单单据编码号
                employeeEmpresignApplyInfo.put(OrderConstants.ORDE_NBR, empresignApplyDTO.getOrdeNbr());
                //申请单状态
                employeeEmpresignApplyInfo.put(OrderConstants.ORDE_STATUS, empresignApplyDTO.getOrdeStatus());
                // 备注
                employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_REMARK, empresignApplyDTO.getOeraRemark());
                //制单人
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_ADDUSER, empresignApplyDTO.getOrdeAdduser());
                //制单部门
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_DEPT, empresignApplyDTO.getOrdeDept());
                //制单岗位
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_DUTY, empresignApplyDTO.getOrdeDuty());
                // 单据定义ID
                employeeEmpresignApplyInfo.put(OrderProperty.ORDE_RODE_OBJ, empresignApplyDTO.getOrdeRodeObj());

                //获取制单人名称、制单部门名称和制单岗位名称
                employeeEmpresignApplyInfo = getExtendInfo(employeeEmpresignApplyInfo , empresignApplyDTO);

                // 查询离职明细, 其他查询条件可以为null
                OeraOeraEmpSetaEntity deleteOrderDetil = findEmpresignInfo(orderId, null,null, null, null, null);

                if (null != deleteOrderDetil)
                {
                    //组装离职人员的详细信息：员工任岗、部门、教育、学校等信息
                    packageEmployeeDimissionInfo(employeeEmpresignApplyInfo, deleteOrderDetil);
                }
            }

            //驼峰转下划线
            String json = JSONObject.toJSONString(employeeEmpresignApplyInfo);
            Object param = JSONObject.parse(json);
            JsonUtils.underLine2Camel(param);
            result.setData(param);
            return result;
        }
    }

    /**
     * 离职申请单保存接口，需要根据离职申请单对象Id是否存在，判断是新增保存，还是修改保存
     * @param jsonObject
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result save(JSONObject jsonObject)
    {
        Result result = new Result();
        try
        {
            //保存必须要先进行登录
            CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
            if(null == sessionEntity)
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("请先登录系统再进行相关操作");
                return result;
            }

            //校验员工是否已经申请过离职
            result = checkIsQuit(jsonObject);
            if(Result.RECODE_ERROR.equals(result.getRetCode()))
            {
                return result;
            }

            JSONObject resultData = new JSONObject();

            //1、获得页面传来的离职申请单数据并校验及转化为下划线格式的entity
            EmpresignapplyEntity entity = jsonObject2Entity(jsonObject);

            //2、验证是否有明细信息
            List<OeraOeraEmpSetaEntity> oeraEmpSetaEntityList = entity.getOera_emp_set();
            if(oeraEmpSetaEntityList.isEmpty())
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("没有找到任何离职申请单明细信息");
                return result;
            }

            OeraOeraEmpSetaEntity  oeraEmpSetaEntity = oeraEmpSetaEntityList.get(0);

            //3、根据session中的当前员工和岗位赋值单据的企业对象
            //企业对象
            entity.setEdmd_ente(sessionEntity.getEnterpriseId());

            //离职申请单Id
            String orderId = "";
            String orderIdValue = entity.getId();

            //离职申请单号
            String orderNbr = "";

            //4、默认是新增操作，只有当存在单据Id后，才做修改操作
            boolean isAddOpt = true;
            if(!StringUtil.isNullOrEmpty(orderIdValue) && !StringUtils.isBlank(orderIdValue))
            {
                isAddOpt = false;
            }

            //5、主表记录的保存或更新（主表记录操作成功后，再保存离职申请信息明细）
            if(!isAddOpt)
            {
                //主表记录的修改操作,只修改备注
                EmpresignapplyEntity updateEntity = new EmpresignapplyEntity();
                //单据ID
                updateEntity.setId(entity.getId());
                //修改时间
                updateEntity.setModtime(new Date());
                //修改人
                updateEntity.setModuser(sessionEntity.getEmployeeId());
                //备注
                updateEntity.setOera_remark(entity.getOera_remark());
                //单据状态修改为待提状态
                updateEntity.setOrde_status(OrderConstants.ORDE_STATUS_1);

                //修改离职申请单主表记录
                ormService.updateSelective(updateEntity);
                orderId = entity.getId();
            }
            else
            {
                //主表记录的新增操作（生成临时单据号，保存成功后会生成正式单据号）
                orderNbr = getOrderNbr(NumberConstants.PREFIX_EMP_RESIGN_APPLY);
                entity.setOrde_nbr(orderNbr);
                entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                entity.setOrde_date(new Date());
                entity.setCretime(new Date());
                entity.setCreuser(sessionEntity.getEmployeeId());
                entity.setEdmd_class(EmpresignApplyConstants.EDM_EMPRESIGNAPPLY);
                //新增离职申请单主表记录
                orderId = (String) ormService.insert(entity);
            }

            //查询单据号（当没有点击保存按钮，直接点击提交按钮的时候，需要做此查询）
            List<EmpresignApplyDTO> empresignApplyDTOList = findEmpresignApplyOrderList(orderId, null, 0, 10);
            if (!empresignApplyDTOList.isEmpty())
            {
                EmpresignApplyDTO empresignApplyDTOResult = empresignApplyDTOList.get(0);
                //查询获得单据的编号
                orderNbr = empresignApplyDTOResult.getOrdeNbr();
            }

            //单据对象Id
            resultData.put("orderId", orderId);

            //单据编号--查询获得
            resultData.put("orderNbr", orderNbr);

            // 保存离职明细
            if (!StringUtil.isNullOrEmpty(orderId))
            {
                String oeraEmpSetId = "";
                //判断离职申请单单据Id是否为空
                if(StringUtil.isNullOrEmpty(orderIdValue)|| StringUtils.isBlank(orderIdValue))
                {
                    //执行新增操作
                    oeraEmpSetaEntity.setPid(orderId);
                    oeraEmpSetaEntity.setClassName(EmpresignApplyConstants.EDM_EMPRESIGNAPPLY_OERA_EMP_SET);
                    oeraEmpSetaEntity.setCretime(new Date());
                    oeraEmpSetaEntity.setCreuser(sessionEntity.getEmployeeId());

                    //保存离职申请单明细表
                    oeraEmpSetId = (String) ormService.insert(oeraEmpSetaEntity);
                }
                else
                {
                    //根据单据Id查找已经存在的离职明细记录对象Id
                    OeraOeraEmpSetaEntity empresignApplyInfoObj = findEmpresignInfo(orderId, null,null, null, null, null);

                    if(null != empresignApplyInfoObj)
                    {
                        //如果离职申请单据对象Id不为空，则表示单据已经存在，执行修改离职明细操作
                        oeraEmpSetaEntity.setId(empresignApplyInfoObj.getId());
                        //修改人
                        oeraEmpSetaEntity.setModuser(sessionEntity.getEmployeeId());
                        //修改时间
                        oeraEmpSetaEntity.setModtime(new Date());
                        //修改操作
                        int count = ormService.updateSelective(oeraEmpSetaEntity);
                        if(count == 0)
                        {
                            result.setRetCode(Result.RECODE_ERROR);
                            result.setErrMsg("修改离职申请单明细失败");
                        }
                        oeraEmpSetId = empresignApplyInfoObj.getId();
                    }
                    else
                    {
                        result.setRetCode(Result.RECODE_ERROR);
                        result.setErrMsg(String.format("根据离职申请单[%s],从离职明细中没有找到有效记录", orderId));
                    }
                }

                resultData.put("oeraEmpSetId", oeraEmpSetId);

                if(StringUtil.isNullOrEmpty(oeraEmpSetId) || StringUtils.isBlank(oeraEmpSetId))
                {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("保存离职申请单-离职明细失败");
                }
                result.setData(resultData);

            }
            else
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("保存离职申请单失败");
            }
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return result;
    }

    /**
     * 组装员工离职信息
     * @param employeeEmpresignApplyInfo
     * @param deleteOrderDetil
     */
    private void packageEmployeeDimissionInfo(JSONObject employeeEmpresignApplyInfo, OeraOeraEmpSetaEntity deleteOrderDetil) throws ParseException
    {
        // 申请离职日期
        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.CRETIME, deleteOrderDetil.getCretime());
        // 预离职日期
        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_APP_DATE, deleteOrderDetil.getOera_app_date());
        // 批准离职日期
        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_APPR_DATE, deleteOrderDetil.getOera_appr_date());
        // 实际离职日期
        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_REAL_DATE, deleteOrderDetil.getOera_real_date());

        // 离职原因
        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_RESON, deleteOrderDetil.getOera_reson());

        // 离职类型
        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_TYPE, deleteOrderDetil.getOera_type());

        // 离职审核意见
        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_AUDIT_DESC, deleteOrderDetil.getOera_audit_desc());

        // 离职人员岗级
        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_PGRADE, deleteOrderDetil.getOera_pgrade());


        // 离职人(员工类数据的id)
        String empresignEmployeeId = deleteOrderDetil.getOera_emp();

        // 离职人(员工类数据的id)
        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_EMP, empresignEmployeeId);

        // 查询离职申请单中的离职人员-员工详细信息
        EmployeeDTO empresignEmployeeDTO = findEmployById(empresignEmployeeId, EdmSysColumn.ID);

        if (null != empresignEmployeeDTO)
        {
            // 员工信息-查询教育背景
            JSONObject eduInfo = findEmployeeStudySet(empresignEmployeeDTO.getId());

            if (null != eduInfo)
            {
                // 学历
                employeeEmpresignApplyInfo.put(EmployeeConstants.REMP_DEGREE, eduInfo.getString(EmployeeConstants.REMP_DEGREE));
                // 专业
                employeeEmpresignApplyInfo.put(EmployeeConstants.REMP_MAJOR, eduInfo.getString(EmployeeConstants.REMP_MAJOR));
                //学校
                employeeEmpresignApplyInfo.put(EmployeeConstants.REMP_RSCH, eduInfo.getString(EmployeeConstants.REMP_RSCH));

                // 查询学校类
                JSONObject schoolInfo = findEmployeeSchoolInfo(eduInfo.getString(EmployeeConstants.REMP_RSCH));

                if (null != schoolInfo)
                {
                    // 毕业院校
                    employeeEmpresignApplyInfo.put(EmployeeConstants.REMP_RSCH+"_name" , schoolInfo.getString(SchoolConstants.RSCH_NAME));
                }
                else
                {
                    employeeEmpresignApplyInfo.put(EmployeeConstants.REMP_RSCH+"_name" , "");
                }
            }

            // 查询员工类-任岗经历（为了计算在岗月）, 岗位名称需要单独查询
            EmpPostSetDTO empPostSetDTO = findEmployeePostSetByOrderId(empresignEmployeeDTO.getId(), DateUtils.parseDateToStr(new Date(), formatStr));

            if (null != empPostSetDTO)
            {
                // 生效日期
                String rempPostBegTime = empPostSetDTO.getRempPostBeg();

                // 失效日期
                String rempPostEndTime = empPostSetDTO.getRempPostEnd();

                // 工号
                employeeEmpresignApplyInfo.put(EmployeeConstants.REMP_NO, empresignEmployeeDTO.getRempNo());
                // 姓名
                employeeEmpresignApplyInfo.put(EmployeeConstants.EMP_NAME, empresignEmployeeDTO.getRempName());
                // 性别
                employeeEmpresignApplyInfo.put(EmployeeConstants.REMP_GENDER, empresignEmployeeDTO.getRempGender());
                // 出生日期
                employeeEmpresignApplyInfo.put(EmployeeConstants.REMP_BIRTH, empresignEmployeeDTO.getRempBirth());
                //岗级
                employeeEmpresignApplyInfo.put(EmployeeConstants.REMP_PGRADE, empresignEmployeeDTO.getRempPgrade());

                // 年龄 年龄=当前日期-出生日期
                if(null == empresignEmployeeDTO.getRempBirth())
                {
                    employeeEmpresignApplyInfo.put(EmpresignApplyConstants.REMP_AGE, "无");
                }
                else
                {
                    employeeEmpresignApplyInfo.put(EmpresignApplyConstants.REMP_AGE, DateUtils.getAgeByBirth(DateUtil.transferDate(Long.parseLong(empresignEmployeeDTO.getRempBirth()), formatStr)));
                }

                // 工龄(年) 工龄=当前日期-首次工作日期
                if(null == empresignEmployeeDTO.getRempWorkDate())
                {
                    employeeEmpresignApplyInfo.put(EmpresignApplyConstants.REMP_WORK_AGE, "无");
                }
                else
                {
                    employeeEmpresignApplyInfo.put(EmpresignApplyConstants.REMP_WORK_AGE, DateUtils.workAge(new Date(), DateUtil.transferDate(Long.parseLong(empresignEmployeeDTO.getRempWorkDate()), formatStr)));
                }

                // 在职(月)  在职月=当前日期-入职日期(到职日)
                if(null == empresignEmployeeDTO.getRempInDate())
                {
                    employeeEmpresignApplyInfo.put(EmpresignApplyConstants.REMP_IN_WORK, "无");
                }
                else
                {
                    employeeEmpresignApplyInfo.put(EmpresignApplyConstants.REMP_IN_WORK, DateUtils.getInwork(new Date(), DateUtil.transferDate(Long.parseLong(empresignEmployeeDTO.getRempInDate()), formatStr)));
                }

                // 员工在岗(月) 在岗月=当前时间-员工岗位经历中任职岗位的有效日期
                String rempPostMonth = getEmployeeInPostMonth(empPostSetDTO, rempPostBegTime, rempPostEndTime);

                employeeEmpresignApplyInfo.put(EmpresignApplyConstants.REMP_IN_JOB, rempPostMonth);

                //到职日
                employeeEmpresignApplyInfo.put(EmpresignApplyConstants.REMP_IN_DATE, empresignEmployeeDTO.getRempInDate());

                //离职日
                employeeEmpresignApplyInfo.put(EmpresignApplyConstants.REMP_LEA_DATE, empresignEmployeeDTO.getRempLeaDate());

                //首次工作时间
                employeeEmpresignApplyInfo.put(EmpresignApplyConstants.REMP_WORK_DATE, empresignEmployeeDTO.getRempWorkDate());

                //直属上级 empPostSetDTO
                // 直属上级 -- 任岗经历中的汇报上级，不同岗位对应的汇报人不一样
                String employeePempHisId = empPostSetDTO.getRempPempHis();

                //查询部门负责的姓名，如果离职人是部门员工非负责人，则直接上级是部门负责人；如果离职人是部门负责人，则直接上级取上级部门的部门负责人
                try
                {
                    EmployeeDTO employeeDTO = findEmployById(employeePempHisId, EdmSysColumn.ID);

                    if(employeeDTO != null)
                    {
                        // 直接上属
                        employeeEmpresignApplyInfo.put(DeptTreeConstants.MDEP_LEADER, employeePempHisId);
                        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.MDEP_LEADER_NAME, employeeDTO.getRempName());
                    }
                }
                catch (ApplicationException ex)
                {
                    //如果上级汇报人不存在，则不去做处理
                }

                //岗位对象Id
                String postId = empresignEmployeeDTO.getRempPost();

                // 查询岗位类--补充离职人的岗位信息字段
                JobPositionDTO jobPositionDTO = findJopPositionById(postId);

                if (null != jobPositionDTO)
                {
                    // 岗位名称
                    employeeEmpresignApplyInfo.put(JobPositionConstants.RPOS_NAME, jobPositionDTO.getRposName());

                    // 查询部门类--补充离职人的部门信息字段
                    String oeraDeptId = deleteOrderDetil.getOera_dept();

                    //根据离职申请单明细关联的部门对象Id，查询部门详细信息
                    DeptTreeInfoDTO deptTreeInfoDTO = findDeptById(oeraDeptId);

                    if (null != deptTreeInfoDTO)
                    {
                        // 离职人关联的部门ID
                        employeeEmpresignApplyInfo.put(EmpresignApplyConstants.OERA_DEPT, oeraDeptId);

                        List<DepttreeEntity> allParentDeptList = new ArrayList<>();

                        Map<String,DepttreeEntity> dtoMap  =new HashMap<>();
                        //递归查询所有上上级部门
                        deptTransactionService.findAllParentDeptList(oeraDeptId, dtoMap);

                        dtoMap.entrySet().forEach(entry -> { allParentDeptList.add(entry.getValue()); });

                        //对list进行排序，按照岗级降序排列
                        allParentDeptList.sort(Comparator.comparing(item -> item.getMdep_grade()));

                        //拼接部门全称
                        String deptTotalName = allParentDeptList.stream().map(item -> item.getMdep_name()).collect(Collectors.joining("."));

                        // 部门全称
                        employeeEmpresignApplyInfo.put(DeptTreeConstants.MDEP_LNAME, deptTotalName + "." + deptTreeInfoDTO.getMdepName());

                        // 部门名称
                        employeeEmpresignApplyInfo.put(DeptTreeConstants.MDEP_NAME, deptTreeInfoDTO.getMdepName());

                        //上级部门对象Id
                        employeeEmpresignApplyInfo.put(DeptTreeConstants.MDEP_PAR, deptTreeInfoDTO.getMdepPar());

                    }
                }
            }
        }
    }

    /**
     * 计算员工在岗月
     * @param empPostSetDTO 任岗记录
     * @param remp_post_beg_time
     * @param remp_post_end_time
     * @return
     */
    private String getEmployeeInPostMonth(EmpPostSetDTO empPostSetDTO, String remp_post_beg_time, String remp_post_end_time)
    {
        if(StringUtil.isNullOrEmpty(remp_post_beg_time) || StringUtil.isNullOrEmpty(remp_post_end_time))
        {
            return "0";
        }

        // 在岗(月)
        String rempPostMonth = "";
        Long beg = null;
        Long end = null;
        Long now = null;
        try
        {
            Date dt1 = DateUtils.parseStrToDate(DateUtil.transferDate(Long.parseLong(remp_post_beg_time),formatStr),formatStr);
            beg = dt1.getTime();
            Date dt2 = DateUtils.parseStrToDate(DateUtil.transferDate(Long.parseLong(remp_post_end_time),formatStr),formatStr);
            end = dt2.getTime();
            Date dt3 = DateUtils.parseStrToDate(DateUtils.parseDateToStr(new Date(),formatStr),formatStr);
            now = dt3.getTime();
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
//            throw new ApplicationException(Result.RECODE_ERROR,"计算员工在岗月错误！");
            return "";
        }

        //TODO 岗位有效 = 任职类型为"任职" 且 岗位类.生效日期<=部门界面的那个日期<岗位类.失效日期
        if ((EmpPostChangeApplyConstant.DUTY_TYPE_0).equals(empPostSetDTO.getRempDtypHis()))
        {
            if((beg <= now && end > now))
            {
                // 当前日期-员工类.任岗经历.生效日期  在岗时间
                try
                {
                    long nowLongValue = System.currentTimeMillis();
                    long workDateLongValue = Long.parseLong(remp_post_beg_time);

                    BigDecimal b1 = new BigDecimal(Long.valueOf((nowLongValue - workDateLongValue)));
                    BigDecimal b2= b1.divide(new BigDecimal(1000*24*60*60),2,BigDecimal.ROUND_CEILING);

                    double value =( b2.divide(new BigDecimal(30),2,BigDecimal.ROUND_CEILING)).doubleValue();

                    //小数点后1位
                    DecimalFormat decimalFormat = new DecimalFormat("0.0");

                    rempPostMonth = String.valueOf(decimalFormat.format(value));
                }
                catch (Exception e)
                {
                    if(LOGGER.isDebugEnabled())
                    {
                        e.printStackTrace();
                    }
                    //throw new ApplicationException(Result.RECODE_ERROR,"计算员工在岗月错误！");
                    return "";
                }
            }
            else
            {
                return "";
            }
        }
        return rempPostMonth;
    }


    /**
     * 离职申请单调用单据提交接口
     * @param empresignApplyDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    @TxTransaction(rollbackFor = Exception.class)
    public Result submit(EmpresignApplyDTO empresignApplyDTO)
    {
        Result result = new Result();

        String orderInstanceId = empresignApplyDTO.getId();
        //提交流程方法,职位维护单
        String orderDefId = empresignApplyDTO.getOrdeRodeObj();

        if(StringUtil.isNullOrEmpty(orderInstanceId) || StringUtil.isNullOrEmpty(orderDefId))
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("单据实例ID或单据定义对象ID为空，不能提交流程");
            return result;
        }

        //判断单据是否已经提交，如果已经提交了，不能再次提交
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID);
        ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.ID,orderInstanceId),ormParam.getEqualXML(OrderConstants.ORDE_STATUS,OrderConstants.ORDE_STATUS_2)));

        try
        {
            List<EmpresignapplyEntity> entityList = ormService.selectBeanList(EmpresignapplyEntity.class , ormParam);

            if(!entityList.isEmpty())
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("该单据已经提交待审核，不能再提交！");
                return result;
            }

            bizFormService.submitWorkFlow(orderDefId, orderInstanceId);

            //提交流程成功了才更新单据状态
            EmpresignapplyEntity entity = new EmpresignapplyEntity();
            entity.setId(orderInstanceId);
            entity.setOrde_status(OrderConstants.ORDE_STATUS_2);
            ormService.updateSelective(entity);
        }
        catch (Exception e)
        {
            if (LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }

        //封装返回值
        JSONObject jo = new JSONObject();
        jo.put("orderId",orderInstanceId);
        jo.put("orderNbr",empresignApplyDTO.getOrdeNbr());
        result.setData(jo);
        return result;
    }

    /**
     * 离职申请单审批通过接口
     * @param id 单据类对象Id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result pass(String id)
    {
        Result result = new Result();
        try
        {
            EmpresignapplyEntity updateEntity = new EmpresignapplyEntity();
            updateEntity.setId(id);
            //修改离职申请单审批状态为5
            updateEntity.setOrde_status(OrderConstants.ORDE_STATUS_5);

            int count = ormService.updateSelective(updateEntity);
            if(count == 0)
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("离职申请单[%s]审批失败", id));
                return result;
            }

            //更新新数据状态为5
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID);
            ormParam.setWhereExp(ormParam.getEqualXML(OrderProperty.ORDE_LAST_VERSION_OBJ,id));
            List<EmpresignapplyEntity> newEntityList = ormService.selectBeanList(EmpresignapplyEntity.class , ormParam);
            if(!newEntityList.isEmpty()){
                EmpresignapplyEntity newEntity = new EmpresignapplyEntity();
                newEntity.setId(newEntityList.get(0).getId());
                newEntity.setModtime(new Date());
                newEntity.setOrde_status(OrderConstants.ORDE_STATUS_5);
                ormService.updateSelective(newEntity);
            }
            
            JSONObject resultObj = new JSONObject();
            resultObj.put(EdmSysColumn.ID, id);
            result.setData(resultObj);
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return result;
    }

    /**
     * 根据岗位对象Id，查询岗位信息
     * @param postId
     * @return
     */
    private JobPositionDTO findJopPositionById(String postId)
    {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID).addColumn(JobpositionProperty.RPOS_NAME);
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID, postId));

        List<JobPositionDTO> empPostSetDTOList = null;
        try
        {
            List<JobpositionEntity> jobpositionEntityList = ormService.selectBeanList(JobpositionEntity.class,ormParam);
            if(jobpositionEntityList.isEmpty())
            {
                return null;
            }
            empPostSetDTOList = JSONArray.parseArray(JSON.toJSONString(jobpositionEntityList),JobPositionDTO.class);
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,"根据岗位对象id，查询岗位信息出错！");
        }
        return empPostSetDTOList.get(0);
    }

    /**
     * 查询员工的教育背景
     * @param employeeId
     * @return
     */
    private JSONObject findEmployeeStudySet(String employeeId)
    {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID).addColumn(EmployeeConstants.REMP_RSCH).addColumn(EmployeeConstants.REMP_MAJOR).addColumn(EmployeeConstants.REMP_DEGREE);
        //学习背景关联员工Id
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID,employeeId));
        List<RempRempStudySetaEntity> rempStudySetaEntityList = null;
        try
        {
            rempStudySetaEntityList = ormService.selectBeanList(RempRempStudySetaEntity.class,ormParam);
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }

        JSONArray studySetJSONArray = new JSONArray();

        rempStudySetaEntityList.forEach(entity -> {
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(entity));
            studySetJSONArray.add(jsonObject);
        });

        if(!studySetJSONArray.isEmpty())
        {
            return (JSONObject)studySetJSONArray.get(0);
        }
        return null;
    }

    /**
     * 查询员工的学校信息
     * @param eduId 教育背景Id
     * @return
     */
    private JSONObject findEmployeeSchoolInfo(String eduId)
    {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID).addColumn(SchoolConstants.RSCH_NAME);
        //学习背景关联员工Id
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID,eduId));
        List<SchoolEntity> schoolEntityList = null;
        try
        {
            schoolEntityList = ormService.selectBeanList(SchoolEntity.class,ormParam);
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }

        JSONArray schoolJSONArray = new JSONArray();

        for (SchoolEntity entity: schoolEntityList)
        {
            SchoolDTO schoolDTO = JSONObject.parseObject(JSONObject.toJSONString(entity),SchoolDTO.class);
            JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(schoolDTO , SerializerFeature.WriteMapNullValue));
            schoolJSONArray.add(jsonObject);
        }

        if(!schoolJSONArray.isEmpty())
        {
            return (JSONObject)schoolJSONArray.get(0);
        }

        return null;
    }

    /**
     * 根据员工类的对象Id，查询员工的任岗经历
     * @param pId
     * @param currentDate 当前时间（YYYY-MM-DD hh:mm:ss），判断属性集记录的有效性
     * @return
     */
    private EmpPostSetDTO findEmployeePostSetByOrderId(String pId, String currentDate)
    {
        EmpPostSetDTO empPostSetDTO = null;
        //employee.remp_post_set
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID)
                .addColumn(EdmSysColumn.PID)
                .addColumn(EmployeeConstants.REMP_DTYP_HIS)
                .addColumn(EmployeeConstants.REMP_POST_HIS)
                .addColumn(EmployeeConstants.REMP_PGRAD_HIS)
                .addColumn(EmployeeConstants.REMP_POST_BEG)
                .addColumn(EmployeeConstants.REMP_POST_END)
                .addColumn(EmployeeConstants.REMP_PEMP_HIS);

        List<String> list = new ArrayList<>();

        //员工对象Id
        list.add(ormParam.getEqualXML(EdmSysColumn.PID, pId));

        //取任职方式=任职（0）的记录
        list.add(ormParam.getEqualXML(EmployeeConstants.REMP_DTYP_HIS, EmpPostChangeApplyConstant.DUTY_TYPE_0));

        //有效记录=生效日期要小于等于当前时间,失效日期大于当前时间
        list.add(ormParam.getLessThanAndEqualXML(EmployeeConstants.REMP_POST_BEG, currentDate));
        list.add(ormParam.getGreaterThanAndEqualXML(EmployeeConstants.REMP_POST_END, currentDate));

        ormParam.setWhereExp(OrmParam.and(list));

        //【有效的集合】员工的任岗经历列表

        try
        {
            List<RempRempPostSetaEntity> entityList = ormService.selectBeanList(RempRempPostSetaEntity.class,ormParam);
            if(!entityList.isEmpty())
            {
                //取最后一笔任职方式=任职 的任岗经历（没失效，取没失效的; 如果失效了，取最后一笔）
                empPostSetDTO = JSONObject.parseObject(JSONObject.toJSONString(entityList.get(0)), EmpPostSetDTO.class);
                return empPostSetDTO;
            }
            else
            {
                //如果没有存在的有效数据，就取失效的记录，最后一条。
                ormParam = new OrmParam();
                ormParam.addColumn(EdmSysColumn.ID)
                        .addColumn(EdmSysColumn.PID)
                        .addColumn(EmployeeConstants.REMP_DTYP_HIS)
                        .addColumn(EmployeeConstants.REMP_POST_HIS)
                        .addColumn(EmployeeConstants.REMP_PGRAD_HIS)
                        .addColumn(EmployeeConstants.REMP_POST_BEG)
                        .addColumn(EmployeeConstants.REMP_POST_END)
                        .addColumn(EmployeeConstants.REMP_PEMP_HIS);

                list = new ArrayList<>();

                //员工对象Id
                list.add(ormParam.getEqualXML(EdmSysColumn.PID, pId));

                //取任职方式=任职（0）的记录
                //TODO 任职方式怎么表示
                list.add(ormParam.getEqualXML(EmployeeConstants.REMP_DTYP_HIS, "0"));

                //设置查询条件，找失效的记录集
                list.add(ormParam.getLessThanAndEqualXML(EmployeeConstants.REMP_POST_END, currentDate));

                //按照失效日期进行降序排序，最最近的一条失效记录
                ormParam.setOrderExp(SQLSortEnum.DESC,EmployeeConstants.REMP_POST_END);

                //【失效的集合】员工的任岗经历列表
                List<RempRempPostSetaEntity> postSetaEntityList = ormService.selectBeanList(RempRempPostSetaEntity.class,ormParam);
                if(!postSetaEntityList.isEmpty())
                {
                    String json = JSONObject.toJSONString(postSetaEntityList.get(0));
                    Object param = JSONObject.parse(json);
                    JsonUtils.underLine2Camel(param);
                    empPostSetDTO = JSONObject.parseObject(JSONObject.toJSONString(param),EmpPostSetDTO.class);
                    //任岗经历存在有效记录, 按照失效日期进行降序排序，最最近的一条失效记录
                    return empPostSetDTO;
                }
            }
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return null;
    }

    /**
     * 根据部门对象Id查询部门对象
     * @param deptId
     * @return
     */
    private DeptTreeInfoDTO findDeptById(String deptId)
    {
        try
        {
            DepttreeEntity depttreeEntity = deptTreeDao.findDeptObj(deptId, EdmSysColumn.ID);
            DeptTreeInfoDTO deptTreeInfoDTO = JSONObject.parseObject(JSON.toJSONString(depttreeEntity), DeptTreeInfoDTO.class);
            return deptTreeInfoDTO;
        }
        catch (ApplicationException ex)
        {
            //没有数据的时候，异常不做处理
        }

        return null;
    }

    /**
     * 根据员工对象Id查询员工对象
     * @param employeeInfo
     * @param type 查询类型: 对象Id NodeConstant.ID; 员工姓名 EmployeeConstants.EMP_NAME; 工号 EmployeeConstants.REMP_NO
     * @return
     */
    private EmployeeDTO findEmployById(String employeeInfo, String type)
    {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID)
                .addColumn(EmployeeConstants.REMP_NO)
                .addColumn(EmployeeConstants.REMP_NAME)
                .addColumn(EmployeeConstants.REMP_GENDER)
                .addColumn(EmployeeConstants.REMP_POST)
                .addColumn(EmployeeConstants.REMP_BIRTH)
                .addColumn(EmployeeConstants.REMP_PGRADE)
                .addColumn(EmployeeConstants.REMP_WORK_DATE)
                .addColumn(EmployeeConstants.REMP_IN_DATE)
                .addColumn(EmployeeConstants.REMP_LEA_DATE)
                .addColumn(EmployeeConstants.REMP_PGRADE);

        //条件封装
        if(EmployeeConstants.EMP_NAME.equals(type))
        {
            //员工姓名
            ormParam.setWhereExp(ormParam.getEqualXML(EmployeeConstants.EMP_NAME, employeeInfo));
        }
        else if(EmployeeConstants.REMP_NO.equals(type))
        {
            //工号
            ormParam.setWhereExp(ormParam.getEqualXML(EmployeeConstants.REMP_NO, employeeInfo));
        }
        else
        {
            if(!StringUtil.isNullOrEmpty(employeeInfo) && !StringUtils.isBlank(employeeInfo))
            {
                //默认按照Id查询
                ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID, employeeInfo));
            }
            else
            {
                return null;
            }
        }

        try
        {
            List<EmployeeEntity>  employeeEntityList = ormService.selectBeanList(EmployeeEntity.class,ormParam);

            if(!employeeEntityList.isEmpty())
            {
                String json = JSONObject.toJSONString(employeeEntityList.get(0));
                EmployeeDTO employeeDTO = JSONObject.parseObject(json, EmployeeDTO.class);
                return employeeDTO;
            }
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return null;
    }

    /**
     * 查询离职申请单-明细列表
     * @param empresignApplyId 离职申请单据Id
     * @param type        日期类型  申请离职日期creTime  预离职日期oera_app_date  批准离职日期oera_appr_date  实际离职日期oera_real_date
     * @param startTime   开始日期
     * @param endTime     结束日期
     * @param staffInfo   员工查询信息：员工对象Id
     * @return
     */
    private OeraOeraEmpSetaEntity findEmpresignInfo(String empresignApplyId, String deptId, String type, String startTime, String endTime, String staffInfo)
    {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID)
                .addColumn(EdmSysColumn.PID)
                .addColumn(EdmSysColumn.CRETIME)
                .addColumn(EdmSysColumn.CREUSER)
                .addColumn(EdmSysColumn.MODTIME)
                .addColumn(EdmSysColumn.MODUSER)
                .addColumn(OeraOeraEmpSetaProperty.OERA_APPR_DATE)
                .addColumn(OeraOeraEmpSetaProperty.OERA_APP_DATE)
                .addColumn(OeraOeraEmpSetaProperty.OERA_AUDIT_DESC)
                .addColumn(OeraOeraEmpSetaProperty.OERA_DEPT)
                .addColumn(OeraOeraEmpSetaProperty.OERA_EMP)
                .addColumn(OeraOeraEmpSetaProperty.OERA_PGRADE)
                .addColumn(OeraOeraEmpSetaProperty.OERA_REAL_DATE)
                .addColumn(OeraOeraEmpSetaProperty.OERA_RESON)
                .addColumn(OeraOeraEmpSetaProperty.OERA_TYPE);

        List<String> list = new ArrayList<>();
        //【时间-查询条件】 添加时间查询条件
        if (!StringUtil.isNullOrEmpty(type))
        {
            // 申请离职日期 就是表单创建日期
            if (StringUtil.isEqual(EmpresignApplyConstants.CRETIME, type))
            {
                list.add(ormParam.getGreaterThanAndEqualXML(EmpresignApplyConstants.CRETIME, startTime));
                list.add(ormParam.getLessThanAndEqualXML(EmpresignApplyConstants.CRETIME, endTime));
            }

            // 预离职日期
            else if (StringUtil.isEqual(EmpresignApplyConstants.OERA_APP_DATE, type))
            {
                list.add(ormParam.getGreaterThanAndEqualXML(EmpresignApplyConstants.OERA_APP_DATE, startTime));
                list.add(ormParam.getLessThanAndEqualXML(EmpresignApplyConstants.OERA_APP_DATE, endTime));
            }

            // 批准离职日期
            else if (StringUtil.isEqual(EmpresignApplyConstants.OERA_APPR_DATE, type))
            {
                list.add(ormParam.getGreaterThanAndEqualXML(EmpresignApplyConstants.OERA_APPR_DATE, startTime));
                list.add(ormParam.getLessThanAndEqualXML(EmpresignApplyConstants.OERA_APPR_DATE, endTime));
            }

            // 实际离职日期
            else if (StringUtil.isEqual(EmpresignApplyConstants.OERA_REAL_DATE, type))
            {
                list.add(ormParam.getGreaterThanAndEqualXML(EmpresignApplyConstants.OERA_REAL_DATE, startTime));
                list.add(ormParam.getLessThanAndEqualXML(EmpresignApplyConstants.OERA_REAL_DATE, endTime));
            }
        }

        //离职申请单对象Id-关联-离职明细类对象PID
        list.add(ormParam.getEqualXML(EdmSysColumn.PID, empresignApplyId));

        //【员工工号/姓名-查询条件】
        if(!StringUtil.isNullOrEmpty(staffInfo) && !StringUtils.isBlank(staffInfo))
        {
            //界面传递的是员工Id
            String employeeId = staffInfo;
            list.add(ormParam.getEqualXML(EmpresignApplyConstants.OERA_EMP, employeeId));
        }

        ormParam.setWhereExp(OrmParam.and(list));

        //3、得到离职明细数据, 由于离职单与离职明细是一对一，此处不需要遍历离职明细了
        try
        {
            List<OeraOeraEmpSetaEntity> oeraOeraEmpSetaEntityList = ormService.selectBeanList(OeraOeraEmpSetaEntity.class,ormParam);
            if(oeraOeraEmpSetaEntityList.isEmpty())
            {
                return null;
            }
            else
            {
                return oeraOeraEmpSetaEntityList.get(0);
            }
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            return null;
        }
    }

    /**
     * 离职申请单审核通过填写审批意见方法:复制原来的申请单数据，作为新数据，再将新数据的id放到原来的数据的上一版单据定义对象字段上；更新申请单明细表
     * @param jsonObject
     * @return
     */
    @Override
    public Result audit(JSONObject jsonObject)
    {
        Result result = new Result();
        //流程处理
        String auditKey = jsonObject.getString(WorkFlowConstants.PARAM_AUDITKEY);
        //单据编辑状态
        String formState = jsonObject.getString(WorkFlowConstants.PARAM_FORMSTATE);
        //流程实例ID
        String actInstanceId = jsonObject.getString(WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
        //审批说明/步骤职责
        String opinion = jsonObject.getString(WorkFlowConstants.PARAM_OPINION);

        JSONObject auditMsg = jsonObject.getJSONObject(WorkFlowConstants.PARAM_ORDER_OBJ);

        //入参校验
        if (StringUtil.isNullOrEmpty(actInstanceId))
        {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
            return result;
        }

        if (StringUtil.isNullOrEmpty(auditKey))
        {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_AUDITKEY);
            return result;
        }

        if (StringUtil.isNullOrEmpty(formState))
        {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_FORMSTATE);
            return result;
        }

        if (StringUtil.isNullOrEmpty(opinion))
        {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_OPINION);
            return result;
        }

        JSONObject jsonData = new JSONObject();

        if (WorkFlowConstants.FormState.EDITABLE.equals(formState)&&WorkFlowConstants.AuditKey.PASS.equals(auditKey))
        {
            jsonData = auditUpdateEmpresignapply(auditMsg);
        }

        //调用审批流程接口
        auditSubmitFlow(actInstanceId,opinion,auditKey);
        result.setErrMsg("提交离职申请单审核意见成功");
        result.setData(jsonData);
        return result;
    }

    /**
     * 更新离职申请单
     * @param auditMsg
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    private JSONObject auditUpdateEmpresignapply(JSONObject auditMsg){
        JSONObject returnData = new JSONObject();
        try
        {
            String orderId = auditMsg.getString(EdmSysColumn.ID);
            String ordeNbr = auditMsg.getString("ordeNbr");

            //step1：操作主表
            //根据原来的数据ID查询原来数据库中的数据,根据id修改原来的数据的上一版单据对象
            EmpresignapplyEntity oldEntity = ormService.load(EmpresignapplyEntity.class,orderId);
            if (null == oldEntity)
            {
                throw new ApplicationException(Result.RECODE_ERROR,"没有找到单据编号为'" + ordeNbr + "'的单据信息");
            }

            //将查询出来的原来的数据作为新数据保存（主要是生成新的id）
            EmpresignapplyEntity newEntity = oldEntity;
            newEntity.setId(null);
            newEntity.setCretime(new Date());
            newEntity.setOrde_last_version_obj(orderId);
            ormService.insert(newEntity);

            //step2：操作子表
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID);
            ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID,orderId));
            List<OeraOeraEmpSetaEntity> oeraEmpSetaEntityList = ormService.selectBeanList(OeraOeraEmpSetaEntity.class, ormParam);
            if(oeraEmpSetaEntityList.isEmpty())
            {
                throw new ApplicationException(Result.RECODE_ERROR,"未找到离职明细信息");
            }

            //离职明细只有一条
            OeraOeraEmpSetaEntity  oeraEmpSetaEntity = oeraEmpSetaEntityList.get(0);
            //离职明细ID
            String oeraEmpSetId = oeraEmpSetaEntity.getId();

            OeraOeraEmpSetaEntity updateOeraEmpSet = new OeraOeraEmpSetaEntity();
            updateOeraEmpSet.setId(oeraEmpSetId);
            //批准离职日
            String oeraApprDate = auditMsg.getString(EmpresignApplyConstants.PARAM_OERA_APPR_DATE);
            if(StringUtil.isNullOrEmpty(oeraApprDate))
            {
                updateOeraEmpSet.setOera_appr_date(DateUtils.parseStrToDate(DateUtils.parseDateToStr(new Date(),DateUtils.DATE_YYYY_MM_DD),DateUtils.DATE_YYYY_MM_DD));
            }
            else
            {
                updateOeraEmpSet.setOera_appr_date(DateUtils.parseStrToDate(oeraApprDate,DateUtils.DATE_YYYY_MM_DD));
            }

            //修改时间
            updateOeraEmpSet.setModtime(new Date());
            //更新明细表
            ormService.updateSelective(updateOeraEmpSet);

            //step4：返回returnData
            returnData.put(EdmSysColumn.ID, orderId);
            returnData.put(EmpresignApplyConstants.ORDERNBR, ordeNbr);
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return returnData;
    }

    /**
     * 调用离职申请单审批流程
     * @param actInstanceId
     * @param opinion
     * @param auditKey
     */
    @TxTransaction(rollbackFor = Exception.class)
    private void auditSubmitFlow(String actInstanceId, String opinion, String auditKey)
    {
        //调用流程
        try
        {
            bizFormService.audit(actInstanceId, opinion, auditKey);
        }
        catch (Exception e)
        {
            throw new ApplicationException(Result.RECODE_ERROR , "调用离职申请单审核流程失败");
        }
    }

    /**
     * 将JSONObject对象转化为Entity，验证通过后返回Entity
     * @param jsonObject
     * @return
     * @throws Exception
     */
    private EmpresignapplyEntity jsonObject2Entity(JSONObject jsonObject) throws Exception
    {
        String json = jsonObject.toJSONString();
        Object params = JSONObject.parse(json);
        // 驼峰转下划线
        JsonUtils.camel2UnderLine(params);
        EmpresignapplyEntity entity =JSONObject.parseObject(JSONObject.toJSONString(params),EmpresignapplyEntity.class);

        // 单据定义类型id
        String ordeRodeObj = entity.getOrde_rode_obj();
        // 制单人
        String ordeAddUser = entity.getOrde_adduser();
        // 制单岗位
        String ordeDuty = entity.getOrde_duty();
        // 制单部门
        String ordeDept =entity.getOrde_dept();

        //入参校验
        if (StringUtil.isNullOrEmpty(ordeRodeObj))
        {
            throw new Exception("必须传递参数ordeRodeObj的值作为单据类型定义id保存");
        }

        if (StringUtil.isNullOrEmpty(ordeAddUser))
        {
            throw new Exception("必须传递参数ordeAddUser的值作为制单人id保存");
        }

        if (StringUtil.isNullOrEmpty(ordeDuty))
        {
            throw new Exception("必须传递参数ordeDuty的值作为制单岗位id保存");
        }

        if (StringUtil.isNullOrEmpty(ordeDept))
        {
            throw new Exception("必须传递参数ordeDept的值作为制单部门id保存");
        }
        return entity;
    }

    /**
     * 查询离职申请单，根据参数判断查询全部还是查询单个对象
     * @param orderIdValue
     * @param auditStatus 审核状态: 临时 1; 待审 2; 待核 3; 待批 4; 完成 5; 退回 6
     * @param startPage
     * @param rows
     * @return
     */
    @Override
    public List<EmpresignApplyDTO> findEmpresignApplyOrderList(String orderIdValue, String auditStatus, int startPage, int rows)
    {
        try
        {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID)
                    .addColumn(OrderProperty.ORDE_STATUS)
                    .addColumn(OrderProperty.ORDE_NBR)
                    .addColumn(EmpresignapplyProperty.OERA_REMARK)
                    .addColumn(OrderProperty.ORDE_ADDUSER)
                    .addColumn(OrderProperty.ORDE_DEPT)
                    .addColumn(OrderProperty.ORDE_DUTY)
                    .addColumn(EmpresignApplyConstants.CRETIME);

            List<String> list = new ArrayList<>();

            //判断是查询所有单据还是根据条件查询
            if(StringUtil.isNullOrEmpty(orderIdValue))
            {
                //查询所有记录，添加分页条件
                ormParam.setPageNo(startPage);
                ormParam.setPageSize(rows);
                //默认按照制单时间查询
                ormParam.setOrderExp(SQLSortEnum.DESC,EmpresignApplyConstants.CRETIME);
            }
            else
            {
                //查询单个对象
                list.add(ormParam.getEqualXML(EdmSysColumn.ID,orderIdValue));
            }

            //【单据状态-查询条件】
            if(!StringUtil.isNullOrEmpty(auditStatus) && !StringUtils.isBlank(auditStatus))
            {
                list.add(ormParam.getEqualXML(OrderProperty.ORDE_STATUS, auditStatus));
            }

            if(!list.isEmpty())
            {
                ormParam.setWhereExp(OrmParam.and(list));
            }

            //1、得到离职申请单的数据
            List<EmpresignapplyEntity> empresignapplyEntityList = new ArrayList<>();

            if(StringUtil.isNullOrEmpty(orderIdValue))
            {
                Pagination pagination = ormService.selectPagedBeanList(EmpresignapplyEntity.class,ormParam);
                empresignapplyEntityList = pagination.getList();
            }
            else
            {
                empresignapplyEntityList = ormService.selectBeanList(EmpresignapplyEntity.class,ormParam);
            }

            JSONArray.parseArray(JSONArray.toJSONString(empresignapplyEntityList));

            JSONArray empresignApplyJSONArray = JSONArray.parseArray(JSONArray.toJSONString(empresignapplyEntityList));

            if(!empresignApplyJSONArray.isEmpty())
            {
                List<EmpresignApplyDTO> empresignApplyDTOList = JSONArray.parseArray(empresignApplyJSONArray.toJSONString(), EmpresignApplyDTO.class);

                return empresignApplyDTOList;
            }
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return null;
    }

    /**
     * 获取单据的字段，并封装到OrmParam对象中
     * @return
     */
    private OrmParam getOrderProperty()
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

    @Override
    public JSONObject findById(String id)
    {
        try
        {
            //构造查询参数对象
            OrmParam ormParam = getOrderProperty();
            ormParam.addColumn(EdmSysColumn.ID)
                    .addColumn(EdmProperty.EDMD_CLASS)
                    .addColumn(EmpresignapplyProperty.OERA_REMARK)
                    .addColumn(EdmSysColumn.CRETIME)
                    .addColumn(EdmSysColumn.CREUSER)
                    .addColumn(EdmSysColumn.MODTIME)
                    .addColumn(EdmSysColumn.MODUSER);
            //添加查询条件
            ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID,id));

            //调用查询接口
            List<EmpresignapplyEntity> entityList = ormService.selectBeanList(EmpresignapplyEntity.class,ormParam);
            JSONArray depts = JSONArray.parseArray(JSONArray.toJSONString(entityList));
            if (depts.isEmpty())
            {
                throw new ApplicationException(Result.RECODE_ERROR,"根据单据Id未找到离职申请单信息");
            }
            return depts.getJSONObject(0);

        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
    }

    /**
     * 查询所有子节点的id
     * @param deptId
     * @param childMap
     */
    private void findAllChildDeptIdById(String deptId,HashMap<String,String> childMap)
    {
        try
        {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID);
            //上级部门对象id
            ormParam.setWhereExp(ormParam.getEqualXML(DepttreeProperty.MDEP_PAR, deptId));
            //查询部门对象
            List<DepttreeEntity> depttreeEntityList = ormService.selectBeanList(DepttreeEntity.class,ormParam);
            if(!depttreeEntityList.isEmpty())
            {
                //递归当前部门下的所有子节点
                for(DepttreeEntity entity : depttreeEntityList)
                {
                    //当前部门对象的id，作为查询子节点的上级部门对象Id
                    String childDeptId = entity.getId();
                    childMap.put(childDeptId ,childDeptId);
                    findAllChildDeptIdById(childDeptId ,childMap);
                }
            }
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
    }

    /**
     * 更新离职单
     * @param empresignapplyEntity
     * @return
     */
    @Override
    public Result update(EmpresignapplyEntity empresignapplyEntity)
    {
        Result result = new Result();
        try
        {
            ormService.updateSelective(empresignapplyEntity);
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return result;
    }

    /**
     * 审核单据
     * @param orderInstanceId
     * @param handlerType
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result approve(String orderInstanceId,String handlerType)
    {
        Result result = new Result();
        EmpresignapplyEntity entity = new EmpresignapplyEntity();
        entity.setId(orderInstanceId);
        LOGGER.info(String.format("离职申请单approve方法：orderInstanceId=%s   handlerType=%s" , orderInstanceId , handlerType));
        switch (handlerType)
        {
            case WFHandlerTypeConstants.PASS:
            {
                //修改单据状态为完成状态
                pass(orderInstanceId);
                break;
            }
            case WFHandlerTypeConstants.REVOKE:
            {
                //修改单据状态为临时单状态
                entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                update(entity);
                break;
            }
            case WFHandlerTypeConstants.RETURN_BACK:
            {
                //修改单据状态为退回状态
                entity.setOrde_status(OrderConstants.ORDE_STATUS_6);
                update(entity);
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
     * 校验员工是否已经申请离职
     * @param jsonObject
     * @return
     */
    private Result checkIsQuit(JSONObject jsonObject)
    {
        Result result = new Result();
        JSONObject oeraEmpSet = jsonObject.getJSONObject("oeraEmpSet");
        String empId = oeraEmpSet.getString("oeraEmp");
        String rempNo = oeraEmpSet.getString("rempNo");
        String rempName = oeraEmpSet.getString("rempName");

        //根据员工ID查询离职申请单明细信息，获得离职申请的ID
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.PID);
        ormParam.setWhereExp(ormParam.getEqualXML(OeraOeraEmpSetaProperty.OERA_EMP , empId));
        try
        {
            List<OeraOeraEmpSetaEntity> oeraEmpSetaEntityList = ormService.selectBeanList(OeraOeraEmpSetaEntity.class , ormParam);
            if(!oeraEmpSetaEntityList.isEmpty())
            {
                OeraOeraEmpSetaEntity entity = oeraEmpSetaEntityList.get(0);
                String[] orderStatus = {OrderConstants.ORDE_STATUS_2 , OrderConstants.ORDE_STATUS_3 , OrderConstants.ORDE_STATUS_4 , OrderConstants.ORDE_STATUS_5};
                ormParam = new OrmParam();
                ormParam.addColumn(EdmSysColumn.ID).addColumn(OrderConstants.ORDE_NBR);
                ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(EdmSysColumn.ID,entity.getPid()),ormParam.getInXML(OrderConstants.ORDE_STATUS,orderStatus)));
                List<EmpresignapplyEntity> empresignapplyEntityList = ormService.selectBeanList(EmpresignapplyEntity.class , ormParam);
                if(!empresignapplyEntityList.isEmpty())
                {
                    result.setErrMsg(String.format("%s/%s已经提交过离职申请单，单号为%s" , rempName , rempNo , empresignapplyEntityList.get(0).getOrde_nbr()));
                    result.setRetCode(result.getRetCode());
                    return  result;
                }
            }
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            return result;
        }
        return result;
    }

}
