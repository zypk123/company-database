package com.huntkey.rx.modeler.provider.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.EdmConvoluteVO;
import com.huntkey.rx.modeler.common.model.vo.EdmModelerVO;
import com.huntkey.rx.modeler.common.util.ExcelUtil;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.*;
import com.huntkey.rx.modeler.provider.service.EdmConvoluteService;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//

/**
 * Created by licj on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class EdmConvoluteServiceImpl implements EdmConvoluteService {

    private static Logger log = LoggerFactory.getLogger(EdmConvoluteServiceImpl.class);

    @Autowired
    private EdmConvoluteMapper edmConvoluteMapper;

    @Autowired
    private EdmModelerMapper edmModelerMapper;

    @Autowired
    private EdmClassMapper edmClassMapper;

    @Autowired
    private EdmPropertyMapper edmPropertyMapper;

    @Autowired
    private EdmCodeMapper edmCodeMapper;


    @Override
    @Transactional(readOnly = false)
    public int insert(EdmConvolute edmConvolute) {
        Date dateTime = new Date();
        edmConvolute.setId(UuidCreater.uuid());//uuid
        edmConvolute.setAddtime(dateTime);
        edmConvolute.setModtime(dateTime);
        edmConvolute.setIsDel((byte) 0);
        return edmConvoluteMapper.insertSelective(edmConvolute);
    }

    @Override
    @Transactional(readOnly = false)
    public int delete(String id) {
        return edmConvoluteMapper.LogicDeleteByPrimaryKey(id);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(EdmConvolute edmConvolute) {
        edmConvolute.setModtime(new Date());
        return edmConvoluteMapper.updateByPrimaryKeySelective(edmConvolute);
    }

    @Override
    public Pagination<EdmConvolute> selectByExample(String edcoEdmpId, Byte edcoUpdateType, String edcoUpdateTimeliness, String edcoUpdateTime,
                                                    String edcoConvoluteFormula, int pageNum, int pageSize) {
        EdmConvoluteExample edmConvoluteExpample = new EdmConvoluteExample();
        EdmConvoluteExample.Criteria criteria = edmConvoluteExpample.createCriteria();
        //属性id
        if (edcoEdmpId != null) {
            criteria.andEdcoEdmpIdEqualTo(edcoEdmpId);
        }

        //卷积公式
        if (edcoConvoluteFormula != null) {
            criteria.andEdcoConvoluteFormulaLike(edcoConvoluteFormula);
        }
        //逻辑未删
        Byte is_del = 0;
        criteria.andIsDelEqualTo(is_del);
        Page page = PageHelper.startPage(pageNum, pageSize);
        List<EdmConvolute> convoluteList = edmConvoluteMapper.selectByExample(edmConvoluteExpample);
        Pagination<EdmConvolute> pageInfo = new Pagination<EdmConvolute>(convoluteList, pageNum, pageSize, page.getTotal());
        return pageInfo;
    }

    public List<EdmClass> queryClassWithSon(List<EdmClass> edmClassList, String id) {
        List<EdmClass> edmClassListTemp = new ArrayList<EdmClass>();
        EdmClassExample edmClassExample = new EdmClassExample();
        edmClassExample.createCriteria().andEdmcParentIdEqualTo(id).andIsDelNotEqualTo((byte)1);
        List<EdmClass> edmClassListSon = edmClassMapper.selectByExample(edmClassExample);
        if (edmClassListSon != null && edmClassListSon.size() > 0) {
            edmClassListTemp.addAll(edmClassListSon);
            for (EdmClass edmClass : edmClassListSon) {
                queryClassWithSon(edmClassListTemp, edmClass.getId());
            }
        }
        if (edmClassListTemp.size() > 0) {
            edmClassList.addAll(edmClassListTemp);
        }
        return edmClassList;

    }

    public String getedmdStatusDesc(Byte edmdStatus) {
        String result = "";
        EdmCodeExample codeExample = new EdmCodeExample();
        codeExample.createCriteria().andCodeTypeEqualTo("edm_edmd_status").andCodeValueEqualTo(edmdStatus.toString());
        List<EdmCode> edmCodeList = edmCodeMapper.selectByExample(codeExample);
        if (edmCodeList != null) {
            if (edmCodeList.size() > 0) {
                if (!StringUtils.isEmpty(edmCodeList.get(0).getCodeName())) {
                    result = edmCodeList.get(0).getCodeName();
                }
            }
        }
        return result;
    }

    @Override
    public List<EdmConvoluteVO> selectConvoluteVOByFormulaEdmdnameEdmpname(String edcoConvoluteFormula, String edmpName,
                                                                           String edmcName, String edmdVer) {

        EdmConvoluteExample edmConvoluteExample = new EdmConvoluteExample();

        if (StringUtil.isNullOrEmpty(edcoConvoluteFormula)) {
            edmConvoluteExample.createCriteria().andIsDelNotEqualTo((byte) 1);
        } else {
            edmConvoluteExample.createCriteria().andIsDelNotEqualTo((byte) 1).andEdcoConvoluteFormulaEqualTo(edcoConvoluteFormula);
        }
        //查询卷积公式
        List<EdmConvolute> edmConvoluteList =edmConvoluteMapper.selectByExample(edmConvoluteExample);
        List<EdmConvoluteVO> edmConvoluteFiterList = null;
        if (edmConvoluteList != null) {
            edmConvoluteFiterList = new ArrayList<EdmConvoluteVO>();
            for (EdmConvolute edmConvolute : edmConvoluteList) {
                EdmConvoluteVO edmConvoluteVO = ModelToVO.edmConvoluteToVO(edmConvolute);

                EdmProperty edmProperty = null;//new EdmProperty();
                EdmClass edmClass = null;//new EdmClass();
                EdmModeler edmModeler = null;// new EdmModeler();

                //卷积公式描述赋值
                if (edmConvoluteVO.getEdcoConvoluteFormula() != null) {
                    EdmCodeExample codeExample = new EdmCodeExample();
                    codeExample.createCriteria().andCodeTypeEqualTo("edm_convolute_formula").andCodeValueEqualTo(edmConvoluteVO.getEdcoConvoluteFormula());
                    List<EdmCode> edmCodeList = edmCodeMapper.selectByExample(codeExample);
                    if (edmCodeList != null && edmCodeList.size() > 0 && !StringUtils.isEmpty(edmCodeList.get(0).getCodeName())) {
                        edmConvoluteVO.setEdcoConvoluteFormulaName(edmCodeList.get(0).getCodeName());
                    }
                }
                //获取所属属性
                if (!StringUtils.isEmpty(edmConvoluteVO.getEdcoEdmpId())) {
                    EdmPropertyExample edmPropertyExample = new EdmPropertyExample();
                    edmPropertyExample.createCriteria().andIsDelEqualTo((byte) 0).andIdEqualTo(edmConvoluteVO.getEdcoEdmpId());
                    List<EdmProperty> edmPropertyList = edmPropertyMapper.selectByExample(edmPropertyExample);
                    if (edmPropertyList != null && edmPropertyList.size() > 0) {
                        edmProperty = edmPropertyList.get(0);
                    }
                }
                //属性名过滤
                if ((edmProperty == null) || (edmProperty != null && !StringUtils.isEmpty(edmpName) && edmProperty.getEdmpName() != null && !edmProperty.getEdmpName().contains(edmpName))) {
                    continue;
                }

                //获取属性所属的类
                if (edmProperty != null && !StringUtils.isEmpty(edmProperty.getEdmpEdmcId())) {

                    EdmClassExample edmClassExample = new EdmClassExample();
                    edmClassExample.createCriteria().andIsDelEqualTo((byte) 0).andIdEqualTo(edmProperty.getEdmpEdmcId());
                    List<EdmClass> edmClassList = edmClassMapper.selectByExample(edmClassExample);
                    if (edmClassList != null && edmClassList.size() > 0) {
                        edmClass = edmClassList.get(0);
                    }
                }

                //类名过滤
                if ((edmClass == null ) || (edmClass != null && !StringUtils.isEmpty(edmcName) && edmClass.getEdmcName() != null && !edmClass.getEdmcName().contains(edmcName)) ){
                    continue;
                }
                //获取类所属的模型
                if (edmClass != null && !StringUtils.isEmpty(edmClass.getEdmcEdmdId())) {
                    EdmModelerExample edmModelerExample = new EdmModelerExample();
                    edmModelerExample.createCriteria().andIsDelEqualTo((byte) 0).andIdEqualTo(edmClass.getEdmcEdmdId());
                    List<EdmModeler> edmModelerList = edmModelerMapper.selectByExample(edmModelerExample);
                    if (edmModelerList != null && edmModelerList.size() > 0) {
                        edmModeler = edmModelerList.get(0);
                    }
                }
                //卷积扩展字段  赋值
                if (edmModeler != null) {
                    edmConvoluteVO.setEdmpName(edmProperty.getEdmpName());   //属性名赋值
                    edmConvoluteVO.setEdmcId(edmClass.getId());              //类ID赋值
                    edmConvoluteVO.setEdmcName(edmClass.getEdmcName());      //类名赋值
                    edmConvoluteVO.setEdmdId(edmModeler.getId());            //模型ID赋值
                    edmConvoluteVO.setEdmdCode(edmModeler.getEdmdCode());    //模型代码赋值
                    edmConvoluteVO.setEdmdStatus(edmModeler.getEdmdStatus());//模型状态赋值
                    edmConvoluteVO.setEdmdVer(edmModeler.getEdmdVer());      //模型版本赋值
                    if (edmConvoluteVO.getEdmdStatus() != null && getedmdStatusDesc(edmConvoluteVO.getEdmdStatus()) != null) {
                        edmConvoluteVO.setEdmdStatusDesc(getedmdStatusDesc(edmConvoluteVO.getEdmdStatus()));
                    }

                    //模型版本名过滤
                    if ((StringUtils.isEmpty(edmdVer)) ||
                            (!StringUtils.isEmpty(edmdVer) && edmModeler.getEdmdVer() != null && edmModeler.getEdmdVer().contains(edmdVer))) {

                        edmConvoluteFiterList.add(edmConvoluteVO);
                    }
                }

                //查找卷积公式所属类的子类
                List<EdmClass> edmEdmClassList = new ArrayList<EdmClass>();
                if (edmClass != null) {
                    edmEdmClassList = queryClassWithSon(edmEdmClassList, edmClass.getId());
                }

                if (edmEdmClassList != null) {
                    for (EdmClass edmClassSon : edmEdmClassList) {
                        EdmModeler edmModelerSon = new EdmModeler();
                        EdmConvoluteVO edmConvoluteSon = new EdmConvoluteVO();

                        //获取类所属的模型
                        if (edmClassSon != null && !StringUtils.isEmpty(edmClassSon.getEdmcEdmdId())) {
                            EdmModelerExample edmModelerSonExample = new EdmModelerExample();
                            edmModelerSonExample.createCriteria().andIsDelEqualTo((byte) 0).andIdEqualTo(edmClassSon.getEdmcEdmdId());
                            List<EdmModeler> edmModelerSonList = edmModelerMapper.selectByExample(edmModelerSonExample);
                            if (edmModelerSonList != null && edmModelerSonList.size() > 0) {
                                edmModelerSon = edmModelerSonList.get(0);
                            }
                        }
                        if (edmModelerSon != null) {
                            //模型名过滤  子类卷积赋值
                            if ((StringUtils.isEmpty(edmdVer)) ||
                                    (!StringUtils.isEmpty(edmdVer) && edmModelerSon.getEdmdVer() != null && edmModelerSon.getEdmdVer().contains(edmdVer))) {
                                edmConvoluteSon.setAdduser(edmConvoluteVO.getAdduser());
                                edmConvoluteSon.setAddtime(edmConvoluteVO.getAddtime());
                                edmConvoluteSon.setAcctid(edmConvoluteVO.getAcctid());
                                edmConvoluteSon.setEdcoConvoluteFormula(edmConvoluteVO.getEdcoConvoluteFormula());
                                edmConvoluteSon.setEdcoConvoluteFormulaName(edmConvoluteVO.getEdcoConvoluteFormulaName());
                                edmConvoluteSon.setEdcoEdmpId(edmConvoluteVO.getEdcoEdmpId());

                                edmConvoluteSon.setEdcoUpdateTypeName(edmConvoluteVO.getEdcoUpdateTypeName());

                                edmConvoluteSon.setEdcoFormulaDesc(edmConvoluteVO.getEdcoFormulaDesc());

                                edmConvoluteSon.setIsDel(edmConvoluteVO.getIsDel());
                                edmConvoluteSon.setId(edmConvoluteVO.getId());
                                edmConvoluteSon.setModtime(edmConvoluteVO.getModtime());
                                edmConvoluteSon.setModuser(edmConvoluteVO.getModuser());
                                edmConvoluteSon.setPropertyName(edmConvoluteVO.getPropertyName());

                                edmConvoluteSon.setEdmpName(edmConvoluteVO.getEdmpName());
                                edmConvoluteSon.setEdmcId(edmConvoluteVO.getEdmcId());
                                edmConvoluteSon.setEdmcName(edmClassSon.getEdmcName());
                                edmConvoluteSon.setEdmdCode(edmClassSon.getEdmcCode());


                                edmConvoluteSon.setEdmdId(edmModelerSon.getId());            //模型ID赋值
                                edmConvoluteSon.setEdmdCode(edmModelerSon.getEdmdCode());    //模型代码赋值
                                edmConvoluteSon.setEdmdStatus(edmModelerSon.getEdmdStatus());//模型状态赋值
                                edmConvoluteSon.setEdmdVer(edmModelerSon.getEdmdVer());      //模型版本赋值

                                if (edmConvoluteSon.getEdmdStatus() != null) {
                                    if (getedmdStatusDesc(edmConvoluteSon.getEdmdStatus()) != null) {
                                        edmConvoluteSon.setEdmdStatusDesc(getedmdStatusDesc(edmConvoluteSon.getEdmdStatus()));
                                    }
                                }
                                edmConvoluteFiterList.add(edmConvoluteSon);
                            }
                        }
                    }
                }
            }
        }

        return edmConvoluteFiterList;
    }

    @Override
    public String selectConvoluteFormulaByEdmpId(String edmpId) {
        return edmConvoluteMapper.selectConFormulaByEdcoEdmpId(edmpId);
    }


}
