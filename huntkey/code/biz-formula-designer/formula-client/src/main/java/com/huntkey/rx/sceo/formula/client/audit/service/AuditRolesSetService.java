package com.huntkey.rx.sceo.formula.client.audit.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.client.audit.service.hystrix.AuditRolesSetServiceHystrixImpl;
import com.huntkey.rx.sceo.formula.common.model.AuditRoles;
import com.huntkey.rx.sceo.formula.common.model.vo.RequestParamVo;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author zhouyou on 2017/10/28.
 */
@FeignClient(value = "formula-provider", url = "${providerURL}", fallback = AuditRolesSetServiceHystrixImpl.class)
public interface AuditRolesSetService {
    /**
     * 获取审核方式列表
     * @Date   2017年10月22日09:22:39
     * @author zhouyou
     * @param classId
     * @return 获取方式列表
     */

    @RequestMapping(value = "/auditRoles/getmode/{classId}", method = RequestMethod.GET)
    Result getmode(@PathVariable(value = "classId") String classId);


    /**
     * 根据选定指定岗位，获取汇报岗位集合
     * @Date   2017年10月23日14:22:39
     * @author zhouyou
     * @return 汇报岗位集合
     */
    @RequestMapping("/auditRoles/getReportPosition")
    Result getReportPosition();

    /**
     * 根据选定指定部门，获取部门树
     * @Date   2017年10月28日11:28:37
     * @param pGrade
     * @param poperater
     * @param dGrade
     * @param doperater
     * @author zhouyou
     * @return 获取部门树
     */
    @RequestMapping(value = "/auditRoles/deptTree")
    Result getDeptTree(@RequestParam(value = "pGrade") String pGrade,
                       @RequestParam(value = "poperater") String poperater,
                       @RequestParam(value = "dGrade") String dGrade,
                       @RequestParam(value = "doperater") String doperater);

    /**
     * 根据页面选择部门所有
     * @param  depttreeId 获取部门id  查询关联的该部门的所有员工  直接到员工类中根据部门id查询出所有
     * @return List 所有人类别
     */
    @RequestMapping(value = "/auditRoles/getDeptAllPeople",method = RequestMethod.GET)
    List getDeptAllPeople(@RequestParam(value = "depttree_id") String depttreeId);


    /**
     * 根据页面选择部门主管人
     * @param  depttreeId 获取部门id  查询orm对应的部门表中的主责岗位 属性并返回
     * @return String 主责岗位信息
     */
    @RequestMapping(value = "/auditRoles/getDeptMatter",method = RequestMethod.GET)
    String getDeptMatter(@RequestParam(value = "depttree_id") String depttreeId);


    /**
     * 根据传入条件操作符以及内容 获取到所属部门id
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
    @RequestMapping("/auditRoles/getRpos_dept")
    List getRposDept(@RequestParam(value = "jobGrad") String jobGrad,
                      @RequestParam(value = "operator1") String operator1,
                      @RequestParam(value = "value1") String value1,
                      @RequestParam(value = "deptGrad") String deptGrad,
                      @RequestParam(value = "operator2") String operator2,
                      @RequestParam(value = "value2") String value2);


    /**
     * 任职部门接口
     * @param id        部门对象id
     * @param level     [主管人 上级主管人 上上级主管人]
     * @param pGrade    岗级
     * @param poperater 操作符
     * @param dGrade    部门级别
     * @param doperater 操作符
     * @author          liulang
     * @return          根据选中的是[主管人 上级主管人  上上级主管人] 查询出对应的结果
     */
    @RequestMapping(value = "/auditRoles/loadSupreior", method = RequestMethod.POST)
    Result loadSupreior(@RequestParam(value = "id") String id,
                        @RequestParam(value = "level") String level,
                        @RequestParam(value = "pGrade") String pGrade,
                        @RequestParam(value = "poperater") String poperater,
                        @RequestParam(value = "dGrade") String dGrade,
                        @RequestParam(value = "doperater") String doperater);

/*    *//**
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
    @RequestMapping(value = "/auditRoles/loadPost", method = RequestMethod.POST)
    Result loadPost(@RequestParam(value = "id") String id,
                    @RequestParam(value = "level") String level,
                    @RequestParam(value = "pGrade") String pGrade,
                    @RequestParam(value = "poperater") String poperater,
                    @RequestParam(value = "dGrade") String dGrade,
                    @RequestParam(value = "doperater") String doperater);*/


    /**
     * 保存审核角色信息
     * 保存多条角色信息记录
     * @param auditRoles
     * @return
     */
    @RequestMapping(value = "/auditRoles/saveAuditRolesBat", method = RequestMethod.POST)
    Result saveAuditRolesBat(@RequestBody List<AuditRoles> auditRoles);


