import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.formula.engine.tools.ValidateTool;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by lulx on 2017/5/31 0031.
 */
public class ValidateExpressionTests {

    @Test
    public void test(){
        long begintime;
        Result result;

        begintime = getTime();
        String expressionLogic = "AND(IF(a,OR(a,TRUE(dasf,XOR(a,AND(as,OR(asd,asd),as))),g),c),FALSE(t,NOT(false),t),true)";
        result = ValidateTool.validateByallRegex(expressionLogic);
        System.out.println(result.getErrMsg()+""+result.getRetCode());
        Assert.assertEquals(Result.RECODE_SUCCESS,result.getRetCode());
        System.out.println("Logic :  "+(getTime()-begintime));

        begintime = getTime();
        String expressionText = "CONCATENATE(EXACT(ISEMPTY(abc),LEFT(LEN(abc), LOWER(AACCDDbnc)))" +
                ", MID(REPLACE(CONCATENATE(a,TEXT(amount, ###,####.000),a), 3, TEXT(date, yyyy-MM-dd HH:mm:ss)," +
                " REPT(RIGHT(VALUE(1212), 2), 3)), SEARCH(SPLIT(a,TRIM(  aaa  ),c), 2, UPPER(aaa)), 3))";
        result = ValidateTool.validateByallRegex(expressionText);
        System.out.println(result.getErrMsg()+""+result.getRetCode());
        Assert.assertEquals(Result.RECODE_SUCCESS,result.getRetCode());
        System.out.println("Text :  "+(getTime()-begintime));

        begintime = getTime();
        String expressionMath = "MAX(ABS(AVERAGE(CEILING(COUNT(INT(8/9)" +
                ",FLOOR(56.878,3+-9*8/-6),SMALL(arr, 1)),2+4),SQRT(8*4))),MIN(LARGE(arr,1),LOG(MAX(1," +
                "SUM(SUMPRODUCT(arr1, arr2),2,3,SUMPRODUCT(arr1, arr2),5,6,SUMPRODUCT(arr1, arr2),8,9,10))" +
                ", MOD(SQRT(4),3)),POWER(2, PRODUCT(1,SQRT(4))),9-5),ROUND(123.456, SMALL(arr, 1)))";
        result = ValidateTool.validateByallRegex(expressionMath);
        System.out.println(result.getErrMsg()+""+result.getRetCode());
        Assert.assertEquals(Result.RECODE_SUCCESS,result.getRetCode());
        System.out.println("Math :  "+(getTime()-begintime));

        begintime = getTime();
        String expressionDate = "DATE(TIMESTAMP(DATEDELTA(DAY(MONTH(TIME(TIMESTAMP(ts), TODAY(), WEEKNUM(date" +
                ", YEAR(date))))), DAYS(HOUR(NOW()), MINUTE(SECOND(date))))))";
        result = ValidateTool.validateByallRegex(expressionDate);
        System.out.println(result.getErrMsg()+""+result.getRetCode());
        Assert.assertEquals(Result.RECODE_SUCCESS,result.getRetCode());
        System.out.println("Date :  "+(getTime()-begintime));

        begintime = getTime();
        String expression = "MAX(ABS(AVERAGE(CEILING(COUNT(INT(89/45)" +
                ",FLOOR(56+878,AND(IF(TEXT(date, yyyy-MM-dd HH:mm:ss),OR(a,TRUE(dasf,XOR(a,AND(as" +
                ",OR(asd,asd),as))),WEEK(a,s)),c),FALSE(t,NOT(false),TEXT(amount, ###,####.000)),true))" +
                ",SMALL(DATE(TIMESTAMP(DATEDELTA(DAY(MONTH(TIME(TIMESTAMP(ts), TODAY()," +
                " WEEKNUM(date, YEAR(date))))), DAYS(HOUR(NOW()), MINUTE(SECOND(date)))))), 1)),56)," +
                "SQRT(4))),MIN(LARGE(arr,UPPERMONEY(a)),LOG(MAX(1,SUM(SUMPRODUCT(arr1, arr2),2,3,SUMPRODUCT(arr1, arr2)," +
                "5,6,SUMPRODUCT(arr1, arr2),8,NUMBER(asas),10))" +
                ", MOD(SQRT(CONCATENATE(EXACT(ISEMPTY(9+-8),LEFT(LEN(9*-4), LOWER(9/1)))," +
                " MID(REPLACE(CONCATENATE(a,STRING(a),YEARS(a,u)), CONCAT(45,CONTAINS(56,89)), 3, REPT(RIGHT(VALUE(1212), 2), 3)), SEARCH(SPLIT(a," +
                "TRIM(  AND(IF(a,OR(a,TRUE(dasf,XOR(a,AND(as,OR(asd,asd),as))),g),c),FALSE(t,NOT(false),t),true)" +
                "  ),c), 2, UPPER(aaa)), 3))),3)),POWER(2, PRODUCT(1,SQRT(4))),-7),ROUND(123.456, SMALL(arr, 1)))";
        result = ValidateTool.validateByallRegex(expression);
        System.out.println(result.getErrMsg()+""+result.getRetCode());
        Assert.assertEquals(Result.RECODE_SUCCESS,result.getRetCode());
        System.out.println("All :  "+(getTime()-begintime));
    }

    public Long getTime(){
        return System.currentTimeMillis();
    }
}