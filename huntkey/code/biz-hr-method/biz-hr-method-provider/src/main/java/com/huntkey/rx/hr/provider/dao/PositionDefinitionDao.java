package com.huntkey.rx.hr.provider.dao;

import com.alibaba.fastjson.JSONArray;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.hr.common.model.PositionDefinitionDTO;
import com.huntkey.rx.hr.common.model.PostDefinitionOrderDTO;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import java.util.List;

/**
 * @author Created by weijian on 2017/11/13.
 */
public interface PositionDefinitionDao
{
    /**
     * 查询职位列表
     * @param ormParam ORM查询参数
     * @return
     */
    JSONArray queryPositionList(OrmParam ormParam);

    /**
     * 职位维护单加载方法
     * @return
     */
    List<PositionDefinitionDTO> load(String rpofType) throws Exception;

    /**
     * 职位维护单保存方法
     * @return
     */
    Result save(PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception;

    /**
     * 职位维护单修改方法
     * @return
     */
    Result update(PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception;

}
