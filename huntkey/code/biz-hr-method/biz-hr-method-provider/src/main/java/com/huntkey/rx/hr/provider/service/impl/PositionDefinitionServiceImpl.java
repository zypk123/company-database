package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.*;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.client.InformationClient;
import com.huntkey.rx.hr.provider.dao.PositionDefinitionDao;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.PositionDefinitionService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Created by weijian on 2017/11/13.
 */
@Service
public class PositionDefinitionServiceImpl implements PositionDefinitionService {
    private static Logger logger = LoggerFactory.getLogger(PositionDefinitionServiceImpl.class);

    @Autowired
    PositionDefinitionDao positionDefinitionDao;

    @Autowired
    BizFormService bizFormService;

    @Autowired
    OrmService ormService;

    @Autowired
    InformationClient informationClient;

    /**
     * 查询职位列表
     *
     * @param positionAttributeType 职位属性类别，0:直接职位; 1:间接职位
     * @param positionTypeCodeValue 职类代码区间，用,分割的字符串 不能有空格
     * @param postGradeValue        岗级代码区间，用,分割的字符串 不能有空格
     * @return
     */
    @Override
    public Result queryPositionListService(String positionAttributeType, String positionTypeCodeValue, String postGradeValue) {
        Result result = new Result();
        OrmParam ormParam = new OrmParam();
        //设置返回结果集
        ormParam.addColumn(EdmSysColumn.ID)
                .addColumn(PositionDefinitionConstant.EDM_RPOF_TYPE)
                .addColumn(PositionDefinitionConstant.EDM_RPOF_PROP)
                .addColumn(PositionDefinitionConstant.EDM_RPOF_CODE)
                .addColumn(PositionDefinitionConstant.EDM_RPOF_NAME)
                .addColumn(PositionDefinitionConstant.EDM_RPOF_GRADE)
                .addColumn(PositionDefinitionConstant.EDM_RPOF_DUTY)
                .addColumn(PositionDefinitionConstant.EDM_RPOF_QUAL)
                .addColumn(PositionDefinitionConstant.EDM_RPOF_SEQ);

        List<String> list = new ArrayList<>();
        //设置过滤条件
        if (!StringUtil.isNullOrEmpty(positionAttributeType)) {
            list.add(ormParam.getEqualXML(PositionDefinitionConstant.EDM_RPOF_PROP, positionAttributeType));
        }

        //职类代码区间段 1,2,3 不能有空格
        if (positionTypeCodeValue != null && !"".equals(positionTypeCodeValue)) {
            //界面搜索条件是从01到10, [01,02,03,04,05,06,07,08,09,10]
            list.add(ormParam.getInXML(PositionDefinitionConstant.EDM_RPOF_TYPE, positionTypeCodeValue.split(",")));
        }

        //岗级代码区间 1,2,3 不能有空格
        if (postGradeValue != null && !"".equals(postGradeValue)) {
            list.add(ormParam.getInXML(PositionDefinitionConstant.EDM_RPOF_GRADE, postGradeValue.split(",")));
        }
        ormParam.setWhereExp(OrmParam.and(list));

        try {
            JSONArray dataSetResult = positionDefinitionDao.queryPositionList(ormParam);

            //结果集加工, 按照职位类别进行分组
            Map<String, List<Object>> positionListGroupByRpofType = dataSetResult.parallelStream().map(item ->
            {
                //驼峰式命名转换
                JSONObject newResultObj = new JSONObject();
                newResultObj.put(EdmSysColumn.ID, ((JSONObject) item).getString(EdmSysColumn.ID));
                newResultObj.put(PositionDefinitionConstant.CLASS_RPOF_TYPE, ((JSONObject) item).getString(PositionDefinitionConstant.EDM_RPOF_TYPE));
                newResultObj.put(PositionDefinitionConstant.CLASS_RPOF_PROP, ((JSONObject) item).getString(PositionDefinitionConstant.EDM_RPOF_PROP));
                newResultObj.put(PositionDefinitionConstant.CLASS_RPOF_CODE, ((JSONObject) item).getString(PositionDefinitionConstant.EDM_RPOF_CODE));
                newResultObj.put(PositionDefinitionConstant.CLASS_RPOF_NAME, ((JSONObject) item).getString(PositionDefinitionConstant.EDM_RPOF_NAME));
                newResultObj.put(PositionDefinitionConstant.CLASS_RPOF_GRADE, ((JSONObject) item).getString(PositionDefinitionConstant.EDM_RPOF_GRADE));
                newResultObj.put(PositionDefinitionConstant.CLASS_RPOF_DUTY, ((JSONObject) item).getString(PositionDefinitionConstant.EDM_RPOF_DUTY));
                newResultObj.put(PositionDefinitionConstant.CLASS_RPOF_QUAL, ((JSONObject) item).getString(PositionDefinitionConstant.EDM_RPOF_QUAL));
                newResultObj.put(PositionDefinitionConstant.CLASS_RPOF_SEQ, ((JSONObject) item).getString(PositionDefinitionConstant.EDM_RPOF_SEQ));
                return newResultObj;
            }).collect(Collectors.groupingBy(item -> ((JSONObject) item).getString(PositionDefinitionConstant.CLASS_RPOF_TYPE)));
            result.setData(positionListGroupByRpofType);
        } catch (Exception e) {
            result.setErrMsg(e.getMessage());
            result.setRetCode(Result.RECODE_ERROR);
            return result;
        }
        return result;
    }


