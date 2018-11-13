package com.huntkey.rx.sceo.serviceCenter.provider.biz.ppi.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.biz.index.ppi.constant.CommonConstants;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.common.model.MergeParam;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import com.huntkey.rx.sceo.serviceCenter.common.model.SearchParam;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.ppi.service.IndexDefineOrderService;
import com.huntkey.rx.sceo.serviceCenter.provider.service.Persistance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IndexDefineOrderServiceImpl implements IndexDefineOrderService {

    @Autowired
    private Persistance persistance;

    @Override
    public Result formulaOrderPass(String formulaOrderId) throws Exception {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);
        //根据子流程单据ID查询子流程信息
        SearchParam formulaOrderSearch = new SearchParam(CommonConstants.EDM_OPFM_CHI_SET);
        formulaOrderSearch.addCond_equals(NodeConstant.ID, formulaOrderId);
        JSONArray formulaOrderArray = persistance.find(formulaOrderSearch.build()).getJSONArray(NodeConstant.DATASET);
        if (formulaOrderArray.size() <= 0) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("找不到公式设置子流程的单据信息！");
            return result;
        }
        JSONObject formulaOrder = formulaOrderArray.getJSONObject(0);
        String opfmId = formulaOrder.getString(NodeConstant.PID);

        //根据单据ID查询对应指标定义的ID
        SearchParam defineSearch = new SearchParam(CommonConstants.EDM_PPIFORMULA);
        defineSearch.addCond_equals("rpif_opfm_id", opfmId);
        JSONArray defines = persistance.find(defineSearch.build()).getJSONArray(NodeConstant.DATASET);
        if (defines.size() <= 0) {
            result.setRetCode(Result.RECODE_ERROR);
            result.setErrMsg("无法找到子流程单据对应的指标定义对象！");
            return result;
        }
        JSONObject define = defines.getJSONObject(0);
        String indexDefineId = define.getString(NodeConstant.ID);

        //转存 单据变量集合 到 定义变量集合,先删后加
        clearVarSetByPID(CommonConstants.EDM_RPIF_CAL_SET, indexDefineId);
        saveDefineVariablesByOrderId(formulaOrderId, indexDefineId);

        // 根据单据ID查询维护单关联的统计周期集
        SearchParam periodSearchParam = new SearchParam(CommonConstants.EDM_OPFM_TIME_SETA);
        periodSearchParam.addCond_equals(NodeConstant.PID, opfmId);
        JSONArray defineOrderPeriods = persistance.find(periodSearchParam.build()).getJSONArray(NodeConstant.DATASET);

        //删除旧的统计周期集合
        clearVarSetByPID(CommonConstants.EDM_FORMULA_PERIOD, indexDefineId);

        //定义新的指标定义统计周期集合
        JSONArray definePeriods = new JSONArray();
        for (int i = 0; i < defineOrderPeriods.size(); i++) {
            JSONObject orderPeriod = defineOrderPeriods.getJSONObject(i);
            JSONObject definePeriod = new JSONObject();
            definePeriod.put("rpif_time_name", orderPeriod.getString("opfm_time_name"));
            definePeriod.put("rpif_cal_id", orderPeriod.getString("opfm_cal_id"));
            definePeriod.put("rpif_cal_fmul", orderPeriod.getString("opfm_cal_fmul"));
            definePeriod.put(NodeConstant.PID, indexDefineId);
            definePeriods.add(definePeriod);
        }

        //根据单据中的周期更新定义中的周期
        MergeParam updateDefinePeriodParams = new MergeParam(CommonConstants.EDM_FORMULA_PERIOD);
        updateDefinePeriodParams.addAllData(definePeriods);
        persistance.save(updateDefinePeriodParams.build());

        return result;
    }

    /**
     * 将制定单据的变量集合转存到制定指标定义类的变量集合表中
     *
     * @param formulaOrderId 子流程单据ID
     * @param indexDefineId  指标定义ID
     * @throws Exception
     */
    private void saveDefineVariablesByOrderId(String formulaOrderId, String indexDefineId) throws Exception {
        // 根据单据ID查询单据关联的公式变量集合
        SearchParam calSearchParam = new SearchParam(CommonConstants.EDM_OPFM_CAL_SET);
        calSearchParam.addCond_equals(NodeConstant.PID, formulaOrderId);
        JSONArray defineOrderVariables = persistance.find(calSearchParam.build()).getJSONArray(NodeConstant.DATASET);

        //遍历变量，根据变量查询变量关联的资源属性集合
        //定义新的指标定义的变量集合
        JSONArray defineVariavles = new JSONArray();
        for (int i = 0; i < defineOrderVariables.size(); i++) {
            JSONObject orderVariable = defineOrderVariables.getJSONObject(i);
            String variavleId = orderVariable.getString(NodeConstant.ID);

            // 根据变量ID查询变量关联的资源类属性集合
            SearchParam varResSearchParam = new SearchParam(CommonConstants.EDM_OPFM_OPTI_SET);
            varResSearchParam.addCond_equals(NodeConstant.PID, variavleId);
            JSONArray resPropArray = persistance.find(varResSearchParam.build()).getJSONArray(NodeConstant.DATASET);

            //将单据的公式变量集合转换成指标定义的公式变量集合后保存
            //定义新的指标定义变量关联资源属性集合
            JSONArray defineResPropArray = new JSONArray();
            for (int j = 0; j < resPropArray.size(); j++) {
                JSONObject resProp = resPropArray.getJSONObject(j);
                JSONObject defineResProp = new JSONObject();
                defineResProp.put("rpif_resources_class", resProp.getString("opfm_res_class"));
                defineResProp.put("rpif_resources_prop", resProp.getString("opfm_prop"));
                defineResPropArray.add(defineResProp);
            }

            //定义新的指标定义的变量对象
            JSONObject defineVariable = new JSONObject();
            defineVariable.put("rpif_var_name", orderVariable.getString("opfm_name_var"));
            defineVariable.put("rpif_val", orderVariable.getString("opfm_val"));
            defineVariable.put(CommonConstants.RPIF_RESOURCES_PROPS_SET, defineResPropArray);
            defineVariable.put(NodeConstant.PID, indexDefineId);

            defineVariavles.add(defineVariable);
        }

        //保存变量属性集以及变量对应的资源类字段集合
        MergeParam mergeParam = new MergeParam(CommonConstants.EDM_RPIF_CAL_SET);
        mergeParam.addAllData(defineVariavles);
        persistance.save(mergeParam.build());

    }

    private void clearVarSetByPID(String edmName, String pid) throws Exception {
        //查询
        SearchParam searchParam = new SearchParam(edmName);
        searchParam.addCond_equals(NodeConstant.PID, pid);
        searchParam.addColumns(NodeConstant.ID);
        JSONArray ids = persistance.find(searchParam.build()).getJSONArray(NodeConstant.DATASET);
        //删除
        MergeParam mergeParam = new MergeParam(edmName);
        mergeParam.addAllData(ids);
        persistance.remove(mergeParam.build());
        // TODO 多层属性集只删除一层时，会产生垃圾数据，待与ORM沟通，是不是有必要提供级联删除功能
    }
}
