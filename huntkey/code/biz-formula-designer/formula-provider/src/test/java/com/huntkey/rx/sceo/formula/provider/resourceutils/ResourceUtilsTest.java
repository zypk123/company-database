package com.huntkey.rx.sceo.formula.provider.resourceutils;

import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.io.FileInputStream;

/**
 * Spring ResourceUtils工具类测试
 *
 * @author zhangyu
 * @create 2017-07-26 15:20
 **/
public class ResourceUtilsTest {

    @Test
    public void testGetFile() throws Exception {
        File file = ResourceUtils.getFile("classpath:com\\huntkey\\rx\\sceo\\formula\\engine\\DataProvider.class");
        FileInputStream fis = new FileInputStream(file); // 字节输入流读取数据
        byte[] buffer = new byte[1024 * 1024];
        int len = -1;
        while ((len = fis.read(buffer)) != -1) {
            String str = new String(buffer, 0, len);
            System.out.println(str);
        }
        fis.close();
    }
}
