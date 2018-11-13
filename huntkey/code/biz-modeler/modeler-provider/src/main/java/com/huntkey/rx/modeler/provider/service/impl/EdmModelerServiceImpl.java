package com.huntkey.rx.modeler.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.*;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.common.util.ExcelUtil;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.*;
import com.huntkey.rx.modeler.provider.feign.OrmClient;
import com.huntkey.rx.modeler.provider.service.EdmModelerService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;
import com.netflix.discovery.converters.Auto;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import sun.misc.Version;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by liangh on 2017/4/12.
 */
@Service
@Transactional(readOnly = true)
public class EdmModelerServiceImpl implements EdmModelerService {

    private static Logger log = LoggerFactory.getLogger(EdmModelerServiceImpl.class);

    @Autowired
    private EdmModelerMapper edmModelerMapper;

    @Autowired
    private EdmCodeMapper edmCodeMapper;

    @Autowired
    private EdmClassMapper edmClassMapper;

    @Autowired
    private EdmPropertyMapper edmPropertyMapper;

    @Autowired
    private EdmConvoluteMapper edmConvoluteMapper;

    @Autowired
    private EdmLinkMapper edmLinkMapper;

    @Autowired
    private EdmConnectMapper edmConnectMapper;

    @Autowired
    private EdmUnitMapper edmUnitMapper;

    @Autowired
    private EdmMethodMapper edmMethodMapper;

    @Autowired
    private EdmAttachmentMapper edmAttachmentMapper;

    @Autowired
    private EdmCondMapper edmCondMapper;

    @Autowired
    private EdmIndexMapper edmIndexMapper;

    @Autowired
    private EdmIndexDetailMapper edmIndexDetailMapper;

    @Autowired
    private EdmClassFormatMapper edmClassFormatMapper;

    @Autowired
    private EdmMethodArgMapper edmMethodArgMapper;

    @Autowired
    private OrmClient ormClient;

    /**
     * 根据模型版本和模型更新说明查询模型
     * @param edmdVer
     * @param edmdUpdateDesc
     * @param pageNum
     * @param pageSize
     * @return EdmModelerVO
     */
    @Override
    public Pagination<EdmModelerVO> selectModelerListByExample(String edmdVer, String edmdUpdateDesc, int pageNum, int pageSize){
        EdmModelerExample example = new EdmModelerExample();
        EdmModelerExample.Criteria criteria = example.createCriteria().andIsDelNotEqualTo((byte)1);
        if(!StringUtil.isNullOrEmpty(edmdVer)){
            edmdVer = "%"+edmdVer+"%";
            criteria.andEdmdVerLike(edmdVer);
        }
        if(!StringUtil.isNullOrEmpty(edmdUpdateDesc)){
            edmdUpdateDesc = "%"+edmdUpdateDesc+"%";
            criteria.andEdmdUpdateDescLike(edmdUpdateDesc);
        }
        Page page = PageHelper.startPage(pageNum,pageSize);
        List<EdmModelerVO> VOList = getShowList(example);
        Pagination<EdmModelerVO> pageInfo = new Pagination<EdmModelerVO>(VOList,pageNum,pageSize,page.getTotal());
        return pageInfo;
    }

    /**
     * 根据模型id查询模型
     * @param id
     * @return EdmModelerVO
     */
    @Override
    public EdmModelerVO selectModelerByPrimaryKey(String id){
        EdmModelerVO edmModelerVO = null;
        EdmModeler edmModeler = edmModelerMapper.selectByPrimaryKey(id);
        if (edmModeler != null){
            edmModelerVO = getExtendModeler(edmModeler);
        }
        return edmModelerVO;
    }


    @Override
    public List<EdmModelerVO> selectByPrimaryKey(String id){
        EdmModelerExample example = new EdmModelerExample();
        EdmModelerExample.Criteria criteria = example.createCriteria();
        criteria.andIdEqualTo(id);
        List<EdmModelerVO> list =  getShowList(example);
        List<EdmModelerVO> returnList = new ArrayList<EdmModelerVO>();
        for(EdmModelerVO edmModelerVO:list){
            if(edmModelerVO.getEdmdStatus()==6){
                returnList.add(edmModelerVO);
            }
        }
        return returnList;
    }

    /**
     * 由EdmModeler转EdmModelerVO
     * @param example
     * @return EdmModelerVO
     */
    @Override
    public List<EdmModelerVO> getShowList(EdmModelerExample example){
        example.setOrderByClause("edmd_ver DESC");
        List<EdmModeler> edmModelers = edmModelerMapper.selectByExample(example);
        List<EdmModelerVO> VOList =null;
        if( null != edmModelers && edmModelers.size()>0){
            VOList = new ArrayList<EdmModelerVO>();
            //将主表list值赋值给VOList
            for(EdmModeler edmModeler:edmModelers){
                EdmModelerVO edmModelerVO = getExtendModeler(edmModeler);
                VOList.add(edmModelerVO);
            }
        }
        return VOList;
    }

    /**
     * 获取扩展字段的模型
     * @return
     */
    private EdmModelerVO getExtendModeler(EdmModeler edmModeler){
        EdmModelerVO edmModelerVO = ModelToVO.edmModelerToVO(edmModeler);
        //设置状态名称
        if(!StringUtils.isEmpty(edmModeler.getEdmdStatus())&&edmModeler.getEdmdStatus()!=0){
            edmModelerVO.setEdmdStatusName(edmCodeMapper.selectEdmdStatusNameBycodeValue(edmModeler.getEdmdStatus()));
        }
        return edmModelerVO;
    }

