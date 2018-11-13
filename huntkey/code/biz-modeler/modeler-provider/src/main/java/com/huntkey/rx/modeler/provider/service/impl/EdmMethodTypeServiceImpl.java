package com.huntkey.rx.modeler.provider.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmMethod;
import com.huntkey.rx.modeler.common.model.EdmMethodExample;
import com.huntkey.rx.modeler.common.model.EdmMethodType;
import com.huntkey.rx.modeler.common.model.EdmMethodTypeExample;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodTypeVO;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.EdmMethodMapper;
import com.huntkey.rx.modeler.provider.dao.EdmMethodTypeMapper;
import com.huntkey.rx.modeler.provider.service.EdmMethodTypeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by gujing on 2017/4/19 0019.
 */

@Service
@Transactional(readOnly = true)
public class EdmMethodTypeServiceImpl implements EdmMethodTypeService {

    private static Logger log = LoggerFactory.getLogger(EdmMethodTypeServiceImpl.class);

    @Autowired
    private EdmMethodTypeMapper edmMethodTypeMapper;

    @Autowired
    private EdmMethodMapper edmMethodMapper;

    @Override
    public EdmMethodType selectMethodTypeById(String id) {
        return edmMethodTypeMapper.selectByPrimaryKey(id);
    }


    public List<EdmMethodTypeVO> queryConstWithSon(EdmMethodTypeVO edmMethodTypeVO) {
        EdmMethodTypeExample edmMethodTypeExample = new EdmMethodTypeExample();
        edmMethodTypeExample.createCriteria().andIsDelNotEqualTo((byte)1).andEdmtParentIdEqualTo(edmMethodTypeVO.getId());
        edmMethodTypeExample.setOrderByClause("edmt_seq ASC");

        List<EdmMethodTypeVO> edmMethodTypeVOListSon = new ArrayList<EdmMethodTypeVO>();
        List<EdmMethodType> edmMethodTypeListSon = edmMethodTypeMapper.selectByExample(edmMethodTypeExample);

        if (null != edmMethodTypeListSon && edmMethodTypeListSon.size() > 0) {

            for (EdmMethodType edmMethodTypeTemp : edmMethodTypeListSon) {
                EdmMethodTypeVO edmMethodTypeVOTemp = ModelToVO.edmMethodTypeToVO(edmMethodTypeTemp);
                edmMethodTypeVOTemp.setChildren(edmMethodTypeVOListSon);
                edmMethodTypeVOTemp.setChildren(queryConstWithSon(edmMethodTypeVOTemp));
                edmMethodTypeVOListSon.add(edmMethodTypeVOTemp);
            }
        }

        return edmMethodTypeVOListSon;

    }

    @Override
    public List<EdmMethodTypeVO> selectEdmMethodTypeVOWithSonById(String id) {
        if (StringUtils.isEmpty(id)) {
            List<EdmMethodTypeVO> edmMethodTypeVOListAll = new ArrayList<EdmMethodTypeVO>();
            List<EdmMethodType> edmMethodTypeListAll = new ArrayList<EdmMethodType>();

            EdmMethodTypeExample EdmMethodTypeExampleTemp1 = new EdmMethodTypeExample();

            //
            EdmMethodTypeExample.Criteria criteria1 = EdmMethodTypeExampleTemp1.createCriteria();
            criteria1.andIsDelNotEqualTo((byte)1).andEdmtParentIdIsNull();

            EdmMethodTypeExample.Criteria criteria2 = EdmMethodTypeExampleTemp1.createCriteria();
            criteria2.andIsDelNotEqualTo((byte)1).andEdmtParentIdEqualTo("");

            EdmMethodTypeExampleTemp1.or(criteria2);
            EdmMethodTypeExampleTemp1.setOrderByClause("edmt_seq ASC");
            List<EdmMethodType> edmMethodTypeListTemp1 = edmMethodTypeMapper.selectByExample(EdmMethodTypeExampleTemp1);
            //

            if (null != edmMethodTypeListTemp1 && edmMethodTypeListTemp1.size() > 0) {
                edmMethodTypeListAll.addAll(edmMethodTypeListTemp1);
            }

            if (null != edmMethodTypeListAll && edmMethodTypeListAll.size() > 0) {
                for (EdmMethodType edmMethodType : edmMethodTypeListAll) {
                    EdmMethodTypeVO edmMethodTypeVO = ModelToVO.edmMethodTypeToVO(edmMethodType);
                    edmMethodTypeVO.setChildren(queryConstWithSon(edmMethodTypeVO));
                    edmMethodTypeVOListAll.add(edmMethodTypeVO);
                }
            }

            return edmMethodTypeVOListAll;

        } else {
            List<EdmMethodTypeVO> edmMethodTypeVOList = new ArrayList<EdmMethodTypeVO>();
            EdmMethodTypeExample edmMethodTypeVOExample = new EdmMethodTypeExample();
            edmMethodTypeVOExample.createCriteria().andIsDelEqualTo((byte) 0).andIdEqualTo(id);


            List<EdmMethodType> edmMethodTypeList = edmMethodTypeMapper.selectByExample(edmMethodTypeVOExample);
            if (null != edmMethodTypeList && edmMethodTypeList.size() > 0) {
                EdmMethodType edmMethodType = edmMethodTypeList.get(0);
                EdmMethodTypeVO edmMethodTypeVO = ModelToVO.edmMethodTypeToVO(edmMethodType);
                edmMethodTypeVO.setChildren(queryConstWithSon(edmMethodTypeVO));
                edmMethodTypeVOList.add(edmMethodTypeVO);
            }
            return edmMethodTypeVOList;
        }

    }