    /**
     * 接口
     * @param    requestParamVo
     * @return   结果集
     */
    @RequestMapping(value = "/auditRoles/auditInterface", method = RequestMethod.POST)
    Result loadPost(@RequestBody RequestParamVo requestParamVo);


    /**
     * 审核角色解析接口
     * @param  formulaId  公式id
     * @param  formId  单据id
     * @return
     */
    @RequestMapping(value = "/auditRoles/auditInterface/{formId}/{formulaId}", method = RequestMethod.GET)
    Result auditRoles(@PathVariable(value = "formId") String formId, @PathVariable(value = "formulaId") String formulaId);

    /**
     * loadSupreiorSet
     * @param id
     * @return
     */
    @RequestMapping(value = "/auditRoles/loadlevel", method = RequestMethod.POST)
    Result loadSupreiorSet(@RequestParam(value = "id") String id);

    /**
     * loadSupreior1
     * @param id
     * @param level
     * @param date
     * @param pGrade
     * @param poperater
     * @param dGrade
     * @param doperater
     * @return
     */
    @RequestMapping(value = "/auditRoles/load", method = RequestMethod.POST)
    Result loadSupreior1(@RequestParam(value = "id") String id,
                         @RequestParam(value = "level") String level,
                         @RequestParam(value = "date") String date,
                         @RequestParam(value = "pGrade") String pGrade,
                         @RequestParam(value = "poperater") String poperater,
                         @RequestParam(value = "dGrade") String dGrade,
                         @RequestParam(value = "doperater") String doperater);

    /**
     * loadSupreiorSet
     * @param id
     * @param level
     * @param date
     * @param pGrade
     * @param poperater
     * @param dGrade
     * @param doperater
     * @return
     */
    @RequestMapping(value = "/auditRoles/loadSet", method = RequestMethod.POST)
    Result loadSupreiorSet(@RequestParam(value = "id") String id,
                           @RequestParam(value = "level") String level,
                           @RequestParam(value = "date") String date,
                           @RequestParam(value = "pGrade") String pGrade,
                           @RequestParam(value = "poperater") String poperater,
                           @RequestParam(value = "dGrade") String dGrade,
                           @RequestParam(value = "doperater") String doperater);



    /**
     * 保存审核角色信息
     * 保存单条角色信息记录
     * @param auditRoles
     * @return
     */
    @RequestMapping(value = "/auditRoles/saveAuditRoles", method = RequestMethod.POST)
    Result saveAuditRoles(@RequestBody AuditRoles auditRoles);




    /**
     * 修改审核角色信息
     *
     * @param auditRoles
     * @return
     */
    @RequestMapping(value = "/auditRoles/updateAuditRoles", method = RequestMethod.PUT)
    Result updateAuditRoles(@RequestBody AuditRoles auditRoles);

    /**
     * 查询审核角色信息
     * 根据审核角色id查询单条审核角色对象信息
     * @param auditId
     * @return
     */
    @RequestMapping(value = "/auditRoles/queryAuditRolesById/{auditId}", method = RequestMethod.GET)
    Result queryAuditRoles(@PathVariable("auditId") String auditId);

    /**
     * 查询审核角色信息
     * 根据审核角色id查询审核角色对象集合信息
     * @param classId
     * @return
     */
    @RequestMapping(value = "/auditRoles/queryAuditRolesByClassId/{classId}", method = RequestMethod.GET)
    Result queryAuditRolesByClassId(@PathVariable("classId") String classId);

    /**
     * 删除审核角色信息(逻辑删除)
     *
     * @param auditId
     * @return
     */
    @RequestMapping(value = "/auditRoles/removeConditionRelated/{auditId}", method = RequestMethod.DELETE)
    Result removeConditionRelated(@PathVariable("auditId") String auditId);


    /**
     * 获取部门对应的岗位
     * @param deptId
     * @return
     */
    @RequestMapping("/auditRoles/getPost")
    Result getPost(@RequestParam (value = "deptId") String deptId);

    /**
     * 指定部门
     * 第一步：首先获取到部门树
     * 第二步：选中了一个节点
     * 第三步：01/02
     * @param deptId   部门id.部门id.部门id
     * @param allorOne 01/02
     * @return 返回岗位列表
     */
    @RequestMapping(value = "/auditRoles/queryDeptLinkPost/{deptId}/{allorOne}", method = RequestMethod.GET)
    Result queryDeptLinkPost(@PathVariable("deptId") String deptId,
                             @PathVariable("allorOne") String allorOne);

}
