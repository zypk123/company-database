package com.huntkey.rx.purchase.provider.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.huntkey.rx.commons.utils.datetime.DateUtil;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.commons.utils.string.StringUtil;
import com.huntkey.rx.edm.constant.*;
import com.huntkey.rx.edm.entity.*;
import com.huntkey.rx.purchase.common.constants.OrderConstants;
import com.huntkey.rx.purchase.common.exception.ApplicationException;
import com.huntkey.rx.purchase.common.model.puquoteorder.PuqoOrderQueryDTO;
import com.huntkey.rx.purchase.common.util.JsonUtils;
import com.huntkey.rx.purchase.common.util.NullUtils;
import com.huntkey.rx.purchase.provider.PurchaseProviderApplication;
import com.huntkey.rx.sceo.orm.common.model.OrmParam;
import com.huntkey.rx.sceo.orm.common.model.OrmParamEx;
import com.huntkey.rx.sceo.orm.common.type.DataVailidEnum;
import com.huntkey.rx.sceo.orm.common.type.SQLSortEnum;
import com.huntkey.rx.sceo.orm.service.OrmService;
import com.huntkey.rx.sceo.serviceCenter.common.model.NodeConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = PurchaseProviderApplication.class)
public class PurchaseQuoteOrderServiceTest {

    @Autowired
    PurchaseQuoteOrderService purchaseQuoteOrderService;

    @Autowired
    OrmService ormService;

    /**
     * 这个例子 演示 如何 使用 left join
     * SELECT remp_name, mdep_name, oepc_emp, rpos_name, orde_nbr
     * , oepc_dept, oepc_post, emppostchangeapply.id AS emppostchangeapply_id, remp_no
     * FROM emppostchangeapply
     * LEFT JOIN oepc_oepc_chang_seta ON emppostchangeapply.id = oepc_oepc_chang_seta.pid
     * LEFT JOIN employee ON employee.id = oepc_oepc_chang_seta.oepc_emp
     * LEFT JOIN depttree ON depttree.id = oepc_oepc_chang_seta.oepc_dept
     * LEFT JOIN jobposition ON jobposition.id = oepc_oepc_chang_seta.oepc_post
     * WHERE orde_status = ?
     * AND oepc_type = ?
     * AND oepc_oepc_chang_seta.is_del = 0
     * AND depttree.is_del = 0
     * AND jobposition.is_del = 0
     * AND employee.is_del = 0
     * LIMIT 10
     * <p>
     * 结果集：
     * {
     * "remp_name":"李蛋",
     * "mdep_name":"嘉源锐信",
     * "oepc_emp":"66531ddba97842748cdcdc2eaed12208",
     * "rpos_name":"911测试岗位",
     * "orde_nbr":"HR12-18010022",
     * "emppostchangeapply_id":"f4cdd5a841d041fda99bb11c69f3bbf8",
     * "oepc_dept":"8f35db44cf7c11e7bbd9005056b7c0c3",
     * "oepc_post":"08fa0527953347cb804e63db9611a469",
     * "remp_no":"18010021"
     * }
     * {
     * "remp_name":"张余生",
     * "mdep_name":"报表与信息安全处",
     * "oepc_emp":"91cda610f57142adb4f0705da84b1be8",
     * "rpos_name":"高级程序员94",
     * "orde_nbr":"HR12-18010003",
     * "emppostchangeapply_id":"80d81c169cf44d0d940e4244a36d30e4",
     * "oepc_dept":"c0348c1bcf7c11e7bbd9005056b7c0c3",
     * "oepc_post":"65c2f50aeb7111e7bbd9005056b7c0c3",
     * "remp_no":"18010013"
     * }
     * {
     * "remp_name":"孙明炳",
     * "mdep_name":"航嘉应用实验室",
     * "oepc_emp":"c4032b3bd5a211e7bbd9005056b7c0c3",
     * "rpos_name":"实施工程师58",
     * "orde_nbr":"HR12-18010012",
     * "emppostchangeapply_id":"a01b10d0524a4abda07074498e2648ba",
     * "oepc_dept":"c0346f96cf7c11e7bbd9005056b7c0c3",
     * "oepc_post":"9219a01cea1c11e7bbd9005056b7c0c3",
     * "remp_no":"10050027"
     * }
     * {
     * "remp_name":"赖远芳",
     * "mdep_name":"管理模型研究室",
     * "oepc_emp":"c4032e48d5a211e7bbd9005056b7c0c3",
     * "rpos_name":"系统分析师21",
     * "orde_nbr":"HR12-18010007",
     * "emppostchangeapply_id":"04563f037ca04707aab30b567278cbf7",
     * "oepc_dept":"c034677ecf7c11e7bbd9005056b7c0c3",
     * "oepc_post":"65c29d40eb7111e7bbd9005056b7c0c3",
     * "remp_no":"08070002"
     * }
     * {
     * "remp_name":"孙悟饭",
     * "mdep_name":"项目管理处",
     * "oepc_emp":"31c9aaedaa444c9785207a682e3946e3",
     * "rpos_name":"项目管理工程师10",
     * "orde_nbr":"HR12-18010025",
     * "emppostchangeapply_id":"039ebe6ba8ec4daf924e974a065f4a00",
     * "oepc_dept":"c0347558cf7c11e7bbd9005056b7c0c3",
     * "oepc_post":"55876528ea1d11e7bbd9005056b7c0c3",
     * "remp_no":"18010026"
     * }
     * {
     * "remp_name":"马献玲",
     * "mdep_name":"管理模型研究室",
     * "oepc_emp":"c403449bd5a211e7bbd9005056b7c0c3",
     * "rpos_name":"系统分析师20",
     * "orde_nbr":"HR12-18010014",
     * "emppostchangeapply_id":"fa05553263a5456db7bea4d7b5ed0be2",
     * "oepc_dept":"c034677ecf7c11e7bbd9005056b7c0c3",
     * "oepc_post":"65c29a45eb7111e7bbd9005056b7c0c3",
     * "remp_no":"11090267"
     * }
     * {
     * "remp_name":"马献玲",
     * "mdep_name":"管理模型研究室",
     * "oepc_emp":"c403449bd5a211e7bbd9005056b7c0c3",
     * "rpos_name":"系统分析师12-1",
     * "orde_nbr":"HR12-18010010",
     * "emppostchangeapply_id":"2c61e9130a094fa58c890eb06bcb102f",
     * "oepc_dept":"c034677ecf7c11e7bbd9005056b7c0c3",
     * "oepc_post":"65c1bb7111e7bbd900505tdy21c0c3",
     * "remp_no":"11090267"
     * }
     * {
     * "remp_name":"陈雄",
     * "mdep_name":"航嘉应用实验室",
     * "oepc_emp":"c40349bad5a211e7bbd9005056b7c0c3",
     * "rpos_name":"实施工程师64",
     * "orde_nbr":"HR12-18010019",
     * "emppostchangeapply_id":"f6d92f1ae9694bb1acb4dcd64ca828b6",
     * "oepc_dept":"c0346f96cf7c11e7bbd9005056b7c0c3",
     * "oepc_post":"9219abbdea1c11e7bbd9005056b7c0c3",
     * "remp_no":"16030740"
     * }
     * {
     * "remp_name":"李蛋",
     * "mdep_name":"嘉源锐信",
     * "oepc_emp":"66531ddba97842748cdcdc2eaed12208",
     * "rpos_name":"狗蛋的岗位",
     * "orde_nbr":"HR12-18010011",
     * "emppostchangeapply_id":"5eb7956c99be4f3e9147b135e5335276",
     * "oepc_dept":"8f35db44cf7c11e7bbd9005056b7c0c3",
     * "oepc_post":"57dfb3ea1d4048f59b81ffd85040f116",
     * "remp_no":"18010021"
     * }
     *
     * @throws Exception
     */