    /**
     * 获取当前最大的可用版本
     * @param
     * @return maxVer
     */
    @Override
    @Transactional(readOnly = true)
    public String selectModelerVer(){
        EdmModelerExample example = new EdmModelerExample();
        EdmModelerExample.Criteria criteria = example.createCriteria();
        criteria.andIsDelNotEqualTo((byte)1);
        List<EdmModeler> edmModelerList = edmModelerMapper.selectByExample(example);
        //获取最大的版本号
        String maxVer = "V1.0";
        if(null != edmModelerList && edmModelerList.size()>0){
            for(EdmModeler edmModeler:edmModelerList){
                if(!StringUtil.isNullOrEmpty(edmModeler.getEdmdVer())){
                    System.out.println(edmModeler.getEdmdVer());
                    double maxver = Double.valueOf(maxVer.substring(1,maxVer.length()));
                    double tempver = Double.valueOf(edmModeler.getEdmdVer().substring(1,edmModeler.getEdmdVer().length()));
                    if(maxver<tempver){
                        maxVer = "V" + Double.toString(tempver);
                    }
                }
            }
        }
        //最大版本号最后一位加一
        if(maxVer.contains(".")){
            String secondNum = maxVer.split("\\.")[1];
            int temp = 0;
            if(secondNum!=null&&!"".equals(secondNum)){
                temp = Integer.parseInt(secondNum)+1;
            }
            maxVer = maxVer.split("\\.")[0] + "." + Integer.toString(temp);
        }
        return maxVer;
    }

    /**
     * 在进行新增模型之前的判断，如有处于编辑状态的模型时，addStatus返回0，否则返回1,当没有处于编辑状态的模型时，即可新增模型，
     * 查询是否有已发布模型，如有，则查询出其id
     * @param
     * @return edmModelerVO
     */
    @Override
    @Transactional(readOnly = true)
    public EdmModelerVO beforeAdd(){
        //查询是否存在处在编辑状态的模型
        EdmModelerExample example = new EdmModelerExample();
        EdmModelerExample.Criteria criteria = example.createCriteria();
        List<Byte> lb = new ArrayList<Byte>();
        lb.add((byte)1);
        lb.add((byte)2);
        lb.add((byte)3);
        lb.add((byte)4);
        lb.add((byte)5);
        criteria.andEdmdStatusIn(lb);
        criteria.andIsDelNotEqualTo((byte)1);
        List<EdmModeler> editEdmModelerList = edmModelerMapper.selectByExample(example);
        if(editEdmModelerList.isEmpty()){//没查询出处在编辑状态的模型
			EdmModelerVO edmModelerVO = new EdmModelerVO();
			EdmModelerExample example2 = new EdmModelerExample();
			EdmModelerExample.Criteria criteria2 = example2.createCriteria();
			criteria2.andEdmdStatusEqualTo((byte)6);
			List<EdmModeler> publishedEdmModelerList = edmModelerMapper.selectByExample(example2);
			if(!publishedEdmModelerList.isEmpty()){
				edmModelerVO.setId(publishedEdmModelerList.get(0).getId());
			}
			edmModelerVO.setEdmdVer(selectModelerVer());
			edmModelerVO.setAddStatus(1);
            return edmModelerVO;
        }else{//查询到处在编辑状态的模型
            EdmModelerVO edmModelerVO = new EdmModelerVO();
            edmModelerVO.setAddStatus(0);
            return edmModelerVO;
        }
    }

    /**
     * 校验模型版本号唯一性
     * @param edmdVer
     * @return int
     */
    @Override
    @Transactional(readOnly = true)
    public String checkUnique(String edmdVer){
        String errorStr =null;
        if(StringUtil.isNullOrEmpty(edmdVer)){
            errorStr = "版本号不能为空";
            return errorStr;
        }
        //校验模型版本是否以v或者V开头
        String head = edmdVer.substring(0,1);
        if(!"v".equals(head) && !"V".equals(head)){
            errorStr ="模型版本号不符合要求，应该以v或者V开头";
            return errorStr;
        }
        EdmModelerExample checkExample = new EdmModelerExample();
        EdmModelerExample.Criteria criteria = checkExample.createCriteria();
        criteria.andEdmdVerEqualTo(edmdVer).andIsDelNotEqualTo((byte)1);
        List<EdmModeler> edmModelers = edmModelerMapper.selectByExample(checkExample);
        if (null != edmModelers && edmModelers.size()>0){
            errorStr ="版本号已存在";
            return errorStr;
        }
        return errorStr;
    }

    /**
     * 获取所有的版本名
     * @return
     */
    @Override
    public List<String> getAllVers() {
        return edmModelerMapper.selectAllVers();
    }

