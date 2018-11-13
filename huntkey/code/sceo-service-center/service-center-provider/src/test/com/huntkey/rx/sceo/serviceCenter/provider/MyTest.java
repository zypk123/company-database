//package com.huntkey.rx.sceo.serviceCenter.provider;
//
//import com.alibaba.fastjson.JSONObject;
//import com.huntkey.rx.sceo.serviceCenter.provider.client.EDMClient;
//import com.huntkey.rx.sceo.serviceCenter.provider.core.DataValidateImpl;
//import com.huntkey.rx.sceo.serviceCenter.provider.core.FullInputArgument;
//import com.huntkey.rx.sceo.serviceCenter.provider.core.ServiceData;
//import com.huntkey.rx.sceo.serviceCenter.provider.orm.core.DBType;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mybatis.spring.annotation.MapperScan;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
//import org.springframework.cloud.netflix.feign.EnableFeignClients;
//import org.springframework.context.annotation.Import;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
///**
// * Created by zhanglb on 2017/7/21.
// */
//@SpringBootTest
//@EnableDiscoveryClient
//@MapperScan(basePackages = "com.huntkey.rx.sceo.commonService.provider")
//@Import(CommonProviderApplication.class)
//@EnableFeignClients(basePackages = "com.huntkey.rx.sceo.serviceCenter.provider.client")
//@RunWith(SpringJUnit4ClassRunner.class)
//public class MyTest {
//
//    private static Logger logger = LoggerFactory.getLogger(MyTest.class);
//
//    @Autowired
//    private ServiceUtil serviceUtil;
//    @Autowired
//    private DataValidateImpl dataValidateImpl;
//
//    @Autowired
//    private EDMClient edmClient;
//
//    @Autowired
//    private CenterDataController centerDataController;
//
//    @Test
////    @Ignore
//    public void checkAdd(){
//        String jsonStr = "{\n" +
//                "    \"edmName\": \"earth\",\n" +
//                "    \"columns\": [\n" +
//                "        \"user001\",\n" +
//                "        \"user002\",\n" +
//                "        \"user003\"\n" +
//                "    ],\n" +
//                "    \"esFileds\": [\n" +
//                "        \"user001\",\n" +
//                "        \"user002\",\n" +
//                "        \"user003\"\n" +
//                "    ],\n" +
//                "    \"conditions\": [\n" +
//                "        {\n" +
//                "            \"attr\": \"user001\",\n" +
//                "            \"operator\": \"=\",\n" +
//                "            \"value\": \"zhang\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"attr\": \"user003\",\n" +
//                "            \"operator\": \"=\",\n" +
//                "            \"value\": \"21\"\n" +
//                "        }\n" +
//                "    ],\n" +
//                "    \"pagenation\": {\n" +
//                "        \"startPage\": \"1\",\n" +
//                "        \"rows\": \"50\"\n" +
//                "    },\n" +
//                "    \"orderBy\": [\n" +
//                "        {\n" +
//                "            \"attr\": \"user001\",\n" +
//                "            \"sort\": \"asc\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"attr\": \"user002\",\n" +
//                "            \"sort\": \"desc\"\n" +
//                "        }\n" +
//                "    ],\n" +
//                "    \"dataset\": [\n" +
//                "        {\n" +
//                "            \"id\": \"154813515131fafefafe\",\n" +
//                "            \"user001\": \"74584456\",\n" +
//                "            \"user002\": \"21\",\n" +
//                "            \"user003\": \"zhang\",\n" +
//                "            \"user004\": \"张家界\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"id\": \"fe4813515131fafefaf4\",\n" +
//                "            \"user001\": \"100215455\",\n" +
//                "            \"user002\": \"26\",\n" +
//                "            \"user003\": \"xiao\",\n" +
//                "            \"user004\": \"黄山\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        JSONObject jsonObject = JSONObject.parseObject(jsonStr);
//        ServiceData serviceData = new ServiceData("earth");
//        serviceData.addData(jsonObject);
//        Assert.assertTrue(dataValidateImpl.validate4add(serviceData));
//    }
//
//    @Test
//    public void test(){
////        Result result = edmClient.getOrmAttrEdmCode("V1.004","earth", "");
////        Assert.assertNotNull(result.getData());
//        String jsonStr = "{\n" +
//                "    \"edmName\": \"earth\",\n" +
//                "    \"columns\": [\n" +
//                "        \"user001\",\n" +
//                "        \"user002\",\n" +
//                "        \"user003\"\n" +
//                "    ],\n" +
//                "    \"esFileds\": [\n" +
//                "        \"user001\",\n" +
//                "        \"user002\",\n" +
//                "        \"user003\"\n" +
//                "    ],\n" +
//                "    \"conditions\": [\n" +
//                "        {\n" +
//                "            \"attr\": \"user001\",\n" +
//                "            \"operator\": \"=\",\n" +
//                "            \"value\": \"zhang\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"attr\": \"user003\",\n" +
//                "            \"operator\": \"=\",\n" +
//                "            \"value\": \"21\"\n" +
//                "        }\n" +
//                "    ],\n" +
//                "    \"pagenation\": {\n" +
//                "        \"startPage\": \"1\",\n" +
//                "        \"rows\": \"50\"\n" +
//                "    },\n" +
//                "    \"orderBy\": [\n" +
//                "        {\n" +
//                "            \"attr\": \"user001\",\n" +
//                "            \"sort\": \"asc\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"attr\": \"user002\",\n" +
//                "            \"sort\": \"desc\"\n" +
//                "        }\n" +
//                "    ],\n" +
//                "    \"dataset\": [\n" +
//                "        {\n" +
//                "            \"id\": \"154813515131fafefafe\",\n" +
//                "            \"user001\": \"74584456\",\n" +
//                "            \"user002\": \"21\",\n" +
//                "            \"user003\": \"zhang\",\n" +
//                "            \"user004\": \"张家界\"\n" +
//                "        },\n" +
//                "        {\n" +
//                "            \"id\": \"fe4813515131fafefaf4\",\n" +
//                "            \"user001\": \"100215455\",\n" +
//                "            \"user002\": \"26\",\n" +
//                "            \"user003\": \"xiao\",\n" +
//                "            \"user004\": \"黄山\"\n" +
//                "        }\n" +
//                "    ]\n" +
//                "}";
//        FullInputArgument inputArgument = new FullInputArgument(jsonStr);
//        inputArgument.setEdmName("earth");
//        DBType dbType = serviceUtil.getDBType(inputArgument);
//        logger.info("dbType={}", dbType.getName());
//        Assert.assertEquals("hbase", dbType.getName());
//
//
////
////        String result = centerDataController.getEdmData();
////        Assert.assertNotNull(result);
//    }
//}
