package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.common.model.EdmCond;
import com.huntkey.rx.modeler.common.model.vo.EdmCondVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by licj on 2017/7/10.
 */
public interface EdmCondService {

    int insert(EdmCond edmCond);

    int update(EdmCond edmCond);

    /**
     * 根据属性id获取触发条件
     * @param edmpId
     * @return
     */
    List<EdmCondVO> getCondsByPropertyId(String edmpId);

    /**
     * 根据触发条件id删除触发条件
     * @param id
     */
    void delete(String id);

    int deleteByEdcoCondId(String condId);

    void clearCond(String id);

    EdmCond selectByPrimaryKey(String id);
}
