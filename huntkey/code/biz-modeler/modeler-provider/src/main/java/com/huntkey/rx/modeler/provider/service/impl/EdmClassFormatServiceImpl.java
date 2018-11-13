package com.huntkey.rx.modeler.provider.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.*;
import com.huntkey.rx.modeler.common.model.vo.EdmClassFormatVO;
import com.huntkey.rx.modeler.common.util.Constant;
import com.huntkey.rx.modeler.common.util.ModelToVO;
import com.huntkey.rx.modeler.provider.dao.EdmClassFormatMapper;
import com.huntkey.rx.modeler.provider.dao.EdmClassMapper;
import com.huntkey.rx.modeler.provider.dao.EdmPropertyMapper;
import com.huntkey.rx.modeler.provider.feign.BaseInfoClient;
import com.huntkey.rx.modeler.provider.feign.OrmClient;
import com.huntkey.rx.modeler.provider.service.EdmClassFormatService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

@Service
@Transactional(readOnly = true)
public class EdmClassFormatServiceImpl implements EdmClassFormatService {

    private static Logger log = LoggerFactory.getLogger(EdmClassFormatServiceImpl.class);

    @Autowired
    private EdmClassFormatMapper edmClassFormatMapper;

    @Autowired
    private EdmPropertyMapper edmPropertyMapper;

    @Autowired
    private EdmClassMapper edmClassMapper;

    @Autowired
    private OrmClient ormClient;

    @Autowired
    private BaseInfoClient baseInfoClient;

    /**
     * 根据模型版本和模型更新说明查询模型
     * @param edmcId
     * @return
     */
    @Override
    public List<EdmClassFormatVO> selectClassFormatListByEdmcId(String edmcId){
        List<EdmClassFormatVO> edmClassFormatVOS = null;
        List<EdmClassFormat> edmClassFormats = edmClassFormatMapper.selectClassFormatListByEdmcId(edmcId);
        if(null != edmClassFormats && edmClassFormats.size()>0){
            edmClassFormatVOS = new ArrayList<>();
            for(EdmClassFormat edmClassFormat : edmClassFormats){
               EdmClassFormatVO edmClassFormatVO = ModelToVO.edmClassFormatToVO(edmClassFormat);
               edmClassFormatVO.setEdmpName(edmPropertyMapper.getEdmpNameById(edmClassFormat.getEdmfEdmpId()));
               edmClassFormatVOS.add(edmClassFormatVO);
            }
        }
        return edmClassFormatVOS;
    }

    /**
     * 批量新增对象呈现格式
     * @param edmClassFormatList
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public String insertBatch(List<EdmClassFormat> edmClassFormatList){
        String errMsg = null;
        //判断传入列表是否为空
        if(CollectionUtils.isEmpty(edmClassFormatList)){
            errMsg = "传入对象呈现特征列表为空";
            return errMsg;
        }
        Date date = new Date();
        List<EdmClassFormat> newList = new ArrayList<>();
        int i=1;
        for(EdmClassFormat edmClassFormat :edmClassFormatList){
            //判断连接符与属性id是否同时为空，同时为空则不进行任何操作
            if(StringUtil.isNullOrEmpty(edmClassFormat.getEdmfConnector()) && StringUtil.isNullOrEmpty(edmClassFormat.getEdmfEdmpId())){
                errMsg = "对象里呈现特征属性名称与连接符不能同时为空";
                return errMsg;
            }
            edmClassFormat.setId(UuidCreater.uuid());
            edmClassFormat.setAddtime(date);
            edmClassFormat.setModtime(date);
            edmClassFormat.setEdmfSeq(i);
            edmClassFormat.setIsDel((byte)0);
            i++;
            newList.add(edmClassFormat);
        }
        String edmcId = edmClassFormatList.get(0).getEdmfEdmcId();
        List<EdmClassFormat> edmClassFormats = edmClassFormatMapper.selectClassFormatListByEdmcId(edmcId);
        if(null != edmClassFormats && edmClassFormats.size()>0){//先删除再插入
            for(EdmClassFormat edmClassFormat : edmClassFormats){
                edmClassFormatMapper.updateIsDelByPrimaryKey(edmClassFormat.getId());
            }
        }
        edmClassFormatMapper.insertBatch(newList);
        return errMsg;
    }

    /**
     * 批量删除对象呈现格式，实际是更改模型数据的is_del值为1
     * @param ids
     * @return
     */
    @Override
    @Transactional(readOnly = false)
    public void deleteBatch(String[] ids){
        if(ids != null && ids.length>0) {
            for(String id:ids) {
                edmClassFormatMapper.updateIsDelByPrimaryKey(id);
            }
        }
    }

