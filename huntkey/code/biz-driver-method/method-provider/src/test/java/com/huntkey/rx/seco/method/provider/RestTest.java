package com.huntkey.rx.seco.method.provider;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.huntkey.rx.commons.utils.rest.Result;
import com.huntkey.rx.sceo.method.provider.MethodProviderApplication;
import com.huntkey.rx.sceo.method.provider.entity.ParamsVo;
import com.huntkey.rx.sceo.method.provider.util.RestUtil;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by lulx on 2017/10/17 0017 下午 2:34
 */

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MethodProviderApplication.class)
public class RestTest {

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setupMockMvc() throws Exception {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void testPut() {
        Result exchange = RestUtil.doPut("http://driver-service-demo/index/put","中文测试");
        System.out.println(exchange);
    }

    @Test
    public void testPost(){
        Result exchange = RestUtil.doPost("http://driver-service-demo/index/post", "中文测试");
        System.out.println(exchange);
    }

    @Test
    public void testGet(){
        Result exchange = RestUtil.doGet("http://driver-service-demo/index/get?params={params}", "{params:{\"id\":\"00e9a2b759114522a4dab5be28a3c19b\"}}");
        System.out.println(exchange);
    }

    @Test
    public void testloadJobPosition(){
        Result exchange = RestUtil.doGet("http://driver-service-demo/jobPosition/loadJobPosition?params={params}", "{params:{\"id\":\"00e9a2b759114522a4dab5be28a3c19b\"}}");
        System.out.println(exchange);
    }

    @Test
    public void testDelete(){
        Result exchange = RestUtil.doDelete("http://driver-service-demo/index/delete", "中文测试");
        System.out.println(exchange);
    }

    @Test
    public void testPatch(){
        Result exchange = RestUtil.doPatch("http://driver-service-demo/index/patch", "中文测试");
        System.out.println(exchange);
    }

    @Test
    public void testAutoExec() throws Exception {
//        String formulaId = "";
//        String methodUrl = "";
//        String reqType = "";
//        String params = "{id:\"213455\"}";
//        String execRate = "1";
//        exceServiceImpl.exceAuto(formulaId,methodUrl,reqType,params,execRate);

        //构造添加的用户信息
        ParamsVo vo = new ParamsVo();
        vo.setClassName("tetsClassName");
        vo.setMethodName("testMethodName");
        Map<String, Object> paramObj = new HashMap<String, Object>();
        vo.setParamObj(paramObj);

        ObjectMapper mapper = new ObjectMapper();

        mockMvc.perform(MockMvcRequestBuilders.post("/methodExce/exce")
                .content(mapper.writeValueAsString(vo))
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
    }
}
