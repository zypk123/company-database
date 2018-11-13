package com.huntkey.rx.sceo.formula.provider.engine.service;

import com.alibaba.fastjson.JSON;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.TfdFormula;
import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import com.huntkey.rx.sceo.formula.engine.DataProvider;
import com.huntkey.rx.sceo.formula.engine.ExprValue;
import com.huntkey.rx.sceo.formula.engine.Parser;
import com.huntkey.rx.sceo.formula.engine.exception.FormulaException;
import com.huntkey.rx.sceo.formula.engine.expression.Expression;
import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.engine.dao.TfdFormulaMapper;
import com.huntkey.rx.sceo.formula.provider.engine.entity.SimpleDataProvider;
import com.huntkey.rx.sceo.formula.provider.variant.service.VariantMgrService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chenfei on 2017/6/16.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class FormulaEngineServiceTest {

    @Autowired
    private FormulaEngineService formulaEngineService;

    @Autowired
    private VariantMgrService variantMgrService;

    @Autowired
    private TfdFormulaMapper tfdFormulaMapper;

    private MockDataProvider dataProvider = null;

    @Before
    public void setup() {
        dataProvider = new MockDataProvider(new HashMap<String, Object>());
    }
    @Test
    public void testSaveFormula() {

        TfdFormula formula = new TfdFormula();
        formula.setAcctid(1);
        formula.setAddtime(new Date());
        formula.setAdduser("test");
        formula.setFrmuFormulaContent("AND(1>2)");
        formula.setFrmuFormulaStyle("no style");
        formula.setFrmuId(UUID.randomUUID().toString().replace("-", ""));
        formula.setIsenable((byte) 1);
        formula.setModtime(new Date());
        formula.setModuser("test");

        String formulaId = formulaEngineService.saveFormula(formula);

        Assert.assertNotNull(formulaId);
    }

    @Test
    public void testUpdateFormula() {
        TfdFormula formula = new TfdFormula();
        formula.setAcctid(1);
        formula.setAddtime(new Date());
        formula.setAdduser("test-update");
        formula.setFrmuFormulaContent("AND(1>2)");
        formula.setFrmuFormulaStyle("no style");
        formula.setFrmuId("3c30efcc97af436e90f93c3c93ba9b1f");
        formula.setIsenable((byte) 1);
        formula.setModtime(new Date());
        formula.setModuser("test-update");

        System.out.println(JSON.toJSONString(formula));

        int count = formulaEngineService.updateFormula(formula);

        Assert.assertEquals("1", "" + count);

    }


    @Test
    public void testGetExpressionFromCache() {
        String id = "4747474";  // there is not a real key.
        Object obj = formulaEngineService.getExpressionFromCache(id);
        Assert.assertNull(obj);
    }

    @Test
    public void testPutExpressionToCache() {

        String formulaId = UUID.randomUUID().toString().replace("-", "");
        System.out.println(formulaId);
        String formula = "AND(1>2, 2<3)";
        FunctionHandlerBuildIn functionHelper = new FunctionHandlerBuildIn();
        Parser parser = new Parser(functionHelper);
        Expression expr = parser.parse(formula);

        formulaEngineService.putExpression2Cache(formulaId, expr);
    }

    @Test
    public void testCalExpression() {
        String formulaId = "9b0de5f9113c4cdb8904649cb0d2a682";
        Expression expr = formulaEngineService.getExpressionFromCache(formulaId);
        System.out.println(expr.toString());
        ExprValue exprValue = expr.getValue(null);

        Assert.assertFalse(exprValue.getBoolean());
    }

    @Test
    public void testCalc() {
        String formulaId = "3c30efcc97af436e90f93c3c93ba9b1f";
        Object obj = formulaEngineService.calc(formulaId, null);

        System.out.println(obj);
        Assert.assertEquals("false", obj.toString());
    }

    @Test
    public void testCalc3() {
        TvmVariant variant = variantMgrService.getVariantById("41761bced04e4234ac3bf4f5af750fa1");

        SimpleDataProvider dataProvider = new SimpleDataProvider();
        dataProvider.getDataContext().put("id", "fc96335d06e8450ca04e2c3e7a57576b");
        dataProvider.getDataContext().put("edmName", "staff");

        Object obj = formulaEngineService.variantCalc(variant, dataProvider);

        System.out.println(obj);
        //Assert.assertEquals("ab", obj.toString());
    }

    @Test
    public void testVariantCalc() {
        String variantId = "eb8d99300ce148df8d963b51ff8b4e02";
        Object obj = formulaEngineService.variantCalc(variantId, null);

        System.out.println(obj);
        Assert.assertEquals("false", obj.toString());
    }

    @Test
    public void testFormulaPreCalc() {

        String formulaContent  = "IF(AND(1 > 2, 3 > 4), 1 + 2, var)";
        dataProvider.getDataContext().put("var", 9);
        String value = formulaEngineService.formulaPreCalc(formulaContent, dataProvider).toString();
        Assert.assertEquals("9", value);

        formulaContent  = "MIN(0.6,1,9)";
        value = formulaEngineService.formulaPreCalc(formulaContent, dataProvider).toString();
        System.out.println(value);
    }

    @Test
    public void testFormulaPreCalc2() {

        String formulaContent = "#prop_6f94f06c66e211e7b2e4005056bc4879_4ea50c4c677911e7b2e4005056bc4879_repo003#+#var_sys1#-INT( 3.6)";
        dataProvider.getDataContext().put("prop_6f94f06c66e211e7b2e4005056bc4879_4ea50c4c677911e7b2e4005056bc4879_repo003", 9);
        dataProvider.getDataContext().put("var_sys1", 19);

        String value = formulaEngineService.formulaPreCalc(formulaContent, dataProvider).toString();
        Assert.assertEquals("25", value);
    }



    @Test
    public void testCalc2() {

        // DATEDIFF(YEARDIFF(#prop_prop002#,#var_GetDate#))
        // 1. use reglex expression to parsing #foo_bar#.
        // 2. judge the #foo_bar# content.
        // 3. if [foo == var] then calculate this variant, and put the result into context.
        // 4. else grap the data from EDM and then put the data into context.
        // 5. compile the formula expression.
        // 6. calculate the result.
        // 7. return the value of the result.

//        String formulaContent = "IF(#var_TestVAR#, #prop_prop002#, 'abc')";
//        System.out.println(calcRecurse(formulaContent));

        //fd0809e5cbbe43d48eca248c87bdea6a
        System.out.println(formulaEngineService.calc("fd0809e5cbbe43d48eca248c87bdea6a", null));
    }

    @Test
    public void testPropLimitFormulaCalc() {
        String propLimitId = "ed252c0c8e55423c9408c634b1189501";
        SimpleDataProvider sdp = new SimpleDataProvider();
        sdp.getDataContext().put("recode123", 23);
        boolean cr = formulaEngineService.propLimitFormulaCalc(propLimitId, sdp);
        System.out.println(cr);
    }

    @Test
    public void testBooleanParse() {

        boolean bool = Boolean.parseBoolean("(1 > 2) && ((3<4) && (5>6))");
        System.out.println(bool);
    }
}


/**
 * 测试用数据提供器
 */
class MockDataProvider implements DataProvider {

    private Map<String, Object> dataContext = null;

    public MockDataProvider(Map<String, Object> dataContext) {

        if (null == dataContext) {
            throw new FormulaException("the dataContext is null, please passed a not null data context map.");
        }
        this.dataContext = dataContext;
    }

    public Map<String, Object> getDataContext() {
        return dataContext;
    }

    @Override
    public Object getValue(String varName, String defaultValue) {

        Object varValue = dataContext.get(varName);

        return varValue;
    }


}
