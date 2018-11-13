package com.huntkey.rx.provider.controller;

import com.huntkey.rx.base.LinkdetailEntity;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.EmployeeEntity;
import com.huntkey.rx.provider.service.LinkdetailService;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by linziy on 2017/11/23.
 */
@RestController
@RequestMapping("/link")
public class LinkdetailController {

    private static Logger logger = LoggerFactory.getLogger(LinkdetailController.class);

    @Autowired
    LinkdetailService linkdetailService;

    @RequestMapping("/insert")
    public Result insert() {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            //do something
            LinkdetailEntity linkdetailEntity = new LinkdetailEntity();
//            linkdetailEntity.setId("lex_test");
            linkdetailEntity.setLind_attr_value("attr_value");
            linkdetailEntity.setLind_attr("attr");
            linkdetailEntity.setLind_class("lndclass");
            linkdetailEntity.setLind_rele_attr("ddd");
            linkdetailEntity.setLind_status(Integer.valueOf(1));
            linkdetailEntity.setCreuser("lex");
            Serializable serializable = linkdetailService.insertLink(EmployeeEntity.class, linkdetailEntity);
            result.setData(serializable);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---add接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    @RequestMapping("/load")
    public Result load() {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            //do something
            String id = "lex_test";
            LinkdetailEntity link = linkdetailService.loadLink(EmployeeEntity.class, id);
            result.setData(link);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---add接口执行时间{}ms", endTime - beginTime);
        return result;
    }

    @RequestMapping("/query")
    public Result query() {
        Long beginTime = System.currentTimeMillis();
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        try {
            String lind_attr_value = "lind_attr_value";
            String moduser = "moduser";
            OrmParam ormParam = new OrmParam();
            List<String> clist = new ArrayList<>();//
            clist.add(lind_attr_value);
            clist.add(moduser);
            ormParam.setColumns(clist);
            String whereExp = OrmParam.and(ormParam.getEqualXML(lind_attr_value
                    , "attr_value"), ormParam.getEqualXML(moduser, "lex"));

            ormParam.setWhereExp(whereExp);
            List<LinkdetailEntity> list = linkdetailService.selectBeanListLink(EmployeeEntity.class, ormParam);
            result.setData(list);
        } catch (Exception e) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg(e.getMessage());
        }
        Long endTime = System.currentTimeMillis();
        logger.debug("---add接口执行时间{}ms", endTime - beginTime);
        return result;
    }

}
