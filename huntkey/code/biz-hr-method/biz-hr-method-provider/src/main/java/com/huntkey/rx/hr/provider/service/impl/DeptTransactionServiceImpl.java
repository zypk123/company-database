package com.huntkey.rx.hr.provider.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.codingapi.tx.annotation.TxTransaction;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.EmpPostChangeApplyConstant;
import com.huntkey.rx.hr.common.constants.NumberConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.common.util.DateUtils;
import com.huntkey.rx.hr.common.util.JsonUtils;
import com.huntkey.rx.hr.common.util.NullUtils;
import com.huntkey.rx.hr.provider.dao.DeptTreeDao;
import com.huntkey.rx.hr.provider.service.*;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 部门结构异动类
 * @author Created by liuwens on 2017/11/15.
 */
@Service
public class DeptTransactionServiceImpl extends BaseService implements DeptTransactionService
{
    Logger LOGGER = LoggerFactory.getLogger(DeptTransactionServiceImpl.class);

    @Autowired
    BizFormService bizFormService;
    /**
     * 部门类Dao
     */
    @Autowired
    DeptTreeDao deptTreeDao;

    /**
     * 岗位类service
     */
    @Autowired
    PostService deptPostService;

    @Autowired
    OrmService ormService;

    @Autowired
    DeptStuChangeService deptStuChangeService;

    @Autowired
    PostService postService;

    /**
     * 查询部门调动列表
     * @param odscBeg
     * @param deptIdList
     * @return
     */
    @Override
    public Result queryDeptListService(String odscBeg , List<String> deptIdList)
    {
        Result result = queryDeptList(odscBeg, deptIdList);
        return result;
    }

    /**
     * 加载待调动部门信息列表
     * @param orderIdValue
     * @param orderTypeValue
     * @return
     */
    @Override
    public Result loadMoveDeptOrderService(String orderIdValue, String orderTypeValue)
    {
        //待返回的结果
        Result result = new Result();
        // 待返回的Data数据
        JSONObject returnData = new JSONObject();

        //查询部门结构异动单据类信息deptstuchangeorder
        JSONObject baseInfoData = loadOrderBaseInfo(orderIdValue,orderTypeValue);
        if (baseInfoData.isEmpty())
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(String.format("未找到单据id为%s、单据类型为%s的信息",orderIdValue,orderTypeValue));
            return result;
        }

