package com.huntkey.rx.sceo.formula.provider.property.service;

import com.alibaba.fastjson.JSON;
import com.huntkey.rx.sceo.formula.common.model.TplPropertyLimit;
import com.huntkey.rx.sceo.formula.common.util.Constant;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import org.junit.Test;
import com.huntkey.rx.commons.utils.rest.Result;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

/**
 * Created by zhangyu on 2017/6/15 0015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class PropertyLimitSettingServiceTest {

    @Autowired
    PropertyLimitSettingService propertyLimitSettingService;

    @Test
    public void testQueryAllConditions() {
        List<TplCondition> tplCondition = propertyLimitSettingService.queryAllConditions("12345");
        if(tplCondition == null || tplCondition.size() == 0){
            System.out.println("=====testQueryAllConditions》》》 no result or error ！" );
        }else {
            for (int i = 0; i < tplCondition.size(); i++) {
                System.out.println("=====testQueryAllConditions " + JSON.toJSON(tplCondition.get(i)));
            }
        }
    }
    @Test
    public void testSaveOrUpdateCondition() {
        TplCondition tp = new TplCondition();
        tp.setAcctid(1);
        tp.setAddtime(new Date());
        tp.setAdduser(Constant.ADDUSER);
        tp.setCndtOperate(">");
        tp.setCndtProp("age");
        tp.setCndtPropClssId("e82c91f22bd0487c801435d54f98666e");
       // tp.setCndtId("asda212");
        tp.setCndtSeq(1);
        tp.setIsenable((byte)1);
        tp.setModtime(new Date());
        tp.setModuser(Constant.MODUSER);
        tp.setCndtValueType("const");
        tp.setCndtValue("1");
        int i  = propertyLimitSettingService.saveOrUpdateCondition(tp);
        Result rt = new Result();
        rt.setData(tp.getCndtId());
        System.out.println("=====testSaveOrUpdateCondition get the  i = " + i+ JSON.toJSON(rt));

    }
    @Test
    public void testRemoveCondition(){
        int i  = propertyLimitSettingService.removeCondition("3424rewfwef");
        System.out.println("=====testRemoveCondition get the  i = " + i);
    }

    @Test
    public void testSaveOrUpdatePropLimit(){
        TplPropertyLimit limit = new TplPropertyLimit();
        limit.setAcctid(1);
        limit.setIsenable((byte) 1);
        limit.setPrprPropMata("自然人");
        limit.setPrprPropDisplay("年龄");
        limit.setPrprConditionDesc("年龄 小于 150 并且 年龄 大于 1");
        limit.setPrprConditionName("自然人.年龄-属性限值条件");
        limit.setPrprConditionFormula("[1] AND [2]");
        int i  = propertyLimitSettingService.saveOrUpdatePropLimit(limit);
        System.out.println("=====testSaveOrUpdatePropLimit get the  i = " + i);
    }
}
