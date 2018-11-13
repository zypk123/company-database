package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.commons.utils.uuid.UuidCreater;
import com.huntkey.rx.modeler.common.model.EdmConnect;
import com.huntkey.rx.modeler.provider.ModelerProviderApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by linziy on 2017/4/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ModelerProviderApplication.class)
public class EdmConnectServiceTest {
    @Autowired
    private EdmConnectService edmConnectService;

    @Test
    @Ignore
    public void add(){
        EdmConnect edmConnect = new EdmConnect();
        Date dateTime = new Date();

        edmConnect.setId(UuidCreater.uuid());
        edmConnect.setEdcnEdmpId("123456789ABCDEF123456789ABCDEF");
        edmConnect.setEdcnLinkPreservable((byte)1);
        edmConnect.setEdcnUpdateType("other");
        edmConnect.setEdcnUpdateTime("00:00");
        edmConnect.setModtime(dateTime);
        edmConnect.setAddtime(dateTime);
        edmConnect.setAdduser("user1");
        edmConnect.setAcctid((byte)10);

        edmConnectService.insert(edmConnect);
    }
    @Test
    @Ignore
    public void logicdelete() {
        String id = "ewq34c8dd7d94eba8e3cef9e0cc361ec";
        edmConnectService.delete(id);
    }
    @Test
    @Ignore
    public void update(){
        EdmConnect edmConnect = new EdmConnect();
        Date dateTime = new Date();
        edmConnect.setId("ewq34c8dd7d94eba8e3cef9e0cc361ec");
        edmConnect.setEdcnEdmpId("123456789ABCDEF123456789ABCDEF");
        edmConnect.setEdcnLinkPreservable((byte)0);
        edmConnect.setEdcnUpdateType("other");
        edmConnect.setEdcnUpdateTime("00:00;01:00");
        edmConnect.setModtime(dateTime);
        edmConnect.setAddtime(dateTime);
        edmConnect.setAdduser("user1");
        edmConnect.setAcctid((byte)10);
        edmConnectService.update(edmConnect);
    }

    @Test

    public void selectbyid()
    {
        edmConnectService.selectbyid("ewq34c8dd7d94eba8e3cef9e0cc361ec");
    }
    @Test
    @Ignore
    public void selectbyexample()
    {
        String EdcnEdmpId = "123456789ABCDEF123456789ABCDEF";
        Byte EdcnLinkPreservable = 0;
        Byte EdcnUpdateType = 1;
        edmConnectService.selectByExample(EdcnEdmpId,EdcnLinkPreservable,EdcnUpdateType,1,10);
    }
}
