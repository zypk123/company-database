package com.huntkey.rx.modeler.provider.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.EdmCondVO;
import com.huntkey.rx.modeler.common.model.vo.EdmLinkVO;
import com.huntkey.rx.modeler.common.model.vo.EdmUnitVO;
import com.huntkey.rx.modeler.common.util.ExcelUtil;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.*;
import com.huntkey.rx.modeler.provider.feign.FormulaClient;
import com.huntkey.rx.modeler.provider.service.EdmCondService;
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
import java.util.Map;


/**
 * Created by licj on 2017/4/13.
 */
@Service
@Transactional(readOnly = true)
public class EdmCondServiceImpl implements EdmCondService {

    private static Logger log = LoggerFactory.getLogger(EdmCondServiceImpl.class);

    @Autowired
    private EdmCondMapper edmCondMapper;

    @Autowired
    private EdmLinkMapper edmLinkMapper;

    @Autowired
    private FormulaClient formulaClient;


    @Autowired
    private EdmConvoluteMapper edmConvoluteMapper;

    @Autowired
    private EdmConnectMapper edmConnectMapper;


    @Override
    @Transactional(readOnly = false)
    public int insert(EdmCond edmCond) {
        Date dateTime = new Date();
        edmCond.setId(UuidCreater.uuid());
        edmCond.setAddtime(dateTime);
        edmCond.setModtime(dateTime);
        edmCond.setIsDel((byte)0);
        return edmCondMapper.insertSelective(edmCond);
    }

    @Override
    @Transactional(readOnly = false)
    public int update(EdmCond edmCond) {
        edmCond.setModtime(new Date());
        return edmCondMapper.updateByPrimaryKeySelective(edmCond);
    }

    @Override
    public List<EdmCondVO> getCondsByPropertyId(String edmpId) {
        if (StringUtil.isNullOrEmpty(edmpId)) {
            return null;
        }

        List<EdmCond> edmCondList = edmCondMapper.getCondsByPropertyId(edmpId);
        EdmCondVO edmCondVO = null;
        List<EdmCondVO> edmCondVOList = new ArrayList<>();
        for (EdmCond edmCond : edmCondList) {
            edmCondVO = ModelToVO.edmCondToVO(edmCond);
            String edcoCond = edmCondVO.getEdcoCond();
            if (!StringUtil.isNullOrEmpty(edcoCond)) {
                Result result = formulaClient.selectByPrimaryKey(edcoCond);
                if (result != null) {
                    Map<String, String> data = (Map<String, String>) result.getData();
                    if (data != null) {
                        edmCondVO.setCondFormula(data.get("prplConditionName"));
                        edmCondVO.setCondFormulaDesc(data.get("prplConditionDesc"));
                    }
                }
            }

            String id = edmCondVO.getId();
            if (!StringUtil.isNullOrEmpty(id)) {
                List<EdmLinkVO> edmLinks = getEdmLinks(edmpId, id);
                if (edmLinks != null) {
                    edmCondVO.setEdmLinks(new ArrayList<>());
                    edmCondVO.getEdmLinks().addAll(edmLinks);
                }
            }
            edmCondVOList.add(edmCondVO);
        }
        return edmCondVOList;
    }

    private List<EdmLinkVO> selectEdmLinkVOListByEdmLinkList(List<EdmLink> edmLinkList) {
        List<EdmLinkVO> resultList = new ArrayList<EdmLinkVO>();
        List<String> formulas = new ArrayList<>();
        for (EdmLink edmLink : edmLinkList) {
            formulas.add(edmLink.getEdmlFormula());
            EdmLinkVO edmLinkVO = ModelToVO.edmLinkToVO(edmLink);
            //查询所属类名
            String edmpEdmcId = null;
            if (edmLink.getEdmlEdmpId() != null) {
                edmpEdmcId = edmConvoluteMapper.selectEdmpEdmcIdByEdcoEdmpId(edmLink.getEdmlEdmpId());
            }
            if (edmpEdmcId != null) {
                edmLinkVO.setEdmcId(edmpEdmcId);
                edmLinkVO.setEdmcName(edmConvoluteMapper.selectEdmcNameByEdmpEdmcId(edmpEdmcId));
            } else {
                edmLinkVO.setEdmcName("");
            }
            //查询属性名称
            if (edmLink.getEdmlEdmpId() != null) {
                edmLinkVO.setEdmpName(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(edmLink.getEdmlEdmpId()));
            } else {
                edmLinkVO.setEdmpName("");
            }
            //查询关联类名
            String edmpEdmcId_link = null;
            if (edmLink.getEdmlEdmpLinkId() != null) {
                edmpEdmcId_link = edmConvoluteMapper.selectEdmpEdmcIdByEdcoEdmpId(edmLink.getEdmlEdmpLinkId());
                edmLinkVO.setEdmlEdmpNameLink(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(edmLink.getEdmlEdmpLinkId()));
            } else {
                edmLinkVO.setEdmlEdmpNameLink("");
            }
            if (edmpEdmcId_link != null) {
                edmLinkVO.setEdmlEdmcIdLink(edmpEdmcId_link);
                edmLinkVO.setEdmlEdmcNameLink(edmConvoluteMapper.selectEdmcNameByEdmpEdmcId(edmpEdmcId_link));
            } else {
                edmLinkVO.setEdmlEdmcNameLink("");
            }

            //设置关联属性
            EdmConnect edmConnect = edmConnectMapper.getEdmConnectPropertieByEdmpId(edmLink.getEdmlEdmpLinkId());
            if (edmConnect != null) {
                edmLinkVO.setAsyncTypePriority(edmConnect.getAsyncTypePriority());
                edmLinkVO.setEdclUpdateTime(edmConnect.getEdcnUpdateTime());
                edmLinkVO.setEdcnLinkPreservable(edmConnect.getEdcnLinkPreservable());
                edmLinkVO.setEdclUpdateType(edmConnect.getEdcnUpdateType());
                edmLinkVO.setEdclType(edmConnect.getEdcnType());
            }
            resultList.add(edmLinkVO);
        }

        //设置对象定位公式
        Result result = formulaClient.getPrplConditionDescByPrplIdArr(null, formulas);
        Map<String, Map<String, String>> data = (Map<String, Map<String, String>>) result.getData();
        setLinkFormulas(resultList, data, null);

        return resultList;
    }

