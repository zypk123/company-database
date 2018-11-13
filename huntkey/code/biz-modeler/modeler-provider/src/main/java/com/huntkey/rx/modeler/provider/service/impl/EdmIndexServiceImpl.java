package com.huntkey.rx.modeler.provider.service.impl;

import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.EdmAttachmentVO;
import com.huntkey.rx.modeler.common.model.vo.EdmIndexVO;
import com.huntkey.rx.modeler.common.util.ExcelUtil;
import com.huntkey.rx.modeler.provider.dao.*;
import com.huntkey.rx.modeler.provider.service.EdmClassService;
import com.huntkey.rx.modeler.provider.service.EdmIndexService;
import com.huntkey.rx.modeler.provider.service.EdmPropertyService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

//linziy add

/**
 * Created by zhuyj on 2017/9/22.
 */
@Service
@Transactional(readOnly = true)
public class EdmIndexServiceImpl implements EdmIndexService {

    private static Logger log = LoggerFactory.getLogger(EdmLinkServiceImpl.class);

    @Autowired
    private EdmIndexMapper edmIndexMapper;

    @Autowired
    private EdmIndexDetailMapper edmIndexDetailMapper;

    @Autowired
    EdmConvoluteMapper edmConvoluteMapper;

    @Autowired
    private EdmClassMapper edmClassMapper;

    @Autowired
    private EdmPropertyMapper edmPropertyMapper;


    /**
     * 新增索引信息
     * @param edmIndexVO
     * @return
     *
     */
    @Override
    @Transactional(readOnly = false)
    public int insert(EdmIndexVO edmIndexVO) {
        // 设置索引表信息
        EdmIndex edmIndex = new EdmIndex();
        String id = UuidCreater.uuid();
        Date date = new Date();
        edmIndex.setId(id);
        edmIndex.setIsDel((byte) 0);
        edmIndex.setAddtime(date);
        edmIndex.setModtime(date);
        String edmcId = edmIndexVO.getEdmcId();
        String edmpId = edmIndexVO.getEdmpAssId();
        if (edmcId.equals(edmpId)) { edmpId = null; }
        edmIndex.setEdmcId(edmcId);
        edmIndex.setEdmpAssId(edmpId);
        edmIndex.setIndexName(edmIndexVO.getIndexName());
        edmIndex.setIndexType(edmIndexVO.getIndexType());
        edmIndex.setAdduser(edmIndexVO.getAdduser());
        String edmpIds[] = edmIndexVO.getIndexProppertyIds().split(",");
        List<EdmIndexDetail> edmIndexDetailList = new ArrayList<>();

//        int seq = 0;
        // 设置索引详细表信息

        for (int i = 1; i<=edmpIds.length; i++ ) {
            EdmIndexDetail edmIndexDetail = new EdmIndexDetail();
            String newId = UuidCreater.uuid();
            edmIndexDetail.setId(newId);
            edmIndexDetail.setIndexId(id);
            edmIndexDetail.setEdmpId(edmpIds[i-1]);
            edmIndexDetail.setIndexSeq((byte) i);
            edmIndexDetail.setIsDel((byte) 0);
            edmIndexDetail.setAddtime(date);
            edmIndexDetail.setModtime(date);
            edmIndexDetail.setAdduser(edmIndexVO.getAdduser());
            edmIndexDetailList.add(edmIndexDetail);
        }
        edmIndexMapper.insertSelective(edmIndex);

        return edmIndexDetailMapper.insertList(edmIndexDetailList);
    }

    /**
     * 判断类下是否已存在主键索引，有返回true 没有返回false
     */
    private boolean hasPrimaryIndex(String edmClassId) {
        if (StringUtil.isNullOrEmpty(edmClassId)) { return true; }
        EdmIndexExample example = new EdmIndexExample();
        EdmIndexExample.Criteria criteria = example.createCriteria();
        criteria.andEdmcIdEqualTo(edmClassId).andIndexTypeEqualTo("primary ").andIsDelNotEqualTo((byte) 1);
        List<EdmIndex> edmIndexList = edmIndexMapper.selectByExample(example);

        if (edmIndexList != null && edmIndexList.size() > 0) {
            return true;
        }

        return false;
    }

    /**
     * 判断索引是否主键索引，是返回true 不是返回false
     */
    private boolean isPrimaryIndex(String edmIndexId) {
        if (StringUtil.isNullOrEmpty(edmIndexId)) { return true; }
        EdmIndexExample example = new EdmIndexExample();
        EdmIndexExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(edmIndexId).andIndexTypeEqualTo("primary ").andIsDelNotEqualTo((byte) 1);
        List<EdmIndex> edmIndexList = edmIndexMapper.selectByExample(example);

        if (edmIndexList != null && edmIndexList.size() > 0) {
            return true;
        }

        return false;
    }

