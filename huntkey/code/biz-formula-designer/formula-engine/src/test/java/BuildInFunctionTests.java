import com.huntkey.rx.sceo.formula.engine.Parser;
import com.huntkey.rx.sceo.formula.engine.expression.Expression;
import com.huntkey.rx.sceo.formula.engine.function.FunctionHandlerBuildIn;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

import static org.junit.Assert.*;

/**
 * Created by chenfei on 2017/5/15.
 */
public class BuildInFunctionTests {

    private FunctionHandlerBuildIn functionHelper;
    private Parser parser;
    private MockDataProvider dataProvider = null;

    @Before
    public void setup() {
        functionHelper = new FunctionHandlerBuildIn();
        parser = new Parser(functionHelper);
        dataProvider = new MockDataProvider(new HashMap<String, Object>());
    }

    @After
    public void tearDown() {

    }

    // test logic functions.

    @Test
    public void testADD() {

        String formula = "AND(true)";
        Expression expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "AND(false)";
        expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

        formula = "AND(true, true)";
        expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "AND(true, false)";
        expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

        formula = "AND(1>2, false)";
        expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

        formula = "AND(1<2, false)";
        expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

        formula = "AND(1<2, true)";
        expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "AND(1<2, 3<4)";
        expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "AND(true, AND(1<2, 3<4))";
        expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());


        formula = "AND(1<2, var1)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("var1", true);
        assertTrue(expr.getValue(dataProvider).getBoolean());

        formula = "AND(1>2, var2)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("var2", true);
        assertFalse(expr.getValue(dataProvider).getBoolean());

        formula = "AND(1>2, var3)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("var3", "3 > 4");
        assertFalse(expr.getValue(dataProvider).getBoolean());
    }

    @Test
    public void testFALSE() {

        String formula = "FALSE(true)";
        Expression expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

        formula = "FALSE('abc')";
        expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

        formula = "FALSE(var4)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("var4", "3 > 4");
        assertFalse(expr.getValue(dataProvider).getBoolean());

    }

    @Test
    public void testIF() {
        String formula = "IF(true, 'abc', 'def')";
        Expression expr = parser.parse(formula);
        assertEquals("abc", expr.getValue(null).getString());

        formula = "IF(true, 1 + 2, 3 + 4)";
        expr = parser.parse(formula);
        assertEquals(3, expr.getValue(null).getLong());

        formula = "IF(false, 1 + 2, 3 + 4)";
        expr = parser.parse(formula);
        assertEquals(7, expr.getValue(null).getLong());

        formula = "IF(1 > 2, 1 + 2, 3 + 4)";
        expr = parser.parse(formula);
        assertEquals(7, expr.getValue(null).getLong());

        formula = "IF(1 > 2, 1 + 2, var5)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("var5", 9);
        assertEquals(9, expr.getValue(dataProvider).getInt());

        formula = "IF(AND(1 > 2, 3 > 4), 1 + 2, var5)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("var5", 9);
        assertEquals(9, expr.getValue(dataProvider).getInt());
    }

    @Test
    public void testNOT() {
        Parser parser = new Parser(functionHelper);
        String formula = "NOT(true)";
        Expression expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

        formula = "NOT(false)";
        expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "NOT(1 > 2)";
        expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "NOT(var6)";
        dataProvider.getDataContext().put("var6", false);
        expr = parser.parse(formula);
        assertTrue(expr.getValue(dataProvider).getBoolean());
    }

    @Test
    public void testOR() {
        Parser parser = new Parser(functionHelper);
        String formula = "OR(false, false, true)";
        Expression expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "OR(1>2, 3>4, 5>6)";
        expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

        formula = "OR(1>2, var7, 5>6)";
        dataProvider.getDataContext().put("var7", true);
        expr = parser.parse(formula);
        assertTrue(expr.getValue(dataProvider).getBoolean());

    }

    @Test
    public void testTRUE() {

        String formula = "TRUE(true)";
        Expression expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "TRUE('abc')";
        expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "TRUE(var8)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("var4", "3 > 4");
        assertTrue(expr.getValue(dataProvider).getBoolean());

    }

    @Test
    public void testXOR() {
        String formula = "XOR(true, false)";
        Expression expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

        formula = "XOR(false, false)";
        expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "XOR(false, true)";
        expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

    }

    // test text functions.

