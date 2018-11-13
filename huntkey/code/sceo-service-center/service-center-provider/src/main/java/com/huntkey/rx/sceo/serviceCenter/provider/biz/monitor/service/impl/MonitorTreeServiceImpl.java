package com.huntkey.rx.sceo.serviceCenter.provider.biz.monitor.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.elasticsearch.index.IndexNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.serviceCenter.common.emun.OperatorType;
import com.huntkey.rx.sceo.serviceCenter.common.model.ConditionNode;
import com.huntkey.rx.sceo.serviceCenter.common.model.FullInputArgument;
import com.huntkey.rx.sceo.serviceCenter.common.model.LoadParam;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.exception.ServiceException;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.monitor.service.MonitorTreeService;
import com.huntkey.rx.sceo.serviceCenter.provider.client.EDMClient;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;
import com.huntkey.rx.sceo.serviceCenter.provider.service.Persistance;

/**
 * Created by zhaomj on 2017/8/10.
 */
@Service
public class MonitorTreeServiceImpl implements MonitorTreeService {
    
	public static final String MONITOR_CLASS_PROP_RESOURCES = "moni_sub_res";
	public static final String MONITOR_CLASS_PROP_RESOUTCES_OBJ = ".moni_res_set";
	private static final Logger logger = LoggerFactory.getLogger(MonitorTreeServiceImpl.class);
	
	private static final String ROOT_LVL = "1";
	private static final String ROOT_LVL_CODE = "1,";
	private static final String MONITOR_HISTORY_SET=".moni_his_set";
	private static final String MONITOR_VERSION = "monitortree";
	
	private static final String MTOR_RES_SET = ".mtor_res_set";
	
    private static final String SPE_HIS_SET = ".mdep_chag_set"; 
	
	@Autowired
	EDMClient edmClient;

	@Autowired
	private Persistance persistance;
	

    @Override
    public JSONArray getMonitorClasses(String treeName, String beginTime, String endTime, String edmdVer,
            String edmcNameEn) throws DBException {
        
        JSONArray monitorClasses = new JSONArray();
        Result monitorClassResult = edmClient.getEntityByVersionAndEnglishName(edmdVer, edmcNameEn);

        if (monitorClassResult.getRetCode() != Result.RECODE_SUCCESS) {
            logger.error("根据类英文名和版本号查询类所有实体子类接口调用出错！");
            throw new ServiceException("根据类英文名和版本号查询类所有实体子类接口调用出错！");
        }
        
        if (monitorClassResult.getData() == null) {
            return monitorClasses;
        }

        JSONArray edmClasses = JSONArray.parseArray(JSONObject.toJSONString(monitorClassResult.getData()));

        for (int i = 0; i < edmClasses.size(); i++) {
            JSONObject tempEdm = edmClasses.getJSONObject(i);
            String searchEdmName = tempEdm.getString("edmcNameEn");

            JSONObject temp = new JSONObject();
            temp.put("id", tempEdm.getString("id"));
            temp.put("edmcName", tempEdm.getString("edmcName"));
            temp.put("edmcEdmdId", tempEdm.getString("edmcEdmdId"));
            temp.put("edmcNameEn", searchEdmName);
            
            Result resourcesResult = edmClient.getPropertyValue(tempEdm.getString("id"),
                    MONITOR_CLASS_PROP_RESOURCES);

            if (resourcesResult.getRetCode() != Result.RECODE_SUCCESS) {
                logger.error("根据监管类查询从属资源类失败！");
                throw new ServiceException("根据监管类查询从属资源类失败！");
            }
            if (resourcesResult.getData() != null) {
                JSONObject jsonObject = JSONObject
                        .parseObject(JSON.toJSONString(resourcesResult.getData()));
                String dataType = jsonObject.getString("dataType");
                if ("class".equals(dataType)) {
                    JSONObject value = jsonObject.getJSONObject("value");
                    temp.put("resourceName", value.getString("edmcName"));
                    temp.put("resourceEdmNameEn", value.getString("edmcNameEn"));
                    temp.put("resourceEdmId", value.getString("id"));
                }
            }
            
            // TODO 新增历史表的查询 - lijie begin
            
            SearchParam versionParams = new SearchParam(MONITOR_VERSION);
            versionParams.addCond_equals("motr_edm_id", temp.getString("id"));
            
            if (!StringUtil.isNullOrEmpty(endTime)) {
                versionParams.addCond_lessOrEquals("motr_beg", endTime);
                versionParams.addCond_greaterOrEquals("motr_end", endTime);
            }
            
            FullInputArgument version = new FullInputArgument(versionParams);
            //此处空表时会抛异常，需先维护索引
            try {
                if(persistance.count(version) == 0){
                    temp.put("count", 0);
                    monitorClasses.add(temp);
                    continue;
                }
            } catch (IndexNotFoundException e) {
                throw new ServiceException("为什么会报错？因为英文名叫”"+MONITOR_VERSION+"“的这个知识类是新建的，没有建索引！");
            }
            String[] edmNames = null;
            if(searchEdmName.endsWith("depttree"))
                edmNames = new String[]{searchEdmName,searchEdmName+SPE_HIS_SET};
            else
                edmNames = new String[]{searchEdmName,searchEdmName+MONITOR_HISTORY_SET};
            
            for(String edmName : edmNames){
                
                SearchParam requestParams = new SearchParam(edmName);
                requestParams.addCondition(new ConditionNode("moni_lvl_code", OperatorType.Equals, ROOT_LVL_CODE));
                requestParams.addCondition(new ConditionNode("moni_lvl", OperatorType.Equals, ROOT_LVL));
                
                if (!StringUtil.isNullOrEmpty(treeName)) 
                    requestParams.addCondition(new ConditionNode("moni_node_name", OperatorType.Like, treeName));

                // ORM暂不支持or查询，先只根据失效时间过滤
                if (!StringUtil.isNullOrEmpty(endTime)) {
                    requestParams.addCondition(new ConditionNode("moni_beg",OperatorType.LessEquals,endTime));
                    requestParams.addCondition(new ConditionNode("moni_end", OperatorType.GreaterEquals, endTime));
                }

                FullInputArgument input = new FullInputArgument(requestParams);

                //此处空表时会抛异常，需先维护索引
                long count = 0;
                try {
                    count = persistance.count(input);
                } catch (IndexNotFoundException e) {
                    throw new ServiceException("为什么会报错？因为英文名叫”"+searchEdmName+"的这个监管类新建的，没有建索引！");
                }
                temp.put("count", (temp.get("count") == null ? 0 : temp.getInteger("count")) + count);
            }
            
            monitorClasses.add(temp);
        }

        return monitorClasses;
    }