    @Override
    public String checkPrimaryIndex(String edmClassId, String edmIndexId) {
        if (StringUtil.isNullOrEmpty(edmIndexId)) {
            if (hasPrimaryIndex(edmClassId)) {
                return "已存在主键索引";
            }
            return null;
        } else {
            if (!isPrimaryIndex(edmIndexId)) {
                if (hasPrimaryIndex(edmClassId)) {
                    return "已存在主键索引";
                }
                return null;
            }
            return null;
        }
    }

    /**
     * 更新索引信息
     * @param edmIndexVO
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int update(EdmIndexVO edmIndexVO) {
        // 设置索引表信息
        EdmIndex edmIndex = new EdmIndex();
        Date date = new Date();
        edmIndex.setId(edmIndexVO.getId());
        edmIndex.setIsDel((byte) 0);
        edmIndex.setAddtime(date);
        edmIndex.setModtime(date);
        String edmcId = edmIndexVO.getEdmcId();
        String edmpId = edmIndexVO.getEdmpAssId();
        if (edmcId.equals(edmpId)) { edmpId = null; }
        edmIndex.setEdmcId(edmcId);
        edmIndex.setEdmpAssId(edmpId);
        edmIndex.setIndexName(edmIndexVO.getIndexName());
        edmIndex.setIndexType(edmIndexVO.getIndexType());
        edmIndex.setAdduser(edmIndexVO.getAdduser());
        edmIndex.setAdduser(edmIndexVO.getModuser());

        edmIndexMapper.updateByPrimaryKey(edmIndex);

        // 删除索引详细表中的索引
        edmIndexDetailMapper.deleteByIndexId(edmIndexVO.getId());

        String edmpIds[] = edmIndexVO.getIndexProppertyIds().split(",");
        List<EdmIndexDetail> edmIndexDetailList = new ArrayList<>();

//        int seq = 0;
        // 设置索引详细表信息
        for (int i = 1; i<=edmpIds.length; i++ ) {
            EdmIndexDetail edmIndexDetail = new EdmIndexDetail();
            edmIndexDetail.setId(UuidCreater.uuid());
            edmIndexDetail.setIndexId(edmIndexVO.getId());
            edmIndexDetail.setEdmpId(edmpIds[i-1]);
            edmIndexDetail.setIndexSeq((byte) i);
            edmIndexDetail.setIsDel((byte) 0);
            edmIndexDetail.setAddtime(date);
            edmIndexDetail.setModtime(date);
            edmIndexDetail.setAdduser(edmIndexVO.getAdduser());
            edmIndexDetail.setAdduser(edmIndexVO.getModuser());
            edmIndexDetailList.add(edmIndexDetail);
        }
        return edmIndexDetailMapper.insertList(edmIndexDetailList);
    }

    @Override
    @Transactional(readOnly = false)
    public int deletes(String ids[]) {
        if (ids != null && ids.length > 0) {
            for (String id : ids) {
                edmIndexMapper.deleteByPrimaryKey(id);
                edmIndexDetailMapper.deleteByIndexId(id);
            }
        }
        return 1;
    }

    @Override
    public String checkIndexIndexProppertyIds(String indexProppertyIds) {
        String errorStr = null;
        if (StringUtil.isNullOrEmpty(indexProppertyIds)) {
            errorStr = "索引键列不能为空";
            return errorStr;
        }
        return errorStr;
    }
    @Override
    public String checkIndexName(String indexName, String edmcId, String id) {
        String errorStr = null;
        if (StringUtil.isNullOrEmpty(indexName)) {
            errorStr = "索引名称不能为空";
            return errorStr;
        } else if (indexName.length() > 32) {
            errorStr = "类简称长度不能大于32";
            return errorStr;
        }

        //类简称唯一性验证
        EdmIndex edmIndex = new EdmIndex();
        edmIndex.setIndexName(indexName);
        edmIndex.setEdmcId(edmcId);
        edmIndex.setId(id);
        List<EdmIndex> edmClassList = edmIndexMapper.selectByEdmIndex(edmIndex);
        if (edmClassList != null && edmClassList.size() > 0) {
            errorStr = "索引名称已存在";
            return errorStr;
        }
        return errorStr;
    }
}
