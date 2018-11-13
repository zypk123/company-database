package com.huntkey.rx.sceo.formula.client.audit.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.audit.service.AuditRolesSetService;
import com.huntkey.rx.sceo.formula.common.model.AuditRoles;
import com.huntkey.rx.sceo.formula.common.model.vo.RequestParamVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhouyou on 2017/10/24.
 */
@RestController

@RequestMapping("/auditRoles")

/**
 * @author chenfei on 2017/8/12
 */
public class AuditRolesSetController {

    private static Logger log = LoggerFactory.getLogger(AuditRolesSetController.class);

    @Autowired
    private AuditRolesSetService auditRolesSetService;

    /**
     * @desc   获取审核方式列表
     * @Date   2017年10月22日09:22:39
     * @author zhouyou
     * @return 获取方式列表
     */

    @RequestMapping(value = "/getmode/{classId}",method = RequestMethod.GET)
    public Result getmode(@PathVariable(value = "classId") String classId){
        Result result = new Result();
        result =auditRolesSetService.getmode(classId);
        return result;
    }

    /**
     * @desc   根据选定指定岗位，获取汇报岗位集合
     * @Date   2017年10月23日14:22:39
     * @author zhouyou
     * @return 汇报岗位集合
     */
    @RequestMapping("/getReportPosition")
    public Result getReportPosition(){
        Result result = new Result();
        result = auditRolesSetService.getReportPosition();
        return result;
    }

    /**
     * @desc   根据选定指定部门，获取部门树
     * @Date   2017年10月28日11:28:37
     * @author zhouyou
     * @return 获取部门树
     */
    @RequestMapping(value = "/deptTree")
    public Result getDeptTree(String pGrade, String poperater, String dGrade, String doperater) {
        Result result = new Result();
        result = auditRolesSetService.getDeptTree(pGrade,poperater,dGrade,doperater);
        return result;
    }

    /**
     * @Desc   根据页面选择部门所有
     * @param  depttreeId 获取部门id  查询关联的该部门的所有员工  直接到员工类中根据部门id查询出所有
     * @return List 所有人类别
     */
    @RequestMapping(value = "/getDeptAllPeople",method = RequestMethod.GET)
    List getDeptAllPeople(@RequestParam(value = "depttree_id") String depttreeId ){

        return auditRolesSetService.getDeptAllPeople(depttreeId);
    }

    /**
     * @Desc   根据页面选择部门主管人
     * @param  depttreeId 获取部门id  查询orm对应的部门表中的主责岗位 属性并返回
     * @return String 主责岗位信息
     */
    @RequestMapping(value = "/getDeptMatter",method = RequestMethod.GET)
    String getDeptMatter(@RequestParam(value = "depttree_id") String depttreeId ){

        return auditRolesSetService.getDeptMatter(depttreeId);
    }


    /**
     * @DESC   根据传入条件操作符以及内容 获取到所属部门id
     * @param  jobGrad  岗位级别
     * @param  operator1 操作符1
     * @param  value1    值1
     * @param  deptGrad  部门级别
     * @param  operator2 操作符2
     * @param  value2    值2
     * @Date   2017年10月25日16:22:39
     * @author zhouyou
     * @return 通过岗位级别条件 获取到对应的所属部门
     */
    @RequestMapping("/getRpos_dept")
    List getRposDept(  @RequestParam(value = "jobGrad") String jobGrad,
                        @RequestParam(value = "operator1") String operator1,
                        @RequestParam(value = "value1") String value1,
                        @RequestParam(value = "deptGrad") String deptGrad,
                        @RequestParam(value = "operator2") String operator2,
                        @RequestParam(value = "value2") String value2) {
        return auditRolesSetService.getRposDept(jobGrad, operator1, value1, deptGrad, operator2, value2);
    }

    /**
     * @Desc            任职部门接口
     * @param id        部门对象id
     * @param level     [主管人 上级主管人 上上级主管人]
     * @param pGrade    岗级
     * @param poperater 操作符
     * @param dGrade    部门级别
     * @param doperater 操作符
     * @author          liulang
     * @return          根据选中的是[主管人 上级主管人  上上级主管人] 查询出对应的结果
     */
    @RequestMapping(value = "/loadSupreior", method = RequestMethod.POST)
    public Result loadSupreior(@RequestParam(value = "id")String id,
                               @RequestParam(value = "level")String level,
                               @RequestParam(value = "pGrade")String pGrade,
                               @RequestParam(value = "poperater")String poperater,
                               @RequestParam(value = "dGrade")String dGrade,
                               @RequestParam(value = "doperater")String doperater){
        Result result = new Result();
        result = auditRolesSetService.loadSupreior(id,level,pGrade,poperater,dGrade,doperater);
        return result;
    }

  /*  *//**
     * @Desc            任职岗位接口
     * @param id        岗位对象id
     * @param level     级别
     * @param pGrade    岗级
     * @param poperater 岗位操作符
     * @param dGrade    部级
     * @param doperater 部门操作符
     * @author          liulang
     * @return
     *//*
    @RequestMapping(value = "/loadPost", method = RequestMethod.POST)
    public Result loadPost(@RequestParam(value = "id")String id,
                           @RequestParam(value = "level")String level,
                           @RequestParam(value = "pGrade")String pGrade,
                           @RequestParam(value = "poperater")String poperater,
                           @RequestParam(value = "dGrade")String dGrade,
                           @RequestParam(value = "doperater")String doperater){
        Result result = new Result();
        result = auditRolesSetService.loadPost(id,level,pGrade,poperater,dGrade,doperater);
        return result;
    }*/


