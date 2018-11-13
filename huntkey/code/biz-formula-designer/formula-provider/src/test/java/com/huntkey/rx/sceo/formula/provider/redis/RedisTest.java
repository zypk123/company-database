package com.huntkey.rx.sceo.formula.provider.redis;

import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.redis.service.IRedisService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by lulx on 2017/6/19 0019.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class RedisTest {

    private static Logger log = LoggerFactory.getLogger(RedisTest.class);

    @Autowired
    private IRedisService redisService;

    @Test
    public void testRedis() throws Exception {
        String key = "redis-test-aaa";
        redisService.set(key, "111");
        Assert.assertEquals("111", redisService.get(key));
        Long del = redisService.delete(key);
        Assert.assertEquals(Long.valueOf(1), del);
    }

    @Test
    public void testDel(){
        String key = "FORMULA_BEHAIVOR_USERID_aaa";
        Long del = redisService.delete(key);
        Assert.assertEquals(Long.valueOf(1), del);
    }

    @Test
    public void testMap(){
        String key = "FORMULA_BEHAIVOR_USERID_aaa";
        System.out.println(redisService.get(key.getBytes()));
    }
}
