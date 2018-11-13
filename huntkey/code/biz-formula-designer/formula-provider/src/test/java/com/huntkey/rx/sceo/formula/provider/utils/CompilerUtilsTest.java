package com.huntkey.rx.sceo.formula.provider.utils;

import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import org.apache.http.util.Asserts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * Created by chenfei on 2017/7/26.
 */
@SpringBootTest(classes = FormulaProviderApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class CompilerUtilsTest {

    @Autowired
    FreeMarkerUtils freeMarkerUtils;

    @Test
    public void testCompile() {
        File sourceFile = new File("/Users/chenfei/IdeaProjects/biz-formula-designer/formula-provider/src/main/java/com/huntkey/rx/sceo/formula/provider/utils/RestUtils.java");
        byte[] classCode = CompilerUtils.compile(sourceFile, "/tmp/");

        Asserts.notNull(classCode, "null");
    }

    @Test
    public void testCompile2() {
        File sourceFile = new File(freeMarkerUtils.getSrcFilePath() + File.separator + "RuleFunction.java");
        byte[] classCode = CompilerUtils.compile(sourceFile, "/tmp/");
        for (byte c : classCode) {
            System.out.println(c);
        }
    }

}
