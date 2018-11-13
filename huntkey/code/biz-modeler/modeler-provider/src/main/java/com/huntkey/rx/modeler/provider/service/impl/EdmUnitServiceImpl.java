package com.huntkey.rx.modeler.provider.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmUnit;
import com.huntkey.rx.modeler.common.model.EdmUnitExample;
import com.huntkey.rx.modeler.common.model.vo.EdmUnitVO;
import com.huntkey.rx.modeler.common.util.ExcelUtil;
import com.huntkey.rx.modeler.provider.dao.EdmConvoluteMapper;
import com.huntkey.rx.modeler.provider.dao.EdmUnitMapper;
import com.huntkey.rx.modeler.provider.service.EdmUnitService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;



/**
 * Created by licj on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class EdmUnitServiceImpl implements EdmUnitService {

    private static Logger log = LoggerFactory.getLogger(EdmUnitServiceImpl.class);

    @Autowired
    private EdmUnitMapper edmUnitMapper;

    @Override
    @Transactional(readOnly = false)
    public int insert(EdmUnit edmUnit){
        Date dateTime = new Date();
        edmUnit.setId(UuidCreater.uuid());
        edmUnit.setAddtime(dateTime);
        edmUnit.setModtime(dateTime);
        edmUnit.setIsDel((byte)0);
        return edmUnitMapper.insertSelective(edmUnit);
    }
	    //删
    @Override
    @Transactional(readOnly = false)
    public int delete(String id) {
        return edmUnitMapper.LogicDeleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public void deleteIds(String[] ids) {
        if(null != ids && ids.length>0){
            for(String id:ids) {
                if (!StringUtil.isNullOrEmpty(id))
                    edmUnitMapper.LogicDeleteByPrimaryKey(id);
            }
        }
    }
    //改
    @Override
    @Transactional(readOnly = false)
    public int update(EdmUnit edmUnit) {
        edmUnit.setModtime(new Date());
        return edmUnitMapper.updateByPrimaryKeySelective(edmUnit);
    }
    //查
    @Override
    public EdmUnit selectbyid(String id) {
        return edmUnitMapper.selectByPrimaryKey(id);
    }

    @Override
    public Pagination<EdmUnit> selectByExample(String edunEdmpId, String edunQtyEdmpId, int pageNum, int pageSize) {

        EdmUnitExample edmUnitExample = new EdmUnitExample();
        EdmUnitExample.Criteria criteria=edmUnitExample.createCriteria();
        if (edunEdmpId != null) {
            criteria.andEdunEdmpIdEqualTo(edunEdmpId);
        }
        if (edunQtyEdmpId != null){
            criteria.andEdunQtyEdmpIdEqualTo(edunQtyEdmpId);
        }
        Byte is_del = 0;
        criteria.andIsDelEqualTo(is_del);
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<EdmUnit> UnitList = edmUnitMapper.selectByExample(edmUnitExample);
        Pagination<EdmUnit> pageInfo = new Pagination<EdmUnit>(UnitList,pageNum,pageSize,page.getTotal());
        return pageInfo;
    }

}