    @Test
    public void testLeftJoin() throws Exception {

        OrmParamEx ormParamEx = new OrmParamEx();
        ormParamEx.addColumn(OrmParamEx.column(EmppostchangeapplyEntity.class, NodeConstant.ID))
                .addColumn(OrderConstants.ORDE_NBR).addColumn(OepcOepcChangSetaProperty.OEPC_DEPT).addColumn("oepc_emp").addColumn("oepc_post")
                .addColumn("remp_name").addColumn("employee.remp_no").addColumn("mdep_name").addColumn("rpos_name as rposName");

//        ormParamEx.leftJoin(OepcOepcChangSetaEntity.class, OrmParamEx.joinLinkInDifferentTable(EmppostchangeapplyEntity.class, NodeConstant.ID, OepcOepcChangSetaEntity.class, NodeConstant.PID))
//                .leftJoin(EmployeeEntity.class, OrmParamEx.joinLinkInDifferentTable(EmployeeEntity.class, NodeConstant.ID, OepcOepcChangSetaEntity.class, "oepc_emp"))
//                .leftJoin(DepttreeEntity.class, OrmParamEx.joinLinkInDifferentTable(DepttreeEntity.class, NodeConstant.ID, OepcOepcChangSetaEntity.class, OepcOepcChangSetaProperty.OEPC_DEPT))
//                .leftJoin(JobpositionEntity.class, OrmParamEx.joinLinkInDifferentTable(JobpositionEntity.class, NodeConstant.ID, OepcOepcChangSetaEntity.class, "oepc_post"))
//        ;
        ormParamEx.setWhereExp(OrmParamEx.and(ormParamEx.getEqualXML(OrderConstants.ORDE_STATUS, OrderConstants.ORDE_STATUS_5), ormParamEx.getEqualXML(EmppostchangeapplyProperty.OEPC_TYPE, "0")));

        PageHelper.startPage(1, 10, false);
        List<Map<String, Object>> list = ormService.selectMapListEx(EmppostchangeapplyEntity.class, ormParamEx);

        if (null != list && list.size() > 0) {
            for (Map<String, Object> map : list) {
                System.out.println(JSON.toJSONString(map, true));
            }
        }

    }