    /**
     * 保存职位设置列表
     *
     * @param positionList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result savePositionService(List<PositiondefinitionEntity> positionList) {
        Result result = new Result();
        try {
            List<String> ids = new ArrayList<>();
            for(PositiondefinitionEntity entity : positionList){
                String orderId = (String) ormService.insert(entity);
                ids.add(orderId);
            }
            result.setData(ids);
        } catch (Exception e) {
            if(logger.isDebugEnabled()){
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR , "保存职位设置列表失败，"+e.getMessage());
        }
        return result;
    }

    /**
     * 删除职位定义
     *
     * @param positionCodeList
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result deletePositionService(List<String> positionCodeList) {
        Result result = new Result();
        try {
            OrmParam ormParam = new OrmParam();
            //封装删除职位定义记录id
            ormParam.setWhereExp(ormParam.getInXML(EdmSysColumn.ID , positionCodeList.toArray()));

            int num = ormService.delete(PositiondefinitionEntity.class , ormParam);
            if (num == 0) {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("删除职位定义失败");
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR , "删除职位定义失败");
        }
        return result;
    }

    @Override
    public Result load(String rpofType) {
        Result result = new Result();
        try {
            List<PositionDefinitionDTO> list = positionDefinitionDao.load(rpofType);
            result.setData(list);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            logger.debug(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 职位维护单保存
     * @param postDefinitionOrderDTO
     * @return
     * @throws Exception
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result save(PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception {
        Result result = new Result();
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = this.getSession();
        postDefinitionOrderDTO.setOrdeAdduser(sessionEntity.getEmployeeId());
        postDefinitionOrderDTO.setOrdeDuty(sessionEntity.getPositionId());
        postDefinitionOrderDTO.setEdmdEnte(sessionEntity.getEnterpriseId());
        postDefinitionOrderDTO.setOrdeStatus(OrderStatusConstants.ORDER_STATUS_1);//单据状态:待提
        //数据校验
        JSONObject checData = checkData(postDefinitionOrderDTO);
        if (checData.containsKey("message") && !StringUtil.isNullOrEmpty(checData.getString("message"))) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(checData.getString("message"));
            return result;
        }
        //判断同岗级下是否存在名称重复职位
        if (!checkSameGradeName(postDefinitionOrderDTO)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg("同岗级下存在重复职位，请修改");
            return result;
        }
        //待审单据校验
        String ordeNbr  =checkDsOrde(postDefinitionOrderDTO);
        if (!StringUtil.isNullOrEmpty(ordeNbr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg("当前职位类别下存在待审单据:"+ordeNbr+"，不可提交");
            return result;
        }

        //新增职位生成职位编码
        List<opdePostSetDTO> list = postDefinitionOrderDTO.getOpdePostSet();
        for (int i = 0; i < list.size(); i++) {
            opdePostSetDTO opdePostSetDTO = list.get(i);
            if (StringUtil.isNullOrEmpty(opdePostSetDTO.getOpdePostCode())) {
                String zwbm = getCode("Position");
                opdePostSetDTO.setOpdePostCode(zwbm);
            }
        }
        postDefinitionOrderDTO.setOpdePostSet(list);
        if (StringUtil.isNullOrEmpty(postDefinitionOrderDTO.getId())) {
            postDefinitionOrderDTO.setOrdeDate(new Date());//制单日期
            //insert
            //职位维护单编号生成
            String edmnEncode = "";
            if ("1".equals(postDefinitionOrderDTO.getOpdeEditType())) {//新增职类单
                edmnEncode = NumberConstants.PREFIX_POST_ORDER_ADD;
            } else if ("2".equals(postDefinitionOrderDTO.getOpdeEditType())) {//修改职类单
                edmnEncode = NumberConstants.PREFIX_POST_DEFINITION_ORDER;
            }
            String orderNum = getCode(edmnEncode);
            postDefinitionOrderDTO.setOrdeNbr(orderNum);
            result = positionDefinitionDao.save(postDefinitionOrderDTO);
            String orderInstanceId = "";
            if (result.getRetCode().equals(Result.RECODE_SUCCESS) && result.getData() != null) {
                JSONObject jsonObject = (JSONObject) result.getData();
                orderInstanceId = (String) jsonObject.get("id");
            }
            result.setRetCode(Result.RECODE_SUCCESS);
            JSONObject dataObj = new JSONObject();
            dataObj.put("id", orderInstanceId);
            dataObj.put("ordeNbr", postDefinitionOrderDTO.getOrdeNbr());
            result.setData(dataObj);
        } else {
            result = positionDefinitionDao.update(postDefinitionOrderDTO);
        }
        return result;
    }

    @Override
    public Result submit(PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception {
        Result result = new Result();
        //根据session中的当前员工和岗位赋值单据的制单人和制单岗位
        CurrentSessionEntity sessionEntity = this.getSession();
        postDefinitionOrderDTO.setOrdeAdduser(sessionEntity.getEmployeeId());
        postDefinitionOrderDTO.setOrdeDuty(sessionEntity.getPositionId());
        postDefinitionOrderDTO.setEdmdEnte(sessionEntity.getEnterpriseId());
        result = savesubmit(postDefinitionOrderDTO);
        if (result.getRetCode().equals(Result.RECODE_SUCCESS) && result.getData() != null) {
            JSONObject jsonObject = (JSONObject) result.getData();
            String orderInstanceId = (String) jsonObject.get("id");
            String ordeNbr = (String) jsonObject.get("ordeNbr");//单据编号
            //提交流程方法
            String orderDefId = postDefinitionOrderDTO.getOrdeRodeObj();
            return submitUpdateStatus(orderDefId,orderInstanceId,ordeNbr);
        } else {
            return result;
        }
    }

    @Transactional( rollbackFor = Exception.class)
    public Result savesubmit(PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception {
        Result result = new Result();
        postDefinitionOrderDTO.setOrdeStatus(OrderStatusConstants.ORDER_STATUS_1);//单据状态:待提
        String orderInstanceId = postDefinitionOrderDTO.getId();
        //数据校验
        JSONObject checData = checkData(postDefinitionOrderDTO);//数据校验
        if (checData.containsKey("message") && !StringUtil.isNullOrEmpty(checData.getString("message"))) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg(checData.getString("message"));
            return result;
        }

        //判断同岗级下是否存在名称重复职位
        if (!checkSameGradeName(postDefinitionOrderDTO)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg("同岗级下存在重复职位，请修改");
            return result;
        }
        //待审单据校验
        String ordeNbr  =checkDsOrde(postDefinitionOrderDTO);
        if (!StringUtil.isNullOrEmpty(ordeNbr)) {
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            result.setErrMsg("当前职位类别下存在待审单据:"+ordeNbr+"，不可提交");
            return result;
        }
        //新增职位生成职位编码
        List<opdePostSetDTO> list = postDefinitionOrderDTO.getOpdePostSet();
        for (int i = 0; i < list.size(); i++) {
            opdePostSetDTO opdePostSetDTO = list.get(i);
            if (StringUtil.isNullOrEmpty(opdePostSetDTO.getOpdePostCode())) {
                String zwbm = getCode("Position");
                opdePostSetDTO.setOpdePostCode(zwbm);
            }
        }
        postDefinitionOrderDTO.setOpdePostSet(list);
        //判断单据是否已保存
        if (StringUtil.isNullOrEmpty(orderInstanceId)) {
            //制单日期
            postDefinitionOrderDTO.setOrdeDate(new Date());
            //职位维护单编号生成
            String edmnEncode = "";
            if ("1".equals(postDefinitionOrderDTO.getOpdeEditType())) {
                //新增职类单
                edmnEncode = NumberConstants.PREFIX_POST_ORDER_ADD;
            } else if ("2".equals(postDefinitionOrderDTO.getOpdeEditType())) {
                //修改职类单
                edmnEncode = NumberConstants.PREFIX_POST_DEFINITION_ORDER;
            }
            String orderNum = getCode(edmnEncode);
            postDefinitionOrderDTO.setOrdeNbr(orderNum);
            result = positionDefinitionDao.save(postDefinitionOrderDTO);
        } else {
            result = positionDefinitionDao.update(postDefinitionOrderDTO);
        }
        return result;
    }

    @TxTransaction(rollbackFor = Exception.class)
    @Transactional(readOnly = false, rollbackFor = Exception.class)
    public Result submitUpdateStatus(String orderDefId, String orderInstanceId, String ordeNbr) throws Exception {
        Result result = new Result();
        bizFormService.submitWorkFlow(orderDefId, orderInstanceId);// 提交流程
        updateOrderStatus(orderInstanceId, OrderStatusConstants.ORDER_STATUS_2);// 更新状态：待审
        // 待返回数据
        JSONObject returnData = new JSONObject();
        returnData.put("id", orderInstanceId);
        returnData.put("ordeNbr", ordeNbr);
        result.setData(returnData);
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setErrMsg("提交单据成功！");
        return result;
    }

    @Override
    public Result loadPostDefinitionOrder(String id) {
        Result result = new Result();
        try {
            PostdefinitionorderEntity postdefinitionorderEntity = ormService.load(PostdefinitionorderEntity.class, id);
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getEqualXML("pid",id)).addOrderExpElement(SQLSortEnum.ASC,"opde_seq");
            List<OpdeOpdePostSetaEntity> list = ormService.selectBeanList(OpdeOpdePostSetaEntity.class,ormParam);
            postdefinitionorderEntity.setOpde_post_set(list);
            //转Dto
            PostDefinitionOrderDTO postDefinitionOrderDTO = JSONObject.parseObject(JSONObject.toJSONString(postdefinitionorderEntity), PostDefinitionOrderDTO.class);
            String ordeAddUserName = queryAddUserName(postDefinitionOrderDTO.getOrdeAdduser());
            postDefinitionOrderDTO.setOrdeAdduserName(ordeAddUserName);
            String ordeDutyName = queryOrdeDutyName(postDefinitionOrderDTO.getOrdeDuty());
            postDefinitionOrderDTO.setOrdeDutyName(ordeDutyName);
            String ordeDeptName = queryOrdeDeptName(postDefinitionOrderDTO.getOrdeDept());
            postDefinitionOrderDTO.setOrdeDeptName(ordeDeptName);
            result.setData(postDefinitionOrderDTO);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public Result pass(String orderInstanceId, String handlerType) {
        Result result = new Result();
        logger.info("职位维护单审批通过回调接口：orderInstanceId：" + orderInstanceId + ",handlerType:" + handlerType);
        switch (handlerType) {
            case WFHandlerTypeConstants.PASS: {
                //TODO 单据状态改为 完成；将单据数据写入资源表
                result = passOrder(orderInstanceId);
                break;
            }
            case WFHandlerTypeConstants.REVOKE: {
                //TODO 单据状态改为 待提
                result = updateOrderStatus(orderInstanceId, OrderStatusConstants.ORDER_STATUS_1);
                break;
            }
            case WFHandlerTypeConstants.RETURN_BACK: {
                //TODO 单据状态改为 退回
                result = updateOrderStatus(orderInstanceId, OrderStatusConstants.ORDER_STATUS_6);
                break;
            }
            default: {
                break;
            }
        }
        return result;
    }

    //职位名称重复性校验
    private Boolean checkZwmc(List<opdePostSetDTO> opdePostSetList) {
        List<String> zwmclist = new ArrayList<String>();
        for (opdePostSetDTO opdePostSetDTO : opdePostSetList) {
            zwmclist.add(opdePostSetDTO.getOpdePostName());
        }
        for (opdePostSetDTO opdePostSetDTO : opdePostSetList) {
            int count = 0;
            String zwmc = opdePostSetDTO.getOpdePostName();
            for (int i = 0; i < zwmclist.size(); i++) {
                if (zwmc.equals(zwmclist.get(i))) {
                    count++;
                }
            }
            if (count > 1) {
                return false;
            } else {
                continue;
            }
        }
        return true;
    }

    private String checkDsOrde(PostDefinitionOrderDTO postDefinitionOrderDTO) {
        String ordeNbr ="";
        String opdeType = postDefinitionOrderDTO.getOpdeType();
        String opdeProp = postDefinitionOrderDTO.getOpdeProp();
        OrmParam ormParam = new OrmParam();
        String[] str = new String[]{"2", "3", "4"};
        String whereCondition = OrmParam.and(ormParam.getEqualXML("opde_prop",opdeProp),ormParam.getEqualXML("opde_type", opdeType), ormParam.getInXML(OrderProperty.ORDE_STATUS, str));
        ormParam.setWhereExp(whereCondition);
        try {
            List<PostdefinitionorderEntity> list = ormService.selectBeanList(PostdefinitionorderEntity.class, ormParam);
            if (list.size() > 0) {
                PostdefinitionorderEntity postdefinitionorderEntity = list.get(0);
                ordeNbr = postdefinitionorderEntity.getOrde_nbr();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordeNbr;
    }

    private boolean checkSameGradeName(PostDefinitionOrderDTO postDefinitionOrderDTO) {
        Boolean flag = true;
        //职类
        String opdeType = postDefinitionOrderDTO.getOpdeType();
        List<opdePostSetDTO> list = postDefinitionOrderDTO.getOpdePostSet();
        //判断结果集新值中是否存在同岗级同名称
        for (opdePostSetDTO opdePostSetDTO : list) {
            int size = 0;
            //岗级
            String grade = opdePostSetDTO.getOpdeGrade();
            //岗位名称
            String opdePostName = opdePostSetDTO.getOpdePostName();
            for (opdePostSetDTO opdePostSet : list) {
                if (opdePostSet.getOpdeGrade().equals(grade) && opdePostSet.getOpdePostName().equals(opdePostName)) {
                    size++;
                }
            }
            if (size > 1) {
                flag = false;
                return flag;
            }
        }

        String rpofProp = postDefinitionOrderDTO.getOpdeProp();//职位属性职位属性0:直接职位 1:间接职位
        for (opdePostSetDTO opdePostSetDTO : list) {
            String grade = opdePostSetDTO.getOpdeGrade();//岗级
            String rpofCode = opdePostSetDTO.getOpdePostCode();//岗位编码
            String opdePostName = opdePostSetDTO.getOpdePostName();//岗位名称

            //获取此职类下所有职位名称
            try {
                OrmParam ormParam = new OrmParam();
                String whereCondition = OrmParam.and(ormParam.getEqualXML(PositiondefinitionProperty.RPOF_GRADE, grade), ormParam.getUnequalXML(PositiondefinitionProperty.RPOF_CODE, rpofCode), ormParam.getEqualXML(PositiondefinitionProperty.RPOF_TYPE, opdeType),
                        ormParam.getEqualXML(PositiondefinitionProperty.RPOF_PROP,rpofProp));
                ormParam.setWhereExp(whereCondition);
                List<PositiondefinitionEntity> posList = ormService.selectBeanList(PositiondefinitionEntity.class, ormParam);
                for (PositiondefinitionEntity positiondefinitionEntity : posList) {
                    //是否修改
                    boolean isEdit = false;
                    for (opdePostSetDTO opdePostSet : list) {
                        if (!StringUtil.isNullOrEmpty(opdePostSet.getOpdeNameOld()) && opdePostSet.getOpdeNameOld().equals(positiondefinitionEntity.getRpof_name())) {
                            isEdit = true;
                        }
                    }
                    //新职位名称与旧职位名称重复 且此职位未做修改
                    if (opdePostName.equals(positiondefinitionEntity.getRpof_name()) && !isEdit) {
                        flag = false;
                        return flag;
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return flag;
    }


    private String queryAddUserName(String userId) {
        this.getSession();
        String ordeAddUserName = "";
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EmployeeProperty.REMP_NAME).addColumn(EmployeeProperty.REMP_NO);
        String whereCondition = ormParam.getEqualXML(EdmSysColumn.ID, userId);
        ormParam.setWhereExp(whereCondition);
        try {
            List<Map<String, Object>> list = ormService.selectMapList(EmployeeEntity.class, ormParam);
            JSONObject jsonObject = (JSONObject) JSON.toJSON(list.get(0));
            ordeAddUserName = jsonObject.getString(EmployeeProperty.REMP_NAME) + "/" + jsonObject.getString(EmployeeProperty.REMP_NO);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordeAddUserName;

    }

    private String queryOrdeDutyName(String ordeDuty) {
        String ordeDutyName = "";
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(JobpositionProperty.RPOS_NAME);
        String whereCondition = ormParam.getEqualXML(EdmSysColumn.ID, ordeDuty);
        ormParam.setWhereExp(whereCondition);
        try {
            List<Map<String, Object>> list = ormService.selectMapList(JobpositionEntity.class, ormParam);
            JSONObject jsonObject = (JSONObject) JSON.toJSON(list.get(0));
            ordeDutyName = (String) jsonObject.get(JobpositionProperty.RPOS_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordeDutyName;
    }

    private String queryOrdeDeptName(String ordeDept) {
        String ordeDeptName = "";
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(DepttreeProperty.MDEP_NAME);
        String whereCondition = ormParam.getEqualXML(EdmSysColumn.ID, ordeDept);
        ormParam.setWhereExp(whereCondition);
        try {
            List<Map<String, Object>> list = ormService.selectMapList(DepttreeEntity.class, ormParam);
            JSONObject jsonObject = (JSONObject) JSON.toJSON(list.get(0));
            ordeDeptName = (String) jsonObject.get(DepttreeProperty.MDEP_NAME);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ordeDeptName;
    }


    /**
     * 获取编号(职位编码，单据编号)
     *
     * @param nbrlCode
     * @return
     */
    public String getCode(String nbrlCode) {
        Result codeResult = informationClient.getNumbers(nbrlCode, null);
        if (codeResult.getData() == null) {
            throw new ApplicationException(Result.RECODE_ERROR, codeResult.getErrMsg());
        }
        return codeResult.getData().toString();
    }