//    @Test
//    public void testCONCATENATE() {
//        String formula = "CONCATENATE(123, 456)";
//        Expression expr = parser.parse(formula);
//        assertEquals("123456", expr.getValue(null).getString());
//
//        formula = "CONCATENATE(123, '456')";
//        expr = parser.parse(formula);
//        assertEquals("123456", expr.getValue(null).getString());
//
//        formula = "CONCATENATE(123, '456', abc)";
//        expr = parser.parse(formula);
//        dataProvider.getDataContext().put("abc", 789);
//        assertEquals("123456789", expr.getValue(dataProvider).getString());
//
//        formula = "CONCATENATE(123)";
//        expr = parser.parse(formula);
//        assertEquals("123", expr.getValue(dataProvider).getString());
//    }

    @Test
    public void testEXACT() {
        String formula = "EXACT('123', '456')";
        Expression expr = parser.parse(formula);
        assertFalse(expr.getValue(null).getBoolean());

        formula = "EXACT('123', '123')";
        expr = parser.parse(formula);
        assertTrue(expr.getValue(null).getBoolean());

        formula = "EXACT('123', abc)";
        dataProvider.getDataContext().put("abc", "123");
        expr = parser.parse(formula);

        assertTrue(expr.getValue(dataProvider).getBoolean());
    }

    @Test
    public void testISEMPTY() {
        String formula = "ISEMPTY(abc)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("abc", null);
        assertTrue(expr.getValue(dataProvider).getBoolean());

        formula = "ISEMPTY(def)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("def", new double[0]);
        assertFalse(expr.getValue(dataProvider).getBoolean());

        formula = "ISEMPTY(ghj)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("ghj", new String[0]);
        assertTrue(expr.getValue(dataProvider).getBoolean());

        formula = "ISEMPTY(kl)";
        expr = parser.parse(formula);
        String[] arr = new String[10];
        arr[4] = "1";
        dataProvider.getDataContext().put("kl", arr);
        assertFalse(expr.getValue(dataProvider).getBoolean());

    }

    @Test
    public void testLEFT() {
        String formula = "LEFT('123456', 5)";
        Expression expr = parser.parse(formula);
        assertEquals("12345", expr.getValue(null).getString());

        formula = "LEFT('123456', 2)";
        expr = parser.parse(formula);
        assertEquals("12", expr.getValue(null).getString());

        formula = "LEFT('123456', 0)";
        expr = parser.parse(formula);
        assertEquals("", expr.getValue(null).getString());
    }

    @Test
    public void testLEN() {
        String formula = "LEN('123456')";
        Expression expr = parser.parse(formula);
        assertEquals(6, expr.getValue(null).getInt());

        formula = "LEN(LEFT('123456', 5))";
        expr = parser.parse(formula);
        assertEquals(5, expr.getValue(null).getInt());

        formula = "LEN(LEFT('123456', LEN('123')))";
        expr = parser.parse(formula);
        assertEquals(3, expr.getValue(null).getInt());
    }

    @Test
    public void testLOWER() {
        String formula = "LOWER('AACCDDbnc')";
        Expression expr = parser.parse(formula);
        assertEquals("aaccddbnc", expr.getValue(null).getString());

        formula = "LOWER(LEFT('ABCDFE', 3))";
        expr = parser.parse(formula);
        assertEquals("abc", expr.getValue(null).getString());
    }

    @Test
    public void testMID() {
        String formula = "MID('abcdefg', 3, 3)";
        Expression expr = parser.parse(formula);
        assertEquals("cde", expr.getValue(null).getString());
    }

    @Test
    public void testREPLACE() {
        String formula = "REPLACE('abcdefg', 3, 3, 'AAA')";
        Expression expr = parser.parse(formula);
        assertEquals("abAAAfg", expr.getValue(null).getString());

        formula = "REPLACE('abcdefg', 3, 5, 'AAA')";
        expr = parser.parse(formula);
        assertEquals("abAAA", expr.getValue(null).getString());
    }

    @Test
    public void testREPT() {
        String formula = "REPT('abcdefg', 3)";
        Expression expr = parser.parse(formula);
        assertEquals("abcdefgabcdefgabcdefg", expr.getValue(null).getString());
    }

    @Test
    public void testRIGHT() {
        String formula = "RIGHT('123456', 5)";
        Expression expr = parser.parse(formula);
        assertEquals("23456", expr.getValue(null).getString());

        formula = "RIGHT('123456', 2)";
        expr = parser.parse(formula);
        assertEquals("56", expr.getValue(null).getString());

        formula = "RIGHT('123456', 0)";
        expr = parser.parse(formula);
        assertEquals("", expr.getValue(null).getString());
    }

    @Test
    public void testSEARCH() {
        String formula = "SEARCH('123456', '5')";
        Expression expr = parser.parse(formula);
        assertEquals(5, expr.getValue(null).getInt());

        formula = "SEARCH('123456', '2', 4)";
        expr = parser.parse(formula);
        assertEquals(-1, expr.getValue(null).getInt());
    }

    @Test
    public void testSPLIT() {
        String formula = "SPLIT('1,2,3,4,5,6', ',', 4)";
        Expression expr = parser.parse(formula);
        assertEquals(4, expr.getValue(null).getInt());
    }

    @Test
    public void testTEXT() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 4, 17, 13, 05, 16);

        String formula = "TEXT(date, 'yyyy-MM-dd HH:mm:ss')";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        assertEquals("2017-05-17 13:05:16", expr.getValue(dataProvider).getOriginValue());

        formula = "TEXT(amount, '###,####.000')";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("amount", 123456789);
        assertEquals("1,2345,6789.000", expr.getValue(dataProvider).getString());
    }

    @Test
    public void testTRIM() {
        String formula = "TRIM('    aaa    ')";
        Expression expr = parser.parse(formula);
        assertEquals("aaa", expr.getValue(null).getString());
    }

    @Test
    public void testUPPER() {
        String formula = "UPPER(TRIM('    aaa    '))";
        Expression expr = parser.parse(formula);
        assertEquals("AAA", expr.getValue(null).getString());
    }

    @Test
    public void testVALUE() {
        String formula = "VALUE(TRIM('    123456    '))";
        Expression expr = parser.parse(formula);
        assertEquals(123456, expr.getValue(null).getLong());
    }

    // test math functions.

    @Test
    public void testABS() {
        String formula = "ABS(VALUE(TRIM('    -123456    ')))";
        Expression expr = parser.parse(formula);
        assertEquals(123456, expr.getValue(null).getLong());

        formula = "ABS(321)";
        expr = parser.parse(formula);
        assertEquals(321, expr.getValue(null).getLong());
    }