    @Test
    public void testLoadPuquoteorderInfo() throws Exception {
        /**
         * -- 单据id,制单人，制单日期，制单部门，制单岗位，单据单号,单据定义id
         -- 制单人工号，制单人名字，制单部门名字，制单岗位名字
         -- 供应商id，供应商简称
         -- 币别id，币别code，币别名称
         -- 税率id,税率名称，税率code
         -- 备注
         SELECT puquoteorder.id as puquoteorder_id, orde_adduser,orde_date,orde_dept,orde_duty,orde_nbr,orde_rode_obj,
         remp_no as orde_adduser_no,remp_name as orde_adduser_name, mdep_name as orde_dept_name, rpos_name as orde_duty_name,
         puqo_code_supp,puqo_sname_supp,
         puqo_curr, currency.info_code as puqo_curr_code,currency.info_name as puqo_curr_name,
         puqo_tax,taxrate.info_code as puqo_tax_code,taxrate.info_name as puqo_tax_name,
         puqo_remark
         from puquoteorder as puquoteorder
         LEFT JOIN employee
         ON employee.id=puquoteorder.orde_adduser
         LEFT JOIN depttree
         ON depttree.id=puquoteorder.orde_dept
         LEFT JOIN jobposition
         ON jobposition.id=puquoteorder.orde_duty
         LEFT JOIN currency
         ON currency.id = puquoteorder.puqo_curr
         LEFT JOIN taxrate
         ON taxrate.id=puquoteorder.puqo_tax

         ==>
         生成的SQL

         SELECT puqo_sname_supp, currency.info_code AS currency_info_code, puqo_curr, orde_dept, orde_adduser
         , puqo_code_supp, orde_nbr, taxrate.info_name AS taxrate_info_name, orde_rode_obj, orde_date
         , puqo_remark, remp_name, puquoteorder.id AS puquoteorder_id, mdep_name, rpos_name
         , orde_duty, taxrate.info_code AS taxrate_info_code, puqo_tax, currency.info_name AS currency_info_name, remp_no
         FROM puquoteorder
         LEFT JOIN employee ON employee.id = puquoteorder.orde_adduser
         LEFT JOIN depttree ON depttree.id = puquoteorder.orde_dept
         LEFT JOIN jobposition ON jobposition.id = puquoteorder.orde_duty
         LEFT JOIN currency ON currency.id = puquoteorder.puqo_curr
         LEFT JOIN taxrate ON taxrate.id = puquoteorder.puqo_tax
         WHERE puquoteorder.id = ?
         AND depttree.is_del = 0
         AND taxrate.is_del = 0
         AND jobposition.is_del = 0
         AND currency.is_del = 0
         AND employee.is_del = 0

         */
        OrmParamEx ormParamEx = new OrmParamEx();
        ormParamEx.addColumn(OrmParamEx.column(PuquoteorderEntity.class, NodeConstant.ID))
                .addColumn(OrderProperty.ORDE_ADDUSER)
                .addColumn(OrderProperty.ORDE_DATE)
                .addColumn(OrderProperty.ORDE_DEPT)
                .addColumn(OrderProperty.ORDE_DUTY)
                .addColumn(OrderProperty.ORDE_NBR)
                .addColumn(OrderProperty.ORDE_RODE_OBJ)
                .addColumn(EmployeeProperty.REMP_NO)
                .addColumn(EmployeeProperty.REMP_NAME)
                .addColumn(DepttreeProperty.MDEP_NAME)
                .addColumn(JobpositionProperty.RPOS_NAME)
                .addColumn(PuquoteorderProperty.PUQO_CODE_SUPP)
                .addColumn(PuquoteorderProperty.PUQO_SNAME_SUPP)
                .addColumn(PuquoteorderProperty.PUQO_CURR)
                .addColumn(OrmParamEx.column(CurrencyEntity.class, InformationProperty.INFO_NAME))
                .addColumn(OrmParamEx.column(CurrencyEntity.class, InformationProperty.INFO_CODE))
                .addColumn(PuquoteorderProperty.PUQO_TAX)
                .addColumn(OrmParamEx.column(TaxrateEntity.class, InformationProperty.INFO_NAME))
                .addColumn(OrmParamEx.column(TaxrateEntity.class, InformationProperty.INFO_CODE))
                .addColumn(PuquoteorderProperty.PUQO_REMARK)
        ;

//        ormParamEx
//                .leftJoin(EmployeeEntity.class,
//                        OrmParamEx.joinLinkInDifferentTable
//                                (EmployeeEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_ADDUSER))
//                .leftJoin(DepttreeEntity.class,
//                        OrmParamEx.joinLinkInDifferentTable
//                                (DepttreeEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_DEPT))
//                .leftJoin(JobpositionEntity.class,
//                        OrmParamEx.joinLinkInDifferentTable
//                                (JobpositionEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_DUTY))
//                .leftJoin(CurrencyEntity.class,
//                        OrmParamEx.joinLinkInDifferentTable
//                                (CurrencyEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_CURR))
//                .leftJoin(TaxrateEntity.class,
//                        OrmParamEx.joinLinkInDifferentTable
//                                (TaxrateEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_TAX))
//        ;

        ormParamEx.setWhereExp(OrmParamEx.and(
                ormParamEx.getEqualXML(
                        OrmParamEx.column(PuquoteorderEntity.class, NodeConstant.ID), "orderId")
        ));

        List<Map<String, Object>> list = ormService.selectMapListEx(PuquoteorderEntity.class, ormParamEx);

        System.out.println(OrmParamEx.column(PuquoteorderEntity.class, NodeConstant.ID).replaceAll("\\.", "_"));

        if (null != list && list.size() > 0) {
            Map<String, Object> map = list.get(0);
            System.out.println(JSON.toJSONString(map, true));
        }

    }

    /**
     * SELECT puqo_puqo_price_seta.*,
     * gods_code,gods_name,gods_model,
     * rpak_code,rpak_name
     * from puqo_puqo_price_seta
     * LEFT JOIN goods
     * ON goods.id=puqo_puqo_price_seta.puqo_goods
     * LEFT JOIN park
     * ON park.id= puqo_puqo_price_seta.puqo_park
     * WHERE puqo_puqo_price_seta.pid = '11'
     * ORDER BY gods_code ASC
     * <p>
     * ==> 转化后sql
     * SELECT puqo_isladder_old, puqo_oqty_min_old, gods_model, puqo_oqty_max_old, puqo_rebate_old
     * , puqo_rebate, puqo_park, puqo_um_group, puqo_oqty_max, gods_name
     * , puqo_price_old, puqo_date_beg, rpak_code, puqo_istatus, puqo_date_beg_old
     * , puqo_isladder, puqo_goods, puqo_oqty_min, puqo_updown_rate, puqo_price
     * , puqo_date_end_old, puqo_date_end, gods_code, puqo_um, rpak_name
     * FROM puqo_puqo_price_seta
     * LEFT JOIN goods ON goods.id = puqo_puqo_price_seta.puqo_goods
     * LEFT JOIN park ON park.id = puqo_puqo_price_seta.puqo_park
     * WHERE puqo_puqo_price_seta.pid = ?
     * AND (goods.is_del = 0
     * AND park.is_del = 0)
     * ORDER BY gods_code ASC
     *
     * @throws Exception
     */
    @Test
    public void testLoadPuquoteorderSetnfo() throws Exception {
        OrmParamEx ormParamEx = new OrmParamEx();
        List<String> columns = new ArrayList<>();
        Field[] fields = PuqoPuqoPriceSetaProperty.class.getFields();
        for (Field f : fields) {
            columns.add(f.get(f.getName()).toString());
        }
        columns.add(GoodsProperty.GODS_CODE);
        columns.add(GoodsProperty.GODS_NAME);
        columns.add(GoodsProperty.GODS_MODEL);
        columns.add(ParkProperty.RPAK_CODE);
        columns.add(ParkProperty.RPAK_NAME);

        ormParamEx.setColumns(columns);

//        ormParamEx
//                .leftJoin(GoodsEntity.class,
//                        OrmParamEx.joinLinkInDifferentTable
//                                (GoodsEntity.class, NodeConstant.ID, PuqoPuqoPriceSetaEntity.class, PuqoPuqoPriceSetaProperty.PUQO_GOODS))
//                .leftJoin(ParkEntity.class,
//                        OrmParamEx.joinLinkInDifferentTable
//                                (ParkEntity.class, NodeConstant.ID, PuqoPuqoPriceSetaEntity.class, PuqoPuqoPriceSetaProperty.PUQO_PARK));
//
//        ormParamEx.setWhereExp(OrmParamEx.and(
//                ormParamEx.getEqualXML(
//                        OrmParamEx.column(ParkEntity.class, NodeConstant.ID), "")
//        ));

        ormParamEx.setOrderExp(SQLSortEnum.ASC, GoodsProperty.GODS_CODE);

        List<Map<String, Object>> list = ormService.selectMapListEx(PuqoPuqoPriceSetaEntity.class, ormParamEx);

        if (null != list && list.size() > 0) {
            for (Map<String, Object> map : list) {
                System.out.println(JSON.toJSONString(map, true));
            }
        }
        // System.out.println(ormService.count(PuqoPuqoPriceSetaEntity.class, ormParamEx));

//        ormService.selectPagedMapListEx()

    }

