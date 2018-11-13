package com.huntkey.rx.sceo.formula.provider.audit.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.model.AuditRoles;
import com.huntkey.rx.sceo.formula.common.model.vo.RequestParamVo;
import com.huntkey.rx.sceo.formula.provider.audit.service.AuditRolesSetService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExecType;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodStatus;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodType;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * @author zhouyou on 2017/10/24.
 */
@RestController

@RequestMapping("/auditRoles")

/**
 * @author zhouyou on 2017/8/12
 */
public class AuditRolesSetController {

    private static Logger log = LoggerFactory.getLogger(AuditRolesSetController.class);

    @Autowired
    private AuditRolesSetService auditRolesSetService;

    /**
     * @return 获取方式列表
     * @desc 获取审核方式列表
     * @Date 2017年10月22日09:22:39
     * @author zhouyou
     */

    @RequestMapping(value = "/getmode/{classId}", method = RequestMethod.GET)
    public Result getmode(@PathVariable(value = "classId") String classId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(auditRolesSetService.getmode(classId));
        return result;
    }


//    /**
//     * @return 汇报岗位集合
//     * @desc 根据选定指定岗位，获取汇报岗位集合
//     * @Date 2017年10月23日14:22:39
//     * @author zhouyou
//     */
//    @RequestMapping("/getReportPosition")
//    public Result getReportPosition() {
//        Result result = new Result();
//        result.setRetCode(Result.RECODE_SUCCESS);
//        result.setData(auditRolesSetService.getReportPosition());
//        return result;
//    }

