package com.huntkey.rx.sceo.formula.provider.related.condition.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.TacConditionRelated;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by zhangyu on 2017/6/15 0015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class RelatedConditionSettingServiceTest {

    @Autowired
    RelatedConditionSettingService relatedConditionSettingService;

    @Test
    public void testSaveOrUpdateConditionRelated() {
        TacConditionRelated tacConditionRelated = new TacConditionRelated();
//        tacConditionRelated.setCndrId("356f832abaf14554bcdbc538877a2bed");
        tacConditionRelated.setCndrPropRelated("12345");
        tacConditionRelated.setCndrSeq(2);
        tacConditionRelated.setCndrProp("54321");
        tacConditionRelated.setCndrValue("ValueTest");
        tacConditionRelated.setCndrValueType("String");
//        relatedConditionSettingService.saveOrUpdateConditionRelated(tacConditionRelated);
    }

    @Test
    public void testQueryAllConditionsRelated() {
        List<TacConditionRelated> tacConditionRelateds = relatedConditionSettingService.queryAllConditionsRelated();
        for (TacConditionRelated tacConditionRelated : tacConditionRelateds) {
            System.out.println("==========" + tacConditionRelated);
        }
    }

    @Test
    public void testQueryConditionsRelatedByCndrId() {
        TacConditionRelated tacConditionRelated = relatedConditionSettingService.queryConditionsRelatedByCndrId("356f832abaf14554bcdbc538877a2bed");
        System.out.println("==============" + tacConditionRelated);
    }

    @Test
    public void testRemoveConditionRelated() {
        relatedConditionSettingService.removeConditionRelated("356f832abaf14554bcdbc538877a2bed");
    }

    @Test
    public void testSaveOrUpdatePropRelated() {
//        TacPropertyRelated tacPropertyRelated = new TacPropertyRelated();
//        tacPropertyRelated.setPrplId("5e6fd23623c9498794b508e7b1bc9ec9");
//        tacPropertyRelated.setPrplClassRelatedFrom("TestTest12345678");
//        tacPropertyRelated.setPrplClassRelatedTo("Test222222");
//        tacPropertyRelated.setPrplConditionName("TestName1");
//        tacPropertyRelated.setPrplClassRelatedFrom("Test33333");
//        tacPropertyRelated.setPrplConditionDesc("Test555555");
//        tacPropertyRelated.setPrplConditionFormula("Test6666688888");
//        relatedConditionSettingService.saveOrUpdatePropRelated(tacPropertyRelated);
    }

    @Test
    public  void testRelCondConfDataByPro(){
        Result result = relatedConditionSettingService.relCondConfDataByPro("98ddd49ff0a24d3e9d8d78bedee72667", "c30053c53ad948fea5ad118e5b1aeffb");
        System.out.println(result);
        Assert.assertEquals(Boolean.TRUE,result.getData());
    }
}
