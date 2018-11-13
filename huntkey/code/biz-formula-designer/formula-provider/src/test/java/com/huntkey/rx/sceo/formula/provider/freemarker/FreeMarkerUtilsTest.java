package com.huntkey.rx.sceo.formula.provider.freemarker;

import com.huntkey.rx.commons.utils.file.FileUtils;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.utils.FreeMarkerUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

/**
 * @author zhangyu
 * @create 2017-07-26 10:26
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class FreeMarkerUtilsTest {

    @Autowired
    FreeMarkerUtils freeMarkerUtils;

    @Test
    public void testCreateSrcFile() throws Exception {
        freeMarkerUtils.createSrcFile("com.huntkey.rx.sceo.formula.common.function.play.PlayFunction", "玩具描述", "玩具");
//        freeMarkerUtils.createSrcFile("Rule", "角色");
    }

    @Test
    public void testRead() throws Exception {
        File file = new File("E:\\work\\idea\\huntkey-rx\\biz-formula-designer\\formula-provider\\temp\\GoodFunction.java");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);
        String s = br.readLine();
        System.out.println("=========" + s.substring(8).replaceAll(";", "") + ".GoodFunction.java");
    }

    @Test
    public void getSrcFilePath() {
        System.out.println(freeMarkerUtils.getSrcFilePath());
    }

    @Test
    public void testDele() {
        File file = new File("E:\\test");
        FileUtils.deleteDirectory(file.getPath());
    }

}
