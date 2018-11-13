package com.huntkey.rx.sceo.formula.provider.engine.service.impl;

import com.alibaba.fastjson.JSON;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.sceo.formula.common.base.BaseService;
import com.huntkey.rx.sceo.formula.common.function.FunctionDescriber;
import com.huntkey.rx.sceo.formula.common.model.*;
import com.huntkey.rx.sceo.formula.common.model.vo.*;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.common.util.Scope;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.Parser;
import com.huntkey.rx.sceo.formula.engine.circle.check.CircleChecker;
import com.huntkey.rx.sceo.formula.engine.circle.check.CircleMapResult;
import com.huntkey.rx.sceo.formula.engine.circle.check.FormulaNode;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.engine.expression.Expression;
import com.huntkey.rx.sceo.formula.provider.audit.feign.EdmInterface;
import com.huntkey.rx.sceo.formula.provider.behavior.service.BehaivorTrackerService;
import com.huntkey.rx.sceo.formula.provider.data.service.DataGraperService;
import com.huntkey.rx.sceo.formula.provider.engine.dao.TfdFormulaMapper;
import com.huntkey.rx.sceo.formula.provider.engine.entity.SimpleDataProvider;
import com.huntkey.rx.sceo.formula.provider.engine.feign.BizModelerService;
import com.huntkey.rx.sceo.formula.provider.engine.feign.DataSharingService;
import com.huntkey.rx.sceo.formula.provider.engine.service.FormulaEngineService;
import com.huntkey.rx.sceo.formula.provider.engine.service.FunctionDescriberService;
import com.huntkey.rx.sceo.formula.provider.property.service.PropertyLimitSettingService;
import com.huntkey.rx.sceo.formula.provider.record.service.RecordMappingService;
import com.huntkey.rx.sceo.formula.provider.redis.service.IRedisService;
import com.huntkey.rx.sceo.formula.provider.related.classes.service.RelatedClassSettingService;
import com.huntkey.rx.sceo.formula.provider.variant.service.VariantMgrService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.concurrent.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 公式引擎服务接口实现类
 *
 * @author chenfei on 2017/6/16.
 */
@Service
@Transactional(readOnly = true, rollbackFor = RuntimeException.class)
public class FormulaEngineServiceImpl extends BaseService<TfdFormula> implements FormulaEngineService {

    private Logger logger = LoggerFactory.getLogger(FormulaEngineServiceImpl.class);

    @Autowired
    private TfdFormulaMapper tfdFormulaMapper;

    @Autowired
    private PropertyLimitSettingService propertyLimitSettingService;

    @Autowired
    private RedisTemplate<String, Expression> expressTemplate;

    @Autowired
    private BehaivorTrackerService behaivorTrackerService;

    @Autowired
    private RelatedClassSettingService relatedClassSettingService;

    @Autowired
    private VariantMgrService variantMgrService;

    @Autowired
    private FunctionDescriberService functionDescriberService;

    @Autowired
    private IRedisService redisService;

    @Autowired
    private BizModelerService bizModelerService;

    @Autowired
    private Parser parser;

    @Autowired
    private RecordMappingService recordMappingService;



    private static final int COUNT_THREADPOOL_SIZE = 6;

    private static ExecutorService executor;

    @Autowired
    private DataGraperService dataGraperService;

    @Autowired
    private DataSharingService dataSharingService;

    static {
        executor = Executors.newFixedThreadPool(COUNT_THREADPOOL_SIZE);
    }

    /**
     * 模式定义
     */
    private Pattern pa = Pattern.compile("\\#.*?\\#");

