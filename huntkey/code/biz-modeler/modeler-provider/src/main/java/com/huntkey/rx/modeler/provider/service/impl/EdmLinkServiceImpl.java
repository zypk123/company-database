package com.huntkey.rx.modeler.provider.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.EdmLinkVO;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.common.util.ExcelUtil;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.*;
import com.huntkey.rx.modeler.provider.feign.FormulaClient;
import com.huntkey.rx.modeler.provider.feign.ScheduleClient;
import com.huntkey.rx.modeler.provider.service.EdmLinkService;
import com.huntkey.rx.sceo.schedule.commom.model.JobBaseInfo;
import com.huntkey.rx.sceo.schedule.commom.model.JobInfo;
import com.huntkey.rx.sceo.schedule.commom.model.JobPlan;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//linziy

/**
 * Created by licj on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class EdmLinkServiceImpl implements EdmLinkService {

    private static Logger log = LoggerFactory.getLogger(EdmLinkServiceImpl.class);

    @Autowired
    private EdmConvoluteMapper edmConvoluteMapper;

    @Autowired
    private EdmLinkMapper edmLinkMapper;
    @Autowired
    private EdmConnectMapper edmConnectMapper;

    @Autowired
    private EdmPropertyMapper edmPropertyMapper;

    @Autowired
    private EdmClassMapper edmClassMapper;

    @Autowired
    private ScheduleClient scheduleClient;

    @Autowired
    private EdmPropertyGroupMapper edmPropertyGroupMapper;

    @Autowired
    private FormulaClient formulaClient;

    @Autowired
    private EdmMethodMapper edmMethodMapper;

    private List<EdmLinkVO> selectEdmLinkVOListByEdmLinkList(List<EdmLink> edmLinkList) {
        List<EdmLinkVO> resultList = new ArrayList<EdmLinkVO>();
        for (EdmLink edmLink : edmLinkList) {
            EdmLinkVO edmLinkVO = new EdmLinkVO();
            //查询所属类名
            String edmpEdmcId = "";
            if (edmLink.getEdmlEdmpId() != null) {
                edmpEdmcId = edmConvoluteMapper.selectEdmpEdmcIdByEdcoEdmpId(edmLink.getEdmlEdmpId());
            }
            if (edmpEdmcId != null) {
                edmLinkVO.setEdmcName(edmConvoluteMapper.selectEdmcNameByEdmpEdmcId(edmpEdmcId));
            } else {
                edmLinkVO.setEdmcName("");
            }
            //查询属性名称
            if (edmLink.getEdmlEdmpId() != null) {
                edmLinkVO.setEdmpName(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(edmLink.getEdmlEdmpId()));
            } else {
                edmLinkVO.setEdmpName("");
            }
            //查询关联类名
            String edmpEdmcId_link = "";
            if (edmLink.getEdmlEdmpLinkId() != null) {
                edmpEdmcId_link = edmConvoluteMapper.selectEdmpEdmcIdByEdcoEdmpId(edmLink.getEdmlEdmpLinkId());
                edmLinkVO.setEdmlEdmpNameLink(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(edmLink.getEdmlEdmpLinkId()));
            } else {
                edmLinkVO.setEdmlEdmpNameLink("");
            }
            if (edmpEdmcId_link != null) {
                edmLinkVO.setEdmlEdmcNameLink(edmConvoluteMapper.selectEdmcNameByEdmpEdmcId(edmpEdmcId_link));
            } else {
                edmLinkVO.setEdmlEdmcNameLink("");
            }
            //查询关联属性名称
            //edmLinkVO.setEdmlEdmpNameLink(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(edmLink.getEdmlEdmpLinkId()));
            edmLinkVO.setEdmlCond(edmLink.getEdmlCond());
            edmLinkVO.setEdmlFormula(edmLink.getEdmlFormula());
            resultList.add(edmLinkVO);
        }
        return resultList;
    }

    @Override
    @Transactional(readOnly = false)
    public int insert(EdmLink edmLink) {
        String strUuid = edmLink.getId();
        Date dateTime = new Date();
        edmLink.setId(strUuid);
        edmLink.setAddtime(dateTime);
        edmLink.setModtime(dateTime);
        edmLink.setIsDel((byte) 0);
        return edmLinkMapper.insertSelective(edmLink);
    }

    @Override
    @Transactional(readOnly = false)
    public void insertVO(EdmLinkVO edmLinkVO) {
        Date dateTime = new Date();
        EdmLink edmLink = new EdmLink();
        String strUuid = UuidCreater.uuid();
        edmLinkVO.setId(strUuid);
        edmLink.setId(strUuid);
        edmLink.setEdmlEdmpId(edmLinkVO.getEdmlEdmpId());
        edmLink.setEdmlEdmpLinkId(edmLinkVO.getEdmlEdmpLinkId());
        edmLink.setEdmlFormula(edmLinkVO.getEdmlFormula());
        edmLink.setEdmlCond(edmLinkVO.getEdmlCond());
        edmLink.setAdduser(edmLinkVO.getAdduser());
        edmLink.setModuser(edmLinkVO.getModuser());
        edmLink.setIsDel((byte) 0);
        edmLink.setAddtime(dateTime);
        edmLink.setModtime(dateTime);
        edmLinkMapper.insertSelective(edmLink);
        EdmConnect edmConnect = edmConnectMapper.getEdmConnectPropertieByEdmpId(edmLinkVO.getEdmlEdmpLinkId());
        if (edmConnect == null) {
            edmConnect = new EdmConnect();
            edmConnect.setId(UuidCreater.uuid());
            edmConnect.setEdcnEdmpId(edmLinkVO.getEdmlEdmpLinkId());
            edmConnect.setEdcnLinkPreservable(edmLinkVO.getEdcnLinkPreservable());
            edmConnect.setEdcnUpdateType(edmLinkVO.getEdclUpdateType());
            edmConnect.setEdcnUpdateTime(edmLinkVO.getEdclUpdateTime());
            edmConnect.setAsyncTypePriority(edmLinkVO.getAsyncTypePriority());
            edmConnect.setEdcnType(edmLinkVO.getEdclType());
            edmConnect.setAddtime(dateTime);
            edmConnect.setIsDel((byte) 0);
            edmConnectMapper.insertSelective(edmConnect);
        } else {
            setConnect(edmConnect, edmLinkVO);
            edmConnect.setModtime(dateTime);
            edmConnectMapper.updateByPrimaryKeySelective(edmConnect);
        }

        insertGroup(edmLinkVO.getEdmlEdmpId(), edmLinkVO.getEdmlEdmpLinkId(), null, true);//插入属性分组表
        //创建调度执行计划
        //createPlan(edmLinkVO);

    }

    @Override
    @Transactional(readOnly = false)
    public void updateVO(EdmLinkVO edmLinkVO, String oldLinkId, boolean isPlan) {

        //link 与 connect 表，id与edmpid 值一致
        Date dateTime = new Date();
        //更新link表
        EdmLink edmLink = new EdmLink();
        edmLink.setId(edmLinkVO.getId());
        edmLink.setEdmlEdmpId(edmLinkVO.getEdmlEdmpId());
        edmLink.setEdmlEdmpLinkId(edmLinkVO.getEdmlEdmpLinkId());
        edmLink.setEdmlCond(edmLinkVO.getEdmlCond());
        edmLink.setEdmlFormula(edmLinkVO.getEdmlFormula());
        edmLink.setModtime(dateTime);
        edmLink.setModuser(edmLinkVO.getModuser());
        edmLinkMapper.updateByPrimaryKeySelective(edmLink);

        EdmConnect edmConnect = edmConnectMapper.getEdmConnectPropertieByEdmpId(oldLinkId);
        //更新Connect 表
        if (edmConnect != null) {
            setConnect(edmConnect, edmLinkVO);
            edmConnect.setModtime(dateTime);
            edmConnectMapper.updateByPrimaryKeySelective(edmConnect);
            insertGroup(edmLinkVO.getEdmlEdmpId(), edmLinkVO.getEdmlEdmpLinkId(), oldLinkId, false);//插入属性分组表
            //if(isPlan){//true 创建执行计划， false 不创建
            //createPlan(edmLinkVO);
            // }
        } else {
            throw new NullPointerException("update connect failed");
        }
    }

    //插入属性分组
    private void insertGroup(String edmpId, String linkId, String oldLink, boolean isInsert) {
        //List<EdmLink> downLinks = edmLinkMapper.selectEdmLinkListByPropertyId(linkId);//往下找所有关联
        EdmProperty edmProperty = edmPropertyMapper.selectByPrimaryKey(linkId);
        boolean isResource = false;
        //获取所有资源类id
        if (edmProperty == null) {
            return;
        }
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmProperty.getEdmpEdmcId());
        if (edmClass == null) {
            return;
        }
        String resourceId = edmClassMapper.selectEdmcIdByEdmdIdAndName(edmClass.getEdmcEdmdId(), Constant.EDM_RESOURCE_NAME);
        if (!StringUtil.isNullOrEmpty(resourceId)) {
            isResource = getAllResourceClass(resourceId, edmProperty.getEdmpEdmcId());
        }
        List<String> groupIds = new ArrayList<>();
        groupIds = getAllUpLink(groupIds, edmpId);// A->B->C,往B的上层关联遍历,至少一个值
        //更新关联时的操作
        if (!isInsert) {
            updateIsDelPids(groupIds, edmpId, oldLink);
        }

        EdmPropertyGroupExample edmPropertyGroupExample = new EdmPropertyGroupExample();
        edmPropertyGroupExample.createCriteria().andEdpgPropertyGroupEqualTo(linkId).andIsDelNotEqualTo((byte) 1);
        List<EdmPropertyGroup> edmPropertyGroupList = edmPropertyGroupMapper.selectByExample(edmPropertyGroupExample);
        List<EdmPropertyGroup> edmPropertyGroups = new ArrayList<>();//需要插入放入分组数据
        Date date = new Date();
        //初始化新建的关联分组
        EdmPropertyGroup edmPropertyGroup2 = new EdmPropertyGroup();
        edmPropertyGroup2.setEdpgEdmcId(edmProperty.getEdmpEdmcId());
        edmPropertyGroup2.setEdpgLinkId(edmpId);
        edmPropertyGroup2.setEdpgEdmpId(linkId);
        edmPropertyGroup2.setIsDel((byte) 0);
        edmPropertyGroup2.setAddtime(date);
        edmPropertyGroup2.setModtime(date);
        if (isResource) {
            edmPropertyGroup2.setIsSource((byte) 1);
        } else {
            edmPropertyGroup2.setIsSource((byte) 0);
        }
        edmPropertyGroupList.add(edmPropertyGroup2);
        //插入分组表
        edmPropertyGroupMapper.updateIsDelByGroupId(linkId);//根据组id更新isDel（逻辑删除）
        for (String groupId : groupIds) {
            for (EdmPropertyGroup edmPropertyGroup : edmPropertyGroupList) {
                EdmPropertyGroup edmPropertyGroup1 = new EdmPropertyGroup();
                edmPropertyGroup1.setId(UuidCreater.uuid());
                edmPropertyGroup1.setEdpgPropertyGroup(groupId);
                edmPropertyGroup1.setEdpgEdmcId(edmPropertyGroup.getEdpgEdmcId());
                edmPropertyGroup1.setEdpgLinkId(edmPropertyGroup.getEdpgLinkId());
                edmPropertyGroup1.setEdpgEdmpId(edmPropertyGroup.getEdpgEdmpId());
                edmPropertyGroup1.setIsDel((byte) 0);
                edmPropertyGroup1.setAddtime(date);
                edmPropertyGroup1.setModtime(date);
                edmPropertyGroup1.setIsSource(edmPropertyGroup.getIsSource());
                edmPropertyGroups.add(edmPropertyGroup1);
            }
        }
        //批量插入分组
        edmPropertyGroupMapper.insertBatch(edmPropertyGroups);
    }

    //判断关联的类是否是资源类
    private boolean getAllResourceClass(String resourceid, String classid) {
        if (StringUtil.isNullOrEmpty(resourceid)) {
            if (resourceid.equals(classid)) {
                return true;
            }
            List<EdmClass> list = edmClassMapper.getEdmClassNameByPid(null, resourceid);
            if (list.size() > 0) {
                for (EdmClass edmClass : list) {
                    if (edmClass.getId().equals(classid)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //向上遍历关联关系
    private List<String> getAllUpLink(List<String> edmpIds, String edmpId) {
        List<EdmLink> list = edmLinkMapper.selectEdmLinkPropertiesListByEdmlEdmpLinkId(edmpId);
        if (list.size() > 0) {
            for (EdmLink edmLink : list) {
                getAllUpLink(edmpIds, edmLink.getEdmlEdmpId());
            }
        } else {
            edmpIds.add(edmpId);
        }
        return edmpIds;
    }

    //向下遍历关联关系
    private void updateDownUpLink(String oldGroupId, String edmplink) {
        List<EdmLink> list = edmLinkMapper.selectEdmLinkListByPropertyId(edmplink);
        if (list.size() > 0) {
            for (EdmLink edmLink : list) {
                EdmPropertyGroupExample edmPropertyGroupExample = new EdmPropertyGroupExample();
                edmPropertyGroupExample.createCriteria().andEdpgPropertyGroupEqualTo(oldGroupId).andEdpgLinkIdEqualTo(edmplink).andEdpgEdmpIdEqualTo(edmLink.getEdmlEdmpLinkId()).andIsDelNotEqualTo((byte) 1);
                List<EdmPropertyGroup> edmPropertyGroupList = edmPropertyGroupMapper.selectByExample(edmPropertyGroupExample);
                if (!CollectionUtils.isEmpty(edmPropertyGroupList)) {
                    edmPropertyGroupMapper.updateBatch(edmPropertyGroupList);
                }
                updateDownUpLink(oldGroupId, edmLink.getEdmlEdmpLinkId());
            }
        }
    }

    //创建执行计划
    public void createPlan(EdmLinkVO edmLinkVO) {
        EdmProperty edmProperty = edmPropertyMapper.selectByPrimaryKey(edmLinkVO.getEdmlEdmpLinkId());
        if (edmProperty != null) {
            String nameEN = edmClassMapper.getEdmNameEnById(edmProperty.getEdmpEdmcId());
            if (!StringUtil.isNullOrEmpty(nameEN)) {
                nameEN = nameEN + Constant.LINK_RECORDS;
                Result result = scheduleClient.checkPlan(Constant.SCHEDULE_JOBID, nameEN);
                Integer retCode = result.getRetCode();
                if (retCode == -2) {
                    throw new RuntimeException("调度异常");
                }
                if (retCode == 1) { //已存在未执行
                    String data = (String) result.getData();
                    JobBaseInfo jobBaseInfo = new JobBaseInfo();
                    jobBaseInfo.setJobId(Constant.SCHEDULE_JOBID);
                    jobBaseInfo.setJobName(nameEN);
                    List<JobPlan> planList = new ArrayList<>();
                    JobPlan jobPlan = new JobPlan();
                    jobPlan.setJobId(data);
                    planList.add(jobPlan);
                    List<String> params = new ArrayList<>();
                    params.add(nameEN);
                    JobInfo jobInfo = new JobInfo();
                    jobInfo.setBaseInfo(jobBaseInfo);
                    jobInfo.setJobPlans(planList);
                    jobInfo.setParams(params);
                    Result result2 = scheduleClient.commit(jobInfo);
                    if (result2.getRetCode() != 1) {
                        throw new RuntimeException("调度异常");
                    }

                } else if (retCode == 3) {//不存在
                    JobBaseInfo jobBaseInfo = new JobBaseInfo();
                    jobBaseInfo.setJobId(Constant.SCHEDULE_JOBID);
                    jobBaseInfo.setJobName(nameEN);
                    List<JobPlan> planList = new ArrayList<>();
                    JobPlan jobPlan = new JobPlan();
                    jobPlan.setJobPlanName(nameEN);
                    jobPlan.setJobPlanType("execute_repeat");
                    jobPlan.setJobPlanPeriodType("day");
                    jobPlan.setJobPlanPeriod(1);
                    jobPlan.setOnceOrTimes(2);
                    jobPlan.setJobPlanPeriod2(5);
                    jobPlan.setPeriod2Unit("minute");
                    jobPlan.setIsUse(1);
                    planList.add(jobPlan);
                    List<String> params = new ArrayList<>();
                    params.add(nameEN);
                    JobInfo jobInfo = new JobInfo();
                    jobInfo.setBaseInfo(jobBaseInfo);
                    jobInfo.setJobPlans(planList);
                    jobInfo.setParams(params);
                    Result result2 = scheduleClient.commit(jobInfo);
                    if (result2.getRetCode() != 1) {
                        throw new RuntimeException("调度异常");
                    }
                }
            }
        }
    }

    /**
     * 逻辑删除组信息以及组的子关联信息
     * @param groupIds 组的ids
     * @param edmpId   联动类ID
     * @param linkId   关联类ID
     */
    private void updateIsDelPids(List<String> groupIds, String edmpId, String linkId) {

        if (CollectionUtils.isEmpty(groupIds)) {
            groupIds = new ArrayList<>();
            groupIds = getAllUpLink(groupIds, edmpId);// A->B->C,往B的上层关联遍历,至少一个值
        }

        if (CollectionUtils.isEmpty(groupIds)) {
            return;
        }

        //更新关联时的操作
        List<EdmPropertyGroup> edmPropertyGroups = new ArrayList<>();
        for (String groupId : groupIds) {
            updateDownUpLink(groupId, linkId);
            EdmPropertyGroup edmPropertyGroup = new EdmPropertyGroup();
            edmPropertyGroup.setEdpgPropertyGroup(groupId);
            edmPropertyGroup.setEdpgLinkId(edmpId);
            edmPropertyGroup.setEdpgEdmpId(linkId);
            edmPropertyGroups.add(edmPropertyGroup);
        }
        //批量删除
        edmPropertyGroupMapper.updateBatch(edmPropertyGroups);//根据组id更新isDel（逻辑删除）
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(String id) {
        EdmLink edmLink = edmLinkMapper.selectByPrimaryKey(id);
        if (edmLink == null) {
            return 0;
        }

        //根据联动属性和关联属性逻辑删除分组表
        updateIsDelPids(null, edmLink.getEdmlEdmpId(), edmLink.getEdmlEdmpLinkId());
        //删除关联
        edmConnectMapper.LogicDeleteByEdmpId(edmLink.getEdmlEdmpLinkId());
        return edmLinkMapper.LogicDeleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteIds(String[] ids) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {

                EdmLink edmLink = edmLinkMapper.selectByPrimaryKey(id);
                if (edmLink == null) {
                    continue;
                }

                //根据联动属性和关联属性逻辑删除分组表
                updateIsDelPids(null, edmLink.getEdmlEdmpId(), edmLink.getEdmlEdmpLinkId());

                //删除关联
                edmConnectMapper.LogicDeleteByEdmpId(edmLink.getEdmlEdmpLinkId());
                //删除关联的对象定位公式
                formulaClient.removeCondition(edmLink.getEdmlFormula());

                //逻辑删除关联
                edmLinkMapper.LogicDeleteByPrimaryKey(id);
            }
        }
    }

    @Override
    @Transactional(readOnly = false)
    public int update(EdmLink edmLink) {
        Date dateTime = new Date();
        edmLink.setModtime(dateTime);
        return edmLinkMapper.updateByPrimaryKey(edmLink);
    }

    //EdmLinkVO的部分字段转为EdmConnect
    private void setConnect(EdmConnect edmConnect, EdmLinkVO edmLinkVO) {
        if (!StringUtil.isNullOrEmpty(edmLinkVO.getEdmlEdmpLinkId())) {
            edmConnect.setEdcnEdmpId(edmLinkVO.getEdmlEdmpLinkId());
        }
        if (!StringUtil.isNullOrEmpty(edmLinkVO.getEdcnLinkPreservable())) {
            edmConnect.setEdcnLinkPreservable(edmLinkVO.getEdcnLinkPreservable());
        }
        if (!StringUtil.isNullOrEmpty(edmLinkVO.getEdclUpdateType())) {
            edmConnect.setEdcnUpdateType(edmLinkVO.getEdclUpdateType());
        }
        if (!StringUtil.isNullOrEmpty(edmLinkVO.getEdclUpdateTime())) {
            edmConnect.setEdcnUpdateTime(edmLinkVO.getEdclUpdateTime());
        }
        if (!StringUtil.isNullOrEmpty(edmLinkVO.getAsyncTypePriority())) {
            edmConnect.setAsyncTypePriority(edmLinkVO.getAsyncTypePriority());
        }
        if (!StringUtil.isNullOrEmpty(edmLinkVO.getEdclType())) {
            edmConnect.setEdcnType(edmLinkVO.getEdclType());
        }

    }

    @Override
    public Pagination<EdmLink> selectByExample(String edmlEdmpId, String edmlEdmpLinkId, String edmlCond, String edmlFormula, Byte edcnLinkPreservable, Byte edclUpdateType, String edclUpdateTimeliness, String edclUpdateTime, int pageNum, int pageSize) {
        EdmLinkExample edmLinkExample = new EdmLinkExample();
        EdmLinkExample.Criteria criteria = edmLinkExample.createCriteria();
        if (edmlEdmpId != null) {
            criteria.andEdmlEdmpIdEqualTo(edmlEdmpId);
        }
        if (edmlEdmpLinkId != null) {
            criteria.andEdmlEdmpIdEqualTo(edmlEdmpLinkId);
        }
        if (edmlCond != null) {
            criteria.andEdmlCondLike(edmlCond);
        }
        if (edmlFormula != null) {
            criteria.andEdmlFormulaEqualTo(edmlFormula);
        }
        Byte is_del = 0;
        criteria.andIsDelEqualTo(is_del);

        Page page = PageHelper.startPage(pageNum, pageSize);
        List<EdmLink> linkList = edmLinkMapper.selectByExample(edmLinkExample);
        Pagination<EdmLink> pageInfo = new Pagination<EdmLink>(linkList, pageNum, pageSize, page.getTotal());
        return pageInfo;
    }

    /**
     * 判断关系的唯一性
     *
     * @param edmlEdmpId
     * @param edmlEdmpLinkId
     * @param id
     * @return
     */
    public boolean checkunique(String edmlEdmpId, String edmlEdmpLinkId, String id) {
        EdmLinkExample edmLinkExample = new EdmLinkExample();
        EdmLinkExample.Criteria criteria = edmLinkExample.createCriteria();
        if (id != null && !id.isEmpty()) {
            criteria.andIdNotEqualTo(id);
        }
        if (!edmlEdmpId.isEmpty()) {
            criteria.andEdmlEdmpIdEqualTo(edmlEdmpId);
        }
        if (!edmlEdmpLinkId.isEmpty()) {
            criteria.andEdmlEdmpLinkIdEqualTo(edmlEdmpLinkId);
        }
        long count = edmLinkMapper.checkUnique(edmLinkExample);
        if (0 < count) {
            return false;
        }
        return true;
    }

    /**
     * 根据两属性id 检查数据是否构成环路
     *
     * @param edmlEdmpId
     * @param edmlEdmpLinkId
     * @return
     */
    public boolean checkcircle(String edmlEdmpId, String edmlEdmpLinkId) {
        EdmLinkExample edmLinkExample = new EdmLinkExample();
        EdmLinkExample.Criteria criteria = edmLinkExample.createCriteria();
        if (edmlEdmpId.isEmpty() || edmlEdmpLinkId.isEmpty() || edmlEdmpId == edmlEdmpLinkId) {
            return true; //数据缺省，默认为已构成环路
        }
        List<String> edmlEdmpIdList = new ArrayList<String>();
        criteria.andEdmlEdmpLinkIdEqualTo(edmlEdmpLinkId);//通过被关联的数据反查关联项
        List<EdmLink> reslist = edmLinkMapper.selectEdmLinkPropertiesListByEdmlEdmpLinkId(edmlEdmpLinkId);
        if (reslist.size() == 0) {
            return false;
        }
        for (EdmLink epv : reslist) {
            if (edmlEdmpId.equals(epv.getEdmlEdmpId())) {
                return true;
            } else {
                if (!checkcircle(edmlEdmpId, epv.getEdmlEdmpId())) {
                    continue;
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public EdmLinkVO getById(String id) {
        EdmLinkVO edmLinkVO = null;
        EdmLink edmLink = edmLinkMapper.selectByPrimaryKey(id);
        if (edmLink != null) {
            edmLinkVO = ModelToVO.edmLinkToVO(edmLink);
            EdmConnect edmConnect = edmConnectMapper.getEdmConnectPropertieByEdmpId(edmLink.getEdmlEdmpLinkId());
            if (edmConnect != null) {
                edmLinkVO.setAsyncTypePriority(edmConnect.getAsyncTypePriority());
                edmLinkVO.setEdclUpdateTime(edmConnect.getEdcnUpdateTime());
                edmLinkVO.setEdcnLinkPreservable(edmConnect.getEdcnLinkPreservable());
                edmLinkVO.setEdclUpdateType(edmConnect.getEdcnUpdateType());
            }
        }
        return edmLinkVO;
    }

    @Override
    public String checkLinkId(String edmpId, String linkid) {
        String errorStr = null;
        if (StringUtil.isNullOrEmpty(linkid)) {
            errorStr = "被关联属性id不能为空";
            return errorStr;
        }
        if (StringUtil.isNullOrEmpty(edmpId)) {
            errorStr = "关联属性id不能为空";
            return errorStr;
        }
        if (edmpId.equals(linkid)) {
            errorStr = "属性不能关联自己";
            return errorStr;
        }
        if (judgeCircuit(edmpId, linkid)) {
            errorStr = "属性不能关联自己的回路属性";
            return errorStr;
        }
        EdmLinkExample edmLinkExample = new EdmLinkExample();
        edmLinkExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdmlEdmpIdEqualTo(edmpId).andEdmlEdmpLinkIdEqualTo(linkid);
        List<EdmLink> edmLinks = edmLinkMapper.selectByExample(edmLinkExample);

        if (edmLinks != null && edmLinks.size() > 0) {
            errorStr = "该属性已被关联";
            return errorStr;
        }
        return errorStr;
    }

    private boolean judgeCircuit(String edmpId, String linkid) {
        //获取属性的已有上级属性
        List<String> propertyIdList = selectCircuitProperties(edmpId);
        if (propertyIdList.size() < 1) {
            return false;
        }
        //判断是否产生回路
        if (propertyIdList.contains(linkid)) {
            return true;
        } else {
            return false;
        }
    }

    private List<String> selectCircuitProperties(String edmpId) {
        List<String> ret = new ArrayList<>();
        EdmLinkExample edmLinkExample = new EdmLinkExample();
        EdmLinkExample.Criteria criteria = edmLinkExample.createCriteria();
        criteria.andEdmlEdmpLinkIdEqualTo(edmpId).andIsDelNotEqualTo((byte) 1);
        List<EdmLink> list = edmLinkMapper.selectByExample(edmLinkExample);
        if (list.size() > 0) {
            for (EdmLink edmLink : list) {
                List<String> temp = selectCircuitProperties(edmLink.getEdmlEdmpId());
                if (temp.size() > 0) {
                    ret.addAll(temp);
                }
                ret.add(edmLink.getEdmlEdmpId());
            }
        }
        return ret;
    }

    @Override
    public String selectLinkidById(String id) {
        return edmLinkMapper.selectLinkidById(id);
    }

    @Override
    public List<List<EdmLinkVO>> getConnectsByCid(String id) {
        List<List<EdmLinkVO>> edmLinkVOS = null;
        EdmPropertyExample edmPropertyExample = new EdmPropertyExample();
        EdmPropertyExample.Criteria criteria = edmPropertyExample.createCriteria();
        criteria.andEdmpEdmcIdEqualTo(id).andIsDelNotEqualTo((byte) 1).andEdmpValueTypeNotEqualTo(Constant.PROPERTY_TYPE_ASSEMBLE);
        List<EdmProperty> edmProperties = edmPropertyMapper.selectByExample(edmPropertyExample);
        if (null != edmProperties && edmProperties.size() > 0) {
            edmLinkVOS = new ArrayList<>();
            for (EdmProperty edmProperty : edmProperties) {
                List<EdmLinkVO> edmLinks = selectEdmLinkVOS(edmProperty.getId());
                if (edmLinks.size() > 0) {
                    edmLinkVOS.add(edmLinks);
                }
            }
        }
        return edmLinkVOS;
    }

    @Override
    public List<EdmLinkVO> getConnectsByPid(String id) {
        List<EdmLinkVO> edmLinkVOS = new ArrayList<>();
        if (!StringUtil.isNullOrEmpty(id)) {
            edmLinkVOS = selectEdmLinkVOS(id);
        }
        return edmLinkVOS;
    }

    //递归获取关联的属性
    private List<EdmLinkVO> selectEdmLinkVOS(String edmpId) {
        //List<EdmLink> edmLinks = new ArrayList<>();
        List<EdmLinkVO> edmLinkVOS = new ArrayList<>();
        EdmLinkExample edmLinkExample = new EdmLinkExample();
        EdmLinkExample.Criteria criteria = edmLinkExample.createCriteria();
        criteria.andEdmlEdmpIdEqualTo(edmpId).andIsDelNotEqualTo((byte) 1);
        List<EdmLink> list = edmLinkMapper.selectByExample(edmLinkExample);
        if (null != list && list.size() > 0) {
            for (EdmLink edmLink : list) {
                EdmLinkVO edmLinkVO = ModelToVO.edmLinkToVO(edmLink);
                EdmConnect edmConnect = edmConnectMapper.getEdmConnectPropertieByEdmpId(edmLink.getEdmlEdmpLinkId());
                if (edmConnect != null) {
                    edmLinkVO.setAsyncTypePriority(edmConnect.getAsyncTypePriority());
                    edmLinkVO.setEdclUpdateTime(edmConnect.getEdcnUpdateTime());
                    edmLinkVO.setEdcnLinkPreservable(edmConnect.getEdcnLinkPreservable());
                    edmLinkVO.setEdclUpdateType(edmConnect.getEdcnUpdateType());

                    EdmMethod edmMethod = edmMethodMapper.selectByPrimaryKey(edmConnect.getEdcnType());
                    if (edmMethod != null) {
                        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(edmMethod.getEdmmEdmcId());
                        if (edmClass != null) {
                            edmLinkVO.setClassName(edmClass.getEdmcNameEn());
                        }
                        edmLinkVO.setEdclType(edmMethod.getEdmmName());
                    }
                }
                List<EdmLinkVO> temp = selectEdmLinkVOS(edmLink.getEdmlEdmpLinkId());
                if (null != temp && temp.size() > 0) {
                    edmLinkVO.setChildren(temp);
                }
                edmLinkVOS.add(edmLinkVO);
            }
        }
        return edmLinkVOS;
    }

    @Override
    @Transactional(readOnly = false)
    public void clearEdmlFormula(String id) {
        if (!StringUtil.isNullOrEmpty(id)) {
            edmLinkMapper.clearEdmlFormula(id);
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void moveEdmLink(String[] ids, EdmCond edmCond) {
        if (edmCond == null || ids == null || ids.length == 0) {
            return;
        }

        Date modTime = new Date();
        edmCond.setModtime(modTime);
        edmLinkMapper.moveLink(ids, edmCond);
    }

    @Override
    public List<EdmLinkVO> getEdmLinks(String edmpId, String edmlCond) {
        if (StringUtil.isNullOrEmpty(edmpId)|| StringUtil.isNullOrEmpty(edmlCond)) {
            return null;
        }
        List<EdmLink> edmLinkList = edmLinkMapper.getEdmLinks(edmpId, edmlCond);
        List<EdmLinkVO> edmLinkVOS = selectEdmLinkVOListByEdmLinkList(edmLinkList);
        return edmLinkVOS;
    }

    @Override
    public String getEdmlEdmpId(String edmlLinkId) {
        List<EdmLink> edmLinkList = edmLinkMapper.selectEdmLinkPropertiesListByEdmlEdmpLinkId(edmlLinkId);

        if (edmLinkList == null || edmLinkList.size() <= 0) {
            return "";
        }

        return edmLinkList.get(0).getEdmlEdmpId();
    }
}