    /**
     * 新增模型
     * @param edmModeler
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void insert(EdmModeler edmModeler){
        //插入新增模型数据
        //String oldModelerId = "9314121da04b4642a02e2c2f0e3920bd";
        String oldModelerId = edmModeler.getId();
        String newModelerId = UuidCreater.uuid();
        edmModeler.setId(newModelerId);
        edmModeler.setIsDel((byte)0);
        Date date = new Date();
        edmModeler.setAddtime(date);
        edmModeler.setAdduser("admin");
        edmModeler.setModtime(date);
        edmModeler.setModuser("admin");
        edmModelerMapper.insert(edmModeler);
        //插入类的数据及其下级所有数据
		if(!StringUtil.isNullOrEmpty(oldModelerId)){
			insertAllData(newModelerId,oldModelerId);
		}else{
            //插入类七要素
            List<EdmCode> edmCodeList = null;
            List<EdmClass> edmClassList = new ArrayList<EdmClass>();
            EdmCodeExample example = new EdmCodeExample();
            EdmCodeExample.Criteria criteria = example.createCriteria();
            criteria.andCodeTypeEqualTo("edm_base_class");
            edmCodeList = edmCodeMapper.selectByExample(example);
            if(!edmCodeList.isEmpty()){
                for(EdmCode edmCode:edmCodeList){
                    EdmClass edmClass = new EdmClass();
                    edmClass.setId(UuidCreater.uuid());
                    edmClass.setEdmcEdmdId(edmModeler.getId());
                    edmClass.setEdmcNameEn(edmCode.getCodeName());
                    edmClass.setEdmcName(edmCode.getCodeExt1());
                    edmClass.setEdmcCode(edmCode.getCodeValue());
                    edmClass.setEdmcShortName(edmCode.getCodeExt4());
                    edmClass.setEdmcSeq(edmCode.getCodeSeq());
                    edmClass.setEdmcIndustryCode(edmCode.getCodeExt2());//插入行业编码
                    edmClass.setEdmcParentId(edmCode.getCodeExt3());//父子关系
                    edmClassList.add(edmClass);
                }
            }
            for(EdmClass edmClass:edmClassList){
                for(EdmClass ec:edmClassList){
                    if(!StringUtil.isNullOrEmpty(edmClass.getEdmcParentId()) && edmClass.getEdmcParentId().equals(ec.getEdmcCode())){
                        edmClass.setEdmcParentId(ec.getId());
                        break;
                    }
                }
                edmClass.setIsDel((byte)0);
                edmClass.setIsEntity((byte)0);
                edmClass.setAdduser(edmModeler.getAdduser());
                edmClass.setAddtime(new Date());
                edmClassMapper.insertSelective(edmClass);
            }
        }
    }

    //插入类的数据及其下级所有数据
    @Override
    @Transactional(readOnly = false)
    public void insertAllData(String newModelerId, String oldModelerId) {
        //获取旧模型下面的所有类
        EdmClassExample example = new EdmClassExample();
        EdmClassExample.Criteria criteria = example.createCriteria();
        criteria.andEdmcEdmdIdEqualTo(oldModelerId).andIsDelNotEqualTo((byte) 1);
        List<EdmClass> oldEdmClassList = edmClassMapper.selectByExample(example);
        Date date = new Date();

        for (EdmClass edmClass : oldEdmClassList) {
            //设置新类的id 保存旧类id
            String oldEdmClassId = edmClass.getId();
            String newEdmcClassId = UuidCreater.uuid();
            edmClass.setId(newEdmcClassId);

            //处理类关系
            for(EdmClass e:oldEdmClassList){
                if(e.getEdmcParentId() != null){
                    if(oldEdmClassId.equals(e.getEdmcParentId())){
                        e.setEdmcParentId(newEdmcClassId);
                    }
                }
            }

            //复制方法
            List<EdmMethod> edmMethods = edmMethodMapper.selectEdmMethodByCid(oldEdmClassId);
            List<EdmMethod> edmMethodsAll = null;
            if (null != edmMethods && edmMethods.size() > 0) {//处理类方法
                edmMethodsAll = new ArrayList<>();
                for (EdmMethod edmMethod : edmMethods) {
                    edmMethod.setId(UuidCreater.uuid());
                    edmMethod.setEdmmEdmcId(newEdmcClassId);
                    edmMethod.setAddtime(date);
                    edmMethod.setModtime(date);
                    edmMethodsAll.add(edmMethod);
                }
                edmMethodMapper.insertBatch(edmMethodsAll);
            }

            // 复制附件
            List<EdmAttachment> edmAttachments = edmAttachmentMapper.selectEdmAttachmentListByClassId(oldEdmClassId);
            List<EdmAttachment> edmAttachmentAll = null;
            if (null != edmAttachments && edmAttachments.size() > 0) {//处理类附件
                edmAttachmentAll = new ArrayList<EdmAttachment>();
                for (EdmAttachment edmAttachment : edmAttachments) {
                    edmAttachment.setId(UuidCreater.uuid());
                    edmAttachment.setEdmaEdmcId(newEdmcClassId);
                    edmAttachment.setAddtime(date);
                    edmAttachment.setModtime(date);
                    edmAttachmentAll.add(edmAttachment);
                }
                edmAttachmentMapper.insertBatch(edmAttachmentAll);
            }

            //复制属性
            List<EdmProperty> edmProperties = edmPropertyMapper.selectEdmPropertiesByCid(oldEdmClassId);
            List<EdmConvolute> edmConvolutes = new ArrayList<>();
            List<EdmConnect> edmConnects = new ArrayList<>();
            List<EdmLink> edmLinks = new ArrayList<>();
            List<EdmUnit> edmUnits = new ArrayList<>();
            List<EdmCond> edmConds = new ArrayList<>();
            Map<String, String> idMap = new HashMap<>();

            if (edmProperties != null && edmProperties.size() > 0) {
                for (EdmProperty edmProperty : edmProperties) {
                    idMap.put(edmProperty.getId(), UuidCreater.uuid());

                    // 处理卷积属性
                    EdmConvolute edmConvolute = edmConvoluteMapper.selectEdmConvoluteByPropertyId(edmProperty.getId());
                    if (edmConvolute != null) { edmConvolutes.add(edmConvolute); }

                    // 关联属性
                    List<EdmLink> edmLinkList = edmLinkMapper.selectEdmLinkListByPropertyId(edmProperty.getId());
                    if (edmLinkList != null) { edmLinks.addAll(edmLinkList); }

                    // 联动属性
                    EdmConnect edmConnect = edmConnectMapper.getEdmConnectPropertieByEdmpId(edmProperty.getId());
                    if (edmConnect != null) { edmConnects.add(edmConnect); }

                    // 单位
                    List<EdmUnit> edmUnitList = edmUnitMapper.selectEdmUnitListByPropertyId(edmProperty.getId());
                    if (edmUnitList != null) { edmUnits.addAll(edmUnitList); }

                    //触发条件
                    List<EdmCond> edmCondList = edmCondMapper.getCondsByPropertyId(edmProperty.getId());
                    if (edmCondList != null) { edmConds.addAll(edmCondList); }
                }
            }

            if (edmProperties != null && edmProperties.size() > 0) {
                for (EdmProperty edmProperty : edmProperties) {
                    if (Constant.PROPERTY_TYPE_ASSEMBLE.equals(edmProperty.getEdmpValueType())) {
                        for (EdmProperty edmPropertySet : edmProperties) {// 处理属性集的关系
                            if (!StringUtil.isNullOrEmpty(edmPropertySet.getEdmpParentId())
                                    && edmPropertySet.getEdmpParentId().equals(edmProperty.getId())) {
                                edmPropertySet.setEdmpParentId(idMap.get(edmProperty.getId()));
                            }
                        }
                    }
                }

                for (EdmProperty edmProperty : edmProperties) {
                    String edmpCode = edmProperty.getEdmpCode();
                    if (!StringUtil.isNullOrEmpty(edmpCode) && edmpCode.length() > 3) {
                        edmProperty.setEdmpCode(edmpCode.replaceAll(edmpCode.substring(0, edmpCode.length() - 3), edmClass.getEdmcShortName()));
                    }

                    edmProperty.setId(idMap.get(edmProperty.getId()));
                    edmProperty.setEdmpEdmcId(newEdmcClassId);
                    edmProperty.setAddtime(date);
                    edmProperty.setModtime(date);
                }
                edmPropertyMapper.insertBatch(edmProperties);
            }

            if (edmConvolutes != null && edmConvolutes.size() > 0) {
                for (EdmConvolute edmConvolute : edmConvolutes) {
                    edmConvolute.setId(UuidCreater.uuid());
                    edmConvolute.setEdcoEdmpId(idMap.get(edmConvolute.getEdcoEdmpId()));
                    edmConvolute.setAddtime(date);
                    edmConvolute.setModtime(date);
                }
                edmConvoluteMapper.insertBatch(edmConvolutes);
            }

            if (edmConds != null && edmConds.size() > 0) {
                for (EdmCond edmCond : edmConds) {
                    idMap.put(edmCond.getId(), UuidCreater.uuid());
                }
            }
            if (edmConds != null && edmConds.size() > 0) {
                int i = 0;
                Date time = new Date();
                for (EdmCond edmCond : edmConds) {
                    time.setTime(System.currentTimeMillis() + (i++) * 1000);
                    edmCond.setId(idMap.get(edmCond.getId()));
                    edmCond.setEdcoEdmpId(idMap.get(edmCond.getEdcoEdmpId()));
                    edmCond.setAddtime(time);
                    edmCond.setModtime(time);
                }
                edmCondMapper.insertBatch(edmConds);
            }

            if (edmLinks != null && edmLinks.size() > 0) {
                int i = 0;
                Date time = new Date();
                for (EdmLink edmLink : edmLinks) {
                    time.setTime(System.currentTimeMillis() + (i++) * 1000);
                    if (idMap.get(edmLink.getEdmlEdmpLinkId()) != null) {
                        edmLink.setEdmlEdmpLinkId(idMap.get(edmLink.getEdmlEdmpLinkId()));
                    }
                    edmLink.setId(UuidCreater.uuid());
                    edmLink.setEdmlEdmpId(idMap.get(edmLink.getEdmlEdmpId()));
                    edmLink.setEdmlCond(idMap.get(edmLink.getEdmlCond()));
                    edmLink.setAddtime(time);
                    edmLink.setModtime(time);
                }
                edmLinkMapper.insertBatch(edmLinks);
            }



            if (edmConnects != null && edmConnects.size() > 0) {
                for (EdmConnect edmConnect : edmConnects) {
                    edmConnect.setId(UuidCreater.uuid());
                    edmConnect.setEdcnEdmpId(idMap.get(edmConnect.getEdcnEdmpId()));
                    edmConnect.setAddtime(date);
                    edmConnect.setModtime(date);
                }
                edmConnectMapper.insertBatch(edmConnects);
            }

            if (edmUnits != null && edmUnits.size() > 0) {
                for (EdmUnit edmUnit : edmUnits) {
                    edmUnit.setId(UuidCreater.uuid());
                    edmUnit.setEdunEdmpId(idMap.get(edmUnit.getEdunEdmpId()));
                    edmUnit.setAddtime(date);
                    edmUnit.setModtime(date);
                }
                edmUnitMapper.insertBatch(edmUnits);
            }

            //复制索引
            List<EdmIndex> indexList = edmIndexMapper.selectIndexsByEdmcId(oldEdmClassId);
            List<EdmIndexDetail> edmIndexDetailList = new ArrayList<>();
            List<EdmIndexDetail> edmIndexDetails = new ArrayList<>();
            if (null != indexList && indexList.size() > 0) {
                int i = 0;
                Date time = new Date();
                for (EdmIndex edmIndex : indexList) {
                    idMap.put(edmIndex.getId(), UuidCreater.uuid());
                    List<EdmIndexDetail> temp = edmIndexDetailMapper.selectByIndexId(edmIndex.getId());
                    if (temp != null) {
                        edmIndexDetailList.addAll(temp);
                    }
                }
                for (EdmIndex edmIndex : indexList) {
                    time.setTime(System.currentTimeMillis() + (i++) * 1000);
                    edmIndex.setId(idMap.get(edmIndex.getId()));
                    edmIndex.setEdmcId(newEdmcClassId);
                    edmIndex.setEdmpAssId(idMap.get(edmIndex.getEdmpAssId()));
                    edmIndex.setAddtime(time);
                    edmIndex.setModtime(time);
                }
                if (edmIndexDetailList != null && edmIndexDetailList.size() > 0) {
                    for (EdmIndexDetail edmIndexDetail : edmIndexDetailList) {
                        if (idMap.get(edmIndexDetail.getEdmpId()) != null) {
                            edmIndexDetail.setId(UuidCreater.uuid());
                            edmIndexDetail.setIndexId(idMap.get(edmIndexDetail.getIndexId()));
                            edmIndexDetail.setEdmpId(idMap.get(edmIndexDetail.getEdmpId()));
                            edmIndexDetail.setAddtime(date);
                            edmIndexDetail.setModtime(date);
                            edmIndexDetails.add(edmIndexDetail);
                        }
                    }
                }
                edmIndexMapper.insertBatch(indexList);
                edmIndexDetailMapper.insertList(edmIndexDetails);
            }

            //复制对象呈现格式
            List<EdmClassFormat> edmClassFormats = edmClassFormatMapper.selectClassFormatListByEdmcId(oldEdmClassId);
            if (edmClassFormats != null && edmClassFormats.size() > 0) {
                for (EdmClassFormat edmClassFormat : edmClassFormats) {
                    edmClassFormat.setId(UuidCreater.uuid());
                    edmClassFormat.setEdmfEdmcId(newEdmcClassId);
                    edmClassFormat.setEdmfEdmpId(idMap.get(edmClassFormat.getEdmfEdmpId()));
                    edmClassFormat.setAddtime(date);
                    edmClassFormat.setModtime(date);
                }
                edmClassFormatMapper.insertBatch(edmClassFormats);
            }
        }
        for (EdmClass edmClass : oldEdmClassList) {
            //插入类数据
            edmClass.setEdmcEdmdId(newModelerId);
            edmClass.setAddtime(date);
            edmClass.setAdduser("admin");
            edmClass.setModtime(date);
            edmClass.setModuser("admin");
            edmClassMapper.insert(edmClass);
        }
    }

    @Override
    public String queryModelerIdByVer(String version) {
        String edmdId = edmModelerMapper.selectEdmdIdByVer(version);
        return edmdId;
    }

    @Override
    public HSSFWorkbook exportExcel(String id) {
        HSSFWorkbook wb = new HSSFWorkbook();
        EdmClassExample example = new EdmClassExample();
        EdmClassExample.Criteria criteria = example.createCriteria().andEdmcEdmdIdEqualTo(id);
        criteria.andIsDelNotEqualTo((byte) 1);
        List<EdmClass> list = edmClassMapper.selectByExample(example);
        List<EdmClassVO> resultList = new ArrayList<EdmClassVO>();
        List<String> classIdList = new ArrayList<>();
        for (EdmClass ec : list) {
            classIdList.add(ec.getId());
            EdmClassVO edmClassVO = ModelToVO.edmClassToVO(ec);
            String parentName = edmClassMapper.selectParentNameByEdmcParentId(ec.getEdmcParentId());
            edmClassVO.setParentName(parentName);
            resultList.add(edmClassVO);
        }
        wb =exportEdmClassVOToExcel(wb,resultList);
        wb =exportEdmPropertyVOToExcel(wb,classIdList);
        wb =exportEdmConvoluteVOToExcel(wb,classIdList);
        wb =exportEdmLinkVOToExcel(wb,classIdList);
        wb =exportEdmConnectVOToExcel(wb,classIdList);
        wb =exportEdmUnitVOToExcel(wb,classIdList);
        wb =exportEdmMethodVOToExcel(wb,classIdList);
        wb =exportEdmAttachmentVOToExcel(wb,classIdList);
        wb =exportIndexVOToExcel(wb,classIdList);
        return wb;
    }

    //导出类
    private HSSFWorkbook exportEdmClassVOToExcel(HSSFWorkbook wb, List<EdmClassVO> list){
        wb =ExcelUtil.exportEdmClassVOToExcel(wb, list);
        return wb;
    }

    //导出属性
    private HSSFWorkbook exportEdmPropertyVOToExcel(HSSFWorkbook wb, List<String> classIdList){
        List<EdmPropertyVO> resultList = new ArrayList<EdmPropertyVO>();
        for (String s : classIdList) {
            EdmPropertyExample example = new EdmPropertyExample();
            EdmPropertyExample.Criteria criteria = example.createCriteria().andEdmpEdmcIdEqualTo(s).andIsDelNotEqualTo((byte) 1);
            List<EdmProperty> list = edmPropertyMapper.selectByExample(example);
            for (EdmProperty ep : list) {
                EdmPropertyVO edmPropertyVO = ModelToVO.edmPropertyToVO(ep);
                //属性类型为普通属性、枚举、对象链接、对象时分别处理
                if (Constant.NORMAL_OBJ.equals(ep.getEdmpValueType())) {
                    edmPropertyVO.setEdmpDataName(edmPropertyMapper.selectEdmpValueName(ep.getEdmpDataType()));
                } else if (Constant.EDM_WORDLIST.equals(ep.getEdmpValueType())){
                    edmPropertyVO.setEdmpDataName("枚举类." + getWordName(ep.getEdmpDataType()));
                } else if (Constant.EDM_OBJECT.equals(ep.getEdmpValueType())
                        || Constant.EDM_OBJECT.equals(ep.getEdmpValueType())) {
                    edmPropertyVO.setEdmpDataName(edmClassMapper.getEdmcNameById(ep.getEdmpDataType()));
                }
                edmPropertyVO.setEdmpEdmcName(edmPropertyMapper.selectEdmpEdmcNameByEdmpEdmcId(ep.getEdmpEdmcId()));
                edmPropertyVO.setEdmpValueName(edmPropertyMapper.selectEdmpValueNameByEdmpValueType(ep.getEdmpValueType()));
                edmPropertyVO.setEdmpEdmmName(edmPropertyMapper.selectEdmpEdmmNameByEdmpEdmmId(ep.getEdmpEdmmId()));
                edmPropertyVO.setEdmpParentName(edmPropertyMapper.selectEdmpParentNameByEdmpParentId(ep.getEdmpParentId()));
                resultList.add(edmPropertyVO);
            }
        }
        wb = ExcelUtil.exportEdmPropertyVOToExcel(wb, resultList);
        return wb;
    }

    /**
     * 根据枚举编码获取枚举名称
     * @param edmpDataType
     * @return
     */
    private String getWordName(String edmpDataType) {
        SearchParam searchParam = new SearchParam("wordlist");
        searchParam.addCond_equals("info_code", edmpDataType);

        System.out.println(searchParam.toJSONString());
        Result result = ormClient.find(searchParam.toJSONString());
        JSONObject object = (JSONObject) JSONObject.toJSON(result.getData());

        String wordName = "";
        if (object != null) {
            JSONArray dataSet = (JSONArray) object.get(NodeConstant.DATASET);
            if (dataSet != null && dataSet.size() > 0) {
                JSONObject jsonObject = (JSONObject) dataSet.get(0);
                if (jsonObject != null) {
                    wordName = (String) jsonObject.get("word_name");
                }
            }
        }

        return wordName;
    }

