package com.huntkey.rx.modeler.provider.service;

import com.huntkey.rx.modeler.provider.ModelerProviderApplication;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;

/**
 * Created by linziy on 2017/5/27.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = ModelerProviderApplication.class)
public class EdmLinkServiceTest {

    @Autowired
    private EdmLinkService edmLinkService;

    @Test
    public void checkcircle(){
        String edmId = "c5c8a66acfea48d7b89320312422e621";
        String edmLinkId = "6f946d18377e4ce693e06c3a434f1e9b";
        boolean bool = edmLinkService.checkcircle(edmId,edmLinkId);
        System.out.println(bool);
    }
}