    @Test
    public void testLoadPuquoteorderParkList() throws Exception {

        OrmParamEx ormParamEx = new OrmParamEx();
        ormParamEx.addColumn(GodsGodsParkSetaProperty.GODS_PARK)
                .addColumn(ParkProperty.RPAK_CODE)
                .addColumn(ParkProperty.RPAK_NAME);
//        ormParamEx
//                .leftJoin(GodsGodsParkSetaEntity.class,
//                        OrmParamEx.joinLinkInDifferentTable
//                                (GodsGodsParkSetaEntity.class, NodeConstant.PID, GoodsEntity.class, NodeConstant.ID))
//                .leftJoin(GodsGodsPupricSetaEntity.class,
//                        "(" + OrmParamEx.joinLinkInDifferentTable
//                                (GodsGodsPupricSetaEntity.class, NodeConstant.PID, GoodsEntity.class, NodeConstant.ID) + " and "
//                                + OrmParamEx.joinLinkInDifferentTable
//                                (GodsGodsParkSetaEntity.class, NodeConstant.ID, GodsGodsPupricSetaEntity.class, "gods_ppark") + ")")
//                .leftJoin(ParkEntity.class,
//                        OrmParamEx.joinLinkInDifferentTable
//                                (ParkEntity.class, NodeConstant.ID, GodsGodsParkSetaEntity.class, GodsGodsParkSetaProperty.GODS_PARK));
        ormParamEx.setWhereExp(OrmParamEx.and(
                ormParamEx.getEqualXML(
                        GoodsProperty.GODS_CODE, '1')
                , ormParamEx.getInXML(GodsGodsParkSetaProperty.GODS_STATUS, new String[]{"UP", "AC"}),
                ormParamEx.getEqualXML(GodsGodsParkSetaProperty.GODS_SUPPLIER, '1'),
                ormParamEx.getEqualXML(GodsGodsPupricSetaProperty.GODS_SUPP, '1')
        ));

        List<Map<String, Object>> list = ormService.selectMapListEx(GoodsEntity.class, ormParamEx);

        if (null != list && list.size() > 0) {
            for (Map<String, Object> map : list) {
                System.out.println(JSON.toJSONString(map, true));
            }
        }

    }


    @Test
    public void savePuquoteOrder() throws Exception {
    }

    @Test
    public void submitPuquoteOrder() throws Exception {
    }

