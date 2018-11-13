package com.huntkey.rx.sceo.formula.provider.utils;

import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;

/**
 * Created by chenfei1 on 2017/8/17 0017.
 */
@SpringBootTest(classes = FormulaProviderApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RestUtilsTest {

    @Test
    public void testDoGet() {
        String url = "http://formula-client/defineFunction/queryDefineFunction";
        Object obj = RestUtils.doGet(url, new HashMap<String, String>());
        System.out.println(obj);
    }
}
