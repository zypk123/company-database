package com.huntkey.rx.sceo.formula.provider.related.classes.service;

import com.huntkey.rx.sceo.formula.common.model.TfdClassRelated;
import com.huntkey.rx.sceo.formula.common.model.TplCondition;
import com.huntkey.rx.sceo.formula.common.model.vo.ClassRelatedVo;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
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
public class RelatedClassSettingServiceTest {

    @Autowired
    RelatedClassSettingService relatedClassSettingService;

    @Test
    public void testLoadRelatedClasses() {
        List<TfdClassRelated> tfdClassRelateds = relatedClassSettingService.loadRelatedClasses("1");
        for (TfdClassRelated list : tfdClassRelateds) {
            System.out.println("======" + list);
        }
    }

    @Test
    public void testSaveRrelatedClasses() {
        TfdClassRelated tfdClassRelated = new TfdClassRelated();
        tfdClassRelated.setClssAliasName("AliasNameTest");
        tfdClassRelated.setClssClassRelatedName("ClassRelatedNameTest");
        tfdClassRelated.setAdduser("admin");
        relatedClassSettingService.saveRrelatedClasses(tfdClassRelated);
    }

    @Test
    public void testSaveOrUpdateCondition() {
        TplCondition tplCondition = new TplCondition();
//        tplCondition.setCndtOperate("+");
//        tplCondition.setCndtProp("PropTest");
//        tplCondition.setCndtSeq(1);
//        tplCondition.setCndtValue("Value Test");
        tplCondition.setCndtId("6c805331ef6e438180a03e1f78a48338");
        tplCondition.setCndtOperate("-");
//        relatedClassSettingService.saveOrUpdateCondition(tplCondition);
    }

    @Test
    public void testRemoveCondition() {
        relatedClassSettingService.removeCondition("111111");
    }

    @Test
    public void testRemoveAllConditions() {
        relatedClassSettingService.removeAllConditions(new String[]{"111111", "6c805331ef6e438180a03e1f78a48338"});
    }

    @Test
    public void testQueryCondisionsByClssId() {
        List<TplCondition> tplConditions = relatedClassSettingService.queryCondisionsByClssId("6b84ba0e9712444e99b62518956712c9");
        for (TplCondition tplCondition : tplConditions) {
            System.out.println(tplCondition);
        }
    }

    @Test
    public void testUpdateRelatedClassesWithFormula() {
        relatedClassSettingService.updateRelatedClassesWithFormula(new String[]{"12345"}, "3c30efcc97af436e90f93c3c93ba9b1f");
    }

    @Test
    public void testExistLocalVar() {
        System.out.println("========================" + relatedClassSettingService.existLocalVar("12345"));
    }

//    @Test
//    public void testShowLocalVarName() {
//        System.out.println("========================"+relatedClassSettingService.showLocalVarName("12345"));
//    }

    @Test
    public void testExistClassRelated() {
        System.out.println("========================" + relatedClassSettingService.existClassRelated("4c198f0f7f9b48df98895a3408f68631"));
    }

    @Test
    public void testDeleteClassRelated() {
        System.out.println("========================" + relatedClassSettingService.deleteClassRelated("e82c91f22bd0487c801435d54f98666e"));
    }

    @Test
    public void testGetClassRelatedAndConditionsByClssId() {
        ClassRelatedVo classRelatedVo = relatedClassSettingService.getClassRelatedAndConditionsByClssId("1199f28f55cd47638ce6c0a13a552b63");
        List<TplCondition> tplConditionList = classRelatedVo.getTplConditions();
        for (TplCondition tplCondition : tplConditionList) {
            System.out.println("================" + tplCondition);
        }

    }

    @Test
    public void testsaveCondition() {
        TplCondition condition = new TplCondition();
        condition.setCndtValueType("class");
        condition.setCndtValueOriginCodeLeft("junit");
        condition.setCndtValueOriginCodeRight("单元测试1");
        condition.setCndtPropOriginCode("junit属性1");
        relatedClassSettingService.saveCondition(condition);
    }

    @Test
    public void testCheckNameUnique() {
        System.out.println("=================" + relatedClassSettingService.checkNameUnique("Staffasdf","c885c816f2ae43fbb3db394cd1db5336","50ab33699e9a4844829cc9de82ec9cdb"));
    }
}