    @Test
    public void getOrderBaseData() throws Exception {
        String orderId="3ba6e76407524a379e8d302af85dc749";
        OrmParamEx ormParamEx = new OrmParamEx();

        String orderColumnId = OrmParamEx.column(PuquoteorderEntity.class, NodeConstant.ID);

        ormParamEx.addColumn(orderColumnId)
                .addColumn(OrderProperty.ORDE_ADDUSER)
                .addColumn(OrderProperty.ORDE_DATE)
                .addColumn(OrderProperty.ORDE_DEPT)
                .addColumn(OrderProperty.ORDE_DUTY)
                .addColumn(OrderProperty.ORDE_NBR)
                .addColumn(OrderProperty.ORDE_RODE_OBJ)
                .addColumn(EmployeeProperty.REMP_NO)
                .addColumn(EmployeeProperty.REMP_NAME)
                .addColumn(DepttreeProperty.MDEP_NAME)
                .addColumn(JobpositionProperty.RPOS_NAME)
                .addColumn(PuquoteorderProperty.PUQO_CODE_SUPP)
                .addColumn(PuquoteorderProperty.PUQO_SNAME_SUPP)
                .addColumn(PuquoteorderProperty.PUQO_CURR)
                .addColumn(CurrencyProperty.CURR_NAME)
                .addColumn(CurrencyProperty.CURR_CODE)
                .addColumn(PuquoteorderProperty.PUQO_TAX)
                .addColumn(TaxrateProperty.TAXR_NAME)
                .addColumn(PuquoteorderProperty.PUQO_REMARK)
        ;

        ormParamEx
                .leftJoin(EmployeeEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (EmployeeEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_ADDUSER), DataVailidEnum.NOMATTER)
                .leftJoin(DepttreeEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (DepttreeEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_DEPT), DataVailidEnum.NOMATTER)
                .leftJoin(JobpositionEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (JobpositionEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_DUTY), DataVailidEnum.NOMATTER)
                .leftJoin(CurrencyEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (CurrencyEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_CURR),DataVailidEnum.NOMATTER)
                .leftJoin(TaxrateEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (TaxrateEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_TAX), DataVailidEnum.NOMATTER)
        ;

        ormParamEx.setWhereExp(OrmParamEx.and(
                ormParamEx.getEqualXML(orderColumnId, orderId),ormParamEx.getEqualXML(OrmParamEx.column(PuquoteorderEntity.class, "is_del"),"0")
        ));
        List<Map<String, Object>> list = ormService.selectMapListEx(PuquoteorderEntity.class, ormParamEx);
        if (null != list && list.size() > 0) {
            JSONObject data = new JSONObject();
            Map<String, Object> map = list.get(0);
            // 单据id
            data.put(NodeConstant.ID, NullUtils.valueOf(map.get(NodeConstant.ID)));
            // 申请人id
            String employeeId = NullUtils.valueOf(map.get(OrderProperty.ORDE_ADDUSER));
            data.put(OrderProperty.ORDE_ADDUSER, employeeId);
            data.put(OrderProperty.ORDE_STATUS, NullUtils.valueOf(map.get(OrderProperty.ORDE_STATUS)));
            // 申请部门id
            String deptId = NullUtils.valueOf(map.get(OrderProperty.ORDE_DEPT));
            data.put(OrderProperty.ORDE_DEPT, deptId);
            // 申请岗位id
            String postId = NullUtils.valueOf(map.get(OrderProperty.ORDE_DUTY));
            data.put(OrderProperty.ORDE_DUTY, postId);
            // 单据单号
            data.put(OrderProperty.ORDE_NBR, NullUtils.valueOf(map.get(OrderProperty.ORDE_NBR)));
            // 单据定义id
            data.put(OrderProperty.ORDE_RODE_OBJ, NullUtils.valueOf(map.get(OrderProperty.ORDE_RODE_OBJ)));
            // 供应商id
            data.put(PuquoteorderProperty.PUQO_CODE_SUPP, NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_CODE_SUPP)));
            // 供应商简称
            data.put(PuquoteorderProperty.PUQO_SNAME_SUPP, NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_SNAME_SUPP)));
            // 币别id
            String currencyId = NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_CURR));
            data.put(PuquoteorderProperty.PUQO_CURR, currencyId);
            // 税率id
            String taxId = NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_TAX));
            data.put(PuquoteorderProperty.PUQO_TAX, taxId);
            // 备注
            data.put(PuquoteorderProperty.PUQO_REMARK, NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_REMARK)));
            // 申请单据日期
            Object ordeDateStr = map.get(OrderProperty.ORDE_DATE);
            if (!StringUtil.isNullOrEmpty(ordeDateStr)) {
                Timestamp ordeDate = (Timestamp) ordeDateStr;
                data.put(OrderProperty.ORDE_DATE, DateUtil.formatDate(new Date(ordeDate.getTime())));
            } else {
                data.put(OrderProperty.ORDE_DATE, "");
            }
            // 申请人工号
            data.put(EmployeeProperty.REMP_NO,NullUtils.valueOf(map.get(EmployeeProperty.REMP_NO)));
            // 申请人名字
            data.put(EmployeeProperty.REMP_NAME,NullUtils.valueOf(map.get(EmployeeProperty.REMP_NAME)));
            //  申请部门名称
            data.put(DepttreeProperty.MDEP_NAME,NullUtils.valueOf(map.get(DepttreeProperty.MDEP_NAME)));
            // 申请岗位名称
            data.put(JobpositionProperty.RPOS_NAME,NullUtils.valueOf(map.get(JobpositionProperty.RPOS_NAME)));
            // 币别code
            data.put(CurrencyProperty.CURR_CODE,NullUtils.valueOf(map.get(CurrencyProperty.CURR_CODE)));
            // 币别name
            data.put(CurrencyProperty.CURR_NAME,NullUtils.valueOf(map.get(CurrencyProperty.CURR_NAME)));
            // 税率名称
            data.put(TaxrateProperty.TAXR_NAME,NullUtils.valueOf(map.get(TaxrateProperty.TAXR_NAME)));

           JsonUtils.underLine2Camel(data);
            System.out.println(JSON.toJSONString(data,true));
        }

    }

    @Test
    public void getOrderSetData() throws Exception {

        OrmParamEx ormParamEx = new OrmParamEx();
        List<String> columns = new ArrayList<>();
        List<String> columnsSetb = new ArrayList<>();
        List<String> columnsSetOldb = new ArrayList<>();

        columnsSetb.add(PuqoPuqoLadderSetbProperty.PUQO_LUPDOWN_RATE);
        columnsSetb.add(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MAX);
        columnsSetb.add(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MIN);
        columnsSetb.add(PuqoPuqoLadderSetbProperty.PUQO_LPRICE);

        columnsSetOldb.add(PuqoPuqoLadderSetOldbProperty.PUQO_LPRICE_OLD);
        columnsSetOldb.add(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MIN_OLD);
        columnsSetOldb.add(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MAX_OLD);

        String columnId = OrmParamEx.column(PuqoPuqoPriceSetaEntity.class, NodeConstant.ID);
        columns.add(columnId);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG_OLD);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER_OLD);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_GOODS);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_UPDOWN_RATE);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_REBATE_OLD);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_PRICE);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_REBATE);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_END_OLD);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_PARK);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_END);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_UM_GROUP);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_UM);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_PRICE_OLD);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_ISTATUS);
        columns.add(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG_OLD);
        columns.add(GoodsProperty.GODS_CODE);
        columns.add(GoodsProperty.GODS_NAME);
        columns.add(GoodsProperty.GODS_MODEL);
        columns.add(ParkProperty.RPAK_CODE);
        columns.add(ParkProperty.RPAK_NAME);
        ormParamEx.setColumns(columns);

        ormParamEx
                .leftJoin(GoodsEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (GoodsEntity.class, NodeConstant.ID, PuqoPuqoPriceSetaEntity.class, PuqoPuqoPriceSetaProperty.PUQO_GOODS),DataVailidEnum.NOMATTER)
                .leftJoin(ParkEntity.class,
                        OrmParamEx.joinLinkInDifferentTable
                                (ParkEntity.class, NodeConstant.ID, PuqoPuqoPriceSetaEntity.class, PuqoPuqoPriceSetaProperty.PUQO_PARK),DataVailidEnum.NOMATTER);

        ormParamEx.setWhereExp(OrmParamEx.and(
                ormParamEx.getEqualXML(
                        OrmParamEx.column(PuqoPuqoPriceSetaEntity.class, NodeConstant.PID), "3ba6e76407524a379e8d302af85dc749"),
                ormParamEx.getEqualXML(
                        OrmParamEx.column(PuqoPuqoPriceSetaEntity.class, "is_del"), "0")
        ));

        ormParamEx.setOrderExp(SQLSortEnum.ASC,GoodsProperty.GODS_CODE);

        List<Map<String, Object>> list = ormService.selectMapListEx(PuqoPuqoPriceSetaEntity.class, ormParamEx);

        JSONObject returnData = new JSONObject();
        JSONArray array = new JSONArray();
        if (null != list && list.size() > 0) {
            for (Map<String, Object> map : list) {
                JSONObject data = new JSONObject();
                String id = NullUtils.valueOf(map.get(columnId.replaceAll("\\.","_")));
                // 物品id
                String goodsId = NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_GOODS));
                data.put(PuqoPuqoPriceSetaProperty.PUQO_GOODS, goodsId);
                // 物品编号// 物品名称// 物品规格
                // 物品编号
                String goodsCode = NullUtils.valueOf(map.get(GoodsProperty.GODS_CODE));
                data.put(GoodsProperty.GODS_CODE,goodsCode);
                // 物品名称
                data.put(GoodsProperty.GODS_NAME,NullUtils.valueOf(map.get(GoodsProperty.GODS_NAME)));
                // 物品规格
                data.put(GoodsProperty.GODS_MODEL,NullUtils.valueOf(map.get(GoodsProperty.GODS_MODEL)));
                // 园区id
                String parkId = NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_PARK));
                data.put(PuqoPuqoPriceSetaProperty.PUQO_PARK, parkId);
                // 园区名称// 园区编码
                // 园区名称
                data.put(ParkProperty.RPAK_NAME,NullUtils.valueOf(map.get(ParkProperty.RPAK_NAME)));
                // 园区编码
                data.put(ParkProperty.RPAK_CODE,NullUtils.valueOf(map.get(ParkProperty.RPAK_CODE)));
                // 单位
                data.put(PuqoPuqoPriceSetaProperty.PUQO_UM, NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_UM)));
                // 涨跌率
                data.put(PuqoPuqoPriceSetaProperty.PUQO_UPDOWN_RATE, NullUtils.doubleValueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_UPDOWN_RATE)));
                // 单价
                data.put(PuqoPuqoPriceSetaProperty.PUQO_PRICE, NullUtils.doubleValueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_PRICE)));
                // 返利额
                data.put(PuqoPuqoPriceSetaProperty.PUQO_REBATE, NullUtils.doubleValueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_REBATE)));
                // 生效日期
                Object str = map.get(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG);
                if (!StringUtil.isNullOrEmpty(str)) {
                    Timestamp date = (Timestamp) str;
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG, DateUtil.formatDate(new Date(date.getTime())));
                } else {
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG, "");
                }
                //失效日期
                str = map.get(PuqoPuqoPriceSetaProperty.PUQO_DATE_END);
                if (!StringUtil.isNullOrEmpty(str)) {
                    Timestamp date = (Timestamp) str;
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_END, DateUtil.formatDate(new Date(date.getTime())));
                } else {
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_END, "");
                }
                // 是否阶梯报价
                String isLadder = NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER));
                data.put(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER, isLadder);
                // 单价_旧
                data.put(PuqoPuqoPriceSetaProperty.PUQO_PRICE_OLD, NullUtils.doubleValueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_PRICE_OLD)));
                // 返利额_旧
                data.put(PuqoPuqoPriceSetaProperty.PUQO_REBATE_OLD, NullUtils.doubleValueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_REBATE_OLD)));
                // 生效日期_旧
                str = map.get(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG_OLD);
                if (!StringUtil.isNullOrEmpty(str)) {
                    Timestamp date = (Timestamp) str;
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG_OLD, DateUtil.formatDate(new Date(date.getTime())));
                } else {
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_BEG_OLD, "");
                }
                //失效日期_旧
                str = map.get(PuqoPuqoPriceSetaProperty.PUQO_DATE_END_OLD);
                if (!StringUtil.isNullOrEmpty(str)) {
                    Timestamp date = (Timestamp) str;
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_END_OLD, DateUtil.formatDate(new Date(date.getTime())));
                } else {
                    data.put(PuqoPuqoPriceSetaProperty.PUQO_DATE_END_OLD, "");
                }
                // 是否阶梯报价_旧
                String isLadderOld = NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER_OLD));
                data.put(PuqoPuqoPriceSetaProperty.PUQO_ISLADDER_OLD, isLadderOld);
                // 审批状态
                data.put(PuqoPuqoPriceSetaProperty.PUQO_ISTATUS, NullUtils.valueOf(map.get(PuqoPuqoPriceSetaProperty.PUQO_ISTATUS)));

                // 阶梯报价属性集
                JSONArray array1 = new JSONArray();
                JSONArray arrayOld1 = new JSONArray();
                if (isLadder.equals("1") || isLadderOld.equals("1")) {
                    OrmParam ormParam = new OrmParam();
                    ormParam.setColumns(columnsSetb);
                    ormParam.setWhereExp(OrmParamEx.and(ormParam.getEqualXML(NodeConstant.PID, id)));
                    List<Map<String, Object>> mapList = ormService.selectMapList(PuqoPuqoLadderSetbEntity.class, ormParam);
                    for (Map<String, Object> map1 : mapList) {
                        JSONObject o = new JSONObject();
                        // 单价
                        o.put(PuqoPuqoLadderSetbProperty.PUQO_LPRICE, NullUtils.doubleValueOf(map1.get(PuqoPuqoLadderSetbProperty.PUQO_LPRICE)));
                        // 阶梯订单量小值
                        o.put(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MIN, NullUtils.intValueOf(map1.get(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MIN)));
                        // 阶梯订单量大值
                        o.put(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MAX, NullUtils.intValueOf(map1.get(PuqoPuqoLadderSetbProperty.PUQO_OQTY_MAX)));
                        // 涨跌率
                        o.put(PuqoPuqoLadderSetbProperty.PUQO_LUPDOWN_RATE, NullUtils.doubleValueOf(map1.get(PuqoPuqoLadderSetbProperty.PUQO_LUPDOWN_RATE)));
                        array1.add(o);
                    }
                    ormParam.reset();
                    ormParam.setColumns(columnsSetOldb);
                    ormParam.setWhereExp(OrmParamEx.and(ormParam.getEqualXML(NodeConstant.PID, id)));
                    mapList = ormService.selectMapList(PuqoPuqoLadderSetOldbEntity.class, ormParam);
                    for (Map<String, Object> map1 : mapList) {
                        JSONObject o = new JSONObject();
                        // 单价
                        o.put(PuqoPuqoLadderSetOldbProperty.PUQO_LPRICE_OLD, NullUtils.doubleValueOf(map1.get(PuqoPuqoLadderSetOldbProperty.PUQO_LPRICE_OLD)));
                        // 阶梯订单量小值
                        o.put(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MIN_OLD, NullUtils.intValueOf(map1.get(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MIN_OLD)));
                        // 阶梯订单量大值
                        o.put(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MAX_OLD, NullUtils.intValueOf(map1.get(PuqoPuqoLadderSetOldbProperty.PUQO_OQTY_MAX_OLD)));
                        arrayOld1.add(o);
                    }
                }
                data.put(PuqoPuqoPriceSetaProperty.PUQO_LADDER_SET, array1);
                data.put(PuqoPuqoPriceSetaProperty.PUQO_LADDER_SET_OLD, arrayOld1);
                // 园区列表
                array.add(data);
            }
        }
        returnData.put(PuquoteorderProperty.PUQO_PRICE_SET, array);
        System.out.println(JSON.toJSONString(returnData,true));
    }

    @Test
    public void deleteTempOrde() throws Exception {


        try {
            OrmParamEx ormParamEx = new OrmParamEx();
            ormParamEx.addColumn(OrmParamEx.column(PuquoteorderEntity.class, NodeConstant.ID))
                    .addColumn(OrderProperty.ORDE_NBR).addColumn(OrderProperty.ORDE_ADDUSER)
                    .addColumn(EmployeeProperty.REMP_NAME).addColumn(OrderProperty.ORDE_STATUS)
                    .addColumn(OrderProperty.ORDE_DATE).addColumn(PuquoteorderProperty.PUQO_REMARK)
                    .addColumn(PuquoteorderProperty.PUQO_CURR)
                    .addColumn(CurrencyProperty.CURR_CODE).addColumn(CurrencyProperty.CURR_NAME)
                    .addColumn(PuquoteorderProperty.PUQO_CODE_SUPP).addColumn(PuquoteorderProperty.PUQO_SNAME_SUPP)
                    .addColumn(RelationProperty.RELA_CODE);

            ormParamEx.leftJoin(RelationEntity.class,
                    OrmParamEx.joinLinkInDifferentTable(RelationEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_CODE_SUPP), DataVailidEnum.NOMATTER);
            ormParamEx.leftJoin(EmployeeEntity.class,
                    OrmParamEx.joinLinkInDifferentTable(EmployeeEntity.class, NodeConstant.ID, PuquoteorderEntity.class, OrderProperty.ORDE_ADDUSER),DataVailidEnum.NOMATTER);
            ormParamEx.leftJoin(CurrencyEntity.class,
                    OrmParamEx.joinLinkInDifferentTable(CurrencyEntity.class, NodeConstant.ID, PuquoteorderEntity.class, PuquoteorderProperty.PUQO_CURR),DataVailidEnum.NOMATTER);

            ormParamEx.setWhereExp(OrmParamEx.and(ormParamEx.getEqualXML(OrmParamEx.column(PuquoteorderEntity.class, "is_del"),0)));
            ormParamEx.setOrderExp(SQLSortEnum.DESC, OrderProperty.ORDE_DATE);

            PageHelper.startPage(1, 10, false);
            List<Map<String, Object>> dataList = ormService.selectMapListEx(PuquoteorderEntity.class, ormParamEx);

            System.out.println(JSON.toJSONString(dataList, true));

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }

    }

    /**
     *SELECT puquoteorder.id,orde_nbr,orde_adduser,employee.remp_name,orde_status, orde_date,puqo_remark,puqo_curr,currency.curr_code,currency.curr_name,puqo_code_supp,puqo_sname_supp,relation.rela_code from puquoteorder LEFT JOIN relation ON relation.id=puqo_code_supp LEFT JOIN employee on employee.id=orde_adduser LEFT JOIN currency ON currency.id = puquoteorder.puqo_curr WHERE puquoteorder.is_del=0 ORDER BY orde_date desc limit 0,10
     *  SELECT count(*) as count from puquoteorder LEFT JOIN relation ON relation.id=puqo_code_supp LEFT JOIN employee on employee.id=orde_adduser WHERE puquoteorder.is_del=0
     *
     *   SELECT puquoteorder.id,orde_nbr,orde_adduser,employee.remp_name,orde_status, orde_date,puqo_remark,puqo_curr,currency.curr_code,currency.curr_name,puqo_code_supp,puqo_sname_supp,relation.rela_code from puquoteorder LEFT JOIN relation ON relation.id=puqo_code_supp LEFT JOIN employee on employee.id=orde_adduser LEFT JOIN currency ON currency.id = puquoteorder.puqo_curr
     *   WHERE puquoteorder.is_del=0
     *   and orde_nbr LIKE '%T1%'
     *   and (relation.rela_code like '%CA%' or puqo_sname_supp like '%CA%')
     *   and orde_date>='2018-01-01'
     *   and orde_date<='2018-02-01'
     *   and orde_status='1'
     *   and puqo_curr='1'
     *   and employee.remp_name like '%林少龙%'
     *   ORDER BY orde_date desc limit 50,50
     *
     *    SELECT count(*) as count from puquoteorder
     *    LEFT JOIN relation ON relation.id=puqo_code_supp
     *    LEFT JOIN employee on employee.id=orde_adduser
     *    WHERE puquoteorder.is_del=0 and orde_nbr LIKE '%T1%' and (relation.rela_code like '%CA%' or puqo_sname_supp like '%CA%') and orde_date>='2018-01-01' and orde_date<='2018-02-01' and orde_status='1' and puqo_curr='1' and employee.remp_name like '%林少龙%'
     *
     *
     */

    @Test
    public void queryPuquOrders() {
        PuqoOrderQueryDTO queryDTO = new PuqoOrderQueryDTO();
//        queryDTO.setOrderNbrKey("T1");
//        queryDTO.setOrdeStatus("1");
//        queryDTO.setStartTime("2018-01-01");
//        queryDTO.setEndTime("2018-02-01");
//        queryDTO.setCurrenyId("1");
//        queryDTO.setOrdeUserNameKey("林少龙");
//        queryDTO.setSuppKey("CA");
//        queryDTO.setPage(2);
//        queryDTO.setRows(50);

        String baseSql="SELECT puquoteorder.id,orde_nbr,orde_adduser,employee.remp_name,orde_status, orde_date,puqo_remark,puqo_curr,currency.curr_code,currency.curr_name,puqo_code_supp,puqo_sname_supp,relation.rela_code from puquoteorder \n" +
                "LEFT JOIN relation ON relation.id=puqo_code_supp \n" +
                "LEFT JOIN employee on employee.id=orde_adduser \n"+
                "LEFT JOIN currency ON currency.id = puquoteorder.puqo_curr\n ";

        String countBaseSql = "SELECT count(*) as count from puquoteorder \n" +
                "LEFT JOIN relation ON relation.id=puqo_code_supp \n" +
                "LEFT JOIN employee on employee.id=orde_adduser \n";

        String whereSql=" WHERE puquoteorder.is_del=0 \n";

        //单据单号 模糊查询关键字
        String orderNbrKey=queryDTO.getOrderNbrKey();
        if(!StringUtil.isNullOrEmpty(orderNbrKey)){
            whereSql+=" and orde_nbr LIKE '%"+orderNbrKey+"%'";
        }
        // 供应商模糊查询关键字
        String suppKey = queryDTO.getSuppKey();
        if(!StringUtil.isNullOrEmpty(suppKey)){
            whereSql+=" and (relation.rela_code like '%"+suppKey+"%' or puqo_sname_supp like '%"+suppKey+"%')";
        }
        //申请时间开始日期
        String startTime= queryDTO.getStartTime();
        if(!StringUtil.isNullOrEmpty(startTime)){
            whereSql+=" and orde_date>='"+startTime+"'";
        }
        // 申请时间结束时间
        String endTime= queryDTO.getEndTime();
        if(!StringUtil.isNullOrEmpty(endTime)){
            whereSql+=" and orde_date<='"+endTime+"'";
        }
        // 报价单状态
        String ordeStatus= queryDTO.getOrdeStatus();
        if(!StringUtil.isNullOrEmpty(ordeStatus)){
            whereSql+=" and orde_status='"+ordeStatus+"'";
        }
        //币别id
        String currId = queryDTO.getCurrenyId();
        if(!StringUtil.isNullOrEmpty(currId)){
            whereSql+=" and puqo_curr='"+currId+"'";
        }
        // 申请人模糊查询关键字
        String ordeUserNameKey = queryDTO.getOrdeUserNameKey();
        if(!StringUtil.isNullOrEmpty(ordeUserNameKey)){
            whereSql+=" and employee.remp_name like '%"+ordeUserNameKey+"%'";
        }
        String orderBySql=" ORDER BY orde_date desc ";
        int page = queryDTO.getPage();
        int rows = queryDTO.getRows();
        String limitSql=" limit "+ (page - 1) * rows + "," + rows;

        String querySql=baseSql+whereSql+orderBySql+limitSql;
        String countSql =countBaseSql+whereSql;

        try{
            Result result = new Result();
            JSONObject returnData = new JSONObject();
            List<Map<String,Object>> dataList = ormService.getDataBySql(querySql);
            List<Map<String,Object>> countList = ormService.getDataBySql(countSql);
            long totalSize = Long.parseLong(NullUtils.valueOf(countList.get(0).get("count")));
            JSONArray array = new JSONArray();
            if(null!=dataList&&dataList.size()>0){
                for(Map<String,Object> map:dataList){
                    JSONObject object = new JSONObject();
                    // 单据id
                    object.put(NodeConstant.ID,NullUtils.valueOf(map.get(NodeConstant.ID)));
                    // 单据单号
                    object.put(OrderProperty.ORDE_NBR,NullUtils.valueOf(map.get(OrderProperty.ORDE_NBR)));
                    // 申请人id
                    object.put(OrderProperty.ORDE_ADDUSER,NullUtils.valueOf(map.get(OrderProperty.ORDE_ADDUSER)));
                    // 申请人姓名
                    object.put(EmployeeProperty.REMP_NAME,NullUtils.valueOf(map.get(EmployeeProperty.REMP_NAME)));
                    //单据状态
                    object.put(OrderProperty.ORDE_STATUS,NullUtils.valueOf(map.get(OrderProperty.ORDE_STATUS)));
                    // 申请日期
                    Object str = map.get(OrderProperty.ORDE_DATE);
                    if (!StringUtil.isNullOrEmpty(str)) {
                        Timestamp date = (Timestamp) str;
                        object.put(OrderProperty.ORDE_DATE, DateUtil.formatDate(new Date(date.getTime())));
                    } else {
                        object.put(OrderProperty.ORDE_DATE, "");
                    }
                    // 单据备注
                    object.put(PuquoteorderProperty.PUQO_REMARK,NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_REMARK)));
                    // 币别id
                    String currObjId = NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_CURR));
                    object.put(PuquoteorderProperty.PUQO_CURR,currObjId);
                    // 币别名称和code
                    object.put(CurrencyProperty.CURR_CODE,NullUtils.valueOf(map.get(CurrencyProperty.CURR_CODE)));
                    object.put(CurrencyProperty.CURR_NAME,NullUtils.valueOf(map.get(CurrencyProperty.CURR_NAME)));
                    // 供应商id
                    object.put(PuquoteorderProperty.PUQO_CODE_SUPP,NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_CODE_SUPP)));
                    // 供应商编号
                    object.put(RelationProperty.RELA_CODE,NullUtils.valueOf(map.get(RelationProperty.RELA_CODE)));
                    // 供应商简称
                    object.put(PuquoteorderProperty.PUQO_SNAME_SUPP,NullUtils.valueOf(map.get(PuquoteorderProperty.PUQO_SNAME_SUPP)));

                    array.add(object);
                }
            }
            returnData.put("total",totalSize);
            returnData.put("list",array);
            returnData.put("page",page);
            returnData.put("rows",rows);
            JsonUtils.underLine2Camel(returnData);

            result.setErrMsg("查询数据成功");
            result.setData(returnData);

            System.out.println(JSON.toJSONString(result));

        } catch (Exception e) {
            e.printStackTrace();
            throw new ApplicationException(Result.RECODE_ERROR, e.getMessage());
        }

    }

}




