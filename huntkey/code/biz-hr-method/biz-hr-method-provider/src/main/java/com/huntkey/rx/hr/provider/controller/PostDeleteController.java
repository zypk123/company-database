package com.huntkey.rx.hr.provider.controller;

import com.alibaba.fastjson.JSONArray;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.DeptpostsetorderEntity;
import com.huntkey.rx.hr.common.constants.DeptPostDeleteConstant;
import com.huntkey.rx.hr.common.constants.DeptPostSetOderConstants;
import com.huntkey.rx.hr.common.model.PostToOdpsDTO;
import com.huntkey.rx.hr.provider.service.PostDeleteService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Size;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Validated
@RequestMapping("/hr/deptPost")
/***
 * 部门岗位controller类
 * @author fangkun
 *
 */
public class PostDeleteController {

    @Autowired
    PostDeleteService postDeleteService;

    @RequestMapping(value = "/loadDpDelete", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = DeptPostSetOderConstants.EDM_JOBPOSITION,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            getReqParamsNameNoPathVariable = {"postIds"},
            methodDesc = "根据岗位ID集合查询注销岗位"
    )
    Result loadDpDelete(@RequestParam(value = "postIds") @Size(min = 1) String[] postIds) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        //对返回结果集做转化
        List<PostToOdpsDTO> list = JSONArray.parseArray(
                JSONArray.toJSONString(postDeleteService.loadDpDelete(postIds)),
                PostToOdpsDTO.class);
        result.setData(list);
        return result;
    }

    @RequestMapping(value = "/loadDpDeleteOrder/{orderId}", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = DeptPostDeleteConstant.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "根据单据ID查询注销岗位"
    )
    Result loadDpDeleteOrder(@PathVariable String orderId) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        result.setData(postDeleteService.loadDpDeleteOrder(orderId));
        return result;
    }

    @RequestMapping(value = "/saveDpDeleteOrder", method = RequestMethod.POST)
    @MethodRegister(
            edmClass = DeptPostDeleteConstant.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "保存部门岗位注销单"
    )
    Result saveDpDeleteOrder(@RequestBody DeptpostsetorderEntity deptPostSetOrder) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String , String> map=postDeleteService.curPostContainOrder(deptPostSetOrder, false);
        if(map!=null && !map.isEmpty()){
        	result.setData(map);
        	return result;
        }
        result.setData(postDeleteService.saveDpDeleteOrder(deptPostSetOrder));
        return result;
    }

    @RequestMapping(value = "/submitDpDeleteOrder", method = RequestMethod.POST)
    @MethodRegister(
            edmClass = DeptPostDeleteConstant.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "提交部门岗位注销单"
    )
    Result submitDpDeleteOrder(@RequestBody DeptpostsetorderEntity deptPostSetOrder) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String, String> map=postDeleteService.curPostContainOrder(deptPostSetOrder, true);
        if(map!=null && !map.isEmpty()){
        	result.setData(map);
        	return result;
        }
        result.setData(postDeleteService.submitDpDeleteOrder(deptPostSetOrder));
        return result;
    }

    @RequestMapping(value = "/checkSub/{postNo}", method = RequestMethod.GET)
    @MethodRegister(
            edmClass = DeptPostDeleteConstant.EDM_DEPTPOSTSETORDER,
            methodCate = "人资系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "点击含下级时校验"
    )
    Result checkSub(@PathVariable String postNo) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        Map<String, String> mapRet=new HashMap<String,String>();
        Map<String, String> map=postDeleteService.checkSubPost(postNo,2);
        if (map!=null && !map.isEmpty()) {
            //下级岗位存在待审单据
        	mapRet.put("validateCode", "4");
        	mapRet.putAll(map);
        } else {
        	Map<String, String> mapMajor=postDeleteService.checkSubPost(postNo,1);
        	if(mapMajor!=null && !mapMajor.isEmpty()){
        		//下级岗位是所属部门的责任岗位
            	mapRet.put("validateCode", "3");
            	mapRet.putAll(mapMajor);
        	}
        }
        result.setData(mapRet);
        return result;
    }
}