    @Transactional(rollbackFor = Exception.class)
    private Result updateOrderStatus(String orderId, String status) {
        Result result = new Result();
        try {
            PostdefinitionorderEntity entity = new PostdefinitionorderEntity();
            entity.setId(orderId);
            entity.setOrde_status(status);
            ormService.updateSelective(entity);
            result.setRetCode(Result.RECODE_SUCCESS);
        } catch (Exception e) {
            if (logger.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR , e.getMessage());
        }
        return result;
    }

    /**
     * 获取session信息
     * @return
     */
    private CurrentSessionEntity getSession(){
        CurrentSessionEntity sessionEntity = null;
        try
        {
            sessionEntity = bizFormService.getCurrentSessionInfo();
        }
        catch (Exception e)
        {
            if (logger.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR , "登录超时，请重新登录系统");
        }

        if(null == sessionEntity)
        {
            throw new ApplicationException(Result.RECODE_ERROR , "登录超时，请重新登录系统");
        }
        return sessionEntity;
    }


    /**
     * 单据通过方法
     *
     * @param orderInstanceId
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    private Result passOrder(String orderInstanceId) {
        Result result = new Result();
        CurrentSessionEntity sessionEntity = this.getSession();
        try {
            //根据ID加载职位维护单
            PostdefinitionorderEntity postdefinitionorderEntity = ormService.load(PostdefinitionorderEntity.class, orderInstanceId);
            postdefinitionorderEntity.setOpde_post_set(postdefinitionorderEntity.loadOpde_post_set());
            //转Dto
            PostDefinitionOrderDTO postDefinitionOrderDTO = JSONObject.parseObject(JSONObject.toJSONString(postdefinitionorderEntity), PostDefinitionOrderDTO.class);
            //单据通过方法:单据状态:通过
            postDefinitionOrderDTO.setOrdeStatus(OrderStatusConstants.ORDER_STATUS_5);
            //更新职位表positiondefinition
            List<opdePostSetDTO> list = postDefinitionOrderDTO.getOpdePostSet();
            //需要update的数据
            JSONArray updateArray = new JSONArray();
            //需要insert的数据
            JSONArray insertArray = new JSONArray();
            for (int i = 0; i < list.size(); i++) {
                opdePostSetDTO opdePostSetDTO = list.get(i);
                if (StringUtil.isNullOrEmpty(opdePostSetDTO.getOpdeNameOld())) {
                    JSONObject jsonObject = new JSONObject();
                    //职位类别
                    jsonObject.put(PositionDefinitionConstant.EDM_RPOF_TYPE, postDefinitionOrderDTO.getOpdeType());
                    //职位属性
                    jsonObject.put(PositionDefinitionConstant.EDM_RPOF_PROP, postDefinitionOrderDTO.getOpdeProp());
                    //职位编码
                    jsonObject.put(PositionDefinitionConstant.EDM_RPOF_CODE, opdePostSetDTO.getOpdePostCode());
                    //职位名称
                    jsonObject.put(PositionDefinitionConstant.EDM_RPOF_NAME, opdePostSetDTO.getOpdePostName());
                    //岗级
                    jsonObject.put(PositionDefinitionConstant.EDM_RPOF_GRADE, opdePostSetDTO.getOpdeGrade());
                    //职位职责
                    jsonObject.put(PositionDefinitionConstant.EDM_RPOF_DUTY, opdePostSetDTO.getOpdePostDuty());
                    //任职资格
                    jsonObject.put(PositionDefinitionConstant.EDM_RPOF_QUAL, opdePostSetDTO.getOpdePostQual());
                    insertArray.add(jsonObject);
                } else {
                    OrmParam ormParam = new OrmParam();
                    ormParam.addColumn(EdmSysColumn.ID).addColumn(PositionDefinitionConstant.EDM_RPOF_CODE);
                    ormParam.setWhereExp(ormParam.getEqualXML(PositionDefinitionConstant.EDM_RPOF_CODE, opdePostSetDTO.getOpdePostCode()));
                    List<Map<String, Object>> jobList = ormService.selectMapList(PositiondefinitionEntity.class, ormParam);
                    Map<String, Object> map = jobList.get(0);
                    JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
                    if (!StringUtil.isNullOrEmpty(jsonObject.get(EdmSysColumn.ID))) {
                        //职位名称
                        jsonObject.put(PositionDefinitionConstant.EDM_RPOF_NAME, opdePostSetDTO.getOpdePostName());
                        //岗级
                        jsonObject.put(PositionDefinitionConstant.EDM_RPOF_GRADE, opdePostSetDTO.getOpdeGrade());
                        //职位职责
                        jsonObject.put(PositionDefinitionConstant.EDM_RPOF_DUTY, opdePostSetDTO.getOpdePostDuty());
                        //任职资格
                        jsonObject.put(PositionDefinitionConstant.EDM_RPOF_QUAL, opdePostSetDTO.getOpdePostQual());
                        updateArray.add(jsonObject);
                    }
                }
            }
            //数据操作
            //保存表单postdefinitionorder
            insertPositiondefinitionEntityList(insertArray , sessionEntity);

            //更新表单postdefinitionorder
            updatePositiondefinitionEntityList(updateArray , sessionEntity);

            result = positionDefinitionDao.update(postDefinitionOrderDTO);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 保存表单
     * @param insertArray
     * @param sessionEntity
     */
    private void insertPositiondefinitionEntityList(JSONArray insertArray , CurrentSessionEntity sessionEntity){
        if (insertArray.size() > 0) {
            try {
                for (int i = 0; i < insertArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) insertArray.get(i);
                    PositiondefinitionEntity positiondefinitionEntity = JSONObject.parseObject(JSONObject.toJSONString(jsonObject), PositiondefinitionEntity.class);
                    positiondefinitionEntity.setEdmd_ente(sessionEntity.getEnterpriseId());
                    positiondefinitionEntity.setCreuser(sessionEntity.getEmployeeId());
                    positiondefinitionEntity.setCretime(new Date());
                    ormService.insertSelective(positiondefinitionEntity);
                }
            } catch (Exception e) {
                e.printStackTrace();
                throw new ApplicationException(Result.RECODE_ERROR , "保存职位信息失败，"+e.getMessage());
            }
        }
    }

    /**
     * 更新表单
     * @param updateArray
     * @param sessionEntity
     */
    private void updatePositiondefinitionEntityList(JSONArray updateArray , CurrentSessionEntity sessionEntity){
        if (updateArray.size() > 0) {
            try {
                for (int i = 0; i < updateArray.size(); i++) {
                    JSONObject jsonObject = (JSONObject) updateArray.get(i);
                    PositiondefinitionEntity positiondefinitionEntity = JSONObject.parseObject(JSONObject.toJSONString(jsonObject), PositiondefinitionEntity.class);
                    positiondefinitionEntity.setModuser(sessionEntity.getEmployeeId());
                    positiondefinitionEntity.setModtime(new Date());
                    ormService.updateSelective(positiondefinitionEntity);
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
                throw new ApplicationException(Result.RECODE_ERROR , "更新职位信息失败，"+e.getMessage());
            }
        }
    }

    private JSONObject checkData(PostDefinitionOrderDTO postDefinitionOrderDTO) {
        JSONObject jsonObject = new JSONObject();
        String ordeRodeObj = postDefinitionOrderDTO.getOrdeRodeObj();// 单据定义类型id
        String ordeAddUser = postDefinitionOrderDTO.getOrdeAdduser();// 制单人
        String ordeDuty = postDefinitionOrderDTO.getOrdeDuty();// 制单岗位
        String ordeDept = postDefinitionOrderDTO.getOrdeDept();// 制单部门
        if (StringUtil.isNullOrEmpty(ordeRodeObj)) {
            jsonObject.put("message", "必须传递参数ordeRodeObj的值作为单据类型定义id保存");
            return jsonObject;
        }
        if (StringUtil.isNullOrEmpty(ordeAddUser)) {
            jsonObject.put("message", "必须传递参数ordeAddUser的值作为制单人id保存");
            return jsonObject;
        }
        if (StringUtil.isNullOrEmpty(ordeDuty)) {
            jsonObject.put("message", "必须传递参数ordeDuty的值作为制单岗位id保存");
            return jsonObject;
        }
        if (StringUtil.isNullOrEmpty(ordeDept)) {
            jsonObject.put("message", "必须传递参数ordeDept的值作为制单部门id保存");
            return jsonObject;
        }
        return jsonObject;
    }

    @Override
    @TxTransaction(rollbackFor = Exception.class)
    public Result audit(JSONObject auditSet) {

        Result result = new Result();

        String auditKey = auditSet.getString(WorkFlowConstants.PARAM_AUDITKEY);
        String formState = auditSet.getString(WorkFlowConstants.PARAM_FORMSTATE);
        String actInstanceId = auditSet.getString(WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
        String opinion = NullUtils.valueOf(auditSet.getString(WorkFlowConstants.PARAM_OPINION));

        if (StringUtil.isNullOrEmpty(auditKey))
        {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_AUDITKEY);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        }

        if (StringUtil.isNullOrEmpty(formState))
        {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_FORMSTATE);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        }

        if (StringUtil.isNullOrEmpty(actInstanceId))
        {
            result.setErrMsg("请传入参数" + WorkFlowConstants.PARAM_ACT_INSTANCE_ID);
            result.setRetCode(Result.RECODE_VALIDATE_ERROR);
            return result;
        }

        // 调用流程
        bizFormService.audit(actInstanceId, opinion, auditKey);

        return result;
    }
}