//    @Test
//    public void testAVERAGE() {
//        String formula = "AVERAGE(1, 2, 3)";
//        Expression expr = parser.parse(formula);
//        assertEquals(2, expr.getValue(null).getLong());
//
//        formula = "AVERAGE(1, 2, 3, 5, 7, 9, 0, 0, 0)";
//        expr = parser.parse(formula);
//        assertEquals(3, expr.getValue(null).getLong());
//
//        formula = "AVERAGE(1, 2, 3, 5, 7, 9, 0, -1, 1)";
//        expr = parser.parse(formula);
//        assertEquals(3, expr.getValue(null).getLong());
//    }

    @Test
    public void testCEILING() {
        String formula = "CEILING(10, 3)";
        Expression expr = parser.parse(formula);
        assertEquals(12, expr.getValue(null).getLong());

        formula = "CEILING(-10, 3)";
        expr = parser.parse(formula);
        assertEquals(-9, expr.getValue(null).getLong());
    }

    @Test
    public void testCOUNT() {
        String formula = "COUNT(10, 3, '', 4, 'abc')";
        Expression expr = parser.parse(formula);
        assertEquals(5, expr.getValue(null).getLong());
    }

    @Test
    public void testFIXED() {
        String formula = "FIXED(123456.789, 2)";
        Expression expr = parser.parse(formula);
        assertEquals("123456.79", expr.getValue(null).getString());

        formula = "FIXED(123456.789)";
        expr = parser.parse(formula);
        assertEquals("123456.789", expr.getValue(null).getString());

        formula = "ABS(FIXED( 20.26498, 4))";
        expr = parser.parse(formula);
        System.out.println(expr.getValue(null).getString());
    }

    @Test
    public void testScale() {
        DecimalFormat df   = new DecimalFormat("#######.0000");
        String s = df.format(10.10104d) ;
        System.out.println(s);
    }

    @Test
    public void testFLOOR() {
        String formula = "FLOOR(10, 3)";
        Expression expr = parser.parse(formula);
        assertEquals(9, expr.getValue(null).getLong());

        formula = "FLOOR(-10, 3)";
        expr = parser.parse(formula);
        assertEquals(-12, expr.getValue(null).getLong());
    }

    @Test
    public void testINT() {
        String formula = "INT(100.99)";
        Expression expr = parser.parse(formula);
        assertEquals(100, expr.getValue(null).getLong());

        formula = "INT(prop_42123f81b05d4866974a1bc9fdd2f1da_6b3fb4b13cc14b0894f96815fda9d2f5_test.test004)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("prop_42123f81b05d4866974a1bc9fdd2f1da_6b3fb4b13cc14b0894f96815fda9d2f5_test.test004", 3);
        System.out.println(expr.getValue(dataProvider));

    }

    @Test
    public void testLARGE() {
        String formula = "LARGE(arr, 1)";
        Expression expr = parser.parse(formula);
        double[] arr = new double[10];
        for (int i=0; i<arr.length; i++) {
            arr[i] = i % 4;
        }
        dataProvider.getDataContext().put("arr", arr);
        assertEquals(3, expr.getValue(dataProvider).getLong());
    }

    @Test
    public void testLOG() {
        String formula = "LOG(2, 1024)";
        Expression expr = parser.parse(formula);
        assertEquals(10, expr.getValue(null).getLong());
    }

    @Test
    public void testMAX() {
        String formula = "MAX(123, 456, 789)";
        Expression expr = parser.parse(formula);
        assertEquals(789, expr.getValue(null).getLong());

        formula = "MAX(123, 456, abc)";
        dataProvider.getDataContext().put("abc", 1000);
        expr = parser.parse(formula);
        assertEquals(1000, expr.getValue(dataProvider).getLong());

    }

    @Test
    public void testMIN() {
        String formula = "MIN(0.6, 1, 9)";
        Expression expr = parser.parse(formula);
        System.out.println(expr.getValue(dataProvider).getDouble());


        formula = "MIN(123, 456, abc)";
        dataProvider.getDataContext().put("abc", 1000);
        expr = parser.parse(formula);
        assertEquals(123, expr.getValue(dataProvider).getLong());

    }

    @Test
    public void testMOD() {
        String formula = "MOD(123, 7)";
        Expression expr = parser.parse(formula);
        assertEquals(4, expr.getValue(null).getLong());
    }

    @Test
    public void testPOWER() {
        String formula = "POWER(2, 10)";
        Expression expr = parser.parse(formula);
        assertEquals(1024, expr.getValue(null).getLong());
    }

    @Test
    public void testPRODUCT() {
        String formula = "PRODUCT(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)";
        Expression expr = parser.parse(formula);
        assertEquals(3628800, expr.getValue(dataProvider).getLong());
    }

    @Test
    public void testROUND() {
        String formula = "ROUND(123.456, 2)";
        Expression expr = parser.parse(formula);
        assertEquals("123.46", expr.getValue(dataProvider).getString());
    }

    @Test
    public void testSMALL() {
        String formula = "SMALL(arr, 1)";
        Expression expr = parser.parse(formula);
        double[] arr = new double[10];
        for (int i=0; i<arr.length; i++) {
            arr[i] = i % 4;
        }
        dataProvider.getDataContext().put("arr", arr);
        assertEquals(1, expr.getValue(dataProvider).getLong());
    }

    @Test
    public void testSQRT() {
        String formula = "SQRT(4)";
        Expression expr = parser.parse(formula);
        assertEquals(2, expr.getValue(null).getLong());
    }

    @Test
    public void testSUM() {
        String formula = "SUM(1,2,3,4,5,6,7,8,9,10)";
        Expression expr = parser.parse(formula);
        assertEquals(55, expr.getValue(null).getLong());
    }

    @Test
    public void testSUMPRODUCT() {
        String formula = "SUMPRODUCT(arr1, arr2)";
        double[] arr1 = new double[3];
        for (int i = 0; i < arr1.length; i++) {
            arr1[i] = i;
        }
        double[] arr2 = new double[3];
        for (int i = 0; i < arr2.length; i++) {
            arr2[i] = i*1d/10;
        }

        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("arr1", arr1);
        dataProvider.getDataContext().put("arr2", arr2);

        assertEquals("0.5", expr.getValue(dataProvider).getString());
    }

    // test date functions.

    @Test
    public void testDATE() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "DATE(TIMESTAMP(ts))";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("ts", calendar.getTime());
        assertEquals(calendar.getTimeInMillis(), ((Date) expr.getValue(dataProvider).getOriginValue()).getTime());
    }

    @Test
    public void testTODATE() {

        String formula = "TODATE('2017-8-09')";
        Expression expr = parser.parse(formula);
        System.out.println(expr.getValue(dataProvider).getOriginValue());

        formula = "TODATE(dateObj)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("dateObj", new Date());
        System.out.println(expr.getValue(dataProvider).getOriginValue());
    }

    @Test
    public void testDATEDELTA() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "DATEDELTA(date, 1)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        assertEquals(calendar.getTimeInMillis(), ((Date) expr.getValue(dataProvider).getOriginValue()).getTime());
    }

    @Test
    public void testDAY() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "DAY(date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        assertEquals(17, expr.getValue(dataProvider).getInt());
    }

    @Test
    public void testDAYS() {
        Calendar calendar1 = Calendar.getInstance();
        calendar1.set(2017, 06, 9, 15, 20, 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2017, 10, 1, 15, 20, 1);

        String formula = "DAYS(date2, date1)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date1", calendar1.getTime());
        dataProvider.getDataContext().put("date2", calendar2.getTime());
        System.out.println(expr.getValue(dataProvider).getInt());
        assertEquals(115, expr.getValue(dataProvider).getInt());
    }

    @Test
    public void testHOUR() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "HOUR(date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        assertEquals(15, expr.getValue(dataProvider).getInt());
    }

    @Test
    public void testMINUTE() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "MINUTE(date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        assertEquals(20, expr.getValue(dataProvider).getInt());
    }

    @Test
    public void testMONTH() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "MONTH(date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        assertEquals(5, expr.getValue(dataProvider).getInt());
    }

    @Test
    public void testNOW() {

        String formula = "NOW()";
        Expression expr = parser.parse(formula);
        System.out.println(expr.getValue(null).getOriginValue());
        assertNotNull(expr.getValue(null).getOriginValue());
    }

    @Test
    public void testSECOND() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "SECOND(date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        assertEquals(1, expr.getValue(dataProvider).getInt());
    }

    @Test
    public void testTIME() {
        String formula = "TIME(12, 0, 0)";
        Expression expr = parser.parse(formula);
        assertEquals("0.5", expr.getValue(null).getString());
    }

    @Test
    public void testTIMESTAMP() {

        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);
        System.out.println(calendar.getTime());
        String formula = "TIMESTAMP(ts)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("ts", calendar.getTime());
        assertEquals(calendar.getTimeInMillis(), expr.getValue(dataProvider).getLong());
    }

    @Test
    public void testTODAY() {
        String formula = "TODAY()";
        Expression expr = parser.parse(formula);
        System.out.println(expr.getValue(null).getOriginValue());
        assertNotNull(expr.getValue(null).getOriginValue());
    }

    @Test
    public void testWEEKNUM() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "WEEKNUM(date, 1)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        assertEquals(22, expr.getValue(dataProvider).getInt());
    }

    @Test
    public void testYEAR() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "YEAR(date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        assertEquals(2017, expr.getValue(dataProvider).getInt());
    }

    @Test
    public void testDAYADD() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "DAYADD(date, 1)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        Long timeMills = ((Date) expr.getValue(dataProvider).getOriginValue()).getTime();
        Calendar calAdded = Calendar.getInstance();
        calAdded.setTimeInMillis(timeMills);

        assertEquals(18, calAdded.get(Calendar.DAY_OF_MONTH));
    }

    @Test
    public void testHOURADD() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "HOURADD(date, 1)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        Long timeMills = ((Date) expr.getValue(dataProvider).getOriginValue()).getTime();
        Calendar calAdded = Calendar.getInstance();
        calAdded.setTimeInMillis(timeMills);

        assertEquals(16, calAdded.get(Calendar.HOUR_OF_DAY));
    }

    @Test
    public void testHOURS() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2017, 04, 18, 20, 20, 1);

        String formula = "HOURS(date2, date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        dataProvider.getDataContext().put("date2", calendar2.getTime());
        Long hours = expr.getValue(dataProvider).getLong();

        assertEquals(29L, hours.longValue());
    }

    @Test
    public void testMINUTEADD() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "MINUTEADD(date, 1)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        Long timeMills = ((Date) expr.getValue(dataProvider).getOriginValue()).getTime();
        Calendar calAdded = Calendar.getInstance();
        calAdded.setTimeInMillis(timeMills);

        assertEquals(21, calAdded.get(Calendar.MINUTE));
    }

    @Test
    public void testMINUTES() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2017, 04, 18, 20, 20, 1);

        String formula = "MINUTES(date2, date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        dataProvider.getDataContext().put("date2", calendar2.getTime());
        Long minutes = expr.getValue(dataProvider).getLong();

        assertEquals(29L * 60, minutes.longValue());
    }

    @Test
    public void testMONTHADD() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "MONTHADD(date, 1)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        Long timeMills = ((Date) expr.getValue(dataProvider).getOriginValue()).getTime();
        Calendar calAdded = Calendar.getInstance();
        calAdded.setTimeInMillis(timeMills);

        assertEquals(5, calAdded.get(Calendar.MONTH));
    }

    @Test
    public void testMONTHS() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2017, 07, 18, 20, 20, 1);

        String formula = "MONTHS(date2, date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        dataProvider.getDataContext().put("date2", calendar2.getTime());
        Long months = expr.getValue(dataProvider).getLong();

        assertEquals(3L, months.longValue());
    }

    @Test
    public void testSECONDADD() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "SECONDADD(date, 1)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        Long timeMills = ((Date) expr.getValue(dataProvider).getOriginValue()).getTime();
        Calendar calAdded = Calendar.getInstance();
        calAdded.setTimeInMillis(timeMills);

        assertEquals(2, calAdded.get(Calendar.SECOND));
    }

    @Test
    public void testSECONDS() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2017, 04, 18, 20, 20, 1);

        String formula = "SECONDS(date2, date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        dataProvider.getDataContext().put("date2", calendar2.getTime());
        Long seconds = expr.getValue(dataProvider).getLong();

        assertEquals(29 * 60 * 60 , seconds.longValue());
    }

    @Test
    public void testWEEK() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "WEEK(date, 1)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        assertEquals(22, expr.getValue(dataProvider).getInt());
    }

    @Test
    public void testYEARADD() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        String formula = "YEARADD(date, 1)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        Long timeMills = ((Date) expr.getValue(dataProvider).getOriginValue()).getTime();
        Calendar calAdded = Calendar.getInstance();
        calAdded.setTimeInMillis(timeMills);

        assertEquals(2018, calAdded.get(Calendar.YEAR));
    }

    @Test
    public void testYEARS() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(2017, 04, 17, 15, 20, 1);

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2018, 04, 18, 20, 20, 1);

        String formula = "YEARS(date2, date)";
        Expression expr = parser.parse(formula);
        dataProvider.getDataContext().put("date", calendar.getTime());
        dataProvider.getDataContext().put("date2", calendar2.getTime());
        Long seconds = expr.getValue(dataProvider).getLong();

        assertEquals(1 , seconds.longValue());
    }

    @Test
    public void testAVG() {
        String formula = "AVG(1, 2, 3)";
        Expression expr = parser.parse(formula);
        assertEquals(2, expr.getValue(null).getLong());

        formula = "AVG(1, 2, 3, 5, 7, 9, 0, 0, 0)";
        expr = parser.parse(formula);
        assertEquals(3, expr.getValue(null).getLong());

        formula = "AVG(1, 2, 3, 5, 7, 9, 0, -1, 1)";
        expr = parser.parse(formula);
        assertEquals(3, expr.getValue(null).getLong());

        formula = "AVG(1, 2, abc)";
        dataProvider.getDataContext().put("abc", new double[]{1, 2, 4});
        expr = parser.parse(formula);
        assertEquals(2, expr.getValue(dataProvider).getLong());
    }

    @Test
    public void testCONCAT() {
        String formula = "CONCAT(123, 456)";
        Expression expr = parser.parse(formula);
        assertEquals("123456", expr.getValue(null).getString());

        formula = "CONCAT(123, '456')";
        expr = parser.parse(formula);
        assertEquals("123456", expr.getValue(null).getString());

        formula = "CONCAT(123, '456', abc)";
        expr = parser.parse(formula);
        dataProvider.getDataContext().put("abc", 789);
        assertEquals("123456789", expr.getValue(dataProvider).getString());

        formula = "CONCAT(123)";
        expr = parser.parse(formula);
        assertEquals("123", expr.getValue(dataProvider).getString());
    }

    @Test
    public void testNUMBER() {

        String formula = "NUMBER('1000')";
        Expression expr = parser.parse(formula);
        assertEquals("1000", expr.getValue(null).getString());

        formula = "NUMBER('1000.001')";
        expr = parser.parse(formula);
        assertEquals("1000.001", expr.getValue(null).getString());

    }

    @Test
    public void testSTRING() {

        String formula = "STRING(1000)";
        Expression expr = parser.parse(formula);
        assertEquals("1000", expr.getValue(null).getString());

        formula = "STRING('1000.001')";
        expr = parser.parse(formula);
        assertEquals("1000.001", expr.getValue(null).getString());

    }

    @Test
    public void testUPPERMONEY() {

        String formula = "UPPERMONEY(1000)";
        Expression expr = parser.parse(formula);
        System.out.println(expr.getValue(null).getString());
//        assertEquals("1000", expr.getValue(null).getString());

        formula = "UPPERMONEY('1000.1')";
        expr = parser.parse(formula);
        System.out.println(expr.getValue(null).getString());
//        assertEquals("1000.001", expr.getValue(null).getString());

    }

//    @Test
//    public void testUnaryOperator() {
//        String formula = "1 != 'abc'";
//        Expression expr = parser.parse(formula);
//        System.out.println(expr.getValue(null).getOriginValue().toString());
//
//        formula = "'a' != 'a'";
//        expr = parser.parse(formula);
//
//        System.out.println(expr.getValue(null).getOriginValue().toString());
//    }
}
