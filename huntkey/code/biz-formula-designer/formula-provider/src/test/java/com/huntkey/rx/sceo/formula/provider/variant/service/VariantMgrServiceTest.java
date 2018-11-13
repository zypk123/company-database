package com.huntkey.rx.sceo.formula.provider.variant.service;

import com.huntkey.rx.commons.utils.rest.Pagination;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.common.model.TvmVariant;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by lulx on 2017/6/15 0015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class VariantMgrServiceTest {
    @Autowired
    private VariantMgrService variantMgrService;

    @Test
    public void testLoadSystemVariants() {
        List<TvmVariant> list = variantMgrService.loadSystemVariants("");
        Assert.assertEquals(1, list.size());
    }

    @Test
    @Ignore
    public void testSaveOrUpdateVariant() {
        TvmVariant tvmVariant = new TvmVariant();
        int retInt = -1;
        /*retInt = variantMgrService.saveOrUpdateVariant(tvmVariant);
        Assert.assertEquals(1,retInt);*/

        tvmVariant.setVrntId("aaa");
        tvmVariant.setVrntVarName("update2");
        retInt = variantMgrService.updateVariant(tvmVariant);
        Assert.assertEquals(1, retInt);

    }

    @Test
    public void testRemoveVariant() {
        Result retInt = variantMgrService.removeVariant("aaa");
        Assert.assertEquals("1", String.valueOf(retInt.getRetCode()));
    }

    @Test
    public void testdisableVariant() {
        int retInt = -1;
        retInt = variantMgrService.disableVariant("aaa");
        Assert.assertEquals(1, retInt);
    }

    @Test
    public void testQueryVariants() {
        int retInt = -1;
        List<TvmVariant> list = variantMgrService.queryVariants("update2", "");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testLoadSystemVariantsWithFormula() {
        int retInt = -1;
        List<TvmVariant> list = variantMgrService.loadSystemVariantsWithFormula("");
        Assert.assertEquals(1, list.size());
    }

    @Test
    public void testLoadLocalVariantsWithFormula() {
        int retInt = -1;
        List<TvmVariant> list = variantMgrService.loadLocalVariantsWithFormula("");
        Assert.assertEquals(0, list.size());
    }

    @Test
    @Ignore
    public void testsaveOrUpdateLocalVariantsWithFormula() {
        String[] strArr = {"aaa"};
        variantMgrService.saveOrUpdateLocalVariantsWithFormula(strArr, "1");
    }

    @Test
    @Ignore
    public void testsaveOrUpdateSystemVariantsWithFormula() {
        String[] strArr = {"aaa"};
        variantMgrService.saveOrUpdateSystemVariantsWithFormula(strArr, "1");
    }

    @Test
    public void testquerySystemVariants() {
        Pagination<TvmVariant> list = variantMgrService.querySystemVariants("aa", "", "vrnt_state", "desc", 1, 5);
        System.out.println(list.getTotal());
    }

    @Test
    public void testDelLocalVarCheck() {
        System.out.println(variantMgrService.existLocalVar("111111111"));
    }

    @Test
    public void testExistRelatedClass() {
        System.out.println(variantMgrService.existRelatedClass("1f512712c1b1483cb5f23555875e41dc"));
    }








}