    @Override
    public JSONArray getNodeResources(String name, List<String> nodes, String edmId,String edmName, int type) throws DBException {

        JSONArray resources = new JSONArray();

        String edmcNameEn = "";
        
        if(!StringUtil.isNullOrEmpty(edmName)){
            edmcNameEn = edmName;
        }else{
            Result edmResult = edmClient.getEdmByid(edmId);
            
            if (edmResult.getRetCode() != Result.RECODE_SUCCESS) {
                logger.error("根据id查询EDM类信息失败！");
                throw new ServiceException("根据id查询EDM类信息失败！");
            }
            if (edmResult.getData() == null) {
                logger.error("未找到对应的EMD类信息！ID：{0}",edmId);
                throw new ServiceException("未找到对应的EMD类信息！ID："+ edmId);
            }
            
            JSONObject edmInfo = JSONObject.parseObject(JSON.toJSONString(edmResult.getData()));
            edmcNameEn = edmInfo.getString("edmcNameEn");
        }
        
        if (StringUtil.isNullOrEmpty(edmcNameEn)) {
            logger.error("EDM类信息中未找到类英文名！");
            throw new ServiceException("EDM类信息中未找到类英文名！");
        }
        
        //循环查找每个节点关联的资源对象ID（in 查询不支持 pid 字段，待优化）
        for (int i = 0; i < nodes.size(); i++) {
            SearchParam requestParams = null;
            if(type == 1){
                requestParams = new SearchParam(edmcNameEn + MONITOR_CLASS_PROP_RESOUTCES_OBJ);
                requestParams.addColumns(new String[]{"id","moni_res_id"});
            }else if(type == 2){
                requestParams = new SearchParam(edmcNameEn + MTOR_RES_SET);
                requestParams.addColumns(new String[]{"id","mtor_res_id"});
            }else
                new ServiceException("查询标志type不正确");
            
            requestParams
            .addCond_equals("pid", nodes.get(i));
            
            FullInputArgument input = new FullInputArgument(requestParams);
            
            JSONObject jsonObject = persistance.find(input);
            JSONArray reIds = jsonObject.getJSONArray("dataset");
            if(reIds == null || reIds.isEmpty())
                continue;
            
            List<String> ids = new ArrayList<String>();
            
            for(int j = 0; j < reIds.size(); j++){
                if(type == 1)
                    ids.add(reIds.getJSONObject(j).getString("moni_res_id"));
                else if(type == 2)
                    ids.add(reIds.getJSONObject(j).getString("mtor_res_id"));
            }
            JSONArray nodeRes = getResourceTxt(edmId, ids, null, nodes.get(i));
            
            if(nodeRes == null || nodeRes.isEmpty())
                continue;
            
            for(int k = 0; k < reIds.size(); k++){
                String res_id = "";
                if(type == 1)
                    res_id = reIds.getJSONObject(k).getString("moni_res_id");
                else if(type == 2)
                    res_id = reIds.getJSONObject(k).getString("mtor_res_id");
                
                for(int j = 0; j < nodeRes.size(); j++){
                    JSONObject nn = nodeRes.getJSONObject(j);
                    String r_id = nn.getString("id");
                    if(res_id.equals(r_id)){
                        JSONObject obj = (JSONObject)nn.clone();
                        obj.put("oid", reIds.getJSONObject(k).getString("id"));
                        resources.add(obj);
                        break;
                    }
                }
            }
        }
            
        return resources;
    }
	