        //根据单据Id只会查询出一条单据记录,从部门结构异动单类中根据单据Id和单据类型查询单据信息-查询异动列表-查询出异动部门的详细信息deptstuchangeorder.
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID)
                .addColumn(EdmSysColumn.PID)
                .addColumn(OdscOdscChagSetaProperty.ODSC_DEPT_CODE)
                .addColumn(OdscOdscChagSetaProperty.ODSC_DGRADE)
                .addColumn(OdscOdscChagSetaProperty.ODSC_DGRADE_OLD)
                .addColumn(OdscOdscChagSetaProperty.ODSC_FLAG)
                .addColumn(OdscOdscChagSetaProperty.ODSC_FUNCTION)
                .addColumn(OdscOdscChagSetaProperty.ODSC_FUNC_OLD)
                .addColumn(OdscOdscChagSetaProperty.ODSC_LL_OLD)
                .addColumn(OdscOdscChagSetaProperty.ODSC_LL_PNUM)
                .addColumn(OdscOdscChagSetaProperty.ODSC_LVL)
                .addColumn(OdscOdscChagSetaProperty.ODSC_MCOP)
                .addColumn(OdscOdscChagSetaProperty.ODSC_MCOP_OLD)
                .addColumn(OdscOdscChagSetaProperty.ODSC_NAME)
                .addColumn(OdscOdscChagSetaProperty.ODSC_NAME_OLD)
                .addColumn(OdscOdscChagSetaProperty.ODSC_PDEPT)
                .addColumn(OdscOdscChagSetaProperty.ODSC_PDEPT_OLD)
                .addColumn(OdscOdscChagSetaProperty.ODSC_RPAK)
                .addColumn(OdscOdscChagSetaProperty.ODSC_RPAK_OLD)
                .addColumn(OdscOdscChagSetaProperty.ODSC_SNAME)
                .addColumn(OdscOdscChagSetaProperty.ODSC_SNAME_OLD)
                .addColumn(OdscOdscChagSetaProperty.ODSC_TL_OLD)
                .addColumn(OdscOdscChagSetaProperty.ODSC_TL_PNUM);
        //异动列表和单据类通过ID和PID关联pid=orderIdValue
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, orderIdValue));
        List<OdscChagSetDTO> odscChagSetDTOList = findDeptOdscChagSetList(ormParam);

        if(odscChagSetDTOList.isEmpty())
        {
            //没有明细列表
            return result;
        }
        for(OdscChagSetDTO odscChagSetDTO : odscChagSetDTOList)
        {
            //根据部门编码查询出部门对象，获取部门对象Id，才可以统计出待审单据和部门岗位数
            DepttreeEntity depttreeEntity = deptTreeDao.findDeptObj(odscChagSetDTO.getOdscDeptCode(), DepttreeProperty.MDEP_CODE);
            DeptTreeInfoDTO deptTreeInfoDTO = JSONObject.parseObject(JSON.toJSONString(depttreeEntity), DeptTreeInfoDTO.class);

            String deptId = deptTreeInfoDTO.getId();
            //部门对象ID
            odscChagSetDTO.setOdscDeptId(deptId);

            //法人公司设置为简称
            RelationEntity relationEntity = deptTreeDao.findRelationById(odscChagSetDTO.getOdscMcop());

            if (null != relationEntity)
            {
                odscChagSetDTO.setOdscMcopName(relationEntity.getRela_short_name());
            }

            try
            {
                //上级部门名称
                String parentDeptId = odscChagSetDTO.getOdscPdept();
                DeptTreeInfoDTO parentDeptTreeInfoDTO = JSONObject.parseObject(JSON.toJSONString(deptTreeDao.findDeptObj(parentDeptId, EdmSysColumn.ID)), DeptTreeInfoDTO.class);

                //如果当前部门已经是部门根节点，则上级部门为空
                if(null != parentDeptTreeInfoDTO)
                {
                    //新增界面需要的显示字段
                    odscChagSetDTO.setOdscPdeptName(parentDeptTreeInfoDTO.getMdepName());
                }

                String parentDeptOldId = odscChagSetDTO.getOdscPdeptOld();
                DeptTreeInfoDTO parentDeptTreeInfoOldDTO = JSONObject.parseObject(JSON.toJSONString(deptTreeDao.findDeptObj(parentDeptOldId, EdmSysColumn.ID)), DeptTreeInfoDTO.class);
                //如果当前部门已经是部门根节点，则上级部门为空
                if(null != parentDeptTreeInfoOldDTO)
                {
                    //新增界面需要的显示字段
                    odscChagSetDTO.setOdscPdeptNameOld(parentDeptTreeInfoOldDTO.getMdepName());
                }

            }
            catch (ApplicationException ex)
            {
                ex.getStackTrace();
                //当前部门已经是部门根节点，则上级部门为空， 底层会抛出异常，不予处理
            }

            //部门编制：部门编制+下层编制
            //查询部门的岗位数：包含下级
            try
            {
                JSONArray jobPositions = deptPostService.getPostsByDeptId(deptId, "1");
                if(!jobPositions.isEmpty())
                {
                    //设置部门岗位数（包含下级）返回给界面
                    odscChagSetDTO.setMdepPostNum(jobPositions.size());
                }
                else
                {
                    odscChagSetDTO.setMdepPostNum(0);
                }
            }
            catch (Exception ae)
            {
                //数据查询不到，不做处理
            }

            try
            {
                //【部门含下级部门在编数：所属部门在编数=当前部门&&岗位有效&&岗位任职人不为空】
                JSONArray inJobPositions = deptPostService.getPostsEmpByDeptId(deptId, "1");
                if(!inJobPositions.isEmpty())
                {
                    //设置部门含下级部门在编数（包含下级）返回给界面
                    odscChagSetDTO.setMdepInJobPostNum(inJobPositions.size());
                }
                else
                {
                    odscChagSetDTO.setMdepInJobPostNum(0);
                }

                //如果单据类型为注销，需要增加部门待审单据数量和部门岗位数量的统计
                if(DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID.equals(orderTypeValue))
                {
                    //TODO 待审单据的查询
                }
            }
            catch (Exception e)
            {
                //数据查询不到的异常不做处理
            }
        }

        //新添加两个参数:上级部门ID、上级部门名称
        baseInfoData.put("odscPdept",odscChagSetDTOList.get(0).getOdscPdept());
        baseInfoData.put("odscPdeptName",odscChagSetDTOList.get(0).getOdscPdeptName());

        returnData.putAll(baseInfoData);

        //将单据的异动列表保存到返回的data中
        returnData.put("odscChagSet",odscChagSetDTOList);

        result.setData(returnData);

        return result;
    }

    /**
     * 部门注销单据批准通过方法
     * @param orderInstanceId
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result passDeptDeleteOrder(String orderInstanceId)
    {
        Result passDeptDeleteOrderResult = new Result();

        //A:根据异动单据id和单据类型，从部门结构异动单类中查询生效日期
        DeptStuChangeOrderDTO deptStuChangeOrderDTO = getOdsChageSetList(orderInstanceId , DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID);
        if(null == deptStuChangeOrderDTO)
        {
            passDeptDeleteOrderResult.setRetCode(Result.RECODE_ERROR);
            passDeptDeleteOrderResult.setErrMsg("没有找到已提交待审核的异动单");
            return passDeptDeleteOrderResult;
        }

        //部门结构异动单-异动列表属性集
        List<OdscChagSetDTO> odscChagSetDTOList = deptStuChangeOrderDTO.getOdscChagSet();
        if(odscChagSetDTOList.isEmpty())
        {
            passDeptDeleteOrderResult.setErrMsg("没有找到该异动单的明细信息");
            passDeptDeleteOrderResult.setRetCode(Result.RECODE_ERROR);
            return passDeptDeleteOrderResult;
        }

        //有效时间字段值
        Date odscBegValue = DateUtils.parseStrToDate(DateUtils.parseDateToStr(deptStuChangeOrderDTO.getOdscBeg(),DateUtils.DATE_YYYY_MM_DD),DateUtils.DATE_YYYY_MM_DD);

        //B:遍历所有的异动列表，处理异动列表中的部门注销及其子部门注销流程
        for(OdscChagSetDTO odscChagSetDTO : odscChagSetDTOList)
        {
            //部门编码,从单据中只能拿到部门对象的编码，拿不到部门对象的ID
            String odscChagSetDeptCodeValue = odscChagSetDTO.getOdscDeptCode();

            //查询部门对象, 当前注销的部门,其中下级部门没有显示，需要单独查询
            DepttreeEntity odscChagSetDeptTreeInfoDTO = deptTreeDao.findDeptObj(odscChagSetDeptCodeValue, DepttreeProperty.MDEP_CODE);

            if(null == odscChagSetDeptTreeInfoDTO)
            {
                passDeptDeleteOrderResult.setErrMsg(String.format("--> 根据部门结构异动单中的部门编码[%s], 从部门类中没有查询到任何有效数据, 不能执行注销操作", odscChagSetDeptCodeValue));
                passDeptDeleteOrderResult.setRetCode(Result.RECODE_ERROR);
                return passDeptDeleteOrderResult;
            }

            String currentDeleteDeptId = odscChagSetDeptTreeInfoDTO.getId();

            //查询注销部门的所有下级及下下级子部门列表
            List<DepttreeEntity> totalPendingDeleteDeptNodes = new ArrayList<>();

            //部门的下级编制=下级部门编制 + 下层编制累加
            //部门编制
            int mdepTlNum = odscChagSetDeptTreeInfoDTO.getMdep_tl_num();
            //下层编制
            int mdepLlNum = odscChagSetDeptTreeInfoDTO.getMdep_ll_num();

            Map<String,DepttreeEntity> depttreeMap = new HashMap<>();

            //将注销部门也加入到自己子级部门列表，做后续流程处理
            depttreeMap.put(odscChagSetDeptTreeInfoDTO.getId(),odscChagSetDeptTreeInfoDTO);

            //FIXME 递归查询-后期可以修改为根据层级码处理
            findAllChildDeptNodes(currentDeleteDeptId, null, depttreeMap);

            depttreeMap.entrySet().forEach(entity ->{totalPendingDeleteDeptNodes.add(entity.getValue());});

            for(DepttreeEntity deptTreeInfoDTO : totalPendingDeleteDeptNodes)
            {
                String deptId = deptTreeInfoDTO.getId();

                //上级部门对象ID
                String pDeptId = deptTreeInfoDTO.getMdep_par();

                //******************** 判断单据的生效时间：立即生效和未来生效 ***********************************
                //当前服务器时间，可作为部门注销的生效日期
                Date deptOdscBeg =  new Date();
                Date deptOdscBegFormat = DateUtils.parseStrToDate(DateUtils.parseDateToStr(deptOdscBeg , DateUtils.DATE_YYYY_MM_DD) , DateUtils.DATE_YYYY_MM_DD);

                //部门类的生效日期属性，ORM返回的时间全是Long型，需要转成string类型
                Date deptBeg = deptTreeInfoDTO.getMdep_beg();

                /*
                 *   注销以后，相关岗位失效，失效岗位的时候判断，找到岗位上的任职人，把任职人的任刚经历中的这笔任刚经历失效，如果任职方式=‘任职’，把员工类.岗位和岗级清空
                 *   D-1：立即生效:
                 *   部门类：修改部门失效时间为注销生效时间；
                 *   岗位类：关联部门的失效时间修改为部门注销的生效时间；
                 *   员工类：部门类对象修改为上级部门对象Id；员工状态修改为待岗；岗位对象设置为空
                 */
                if(odscBegValue.getTime() <= deptOdscBeg.getTime())
                {
                    try
                    {
                        //1 【部门类】部门失效日期 == 部门注销通过的时间
                        DepttreeEntity depttreeEntity1 =  new DepttreeEntity();
                        depttreeEntity1.setId(deptTreeInfoDTO.getId());
                        depttreeEntity1.setMdep_beg(deptBeg);
                        depttreeEntity1.setMdep_end(deptOdscBegFormat);
                        depttreeEntity1.setModtime(new Date());

                        // 修改部门失效日期为注销通过的生效日期
                        int updateMDeptResult = deptTreeDao.updateDepttree(depttreeEntity1);

                        if(updateMDeptResult == 0)
                        {
                            passDeptDeleteOrderResult.setErrMsg(String.format("--> 部门对象[%s]注销立即生效-修改部门失效时间失败, 不能执行注销操作", deptId));
                            passDeptDeleteOrderResult.setRetCode(Result.RECODE_ERROR);
                            return passDeptDeleteOrderResult;
                        }

                        //2-1 【岗位类】 根据部门对象ID查询岗位类对象jobposition
                        OrmParam ormParam = new OrmParam();
                        ormParam.addColumn(EdmSysColumn.ID)
                                .addColumn(JobpositionProperty.RPOS_EMP)
                                .addColumn(JobpositionProperty.RPOS_DEPT)
                                .addColumn(JobpositionProperty.RPOS_BEG)
                                .addColumn(JobpositionProperty.RPOS_DUTY_TYPE)
                                .addColumn(JobpositionProperty.RPOS_END);
                        String whereExp = OrmParam.and(
                                //查询条件：部门对象Id、有效时间内
                                ormParam.getEqualXML(JobpositionProperty.RPOS_DEPT,deptId),
                                //岗位的生效时间小于等于当前时间
                                ormParam.getLessThanAndEqualXML(JobpositionProperty.RPOS_BEG,deptOdscBeg),
                                //岗位的失效时间大于当前时间
                                ormParam.getGreaterThanXML(JobpositionProperty.RPOS_END,deptOdscBeg));
                        ormParam.setWhereExp(whereExp);
                        List<JobpositionEntity> jobpositionEntityList = ormService.selectBeanList(JobpositionEntity.class,ormParam);

                        //和部门关联的岗位可能会有很多条记录
                        if(!jobpositionEntityList.isEmpty())
                        {
                            List<String> jobpositionIdList = new ArrayList<>();

                            //修改岗位类jobposition的立即失效时间
                            List<String> rzEmpIdList = new ArrayList<>();
                            List<String> employIdList = new ArrayList<>();

                            jobpositionEntityList.forEach(item -> {

                                jobpositionIdList.add((item.getId()));

                                String empId = item.getRpos_emp();
                                //必须要有员工的才放进来
                                if(!StringUtil.isNullOrEmpty(empId))
                                {
                                    employIdList.add(empId);
                                    if(!StringUtil.isNullOrEmpty(item.getRpos_duty_type()) && EmpPostChangeApplyConstant.DUTY_TYPE_0.equals(item.getRpos_duty_type())){
                                        //0-任职
                                        rzEmpIdList.add(empId);
                                    }
                                }
                            });

                            ormParam = new OrmParam();
                            ormParam.setWhereExp(ormParam.getInXML(EdmSysColumn.ID,jobpositionIdList.toArray()));

                            JobpositionEntity jobpositionEntity = new JobpositionEntity();
                            jobpositionEntity.setRpos_end(deptOdscBeg);

                            //2-2修改岗位类的失效日期
                            int num = ormService.updateSelective(jobpositionEntity,ormParam);

                            //修改失效时间失败
                            if (num == 0)
                            {
                                //TODO 此处是否需要做事务回滚
                                passDeptDeleteOrderResult.setErrMsg(String.format("--> 根据部门对象[%s]注销立即生效-修改岗位类的失效时间失败, 不能执行注销操作", deptId));
                                passDeptDeleteOrderResult.setRetCode(Result.RECODE_ERROR);
                                return passDeptDeleteOrderResult;
                            }

                            //2-3 :修改岗位类-变更历史记录 jobposition.rpos_chag_set
                            List<String> chagSetIdList = new ArrayList<>();

                            //查询-批量-所有的岗位类变更记录集，采用in操作包含所有的PID
                            String deptPostIdStr = jobpositionEntityList.stream().map(item -> item.getId()).collect(Collectors.joining(","));
                            JSONArray mdepPostChagSetJSONArray = findValidatorDeptPostFromChagSet(deptPostIdStr, deptOdscBeg);
                            if(mdepPostChagSetJSONArray == null)
                            {
                                String errMsg = String.format("--> 根据部门对象[%s]注销立即生效-岗位类[%s]-查询变更记录集失败，没有任何有效记录, 不能执行注销操作", deptId, deptPostIdStr);
                                throw new ApplicationException(Result.RECODE_ERROR , errMsg);
                            }

                            //设置岗位类-变更历史记录的失效时间 RPOS_END
                            mdepPostChagSetJSONArray.forEach(item -> {chagSetIdList.add(((JSONObject)item).getString(EdmSysColumn.ID));});
                            ormParam = new OrmParam();
                            ormParam.setWhereExp(ormParam.getInXML(EdmSysColumn.ID,chagSetIdList.toArray()));
                            RposRposChagSetaEntity rposRposChagSetaEntity = new RposRposChagSetaEntity();
                            rposRposChagSetaEntity.setRpos_end_his(deptOdscBeg);

                            //开始修改岗位类-变更记录集-批量
                            num = ormService.updateSelective(rposRposChagSetaEntity,ormParam);
                            if (num ==0 )
                            {
                                passDeptDeleteOrderResult.setErrMsg(String.format("--> 根据部门对象[%s]注销立即生效-修改岗位类-变更记录集的失效时间失败, 不能执行注销操作", deptId));
                                passDeptDeleteOrderResult.setRetCode(Result.RECODE_ERROR);
                                return passDeptDeleteOrderResult;
                            }

                            //3 【员工类】修改部门员工类employee：状态为待岗(状态为空字符串""); 岗位对象清空；部门修改为上级部门; 任岗经历失效
                            //首先，如果是任职方式=任职，要清空员工类的 岗位和岗级，更新员工部门的时候，部门=被注销部门的上级部门。法人=被注销部门的上级部门法人公司
                            // 然后，失效员工任岗经历的对应岗位信息

                            //3-1:当岗位中的任职方式=任职时，修改员工类
                            Result updateEmployeeResult = updateEmployee(deptId , pDeptId , rzEmpIdList);

                            if(!Result.RECODE_SUCCESS.equals(updateEmployeeResult.getRetCode())){
                                return updateEmployeeResult;
                            }
                            //3-2 :修改员工类-任岗经历-变更记录
                            //根据任职员工id和部门id查询岗位id，失效变更记录集时，只失效注销部门下有任职方式=任职的员工的任岗经历，该员工在其他部门的任岗经历不失效
                            ormParam = new OrmParam();
                            ormParam.addColumn(EdmSysColumn.ID);
                            ormParam.setWhereExp(OrmParam.and(ormParam.getEqualXML(JobpositionProperty.RPOS_DEPT , deptId) , ormParam.getInXML(JobpositionProperty.RPOS_EMP , rzEmpIdList.toArray())));
                            List<JobpositionEntity> jobpositionList = ormService.selectBeanList(JobpositionEntity.class , ormParam);
                            if(jobpositionList.isEmpty())
                            {
                                LOGGER.info(String.format("--> 根据部门对象[%s]和员工对象%s，没有查询到与任职部门有关的任何岗位信息", deptId , rzEmpIdList.toString()));
                            }
                            else{
                                List<String> postIdList = new ArrayList<>();
                                jobpositionList.forEach(item->{
                                    postIdList.add(item.getId());
                                });
                                Result updateRempPostSetResult = updateRempPostSet(deptId, rzEmpIdList,postIdList, deptOdscBeg);
                                if(!Result.RECODE_SUCCESS.equals(updateRempPostSetResult.getRetCode())){
                                    return updateRempPostSetResult;
                                }
                            }
                        }
                        else{
                            LOGGER.info(String.format("--> 根据部门对象[%s]没有查询到任何岗位信息", deptId));
                        }

                        //4 立即生效时，由于部门注销后，上级部门的部门下级编制发生改变，需要重新设置
                        if(currentDeleteDeptId.equals(deptId))
                        {
                            //只计算当前注销部门的编制，因为当前注销部门的子部门编制已经包含在当前部门下级编制中;被注销的部门的编制暂不更改
                            DepttreeEntity parentDeptInfoJSONObject = deptTreeDao.findDeptObj(pDeptId, EdmSysColumn.ID);
                            DepttreeEntity depttreeEntity2 = new DepttreeEntity();
                            if(parentDeptInfoJSONObject != null)
                            {
                                //计算上级部门的下级编制=原下级编制 - 注销部门的部门编制 - 注销部门的下级编制
                                int parentLlNum = parentDeptInfoJSONObject.getMdep_ll_num();
                                int parentLlNumNewValue = parentLlNum - mdepTlNum - mdepLlNum;

                                ormParam = new OrmParam();
                                ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID,pDeptId));
                                depttreeEntity2.setMdep_ll_num(parentLlNumNewValue);

                                //修改上级部门的下层编制数量
                                int num = ormService.updateSelective(depttreeEntity2, ormParam);
                                if (num == 0)
                                {
                                    passDeptDeleteOrderResult.setErrMsg(String.format("--> 根据部门对象[%s]注销立即生效-修改上级部门类的部门编制失败, 不能执行注销操作", deptId));
                                    passDeptDeleteOrderResult.setRetCode(Result.RECODE_ERROR);
                                    return passDeptDeleteOrderResult;
                                }
                            }
                        }
                    }
                    catch (Exception e)
                    {
                        if (LOGGER.isDebugEnabled())
                        {
                            e.printStackTrace();
                        }
                        throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
                    }
                }

                //D-2：变更记录集：立即生效和未来生效都需要修改-部门类-变更历史记录集中的失效时间---修改封装变更记录集的类参数
                MdepMdepChagSetaEntity medpChagSet = findValidatorDeptFromChagSet(deptId, odscBegValue);

                if(medpChagSet != null)
                {
                    //找到变更记录里面的一条有效记录，将失效日期修改为注销的生效日期
                    medpChagSet.setMdep_end_his(odscBegValue);

                    //部门编制
                    medpChagSet.setMdep_tnum_his(odscChagSetDeptTreeInfoDTO.getMdep_tl_num());

                    int updateMdepChagSetResult = deptTreeDao.updateMdepChagSet(medpChagSet);

                    if(updateMdepChagSetResult == 0)
                    {
                        passDeptDeleteOrderResult.setErrMsg(String.format("--> 根据部门对象[%s]注销-变更记录集-失败, 不能执行注销操作", deptId));
                        passDeptDeleteOrderResult.setRetCode(Result.RECODE_ERROR);
                        return passDeptDeleteOrderResult;
                    }
                    //更新单据状态

                    int count = updateOrderStatusById(orderInstanceId,OrderConstants.ORDE_STATUS_5);
                    if(count == 0)
                    {
                        throw new ApplicationException(Result.RECODE_ERROR,"更新单据状态失败");
                    }
                }
                else
                {
                    throw new ApplicationException(Result.RECODE_ERROR,String.format("部门类-部门变更历史中没有找到有效记录, 查询条件：部门对象[%s],有效时间[%s]", deptId, deptOdscBeg));
                }
            }
        }
        return passDeptDeleteOrderResult;
    }

    /**
     * 修改员工类：如果任职方式=任职，要清空员工类的 岗位和岗级，部门=被注销部门的上级部门，法人=被注销部门的上级部门法人公司
     * @param deptId
     * @param pDeptId
     * @param rzEmpIdList
     * @return
     */
    private Result updateEmployee(String deptId , String pDeptId , List<String> rzEmpIdList){
        Result result = new Result();
        String[] status = {EmployeeConstants.ONJOB,EmployeeConstants.ONPROBATION};
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID);
            //根据部门、员工（任职方式=任职）、员工状态为在职/试用查询任职的员工信息
            String whereExp = OrmParam.and(
                    //关联部门
                    ormParam.getEqualXML(EmployeeProperty.REMP_DEPT, deptId),
                    //关联具体岗位，从岗位类获取任职人的对象Id
                    ormParam.getInXML(EdmSysColumn.ID, rzEmpIdList.toArray()),
                    //员工状态 in (1,2) 在职/试用
                    ormParam.getInXML(EmployeeProperty.REMP_STATUS, status));
            ormParam.setWhereExp(whereExp);
            //查询岗位任职方式为任职的员工
            List<EmployeeEntity> rzEmployeeEntityList = ormService.selectBeanList(EmployeeEntity.class,ormParam);
            //更新员工
            if(!rzEmployeeEntityList.isEmpty()){
                EmployeeEntity updateEmployeeEntity = new EmployeeEntity();
                //岗位对象清空
                updateEmployeeEntity.setRemp_post("");
                //岗级对象清空
                updateEmployeeEntity.setRemp_pgrade("");
                //部门修改为上级部门对象
                updateEmployeeEntity.setRemp_dept(pDeptId);
                try {
                    DepttreeEntity depttreeEntity = ormService.load(DepttreeEntity.class,pDeptId);
                    if(null != depttreeEntity){
                        //修改法人公司为上级部门法人公司
                        updateEmployeeEntity.setRemp_mcop(depttreeEntity.getMdep_mcop());
                    }
                } catch (Exception e) {
                    //出现异常不做任何处理
                }finally {
                    List<String> rzEmployeeIdList = new ArrayList<>();
                    rzEmployeeEntityList.forEach(item ->{ rzEmployeeIdList.add(item.getId()); });
                    ormParam = new OrmParam();
                    ormParam.setWhereExp(ormParam.getInXML(EdmSysColumn.ID,rzEmployeeIdList.toArray()));

                    int num = ormService.updateSelective(updateEmployeeEntity,ormParam);
                    if(num == 0)
                    {
                        //TODO  此处是否需要做事务回滚
                        result.setErrMsg("修改员工类信息失败, 不能执行注销操作");
                        result.setRetCode(Result.RECODE_ERROR);
                        return result;
                    }
                }
            }
        } catch (Exception e) {
            throw new ApplicationException(Result.RECODE_ERROR ,"修改员工信息异常，" + e.getMessage());
        }
        return result;
    }

    /**
     * 修改员工的任岗经历记录集
     * @param deptId
     * @param rzEmpIdList
     * @param deptOdscBeg
     * @return
     */
    private Result updateRempPostSet(String deptId, List<String> rzEmpIdList,List<String> postIdList , Date deptOdscBeg){
        Result result = new Result();
        String[] status = {EmployeeConstants.ONJOB,EmployeeConstants.ONPROBATION};
        try {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID);
            //根据部门、员工（任职方式包括任职、代职、兼职）、员工状态为在职/试用查询员工信息
            String whereExp = OrmParam.and(
                    ormParam.getEqualXML(EmployeeProperty.REMP_DEPT, deptId),
                    //关联具体岗位，从岗位类获取任职人的对象Id
                    ormParam.getInXML(EdmSysColumn.ID, rzEmpIdList.toArray()),
                    //员工状态 in (1,2) 在职/试用
                    ormParam.getInXML(EmployeeProperty.REMP_STATUS, status));
            ormParam.setWhereExp(whereExp);
            List<EmployeeEntity> employeeEntityList = ormService.selectBeanList(EmployeeEntity.class,ormParam);

            if(!employeeEntityList.isEmpty())
            {
                String deptEmployeeIdStr = employeeEntityList.stream().map(item -> item.getId()).collect(Collectors.joining(","));

                JSONArray mdepEmployeeJSONArray = findValidatorDeptEmployeeFromChagSet(deptEmployeeIdStr, postIdList , deptOdscBeg);

                if(null == mdepEmployeeJSONArray || mdepEmployeeJSONArray.isEmpty())
                {
                    result.setErrMsg(String.format("--> 根据部门对象[%s]注销立即生效-员工类[%s]-查询变更记录集失败，没有任何有效记录, 不能执行注销操作", deptId, deptEmployeeIdStr));
                    result.setRetCode(Result.RECODE_ERROR);
                    return result;
                }

                List<String> rposChagSetId = new ArrayList<>();

                //将员工任岗经历的有效记录设置失效
                //状态为待岗:任岗经历（属性集）中没有有效记录
                mdepEmployeeJSONArray.forEach(item -> {
                    String id = ((JSONObject)item).getString(EdmSysColumn.ID);
                    rposChagSetId.add(id);
                });

                ormParam = new OrmParam();
                ormParam.setWhereExp(ormParam.getInXML(EdmSysColumn.ID,rposChagSetId.toArray()));
                RempRempPostSetaEntity rempPostSetaEntity = new RempRempPostSetaEntity();
                rempPostSetaEntity.setRemp_post_end(deptOdscBeg);

                //开始修改员工任岗经历变更记录集-批量
                int num = ormService.updateSelective(rempPostSetaEntity,ormParam);
                if (num == 0)
                {
                    //TODO 此处是否需要做事务回滚
                    result.setErrMsg(String.format("--> 根据部门对象[%s]注销立即生效-修改员工类-变更记录集的失效时间失败, 不能执行注销操作", deptId));
                    result.setRetCode(Result.RECODE_ERROR);
                    return result;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("修改员工类-变更记录集的失效时间出现异常，" + e.getMessage());
            return result;
        }
        return result;
    }

    /**
     * 根据单据 id 更新单据状态
     * @param orderId
     * @param status
     */
    private int updateOrderStatusById(String orderId, String status)
    {
        try
        {
            //更新单据状态
            DeptstuchangeorderEntity updateEntity = new DeptstuchangeorderEntity();
            updateEntity.setId(orderId);
            updateEntity.setOrde_status(status);
            return ormService.updateSelective(updateEntity);
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
     * 部门结构异动单保存接口, 如果单据Id不为null，则表示单据执行保存后的修改操作，反之只做保存操作
     * 该方法调动和注销共用，在JSON对象中，已经存在odsc_type异动单类型
     * @param jsonObject
     * @return 返回部门结构异动单据类的对象Id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result saveDeptTransactionOrderService(JSONObject jsonObject)
    {
        Result result = new Result();
        JSONObject returnData = new JSONObject();
        try
        {
            CurrentSessionEntity sessionEntity = this.getSession();

            //1、获得页面传来的异动单数据并校验及转化为下划线格式的entity
            DeptstuchangeorderEntity entity = jsonObject2Entity(jsonObject);

            //2、验证异动列表数据
            //2.1 验证是否有数据
            List<OdscOdscChagSetaEntity> odscOdscChagSetaEntityList = entity.getOdsc_chag_set();
            if(odscOdscChagSetaEntityList.isEmpty())
            {
                //如果一个部门异动单据内不包含任何部门异动记录，则不能保存
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("没有找到任何部门异动信息");
                return result;
            }

            //2.2 验证数据有效性【被调动或注销的部门编码不能为空、调入部门不能为空】
            for(OdscOdscChagSetaEntity odscChagSetaEntity : odscOdscChagSetaEntityList)
            {
                if(StringUtil.isNullOrEmpty(odscChagSetaEntity.getOdsc_dept_code()))
                {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("部门异动列表数据异常，被调动或被注销部门的部门编码为空");
                    return result;
                }

                if(DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID.equals(entity.getOdsc_type()) && StringUtil.isNullOrEmpty(odscChagSetaEntity.getOdsc_pdept()))
                {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("部门异动列表数据异常，调入部门为空");
                    return result;
                }
            }

            //3、根据session中的当前员工和岗位赋值单据的制单人和制单岗位
            //企业对象
            entity.setEdmd_ente(sessionEntity.getEnterpriseId());
            //单据Id
            String orderId = entity.getId();
            //单据号
            String orderNbr = "";

            //5、默认是新增操作，只有当存在单据Id后，才做修改操作
            boolean isAddOpt = true;
            if(!StringUtil.isNullOrEmpty(orderId) && !StringUtils.isBlank(orderId))
            {
                isAddOpt = false;
            }

            //6、主表记录的保存或更新（主表记录操作成功后，再保存异动列表）
            if(!isAddOpt)
            {
                //主表记录的修改操作
                //【部门结构异动单操作】部门结构异动单类中增加一条部门调动类单据：只修改生效时间、单据类型和备注; 单据状态=1
                DeptstuchangeorderEntity updateEntity = new DeptstuchangeorderEntity();
                //单据ID
                updateEntity.setId(entity.getId());
                //修改时间
                updateEntity.setModtime(new Date());
                //修改人
                updateEntity.setModuser(sessionEntity.getEmployeeId());
                //生效日期
                updateEntity.setOdsc_beg(entity.getOdsc_beg());
                //备注
                updateEntity.setOdsc_remark(entity.getOdsc_remark());
                //修改部门调动单主表记录
                ormService.updateSelective(entity);
            }
            else
            {
                //主表记录的新增操作
                //生成临时单据号，保存成功后会生成正式单据号,根据单据类型返回不同单号
                if(null != entity.getOdsc_type())
                {
                    if(DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID.equals(entity.getOdsc_type()))
                    {
                        //调动,生成单据单号
                        orderNbr = getOrderNbr(NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_MOVE);
                    }
                    else
                    {
                        //注销,生成单据单号
                        orderNbr = getOrderNbr(NumberConstants.PREFIX_DEPT_STU_CHANGE_ORDER_CANCEL);
                    }
                }
                entity.setOrde_nbr(orderNbr);
                entity.setOrde_status(OrderConstants.ORDE_STATUS_1);
                entity.setOrde_date(new Date());
                entity.setCretime(new Date());
                entity.setCreuser(sessionEntity.getEmployeeId());
                entity.setEdmd_class(DeptStuChangeOrderConstants.EDM_DEPT_STU_CHANGE_ORDER);
                //新增部门调动单主表记录
                orderId = (String) ormService.insertSelective(entity);
            }

            //查询单据号（当没有点击保存按钮，直接点击提交按钮的时候，需要做此查询）
            DeptStuChangeOrderDTO deptStuChangeOrderDTOResult = findDeptStuChangeOrderById(orderId , entity.getOdsc_type());
            if(null != deptStuChangeOrderDTOResult)
            {
                orderNbr = deptStuChangeOrderDTOResult.getOrdeNbr();
            }

            //【部门结构异动单-异动列表操作】部门结构异动单类-异动列表（odsc_chag_set）中增加部门调动类单据时：只修改上级部门（调入部门）、上级部门_旧（当前的部门）
            if(!StringUtil.isNullOrEmpty(orderId))
            {
                //一个部门调动单据会包含多个部门异动列表：需要校验调动部门、待调入部门、原上级部门是否存在，避免出现垃圾数据
                for(OdscOdscChagSetaEntity odscOdscChagSetaEntity : odscOdscChagSetaEntityList)
                {
                    Object params = JSONObject.parse(JSON.toJSONString(odscOdscChagSetaEntity));
                    // 下划线转驼峰
                    JsonUtils.underLine2Camel(params);
                    OdscChagSetDTO odscChagSetDTO =JSONObject.parseObject(JSONObject.toJSONString(params),OdscChagSetDTO.class);

                    //【校验参数合法性:存在性】dao层接口如果查询不到结果，直接抛出全局异常
                    String chechResultMessage = checkDeptValidator(odscChagSetDTO);

                    if(null != chechResultMessage)
                    {
                        result.setRetCode(Result.RECODE_ERROR);
                        result.setErrMsg(chechResultMessage);
                        return result;
                    }
                }

                //保存异动列表前，要先删除之前的异动列表信息
                if(!isAddOpt)
                {
                    //待删除的异动列表id集合
                    List<String> delOdscChagSetIdList = new ArrayList<>();

                    //根据异动单单据ID查询异动单列表
                    OrmParam ormParam = new OrmParam();
                    ormParam.addColumn(EdmSysColumn.ID);
                    String whereExp = ormParam.getEqualXML(EdmSysColumn.PID,orderId);
                    ormParam.setWhereExp(whereExp);
                    List<OdscChagSetDTO> odscChagSetDTOList = findDeptOdscChagSetList(ormParam);

                    //由于异动列表可能有部门被删除，先执行清空异动列表所有记录，再做新增
                    if(!odscChagSetDTOList.isEmpty())
                    {
                        for(OdscChagSetDTO odscChagSetDTO : odscChagSetDTOList)
                        {
                            String odscChagSetId = odscChagSetDTO.getId();
                            if(!StringUtil.isNullOrEmpty(odscChagSetId)&& !StringUtils.isBlank(odscChagSetId))
                            {
                                //删除操作需要携带ID
                                delOdscChagSetIdList.add(odscChagSetId);
                            }
                        }
                        //根据id逻辑删除部门异动单明细
                        if(!delOdscChagSetIdList.isEmpty())
                        {
                            deleteOdscChagSetById(delOdscChagSetIdList);
                        }
                    }
                }

                //接收保存异动列表返回的id
                List<String> saveOdscChagSetIdList = new ArrayList<>();

                //保存操作,并返回异动列表最新的id
                for(OdscOdscChagSetaEntity setaEntity:odscOdscChagSetaEntityList)
                {
                    setaEntity.setId(null);
                    setaEntity.setPid(orderId);
                    setaEntity.setClassName(OdscChagSetConstants.EDM_ODSC_CHAG_SET);
                    setaEntity.setCreuser(sessionEntity.getEmployeeId());
                    setaEntity.setCretime(new Date());
                    String odscChagSetId = ormService.insert(setaEntity).toString();
                    saveOdscChagSetIdList.add(odscChagSetId);
                }

                if(saveOdscChagSetIdList.isEmpty())
                {
                    LOGGER.info("保存部门结构异动单据-异动列表-失败");
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("保存部门结构异动单据-异动列表-失败");
                }
                else
                {
                    //返回给前端的操作结果，主要保存-部门结构异动单-异动列表-中的记录ID
                    returnData.put("odscChagSetId", saveOdscChagSetIdList);
                }
            }
            else
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("调动单据保存到-部门结构异动单类-时失败");
                //拼接错误记录
                LOGGER.info(String.format("调动单据保存到-部门结构异动单类-时未成功!"));
                return result;
            }
            returnData.put("deptStuChangeOrderId", orderId);
            returnData.put("deptStuChangeOrderNbr", orderNbr);
            result.setData(returnData);
            result.setErrMsg("保存或更新部门异动单信息成功");
            result.setRetCode(Result.RECODE_SUCCESS);
        }
        catch (Exception e)
        {
            if (LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,"保存或更新部门异动信息出现异常！");
        }
        return result;
    }

    /**
     * 检查部门的合法性：目前只包含是否存在。
     * @param odscChagSetDTO 部门对象Id，部门编码
     * @return
     */
    private String checkDeptValidator(OdscChagSetDTO odscChagSetDTO)
    {
        //部门编码
        String odscDeptCode = odscChagSetDTO.getOdscDeptCode();

        //新调入的上级部门对象Id
        String odscPdeptId = odscChagSetDTO.getOdscPdept();

        //旧的上级部门对象Id
        String odscPdeptOldId = odscChagSetDTO.getOdscPdeptOld();

        //1、按照部门编码查询
        DepttreeEntity queryByCodeResult = null;
        if(!StringUtil.isNullOrEmpty(odscDeptCode))
        {
            queryByCodeResult = deptTreeDao.findDeptObj(odscDeptCode, DeptTreeConstants.MDEP_CODE);

            if(null == queryByCodeResult)
            {
                return String.format("按照部门编码[%s]没有查到任何有效的部门记录", odscDeptCode);
            }
        }

        //1、按照新调入的上级部门对象Id
        DepttreeEntity queryByPdeptIdResult = null;
        if(!StringUtil.isNullOrEmpty(odscPdeptId))
        {
            queryByPdeptIdResult = deptTreeDao.findDeptObj(odscPdeptId, EdmSysColumn.ID);

            if(null == queryByPdeptIdResult)
            {
                return String.format("按照新调入的部门对象Id[%s]没有查到任何有效的部门记录", odscPdeptId);
            }
        }

        //1、按照旧的上级部门对象Id
        DepttreeEntity queryByPdeptOldIdResult = null;
        if(!StringUtil.isNullOrEmpty(odscPdeptOldId))
        {
            queryByPdeptOldIdResult = deptTreeDao.findDeptObj(odscPdeptOldId, EdmSysColumn.ID);

            if(null == queryByPdeptOldIdResult)
            {
                return String.format("按照旧上级部门对象Id[%s]没有查到任何有效的部门记录", odscPdeptOldId);
            }
        }

        return null;
    }

    /**
     * 部门调动单据批准通过方法
     * 字段说明：
     *      部门编制：本部门的编制，不包含下级的
     *      下层编制：指包含所有下下级部门的部门编制+下层编制总和
     *
     * @param orderInstanceId 部门调动异动单id
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Result passMoveOrder(String orderInstanceId)
    {
        Result passMoveOrderResult = new Result();

        //A:根据异动单据id和单据类型，从部门结构异动单类中查询生效日期
        DeptStuChangeOrderDTO deptStuChangeOrderDTO = getOdsChageSetList(orderInstanceId , DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID);

        if(null == deptStuChangeOrderDTO)
        {
            passMoveOrderResult.setRetCode(Result.RECODE_ERROR);
            passMoveOrderResult.setErrMsg("有找到已提交待审核的异动单");
            return passMoveOrderResult;
        }

        List<OdscChagSetDTO> odscChagSetDTOList = deptStuChangeOrderDTO.getOdscChagSet();
        if(odscChagSetDTOList.isEmpty())
        {
            passMoveOrderResult.setErrMsg("没有找到该异动单的明细信息");
            passMoveOrderResult.setRetCode(Result.RECODE_ERROR);
            return passMoveOrderResult;
        }

        //有效时间字段值
        Date odscBegValue = DateUtils.parseStrToDate(DateUtils.parseDateToStr(deptStuChangeOrderDTO.getOdscBeg(),DateUtils.DATE_YYYY_MM_DD),DateUtils.DATE_YYYY_MM_DD);

        //B:取一个异动类-获取上级部门编码-校验上级部门是否已经被注销，如果已经注销，则调动不成功。true 被注销；false未被注销
        OdscChagSetDTO exitOdscChagSetDTO = odscChagSetDTOList.get(0);
        //此处增加上级部门的校验，是否已经被注销:部门变更记录集-中查当前时间的失效日期下是否有有效记录
        String pDeptId = exitOdscChagSetDTO.getOdscPdept();
        //检查上级部门是否已经被注销
        Boolean pDeptIsDel = isDeptDelete(pDeptId, odscBegValue);
        //部门已经被删除
        if(pDeptIsDel)
        {
            passMoveOrderResult.setRetCode(Result.RECODE_ERROR);
            passMoveOrderResult.setErrMsg("调入上级部门已经被注销");
            LOGGER.info(String.format("调入上级部门已经被注销, 上级部门对象ID：%s , 当前时间：%s" , pDeptId , odscBegValue));
            return passMoveOrderResult;
        }

        //C:如果上级部门有效，则开始执行部门及子部门的调入操作流程，需要修改部门类变更集中-<失效日期大于部门调动单的生效日期>的记录的 失效日期为 调动单的生效日期
        for (OdscChagSetDTO odscChagSetDTO : odscChagSetDTOList)
        {
            //新上级部门的对象Id,用来处理层级码和更新部门的父ID的、子部门父ID不用变
            String newParentDeptId = odscChagSetDTO.getOdscPdept();

            //从部门结构异动单中只能拿到部门编码，需要根据部门编码查询部门对象
            String deptCode = odscChagSetDTO.getOdscDeptCode();
            DepttreeEntity depttreeEntity = deptTreeDao.findDeptObj(deptCode, DeptTreeConstants.MDEP_CODE);

            if(null != depttreeEntity)
            {
                //当前部门的对象Id，需要根据此Id，递归获取所有的子部门节点列表
                String deptObjIdValue = depttreeEntity.getId();

                //部门的新层级码-当前部门，下级子部门单独处理
                String deptLvCode = makeDeptLvCode(depttreeEntity.getId(), newParentDeptId);

                //C-0 获取部门的所有子节点，平行存储
                List<DepttreeEntity> depttreeEntityList = new ArrayList<>();

                Map<String,DepttreeEntity>  childMap = new HashMap<>();

                //递归查询所有子部门节点,同时处理子级部门的层级码, 用当前部门的层级码替换下级部门层级码中的上级层级码段

                //FIXME 递归查询-后期可以修改为根据层级码处理
                findAllChildDeptNodes(deptObjIdValue, deptLvCode, childMap);

                childMap.entrySet().forEach(item -> { depttreeEntityList.add(item.getValue()); });

                //当前服务器时间，可作为生效时间
                Date currentTime = DateUtils.parseStrToDate(DateUtils.parseDateToStr(new Date(),DateUtils.DATE_YYYY_MM_DD),DateUtils.DATE_YYYY_MM_DD);

                //需要修改部门类中的上级部门对象Id、层级码
                depttreeEntity.setMdep_par(odscChagSetDTO.getOdscPdept());

                //最新的层级码
                depttreeEntity.setMdep_lvl_code(deptLvCode);

                //将调动部门加入到自己的子部门集合中，待入库操作
                depttreeEntityList.add(depttreeEntity);

                //需要修改所有子部门节点的生效日期和层级编码,
                for(DepttreeEntity item: depttreeEntityList)
                {
                    //生效日期采用批准通过的服务器当前时间
                    item.setMdep_beg(odscBegValue);
                    //所有被调动的部门的失效日期需要修改
                    item.setMdep_end(DateUtil.parseFormatDate("9999-12-31 23:59:59",DateUtils.DATE_YYYY_MM_DD_HH_MM_SS));
                }

                //D-1：立即生效,类属性和变更集都插入记录；
                if(odscBegValue.getTime() <= currentTime.getTime())
                {
                    //入库操作:部门类属性值修改

                    LOGGER.info("--> [部门调动单批准通过-立即生效-修改部门类-入参] " + JSON.toJSONString(depttreeEntityList));

                    int upateDeptResult = deptTreeDao.updateDepttree(depttreeEntityList);

                    if(upateDeptResult == 0)
                    {
                        passMoveOrderResult.setRetCode(Result.RECODE_ERROR);
                        passMoveOrderResult.setErrMsg("更新部门类失败");
                        break;
                    }

                    //D-3:修改新上级部门的下级编制和旧上级部门的下级编制
                    updatePDeptLlNum(depttreeEntity, odscChagSetDTO.getOdscPdeptOld());
                }

                try
                {
                    LOGGER.info("--> [部门调动单批准通过-未来生效-修改部门类-入参] " + JSON.toJSONString(depttreeEntityList));
                    //D-2: 未来生效，只存部门类变更记录集, 将-部门结构异动单类-异动列表类和-部门类-转换为-部门类-变更记录集数据结构，准备入库
                    //D-4：修改变更记录集中的有效记录:需要单独操作，将有效记录的失效时间修改为部门调动单据的生效时间
                    for(DepttreeEntity item : depttreeEntityList)
                    {
                        //部门类对象ID是变更记录类的PID
                        String mdepChagSetPID = item.getId();

                        //变更记录集中的有效记录:有效记录的生效时间小于调动单据的生效时间and有效记录的失效时间大于等于调动单据的生效时间
                        MdepMdepChagSetaEntity validatorMdepChagSetObj = findValidatorDeptFromChagSet(mdepChagSetPID, odscBegValue);

                        if(null != validatorMdepChagSetObj)
                        {
                            //修改有效记录的失效时间为部门调动单据的生效时间
                            MdepMdepChagSetaEntity updateMdepChagSetParam = new MdepMdepChagSetaEntity();
                            updateMdepChagSetParam.setId(validatorMdepChagSetObj.getId());
                            updateMdepChagSetParam.setMdep_end_his(odscBegValue);
                            deptTreeDao.updateMdepChagSet(updateMdepChagSetParam);
                        }
                    }

                    List<MdepMdepChagSetaEntity> deptTreeObjList = transformDeptToJSONObject(depttreeEntityList, odscBegValue);
                    //入库操作：部门类-变更记录集新增记录    需要将变更记录集中的有效数据的失效时间修改为单据的生效日期
                    deptTreeDao.addDeptChagSetRecord(deptTreeObjList);

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
            else
            {
                passMoveOrderResult.setRetCode(Result.RECODE_ERROR);
                passMoveOrderResult.setErrMsg(String.format("根据部门编码[%s]没有查询到有效的部门信息", deptCode));
                break;
            }
        }

        //更新单据状态
        updateOrderStatusById(orderInstanceId,OrderConstants.ORDE_STATUS_5);
        return passMoveOrderResult;
    }

    /**
     * 根据部门结构异动单据类对象Id和单据类型查询：单据有效时间和异动列表deptstuchangeorder
     * @param deptStuChangeOrderIdValue
     * @return
     */
    public DeptStuChangeOrderDTO getOdsChageSetList(String deptStuChangeOrderIdValue , String odscType)
    {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(DeptstuchangeorderProperty.ODSC_TYPE)
                .addColumn(DeptstuchangeorderProperty.ODSC_BEG)
                .addColumn(OrderProperty.ORDE_STATUS);
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID,deptStuChangeOrderIdValue));
        //查询部门结构异动单
        List<DeptStuChangeOrderDTO> deptStuChangeOrderDTOList = findDeptStuChangeOrderList(ormParam , odscType);
        //根据异动单据ID和单据类型，只会有一条记录
        if(deptStuChangeOrderDTOList.isEmpty())
        {
            return null;
        }

        //部门结构异动单-获取有效时间字段值
        DeptStuChangeOrderDTO deptStuChangeOrderDTO = deptStuChangeOrderDTOList.get(0);

        //查询-部门结构异动单类-异动列表-获取部门信息：部门编号，上级部门等
        ormParam = new OrmParam();
        ormParam.addColumn(OdscOdscChagSetaProperty.ODSC_DEPT_CODE)
                .addColumn(OdscOdscChagSetaProperty.ODSC_PDEPT)
                .addColumn(OdscOdscChagSetaProperty.ODSC_PDEPT_OLD)
                .addColumn(EdmSysColumn.ID);
        //通过PID关联,
        String whereSql = ormParam.getEqualXML(EdmSysColumn.PID,deptStuChangeOrderIdValue);
        ormParam.setWhereExp(whereSql);
        //部门结构异动单类-异动列表-会有多条部门异动记录
        List<OdscChagSetDTO> odscChagSetDTOList = findDeptOdscChagSetList(ormParam);
        deptStuChangeOrderDTO.setOdscChagSet(odscChagSetDTOList);
        return deptStuChangeOrderDTO;
    }

    /**
     * 根据父级部门ID获取所有的父级部门列表，平行存储
     * @param mdepPar
     * @return
     */
    private List<DepttreeEntity> getAllParentDept(String mdepPar)
    {
        List<DepttreeEntity> allParentDeptEntityList = new ArrayList<>();
        Map<String,DepttreeEntity> parentDeptMap = new HashMap<>();

        //递归查询所有的上级部门，平行存放在list中
        //FIXME 递归查询-后期可以修改为根据层级码处理
        findAllParentDeptList(mdepPar, parentDeptMap);

        parentDeptMap.entrySet().forEach(item ->{ allParentDeptEntityList.add(item.getValue()); });

        return allParentDeptEntityList;
    }

    /**
     * 根据部门ID获取所有的子级部门列表，平行存储
     * @param deptId
     * @return
     */
    private List<DepttreeEntity> getAllChildDept(String deptId)
    {
        //存放所有的下级部门
        List<DepttreeEntity> deptAllChildList = new ArrayList<>();
        Map<String,DepttreeEntity> childMap = new HashMap<>();

        //递归查找待调动部门的所有下级部门
        //FIXME 递归查询-后期可以修改为根据层级码处理
        findAllChildDeptNodes(deptId, null, childMap);

        childMap.entrySet().forEach(item ->{ deptAllChildList.add(item.getValue()); });

        return deptAllChildList;
    }

    /**
     * 调动单调动校验方法
     * 校验规则：
     *  1、调入部门及调入部门的上上级部门不可存在调动或注销的待审单据；
     *  2、被调动部门是否存在生效日期以后的生效记录，如果存在，提示用户被调动部门还未生效，不可调动；
     *  3、调入部门与列表中被调入部门的上级部门不可相同；
     *  4、调入部门不可是“申请调动部门列表中的相同部门或者下级部门，如果是下级部门，会变成循环嵌套死循环。只可以选择列表中部门所在分支的上级/上上级部门，或者其他分支部门；
     *  5、被调动的部门上级及其上上级部门不能存在注销的待审单据；
     *  6、被调动的部门不能存在待审单据,如果存在，提示“部门名称/部门代码存在待审单据HR***-*****”；
     *  7、被调动的部门下级部门及其下下级部门不能存在注销或者调动的待审单据；
     *  8、被调动部门及其下下级部门是否存在生效日期以后的生效的记录，如果存在，提示用户，存在yyyy-MM-dd 生效的记录，不可调动。或提示部门名称/部门编码还未生效，不可调动；
     * @param newPdeptIdValue
     * @param delayMoveDeptIdList
     * @param odscBeg 有效时间
     * @return
     */
    @Override
    public Result deptMoveValidateService(String newPdeptIdValue, List<String> delayMoveDeptIdList, Date odscBeg)
    {
        Result result = new Result();

        //默认单据类型为调动和注销
        String[] odscTypeArr = {DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID , DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID};

        //【调入部门校验】
        //1、调入部门及调入部门的上级部门不可存在调动或注销的待审单据
        //查询调入部门信息，从而获取调入部门的上级部门ID
        DepttreeEntity moveInDeptEntity = deptTreeDao.findDeptObj(newPdeptIdValue, EdmSysColumn.ID);

        if(null == moveInDeptEntity)
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(String.format("根据部门对象[%s]没有查询到有效的部门对象信息",newPdeptIdValue));
            return result;
        }

        if(StringUtil.isNullOrEmpty(moveInDeptEntity.getMdep_code()))
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(String.format("数据异常，%s的部门编码为空", moveInDeptEntity.getMdep_name()));
            return result;
        }

        //1.1  检查调入部门是否存在：注销或者调动的待审单据 true:存在待审单据；false：不存在
        List<DepttreeEntity> newParentDeptList = new ArrayList<>();
        newParentDeptList.add(moveInDeptEntity);
        List<String> orderNbrList = getOrderNbrList(newParentDeptList , odscTypeArr);
        if(!orderNbrList.isEmpty())
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(String.format("%s/%s存在待审单据%s,不可调动",moveInDeptEntity.getMdep_name(),moveInDeptEntity.getMdep_code(),orderNbrList.get(0)));
            return result;
        }

        //获取调入部门的所有上级部门
        List<DepttreeEntity> allParentDeptDtoList =getAllParentDept(moveInDeptEntity.getMdep_par());

        if(!allParentDeptDtoList.isEmpty()){
            //1.2  检查调入部门的所有上级部门是否存在：注销或者调动的待审单据
            orderNbrList = getOrderNbrList(allParentDeptDtoList , odscTypeArr);
            if(!orderNbrList.isEmpty())
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("%s/%s的上级部门存在待审单据%s,不可调动",moveInDeptEntity.getMdep_name(),moveInDeptEntity.getMdep_code(),orderNbrList.size()==1?orderNbrList.get(0):orderNbrList.toString()));
                return result;
            }
        }

        //2、校验被调动部门是否存在生效日期以后的生效记录，如果存在，提示用户部门[部门名称/部门编码]的生效日期为yyyy-MM-dd，还未生效。
        result = isDeptEffective(delayMoveDeptIdList,odscBeg);

        if(Result.RECODE_ERROR.equals(result.getRetCode()))
        {
            return result;
        }

        //循环遍历被调动部门
        for(String delayMoveDepId : delayMoveDeptIdList)
        {
            //查询被调动部门
            DepttreeEntity delayMoveDeptEntity = deptTreeDao.findDeptObj(delayMoveDepId, EdmSysColumn.ID);
            if(null == delayMoveDeptEntity)
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("根据部门对象[%s]没有查询到有效的部门对象信息",delayMoveDepId));
                return result;
            }

            //4.1调入部门不可是“申请调动部门列表中的相同部门”
            if(newPdeptIdValue.equals(delayMoveDepId))
            {
                String nameAndCode = delayMoveDeptEntity.getMdep_name() + "/" + delayMoveDeptEntity.getMdep_code();
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("调入部门[%s]和被调动部门[%s]是同一个部门，不可调动", nameAndCode, nameAndCode));
                return result;
            }

            //3、调入部门ID和被调动部门的上级部门ID一致，则不允许调动，后续流程终止
            if(newPdeptIdValue.equals(delayMoveDeptEntity.getMdep_par()))
            {
                DepttreeEntity depttreeEntity = deptTreeDao.findById(newPdeptIdValue);
                String nameAndCode = depttreeEntity.getMdep_name() + "/" + depttreeEntity.getMdep_code();
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("调入部门[%s]和待调动部门的上级部门[%s]是同一个部门，不可调动", nameAndCode, nameAndCode));
                return result;
            }

            //4、校验：调入部门不可是“申请调动部门列表中的相同部门或者下级部门"，只可以选择列表中部门所在分支的上级/上上级部门，或者其他分支部门；
            //4.2检查当前被调入部门的所有下级部门
            //存放所有的下级部门
            List<DepttreeEntity> deptAllChildList = getAllChildDept(delayMoveDepId);
            for(DepttreeEntity childDeptDto : deptAllChildList)
            {
                //子部门的对象Id
                String childDeptId = childDeptDto.getId();

                //调入部门不可是“申请调动部门列表中的部门的下级部门"
                if(newPdeptIdValue.equals(childDeptId))
                {
                    //如果部门名称为null，说明数据有问题
                    DepttreeEntity childDept = deptTreeDao.findById(childDeptId);
                    String newPdeptNameAndCode = childDept.getMdep_name() + "/" + childDept.getMdep_code();

                    DepttreeEntity delayMoveDept = deptTreeDao.findById(delayMoveDepId);
                    String delayMoveDeptNameAndCode = delayMoveDept.getMdep_name() + "/" + delayMoveDept.getMdep_code();
                    //不能是自己的下级部门
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg(String.format("调入部门[%s]是待调动部门[%s]的子部门，不可调动", newPdeptNameAndCode, delayMoveDeptNameAndCode));
                    return result;
                }
            }

            //5、被调动的部门上级及其上上级部门不能存在注销的待审单据
            List<DepttreeEntity> moveDeptParentList = getAllParentDept(delayMoveDeptEntity.getMdep_par());
            //检查被调动的部门上级及其上上级部门是否存在：注销的待审单据 true:存在待审单据；false：不存在
            String[] odscTypeArr1 = {DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID};
            orderNbrList = getOrderNbrList(moveDeptParentList , odscTypeArr1);
            if(!orderNbrList.isEmpty())
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("%s/%s的上级部门存在注销的待审单据，不可调动", delayMoveDeptEntity.getMdep_name(), delayMoveDeptEntity.getMdep_code(),orderNbrList.size()==1?orderNbrList.get(0):orderNbrList.toString()));
                return result;
            }

            //6、被调动的部门不能存在待审单据(调动和注销),如果存在，则提示“部门名称/部门代码存在待审单据HR***-*****”。
            List<DepttreeEntity> moveDeptList = new ArrayList<>();
            moveDeptList.add(delayMoveDeptEntity);
            //检查被调动的部门上级及其上上级部门是否存在：注销的待审单据 true:存在待审单据；false：不存在
            orderNbrList = getOrderNbrList(moveDeptList , odscTypeArr);
            if(!orderNbrList.isEmpty())
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("%s/%s存在待审单据%s，不可调动", delayMoveDeptEntity.getMdep_name(), delayMoveDeptEntity.getMdep_code(), orderNbrList.get(0)));
                return result;
            }

            //7、被调动的部门下级部门及其下下级部门不能存在注销或者调动的待审单据
            //检查被调动的部门上级及其上上级部门是否存在：调动或注销的待审单据 true:存在待审单据；false：不存在
            orderNbrList = getOrderNbrList(deptAllChildList , odscTypeArr);
            if(!orderNbrList.isEmpty())
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("%s/%s的下级部门存在注销或者调动的待审单据%s，不可调动", delayMoveDeptEntity.getMdep_name(), delayMoveDeptEntity.getMdep_code(), orderNbrList.size()==1?orderNbrList.get(0):orderNbrList.toString()));
                return result;
            }

            //8、校验当前被调入部门，及其下下级部门是否存在生效日期以后的生效的记录，如果存在，提示用户，存在yyyy-MM-dd 生效的记录，不可调动
            //8.1 校验当前被调入部门
            Result checkResult = getMdepChagSetByPidAndBegDate(delayMoveDepId, odscBeg);
            if(Result.RECODE_ERROR.equals(checkResult.getRetCode()))
            {
                //生效时间 > 单据生效时间的记录
                return checkResult;
            }

            //8.2 校验当前被调入部门的所有下级部门
            //查询当前被调动部门的所有下级部门是否有：生效时间 > 单据生效时间的记录，如果有，返回true
            String childDpetIds = deptAllChildList.stream().map(item -> item.getId()).collect(Collectors.joining(","));
            checkResult = getMdepChagSetByPidAndBegDate(childDpetIds, odscBeg);
            if(Result.RECODE_ERROR.equals(checkResult.getRetCode()))
            {
                //生效时间 > 单据生效时间的记录
                return checkResult;
            }
        }
        return result;
    }

    /**
     * 部门注销单调动校验方法
     * 1、部门注销时，如果选择的部门、下级部门存在单据类型为‘剪切调出/注销直属’的待审单据时，提示用户，且不弹出注销部门页面。
     * 2、如果被选择注销的部门，上级部门存在待审单据（剪切调出/注销直属），在AA页面控制不可选择下级部门
     * 3、校验当前部门，及其下下级部门是否存在生效日期>单据生效日期以后的生效的记录，如果存在，提示用户，存在yyyy-MM-dd 生效的记录，不可注销
     * @param delayMoveDeptIdList
     * @param odscBeg 有效时间
     * @return
     */
    @Override
    public Result deptDeleteValidateService(List<String> delayMoveDeptIdList, Date odscBeg)
    {
        Result result = new Result();

        for (String delayDeptId : delayMoveDeptIdList)
        {

            //部门异动单中只保存了部门类的编码，所以需要根据部门类对象Id查找部门对象
            DepttreeEntity deptTreeInfoDTO = deptTreeDao.findDeptObj(delayDeptId, EdmSysColumn.ID);

            if(null == deptTreeInfoDTO)
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("根据部门对象[%s]没有找到部门信息", delayDeptId));
                return result;
            }

            if(StringUtil.isNullOrEmpty(deptTreeInfoDTO.getMdep_code()))
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("数据异常，%s的部门编码为空", deptTreeInfoDTO.getMdep_name()));
                return result;
            }

            //默认单据类型
            String[] odscTypeArr = {DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID , DeptStuChangeOrderConstants.ODSC_TYPE_CANCEL_ID};
            //默认单据状态
            String[] ordeStatusArr = {OrderConstants.ORDE_STATUS_2 , OrderConstants.ORDE_STATUS_3 , OrderConstants.ORDE_STATUS_4};

            //存放所有部门、部门子部门的列表
            List<DepttreeEntity> deptTreeInfoDTOList = getAllChildDept(delayDeptId);
            deptTreeInfoDTOList.add(deptTreeInfoDTO);

            //1、查询部门、部门子部门是否存在“调动/注销”待审单据,true 存在；false 不存在
            boolean havePendingTrialOrder = queryDeptHavePendingTrialOrder(deptTreeInfoDTOList , odscTypeArr , ordeStatusArr);

            if(havePendingTrialOrder)
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("根据部门对象[%s], 查询到部门、部门子部门中存在部门结构异动单“调动/注销”待审单据", deptTreeInfoDTO.getMdep_name()));
                return result;
            }

            //2、查询部门的上级部门是否存在“调动/注销”待审单据,true 存在；false 不存在
            List<DepttreeEntity> parentDeptTreeInfoDTOList = getAllParentDept(delayDeptId);
            boolean parentDeptHavePendingTrialOrder = queryDeptHavePendingTrialOrder(parentDeptTreeInfoDTOList , odscTypeArr , ordeStatusArr);

            if(parentDeptHavePendingTrialOrder)
            {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("根据部门对象[%s], 查询到部门的上级部门中存在部门结构异动单“调动/注销”待审单据", deptTreeInfoDTO.getMdep_name()));
                return result;
            }

            //3、校验当前部门，及其下下级部门是否存在生效日期>单据生效日期以后的生效的记录，如果存在，提示用户，存在yyyy-MM-dd 生效的记录，不可注销
            List<String> idList = new ArrayList<>();

            deptTreeInfoDTOList.forEach(item -> { idList.add(item.getId()); });

            result = isDeptEffective(idList , odscBeg);
            if(Result.RECODE_ERROR.equals(result.getRetCode()))
            {
                return result;
            }
        }
        return result;
    }

    /**
     * 校验被调动/注销部门是否存在生效日期以后的生效记录，如果存在，提示用户，部门名称/部门编码还未生效，不可调动/注销。
     * @param deptIdList
     * @param odscBeg
     * @return
     */
    private Result isDeptEffective(List<String> deptIdList, Date odscBeg)
    {
        Result result = new Result();
        try
        {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.PID).addColumn(MdepMdepChagSetaProperty.MDEP_BEG_HIS);
            String whereExp = OrmParam.and(ormParam.getInXML(EdmSysColumn.PID,deptIdList.toArray()),ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_BEG_HIS,odscBeg));
            ormParam.setWhereExp(whereExp);
            List<MdepMdepChagSetaEntity> mdepChagSetaEntityList = ormService.selectBeanList(MdepMdepChagSetaEntity.class,ormParam);
            if(!mdepChagSetaEntityList.isEmpty())
            {
                String errMsg = "";

                for(MdepMdepChagSetaEntity entity : mdepChagSetaEntityList)
                {
                    DepttreeEntity depttreeEntity = ormService.load(DepttreeEntity.class,entity.getPid());
                    errMsg += String.format("%s/%s存在%s生效的记录，请调整失效日期申请部门注销",depttreeEntity.getMdep_name(),depttreeEntity.getMdep_code(),DateUtils.parseDateToStr(entity.getMdep_beg_his(),DateUtils.DATE_YYYY_MM_DD)) + ";";
                }
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(errMsg.substring(0,errMsg.length()-1));
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
     * 查询部门列表是否存在单据类型为‘剪切调动/注销直属’的待审单据；
     * @param depttreeEntityList
     * @param odscTypeArr
     * @param ordeStatusArr
     * @return
     */
    private boolean queryDeptHavePendingTrialOrder(List<DepttreeEntity> depttreeEntityList ,String[] odscTypeArr , String[] ordeStatusArr)
    {
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.PID);

        //部门编码采用in操作，将所有部门一起过滤查询
        String deptCodeArray = depttreeEntityList.stream().map(item -> item.getMdep_code()).collect(Collectors.joining(","));

        List<String> list = new ArrayList<>();

        //1 部门编码过滤条件
        list.add(ormParam.getInXML(OdscOdscChagSetaProperty.ODSC_DEPT_CODE,deptCodeArray.split(",")));

        //2 异动列表中的flag过滤
        String odscFlagStrArray = OdscChagSetConstants.ODSC_FLAG_MOVE + "," + OdscChagSetConstants.ODSC_FLAG_CANCEL;
        list.add(ormParam.getInXML(OdscOdscChagSetaProperty.ODSC_FLAG,odscFlagStrArray.split(",")));

        ormParam.setWhereExp(OrmParam.and(list));
        List<OdscChagSetDTO> odscChagSetDTOList = new ArrayList<>();

        try
        {
            //查询部门的异动列表记录, 只返回部门结构异动单对象的ID-PID
            odscChagSetDTOList = findDeptOdscChagSetList(ormParam);
        }
        catch (ApplicationException ex)
        {
            //此处异常可以不做处理，只是数据校验时使用
        }

        if(!odscChagSetDTOList.isEmpty())
        {
            OrmParam ormParam1 = new OrmParam();
            ormParam1.addColumn(EdmSysColumn.ID).addColumn(DeptstuchangeorderProperty.ODSC_BEG);

            List<String> list1 = new ArrayList<String>();
            //1 异动列表的PID就是部门结构异动单对象的ID
            String idStrArray = odscChagSetDTOList.stream().map(item -> item.getPid()).collect(Collectors.joining(","));
            list1.add(ormParam1.getInXML(EdmSysColumn.ID,idStrArray.split(",")));


            //2 单据类型odscType
            if(null != odscTypeArr)
            {
                list1.add(ormParam1.getInXML(DeptstuchangeorderProperty.ODSC_TYPE,odscTypeArr));
            }

            //3 单据审核状态过滤条件 （未完，状态 in (2,3,4)）
            list1.add(ormParam1.getInXML(OrderProperty.ORDE_STATUS,ordeStatusArr));

            ormParam1.setWhereExp(OrmParam.and(list1));

            //查询部门结构异动单列表，统计是否存在异动单，如果有一条，则认为存在。
            List<DeptStuChangeOrderDTO> deptStuChangeOrderDTOList = findDeptStuChangeOrderList(ormParam1 , null);

            if(!deptStuChangeOrderDTOList.isEmpty())
            {
                //有部门存在 调动或者注销 异动单据
                return true;
            }
            else
            {
                //没有一个部门存在调动或者注销 异动单据
                return false;
            }
        }
        else
        {
            //没有一个部门存在调动或者注销 异动单据
            return false;
        }
    }

    /**
     * 根据条件查询返回的单据号orderNbr集合
     * @param depttreeEntityList
     * @param odscTypeArr
     * @return
     */
    private List<String> getOrderNbrList(List<DepttreeEntity> depttreeEntityList ,String[] odscTypeArr)
    {
        //返回的申请单单号集合
        List<String> orderNbrList = new ArrayList<>();

        //查询条件集合
        List<String> list = new ArrayList<>();

        //封装OrmParam对象
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.PID);

        //部门编码采用in操作，将所有部门一起过滤查询
        String deptCodeArray = depttreeEntityList.stream().map(item -> item.getMdep_code()).collect(Collectors.joining(","));

        //1 部门编码过滤条件
        list.add(ormParam.getInXML(OdscOdscChagSetaProperty.ODSC_DEPT_CODE,deptCodeArray.split(",")));

        //2 异动列表中的flag过滤
        String[] odscFlagStrArray = {OdscChagSetConstants.ODSC_FLAG_MOVE , OdscChagSetConstants.ODSC_FLAG_CANCEL};
        list.add(ormParam.getInXML(OdscOdscChagSetaProperty.ODSC_FLAG,odscFlagStrArray));

        ormParam.setWhereExp(OrmParam.and(list));

        try
        {
            //查询部门的异动列表记录, 只返回部门结构异动单对象的ID-PID
            List<OdscOdscChagSetaEntity> odscOdscChagSetaEntityList = ormService.selectBeanList(OdscOdscChagSetaEntity.class , ormParam);
            if(odscOdscChagSetaEntityList.isEmpty())
            {
                return orderNbrList;
            }

            ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID).addColumn(DeptstuchangeorderProperty.ODSC_BEG).addColumn(OrderProperty.ORDE_NBR);

            list = new ArrayList<String>();
            //1 异动列表的PID就是部门结构异动单对象的ID
            String idStrArray = odscOdscChagSetaEntityList.stream().map(item -> item.getPid()).collect(Collectors.joining(","));
            list.add(ormParam.getInXML(EdmSysColumn.ID,idStrArray.split(",")));

            //2 单据类型odscType
            if(null != odscTypeArr)
            {
                list.add(ormParam.getInXML(DeptstuchangeorderProperty.ODSC_TYPE , odscTypeArr));
            }
            //3 单据审核状态过滤条件 （未完，状态 in (2,3,4)）
            String[] ordeStatusArr = {OrderConstants.ORDE_STATUS_2 , OrderConstants.ORDE_STATUS_3 , OrderConstants.ORDE_STATUS_4};
            list.add(ormParam.getInXML(OrderProperty.ORDE_STATUS,ordeStatusArr));

            ormParam.setWhereExp(OrmParam.and(list));

            //查询部门结构异动单列表，统计是否存在异动单，如果有一条，则认为存在。
            List<DeptstuchangeorderEntity> deptstuchangeorderEntityList = ormService.selectBeanList(DeptstuchangeorderEntity.class , ormParam);

            deptstuchangeorderEntityList.forEach(item -> { orderNbrList.add(item.getOrde_nbr()); });

        }
        catch (Exception e)
        {
            //此处异常可以不做处理，只是数据校验时使用
            return orderNbrList;
        }
        return orderNbrList;
    }

    /**
     * 修改新上级部门的下级编制和旧上级部门的下级编制
     * @param deptTreeInfoDTO  包含新的上级部门对象Id
     * @param oldPDeptId 旧的上级部门对象Id
     */
    private void updatePDeptLlNum(DepttreeEntity deptTreeInfoDTO, String oldPDeptId)
    {
        String pDeptId = deptTreeInfoDTO.getMdep_par();

        //当前部门的编制（只包含部门本层编制，并不是所有下级编制的汇总）
        int moveDeptTlNum = deptTreeInfoDTO.getMdep_tl_num();

        //当前部门的下层编制(不包含本层编制，是所有下级部门的编制和下层编制的汇总值)
        int moveDeptLlNum = deptTreeInfoDTO.getMdep_ll_num();

        //上级部门的岗位编制增加，原上级部门的部门编制减
        DepttreeEntity parentDeptTreeInfoJSONObj = deptTreeDao.findDeptObj(pDeptId, EdmSysColumn.ID);
        //修改新上级部门类中的：下层编制=原下层编制 + 新调入部门的部门编制 + 新调入部门的下层编制
        if(null != parentDeptTreeInfoJSONObj)
        {
            //新上级部门的下级编制
            int pDeptLlNum = parentDeptTreeInfoJSONObj.getMdep_ll_num();

            //新调入的上级部门的下层编制
            int newPDeptLlNum = pDeptLlNum + moveDeptTlNum + moveDeptLlNum;

            DepttreeEntity updatePDeptLlNumParam = new DepttreeEntity();

            updatePDeptLlNumParam.setId(pDeptId);
            updatePDeptLlNumParam.setMdep_ll_num(newPDeptLlNum);

            deptTreeDao.updateDepttree(updatePDeptLlNumParam);
        }

        //原部门的部门编制因为部门被调出，需要减去被调出的部门编制
        DepttreeEntity oldParentDeptTreeInfoJSONObj = deptTreeDao.findDeptObj(oldPDeptId, EdmSysColumn.ID);

        //原上级部门的：下层编制=原下层编制 - 调出部门的部门编制 - 调出部门的下层编制
        if(null != oldParentDeptTreeInfoJSONObj)
        {
            int oldPDeptLlNum = oldParentDeptTreeInfoJSONObj.getMdep_ll_num();

            //部门旧的上级部门编制数
            int newOldPDeptLlNum = oldPDeptLlNum - moveDeptTlNum - moveDeptLlNum;

            DepttreeEntity updateOldPDeptLlNumParam = new DepttreeEntity();

            updateOldPDeptLlNumParam.setId(oldPDeptId);
            updateOldPDeptLlNumParam.setMdep_ll_num(newOldPDeptLlNum);

            deptTreeDao.updateDepttree(updateOldPDeptLlNumParam);
        }
    }

    /**
     * 查询部门是否被注销, true 被注销；false未被注销
     * @param deptID  变更记录表中的PID就是部门对象的ID
     * @param validtorTime 有效时间
     * @return
     */
    public boolean isDeptDelete(String deptID, Date validtorTime)
    {
        //orm3.0规范
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID).addColumn(MdepMdepChagSetaProperty.MDEP_NAME_HIS).addColumn(MdepMdepChagSetaProperty.MDEP_LVL_HIS);
        String whereExp = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID,deptID),ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_END_HIS, validtorTime));
        ormParam.setWhereExp(whereExp);
        try
        {
            List<MdepMdepChagSetaEntity> list = ormService.selectBeanList(MdepMdepChagSetaEntity.class,ormParam);
            if(!list.isEmpty())
            {
                return false;
            }
        }
        catch (Exception e)
        {
            if (LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return true;
    }

    /**
     * 从部门变更记录集中根据PID和有效时间查询有效的记录
     * @param pId
     * @param date
     * @return
     */
    public MdepMdepChagSetaEntity findValidatorDeptFromChagSet(String pId, Date date)
    {
        try
        {
            //depttree.mdep_chag_set
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID);
            String whereExp = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.PID,pId),
                    //有效记录的生效时间小于等于调动单据的生效时间and有效记录的失效时间大于调动单据的生效时间
                    ormParam.getLessThanAndEqualXML(MdepMdepChagSetaProperty.MDEP_BEG_HIS, date),
                    ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_END_HIS, date));
            ormParam.setWhereExp(whereExp);
            List<MdepMdepChagSetaEntity> list = ormService.selectBeanList(MdepMdepChagSetaEntity.class,ormParam);
            if(!list.isEmpty())
            {
                MdepMdepChagSetaEntity entity = list.get(0);
                return  entity;
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
     * 根据单据生效日期，查找部门变更记录集中是否有记录大于单据生效日期的记录, true有记录的生效日期>单据的生效日期；false没有
     * 生效日期 > 单据生效日期-以后的生效的记录 MdepChagSet
     * @param deptId
     * @param begDate
     * @return
     */
//    public boolean checkBegGreaterOrderBegRecordFromMdepChagSet(String deptId, Date begDate)
//    {
//        OrmParam ormParam = new OrmParam();
//        ormParam.addColumn(EdmSysColumn.ID);
//        String whereExp = OrmParam.and(
//                //deptId采用In操作，可支持查询很多部门
//                ormParam.getInXML(EdmSysColumn.PID, deptId.split(",")),
//                //生效日期 > 单据生效日期-以后的生效的记录
//                ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_BEG_HIS, begDate));
//        ormParam.setWhereExp(whereExp);
//        try
//        {
//            List<MdepMdepChagSetaEntity> list = ormService.selectBeanList(MdepMdepChagSetaEntity.class,ormParam);
//            if(!list.isEmpty())
//            {
//                return true;
//            }
//        }
//        catch (Exception e)
//        {
//            if (LOGGER.isDebugEnabled())
//            {
//                e.printStackTrace();
//            }
//            return false;
//        }
//        return false;
//    }

    public Result getMdepChagSetByPidAndBegDate(String deptId , Date begDate)
    {
        Result result = new Result();
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID).addColumn(EdmSysColumn.PID).addColumn(MdepMdepChagSetaProperty.MDEP_BEG_HIS);
        String whereExp = OrmParam.and(
                //deptId采用In操作，可支持查询很多部门
                ormParam.getInXML(EdmSysColumn.PID, deptId.split(",")),
                //生效日期 > 单据生效日期-以后的生效的记录
                ormParam.getGreaterThanXML(MdepMdepChagSetaProperty.MDEP_BEG_HIS, begDate));
        ormParam.setOrderExp(SQLSortEnum.DESC , MdepMdepChagSetaProperty.MDEP_BEG_HIS);
        ormParam.setWhereExp(whereExp);
        try
        {
            List<MdepMdepChagSetaEntity> list = ormService.selectBeanList(MdepMdepChagSetaEntity.class,ormParam);
            if(!list.isEmpty())
            {
                MdepMdepChagSetaEntity entity = list.get(0);
                Date mdep_beg_his = entity.getMdep_beg_his();
                String mdep_beg = DateUtils.parseDateToStr(mdep_beg_his,DateUtils.DATE_YYYY_MM_DD);
                String pId =  entity.getPid();
                DepttreeEntity dept = deptTreeDao.findById(pId);
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg(String.format("%s/%s存在%s生效的记录，不可调动当前部门，请调整单据生效日期。",dept.getMdep_name(),dept.getMdep_code(),mdep_beg));
            }
        }
        catch (Exception e)
        {
            if (LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            return result;
        }
        return result;
    }

    /**
     * 从岗位变更记录集中根据PID和有效时间查询有效的记录
     * @param pId 配合ORM中的in操作，字符串采用,分割
     * @param date
     * @return
     */
    public JSONArray findValidatorDeptPostFromChagSet(String pId, Date date)
    {
        try
        {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID);
            String whereExp = OrmParam.and(
                    //批量查询-支持in操作，id之间采用,分割
                    ormParam.getInXML(EdmSysColumn.PID,pId.split(",")),
                    //有效记录的生效时间小于调动单据的生效时间and有效记录的失效时间大于调动单据的生效时间
                    ormParam.getLessThanAndEqualXML(RposRposChagSetaProperty.RPOS_BEG_HIS, date),
                    ormParam.getGreaterThanXML(RposRposChagSetaProperty.RPOS_END_HIS, date));
            ormParam.setWhereExp(whereExp);
            List<RposRposChagSetaEntity> list = ormService.selectBeanList(RposRposChagSetaEntity.class,ormParam);
            JSONArray deptResult = new JSONArray();
            if(!list.isEmpty())
            {
                //下划线转驼峰
                list.forEach(item -> { deptResult.add( underLineJson2CamelJson(JSON.toJSONString(item))); });
                return deptResult;
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
     * * 从员工类任岗记录集中根据PID和有效时间查询有效的记录【员工在所注销的部门中有岗位的任职方式=任职的任岗经历】
     * @param pId 配合ORM中的in操作，字符串采用,分割
     * @param postIdList
     * @param date
     * @return
     */
    public JSONArray findValidatorDeptEmployeeFromChagSet(String pId, List<String> postIdList , Date date)
    {
        JSONArray rposChagSetResult = new JSONArray();
        try
        {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID);
            String whereExp = OrmParam.and(
                    //批量查询-支持in操作，id之间采用,分割
                    ormParam.getInXML(EdmSysColumn.PID,pId.split(",")),
                    ormParam.getInXML(RempRempPostSetaProperty.REMP_POST_HIS , postIdList.toArray()),
                    //有效记录的生效时间小于调动单据的生效时间and有效记录的失效时间大于调动单据的生效时间
                    ormParam.getLessThanAndEqualXML(EmployeeConstants.REMP_POST_BEG, date),
                    ormParam.getGreaterThanXML(EmployeeConstants.REMP_POST_END, date));
            ormParam.setWhereExp(whereExp);

            List<RempRempPostSetaEntity> list = ormService.selectBeanList(RempRempPostSetaEntity.class,ormParam);
            if(!list.isEmpty())
            {
                list.forEach(item -> {
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put(EdmSysColumn.ID , item.getId());
                    rposChagSetResult.add(jsonObject);
                });
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
        return rposChagSetResult;
    }

    /**
     * 将下划线的json转化为驼峰的JSONObject
     * @param json
     * @return
     */
    private JSONObject  underLineJson2CamelJson(String json)
    {
        Object params = JSONObject.parse(json);
        //下划线转驼峰
        JsonUtils.underLine2Camel(params);
        return JSONObject.parseObject(JSONObject.toJSONString(params));
    }

    /**
     * 查询部门所有子节点中的最大层级码, 如果没有子部门，则返回默认层级码，反之，返回所有子节点中最大的层级码,子部门层级码基础上增加1
     * @param deptId 本节点
     * @param pDeptId     父节点对象Id
     * @return 返回部门的最大层级码，如果有子部门，则在子部门层级码基础上增加1
     */
    public String makeDeptLvCode(String deptId, String pDeptId)
    {
        String deptLvCode = "";

        //查询待调入部门-下级部门最大层级码 + 1;如果没有下级部门，则默认第一级子部门编码 + 001
        DepttreeEntity pDeptObj = deptTreeDao.findDeptObj(pDeptId, EdmSysColumn.ID);

        String pDeptLvCode;
        if(null != pDeptObj)
        {
            pDeptLvCode = pDeptObj.getMdep_lvl_code();
        }
        else
        {
            //部门类对象不存在，说明数据存在异常
            throw new ApplicationException(Result.RECODE_ERROR, "未查询到任何有效的部门类对象");
        }

        //查询所有子部门节点
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID).addColumn(DepttreeProperty.MDEP_LVL_CODE);
        //上级部门对象id
        ormParam.setWhereExp(ormParam.getEqualXML(DepttreeProperty.MDEP_PAR, pDeptId));
        List<DepttreeEntity> depttreeEntityList = null;
        try
        {
            depttreeEntityList = ormService.selectBeanList(DepttreeEntity.class,ormParam);
        }
        catch (Exception e)
        {
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }

        List<DeptTreeInfoDTO> childDeptTreeList = new ArrayList<>();

        depttreeEntityList.forEach(item -> { childDeptTreeList.add(JSONObject.parseObject(JSONObject.toJSONString(item),DeptTreeInfoDTO.class)); });

        if(childDeptTreeList.isEmpty())
        {
            //如果父节点下没有任何子部门，则采用默认层级码001
            deptLvCode += pDeptLvCode + "001,";
        }
        else
        {
            //自然排序：倒序排列
            childDeptTreeList.sort(Comparator.comparing(DeptTreeInfoDTO::getMdepLvlCode));

            //取最后一个元素
            DeptTreeInfoDTO deptTreeInfoDTO = childDeptTreeList.get(childDeptTreeList.size() - 1);

            String childMaxLvCode = deptTreeInfoDTO.getMdepLvlCode();

            //如果当前部门节点已经属于上级部门的子节点，则层级码不变
            if(deptTreeInfoDTO.getId().equals(deptId))
            {
                return childMaxLvCode;
            }

            //截取最后一个编码段，如：001,001,002,
            String childMaxLvCodeStrValue = childMaxLvCode.substring(childMaxLvCode.lastIndexOf(",") - 3, childMaxLvCode.lastIndexOf(","));

            //层级码开始段
            String childStartLvCodeStrValue = childMaxLvCode.substring(0, childMaxLvCode.lastIndexOf(",") - 3);

            //层级码+1
            Integer childMaxLvCodeIntValue = Integer.valueOf(childMaxLvCodeStrValue) + 1;

            int max_10 = 10;
            int max_100 = 100;
            int max_1000 =1000;

            //将层级码开始段拼接最大层级码
            if(childMaxLvCodeIntValue < max_10)
            {
                deptLvCode += childStartLvCodeStrValue + "00" + childMaxLvCodeIntValue;
            }
            else if(childMaxLvCodeIntValue < max_100)
            {
                deptLvCode += childStartLvCodeStrValue + "0" + childMaxLvCodeIntValue;
            }
            else if(childMaxLvCodeIntValue < max_1000)
            {
                deptLvCode += childStartLvCodeStrValue + childMaxLvCodeIntValue;
            }
            else
            {
                //超标
                LOGGER.info("--> 部门层级码长度不够");
                deptLvCode += childStartLvCodeStrValue + "999";
            }
        }
        if(!",".equals(deptLvCode.substring(deptLvCode.length()-1)))
        {
            deptLvCode += ",";
        }
        return deptLvCode;
    }

    /**
     * FIXME 递归查询所有的子部门列表
     * @param deptId  部门对象Id
     * @param deptLvCode [可选] 当前部门的层级码，作为其下级部门的层级码-上级层级码段，如：[001,002],001
     * @param childMap  接受部门对象的容器
     */
    @Override
    public void findAllChildDeptNodes(String deptId, String deptLvCode,Map<String,DepttreeEntity> childMap)
    {
        try
        {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID)
                    .addColumn(DepttreeProperty.MDEP_BEG)
                    .addColumn(DepttreeProperty.MDEP_CODE)
                    .addColumn(DepttreeProperty.MDEP_DUTY)
                    .addColumn(DepttreeProperty.MDEP_END)
                    .addColumn(DepttreeProperty.MDEP_GRADE)
                    .addColumn(DepttreeProperty.MDEP_LEADER)
                    .addColumn(DepttreeProperty.MDEP_LEADER_POST)
                    .addColumn(DepttreeProperty.MDEP_LL_NUM)
                    .addColumn(DepttreeProperty.MDEP_LNAME)
                    .addColumn(DepttreeProperty.MDEP_LVL_CODE)
                    .addColumn(DepttreeProperty.MDEP_MCOP)
                    .addColumn(DepttreeProperty.MDEP_NAME)
                    .addColumn(DepttreeProperty.MDEP_PAR)
                    .addColumn(DepttreeProperty.MDEP_RPAK)
                    .addColumn(DepttreeProperty.MDEP_SEQ)
                    .addColumn(DepttreeProperty.MDEP_SNAME)
                    .addColumn(DepttreeProperty.MDEP_TL_NUM);

            //上级部门对象id
            ormParam.setWhereExp(ormParam.getEqualXML(DepttreeProperty.MDEP_PAR, deptId));

            List<DepttreeEntity> depttreeEntityList = ormService.selectBeanList(DepttreeEntity.class,ormParam);

            if(!depttreeEntityList.isEmpty())
            {
                depttreeEntityList.forEach(item -> {
                    //处理下级层级码，将最新的上级部门层级码替换下级层级码中的上级部分：[001,001],002, 保留002部门
                    String currentChildDeptLvCode = item.getMdep_lvl_code();
                    //部门层级码上级部门层级码段
                    String currentChildPDeptLvCodeSegment = currentChildDeptLvCode.substring(0, currentChildDeptLvCode.length() - 4);

                    //替换旧的上级部门层级码
                    if(null != deptLvCode)
                    {
                        String currentChildDeptLvCodeNew = currentChildDeptLvCode.replaceAll(currentChildPDeptLvCodeSegment, deptLvCode);
                        item.setMdep_lvl_code(currentChildDeptLvCodeNew);
                    }
                });


                //递归当前部门下的所有子节点
                for (DepttreeEntity deptTreeDto : depttreeEntityList)
                {
                    childMap.put(deptTreeDto.getId(),deptTreeDto);

                    //当前部门对象的id，作为查询子节点的上级部门对象Id
                    String childDeptId = deptTreeDto.getId();

                    //当前部门的层级码作为其下级部门的层级码段使用
                    String currrentDeptLvCode = deptTreeDto.getMdep_lvl_code();

                    findAllChildDeptNodes(childDeptId, currrentDeptLvCode, childMap);
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
     * 查询上级部门，追溯到根节点, 返回平行的列表，不是树形结构
     * @param pDeptId  直属上级部门对象Id
     * @param dtoMap
     */
    @Override
    public void findAllParentDeptList(String pDeptId, Map<String,DepttreeEntity> dtoMap)
    {
        try
        {
            OrmParam ormParam = new OrmParam();
            ormParam.addColumn(EdmSysColumn.ID)
                    .addColumn(DepttreeProperty.MDEP_BEG)
                    .addColumn(DepttreeProperty.MDEP_CODE)
                    .addColumn(DepttreeProperty.MDEP_DUTY)
                    .addColumn(DepttreeProperty.MDEP_END)
                    .addColumn(DepttreeProperty.MDEP_GRADE)
                    .addColumn(DepttreeProperty.MDEP_LEADER)
                    .addColumn(DepttreeProperty.MDEP_LEADER_POST)
                    .addColumn(DepttreeProperty.MDEP_LL_NUM)
                    .addColumn(DepttreeProperty.MDEP_LNAME)
                    .addColumn(DepttreeProperty.MDEP_LVL_CODE)
                    .addColumn(DepttreeProperty.MDEP_MCOP)
                    .addColumn(DepttreeProperty.MDEP_NAME)
                    .addColumn(DepttreeProperty.MDEP_PAR)
                    .addColumn(DepttreeProperty.MDEP_RPAK)
                    .addColumn(DepttreeProperty.MDEP_SEQ)
                    .addColumn(DepttreeProperty.MDEP_SNAME)
                    .addColumn(DepttreeProperty.MDEP_TL_NUM);
            ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.ID, pDeptId));

            //调用部门类中的查询接口, 返回上级部门对象,只会有一个上级
            List<DepttreeEntity> depttreeEntityList = ormService.selectBeanList(DepttreeEntity.class,ormParam);

            if(!depttreeEntityList.isEmpty())
            {
                //继续递归调用，查询上上级
                DepttreeEntity parentDeptDto = depttreeEntityList.get(0);


                //获取上级对象Id
                String parentDeptId = parentDeptDto.getMdep_par();

                dtoMap.put(parentDeptDto.getId(),parentDeptDto);

                //继续递归调用，查询上上级
                //FIXME 递归查询-后期可以修改为根据层级码处理
                findAllParentDeptList(parentDeptId, dtoMap);
            }
        }
        catch (Exception ex)
        {
            //此处异常可以不做处理，只是数据校验时使用
            if(LOGGER.isDebugEnabled())
            {
                ex.printStackTrace();
            }
        }
    }

    /**
     * 将部门结构异动单据-异动列表-和部门类对象统一转换为-部门类-变更记录集JSONObject对象，准备入库
     * @param list  可能是异动单类对象OdscChagSetDTO 和部门类对象DeptTreeInfoDTO
     * @param effectiveDate 生效日期
     * @return
     */
    private List<MdepMdepChagSetaEntity> transformDeptToJSONObject(List<DepttreeEntity> list, Date effectiveDate)
    {
        List<MdepMdepChagSetaEntity> deptTreeObjList = new ArrayList<>();

        for (DepttreeEntity deptTreeObj : list)
        {
            //接受不同来源的数据源,封装为JSONObject准备入库
            MdepMdepChagSetaEntity deptTreeJSONObject = new MdepMdepChagSetaEntity();

            //部门变更记录类属性：变更记录集和部门类的关联PID
            deptTreeJSONObject.setPid(deptTreeObj.getId());

            //部门变更记录类属性：部门层码
            deptTreeJSONObject.setMdep_lvl_his(deptTreeObj.getMdep_lvl_code());

            //部门变更记录类属性：上级部门
            deptTreeJSONObject.setMdep_par_his(deptTreeObj.getMdep_par());

            //部门变更记录类属性：部门名称
            deptTreeJSONObject.setMdep_name_his(deptTreeObj.getMdep_name());

            //部门变更记录类属性：部门简称
            deptTreeJSONObject.setMdep_sname_his(deptTreeObj.getMdep_sname());

            //部门变更记录类属性：部门级别
            deptTreeJSONObject.setMdep_grade_his(deptTreeObj.getMdep_grade());

            //部门变更记录类属性：排序---> 待处理
            deptTreeJSONObject.setMdep_seq_his(0);

            //部门变更记录类属性：办公园区
            deptTreeJSONObject.setMdep_rpak_his(deptTreeObj.getMdep_rpak());

            //部门变更记录类属性：法人公司
            deptTreeJSONObject.setMdep_mcop_his(deptTreeObj.getMdep_mcop());

            //部门变更记录类属性：部门职责
            deptTreeJSONObject.setMdep_duty_his(deptTreeObj.getMdep_duty());

            //部门变更记录类属性：部门编制
            deptTreeJSONObject.setMdep_tnum_his(deptTreeObj.getMdep_tl_num());

            //部门变更记录类属性：生效日期
            deptTreeJSONObject.setMdep_beg_his(effectiveDate);

            //部门变更记录类属性：失效日期  默认9999-12-31 23:59:59
            deptTreeJSONObject.setMdep_end_his(DateUtil.parseFormatDate("9999-12-31 23:59:59",DateUtils.DATE_YYYY_MM_DD_HH_MM_SS));

            //将待入库的部门变更对象加入队列中
            deptTreeObjList.add(deptTreeJSONObject);
        }
        return deptTreeObjList;
    }

    /**
     * 查询部门对象列表
     * @param odscBeg
     * @param deptIdList
     * @return
     */
    private Result queryDeptList(String odscBeg , List<String> deptIdList)
    {
        Result result = new Result();

        //调用部门类接口返回部门列表
        List<MdepMdepChagSetaEntity> mdepMdepChagSetaEntityList = deptTreeDao.findChangeSetByPids(deptIdList.toArray(new String[deptIdList.size()]) , DateUtils.parseStrToDate(odscBeg, DateUtils.DATE_YYYY_MM_DD));

        if(mdepMdepChagSetaEntityList.isEmpty())
        {
            return result;
        }

        List<String> pids = new ArrayList<>();
        mdepMdepChagSetaEntityList.forEach(item -> { pids.add(item.getPid());});

        //根据部门变更记录集中的pid查询主表部门信息
        List<DepttreeEntity> deptTreeInfoJSONList = deptTreeDao.findByIdList(pids);

        for(DepttreeEntity depttreeEntity : deptTreeInfoJSONList)
        {
            for(MdepMdepChagSetaEntity mdepMdepChagSetaEntity : mdepMdepChagSetaEntityList)
            {
                if(depttreeEntity.getId().equals(mdepMdepChagSetaEntity.getPid()))
                {
                    //生效时间
                    depttreeEntity.setMdep_beg(mdepMdepChagSetaEntity.getMdep_beg_his());
                    //失效时间
                    depttreeEntity.setMdep_end(mdepMdepChagSetaEntity.getMdep_end_his());
                    //部门名称
                    depttreeEntity.setMdep_name(mdepMdepChagSetaEntity.getMdep_name_his());
                    //上级部门
                    depttreeEntity.setMdep_par(mdepMdepChagSetaEntity.getMdep_par_his());
                    //部门简称
                    depttreeEntity.setMdep_sname(mdepMdepChagSetaEntity.getMdep_sname_his());
                    //法人公司
                    depttreeEntity.setMdep_mcop(mdepMdepChagSetaEntity.getMdep_mcop_his());
                    //部门级
                    depttreeEntity.setMdep_grade(mdepMdepChagSetaEntity.getMdep_grade_his());
                    //办公园区
                    depttreeEntity.setMdep_rpak(mdepMdepChagSetaEntity.getMdep_rpak_his());
                    //部门职责
                    depttreeEntity.setMdep_duty(mdepMdepChagSetaEntity.getMdep_duty_his());
                    //部门层级码
                    depttreeEntity.setMdep_lvl_code(mdepMdepChagSetaEntity.getMdep_lvl_his());
                    //部门排序
                    depttreeEntity.setMdep_seq(mdepMdepChagSetaEntity.getMdep_seq_his());
                    //部门编制
                    depttreeEntity.setMdep_tl_num(mdepMdepChagSetaEntity.getMdep_tnum_his());
                }
            }
        }

        //查询加载待调动部门列表
        List<DeptTreeInfoDTO> deptTreeInfoDTOList = JSONArray.parseArray(JSON.toJSONString(deptTreeInfoJSONList), DeptTreeInfoDTO.class);

        for(DeptTreeInfoDTO deptTreeInfoDTO : deptTreeInfoDTOList)
        {
            String deptId = deptTreeInfoDTO.getId();

            //上级部门对象Id
            String parentDeptId = deptTreeInfoDTO.getMdepPar();

            //查询上级部门信息
            try
            {
                DepttreeEntity parentDeptTreeInfoDTO = deptTreeDao.findDeptObj(parentDeptId, EdmSysColumn.ID);

                if(null != parentDeptTreeInfoDTO)
                {
                    //设置上级部门名称
                    deptTreeInfoDTO.setMdepParName(parentDeptTreeInfoDTO.getMdep_name());
                }
            }
            catch (ApplicationException ex)
            {
                //没有查询到数据的异常不做处理
            }

            //【部门含下级部门岗位数：所属部门=当前部门&&岗位有效】
            JSONArray jobPositions = null;
            try
            {
                jobPositions = postService.getPostsByDeptId(deptId,"1");
            }
            catch (Exception e)
            {
                if(LOGGER.isDebugEnabled())
                {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
            }

            if(null != jobPositions && !jobPositions.isEmpty())
            {
                //设置部门岗位数（包含下级）返回给界面
                deptTreeInfoDTO.setMdepPostNum(jobPositions.size());
            }
            else
            {
                deptTreeInfoDTO.setMdepPostNum(0);
            }

            //【部门含下级部门在编数：所属部门在编数=当前部门&&岗位有效&&岗位任职人不为空】
            JSONArray inJobPositions = null;
            try
            {
                inJobPositions = postService.getPostsEmpByDeptId(deptId, "1");
            }
            catch (Exception e)
            {
                if(LOGGER.isDebugEnabled())
                {
                    e.printStackTrace();
                }
                throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
            }

            if(null != inJobPositions && !inJobPositions.isEmpty())
            {
                //设置部门含下级部门在编数（包含下级）返回给界面
                deptTreeInfoDTO.setMdepInJobPostNum(inJobPositions.size());
            }
            else
            {
                deptTreeInfoDTO.setMdepInJobPostNum(0);
            }
        }
        result.setData(deptTreeInfoDTOList);
        return result;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @TxTransaction(rollbackFor = Exception.class)
    public Result submit(DeptStuChangeOrderDTO dto)
    {
        Result result = new Result();

        String orderInstanceId = dto.getId();
        String orderDefId = dto.getOrdeRodeObj();

        if(StringUtil.isNullOrEmpty(orderInstanceId) || StringUtil.isNullOrEmpty(orderDefId))
        {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("单据实例ID或单据定义对象ID为空");
            return result;
        }

        //提交流程方法,部门异动单
        try
        {
            bizFormService.submitWorkFlow(orderDefId, orderInstanceId);
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }

        //只有提交流程正常了才修改单据状态
        try
        {
            //更新异动单状态为待审状态
            deptStuChangeService.updateDeptOrderStatus(orderInstanceId, OrderConstants.ORDE_STATUS_2);
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
     * 将JSON对象转化为Entity，验证通过后返回Entity
     * @param jsonObject
     * @return
     * @throws Exception
     */
    private DeptstuchangeorderEntity jsonObject2Entity(JSONObject jsonObject) throws Exception
    {
        Object params = JSONObject.parse(jsonObject.toJSONString());
        // 驼峰转下划线
        JsonUtils.camel2UnderLine(params);
        DeptstuchangeorderEntity entity =JSONObject.parseObject(JSONObject.toJSONString(params),DeptstuchangeorderEntity.class);

        //入参校验
        if (StringUtil.isNullOrEmpty(entity.getOrde_rode_obj()))
        {
            throw new Exception("必须传递参数ordeRodeObj的值作为单据类型定义id保存");
        }
        if (StringUtil.isNullOrEmpty(entity.getOrde_adduser()))
        {
            throw new Exception("必须传递参数ordeAddUser的值作为制单人id保存");
        }
        if (StringUtil.isNullOrEmpty(entity.getOrde_duty()))
        {
            throw new Exception("必须传递参数ordeDuty的值作为制单岗位id保存");
        }
        if (StringUtil.isNullOrEmpty(entity.getOrde_dept()))
        {
            throw new Exception("必须传递参数ordeDept的值作为制单部门id保存");
        }
        return entity;
    }

    /**
     * 获取单据的基本信息包括 单据id,生效日期,单据类型,单据备注信息,单据单号
     *
     * @param id
     * @return
     * @throws Exception
     */
    private JSONObject loadOrderBaseInfo(String id , String orderType)
    {
        // 待返回的数据
        JSONObject result = new JSONObject();
        try
        {
            OrmParam ormParam = getOrderProperty();
            // 单据id,生效日期,单据类型,单据备注信息,单据单号
            ormParam.addColumn(EdmSysColumn.ID)
                    .addColumn(EdmProperty.EDMD_CLASS)
                    .addColumn(DeptstuchangeorderProperty.ODSC_BEG)
                    .addColumn(DeptstuchangeorderProperty.ODSC_DEPT_BEG)
                    .addColumn(DeptstuchangeorderProperty.ODSC_REMARK)
                    .addColumn(DeptstuchangeorderProperty.ODSC_TYPE)
                    .addColumn(EdmSysColumn.CRETIME)
                    .addColumn(EdmSysColumn.CREUSER)
                    .addColumn(EdmSysColumn.MODTIME)
                    .addColumn(EdmSysColumn.CREUSER);
            // 条件: id  odsc_type
            String whereExp = OrmParam.and(ormParam.getEqualXML(EdmSysColumn.ID,id),ormParam.getEqualXML(DeptstuchangeorderProperty.ODSC_TYPE,orderType));
            ormParam.setWhereExp(whereExp);

            //查询部门异动单
            List<DeptstuchangeorderEntity> entityList = ormService.selectBeanList(DeptstuchangeorderEntity.class,ormParam);
            if (!entityList.isEmpty())
            {
                DeptstuchangeorderEntity entity = entityList.get(0);
                DeptStuChangeOrderDTO dto = JSONObject.parseObject(JSONObject.toJSONString(entity),DeptStuChangeOrderDTO.class);
                //制单人ID
                String empId = NullUtils.valueOf(entity.getOrde_adduser());
                // 制单部门id
                String deptId = NullUtils.valueOf(entity.getOrde_dept());
                // 制单岗位id
                String postId = NullUtils.valueOf(entity.getOrde_duty());

                //通用查询条件参数
                Map<String,Object> paramMap = null;
                //通用返回值参数
                List<String>  columnList = null;

                //查询员工的姓名和工号，作为制单人名字和制单人工号
                if(!StringUtil.isNullOrEmpty(empId))
                {
                    //设置查询参数
                    paramMap = new HashMap<>();
                    paramMap.put(EdmSysColumn.ID+",equal",empId);
                    //设置返回参数
                    columnList = new ArrayList<>();
                    columnList.add(EmployeeConstants.REMP_NAME);
                    columnList.add(EmployeeConstants.REMP_NO);
                    //查询员工信息
                    List<Map<String,Object>> mapList= getBaseInfoByConditions(paramMap,columnList,EmployeeEntity.class);
                    if(!mapList.isEmpty())
                    {
                        Map<String,Object> baseMap = mapList.get(0);
                        // 制单人名字
                        dto.setOrdeAdduserName((String) baseMap.get(EmployeeConstants.REMP_NAME));
                        // 制单人工号
                        dto.setOrdeAdduserNo((String) baseMap.get(EmployeeConstants.REMP_NO));
                    }
                }

                //查询部门名称
                if(!StringUtil.isNullOrEmpty(deptId))
                {
                    //设置查询参数
                    paramMap = new HashMap<>();
                    paramMap.put(EdmSysColumn.ID+",equal",deptId);
                    //设置返回参数
                    columnList = new ArrayList<>();
                    columnList.add(DepttreeProperty.MDEP_NAME);
                    //查询部门信息
                    List<Map<String,Object>> mapList= getBaseInfoByConditions(paramMap,columnList,DepttreeEntity.class);
                    if(!mapList.isEmpty())
                    {
                        Map<String, Object> baseMap = mapList.get(0);
                        dto.setOrdeDeptName((String) baseMap.get(DepttreeProperty.MDEP_NAME));
                    }
                }

                //查询岗位名称
                if(!StringUtil.isNullOrEmpty(postId))
                {
                    //设置查询参数
                    paramMap = new HashMap<>();
                    paramMap.put(EdmSysColumn.ID+",equal",postId);
                    //设置返回参数
                    columnList = new ArrayList<>();
                    columnList.add(JobpositionProperty.RPOS_NAME);
                    //查询岗位信息
                    List<Map<String,Object>> mapList= getBaseInfoByConditions(paramMap,columnList,JobpositionEntity.class);
                    if(!mapList.isEmpty())
                    {
                        Map<String, Object> baseMap = mapList.get(0);
                        dto.setOrdeDutyName((String) baseMap.get(JobpositionProperty.RPOS_NAME));
                    }
                }

                //object转JSON对象或字符串时，会丢失字段值为空null/""的属性，为了保证属性不丢失,
                // 需要加上SerializerFeature.WriteMapNullValue和SerializerFeature.WriteNullStringAsEmpty这两个属性
                String json = JSONObject.toJSONString(dto,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty);
                Object params = JSONObject.parse(json);
                JsonUtils.underLine2Camel(params);
                result = JSONObject.parseObject(JSONObject.toJSONString(params,SerializerFeature.WriteMapNullValue,SerializerFeature.WriteNullStringAsEmpty));
                //生效日期
                if(null != dto.getOdscBeg())
                {
                    result.put("odscBeg",DateUtils.parseDateToStr(dto.getOdscBeg(),DateUtils.DATE_YYYY_MM_DD));
                }
                //单据生效时间
                if(null != dto.getOrdeEffdate())
                {
                    result.put("ordeEffdate",DateUtils.parseDateToStr(dto.getOrdeEffdate(),DateUtils.DATE_YYYY_MM_DD_HH_MM_SS));
                }
                //制单时间
                if(null != dto.getOrdeDate())
                {
                    result.put("ordeDate",DateUtils.parseDateToStr(dto.getOrdeDate(),DateUtils.DATE_YYYY_MM_DD_HH_MM_SS));
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
        return result;
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

    /**
     * 根据传入的参数和返回的字段查询Class对象的信息
     * @param paramMap 传入的参数,入参字符串：column + 符号（equal、lessThan、greaterThan、in等等）
     * @param columnList 返回的字段
     * @return
     * @throws Exception
     */
    @Override
    public List<Map<String, Object>> getBaseInfoByConditions(Map<String,Object> paramMap, List<String> columnList, Class c)
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
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return result;
    }

    /**
     * 查询部门结构异动单据，并根据单据类型odscType打印相应日志
     * @param ormParam
     * @param odscType
     * @return
     */
    @Override
    public List<DeptStuChangeOrderDTO> findDeptStuChangeOrderList(OrmParam ormParam , String odscType)
    {
        List<DeptStuChangeOrderDTO> deptStuChangeOrderDTOList = new ArrayList<>();
        try
        {
            List<DeptstuchangeorderEntity> deptstuchangeorderEntityList = ormService.selectBeanList(DeptstuchangeorderEntity.class ,ormParam);

            if(deptstuchangeorderEntityList.isEmpty())
            {
                //一条异动单都没有，存在异常数据
                if(null == odscType)
                {
                    LOGGER.info("未查询到任何单据类型为‘调动/注销’的待审单据");
                }
                else
                {
                    LOGGER.info(String.format("-->部门异动单单据批准通过方法-查询部门异动单单据-异常：未查询到任何有效数据, 单据类型[部门%s]",DeptStuChangeOrderConstants.ODSC_TYPE_MOVE_ID.equals(odscType)?"调动":"注销"));
                }
                return deptStuChangeOrderDTOList;
            }

            //下划线修改为驼峰原则
            deptstuchangeorderEntityList.forEach(entity -> {
                String json = JSONObject.toJSONString(entity);
                DeptStuChangeOrderDTO dto = JSONObject.parseObject(json,DeptStuChangeOrderDTO.class);
                deptStuChangeOrderDTOList.add(dto);
            });

        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return deptStuChangeOrderDTOList;
    }

    /**
     * 根据单据对象Id，查找单据对象
     * @param orderIdValue
     * @param odscType
     * @return
     */
    @Override
    public DeptStuChangeOrderDTO findDeptStuChangeOrderById(String orderIdValue , String odscType)
    {
        DeptStuChangeOrderDTO deptStuChangeOrderDTOResult = null;
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(OrderProperty.ORDE_NBR);
        String whereExp = ormParam.getEqualXML(EdmSysColumn.ID, orderIdValue);
        ormParam.setWhereExp(whereExp);
        List<DeptStuChangeOrderDTO> deptStuChangeOrderDTOList = findDeptStuChangeOrderList(ormParam , odscType);

        if(!deptStuChangeOrderDTOList.isEmpty())
        {
            deptStuChangeOrderDTOResult = deptStuChangeOrderDTOList.get(0);
        }
        return deptStuChangeOrderDTOResult;
    }


    /**
     * 查询异动列表
     * @param ormParam
     * @return
     */
    @Override
    public List<OdscChagSetDTO> findDeptOdscChagSetList(OrmParam ormParam)
    {
        //所有调动单据中包含的部门对象
        List<OdscChagSetDTO> odscChagSetDTOList = new ArrayList<>();
        try
        {
            List<OdscOdscChagSetaEntity> odscOdscChagSetaEntityList = ormService.selectBeanList(OdscOdscChagSetaEntity.class,ormParam);
            odscOdscChagSetaEntityList.forEach(item -> { odscChagSetDTOList.add(JSONObject.parseObject(JSONObject.toJSONString(item),OdscChagSetDTO.class)); });
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,e.getMessage());
        }
        return odscChagSetDTOList;
    }

    /**
     * 由于异动列表可能有部门被删除，先执行清空异动列表所有记录，再做新增
     * @param delOdscChagSetIdList 待删除的异动列表
     */
    @Override
    public boolean deleteOdscChagSetById(List<String> delOdscChagSetIdList)
    {
        boolean b = true;
        try
        {
            OrmParam ormParam = new OrmParam();
            ormParam.setWhereExp(ormParam.getInXML(EdmSysColumn.ID,delOdscChagSetIdList.toArray()));
            int num = ormService.delete(OdscOdscChagSetaEntity.class,ormParam);
            //有数据但是没有删除
            if(!delOdscChagSetIdList.isEmpty() && num == 0)
            {
                b = false;
            }
        }
        catch (Exception e)
        {
            if(LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }
        return b;
    }

    /**
     * 如果该单据已提交，retCode为0；没有提交，retCode为1
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
            DeptstuchangeorderEntity entity = ormService.load(DeptstuchangeorderEntity.class,orderId);

            if(null != entity && OrderConstants.ORDE_STATUS_2.equals(entity.getOrde_status()))
            {
                result.setErrMsg(String.format("单据%s已经提交过，不能再次提交" , entity.getOrde_nbr()));
                result.setRetCode(Result.RECODE_ERROR);
                return result;
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

    private CurrentSessionEntity getSession(){
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
            if (LOGGER.isDebugEnabled())
            {
                e.printStackTrace();
            }
            throw new ApplicationException(Result.RECODE_ERROR,"登录超时，请重新登录");
        }
        return sessionEntity;
    }

}