    //设置对象定位公式
    private void setLinkFormulas(List<EdmLinkVO> edmLinkVOS, Map<String, Map<String, String>> data, Map<String, String> map) {
        if (data != null) {

            Map<String, String> formulaMap = data.get("formulas");
            Map<String, String> formulaDescMap = data.get("formulaDescs");
            Map<String, String> condsMap = data.get("conds");
            Map<String, String> condDescsMap = data.get("condDescs");
            for (EdmLinkVO edmLinkVO : edmLinkVOS) {
                if (!StringUtil.isNullOrEmpty(edmLinkVO.getEdmlFormula()) && formulaMap != null) {
                    edmLinkVO.setFormula(formulaMap.get(edmLinkVO.getEdmlFormula()));
                }
                if (!StringUtil.isNullOrEmpty(edmLinkVO.getEdmlFormula()) && formulaDescMap != null) {
                    edmLinkVO.setFormulaDesc(formulaDescMap.get(edmLinkVO.getEdmlFormula()));
                }
                if (map != null) {
                    if (condsMap != null) {
                        edmLinkVO.setCondName(condsMap.get(map.get(edmLinkVO.getEdmlEdmpId())));
                    }
                    if (condDescsMap != null) {
                        edmLinkVO.setCondDesc(condDescsMap.get(map.get(edmLinkVO.getEdmlEdmpId())));
                    }
                }
            }
        }
    }

    private List<EdmLinkVO> getEdmLinks(String edmpId, String edmlCond) {
        if (StringUtil.isNullOrEmpty(edmpId)|| StringUtil.isNullOrEmpty(edmlCond)) {
            return null;
        }
        List<EdmLink> edmLinkList = edmLinkMapper.getEdmLinks(edmpId, edmlCond);
        List<EdmLinkVO> edmLinkVOS = selectEdmLinkVOListByEdmLinkList(edmLinkList);
        return edmLinkVOS;
    }
    /**
     * 根据触发条件id逻辑上删除触发条件
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void delete(String id) {
        EdmCond edmCond = edmCondMapper.selectByPrimaryKey(id);
        if (edmCond != null && !StringUtil.isNullOrEmpty(edmCond.getEdcoCond())) {
            formulaClient.delRelTrigger(edmCond.getEdcoCond());
        }
        edmLinkMapper.logicDeleteByCondId(id);
        edmCondMapper.logicDeleteById(id);
    }

    /**
     * 根据触发条件公式id删除触发条件
     * @param condId
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int deleteByEdcoCondId(String condId) {
        return edmCondMapper.deleteByEdcoCondId(condId);
    }

    /**
     * 清除触发条件公式
     * @param id
     */
    @Override
    @Transactional(readOnly = false)
    public void clearCond(String id) {
        if (StringUtil.isNullOrEmpty(id)) { return; }
        EdmCond edmCond = edmCondMapper.selectByPrimaryKey(id);
        if (edmCond != null && !StringUtil.isNullOrEmpty(edmCond.getEdcoCond())) {
            //调用biz_formula接口，删除触发条件公式
            formulaClient.delRelTrigger(edmCond.getEdcoCond());
        }
        edmCondMapper.clearCond(id);
    }

    /**
     * 根据触发条件id查找触发条件
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = true)
    public EdmCond selectByPrimaryKey(String id) {
        if (StringUtil.isNullOrEmpty(id)) { return null; }
        return edmCondMapper.selectByPrimaryKey(id);
    }
}
