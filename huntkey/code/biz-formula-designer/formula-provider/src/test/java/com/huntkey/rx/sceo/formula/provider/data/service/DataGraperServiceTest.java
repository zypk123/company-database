package com.huntkey.rx.sceo.formula.provider.data.service;

import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.engine.entity.SimpleDataProvider;
import com.huntkey.rx.sceo.formula.provider.variant.service.VariantMgrService;
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
public class DataGraperServiceTest {

    @Autowired
    private VariantMgrService variantMgrService;

    @Autowired
    private DataGraperService dataGraperService;

    @Test
    public void testDataGrap() {
        TvmVariant variant = variantMgrService.getVariantById("41761bced04e4234ac3bf4f5af750fa1");
        try {
            // "fc96335d06e8450ca04e2c3e7a57576b", "63c481659f3144deabc6a8f48fb8cfe1"
            SimpleDataProvider dataProvider = new SimpleDataProvider();
            dataProvider.getDataContext().put("id", "fc96335d06e8450ca04e2c3e7a57576b");
            dataProvider.getDataContext().put("edmName", "staff");

            dataGraperService.grapData(variant, dataProvider);

            System.out.println(dataProvider.getDataContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