    //导出卷积
    public HSSFWorkbook exportEdmConvoluteVOToExcel(HSSFWorkbook wb, List<String> classIdList) {
        List<EdmConvoluteVO> resultList = new ArrayList<EdmConvoluteVO>();
        List<EdmConvolute> edmConvoluteList = new ArrayList<EdmConvolute>();
        for (String classId : classIdList) {
            List<String> propertyIdList = edmConvoluteMapper.selectPropertyIdListByClassId(classId);
            for (String propertyId : propertyIdList) {
                EdmConvolute temp = edmConvoluteMapper.selectEdmConvoluteByPropertyId(propertyId);
                if (temp != null) {
                    EdmConvoluteVO edmConvoluteVO = ModelToVO.edmConvoluteToVO(temp);
                    String edmpEdmcId = edmConvoluteMapper.selectEdmpEdmcIdByEdcoEdmpId(temp.getEdcoEdmpId());
                    edmConvoluteVO.setEdmcName(edmConvoluteMapper.selectEdmcNameByEdmpEdmcId(edmpEdmcId));
                    edmConvoluteVO.setEdmpName(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(temp.getEdcoEdmpId()));
                    resultList.add(edmConvoluteVO);
                }
            }
        }
        wb = ExcelUtil.exportEdmConvoluteVOToExcel(wb, resultList);
        return wb;
    }