    @Override
    @Transactional(readOnly = false)
    public int delete(String id) {
        return edmMethodTypeMapper.updateIsDelByPrimaryKey(id);
    }

    @Override
    public String deleteValidate(String id) {
        String errorStr = null;
        // 检查子分类
        EdmMethodTypeExample edmMethodTypeExample = new EdmMethodTypeExample();
        edmMethodTypeExample.createCriteria().andIsDelNotEqualTo((byte)1).andEdmtParentIdEqualTo(id);
        List<EdmMethodType> edmMethodTypeList = edmMethodTypeMapper.selectByExample(edmMethodTypeExample);
        if (edmMethodTypeList != null && edmMethodTypeList.size() > 0) {
            errorStr = "方法分类删除失败：分类下存在子分类";
            return errorStr;
        }
        // 检查分类下的方法
        EdmMethodExample edmMethodExample = new EdmMethodExample();
        edmMethodExample.createCriteria().andIsDelNotEqualTo((byte)1).andEdmmTypeEqualTo(id);
        List<EdmMethod> edmMethodList = edmMethodMapper.selectByExample(edmMethodExample);

        if (edmMethodList != null && edmMethodList.size() > 0) {
            errorStr = "方法分类删除失败：分类下存在方法";
            return errorStr;
        }
        return errorStr;

    }

    @Override
    @Transactional(readOnly = false)
    public int insert(EdmMethodType edmMethodType) {
        Date date = new Date();
        edmMethodType.setId(UuidCreater.uuid());
        edmMethodType.setAddtime(date);
        edmMethodType.setModtime(date);
        edmMethodType.setIsDel((byte) 0);
        return edmMethodTypeMapper.insertSelective(edmMethodType);
    }


    /**
     * 检验方法分类编码唯一性
     * @param code
     * @return
     */
    @Override
    public String checkCodeUnique(String code) {
        String errorStr = null;
        if(StringUtil.isNullOrEmpty(code)){
            errorStr ="方法分类编码不能为空";
            return errorStr;
        }
        EdmMethodTypeExample edmMethodTypeExample = new EdmMethodTypeExample();
        edmMethodTypeExample.createCriteria().andIsDelNotEqualTo((byte)1).andEdmtCodeEqualTo(code);
        List<EdmMethodType> edmMethodTypeListTemp = edmMethodTypeMapper.selectByExample(edmMethodTypeExample);
        if (edmMethodTypeListTemp != null && edmMethodTypeListTemp.size() > 0) {
            errorStr ="方法分类编码已存在";
            return errorStr;
        }
        return errorStr;

    }


    /**
     * 检验方法分类名称唯一性
     * @param name
     * @return
     */
    @Override
    public String checkNameUnique(String name) {
        String errorStr = null;
        if(StringUtil.isNullOrEmpty(name)){
            errorStr ="方法分类名称不能为空";
            return errorStr;
        }
        EdmMethodTypeExample edmMethodTypeExample = new EdmMethodTypeExample();
        edmMethodTypeExample.createCriteria().andIsDelNotEqualTo((byte)1).andEdmtNameEqualTo(name);
        List<EdmMethodType> edmMethodTypeListTemp = edmMethodTypeMapper.selectByExample(edmMethodTypeExample);
        if (edmMethodTypeListTemp != null && edmMethodTypeListTemp.size() > 0) {
            errorStr ="方法分类名称已存在";
            return errorStr;
        }
        return errorStr;
    }

    @Override
    @Transactional(readOnly = false)
    public int update(EdmMethodType edmMethodType) {
        edmMethodType.setModtime(new Date());
        return edmMethodTypeMapper.updateByPrimaryKeySelective(edmMethodType);
    }

    @Override
    public String checkFatherNode(String parentId, String id) {
        String errorStr = null;
        if (!StringUtil.isNullOrEmpty(parentId)) {
            if(parentId.equals(id)){
                errorStr = "父分类不能为该分类本身";
                return errorStr;
            }
            List<EdmMethodTypeVO> edmMethodTypeVOList = selectEdmMethodTypeVOWithSonById(id);
            if (null != edmMethodTypeVOList && edmMethodTypeVOList.size()>0){
                boolean isSon = false;
                for (EdmMethodTypeVO edmMethodTypeVO : edmMethodTypeVOList){
                    if(edmMethodTypeVO.getEdmtParentId().equals(id) ){
                        isSon =true;
                        break;
                    }
                }
                if(isSon){
                    errorStr = "父分类不能为该分类的子分类";
                    return errorStr;
                }
            }
        }
        return errorStr;
    }

    @Override
    @Transactional(readOnly = false)
    public void moveMethodtypeMethodToMethodtype(String sourceMethodtypeId, String aimMethodtypeId) {
        edmMethodMapper.moveMethodtypeMethodToMethodtype(sourceMethodtypeId, aimMethodtypeId);
    }
}
