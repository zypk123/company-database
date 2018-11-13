package com.huntkey.rx.sceo.formula.provider.engine.service;

import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by lulx on 2017/6/15 0015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class FormulaCheckerServiceTest {

    @Autowired
    private FormulaCheckerService formulaCheckerService;

    @Test
    public void testcheckFormula(){
        String formulaStr = "MAX(ABS(AVERAGE(CEILING(COUNT(INT(89/45)" +
                ",FLOOR(56+878,AND(IF(TEXT(date, yyyy-MM-dd HH:mm:ss),OR(a,TRUE(dasf,XOR(a,AND(as" +
                ",OR(asd,asd),as))),g),c),FALSE(t,NOT(false),TEXT(amount, ###,####.000)),true))" +
                ",SMALL(DATE(TIMESTAMP(DATEDELTA(DAY(MONTH(TIME(TIMESTAMP(ts), TODAY()," +
                " WEEKNUM(date, YEAR(date))))), DAYS(HOUR(NOW()), MINUTE(SECOND(date)))))), 1)),56)," +
                "SQRT(4))),MIN(LARGE(arr,1),LOG(MAX(1,SUM(SUMPRODUCT(arr1, arr2),2,3,SUMPRODUCT(arr1, arr2)," +
                "5,6,SUMPRODUCT(arr1, arr2),8,9,10))" +
                ", MOD(SQRT(CONCATENATE(EXACT(ISEMPTY(9+-8),LEFT(LEN(9*-4), LOWER(9/1)))," +
                " MID(REPLACE(CONCATENATE(a,a,a), 3, 3, REPT(RIGHT(VALUE(1212), 2), 3)), SEARCH(SPLIT(a," +
                "TRIM(  AND(IF(a,OR(a,TRUE(dasf,XOR(a,AND(as,OR(asd,asd),as))),g),c),FALSE(t,NOT(false),t),true)" +
                "  ),c), 2, UPPER(aaa)), 3))),3)),POWER(2, PRODUCT(1,SQRT(4))),-7),ROUND(123.456, SMALL(arr, 1)))";
        Result result = formulaCheckerService.checkFormula(formulaStr);
        Assert.assertEquals(Result.RECODE_SUCCESS,result.getRetCode());
    }
}
