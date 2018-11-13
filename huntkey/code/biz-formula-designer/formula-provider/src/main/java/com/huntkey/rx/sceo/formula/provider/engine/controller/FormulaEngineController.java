package com.huntkey.rx.sceo.formula.provider.engine.controller;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.constant.EdmDataType;
import com.huntkey.rx.sceo.formula.common.constant.FormulaCode;
import com.huntkey.rx.sceo.formula.common.model.PPIFormula;
import com.huntkey.rx.sceo.formula.common.model.TfdFormula;
import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import com.huntkey.rx.sceo.formula.common.model.vo.FormulaVo;
import com.huntkey.rx.sceo.formula.common.model.vo.ShowFormulaVo;
import com.huntkey.rx.sceo.formula.common.model.vo.SimplePPIParasVo;
import com.huntkey.rx.sceo.formula.common.model.vo.SimpleParasVo;
import com.huntkey.rx.sceo.formula.common.params.*;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.provider.engine.entity.SimpleDataProvider;
import com.huntkey.rx.sceo.formula.provider.engine.service.FormulaEngineService;
import com.huntkey.rx.sceo.formula.provider.variant.service.VariantMgrService;
import com.huntkey.rx.sceo.method.register.plugin.annotation.MethodRegister;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodExecType;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodStatus;
import com.huntkey.rx.sceo.method.register.plugin.entity.MethodType;
import com.huntkey.rx.sceo.method.register.plugin.entity.ProgramCate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhangyu
 * @create 2017-06-14 17:27
 **/
@RestController
@RequestMapping("/formula")
public class FormulaEngineController {

    private Logger logger = LoggerFactory.getLogger(FormulaEngineController.class);

    @Autowired
    private FormulaEngineService formulaEngineService;

    @Autowired
    private VariantMgrService variantMgrService;

    @RequestMapping(value = "/saveFormula", method = RequestMethod.POST)
    public Result saveFormula(@RequestBody TfdFormula formula) {
        Result result = new Result();
        try {
            String formulaId = formulaEngineService.saveFormula(formula);
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaId);
        } catch (Exception e) {
            String err = "保存公式错误！";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }

