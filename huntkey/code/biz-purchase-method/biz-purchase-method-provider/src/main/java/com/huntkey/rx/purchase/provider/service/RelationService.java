package com.huntkey.rx.purchase.provider.service;

import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.purchase.common.model.relation.RelationDTO;
import com.huntkey.rx.purchase.common.model.relation.RelationPageListDTO;
import com.huntkey.rx.purchase.common.model.relation.RelationPageParamDTO;

import java.util.List;

/**
 * 伙伴Service接口
 *
 * @author zhangyu
 * @create 2017-12-29 10:40
 **/
public interface RelationService {

    /**
     * 分页查询伙伴
     *
     * @param relationPageParamDTO
     * @return
     */
    Pagination<RelationPageListDTO> getRelationPageList(RelationPageParamDTO relationPageParamDTO);

    /**
     * 根据伙伴id加载完整伙伴对象（含子集信息）
     *
     * @param relationId
     * @return
     */
    RelationDTO getRelationById(String relationId);

    /**
     * 根据关键字模糊查询伙伴集合
     * @param keyword
     * @param type
     * @param status
     * @return
     */
    List<RelationDTO> fuzzyQueryRelationList(String keyword, String type, String[] status);

    /**
     * 根据供应商ID获取币别集合、税率集合
     * @param supplierId
     * @return
     */
    Result queryCurrRate(String supplierId);

}