    /**
     * @return 获取部门树
     * @desc 根据选定指定部门，获取部门树
     * @Date 2017年10月28日11:28:37
     * @author zhouyou
     */
    @RequestMapping(value = "/deptTree")
    public Result getDeptTree(String pGrade, String poperater, String dGrade, String doperater) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(auditRolesSetService.getDeptTree(pGrade, poperater, dGrade, doperater));
        return result;
    }


    /**
     * @param depttreeId 获取部门id  查询关联的该部门的所有员工  直接到员工类中根据部门id查询出所有
     * @return List 所有人类别
     * @Desc 根据页面选择部门所有
     */
    @RequestMapping(value = "/getDeptAllPeople", method = RequestMethod.GET)
    List getDeptAllPeople(@RequestParam(value = "depttree_id") String depttreeId) {

        return auditRolesSetService.getDeptAllPeople(depttreeId);
    }

    /**
     * @param depttreeId 获取部门id  查询orm对应的部门表中的主责岗位 属性并返回
     * @return String 主责岗位信息
     * @Desc 根据页面选择部门主管人
     */
    @RequestMapping(value = "/getDeptMatter", method = RequestMethod.GET)
    String getDeptMatter(@RequestParam(value = "depttree_id") String depttreeId) {

        return auditRolesSetService.getDeptMatter(depttreeId);
    }


    /**
     * @param jobGrad   岗位级别
     * @param operator1 操作符1
     * @param value1    值1
     * @param deptGrad  部门级别
     * @param operator2 操作符2
     * @param value2    值2
     * @return 通过岗位级别条件 获取到对应的所属部门
     * @DESC 根据传入条件操作符以及内容 获取到所属部门id
     * @Date 2017年10月25日16:22:39
     * @author zhouyou
     */
    @RequestMapping("/getRpos_dept")
    List getRposDept(@RequestParam(value = "jobGrad") String jobGrad,
                     @RequestParam(value = "operator1") String operator1,
                     @RequestParam(value = "value1") String value1,
                     @RequestParam(value = "deptGrad") String deptGrad,
                     @RequestParam(value = "operator2") String operator2,
                     @RequestParam(value = "value2") String value2) {
        return auditRolesSetService.getRposdept(jobGrad, operator1, value1, deptGrad, operator2, value2);
    }

    /**
     * @param id        部门对象id
     * @param level     [主管人 上级主管人 上上级主管人]
     * @param pGrade    岗级
     * @param poperater 操作符
     * @param dGrade    部门级别
     * @param doperater 操作符
     * @return 根据选中的是[主管人 上级主管人  上上级主管人] 查询出对应的结果
     * @Desc 任职部门接口
     * @author liulang
     */
    @RequestMapping(value = "/loadSupreior", method = RequestMethod.POST)
    public Result loadSupreior(@RequestParam(value = "id") String id,
                               @RequestParam(value = "level") String level,
                               @RequestParam(value = "pGrade") String pGrade,
                               @RequestParam(value = "poperater") String poperater,
                               @RequestParam(value = "dGrade") String dGrade,
                               @RequestParam(value = "doperater") String doperater) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            result = auditRolesSetService.getSuper(id, level, pGrade, poperater, dGrade, doperater);
        } catch (Exception e) {
            throw new RuntimeException("加载常用函数出错", e);
        }
        return result;
    }

    /**
     * 保存审核角色信息
     * 保存多条角色信息记录
     *
     * @param auditRoles
     * @return
     */

    @RequestMapping(value = "/saveAuditRolesBat", method = RequestMethod.POST)
    public Result saveAuditRolesBat(@RequestBody List<AuditRoles> auditRoles) {
        for (AuditRoles auditRole : auditRoles) {
            System.out.println(auditRole.toString() + "======================================");
        }
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        String formulaId = UUID.randomUUID().toString().replace("-", "");
        for (AuditRoles auditRoles1 : auditRoles) {
            if (!"".equals(auditRoles1.getAlternateField4())) {
                formulaId = auditRoles1.getAlternateField4();
            }
        }

        for (AuditRoles auditRole : auditRoles) {
            //如果公式id为空执行插入
            if ("".equals(auditRole.getAditId())) {
                try {
                    auditRole.setAlternateField4(formulaId);
                    auditRolesSetService.saveAuditRoles(auditRole);
                    result.setData(auditRole.getAlternateField4());
                } catch (Exception e) {
                    log.error("保存审核角色信息发生错误", e);
                    throw new RuntimeException("保存审核角色信息发生错误", e);
                }
            } else {
                try {
                    //如果公式id不为空 执行修改操作
                    auditRolesSetService.updateAuditRoles(auditRole);
                    result.setData(auditRole.getAlternateField4());
                } catch (Exception e) {
                    log.error("保存审核角色信息发生错误", e);
                    throw new RuntimeException("保存审核角色信息发生错误", e);
                }
            }
        }

        return result;
    }

    @RequestMapping(value = "/loadlevel", method = RequestMethod.POST)
    public Result loadSupreiorSet(@RequestParam(value = "id") String id) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            String departmentSuperior = auditRolesSetService.office(id);
            result.setData(departmentSuperior);
        } catch (Exception e) {
            throw new RuntimeException("加载常用函数出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public Result loadSupreior1(@RequestParam(value = "id") String id,
                                @RequestParam(value = "level") String level,
                                @RequestParam(value = "date") String date,
                                @RequestParam(value = "pGrade") String pGrade,
                                @RequestParam(value = "poperater") String poperater,
                                @RequestParam(value = "dGrade") String dGrade,
                                @RequestParam(value = "doperater") String doperater) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            List departmentSuperior = auditRolesSetService.getDepartmentSuperior(id, level, pGrade, doperater, poperater, dGrade);
            result.setData(departmentSuperior);
        } catch (Exception e) {
            throw new RuntimeException("加载常用函数出错", e);
        }
        return result;
    }

    @RequestMapping(value = "/loadSet", method = RequestMethod.POST)
    public Result loadSupreiorSet(@RequestParam(value = "id") String id,
                                  @RequestParam(value = "level") String level,
                                  @RequestParam(value = "date") String date,
                                  @RequestParam(value = "pGrade") String pGrade,
                                  @RequestParam(value = "poperater") String poperater,
                                  @RequestParam(value = "dGrade") String dGrade,
                                  @RequestParam(value = "doperater") String doperater) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            List departmentSuperior = auditRolesSetService.getDirectorSet(id, poperater, doperater, dGrade, pGrade);
            result.setData(departmentSuperior);
        } catch (Exception e) {
            throw new RuntimeException("加载常用函数出错", e);
        }
        return result;
    }

    /**
     * 保存审核角色信息
     * 保存单条角色信息记录
     *
     * @param auditRoles
     * @return
     */
    @RequestMapping(value = "/saveAuditRoles", method = RequestMethod.POST)
    public Result saveAuditRoles(@RequestBody AuditRoles auditRoles) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            auditRolesSetService.saveAuditRoles(auditRoles);
            result.setData(auditRoles.getAditId());
        } catch (Exception e) {
            log.error("保存审核角色信息发生错误", e);
            throw new RuntimeException("保存审核角色信息发生错误", e);
        }
        return result;
    }

    /**
     * 修改审核角色信息
     *
     * @param auditRoles
     * @return
     */
    @RequestMapping(value = "/updateAuditRoles", method = RequestMethod.PUT)
    public Result updateAuditRoles(@RequestBody AuditRoles auditRoles) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            auditRolesSetService.updateAuditRoles(auditRoles);
            result.setData(auditRoles.getAditId());
        } catch (Exception e) {
            log.error("修改审核角色信息发生错误", e);
            throw new RuntimeException("修改审核角色信息发生错误", e);
        }
        return result;
    }

    /**
     * 查询审核角色信息
     * 根据公式id查询单条审核角色对象信息
     *
     * @return
     */
    @RequestMapping(value = "/queryAuditRolesById/{auditId}", method = RequestMethod.GET)
    public Result queryAuditRoles(@PathVariable("auditId") String auditId) {
        System.out.println("进入查询接口");
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            AuditRoles auditRoles = auditRolesSetService.selectByPrimaryKey(auditId);
            result.setData(auditRoles);
        } catch (Exception e) {
            log.error("查询审核角色信息发生错误", e);
            throw new RuntimeException("查询审核角色信息发生错误", e);
        }
        return result;
    }


    /**
     * 查询审核角色信息
     * 根据公式id查询审核角色对象集合信息
     *
     * @return
     */
    @RequestMapping(value = "/queryAuditRolesByClassId/{classId}", method = RequestMethod.GET)
    public Result queryAuditRolesByClassId(@PathVariable("classId") String classId) {
        System.out.println("进入查询接口");
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            List<AuditRoles> auditRoles = auditRolesSetService.selectByClassId(classId);
            result.setData(auditRoles);
        } catch (Exception e) {
            log.error("查询审核角色信息发生错误", e);
            throw new RuntimeException("查询审核角色信息发生错误", e);
        }
        return result;
    }


    /**
     * 删除审核角色信息(逻辑删除)
     *
     * @param auditId 公式id
     * @return
     */
    @RequestMapping(value = "/removeConditionRelated/{auditId}", method = RequestMethod.DELETE)
    public Result removeConditionRelated(@PathVariable("auditId") String auditId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        System.out.println(auditId);
        try {
            auditRolesSetService.delAuditRolesInfo(auditId);
        } catch (Exception e) {
            log.error("删除查询审核角色信息发生错误", e);
            throw new RuntimeException("删除查询审核角色信息发生错误", e);
        }
        return result;
    }


    /**
     * @desc 获取部门对应的岗位
     */

    @RequestMapping("/getPost")
    public Result getPost(@RequestParam(value = "deptId") String deptId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(auditRolesSetService.getCurrentDeptUnderJobposition(deptId));
        return result;
    }


