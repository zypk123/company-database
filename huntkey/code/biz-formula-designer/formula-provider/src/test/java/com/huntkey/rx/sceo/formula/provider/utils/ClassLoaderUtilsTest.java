package com.huntkey.rx.sceo.formula.provider.utils;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * Created by chenfei on 2017/7/26.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ClassLoaderUtilsTest {

    @Test
    public void testLoadClass() {
        try {
            File sourceFile = new File("/Users/chenfei/IdeaProjects/biz-formula-designer/formula-provider/src/main/java/com/huntkey/rx/sceo/formula/provider/utils/RestUtils.java");
            byte[] classCode = CompilerUtils.compile(sourceFile, "/tmp/");
            Class<?> clazz = ClassLoaderUtils.loadClass(classCode, "com.huntkey.rx.sceo.formula.provider.utils.RestUtils");
            Assert.assertEquals(clazz.newInstance().getClass().getName(), "com.huntkey.rx.sceo.formula.provider.utils.RestUtils");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
