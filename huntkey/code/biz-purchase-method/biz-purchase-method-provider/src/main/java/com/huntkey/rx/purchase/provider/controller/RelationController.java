package com.huntkey.rx.purchase.provider.controller;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.constants.RelationConstants;
import com.huntkey.rx.purchase.common.model.relation.RelationDTO;
import com.huntkey.rx.purchase.common.model.relation.RelationPageListDTO;
import com.huntkey.rx.purchase.common.model.relation.RelationPageParamDTO;
import com.huntkey.rx.purchase.provider.service.RelationService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExeFrequency;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 伙伴类Controller
 *
 * @author xuyf
 * @create 2017-12-29 11:13
 **/
@RestController
@RequestMapping("/pu/relation")
public class RelationController {

    private static final Logger logger = LoggerFactory.getLogger(RelationController.class);

    @Autowired
    private RelationService relationService;

    /**
     * 分页查询伙伴列表
     *
     * @param relationPageParamDTO
     * @return
     */
    @MethodRegister(
            edmClass = RelationConstants.className,
            methodCate = "采购系统方法",
            methodDesc = "伙伴类列表查询方法")
    @RequestMapping(value = "/queryList", method = RequestMethod.POST)
    public Result queryList(@RequestBody RelationPageParamDTO relationPageParamDTO) {
        Result result = new Result();
        Pagination<RelationPageListDTO> relationPageList = relationService.getRelationPageList(relationPageParamDTO);
        result.setData(relationPageList);
        return result;
    }

    /**
     * 根据伙伴id查询伙伴详情
     *
     * @param id
     * @return
     */
    @MethodRegister(
            edmClass = RelationConstants.className,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "伙伴类详情查询方法",
            getReqParamsNameNoPathVariable = {"id"}
    )
    @RequestMapping(value = "/queryObject", method = RequestMethod.GET)
    public Result queryObject(@RequestParam(value = "id")  String id){
        Result result = new Result();
        RelationDTO relationDTO = relationService.getRelationById(id);
        result.setData(relationDTO);
        return result;
    }


    @MethodRegister(
            edmClass = RelationConstants.className,
            methodCate = "采购系统方法",
            programCate = ProgramCate.Java,
            methodExeFrequency = MethodExeFrequency.Loop,
            methodExeInterval = 10,
            methodDesc = "伙伴类模糊查询方法",
            getReqParamsNameNoPathVariable = {"keyword", "type", "status"}
    )
    @RequestMapping(value = "/fuzzyQuery", method = RequestMethod.GET)
    public Result fuzzyQuery(@RequestParam(value = "keyword") String keyword, @RequestParam(value = "type") String type,
                             @RequestParam(value = "status") String[] status){
        Result result = new Result();
        List<RelationDTO> relationDTOList = relationService.fuzzyQueryRelationList(keyword, type, status);
        result.setData(relationDTOList);
        return result;
    }


    /**
     * 根据供应商获取币别集合和税率
     * @param supplierId
     * @return
     */
    @MethodRegister(
            edmClass = RelationConstants.RELATION,
            methodCate = "采购系统方法",
            methodDesc = "根据供应商ID获取币别集合、税率集合"
    )
    @RequestMapping(value = "/{supplierId}/currencyAndTaxrate",  method = RequestMethod.GET)
    public Result queryCurrRate(@PathVariable(value = "supplierId") String supplierId) {
        return relationService.queryCurrRate(supplierId);
    }

}