    public FormulaEngineServiceImpl() {
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public String saveFormula(TfdFormula formula) {

        // FIXME mock some not null fields.
        formula.setAcctid(0);
        formula.setAdduser("MR. Mock");
        formula.setAddtime(new Date());
        formula.setModtime(new Date());
        formula.setModuser("MR. Mock");
        formula.setIsenable((byte) 1);

        try {
            this.preSave(formula);
            tfdFormulaMapper.insert(formula);
            return formula.getFrmuId();
        } catch (Exception e) {
            String errMsg = "新增公式错误！";
            logger.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updateFormula(TfdFormula formula) {

        formula.setAcctid(0);
        formula.setAdduser("MR. Mock");
        formula.setAddtime(new Date());
        formula.setModtime(new Date());
        formula.setModuser("MR. Mock");
        formula.setIsenable((byte) 1);

        try {
            return tfdFormulaMapper.updateByPrimaryKey(formula);
        } catch (Exception e) {
            String errMsg = "更新公式错误！";
            logger.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }
    }

    /**
     *
     * @param formulaId
     * @param provider
     * @return
     */
    @Override
    public Object calc(String formulaId, DataProvider provider) {

        try {

            if (null == provider) {
                provider = new SimpleDataProvider();
            }

            TfdFormula formula = tfdFormulaMapper.selectByPrimaryKey(formulaId);
            return this.calcRecurse(formulaId, formula.getFrmuFormulaContent(), provider);
        } catch (FormulaException e) {
            String errMsg = "Some error occured when calc formula.";
            logger.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }

    }

    @Override
    public List<TfdFormula> getFormulasByids(List<String> formulas){

        TfdFormulaExample example = new TfdFormulaExample();
        TfdFormulaExample.Criteria criteria = example.createCriteria();
        criteria.andFrmuIdIn(formulas);
        return tfdFormulaMapper.selectByExample(example);
    }

    @Override
    public Object variantCalc(String variantId, DataProvider provider) {

        if (null == provider) {
            provider = new SimpleDataProvider();
        }

        TvmVariant variant = variantMgrService.getVariantById(variantId);
        checkVariantCircle(variant);

        dataGraperService.grapData(variant, provider);
        logger.info("data context: {}", provider.getDataContext());

        return this.calc(variant.getVrntFormulaId(), provider);
    }

    @Override
    public Object variantCalc(TvmVariant variant, DataProvider provider) {

        if (null == provider) {
            provider = new SimpleDataProvider();
        }

        checkVariantCircle(variant);
        logger.info("data context: {}", provider.getDataContext());
        dataGraperService.grapData(variant, provider);

        return this.calc(variant.getVrntFormulaId(), provider);
    }

    /**
     * check the variant which maybe dependence other variant
     * so, the circle would be happened.
     *
     * @param variant
     */
    private void checkVariantCircle(TvmVariant variant) {

        String formulaId = variant.getVrntFormulaId();
        String varName = variant.getVrntVarName();

        CircleChecker checker = new CircleChecker();
        FormulaNode rootNode = new FormulaNode(varName);
        rootNode.setNodeName(varName);
        TfdFormula formula = tfdFormulaMapper.selectByPrimaryKey(formulaId);

        if (null == formula) {
            return;
        }

        checkRecurse(formula.getFrmuFormulaContent(), rootNode, checker);
    }


    /**
     * check the formula recursively.
     *
     * @param formulaContent
     * @param rootNode
     * @param checker
     */
    private void checkRecurse(String formulaContent, FormulaNode rootNode, CircleChecker checker) {

        CircleMapResult result = checker.checkCircle(rootNode);

        if (result.isHasCircle()) {
            throw new RuntimeException("This variant has been referenced some other variant duplicatedly. " + result.toString());
        }

        Matcher mc = pa.matcher(formulaContent);

        while(mc.find()) {
            String foobar = mc.group().replace("#", "");
            if (foobar.startsWith("var_")) {
                String varName = foobar.replace("var_", "");
                TvmVariant variant = variantMgrService.getVariantByName(varName);

                FormulaNode subNode = new FormulaNode(varName);
                rootNode.setNodeName(variant.getVrntVarName());
                rootNode.getRefList().add(subNode);

                String subFormulaId = variant.getFormulaId();
                TfdFormula formula = tfdFormulaMapper.selectByPrimaryKey(subFormulaId);

                String subFormulaContent = formula.getFrmuFormulaContent();
                checkRecurse(subFormulaContent, subNode, checker);
            }
        }
    }

    /**
     * calculate the formula recursively.
     *
     * @param formulaId
     * @param formulaContent
     * @param dataProvider
     * @return
     */
    private Object calcRecurse(String formulaId, String formulaContent, DataProvider dataProvider) {

        Matcher mc = pa.matcher(formulaContent);
        while(mc.find()) {
            String foobar = mc.group().replace("#", "");
            if (foobar.startsWith("var_")) {

                String varName = foobar.replace("var_", "");
                // grap the variant from DB, then parse and calc.
                // get vaiant by name.
                TvmVariant variant = variantMgrService.getVariantByName(varName);
                // get formula id, than invoke the calc interface.
                String subFormulaId = variant.getVrntFormulaId();
                TfdFormula formula = tfdFormulaMapper.selectByPrimaryKey(subFormulaId);

                String subFormulaContent = formula.getFrmuFormulaContent();
                // return the value of result.
                Object obj = calcRecurse(subFormulaId, subFormulaContent, dataProvider);
                // maybe recurse.
                if(!StringUtil.isNullOrEmpty(obj)) {
                    dataProvider.getDataContext().put(foobar, obj);
                }else{
                    dataProvider.getDataContext().put(foobar,null);
                }
            }
        }

        logger.info("formulaId: {} , content: {}", formulaId, formulaContent);
        // get expression from cache.
        Expression expr = parser.parse(formulaContent.replace("#", ""));
//        Expression expr = (Expression) getExpressionFromCache(formulaId);
//
//        if (null == expr) {
//
//            logger.info("formulaContent: {}", formulaContent.replace("#", ""));
//            logger.info("dataMap: {}", dataProvider.getDataContext());
//            expr = parser.parse(formulaContent.replace("#", ""));
//            putExpression2Cache(formulaId, expr);
//        }

        logger.info("expr: {}", expr.toString());
        if(expr.getValue(dataProvider)==null){
            return null;
        }else {
            return expr.getValue(dataProvider).getOriginValue();
        }
    }

    /**
     * calculate the formula recursively for formula pre-calc.
     *
     * @param formulaContent
     * @param dataProvider
     * @return
     */
    private Object calcRecurse4Pre(String formulaContent, DataProvider dataProvider) {

        Matcher mc = pa.matcher(formulaContent);
        while(mc.find()) {
            String foobar = mc.group().replace("#", "");
            if (foobar.startsWith("var_")) {

                String varName = foobar.replace("var_", "");
                // grap the variant from DB, then parse and calc.
                // get vaiant by name.
                TvmVariant variant = variantMgrService.getVariantByName(varName);
                // get formula id, than invoke the calc interface.
                String subFormulaId = variant.getVrntFormulaId();
                TfdFormula formula = tfdFormulaMapper.selectByPrimaryKey(subFormulaId);

                String subFormulaContent = formula.getFrmuFormulaContent();
                // return the value of result.
                //递归调用
                Object obj = calcRecurse4Pre(subFormulaContent, dataProvider);
                // maybe recurse.
                dataProvider.getDataContext().put(foobar, obj);
            }
        }

        Expression expr = parser.parse(formulaContent.replace("#", ""));
        return expr.getValue(dataProvider).getValue();
    }

    @Override
    public Expression getExpressionFromCache(String formulaId) {

        try {
            Expression expr = (Expression) expressTemplate.opsForHash().get(formulaId, formulaId);

            return expr;
        } catch (Exception e) {
            String errMsg = "Some error occured when get expr from cache.";
            logger.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }

    }

    @Override
    public void putExpression2Cache(String formulaId, Expression expr) {

        try {
            if (null == formulaId || null == expr) {
                throw new RuntimeException("The args can not be null.");
            }

            expressTemplate.opsForHash().put(formulaId, formulaId, expr);
            expressTemplate.expire(formulaId, 1000, TimeUnit.SECONDS);
        } catch (Exception e) {
            String errMsg = "Some error occured when put expr to cache.";
            logger.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }
    }

    @Override
    public boolean functionCompile(String functionContent) {
        return false;
    }

    @Override
    public Object formulaPreCalc(String formulaContent, DataProvider provider) {

        if (null == provider) {
            provider = new SimpleDataProvider();
        }

        logger.info("formulaContent: {}", formulaContent);
        logger.info("data: {}", provider.getDataContext());

        // substitute all keys that have values in data context.
        try {

            formulaContent = substitute(formulaContent, provider);
            logger.info("formulaContent-substitute: {}", formulaContent);

            Object obj = calcRecurse4Pre(formulaContent, provider);
            logger.info("pre-calc result: {}", obj.toString());
            return obj;
        } catch (Exception e) {
            String errMsg = "Some error occured when execute pre calc. [" + e.getMessage() + "]";
            logger.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }
    }

    private String substitute(String formulaContent, DataProvider provider) {

        Map<String, Object> dataContext = provider.getDataContext();

        Matcher mc = pa.matcher(formulaContent);
        while(mc.find()) {
            //去除公式中的"#"
            String foobar = mc.group().replace("#", "");
            logger.info("foobar: {}", foobar);

            Object value = dataContext.get(foobar);
            if (foobar.startsWith("var_")) {
                value = dataContext.get(foobar.replace("var_", ""));
            }

            if (null != value) {
                formulaContent = formulaContent.replace("#" + foobar + "#", value.toString());
            }
        }

        return formulaContent;
    }

    /**
     * calculate property limit value.
     * FIXME related condtions && trigger conditions
     *
     * @param propLimitId
     * @param provider
     * @return
     */
    @Override
    public Boolean propLimitFormulaCalc(String propLimitId, DataProvider provider) {

        if (null == provider) {
            provider = new SimpleDataProvider();
        }

        /**
         * pseudo-code
         *
         * 1. query property limit object.
         * 2. query all conditions.
         * 3. iterate all conditions.
         * 4. calculate every condition and put the result into a map
         * 5. parse the condition formula.
         * 6. calculate the condition formula.
         * 7. get the result of condition formula
         *
         */
        Map<Integer, Boolean> condCache = new HashMap<Integer, Boolean>();
        TplPropertyLimit propLimit = propertyLimitSettingService.getPropLimitById(propLimitId);
        List<TplCondition> conditions = propertyLimitSettingService.queryAllConditions(propLimitId);

        for (TplCondition cond : conditions) {
            String prop = cond.getCndtPropOriginCode();
            String op = cond.getCndtOperate();
            String value = cond.getCndtValueOriginCode();
            String valueType = cond.getCndtValueType();
            Integer seq = cond.getCndtSeq();
            // constant, variant
            // wrap the expression.
            StringBuffer buff = new StringBuffer();
            if ("constant".contains(valueType)) {
                buff.append("(" + prop + " " + op + " " + value + ")");
            } else if ("variant".contains(valueType)) {
                Object v = this.variantCalc(value, provider);

                buff.append("(#" + prop + "# " + op + " " + v.toString() + ")");

            } else {
                throw new RuntimeException("the value type: " + valueType + " is illegal, mush be constant or variant");
            }

            logger.info("formula: {}", buff.toString());
            Boolean res = (Boolean) formulaPreCalc(buff.toString(), provider);
            logger.info("result: {}", res);
            condCache.put(seq, res);
        }

        String condFormula = propLimit.getPrprConditionFormula();
        logger.info("condFormula-origin: {}", condFormula);

        // substitute all conditions place holder.
        for (Integer i : condCache.keySet()) {
            String placeHolder = "[" + i + "]";
            condFormula = condFormula.replace(placeHolder, "" + condCache.get(i));
        }

        // substitue logic holder.
        condFormula = condFormula.replace("AND", "&").replace("OR", "|");
        logger.info("condFormula: {}", condFormula);

        return Boolean.parseBoolean("("+condFormula+")");
    }

    @Override
    public TfdFormula getTfdFormulaById(String formulaId) {
        return tfdFormulaMapper.selectByPrimaryKey(formulaId);
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public TfdFormula initNewFormula() {
        TfdFormula formula = new TfdFormula();
        formula.setAcctid(1);
        this.saveSetting(formula);
        tfdFormulaMapper.insert(formula);
        return formula;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Map initFormula(SimpleParasVo params) {
        Map<String,Object> map = null;
        try {
            String propId = params.getPropId();
            String varId = params.getVarId();
            map = new HashMap<String,Object>();
            String formulaId = null;
            String varType = "";
            String varName = "";
           // if(StringUtil.isNullOrEmpty(varId)){
                Result result = bizModelerService.getPropertyAndClass(propId);
                if(Result.RECODE_SUCCESS .equals( result.getRetCode())){
                    Map<String,Object> retMap = (Map)result.getData();
                    if(StringUtil.isNullOrEmpty(retMap)){
                        throw new RuntimeException("modeler 根据属性id取值为空，result：" + result.toString());
                    }
                    Map<String,Object> classMap = retMap.containsKey("class") ? (Map)retMap.get("class") : null;
                    if(!StringUtil.isNullOrEmpty(classMap)){
                        varName = classMap.containsKey("edmcName") ? classMap.get("edmcName") + "." : null;
                    }
                    Map<String,Object> propertyMap = retMap.containsKey("property") ? (Map)retMap.get("property") : null;
                    if(!StringUtil.isNullOrEmpty(propertyMap)){
                        // 根据BizModeler属性类型的设置关系 确定属性的类型
                        // 如果 edmpValueType 是 normalObj ，属性类型取edmpDataType
                        varType = propertyMap.containsKey("edmpValueType") ? (String) propertyMap.get("edmpValueType"): null;
                        if(!StringUtil.isNullOrEmpty(varType) && "normalObj".equalsIgnoreCase(varType)){
                            varType = propertyMap.containsKey("edmpDataType") ? (String) propertyMap.get("edmpDataType"): null;
                        }
                        varName = propertyMap.containsKey("edmpName") && !StringUtil.isNullOrEmpty(varName) ? varName + propertyMap.get("edmpName"): null;
                    }
                }
          //  }
            String varIdDB = getVarIDByPropId(propId);
            if(!StringUtil.isNullOrEmpty(varIdDB)){
                if(!StringUtil.isNullOrEmpty(varId) && !varIdDB.equals(varId) ){
                    throw new RuntimeException("属性id对应的变量id错误");
                }else{
                    varId = varIdDB;
                }
            }
            if(StringUtil.isNullOrEmpty(varId)){
                //新创建varId,formulaId 保存关联关系
                formulaId = initNewFormula().getFrmuId();
                varId = variantMgrService.initByformulaId(formulaId,varType,varName).getVrntId();
                //保存映射关系
                savePropIDAndVarID(propId,varId);
            }
            map.put("formulaId",formulaId);
            map.put("varId",varId);
            map.put("varType",varType);
            map.put("varName",varName);
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public Map initPPIFormula(SimplePPIParasVo params) {

        Map<String,Object> map = null;
        try {
            String ppiCId = params.getPpiId();
            String ppiId = ppiCId.substring(0,ppiCId.length()-1);
            String cycle = ppiCId.substring(ppiCId.length()-1,ppiCId.length());

            List<String> propIds = new ArrayList<>();
            if("A".equals(cycle)){
                String yPropId = ppiId + "Y";
                String sPropId = ppiId + "S";
                String mPropId = ppiId + "M";
                String wPropId = ppiId + "W";
                String dPropId = ppiId + "D";
                propIds.add(ppiCId);
                propIds.add(yPropId);
                propIds.add(sPropId);
                propIds.add(mPropId);
                propIds.add(wPropId);
                propIds.add(dPropId);
            }else{
                propIds.add(ppiCId);
            }
            map = new HashMap<String,Object>();
            String formulaId = null;
            String varType = "";
            String varName = "";
            for(String propId : propIds) {
                String varId = params.getVarId();
                String varIdDB = getVarIDByPropId(propId);
                if (!StringUtil.isNullOrEmpty(varIdDB)) {
                    if (!StringUtil.isNullOrEmpty(varId) && !varIdDB.equals(varId)) {
                        throw new RuntimeException("属性id对应的变量id错误");
                    } else {
                        varId = varIdDB;
                    }
                }
                if (StringUtil.isNullOrEmpty(varId)) {
                    //新创建varId,formulaId 保存关联关系
                    formulaId = initNewFormula().getFrmuId();
                    varId = variantMgrService.initByformulaId(formulaId, varType, varName).getVrntId();
                    SimpleParasVo simpleParasVo = new SimpleParasVo(propId, varId);
                    //保存映射关系
                    savePropIDAndVarID(propId, varId);
                } else {

                    TvmVariant variant = variantMgrService.getVariantById(varId);
                    formulaId = variant.getVrntFormulaId();
                }
                if(ppiCId.equals(propId)) {
                    map.put("formulaId", formulaId);
                    map.put("varId", varId);
                }
            }
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        return map;
    }

    private String getVarIDByPropId(String propId) {
        SourceSystemRecordMapping sourceSystemRecordMapping = recordMappingService.queryRecord(propId,"1", Constant.ISENABLE_YSE);
        if(!StringUtil.isNullOrEmpty(sourceSystemRecordMapping)){
            return sourceSystemRecordMapping.getInsideId();
        }else{
            return null;
        }
    }

    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    private int savePropIDAndVarID(String propId, String varId) {
        SourceSystemRecordMapping sourceSystemRecordMapping = new SourceSystemRecordMapping();
        sourceSystemRecordMapping.setSourceName("modeler");
        sourceSystemRecordMapping.setInsideId(varId);
        sourceSystemRecordMapping.setInsideType("1");
        sourceSystemRecordMapping.setSourceMappingId(propId);
        sourceSystemRecordMapping.setSourceMappingType("1");
        return recordMappingService.insert(sourceSystemRecordMapping);
    }

    @Override
    public Result getFormulaElement(String varid, String userid, String classId) {

        try {
            Result result = new Result();
            //变量
            TvmVariant tvmVariant = variantMgrService.getVariantById(varid);
            if (StringUtil.isNullOrEmpty(tvmVariant) || StringUtil.isNullOrEmpty(tvmVariant.getVrntFormulaId())) {
                result.setRetCode(Result.RECODE_ERROR);
                result.setErrMsg("错误的变量编号");
                return result;
            }

            String formulaId = tvmVariant.getVrntFormulaId();

            Map<String, Object> formulaElementMap = new HashMap<String, Object>();
            List<Callable<Boolean>> list = new ArrayList<Callable<Boolean>>();

            //相关类
            list.add(new LoadRelatedClasses(formulaId, classId, formulaElementMap));
            //计算公式
            list.add(new GetTfdFormulaById(formulaId, formulaElementMap));
            //局部变量
            list.add(new GetByFormulaId(formulaId, Scope.VARIANT_SCOPE_LOCAL.getScope(), formulaElementMap));
            //系统变量
            list.add(new GetByFormulaId(formulaId, Scope.VARIANT_SCOPE_SYSTEM.getScope(), formulaElementMap));
            //常用函数
            list.add(new LoadCommonlyFormulas(userid, formulaElementMap));
            //函数
            list.add(new LoadAllFunctions(formulaElementMap));

            List<Future<Boolean>> futures = executor.invokeAll(list);

            for (Future<Boolean> f : futures) {
                f.get();
            }

            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaElementMap);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }

    }

    @Override
    public Result getFormulaFunction(String userid) {

        try {
            Result result = new Result();
            Map<String, Object> formulaElementMap = new HashMap<String, Object>();
            List<Callable<Boolean>> list = new ArrayList<Callable<Boolean>>();
            //常用函数
            list.add(new LoadCommonlyFormulas(userid, formulaElementMap));
            //函数
            list.add(new LoadAllFunctions(formulaElementMap));

            List<Future<Boolean>> futures = executor.invokeAll(list);

            for (Future<Boolean> f : futures) {
                f.get();
            }

            result.setRetCode(Result.RECODE_SUCCESS);
            result.setData(formulaElementMap);
            return result;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new RuntimeException(e.getMessage());
        }

    }


    private class GetTfdFormulaById implements Callable<Boolean> {
        private String formulaId;
        private Map<String, Object> formulaElementMap;

        public GetTfdFormulaById(String formulaId, Map<String, Object> formulaElementMap){
            this.formulaElementMap = formulaElementMap;
            this.formulaId = formulaId;
        }

        @Override
        public Boolean call() throws Exception {
            //计算公式
            TfdFormula tfdFormula = getTfdFormulaById(formulaId);
            formulaElementMap.put("tfdFormula",tfdFormula);
            return true;
        }
    }

    private class LoadRelatedClasses implements Callable<Boolean> {
        private String formulaId;
        private Map<String, Object> formulaElementMap;

        public LoadRelatedClasses(String formulaId, String classId, Map<String, Object> formulaElementMap){
            this.formulaElementMap = formulaElementMap;
            this.formulaId = formulaId;
        }

        @Override
        public Boolean call() throws Exception {
            //相关类
            List<TfdClassRelated> tfdClassRelateds = relatedClassSettingService.loadRelatedClasses(formulaId);
            formulaElementMap.put("tfdClassRelateds",tfdClassRelateds);
            return true;
        }
    }

    private class GetByFormulaId implements Callable<Boolean> {
        private String formulaId;
        private String varScope;
        private Map<String, Object> formulaElementMap;

        public GetByFormulaId(String formulaId, String varScope, Map<String, Object> formulaElementMap){
            this.formulaElementMap = formulaElementMap;
            this.formulaId = formulaId;
            this.varScope = varScope;
        }

        @Override
        public Boolean call() throws Exception {
            //局部/系统变量
            List<TvmVariant> tvmLocVariants = variantMgrService.getByFormulaId(formulaId, varScope);
            String keyString = Scope.VARIANT_SCOPE_LOCAL.getScope().equalsIgnoreCase(varScope) ? "tvmLocVariants" : "tvmSysVariants";
            formulaElementMap.put(keyString,tvmLocVariants);
            return true;
        }
    }

    private class LoadCommonlyFormulas implements Callable<Boolean> {
        private String userid;
        private Map<String, Object> formulaElementMap;

        public LoadCommonlyFormulas(String userid, Map<String, Object> formulaElementMap){
            this.formulaElementMap = formulaElementMap;
            this.userid = userid;
        }

        @Override
        public Boolean call() throws Exception {
            //常用函数
            Map commonlyFormulasMap = behaivorTrackerService.loadCommonlyFormulas(userid);
            List<FunctionDescriber> commonlyFormulasList = new ArrayList<FunctionDescriber>(commonlyFormulasMap.values());
            formulaElementMap.put("commonlyFormulasList",commonlyFormulasList);
            return true;
        }
    }

    private class LoadAllFunctions implements Callable<Boolean> {
        private String userid;
        private Map<String, Object> formulaElementMap;

        public LoadAllFunctions(Map<String, Object> formulaElementMap){
            this.formulaElementMap = formulaElementMap;
            this.userid = userid;
        }

        @Override
        public Boolean call() throws Exception {
            //函数
            List<FunctionDescriber> allFuncList = functionDescriberService.loadAllFunctions();
            formulaElementMap.put("allFuncList",allFuncList);
            return true;
        }
    }

    /**
     * @deprecated
     */
    private class LoadCustomizeFunctions implements Callable<Boolean> {
        private Map<String, Object> formulaElementMap;

        public LoadCustomizeFunctions(Map<String, Object> formulaElementMap){
            this.formulaElementMap = formulaElementMap;
        }

        @Override
        public Boolean call() throws Exception {
            //自定义函数
            List<FunctionDescriber> customizeFuncList = new ArrayList<FunctionDescriber>();
            formulaElementMap.put("customizeFuncList",customizeFuncList);
            return true;
        }
    }



    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public String savePPIFormula(PPIFormula ppiFormula) {
        String ppiCId = ppiFormula.getPpiId();
        String ppiId = ppiCId.substring(0,ppiCId.length()-1);
        String cycle = ppiCId.substring(ppiCId.length()-1,ppiCId.length());
        List<String> propIds = new ArrayList<>();
        String result = "";
        if("A".equals(cycle)){
            String yPropId = ppiId + "Y";
            String sPropId = ppiId + "S";
            String mPropId = ppiId + "M";
            String wPropId = ppiId + "W";
            String dPropId = ppiId + "D";
            propIds.add(ppiCId);
            propIds.add(yPropId);
            propIds.add(sPropId);
            propIds.add(mPropId);
            propIds.add(wPropId);
            propIds.add(dPropId);
        }else{
            propIds.add(ppiCId);
        }

        for(String propId : propIds) {
            TfdFormula formula = new TfdFormula();
            if(propId.equals(ppiCId)) {
                formula.setFrmuId(ppiFormula.getFrmuId());
            }else{
                String varId = getVarIDByPropId(propId);
                TvmVariant variant = variantMgrService.getVariantById(varId);
                String formulaId = variant.getVrntFormulaId();
                formula.setFrmuId(formulaId);
            }
                formula.setFrmuFormulaContent(ppiFormula.getFrmuFormulaContent());
                formula.setFrmuFormulaStyle(ppiFormula.getFrmuFormulaStyle());
                formula.setFrmuFormulaText(ppiFormula.getFrmuFormulaText());
                formula.setAcctid(2);
                formula.setAdduser("MR. Mock");
                formula.setAddtime(new Date());
                formula.setModtime(new Date());
                formula.setModuser("MR. Mock");
                formula.setIsenable((byte) 1);

            try {
                this.preSave(formula);
                tfdFormulaMapper.insert(formula);
                if(propId.equals(ppiCId)) {
                    result = formula.getFrmuId();
                }
            } catch (Exception e) {
                String errMsg = "新增公式错误！";
                logger.error(errMsg, e);
                throw new RuntimeException(errMsg, e);
            }
        }
        return  result;
    }

    @Override
    @Transactional(readOnly = false, rollbackFor = RuntimeException.class)
    public int updatePPIFormula(PPIFormula ppiFormula) {
        String ppiCId = ppiFormula.getPpiId();
        String ppiId = ppiCId.substring(0, ppiCId.length() - 1);
        String cycle = ppiCId.substring(ppiCId.length() - 1, ppiCId.length());
        List<String> propIds = new ArrayList<>();
        int id = 0;
        if ("A".equals(cycle)) {
            String yPropId = ppiId + "Y";
            String sPropId = ppiId + "S";
            String mPropId = ppiId + "M";
            String wPropId = ppiId + "W";
            String dPropId = ppiId + "D";
            propIds.add(ppiCId);
            propIds.add(yPropId);
            propIds.add(sPropId);
            propIds.add(mPropId);
            propIds.add(wPropId);
            propIds.add(dPropId);
        } else {
            propIds.add(ppiCId);
        }

        for (String propId : propIds) {
            TfdFormula formula = new TfdFormula();

            String varId = getVarIDByPropId(propId);
            TvmVariant variant = variantMgrService.getVariantById(varId);
            String formulaId = variant.getVrntFormulaId();
            formula.setFrmuId(formulaId);
            formula.setFrmuFormulaContent(ppiFormula.getFrmuFormulaContent());
            formula.setFrmuFormulaStyle(ppiFormula.getFrmuFormulaStyle());
            formula.setFrmuFormulaText(ppiFormula.getFrmuFormulaText());
            formula.setAcctid(2);
            formula.setAdduser("MR. Mock");
            formula.setAddtime(new Date());
            formula.setModtime(new Date());
            formula.setModuser("MR. Mock");
            formula.setIsenable((byte) 1);

            try {
                if (propId.equals(ppiCId)) {
                    id = tfdFormulaMapper.updateByPrimaryKey(formula);
                }else{
                    tfdFormulaMapper.updateByPrimaryKey(formula);
                }
            } catch (Exception e) {
                String errMsg = "更新公式错误！";
                logger.error(errMsg, e);
                throw new RuntimeException(errMsg, e);
            }
        }
        return id;
    }
    /**
     *
     * @param formulaContent
     * @param provider
     * @return
     */
    @Override
    public Object calcPPI(String formulaContent, DataProvider provider) {

        try {

            if (null == provider) {
                provider = new SimpleDataProvider();
            }

            return this.calcPPIByData(formulaContent, provider);
        } catch (FormulaException e) {
            String errMsg = "Some error occured when calc formula.";
            logger.error(errMsg, e);
            throw new RuntimeException(errMsg, e);
        }

    }



    private Object calcPPIByData(String formulaContent, DataProvider dataProvider) {

        Matcher mc = pa.matcher(formulaContent);
        while (mc.find()) {
            String foobar = mc.group().replace("#", "");
        }
        logger.info("formulaContent: {}", formulaContent.replace("#", ""));
        logger.info("dataMap: {}", dataProvider.getDataContext());
        Expression expr = parser.parse(formulaContent.replace("#", ""));
        logger.info("expr: {}", expr.toString());
        return expr.getValue(dataProvider).getOriginValue();
    }


    public Object calcShow(ShowFormulaVo showFormulaVo){
        String frmuContent = showFormulaVo.getFrmuContent();
        String dataId = showFormulaVo.getDataId();
        String edmcId = showFormulaVo.getEdmcId();
        Map<String,Object> dataMap = new HashMap<>();
        //查询出主类的数据
        Map<String,String> mainDataMap = getDataMapByClassIdAndDataId(edmcId,dataId);
        //根据主类id查询所有属性
        Result propsResult = bizModelerService.getClassPropsById(edmcId);

        Matcher mc = pa.matcher(frmuContent);
        Map <String,Set<String>> map = new HashMap<>();
        Set<String> dataSetNameSet = new HashSet<>();
        List<String> dataSetPropList = new ArrayList<>();
        Map<String,String> enumMap = new HashMap();
        while (mc.find()) {
            String foobar = mc.group().replace("#", "");
            if(foobar.startsWith("prop_")){
                Set<String> set;
                String[]  conts = foobar.split("_");
                String classId = conts[1];
                String propId = conts[2];
                Result result = bizModelerService.getPropertyAndClass(propId);

                Map<String,Map<String,String>> data = (Map<String,Map<String,String>> ) result.getData();
                //String edmName = data.get("class").get("edmcCode");

                String prop = getParentProp(data.get("property").get("edmpCode"),data.get("property").get("edmpParentId"));
                String className = data.get("class").get("edmcCode");
//                //判断属性类型为枚举类型时,将属性编码放入枚举map中,在之后的取值中特殊处理
//                if(!StringUtil.isNullOrEmpty(data.get("property").get("edmpValueType"))&&data.get("property").get("edmpValueType").equals("enum")){
//                    enumMap.put(prop,data.get("property").get("edmpDataType"));
//                }
                if(prop.indexOf(".")!=-1){
                    dataSetNameSet.add(className+"."+prop.substring(0,prop.lastIndexOf(".")));
                    dataSetPropList.add(prop.substring(prop.lastIndexOf(".")+1,prop.length()));
                    //判断存在不同属性集的公式运算时，由于属性集不同,无法合并计算，故直接返回异常信息
                    if(dataSetNameSet.size()>1){
                        throw new RuntimeException("属性集不同,无法计算!");
                    }

                }else {
                    if (StringUtil.isNullOrEmpty(map.get(classId))) {
                        set = new HashSet<>();
                        set.add(prop);
                        map.put(classId, set);
                    } else {
                        set = map.get(classId);
                        set.add(prop);
                        map.put(classId, set);
                    }
                }
                frmuContent = frmuContent.replace(foobar,prop);
                dataMap.put(prop,null);
            }
        }

        for(String key: map.keySet()){
            //判断是否为主类的属性，如果是主类属性,直接取对应属性值，如果是关联类，则从主类属性中找出关联类的对象id,再查询出关联类的数据
            if(key.equals(edmcId)){
                for(String propCode : map.get(key)){
//                        //判断属性是否是枚举类型，如果是调用方法获取枚举的名称，否则直接返回对应属性值
//                        if(enumMap.containsKey(propCode)) {
//                            dataMap.put(propCode,getEnumName(mainDataMap.get(propCode),enumMap.get(propCode)));
//                        }else{
                            dataMap.put(propCode, mainDataMap.get(propCode));
//                        }
                }
            }else{
                for(Map<String,String> propMap : (List<Map<String,String>>)propsResult.getData()){
                    if(!StringUtil.isNullOrEmpty(propMap.get("edmpObjEdmcId"))&&propMap.get("edmpObjEdmcId").equals(key)){
                        if(!StringUtil.isNullOrEmpty(mainDataMap.get(propMap.get("edmpCode")))) {
                            Map<String, String>    relaMap = getDataMapByClassIdAndDataId(key, mainDataMap.get(propMap.get("edmpCode")));
                            for (String propCode : map.get(key)) {
//                            //判断属性是否是枚举类型，如果是调用方法获取枚举的名称，否则直接返回对应属性值
//                            if(enumMap.containsKey(propCode)){
//                                dataMap.put(propCode,getEnumName(relaMap.get(propCode),enumMap.get(propCode)));
//                            }else {
                                dataMap.put(propCode, relaMap.get(propCode));
//                            }
                            }
                            break;
                        }

                    }
                }
            }
        }
        //查询属性集属性
        if(dataSetNameSet.size()>0&&dataSetPropList.size()>0){
            List<String> dataSetNameList = new ArrayList(dataSetNameSet);
            Map<String,List<String>>   dataSetMap = getDataSetMapByClassIdAndDataId(dataSetNameList.get(0),dataSetPropList,dataId);
            dataMap.putAll(dataSetMap);

        }
        DataProvider provider = new SimpleDataProvider(dataMap);
        Expression expr = parser.parse(frmuContent.replace("#", ""));
        logger.info("");
        return expr.getValue(provider).getOriginValue();
    }

    /**
     * 根据类id和对象id查询数据
     * @param classId
     * @param dataId
     * @return
     */
    private Map getDataMapByClassIdAndDataId (String classId,String dataId){
            Result classResult = bizModelerService.getClassById(classId);
            Map<String,String> classData = (Map<String,String>) classResult.getData();
            String className = classData.get("edmcCode");
            ParamObject paramObject = new ParamObject();
            SearchObject searchObject = new SearchObject();
            paramObject.setEdmName(className);
            List<Condition> conditions = new ArrayList<>();
            Condition condition = new Condition();
            condition.setAttr("id");
            condition.setOperator("=");
            condition.setValue(dataId);
            conditions.add(condition);
            searchObject.setConditions(conditions);
            paramObject.setSearch(searchObject);
            String ormJSON = JSON.toJSONString(paramObject);
            Result ormResult = dataSharingService.find(ormJSON);
            Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) ormResult.getData();
            List<Map<String,String>> dataSet = data.get("dataset");
            Map<String,String> dataMap = new HashMap();
            if(dataSet.size()>0) {
                dataMap = dataSet.get(0);
            }
            return dataMap;
    }

    /**
     * 查询属性集数据
     * @param edmName
     * @param dataSetProps
     * @param dataId
     * @return
     */
    private Map<String,List<String>>  getDataSetMapByClassIdAndDataId (String edmName,List<String> dataSetProps,String dataId){
        String dataSetName = edmName.substring(edmName.indexOf(".")+1,edmName.length());
        ParamObject paramObject = new ParamObject();
        SearchObject searchObject = new SearchObject();
        paramObject.setEdmName(edmName);
        List<Condition> conditions = new ArrayList<>();
        Condition condition = new Condition();
        condition.setAttr("pid");
        condition.setOperator("=");
        condition.setValue(dataId);
        conditions.add(condition);
        searchObject.setConditions(conditions);
        searchObject.setColumns(dataSetProps);
        paramObject.setSearch(searchObject);
        String ormJSON = JSON.toJSONString(paramObject);
        Result ormResult = dataSharingService.find(ormJSON);
        Map<String, List<Map<String, String>>> data = (Map<String, List<Map<String, String>>>) ormResult.getData();
        List<Map<String,String>> dataSet = data.get("dataset");
        Map<String,List<String>> dataSetMap = new HashMap<>();
        for(Map<String,String> map :dataSet){

            for(String prop :dataSetProps){
                List<String> list;
                if(StringUtil.isNullOrEmpty(dataSetMap.get(dataSetName+"."+prop))){
                    list = new ArrayList<>();
                    dataSetMap.put(dataSetName+"."+prop,list);
                }else{
                    list = dataSetMap.get(dataSetName+"."+prop);
                }
                list.add(map.get(prop));
            }
        }
        return dataSetMap;
    }

    private String getParentProp(String edmpCode, String edmpParentId) {
        Map map = new HashMap();
        String edmSetCode;
        if (!StringUtil.isNullOrEmpty(edmpParentId)) {
            Result propResult;
            propResult = bizModelerService.getEdmProp(edmpParentId);
            Map<String, String> propDatas = (Map<String, String>) propResult.getData();
            edmSetCode = getParentProp(propDatas.get("edmpCode") + "." + edmpCode, propDatas.get("edmpParentId"));
        }else{
            edmSetCode = edmpCode;
        }
        return edmSetCode;

    }
    /**
     * 根据枚举编码和枚举类型获取枚举名称
     */
    private String getEnumName(String enumCode,String enumType){
        String enumName ="";
        ParamObject typeParam = new ParamObject();
        SearchObject typeSearch = new SearchObject();
        typeParam.setEdmName("wordlist");
        List<Condition> typeConditions = new ArrayList<>();
        List<String> typeColumnList = new ArrayList<>();
        typeColumnList.add("id");
        Condition typeCondition = new Condition();
        typeCondition.setAttr("info_code");
        typeCondition.setOperator("=");
        typeCondition.setValue(enumType);
        typeConditions.add(typeCondition);
        typeSearch.setConditions(typeConditions);
        typeSearch.setColumns(typeColumnList);
        typeParam.setSearch(typeSearch);
        String typeJSON = JSON.toJSONString(typeParam);
        Result typeResult = dataSharingService.find(typeJSON);
        Map<String, List<Map<String, String>>> typeData = (Map<String, List<Map<String, String>>>) typeResult.getData();
        List<Map<String,String>> typeDataSet = typeData.get("dataset");
        String typeId="";
        if(!StringUtil.isNullOrEmpty(typeDataSet)&&typeDataSet.size()>0){
            typeId = typeDataSet.get(0).get("id");
            ParamObject enumParam = new ParamObject();
            SearchObject enumSearch = new SearchObject();
            enumParam.setEdmName("wordlist");
            List<Condition> enumConditions = new ArrayList<>();
            List<String> enumColumnList = new ArrayList<>();
            enumColumnList.add("info_name");
            Condition enumCondition1 = new Condition();
            enumCondition1.setAttr("word_par");
            enumCondition1.setOperator("=");
            enumCondition1.setValue(typeId);
            Condition enumCondition2 = new Condition();
            enumCondition2.setAttr("info_code");
            enumCondition2.setOperator("=");
            enumCondition2.setValue(enumCode);
            enumConditions.add(enumCondition1);
            enumConditions.add(enumCondition2);
            enumSearch.setColumns(enumColumnList);
            enumSearch.setConditions(enumConditions);
            enumParam.setSearch(enumSearch);
            String enumJSON = JSON.toJSONString(enumParam);
            Result enumResult = dataSharingService.find(enumJSON);
            Map<String, List<Map<String, String>>> enumData = (Map<String, List<Map<String, String>>>) enumResult.getData();
            List<Map<String,String>> enumDataSet = enumData.get("dataset");

            if(!StringUtil.isNullOrEmpty(enumDataSet)&&enumDataSet.size()>0){
                enumName = enumDataSet.get(0).get("info_name");
            }else{
                return enumCode;
            }
        }else{
            return enumCode;
        }
        return enumName;
    }
}
