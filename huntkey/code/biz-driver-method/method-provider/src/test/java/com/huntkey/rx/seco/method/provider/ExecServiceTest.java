package com.huntkey.rx.seco.method.provider;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.provider.MethodProviderApplication;
import com.huntkey.rx.sceo.method.provider.entity.ParamsVo;
import com.huntkey.rx.sceo.method.provider.service.ExecService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulx on 2017/10/19 0019 上午 10:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MethodProviderApplication.class)
public class ExecServiceTest {
    @Autowired
    ExecService execService;

    /**
     * 同步执行
     */
    @Test
    public void execSyncTest(){
        ParamsVo params = new ParamsVo();
        params.setClassName("salesposition");
        params.setMethodName("load");
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "00e9a2b759114522a4dab5be28a3c19b");
        params.setParamObj(map);
        Result result = execService.execObject(params);
        System.out.println(result);
        Assert.assertEquals(Result.RECODE_SUCCESS,result.getRetCode());
    }

    /**
     * 异步执行
     */
    @Test
    public void execAsyncTest(){
        ParamsVo params = new ParamsVo();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "00e9a2b759114522a4dab5be28a3c19b");
        params.setClassName("salesposition");
        params.setMethodName("loadAsync");
        Result result = execService.execObject(params);
        System.out.println(result);
        Assert.assertEquals(Result.RECODE_SUCCESS,result.getRetCode());
    }

    /**
     * 自动方法
     */
    @Test
    public void execAutoTest(){
        ParamsVo params = new ParamsVo();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "00e9a2b759114522a4dab5be28a3c19b");
        params.setClassName("salesposition");
        params.setMethodName("loadAuto");
        params.setParamObj(map);
        Result result = execService.execObject(params);
        System.out.println(result);
        Assert.assertEquals(Result.RECODE_SUCCESS,result.getRetCode());
    }

    /**
     * 定时任务
     */
    @Test
    public void execTimingTest(){
        ParamsVo params = new ParamsVo();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("id", "00e9a2b759114522a4dab5be28a3c19b");
        params.setClassName("salesposition");
        params.setMethodName("loadTiming");
        Result result = execService.execObject(params);
        System.out.println(result);
        Assert.assertEquals(Result.RECODE_SUCCESS,result.getRetCode());
    }
}
