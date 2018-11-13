package com.huntkey.rx.sceo.formula.provider.record.service;

import com.huntkey.rx.sceo.formula.common.model.SourceSystemRecordMapping;

/**
 * @author nidongx on 2017/7/21.
 */
public interface RecordMappingService {

    /**
     * 新增源系统记录映射
     *
     * @param sourceSystemRecordMapping
     * @return
     */
    int addSourceSystemRecordMapping(SourceSystemRecordMapping sourceSystemRecordMapping);

    /**
     * 修改源系统记录映射
     *
     * @param sourceSystemRecordMapping
     * @return
     */
    int updateSourceSystemRecordMapping(SourceSystemRecordMapping sourceSystemRecordMapping);

    /**
     * 根据源关联编码和源关联类型查询公式描述
     *
     * @param sourceMappingId
     * @param sourceMappingType (1:属性公式、2:属性限值、3:关联条件)
     * @return
     */
    String queryFormularDec(String sourceMappingId, String sourceMappingType);

    /**
     * insert
     * @param sourceSystemRecordMapping
     * @return
     */
    int insert(SourceSystemRecordMapping sourceSystemRecordMapping);

    /**
     * 慎用 sourceMappingId不具有唯一性
     *
     * @param sourceMappingId
     * @return
     */
    SourceSystemRecordMapping queryBySourceMappingId(String sourceMappingId);

    /**
     * queryRecord
     * @param sourceMappingId
     * @param sourceMappingType
     * @param isEnable
     * @return
     */
    SourceSystemRecordMapping queryRecord(String sourceMappingId, String sourceMappingType, Byte isEnable);

    /**
     * 根据内部编号删除记录
     *
     * @param insideId
     * @return
     */
    int delRecordMapping(String insideId);

}
