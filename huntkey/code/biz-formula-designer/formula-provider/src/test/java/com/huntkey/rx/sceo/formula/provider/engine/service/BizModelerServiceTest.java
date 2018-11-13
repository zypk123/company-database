package com.huntkey.rx.sceo.formula.provider.engine.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.engine.feign.BizModelerService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulx on 2017/8/26 0026 下午 2:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class BizModelerServiceTest {

    @Autowired
    private BizModelerService bizModelerService;

    @Test
    public void testDeleteLimitOrFormula(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("edmpFormula","a01dd02c5b4c47f0a3c61438a9baa99d");
        map.put("edmpValueLimit","");
        Result result = bizModelerService.deleteLimitOrFormula(map);
        System.out.println(result);
        Assert.assertEquals("1",String.valueOf(result.getRetCode()));
    }
}
