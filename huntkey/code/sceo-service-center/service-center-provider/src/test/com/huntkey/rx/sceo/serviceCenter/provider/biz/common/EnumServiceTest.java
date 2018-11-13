package com.huntkey.rx.sceo.serviceCenter.provider.biz.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.serviceCenter.ServiceCenterProviderApplication;
import com.huntkey.rx.sceo.serviceCenter.provider.biz.common.service.EnumService;
import com.huntkey.rx.sceo.serviceCenter.provider.orm.exception.DBException;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by clarkzhao on 2017/10/23.
 *
 * @author clarkzhao
 * @date 2017/10/23
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ServiceCenterProviderApplication.class)
public class EnumServiceTest {

    @Autowired
    private EnumService enumService;

    @Test
    @Ignore
    public void test() throws DBException{
        String enumCode = "undef";
        Result result = enumService.getEnumsObjects(enumCode);
        Assert.assertEquals(result.getRetCode(),new Integer(10001));

    }

    @Test
    public void testparent() throws DBException{
        String enumCode = "sex";
        Result result = enumService.getEnumsObjects(enumCode);
        Assert.assertEquals(result.getRetCode(),new Integer(1));
        Assert.assertEquals(JSONArray.parseArray(JSON.toJSONString(result.getData())).size(),2);

    }

    @Test
    public void testchildren() throws DBException{
        String enumCode = "clark";
        Result result = enumService.getEnumsObjects(enumCode);
        Assert.assertEquals(result.getRetCode(),new Integer(1));
        Assert.assertEquals(JSONObject.parseObject(JSON.toJSONString(result.getData())).getString("rinf_num"),"clark");

    }
}
