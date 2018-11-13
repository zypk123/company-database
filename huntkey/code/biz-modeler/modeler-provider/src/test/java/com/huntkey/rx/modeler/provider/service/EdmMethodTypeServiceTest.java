package com.huntkey.rx.modeler.provider.service;

/**
 * Created by gujing on 2017/5/4 0004.
 */


import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmMethodType;
import com.huntkey.rx.modeler.common.model.vo.EdmMethodTypeVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.huntkey.rx.modeler.provider.ModelerProviderApplication;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ModelerProviderApplication.class)
public class EdmMethodTypeServiceTest {
    @Autowired
    EdmMethodTypeService edmMethodTypeService;



    @Test
    public void testSelectEdmMethodTypeVOWithSonById() {
        List<EdmMethodTypeVO> edmMethodTypeVOList = edmMethodTypeService.selectEdmMethodTypeVOWithSonById("3");
        System.out.println("=========" + edmMethodTypeVOList);
    }



    @Test
    public void testAdd(){
        EdmMethodType edmMethodType = new EdmMethodType();
        edmMethodType.setId(UuidCreater.uuid());
        edmMethodType.setEdmtCode("test");
        edmMethodType.setEdmtName("test");
        edmMethodType.setIsDel((byte)0);
        edmMethodType.setAddtime(new Date());
        edmMethodTypeService.insert(edmMethodType);
    }

    @Test
    public void testDelete(){
        edmMethodTypeService.delete("6");
    }



    @Test
    public void testUpdate(){
        EdmMethodType edmMethodType = new EdmMethodType();
        edmMethodType.setId("8cf17a6dd51a458cb288f733c2950d2e");
        edmMethodType.setEdmtCode("test");
        edmMethodType.setEdmtName("test");
        edmMethodType.setIsDel((byte)0);
        edmMethodType.setAddtime(new Date());
        edmMethodTypeService.update(edmMethodType);
    }

    @Test
    public void testMoveMethodtypeMethodToMethodtype(){
        edmMethodTypeService.moveMethodtypeMethodToMethodtype("0f39c2e74d4847e98709c63baf95a4df","aaa");
    }


}

