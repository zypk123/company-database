package com.huntkey.rx.sceo.formula.provider.function.definefunc;

import com.huntkey.rx.sceo.formula.common.model.TfmFunctionCompiled;
import com.huntkey.rx.sceo.formula.common.model.TfmFunctionDefinition;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.service.DefineFunctionService;
import com.huntkey.rx.sceo.formula.provider.function.definefunc.utils.FunctionDefinedUtils;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by nidongx on 2017/7/28.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class FunctionDefineTest {

    @Autowired
    DefineFunctionService definefunctionService;

    @Test
    public void testCreateCustomizeFunction(){

        FileInputStream fis = null;

        try {
            TfmFunctionDefinition funcDefinition = new TfmFunctionDefinition();
            funcDefinition.setFundFunDesc("test");
            funcDefinition.setFundState("inusing");
            funcDefinition.setFundFunCatagoryId("01981182f04c480e84c261832aa293c7");

            File file = new File("/tmp/Count33.java");
            fis = new FileInputStream(file);
            String[] arr = FunctionDefinedUtils.getSourceCodeAndClassFullName(fis);

//            definefunctionService.createCustomizeFunction(funcDefinition, file.getName(),
//                    arr[0], arr[1], "com.huntkey.rx.sceo.formula.common.function.rule.RuleFunction");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Test
    public void testGetDefineFunByDefineId() {

        String funcId = "8401f4216ebb49fba46745d70f5ff0e9";
        TfmFunctionDefinition funcDefinition = definefunctionService.getDefineFunByDefineId(funcId);

        Assert.assertNotNull(funcDefinition);
    }

    @Test
    public void testGetCompileFunByCompileId(){

        String funcId = "c99fb65def3844bfbd2c682703467398";
        TfmFunctionCompiled funcCompiled = definefunctionService.getCompileFunByCompileId(funcId);

        Assert.assertNotNull(funcCompiled);
    }

    @Test
    public void testUpdateCustomizeFunction() {

        FileInputStream fis = null;

        try {
            TfmFunctionDefinition funcDefinition = new TfmFunctionDefinition();
            funcDefinition.setFundId("8401f4216ebb49fba46745d70f5ff0e9");
            funcDefinition.setFundFunDesc("test34343");
            funcDefinition.setFundState("inusing");
            funcDefinition.setFundFunName("Count33");
            funcDefinition.setFundFunCatagoryId("01981182f04c480e84c261832aa293c7");

            File file = new File("/tmp/Count33.java");
            fis = new FileInputStream(file);
            String[] arr = FunctionDefinedUtils.getSourceCodeAndClassFullName(fis);

//            definefunctionService.updateCustomizeFunction(funcDefinition, file.getName(),
//                    arr[0], arr[1], "com.huntkey.rx.sceo.formula.common.function.rule.RuleFunction");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            if (null != fis) {
                try {
                    fis.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }

    }

    @Test
    public void testDeleteCustomizeFunction(){

        String funcId = "464c14e112e849adbc13afec718fb931";
        definefunctionService.deleteCustomizeFunction(funcId);
    }
}
