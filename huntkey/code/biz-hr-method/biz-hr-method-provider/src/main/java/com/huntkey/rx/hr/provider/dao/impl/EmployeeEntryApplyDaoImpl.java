package com.huntkey.rx.hr.provider.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.hr.common.constants.NumberConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.provider.client.InformationClient;
import com.huntkey.rx.hr.provider.dao.EmployeeEntryApplyDao;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.hr.provider.service.EmployeeService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by weijian on 2017/11/16.
 */
@Component
public class EmployeeEntryApplyDaoImpl implements EmployeeEntryApplyDao {
    @Autowired
    private OrmService ormService;
    @Autowired
    private BizFormService bizFormService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    InformationClient informationClient;

    @Override
    public JSONObject load(String id) throws Exception {
        EmployeeentryapplyEntity employeeentryapplyEntity = ormService.load(EmployeeentryapplyEntity.class, id);

        OrmParam famOrm  = new OrmParam();
        famOrm.setWhereExp(famOrm.getEqualXML("pid",id)).addOrderExpElement(SQLSortEnum.ASC,"oeeo_fam_seq");
        List<OeeoOeeoFamSetaEntity> famList = ormService.selectBeanList(OeeoOeeoFamSetaEntity.class,famOrm);
        if (famList.size() > 0) {
            employeeentryapplyEntity.setOeeo_fam_set(famList);
        }

        OrmParam skillOrm  = new OrmParam();
        skillOrm.setWhereExp(skillOrm.getEqualXML("pid",id)).addOrderExpElement(SQLSortEnum.ASC,"oeeo_skill_seq");
        List<OeeoOeeoSkillSetaEntity> skillList = ormService.selectBeanList(OeeoOeeoSkillSetaEntity.class, skillOrm);
        if (skillList.size() > 0) {
            employeeentryapplyEntity.setOeeo_skill_set(skillList);
        }

        OrmParam studyOrm  = new OrmParam();
        studyOrm.setWhereExp(studyOrm.getEqualXML("pid",id)).addOrderExpElement(SQLSortEnum.DESC,"oeeo_stu_beg");
        List<OeeoOeeoStudySetaEntity> studyList = ormService.selectBeanList(OeeoOeeoStudySetaEntity.class, studyOrm);
        if (studyList.size() > 0) {
            employeeentryapplyEntity.setOeeo_study_set(studyList);
        }

        OrmParam workOrm  = new OrmParam();
        workOrm.setWhereExp(workOrm.getEqualXML("pid",id)).addOrderExpElement(SQLSortEnum.DESC,"oeeo_work_beg");
        List<OeeoOeeoWorkSetaEntity> workList = ormService.selectBeanList(OeeoOeeoWorkSetaEntity.class, workOrm);
        if (workList.size() > 0) {
            employeeentryapplyEntity.setOeeo_work_set(workList);
        }
        /*List<OeeoOeeoFamSetaEntity> famList = employeeentryapplyEntity.loadOeeo_fam_set();
        if (famList.size() > 0) {
            employeeentryapplyEntity.setOeeo_fam_set(famList);
        }
        List<OeeoOeeoSkillSetaEntity> skillList = employeeentryapplyEntity.loadOeeo_skill_set();
        if (skillList.size() > 0) {
            employeeentryapplyEntity.setOeeo_skill_set(skillList);
        }
        List<OeeoOeeoStudySetaEntity> studyList = employeeentryapplyEntity.loadOeeo_study_set();
        if (studyList.size() > 0) {
            employeeentryapplyEntity.setOeeo_study_set(studyList);
        }
        List<OeeoOeeoWorkSetaEntity> workList = employeeentryapplyEntity.loadOeeo_work_set();
        if (workList.size() > 0) {
            employeeentryapplyEntity.setOeeo_work_set(workList);
        }*/
        JSONObject jsonObject = (JSONObject) JSONObject.toJSON(employeeentryapplyEntity);//转object
        return jsonObject;
    }