    @Override
    public Map getCharacterAndFormat(String classId) {
        Map map = null;
        //获取类特征值
        EdmClassFormatExample edmClassFormatExample = new EdmClassFormatExample();
        EdmClassFormatExample.Criteria criteria = edmClassFormatExample.createCriteria();
        criteria.andEdmfEdmcIdEqualTo(classId).andIsDelNotEqualTo((byte)1);
        edmClassFormatExample.setOrderByClause("edmf_seq asc");
        List<EdmClassFormat> edmClassFormatList = edmClassFormatMapper.selectByExample(edmClassFormatExample);
        List<String> propertyCodeList = null;
        if(null != edmClassFormatList && edmClassFormatList.size()>0) {
            map = new HashMap();
            propertyCodeList = new ArrayList<>();
            StringBuffer format = new StringBuffer();
            for(EdmClassFormat edmClassFormat:edmClassFormatList) {
                String edmpCode = edmPropertyMapper.getEdmpCodeById(edmClassFormat.getEdmfEdmpId());
                String connector = edmClassFormat.getEdmfConnector();
                if(!StringUtil.isNullOrEmpty(edmpCode)) {
                    propertyCodeList.add(edmpCode);
                    format.append(edmpCode);
                }
                if(!StringUtil.isNullOrEmpty(connector)){
                    format.append(connector);
                }
            }
            map.put("character",propertyCodeList);
            map.put("format",format.toString());
        }
        return map;
    }

    /**
     * 根据类id获取类的对象列表（呈现、枚举以及其他类）
     * @param classId
     * @param name
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Pagination<Map<String,String>> getAppearAndEnumObject(String classId,String name,int pageNum,int pageSize) {
        //根据类id查询类英文名称
        EdmClass edmClass = edmClassMapper.selectByPrimaryKey(classId);
        Pagination<Map<String,String>> pagination =null;
        if(edmClass != null){
            //查询特征值
            Map map = getCharacterAndFormat(classId);
            if(map != null){

                JSONObject datas = new JSONObject();
                String classNameEn = edmClass.getEdmcNameEn();
                datas.put(NodeConstant.EDMNAME,classNameEn);
                //获取特征属性
                List<String> characters = (List<String>)map.get("character");
                if(null != characters && characters.size()>0){
                    characters.add("id");
                    //组装参数
                    JSONObject pagenation = new JSONObject();
                    JSONObject search = new JSONObject();
                    JSONArray conditions = new JSONArray();
                    JSONObject condition = new JSONObject();
                    pagenation.put(NodeConstant.START_PAGE,pageNum);
                    pagenation.put(NodeConstant.ROWS,pageSize);
                    search.put(NodeConstant.COLUMNS,characters);
                    if(Constant.EDM_WORDLIST_NAME.equals(classNameEn)){
                        condition.put(NodeConstant.ATTR,"word_par");
                        condition.put(NodeConstant.OPERATOR,"is");
                        condition.put(NodeConstant.VALUE,"null");
                        conditions.add(condition);
                    }
                    if(!StringUtil.isNullOrEmpty(name)){
                        JSONObject condition2 = new JSONObject();
                        condition2.put(NodeConstant.ATTR,"word_name");
                        condition2.put(NodeConstant.OPERATOR,"like");
                        condition2.put(NodeConstant.VALUE,name);
                        conditions.add(condition2);
                    }
                    search.put(NodeConstant.CONDITIONS,conditions);
                    search.put(NodeConstant.PAGENATION,pagenation);
                    datas.put(NodeConstant.SEARCH,search);
                    System.out.println(datas.toJSONString());
                    //查询
                    Result result = ormClient.find(datas.toJSONString());
                    if(result.getRetCode() != 1){
                        throw new RuntimeException("orm异常");
                    }
                    JSONObject data = (JSONObject)JSONObject.toJSON(result.getData()) ;
                    JSONArray dataSet = (JSONArray)data.get(NodeConstant.DATASET);
                    if(dataSet != null){
                       // List<String> characters = (List<String>)map.get("character");
                        //总条数
                        Integer size = (Integer) data.get(NodeConstant.TOTALSIZE);
                        List<Map<String,String>> resutList = new ArrayList<>();
                        for(int i=0;i<dataSet.size();i++){
                            JSONObject object = dataSet.getJSONObject(i);
                            String id = (String) object.get("id");
                            String charactorFormat = (String)map.get("format");
                            Map<String,String> resultMap = new HashMap<>();
                            for(String edmpCode : characters){
                                String value = object.getString(edmpCode);
                                if(!StringUtil.isNullOrEmpty(value)){
                                    charactorFormat =charactorFormat.replace(edmpCode,value);
                                }else{
                                    charactorFormat = charactorFormat.replace(edmpCode,"");
                                }
                            }
                            resultMap.put(id,charactorFormat);
                            resutList.add(resultMap);
                        }
                        pagination = new Pagination(resutList,pageNum,pageSize,size);
                    }
                }
            }
        }
        return pagination;
    }

    /**
     * 获取枚举对象列表
     * @param wordName
     * @param pageNum
     * @param pageSize
     * @return
     */
    @Override
    public Result selectWordlists(String wordName, int pageNum, int pageSize){
        Result result = baseInfoClient.selectWordlists(wordName,pageNum,pageSize);
        if(result.getRetCode() !=1){
            //抛出异常
        }
        return result;
    }
}
