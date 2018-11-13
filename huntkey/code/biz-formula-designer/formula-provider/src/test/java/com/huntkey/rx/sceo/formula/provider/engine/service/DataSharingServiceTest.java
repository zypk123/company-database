package com.huntkey.rx.sceo.formula.provider.engine.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.engine.feign.DataSharingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by chenfei1 on 2017/8/3 0003.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class DataSharingServiceTest {

    @Autowired
    private DataSharingService dataSharingService;

    @Test
    public void testFind() {
        Result r = dataSharingService.find("{\"edmName\":\"customizedshow\",\"search\":{\"columns\":[\"cush002\"],\"conditions\":[],\"pagenation\":{\"rows\":15,\"startPage\":1}}}");
        System.out.println(r);
    }

    @Test
    public void testLoad() {
        Result r = dataSharingService.load("{\"edmName\":\"staff\",\"search\":{\"type\":\"ref\",\"ids\":[\"ad729c08630046a09d0c1d2676cef44e\",\"2345c9cc421547edbd7f3f869b65f3a1\"]}}");
        System.out.println(r);
    }
}
