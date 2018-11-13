package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.modeler.common.model.EdmCond;
import com.huntkey.rx.modeler.common.model.EdmConnect;
import com.huntkey.rx.modeler.common.model.EdmLink;
import com.huntkey.rx.modeler.common.model.vo.EdmLinkVO;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.List;

/**
 * Created by licj on 2017/4/13.
 */
public interface EdmLinkService {
    int insert(EdmLink edmLink);
    //扩展属性 - 关联 - 表单单数据插入
    void insertVO(EdmLinkVO edmLinkVO);
    //逻辑删
	int delete(String id);
	//批量逻辑删除
    void deleteIds(String[] ids);
    //改
    int update(EdmLink edmLink);
    // 扩展属性 - 关联 - 表单单数据更新
    void updateVO(EdmLinkVO edmLinkVO,String oldLinkId,boolean isPlan);
    //分页查询
    Pagination<EdmLink> selectByExample(String edmlEdmpId, String edmlEdmpLinkId, String edmlCond, String edmlFormula,
                                        Byte edcnLinkPreservable,Byte edclUpdateType,String edclUpdateTimeliness,String edclUpdateTime,
                                        int pageNum, int pageSize);

    /**
     *
     * @param edmlEdmpId
     * @param edmlEdmpLinkId
     * @return
     */
    boolean  checkcircle(String edmlEdmpId,String edmlEdmpLinkId);

    EdmLinkVO getById(String id);

    String checkLinkId(String edmpId, String linkid);

    String selectLinkidById(String id);

    List<List<EdmLinkVO>> getConnectsByCid(String id);

    List<EdmLinkVO> getConnectsByPid(String id);

    void clearEdmlFormula(String id);

    List<EdmLinkVO> getEdmLinks(String edmpId, String edmlCond);

    void moveEdmLink(String[] edmpIds, EdmCond edmCond);

    String getEdmlEdmpId(String edmlLinkId);
}
