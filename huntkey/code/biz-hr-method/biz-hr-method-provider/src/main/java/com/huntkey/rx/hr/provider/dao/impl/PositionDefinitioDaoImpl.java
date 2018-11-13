package com.huntkey.rx.hr.provider.dao.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.edm.entity.OpdeOpdePostSetaEntity;
import com.huntkey.rx.edm.entity.PositiondefinitionEntity;
import com.huntkey.rx.edm.entity.PostdefinitionorderEntity;
import com.huntkey.rx.hr.common.constants.MsgConstants;
import com.huntkey.rx.hr.common.constants.PositionDefinitionConstant;
import com.huntkey.rx.hr.common.exception.ApplicationException;
import com.huntkey.rx.hr.common.model.*;
import com.huntkey.rx.hr.provider.dao.PositionDefinitionDao;
import com.huntkey.rx.hr.provider.service.BizFormService;
import com.huntkey.rx.sceo.orm.common.constant.EdmSysColumn;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by weijian on 2017/11/13.
 */
@Component
public class PositionDefinitioDaoImpl implements PositionDefinitionDao {
    @Autowired
    private OrmService ormService;
    @Autowired
    private BizFormService bizFormService;

    /**
     * 查询职位列表
     *
     * @param ormParam ORM查询参数
     * @return 返回结果集
     */
    @Override
    public JSONArray queryPositionList(OrmParam ormParam)
    {
        JSONArray jsonArray = new JSONArray();
        try
        {
            //调用ORM查询接口
            List<PositiondefinitionEntity> entityList = ormService.selectBeanList(PositiondefinitionEntity.class , ormParam);

            //将Entity转化为DTO,并放入JSONArray
            for(PositiondefinitionEntity entity : entityList)
            {
                JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(entity , SerializerFeature.WriteMapNullValue));
                jsonArray.add(jsonObject);
            }

        }
        catch (Exception e)
        {
            e.printStackTrace();
            //业务数据异常
            throw new ApplicationException(Result.RECODE_ERROR , MsgConstants.MSG_HR_POSTION_LIST_NOT_FOUND);
        }
        return jsonArray;
    }

    /**
     * 职位维护单加载
     *
     * @param rpofType
     * @return
     */
    @Override
    public List<PositionDefinitionDTO> load(String rpofType) throws Exception {
        OrmParam ormParam = new OrmParam();
        String whereExp = ormParam.getEqualXML(PositionDefinitionConstant.EDM_RPOF_TYPE, rpofType);
        ormParam.setWhereExp(whereExp).addOrderExpElement(SQLSortEnum.ASC,"rpof_grade");;
        List<PositionDefinitionDTO> datalist = new ArrayList<>();
        List<Map<String, Object>> list = ormService.selectMapList(PositiondefinitionEntity.class, ormParam);
        JSONArray jsonArray = new JSONArray();
        for (Map<String, Object> map : list) {
            JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
            jsonArray.add(jsonObject);
        }
        datalist = jsonArray.stream().map(obj -> JSONObject.toJavaObject((JSONObject) JSON.toJSON(obj), PositionDefinitionDTO.class)).collect(Collectors.toList());
        return datalist;
    }

    /**
     * 保存职位维护单
     *
     * @param postDefinitionOrderDTO
     * @return
     */
    @Override
    public Result save(PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception {
        Result result = new Result();
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        //保存表单数据
        //职位列表属性集
        List<opdePostSetDTO> list = postDefinitionOrderDTO.getOpdePostSet();
        postDefinitionOrderDTO.setOpdePostSet(null);

        //驼峰格式转下划线
        JSONObject insertObject = JSONObject.parseObject(JSON.toJSONString(postDefinitionOrderDTO));
        PostdefinitionorderEntity postdefinitionorderEntity = JSONObject.parseObject(JSONObject.toJSONString(insertObject), PostdefinitionorderEntity.class);
        postdefinitionorderEntity.setCretime(new Date());
        postdefinitionorderEntity.setCreuser(sessionEntity.getEmployeeId());
        //插入主表信息
        String orderId = (String) ormService.insertSelective(postdefinitionorderEntity);

        for (opdePostSetDTO opdePostSetDTO : list) {
            opdePostSetDTO.setPid(orderId);
            opdePostSetDTO.setClassname(PostDefinitionOrderConstants.EDM_NAME);
            //驼峰格式转下划线
            JSONObject param = JSONObject.parseObject(JSON.toJSONString(opdePostSetDTO));
            OpdeOpdePostSetaEntity opdeOpdePostSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(param), OpdeOpdePostSetaEntity.class);
            opdeOpdePostSetaEntity.setCretime(new Date());
            opdeOpdePostSetaEntity.setCreuser(sessionEntity.getEmployeeId());

            //插入结果集信息
            ormService.insertSelective(opdeOpdePostSetaEntity);
        }

        JSONObject dataObj = new JSONObject();
        dataObj.put(EdmSysColumn.ID , orderId);
        dataObj.put("ordeNbr", postDefinitionOrderDTO.getOrdeNbr());
        result.setData(dataObj);

        return result;
    }

    /**
     * 保存职位维护单
     *
     * @param postDefinitionOrderDTO
     * @return
     */
    @Override
    public Result update(PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception {
        Result result = new Result();
        //获取删除数据并做删除
        delOpdePostSet(postDefinitionOrderDTO);
        //获取新增数据并做新增
        List<opdePostSetDTO> addlist = new ArrayList<>();
        List<opdePostSetDTO> updatelist = new ArrayList<>();
        List<opdePostSetDTO> list = postDefinitionOrderDTO.getOpdePostSet();
        for (int i = 0; i < list.size(); i++) {
            opdePostSetDTO opdePostSetDTO = list.get(i);
            if (opdePostSetDTO.getId() == null) {
                opdePostSetDTO.setPid(postDefinitionOrderDTO.getId());
                opdePostSetDTO.setClassname(PositionDefinitionConstant.EDM_NAME);
                addlist.add(opdePostSetDTO);
            } else {
                updatelist.add(opdePostSetDTO);
            }
        }
        //主表数据更新
        postDefinitionOrderDTO.setOpdePostSet(null);
        //驼峰格式转下划线
        JSONObject updateObject = JSONObject.parseObject(JSON.toJSONString(postDefinitionOrderDTO));
        PostdefinitionorderEntity postdefinitionorderEntity = JSONObject.parseObject(JSONObject.toJSONString(updateObject), PostdefinitionorderEntity.class);
        ormService.updateSelective(postdefinitionorderEntity);
        //属性集数据更新
        for(opdePostSetDTO opdePostSetDTO: updatelist){
            //驼峰格式转下划线
            JSONObject param = JSONObject.parseObject(JSON.toJSONString(opdePostSetDTO));
            OpdeOpdePostSetaEntity opdeOpdePostSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(param), OpdeOpdePostSetaEntity.class);
            ormService.updateSelective(opdeOpdePostSetaEntity);
        }
        //插入新属性集
        addOpdePostSet(addlist);
        JSONObject dataObj = new JSONObject();
        dataObj.put(EdmSysColumn.ID , postDefinitionOrderDTO.getId());
        dataObj.put("ordeNbr" , postDefinitionOrderDTO.getOrdeNbr());
        result.setData(dataObj);
        return result;
    }

    /**
     * 判断结果集是否存在删除数据
     * @param postDefinitionOrderDTO
     * @throws Exception
     */
    private void delOpdePostSet(PostDefinitionOrderDTO postDefinitionOrderDTO) throws Exception {
        JSONArray jsonArray = new JSONArray();
        OrmParam ormParam = new OrmParam();
        ormParam.addColumn(EdmSysColumn.ID);
        ormParam.setWhereExp(ormParam.getEqualXML(EdmSysColumn.PID, postDefinitionOrderDTO.getId()));
        List<Map<String, Object>> forDelList = ormService.selectMapList(OpdeOpdePostSetaEntity.class, ormParam);
        for (Map<String, Object> map : forDelList) {
            JSONObject jsonObject = (JSONObject) JSON.toJSON(map);
            jsonArray.add(jsonObject);
        }
        List<opdePostSetDTO> list = postDefinitionOrderDTO.getOpdePostSet();
        JSONArray delIdArr = new JSONArray();
        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject jsonObject = (JSONObject) jsonArray.get(i);
            String id = (String) jsonObject.get(EdmSysColumn.ID);
            int num = 0;
            for (opdePostSetDTO opdePostSetDTO : list) {
                String str = opdePostSetDTO.getId();
                if (id.equals(str)) {
                    num++;
                }
            }
            if (num == 0) {
                delIdArr.add(jsonObject);
            }
        }
        if (delIdArr.size() > 0) {
            for (int i = 0; i < delIdArr.size(); i++) {
                JSONObject jsonObject = (JSONObject) delIdArr.get(i);
                ormService.delete(OpdeOpdePostSetaEntity.class, (String) jsonObject.get(EdmSysColumn.ID));
            }
        }
    }

    /**
     * 插入数据
     * @param addlist
     * @throws Exception
     */
    private void addOpdePostSet(List<opdePostSetDTO> addlist) throws Exception {
        CurrentSessionEntity sessionEntity = bizFormService.getCurrentSessionInfo();
        for (opdePostSetDTO opdePostSetDTO : addlist) {
            if (opdePostSetDTO.getId() == null) {
                JSONObject insertObj = (JSONObject) JSONObject.toJSON(opdePostSetDTO);
                OpdeOpdePostSetaEntity opdeOpdePostSetaEntity = JSONObject.parseObject(JSONObject.toJSONString(insertObj), OpdeOpdePostSetaEntity.class);
                opdeOpdePostSetaEntity.setCreuser(sessionEntity.getEmployeeId());
                opdeOpdePostSetaEntity.setCretime(new Date());
                ormService.insertSelective(opdeOpdePostSetaEntity);
            }
        }
    }

}