//    /**
//     * 指定部门
//     * 第一步：首先获取到部门树
//     * 第二步：选中了一个节点
//     * 第三步：01/02
//     *
//     * @param deptId   部门id.部门id.部门id
//     * @param allorOne 01/02
//     * @return 返回岗位列表
//     */
//    @RequestMapping(value = "/queryDeptLinkPost/{deptId}/{allorOne}", method = RequestMethod.GET)
//    public Result queryDeptLinkPost(@PathVariable("deptId") String deptId,
//                                    @PathVariable("allorOne") String allorOne) {
//        Result result = new Result();
//        result.setRetCode(Result.RECODE_SUCCESS);
//        try {
//            String[] splitDeptId = deptId.split("\\.");
//            //获取部门的id信息
//            String nodeCode = splitDeptId[splitDeptId.length - 1];
//            List<String> listPost = auditRolesSetService.getCurrentDeptUnderJobposition2(nodeCode, allorOne);
//            Map<String, List<String>> map = new HashMap<String, List<String>>();
//            map.put("post", listPost);
//            result.setData(map);
//        } catch (Exception e) {
//            log.error("根据部门树节点的类查找对应的岗位发生错误", e);
//            throw new RuntimeException("根据部门树节点的类查找对应的岗位发生错误", e);
//        }
//        return result;
//    }


    /**
     * @param requestParamVo
     * @return 结果集
     * 参数      1 公式ID 2 单据类对象ID
     * 通过公式拿到具体的公式内容来计算结果，数据是根据单据类对象ID ORM接口拿到具体的数据（部门类和岗位类id）
     * @Desc 方式接口
     */
    @RequestMapping(value = "/auditInterface", method = RequestMethod.POST)
    public Result loadPost(@RequestBody RequestParamVo requestParamVo) {
        //指定岗位
        if ("1".equals(requestParamVo.getMark())) {
            Result result = new Result();
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(auditRolesSetService.getRootReportPosition());
            return result;
            //指定部门
        } else if ("2".equals(requestParamVo.getMark())) {
            Result result = new Result();
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(auditRolesSetService.getOnlyDeptTree());
            return result;
        }
        return null;
    }


    /**
     * @param formId    单据id
     * @param formulaId 公式id
     * @return 结果集
     * 参数     1单据类对象ID 2公式ID
     * 通过公式拿到具体的公式内容来计算结果，数据是根据单据类对象ID ORM接口拿到具体的数据（部门类和岗位类id）
     * @Desc 审核角色接口
     */
    @MethodRegister(
            edmClass = "formula",
            methodCate = "公式",
            programCate = ProgramCate.Java,
            methodType = MethodType.GeneralMethod,
            methodExecType = MethodExecType.SyncMethod,
            methodStatus = MethodStatus.Enable,
            methodDesc = "关联触发条件"
    )
    @RequestMapping(value = "/auditInterface/{formId}/{formulaId}", method = RequestMethod.GET)
    public Result auditRoles(@PathVariable(value = "formId") String formId, @PathVariable(value = "formulaId") String formulaId) {
        Result result = new Result();
        String effectiveId = null;
        Set<String> reList = new HashSet<>();
        Set templist;
        List<AuditRoles> auditRoles = auditRolesSetService.selectByClassId(formulaId);
        for (AuditRoles auditRole : auditRoles) {
            //如果是指定岗位的记录 直接取出对应的岗位结果
            if ("1".equals(auditRole.getMark())) {
                String[] splitPostId = auditRole.getAlternateField2().split("\\.");
                String postId = splitPostId[splitPostId.length - 1];
                effectiveId = auditRolesSetService.getEffectivePost(postId);
                if (StringUtil.isNullOrEmpty(effectiveId)) {
                    result.setRetCode(Result.RECODE_ERROR);
                    result.setErrMsg("岗位或者部门无效");
                }
                if (!StringUtil.isNullOrEmpty(effectiveId)) {
                    reList.add(effectiveId);

                }
            } else
                //如果是指定部门的记录 直接取出对应的岗位结果
                if ("2".equals(auditRole.getMark())) {

                    String[] splitPostId = auditRole.getAlternateField2().split("\\.");
                    String id = splitPostId[splitPostId.length - 1];
                    templist = auditRolesSetService.getCurrentDeptUnderJobposition2(id, auditRole.getAlternateField3(), auditRole.getPostValue(), auditRole.getPostOperate(), auditRole.getDeptValue(), auditRole.getDeptOperate());
                    if (null == templist || templist.size() == 0) {
                        result.setRetCode(Result.RECODE_ERROR);
                        result.setErrMsg("岗位或部门无效");
                        return result;
                    }
                    reList.addAll(templist);
                } else
                    //如果是相对岗位  需要调用uuid集
                    if ("3".equals(auditRole.getMark())) {
                        //如何获取相对岗位的id
                        List<String> postIds = auditRolesSetService.getChooseId(auditRole.getProcessDocuObjId(), auditRole.getBeiyong4(), formId, auditRole.getChooseCode());
                        if (null != postIds) {
                            for (String postId : postIds) {
                                Result postSuperior = auditRolesSetService.getPostSuperior(postId, auditRole.getAlternateField3(), auditRole.getPostValue(), auditRole.getPostOperate(), auditRole.getDeptValue(), auditRole.getDeptOperate());
                                if (0 == (postSuperior.getRetCode())) {
                                    result.setRetCode(Result.RECODE_ERROR);
                                    result.setErrMsg(postSuperior.getErrMsg());
                                    return result;
                                }
                                reList.addAll((List) postSuperior.getData());
                            }
                        } else {
                            result.setRetCode(Result.RECODE_ERROR);
                            result.setErrMsg("岗位或部门无效");
                        }
                    } else
                        //如果是相对部门 需要调用方法 去查询结果集
                        if ("4".equals(auditRole.getMark())) {
                            List<String> deptIds = auditRolesSetService.getChooseId(auditRole.getProcessDocuObjId(), auditRole.getBeiyong4(), formId, auditRole.getChooseCode());
                            if (!StringUtil.isNullOrEmpty(deptIds)) {
                                for (String deptId : deptIds) {
                                    Result aSuper = auditRolesSetService.getSuper(deptId, auditRole.getAlternateField3(), auditRole.getPostValue(), auditRole.getPostOperate(), auditRole.getDeptValue(), auditRole.getDeptOperate());
                                    if (0 == (aSuper.getRetCode())) {
                                        result.setRetCode(Result.RECODE_ERROR);
                                        result.setErrMsg(aSuper.getErrMsg());
                                        return result;
                                    }
                                    reList.addAll((Set) aSuper.getData());
                                }
                            }
                        }
        }
        result.setData(reList);
        return result;
    }


}