    //导出关联
    private HSSFWorkbook exportEdmLinkVOToExcel(HSSFWorkbook wb, List<String> classIdList){
        List<EdmLinkVO> resultList = new ArrayList<>();
        for (String classId : classIdList) {
            List<String> propertyIdList = edmConvoluteMapper.selectPropertyIdListByClassId(classId);
            for (String propertyId : propertyIdList) {
                List<EdmLink> tempList = edmLinkMapper.selectEdmLinkListByPropertyId(propertyId);
                if (tempList !=null) {
                    for (EdmLink edmLink : tempList) {
                        EdmLinkVO edmLinkVO = new EdmLinkVO();
                        //查询所属类名
                        String edmpEdmcId = "";
                        if (edmLink.getEdmlEdmpId() != null) {
                            edmpEdmcId = edmConvoluteMapper.selectEdmpEdmcIdByEdcoEdmpId(edmLink.getEdmlEdmpId());
                        }
                        if (edmpEdmcId != null) {
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
                        String edmpEdmcId_link = "";
                        if (edmLink.getEdmlEdmpLinkId() != null) {
                            edmpEdmcId_link = edmConvoluteMapper.selectEdmpEdmcIdByEdcoEdmpId(edmLink.getEdmlEdmpLinkId());
                            edmLinkVO.setEdmlEdmpNameLink(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(edmLink.getEdmlEdmpLinkId()));
                        } else {
                            edmLinkVO.setEdmlEdmpNameLink("");
                        }
                        if (edmpEdmcId_link != null) {
                            edmLinkVO.setEdmlEdmcNameLink(edmConvoluteMapper.selectEdmcNameByEdmpEdmcId(edmpEdmcId_link));
                        } else {
                            edmLinkVO.setEdmlEdmcNameLink("");
                        }
                        //查询关联属性名称
                        //edmLinkVO.setEdmlEdmpNameLink(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(edmLink.getEdmlEdmpLinkId()));
                        edmLinkVO.setEdmlCond(edmLink.getEdmlCond());
                        edmLinkVO.setEdmlFormula(edmLink.getEdmlFormula());
                        resultList.add(edmLinkVO);
                    }
                }
            }
        }
        wb = ExcelUtil.exportEdmLinkVOToExcel(wb, resultList);
        return wb;
    }

    //导出联动
    public HSSFWorkbook exportEdmConnectVOToExcel(HSSFWorkbook wb, List<String> classIdList){
        List<EdmConnectVO> resultList = new ArrayList<>();
        for(String classId:classIdList){
            List<String> propertyIdList = edmConvoluteMapper.selectPropertyIdListByClassId(classId);
            for(String propertyId:propertyIdList){
                List<EdmConnect> tempList = edmConnectMapper.selectEdmConnectListByPropertyId(propertyId);
                if(tempList != null){
                    for(EdmConnect edmConnect:tempList){
                        EdmConnectVO edmConnectVO = ModelToVO.edmConnectToVO(edmConnect);
                        //查询所属类名
                        String edmpEdmcId = edmConvoluteMapper.selectEdmpEdmcIdByEdcoEdmpId(edmConnect.getEdcnEdmpId());
                        edmConnectVO.setEdmcName(edmConvoluteMapper.selectEdmcNameByEdmpEdmcId(edmpEdmcId));
                        //查询属性名称
                        edmConnectVO.setEdmpName(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(edmConnect.getEdcnEdmpId()));
                        resultList.add(edmConnectVO);
                    }
                }
            }
        }
        wb = ExcelUtil.exportEdmConnectVOToExcel(wb,resultList);
        return wb;
    }

    //导出单位
    public HSSFWorkbook exportEdmUnitVOToExcel(HSSFWorkbook wb,List<String> classIdList){
        List<EdmUnitVO> resultList = new ArrayList<>();
        for(String classId:classIdList){
            List<String> propertyIdList = edmConvoluteMapper.selectPropertyIdListByClassId(classId);
            for(String propertyId:propertyIdList){
                List<EdmUnit> tempList = edmUnitMapper.selectEdmUnitListByPropertyId(propertyId);
                if(!tempList.isEmpty()){
                    for(EdmUnit edmUnit:tempList){
                        EdmUnitVO edmUnitVO = new EdmUnitVO();
                        //查询所属类名
                        String edmpEdmcId = edmConvoluteMapper.selectEdmpEdmcIdByEdcoEdmpId(edmUnit.getEdunEdmpId());
                        edmUnitVO.setEdmcName(edmConvoluteMapper.selectEdmcNameByEdmpEdmcId(edmpEdmcId));
                        //查询属性名称
                        edmUnitVO.setEdmpName(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(edmUnit.getEdunEdmpId()));
                        //查询关联类名
                        String edmpEdmcId_link = edmConvoluteMapper.selectEdmpEdmcIdByEdcoEdmpId(edmUnit.getEdunQtyEdmpId());
                        edmUnitVO.setEdmcQtyName(edmConvoluteMapper.selectEdmcNameByEdmpEdmcId(edmpEdmcId_link));
                        //查询关联属性名称
                        edmUnitVO.setEdmpQtyName(edmConvoluteMapper.selectEdmpNameByEdcoEdmpId(edmUnit.getEdunQtyEdmpId()));
                        resultList.add(edmUnitVO);
                    }
                }
            }
        }
        wb = ExcelUtil.exportEdmUnitVOToExcel(wb,resultList);
        return wb;
    }

    //导出方法
    public HSSFWorkbook exportEdmMethodVOToExcel(HSSFWorkbook wb, List<String> classIdList) {
        List<EdmMethodVO> resultList = new ArrayList<EdmMethodVO>();
        if (!classIdList.isEmpty()) {
            for (String classId : classIdList) {
                List<EdmMethod> edmMethodList = edmMethodMapper.selectEdmMethodByCid(classId);
                String name = edmClassMapper.getEdmcNameById(classId);
                // 设置所属类名
                for (EdmMethod edmMethod : edmMethodList) {
                    EdmMethodVO edmMethodVO  = ModelToVO.edmMethodToVO(edmMethod);
                    edmMethodVO.setEdmcName(name);
                    // 查询程序类别名称
                    edmMethodVO.setEdmProgramTypeName(edmMethodMapper
                            .selectEdmProgramTypeNameByEdmmProgramType(edmMethod.getEdmmProgramType()));
                    // 缺少方法输入参数和方法返回值
                    // 查询方法输入参数和方法返回值
                    StringBuilder edmEdmdInsertArgName = new StringBuilder();
                    StringBuilder edmEdmdReturnName = new StringBuilder();
                    // 输入参数描述
                    StringBuilder edmInsertArgDesc = new StringBuilder();
                    // 返回参数描述
                    StringBuilder edmReturnDesc = new StringBuilder();

                    EdmMethodArgExample example = new EdmMethodArgExample();
                    EdmMethodArgExample.Criteria criteria = example.createCriteria();
                    criteria.andIsDelNotEqualTo((byte)1).andEdmaEdmmIdEqualTo(edmMethod.getId());
                    List<EdmMethodArg> edmMethodArgs = edmMethodArgMapper.selectByExample(example);
                    if (edmMethodArgs != null && edmMethodArgs.size() > 0) {
                        for (EdmMethodArg edmMethodArg : edmMethodArgs) {
                            //“1”代表参数类型是输入参数, "2"代表参数类型是返回值
                            if (edmMethodArg.getEdmaType() == 1) {
                                edmEdmdInsertArgName.append(edmMethodArg.getEdmaName()).append(",");
                                edmInsertArgDesc.append(edmMethodArg.getEdmaDesc()).append(",");
                            } else if (edmMethodArg.getEdmaType() == 2) {
                                edmEdmdReturnName.append(edmMethodArg.getEdmaName());
                                edmReturnDesc.append(edmMethodArg.getEdmaDesc());
                            }
                        }
                    }

                    //有数据时，去掉尾部逗号
                    if (!StringUtil.isNullOrEmpty(edmEdmdInsertArgName)) {
                        edmEdmdInsertArgName.delete(edmEdmdInsertArgName.length() - 1, edmEdmdInsertArgName.length());
                    }
                    if (!StringUtil.isNullOrEmpty(edmInsertArgDesc)) {
                        edmInsertArgDesc.delete(edmInsertArgDesc.length() - 1, edmInsertArgDesc.length());
                    }

                    edmMethodVO.setEdmEdmdInsertArgName(edmEdmdInsertArgName.toString());
                    edmMethodVO.setEdmEdmdReturnName(edmEdmdReturnName.toString());
                    edmMethodVO.setEdmInsertArgDesc(edmInsertArgDesc.toString());
                    edmMethodVO.setEdmReturnDesc(edmReturnDesc.toString());
                    resultList.add(edmMethodVO);
                }
            }
        }
        wb = ExcelUtil.exportEdmMethodVOToExcel(wb, resultList);
        return wb;
    }

    //导出附件
    public HSSFWorkbook exportEdmAttachmentVOToExcel(HSSFWorkbook wb, List<String> classIdList) {
        List<EdmAttachmentVO> resultList = new ArrayList<>();
        for (String classId : classIdList) {
            List<EdmAttachment> tempList=edmAttachmentMapper.selectEdmAttachmentListByClassId(classId);
            if(null != tempList && tempList.size()>0){
                for(EdmAttachment edmAttachment : tempList){
                    EdmAttachmentVO edmAttachmentVO = ModelToVO.edmAttachmentToVO(edmAttachment);
                    if (edmAttachmentVO != null) {
                        // 查询所属类名
                        edmAttachmentVO
                                .setEdmcName(edmConvoluteMapper.selectEdmcNameByEdmpEdmcId(edmAttachment.getEdmaEdmcId()));
                        resultList.add(edmAttachmentVO);
                    }
                }
            }
        }
        wb = ExcelUtil.exportEdmAttachmentVOToExcel(wb, resultList);
        return wb;
    }

    //导出索引
    public HSSFWorkbook exportIndexVOToExcel(HSSFWorkbook wb, List<String> classIdList) {
        List<EdmIndexVO> list = new ArrayList<>();
        for (String classId : classIdList) {
            List<EdmIndex> tempList = edmIndexMapper.selectIndexsByEdmcId(classId);
            if(null != tempList && tempList.size()>0){
                for (EdmIndex edmIndex : tempList){
                    EdmIndexVO edmIndexVO = ModelToVO.edmIndexToVO(edmIndex);
                    if(!StringUtil.isNullOrEmpty(edmIndex.getEdmpAssId())){
                        edmIndexVO.setEdmcName(edmPropertyMapper.getEdmpNameById(edmIndex.getEdmpAssId()));
                    }else{
                        edmIndexVO.setEdmcName(edmClassMapper.getEdmcNameById(edmIndex.getEdmcId()));
                    }
                    List<EdmIndexDetail> edmIndexDetailList = edmIndexDetailMapper.selectByIndexId(edmIndex.getId());
                    if(null != edmIndexDetailList && edmIndexDetailList.size()>0){
                        StringBuffer indexPropertyNames = new StringBuffer();
                        for(EdmIndexDetail edmIndexDetail: edmIndexDetailList){
                            String name = edmPropertyMapper.getEdmpNameById(edmIndexDetail.getEdmpId());
                            if (!StringUtil.isNullOrEmpty(name)) {
                                indexPropertyNames.append(name).append(",");
                            }
                        }
                        if(indexPropertyNames.length()>0){
                            indexPropertyNames.deleteCharAt(indexPropertyNames.length()-1);
                        }
                        edmIndexVO.setIndexPropertyNames(indexPropertyNames.toString());
                    }
                    list.add(edmIndexVO);
                }
            }
        }
        wb = ExcelUtil.exportEdmIndexVOToExcel(wb, list);
        return wb;
    }

    /**
     * 根据模型数据更新模型
     * @param edmModeler
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void updateByPrimaryKey(EdmModeler edmModeler){
        edmModeler.setModtime(new Date());
        edmModeler.setModuser("admin");
        if(edmModeler.getEdmdStatus()!=null){
            if(edmModeler.getEdmdStatus()==6){//判断是否将状态设置成已发布状态，如果是，则将数据库里面其他已发布状态的模型设置成作废状态
                edmModelerMapper.updateStatusWithPublishedModeler();
            }
        }
        edmModelerMapper.updateByPrimaryKeySelective(edmModeler);
    }

    /**
     * 删除模型，实际是更改模型数据的is_del值为1
     * @param id
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public int updateIsDelByPrimaryKey(String id){
        return edmModelerMapper.updateIsDelByPrimaryKey(id);
    }


    /**
     * 判断路径是否存在，如果不存在则新建路径
     * @param fileUrl
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public String judgeFilePath(String fileUrl){
        //判断输入路径的盘符是否存在
        boolean judgeRoot = false;
        File[] roots = File.listRoots();
        for(File file:roots){
            String fileUrlHead = fileUrl.substring(0,1).toLowerCase();
            String rootHead = file.getAbsolutePath().substring(0,1).toLowerCase();
            if(fileUrlHead.equals(rootHead)){
                judgeRoot = true;
            }
        }
        if(judgeRoot){//盘符存在
            //判断路径是否存在
            File file =new File(fileUrl);
            if(!file.exists()&&!file.isDirectory()){
                System.out.println("目录不存在，新建目录");
                try{
                    file.mkdir();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }else{
                System.out.println("目录存在");
            }
            Date date = new Date(System.currentTimeMillis());
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
            String fileName = dateFormat.format(date);
            if(!fileUrl.substring(fileUrl.length()-1,fileUrl.length()).equals("/")){
                fileUrl = fileUrl + "/";
            }
            fileUrl = fileUrl + fileName + ".xls";
        }else{//盘符不存在
            System.out.println("盘符不存在");
            //System.exit(0);
        }
        return fileUrl;
    }
}