	private JSONArray getResourceTxt(String edmId, List<String> ids, String name, String nodeId) throws DBException{
	    
	    if(ids == null || ids.isEmpty())
	        return null;
	    
	    JSONArray resources = new JSONArray();
	    
        // 根据资源ID查询资源，先找对应的资源表
        Result resourcesResult = edmClient.getPropertyValue(edmId, MONITOR_CLASS_PROP_RESOURCES);

        if (resourcesResult.getRetCode() != Result.RECODE_SUCCESS) {
            logger.error("根据监管类查询从属资源类失败！请检查Modeler服务！");
            throw new ServiceException("根据监管类查询从属资源类失败！请检查Modeler服务！");
        }
        
        if (resourcesResult.getData() == null) {
            logger.error("未找到资监管类的从属资源类型，请检查是否已设置！");
            throw new ServiceException("未找到资监管类的从属资源类型，请检查是否已设置！");
        }

        JSONObject resourceEdmObj = JSONObject.parseObject(JSON.toJSONString(resourcesResult.getData()));
        String dataType = resourceEdmObj.getString("dataType");
        
        if (!"class".equals(dataType)) {
            logger.error("资监管类的从属资源值不是EDM类，请检查！");
            throw new ServiceException("资监管类的从属资源值不是EDM类，请检查！");
        }
        
        JSONObject value = resourceEdmObj.getJSONObject("value");
        String resourcesEdmName = value.getString("edmcNameEn");

        Result result = edmClient.getCharacterAndFormat(value.getString("id"));
        
        if (result.getRetCode() != Result.RECODE_SUCCESS) {
            logger.error("modelerClient getCharacterAndFormat fail");
            throw new ServiceException("调用 Modeler 类特征值显示格式查询接口失败！");
        }
        
        JSONObject characterObj = (JSONObject) JSONObject.toJSON(result.getData());
        
        if(characterObj != null && characterObj.containsKey("character") && characterObj.containsKey("format")){
            JSONArray characterArray = characterObj.getJSONArray("character");
            String format = characterObj.getString("format");
            String[] resourceFields = new String[characterArray.size()];
            characterArray.toArray(resourceFields);
            
            //查询资源列表
            LoadParam loadParams = new LoadParam(resourcesEdmName);
            loadParams.addIDs(ids);
            FullInputArgument objinput = new FullInputArgument(loadParams);
            JSONObject objJsonObject = persistance.load(objinput);
            JSONArray resourcesObjs = objJsonObject.getJSONArray("dataset");

            //根据资源类特征值格式组装显示字段
            for (int j = 0; j < resourcesObjs.size(); j++) {
                JSONObject resourcesObj = resourcesObjs.getJSONObject(j);
                String edmObjName = format.toLowerCase();
                
                for (String fieldName : resourceFields) {
                    String f_str = StringUtil.isNullOrEmpty(resourcesObj.getString(fieldName))?"":resourcesObj.getString(fieldName);
                    edmObjName = edmObjName.replace(fieldName,f_str);
                }
                
                if(!StringUtil.isNullOrEmpty(nodeId))
                    resourcesObj.put("nodeId", nodeId);
                
                if(StringUtil.isNullOrEmpty(name) || edmObjName.contains(name))
                    resourcesObj.put("text",edmObjName);
                resources.add(resourcesObj);
            }
        }else{
            throw new ServiceException("未发现“"+value.getString("edmcName")+"”的显示特征值！请前往modeler设置！");
        }
	    return resources;
	}
	
