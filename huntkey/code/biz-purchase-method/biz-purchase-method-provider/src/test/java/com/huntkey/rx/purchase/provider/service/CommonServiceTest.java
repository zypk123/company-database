package com.huntkey.rx.purchase.provider.service;

import com.huntkey.rx.purchase.common.constants.NumberConstants;
import com.huntkey.rx.purchase.provider.PurchaseProviderApplication;
import com.huntkey.rx.purchase.provider.service.CommonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangyu
 * @create 2018-01-19 13:50
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PurchaseProviderApplication.class)
public class CommonServiceTest {

    @Autowired
    private CommonService commonService;

    @Test
    public void getCodeTest() {
        String code = commonService.getCode(NumberConstants.PREFIX_SUPPLIER_MAINT_ORDER);
        System.out.println("========" + code);
    }

}