        return result;
    }

    @RequestMapping(value = "/updateFormula", method = RequestMethod.POST)
    public Result updateFormula(@RequestBody TfdFormula formula) {
        Result result = new Result();
        try {
            int count = formulaEngineService.updateFormula(formula);
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(count);
        } catch (Exception e) {
            String err = "更新公式错误！";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }

        return result;
    }

    @RequestMapping(value = "/calc/{formulaId}", method = RequestMethod.GET)
    public Result calc(@PathVariable String formulaId) {
        Result result = new Result();

        try {
            SimpleDataProvider provider = new SimpleDataProvider();
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaEngineService.calc(formulaId, provider));
        } catch (Exception e) {
            String err = "公式计算错误！";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }
        return result;
    }

    @RequestMapping(value = "/variantCalc/{variantId}", method = RequestMethod.POST)
    public Result variantCalc(@PathVariable String variantId, @RequestBody VariantParamPack paramPack) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        try {
            DataProvider provider = new SimpleDataProvider();
            provider.getDataContext().put("id", paramPack.getId());
            provider.getDataContext().put("edmName", paramPack.getEdmName());
            provider.getDataContext().putAll(extractVariantParamPack(paramPack.getItems()));
            TvmVariant variant = variantMgrService.getVariantById(variantId);
            result.setData(formulaEngineService.variantCalc(variant, provider));
        } catch (Exception e) {
            String err = "变量计算错误！";
            logger.error(err, e);
            // hack for MODELER.
            if (e instanceof NullPointerException) {
                result.setData(null);
            } else {
                throw new RuntimeException(err, e);
            }
        }
        return result;
    }

    /**
     * 对外提供的公式计算接口
     *
     * @param batchParam
     * @return
     */
    @RequestMapping(value = "/variantCalcBatch", method = RequestMethod.POST)
    public Result variantCalcBatch(@RequestBody VariantBatchParam batchParam) {
        Result result = new Result();
        result.setRetCode(Result.RECODE_SUCCESS);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        try {

            List<IdMapper> idMappers = batchParam.getIdMappers();
            if (null == idMappers) {
                throw new RuntimeException("id mappers can not be null.");
            }

            logger.info("id mappers: {}", idMappers);
            logger.info("dataMap: {}", batchParam.getDataMap());
            logger.info("edmName: {}", batchParam.getEdmName());
            for (IdMapper mapper : idMappers) {
                try {
                    logger.info("calculate variant: {}", mapper.getVariantId());
                    DataProvider provider = new SimpleDataProvider();
                    provider.getDataContext().putAll(batchParam.getDataMap());
                    provider.getDataContext().put("edmName", batchParam.getEdmName());
                    TvmVariant variant = variantMgrService.getVariantById(mapper.getVariantId());
                    Object value = formulaEngineService.variantCalc(variant, provider);
                    resultMap.put(mapper.getFieldId(), value);
                    logger.info("field: {}, value: {}", mapper.getVariantId(), value);
                } catch (Exception e) {
                    if (e instanceof NullPointerException) {
                        resultMap.put(mapper.getFieldId(), null);
                    } else {
                        throw new RuntimeException(e);
                    }
                }
            }
            result.setData(resultMap);
        } catch (Exception e) {
            String err = "变量计算错误！";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }
        return result;
    }

    private Map<String, Object> extractVariantParamPack(List<VariantParamItem> items) {
        Map<String, Object> dataMap = new HashMap<String, Object>();
        if (null == items) {
            return dataMap;
        }

        for (VariantParamItem vpi : items) {

            String dataKey = vpi.getDataKey();
            String dataValue = vpi.getDataValue();
            EdmDataType dataType = vpi.getDataType();
            Object obj = dataType.getTransable().transfer(dataValue);

            dataMap.put(dataKey, obj);
        }

        return dataMap;
    }

    private void validateDataKey(String dataKey) {

        // ABS(#prop_9bbc44e1e1394bfda7d217d9f7a24f0c_64e19108cdfc41a88d98d812184d1954_dashulei.562#
        // ABS(#prop_f5ce3b6fd4814b50ad2ad9e214f86d50#)
        // prop_classid_propid_classNameEn.propcode1.propCode2
        String[] arr = dataKey.split("_");
        if (arr.length != 4) {
            String errMsg = "The key must be this style: prop_classid_propid_classNameEn.propcode1[.propCode2]";
            logger.error(errMsg);
            throw new RuntimeException(errMsg);
        }

        if (!"prop".equals(arr[0])) {
            String errMsg = "the first word of the key must be prop.";
            logger.error(errMsg);
            throw new RuntimeException(errMsg);
        }

        if (!(arr[1].length() == 32 && arr[2].length() == 32)) {
            String errMsg = "the classid or propid of the key must be 32 bit uuid.";
            logger.error(errMsg);
            throw new RuntimeException(errMsg);
        }
    }

    @RequestMapping(value = "/getExpressionFromCache/{formulaId}", method = RequestMethod.GET)
    public Result getExpressionFromCache(@PathVariable String formulaId) {
        Result result = new Result();

        try {
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaEngineService.getExpressionFromCache(formulaId));
        } catch (Exception e) {
            String err = "从缓存中获取表达式错误！";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }

        return result;
    }

    @RequestMapping(value = "/formulaPreCalc", method = RequestMethod.POST)
    public Result formulaPreCalc(
            @RequestParam(value = "formulaContent") String formulaContent,
            @RequestParam Map<String, String> params) {

        Result result = new Result();
        try {
            SimpleDataProvider provider = new SimpleDataProvider();
            provider.getDataContext().putAll(params);
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaEngineService.formulaPreCalc(formulaContent, provider));
        } catch (Exception e) {
            String err = "公式预计算错误！";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }
        return result;
    }

    /**
     * 公式校验
     *
     * @param paramPack
     * @return
     */
    @RequestMapping(value = "/validate", method = RequestMethod.POST)
    public Result validate(@RequestBody FormulaParamPack paramPack) {
        Result result = new Result();
        try {
            SimpleDataProvider provider = new SimpleDataProvider();
            // extract the params.

            List<FormulaItemValue> formulaItemsValues = paramPack.getFormulaItemsValues();
            //判断前台传的数据，如果是日期输入框，则把日期2017-10-10 10:35:33转化为DATE（2017,10,10 10:35:33）并把dateValType设置为"text"
            if (formulaItemsValues.size() > 0) {
                for (FormulaItemValue formulaItemsValue : formulaItemsValues) {
                    EdmDataType dataValType = formulaItemsValue.getDataValType();
                    if ("DATE".equals(dataValType.toString())) {
                        formulaItemsValue.setDataValType("text");
                        String dataVal = formulaItemsValue.getDataVal();
                        String s = valueOf(dataVal);
                        formulaItemsValue.setDataVal(s);
                    }
                }
                provider.getDataContext().putAll(extractItems2Map(paramPack.getFormulaItemsValues()));
                result.setRetCode(Result.RECODE_SUCCESS);
                Object obj = formulaEngineService.formulaPreCalc(paramPack.getFormulaStr(), provider);
                result.setData(obj.toString());

            } else
            //如果前台传的格式为输入框输入，则按原方法进行
            {
                provider.getDataContext().putAll(extractItems2Map(paramPack.getFormulaItemsValues()));
                result.setRetCode(Result.RECODE_SUCCESS);
                Object obj = formulaEngineService.formulaPreCalc(paramPack.getFormulaStr(), provider);
                result.setData(obj.toString());
            }
        } catch (Exception e) {
            String err = "Formula pre-calc error！" + "[" + e.getMessage() + "]";
            logger.error(err, e);
            result.setRetCode(FormulaCode.FORMULA_PRE_CALC_ERR.getStateCode());
            result.setData(err);
        }
        return result;
    }

    private int getScale(List<FormulaItemValue> itemValues) {
        int scale = 0;
        int maxScale = 0;
        for (FormulaItemValue fiv : itemValues) {
            String varType = fiv.getVarType();

            if (!"prop".equals(varType)) {
                continue;
            }

            String dataVal = fiv.getDataVal();
            if (dataVal.contains(".")) {
                int index = dataVal.indexOf(".");
                scale = dataVal.length() - index - 1;
                if (scale > maxScale) {
                    maxScale = scale;
                }
            }
        }
        return maxScale;
    }

    private Map<String, Object> extractItems2Map(List<FormulaItemValue> itemValues) {
        Map<String, Object> paramsMap = new HashMap<String, Object>();

        for (FormulaItemValue fiv : itemValues) {
            String varType = fiv.getVarType();

            if (!("prop".equals(varType) || "var".equals(varType))) {
                continue;
            }

            String varVal = fiv.getVarVal();
            String dataVal = fiv.getDataVal();
            EdmDataType dataType = fiv.getDataValType();
            Object obj = dataType.getTransable().transfer(dataVal);
            logger.info(obj.toString());
            paramsMap.put("prop".equals(varType) ? varType + "_" + varVal : varVal, obj);
        }

        return paramsMap;
    }

    @RequestMapping(value = "/getFormulaElement/{varid}/{userid}", method = RequestMethod.GET)
    public Result getFormulaElement(@PathVariable("varid") String varid, @PathVariable("userid") String userid, @RequestParam(value = "classId") String classId) {
        Result result = null;
        try {
            result = formulaEngineService.getFormulaElement(varid, userid, classId);
        } catch (Exception e) {
            logger.error("根据变量编号获取相关类、局部变量、计算公式等错误:", e);
            throw new RuntimeException("根据变量编号获取相关类、局部变量、计算公式等错误:", e);
        }
        return result;
    }

    @RequestMapping(value = "/getFormulaFunction/{userid}", method = RequestMethod.GET)
    public Result getFormulaFunction( @PathVariable("userid") String userid) {
        Result result = null;
        try {
            result = formulaEngineService.getFormulaFunction(userid);
        } catch (Exception e) {
            logger.error("获取计算公式错误:", e);
            throw new RuntimeException("获取计算公式等错误:", e);
        }
        return result;
    }

    @RequestMapping(value = "/initFormula", method = RequestMethod.POST)
    public Result initFormula(@RequestBody SimpleParasVo params) {
        Result result = new Result();
        try {

            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaEngineService.initFormula(params));
        } catch (Exception e) {
            String err = "初始化公式变量id,公式id错误！";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }
        return result;
    }

    @RequestMapping(value = "/initPPIFormula", method = RequestMethod.POST)
    public Result initPPIFormula(@RequestBody SimplePPIParasVo params) {
        Result result = new Result();
        try {

            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaEngineService.initPPIFormula(params));
        } catch (Exception e) {
            String err = "初始化PPI公式变量id,公式id错误！";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }
        return result;
    }

    /**
     * @des 把日期2017-10-10 10:33:35 转成 DATE(2017，10，10，10，33，35)
     */
    private static String valueOf(String s) {
        final int yearLength = 4;
        final int monthLength = 2;
        final int dayLength = 2;
        final int maxMonth = 12;
        final int maxDay = 31;
        String dateS;
        String timeS;
        String nanosS;
        int year = 0;
        int month = 0;
        int day = 0;
        int hour;
        int minute;
        int second;
        int aNanos = 0;
        int firstDash;
        int secondDash;
        int dividingSpace;
        int firstColon = 0;
        int secondColon = 0;
        int period = 0;
        String formatError = "Timestamp format must be yyyy-mm-dd hh:mm:ss[.fffffffff]";
        String zeros = "000000000";
        String delimiterDate = "-";
        String delimiterTime = ":";

        if (s == null) {
            throw new java.lang.IllegalArgumentException("null string");
        }

        // Split the string into date and time components
        s = s.trim();
        dividingSpace = s.indexOf(' ');
        if (dividingSpace > 0) {
            dateS = s.substring(0, dividingSpace);
            timeS = s.substring(dividingSpace + 1);
        } else {
            throw new java.lang.IllegalArgumentException(formatError);
        }

        // Parse the date
        firstDash = dateS.indexOf('-');
        secondDash = dateS.indexOf('-', firstDash + 1);

        // Parse the time
        if (timeS == null) {
            throw new java.lang.IllegalArgumentException(formatError);
        }
        firstColon = timeS.indexOf(':');
        secondColon = timeS.indexOf(':', firstColon + 1);
        period = timeS.indexOf('.', secondColon + 1);

        // Convert the date
        boolean parsedDate = false;
        if ((firstDash > 0) && (secondDash > 0) && (secondDash < dateS.length() - 1)) {
            String yyyy = dateS.substring(0, firstDash);
            String mm = dateS.substring(firstDash + 1, secondDash);
            String dd = dateS.substring(secondDash + 1);
            if (yyyy.length() == yearLength &&
                    (mm.length() >= 1 && mm.length() <= monthLength) &&
                    (dd.length() >= 1 && dd.length() <= dayLength)) {
                year = Integer.parseInt(yyyy);
                month = Integer.parseInt(mm);
                day = Integer.parseInt(dd);

                if ((month >= 1 && month <= maxMonth) && (day >= 1 && day <= maxDay)) {
                    parsedDate = true;
                }
            }
        }
        if (!parsedDate) {
            throw new java.lang.IllegalArgumentException(formatError);
        }

        // Convert the time; default missing nanos
        if ((firstColon > 0) & (secondColon > 0) &
                (secondColon < timeS.length() - 1)) {
            hour = Integer.parseInt(timeS.substring(0, firstColon));
            minute =
                    Integer.parseInt(timeS.substring(firstColon + 1, secondColon));
            if ((period > 0) & (period < timeS.length() - 1)) {
                second =
                        Integer.parseInt(timeS.substring(secondColon + 1, period));
                nanosS = timeS.substring(period + 1);
                if (nanosS.length() > 9) {
                    throw new java.lang.IllegalArgumentException(formatError);
                }
                if (!Character.isDigit(nanosS.charAt(0))) {
                    throw new java.lang.IllegalArgumentException(formatError);
                }
                nanosS = nanosS + zeros.substring(0, 9 - nanosS.length());
                aNanos = Integer.parseInt(nanosS);
            } else if (period > 0) {
                throw new java.lang.IllegalArgumentException(formatError);
            } else {
                second = Integer.parseInt(timeS.substring(secondColon + 1));
            }
        } else {
            throw new java.lang.IllegalArgumentException(formatError);
        }
        String date = "DATE(" + (year) + "," + (month) + "," + day + "," + hour + "," + minute + "," + second + ")";
        return date;
    }

    @RequestMapping(value = "/savePPIFormula", method = RequestMethod.POST)
    public Result savePPIFormula(@RequestBody PPIFormula formula) {
        Result result = new Result();
        try {
            String formulaId = formulaEngineService.savePPIFormula(formula);
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaId);
        } catch (Exception e) {
            String err = "保存公式错误！";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }

        return result;
    }

    @RequestMapping(value = "/updatePPIFormula", method = RequestMethod.POST)
    public Result updatePPIFormula(@RequestBody PPIFormula formula) {
        Result result = new Result();
        try {
            int count = formulaEngineService.updatePPIFormula(formula);
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(count);
        } catch (Exception e) {
            String err = "更新公式错误！";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }

        return result;
    }
    @MethodRegister(
            edmClass = "formula",
            methodCate = "公式",
            programCate = ProgramCate.Java,
            methodType = MethodType.GeneralMethod,
            methodExecType = MethodExecType.SyncMethod,
            methodStatus = MethodStatus.Enable,
            methodDesc = "PPI公式"
    )
    @RequestMapping(value = "/calcPPI", method = RequestMethod.POST)
    public Result calcPPI(@RequestBody FormulaVo formulaVo) {
        Result result = new Result();
        String formulaContent = formulaVo.getFrmuContent();
        try {
            SimpleDataProvider provider = new SimpleDataProvider(formulaVo.getDataContext());

            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaEngineService.calcPPI(formulaContent, provider));
        } catch (Exception e) {
            String err = "公式计算错误!";
            logger.error(err, e);
            throw new RuntimeException(err, e);
        }
        return result;
    }

    @RequestMapping(value = "/calcShow",method = RequestMethod.POST)
    public Result calcShow(@RequestBody ShowFormulaVo showFormulaVo){
        Result result = new Result();
        try{
            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaEngineService.calcShow(showFormulaVo));
        }catch(Exception e){
            String err = "对象呈现公式计算错误!";
            logger.error(err,e);
            throw new RuntimeException(err,e);
        }
        return  result;
    }
}