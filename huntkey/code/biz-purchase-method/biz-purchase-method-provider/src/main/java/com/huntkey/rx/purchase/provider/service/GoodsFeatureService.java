package com.huntkey.rx.purchase.provider.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.goodsfeature.GoodsFeatureDTO;

import java.util.List;

/**
 * ClassName:GoodsFeatureService 物品特征类操作
 * Function: TODO ADD FUNCTION
 * Reason:	 TODO ADD REASON
 * Date:     2017年12月27日 上午11:55:29
 * @author   liangh
 * @version
 * @see
 */
public interface GoodsFeatureService {

    /**
     * 查询物品特征类列表接口
     * @param
     */
    Result query();

    /**
     * 根据物品特性类id加载物品特征信息接口
     * @param id 物品id
     */
    Result load(String id);

    /**
     * 保存物品特征信息接口
     * @param goodsFeatureDTO
     */
    Result save(GoodsFeatureDTO goodsFeatureDTO);

    /**
     * @ 根据物品特征id集合删除物品特征信息
     * @param ids 物品特征id集合
     */
    Result delete(List<String> ids);

    /**
     * 验证物品特征名称唯一性
     * @param goftName 物品特征名称
     */
    Result check(String goftName);

    /**
     * 根据枚举名称查询枚举列表方法
     * @param wordName 枚举名称
     */
    Result queryWordList(String wordName);

    /**
     * 上移下移
     * @param ids 物品特征id集合
     */
    Result move(List<String> ids);

}
