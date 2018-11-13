package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.common.util.JsonUtils;
import com.huntkey.rx.hr.provider.service.EmployeeService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.Range;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;

@RestController
@Validated
@RequestMapping("/hr/employee")
/***
 * 员工controller类
 * @author fangkun
 *
 */
public class EmployeeController {

    @Autowired
    EmployeeService employee;

    @RequestMapping(value = "/queryEmployee", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = EmployeeConstants.EDM_EMPLOYEE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"deptId", "searchName", "removeLeav",
                    "startPage", "rows"},
            methodDesc = "模糊查询员工对象"
    )
    Result queryEmployee(@RequestParam(required = false) String deptId,
                         @RequestParam String searchName,
                         @RequestParam(required = false,defaultValue="1") @Range(min = 0, max = 1) int removeLeav,
                         @RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_START_MIN) int startPage,
                         @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_ROWS_MIN) int rows) {
        return employee.queryEmployee(deptId, searchName, removeLeav, startPage, rows);
    }

    @RequestMapping(value = "/getEmployeePositions", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = EmployeeConstants.EDM_EMPLOYEE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"employeeId","employeeNo"},
            methodDesc = "根据员工ID或者工号查询员工任岗经历"
    )
    Result getEmployeePositions(@RequestParam(required = false)  String employeeId,
                                @RequestParam(required = false)  String employeeNo) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        if(StringUtil.isNullOrEmpty(employeeId) && StringUtil.isNullOrEmpty(employeeNo)){
            throw new ApplicationException(Result.RECODE_VALIDATE_ERROR,"员工ID和员工工号必须传一个！");
        }

        JSONArray postSet = employee.getEmployeePositions(employeeId,employeeNo);
        result.setData(postSet);
        return result;
    }

    @RequestMapping(value = "/findEmployeeById", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = EmployeeConstants.EDM_EMPLOYEE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"employeeId", "type"},
            methodDesc = "根据员工ID查询员工对象"
    )
    /**
     * 根据员工ID 查询员工对象
     * @param employeeId 员工ID
     * @param type 1 代表查询档案变更需要的结果集 2 表示员工类需要的结果集 3单纯查询员工结果 不需要属性集
     * @return 员工类结果集
     */
    Result findEmployeeById(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_EMPLYEE_ID) String employeeId,
                            @RequestParam @Range(min = 1, max = 3) int type) {
        Result result = new Result();
        JSONObject employeeObj = null;
        if (type == 1) {
        	employeeObj = employee.queryEmployeeById(employeeId, true, true);
            //查询档案变更需要的结果集
            result.setData(JSONObject.parseObject(JSONObject.toJSONString(employeeObj),
                    EmpToOrderDTO.class));
        } else if(type==2){
        	employeeObj = employee.queryEmployeeById(employeeId, true, true);
            //员工类需要的结果集
            result.setData(JSONObject.parseObject(JSONObject.toJSONString(employeeObj),
                    EmployeeDTO.class));
        }else{
        	employeeObj = employee.queryEmployeeById(employeeId, true, false);
        	JsonUtils.underLine2Camel(employeeObj);
        	 result.setData(employeeObj);
        }
        result.setRetCode(Result.RECODE_SUCCESS);
        return result;
    }

    @RequestMapping(value = "/deleteEmployee", method = RequestMethod.DELETE)
    @MethodRegister(
            edmClass = EmployeeConstants.EDM_EMPLOYEE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "根据员工ID删除员工对象"
    )
    Result deleteEmployee(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_EMPLYEE_ID) String employeeId) {
        return employee.deleteEmployee(employeeId);
    }

    @RequestMapping(value = "/saveEmployee", method = RequestMethod.POST)
    @MethodRegister(
            edmClass = "employee",
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "保存员工对象"
    )
    Result saveEmployee(@RequestBody JSONObject employeeDTO) {
    	Object params = JSONObject.parse(employeeDTO.toJSONString());
    	JsonUtils.camel2UnderLine(params);
        return employee.saveEmployee(JSONObject.parseObject(JSONObject.toJSONString(params),
        		EmployeeEntity.class));
    }


    @RequestMapping(value = "/loadEmployeePost", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = EmployeeConstants.EDM_EMPLOYEE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"deptId","subDept", "type", "startPage", "rows"},
            methodDesc = "根据部门ID查询岗位员工"
    )
    Result loadEmployeePost(@RequestParam @NotBlank(message = MsgConstants.MSG_HR_NOT_BLANK_DEPT_ID) String deptId,
    						@RequestParam(required=false,defaultValue="0") @Range(min = 0, max = 1) int subDept,
                            @RequestParam @Range(min = 1, max = 3) int type,
                            @RequestParam(required = false, defaultValue = "1") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_START_MIN) int startPage,
                            @RequestParam(required = false, defaultValue = "10") @Min(value = 1, message = MsgConstants.MSG_HR_PAGE_ROWS_MIN) int rows) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        JSONObject json = null;
        if (type == 1) {
        	//查询有岗有人
        	json = employee.queryEmpPost(deptId, subDept, rows, startPage);
        } else if(type==2) {
        	//查询空岗人员
            json = employee.loadEmptyPost(deptId,subDept, rows, startPage);
        }else{
        	//查询待岗人员
            json = employee.loadEmpEmpty(deptId,subDept, rows, startPage);
        }
        result.setData(json);
        return result;
    }

    @RequestMapping(value = "/deptEmptyPost", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = EmployeeConstants.EDM_EMPLOYEE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"deptId","containSub"},
            methodDesc = "根据部门ID查询部门空岗"
    )
    Result deptEmptyPost(@RequestParam(required = false) String deptId,
    		@RequestParam(required = false,defaultValue="0") 
    		@Range(min=0,max=1)  int containSub) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(employee.deptEmptyPost(deptId,containSub));
        return result;
    }

    @RequestMapping(value = "/queryByDeptEmp/{empId}", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = EmployeeConstants.EDM_EMPLOYEE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "根据员工ID查询部门岗位需要的数据"
    )
    Result queryByDeptEmp(@PathVariable String empId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(employee.queryByDeptEmp(empId));
        return result;
    }
    
    @RequestMapping(value = "/checkCardNo/{cardNo}", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = EmployeeConstants.EDM_EMPLOYEE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "根据身份证号在当前企业查询是否存在"
    )
    Result checkCardNo(@PathVariable String cardNo) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(employee.checkCardNoInEnte(cardNo));
        return result;
    }
    
    @RequestMapping(value = "/checkEmail", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = EmployeeConstants.EDM_EMPLOYEE,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"entryOrderId","peopleId","email"},
            methodDesc = "校验企业邮箱是否存在"
    )
    Result checkEmail(@RequestParam(required=false) String entryOrderId,
    		@RequestParam(required=false) String peopleId,
    		@RequestParam(required=true) @NotBlank(message="邮箱不能为空") String email) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(employee.checkEmail(entryOrderId,peopleId, email));
        return result;
    }
}
