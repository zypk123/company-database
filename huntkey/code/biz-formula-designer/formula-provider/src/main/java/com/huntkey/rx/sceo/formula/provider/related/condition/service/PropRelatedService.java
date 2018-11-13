package com.huntkey.rx.sceo.formula.provider.related.condition.service;

import com.huntkey.rx.sceo.formula.common.model.TacPropertyRelated;

/**
 * 属性关联Service
 *
 * @author zhangyu
 * @create 2017-07-04 14:22
 **/
public interface PropRelatedService {


    /**
     * 保存属性关联条件
     *
     * @param tacPropertyRelated
     * @return
     */
    int savePropRelated(TacPropertyRelated tacPropertyRelated);


    /**
     * 更新属性关联条件
     *
     * @param tacPropertyRelated
     * @return
     */
    int updatePropRelated(TacPropertyRelated tacPropertyRelated);

    /**
     * 通过ID查找关联属性(触发条件)
     *
     * @param prplId
     * @return
     */
    TacPropertyRelated selectByPrimaryKey(String prplId);

    /**
     * 删除关联条件或者触发条件(条件也删除)
     *
     * @param prplId
     * @return
     * @throws Exception
     */
    int delRelCondOrRelTrigger(String prplId);

}