    @Override
    public Result insertEmployeeEntryApply(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        Result result = new Result();
        //入职单校验任职岗位是否为空岗
        if (!StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoDept()) && !StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoPost()) && employeeEntryApplyDTO.getOeeoType().equals("1")) {
            Boolean flag = false;
            List<Map<String, Object>> list = employeeService.deptEmptyPost(employeeEntryApplyDTO.getOeeoDept(),0);//查询所有空岗
            for (Map<String, Object> map : list) {
                if (employeeEntryApplyDTO.getOeeoPost().equals(map.get("id"))) {
                    flag = true;
                }
            }
            if (!flag) {
                result.setRetCode(Result.RECODE_VALIDATE_ERROR);
                result.setErrMsg("当前所选部门岗位已有职员入职，请重新选择");
                return result;
            }
        }
        //入职申请单单编号生成
        String orderNum = getCode(NumberConstants.PREFIX_EMPLOYEE_ENTRY_APPLY);
        employeeEntryApplyDTO.setOrdeNbr(orderNum);
        if (StringUtil.isNullOrEmpty(employeeEntryApplyDTO.getOeeoCode())) {
            String yggh = getCode("JobNumber");
            employeeEntryApplyDTO.setOeeoCode(yggh);
        }
        employeeEntryApplyDTO.setOrdeDate(new Date());//制单日期
        //数据保存
        JSONObject insertObject = JSONObject.parseObject(JSON.toJSONString(employeeEntryApplyDTO));//驼峰格式转下划线
        EmployeeentryapplyEntity employeeentryapplyEntity = JSONObject.parseObject(JSONObject.toJSONString(insertObject), EmployeeentryapplyEntity.class);
        String id = (String) ormService.insertSelective(employeeentryapplyEntity);
        //结果集数据保存
        //教育背景
        List<OeeoStudySetDTO> oeeoStu = employeeEntryApplyDTO.getOeeoStudySet();
        employeeEntryApplyDTO.setOeeoStudySet(null);
        if (oeeoStu != null && oeeoStu.size() > 0) {
            for (OeeoStudySetDTO oeeoStudySetDTO : oeeoStu) {
                oeeoStudySetDTO.setId(null);
                oeeoStudySetDTO.setPid(id);
                oeeoStudySetDTO.setClassname("employeeentryapply");
                JSONObject studyObject = (JSONObject) JSONObject.toJSON(oeeoStudySetDTO);
                OeeoOeeoStudySetaEntity oeeoOeeoStudySetaEntity = JSONObject.parseObject(JSONObject.toJSONString(studyObject), OeeoOeeoStudySetaEntity.class);
                oeeoOeeoStudySetaEntity.setCretime(new Date());
                oeeoOeeoStudySetaEntity.setCreuser(sessionEntity.getEmployeeId());
                ormService.insertSelective(oeeoOeeoStudySetaEntity);
            }
        }
        //家庭成员
        List<OeeoFamSetDTO> oeeoFam = employeeEntryApplyDTO.getOeeoFamSet();
        employeeEntryApplyDTO.setOeeoFamSet(null);
        if (oeeoFam != null && oeeoFam.size() > 0) {
            for (OeeoFamSetDTO oeeoFamSetDTO : oeeoFam) {
                oeeoFamSetDTO.setId(null);
                oeeoFamSetDTO.setPid(id);
                oeeoFamSetDTO.setClassname("employeeentryapply");
                JSONObject famObject = (JSONObject) JSONObject.toJSON(oeeoFamSetDTO);
                OeeoOeeoFamSetaEntity oeeoOeeoFamSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(famObject), OeeoOeeoFamSetaEntity.class);
                oeeoOeeoFamSetaEntity.setCretime(new Date());
                oeeoOeeoFamSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                ormService.insertSelective(oeeoOeeoFamSetaEntity);
            }
        }
        //工作经历
        List<OeeoWorkSetDTO> oeeoWork = employeeEntryApplyDTO.getOeeoWorkSet();
        employeeEntryApplyDTO.setOeeoWorkSet(null);
        if (oeeoWork != null && oeeoWork.size() > 0) {
            for (OeeoWorkSetDTO oeeoWorkSetDTO : oeeoWork) {
                oeeoWorkSetDTO.setId(null);
                oeeoWorkSetDTO.setPid(id);
                oeeoWorkSetDTO.setClassname("employeeentryapply");
                JSONObject workObject = (JSONObject) JSONObject.toJSON(oeeoWorkSetDTO);
                OeeoOeeoWorkSetaEntity oeeoOeeoWorkSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(workObject), OeeoOeeoWorkSetaEntity.class);
                oeeoOeeoWorkSetaEntity.setCretime(new Date());
                oeeoOeeoWorkSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                ormService.insertSelective(oeeoOeeoWorkSetaEntity);
            }
        }
        //专业技能
        List<OeeoSkillSetDTO> oeeoSkill = employeeEntryApplyDTO.getOeeoSkillSet();
        employeeEntryApplyDTO.setOeeoSkillSet(null);
        if (oeeoSkill != null && oeeoSkill.size() > 0) {
            for (OeeoSkillSetDTO oeeoSkillSetDTO : oeeoSkill) {
                oeeoSkillSetDTO.setId(null);
                oeeoSkillSetDTO.setPid(id);
                oeeoSkillSetDTO.setClassname("employeeentryapply");
                JSONObject skillObject = (JSONObject) JSONObject.toJSON(oeeoSkillSetDTO);
                OeeoOeeoSkillSetaEntity oeeoOeeoSkillSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(skillObject), OeeoOeeoSkillSetaEntity.class);
                oeeoOeeoSkillSetaEntity.setCretime(new Date());
                oeeoOeeoSkillSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                ormService.insertSelective(oeeoOeeoSkillSetaEntity);
            }
        }
        JSONObject dataObj = new JSONObject();
        dataObj.put("id", id);
        dataObj.put("ordeNbr", employeeEntryApplyDTO.getOrdeNbr());
        result.setData(dataObj);
        return result;
    }

    @Override
    public Result updateEmployeeEntryApply(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        Result result = new Result();
        //删除旧结果集数据，设置新结果集数据
        employeeEntryApplyDTO = setNewDataSet(employeeEntryApplyDTO);
        JSONObject updateObject = JSONObject.parseObject(JSON.toJSONString(employeeEntryApplyDTO));
        updateObject.remove("ordeAdduserName");
        updateObject.remove("ordeDutyName");
        updateObject.remove("ordeDeptName");
        EmployeeentryapplyEntity employeeentryapplyEntity = JSONObject.parseObject(JSONObject.toJSONString(updateObject), EmployeeentryapplyEntity.class);
        employeeentryapplyEntity.setModuser(sessionEntity.getEmployeeId());
        employeeentryapplyEntity.setModtime(new Date());
        ormService.update(employeeentryapplyEntity);
        JSONObject dataObj = new JSONObject();
        dataObj.put("id", employeeEntryApplyDTO.getId());
        dataObj.put("ordeNbr", employeeEntryApplyDTO.getOrdeNbr());
        result.setData(dataObj);
        return result;
    }

    private EmployeeEntryApplyDTO setNewDataSet(EmployeeEntryApplyDTO employeeEntryApplyDTO) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        String pid = employeeEntryApplyDTO.getId();
        //删除旧结果集
        OrmParam ormParam = new OrmParam();
        ormParam.setWhereExp(ormParam.getEqualXML("pid", pid));
        ormService.delete(OeeoOeeoStudySetaEntity.class, ormParam);//教育背景
        ormService.delete(OeeoOeeoSkillSetaEntity.class, ormParam);//专业技能
        ormService.delete(OeeoOeeoWorkSetaEntity.class, ormParam);//工作经历
        ormService.delete(OeeoOeeoFamSetaEntity.class, ormParam);//家庭成员
        //教育背景
        List<OeeoStudySetDTO> oeeoStu = employeeEntryApplyDTO.getOeeoStudySet();
        employeeEntryApplyDTO.setOeeoStudySet(null);
        if (oeeoStu != null && oeeoStu.size() > 0) {
            for (OeeoStudySetDTO oeeoStudySetDTO : oeeoStu) {
                oeeoStudySetDTO.setId(null);
                oeeoStudySetDTO.setPid(pid);
                oeeoStudySetDTO.setClassname("employeeentryapply");
                JSONObject insertObject = (JSONObject) JSONObject.toJSON(oeeoStudySetDTO);
                OeeoOeeoStudySetaEntity oeeoOeeoStudySetaEntity = JSONObject.parseObject(JSONObject.toJSONString(insertObject), OeeoOeeoStudySetaEntity.class);
                oeeoOeeoStudySetaEntity.setCretime(new Date());
                oeeoOeeoStudySetaEntity.setCreuser(sessionEntity.getEmployeeId());
                ormService.insertSelective(oeeoOeeoStudySetaEntity);
            }
        }
        //家庭成员
        List<OeeoFamSetDTO> oeeoFam = employeeEntryApplyDTO.getOeeoFamSet();
        employeeEntryApplyDTO.setOeeoFamSet(null);
        if (oeeoFam != null && oeeoFam.size() > 0) {
            for (OeeoFamSetDTO oeeoFamSetDTO : oeeoFam) {
                oeeoFamSetDTO.setId(null);
                oeeoFamSetDTO.setPid(pid);
                oeeoFamSetDTO.setClassname("employeeentryapply");
                JSONObject insertObject = (JSONObject) JSONObject.toJSON(oeeoFamSetDTO);
                OeeoOeeoFamSetaEntity oeeoOeeoFamSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(insertObject), OeeoOeeoFamSetaEntity.class);
                oeeoOeeoFamSetaEntity.setCretime(new Date());
                oeeoOeeoFamSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                ormService.insertSelective(oeeoOeeoFamSetaEntity);
            }
        }
        //工作经历
        List<OeeoWorkSetDTO> oeeoWork = employeeEntryApplyDTO.getOeeoWorkSet();
        employeeEntryApplyDTO.setOeeoWorkSet(null);
        if (oeeoWork != null && oeeoWork.size() > 0) {
            for (OeeoWorkSetDTO oeeoWorkSetDTO : oeeoWork) {
                oeeoWorkSetDTO.setId(null);
                oeeoWorkSetDTO.setPid(pid);
                oeeoWorkSetDTO.setClassname("employeeentryapply");
                JSONObject insertObject = (JSONObject) JSONObject.toJSON(oeeoWorkSetDTO);
                OeeoOeeoWorkSetaEntity oeeoOeeoWorkSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(insertObject), OeeoOeeoWorkSetaEntity.class);
                oeeoOeeoWorkSetaEntity.setCretime(new Date());
                oeeoOeeoWorkSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                ormService.insertSelective(oeeoOeeoWorkSetaEntity);
            }
        }
        //专业技能
        List<OeeoSkillSetDTO> oeeoSkill = employeeEntryApplyDTO.getOeeoSkillSet();
        employeeEntryApplyDTO.setOeeoSkillSet(null);
        if (oeeoSkill != null && oeeoSkill.size() > 0) {
            for (OeeoSkillSetDTO oeeoSkillSetDTO : oeeoSkill) {
                oeeoSkillSetDTO.setId(null);
                oeeoSkillSetDTO.setPid(pid);
                oeeoSkillSetDTO.setClassname("employeeentryapply");
                JSONObject insertObject = (JSONObject) JSONObject.toJSON(oeeoSkillSetDTO);
                OeeoOeeoSkillSetaEntity oeeoOeeoSkillSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(insertObject), OeeoOeeoSkillSetaEntity.class);
                oeeoOeeoSkillSetaEntity.setCretime(new Date());
                oeeoOeeoSkillSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                ormService.insertSelective(oeeoOeeoSkillSetaEntity);
            }
        }
        return employeeEntryApplyDTO;
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
}