    /**
     * @Desc     接口
     * @param    requestParamVo
     * @return   结果集
     */
    @RequestMapping(value = "/auditInterface", method = RequestMethod.POST)
    public Result loadPost(@RequestBody RequestParamVo requestParamVo){
        Result result = new Result();
        result = auditRolesSetService.loadPost(requestParamVo);
        return result;
    }




    @RequestMapping(value = "/loadlevel", method = RequestMethod.POST)
    public Result loadSupreiorSet(@RequestParam(value = "id")String id){
        Result result = new Result();
        result = auditRolesSetService.loadSupreiorSet(id);
        return result;
    }


    @RequestMapping(value = "/load", method = RequestMethod.POST)
    public Result loadSupreior1(@RequestParam(value = "id")String id,
                                @RequestParam(value = "level")String level,
                                @RequestParam(value = "date")String date,
                                @RequestParam(value = "pGrade")String pGrade,
                                @RequestParam(value = "poperater")String poperater,
                                @RequestParam(value = "dGrade")String dGrade,
                                @RequestParam(value = "doperater")String doperater){
        Result result = new Result();
        result = auditRolesSetService.loadSupreior1(id,level,date,pGrade,poperater,dGrade,doperater);
        return result;
    }


    @RequestMapping(value = "/loadSet", method = RequestMethod.POST)
    public Result loadSupreiorSet(@RequestParam(value = "id")String id,
                                  @RequestParam(value = "level")String level,
                                  @RequestParam(value = "date")String date,
                                  @RequestParam(value = "pGrade")String pGrade,
                                  @RequestParam(value = "poperater")String poperater,
                                  @RequestParam(value = "dGrade")String dGrade,
                                  @RequestParam(value = "doperater")String doperater){
        Result result = new Result();
        result = auditRolesSetService.loadSupreiorSet(id,level,date,pGrade,poperater,dGrade,doperater);
        return result;
    }


    /**
     * 保存审核角色信息
     * 保存多条角色信息记录
     * @param auditRoles
     * @return
     */
    @RequestMapping(value = "/saveAuditRolesBat", method = RequestMethod.POST)
    public Result saveAuditRolesBat(@RequestBody List<AuditRoles> auditRoles) {
        Result result = new Result();
        result = auditRolesSetService.saveAuditRolesBat(auditRoles);
        return result;
    }

    /**
     * 保存审核角色信息
     * 保存单条角色信息记录
     * @param auditRoles
     * @return
     */
    @RequestMapping(value = "/saveAuditRoles", method = RequestMethod.POST)
    public Result saveAuditRoles(@RequestBody AuditRoles auditRoles) {
        Result result = new Result();
        result = auditRolesSetService.saveAuditRoles(auditRoles);
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
        result = auditRolesSetService.updateAuditRoles(auditRoles);
        return result;
    }

    /**
     * 查询审核角色信息
     * 根据审核角色id查询单条审核角色对象信息
     * @return
     */
    @RequestMapping(value = "/queryAuditRolesById/{auditId}", method = RequestMethod.GET)
    public Result queryAuditRoles(@PathVariable("auditId") String auditId) {
        System.out.println("进入查询接口");
        Result result = new Result();
        result = auditRolesSetService.queryAuditRoles(auditId);
        return result;
    }


    /**
     * 查询审核角色信息
     * 根据审核角色id查询审核角色对象集合信息
     * @return
     */
    @RequestMapping(value = "/queryAuditRolesByClassId/{classId}", method = RequestMethod.GET)
    public Result queryAuditRolesByClassId(@PathVariable("classId") String classId) {
        System.out.println("进入查询接口");
        Result result = new Result();
        result = auditRolesSetService.queryAuditRolesByClassId(classId);
        return result;
    }


    /**
     * 删除审核角色信息(逻辑删除)
     *
     * @param auditId
     * @return
     */
    @RequestMapping(value = "/removeConditionRelated/{auditId}", method = RequestMethod.DELETE)
    public Result removeConditionRelated(@PathVariable("auditId") String auditId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result = auditRolesSetService.removeConditionRelated(auditId);
        return result;
    }

    /**
     * @Desc   审核角色接口
     * @param  formId  单据id
     * @param  formulaId  公式id
     * @return
     */
    @RequestMapping(value = "/auditInterface/{formId}/{formulaId}", method = RequestMethod.GET)
    public Result auditRoles(@PathVariable(value = "formId")String formId, @PathVariable(value = "formulaId") String formulaId){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result = auditRolesSetService.auditRoles(formId,formulaId);
        return result;
    }

    /**
     * @desc   获取部门对应的岗位
     */

    @RequestMapping("/getPost")
    public Result getPost(@RequestParam (value = "deptId") String deptId){
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result =auditRolesSetService.getPost(deptId);
        return result;
    }

    /**
     * 指定部门
     * 第一步：首先获取到部门树
     * 第二步：选中了一个节点
     * 第三步：01/02
     * @param deptId   部门id.部门id.部门id
     * @param allorOne 01/02
     * @return 返回岗位列表
     */
    @RequestMapping(value = "/queryDeptLinkPost/{deptId}/{allorOne}", method = RequestMethod.GET)
    public Result queryDeptLinkPost(@PathVariable("deptId") String deptId,
                                    @PathVariable("allorOne") String allorOne) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result = auditRolesSetService.queryDeptLinkPost(deptId,allorOne);
        return result;
    }


}
