package com.huntkey.rx.purchase.provider.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.constants.GoodsFeatureConstants;
import com.huntkey.rx.purchase.common.model.goodsfeature.GoodsFeatureDTO;
import com.huntkey.rx.purchase.provider.service.GoodsFeatureService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author  liangh on 2017/12/27 0027.
 */
@RestController
@Validated
@RequestMapping("/pu/goodsfeature")
public class GoodsFeatureController {

    @Autowired
    private GoodsFeatureService goodsFeatureService;

    /**
     * 查询物品特征类列表接口
     * @param
     */
    @MethodRegister(
            edmClass = GoodsFeatureConstants.GOODSFEATURE,
            methodCate	= "采购系统方法",
            methodDesc = "查询物品特征类列表方法"
    )
    @RequestMapping(value = "/query", method = RequestMethod.GET)
    Result query() {
        return goodsFeatureService.query();
    }

    /**
     * 根据物品特性类id加载物品特征信息接口
     * @param id 物品id
     */
    @MethodRegister(
            edmClass = GoodsFeatureConstants.GOODSFEATURE,
            methodCate	= "采购系统方法",
            methodDesc = "加载物品特征信息方法",
            getReqParamsNameNoPathVariable = {"id"}
    )
    @RequestMapping(value = "/load", method = RequestMethod.GET)
    Result load(@RequestParam(value = "id") @NotEmpty(message = "物品特征id不能为空") String id) {
        return goodsFeatureService.load(id);
    }

    /**
     * 保存物品特征信息接口
     * @param goodsFeatureDTO
     */
    @MethodRegister(
            edmClass = GoodsFeatureConstants.GOODSFEATURE,
            methodCate = "采购系统方法",
            methodDesc = "保存物品特征信息方法"
    )
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    Result save(@RequestBody GoodsFeatureDTO goodsFeatureDTO) {
        return goodsFeatureService.save(goodsFeatureDTO);
    }

    /**
     * 根据物品特征id集合删除物品特征信息
     * @param ids 物品特征id集合
     */
    @MethodRegister(
            edmClass = GoodsFeatureConstants.GOODSFEATURE,
            methodCate = "采购系统方法",
            methodDesc = "删除物品特征方法"
    )
    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    Result delete(@RequestBody @NotEmpty(message = "物品特征ID数组不能为空") List<String> ids) {
        return goodsFeatureService.delete(ids);
    }

    /**
     * 验证物品特征名称唯一性
     * @param goftName 物品特征名称
     */
    @MethodRegister(
            edmClass = GoodsFeatureConstants.GOODSFEATURE,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"goftName"},
            methodDesc = "物品特征名称验证方法"
    )
    @RequestMapping(value = "/check", method = RequestMethod.GET)
    Result check(@RequestParam(value = "goftName") @NotEmpty(message = "物品特征名称不能为空") String goftName) {
        return goodsFeatureService.check(goftName);
    }

    /**
     * 根据枚举名称查询枚举列表方法
     * @param wordName 枚举名称
     */
    @MethodRegister(
            edmClass = GoodsFeatureConstants.GOODSFEATURE,
            methodCate = "采购系统方法",
            getReqParamsNameNoPathVariable = {"wordName"},
            methodDesc = "根据枚举名称查询枚举列表方法"
    )
    @RequestMapping(value = "/queryWordList", method = RequestMethod.GET)
    Result queryWordList(@RequestParam(value = "wordName", required = false) String wordName) {
        return goodsFeatureService.queryWordList(wordName);
    }

    /**
     * 上移下移
     * @param ids 物品特征id集合
     */
    @MethodRegister(
            edmClass = GoodsFeatureConstants.GOODSFEATURE,
            methodCate = "采购系统方法",
            methodDesc = "物品特征上移下移方法"
    )
    @RequestMapping(value = "/move", method = RequestMethod.POST)
    Result move(@RequestBody @NotEmpty(message = "物品特征ID数组不能为空") List<String> ids) {
        return goodsFeatureService.move(ids);
    }

}
