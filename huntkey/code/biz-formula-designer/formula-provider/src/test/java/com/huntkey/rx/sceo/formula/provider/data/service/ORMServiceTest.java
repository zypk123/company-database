package com.huntkey.rx.sceo.formula.provider.data.service;

import com.huntkey.rx.sceo.formula.common.model.vo.ConditionsVo;
import com.huntkey.rx.sceo.formula.common.model.vo.EsHbaseCriteriaVo;
import com.huntkey.rx.sceo.formula.provider.FormulaProviderApplication;
import com.huntkey.rx.sceo.formula.provider.engine.feign.DataSharingService;
import com.huntkey.rx.sceo.formula.provider.utils.ORMUtils;
import com.huntkey.rx.sceo.formula.provider.utils.RestUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by chenfei on 2017/7/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = FormulaProviderApplication.class)
public class ORMServiceTest {

    @Autowired
    private DataSharingService dataSharingService;

    //private final String ORM_URL = "http://192.168.13.34:2008/servicecenter/find";
    private final String ORM_URL = "http://SERVICECENTER-PROVIDER/servicecenter/find";

    @Test
    public void testQueryDataFromORM() {
        Long startTime = System.currentTimeMillis();

        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateNowStr = sdf.format(d);

        List<ConditionsVo> conditionsStaf121 = new ArrayList<ConditionsVo>();
        ConditionsVo condStaf121 = new ConditionsVo();
        condStaf121.setAttr("staf001");
        condStaf121.setOperator("=");
        condStaf121.setValue("kongquanvalidateupdate");
        conditionsStaf121.add(condStaf121);

        EsHbaseCriteriaVo queryStaf121 = ORMUtils.getEsHbaseCriteria("staff",new String[]{ "staf121"},conditionsStaf121,null);
        String restStaf121 = RestUtils.doPost(ORM_URL,queryStaf121);
        String staff121 = (String)ORMUtils.getValByPara(ORMUtils.getDataSet(restStaf121), "staf121");
        System.out.println(staff121);

        List<ConditionsVo> conditionsJobp007 = new ArrayList<ConditionsVo>();
        ConditionsVo condJobp007 = new ConditionsVo();
        condJobp007.setAttr("id");
        condJobp007.setOperator("=");
        condJobp007.setValue(staff121);
        conditionsJobp007.add(condJobp007);

        ConditionsVo condJobp0072 = new ConditionsVo();
        condJobp0072.setAttr("jobp005");
        condJobp0072.setOperator("<=");
        condJobp0072.setValue(dateNowStr);
        conditionsJobp007.add(condJobp0072);

        ConditionsVo condJobp0073 = new ConditionsVo();
        condJobp0073.setAttr("jobp006");
        condJobp0073.setOperator(">=");
        condJobp0073.setValue(dateNowStr);
        conditionsJobp007.add(condJobp0073);

        EsHbaseCriteriaVo queryJobp007 = ORMUtils.getEsHbaseCriteria("jobposition",new String[]{ "jobp007"},conditionsJobp007,null);
        String restJobp007 = RestUtils.doPost(ORM_URL,queryJobp007);
        String jobp007 = (String)ORMUtils.getValByPara(ORMUtils.getDataSet(restJobp007), "jobp007");
        System.out.println(jobp007);

        List<ConditionsVo> conditionsJobp009 = new ArrayList<ConditionsVo>();
        ConditionsVo condJobp009 = new ConditionsVo();
        condJobp009.setAttr("id");
        condJobp009.setOperator("=");
        condJobp009.setValue(jobp007);
        conditionsJobp009.add(condJobp009);


        ConditionsVo condJobp0092 = new ConditionsVo();
        condJobp0092.setAttr("jobp005");
        condJobp0092.setOperator("<=");
        condJobp0092.setValue(dateNowStr);
        conditionsJobp009.add(condJobp0092);

        ConditionsVo condJobp0093 = new ConditionsVo();
        condJobp0093.setAttr("jobp006");
        condJobp0093.setOperator(">=");
        condJobp0093.setValue(dateNowStr);
        conditionsJobp009.add(condJobp0093);

        EsHbaseCriteriaVo queryJobp009 = ORMUtils.getEsHbaseCriteria("jobposition",new String[]{ "jobp009"},conditionsJobp009,null);
        String restJobp009 = RestUtils.doPost(ORM_URL,queryJobp009);
        String jobp009 = (String)ORMUtils.getValByPara(ORMUtils.getDataSet(restJobp009), "jobp009");
        System.out.println(jobp009);

        List<ConditionsVo> conditionsStaf001 = new ArrayList<ConditionsVo>();
        ConditionsVo condStaf001 = new ConditionsVo();
        condStaf001.setAttr("id");
        condStaf001.setOperator("=");
        condStaf001.setValue(jobp009);
        conditionsStaf001.add(condStaf001);

        EsHbaseCriteriaVo queryStaf001 = ORMUtils.getEsHbaseCriteria("staff",new String[]{ "staf001"},conditionsStaf001,null);
        String restStaf001 = RestUtils.doPost(ORM_URL,queryStaf001);
        String staff001 = (String)ORMUtils.getValByPara(ORMUtils.getDataSet(restStaf001), "staf001");
        System.out.println(staff001);
        System.out.println(System.currentTimeMillis() - startTime);

    }
}