	   @Override
	public JSONArray searchResourceObj(String resourceClassId, String resourceValue,int isFuzzyQuery) {
        // TODO Auto-generated method stub
        Result result = edmClient.getEdmByid(resourceClassId);
        Result edmResult = edmClient.getEdmByid(resourceClassId);
        if (edmResult.getRetCode() != Result.RECODE_SUCCESS) {
            logger.error("根据id查询EDM类信息失败！");
            throw new ServiceException("根据id查询EDM类信息失败！");
        }

        if (edmResult.getData() == null) {
            logger.error("未找到对应的EMD类信息！ID：{0}",resourceClassId);
            throw new ServiceException("未找到对应的EMD类信息！ID："+resourceClassId);
        }

        JSONObject edmInfo = JSONObject.parseObject(JSON.toJSONString(edmResult.getData()));
        String edmcNameEn = edmInfo.getString("edmcNameEn");

        if (StringUtil.isNullOrEmpty(edmcNameEn)) {
            logger.error("EDM类信息中未找到类英文名！");
            throw new ServiceException("EDM类信息中未找到类英文名！");
        }

        String resourceEdmName = edmcNameEn.toLowerCase().replace(" ","");
        String[] resourceFields = null;
        JSONArray resourceObjList = null;
        if (isFuzzyQuery == 1){
            result = edmClient.getCharacterAndFormat(edmInfo.getString("id"));
            if (result.getRetCode() != Result.RECODE_SUCCESS){
                logger.error("操作moderler:获取类的特征值及显示格式");
                throw new ServiceException("操作moderler:获取类的特征值及显示格式");
            }
            JSONObject characterObj = (JSONObject)JSONObject.toJSON(result.getData());
            JSONArray characterArray = characterObj.getJSONArray("character");
            String format = characterObj.getString("format");
            if (characterArray == null || characterArray.size() < 0){
                logger.error("未找到显示格式");
                throw new ServiceException("未找到显示格式");
            }
            resourceFields = new String[characterArray.size()];
            characterArray.toArray(resourceFields);
            Arrays.stream(resourceFields).map(field -> field.toLowerCase()).collect(Collectors.toList()).toArray(resourceFields);
            JSONArray resourceObjDetailList = selectEdmObjList(resourceEdmName, resourceFields, resourceValue);
            resourceObjList = new JSONArray();
            for (Object obj : resourceObjDetailList){
                JSONObject json=(JSONObject) JSONObject.toJSON(obj);
                String edmObjName = format.toLowerCase();
                JSONObject jsonObject = new JSONObject();
                jsonObject.put("id", json.getString("id"));
                for (String fieldName : resourceFields){
                    edmObjName = edmObjName.replace(fieldName, json.getString(fieldName));
                }
                jsonObject.put("name", edmObjName);
                resourceObjList.add(jsonObject);
            }
        }else{
            resourceObjList = selectEdmObjList(resourceEdmName, resourceFields, resourceValue);
        }
        return resourceObjList.size() > 0 ? resourceObjList : null;
    }

   public JSONArray selectEdmObjList(String edmName, String[] fields, String fieldValue) {
        //根据查询条件查询授权对象已授权的资源配置集合
        JSONArray resourceObjList = new JSONArray();
        SearchParam param = new SearchParam(edmName);
        try {
            if (fields == null || fields.length <= 0){//精确查找
                param.addCondition(new ConditionNode("id", OperatorType.Equals, fieldValue));
                FullInputArgument input = new FullInputArgument(param);
                JSONObject jsonObject;
                jsonObject = persistance.find(input);
                if (jsonObject != null){
                    resourceObjList.addAll(jsonObject.getJSONArray("dataset"));
                }
            }else{//模糊查找
                for (String fieldName : fields){
                    param.addCondition(new ConditionNode(fieldName, OperatorType.Like, fieldValue));
                     FullInputArgument input = new FullInputArgument(param);
                    JSONObject jsonObject = persistance.find(input);
                    if (jsonObject != null){
                        resourceObjList.addAll(jsonObject.getJSONArray("dataset"));
                    }
                }
            }
        }catch (Exception e) {
            // TODO: handle exception
            logger.error("操作ORM查询失败");
            throw new ServiceException("操作ORM查询失败");
        }
        return resourceObjList;
    }
}
