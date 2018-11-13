package com.huntkey.rx.purchase.provider.service;

import com.huntkey.rx.purchase.common.model.supplier.SupplierMaintOrderDTO;
import com.huntkey.rx.purchase.provider.PurchaseProviderApplication;
import com.huntkey.rx.purchase.provider.service.impl.SupplierMaintOrderServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author zhangyu
 * @create 2018-01-11 16:07
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PurchaseProviderApplication.class)
public class SupplierMaintOrderServiceTest {

    @Autowired
    private SupplierMaintOrderServiceImpl supplierMaintOrderServiceImpl;

    @Autowired
    private SupplierMaintOrderService supplierMaintOrderService;

    @Test
    public void checkUniqueTest() {

        SupplierMaintOrderDTO supplierMaintOrderDTO = new SupplierMaintOrderDTO();

        // 伙伴编码
        supplierMaintOrderDTO.setSumoCode("cumo_123-1");

        // 伙伴简称
        supplierMaintOrderDTO.setSumoShortName("一直、很安静");

        // 统一社会信用代码
        supplierMaintOrderDTO.setSumoUscc("zy19931027131X-1");

        String msg = supplierMaintOrderServiceImpl.checkUnique(supplierMaintOrderDTO);

        System.out.println("============" + msg);
    }

    @Test
    public void loadTest() {
        supplierMaintOrderService.load("e5db952f06fa4a5e87a4ea7d3ee35e94");
    }


}
