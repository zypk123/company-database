package com.huntkey.rx.sceo.formula.provider.engine.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.engine.feign.EdmdService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Map;

/**
 * Created by chenfei1 on 2017/8/16 0016.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class EdmdServiceTest {

    @Autowired
    private EdmdService edmdService;

    @Test
    public void testGetEdmDataType() {
        Result result = edmdService.getEdmDataType("V1.0", "monitortreeorder");
        if (null == result.getData()) {
            throw new RuntimeException("edmName: {} has no data type descriptions.");
        }

        List<Map<String, String>> dataList = (List<Map<String, String>>) result.getData();

        System.out.println("dataList: " + dataList);
        System.out.println("dataList.size: " + dataList.size());
        for (Map<String, String> descMap : dataList) {

            for (String key : descMap.keySet()) {
                System.out.println(key + ", " + descMap.get(key));
                String[] arr = key.split("\\.");
                String fieldName = arr[arr.length - 1];
                String value = descMap.get(key);
            }

        }
    }
}
