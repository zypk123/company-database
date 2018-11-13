package com.huntkey.rx.modeler.provider.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.modeler.common.model.EdmConnect;
import com.huntkey.rx.modeler.common.model.EdmConnectExample;
import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.common.model.vo.EdmConnectVO;
import com.huntkey.rx.modeler.common.util.ExcelUtil;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.*;
import com.huntkey.rx.modeler.provider.service.EdmConnectService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//linziy

/**
 * Created by licj on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class EdmConnectServiceImpl implements EdmConnectService {

    private static Logger log = LoggerFactory.getLogger(EdmConnectServiceImpl.class);

    @Autowired
    private EdmConvoluteMapper edmConvoluteMapper;

    @Autowired
    private EdmConnectMapper edmConnectMapper;

    @Autowired
    private EdmLinkMapper edmLinkMapper;

    @Autowired
    private EdmPropertyMapper edmPropertyMapper;

    @Autowired
    private EdmMethodMapper edmMethodMapper;

    @Override
    @Transactional(readOnly = false)
    public int insert(EdmConnect edmConnect){
        Date dateTime = new Date();
        edmConnect.setId(edmConnect.getId());//uuid
        edmConnect.setAddtime(dateTime);
        edmConnect.setModtime(dateTime);
        edmConnect.setIsDel((byte)0);
        return  edmConnectMapper.insertSelective(edmConnect);
    }
    //删
    @Override
    @Transactional(readOnly = false)
    public void delete(String id) {
        EdmConnect edmConnect = edmConnectMapper.selectByPrimaryKey(id);
        edmConnectMapper.LogicDeleteByPrimaryKey(id);
        if(edmConnect != null){
            edmLinkMapper.logicDeleteByEdmlEdmpLinkId(edmConnect.getEdcnEdmpId());
        }
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteIds(String[] ids) {
        if(ids != null && ids.length>0){
            for(String id:ids) {
                if(!StringUtil.isNullOrEmpty(id)){
                    edmConnectMapper.LogicDeleteByPrimaryKey(id);
                }
            }
        }
    }

    //改
    @Override
    @Transactional(readOnly = false)
    public int update(EdmConnect edmConnect)
    {
        edmConnect.setModtime(new Date());
        return edmConnectMapper.updateByPrimaryKeySelective(edmConnect);
    }

    @Override
    public EdmConnect selectbyid(String id) {
        return edmConnectMapper.selectByPrimaryKey(id);
    }

    @Override
    public Pagination<EdmConnect> selectByExample(String edcnEdmpId, Byte edcnLinkPreservable, Byte edcnUpdateType, int pageNum, int pageSize)
    {
        EdmConnectExample edmConnectExample = new EdmConnectExample();
        EdmConnectExample.Criteria criteria = edmConnectExample.createCriteria();
        if (edcnEdmpId != null ){
            criteria.andEdcnEdmpIdEqualTo(edcnEdmpId);
        }
        //是否保存联动记录 0 否 1是
        if (edcnLinkPreservable != null) {
            criteria.andEdcnLinkPreservableEqualTo(edcnLinkPreservable);
        }

        //1.同步 2.异步 3.定时
        if ( edcnUpdateType != null) {
            criteria.andEdcnLinkPreservableEqualTo(edcnUpdateType);
        }
        //逻辑未删
        Byte is_del = 0;
        criteria.andIsDelEqualTo(is_del);
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<EdmConnect> ConnectList = edmConnectMapper.selectByExample(edmConnectExample);
        Pagination<EdmConnect> pageInfo = new Pagination<EdmConnect>(ConnectList,pageNum,pageSize,page.getTotal());
        return pageInfo;
    }

    @Override
    public EdmConnectVO getConnectByEdmpId(String edcnEdmpId) {
        EdmConnect edmConnect = edmConnectMapper.getEdmConnectPropertieByEdmpId(edcnEdmpId);
        EdmConnectVO edmConnectVO = null;
        if (edmConnect != null) {
            edmConnectVO = ModelToVO.edmConnectToVO(edmConnect);
            EdmMethod edmMethod = edmMethodMapper.selectByPrimaryKey(edmConnect.getEdcnType());
            if (edmMethod != null) {
                edmConnectVO.setEdcnType(edmMethod.getEdmmName());
                edmConnectVO.setMethodDesc(edmMethod.getEdmmDesc());
            }
        }

        return edmConnectVO;
    }

    @Override
    public List<EdmConnect> getConnectsOfClass(String classId) {
        if (StringUtil.isNullOrEmpty(classId)) { return null; }
        List<String> propertyIds = edmPropertyMapper.selectPropertyIdByClassId(classId);
        List<EdmConnect> edmConnects = new ArrayList<>();
        for (String propertyId : propertyIds) {
            if (StringUtil.isNullOrEmpty(propertyId)) { continue; }
            List<EdmConnect> connects = edmConnectMapper.selectEdmConnectListByPropertyId(propertyId);
            if (connects == null) { continue; }
            edmConnects.addAll(connects);
        }

        return edmConnects;
    }
}
